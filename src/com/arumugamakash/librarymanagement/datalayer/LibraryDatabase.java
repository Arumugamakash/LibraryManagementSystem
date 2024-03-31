package com.arumugamakash.librarymanagement.datalayer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.arumugamakash.librarymanagement.model.Book;
import com.arumugamakash.librarymanagement.model.Credentials;
import com.arumugamakash.librarymanagement.model.Library;
import com.arumugamakash.librarymanagement.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LibraryDatabase {
	private String libraryFile = "D:\\Zoho\\LibraryManagementSystem\\src\\com\\arumugamakash\\librarymanagement\\JsonFile\\library.json";
	private String bookFile = "D:\\Zoho\\LibraryManagementSystem\\src\\com\\arumugamakash\\librarymanagement\\JsonFile\\book.json";
	private String userFile = "D:\\Zoho\\LibraryManagementSystem\\src\\com\\arumugamakash\\librarymanagement\\JsonFile\\user.json";
	private String userCredentialsFile = "D:\\Zoho\\LibraryManagementSystem\\src\\com\\arumugamakash\\librarymanagement\\JsonFile\\credentialUser.json";
	private String assignBookFile = "D:\\Zoho\\LibraryManagementSystem\\src\\com\\arumugamakash\\librarymanagement\\JsonFile\\assignBook.json";
	private Credentials credentials;
//	private Credentials adminCredentialsList;
	private Library library;
	private List<Book> bookList;
	private List<User> userList;
	private List<Map<String, String>> issuedBook;
	private List<Credentials> credentialsList;

	ObjectMapper obj = new ObjectMapper();

	// constructors
	private LibraryDatabase() {
		bookList = new ArrayList<Book>();
		userList = new ArrayList<User>();
		issuedBook = new ArrayList<Map<String, String>>();
		credentialsList = new ArrayList<Credentials>();
	}

	private static LibraryDatabase libraryDatabase;

	// singleton pattern//object
	public static LibraryDatabase getInstance() {
		if (libraryDatabase == null) {
			libraryDatabase = new LibraryDatabase();
		}
		return libraryDatabase;
	}

	// insertCredentials
	public void insertCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	// getCredentials
	public Credentials getCredentials() {
		return credentials;
	}

	// addLibrary
	public void insertLibrary(Library library2) {
		this.library = library2;
		try {
			File file = new File(libraryFile);
			if (!file.exists()) {
				file.createNewFile();
			}
			obj.writeValue(file, library2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// getLibrary
	public Library getLibrary() {
		try {
			return library = obj.readValue(new File(libraryFile), new TypeReference<Library>() {
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/// usercredentialsList
	public boolean insertUserCredentials(Credentials credentials) {
		if (!viewUserCredentials().isEmpty()) {
			List<Credentials> credentialsList = viewUserCredentials();
			for (Credentials credential : credentialsList) {
				if (credential.getUserName().equals(credentials.getUserName())) {
					return false;
				}
			}
			credentialsList.add(credentials);
			return true;
		} else {
			return credentialsList.add(credentials);
		}

	}

	public void writeCredentialsFile() {
		try {
			File file = new File(userCredentialsFile);
			if (!file.exists()) {
				file.createNewFile();
			}
			obj.writeValue(file, credentialsList);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<Credentials> viewUserCredentials() {
		return credentialsList;
	}

	public void readCredentialsFile() {
		File file = new File(userCredentialsFile);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (file.length() > 0) {
			try {
				credentialsList = obj.readValue(file, new TypeReference<List<Credentials>>() {
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public Credentials removeCredentials(String userName) {

		try {
			List<Credentials> credentialsList = viewUserCredentials();
			for (Credentials credentials : credentialsList) {
				if (credentials.getUserName().equals(userName)) {
					FileWriter file = new FileWriter(userCredentialsFile, false);
					credentialsList.remove(credentials);
				}
				// insertUserCredentials(credentials);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return credentials;
	}

	public boolean insertBook(Book book) {
		if (!showBooks().isEmpty()) {
			List<Book> bookList = showBooks();
			for (Book books : bookList) {
				if (books.getBookName().equals(book.getBookName())) {
					return false;
				}
			}
			bookList.add(book);
			return true;
		} else
			return bookList.add(book);
	}

	public void writeBookFile() {
		try {
			File file = new File(bookFile);
			if (!file.exists()) {
				file.createNewFile();
			}
			obj.writeValue(file, bookList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Book> showBooks() {
		return bookList;
	}

	public void readBookFile() {
		File file = new File(bookFile);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (file.length() > 0) {
			try {
				bookList = obj.readValue(file, new TypeReference<List<Book>>() {
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<Book> searchBook(String bookName) {
		List<Book> searchResult = new ArrayList<Book>();
		for (Book book : bookList) {
			String name = book.getBookName();
			if (name.charAt(0) == bookName.charAt(0)) {
				searchResult.add(book);
			}
		}
		return searchResult;
	}

	public boolean insertUser(User user) {
		if (!viewUser().isEmpty()) {
			List<User> userList = viewUser();
			for (User users : userList) {
				if (user.getEmailId().equals(users.getEmailId())) {
					return false;
				}
			}
			userList.add(user);
			return true;
		} else {
			return userList.add(user);
		}

	}

	public void writeUserFile() {
		try {
			File file = new File(userFile);
			if (!file.exists()) {
				file.createNewFile();
			}
			obj.writeValue(file, userList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<User> viewUser() {
		return userList;
	}

	public void readUserFile() {
		File file = new File(userFile);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (file.length() > 0) {
			try {
				userList = obj.readValue(file, new TypeReference<List<User>>() {
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void assignBookToUser(Map<String, String> assign) {
		issuedBook.add(assign);
	}

	// WRITE MAPPING DETAILS
	private void writeAssignBookFile() {
		try {
			File file = new File(assignBookFile);
			if (!file.exists()) {
				file.createNewFile();
			}
			obj.writeValue(file, issuedBook);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Map<String, String>> showIssuedBooks() {
		return issuedBook;
	}

	private void readAssignBookFile() {
		File file = new File(assignBookFile);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (file.length() > 0) {
			try {
				issuedBook = obj.readValue(file, new TypeReference<ArrayList<Map<String, String>>>() {
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// WRITE ALL JSON FILE
	public void writeAll() {
		writeBookFile();
		writeUserFile();
		writeAssignBookFile();
		writeCredentialsFile();
	}

	// READ ALL JSON FILE
	public void readAllDatas() {
		readBookFile();
		readUserFile();
		readAssignBookFile();
		readCredentialsFile();
	}
}
