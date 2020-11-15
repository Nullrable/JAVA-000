# 线程，多线程，线程安全

## 可见性，原子性

>可见性：对于可见性，Java 提供了 volatile 关键字来保证可见性。
当一个共享变量被 volatile 修饰时，它会保证修改的值会立即被更新到主存，当有其他
线程需要读取时，它会去内存中读取新值。
另外，通过 synchronized 和 Lock 也能够保证可见性，synchronized 和 Lock 能保证
同一时刻只有一个线程获取锁然后执行同步代码，并且在释放锁之前会将对变量的修改
刷新到主存当中。
volatile 并不能保证原子性。





## 守护线程和用户线程
**用户线程：** 我们平常创建的普通线程。
**守护线程：** 用来服务于用户线程；不需要上层逻辑介入。

**使用它需要注意些什么？**
* thread.setDaemon(true)必须在thread.start()之前设置，否则会跑出一个IllegalThreadStateException异常。你不能把正在运行的常规线程设置为守护线程。
* 在Daemon线程中产生的新线程也是Daemon的。
* 守护线程不能用于去访问固有资源，比如读写操作或者计算逻辑。因为它会在任何时候甚至在一个操作的中间发生中断。
Java自带的多线程框架，比如ExecutorService，会将守护线程转换为用户线程，所以如果要使用后台线程就不能用Java的线程池。

**意义及应用场景**
* 当主线程结束时，结束其余的子线程（守护线程）自动关闭，就免去了还要继续关闭子线程的麻烦。如：Java垃圾回收线程就是一个典型的守护线程；内存资源或者线程的管理，但是非守护线程也可以。

## 线程的几种状态
NRRWWBT

NEW -> Running/Runnable,Ready -> Waiting,Timed_Waiting -> Blocked,Terminate



## join, wait, notify, notifyAll, sleep

### 先理解两个概念：锁池 和 等待池

#### 锁池（monitor）
>线程A拥有某个对象的锁时，其他线程调用Synchronized方法或者方法块时，需要获取该对象的锁，但是该对象的所有
正在被线程A拥有，所以这些线程就进入该对象的锁池

#### 等待池
>线程A调用某个对象的wait()方法，线程就会释放该对象的锁，然后进入该对象的等待池；与wait方法对应的时notify
和notifyAll，不同的是调用对象的wait方法会立马释放该对象的锁，但是notify和notifyAll并不会，
如果调用notify或notifyAll代码后面还有另外代码，比如while(true)，则不会释放该对象的锁，即对于
notify和notifyAl需要执行完Synchronized方法或者方法块才会释放锁

#### join
>thread.join的含义是当前线程需要等待previousThread线程终止之后才从thread.join返回。简单来说，就是线程没有执行完之前，会一直阻塞在join方法处。

![avatar](resources/thread_join.png)

```
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
```

#### wait

* 首先，调用了wait()之后会引起当前线程处于等待状状态。
* 其次，每个线程必须持有该对象的monitor。
如果在当前线程中调用wait()方法之后，该线程就会释放monitor的持有对象并让自己处于等待状态。
* 如果想唤醒一个正在等待的线程，那么需要开启一个线程通过notify()或者notifyAll()方法去通知正在等待的线程获取monitor对象。
如此，该线程即可打破等待的状态继续执行代码。 这里需要注意只有notify或notifyAll所在的Synchronized块执行完（不执行完就不会释放锁），
wait所在线程才会被唤醒

**简单Wait实验**
```
public class WaitTest {

    public static void main(String[] args) {
        Object oo = new Object();

        WaitThread thread1 = new WaitThread("thread_wait1 -- ");
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


class WaitThread extends Thread {

    private String name;
    private Object oo;

    public void setOo(Object oo) {
        this.oo = oo;
    }

    public WaitThread(String name) {
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
```

####  简单Wait实验 - notifyAll

notifyAll后，沉睡5秒后，跳出同步块

```
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


```

####  sleep

* sleep()方法是Thread的静态方法,提供了两种sleep的方式可让我们更灵活的控制,目的是为了使线程睡眠一段时间,自然醒后继续执行,不存在继续竞争,因为期间并没有释放同步锁.

```
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
                        
                        //并不会释放thread1锁
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
```

## 无锁技术

### CAS（copy and swap）

cas

### 分段思想

分段思想

## 并发的工具类

### 信号量


### 