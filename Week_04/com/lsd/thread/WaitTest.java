package Week_04.com.lsd.thread;

/**
 *
 * 模仿Join
 *
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-11-14 14:46
 * @Modified By：
 */
public class WaitTest {

    public static void main(String[] args) {
        Object oo = new Object();

        WaitThread thread1 = new WaitThread("thread_wait1 -- ");
        thread1.setOo(oo);
        thread1.start();

        synchronized (thread1) {
            for (int i = 0; i < 100; i++) {
                if (i == 20) {
                    try {

                        /**
                         * 将JoinTest改用wait
                         */
                        thread1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " -- " + i);
            }
        }
    }

}


class WaitThread extends Thread {

    private String name;
    private Object oo;

    public void setOo(Object oo) {
        this.oo = oo;
    }

    public WaitThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        synchronized (Thread.currentThread()) {
            for (int i = 0; i < 100; i++) {
                System.out.println(name + i);
            }
            Thread.currentThread().notifyAll();
        }
    }
}

