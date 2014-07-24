package com.deviceyun.yunos.service;

import java.util.Map;

public interface RemoteService {

	public abstract Object urlApi(Map<String, String> parameters);

}