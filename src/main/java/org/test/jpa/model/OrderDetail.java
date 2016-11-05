package org.test.jpa.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_list_detail")
public class OrderDetail implements Serializable {

	
	/**
	 * 
	*/
	private static final long serialVersionUID = 6651289223443434434L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Product product;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Order order;
	
	private long price = 0;
	private long discount = 0;
	private int quantity = 0;
	private long total = 0;
	
	public OrderDetail() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public long getDiscount() {
		return discount;
	}

	public void setDiscount(long discount) {
		this.discount = discount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
		
}
