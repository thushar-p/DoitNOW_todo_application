package com.thushar.todoWebApp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.thushar.todoWebApp.entities.CompletedTask;
import com.thushar.todoWebApp.entities.Task;
import com.thushar.todoWebApp.entities.User;
import com.thushar.todoWebApp.helper.DatabaseConnector;


/*
	This class is used to perform database operations of Task entity
 */
public class TaskDAO {


	/*
	 * addTask(Task task, User userSession) method is used to add task to the
	 * database with foriegn key from the user table
	 * 
	 * @param Task task --> Task information inorder to add to the the database
	 * @param User userSession --> inorder to fetch the userId from the user table --> to get userId there is a method called getUserIdFromUser(String userEmail)
	 * 
	 * @return int --> inorder to get verified when the particular Task is assigned with foreign key from the user table --> if we didin't get userId method will return 1
	 * 
	 */
	public int addTask(Task task, User userSession) {

		Connection connection = DatabaseConnector.getConnection();

		String userEmail = userSession.getUserEmail();

		String addTaskDML = "INSERT INTO todowebapp.task (taskDetail, taskDueDate, taskDueTime, userId) VALUES (?,?,?,?)";

		String taskDetail = task.getTaskDetail();
		String taskDueDate = task.getDueDate();
		String taskDueTime = task.getDueTime();

		UserDAO userDAO = new UserDAO();

		/*
		 * we are getting userId from the userTable to map it to the task table
		 */
		String userIdString = userDAO.getUserIdFromUser(userEmail);
		int userId = 0;
		if(userIdString == null) {

			//if there is no user with the given email then it will return 1
			return 1;

		}
		userId = Integer.parseInt(userIdString);

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(addTaskDML);
			preparedStatement.setString(1, taskDetail);
			preparedStatement.setString(2, taskDueDate);
			preparedStatement.setString(3, taskDueTime);
			preparedStatement.setInt(4, userId);

			preparedStatement.executeUpdate();


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

		return userId;
	}
	
	

	/*
	 * this method is used to delete the respected task which assigned with the userId 
	 * this is invoked when user delete his/her account permenently
	 * 
	 * @param int userId --> inorder to delete all the task with respect foreign key userId
	 */
	public void deleteTaskByUserId(int userId) {

		Connection connection = DatabaseConnector.getConnection();

		String deleteDML = "DELETE FROM todowebapp.task WHERE userId = '"+userId+"'";

		try {
			
			
			Statement statement = connection.createStatement();

			statement.executeUpdate(deleteDML);

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	
	/*
	 * This method which is used to fetch all the task present in the task table of corresponding userId
	 * 
	 * @param int userId --> fetches all the task present in the task table with respect to the userId inorder to display it in the index.jsp
	 * 
	 * @return List<Task> -->
	 * returns List<Task> because there might be multiple task for a single user
	 * 
	 */
	public List<Task> getAllTask(int userId){
		List<Task> listOfTasks = new LinkedList<Task>();
		// fetch all the data corresponding to userId

		Connection connection = DatabaseConnector.getConnection();
		
		String getAllDQL = "SELECT * FROM todowebapp.task WHERE userId = "+userId;

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(getAllDQL);

			while(resultSet.next()){
				Task task = new Task();
				task.setTaskId(resultSet.getInt("taskId"));
				task.setTaskDetail(resultSet.getString("taskDetail"));
				task.setDueDate(resultSet.getString("taskDueDate"));
				task.setDueTime(resultSet.getString("taskDueTime"));
				task.setUserId(resultSet.getInt("userId"));

				listOfTasks.add(task);
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

		return listOfTasks;
	}
	
	
	
	
	/*
	 * This method is used to edit the already exisiting task which is present in the database
	 * 
	 * @param Task editedTask --> get all the information of new task(i.e edited task) inorder to get updated
	 * @param int taskId --> get the taskId to edit the particular task
	 * 
	 * @return boolean
	 * returns true -->  when the task updation is successfull
	 * returns false --> when the task updation is failed
	 * 
	 */
	public boolean editExisitingTaskById(Task editedTask, int taskId) {

		Connection connection = DatabaseConnector.getConnection();

		String updateDMl = "UPDATE todowebapp.task SET taskDetail = ? , taskDueDate = ? , taskDueTime = ? WHERE taskId = "+taskId;

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(updateDMl);
			preparedStatement.setString(1, editedTask.getTaskDetail());
			preparedStatement.setString(2, editedTask.getDueDate());
			preparedStatement.setString(3, editedTask.getDueTime());

			int updated = preparedStatement.executeUpdate();
			if(updated > 0 ) {
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

	
	
	/*
	 * This method is used to delete the task according to the taskId
	 * when the user want to delete the particular from his profile this method is invoked
	 * 
	 * @param int taskId --> deletes the task with respected taskId
	 * 
	 * @return boolean -->
	 * returns true -->  when the task deletion is successfull
	 * returns false --> when the task deletion is failed
	 * 
	 */
	public boolean deleteTaskByTaskId(int taskId) {

		Connection connection = DatabaseConnector.getConnection();

		String deleteTaskDML = "DELETE FROM todowebapp.task WHERE taskId = "+taskId;

		try {
			Statement statement = connection.createStatement();

			int update = statement.executeUpdate(deleteTaskDML);

			if(update > 0) {
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
	
	
	
	/*
	 * This method is used to fetch the task details inorder to get it stored inside the completed_task table 
	 * This method is invoked when the user has completed one task
	 * 
	 * @param int taskId --> fetches the particular task based to taskId
	 * 
	 * @returns Task --> returns all the taskDetails
	 */
	public Task getTaskByTaskId(int taskId) {

		Connection connection = DatabaseConnector.getConnection();

		String getTaskByTaskIdDQL = "SELECT * FROM todowebapp.task WHERE taskId = "+taskId;

		try {
			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(getTaskByTaskIdDQL);

			if(resultSet.next()) {
				String taskDetail = resultSet.getString("taskDetail");
				String taskDueDate = resultSet.getString("taskDueDate");
				String taskDueTime = resultSet.getString("taskDueTime");
				int userId = resultSet.getInt("userId");

				Task task = new Task(taskDetail, taskDueDate, taskDueTime, userId);
				return task;
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


		return null;

	}

	
	/*
	 * This method is used to add the task back to task table from completed_task table
	 * This method is invoked when user clicks unfinish the task
	 *  
	 * @param --> CompletedTask completedTaskInfo --> has all the information of a task
	 *  
	 * @return boolean -->
	 * returns true -->  when the task insertion is successfull
	 * returns false --> when the task insertion is failed
	 * 
	 */
	public boolean addUnfinishedTask(CompletedTask completedTaskInfo) {

		Connection connection = DatabaseConnector.getConnection();

		String addTaskDML = "INSERT INTO todowebapp.task (taskDetail, taskDueDate, taskDueTime, userId) VALUES (?,?,?,?)";

		
		String taskDetail = completedTaskInfo.getCompletedTaskDetail();
		String taskDueDate = completedTaskInfo.getCompletedTaskDueDate();
		String taskDueTime = completedTaskInfo.getCompletedTaskDueTime();
		int userId = completedTaskInfo.getCompletedTaskuserId();


		if(taskDueDate == null) {
			taskDueDate = "";
		}
		if(taskDueTime == null) {
			taskDueTime = "";
		}


		try {
			PreparedStatement preparedStatement = connection.prepareStatement(addTaskDML);

			preparedStatement.setString(1, taskDetail);
			preparedStatement.setString(2, taskDueDate);
			preparedStatement.setString(3, taskDueTime);
			preparedStatement.setInt(4, userId);

			int updated = preparedStatement.executeUpdate();
			if(updated > 0) {
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


	/*
	 * This method is used to add all the completed_task table to task table
	 */
	public void addAllTask(List<CompletedTask> completedTaskList) {
		
		Connection connection = DatabaseConnector.getConnection();
		
		String addTaskDML = "INSERT INTO todowebapp.task (taskId, taskDetail, taskDueDate, taskDueTime, userId) VALUES (?,?,?,?,?)";
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(addTaskDML);
			
			for(CompletedTask t : completedTaskList) {
				
				pstmt.setInt(1, t.getCompletedTaskId());
				pstmt.setString(2, t.getCompletedTaskDetail());
				pstmt.setString(3, t.getCompletedTaskDueDate());
				pstmt.setString(4, t.getCompletedTaskDueTime());
				pstmt.setInt(5, t.getCompletedTaskuserId());
				
				pstmt.executeUpdate();
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
	}
	
}
