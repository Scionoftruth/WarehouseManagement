package com.wm.models;

import java.io.Serializable;
import java.util.Objects;

public class TransactionId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Order orderId;
	private Customer custId;
	
	public TransactionId() {
		
	}
	
	public TransactionId(Order orderId, Customer custId) {
		this.orderId=orderId;
		this.custId=custId;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o==null||getClass() != o.getClass()) return false;
		TransactionId transactionId = (TransactionId) o;
		return orderId.equals(transactionId.orderId)&&
				custId.equals(transactionId.custId);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(orderId, custId);
	}

}
