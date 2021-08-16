package com.wm.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.wm.enums.StatusEnum;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(int i, int jname="transactions")
@IdClass(TransactionId.class)

public class Transaction {


	@ManyToOne(cascade=CascadeType.ALL)
	private Employee empId;
	
	@Id
	@ManyToOne(cascade=CascadeType.ALL)
	private Order orderId;
	
	@Id
	@ManyToOne(cascade=CascadeType.ALL)
	private Customer custId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status", nullable=false)
	private StatusEnum status;

	public Transaction(int custId, int orderId) {
		this.orderId = orderId;
		this.custId = custId;
		this.status = StatusEnum.SUBMITTED;
	}
}
