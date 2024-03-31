package com.arumugamakash.librarymanagement.model;

import com.arumugamakash.librarymanagement.librarysetup.LibrarySetupView;
import com.arumugamakash.librarymanagement.login.LoginModel;

public class User {
	private String userName;
	private static int startId = 1;
	private int userId;
	private long phoneNumber;
	private String address;
	private String emailId;
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserId() {
		this.userId = startId++;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
