package com.infosys.user.dto;

import java.util.Date;
import java.util.List;


public class OrderDetailsDTO {

	int orderId;

	int buyerId;
	
	double amount;

	Date date;

	String address;
	
	String status;
	
	List<ProductsOrderedDTO> productsOrdered;



		public int getOrderId() {
			return orderId;
		}

		public void setOrderId(int orderId) {
			this.orderId = orderId;
		}

		public int getBuyerId() {
			return buyerId;
		}

		public void setBuyerId(int buyerId) {
			this.buyerId = buyerId;
		}

		public double getAmount() {
			return amount;
		}

		public void setAmount(double amount) {
			this.amount = amount;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public List<ProductsOrderedDTO> getProductsOrdered() {
			return productsOrdered;
		}

		public void setProductsOrdered(List<ProductsOrderedDTO> productsOrdered) {
			this.productsOrdered = productsOrdered;
		}

}
