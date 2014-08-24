package com.deviceyun.yunos.driver.config;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class ConfigureAnnotationExtractorTest {

	@Test
	public void test() {
		ConfigureAnnotationExtractor extractor = new ConfigureAnnotationExtractor();
		List<ConfigItem> items = extractor.parse(SampleConfig.class);

		Assert.assertEquals(1, items.size());

		ConfigItem item1 = items.get(0);

		Assert.assertEquals("主机", item1.getName().get("zh_CN"));

	}
}
