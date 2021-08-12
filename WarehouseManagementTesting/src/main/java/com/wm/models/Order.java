package com.wm.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="order")
public class Order {

	@Id
	@Column(name="order_id", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="item_name", nullable=false)
	private Item iname;
	
	@Column(name="quantity", nullable=false)
	private int quantity;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="author_id")
	private User author;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="resolver_id")
	private User resolver;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="i_status_id")
	private OrderStatus status;

	public Order() {
		
	}

	public Order(int id, Item iname, int quantity, User author, User resolver, OrderStatus status) {
		super();
		this.id = id;
		this.iname = iname;
		this.quantity = quantity;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
	}

	public Order(Item iname, int quantity, User author, User resolver, OrderStatus status) {
		super();
		this.iname = iname;
		this.quantity = quantity;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
	}

	public Order(Item iname, int quantity, User author, OrderStatus status) {
		super();
		this.iname = iname;
		this.quantity = quantity;
		this.author = author;
		this.status = status;
	}

	public Order(int id, Item iname, int quantity, User author, OrderStatus status) {
		super();
		this.id = id;
		this.iname = iname;
		this.quantity = quantity;
		this.author = author;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Item getIname() {
		return iname;
	}

	public void setIname(Item iname) {
		this.iname = iname;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public User getResolver() {
		return resolver;
	}

	public void setResolver(User resolver) {
		this.resolver = resolver;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", iname=" + iname + ", quantity=" + quantity + ", author=" + author + ", resolver="
				+ resolver + ", status=" + status + "]";
	}
	
	
}
