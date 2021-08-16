package com.wm.models;

import java.io.Serializable;
import java.util.Objects;

public class OrderId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Order orderId;
	private Item itemId;
	
	public OrderId() {
		
	}
	
	public OrderId(Order orderId, Item itemId) {
		this.orderId=orderId;
		this.itemId=itemId;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o==null||getClass() != o.getClass()) return false;
		OrderId oId = (OrderId) o;
		return orderId.equals(oId.orderId)&&
				itemId.equals(oId.itemId);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(orderId, itemId);
	}
}
