<%@page import="com.thushar.todoWebApp.entities.Message"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page errorPage="errorPage.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign up | To do</title>
<link rel="icon" href="images/registerFavicon.png" type="image/x-icon">
<link rel="stylesheet" href="registerPage.css">
</head>
<body>
	<section id="imageBlock">
		<img alt="" src="images/registerImage.svg">
	</section>
	<section id="textBlock">
		<%
		
// 		this tag gets invoked when the user delete his account permenently and gets a success message in register page
		
			Message msg1 = (Message)session.getAttribute("AccountDeleteSuccess");
			if(msg1 != null){
		%>
			<div id="del"><%= msg1.getMessage() %></div>
		<%
			}
			session.removeAttribute("AccountDeleteSuccess");
		%>
		
		
		
		
		<%
		
// 		this error message is invoked when the new user tries registering with the same email which is already used by other user
		
			Message msg2 = (Message)session.getAttribute("duplicateEmail");
			if(msg2 != null){
		%>
			<div id="del"><%= msg2.getMessage() %></div>
		<%
			}
			session.removeAttribute("duplicateEmail");
		%>
		
		
		
		
		<%
		
// 		this error message is invoked when the new user tries registering with the same contact which is already used by other user

			Message msg3 = (Message)session.getAttribute("duplicateContact");
			if(msg3 != null){
		%>
			<div id="del"><%= msg3.getMessage() %></div>
		<%
			}
			session.removeAttribute("duplicateContact");
		%>
		<form action="registerServlet" method="POST">
			<table>
				<tr>
					<td><label for="name">Full Name</label></td>
					<td><input type="text" id="name" autocomplete="off"
						name="name" required autofocus></td>
				</tr>
				<tr>
					<td><label for="email">Email</label></td>
					<td><input type="email" name="email" required></td>
				</tr>
				<tr>
					<td><label for="phoneNumber">Phone Number</label></td>
					<td><input type="tel" name="phoneNumber" required
						pattern="[0-9]{10}"></td>
				</tr>
				<tr>
					<td><label for="password">Password</label></td>
					<td><input type="password" id="password" name="password"
						required></td>
				</tr>
				<tr>
					<td><label for="confirmPassword">Confirm password</label></td>
					<td><input type="password" id="confirmPassword"
						name="confirmPassword" required></td>
				</tr>
				<tr>
					<td colspan="2"><input type="checkbox" id="checkbox"
						onclick="show_password()"><label for="checkbox">
							Show password</label></td>
				</tr>
				<tr>
					<td colspan="2" id="passwordErrorMessage"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Register"
						id="submitButton"> <input type="reset" value="Clear">
					</td>
				</tr>
			</table>
			<h2>Been here before? <a href="loginPage.jsp">Login here</a></h2>
		</form>
	</section>

	<script>
	
	 let password = document.getElementById("password");
	 let confirmPassword = document.getElementById("confirmPassword");
	 let passwordErrorMessage = document.getElementById("passwordErrorMessage");
	 let submitButton = document.getElementById("submitButton");
	 
	 
	 
	//function which is performed when we check the show password checkbox
	 function show_password(){
	    if(password.type == "password" && confirmPassword.type == "password"){
	        confirmPassword.type = "text";
	        password.type = "text";
	    }
	    else{
	        confirmPassword.type = "password";
	        password.type = "password";
	    }
	}

	//function which is ued to display the error message about confirm password
	confirmPassword.addEventListener("keyup", () =>{
		let passwordInput = password.value;
		let confirmPasswordInput = confirmPassword.value;

		if(passwordInput != confirmPasswordInput){
			passwordErrorMessage.innerHTML = "Password dosen't match";
			submitButton.disabled = true;
			submitButton.classList.add("disabled");
		}
		else if(passwordInput == confirmPasswordInput){
			passwordErrorMessage.innerHTML = "";
			submitButton.disabled = false;
			submitButton.classList.remove("disabled");
		}
	})
	
	</script>

</body>
</html>