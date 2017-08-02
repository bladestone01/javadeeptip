package org.jdk.deep.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxyDemo {
    
	static class ServiceA {
		public void doIt() {
			System.out.println("Do It now....");
		}
	}
	
	static class InterceptorDemo implements MethodInterceptor {
		@Override
		public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable {
			System.out.println("entering the method:" + method.getName());
			Object result = proxy.invokeSuper(object, args);
			
			System.out.println("Ending of the method:" + method.getName());
			return result;
		}
	}
	
	static <T> T getProxy(Class<T> cls) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(cls);
		
		enhancer.setCallback(new InterceptorDemo());
		
		return (T)enhancer.create();
	}
	
	public static void main(String[] args) {
		ServiceA proxyService = getProxy(ServiceA.class);
		proxyService.doIt();

	}

}
