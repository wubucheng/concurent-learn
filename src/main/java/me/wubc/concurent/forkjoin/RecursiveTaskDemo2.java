package me.wubc.concurent.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author wbc
 * @date 2020/6/17
 * @desc
 */
public class RecursiveTaskDemo2 extends RecursiveTask<String> {
    private int beginValue;
    private int endValue;

    public RecursiveTaskDemo2(int beginValue, int endValue) {
        this.beginValue = beginValue;
        this.endValue = endValue;
    }

    @Override
    protected String compute() {
        System.out.println(Thread.currentThread().getName() + "-----开始");
        if (endValue - beginValue > 2) {
            int middleValue = (endValue + beginValue) / 2;
            RecursiveTaskDemo2 leftTask = new RecursiveTaskDemo2(beginValue, middleValue);
            RecursiveTaskDemo2 rightTask = new RecursiveTaskDemo2(middleValue + 1,endValue);
            this.invokeAll(leftTask, rightTask);
            System.out.println("打印");
            return leftTask.join() + rightTask.join();
        } else {
            String returnStering = "";
            for (int i = beginValue; i <= endValue; i++) {
                returnStering = returnStering + i;
            }
            System.out.println("else返回: " + returnStering + " " + beginValue + " " + endValue);
            return returnStering;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.isTerminated();
        RecursiveTaskDemo2 task = new RecursiveTaskDemo2(1, 100);
        ForkJoinTask<String> result = forkJoinPool.submit(task);
        System.out.println(result.join());
        Thread.sleep(500);
    }
}
