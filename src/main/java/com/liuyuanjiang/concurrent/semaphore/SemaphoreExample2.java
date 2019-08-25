package com.liuyuanjiang.concurrent.semaphore;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SemaphoreExample2 {
	 
	 private final static int threadCount = 20;
	 static Semaphore semaphore = new Semaphore(3);

	    public static void main(String[] args) throws Exception {

	        ExecutorService exec = Executors.newCachedThreadPool();
	       // 每次最多三个线程获取许可  
	        

	        for (int i = 0; i < threadCount; i++) {
	            final int threadNum = i;
	            exec.execute(() -> {
	                try {
	                    if(semaphore.tryAcquire()){
	                    	test(threadNum);
	                    	semaphore.release(); 
	                    	
	                    } 
	                } catch (Exception e) {
	                    System.out.println("exception"+ e);
	                }
	            });
	        }
	        exec.shutdown();
	    }

	    private static void test(int threadNum) throws Exception {
	    	System.out.println( "Thread number: "+threadNum + " datetime " + LocalDateTime.now());
	        Thread.sleep(1000);
	    }

}
