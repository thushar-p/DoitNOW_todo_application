<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="com.thushar.todoWebApp.entities.User"%>
<%@ page import="com.thushar.todoWebApp.entities.Message"%>
<%@ page errorPage="errorPage.jsp"%>

<%
/* * accepts the data which is sent from the loginServlet and stored in respective var. */
User user = (User) session.getAttribute("presentUserDetails");
if (user == null) {
	response.sendRedirect("loginPage.jsp");
}



Message otpMessage = (Message)session.getAttribute("otpEnteredWrong");
%>






<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Profile | Do it NOW</title>
<!-- <link rel="stylesheet" href="profile.css"> -->
<link rel="icon" href="images/profileFavicon.png" type="image/x-icon">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
	integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />

<style>
     <%@ include file="profile.css"%>
</style>

</head>

<body>
	<header>
		<a href="index.jsp"><button>Home</button></a>
		<div id="first">
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
			<img src="images/settingImage.svg" alt="settingImage">
		</div>
		<div id="textBlock">
		
			<%
			
// 			this tag is used to get invoked when we cannot delete our account for some unknown reason
			
			Message msg = (Message) session.getAttribute("UnableToDeleteAccount");
			if (msg != null) {
			%>
			<div id="unableToDel"><%=msg.getMessage()%></div>
			<%
			}
			session.removeAttribute("UnableToDeleteAccount");
			
			%>
			
			<%
			
// 			this tag is used to get invoked when the password confirmation goes wrong
			
			Message msg4 = (Message) session.getAttribute("passwordConfirmationWrong");
			if (msg4 != null) {
			%>
			<div id="unableToDel"><%=msg4.getMessage()%></div>
			<%
			}
			session.removeAttribute("passwordConfirmationWrong");
			
			%>
			
			<%
			
// 			this tag is used to get invoked when the updating profile couldnt get completed for some reason

			Message msg1 = (Message) session.getAttribute("profileCannotUpdate");
			if (msg1 != null) {
			%>
			<div id="updateFailed"><%=msg1.getMessage()%></div>
			<%
			}
			session.removeAttribute("profileCannotUpdate");
			%>
			
			<%
			
// 			this tag will get invoked when the profil gets updated successfully
			
			Message msg2 = (Message) session.getAttribute("updatedSuccessfully");
			if (msg2 != null) {
			%>
			<div id="updateSuccess"><%=msg2.getMessage()%></div>
			<%
			}
			session.removeAttribute("updatedSuccessfully");
			
			%>
			
			<%
			
// 			this tag will get invoked when the user entered wrong password for account deletion
			
			Message msg3 = (Message) session.getAttribute("wrongPasswordForDeletion");
			if (msg3 != null) {
			%>
			<div id="updateSuccess"><%=msg3.getMessage()%></div>
			<%
			}
			session.removeAttribute("wrongPasswordForDeletion");
			
			%>
			
			
			<%
			
// 			this tag will get invoked when the user entered wrong email to send the OTP
			
			Message msg5 = (Message) session.getAttribute("incorrectEmailAddress");
			if (msg5 != null) {
			%>
			<div id="updateSuccess"><%=msg5.getMessage()%></div>
			<%
			}
			session.removeAttribute("incorrectEmailAddress");
			
			%>
			
			
			
			<%
			
// 			this tag will get invoked when the user updated with new password

			Message msg6 = (Message) session.getAttribute("passwordUpdationSuccess");
			if (msg6 != null) {
			%>
			<div id="updateSuccess"><%=msg6.getMessage()%></div>
			<%
			}
			session.removeAttribute("passwordUpdationSuccess");
			
			%>
			
			
			<%
			
// 			this tag will get invoked when the user's new passwod updation failed
			
			Message msg7 = (Message) session.getAttribute("passwordUpdationFailed");
			if (msg7 != null) {
			%>
			<div id="updateSuccess"><%=msg7.getMessage()%></div>
			<%
			}
			session.removeAttribute("passwordUpdationFailed");
			
			%>
			
			
			<form>
				<ul>
					<li>
						<h2>Profile Details</h2>
					</li>
					<li>
						<p>
							Name : <span> <%=user.getUserName()%>
							</span>
						</p>
					</li>
					<li>
						<p>
							Email : <span> <%=user.getUserEmail()%>
							</span>
						</p>
					</li>
					<li>
						<p>
							Contact : <span> <%=user.getUserContact()%>
							</span>
						</p>
					</li>
					<li>
						<p>
							Password : <span>********</span>
						</p>
					</li>
				</ul>
			</form>
			<Button id="editbtn">Edit</Button>
			<Button id="deleteButton">Delete Account</Button>
		</div>
		
		
<!-- 		popup window for delete confirmation starts here -->
		<div class="popup">
			<div class="popup_content">
				<h2>Are you sure you want to delete your Account? or do you want to logout?</h2>
				<div class="option">
					<a href=""><button class="close">Close</button></a>
					<a id="deleteConfirmationButton"><button>Delete Account</button></a>
					<a href="logoutServlet"><button>Logout</button></a>	
				</div>
			</div>
		</div>
<!-- 		popup window for delete confirmation ends here -->
		
<!-- 		password input window popup for delete confirmation starts here -->
		<div class="deleteConfirmation">
			<div class="deleteConfirmation_content">
				<form action="deleteAccount" method="POST">
					<h2>Enter your old password</h2>
					<table>
						<tr>
							<td><input type="password" id="password" name="password" autofocus></td>
						</tr>
						<tr>
							<td colspan="2"><input type="checkbox" id="checkbox"
						onclick="show_password()"><label for="checkbox">
							Show password</label></td>
						</tr>
						<tr>
							<td>
								<input type="button" value="Close" id="closePasswordBox">
<!-- 								<button id="closePasswordBox">Close</button> -->
								<input id="forgotPasswordDelete" type="button" value="Forgot password">
								<input type="submit" value="Delete account permenently">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
<!-- 		password input window popup for delete confirmation ends here -->
		
		
		
<!-- 		password input window popup for edit confirmation to check it whether it is from the same user starts here -->
		<div class="editConfirmation">
			<div class="editConfirmation_content">
				<form action="confirmPasswordServlet" method="POST">
					<h2>Enter your old password</h2>
					<table>
						<tr>
							<td><input type="password" id="passwordEdit" name="password" autofocus></td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="checkbox" id="checkboxEdit" onclick="show_passwordEdit()">
								<label for="checkboxEdit">Show password</label>
							</td>
						</tr>
						<tr>
							<td>
								<input type="button" value="Close" id="exit">
<!-- 								<button id="exit">Close</button> -->
								<input id="forgotPasswordEdit" type="button" value="Forgot password">
								<input type="submit" value="Continue">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
<!-- 		password confirmation window popup ends here -->


<!-- 		forgot password popup starts here -->

		<div class="forgotPassword_popup">
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

			<div class="OTP_popup">
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

			<div class="newPassword_popup">
				<div class="newPassword_content">
					<form action="newPassword" method="POST">
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
		
	</main>
	
	
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
	
	<script type="text/javascript" src="profile.js"></script>
	
</body>

</html>