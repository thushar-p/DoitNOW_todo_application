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
this servlet is used to delete account
 */
@WebServlet("/deleteAccount")
public class DeleteAccountServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("presentUserDetails");

		String userEmail = user.getUserEmail();
		String userPassword = user.getUserPassword();

		String inputPassword = request.getParameter("password");

		if(userPassword.equals(inputPassword)) {

			UserDAO userDAO = new UserDAO();

			boolean updated = userDAO.deleteAccountByEmail(userEmail);

			if(updated) {
				session.removeAttribute("presentUserDetails");
				Message msg = new Message("Account deleted successfully", "success");
				session.setAttribute("AccountDeleteSuccess", msg);
				response.sendRedirect("registerPage.jsp");
			}
			else {
				Message msg = new Message("Sorry couldn't complete the request", "error");
				session.setAttribute("UnableToDeleteAccount", msg);
				response.sendRedirect("profile.jsp");
			}
		}
		else {
			System.out.println("inside checkpoint");
			Message msg = new Message("Password you entered is not matching", "error");
			session.setAttribute("wrongPasswordForDeletion", msg);
			response.sendRedirect("profile.jsp");
		}


	}


}
