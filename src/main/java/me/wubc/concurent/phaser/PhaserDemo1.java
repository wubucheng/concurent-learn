package me.wubc.concurent.phaser;

import java.util.concurrent.Phaser;

/**
 * @author wbc
 * @date 2020/6/10
 * @desc
 */
public class PhaserDemo1 {

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
        System.out.println(Thread.currentThread().getName() + " One Begin" + System.currentTimeMillis());
        phaser.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName() + " One End" + System.currentTimeMillis());


        System.out.println(Thread.currentThread().getName() + " Two Begin" + System.currentTimeMillis());
        phaser.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName() + " Two End" + System.currentTimeMillis());
    }

    static class ThreadA extends Thread {
        private Phaser phaser;

        public ThreadA(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            PhaserDemo1.testA();
        }
    }

    static class ThreadB extends Thread {
        private Phaser phaser;

        public ThreadB(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            PhaserDemo1.testA();
        }
    }

    static class ThreadC extends Thread {
        private Phaser phaser;
        public ThreadC(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            PhaserDemo1.testB();
        }
    }

    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);
        PhaserDemo1.phaser=phaser;
        new ThreadA(phaser).start();
        new ThreadB(phaser).start();
        new ThreadC(phaser).start();
    }


}
