package com.dayosoft.checkmark;

import com.dayosoft.checkmark.util.LoginHelper;
import com.facebook.Session;
import com.facebook.UiLifecycleHelper;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionState;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SettingsActivity extends Activity implements StatusCallback {
	
	private UiLifecycleHelper uiHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		uiHelper = new UiLifecycleHelper(this, this);
		uiHelper.onCreate(savedInstanceState);
		
		Button signupButton = (Button)findViewById(R.id.buttonSignUpEmail);
		signupButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(SettingsActivity.this, SignupActivity.class);
				startActivity(intent);
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

	@Override
	public void call(Session session, SessionState state, Exception exception) {
		LoginHelper.onSessionStateChange(this, session, state, exception);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		uiHelper.onDestroy();
	}


}
