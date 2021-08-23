package com.wm.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="item")
public class Item {
	
	@Id
	@Column(name="item_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int itemId;

	@Column(name="item_name", nullable=false)
	private String itemName;
	
	@Column(name="item_price")
	private float itemPrice;
	
	@Column(name="inv_quantity")
	private int invQuantity;
	
	@Column(name="inv_buffer")
	private int buffer;

	public Item(String itemName, float itemPrice, int invQuantity) {
		super();
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.invQuantity = invQuantity;
		this.buffer = 0;
	}

	public Item(String itemName, float itemPrice) {
		super();
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.invQuantity = 0;
		this.buffer = 0;
	}
	
	public Item(int itemId, String itemName, float itemPrice, int invQuantity) {
		super();
		this.itemId=itemId;
		this.itemName=itemName;
		this.itemPrice=itemPrice;
		this.invQuantity=invQuantity;
		this.buffer=0;
	}
	
	
	
	
}
