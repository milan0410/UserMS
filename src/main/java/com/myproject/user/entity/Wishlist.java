package com.myproject.user.entity;

import javax.persistence.Id;
import javax.persistence.IdClass;

import com.myproject.user.dto.MyKey;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
@IdClass(MyKey.class)
public class Wishlist implements Serializable {
	@Id
	@Column(name="BUYERID")
	int buyerId;
	@Id
	@Column(name="PRODID")
	int prodId;
	
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

}
