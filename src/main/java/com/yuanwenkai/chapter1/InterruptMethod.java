package com.yuanwenkai.chapter1;

/**
 * @author 袁闻锴
 * @date 2020/11/22 15:25
 * @describe
 */
public class InterruptMethod {

    public void testInterruptMethod() throws InterruptedException {
        Thread threadA = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread() + ": hello");
            }
        });

        //启动线程A
        threadA.start();

        //主线程休眠1s，以便让子线程中断前输出
        Thread.sleep(1000);

        //中断子线程
        System.out.println("main thread interrupt threadA");
        threadA.interrupt();

        //等待子线程执行完毕
        threadA.join();
        System.out.println("main is over!");
    }

    public void isInterruptedAndInterrupt() {
        Thread threadA = new Thread(() -> {
            for (; ; ) {
            }
        });

        //启动线程A
        threadA.start();

        //设置中断标志位
        threadA.interrupt();

        //获取中断标志位
        //threadA.isInterrupted() = true
        System.out.println("threadA.isInterrupted() = " + threadA.isInterrupted());

        /*
            threadA.interrupted()和Thread.interrupted()方法作用是一样的，都是获取当前线程的中断标志位，所以都是获取主线程的中断标志
            Thread.interrupted() = false
            Thread.interrupted() = false
         */
        //获取中断标志位并重置
        System.out.println("Thread.interrupted() = " + threadA.interrupted());

        //获取中断标志位并重置
        System.out.println("Thread.interrupted() = " + Thread.interrupted());

        //获取中断标志位
        //threadA.isInterrupted() = true
        System.out.println("threadA.isInterrupted() = " + threadA.isInterrupted());
    }
}
