package com.dayosoft.models;

import java.util.ArrayList;

public class BudgetEntity {

	int weight;
	long id;
	String displayName;
	String mainImageUrl;
	String budgetTotal;
	
	public String getBudgetTotal() {
		return budgetTotal;
	}

	public void setBudgetTotal(String budgetTotal) {
		this.budgetTotal = budgetTotal;
	}

	ArrayList<BudgetEntity> subEntities = new ArrayList<BudgetEntity>();

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
