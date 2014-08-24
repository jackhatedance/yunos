package com.deviceyun.yunos.driver.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Configure {
	String resourceFile() default "config";
	String defaultLocale() default "en_US";
	String[] supportedlocales() default "";
}
