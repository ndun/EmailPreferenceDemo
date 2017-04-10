package com.nfd.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "email_preferences")
public class EmailPreference implements Serializable {

	private static final long serialVersionUID = -3465813074586302847L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;

	@Column
	private String emailAddress;

	@Column
	private int optin;
	
	public EmailPreference() {
		super();
	}
	
	public EmailPreference(String emailAddress, int optin) {
		this.emailAddress = emailAddress;
		this.optin = optin;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String email) {
		this.emailAddress = email;
	}

	public int getOptin() {
		return optin;
	}

	public void setOptin(int optin) {
		this.optin = optin;
	}

}