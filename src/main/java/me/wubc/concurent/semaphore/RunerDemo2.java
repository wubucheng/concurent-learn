package me.wubc.concurent.semaphore;

/**
 * @author wbc
 * @date 2020/6/8
 * @desc
 */
public class RunerDemo2 {

    public static void main(String[] args) throws InterruptedException {
        SemaphoreDemo1 semaphoreDemo1 = new SemaphoreDemo1();
        Thread threadA = new Thread(new SemaphoreThreadA(semaphoreDemo1));
        Thread threadB = new Thread(new SemaphoreThreadB(semaphoreDemo1));

        threadA.start();
        threadB.start();
        Thread.sleep(1000);

        threadB.interrupt();

        System.out.println("线程被中断");
    }
}
