package com.thushar.todoWebApp.entities;

/*
	Task entity class which is used to store uncompleted task details
*/
public class Task {
	
	private int taskId;
	private String taskDetail;
	private String dueDate;
	private String dueTime;
	private int userId;
	
	
	public Task(String taskDetail, String dueDate, String dueTime, int userId) {
		this.taskDetail = taskDetail;
		this.dueDate = dueDate;
		this.dueTime = dueTime;
		this.userId = userId;
	}

	/*
	 * constructor which is initial all the states except taskId
	 */
	public Task(String taskDetail, String dueDate, String dueTime) {
		this.taskDetail = taskDetail;
		this.dueDate = dueDate;
		this.dueTime = dueTime;
	}

	/*
	 * empty dummy constructor which is used to invoke the setter and getter method
	 */
	public Task() {};
	
	
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getTaskDetail() {
		return taskDetail;
	}
	public void setTaskDetail(String taskDetail) {
		this.taskDetail = taskDetail;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getDueTime() {
		return dueTime;
	}
	public void setDueTime(String dueTime) {
		this.dueTime = dueTime;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
