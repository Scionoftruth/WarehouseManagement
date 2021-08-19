package com.wm.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="i_order")
//@IdClass(OrderId.class)

public class Order {


	@Id
	@Column(name="order_id") // dummy value
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int orderId;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="trans_id")
	private Transaction transId;
	
	@Column(name="order_quantity", nullable=false)
	private int orderQty;
	
	@ManyToOne(cascade= CascadeType.ALL)
	private Item itemId;

	public Order(int orderId, int orderQty, Item itemId) {
		super();
		this.orderQty = orderQty;
		this.itemId = itemId;
	}
	
	
}
