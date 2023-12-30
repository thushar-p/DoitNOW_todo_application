<%@page import="com.thushar.todoWebApp.entities.CompletedTask"%>
<%@page import="com.thushar.todoWebApp.DAO.CompletedTaskDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.thushar.todoWebApp.entities.Task"%>
<%@page import="com.thushar.todoWebApp.DAO.TaskDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.thushar.todoWebApp.entities.User"%>
<%@ page errorPage="errorPage.jsp"%>

<%
/* 
 *	accepts the data which is sent from the loginServlet and stored in respective var.
 */
User user = (User) session.getAttribute("presentUserDetails");


	if (user == null) {
		response.sendRedirect("loginPage.jsp");
	}

	int userId = user.getUserId();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- <link rel="stylesheet" href="index.css"> -->
<link rel="icon" href="images/indexFavicon.png" type="image/x-icon">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
	integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<title>Home | Do it NOW</title>

<style>
	<%@ include file="index.css"%>
</style>

</head>
<body>

<!-- 	top header section starts here -->
	<header>
		<div id="userDetails">
			<h1><%=user.getUserName()%></h1>
			<h1><%=user.getUserEmail()%></h1>
		</div>
		<a href="profile.jsp"><i class="fa-solid fa-user"></i></a>
	</header>
<!-- 	top header section ends here -->

<!-- 	side section start here -->
	<section>
		<div id="sideBar">
			<img alt="CompanyLogo" src="images/companyLogo.png" id="companyLogo">
			<div id="first">
				<button class="taskSideButton active">Incomplete Tasks</button> 
				<button class="completedSideButton">Completed Tasks</button>
			</div>
			<a href="logoutServlet"><button id="logout">Logout</button></a>
		</div>
	</section>
<!-- 	side section ends here -->


	<main>
		<div id="tasks">
			<div id="testCase"></div>
		
			<div id="firstRowButton">
				<div id="firstSet">
					<button id="completeAllTask">Complete all</button>
					<button id="deleteAllTask">Delete all</button>
				</div>
			<a href="addTask.jsp"><button>Add Task</button></a>
			</div>
			
			<%
			// 		this function and loop is used to create mulitple task list as per the requirement

			TaskDAO taskDAO = new TaskDAO();
			List<Task> taskList = taskDAO.getAllTask(userId);
			
// 			int taskId = 0;
			
			if (taskList.isEmpty() == false) {
				
				int i = 0;
				for (Task t : taskList) {

			%>

			<div class="task_container">
				<div class="task_details">
					<h2><%=t.getTaskDetail()%></h2>
					<div class="oneBox">
						<div class="task_dueTime">
							<h2><%=t.getDueTime()%></h2>
						</div>
						<div class="task_dueDate">
							<h2><%=t.getDueDate()%></h2>
						</div>
					</div>
					
					
				</div>
					<div class="task_buttons">
						<i class="fa-solid fa-pen" onclick="editHandler(<%= i %>)">
							<span><% t.getTaskId(); %></span>
						</i>
						<i class="fa-solid fa-trash" onclick="deleteHandler(<%= i %>)"></i>
						<i class="fa-solid fa-square-check" onclick="taskCompleteHandler(<%= i %>)"></i>
					</div>
				
			</div>

																<!-- 		edit task button starts here -->
																	<div class="edit_popup">
																		<div class="edit_popup_content">
																			<i class="fa-solid fa-circle-xmark" onclick="editClose(<%= i %>)"></i>
																			<form action="editTaskServlet?id=<%= t.getTaskId() %>&??" method="POST">
																				<table>
																					<tr>
																						<td colspan="2"><label for="editTask">Edit task</label> <input
																							type="text" id="editTask" name="editedTask" required
																							autocomplete="OFF" autofocus="autofocus" value="<%= t.getTaskDetail() %>">
																						</td>
																					</tr>
																					<tr>
																						<td><label for="time">Edit due time : </label> <input
																							type="time" name="dueTime" id="time"></td>
																						<td><label for="date">Edit due date : </label> <input
																							type="date" name="dueDate" id="date"></td>
																					</tr>
																					<tr>
																						<td colspan="2">
																							<input type="submit" value="Save"> 
																							<input type="reset" value="Clear">
																						</td>
																					</tr>
																				</table>
																			</form>
																		</div>
																	</div>
																	<!-- 		edit task button ends here -->
																	
																	
																	
																	
																	
																	
																	<!-- 		delete task button starts here -->
																		<div class="deleteTask_popup">
																			<i class="fa-solid fa-circle-xmark" onclick="deleteClose(<%= i %>)"></i>
																			<div class="deleteTask_popup_content">
																				<h2>Delete the task?</h2>
																				<div id="options">
<%-- 																					<%= t.getTaskId() %> --%>
																						<form action="deleteTaskComplete?id=<%= t.getTaskId() %>&??" method="POST">
																							<button>Delete task</button>
																						</form>
																				</div>
																			</div>
																		</div>
																		<!-- 		delete task button ends here -->
																		
																		
																		
																		
																		
																		<!-- 	 task complete button starts here -->
																			<div class="taskComplete_popup">
																				<div class="taskComplete_popup_content">
																					<form action="taskFinishedSuccessfully?id=<%= t.getTaskId() %>&??" method="POST">
																						<h2>Congratulations on completing the task !</h2>
																						<input type="submit" value="Thanks">
																					</form>
																				</div>
																			</div>
																			<!-- 	 task complete button ends here -->

			<%
			
					i++;
				}
				
			}
			%>
		</div>




		<div id="completed">
		
			<div id="completeTaskTestCase"></div>
			
			<div id="firstRowButton">
				<div id="firstSet">
					<button id="inCompleteAllTask">Regain all</button>
				</div>
			<a href="addTask.jsp"><button>Add Task</button></a>
			</div>
		
			<%
			// 		this function and loop is used to create mulitple task list as per the requirement

			CompletedTaskDAO ctDAO = new CompletedTaskDAO();
			List<CompletedTask> listOfCompletedTask = ctDAO.getAllCompletedTask(userId);
			
// 			int taskId = 0;
			
			if (listOfCompletedTask.isEmpty() == false) {
				
				int i = 0;
				for (CompletedTask t : listOfCompletedTask) {

			%>

			<div class="completeTask_container">
				<div class="completeTask_details">
					<h2><%=t.getCompletedTaskDetail()%></h2>

					<div class="oneBox">
						<div class="completeTask_dueTime">
							<h2><%=t.getCompletedTaskDueTime()%></h2>
						</div>
						<div class="completeTask_dueDate">
							<h2><%=t.getCompletedTaskDueDate()%></h2>
						</div>
					</div>

				</div>
				
				<div class="completeTask_buttons">
					<i class="fa-solid fa-list-ul" onclick="taskRegainHandler(<%= i %>)"></i>
				</div>
			</div>

																		
																		<!-- 	 task complete button starts here -->
																			<div class="taskRegain_popup">
																				<div class="taskRegain_popup_content">
																					<form action="taskNotFinished?id=<%= t.getCompletedTaskId() %>&??" method="POST">
																						<h2>We are waiting for you to complete the task !</h2>
																						<input type="submit" value="Okay">
																					</form>
																				</div>
																			</div>
																		<!-- 	 task complete button ends here -->

			<%
			
					i++;
				}
				
			}
			%>
		</div>
		
		
		
<!-- 		popups for complete task, regain task, delete task -->

<!-- 	 task complete button starts here -->
		<div class="completeAllTask_popup">
		<i class="fa-solid fa-circle-xmark" id="exit_popup"></i>
			<div class="completeAllTask_popup_content">
				<form action="completeAllTask">
					<h2>Have you completed all the task ? Great</h2>
					<input type="submit" value="Yes">
				</form>
			</div>
		</div>
	<!-- 	 task complete button ends here -->
	
	
	<!-- 	 task complete button starts here -->
		 <div class="deleteAllTask_popup">
		 <i class="fa-solid fa-circle-xmark" id="exit_popup"></i>
			<div class="deleteAllTask_popup_content">
				<form action="deleteAllTask">
					<h2>Are you sure you want to delete all your tasks !</h2>
					<input type="submit" value="Yes">
				</form>
			</div>
		</div> 
	<!-- 	 task complete button ends here -->
	
	<!-- 	 task complete button starts here -->
		<div class="incompleteTask_popup">
		<i class="fa-solid fa-circle-xmark" id="exit_popup"></i>
			<div class="incompleteTask_popup_content">
				<form action="incompleteTask">
					<h2>Incompleted tasks?</h2>
					<input type="submit" value="Yes">
				</form>
			</div>
		</div>
	<!-- 	 task complete button ends here -->
			
	</main>



	<script type="text/javascript" src="index.js"></script>
</body>
</html>