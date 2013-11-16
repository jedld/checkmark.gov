package com.dayosoft.checkmark;

import java.util.Timer;
import java.util.TimerTask;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class SplashActivity extends Activity {

	/**
	 * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
	 * user interaction before hiding the system UI.
	 */
	private static final int AUTO_HIDE_DELAY_MILLIS = 2000;

	/**
	 * The instance of the {@link SystemUiHider} for this activity.
	 */
	Button retryButton;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		Log.d(this.getClass().toString(), "onCreate()");
		Timer timer = new Timer();
		timer.schedule(closeSplashScreenRunnable,
				AUTO_HIDE_DELAY_MILLIS);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
	}

	TimerTask closeSplashScreenRunnable = new TimerTask() {


		@Override
		public void run() {
			Log.v(this.getClass().toString(), "Starting main activity");
			SplashActivity.this.finish();
			
				Intent intent = new Intent(SplashActivity.this,
						MainActivity.class);
				startActivity(intent);
			
		}

	};
}
