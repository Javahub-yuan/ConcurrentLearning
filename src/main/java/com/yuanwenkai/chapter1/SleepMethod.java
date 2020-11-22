package com.yuanwenkai.chapter1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 袁闻锴
 * @date 2020/11/22 14:47
 * @describe
 */
public class SleepMethod {

    //创建一个独占锁
    private static final Lock lock = new ReentrantLock();

    public void testSleep() {

        Thread threadA = new Thread(() -> {
            //获取独占锁
            lock.lock();
            try {
                System.out.println("threadA is in sleep!");
                Thread.sleep(3000);
                System.out.println("threadA is in awaked!");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //释放锁
                lock.unlock();
            }
        });

        Thread threadB = new Thread(() -> {
            //获取独占锁
            lock.lock();
            try {
                System.out.println("threadB is in sleep!");
                Thread.sleep(3000);
                System.out.println("threadB is in awaked!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //释放锁
                lock.unlock();
            }
        });

        //启动线程
        threadA.start();
        threadB.start();

        //让主线程等待两个子线程执行完再结束
        try {
            threadB.join();
            threadA.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //如果在线程睡眠期间，中断它，则会报错
    public void testSleepException() throws InterruptedException {

        Thread threadA = new Thread(() -> {
            System.out.println("threadA is in sleep!");
            try {
                Thread.sleep(10000);
                System.out.println("threadA is awaked!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //启动线程A
        threadA.start();

        //主线程休眠2s
        Thread.sleep(2000);

        //主线程中断子线程
        threadA.interrupt();
    }
}
