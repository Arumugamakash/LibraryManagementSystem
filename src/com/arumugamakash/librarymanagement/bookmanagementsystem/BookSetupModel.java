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

	public void userSearchBook(String bookName) {
		bookList = LibraryDatabase.getInstance().searchBook(bookName);
		if (bookList.size() == 0) {
			bookSetupView.showSearchAlert("No book exist");
		} else {
			bookSetupView.showSearchBook(bookList);
		}
	}

	public boolean userUpdateSearch(String updatebook) {
		if (LibraryDatabase.getInstance().updateSearchBook(updatebook)) {
			return true;
		}
		return false;

	}

	public void updateBookEdition(String updatebook, int edition) {
		if (LibraryDatabase.getInstance().UpdateBookEdition(updatebook, edition)) {
			bookSetupView.updateSuccess("Book Edition Updated Successfully");
		} else {
			bookSetupView.alertBook();
		}
	}

	public void updateBookCount(String updatebook, int count) {
		if (LibraryDatabase.getInstance().UpdateBookCount(updatebook, count)) {
			bookSetupView.updateSuccess("Book Count Updated Successfully");
		} else {
			bookSetupView.alertBook();
		}

	}

	public void updateBookVolume(String updatebook, int volume) {
		if (LibraryDatabase.getInstance().UpdateBookVolume(updatebook, volume)) {
			bookSetupView.updateSuccess("Book Volume Updated Successfully");
		} else {
			bookSetupView.alertBook();
		}

	}

	public void userDeleteBook(String deleteBook) {
		if (LibraryDatabase.getInstance().userDeleteBook(deleteBook)) {
			bookSetupView.deleteSuccess("Book Deleted SuccessFully");
		} else {
			bookSetupView.alertBook();
		}

	}

	public boolean checkUser(String userName, String bookName) {
		List<User> userList = LibraryDatabase.getInstance().viewuser();
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

	public Book checkBookNameCount(String bookName) {
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
			Book takenBook = checkBookNameCount(bookName);
			assign.put(userName, bookName);
			takenBook.setAvailableCount(takenBook.getAvailableCount() - 1);
			System.out.println("\t-------Assighning the Book from User succeful----------");
			LibraryDatabase.getInstance().assignBookToUser(assign);
		} else {
			System.out.println("Book Or User is Invalid...try Again");
		}
	}

}
