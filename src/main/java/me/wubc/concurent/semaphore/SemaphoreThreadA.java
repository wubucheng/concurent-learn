package me.wubc.concurent.semaphore;

/**
 * @author wbc
 * @date 2020/6/8
 * @desc
 */
public class SemaphoreThreadA implements Runnable {

    private SemaphoreDemo1 semaphoreDemo1;

    public SemaphoreThreadA(SemaphoreDemo1 semaphoreDemo1) {
        this.semaphoreDemo1 = semaphoreDemo1;
    }

    public SemaphoreThreadA() {

    }


    public void run() {
        semaphoreDemo1.test();
    }
}
