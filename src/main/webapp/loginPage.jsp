<%@page import="com.thushar.todoWebApp.entities.Message"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ page errorPage="errorPage.jsp"%>

<%
	Message otpMessage = (Message)session.getAttribute("otpEnteredWrong");
%>


<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Log in | To do</title>
<link rel="icon" href="images/loginFavicon.png" type="image/x-icon">
<!-- <link rel="stylesheet" href="loginPage.css"> -->
</head>

<style>
	<%@ include file="loginPage.css"%>
</style>

<body>
	<section id="textBlock">
			<%
			
	// 		this tag is used to show logout message in the login page when you logout from the account
			
				Message msg = (Message)session.getAttribute("logoutMessage");
				if(msg != null){
			%>
			<div id="logout"><%= msg.getMessage() %></div>
			<%
				}
				session.removeAttribute("logoutMessage");
			%>
		
		
		
			<%
			
// 			this tag will get invoked when the user entered wrong email to send the OTP
			
			Message msg5 = (Message) session.getAttribute("incorrectEmailAddress");
			if (msg5 != null) {
			%>
			<div id="logout"><%=msg5.getMessage()%></div>
			<%
			}
			session.removeAttribute("incorrectEmailAddress");
			
			%>
			
			
			
			<%
			
// 			this tag will get invoked when the user updated with new password

			Message msg6 = (Message) session.getAttribute("passwordUpdationSuccess");
			if (msg6 != null) {
			%>
			<div id="logout"><%=msg6.getMessage()%></div>
			<%
			}
			session.removeAttribute("passwordUpdationSuccess");
			
			%>
			
			
			<%
			
// 			this tag will get invoked when the user's new passwod updation failed
			
			Message msg7 = (Message) session.getAttribute("passwordUpdationFailed");
			if (msg7 != null) {
			%>
			<div id="logout"><%=msg7.getMessage()%></div>
			<%
			}
			session.removeAttribute("passwordUpdationFailed");
			
			%>
			
			
			
		<form action="logInServlet" method="post" class="main">
			<table>
				<tr>
					<td><label for="email">Email</label></td>
					<td><input type="email" name="email" required autofocus></td>
				</tr>
				<tr>
					<td><label for="password">Password</label></td>
					<td><input type="password" id="password" name="password"
						required></td>
				</tr>
				<tr>
					<td colspan="2"><input type="checkbox" id="checkbox"
						onclick="show_password()"><label for="checkbox">
							Show password</label></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Log in"
						id="submitButton"> <input type="reset" value="Clear">
					</td>
				</tr>
			</table>
		</form>
			<h2 class="mainAbove">Forgot password? <a id="forgotPasswordEdit">Click here</a></h2>
			<h2 class="mainAbove">New User? <a href="registerPage.jsp">Register here</a></h2>
			
			
			
			<!-- 		forgot password popup starts here -->

		<div class="forgotPassword_popup exter">
			<div class="forgotPassword_popup_content">
				<form action="forgotPassword" method="POST">
					<h2>Enter your registered email, we will be sending you an OTP to reset the password</h2>
					<table>
						<tr>
							<td><input type="email" id="email" name="email" autofocus placeholder="Enter your registered email"></td>
						</tr>
						<tr>
							<td>
								<button id="closeForgotPasswordBox">Close</button>							
								<input id="submitEmail" type="submit" value="Send OTP">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>

<!-- 		forgot password popup ends here -->





<!-- 		OTP input popup starts here -->

			<div class="OTP_popup exter">
				<div class="OTP_content">
					<%  if(otpMessage!=null){  %>
					<div id="updateSuccess"><%= otpMessage.getMessage() %></div>
					<% } %>
					<form action="otp" method="POST">
						<h2>Enter your OTP here</h2>
						<table>
							<tr>
								<td><input type="text" id="otp" name="otp" autofocus></td>
							</tr>
							<tr>
								<td>
									<input type="button" value="Close" id="closeOTPBox">
									<input type="submit" value="Done">
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>

<!-- 		OTP input popup ends here -->



<!-- 		new passwrod input popup starts here -->

			<div class="newPassword_popup exter">
				<div class="newPassword_content">
					<form action="forgetPasswordFromLogin" method="POST">
						<table>
							<tr>
								<td><input type="password" id="newPassword" name="newPassword"
									required placeholder="Enter new Password"></td>
							</tr>
							<tr>
								<td><input type="password" id="confirmNewPassword" placeholder="confirm password"
									name="confirmNewPassword" required></td>
							</tr>
							<tr>
								<td colspan="2">
								<input type="checkbox" id="checkboxNew" onclick="show_NewPassword()">
								<label for="checkboxNew"> Show password</label></td>
							</tr>
							<tr>
								<td colspan="2" id="passwordErrorMessage"></td>
							</tr>
							<tr>
								<td colspan="2">
									<button id="closeNewPasswordPopup">Close</button>
									<input type="reset" value="Clear">	
									<input type="submit" value="Done" id="submitButtonForNewPassword"> 
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>

<!-- 		new passwrod input popup ends here -->
			
			
			
			
	</section>
	<section id="imageBlock">
		<img alt="loginImage" src="images/loginImage.svg">
	</section>
	
	
	
	<!-- 	below code where we want to open the otp popup automatically beacause of the inavlid otp even after refreshing forgot passwod popup -->


	<%

		if(otpMessage!=null){

	%>
			<script type="text/javascript">
				document.querySelector(".OTP_popup").style.display = "flex"
			</script>		
	<%
		session.removeAttribute("otpEnteredWrong");
		}
	
%>

<!-- 	below code where we want to open the otp popup automatically beacause of the inavlid otp even after refreshing forgot passwod popup -->
	
	
	
	
<!-- 	below code where we want to open the otp popup automatically even after refreshing forgot passwod popup -->
	
	<%
		Integer otp = (Integer)session.getAttribute("generatedOTP");
		int readyOTP = 0;
		if(otp!=null){
			readyOTP = otp;

	%>
			<script type="text/javascript">
				document.querySelector(".OTP_popup").style.display = "flex"
			</script>		
	<%
			session.setAttribute("transferedOTP", readyOTP);
			session.removeAttribute("generatedOTP");
		}
	
	%>
<!-- 	Above code where we want to open the otp popup automatically even after refreshing forgot passwod popup -->




<!-- 	below code where we want to open the new password popup after successfull otp verification -->
	
	<%
		Message otpSuccessMsg = (Message)session.getAttribute("otpSuccess");
	
		if(otpSuccessMsg!=null){

	%>
			<script type="text/javascript">
				document.querySelector(".newPassword_popup").style.display = "flex"
			</script>		
	<%
			session.removeAttribute("otpSuccess");
		}
	
	%>
<!-- 	above code where we want to open the new password popup after successfull otp verification -->
	
	
	
	
	
	
	<script>
	
// 	function starts here
// this function is used to show the password
        let password = document.getElementById("password");

        function show_password(){
            if(password.type == "password"){
                password.type = "text";
            }
            else{
                password.type = "password";
            }
        }
//         function ends here
	
	
	
	
	// -------------------------------------------------------------------
	
	
	//		popup show function for the forgot password popup message starts here
		document.getElementById("forgotPasswordEdit").addEventListener("click", function(){
			document.querySelector(".forgotPassword_popup").style.display = "flex"
		});
		
		
		document.getElementById("closeForgotPasswordBox").addEventListener("click", function(){
			document.querySelector(".forgotPassword_popup").style.display = "none"
		});
// 		popup show function for the forgot password popup message ends here





//		OTP input popup message starts here
		document.getElementById("closeOTPBox").addEventListener("click", function(){
			document.querySelector(".OTP_popup").style.display = "none"
		})
//		OTP input popup message ends here






//--------------------------------------------------------------------------------------------------------

//		new password functionality starts here

	 let newPassword = document.getElementById("newPassword");
	 let confirmNewPassword = document.getElementById("confirmNewPassword");
	 let passwordErrorMessage = document.getElementById("passwordErrorMessage");
	 let submitButton = document.getElementById("submitButtonForNewPassword");
	 
	 
	 
	//function which is performed when we check the show password checkbox
	 function show_NewPassword(){
	    if(newPassword.type == "password" && confirmNewPassword.type == "password"){
	        confirmNewPassword.type = "text";
	        newPassword.type = "text";
	    }
	    else{
	        confirmNewPassword.type = "password";
	        newPassword.type = "password";
	    }
	}

	//function which is ued to display the error message about confirm password
	confirmNewPassword.addEventListener("keyup", () =>{
		let passwordInput = newPassword.value;
		let confirmPasswordInput = confirmNewPassword.value;

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
	
	
	document.getElementById("closeNewPasswordPopup").addEventListener("click", function(){
			document.querySelector(".newPassword_popup").style.display = "none"
		})
//		new password functionality starts here

	</script>
</body>
</html>