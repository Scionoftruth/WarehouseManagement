package com.wm.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Item {

	@Id
	@Column(name="item_id", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int item_id;
	
	@Column(name="price", nullable=false)
	private float price;
	
	@Column(name="quantity", nullable=false)
	private int quantity;

	

}
