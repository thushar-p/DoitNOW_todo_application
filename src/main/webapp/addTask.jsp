<%@page import="com.thushar.todoWebApp.entities.Message"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="com.thushar.todoWebApp.entities.User"%>
<%@ page errorPage="errorPage.jsp"%>

<%
/* * accepts the data which is sent from the loginServlet and stored in respective var. */ 
User user = (User) session
		.getAttribute("presentUserDetails");
if (user == null) {
	response.sendRedirect("loginPage.jsp");
}
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Do it NOW | add task</title>
<!-- <link rel="stylesheet" href="addTask.css"> -->
<link rel="icon" href="images/loginFavicon.png" type="image/x-icon">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
	integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />


<style>
<%@
	include file="addTask.css"
%>
</style>

</head>

<body>
	<header>
		<a href="index.jsp"><button>Go to Home</button></a>
		<div id="firstDiv">
			<div id="userDetails">
				<h1>
					<%=user.getUserName()%>
				</h1>
				<h1>
					<%=user.getUserEmail()%>
				</h1>
			</div>
			<a href="profile.jsp"><i class="fa-solid fa-user"></i></a>
		</div>
	</header>
	<main>
		<div id="imageBlock">
			<img src="images/addTaskImage.svg" alt="addTaskImage">
		</div>
		<div id="textBlock">
		<%
		
// 		used to show the success message of adding task to the database succesfully
		
			Message msg = (Message)session.getAttribute("succuessfullyAddedTask");
			if(msg != null){
		%>
			<div id="taskAdded"><%= msg.getMessage() %></div>
		<%
			}
			session.removeAttribute("succuessfullyAddedTask");
		%>
		
		
			<h1>Add task details</h1>
			<form action="addTask" method="get">
				<ul>
					<li><label for="addTask">Add your task details : </label> 
					<input type="text" id="addTask" name="taskDetail" required
						autocomplete="OFF" autofocus>
					</li>
					<li><label for="time">Set due time : </label> <input
						type="time" name="dueTime" id="time"> &nbsp; &nbsp; &nbsp;
						&nbsp; &nbsp; &nbsp; <label for="date">Set due date : </label> <input
						type="date" name="dueDate" id="date">
					</li>
					<li><input type="submit" value="Add Task"> <input
						type="reset" value="Clear"></li>
				</ul>
			</form>
		</div>
	</main>
</body>

</html>