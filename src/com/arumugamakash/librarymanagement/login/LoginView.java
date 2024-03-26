package com.arumugamakash.librarymanagement.login;

import java.util.Scanner;

import com.arumugamakash.librarymanagement.LibraryManagement;
import com.arumugamakash.librarymanagement.librarysetup.LibrarySetupView;
import com.arumugamakash.librarymanagement.model.Credentials;

public class LoginView {
	Scanner sc = new Scanner(System.in);
	private LoginModel loginModel;
	private LibrarySetupView librarySetupView;

	// constructor
	public LoginView() {
		loginModel = new LoginModel(this);
		librarySetupView = new LibrarySetupView();
	}

	// Home page
	public void start() {
		System.out.println("--------------" + LibraryManagement.getInstance().getAppName()
				+ " ---------------- \n\t\t  version " + "(" + LibraryManagement.getInstance().getAppVersion() + ")");
		try {
			while (true) {
				System.out.println("\nWelcome to Home page\n");
				System.out.println("\n1.SignIn   \n2.Exit\n");
				System.out.println("Enter Your choice");
				int choice = sc.nextInt();
				sc.nextLine();
				switch (choice) {
				case 1:
					loginModel.init();
					break;
				case 2:
					System.out.println("\t......Thank you for Using this Application......");
					System.exit(0);
					return;
				}
			}
		} catch (Exception e) {
			System.out.println("Input MissMatch..tryAgain");
			start();
		}

	}

	// set credentials
	public void initiateSetup() {
		Credentials credentials = new Credentials();
		credentials.setUserName("Akash");
		credentials.setPassword("admin");
		loginModel.createCredentials(credentials);
	}

	// login start UserCredentials
	public void startLogin() {
		System.out.println("Enter the UserName");
		String userName = sc.nextLine();
		System.out.println("Enter the Password");
		String password = sc.nextLine();
		loginModel.validateLogin(userName, password);
	}

	// wrong userName and password
	public void showAlert(String alertText) {
		System.out.println(alertText);
	}

	// login successful and start the librarySetUp
	public void loginSuccess() {
		System.out.println("\n--------------Admin Login successful--------------\n");
		librarySetupView.init();
	}

	// success message
	public void userLoginSuccess(String userName) {
		System.out.println("\n......User Login Successful.....\n");
		librarySetupView.userSetUpcomplete(userName);
	}
}
