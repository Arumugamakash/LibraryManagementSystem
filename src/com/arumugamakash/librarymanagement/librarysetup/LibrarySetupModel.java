package com.arumugamakash.librarymanagement.librarysetup;

import java.util.Scanner;

import com.arumugamakash.librarymanagement.datalayer.LibraryDatabase;
import com.arumugamakash.librarymanagement.model.Library;

public class LibrarySetupModel {
	Scanner sc = new Scanner(System.in);
	private LibrarySetupView librarySetupView;
	
//	LibrarySetupModel librarySetupModel=new LibrarySetupModel(librarySetupView);
	private Library library;
	//constructors
	public LibrarySetupModel(LibrarySetupView librarySetupView) {
		this.librarySetupView = librarySetupView;
		library = LibraryDatabase.getInstance().getLibrary();
	}
	//library check
	public void startSetup() {
		if (library == null) {
			librarySetupView.setupInitialize();
		} else {
			librarySetupView.setupComplete(library);
		}
	}
	//setLibrary
	public void setLibrary(String libraryName, String libraryEmail, long libraryPhonenumber,
			String libraryAddress) {
		Library library = new Library();
		library.setLibraryName(libraryName);
		library.setEmail(libraryEmail);
		library.setPhoneNumber(libraryPhonenumber);
		library.setAddress(libraryAddress);
		createLibrary(library);
	}
	//CreateLibrary
	public void createLibrary(Library library) {
		if (validateLibraryName(library.getLibraryName()) && validateEmail(library.getEmail())
				&& validatePhoneNumber(library.getPhoneNumber())) {
			LibraryDatabase.getInstance().insertLibrary(library);
			librarySetupView.setupComplete(library);
		} else {
			System.out.println("Invalid LibraryDetails");
			librarySetupView.setupInitialize();
		}
	}

	// verified libraryName
	private boolean validateLibraryName(String name) {
		if (name.length() > 3 && name.length() <= 50) {
			return true;
		}
		return false;
	}

	// verified library phonenumber
	private boolean validatePhoneNumber(long phoneNumber) {
		String phoneNo = "" + phoneNumber;
		String regex = "\\d{10}";
		return phoneNo.matches(regex);
	}

	// verified libraryemail
	private boolean validateEmail(String email) {
		String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-z0-9.-]+$";
		return email.matches(regex);
	}
	public void writeAll() {
		LibraryDatabase.getInstance().writeAll();
		
	}

}