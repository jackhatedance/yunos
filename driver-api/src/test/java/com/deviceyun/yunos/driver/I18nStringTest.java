package com.deviceyun.yunos.driver;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import com.deviceyun.yunos.driver.i18n.I18nString;

public class I18nStringTest {

	I18nString hello;

	public I18nStringTest() {
		hello = new I18nString("hello");
		hello.add("zh_CN", "nihao");
	}

	@Test
	public void test() {
		Assert.assertEquals("hello", hello.get("en_US"));
		Assert.assertEquals("nihao", hello.get("zh_CN"));

	}

}
