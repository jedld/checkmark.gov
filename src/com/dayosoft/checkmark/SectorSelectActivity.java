package com.dayosoft.checkmark;

import com.dayosoft.animations.CheckMarkAnimations;

import android.os.Bundle;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class SectorSelectActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sector_select);
		
		int containerIds[] = {R.id.selectDebtBurden, R.id.selectDefence, 
				R.id.selectEconomicServices, R.id.selectPublicServices, R.id.selectSocialServices};
		
		int customizeFont[] = {R.id.textViewDebtBurden, R.id.textViewDefence, 
				R.id.textViewEconomicServices, R.id.textViewPublicServices, R.id.textViewSocialServices};
		
		for(int id: containerIds) {
			View view=(View)findViewById(id);
			view.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(final View view) {
					CheckMarkAnimations.bobble(view, new AnimatorListener() {

						@Override
						public void onAnimationCancel(Animator animation) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void onAnimationEnd(Animator animation) {
							Intent intent = new Intent(SectorSelectActivity.this, MainActivity.class);
							intent.putExtra("sector", (String)view.getTag());
							startActivity(intent);
							
						}

						@Override
						public void onAnimationRepeat(Animator animation) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void onAnimationStart(Animator animation) {
							// TODO Auto-generated method stub
							
						}
						
					});
					
				}
				
			});

		}
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
