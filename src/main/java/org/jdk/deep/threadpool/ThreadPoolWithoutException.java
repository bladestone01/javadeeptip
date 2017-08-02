package org.jdk.deep.threadpool;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolWithoutException {

	public static class MyTask implements Runnable {
		private int aInt;
		private int bInt;

		public MyTask(int aInt, int bInt) {
			this.aInt = aInt;
			this.bInt = bInt;
		}

		@Override
		public void run() {
			double result = aInt / bInt;
			System.out.println("MyTask result:" + result);
		}

	}

	public static void main(String[] args) {
		ThreadPoolExecutor poolExec = new ThreadPoolExecutor(5, 10, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
		
		for (int i=0; i<5; i++) {
			poolExec.submit(new MyTask(10, i));
		}
		
		poolExec.shutdown();
	}
}
