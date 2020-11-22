package com.yuanwenkai.chapter1;

import org.junit.Test;

/**
 * @author 袁闻锴
 * @date 2020/11/22 14:32
 * @describe
 */
public class JoinMethodTest {

    JoinMethod joinMethod = new JoinMethod();

    @Test
    public void testJoinMethod() throws InterruptedException {
        joinMethod.testJoinMethod();
    }

    @Test
    public void testJoinException() throws InterruptedException {
        //当一个线程A处于阻塞状态等待另一个线程执行完毕，这时调用线程A的中断方法，则会抛出java.lang.InterruptedException异常
        joinMethod.testJoinException();
    }
}