package com.myproject.user.dto;

import com.myproject.user.entity.Wishlist;

public class WishlistDTO {

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
	
	public static WishlistDTO valueOf(Wishlist wishlist)
	{
		WishlistDTO wishlistDTO=new WishlistDTO();
		wishlistDTO.setBuyerId(wishlist.getBuyerId());
		wishlist.setProdId(wishlist.getProdId());
		return wishlistDTO;
	}
	public Wishlist createEntity()
	{
		Wishlist wishlist=new Wishlist();
		wishlist.setBuyerId(this.getBuyerId());
		wishlist.setProdId(this.getProdId());
		return wishlist;
	}


}
