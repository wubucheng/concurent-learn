package me.wubc.concurent.exchanger;

import java.util.concurrent.Exchanger;

/**
 * @author wbc
 * @date 2020/6/8
 * @desc
 */
public class ExchangerThreadA extends Thread {
    private Exchanger<String> exchanger;

    public ExchangerThreadA(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            System.out.println("A获取到线程B的值为: " + exchanger.exchange("hello"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
