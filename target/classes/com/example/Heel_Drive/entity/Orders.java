package com.example.Heel_Drive.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String userid;
	private String totalprice;
	private String status;
	private String paymentstatus;
	private String addressid;
	private String createdat;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPaymentstatus() {
		return paymentstatus;
	}
	public void setPaymentstatus(String paymentstatus) {
		this.paymentstatus = paymentstatus;
	}
	public String getAddressid() {
		return addressid;
	}
	public void setAddressid(String addressid) {
		this.addressid = addressid;
	}
	public String getCreatedat() {
		return createdat;
	}
	public void setCreatedat(String createdat) {
		this.createdat = createdat;
	}
	@Override
	public String toString() {
		return "Orders [id=" + id + ", userid=" + userid + ", totalprice=" + totalprice + ", status=" + status
				+ ", paymentstatus=" + paymentstatus + ", addressid=" + addressid + ", createdat=" + createdat + "]";
	}
	public Orders(int id, String userid, String totalprice, String status, String paymentstatus, String addressid,
			String createdat) {
		super();
		this.id = id;
		this.userid = userid;
		this.totalprice = totalprice;
		this.status = status;
		this.paymentstatus = paymentstatus;
		this.addressid = addressid;
		this.createdat = createdat;
	}
	public Orders() {
		super();
	}
	
	
	
	

}
