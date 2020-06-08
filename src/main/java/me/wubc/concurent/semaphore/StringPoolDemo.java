package me.wubc.concurent.semaphore;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wbc
 * @date 2020/6/8
 * @desc
 */
public class StringPoolDemo {

    private final int MAX_SIZE = 3;
    private final int PERMITS = 5;
    private List<String> dataList = new ArrayList<String>();
    private Semaphore semaphore = new Semaphore(PERMITS);
    private ReentrantLock reentrantLock = new ReentrantLock();
    private Condition condition = reentrantLock.newCondition();

    public StringPoolDemo() {
        for (int i = 0; i < MAX_SIZE; i++) {
            dataList.add("第" + i + "个");
        }
    }

    /**
     * 获取数据
     *
     * @return
     */
    public String getData() {
        String data = null;
        try {
            semaphore.acquire();
            reentrantLock.lock();
            while (dataList.isEmpty()) {
                // 没有数据则等待
                condition.await();
            }
            data = dataList.remove(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
        return data;
    }

    /**
     * 设置数据
     *
     * @param value
     */
    public void setData(String value) {
        reentrantLock.lock();
        dataList.add(value);
        // 添加数据，唤醒所有等待的线程
        condition.signalAll();
        reentrantLock.unlock();
        semaphore.release();
    }

    static class SemaphorePoolThreadA extends Thread {
        private StringPoolDemo semaphorePool;

        public SemaphorePoolThreadA(StringPoolDemo semaphorePool) {
            this.semaphorePool = semaphorePool;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                String data = semaphorePool.getData();
                System.out.println("获取到值为:" + data);
                semaphorePool.setData(data);
            }
        }
    }

    public static void main(String[] args) {
        StringPoolDemo semaphorePool = new StringPoolDemo();
        for (int i = 0; i < 10; i++) {
            new SemaphorePoolThreadA(semaphorePool).start();
        }

    }
}
