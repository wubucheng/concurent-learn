package me.wubc.concurent.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wbc
 * @date 2020/6/11
 * @desc
 */
public class ExecutorDemo1 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " begin: " + System.currentTimeMillis());
                    Thread.sleep(1000);
                    System.out.println("Thread A");
                    System.out.println("end: " + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " begin: " + System.currentTimeMillis());
                    Thread.sleep(1000);
                    System.out.println("Thread B");
                    System.out.println(Thread.currentThread().getName() + " end: " + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
