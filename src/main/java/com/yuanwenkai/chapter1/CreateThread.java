package com.yuanwenkai.chapter1;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author 袁闻锴
 * @date 2020/11/21 16:55
 * @describe 1.2线程创建与运行
 */
public class CreateThread {

    //方式1：继承Thread类并重写run方法
    public static class MyThread extends Thread {

        //使用继承好处之一：可以在子类中定义成员变量
        private final String msg;

        public MyThread(String msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            System.out.println("我是" + this.getName() + "，开始执行任务：方法1, msg = " + msg);
        }

    }

    //方式2：实现Runnable接口
    public static class RunnableTask implements Runnable {

        private final String msg;

        public RunnableTask(String msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println("我是" + name + "，开始执行任务：方法2, msg:" + msg);
        }
    }

    //方式3：使用FutureTask方式
    public static class CallerTask implements Callable<String> {

        @Override
        public String call() {
            String name = Thread.currentThread().getName();
            System.out.println("我是" + name + "，开始执行任务：方法3");
            return "ok";
        }
    }

    public static void main(String[] args) {
        /*
        方式1：
            创建线程;
            启动线程;
         */
        MyThread myThread = new MyThread("hello");
        myThread.start();
        MyThread myThread2 = new MyThread("world");
        myThread2.start();

        /*
        方式2：
            创建任务;
            启动线程;
         */
        RunnableTask runnableTask = new RunnableTask("!!");
        new Thread(runnableTask).start();
        new Thread(runnableTask).start();

        /*
        方式3：
            创建异步任务;
            启动线程;
            等待任务完成完毕，并返回结果;
         */
        FutureTask<String> futureTask = new FutureTask<>(new CallerTask());
        new Thread(futureTask).start();
        try {
            String result = futureTask.get();
            System.out.println("result = " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
