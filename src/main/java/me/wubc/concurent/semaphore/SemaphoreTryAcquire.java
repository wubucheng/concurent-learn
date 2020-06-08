package me.wubc.concurent.semaphore;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.concurrent.Semaphore;

/**
 * @author wbc
 * @date 2020/6/8
 * @desc
 */
public class SemaphoreTryAcquire {

    private Semaphore semaphore = new Semaphore(1);

    public void test() {
        if (semaphore.tryAcquire(1)) {
            try {
                System.out.println(Thread.currentThread().getName() + "获得许可 ");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + "未获得许可 ");
        }
    }

    static class SemaphoreTryAcquireThreadA extends Thread {
        private SemaphoreTryAcquire semaphoreQueue;

        public SemaphoreTryAcquireThreadA(SemaphoreTryAcquire semaphoreQueue) {
            this.semaphoreQueue = semaphoreQueue;
        }

        @Override
        public void run() {
            semaphoreQueue.test();
        }
    }

    static class SemaphoreTryAcquireThreadB extends Thread {
        private SemaphoreTryAcquire semaphoreQueue;

        public SemaphoreTryAcquireThreadB(SemaphoreTryAcquire semaphoreQueue) {
            this.semaphoreQueue = semaphoreQueue;
        }

        @Override
        public void run() {
            semaphoreQueue.test();
        }
    }


    public static void main(String[] args) {
        SemaphoreTryAcquire semaphoreTryAcquire = new SemaphoreTryAcquire();
        new Thread(new SemaphoreTryAcquireThreadA(semaphoreTryAcquire)).start();
        new Thread(new SemaphoreTryAcquireThreadB(semaphoreTryAcquire)).start();


    }

}
