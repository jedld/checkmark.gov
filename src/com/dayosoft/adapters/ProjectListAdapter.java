package com.dayosoft.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import com.dayosoft.animations.CheckMarkAnimations;
import com.dayosoft.async.QueryStatusCallback;
import com.dayosoft.async.RestQueryRetriever;
import com.dayosoft.checkmark.BudgetDetailActivity;
import com.dayosoft.checkmark.LoginActivity;
import com.dayosoft.checkmark.MainActivity;
import com.dayosoft.checkmark.R;
import com.dayosoft.checkmark.util.LoginHelper;
import com.dayosoft.models.BudgetEntity;
import com.dayosoft.utils.CheckmarkClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class ProjectListAdapter implements ListAdapter {

	Activity context;
	private LayoutInflater layoutInflater;
	ArrayList<BudgetEntity> list = new ArrayList<BudgetEntity>();
	ArrayList<DataSetObserver> observers = new ArrayList<DataSetObserver>();
	boolean queryInProgress = false;

	static final int PER_PAGE = 10;
	int total_pages;
	int total_count;
	int items_on_last_page;
	int pages_cached = 1;
	String q;

	int compute_page(int total_items) {
		return total_items % PER_PAGE == 0 ? (total_items / PER_PAGE)
				: (total_items / PER_PAGE) + 1;
	}

	public ProjectListAdapter(Activity context, String q,
			ArrayList<BudgetEntity> list, int total_count, int last_page_count) {
		this.context = context;
		this.list = list;
		this.total_count = total_count;
		this.q = q;
		this.total_pages = compute_page(total_count);
		this.items_on_last_page = last_page_count;
		this.layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		if (pages_cached < total_pages) {
			return list.size() + 1;
		} else {
			return list.size();
		}
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		if (position < list.size()) {
			return list.get(position).getId();
		} else {
			return 0;
		}
	}

	@Override
	public int getItemViewType(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	private synchronized void loadMore(int position) {
		Log.d(this.getClass().toString(), "CACHED: " + pages_cached
				+ " total :" + total_pages);
		if (pages_cached < total_pages
				&& position > (list.size() - (items_on_last_page / 2) + 1)) {
			Log.d(this.getClass().toString(), "load more elements requested");
			if (!this.queryInProgress) {
				this.queryInProgress = true;
				HashMap<String, String> params = new HashMap<String, String>();
				params.put("q", q);
				params.put("from",
						Integer.toString((pages_cached * PER_PAGE) + 1));
				new RestQueryRetriever(CheckmarkClient.HTTP_GET, "ga/list",
						params, new QueryStatusCallback() {

							@Override
							public void onComplete(JsonObject result) {
								JsonArray res = result.getAsJsonArray("result");
								int current_page = compute_page(result.get(
										"from").getAsInt());

								ProjectListAdapter.this.items_on_last_page = res
										.size();
								ProjectListAdapter.this.pages_cached += 1;

								ArrayList<BudgetEntity> agencies = new ArrayList<BudgetEntity>();
								for (JsonElement elem : res) {
									BudgetEntity entity = RestQueryRetriever
											.resultToEntity(elem);
									agencies.add(entity);
								}
								ProjectListAdapter.this.list.addAll(agencies);
								onChanged();
								ProjectListAdapter.this.queryInProgress = false;
							}

							@Override
							public void onStart() {
							}

							@Override
							public void onFinish() {
							}

						}).execute();

			}
		}
	}

	class VoteClickListener implements OnClickListener {

		public static final int VOTE_UP = 1;
		public static final int VOTE_DOWN = 2;
		long id;
		int vote_type;

		public VoteClickListener(long id, int vote_type) {
			this.id = id;
			this.vote_type = vote_type;
		}

		@Override
		public void onClick(final View view) {
			if (!LoginHelper.isLoggedIn(context)) {
				Intent intent = new Intent(context, LoginActivity.class);
				context.startActivity(intent);
			} else {
				CheckMarkAnimations.bobble(view, new AnimatorListener() {

					@Override
					public void onAnimationCancel(Animator arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationEnd(Animator arg0) {
						HashMap<String, String> params = new HashMap<String, String>();
						params.put("vote[vote_type]",
								Integer.toString(vote_type));
						params.put("vote[comment]", "");
						RestQueryRetriever rest = new RestQueryRetriever(
								CheckmarkClient.HTTP_POST, "votes/ga/" + id,
								params, new QueryStatusCallback() {

									@Override
									public void onComplete(JsonObject result) {
										// TODO Auto-generated method stub

									}

									@Override
									public void onStart() {
										// TODO Auto-generated method stub

									}

									@Override
									public void onFinish() {
										// TODO Auto-generated method stub

									}

								});
						rest.getClient().setAuthToken(
								LoginHelper.getCurrentUser(context));
						rest.execute();
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
	}

	@Override
	public View getView(int index, View oldView, ViewGroup viewGroup) {

		loadMore(index);
		if (index < list.size()) {
			View view = layoutInflater.inflate(R.layout.main_entity, null,
					false);
			View vote = (View) view.findViewById(R.id.buttonVote);

			View issuses = (View) view.findViewById(R.id.buttonHasIssues);

			ImageView image = (ImageView) view
					.findViewById(R.id.imageViewAgency);
			TextView name = (TextView) view.findViewById(R.id.agencyName);

			BudgetEntity entity = this.list.get(index);

			view.setTag(Long.toString(entity.getId()));

			name.setText(entity.getDisplayName());
			UrlImageViewHelper.setUrlDrawable(image, entity.getMainImageUrl());

			vote.setOnClickListener(new VoteClickListener(entity.getId(),
					VoteClickListener.VOTE_UP));

			issuses.setOnClickListener(new VoteClickListener(entity.getId(),
					VoteClickListener.VOTE_DOWN));

			view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(final View view) {

					Intent intent = new Intent(context,
							BudgetDetailActivity.class);
					intent.putExtra("id", (String) view.getTag());
					context.startActivity(intent);
					context.overridePendingTransition(
							R.animator.slide_in_right_to_left,
							R.animator.slide_out_right_to_left);
				}
			});
			return view;
		} else {
			return layoutInflater.inflate(R.layout.loader_item, viewGroup,
					false);
		}
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
	public boolean areAllItemsEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public void onChanged() {
		for (DataSetObserver observer : observers) {
			observer.onChanged();
		}
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		observers.add(observer);
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		observers.remove(observer);
	}

}
