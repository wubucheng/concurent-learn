package me.wubc.concurent.semaphore;

/**
 * @author wbc
 * @date 2020/6/8
 * @desc
 */
public class SemaphoreThreadC implements Runnable {

    private SemaphoreDemo1 semaphoreDemo1;

    public SemaphoreThreadC(SemaphoreDemo1 semaphoreDemo1) {
        this.semaphoreDemo1 = semaphoreDemo1;
    }

    public SemaphoreThreadC() {

    }


    public void run() {
        semaphoreDemo1.test();
    }
}
