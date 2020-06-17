package me.wubc.concurent.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author wbc
 * @date 2020/6/16
 * @desc
 */
public class ScheduledThreadPoolExecutoreDemo2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 使用的是单任务的计划任务池
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(new MyRunnable(), 1, 2, TimeUnit.SECONDS);
        System.out.println(scheduledFuture.get());
        scheduledFuture.cancel(true);
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(10);
        scheduledThreadPoolExecutor.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
        scheduledThreadPoolExecutor.setContinueExistingPeriodicTasksAfterShutdownPolicy(false);
        scheduledThreadPoolExecutor.setRemoveOnCancelPolicy(false);

    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("begin=" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
//                Thread.sleep(4000);
            System.out.println("end=" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
        }
    }
}
