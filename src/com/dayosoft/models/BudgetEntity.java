package com.dayosoft.models;

import java.util.ArrayList;

public class BudgetEntity {

	int weight;
	String id;
	String displayName;
	String mainImageUrl;
	ArrayList<BudgetEntity> subEntities = new ArrayList<BudgetEntity>();

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getMainImageUrl() {
		return mainImageUrl;
	}

	public void setMainImageUrl(String mainImageUrl) {
		this.mainImageUrl = mainImageUrl;
	}

	public ArrayList<BudgetEntity> getSubEntities() {
		return subEntities;
	}

	public void setSubEntities(ArrayList<BudgetEntity> subEntities) {
		this.subEntities = subEntities;
	}

}
