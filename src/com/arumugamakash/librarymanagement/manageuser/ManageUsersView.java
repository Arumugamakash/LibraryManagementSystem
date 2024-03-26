package com.arumugamakash.librarymanagement.manageuser;

import java.util.List;
import java.util.Scanner;

import com.arumugamakash.librarymanagement.bookmanagementsystem.BookSetupView;
import com.arumugamakash.librarymanagement.datalayer.LibraryDatabase;
import com.arumugamakash.librarymanagement.model.User;

public class ManageUsersView {
	Scanner sc = new Scanner(System.in);
	private ManageUsersModel manageUserModel;

	public ManageUsersView() {
		manageUserModel = new ManageUsersModel(this);
	}

	public void initManageUser() {
		try {
			while (true) {
				System.out.println("\n 1.Add User   2.Update User   3.Remove User   4.view User   5.Exit\n");
				System.out.println("\nEnter your choice\n");
				int choice = sc.nextInt();
				sc.nextLine();
				switch (choice) {
				case 1:
					initAddUser();
					break;
				case 2:
					updateUser();
					break;
				case 3:
					deleteUser();
					break;
				case 4:
					userView();
					break;
				case 5:
					return;
				}
			}
		} catch (Exception e) {
			System.out.println("InputMissMatch..tryAgain");
			initManageUser();
		}

	}

	public void initAddUser() {
		try {
			System.out.println("\nEnter the following user Details\n");
			System.out.println("\nEnter user name");
			String name = sc.nextLine();
			System.out.println("\nEnter the PhoneNumber");
			long phNo = sc.nextLong();
			sc.nextLine();
			if (validatePhoneNumber(phNo)) {
				System.out.println("\nEnter user Email Id:");
				String mail = sc.nextLine();
				if (validateEmail(mail)) {
					System.out.println("\nEnter the Password");
					String password = sc.nextLine();
					System.out.println("\nEnter the Address");
					String address = sc.nextLine();
					manageUserModel.addNewUser(name, phNo, mail, password, address);
				} else {
					System.out.println("User Details wrong...try Again");
					initAddUser();
				}
			} else {
				System.out.println("User Details wrong...try Again");
				initAddUser();
			}
		} catch (Exception e) {
			System.out.println("Input Miss Match..try Again");
			initAddUser();
		}
	}

	// Validate Email
	private boolean validateEmail(String email) {
		String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-z0-9.-]+$";
		return email.matches(regex);
	}

	// verified Phonenumber
	private boolean validatePhoneNumber(long phoneNumber) {
		String phoneNo = "" + phoneNumber;
		String regex = "\\d{10}";
		return phoneNo.matches(regex);
	}

	public void showLibraryName(String libraryName) {
		System.out.println("Current Library Name - " + libraryName);
	}

	public void onUserAdded(User user) {
		System.out.println("\n------- User '" + user.getUserName() + "' added successfully ------- \n");
		checkForAddNewUser();
	}

	public void onUserExist(User user) {
		System.out.println("\n------- User '" + user.getUserName() + "' Already Exist ------- \n");
		checkForAddNewUser();
	}

	private void checkForAddNewUser() {
		System.out.println("Do you want to add more users? \nType Yes/No");
		String choice = sc.nextLine();
		if (choice.equalsIgnoreCase("yes")) {
			initAddUser();
		} else if (choice.equalsIgnoreCase("no")) {
			System.out.println("\n Thanks for adding users");
		} else {
			System.out.println("\nInvalid choice, Please enter valid choice.\n");
			checkForAddNewUser();
		}
	}

	public void updateUser() {

		System.out.println("Please Enter User Name....to update the User_record: ");
		String userName = sc.nextLine();
		List<User> users = manageUserModel.viewUser();
		for (User user : users) {
			if (manageUserModel.searchUser(userName) != null) {
				while (true) {
					System.out.println("What you need to Update in the User");
					System.out
							.println("\n 1.UpdateUserMail \n 2.Update User PhoneNumber \n 3.Exit \nEnter your Choice");
					System.out.println();
					int choice = sc.nextInt();
					sc.nextLine();
					switch (choice) {
					case 1:
						System.out.println("Enter the User Email Id: ");
						String userEmail = sc.nextLine();
						manageUserModel.modifyUserMail(userName, userEmail);
						break;
					case 2:
						System.out.println("Enter the User Mobile No: ");
						long userPhoneNo = sc.nextLong();
						manageUserModel.modifyUserMobile(userName, userPhoneNo);
						break;
					case 3:
						System.out.println("\n-- Updation Completed-----");
						// new LibrarySetupView().onSetupComplete();
						return;
					default:
						System.out.println("\nPlease Enter valid choice\n");
					}

				}
			} else {
				System.out.println("Invalid User Details....");
			}
		}
	}

	public String updateSuccess(String string) {
		return string;
	}

	public void deleteUser() {
		System.out.println("Enter the Name of the User you want to delete");
		System.out.println(manageUserModel.deleteUser(sc.nextLine()));
	}

	public void userView() {
		System.out.println("\nList of Users\n");
		int i = 1;
		List<User> users = manageUserModel.viewUser();
		for (User user : users) {
			System.out.println(i++ + "." + user.getUserName());
		}
	}

	public String alert(String string) {
		return string;
	}
}
