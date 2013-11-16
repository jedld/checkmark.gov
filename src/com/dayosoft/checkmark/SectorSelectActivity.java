package com.dayosoft.checkmark;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.widget.TextView;

public class SectorSelectActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sector_select);
		int customizeFont[] = {R.id.textViewDebtBurden, R.id.textViewDefence, 
				R.id.textViewEconomicServices, R.id.textViewPublicServices, R.id.textViewSocialServices};
		for(int id : customizeFont) {
			TextView myTextView=(TextView)findViewById(id);
			Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/SourceSansPro-Regular.otf");
			myTextView.setTypeface(typeFace);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sector_select, menu);
		return true;
	}

}
