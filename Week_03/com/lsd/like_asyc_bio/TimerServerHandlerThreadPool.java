package Week_03.com.lsd.like_asyc_bio;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-10-31 16:27
 * @Modified By：
 */
public class TimerServerHandlerThreadPool {

    private ExecutorService executorService;

    public TimerServerHandlerThreadPool() {
        this.executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                50, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000));
    }

    public void execute(Runnable task){
        executorService.execute(task);
    }
}
