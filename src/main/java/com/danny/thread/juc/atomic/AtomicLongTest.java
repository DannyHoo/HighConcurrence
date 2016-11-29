package com.danny.thread.juc.atomic;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author huyuyang@lxfintech.com
 * @Title: AtomicLongTest
 * @Copyright: Copyright (c) 2016
 * @Description: int类型的自增自减是原子操作，long不是，如果long在多线程环境下可以用AtomicLong
 * @Company: lxjr.com
 * @Created on 2016-11-20 23:46:03
 */
public class AtomicLongTest {
    private volatile static long i = 0;
    private static AtomicLong atomicLongI=new AtomicLong(0);

    public static void main(String[] args) throws Exception {
        test3();
    }

    public static void test1() throws InterruptedException {
        Thread a = new Thread() {
            @Override
            public void run() {
                for (int j = 0; j < 1000000; j++) {
                    //synchronized (IncTest.class) {
                    i++;
                    //}
                }
            }
        };
        a.start();
        Thread b = new Thread() {
            @Override
            public void run() {
                for (int j = 0; j < 1000000; j++) {
                    //synchronized (IncTest.class) {
                    i--;
                    //}
                }
            }
        };
        b.start();
        a.join();
        b.join();
        System.out.println(i);
    }

    public static void test2() throws InterruptedException {
        for (int j = 0; j <10 ; j++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        //synchronized (IncTest.class){
                        i++;
                        //}
                    }
                }
            }.start();
        }

        Thread.sleep(1000);
        System.out.println(i);
    }

    public static void test3() throws InterruptedException {
        for (int j = 0; j <10 ; j++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        atomicLongI.addAndGet(1);
                    }
                }
            }.start();
        }

        Thread.sleep(1000);
        System.out.println(atomicLongI.get());
    }
}
