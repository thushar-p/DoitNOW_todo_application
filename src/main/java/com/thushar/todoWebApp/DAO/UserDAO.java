package com.thushar.todoWebApp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.thushar.todoWebApp.entities.User;
import com.thushar.todoWebApp.helper.DatabaseConnector;

/*
	UserDAO class is used to perform database operations which is related to user table
 */

public class UserDAO {

	/*
	 * addUser(User user) method which is used to add the user details into the
	 * database. 
	 * 
	 * userId is automated here.
	 * 
	 * return string type because of what type of error message to be displayed in the UI
	 * 
	 * @param User --> user object
	 * 
	 * @return String --> 
	 * returns "true" --> if the User data has been inserted to database succesfully
	 * returns "false" --> if the User data have not been inserted to database successfully
	 * returns exception message --> if the user is using the already existing email or contact which is already present in the database
	 * 
	 */
	public String addUser(User user) {

		String checkPoint = "false";

		Connection connection = DatabaseConnector.getConnection();

		String insertDML = "INSERT INTO todowebapp.user (userName, userEmail, userContact, userPassword) VALUES (?,?,?,?)";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(insertDML);

			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getUserEmail());
			preparedStatement.setLong(3, user.getUserContact());
			preparedStatement.setString(4, user.getUserPassword());
			preparedStatement.executeUpdate();
			
			checkPoint = "true";

			System.out.println(checkPoint);
		} catch (SQLException e) {
			
//			assigning the exception message to check what input type is duplicated here
			checkPoint = e.getMessage();

		}
		
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return checkPoint;
	}

	
	

	/*
	 * getDetailsOnEmailAndPassword(String email, String password) method which is
	 * used to retreive the user data from the database based on the email and password.
	 * 
	 * @param String email
	 * @param String password
	 * 
	 * @return User  --> returns USer object with all 5 states
	 */
	public User getDetailsOnEmailAndPassword(String email, String password) {

		Connection connection = DatabaseConnector.getConnection();
		User user = null;

		String getDQL = "SELECT * FROM todowebapp.user WHERE userEmail = '"+email+"' AND userPassword = '"+password+"'";

		try {
			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(getDQL);

			if(resultSet.next()) {
				System.out.println("inside result statement");
				user = new User();
				user.setUserId(resultSet.getInt("userId"));
				user.setUserName(resultSet.getString("userName"));
				user.setUserEmail(resultSet.getString("userEmail"));
				user.setUserContact(resultSet.getLong("userContact"));
				user.setUserPassword(resultSet.getString("userPassword"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return user;
	}
	
	
	


	/*
	 * This method is used to delete user details from the database table permenently
	 * 
	 * this method is invoked when the user clicks delete account permenently
	 * 
	 * @param String userEmail --> delete the account on the basis of userEmail because userEmail is unique
	 * 
	 * @returns boolean
	 * returns true --> if the User object deleted from the database successfully from the database
	 * returns false --> if the User object is not deleted from the database.
	 * 
	 */
	public boolean deleteAccountByEmail(String userEmail) {

		Connection connection = DatabaseConnector.getConnection();

		String deleteDML = "DELETE FROM todowebapp.user WHERE userEmail = '"+userEmail+"'";

		String userIdString = getUserIdFromUser(userEmail);
		if(userIdString != null) {

			int userId = Integer.parseInt(userIdString);

			try {

				TaskDAO taskDAO = new TaskDAO();
				taskDAO.deleteTaskByUserId(userId);
				
				CompletedTaskDAO ctDAO = new CompletedTaskDAO();
				ctDAO.deleteCompletedTaskByUserId(userId);

				Statement statement = connection.createStatement();
				int updated = statement.executeUpdate(deleteDML);

				if(updated > 0) {
					return true;
				}
			} catch (SQLException e) {

				e.printStackTrace();

			} finally {

				try {
					connection.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	
	
	

	/*
	 * this method is used to get the userId from the user table corresponding with the given userEmail
	 * 
	 * @param String userEmail --> uses the userEmail to fetch the userId of with respect to unique userEmail
	 * 
	 * @return String --> returns userId in the form of String because the method caller will check whether it is getting the userId or null
	 * 
	 */
	public String getUserIdFromUser(String userEmail) {
		String userId = null;
		Connection connection = DatabaseConnector.getConnection();

		String getIdDQL = "SELECT userId FROM todowebapp.user WHERE userEmail = '"+userEmail+"'";

		try {
			Statement createStatement = connection.createStatement();
			ResultSet resultSet = createStatement.executeQuery(getIdDQL);

			if(resultSet.next()) {
				userId = resultSet.getString("userId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return userId;
	}
	
	


	/*
	 * this method is invoked when user edit his profile.
	 * 
	 * This method is used to update the existing user details with respect to his email because email is unique
	 * 
	 * @param User user --> User object where we get all the user details
	 * @param String userEmail --> using the userEmail the user information are being updated
	 * 
	 * @return String -->
	 * returns "true" --> when the updation is successfull 
	 * returns "false" --> when the updation is failed
	 * returns exception message --> to get to know due to what duplicate entry the updation is failed --> used to show the error message in the editProfile.jsp page
	 * 
	 */
	public String updateUserDetails(User user, String userEmail) {

		String message = null;
		String userIdString = getUserIdFromUser(userEmail);
		if(userIdString == null) {
			return message;
		}
		else {
			int userId = Integer.parseInt(userIdString);

			Connection connection = DatabaseConnector.getConnection();

			String updateDML = "UPDATE todowebapp.user SET userName = ? , userEmail = '"+userEmail+"' ,userContact = ? , userPassword = ? WHERE userId = "+userId;

			try {

				PreparedStatement preparedStatement = connection.prepareStatement(updateDML);

				preparedStatement.setString(1, user.getUserName());
				preparedStatement.setLong(2, user.getUserContact());
				preparedStatement.setString(3, user.getUserPassword());
				
				int executeUpdate = preparedStatement.executeUpdate();
				
				if(executeUpdate > 0) {
					message = "true";
				}

			} catch (SQLException e) {
				message = e.getMessage();
				e.printStackTrace();
			}

		}
		return message;
	}


	/*
	 * this method is used to change the password when user is trying to set new password using the forgot password functionality
	 * 
	 * @param String userEmail -> Using this email we will be updating the password
	 * @param String updatedPassword -> this is the user entered new password
	 */
	public boolean changePassword(String userEmail, String updatedPassword) {
		
		Connection connection = DatabaseConnector.getConnection();
		String updatePasswordDML = "UPDATE todowebapp.user SET userPassword = '"+updatedPassword+"' WHERE userEmail = '"+userEmail+"'";
		
		try {
			Statement createStatement = connection.createStatement();
			int result = createStatement.executeUpdate(updatePasswordDML);
			if(result>0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
}

