package me.wubc.concurent.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wbc
 * @date 2020/6/8
 * @desc Semaphore实现生产者与消费者示例
 */
public class SemaphoreProductConsume {

    private Semaphore produce = new Semaphore(10);
    private Semaphore consume = new Semaphore(10);
    private ReentrantLock reentrantLock = new ReentrantLock();
    private Condition proCondition = reentrantLock.newCondition();
    private Condition consumeCondition = reentrantLock.newCondition();
    private String[] goods = new String[5];

    private boolean isEmpty() {
        for (int index = 0; index < goods.length; index++) {
            if (goods[index] != null) {
                return false;
            }
        }
        return true;
    }

    private boolean isFull() {
        for (int index = 0; index < goods.length; index++) {
            if (goods[index] == null) {
                return false;
            }
        }
        return true;
    }


    public void product(String value) {
        try {
            produce.acquire();
            proCondition.await();
            while (isFull()) {
                proCondition.await();
            }

            // 向下标指向值为空的设置值
            for (int index = 0; index < goods.length; index++) {
                if (goods[index] == null) {
                    goods[index] = value;
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            consumeCondition.signalAll();
            reentrantLock.unlock();
            produce.release();
        }
    }

    public void consume() {
        try {
            consume.acquire();
            reentrantLock.lock();
            while (isEmpty()) {
                consumeCondition.await();
            }

            for (int index = 0; index < goods.length; index++) {
                if (goods[index] != null) {
                    System.out.println("线程:" + Thread.currentThread().getName() + "消费了:" + goods[index]);
                    goods[index] = null;
                    break;
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            proCondition.signalAll();
            reentrantLock.unlock();
            consume.release();
        }
    }


}
