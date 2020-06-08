package me.wubc.concurent.semaphore;

import javax.xml.bind.ValidationEvent;
import java.util.concurrent.Semaphore;

/**
 * @author wbc
 * @date 2020/6/8
 * @desc
 */
public class SemaphoreDemo1 {

    // 表示同一时间只允许一个线程执行
    private Semaphore semaphore = new Semaphore(1);

    public void test() {
        try {
            // 获取许可，默认为一
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + "start date= " + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + "end date= " + System.currentTimeMillis());
            // 释放许可
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
