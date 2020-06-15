package me.wubc.concurent.completionservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author wbc
 * @date 2020/6/15
 * @desc
 */
public class CompletionServiceDemo1 {

    public static void main(String[] args) {
        MyCasllable hello = new MyCasllable("hello");
        MyCasllable world = new MyCasllable("world");
        List<MyCasllable> callables = new ArrayList<>();
        callables.add(hello);
        callables.add(world);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        CompletionService executorCompletionService = new ExecutorCompletionService<>(threadPoolExecutor);
        for (MyCasllable callable : callables) {
            executorCompletionService.submit(callable);
        }

        for (MyCasllable callable : callables) {
            try {
                System.out.println("返回结果: "+executorCompletionService.take().get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    static class MyCasllable implements Callable {
        private String name;

        public MyCasllable(String name) {
            super();
            this.name = name;
        }

        @Override
        public Object call() throws Exception {
            return "the name is " + name;
        }
    }


}
