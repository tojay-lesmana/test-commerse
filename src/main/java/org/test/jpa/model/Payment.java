package org.test.jpa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "payment")
public class Payment implements Serializable {

	/**
	 * 
	*/
	private static final long serialVersionUID = 6799577416812809444L;
	
	@Id
	@GeneratedValue
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;	
	private String paymentInfo;
	private String paymentProof;
	private String paymentHolder;
	private long amount;
	private boolean valid;
	@Temporal(TemporalType.TIMESTAMP)
	private Date validityDate;
	
	public Payment() {
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
	
	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(String paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	public String getPaymentProof() {
		return paymentProof;
	}

	public void setPaymentProof(String paymentProof) {
		this.paymentProof = paymentProof;
	}

	public String getPaymentHolder() {
		return paymentHolder;
	}

	public void setPaymentHolder(String paymentHolder) {
		this.paymentHolder = paymentHolder;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public Date getValidityDate() {
		return validityDate;
	}

	public void setValidityDate(Date validityDate) {
		this.validityDate = validityDate;
	}	
}
