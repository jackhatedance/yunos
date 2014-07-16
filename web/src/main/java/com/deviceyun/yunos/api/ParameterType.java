package com.deviceyun.yunos.api;

public enum ParameterType {

	INTEGER {
		@Override
		public Object fromString(String str) {
			return INTEGER.valueOf(str);
		}
	},
	STRING {
		@Override
		public Object fromString(String str) {
			return str;
		}
	};

	public abstract Object fromString(String str);

	public static ParameterType getType(Class clazz) {
		if (clazz == Integer.class) {
			return INTEGER;
		} else if (clazz == String.class) {
			return STRING;
		} else
			throw new RuntimeException("unkown parameter type:"
					+ clazz.toString());

	}
}
