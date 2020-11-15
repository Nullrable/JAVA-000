package Week_04.com.lsd.thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-11-15 12:25
 * @Modified By：
 */
public class InterruptedTest {


    public static void main (String[] args)throws Exception {

        Thread sleepThread = new Thread(new Interrupted_SleepThread());
        sleepThread.setName("sleepThread");
        sleepThread.start();

        Thread busyThread = new Thread(new Interrupted_BusyThread());
        busyThread.setName("busyThread");
        busyThread.start();

        TimeUnit.SECONDS.sleep(2);

        sleepThread.interrupt();
        busyThread.interrupt();

        System.out.println(new Date() + ":" + sleepThread.getName() + "========>" + sleepThread.isInterrupted());
        System.out.println(new Date() + ":" + sleepThread.getName() + "========>" + sleepThread.getState());
        System.out.println(new Date() + ":" + sleepThread.getName() + "========>" + sleepThread.getPriority());
        System.out.println(new Date() + ":" + sleepThread.getName() + "========>" + sleepThread.getId());
        System.out.println(new Date() + ":" + sleepThread.getName() + "========>" + sleepThread.isAlive());
        System.out.println(new Date() + ":" + sleepThread.getName() + "========>" + sleepThread.getThreadGroup());


        System.out.println(busyThread.getName() + "========>" + busyThread.isInterrupted());

        TimeUnit.SECONDS.sleep(2);

        System.out.println(new Date() + ":" + sleepThread.getName() + "========>" + sleepThread.isInterrupted());
        System.out.println(new Date() + ":" + sleepThread.getName() + "========>" + sleepThread.getState());
        System.out.println(new Date() + ":" + sleepThread.getName() + "========>" + sleepThread.getPriority());
        System.out.println(new Date() + ":" + sleepThread.getName() + "========>" + sleepThread.getId());
        System.out.println(new Date() + ":" + sleepThread.getName() + "========>" + sleepThread.isAlive());
        System.out.println(new Date() + ":" + sleepThread.getName() + "========>" + sleepThread.getThreadGroup());
    }
}


class Interrupted_SleepThread implements Runnable {

    @Override
    public void run() {

        try {

            TimeUnit.SECONDS.sleep(3);


        } catch (InterruptedException e) {

            System.out.println(new Date() + "报错啦:" + e.getMessage());
        }

    }
}


class Interrupted_BusyThread implements Runnable {

    @Override
    public void run() {


        while (true) {

        }

    }
}