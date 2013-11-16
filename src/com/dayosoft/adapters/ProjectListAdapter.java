package com.dayosoft.adapters;

import java.util.ArrayList;

import com.dayosoft.animations.CheckMarkAnimations;
import com.dayosoft.checkmark.R;
import com.dayosoft.models.BudgetEntity;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class ProjectListAdapter implements ListAdapter {

	Context context;
	private LayoutInflater layoutInflater;
	ArrayList <BudgetEntity> list = new ArrayList <BudgetEntity>();
	
	public ProjectListAdapter(Context context, ArrayList<BudgetEntity> list) {
		this.context = context;
		this.list = list;
		this.layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0).getId();
	}

	@Override
	public int getItemViewType(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int index, View oldView, ViewGroup viewGroup) {
		View view = layoutInflater.inflate(R.layout.main_entity, null, false);
		ImageView image = (ImageView)view.findViewById(R.id.imageViewAgency);
		TextView name = (TextView)view.findViewById(R.id.agencyName);
		
		BudgetEntity entity = this.list.get(index);
		
		name.setText(entity.getDisplayName());
		UrlImageViewHelper.setUrlDrawable(image, entity.getMainImageUrl());
		
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				CheckMarkAnimations.zoomOutExitRight(context, arg0, new AnimatorListener() {

					@Override
					public void onAnimationCancel(Animator arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onAnimationEnd(Animator arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onAnimationRepeat(Animator arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onAnimationStart(Animator arg0) {
						// TODO Auto-generated method stub
						
					}
					
				});
//				CheckMarkAnimations.bobble(arg0, new AnimatorListener() {
//
//					@Override
//					public void onAnimationCancel(Animator animation) {
//						// TODO Auto-generated method stub
//						
//					}
//
//					@Override
//					public void onAnimationEnd(Animator animation) {
//						// TODO Auto-generated method stub
//						
//					}
//
//					@Override
//					public void onAnimationRepeat(Animator animation) {
//						// TODO Auto-generated method stub
//						
//					}
//
//					@Override
//					public void onAnimationStart(Animator animation) {
//						// TODO Auto-generated method stub
//						
//					}
//					
//				});
			}
			
		});
		return view;
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean areAllItemsEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
