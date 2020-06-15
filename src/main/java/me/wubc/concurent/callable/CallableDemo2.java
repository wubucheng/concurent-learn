package me.wubc.concurent.callable;

import java.util.concurrent.*;

/**
 * @author wbc
 * @date 2020/06/14
 * @desc
 **/
public class CallableDemo2 {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String result="";
        MyRunnable myRunnable = new MyRunnable("hello world");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
        Future<String> future = threadPoolExecutor.submit(myRunnable,result);
        System.out.println(future.get());
    }

    static class MyRunnable implements Runnable {

        private String name;

        public MyRunnable(String name) {
            this.name = name;
        }


        @Override
        public void run() {
            name = "after the name is :" + name;
        }
    }
}
