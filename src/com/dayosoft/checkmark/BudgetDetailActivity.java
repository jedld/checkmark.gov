package com.dayosoft.checkmark;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class BudgetDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_budget_detail);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.budget_detail, menu);
		return true;
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.animator.slide_in_left_to_right,
				R.animator.slide_out_left_to_right);
	}

}
