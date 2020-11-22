package com.yuanwenkai.chapter1;

/**
 * @author 袁闻锴
 * @date 2020/11/22 15:55
 * @describe
 */
public class daemonThread {

    public static void main(String[] args) {

        Thread threadA = new Thread(() -> {
            for (; ; ) {
            }
        });

        //将threadA设为用户线程，则JVM不会退出
        //将threadA设为守护线程，则JVM会退出
        threadA.setDaemon(true);

        threadA.start();
        System.out.println("main thread is over");
    }
}
