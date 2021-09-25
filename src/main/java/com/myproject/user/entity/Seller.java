package com.myproject.user.entity;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Entity
public class Seller {
	@Id
	@Column(name="SELLERID",length=11)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int sellerId;
	@Column(name="NAME",nullable=false,length=45)
	String name;
	@Column(name="EMAIL",nullable=false,length=45)
	String email;
	@Column(name="PHONENUMBER",nullable=false,length=45)
	String phoneNumber;
	@Column(name="PASSWORD",nullable=false,length=45)
	String password;
	@Column(name="ISACTIVE")
	int isActive;
	
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	

}
