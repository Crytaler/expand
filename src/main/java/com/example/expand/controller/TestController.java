package com.example.expand.controller;

import com.example.expand.domain.User;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yz.m
 * @version v1.0.0
 * @description:
 * @date 2021/1/25 15:21
 */
public class TestController {

    public static String test = "abc";

//    public static void main(String[] args) throws InterruptedException {
//        final int totalThread = 10;
//        CountDownLatch countDownLatch = new CountDownLatch(totalThread);
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        for (int i = 0; i < totalThread; i++) {
//            executorService.execute(() -> {
//                System.out.print("run..");
//                countDownLatch.countDown();
//            });
//        }
//        countDownLatch.await();
//        System.out.println("end");
//        executorService.shutdown();
//    }
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final Semaphore sp = new Semaphore(3);//创建Semaphore信号量，初始化许可大小为3
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        sp.acquire();//请求获得许可，如果有可获得的许可则继续往下执行，许可数减1。否则进入阻塞状态
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "进入，当前已有" + (3 - sp.availablePermits()) + "个并发");
                    try {
                        Thread.sleep((long) (Math.random() * 10000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "即将离开");
                    sp.release();//释放许可，许可数加1
                    //下面代码有时候执行不准确，因为其没有和上面的代码合成原子单元
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "已离开，当前已有" + (3 - sp.availablePermits()) + "个并发");
                }
            };
            service.execute(runnable);
        }
    }

//    public static void main(String[] args) {
//        final Business business = new Business();
//        ExecutorService executor = Executors.newFixedThreadPool(3);
//        for (int i = 0; i < 3; i++) {
//            executor.execute(
//                    new Runnable() {
//                        public void run() {
//                            business.service();
//                        }
//                    }
//
//            );
//        }
//        executor.shutdown();
//    }
//
//
//    private static class Business {
//        private int count;
//        Lock lock = new ReentrantLock();
//        Semaphore sp = new Semaphore(1);
//
//        public void service() {
//            //lock.lock();
//            try {
//                sp.acquire(); //当前线程使用count变量的时候将其锁住，不允许其他线程访问
//            } catch (InterruptedException e1) {
//                e1.printStackTrace();
//            }
//            try {
//                count++;
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(count);
//            } catch (RuntimeException e) {
//                e.printStackTrace();
//            } finally {
//                //lock.unlock();
//                sp.release();  //释放锁
//            }
//        }
//    }




}
