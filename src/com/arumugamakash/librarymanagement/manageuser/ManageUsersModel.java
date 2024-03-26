package com.arumugamakash.librarymanagement.manageuser;

import java.util.List;

import com.arumugamakash.librarymanagement.bookmanagementsystem.BookSetupView;
import com.arumugamakash.librarymanagement.datalayer.LibraryDatabase;
import com.arumugamakash.librarymanagement.model.Book;
import com.arumugamakash.librarymanagement.model.Credentials;
import com.arumugamakash.librarymanagement.model.User;

public class ManageUsersModel {
	private ManageUsersView manageUsersView;

//	private Credentials credentials;
	ManageUsersModel(ManageUsersView manageUsersView) {
		this.manageUsersView = manageUsersView;
	}

	public void addNewUser(String name, long phNo, String mail, String password, String address) {
		User user = new User();
		user.setUserName(name);
		user.setPhoneNumber(phNo);
		user.setEmailId(mail);
		user.setPassword(password);
		user.setAddress(address);
		Credentials credentials = new Credentials();
		credentials.setUserName(name);
		credentials.setPassword(password);
		LibraryDatabase.getInstance().insertUserCredentials(credentials);

		if (LibraryDatabase.getInstance().insertUser(user)) {
			manageUsersView.onUserAdded(user);
		} else {
			manageUsersView.onUserExist(user);
		}
	}

	public void modifyUserMail(String name, String userEmail) {
		if (LibraryDatabase.getInstance().updateUserEmail(name, userEmail)) {
			manageUsersView.updateSuccess("UserMail Updated Successfully");
		} else {
			manageUsersView.alert("Invalid User");
		}
	}

	public void modifyUserMobile(String name, long userPhoneNo) {
		if (LibraryDatabase.getInstance().updateUserPhoneNumber(name, userPhoneNo)) {
			manageUsersView.updateSuccess("UserMobileNumber Updated Successfully");
		} else {
			manageUsersView.alert("Invalid User");
		}
	}

	public String deleteUser(String userName) {

		if (LibraryDatabase.getInstance().deleteUser(userName)) {
			return "User Deleted SuccessFully";
		}
		return "User not found";
	}

	public User searchUser(String userName) {
		List<User> users = LibraryDatabase.getInstance().viewuser();
		for (User user : users) {
			if (userName.equals(user.getUserName())) {
				return user;
			}
		}
		return null;
	}

	public List<User> viewUser() {
		List<User> users = LibraryDatabase.getInstance().viewuser();
		return users;
	}
}