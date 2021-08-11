package com.wm.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wm.enums.PStatus;

@Entity
@Table(name="pstatus")
public class PaymentStatus {

	@Id
	@Column(name="p_status_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="p_status")
	private PStatus payment_status;
	
	public PaymentStatus(){
		
	}
	
	public PaymentStatus(int id, PStatus payment_status) {
		super();
		this.id = id;
		this.payment_status = payment_status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PStatus getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(PStatus payment_status) {
		this.payment_status = payment_status;
	}

	@Override
	public String toString() {
		return "" + payment_status;
	}
	
	
	
}
