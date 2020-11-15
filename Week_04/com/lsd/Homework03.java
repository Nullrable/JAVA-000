package Week_04.com.lsd;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-11-11 22:15
 * @Modified By：
 */
public class Homework03 {

    private static int result;

    public static void main(String[] args)throws Exception {

        long start=System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法


        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                result = sum(); //这是得到的返回值
            }
        });

        t.start();


        while (true) {

            if (result > 1) {
                // 确保  拿到result 并输出
                System.out.println("异步计算结果为："+result);

                System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

                System.exit(0);
            }

            Thread.sleep(10);

        }

        // 然后退出main线程
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
