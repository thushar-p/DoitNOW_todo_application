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
import com.thushar.todoWebApp.helper.DatabaseConnector;

/*
	This class is used to perform database operation on completed_task table
	*/
public class CompletedTaskDAO {
	
	

	
	/*
	 * This method is used to add the completed task to the completed_task table
	 * 
	 * @param int taskId --> to fetch the Task object from TaskDAO
	 * 
	 * @return boolean
	 * 
	 * returns true -->  when the task insertion is successfull
	 * returns false --> when the task insertion is failed
	 * 
	 */
	public boolean addCompletedTask(int taskId) {

		TaskDAO taskDAO = new TaskDAO();

		Task taskInfo = taskDAO.getTaskByTaskId(taskId);

		String completedTaskDetail = taskInfo.getTaskDetail();
		String completedTaskDueDate = taskInfo.getDueDate();
		String completedTaskDueTime = taskInfo.getDueTime();
		int completedTaskUserId = taskInfo.getUserId();


		if(completedTaskDueDate == null) {
			completedTaskDueDate = "";
		}
		if(completedTaskDueTime == null) {
			completedTaskDueTime = "";
		}

		if(taskInfo != null) {

			Connection connection = DatabaseConnector.getConnection();
			String insertDML = "INSERT INTO todowebapp.completed_task (completedTaskId, completedTaskDetail, completedTaskDueDate, completedTaskDueTime, completedTaskUserId) VALUES (?,?,?,?,?)";

			try {
				PreparedStatement preparedStatement = connection.prepareStatement(insertDML);
				preparedStatement.setInt(1, taskId);
				preparedStatement.setString(2, completedTaskDetail);
				preparedStatement.setString(3, completedTaskDueDate);
				preparedStatement.setString(4, completedTaskDueTime);
				preparedStatement.setInt(5, completedTaskUserId);

				int updated = preparedStatement.executeUpdate();
				if(updated > 0) {

					boolean deleted = taskDAO.deleteTaskByTaskId(taskId);
					if(deleted) {
						return true;
					}
					else {
						return false;
					}

				}
				else {
					return false;
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
		return false;
	}





	/*
	 * This method which is used to fetch all the CompletedTask present in the completed_task table of corresponding userId
	 * 
	 * @param int userId --> fetches all the task present in the task table with respect to the userId inorder to display it in the index.jsp
	 * 
	 * @return List<CompletedTask> -->
	 * returns List<CompletedTask> because there might be multiple task for a single user
	 * 
	 */
	public List<CompletedTask> getAllCompletedTask(int userId){

		List<CompletedTask> listOfCompletedTasks = new LinkedList<CompletedTask>();

		Connection connection = DatabaseConnector.getConnection();

		String getAllDQL = "SELECT * FROM todowebapp.completed_task WHERE completedTaskUserId = "+userId;

		try {
			Statement statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery(getAllDQL);

			while(resultSet.next()){
				CompletedTask completedTask = new CompletedTask();
				completedTask.setCompletedTaskId(resultSet.getInt("completedTaskId"));
				completedTask.setCompletedTaskDetail(resultSet.getString("completedTaskDetail"));
				completedTask.setCompletedTaskDueDate(resultSet.getString("completedTaskDueDate"));
				completedTask.setCompletedTaskDueTime(resultSet.getString("completedTaskDueTime"));
				completedTask.setCompletedTaskuserId(resultSet.getInt("completedTaskUserId"));

				listOfCompletedTasks.add(completedTask);
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

		return listOfCompletedTasks;
	}
	
	
	

	/*
	 * This method is used to perfome the opertions when the user click on unfinished icon on the webPage
	 * 
	 * First --> Gets all the completedTask Information from the completed_task table database
	 * Second --> adds the unfinished task to the Task table 
	 * Third --> deletes the completedTask from the completed_task database
	 * 
	 * @param int taskId --> based on the taskId it performs the operation
	 * 
	 * @return boolean
	 */
	public boolean unfinishedTask(int taskId) {
		
		CompletedTask completedTaskInfo = getCompletedTaskByTaskId(taskId);
		
		TaskDAO taskDAO = new TaskDAO();
		
		boolean addUnfinishedTask = taskDAO.addUnfinishedTask(completedTaskInfo);
		
		if(addUnfinishedTask) {
			boolean deleteUnfinishedTask = deleteUnfinishedTask(taskId);
			
			if(deleteUnfinishedTask) {
				return true;
			}
		}
		return  false;
	}

	
	
	/*
	 * This method is used to delete the task from the completed_task table
	 * 
	 * @param int taskId --> using the taskId it deletes the task from the completed_task table
	 * 
	 * @return return boolean
	 * returns true -->  when the task deletion is successfull
	 * returns false --> when the task deletion is failed
	 */
	public boolean deleteUnfinishedTask(int taskId) {

		Connection connection = DatabaseConnector.getConnection();

		String deleteTaskDML = "DELETE FROM todowebapp.completed_task WHERE completedTaskId = "+taskId;

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
	 * This method is used to get all the information about the particular task with respect to the taskId
	 * 
	 * @param int taskId --> using the taskId it fetches all the information of particular completedTask
	 * 
	 * @return CompletedTask --> return CompletedTask object which has all the details for adding it back to the task table
	 * 
	 */
	public CompletedTask getCompletedTaskByTaskId(int taskId) {
		
		
		Connection connection = DatabaseConnector.getConnection();
		
		String getTaskByTaskIdDQL = "SELECT * FROM todowebapp.completed_task WHERE completedTaskId = "+taskId;
		
		try {
			Statement statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery(getTaskByTaskIdDQL);
			
			if(resultSet.next()) {
				String taskDetail = resultSet.getString("completedTaskDetail");
				String taskDueDate = resultSet.getString("completedTaskDueDate");
				String taskDueTime = resultSet.getString("completedTaskDueTime");
				int userId = resultSet.getInt("completedTaskUserId");
				
				CompletedTask cTask = new CompletedTask(taskId, taskDetail, taskDueDate, taskDueTime, userId);
				
				return cTask;
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
	 * This method is used to delete all the task present in the completed_task
	 * table This method is invoked when the user clicks on the delete account
	 * permanently
	 * 
	 * @param int userId --> with respect to userId all the tasks are being deleted
	 */
	public void deleteCompletedTaskByUserId(int userId) {

		Connection connection = DatabaseConnector.getConnection();

		String deleteDML = "DELETE FROM todowebapp.completed_task WHERE completedTaskUserId = '"+userId+"'";

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
	 * This method is used to add all the tasks from task table to completed_task table.
	 */
	public void addAllTask(List<Task> taskList) {
		
		Connection connection = DatabaseConnector.getConnection();
		
		String addTaskDML = "INSERT INTO todowebapp.completed_task (completedTaskId, completedTaskDetail, completedTaskDueDate, completedTaskDueTime, completedTaskUserId) VALUES (?,?,?,?,?)";
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(addTaskDML);
			
			for(Task t : taskList) {
				
				pstmt.setInt(1, t.getTaskId());
				pstmt.setString(2, t.getTaskDetail());
				pstmt.setString(3, t.getDueDate());
				pstmt.setString(4, t.getDueTime());
				pstmt.setInt(5, t.getUserId());
				
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
