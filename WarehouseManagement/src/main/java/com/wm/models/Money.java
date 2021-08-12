package com.wm.models;

import javax.persistence.Column;
import javax.persistence.Id;

public class Money {

	@Id
	@Column(name="money", nullable=false)
	private int id = 1;
	
	@Column(name="profit", nullable=false)
	private double profit;
	
	@Column(name="loss", nullable=false)
	private double loss;

	public Money() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Money(int id, double profit, double loss) {
		super();
		this.id = id;
		this.profit = profit;
		this.loss = loss;
	}

	public Money(double profit, double loss) {
		super();
		this.profit = profit;
		this.loss = loss;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public double getLoss() {
		return loss;
	}

	public void setLoss(double loss) {
		this.loss = loss;
	}

	@Override
	public String toString() {
		return "Money [id=" + id + ", profit=" + profit + ", loss=" + loss + "]";
	}
	
	
	
}
