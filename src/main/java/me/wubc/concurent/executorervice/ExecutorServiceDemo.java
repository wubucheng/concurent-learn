package me.wubc.concurent.executorervice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wbc
 * @date 2020/6/15
 * @desc
 */
public class ExecutorServiceDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Callable> callableList = new ArrayList<Callable>();
        callableList.add(new CallableA());
        callableList.add(new CallableB2());

        ExecutorService executorService = Executors.newCachedThreadPool();
//        String value = executorService.invokeAny(callableList);
//        System.out.println("结果为: " + value);

    }

    static class CallableA implements Callable {

        @Override
        public Object call() throws Exception {
            System.out.println("CallableA " + Math.random());
            return "callableA";
        }
    }

    static class CallableB1 implements Callable {

        @Override
        public Object call() throws Exception {
            System.out.println("CallableB1 " + Math.random());
            return "CallableB1";
        }
    }

    static class CallableB2 implements Callable {

        @Override
        public Object call() throws Exception {
            if (!Thread.currentThread().isInterrupted()) {
                System.out.println("CallableB2 " + Math.random());
            } else {
                System.out.println("线程被中断了");
            }
            return "CallableB2";
        }
    }
}
