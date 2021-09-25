package com.myproject.user.dto;

import com.myproject.user.entity.Buyer;

public class BuyerDTO {

	int buyerId;
	String name;
	String email;
	String phoneNumber;
	String password;
	int isPrivileged;
	int rewardPoints;
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
	
	public static BuyerDTO valueOf(Buyer buyer)
	{
		BuyerDTO buyerDTO= new BuyerDTO();
		buyerDTO.setBuyerId(buyer.getBuyerId());
		buyerDTO.setEmail(buyer.getEmail());
		buyerDTO.setIsActive(buyer.getIsActive());
		buyerDTO.setIsPrivileged(buyer.getIsPrivileged());
		buyerDTO.setName(buyer.getName());
		buyerDTO.setPhoneNumber(buyer.getPhoneNumber());
		buyerDTO.setPassword(buyer.getPassword());
		buyerDTO.setRewardPoints(buyer.getRewardPoints());
		return buyerDTO;
	}
	public Buyer createEntity()
	{
		Buyer buyer=new Buyer();
		buyer.setBuyerId(this.getBuyerId());
		buyer.setEmail(this.getEmail());
		buyer.setIsActive(1);
		buyer.setIsPrivileged(this.getIsPrivileged());
		buyer.setName(this.getName());
		buyer.setPassword(this.getPassword());
		buyer.setPhoneNumber(this.getPhoneNumber());
		buyer.setRewardPoints(this.getRewardPoints());
		return buyer;
	}
   
}
