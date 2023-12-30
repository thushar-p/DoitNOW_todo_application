package com.thushar.todoWebApp.entities;


/*
CompletedTask entity class to store the completed task information
*/
public class CompletedTask {

	private int completedTaskId;
	private String completedTaskDetail;
	private String completedTaskDueDate;
	private String completedTaskDueTime;
	private int completedTaskuserId;
	
	
	public CompletedTask(int completedTaskId, String completedTaskDetail, String completedTaskDueDate,
			String completedTaskDueTime, int completedTaskuserId) {
		this.completedTaskId = completedTaskId;
		this.completedTaskDetail = completedTaskDetail;
		this.completedTaskDueDate = completedTaskDueDate;
		this.completedTaskDueTime = completedTaskDueTime;
		this.completedTaskuserId = completedTaskuserId;
	}

	/*
	 * public CompletedTask(String completedTaskDetail, String completedTaskDueDate,
	 * String completedTaskDueTime) { this.completedTaskDetail =
	 * completedTaskDetail; this.completedTaskDueDate = completedTaskDueDate;
	 * this.completedTaskDueTime = completedTaskDueTime; }
	 */
	
	public CompletedTask() {}

	
	public int getCompletedTaskId() {
		return completedTaskId;
	}

	public void setCompletedTaskId(int completedTaskId) {
		this.completedTaskId = completedTaskId;
	}

	public String getCompletedTaskDetail() {
		return completedTaskDetail;
	}

	public void setCompletedTaskDetail(String completedTaskDetail) {
		this.completedTaskDetail = completedTaskDetail;
	}

	public String getCompletedTaskDueDate() {
		return completedTaskDueDate;
	}

	public void setCompletedTaskDueDate(String completedTaskDueDate) {
		this.completedTaskDueDate = completedTaskDueDate;
	}

	public String getCompletedTaskDueTime() {
		return completedTaskDueTime;
	}

	public void setCompletedTaskDueTime(String completedTaskDueTime) {
		this.completedTaskDueTime = completedTaskDueTime;
	}

	public int getCompletedTaskuserId() {
		return completedTaskuserId;
	}

	public void setCompletedTaskuserId(int completedTaskuserId) {
		this.completedTaskuserId = completedTaskuserId;
	}
	
}
