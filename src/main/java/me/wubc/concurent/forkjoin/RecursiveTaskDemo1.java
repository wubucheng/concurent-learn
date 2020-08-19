package me.wubc.concurent.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author wbc
 * @date 2020/6/17
 * @desc
 */
public class RecursiveTaskDemo1 extends RecursiveTask<Integer> {
    @Override
    protected Integer compute() {
        return 100;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        RecursiveTaskDemo1 recursiveTaskDemo1 = new RecursiveTaskDemo1();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
//        forkJoinPool.awaitTermination()
        ForkJoinTask<Integer> joinTask = forkJoinPool.submit(recursiveTaskDemo1);
        joinTask.isCompletedNormally();
        joinTask.getException();
        System.out.println("joinTask: " + joinTask.get());
    }
}
