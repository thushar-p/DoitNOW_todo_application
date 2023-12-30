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
register servlet which is used to take the inputs from the UI and store it inside the database.
 */
@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//		response.setContentType("text/html");

		HttpSession session = request.getSession();

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		long contact = Long.parseLong(request.getParameter("phoneNumber"));
		String password = request.getParameter("password");
		String strContact = Long.toString(contact);

		if(name == null || email == null || password == null) {
			// show the error message
		}
		else {
			UserDAO userDAO = new UserDAO();
			User user = new User(name, email, contact, password);
			String addedOrNot = userDAO.addUser(user);

			if(addedOrNot.equals("true")) {
				response.sendRedirect("loginPage.jsp");
			}
			else {
				if(addedOrNot.contains(email)) {
					System.out.println("contains email error");
					Message msg = new Message("The email you entered "+email+" is already present, try with another.", "error");
					session.setAttribute("duplicateEmail", msg);
					response.sendRedirect("registerPage.jsp");
				}
				else if(addedOrNot.contains(strContact)) {
					System.out.println("contains contact error");
					Message msg = new Message("The phone number you entered "+contact+" is already present, try with another.", "error");
					session.setAttribute("duplicateContact", msg);
					response.sendRedirect("registerPage.jsp");
				}
			}
		}
	}
}
