package org.jdk.deep.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TraceThreadPool extends ThreadPoolExecutor {

	public TraceThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}
	
	
	@Override
	public Future<?> submit(Runnable task) {
		return super.submit(task);
	}
	
	@Override
	public void execute(Runnable task) {
		super.execute(wrapper(task, clientTrace(), Thread.currentThread().getName()));
	}
	
	private Exception clientTrace() {
		return new Exception("Client Stack Trace");
	}
	
	private Runnable wrapper(final Runnable task, final Exception clientStack, String clientThreadName) {
		return new Runnable() {

			@Override
			public void run() {
				try {
					task.run();
				} catch (Exception e) {
					clientStack.printStackTrace();
					throw e;
				}
			}
		};
	}
	
	
}
