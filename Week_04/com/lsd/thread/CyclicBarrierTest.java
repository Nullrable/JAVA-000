package Week_04.com.lsd.thread;

import java.util.concurrent.CyclicBarrier;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-11-17 10:40
 * @Modified By：
 */
public class CyclicBarrierTest {

    public static void main (String[] args)throws InterruptedException {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(101, new Runnable() {
            @Override
            public void run() {
                System.out.println("看看我在哪里执行");
            }
        });


        for (int i = 0; i < 100; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    System.out.println("thread"+" 开始了");

                    try {
                        cyclicBarrier.await();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    System.out.println("thread1 结束了");
                }
            }, "thread" + i).start();

        }


    }
}
