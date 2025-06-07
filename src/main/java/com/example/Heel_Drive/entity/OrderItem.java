package com.example.Heel_Drive.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String orderid;
	private String productid;
	private String quantity;
	private String perunitprice;
	private String totalprice;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getPerunitprice() {
		return perunitprice;
	}
	public void setPerunitprice(String perunitprice) {
		this.perunitprice = perunitprice;
	}
	public String getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", orderid=" + orderid + ", productid=" + productid + ", quantity=" + quantity
				+ ", perunitprice=" + perunitprice + ", totalprice=" + totalprice + "]";
	}
	public OrderItem(int id, String orderid, String productid, String quantity, String perunitprice,
			String totalprice) {
		super();
		this.id = id;
		this.orderid = orderid;
		this.productid = productid;
		this.quantity = quantity;
		this.perunitprice = perunitprice;
		this.totalprice = totalprice;
	}
	public OrderItem() {
		super();
	}
	
	

}
