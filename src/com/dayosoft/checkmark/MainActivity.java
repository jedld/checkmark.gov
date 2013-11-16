package com.dayosoft.checkmark;

import java.util.ArrayList;

import com.dayosoft.models.BudgetEntity;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	ArrayList <BudgetEntity> agencies = new ArrayList <BudgetEntity>();
	private LayoutInflater layoutInflater;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.layoutInflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		BudgetEntity entity = new BudgetEntity();
		entity.setDisplayName("Department of Education");
		entity.setWeight(80);
		entity.setMainImageUrl("http://image.shutterstock.com/display_pic_with_logo/91282/144566138/stock-photo-portrait-of-diligent-schoolkids-and-their-teacher-talking-at-lesson-144566138.jpg");
		for (int i =0; i <20; i++) {
			agencies.add(entity);
		}
		renderAgencies();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	private void renderAgencies() {
		ViewGroup mainView = (ViewGroup)this.findViewById(R.id.agencies);
		for (BudgetEntity agency : agencies) {
			View view = layoutInflater.inflate(R.layout.main_entity, null, false);
			ImageView image = (ImageView)view.findViewById(R.id.imageViewAgency);
			TextView name = (TextView)view.findViewById(R.id.agencyName);
			name.setText(agency.getDisplayName());
			UrlImageViewHelper.setUrlDrawable(image, agency.getMainImageUrl());
			mainView.addView(view);
		}
	}
}
