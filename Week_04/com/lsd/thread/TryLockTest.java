package Week_04.com.lsd.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-11-15 19:43
 * @Modified By：
 */
public class TryLockTest {

    public static void main (String[] args)throws Exception {
        Lock lock = new ReentrantLock(true);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {


                //tryLock() 不带参数的，会直接返回获取锁的结果，如果获取锁，返回true，反之false
                //tryLock(Long mills, TimeUnit unit) 则会等待 mills unit 后，再返回获取锁的结果，这个会发生等待

                if(lock.tryLock()) {
                    try{
                        //处理任务
                        System.out.println("test1 获取到锁，进入等待");

                        TimeUnit.SECONDS.sleep(10);

                    }catch(Exception ex){

                    }finally{
                        lock.unlock();   //释放锁
                    }
                }else {
                    //如果不能获取锁，则直接做其他事情

                    System.out.println("test1 未获取到锁，他哭了");
                }
            }
        });
        t1.setName("test1");
        t1.start();


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{


                    if(lock.tryLock(9, TimeUnit.SECONDS)) {

                        try {
                            //处理任务
                            System.out.println("test2 获取到锁，进入等待");

                            TimeUnit.SECONDS.sleep(10);

                        }finally{
                            lock.unlock();   //释放锁
                        }
                    }else {
                        //如果不能获取锁，则直接做其他事情
                        System.out.println("test2 未获取到锁，他哭了");
                    }
                }catch(Exception ex){

                }
            }
        });
        t2.setName("test2");
        t2.start();

    }
}
