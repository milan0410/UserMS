package com.infosys.user.entity;

import javax.persistence.Id;
import javax.persistence.IdClass;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
@IdClass(MyKey.class)
public class Cart implements Serializable{
	@Id
	@Column(name="BUYERID")
	int buyerId;
	@Id
	@Column(name="PRODID")
	int prodId;
	@Column(name="QUANTITY",nullable=false)
	int quantity;
	public int getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
