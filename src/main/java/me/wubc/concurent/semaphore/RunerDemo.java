package me.wubc.concurent.semaphore;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @author wbc
 * @date 2020/6/8
 * @desc
 */
public class RunerDemo {

    public static void main(String[] args) {
        SemaphoreDemo1 semaphoreDemo1 = new SemaphoreDemo1();
        new Thread(new SemaphoreThreadA(semaphoreDemo1)).start();
        new Thread(new SemaphoreThreadB(semaphoreDemo1)).start();
        new Thread(new SemaphoreThreadC(semaphoreDemo1)).start();
    }
}
