package com.yuanwenkai.chapter1;

/**
 * @author 袁闻锴
 * @date 2020/11/22 15:48
 * @describe 线程死锁
 */
public class DeadLock {

    private static final Object resourceA = new Object();
    private static final Object resourceB = new Object();

    public static void main(String[] args) {

        Thread threadA = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println(Thread.currentThread() + "get resourceA");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread() + "waiting get resourceB");
                synchronized (resourceB) {
                    System.out.println(Thread.currentThread() + "get resourceB");
                }
            }
        });


        Thread threadB = new Thread(() -> {
            synchronized (resourceB) {
                System.out.println(Thread.currentThread() + "get resourceB");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread() + "waiting get resourceA");
                synchronized (resourceA) {
                    System.out.println(Thread.currentThread() + "get resourceA");
                }
            }
        });

        //启动线程
        threadA.start();
        threadB.start();
    }
}
