package com.driverstack.yunos.driver.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

import junit.framework.Assert;

import org.junit.Test;

import com.driverstack.yunos.driver.i18n.UTF8Control;

public class UTF8ControlTest {

	@Test
	public void test() {
		Locale l = new Locale("zh","CN");
		ResourceBundle bundle = ResourceBundle.getBundle("config/sampleConfig", l,new UTF8Control());
		String port = bundle.getString("host.name");
		Assert.assertEquals("主机", port);
		
		
	}

}
