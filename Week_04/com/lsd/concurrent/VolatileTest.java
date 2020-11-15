package Week_04.com.lsd.concurrent;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-11-14 10:20
 * @Modified By：
 */
public class VolatileTest {

    private static AtomicBoolean flag = new AtomicBoolean(true);


    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag.get()) {

                    //如果什么都不加，线程会一直执行，不会出现CPU上下文切换，所以当Thread2修改了flag，对其也是不可见的
                    //当这个方法块内Sleep后，就能读取到flag的值，跟volatile的效果一样

                }

                System.out.println("=========>thread1 end");
            }
        });
        t1.start();
        t1.join(1, 22);

        Thread.sleep(1000);

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(2000);
                } catch (Exception e) {

                }


                System.out.println("=========>thread2 running");

                flag.set(false);

                System.out.println("=========>thread2 set flag false");
            }
        });

        t2.start();

    }
}
