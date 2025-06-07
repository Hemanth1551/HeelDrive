package com.example.Heel_Drive.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Payments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String orderid;
	private String userid;
	private String razorpaypaymentid;
	private String razorpayorderid;
	private String razorpaysignature;
	private String amount;
	private String status;
	private String createdat;
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
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getRazorpaypaymentid() {
		return razorpaypaymentid;
	}
	public void setRazorpaypaymentid(String razorpaypaymentid) {
		this.razorpaypaymentid = razorpaypaymentid;
	}
	public String getRazorpayorderid() {
		return razorpayorderid;
	}
	public void setRazorpayorderid(String razorpayorderid) {
		this.razorpayorderid = razorpayorderid;
	}
	public String getRazorpaysignature() {
		return razorpaysignature;
	}
	public void setRazorpaysignature(String razorpaysignature) {
		this.razorpaysignature = razorpaysignature;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreatedat() {
		return createdat;
	}
	public void setCreatedat(String createdat) {
		this.createdat = createdat;
	}
	@Override
	public String toString() {
		return "Payments [id=" + id + ", orderid=" + orderid + ", userid=" + userid + ", razorpaypaymentid="
				+ razorpaypaymentid + ", razorpayorderid=" + razorpayorderid + ", razorpaysignature="
				+ razorpaysignature + ", amount=" + amount + ", status=" + status + ", createdat=" + createdat + "]";
	}
	public Payments(int id, String orderid, String userid, String razorpaypaymentid, String razorpayorderid,
			String razorpaysignature, String amount, String status, String createdat) {
		super();
		this.id = id;
		this.orderid = orderid;
		this.userid = userid;
		this.razorpaypaymentid = razorpaypaymentid;
		this.razorpayorderid = razorpayorderid;
		this.razorpaysignature = razorpaysignature;
		this.amount = amount;
		this.status = status;
		this.createdat = createdat;
	}
	public Payments() {
		super();
	}
	
	
}
