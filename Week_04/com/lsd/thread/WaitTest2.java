package Week_04.com.lsd.thread;

/**
 *
 * 模仿Join
 *
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-11-14 14:46
 * @Modified By：
 */
public class WaitTest2 {

    public static void main(String[] args) {
        Object oo = new Object();

        WaitThread2 thread1 = new WaitThread2("thread_wait1 -- ");
        thread1.setOo(oo);
        thread1.start();

        synchronized (thread1) {
            for (int i = 0; i < 100; i++) {
                if (i == 20) {
                    try {

                        /**
                         * 将JoinTest改用wait
                         */
                        thread1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " -- " + i);
            }
        }
    }

}


class WaitThread2 extends Thread {

    private String name;
    private Object oo;

    public void setOo(Object oo) {
        this.oo = oo;
    }

    public WaitThread2(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        synchronized (Thread.currentThread()) {
            for (int i = 0; i < 100; i++) {
                System.out.println(name + i);
            }
            Thread.currentThread().notifyAll();

            System.out.println(name + " 我调用了notifyAll，但是我要sleep 5妙");

            try {
                Thread.sleep(5000);

                System.out.println(name + " 5妙后我醒了");
            }catch (Exception e){

            }

        }
    }
}

