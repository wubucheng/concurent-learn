package me.wubc.concurent.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author wbc
 * @date 2020/6/8
 * @desc
 */
public class FairSemaphoreDemo {

    private Semaphore semaphore = new Semaphore(5, true);

    public void test() {
        try {
            // 获取许可，默认为一，获取后当前可用许可数=原当前可用许可数为减一
            semaphore.acquire();
            System.out.println("当前可用许可数为: " + semaphore.availablePermits());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }

    }

}
