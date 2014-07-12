package com.deviceyun.yunos;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

public class DeviceProxy implements java.lang.reflect.InvocationHandler{
	private static Logger logger = Logger.getLogger(DeviceProxy.class);
	private Object obj;

	public static Object newInstance(Object obj) {
		return java.lang.reflect.Proxy.newProxyInstance(obj.getClass()
				.getClassLoader(), obj.getClass().getInterfaces(),
				new DeviceProxy(obj));
	}

	private DeviceProxy(Object obj) {
		this.obj = obj;
	}

	public Object invoke(Object proxy, Method m, Object[] args)
			throws Throwable {
		Object result;
		try {
			logger.info("before method " + m.getName());
			long start = System.nanoTime();
			result = m.invoke(obj, args);
			long end = System.nanoTime();
			logger.info(String.format("%s took %d ns", m.getName(),
					(end - start)));
			System.out.println(String.format("%s took %d ns", m.getName(),
					(end - start)));
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		} catch (Exception e) {
			throw new RuntimeException("unexpected invocation exception: "
					+ e.getMessage());
		} finally {
			logger.info("after method " + m.getName());
		}
		return result;
	}
}
