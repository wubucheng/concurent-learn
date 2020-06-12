package me.wubc.concurent.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author wbc
 * @date 2020/6/11
 * @desc
 */
public class ExecutorDemo2 {

    public static void main(String[] args) {
        CustomizeFactory customizeFactory = new CustomizeFactory();
        ExecutorService executorService = Executors.newCachedThreadPool(customizeFactory);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " begin: " + System.currentTimeMillis());
                    Thread.sleep(1000);
                    System.out.println("end: " + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    static class CustomizeFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("自定义的线程工厂");
            thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread t, Throwable e) {
                    System.out.println(t.getName() + "发生了异常" + " " + e.getMessage());
                }
            });
            return thread;
        }
    }
}
