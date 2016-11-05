package org.test.jpa.model.helper;

import java.util.List;

public class CartCookies {

	private List<CartItemCookies> items;
	private int size;
	
	public CartCookies() {
		
	}

	public List<CartItemCookies> getItems() {
		return items;
	}

	public void setItems(List<CartItemCookies> items) {
		this.items = items;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
}
