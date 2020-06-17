package me.wubc.concurent.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * @author wbc
 * @date 2020/6/17
 * @desc
 */
public class RecursiveActionDemo1 extends RecursiveAction {

    private int beginValue;
    private int endValue;

    public RecursiveActionDemo1(int beginValue, int endValue) {
        this.beginValue = beginValue;
        this.endValue = endValue;
    }

    @Override
    protected void compute() {
        System.out.println(Thread.currentThread().getName() + "-----------开始");
        if (endValue - beginValue > 2) {
            int middleNum = (beginValue + endValue) / 2;
            RecursiveActionDemo1 leftAction = new RecursiveActionDemo1(beginValue, middleNum);
            RecursiveActionDemo1 rightAction = new RecursiveActionDemo1(middleNum+1, endValue);
            this.invokeAll(leftAction, rightAction);
        } else {
            System.out.println("打印组合为: " + beginValue + "---" + endValue);
        }
        System.out.println(Thread.currentThread().getName() + "-----------结束");
    }

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(new RecursiveActionDemo1(1, 10));
        Thread.sleep(5000);
    }
}
