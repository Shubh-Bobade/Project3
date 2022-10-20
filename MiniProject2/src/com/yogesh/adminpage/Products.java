package com.yogesh.adminpage;

public class Products {
	
	private int productId;
	private String productName;
	private int price;
	private int quantity;
	private String description;
	
	public Products() {
		super();
		
	}
		
	public Products(int productId, String productName, int price, int quantity, String description) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
	}
	
	public Products(String productName, int price, int quantity, String description) {
		super();
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
	}
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Products [productId=" + productId + ", productName=" + productName + ", "
				+ "price=" + price + ", quantity="+ quantity + ", description=" + description + "]";
	}
	
	
}
