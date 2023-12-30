
// 		popup show function for the delete confirmation message starts here
		document.getElementById("deleteButton").addEventListener("click", function(){
			document.querySelector(".popup").style.display = "flex"
		});
		
		
		document.querySelector(".close").addEventListener("click", function(){
			document.querySelector(".popup").style.display = "none"
		});
//	 	popup show function for the delete confirmation message ends here





// 		when clicked on delete button it should popup the delete popup window starts here
		document.getElementById("deleteConfirmationButton").addEventListener("click", function(){
			document.querySelector(".popup").style.display = "none";
			document.querySelector(".deleteConfirmation").style.display = "flex";
		});
		
		
		document.getElementById("closePasswordBox").addEventListener("click", function(){
			document.querySelector(".deleteConfirmation").style.display = "none"
			document.querySelector(".popup").style.display = "none"
		});
// 		when clicked on delete button it should popup the delete popup window ends here




// 		show password function for the password confirmation for delete popup for the input field starts here
		let password = document.getElementById("password");

		function show_password(){
	    if(password.type == "password"){
	        password.type = "text";
	    }
	    else{
	        password.type = "password";
	    }
	}
//		 show password function for the password confirmation for delete popup for the input field ends here
	
	
	
	// 		show password function for the password confirmation for edit popup for  the input field starts here
		let passwordEdit = document.getElementById("passwordEdit");

		function show_passwordEdit(){
	    if(passwordEdit.type == "password"){
	        passwordEdit.type = "text";
	    }
	    else{
	        passwordEdit.type = "password";
	    }
	}
//		 show password function for the password confirmation for edit popup for the input field ends here
	
	
	
	
	
// 		edit confirmation popup window function starts here
		document.getElementById("editbtn").addEventListener("click",function(){
			document.querySelector(".editConfirmation").style.display = "flex"
		})

		document.getElementById("exit").addEventListener("click", function(){
			document.querySelector(".editConfirmation").style.display = "none"
		})

//		 edit confirmation popup window function ends here




	
//		popup show function for the forgot password popup message starts here
		document.getElementById("forgotPasswordEdit").addEventListener("click", function(){
			document.querySelector(".editConfirmation").style.display = "none"
			document.querySelector(".forgotPassword_popup").style.display = "flex"
		});
		
		document.getElementById("forgotPasswordDelete").addEventListener("click", function(){
			document.querySelector(".deleteConfirmation").style.display = "none";
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

