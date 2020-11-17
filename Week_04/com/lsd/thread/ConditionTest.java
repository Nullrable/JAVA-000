package Week_04.com.lsd.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-11-15 22:20
 * @Modified By：
 */
public class ConditionTest {
}


class AarrayBlockQuery {

    private final Object[] items;
    private int capacity;
    private boolean fair;
    private final Condition notEmpty;
    private final Condition notFull;
    private final ReentrantLock lock;

    /** items index for next take, poll, peek or remove */
    int takeIndex;

    /** items index for next put, offer, or add */
    int putIndex;

    /** Number of elements in the queue */
    int count;

    public AarrayBlockQuery(int capacity, boolean fair) {

        if(capacity < 0){
            throw new IllegalArgumentException();
        }

        items = new Object[capacity];

        lock = new ReentrantLock(fair);

        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }

    public void add(Object item)throws InterruptedException {

        lock.lockInterruptibly();
        try {

            while (count == items.length){
                notFull.await();
            }

            enqueue(item);

        }finally {
            lock.unlock();
        }
    }

    public void get() throws InterruptedException{

        lock.lockInterruptibly();

        try {
            while (count == 0) {
                notEmpty.await();
            }
            dequeue();

        }finally {
            lock.unlock();
        }

    }

    /**
     * Inserts element at current put position, advances, and signals.
     * Call only when holding lock.
     */
    private void enqueue(Object x) {
        // assert lock.getHoldCount() == 1;
        // assert items[putIndex] == null;
        final Object[] items = this.items;
        items[putIndex] = x;
        if (++putIndex == items.length)
            putIndex = 0;
        count++;
        notEmpty.signal(); //通知可以消费
    }

    /**
     * Extracts element at current take position, advances, and signals.
     * Call only when holding lock.
     */
    private Object dequeue() {
        // assert lock.getHoldCount() == 1;
        // assert items[takeIndex] != null;
        final Object[] items = this.items;
        @SuppressWarnings("unchecked")
        Object x = (Object) items[takeIndex];
        items[takeIndex] = null;
        if (++takeIndex == items.length)
            takeIndex = 0;
        count--;
//        if (itrs != null)
//            itrs.elementDequeued();
        notFull.signal();
        return x;
    }
}