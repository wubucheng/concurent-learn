package me.wubc.concurent.completionservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author wbc
 * @date 2020/6/15
 * @desc
 */
public class CompletionServiceDemo2 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        CompletionService completionService = new ExecutorCompletionService<>(executorService);
        for (int i = 0; i < 1; i++) {
            completionService.submit(new Callable() {
                @Override
                public Object call() throws Exception {
                    Thread.sleep(5000);
                    return "已经返回了";
                }
            });
        }

        for (int i  = 0; i < 1; i++) {
            System.out.println(completionService.poll());
        }

    }


}
