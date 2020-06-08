package me.wubc.concurent.exchanger;

import java.util.concurrent.Exchanger;

/**
 * @author wbc
 * @date 2020/6/8
 * @desc
 */
public class ExchangerRunnber {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<String>();
        ExchangerThreadA exchangerThreadA = new ExchangerThreadA(exchanger);
        ExchangerThreadB exchangerThreadB = new ExchangerThreadB(exchanger);
        exchangerThreadA.start();
        exchangerThreadB.start();
    }
}
