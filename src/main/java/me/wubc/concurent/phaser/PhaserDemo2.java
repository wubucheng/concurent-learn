package me.wubc.concurent.phaser;

import java.util.concurrent.Phaser;

/**
 * @author wbc
 * @date 2020/6/10
 * @desc
 */
public class PhaserDemo2 {

    public static Phaser phaser;

    public static void testA() {
        System.out.println(Thread.currentThread().getName() + " One Begin" + System.currentTimeMillis());
        phaser.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName() + " One End" + System.currentTimeMillis());

        System.out.println(Thread.currentThread().getName() + " Two Begin" + System.currentTimeMillis());
        phaser.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName() + " Two End" + System.currentTimeMillis());
    }

    public static void testB() {
        try {
            System.out.println(Thread.currentThread().getName() + " One Begin" + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("A: "+phaser.getRegisteredParties());
            phaser.arriveAndDeregister();
            System.out.println(Thread.currentThread().getName() + " One End" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


//        System.out.println(Thread.currentThread().getName() + " Two Begin" + System.currentTimeMillis());
//        phaser.arriveAndAwaitAdvance();
//        System.out.println(Thread.currentThread().getName() + " Two End" + System.currentTimeMillis());
    }

    static class ThreadA extends Thread {
        private Phaser phaser;

        public ThreadA(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            PhaserDemo2.testA();
        }
    }

    static class ThreadB extends Thread {
        private Phaser phaser;

        public ThreadB(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            PhaserDemo2.testA();
        }
    }

    static class ThreadC extends Thread {
        private Phaser phaser;

        public ThreadC(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            PhaserDemo2.testB();
        }
    }

    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);
        PhaserDemo2.phaser = phaser;
        new ThreadA(phaser).start();
        new ThreadB(phaser).start();
        new ThreadC(phaser).start();
    }


}
