package me.wubc.concurent.semaphore;

import javax.sound.midi.Soundbank;
import java.util.concurrent.Semaphore;

/**
 * @author wbc
 * @date 2020/6/8
 * @desc
 */
public class SemaphoreQueue {
    private Semaphore semaphore = new Semaphore(5);

    public void test() {
        try {
            semaphore.acquire();
            Thread.sleep(5000);
            if (semaphore.hasQueuedThreads()) {
                System.out.println("当前有线程等待这个许可，等待的线程数为: " + semaphore.getQueueLength());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }


    static class SemaphoreQueueThreadA extends Thread {
        private SemaphoreQueue semaphoreQueue;

        public SemaphoreQueueThreadA(SemaphoreQueue semaphoreQueue) {
            this.semaphoreQueue = semaphoreQueue;
        }
        @Override
        public void run() {
            semaphoreQueue.test();
        }
    }

    public static void main(String[] args) {
        SemaphoreQueue semaphoreQueue = new SemaphoreQueue();
        SemaphoreQueueThreadA semaphoreQueueThreadA = new SemaphoreQueueThreadA(semaphoreQueue);
        new Thread(semaphoreQueueThreadA).start();

        SemaphoreQueueThreadA[] semaphoreQueueThreadAS = new SemaphoreQueueThreadA[5];
        for (int i = 0; i < 4; i++) {
            semaphoreQueueThreadAS[i] = new SemaphoreQueueThreadA(semaphoreQueue);
            semaphoreQueueThreadAS[i].start();
        }


    }

}

