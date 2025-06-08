package com.example.Heel_Drive.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String userid;
	private String addresstype;
	private String addressline;
	private String city;
	private String state;
	private String zipcode;
	private String country;
	private String isdefault;
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
	public String getAddresstype() {
		return addresstype;
	}
	public void setAddresstype(String addresstype) {
		this.addresstype = addresstype;
	}
	public String getAddressline() {
		return addressline;
	}
	public void setAddressline(String addressline) {
		this.addressline = addressline;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getIsdefault() {
		return isdefault;
	}
	public void setIsdefault(String isdefault) {
		this.isdefault = isdefault;
	}
	public String getCreatedat() {
		return createdat;
	}
	public void setCreatedat(String createdat) {
		this.createdat = createdat;
	}
	
	@Override
	public String toString() {
		return "Address [id=" + id + ", userid=" + userid + ", addresstype=" + addresstype + ", addressline="
				+ addressline + ", city=" + city + ", state=" + state + ", zipcode=" + zipcode + ", country=" + country
				+ ", isdefault=" + isdefault + ", createdat=" + createdat + "]";
	}
	public Address(int id, String userid, String addresstype, String addressline, String city, String state,
			String zipcode, String country, String isdefault, String createdat) {
		super();
		this.id = id;
		this.userid = userid;
		this.addresstype = addresstype;
		this.addressline = addressline;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.country = country;
		this.isdefault = isdefault;
		this.createdat = createdat;
	}
	public Address() {
		super();
	}
	
	

}
