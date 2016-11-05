package org.test.jpa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "order_list")
public class Order implements Serializable {

	/**
	 * 
	*/
	private static final long serialVersionUID = -1407376283832042119L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique=true)
	private String invoice;		
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Account account;
	
	@OneToMany(fetch=FetchType.EAGER, cascade= CascadeType.ALL)
	@JsonManagedReference
	private List<OrderDetail> details;		
	
	@OneToOne(fetch=FetchType.EAGER, cascade= CascadeType.ALL)
	@JoinColumn(name = "payment_id")
	private Payment payment;	
	
	@OneToOne(fetch=FetchType.EAGER, cascade= CascadeType.ALL)
	private Shipping shippingTo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdate;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public List<OrderDetail> getDetails() {
		return details;
	}

	public void setDetails(List<OrderDetail> details) {
		this.details = details;
	}
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Shipping getShippingTo() {
		return shippingTo;
	}

	public void setShippingTo(Shipping shippingTo) {
		this.shippingTo = shippingTo;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}		
		
}
