package com.driverstack.yunos.api;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.PrioritizedParameterNameDiscoverer;

public class ApiUtils {

	public static Method getMethod(Object obj, String methodName) {
		Method[] methods = obj.getClass().getDeclaredMethods();

		Method method = null;
		for (Method m : methods) {
			if (methodName.equals(m.getName())) {
				method = m;
				break;
			}
		}

		return method;
	}

	public static List<Parameter> getParameterInfo(Method method) {

		PrioritizedParameterNameDiscoverer pnd = new PrioritizedParameterNameDiscoverer();
		// pnd.addDiscoverer(new StandardReflectionParameterNameDiscoverer());
		pnd.addDiscoverer(new LocalVariableTableParameterNameDiscoverer());

		String[] names = pnd.getParameterNames(method);
		Class[] types = method.getParameterTypes();

		List<Parameter> paramList = new ArrayList<Parameter>();
		for (int i = 0; i < names.length; i++) {
			paramList.add(new Parameter(names[i], types[i]));
		}

		return paramList;

	}

	public static Object invoke(Object obj, Method method, Object[] parameters) {

		try {
			return method.invoke(obj, parameters);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
