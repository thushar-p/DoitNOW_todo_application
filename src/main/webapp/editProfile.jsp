<%@page import="com.thushar.todoWebApp.entities.Message"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <%@ page import="com.thushar.todoWebApp.entities.User" %>

        <%
        /* * accepts the data which is sent from the loginServlet and stored in respective var. */ 
        User user=(User) session.getAttribute("presentUserDetails");
        if (user==null) {
            response.sendRedirect("loginPage.jsp"); 
            } 
            %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit | Profile</title>
<!-- <link rel="stylesheet" href="editProfile.css"> -->
<link rel="icon" href="images/settingFavicon.png" type="image/x-icon">

<style type="text/css">
	<%@ include file="editProfile.css" %>
</style>

</head>
<body>
    <main>	
		
		<%
		
// 		used to show the error message when trying to save with the contact number which is already present in the database
		
			Message msg2 = (Message)session.getAttribute("duplicateContact");
			if(msg2 != null){
		%>
			<div id="del"><%= msg2.getMessage() %></div>
		<%
			}
			session.removeAttribute("duplicateContact");
		%>
    
    <h2>Please fill the complete form</h2>
        <form action="editServlet" method="POST">
            <table>
                <tr>
                    <td><label for="name">Name<span>*</span></label></td>
                    <td><input type="text" id="name" name="name" value="<%= user.getUserName() %>" required></td>
                </tr>
                <tr>
                    <td><label for="email">Email - (non editable)</label></td>
                    <td><input type="email" id="email" name="email" readonly value="<%= user.getUserEmail() %>"></td>
                </tr>
                <tr>
                    <td><label for="contact">Contact<span>*</span></label></td>
                    <td><input type="tel" id="contact" name="contact" pattern="[0-9]{10}" required value="<%= user.getUserContact() %>"></td>
                </tr>
                <tr>
                    <td><label for="password">New password<span>*</span></label></td>
                    <td><input type="password" id="password" name="password" required value=<%= user.getUserPassword() %>></td>
                </tr>
                <tr>
                    <td><label for="confirmPassword">Confirm Password<span>*</span></label></td>
                    <td><input type="password" id="confirmPassword" required name="confirmPassword" value=<%= user.getUserPassword() %>></td>
                </tr>
                <tr>
					<td colspan="2" id="check"><input type="checkbox" id="checkbox"
						onclick="show_password()"><label for="checkbox">
							Show password</label></td>
				</tr>
				<tr>
					<td colspan="2" id="passwordErrorMessage"></td>
				</tr>
                <tr>
                    <td colspan="2">
                    <input type="button" id="goBack" value="Go back">
                    <input type="submit" id="submitButton" value="Save">
                    <input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form>
    </main>
    
    
    <script>
	
	 let password = document.getElementById("password");
	 let confirmPassword = document.getElementById("confirmPassword");
	 let passwordErrorMessage = document.getElementById("passwordErrorMessage");
	 let submitButton = document.getElementById("submitButton");
	 
	 
	 
// 	 function starts here
// 	 when i click to goBack button it should return to the profile page
	document.getElementById("goBack").addEventListener("click" , function(){
		window.open("profile.jsp","_self");
	})
// 	 function ends here	 
	 
	
	// 	 function starts here
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
//	 function ends here




// 	 function starts here
	//function which is used to display the error message about confirm password when password and confirm password are not matching
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
	// 	 function ends here
	
	
	</script>
    
</body>
</html>