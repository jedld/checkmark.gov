package com.dayosoft.checkmark;

import java.util.ArrayList;

import com.dayosoft.adapters.ProjectListAdapter;
import com.dayosoft.animations.CheckMarkAnimations;
import com.dayosoft.models.BudgetEntity;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import android.os.Bundle;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		renderAgencies();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	private void renderAgencies() {
		ListView mainView = (ListView)this.findViewById(R.id.listViewProjects);
		ArrayList <BudgetEntity> agencies = new ArrayList <BudgetEntity>();

		BudgetEntity entity = new BudgetEntity();
		entity.setDisplayName("Department of Education");
		entity.setWeight(80);
		entity.setMainImageUrl("http://image.shutterstock.com/display_pic_with_logo/91282/144566138/stock-photo-portrait-of-diligent-schoolkids-and-their-teacher-talking-at-lesson-144566138.jpg");

		for (int i =0; i <20; i++) {
			agencies.add(entity);
		}
		mainView.setAdapter(new ProjectListAdapter(this, agencies));
	}
}
