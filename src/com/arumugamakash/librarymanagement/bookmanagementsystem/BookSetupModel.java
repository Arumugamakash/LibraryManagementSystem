package com.arumugamakash.librarymanagement.bookmanagementsystem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.arumugamakash.librarymanagement.datalayer.LibraryDatabase;
import com.arumugamakash.librarymanagement.manageuser.ManageUsersView;
import com.arumugamakash.librarymanagement.model.Book;
import com.arumugamakash.librarymanagement.model.Credentials;
import com.arumugamakash.librarymanagement.model.User;

public class BookSetupModel {
	Scanner sc = new Scanner(System.in);
//	private Credentials credentials;
	private BookSetupView bookSetupView;
	private List<Book> bookList;
	private ManageUsersView manageUsersView;

	public BookSetupModel(BookSetupView bookSetupView) {
		this.bookSetupView = bookSetupView;
		manageUsersView = new ManageUsersView();
	}

	public void addNewBook(Book book) {// addedBooks
		if (LibraryDatabase.getInstance().insertBook(book)) {
			bookSetupView.onBookAdded(book);
		} else {
			bookSetupView.onBookExist(book);
		}
	}

	public void userSearchBook(String bookName) {// u
		bookList = LibraryDatabase.getInstance().searchBook(bookName);
		if (bookList.size() == 0) {
			bookSetupView.showSearchAlert("No book exist");
		} else {
			bookSetupView.showSearchBook(bookList);
		}
	}

	public boolean searchBook(String bookName) {// u
		List<Book> bookList = LibraryDatabase.getInstance().showBooks();
		for (Book books : bookList) {
			if (books.getBookName().equals(bookName)) {
				return true;
			}
		}
		return false;
	}

	public void updateBookEdition(String updatebook, int edition) {// u
		List<Book> bookList = LibraryDatabase.getInstance().showBooks();
		for (Book book : bookList) {
			if (book.getBookName().equals(updatebook)) {
				book.setEdition(edition);
				bookSetupView.successMessage("Book Edition Updated Successfully");
				break;
			}
		}
	}
	public void updateBookCount(String updatebook, int count) {// u
		List<Book> bookList = LibraryDatabase.getInstance().showBooks();
		for (Book book : bookList) {
			if (book.getBookName().equals(updatebook)) {
				book.setAvailableCount(count);
				bookSetupView.successMessage("Book Count Updated Successfully");
				break;
			}
		}

	}

	public void updateBookVolume(String updatebook, int volume) {// u

		List<Book> bookList = LibraryDatabase.getInstance().showBooks();
		for (Book book : bookList) {
			if (book.getBookName().equals(updatebook)) {
				book.setVolume(volume);
				bookSetupView.successMessage("Book Volume Updated Successfully");
				break;
			}
		}
	}

	public void userDeleteBook(String deleteBook) {// u
		List<Book> bookList = LibraryDatabase.getInstance().showBooks();
		for (Book books : bookList) {
			if (books.getBookName().equals(deleteBook)) {
				bookList.remove(books);
				bookSetupView.successMessage("Book Deleted SuccessFully");
				break;
			}
		}

	}

	public boolean checkUser(String userName, String bookName) {
		List<User> userList = LibraryDatabase.getInstance().viewUser();
		for (User user : userList) {
			if (user.getUserName().equals(userName)) {
				return checkBookName(bookName);
			}
		}
		return false;

	}

	/// checkBookName
	public boolean checkBookName(String bookName) {
		List<Book> bookList = LibraryDatabase.getInstance().showBooks();
		for (Book book : bookList) {
			if (book.getBookName().equals(bookName)) {
				return true;
			}
		}
		return false;
	}

	public Book checkTakenBook(String bookName) {
		List<Book> bookList = LibraryDatabase.getInstance().showBooks();
		for (Book book : bookList) {
			if (book.getBookName().equals(bookName)) {
				return book;
			}
		}
		return null;
	}

	public void setAssignBook(String userName, String bookName) {
		Map<String, String> assign = new HashMap();
		if (checkUser(userName, bookName)) {
			Book takenBook = checkTakenBook(bookName);
			takenBook.setAvailableCount(takenBook.getAvailableCount() - 1);
			assign.put(userName, bookName);
			System.out.println("\t-------Assighning the Book from User successful----------");
			LibraryDatabase.getInstance().assignBookToUser(assign);
		} else {
			System.out.println("Book Or User is Invalid...try Again");
		}
	}

	public void showAssignBookToUser() {
		if (!LibraryDatabase.getInstance().showIssuedBooks().isEmpty()) {
			List<Map<String, String>> issuedBooks = LibraryDatabase.getInstance().showIssuedBooks();
			for (Map<String, String> map : issuedBooks) {
				System.out.println(map);
			}
		} else
			bookSetupView.alertBook("No one can assignBook");

	}

	public void viewAllBooks() {
		System.out.println("\nList of Books\n");
		int i = 1;

		List<Book> books = LibraryDatabase.getInstance().showBooks();
		if (books.size() != 0) {
			for (Book book : books) {
				System.out.println(i++ + "." + "Book Name:-" + book.getBookName());
			}
		} else {
			bookSetupView.alertBook("NO Book Exist");
		}

	}

}
