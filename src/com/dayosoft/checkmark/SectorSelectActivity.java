package com.dayosoft.checkmark;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SectorSelectActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sector_select);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sector_select, menu);
		return true;
	}

}
