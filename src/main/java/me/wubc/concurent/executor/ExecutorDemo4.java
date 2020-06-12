package me.wubc.concurent.executor;

import java.util.concurrent.*;

/**
 * @author wbc
 * @date 2020/6/11
 * @desc
 */
public class ExecutorDemo4 {

    public static void main(String[] args) {
        TestRunnable testRunnable1 = new TestRunnable();
        TestRunnable testRunnable2 = new TestRunnable();
        TestRunnable testRunnable3 = new TestRunnable();
        TestRunnable testRunnable4 = new TestRunnable();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 3, 9999L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        threadPoolExecutor.setRejectedExecutionHandler(new CustomizeExecutionHandler());
        threadPoolExecutor.execute(testRunnable1);
        threadPoolExecutor.execute(testRunnable2);
        threadPoolExecutor.execute(testRunnable3);
        threadPoolExecutor.execute(testRunnable4);


    }

    static class TestRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("执行了");
        }
    }

    static class CustomizeExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("被拒绝执行了");
        }
    }


}
