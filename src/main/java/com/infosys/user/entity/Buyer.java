package com.infosys.user.entity;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Buyer {
    @Id
    @Column(name="BUYERID",length=11)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	int buyerId;
    @Column(name="NAME",nullable=false,length=45)
	String name;
    @Column(name="EMAIL",nullable=false,length=45)
	String email;
    @Column(name="PHONENUMBER",nullable=false,length=45)
	String phoneNumber;
    @Column(name="PASSWORD",nullable=false,length=45)
    String password;
    @Column(name="ISPRIVILEGED")
	int isPrivileged;
    @Column(name="REWARDPOINTS")
	int rewardPoints;
    @Column(name="ISACTIVE",length=1)
	int isActive;
	
	public int getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
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
	public int getIsPrivileged() {
		return isPrivileged;
	}
	public void setIsPrivileged(int isPrivileged) {
		this.isPrivileged = isPrivileged;
	}
	public int getRewardPoints() {
		return rewardPoints;
	}
	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	

}
