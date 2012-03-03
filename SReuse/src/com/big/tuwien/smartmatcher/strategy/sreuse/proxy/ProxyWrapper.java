package com.big.tuwien.smartmatcher.strategy.sreuse.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.log4j.Logger;

public class ProxyWrapper {
	private static Logger logger = Logger.getLogger(ProxyWrapper.class);
	
	
	public static <T, S extends T> T getProxyWrapper(Class<T> ctype, S instance,
													Provider<S> provider) {
		provider.setWrapped(instance);
		
		InvocationHandler handler = 
				new MyInvocationHandler(instance, provider);
		
		@SuppressWarnings("unchecked")
		T pInstance = (T) Proxy.newProxyInstance(
					ctype.getClassLoader(),
					new Class[] { ctype },
					handler);

		return pInstance;
	}
	
	
	/**
	 * A Provider instance provides new method implementations 
	 * that override the corresponding method implementations 
	 * in the wrapped object.
	 * @author alex
	 *
	 */
	public interface Provider<T> {
		/**
		 * Allows the provider to access the wrapped object instance.
		 * @param wrapped
		 */
		public void setWrapped(T wrapped);
	}
	
	
	public static class DefaultProvider<T> implements Provider<T> {
		protected T wrapped;
		
		public void setWrapped(T wrapped) {
			this.wrapped = wrapped;
		}
	}
	
	
	public static class MyInvocationHandler implements InvocationHandler {
		private Object wrapped;
		private Object provider;
		
		
		public MyInvocationHandler(Object wrapped, Object provider) {
			this.wrapped = wrapped;
			this.provider = provider;
		}
		
		
		@Override
		/**
		 * Tries to forward method calls to provider instance.
		 * If provider instance does not contain a method with an suitable signature
		 * the method is called on the wrapped object instance.
		 */
		public Object invoke(Object proxy, Method method, Object[] args)
														throws Throwable {
			try {
				logger.debug("Looking at provider instance " 
						+ provider + " for method " + method);
				
				Method providerMethod = provider.getClass()
										.getDeclaredMethod(method.getName(), 
												method.getParameterTypes());
				
				logger.debug("Found method on provider. Calling it");
				
				Object res = providerMethod.invoke(provider, args);
				return res;
			} catch (NoSuchMethodException e) {
				logger.debug("Provider instance does not contain method");
			}
			
			// if provider instance does not have method
			// -> call method on wrapped object
			logger.debug("Calling method on wrapped object instance");
			
			Object res = method.invoke(wrapped, args);		
			return res;
		}
	}
		
}
