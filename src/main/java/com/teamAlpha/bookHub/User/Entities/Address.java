package com.teamAlpha.bookHub.User.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ADDRESS")
public class Address {
	
	
	@Column(name="ADDRESS_ID")
	private String addressId;
	
	@Id
	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="ADDRESSLINE_1")
	private String addressLine1;
	
	@Column(name="ADDRESSLINE_2")
	private String adrressLine2;
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="POSTALCODE")
	private String postalCode;
	
	@Column(name="COUNTRY")
	private String country;
	
	@Column(name="PHONE")
	private String phone;
	
	public Address(String addressId, String userId, String addressLine1, String adrressLine2, String city,
			String postalCode, String country, String phone) {
		super();
		this.addressId = addressId;
		this.userId = userId;
		this.addressLine1 = addressLine1;
		this.adrressLine2 = adrressLine2;
		this.city = city;
		this.postalCode = postalCode;
		this.country = country;
		this.phone = phone;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAdrressLine2() {
		return adrressLine2;
	}
	public void setAdrressLine2(String adrressLine2) {
		this.adrressLine2 = adrressLine2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", userId=" + userId + ", addressLine1=" + addressLine1
				+ ", adrressLine2=" + adrressLine2 + ", city=" + city + ", postalCode=" + postalCode + ", country="
				+ country + ", phone=" + phone + "]";
	}
	
	

}
