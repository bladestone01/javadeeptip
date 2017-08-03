package org.jdk.deep.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.jdk.deep.threadpool.ThreadPoolException.MyTask;

public class ThreadPoolSubmitTest {
	public static class MyTask implements Callable<Double> {
		private int aInt;
		private int bInt;

		public MyTask(int aInt, int bInt) {
			this.aInt = aInt;
			this.bInt = bInt;
		}

		@Override
		public Double call() {
			double result = aInt / bInt;
			System.out.println("MyTask result:" + result);
			
			return result;
		}

	}

	public static void main(String[] args) {
		ThreadPoolExecutor poolExec = new ThreadPoolExecutor(5, 10, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
		
		List<Future<Double>> results = new ArrayList<>();
		
		for (int i=0; i<5; i++) {
			Future<Double> result = poolExec.submit(new MyTask(10, i));
			results.add(result);
		}
		
		for (Future<Double> result : results) {
			
			try {
				System.out.println("Result:" + result.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		poolExec.shutdown();
	}
}
