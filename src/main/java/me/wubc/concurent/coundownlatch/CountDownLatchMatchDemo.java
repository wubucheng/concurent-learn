package me.wubc.concurent.coundownlatch;

import javax.sound.midi.Track;
import javax.swing.undo.CannotUndoException;
import java.util.concurrent.CountDownLatch;

/**
 * @author wbc
 * @date 2020/6/8
 * @desc 使用CountDownLatch的demo
 */
public class CountDownLatchMatchDemo extends Thread {

    // 裁判等待远动员
    private CountDownLatch comingTag;
    // 等待裁判宣布比赛开始
    private CountDownLatch waitStartTag;
    // 等待起跑
    private CountDownLatch waitRunTag;
    // 起跑
    private CountDownLatch runningTag;
    // 结束
    private CountDownLatch endTag;

    public CountDownLatchMatchDemo(CountDownLatch comingTag, CountDownLatch waitTag, CountDownLatch waitRunTag, CountDownLatch runningTag, CountDownLatch endTag) {
        this.comingTag = comingTag;
        this.waitStartTag = waitTag;
        this.waitRunTag = waitRunTag;
        this.runningTag = runningTag;
        this.endTag = endTag;
    }

    @Override
    public void run() {
        try {
            System.out.println("远动员以不同交通工具走来");
            Thread.sleep((int) (Math.random()) * 10000);
            // 每一个线程到这里都是将的comingTag计数减一
            System.out.println("运动员达到起跑点");
            comingTag.countDown();

            System.out.println("等待裁判宣布开始比赛");
            waitStartTag.await();

            System.out.println("预备跑");
            Thread.sleep((int) (Math.random()) * 10000);
            waitRunTag.countDown();

            System.out.println("开始跑");
            runningTag.await();

            System.out.println("比赛中");
            Thread.sleep((int) (Math.random()) * 10000);
            endTag.countDown();

            System.out.println("到达终点");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 裁判等待远动员
        CountDownLatch comingTag = new CountDownLatch(10);
        CountDownLatch waitStartTag = new CountDownLatch(1);
        // 等待起跑
        CountDownLatch waitRunTag = new CountDownLatch(10);
        // 起跑
        CountDownLatch runningTag = new CountDownLatch(1);
        // 结束
        CountDownLatch endTag = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            CountDownLatchMatchDemo countDownLatchMatchDemo = new CountDownLatchMatchDemo(comingTag, waitStartTag, waitRunTag, runningTag, endTag);
            countDownLatchMatchDemo.start();
        }

        try {
            System.out.println("裁判等待运动员到来");
            comingTag.await();

            System.out.println("裁判进行检查");
            Thread.sleep(5000);
            waitStartTag.countDown();

            System.out.println("各就各位");
            waitRunTag.await();

            Thread.sleep(2000);
            System.out.println("开始！");
            runningTag.countDown();
            endTag.await();

            System.out.println("比赛结束");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
