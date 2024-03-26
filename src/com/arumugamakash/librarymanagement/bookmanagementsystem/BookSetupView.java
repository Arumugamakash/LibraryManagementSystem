package com.arumugamakash.librarymanagement.bookmanagementsystem;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.arumugamakash.librarymanagement.datalayer.LibraryDatabase;
import com.arumugamakash.librarymanagement.model.Book;

public class BookSetupView {
	Scanner sc = new Scanner(System.in);
	private BookSetupModel bookSetupModel;

	public BookSetupView() {
		bookSetupModel = new BookSetupModel(this);
	}

	public void startManageBook() {
		try {
			while (true) {
				System.out.println(
						"\n 1.Add Book   2.Search Book   3.Update Book   4.view Book   5.Remove Book    6.Exit\n\nEnter Your Choice");
				int choice = sc.nextInt();
				sc.nextLine();
				switch (choice) {
				case 1:
					initAddBook();
					break;
				case 2:
					initSearch();
					break;
				case 3:
					initUpdate();
					break;
				case 4:
					initView();
					break;
				case 5:
					initDelete();
					break;
				case 6:
					return;
				}
			}
		} catch (Exception e) {
			System.out.println("InputMissMatch..tryAgain");
			sc.nextLine();
			startManageBook();
		}
	}

	public void initAddBook() {

		try {
			Book book = new Book();/// book object
			System.out.println("\n\t\tEnter book details: ");
			System.out.println("\nEnter the Book Name");
			book.setBookName(sc.nextLine());
			System.out.println("\nEnter the Book Author:");
			book.setAuthorName(sc.nextLine());
			System.out.println("Enter the Publication year");
			book.setPublishingYear(sc.nextInt());
			sc.nextLine();
			System.out.println("Enter the Book Edition");
			book.setEdition(sc.nextInt());
			sc.nextLine();
			System.out.println("Enter the Book Genre");
			book.setGenre(sc.nextLine());
			System.out.println("Enter the Available count of the Book");
			book.setAvailableCount(sc.nextInt());
			System.out.println("Enter the Book volume");
			book.setVolume(sc.nextInt());
			sc.nextLine();
			bookSetupModel.addNewBook(book);
		} catch (Exception e) {
			System.out.println("Input Miss Match try again.....");
			sc.nextLine();
			initAddBook();
		}

	}

	public void onBookAdded(Book book) {
		System.out.println("\n------- Book '" + book.getBookName() + "' added successfully ------- \n");
		checkForAddNewBook();
	}

	public void onBookExist(Book book) {
		System.out.println("\n------- Book '" + book.getBookName() + "' already exist -------\n");
		checkForAddNewBook();

	}

	private void checkForAddNewBook() {
		System.out.println("Do you want to add more books? \nType Yes/No");
		String choice = sc.nextLine();
		if (choice.equalsIgnoreCase("yes")) {
			initAddBook();
		} else if (choice.equalsIgnoreCase("no")) {
			System.out.println("\n Thanks for adding books");
		} else {
			System.out.println("\nInvalid choice, Please enter valid choice.\n");
			checkForAddNewBook();
		}
	}

	public void initSearch() {
		System.out.println("Enter the Book Name");
		String bookName = sc.nextLine();
		bookSetupModel.userSearchBook(bookName);
	}

	public void showSearchAlert(String bookAlert) {
		System.out.println(bookAlert);
		checkForNewSearchBook();
	}

	public void showSearchBook(List<Book> bookList) {
		System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s", "Book name", "Author", "Publication", "Edition",
				"Avialable Count", "Journer", "Volume");
		System.out.println();
		for (Book book : bookList) {
			System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s", book.getBookName(), book.getAuthorName(),
					book.getPublishingYear(), book.getEdition(), book.getAvailableCount(), book.getGenre(),
					book.getVolume());
			System.out.println();
		}
		checkForNewSearchBook();

	}

	private void checkForNewSearchBook() {
		System.out.println("\nDo you want to Search New more books? \nType Yes/No");
		String choice = sc.nextLine();
		if (choice.equalsIgnoreCase("yes")) {
			initSearch();
		} else if (choice.equalsIgnoreCase("no")) {
			System.out.println("\n Thanks for searching books");
		} else {
			System.out.println("\nInvalid choice, Please enter valid choice.\n");
			checkForNewSearchBook();
		}
	}

	public void initUpdate() {
		System.out.println("Enter the UpdateBook_Name");
		String updateBook = sc.nextLine();
		if (bookSetupModel.userUpdateSearch(updateBook)) {
			while (true) {
				System.out.println("What you need to Update in the Book");
				System.out.println(
						"\n 1.Update Book Edition \n 2.Update Book Count \n 3.Update Book Volume \n 4.Exit Updation Process \nEnter your Choice");
				System.out.println();
				int choice = sc.nextInt();
				sc.nextLine();
				switch (choice) {
				case 1:
					System.out.println("Enter the Updated Edition of the Book");
					int edition = sc.nextInt();
					bookSetupModel.updateBookEdition(updateBook, edition);
					break;
				case 2:
					System.out.println("Enter the Updated Count of the Book");
					int count = sc.nextInt();
					bookSetupModel.updateBookCount(updateBook, count);
					break;
				case 3:
					System.out.println("Enter the Updated Volume of the Book");
					int volume = sc.nextInt();
					bookSetupModel.updateBookVolume(updateBook, volume);
					break;
				case 4:
					System.out.println("\n------Updation Completed-----");
					// new LibrarySetupView().onSetupComplete();
					return;
				default:
					System.out.println("\n\nPlease Enter valid choice\n");
				}
			}
		} else {
			alertBook();
		}
	}

	public void alertBook() {
		System.out.println("Book Not Found");
	}

	public void updateSuccess(String string) {
		System.out.println(string);
	}

	public void initDelete() {
		System.out.println("Enter the DeleteBook_Name");
		String deleteBook = sc.nextLine();
		if (bookSetupModel.userUpdateSearch(deleteBook)) {
			bookSetupModel.userDeleteBook(deleteBook);
		} else {
			alertBook();
		}

	}

	public void deleteSuccess(String string) {
		System.out.println(string);
	}

	public void initView() {
		System.out.println("\nList of Books\n");
		int i = 1;
		List<Book> books = LibraryDatabase.getInstance().showBooks();
		for (Book book : books) {
			System.out.println(i++ + "." + "Book Name:-" + book.getBookName());
		}
	}

	public void assignBookForAdmin() {
		System.out.println("Enter the UserName");
		String userName = sc.nextLine();
		System.out.println("Enter the BookName");
		String bookName = sc.nextLine();
		bookSetupModel.setAssignBook(userName, bookName);
	}

	public void assignBookForUser(String userName) {
		System.out.println("Enter the BookName");
		String bookName = sc.nextLine();
		if (bookSetupModel.checkBookName(bookName)) {
			bookSetupModel.setAssignBook(userName, bookName);
		} else {
			alertBook();
		}

	}

	public void showAssignBookToUser() {
		List<Map<String, String>> issuedBooks = LibraryDatabase.getInstance().showIssuedBooks();
		for (Map<String, String> map : issuedBooks) {
			System.out.println(map);
		}
	}
}
