package com.liuyuanjiang.concurrent.cyclicBarrier;

import java.time.LocalDateTime;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CyclicBarrierExample1 {
	 private static CyclicBarrier barrier = new CyclicBarrier(5);

	    public static void main(String[] args) throws Exception {

	        ExecutorService executor = Executors.newCachedThreadPool();

	        for (int i = 0; i < 20; i++) {
	            final int threadNum = i;
	            Thread.sleep(1000);
	            executor.execute(() -> {
	                try {
	                    race(threadNum);
	                } catch (Exception e) {
	                	System.out.println("exception"+ e);
	                }
	            });
	        }
	        executor.shutdown();
	    }

	    private static void race(int threadNum) throws Exception {
	        Thread.sleep(1000);
	        System.out.println("{} is ready"+ threadNum + " datetime " + LocalDateTime.now());
	        System.out.println( " datetime " + LocalDateTime.now() + " waiting number " + barrier.getNumberWaiting());
	        barrier.await();
	        System.out.println("{} continue"+ threadNum + " datetime " + LocalDateTime.now());
	    }
}
