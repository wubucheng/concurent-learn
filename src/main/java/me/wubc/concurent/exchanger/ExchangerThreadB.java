package me.wubc.concurent.exchanger;

import java.util.concurrent.Exchanger;

/**
 * @author wbc
 * @date 2020/6/8
 * @desc
 */
public class ExchangerThreadB extends Thread {
    private Exchanger<String> exchanger;

    public ExchangerThreadB(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            System.out.println("B获取到线程A的值为: " + exchanger.exchange("world"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
