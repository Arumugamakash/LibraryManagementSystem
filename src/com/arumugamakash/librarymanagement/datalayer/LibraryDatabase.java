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
//			try {
//				File file = new File(
//						"D:\\Zoho\\LibraryManagementSystem\\src\\com\\arumugamakash\\librarymanagement\\JsonFile\\credentialAdmin.json");
//				if (!file.exists()) {
//					file.createNewFile();
//					obj.writeValue(file, new ArrayList<Credentials>());
//				}
//				Credentials credential = obj.readValue(file, new TypeReference<Credentials>() {
//				});
//				credential = credentials;
//				obj.writeValue(file, credential);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}

	}

	// getCredentials
	public Credentials getCredentials() {
//			try {
//				credentials = obj.readValue(new File(
//						"D:\\Zoho\\LibraryManagementSystem\\src\\com\\arumugamakash\\librarymanagement\\JsonFile\\credentialAdmin.json"),
//						new TypeReference<Credentials>() {
//						});
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		return credentials;
	}

	// getLibrary
	public Library getLibrary() {
		return library;
	}

	// addLibrary
	public void insertLibrary(Library library2) {
		library = library2;
	}

	/// usercredentialsList
	public boolean insertUserCredentials(Credentials credentials) {
		credentialsList.add(credentials);
		try {
			File file = new File(userCredentialsFile);
			if (!file.exists()) {
				file.createNewFile();
				obj.writeValue(file, new ArrayList<>());
			}
			List<Credentials> existingUser = obj.readValue(file, new TypeReference<List<Credentials>>() {
			});
			existingUser.add(credentials);
			obj.writeValue(file, existingUser);

			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Credentials> viewUserCredentials() {
		try {
			credentialsList = obj.readValue(new File(userCredentialsFile), new TypeReference<List<Credentials>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return credentialsList;
	}

	public boolean insertBook(Book book) {
		try {
			File file = new File(bookFile);
			if (!file.exists()) {
				System.out.println("file");
				file.createNewFile();
			}
			if (file.length() > 0) {
				// verify the length of the file
				bookList = obj.readValue(new File(bookFile), new TypeReference<List<Book>>() {
				});
				for (Book books : bookList) {
					if (book.getBookName().equals(books.getBookName()))
						return false;
				}
				bookList.add(book);
				obj.writeValue(file, bookList);
				return true;
			} else {
				bookList.add(book);
				obj.writeValue(file, bookList);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("dfgh");
		}
		return false;

	}

	public List<Book> showBooks() {
		try {
			return bookList = obj.readValue(new File(bookFile), new TypeReference<List<Book>>() {
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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

	public boolean updateSearchBook(String bookName) {
		for (Book book : bookList) {
			if (book.getBookName().contains(bookName))
				return true;
		}
		return false;
	}

	public boolean UpdateBookEdition(String updatebook, int edition) {
		try {
			bookList = obj.readValue(new File(bookFile), new TypeReference<List<Book>>() {
			});
			for (Book book : bookList) {
				if (book.getBookName().equals(updatebook)) {
					FileWriter file = new FileWriter(bookFile, false);
					bookList.remove(book);
					book.setEdition(edition);
					insertBook(book);
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean UpdateBookCount(String updatebook, int count) {
		try {
			bookList = obj.readValue(new File(bookFile), new TypeReference<List<Book>>() {
			});
			for (Book book : bookList) {
				if (book.getBookName().equals(updatebook)) {
					FileWriter file = new FileWriter(bookFile, false);
					bookList.remove(book);
					book.setAvailableCount(count);
					;
					insertBook(book);
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean UpdateBookVolume(String updatebook, int volume) {
		try {
			bookList = obj.readValue(new File(bookFile), new TypeReference<List<Book>>() {
			});
			for (Book book : bookList) {
				if (book.getBookName().equals(updatebook)) {
					FileWriter file = new FileWriter(bookFile, false);
					bookList.remove(book);
					book.setVolume(volume);
					insertBook(book);
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean userDeleteBook(String deleteBook) {
//		try {
//			bookList = obj.readValue(new File(bookFile), new TypeReference<List<Book>>() {
//			});
//			for (Book book : bookList) {
//				if (book.getBookName().equals(deleteBook)) {
//					FileWriter file = new FileWriter(bookFile, false);
//				}
//			}
//			for (Book book : bookList) {
//				if (!book.getBookName().equals(deleteBook)) {
////					insertBook(book);
//					return true;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return false;
		for (Book book : bookList) {
			if (book.getBookName().equals(deleteBook)) {
				bookList.remove(book);
				return true;
			}

		}
		return false;
	}

	public boolean insertUser(User user) {
		try {
			File fileCandidate = new File(userFile);
			if (!fileCandidate.exists()) {
				System.out.println("file");
				fileCandidate.createNewFile();
			}
			if (fileCandidate.length() > 0) {// verifying that the length of the file is not empty
				userList = obj.readValue(new File(userFile), new TypeReference<List<User>>() {
				});
				for (User users : userList) {
					if (user.getEmailId().equals(users.getEmailId()))
						return false;
				}
				userList.add(user);
				obj.writeValue(fileCandidate, userList);
				return true;
			} else {
				userList.add(user);
				obj.writeValue(fileCandidate, userList);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("User");
		}
		return false;

	}

	public List<User> viewuser() {
		try {
			return userList = obj.readValue(new File(userFile), new TypeReference<List<User>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
//		return userList;
	}

	public boolean updateUserEmail(String userName, String userEmail) {
		try {
			userList = obj.readValue(new File(userFile), new TypeReference<List<User>>() {
			});
			for (User user : userList) {
				if (user.getUserName().equals(userName)) {
					FileWriter file = new FileWriter(userFile, false);
					userList.remove(user);
					user.setEmailId(userEmail);
					insertUser(user);
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateUserPhoneNumber(String userName, long phonenumber) {
		try {
			userList = obj.readValue(new File(userFile), new TypeReference<List<User>>() {
			});
			for (User user : userList) {
				if (user.getUserName().equals(userName)) {
					FileWriter file = new FileWriter(userFile, false);
					userList.remove(user);
					user.setPhoneNumber(phonenumber);
					insertUser(user);
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteUser(String userName) {
		for (User addedUser : userList) {
			if (addedUser.getUserName().equals(userName)) {
				userList.remove(addedUser);
				return true;
			}
		}
		return false;
	}

	public boolean assignBookToUser(Map<String, String> assign) {
		issuedBook.add(assign);
		try {
			File file = new File(assignBookFile);
			if (!file.exists()) {
				file.createNewFile();
			}
			if (file.length() >= 0) {

				obj.writeValue(file, new ArrayList<Map<String, String>>());
			}
			List<Map<String, String>> existingAssignBooks = obj.readValue(file,
					new TypeReference<List<Map<String, String>>>() {
					});
			existingAssignBooks.add(assign);
			obj.writeValue(file, existingAssignBooks);

			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Map<String, String>> showIssuedBooks() {
		try {
			issuedBook = obj.readValue(new File(assignBookFile), new TypeReference<List<Map<String, String>>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return issuedBook;
	}
}
