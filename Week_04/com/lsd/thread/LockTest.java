package Week_04.com.lsd.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-11-15 19:27
 * @Modified By：
 */
public class LockTest {

    public static void main (String[] args)throws Exception {

        Lock lock = new ReentrantLock(true); //公平锁


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                lock.lock(); //获取不到锁的时候，线程会进入time_waiting状态，直到其他线程unlock

                try{
                    System.out.println("Lock_test1 进入等待");
                    TimeUnit.SECONDS.sleep(10);
                    //处理任务
                }catch(Exception ex){

                }finally{
                    lock.unlock();   //释放锁
                }

            }
        });
        t1.setName("Lock_test1");
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {

                lock.lock();

                try{
                    System.out.println("Lock_test2 进入等待");
                    TimeUnit.SECONDS.sleep(10);
                    //处理任务
                }catch(Exception ex){

                }finally{
                    lock.unlock();   //释放锁
                }

            }
        });
        t2.setName("Lock_test2");
//        t2.setDaemon(true);
// 如果这个设置为守护线程，当t1执行完以后，main和t2线程会在之后不确定当线程里直接退出，t2线程不会等待10秒后退出
// 如果是用户线程Daemon = false，则t2会在执行10秒后终止，main则等着t2退出后，再退出

        t2.start();



    }
}
