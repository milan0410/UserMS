package com.myproject.user.dto;

import com.myproject.user.entity.Cart;

public class CartDTO {

	
	int buyerId;
	int prodId;
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
	
	public static CartDTO valueOf(Cart cart)
	{
		CartDTO cartDTO=new CartDTO();
		cartDTO.setQuantity(cart.getQuantity());
		cartDTO.setBuyerId(cart.getBuyerId());
		cartDTO.setProdId(cart.getProdId());

		return cartDTO;
	}
	public Cart createEntity()
	{
		Cart cart =new Cart();
		cart.setBuyerId(this.getBuyerId());
		cart.setProdId(this.getProdId());
		cart.setQuantity(this.getQuantity());
		return cart;
	}

	
}
