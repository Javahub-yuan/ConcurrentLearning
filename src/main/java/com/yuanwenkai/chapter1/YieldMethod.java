package com.yuanwenkai.chapter1;

/**
 * @author 袁闻锴
 * @date 2020/11/22 15:09
 * @describe
 */
public class YieldMethod {

    public YieldMethod() {
        //创建并启动线程
        Thread thread = new Thread(runnable());
        thread.start();
    }

    public Runnable runnable() {
        return () -> {
            for (int i = 0; i < 5; i++) {
                //当i=0时，让出自己的CPU执行权，放弃时间片，进行下一轮调度
                if (i % 5 == 0) {
                    System.out.println(Thread.currentThread() + "yield cpu...");
                    Thread.yield();
                }
            }
            System.out.println(Thread.currentThread() + "is over !");
        };
    }

    public static void main(String[] args) {
        new YieldMethod();
        new YieldMethod();
        new YieldMethod();
    }


}
