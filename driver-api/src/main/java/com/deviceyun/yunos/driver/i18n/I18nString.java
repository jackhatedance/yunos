package com.deviceyun.yunos.driver.i18n;

import java.util.HashMap;
import java.util.Map;

public class I18nString {

	/**
	 * key is locale, value is value
	 */
	private Map<String,String> valueMap=new HashMap<String,String>();
	
	public I18nString(String defaultValue) {
		valueMap.put(null, defaultValue);
	}
	
	public void add(String locale, String value){
		valueMap.put(locale, value);
	}
	/**
	 * get localized value.
	 * @param locale
	 * @return if not exist then return default value
	 */
	public String get(String locale){
		String value = valueMap.get(locale);
		if(value!=null)
			return value;
		else
			return valueMap.get(null);			
	}
	
	@Override
	public String toString() {
		
		return get(null);
	}
}
