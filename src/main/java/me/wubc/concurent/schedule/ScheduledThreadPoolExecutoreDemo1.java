package me.wubc.concurent.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author wbc
 * @date 2020/6/16
 * @desc
 */
public class ScheduledThreadPoolExecutoreDemo1 {

    public static void main(String[] args) {
        List<Callable> callableList = new ArrayList<>();
        callableList.add(new MyCallableA());
        callableList.add(new MyCallableB());
        // 使用的是单任务的计划任务池
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        ScheduledFuture futureA = scheduledExecutorService.schedule(callableList.get(0), 4, TimeUnit.SECONDS);
        ScheduledFuture futureB = scheduledExecutorService.schedule(callableList.get(1), 4, TimeUnit.SECONDS);
        try {
            System.out.println("Begin: " + System.currentTimeMillis());
            System.out.println("futureA: " + futureA.get());
            System.out.println("futureB: " + futureB.get());
            System.out.println("End: " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    static class MyCallableA implements Callable {
        @Override
        public Object call() throws Exception {
            return "return A";
        }
    }

    static class MyCallableB implements Callable {
        @Override
        public Object call() throws Exception {
            return "return B";
        }
    }

}
