package Week_04.com.lsd.concurrent;

import java.util.Date;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-11-14 11:09
 * @Modified By：
 */
public class ThreadStateTest1 {

    public static void main (String[] args) throws Exception{

        Object co = new Object();

        final Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println(new Date() + " 开始进入锁");

                synchronized (co) {
                    System.out.println(new Date() + " 已经进入锁");

                    co.notifyAll();


                }
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(new Date() + " 线程结束了");


//                Thread currentThread = Thread.currentThread();
//
//                String currentThreadName = currentThread.getName();
//
//
//                for (int i = 0; i <= 100; i++) {
//
//                    System.out.println("打印：" + i);
//                    if(i == 20){
//                        synchronized (co) {
//                            System.out.println("notifyAll");
//                            co.notifyAll();
//                        }
//                    }
//
//                    try {
//                        Thread.sleep(10);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                }
//
//                System.out.println("这是线程的名称：" + currentThreadName);
//                System.out.println("返回当前线程" + currentThreadName + "的线程组中活动线程的数量:" + Thread.currentThread().getThreadGroup().activeCount());
//                System.out.println("返回该线程" + currentThreadName + "的标识符:" + currentThread.getId());
//                System.out.println("返回该线程" + currentThreadName + "的优先级:" + currentThread.getPriority());
//                System.out.println("返回该线程" + currentThreadName + "的状态:" + currentThread.getState());
//                System.out.println("返回该线程" + currentThreadName + "所属的线程组:" + currentThread.getThreadGroup());
//                System.out.println("测试该线程" + currentThreadName + "是否处于活跃状态:" + currentThread.isAlive());

                /**
                 * 用户线程：我们平常创建的普通线程。
                 * 守护线程：用来服务于用户线程；不需要上层逻辑介入。
                 *
                 * 使用它需要注意些什么？
                 * 1. thread.setDaemon(true)必须在thread.start()之前设置，否则会跑出一个IllegalThreadStateException异常。你不能把正在运行的常规线程设置为守护线程。
                 * 2. 在Daemon线程中产生的新线程也是Daemon的。
                 * 3. 守护线程不能用于去访问固有资源，比如读写操作或者计算逻辑。因为它会在任何时候甚至在一个操作的中间发生中断。
                 * 4. Java自带的多线程框架，比如ExecutorService，会将守护线程转换为用户线程，所以如果要使用后台线程就不能用Java的线程池。
                 *
                 *
                 * Java垃圾回收线程就是一个典型的守护线程
                 */
//                System.out.println("测试该线程" + currentThreadName + "是否为守护线程:" + currentThread.isDaemon());

            }
        });
        t.start();

//        这种情况会一直堵塞，因为 wait是后加，上面Thread执行完以后，notifyAll后，才加上wait，导致wait一直在等待中；去掉Thread.sleep(2000);就可以正常
//        Thread.sleep(2000);
//
        synchronized (co){
            try {
                Thread.sleep(5000);
                System.out.println(new Date() + " 我释放了锁");
                co.wait();

                System.out.println(new Date() + " 线程返回了锁");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("测试该线程" + Thread.currentThread().getName() + "是否为守护线程:" + Thread.currentThread().isDaemon());

    }

}
