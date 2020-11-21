package com.yuanwenkai.chapter1;

/**
 * @author 袁闻锴
 * @date 2020/11/21 18:29
 * @describe wait(), notify()
 */
public class WaitAndNotify {

    public void WaitException() throws InterruptedException {
        Object obj = new Object();
        //如果当前线程没有取到共享变量的监视器锁，则会报错
        obj.wait();
    }

    public void WaitTime() throws InterruptedException {
        Object obj = new Object();
        synchronized (obj) {

            System.out.println("调用wait方法，挂起线程，等待唤醒...");

            long startTime = System.currentTimeMillis();
            obj.wait(3000);
            long time = System.currentTimeMillis() - startTime;

            System.out.println("线程经过: " + time + "ms,被唤醒，获取到CPU资源，继续执行代码...");

        }
    }

    public void notifyException() {
        Object obj = new Object();
        //如果当前线程没有取到共享变量的监视器锁，则会报错
        obj.notify();
    }


    public void notifyAndNotifyAll() throws InterruptedException {
        Object obj = new Object();

        Thread threadA = new Thread(() -> {
            synchronized (obj) {
                System.out.println("线程A拿到obj的监视器锁");

                try {
                    System.out.println("线程A开始挂起");
                    obj.wait();
                    System.out.println("线程A结束挂起");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadB = new Thread(() -> {
            synchronized (obj) {
                System.out.println("线程B拿到obj的监视器锁");

                try {
                    System.out.println("线程B开始挂起");
                    obj.wait();
                    System.out.println("线程B结束挂起");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadC = new Thread(() -> {
            synchronized (obj) {
                System.out.println("线程C拿到obj的监视器锁");

                System.out.println("线程C开始随机唤醒一个线程");
                obj.notify();
            }
        });

        Thread threadD = new Thread(() -> {
            synchronized (obj) {
                System.out.println("线程D拿到obj的监视器锁");

                System.out.println("线程D开始唤醒所有线程");
                obj.notifyAll();
            }
        });

        //notify
        threadA.start();
        threadB.start();
        Thread.sleep(1000);
        threadC.start();
        //threadD.start();

    }
}
