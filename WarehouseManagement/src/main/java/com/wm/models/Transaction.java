package com.wm.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
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
@Table(name="employee")

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
}
