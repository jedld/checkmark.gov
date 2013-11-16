package com.dayosoft.async;

import java.util.HashMap;

import com.dayosoft.utils.CheckmarkClient;
import com.google.gson.JsonObject;

import android.os.AsyncTask;

public class RestQueryRetriever extends AsyncTask<Void,Void,JsonObject> {
	
	OnQueryComplete queryCommplete;
	private CheckmarkClient client;
	private HashMap<String, String> params;
	private int method;
	private String url;
	
	public RestQueryRetriever(CheckmarkClient client,
			int method,
			String url,
			HashMap<String, String> params, 
			OnQueryComplete queryCommplete) {
		this.queryCommplete = queryCommplete;
		this.client = client;
		this.params = params;
		this.method = method;
		this.url = url;
	} 
	

	@Override
	protected void onPostExecute(JsonObject result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		queryCommplete.onComplete(result);
	}

	@Override
	protected JsonObject doInBackground(Void... p) {
		JsonObject response = client.query(url, params, method);
		return response;
	}
	
	
}
