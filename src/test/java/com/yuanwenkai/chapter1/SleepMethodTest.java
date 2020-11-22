package com.yuanwenkai.chapter1;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author 袁闻锴
 * @date 2020/11/22 14:52
 * @describe
 */
public class SleepMethodTest {

    SleepMethod sleepMethod = new SleepMethod();
    @Test
    public void testSleep() {
        sleepMethod.testSleep();
    }

    @Test
    public void testSleepException() throws InterruptedException {
        sleepMethod.testSleepException();
    }
}