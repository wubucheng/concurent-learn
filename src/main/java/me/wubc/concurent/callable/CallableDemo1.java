package me.wubc.concurent.callable;

import java.util.concurrent.*;

/**
 * @author wbc
 * @date 2020/06/14
 * @desc
 **/
public class CallableDemo1 {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable myCallable = new MyCallable("hello world");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS, new LinkedBlockingDeque());
        Future<String> future = threadPoolExecutor.submit(myCallable);
        System.out.println(future.get());
    }

    static class MyCallable implements Callable<String> {
        private String name;

        public MyCallable(String name) {
            super();
            this.name = name;
        }

        @Override
        public String call() throws Exception {
            Thread.sleep(5000);
            return "返回值是：" + this.name;
        }
    }
}
