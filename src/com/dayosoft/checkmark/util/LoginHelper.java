package com.dayosoft.checkmark.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;

public class LoginHelper {

	public static boolean isLoggedIn(Context context) {
		SharedPreferences prefs = context.getSharedPreferences("checkmark",
				Context.MODE_PRIVATE);
		if (prefs.contains("auth_token")) {
			return true;
		}
		return false;
	}
	
	public static void storeCurrentUser(Context context, String auth_token) {
		SharedPreferences prefs = context.getSharedPreferences("checkmark",
				Context.MODE_PRIVATE);
		Editor editor = prefs.edit();
		editor.putString("auth_token", auth_token);
		editor.commit();
	}

	public static String getCurrentUser(Context context) {
		SharedPreferences prefs = context.getSharedPreferences("checkmark",
				Context.MODE_PRIVATE);
		return prefs.getString("auth_token", null);
	}

	public static void onSessionStateChange(final Activity activity,
			final Session session, SessionState state, Exception exception) {
		Log.d(activity.getClass().toString(), "session state change called...");
		if (state.isOpened()) {
			Log.d(activity.getClass().toString(), "Session state opened");
			if (session != null && session.isOpened()) {
				Log.d(activity.getClass().toString(), "making API request");
				// If the session is open, make an API call to get user data
				// and define a new callback to handle the response
				Request request = Request.newMeRequest(session,
						new Request.GraphUserCallback() {
							@Override
							public void onCompleted(GraphUser user,
									Response response) {
								SharedPreferences prefs = activity
										.getSharedPreferences("checkmark",
												Context.MODE_PRIVATE);

								String user_id = user.getId();
								String ac = session.getAccessToken();
								Editor editor = prefs.edit();
								// editor.putInt("user_id", arg1)
							}
						});
				Request.executeBatchAsync(request);
			}
		} else if (state.isClosed()) {
			Log.d(activity.getClass().toString(), "Session state is closed.");
		}
	}
}
