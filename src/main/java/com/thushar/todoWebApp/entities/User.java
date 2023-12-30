package com.thushar.todoWebApp.entities;

/*
	User entity which is used to store the details of User
 */
public class User {

	private int userId;
	private String userName;
	private String userEmail;
	private long userContact;
	private String userPassword;


	/*
	 * parameterised constructor in initialize the objext states. userId is auto
	 * generated in mysql
	 */
	public User(String userName, String userEmail, long userContact, String userPassword) {
		this.userName = userName;
		this.userEmail = userEmail;
		this.userContact = userContact;
		this.userPassword = userPassword;
	}


	/*
	 * empty constructor which is used to invoke the getters and setter methods.
	 */
	public User() {}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public long getUserContact() {
		return userContact;
	}

	public void setUserContact(long userContact) {
		this.userContact = userContact;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

}
