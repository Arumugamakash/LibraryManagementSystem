package com.arumugamakash.librarymanagement.model;

public class Library {
	private String libraryName;
	private static int libraryId=1;
	private long phoneNumber;
	private String address;
	private String email;

	public String getLibraryName() {
		return libraryName;
	}

	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Library [libraryName=" + libraryName + ", libraryId=" + libraryId + ", phoneNumber=" + phoneNumber
				+ ", address=" + address + ", email=" + email + "]";
	}

}
