package Week_04.com.lsd;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-11-11 22:27
 * @Modified By：
 */
public class Homework03_3 {

    private static Lock lock = new ReentrantLock(true);

    private static int result;

    public static void main(String[] args) throws Exception {

        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法




        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                lock.lock();

                result = sum(); //这是得到的返回值

                lock.unlock();

            }
        });

        t.start();


        while (true) {

            Thread.sleep(10);

            if(lock.tryLock()){
                // 确保  拿到result 并输出
                System.out.println("异步计算结果为："+result);

                System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

                // 然后退出main线程
                System.exit(0);
            }

        }



    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
