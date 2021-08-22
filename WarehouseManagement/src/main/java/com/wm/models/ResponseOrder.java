package com.wm.models;

public class ResponseOrder {
	private int orderId;
	private int transactionId;
	private int itemId;
	private int orderQty;
	private String itemName;
	private float itemPrice;
	private int invQuantity;
	private String status;
	private String customerName;
	private String employeeName;
	private String customerAddress;
	
	
	
	public ResponseOrder(int orderId, int transactionId, int itemId, int orderQty, String itemName, float itemPrice,
			int invQuantity, String status, String customerName, String employeeName, String customerAddress) {
		super();
		this.orderId = orderId;
		this.transactionId = transactionId;
		this.itemId = itemId;
		this.orderQty = orderQty;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.invQuantity = invQuantity;
		this.status = status;
		this.customerName = customerName;
		this.employeeName = employeeName;
		this.customerAddress = customerAddress;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getOrderQty() {
		return orderQty;
	}
	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public float getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(float itemPrice) {
		this.itemPrice = itemPrice;
	}
	public int getInvQuantity() {
		return invQuantity;
	}
	public void setInvQuantity(int invQuantity) {
		this.invQuantity = invQuantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	
	
	
	
	
	
}
