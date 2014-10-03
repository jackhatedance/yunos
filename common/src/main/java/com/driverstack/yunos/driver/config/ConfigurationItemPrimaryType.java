package com.driverstack.yunos.driver.config;

public enum ConfigurationItemPrimaryType {

	STRING {
		@Override
		public Object fromString(String str) {

			return str;
		}
	},
	INTEGER {
		@Override
		public Object fromString(String str) {

			return new Integer(str);
		}
	},
	LONG {
		@Override
		public Object fromString(String str) {

			return new Long(str);
		}
	},
	FLOAT {
		@Override
		public Object fromString(String str) {

			return new Float(str);
		}
	},
	ENUM, DEVICE;

	public Object fromString(String str) {

		return null;
	}
}
