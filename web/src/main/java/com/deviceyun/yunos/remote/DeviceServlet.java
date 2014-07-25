package com.deviceyun.yunos.remote;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.deviceyun.yunos.dao.ApplicationDao;
import com.deviceyun.yunos.domain.Application;
import com.deviceyun.yunos.service.RemoteService;

/**
 * device operation
 * 
 * @author jack
 * 
 */
public class DeviceServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static final String APP_ID = "appId";
	public static final String APP_KEY = "appKey";
	public static final String DEVICE_ID = "deviceId";
	public static final String FUNCTIONAL_DEVICE_INDEX = "fdi";
	public static final String OPERATION = "operation";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		Map<String, String> parameters = new HashMap<String, String>();
		Enumeration names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			String value = request.getParameter(name);

			parameters.put(name, value);
		}

		ApplicationContext beanFactory = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());
		RemoteService remoteService = (RemoteService) beanFactory
				.getBean("remoteService");

		ApplicationDao applicationDao = (ApplicationDao) beanFactory
				.getBean("applicationDao");

		String appId = parameters.get(APP_ID);

		Application app = applicationDao.get(appId);
		if (app == null)
			out.println("error:appId invalid");

		String appKey = parameters.get(APP_KEY);
		String deviceId = parameters.get(DEVICE_ID);
		int functionalDeviceIndex = Integer.valueOf(parameters
				.get(FUNCTIONAL_DEVICE_INDEX));
		String operation = parameters.get(OPERATION);

		boolean successful = true;
		Object msg = "";
		try {
			Object result = remoteService.operateDevice(deviceId,
					functionalDeviceIndex, operation, parameters);
			msg = String.format("success:%s", result);
		} catch (Exception e) {
			successful = false;
			msg = String.format("error:%s", e.getMessage());
		}

		out.println("<html><body><h1>Result</h1>" + "<p>" + msg + "</p>"
				+ "</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
