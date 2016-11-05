package org.test.jpa.model.helper;

import java.util.Set;

public class Cart {

	private Set<CartItem> items;
	private int size 		= 0;
	private Long total 		= 0l;
	
	public Cart() {
		
	}

	public Set<CartItem> getItems() {
		return items;
	}

	public void setItems(Set<CartItem> items) {
		this.items = items;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
	
}
