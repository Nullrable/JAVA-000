package Week_04.com.lsd.concurrent;

/**
 *
 * 串行执行时间 不一定 比并行多，原因在于并发时，存在CPU上下文切换的带来的消耗
 * 对于数据量比较少时，串行的表现 比 并发好，当数据量达到一定数量级以后，并发的
 * 表现更好
 *
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-11-12 20:30
 * @Modified By：
 */
public class ConcurrencyTest {

    public static final long count = 10000000000l;
    public static int a = 0;

    public static void main(String[] args) throws InterruptedException{

        concurrency();

        serial();
    }

    public static void concurrency() throws InterruptedException{

        long start = System.currentTimeMillis();



        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                for (long i = 0; i < count; i ++) {

                    a += 5;
                }
            }
        });

        t.start();

        int b = 0;

        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        t.join();
        System.out.println("concurrency :" + time+"ms,b="+b+",a="+a);

    }

    public static void serial(){
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;

        System.out.println("serial:" + time+"ms,b="+b+",a="+a);
    }
}
