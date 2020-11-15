package Week_04.com.lsd.concurrent;

import java.util.Date;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-11-14 12:34
 * @Modified By：
 */
public class SynchronizedThreadTest {

    public static void main(String[] args) throws InterruptedException{

        Object oo = new Object();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (Thread.currentThread()) {
                    System.out.println(new Date() + " running");

                    try {
                        Thread.sleep(5000);
                    }catch (Exception e) {

                    }

                }

            }
        });

        t.start();


        System.out.println(new Date() + " main start");

        Thread.sleep(1000);

        synchronized (t) {

            System.out.println(new Date() + " main end");

        }
    }


}
