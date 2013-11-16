package com.dayosoft.async;

import com.google.gson.JsonObject;

public interface QueryStatusCallback {

	void onComplete(JsonObject result);
	
	void onStart();
	
	void onFinish();

}
