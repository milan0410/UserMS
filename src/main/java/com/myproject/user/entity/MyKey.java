package com.myproject.user.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MyKey implements Serializable{

	private int buyerId;
	private int prodId;
	
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
	{   if(obj==null)
    	return false;
	    if(obj.getClass()!=this.getClass())
	    	return false;
		MyKey myKey=(MyKey)obj;
	  
		if(myKey.getBuyerId()==this.getBuyerId()&&myKey.getProdId()==this.getProdId())
		{
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode()
	{
		return this.buyerId+this.prodId;
	}
}
