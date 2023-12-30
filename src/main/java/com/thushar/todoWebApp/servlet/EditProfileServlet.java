package com.thushar.todoWebApp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thushar.todoWebApp.DAO.UserDAO;
import com.thushar.todoWebApp.entities.Message;
import com.thushar.todoWebApp.entities.User;

/*
this servlet is invoked when we edit the profile
*/
@WebServlet("/editServlet")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userNameInput = request.getParameter("name");
		String userContactInputString = request.getParameter("contact");
		String userPasswordInput = request.getParameter("password");
		long userContactInput = Long.parseLong(userContactInputString);

		HttpSession session = request.getSession();

		User userDetails = (User)session.getAttribute("presentUserDetails");

		String userEmail = userDetails.getUserEmail();


		User user = new User(userNameInput, userEmail, userContactInput, userPasswordInput);


		UserDAO userDAO = new UserDAO();
		String updated = userDAO.updateUserDetails(user , userEmail);
		if(updated.equals("true")) {

			userDetails.setUserName(userNameInput);
			userDetails.setUserContact(userContactInput);
			userDetails.setUserPassword(userPasswordInput);


			Message msg = new Message("Profile updated successfully", "success");
			session.setAttribute("updatedSuccessfully", msg);
			System.out.println("inside else statement of editProfileServlet");
			response.sendRedirect("profile.jsp");
		}
		else {
			
			if(updated.contains(userContactInputString)) {

				System.out.println("contains contact error");
				Message msg = new Message("The contact you entered "+userContactInputString+" is already present, try with another.", "error");
				session.setAttribute("duplicateContact", msg);
				response.sendRedirect("editProfile.jsp");
				
			}
		}
		
	}

}
