package me.wubc.concurent.phaser;

import java.util.concurrent.Phaser;

/**
 * @author wbc
 * @date 2020/6/10
 * @desc 演示onAdvance的作用
 */
public class PhaserDemo4 {

    public Phaser phaser;

    public void testA() {
        System.out.println(Thread.currentThread().getName() + " One Begin" + phaser.getPhase());
        phaser.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName() + " One End" + phaser.getPhase());
        phaser.getUnarrivedParties();
        System.out.println(Thread.currentThread().getName() + " Two Begin" + phaser.getPhase());
        phaser.arriveAndAwaitAdvance();
//        phaser.awaitAdvanceInterruptibly(1);
//        phaser.forceTermination();
//        phaser.isTerminated();
        System.out.println(Thread.currentThread().getName() + " Two End" + phaser.getPhase());
    }

    public PhaserDemo4(Phaser phaser) {
        this.phaser = phaser;
    }

    static class ThreadA extends Thread {
        private PhaserDemo4 phaserDemo4;

        public ThreadA(PhaserDemo4 phaserDemo4) {
            this.phaserDemo4 = phaserDemo4;
        }

        @Override
        public void run() {
            phaserDemo4.testA();
        }
    }

    class ThreadB extends Thread {
        private PhaserDemo4 phaserDemo4;

        public ThreadB(PhaserDemo4 phaserDemo4) {
            this.phaserDemo4 = phaserDemo4;
        }

        @Override
        public void run() {
            phaserDemo4.testA();
        }
    }


    public static void main(String[] args) {
        Phaser phaser = new Phaser(2) {
            protected boolean onAdvance(int phase, int registerdParties) {
                System.out.println("被调用了");
                // true不进行等待
                return true;
            }
        };

        PhaserDemo4 phaserDemo4 = new PhaserDemo4(phaser);
        new ThreadA(phaserDemo4).start();
        new ThreadA(phaserDemo4).start();

    }


}
