package com.yuanwenkai.chapter1;

/**
 * @author 袁闻锴
 * @date 2020/11/21 17:45
 * @describe 使用wait()，notifyAll()实现最简单的生产者消费者模型
 */
public class ProductConsumerModel {

    public static class MyQueue {
        private Integer size;

        public MyQueue(Integer size) {
            this.size = size;
        }

        public void add() {
            this.size++;
        }

        public void remove() {
            this.size--;
        }
    }

    //生产者线程
    public static class ProductThread implements Runnable {

        private final MyQueue myQueue;

        public ProductThread(MyQueue myQueue) {
            this.myQueue = myQueue;
        }

        @Override
        public void run() {

            String name = Thread.currentThread().getName();
            while (true) {

                synchronized (myQueue) {
                    while (myQueue.size == 10) {
                        try {
                            System.out.println("生产者:" + name + "线程阻塞，挂起该线程等待消费，size = " + myQueue.size);
                            myQueue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    myQueue.add();
                    System.out.println("生产者:" + name + "成功生产，size = " + myQueue.size);
                    myQueue.notifyAll();
                }
            }


        }
    }

    //消费者线程
    public static class ConsumerThread implements Runnable {

        private final MyQueue myQueue;

        public ConsumerThread(MyQueue myQueue) {
            this.myQueue = myQueue;
        }

        @Override
        public void run() {

            String name = Thread.currentThread().getName();

            while (true) {

                try {
                    Thread.sleep(1000);
                    synchronized (myQueue) {
                        while (myQueue.size == 0) {
                            System.out.println("消费者:" + name + "线程阻塞，挂起该线程等待消费，size = " + myQueue.size);
                            myQueue.wait();
                        }

                        myQueue.remove();
                        System.out.println("消费者:" + name + "成功消费，size = " + myQueue.size);
                        myQueue.notifyAll();

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) {

        MyQueue myQueue = new MyQueue(0);

        Thread proThread1 = new Thread(new ProductConsumerModel.ProductThread(myQueue));
        Thread proThread2 = new Thread(new ProductConsumerModel.ProductThread(myQueue));
        Thread cunThread1 = new Thread(new ProductConsumerModel.ConsumerThread(myQueue));
        Thread cunThread2 = new Thread(new ProductConsumerModel.ConsumerThread(myQueue));

        /* result:
        生产者:Thread-0成功生产，size = 1
        生产者:Thread-0成功生产，size = 2
        生产者:Thread-0成功生产，size = 3
        生产者:Thread-1成功生产，size = 4
        生产者:Thread-1成功生产，size = 5
        生产者:Thread-1成功生产，size = 6
        生产者:Thread-1成功生产，size = 7
        生产者:Thread-1成功生产，size = 8
        生产者:Thread-1成功生产，size = 9
        生产者:Thread-1成功生产，size = 10
        生产者:Thread-1线程阻塞，挂起该线程等待消费，size = 10
        生产者:Thread-0线程阻塞，挂起该线程等待消费，size = 10
        消费者:Thread-2成功消费，size = 9
        生产者:Thread-0成功生产，size = 10
        生产者:Thread-0线程阻塞，挂起该线程等待消费，size = 10
        生产者:Thread-1线程阻塞，挂起该线程等待消费，size = 10
        消费者:Thread-3成功消费，size = 9
        生产者:Thread-1成功生产，size = 10
        生产者:Thread-1线程阻塞，挂起该线程等待消费，size = 10
        生产者:Thread-0线程阻塞，挂起该线程等待消费，size = 10
        消费者:Thread-3成功消费，size = 9
        生产者:Thread-0成功生产，size = 10
        生产者:Thread-0线程阻塞，挂起该线程等待消费，size = 10
        生产者:Thread-1线程阻塞，挂起该线程等待消费，size = 10
        消费者:Thread-2成功消费，size = 9
        生产者:Thread-1成功生产，size = 10
        生产者:Thread-1线程阻塞，挂起该线程等待消费，size = 10
        生产者:Thread-0线程阻塞，挂起该线程等待消费，size = 10

         */
        proThread1.start();
        proThread2.start();
        cunThread1.start();
        cunThread2.start();


    }
}
