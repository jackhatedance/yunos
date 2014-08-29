package com.deviceyun.yunos.driver.config;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class ConfigureAnnotationExtractorTest {

	@Test
	public void test() {
		ConfigureAnnotationParser extractor = new ConfigureAnnotationParser();
		ConfigurationDefinition def = extractor.parse(SampleConfig.class);

		List<ConfigurationItem> items = def.getItems();
		
		Assert.assertEquals(1, items.size());

		ConfigurationItem item1 = items.get(0);

		Assert.assertEquals("主机", item1.getName().get("zh_CN"));

	}
}
