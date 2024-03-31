package com.arumugamakash.librarymanagement;

import com.arumugamakash.librarymanagement.login.LoginView;

public class LibraryManagement {
	private static LibraryManagement libraryManagement;
	private String appName = "Library Management System";
	private String appVersion = "0.1.0";

	public String getAppName() {
		return appName;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public static LibraryManagement getInstance() {
		if (libraryManagement == null)
			libraryManagement = new LibraryManagement();
		return libraryManagement;
	}

	private void create() {
		LoginView loginView = new LoginView();
		loginView.start();
	}

	public static void main(String[] args) {
		libraryManagement.getInstance().create();
	}

}
