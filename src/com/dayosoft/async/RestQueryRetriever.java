package com.dayosoft.async;

import java.util.HashMap;

import com.dayosoft.models.BudgetEntity;
import com.dayosoft.utils.CheckmarkClient;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import android.os.AsyncTask;

public class RestQueryRetriever extends AsyncTask<Void,Void,JsonObject> {
	
	QueryStatusCallback queryComplete;
	private CheckmarkClient client;
	private HashMap<String, String> params;
	private int method;
	private String url;
	
	public RestQueryRetriever(
			int method,
			String url,
			HashMap<String, String> params, 
			QueryStatusCallback queryCommplete) {
		this.queryComplete = queryCommplete;
		this.client = new CheckmarkClient();
		this.params = params;
		this.method = method;
		this.url = url;
	} 
	

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		queryComplete.onStart();
	}


	@Override
	protected void onPostExecute(JsonObject result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if (result!=null) {
		queryComplete.onComplete(result);
		};
		queryComplete.onFinish();
	}

	@Override
	protected JsonObject doInBackground(Void... p) {
		JsonObject response = null;
		try {
			response = client.query(url, params, method);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public static BudgetEntity resultToEntity(JsonElement elem) {
		BudgetEntity entity = new BudgetEntity();
		int budget_id = elem.getAsJsonObject().get("budget_id").getAsInt();
		String budget_description = elem.getAsJsonObject().get("budget_description").getAsString();
		entity.setId(budget_id);
		entity.setDisplayName(budget_description);
		return entity;
	}
}
