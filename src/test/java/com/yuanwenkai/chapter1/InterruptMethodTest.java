package com.yuanwenkai.chapter1;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author 袁闻锴
 * @date 2020/11/22 15:29
 * @describe
 */
public class InterruptMethodTest {

    InterruptMethod interruptMethod = new InterruptMethod();

    @Test
    public void testInterruptMethod() throws InterruptedException {
        interruptMethod.testInterruptMethod();
    }

    @Test
    public void isInterruptedAndInterrupt() {
        interruptMethod.isInterruptedAndInterrupt();
    }
}