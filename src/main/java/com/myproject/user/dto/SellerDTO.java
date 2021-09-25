package com.myproject.user.dto;

import com.myproject.user.entity.Seller;

public class SellerDTO {

	int sellerId;
	String name;
	String email;
	String phoneNumber;
	String password;
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
	
	public static SellerDTO valueOf(Seller seller)
	{
		SellerDTO sellerDTO=new SellerDTO();
		sellerDTO.setEmail(seller.getEmail());
		sellerDTO.setIsActive(seller.getIsActive());
		sellerDTO.setName(seller.getName());
		sellerDTO.setPassword(seller.getPassword());
		sellerDTO.setPhoneNumber(seller.getPhoneNumber());
		sellerDTO.setSellerId(seller.getSellerId());
		return sellerDTO;
	}
	public Seller createEntity()
	{
		Seller seller=new Seller();
		seller.setEmail(this.getEmail());
		seller.setIsActive(1);
		seller.setName(this.getName());
		seller.setPassword(this.getPassword());
		seller.setPhoneNumber(this.getPhoneNumber());
		seller.setSellerId(this.getSellerId());
		return seller;
	}

}
