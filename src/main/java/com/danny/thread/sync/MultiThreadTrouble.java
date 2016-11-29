package com.danny.thread.sync;

/**
 * @author huyuyang@lxfintech.com
 * @Title: MultiThreadTrouble
 * @Copyright: Copyright (c) 2016
 * @Description: 线程的同步是为了防止多个线程访问一个数据对象时，对数据造成的破坏。
 * 这里例子中，简单表明了，多线程在加锁和不加锁的环境下产生的问题
 * @Company: lxjr.com
 * @Created on 2016-11-26 20:45:47
 */
public class MultiThreadTrouble {

    public static void main(String[] args){
        new MultiThreadTrouble().execute();
        /*
        * Foo类的setX()方法中不加锁时，会造成最终结果不一致（多个线程最终计算的结果是混乱的）
        * Foo类的setX()方法中不加锁时，多个线程最终计算的结果是正确的
        */
    }

    /**
     * 执行多线程操作
     */
    public void execute() {
        Foo foo=new Foo();
        MyRunnable myRunnable=new MyRunnable(foo);
        Thread thread1=new Thread(myRunnable,"Thread-1");
        Thread thread2=new Thread(myRunnable,"Thread-2");
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(foo.getX());
    }

    /**
     * 线程类
     */
    class MyRunnable implements Runnable{
        private Foo foo;
        public MyRunnable(Foo foo){
            this.foo=foo;
        }
        //每个线程让Foo的x值执行三次加10操作
        public void run(){
            for (int i = 0; i < 3; i++) {
                foo.setX(foo.getX()+10);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 可能会被多线程造成数据不一致的数据
     */
    class Foo{
        private int x=100;
        public int getX() {
            return x;
        }
        public void setX(int x) {
            synchronized (this){//不加锁时，最终结果是混乱的
                this.x = x;
            }
        }
    }
}
