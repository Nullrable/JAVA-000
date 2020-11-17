package Week_04.com.lsd.thread;

import java.util.Calendar;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-11-17 11:19
 * @Modified By：
 */
public class SemaphoreTest {

    public static void main (String[] args) throws InterruptedException {


        //初始化3许可证，表示最多只允许三个线程同时执行
        //这里有个参数公平 fair
        Semaphore semaphore = new Semaphore(3, false);


        for (int i = 0; i < 10; i++) {

          Thread t =  new Thread(new Runnable() {
                @Override
                public void run() {

                    String name = Thread.currentThread().getName();

                    System.out.println(Calendar.getInstance().getTime() + "==>" + name + ": " + "呀，我在门口啦，我尝试推开门，我最多推五秒");



                    try {

                        boolean getKey = semaphore.tryAcquire(5, TimeUnit.SECONDS);

                        //tryAcquire(), 如果未设置超时时间，表示结果立马返回，线程无需一直等待，如果获取返回true，未获取 false
                        //如果设置超时时间，表示等待 X 时间后，如果获取则返回true，未获取false
                        if(getKey) {

                            System.out.println( Calendar.getInstance().getTime() + "==>" + name + ": " + "我进来啦");


                            TimeUnit.SECONDS.sleep(10);


                            System.out.println( Calendar.getInstance().getTime() + "==>" + name + ": " + "我休息啦10秒，走了");

                        }else{

                            System.out.println( Calendar.getInstance().getTime() + "==>" + name + ": " + "我推了5秒，推不开，走了");
                        }



                    }catch (InterruptedException e) {

                        System.out.println( Calendar.getInstance().getTime() + "==>" + name + ": " + "呀，我被老妈叫回去吃饭啦");


                    }finally {
                        semaphore.release();

                    }

                }
            }, "thread" + i);

            t.start();

//            if( i == 9 ){
//
//                TimeUnit.SECONDS.sleep(2);
//                t.interrupt();
//
//                System.out.println( Calendar.getInstance().getTime() + "==>" + t.getName() + ": Interrupted = " + t.isInterrupted());
//
//            }

        }

    }
}
