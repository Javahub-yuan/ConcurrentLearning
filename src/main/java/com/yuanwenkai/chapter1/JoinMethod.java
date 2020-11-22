package com.yuanwenkai.chapter1;

/**
 * @author 袁闻锴
 * @date 2020/11/22 14:28
 * @describe
 */
public class JoinMethod {

    public void testJoinMethod() throws InterruptedException {
        Thread threadA = new Thread(() -> {

            String name = Thread.currentThread().getName();
            System.out.println("线程：" + name + "， 开始工作");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        Thread threadB = new Thread(() -> {

            String name = Thread.currentThread().getName();
            System.out.println("线程：" + name + "， 开始工作");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadA.start();
        threadB.start();

        System.out.println("wait all child thread over!");

        threadA.join();
        threadB.join();

        System.out.println("all child thread over!");
    }

    public void testJoinException() throws InterruptedException {
        Thread threadA = new Thread(() -> {
            System.out.println("ThreadA begin run!");
            for (; ; ) {
            }
        });

        Thread mainThread = Thread.currentThread();

        Thread threadB = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ThreadB begin run!");
            //中断主线程
            mainThread.interrupt();
        });

        //启动线程A
        threadA.start();
        //延时1s启动线程B
        threadB.start();

        //使主线程阻塞，等待线程A执行结束
        threadA.join();

    }
}
