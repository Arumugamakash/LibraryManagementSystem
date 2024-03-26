package com.arumugamakash.librarymanagement.login;

import java.util.List;

import com.arumugamakash.librarymanagement.datalayer.LibraryDatabase;
import com.arumugamakash.librarymanagement.model.Credentials;
import com.arumugamakash.librarymanagement.model.User;

public class LoginModel {
	private LoginView loginView;
	private Credentials credentials;

	// constructor
	public LoginModel(LoginView loginView) {
		this.loginView = loginView;
	}

	// start and initiate the credentials
	public void init() {
		if (credentials == null) {
			loginView.initiateSetup();
		} else {
			loginView.startLogin();
		}

	}

	// create Credentials
	public void createCredentials(Credentials credentials) {
		LibraryDatabase.getInstance().insertCredentials(credentials);
		loginView.startLogin();
	}

	// validate Login
	public void validateLogin(String userName, String password) {
		if (isValidUserName(userName) && isValidPassword(password)) {
			loginView.loginSuccess();
		} else {
			if (!LibraryDatabase.getInstance().viewUserCredentials().isEmpty()) {
				List<Credentials> crendentialsList = LibraryDatabase.getInstance().viewUserCredentials();
				for (Credentials credentials : crendentialsList) {
					if (credentials.getUserName().equals(userName) && credentials.getPassword().equals(password)) {
						loginView.userLoginSuccess(credentials.getUserName());
					} else {
						loginView.showAlert("\t\tInvalid Username or Password....");
						loginView.startLogin();
					}
				}
			} else {
				loginView.showAlert("\t\tInvalid Username or Password");
				loginView.startLogin();
			}
		}
	}

	// checkUserName
	private boolean isValidUserName(String userName) {
		return userName.equals(LibraryDatabase.getInstance().getCredentials().getUserName());
	}

	// CheckPassword
	private boolean isValidPassword(String password) {
		return password.equals(LibraryDatabase.getInstance().getCredentials().getPassword());
	}

}
