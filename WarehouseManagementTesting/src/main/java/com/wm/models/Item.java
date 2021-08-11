package com.wm.models;


import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


public class Item {

	@Id
	@Column(name="item_id", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int item_id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="i_type_id")
	private ItemTypes type;
	
	@Column(name="name")
	private String itemname;
	
	@Column(name="price")
	private double price;
	
	@Column(name="quantity", nullable=false)
	private int quantity;

	
	
	public Item() {
		
	}

	public Item(int item_id, ItemTypes type, String itemname, double price, int quantity) {
		super();
		this.item_id = item_id;
		this.type = type;
		this.itemname = itemname;
		this.price = price;
		this.quantity = quantity;
	}

	public Item(ItemTypes type, String itemname, double price, int quantity) {
		super();
		this.type = type;
		this.itemname = itemname;
		this.price = price;
		this.quantity = quantity;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public ItemTypes getType() {
		return type;
	}

	public void setType(ItemTypes type) {
		this.type = type;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Item [item_id=" + item_id + ", type=" + type + ", itemname=" + itemname + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}

}
