package com.wm.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.criteria.Order;

@Entity
@Table(name="custOrder")
public class CustOrder {

	@Id
	@Column(name="order_id", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="item_name", nullable=false)
	private Item iname;
	
	@Column(name="quantity", nullable=false)
	private int quantity;
	
	@JoinColumn(name="user_id")
	private User employee;
	
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="i_status_id")
	private PaymentStatus pStatus;
	
	public CustOrder(){
		
	}
	
	public CustOrder(int id, Item iname, int quantity, Customer customer, User employee, PaymentStatus pStatus) {
		super();
		this.id = id;
		this.iname = iname;
		this.quantity = quantity;
		this.customer = customer;
		this.employee = employee;
		this.pStatus = pStatus;
	}
	
	public CustOrder(Item iname, int quantity, Customer customer, User employee, PaymentStatus pStatus) {
		super();
		this.iname = iname;
		this.quantity = quantity;
		this.customer = customer;
		this.employee = employee;
		this.pStatus = pStatus;
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

	public User getEmployee() {
		return employee;
	}

	public void setEmployee(User employee) {
		this.employee = employee;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public PaymentStatus getpStatus() {
		return pStatus;
	}

	public void setpStatus(PaymentStatus pStatus) {
		this.pStatus = pStatus;
	}

	@Override
	public String toString() {
		return "CustOrder [id=" + id + ", iname=" + iname + ", quantity=" + quantity + ", employee=" + employee
				+ ", customer=" + customer + ", pStatus=" + pStatus + "]";
	}
	
	
}



