package com.deviceyun.yunos.api;

import java.lang.reflect.Method;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.PrioritizedParameterNameDiscoverer;

import com.deviceyun.yunos.core.DeviceManagerImpl;

public class ApiUtils {

	public static void printMethodSignature(Class _interface) {

		Method[] methods = _interface.getDeclaredMethods();
		PrioritizedParameterNameDiscoverer pnd = new PrioritizedParameterNameDiscoverer();
		// pnd.addDiscoverer(new StandardReflectionParameterNameDiscoverer());
		pnd.addDiscoverer(new LocalVariableTableParameterNameDiscoverer());

		for (Method method : methods) {

			String[] names = pnd.getParameterNames(method);

			for (String name : names) {
				System.out.print(name + ",");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		printMethodSignature(DeviceManagerImpl.class);
	}
}
