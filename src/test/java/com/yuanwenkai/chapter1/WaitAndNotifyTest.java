package com.yuanwenkai.chapter1;

import org.junit.Test;

import static org.junit.Assert.assertThrows;

/**
 * @author 袁闻锴
 * @date 2020/11/21 18:31
 * @describe
 */
public class WaitAndNotifyTest {

    WaitAndNotify waitAndNotify = new WaitAndNotify();

    @Test
    public void waitTime() throws InterruptedException {
        waitAndNotify.WaitTime();
    }

    @Test
    public void waitException() {
        assertThrows(IllegalMonitorStateException.class, () -> waitAndNotify.WaitException());
    }

    @Test
    public void notifyException() {
        assertThrows(IllegalMonitorStateException.class, () -> waitAndNotify.notifyException());
    }

    @Test
    public void notifyAndNotifyAll() throws InterruptedException {
        waitAndNotify.notifyAndNotifyAll();
    }
}