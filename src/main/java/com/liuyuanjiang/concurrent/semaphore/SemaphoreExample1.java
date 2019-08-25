package com.liuyuanjiang.concurrent.semaphore;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SemaphoreExample1 {
	 
	 private final static int threadCount = 20;
	 static Semaphore semaphore = new Semaphore(4);
	 static Random random = new Random();
	    public static void main(String[] args) throws Exception {

	        ExecutorService exec = Executors.newCachedThreadPool();
	       // ÿ����������̻߳�ȡ���
	        

	        for (int i = 0; i < threadCount; i++) {
	            final int threadNum = i;
	            exec.execute(() -> {
	                try {
	                    semaphore.acquire(); // ��ȡһ�����
	                    test(threadNum,semaphore.getQueueLength());
	                    semaphore.release(); // �ͷ�һ�����
	                } catch (Exception e) {
	                    System.out.println("exception"+ e);
	                }
	            });
	        }
	        exec.shutdown();
	    }

	    private static void test(int threadNum,int length) throws Exception {
	    	int second = random.nextInt(10);
	    	System.out.println( "Thread number: "+threadNum + "current thread name :"+Thread.currentThread().getName() +
	    						" datetime " + LocalDateTime.now() +" sleep time: "+second + " quneue length :"+length);
	        Thread.sleep(second*1000);
	    }

}
