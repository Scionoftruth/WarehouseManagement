package com.wm.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name="transactions")
//@IdClass(TransactionId.class)

public class Transaction {
	
	@Id
	@Column(name="trans_id")
//	@ManyToOne(cascade=CascadeType.ALL)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int transId;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Employee empId;

	@ManyToOne(cascade=CascadeType.ALL)
	private Customer custId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status", nullable=false)
	private StatusEnum status;

	public Transaction(Customer custId) {
		this.custId = custId;
		this.status = StatusEnum.SUBMITTED;
	}
}
