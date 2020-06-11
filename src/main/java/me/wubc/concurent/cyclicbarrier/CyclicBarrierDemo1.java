package me.wubc.concurent.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author wbc
 * @date 2020/6/9
 * @desc
 */
public class CyclicBarrierDemo1 extends Thread {

    private CyclicBarrier cyclicBarrier;

    public CyclicBarrierDemo1(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
            System.out.println(cyclicBarrier.getNumberWaiting());
            System.out.println(Thread.currentThread().getName() + "完成了");
            // 全部完成才会是否
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
            public void run() {
                // cyclicBarrier全部执行了将会回调run方法
                System.out.println("全部已完成");
            }
        });

        for (int i = 0; i < 3; i++) {
            new CyclicBarrierDemo1(cyclicBarrier).start();
        }
    }
}
