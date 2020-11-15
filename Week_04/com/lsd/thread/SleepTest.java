package Week_04.com.lsd.thread;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-11-14 15:05
 * @Modified By：
 */
public class SleepTest {

    public static void main(String[] args) {
        Object oo = new Object();

        SleepThread thread1 = new SleepThread("thread_sleep1 -- ");
        thread1.setOo(oo);
        thread1.start();

        synchronized (thread1) {
            for (int i = 0; i < 100; i++) {
                if (i == 20) {
                    try {

                        System.out.println(Thread.currentThread().getName() + " -- 我准备沉睡1秒");
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + " -- 我醒来啦");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " -- " + i);
            }
        }
    }

}


class SleepThread extends Thread {

    private String name;
    private Object oo;

    public void setOo(Object oo) {
        this.oo = oo;
    }

    public SleepThread(String name) {
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
