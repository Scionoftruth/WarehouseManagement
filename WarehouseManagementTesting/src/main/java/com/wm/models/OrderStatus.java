package com.wm.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wm.enums.OStatus;

@Entity
@Table(name="ostatus")
public class OrderStatus {

	@Id
	@Column(name="o_status_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="o_status")
	private OStatus order_status;

	public OrderStatus() {
		
	}

	public OrderStatus(int id, OStatus order_status) {
		super();
		this.id = id;
		this.order_status = order_status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public OStatus getOrder_status() {
		return order_status;
	}

	public void setOrder_status(OStatus order_status) {
		this.order_status = order_status;
	}

	@Override
	public String toString() {
		return "" + order_status;
	}
	
	
	
}
