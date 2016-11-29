package com.danny.thread.sync;

/**
 * @author huyuyang@lxfintech.com
 * @Title: DeadLock
 * @Copyright: Copyright (c) 2016
 * @Description: 死锁的模拟
 * @Company: lxjr.com
 * @Created on 2016-11-26 21:09:59
 */
public class DeadLock implements Runnable {
    public static Object resource1 = new Object();
    public static Object resource2 = new Object();
    public int flag;
    public DeadLock(int flag){
        this.flag=flag;
    }
    public static void main(String[] args) {
        DeadLock deadLock1 = new DeadLock(0);
        DeadLock deadLock2 = new DeadLock(1);
        Thread deadLockThread1 = new Thread(deadLock1);
        Thread deadLockThread2 = new Thread(deadLock2);
        deadLockThread1.start();
        deadLockThread2.start();

        /*
        * 运行结果：
        * Thread-0获得了资源【resource1】，准备获得【resource2】……
        * Thread-1获得了资源【resource2】，准备获得【resource1】……
        */
    }

    public void run() {
        if (flag == 0) {
            synchronized (resource1) {
                System.out.println(Thread.currentThread().getName() + "获得了资源【resource1】，准备获得【resource2】……");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resource2) {
                    System.out.println(Thread.currentThread().getName() + "成功获得了资源【resource1】和【resource2】！");
                }
            }
        }
        if (flag == 1) {
            synchronized (resource2) {
                System.out.println(Thread.currentThread().getName() + "获得了资源【resource2】，准备获得【resource1】……");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resource1) {
                    System.out.println(Thread.currentThread().getName() + "成功获得了资源【resource2】和【resource1】！");
                }
            }
        }
    }
}
