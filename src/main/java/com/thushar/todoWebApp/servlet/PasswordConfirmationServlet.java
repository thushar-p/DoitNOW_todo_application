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
	this servlet is used to confrim the password when editing the profile
*/
@WebServlet("/confirmPasswordServlet")
public class PasswordConfirmationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("presentUserDetails");
		
		System.out.println("in the servlet");
		
		String email = user.getUserEmail();
		String password = user.getUserPassword();
		
		UserDAO userDAO = new UserDAO();
		User userFromDatabase = userDAO.getDetailsOnEmailAndPassword(email, password);
		
		String passwordFromForm = request.getParameter("password");
		
		if(userFromDatabase != null) {
			
			System.out.println("outer if");
			String passwordFromDatabase = userFromDatabase.getUserPassword();
			if(passwordFromDatabase.equals(passwordFromForm)) {
				System.out.println("inner if");
				response.sendRedirect("editProfile.jsp");
				
			}
			else {
				
				Message msg = new Message("The password you entered is wrong", "error");
				session.setAttribute("passwordConfirmationWrong", msg);
				System.out.println("inner else");
				response.sendRedirect("profile.jsp");
			}
		}
		else {
			System.out.println("outer else");
			response.sendRedirect("errorPage.jsp");
		}
		
	}

}
