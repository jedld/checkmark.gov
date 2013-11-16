package com.dayosoft.checkmark;

import java.util.ArrayList;
import java.util.HashMap;

import com.dayosoft.adapters.ProjectListAdapter;
import com.dayosoft.animations.CheckMarkAnimations;
import com.dayosoft.async.QueryStatusCallback;
import com.dayosoft.async.RestQueryRetriever;
import com.dayosoft.models.BudgetEntity;
import com.dayosoft.utils.CheckmarkClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import android.os.Bundle;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		renderProjects("*");
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.action_settings:
			Intent intent = new Intent(this, SettingsActivity.class);
			this.startActivity(intent);
		}
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	private void renderProjects(final String q) {
		final ListView mainView = (ListView)this.findViewById(R.id.listViewProjects);
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("q", q);
		new RestQueryRetriever(CheckmarkClient.HTTP_GET,
				"ga/list",
				params, 
				new QueryStatusCallback() {

					@Override
					public void onComplete(JsonObject result) {
						int total = result.get("total").getAsInt();
						JsonArray res = result.getAsJsonArray("result");
						ArrayList <BudgetEntity> agencies = new ArrayList <BudgetEntity>();
						for(JsonElement elem : res) {
							BudgetEntity entity = RestQueryRetriever.resultToEntity(elem);
							agencies.add(entity);
						}
						mainView.setAdapter(new ProjectListAdapter(MainActivity.this, q, agencies, total, res.size()));
					}

					@Override
					public void onStart() {
						showLoader();
						
					}

					@Override
					public void onFinish() {
						hideLoader();
					}
			
		}).execute();
//			
//		BudgetEntity entity = new BudgetEntity();
//		entity.setDisplayName("Department of Education");
//		entity.setWeight(80);
//		entity.setMainImageUrl("http://image.shutterstock.com/display_pic_with_logo/91282/144566138/stock-photo-portrait-of-diligent-schoolkids-and-their-teacher-talking-at-lesson-144566138.jpg");
//
//		for (int i =0; i <20; i++) {
//			agencies.add(entity);
//		}
//		mainView.setAdapter(new ProjectListAdapter(this, agencies));
	}
	
	void showLoader() {
		ProgressBar loader = (ProgressBar)findViewById(R.id.progressBarLoading);
		loader.setVisibility(View.VISIBLE);
	}
	
	void hideLoader() {
		ProgressBar loader = (ProgressBar)findViewById(R.id.progressBarLoading);
		loader.setVisibility(View.GONE);
	}
}
