package com.dayosoft.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.commons.lang3.StringUtils;

import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

class CheckmarkClientResponse extends BasicResponseHandler {

	public CheckmarkClientResponse() {
	}

	@Override
	public String handleResponse(HttpResponse response)
			throws ClientProtocolException, IOException {

		long content_length = response.getEntity().getContentLength();
		Log.d(this.getClass().toString(), "status = "
				+ response.getStatusLine().getStatusCode());
		Log.d(this.getClass().toString(), "reason = "
				+ response.getStatusLine().getReasonPhrase());
		Log.d(this.getClass().toString(), "content length = " + content_length);
		if (response.getStatusLine().getStatusCode() != 200) {
			InputStreamReader reader = new InputStreamReader(response
					.getEntity().getContent());
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < content_length; i++) {
				buffer.append((char) reader.read());
			}
			return buffer.toString();
		} else {
			String responseBody = super.handleResponse(response);
			return responseBody;
		}
	}

}

public class CheckmarkClient {
	public static final int HTTP_GET = 0;
	public static final int HTTP_POST = 1;

	final String API_URL = "http://192.168.1.133:3000/api/v1/";
	static CheckmarkClient instance;
	String authToken;
	String apiKey;
	String apiSecret;
	String apid;

	public String getApid() {
		return apid;
	}

	public void setApid(String apid) {
		this.apid = apid;
	}
	
	
	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public JsonObject query(String path, HashMap<String, String> params,
			int method) {

		HttpClient httpclient = new DefaultHttpClient();
		HttpUriRequest request = null;
		BasicHttpParams basicParams = new BasicHttpParams();
		
		if (this.getAuthToken()!=null) {
			params.put("auth_token", this.getAuthToken());
		}
		
		if (method == CheckmarkClient.HTTP_GET) {
			ArrayList<String> urlparams = new ArrayList<String>();
			for (String key : params.keySet()) {
				String value = params.get(key);
				if (value != null) {
					urlparams.add(key + "=" + URLEncoder.encode(value));
					Log.v(this.getClass().toString(), key + " = " + value);
				}
			}
			String stringtype[] = new String[0];
			String fullurl = API_URL + path + "?"
					+ StringUtils.join(urlparams.toArray(stringtype), '&');
			request = new HttpGet(fullurl);
			Log.v(this.getClass().toString(), "Checkmark Request: GET " + fullurl);

		} else {
			request = new HttpPost( API_URL + path);
			

			HttpPost postRequest = (HttpPost) request;

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (String key : params.keySet()) {
				String value = params.get(key);
				if (value != null) {
					nvps.add(new BasicNameValuePair(key, value));
					Log.v(this.getClass().toString(), key + " = " + value);
				}
			}

			try {
				postRequest
						.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Log.d(this.getClass().toString(), "Checkmark Request: POST " + path);
		}

		HttpClientParams.setRedirecting(basicParams, true);
		request.setParams(basicParams);
		ResponseHandler<String> responseHandler = new CheckmarkClientResponse();
		try {
			String responseString = httpclient
					.execute(request, responseHandler);
			Log.v(this.getClass().toString(), "Checkmark Response: "
					+ responseString);
			JsonParser parser = new JsonParser();
			if (responseString != null && !responseString.equals("")) {
				JsonElement element = parser.parse(responseString);
				JsonObject object = element.getAsJsonObject();
				return object;
			} else {
				return null;
			}
		} catch (HttpResponseException e) {

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
