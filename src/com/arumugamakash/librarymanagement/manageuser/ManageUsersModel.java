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

	public void addNewUser(String name, long phNo, String mail, String password, String address) {// u
		User user = new User();
		user.setUserName(name);
		user.setPhoneNumber(phNo);
		user.setEmailId(mail);
		user.setPassword(password);
		user.setAddress(address);
		if (LibraryDatabase.getInstance().insertUser(user)) {
			Credentials credentials = new Credentials();
			credentials.setUserName(name);
			credentials.setPassword(password);
			LibraryDatabase.getInstance().insertUserCredentials(credentials);
			manageUsersView.onUserAdded(user);
		} else {
			manageUsersView.onUserExist(user);
		}
	}

	public void modifyUserMail(String name, String userEmail) {// u
		List<User> userList = LibraryDatabase.getInstance().viewUser();
		for (User users : userList) {
			if (users.getUserName().equals(name)) {
				users.setEmailId(userEmail);
				manageUsersView.alert("UserMail Updated Successfully");
				break;
			}
		}
	}

	public void modifyUserMobile(String name, long userPhoneNo) {// u
		List<User> userList = LibraryDatabase.getInstance().viewUser();
		for (User users : userList) {
			if (users.getUserName().equals(name)) {
				users.setPhoneNumber(userPhoneNo);
				manageUsersView.alert("UserPhoneNumber Updated Successfully");
				break;
			}
		}
	}

	public String deleteUser(String userName) {// u
		List<User> userList = LibraryDatabase.getInstance().viewUser();
		for (User users : userList) {
			if (users.getUserName().equals(userName)) {
				userList.remove(users);
				Credentials c= LibraryDatabase.getInstance().removeCredentials(userName);
				LibraryDatabase.getInstance().insertCredentials(c);
				return "User Deleted SuccessFully";
			}
		}
		return "User not found";
	}

	public User searchUser(String userName) {
		List<User> users = LibraryDatabase.getInstance().viewUser();
		for (User user : users) {
			if (userName.equals(user.getUserName())) {
				return user;
			}
		}
		return null;
	}

	public List<User> viewUser() {
		System.out.println("\nList of Users\n");
		int i = 1;
		List<User> users = LibraryDatabase.getInstance().viewUser();
		if (users.size() != 0) {
			for (User user : users) {
				System.out.println(i++ + "." + user.getUserName());
			}
		} else {
			manageUsersView.alert("NO Book Exist");
		}
		return users;
	}
}