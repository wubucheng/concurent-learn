package me.wubc.concurent.phaser;

import java.util.concurrent.Phaser;

/**
 * @author wbc
 * @date 2020/6/10
 * @desc
 */
public class PhaserDemo3 {

    public static Phaser phaser;

    public static void testA() {
        System.out.println(Thread.currentThread().getName() + " One Begin" + phaser.getPhase());
        phaser.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName() + " One End" + phaser.getPhase());

        System.out.println(Thread.currentThread().getName() + " Two Begin" + phaser.getPhase());
        phaser.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName() + " Two End" + phaser.getPhase());
    }


    static class ThreadA extends Thread {
        private Phaser phaser;

        public ThreadA(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            PhaserDemo3.testA();
        }
    }



    public static void main(String[] args) {
        Phaser phaser = new Phaser(1);
        PhaserDemo3.phaser = phaser;
        new ThreadA(phaser).start();
    }


}
