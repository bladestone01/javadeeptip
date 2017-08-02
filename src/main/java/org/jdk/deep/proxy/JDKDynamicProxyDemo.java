package org.jdk.deep.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKDynamicProxyDemo {

	static interface IServiceA {
	    public void doIt();	
	}
	
	static class ServiceAImpl implements IServiceA {

		@Override
		public void doIt() {
			System.out.println("I am doing it now");
		}
	}
	
	static class InvocationHandlerImpl implements InvocationHandler {
		private Object serviceObj;
		
        public InvocationHandlerImpl(Object serviceObj) {
            this.serviceObj = serviceObj;	
        }
        
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("start logging the method....");
			Object result = method.invoke(this.serviceObj, args);
			System.out.println("end of logging in the method");
			
			return result;
		}
		
	}
	
	public static void main(String[] args) {
		IServiceA serviceA = new ServiceAImpl();

		IServiceA proxyService = (IServiceA)Proxy.newProxyInstance(IServiceA.class.getClassLoader(), new Class<?>[]{IServiceA.class}, new InvocationHandlerImpl(serviceA));
		
		proxyService.doIt();
	}

}
