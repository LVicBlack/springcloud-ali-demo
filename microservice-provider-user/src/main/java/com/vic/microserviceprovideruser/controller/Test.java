package com.vic.microserviceprovideruser.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    public static AtomicInteger count = new AtomicInteger();
    private static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void test1() {
        for (int i = 0; i < 50; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Future<?>> test2() {
        List<Future<?>> result = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(50);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(50, 50, 10000, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(100);
                int i = count.getAndAdd(1);
                logger.info(String.valueOf(i));
                countDownLatch.countDown();
                return String.valueOf(i);
            }
        };
        FutureTask<String> futureTask;
        Future<?> future = null;

        for (int i = 0; i < 50; i++) {
//            futureTask = new FutureTask<String>(callable);
            // 此处不该传入futuretask，否则获取不到执行结果
            future = executor.submit(callable);
            // 此处不能直接使用future.get获取返回值，否则会阻塞
            result.add(future);
        }
        try {
            System.out.println("等待for循环执行");
            countDownLatch.await();
            System.out.println("for循环执行结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        return result;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // 单线程跑
//        test1();

        // 多线程
        List<Future<?>> list = test2();

        System.out.println("-----||||-------------------输出结果---------------||||---------------");
        for (int i = 0; i < list.size(); i++) {
            try {
                logger.info((String) list.get(i).get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

//        test3();
        long end = System.currentTimeMillis();
        System.out.println("运行时间::::::" + (end - start));
    }
}
