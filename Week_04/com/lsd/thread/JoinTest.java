package Week_04.com.lsd.thread;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-11-14 11:44
 * @Modified By：
 */
public class JoinTest {

    public static void main(String[] args) {
        Object oo = new Object();

        MyThread thread1 = new MyThread("thread1 -- ");
        thread1.setOo(oo);
        thread1.start();

        synchronized (thread1) {
            for (int i = 0; i < 100; i++) {
                if (i == 20) {
                    try {

                        /**
                         * 进入等待池，调用所在线程（这里就是main）进入等待，
                         * 等thread1执行完毕后thread1会隐式调用notifyAll，唤醒main线程，
                         * main线程继续执行，可以用jstack -l pid 查看线程状态
                         */

                        thread1.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " -- " + i);
            }
        }
    }

}

class MyThread extends Thread {

    private String name;
    private Object oo;

    public void setOo(Object oo) {
        this.oo = oo;
    }

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 100; i++) {
                System.out.println(name + i);
            }
        }
    }

}
