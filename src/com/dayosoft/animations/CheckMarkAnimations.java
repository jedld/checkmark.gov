package com.dayosoft.animations;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.Animator.AnimatorListener;
import android.view.View;
import android.view.animation.AnimationSet;

public class CheckMarkAnimations {
	
	public static void bobble(final View view, final AnimatorListener finalListener) {
		AnimatorSet set = new AnimatorSet();
		ObjectAnimator animator = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 1.5f);
		animator.setDuration(250);
		ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 1.5f);
		animator.setDuration(250);
		set.playTogether(animator, animator2);
		set.addListener(new AnimatorListener() {

			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				AnimatorSet set = new AnimatorSet();
				ObjectAnimator animator = ObjectAnimator.ofFloat(view, "scaleX", 1.5f, 1.0f);
				animator.setDuration(250);
				ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "scaleY", 1.5f, 1.0f);
				animator.setDuration(250);
				set.playTogether(animator, animator2);
				set.addListener(finalListener);
				set.start();
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
		set.start();
	}
	public static void fadeOut(View view) {
		ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.0f);
		animator.setDuration(1000);
		animator.start();
		animator.addListener(new AnimatorListener() {

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
	}
	
}
