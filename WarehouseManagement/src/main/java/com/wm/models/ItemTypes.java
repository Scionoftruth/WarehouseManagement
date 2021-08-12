package com.wm.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wm.enums.ItemType;

@Entity
@Table(name="itype")
public class ItemTypes {

	@Id
	@Column(name="i_type_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="i_type")
	private ItemType item_type;

	public ItemTypes() {
		
	}

	public ItemTypes(int id, ItemType item_type) {
		super();
		this.id = id;
		this.item_type = item_type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ItemType getOrder_status() {
		return item_type;
	}

	public void setOrder_status(ItemType item_type) {
		this.item_type = item_type;
	}

	@Override
	public String toString() {
		return "" + item_type;
	}
	
}
