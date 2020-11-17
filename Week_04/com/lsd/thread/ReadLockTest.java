package Week_04.com.lsd.thread;


import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-11-14 10:20
 * @Modified By：
 */
public class ReadLockTest {

    private static boolean flag = true;


    public static void main(String[] args) throws Exception {


        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(false);

        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();





        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {




                writeLock.lock();

                System.out.println("=========>t2 thread2 running");

                readLock.lock();

                try {
                    Thread.sleep(2000);
                } catch (Exception e) {

                }





                flag = false;

                System.out.println("=========>t2 thread2 set flag false");

                try {
                    Thread.sleep(2000);
                } catch (Exception e) {

                }


                writeLock.unlock();






            }
        });

        t2.start();

        Thread.sleep(1000);

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {


                readLock.lock();


                System.out.println("=========>t3 readLock lock");

                try {
                    Thread.sleep(5000);
                } catch (Exception e) {

                }

                System.out.println("=========>t3 " + flag);


                readLock.unlock();


                System.out.println("=========>t3 readLock unlock");


                System.out.println("=========>t3 thread3 end");
            }
        });
        t3.start();

        Thread.sleep(1000);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {


                readLock.lock();

                System.out.println("=========>t1 readLock lock");

                boolean b = flag;


                System.out.println("=========>t1 " + flag);


                readLock.unlock();


                System.out.println("=========>t1 readLock unlock");
                while (b) {

                }


                System.out.println("=========>t1 thread1 end");
            }
        });
        t1.start();


    }
}
