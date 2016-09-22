package net.abstractfactory.yunos.driver;

import static org.junit.Assert.*;
import junit.framework.Assert;
import net.abstractfactory.yunos.driver.i18n.I18nString;

import org.junit.Test;

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
