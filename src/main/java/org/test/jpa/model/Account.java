package org.test.jpa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "accounts")
public class Account implements Serializable {

	/**
	 * 
	*/
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue
	@Id
	private Long id;
	@Column(unique = true)
	private String email;
	@JsonIgnore
	private String password;
	private String name;
	private String phone;
	private String address;
	private String ROLE_ACCOUNT = "user";
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogin;
	
	public Account() {
		
	}
	
	public Account(String email, String password, String name, String phone){
		this.email = email;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.createdAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getROLE_ACCOUNT() {
		return ROLE_ACCOUNT;
	}

	public void setROLE_ACCOUNT(String rOLE_ACCOUNT) {
		ROLE_ACCOUNT = rOLE_ACCOUNT;
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

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}					
	
}
