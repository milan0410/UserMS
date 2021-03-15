package com.infosys.user.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MyKey implements Serializable{

	int buyerId;
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

	@Override
	public boolean equals(Object obj)
	{  MyKey myKey=(MyKey)obj;
		if(myKey.getBuyerId()==this.getBuyerId()&&myKey.getProdId()==this.getProdId())
		{
			return true;
		}
		return false;
	}
}
