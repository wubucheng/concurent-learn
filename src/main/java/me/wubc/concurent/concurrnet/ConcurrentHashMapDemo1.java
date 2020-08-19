package me.wubc.concurent.concurrnet;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wbc
 * @date 2020/6/18
 * @desc
 */
public class ConcurrentHashMapDemo1 {

    public static void main(String[] args) {
        MyService myService = new MyService();
        ThreadA threadA = new ThreadA(myService);
        ThreadB threadB = new ThreadB(myService);
        ThreadC threadc = new ThreadC(myService);
        threadA.start();
        threadB.start();
        threadc.start();
    }

    static class MyService {
        public ConcurrentHashMap map = new ConcurrentHashMap();
    }

    public static class ThreadA extends Thread {
        private MyService myService;

        public ThreadA(MyService myService) {
            this.myService = myService;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5000; i++) {
                myService.map.put("ThreadA", i);
                System.out.println("ThreadA：" + i);
            }
        }
    }

    public static class ThreadB extends Thread {
        private MyService myService;

        public ThreadB(MyService myService) {
            this.myService = myService;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5000; i++) {
                myService.map.put("ThreadB", i);
                System.out.println("ThreadB：" + i);
            }
        }
    }

    public static class ThreadC extends Thread {
        private MyService myService;

        public ThreadC(MyService myService) {
            this.myService = myService;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5000; i++) {
                myService.map.put("ThreadC", i);
                System.out.println("ThreadC：" + i);
            }
        }
    }
}
