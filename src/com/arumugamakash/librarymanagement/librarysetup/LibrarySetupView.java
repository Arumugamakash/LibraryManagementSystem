package com.arumugamakash.librarymanagement.librarysetup;

import java.util.Scanner;

import com.arumugamakash.librarymanagement.LibraryManagement;
import com.arumugamakash.librarymanagement.bookmanagementsystem.BookSetupView;
import com.arumugamakash.librarymanagement.login.LoginView;
import com.arumugamakash.librarymanagement.manageuser.ManageUsersView;
import com.arumugamakash.librarymanagement.model.Library;
import com.arumugamakash.librarymanagement.model.User;

public class LibrarySetupView {
	private LibrarySetupModel librarySetupModel;
	Scanner sc = new Scanner(System.in);

	// constructor
	public LibrarySetupView() {
		librarySetupModel = new LibrarySetupModel(this);
	}

	// start librarySetUp
	public void init() {
		librarySetupModel.startSetup();
	}

	// initalizeLibrary
	public void setupInitialize() {
		try {
			System.out.println("Enter the Library Name");
			String libraryName = sc.nextLine();
			System.out.println("Enter the Library Email");
			String libraryEmail = sc.nextLine();
			System.out.println("Enter the Library PhoneNumber");
			long libraryPhonenumber = sc.nextLong();
			sc.nextLine();
			System.out.println("Enter the Library Address");
			String libraryAddress = sc.nextLine();
			librarySetupModel.setLibrary(libraryName, libraryEmail, libraryPhonenumber, libraryAddress);
		} catch (Exception ez) {
			System.out.println("Input MissMatch");
			sc.nextLine();
			setupInitialize();
		}
	}

	// ----------SHOW ALL FEATURES---------//
	public void setupComplete(Library library) {
		System.out.println("---------------Library SetUp Completed--------------\n");
		System.out.println("\nCurrent Library Name - " + library.getLibraryName());
		boolean b = true;
		while (b) {
			System.out.println(
					"\n\t 1.Manage Book\n\t 2.Manage User\n\t 3.AssignBook\n\t 4.Show Assign Book \n\t 5.logout\n\t 6.Exit Application \n\n Enter your Choice:");
			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				new BookSetupView().startManageBook();
				break;
			case 2:
				new ManageUsersView().initManageUser();
				break;
			case 3:
				new BookSetupView().assignBookForAdmin();
				break;
			case 4:
				new BookSetupView().showAssignBookToUser();
				break;
			case 5:
				System.out.println("\n-- You are logged out successfully -- \n\n");
				new LoginView().start();
				return;
			case 6:
				System.out.println("\n-------------- Thanks for using " + LibraryManagement.getInstance().getAppName()
						+ " --------------");
				b = false;
				return;
			default:
				System.out.println("\nPlease Enter valid choice\n");
			}
		}
	}

	// login successful for user
	public void userSetUpcomplete(String userName) {
		System.out.println("\nUser Login Successful...\n");
		while (true) {
			System.out.println(
					"\n1.Search Book   2.view Book   3.AssignBook    4.Logout    5.Exit\n\nEnter Your Choice\n");
			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				new BookSetupView().initSearch();
				break;
			case 2:
				new BookSetupView().initView();
				break;
			case 3:
				new BookSetupView().assignBookForUser(userName);
				break;
//			case 4:
//				new BookSetupView().returnBook(userName);
			case 4:
				System.out.println("\n-- You are logged out successfully -- \n\n");
				return;
			case 5:
				System.out.println("\n-------------- Thanks for using " + LibraryManagement.getInstance().getAppName()
						+ " --------------");
				return;
			default:
				System.out.println("\nPlease Enter valid choice\n");
			}

		}
	}
}
