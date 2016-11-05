package org.test.jpa.model.helper;

public class CartItemCookies {

	private Long productId;
	private int quantity;
	
	public CartItemCookies() {
	
	}

	public CartItemCookies(Long productId, int quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof CartItemCookies))
			return false;	
		if (obj == this)
			return true;
		CartItemCookies item = (CartItemCookies) obj;
		return item.getProductId() == this.getProductId();
	}
	
	@Override
	public int hashCode() {
		return productId.intValue();
	}
	
}
