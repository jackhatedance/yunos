package com.driverstack.yunos.api;

public enum ParameterType {

	INTEGER {
		@Override
		public Object fromString(String str) {
			return Integer.valueOf(str);
		}
	},

	LONG {
		@Override
		public Object fromString(String str) {
			return Long.valueOf(str);
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
		if (clazz == Integer.class || clazz == int.class) {
			return INTEGER;
		} else if (clazz == Long.class || clazz == long.class) {
			return LONG;
		} else if (clazz == String.class) {
			return STRING;
		} else
			throw new RuntimeException("unkown parameter type:"
					+ clazz.toString());

	}
}
