package com.thushar.todoWebApp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thushar.todoWebApp.DAO.UserDAO;
import com.thushar.todoWebApp.entities.User;


/*
login servlet which is used to accept the values from the UI and pass it into the DAO class and verify it
*/

@WebServlet("/logInServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");

		String email = request.getParameter("email");
		String password = request.getParameter("password");

//		PrintWriter out = response.getWriter();

		UserDAO userDAO = new UserDAO();

		User user = userDAO.getDetailsOnEmailAndPassword(email, password);
		

		if (user == null) {
//			System.out.println(user + "inside if block");
			
			// display a proper error page regarding on user not available and inform them
			// to register first
//			out.println("User not available! Register first");
			response.sendRedirect("wrongPassword.jsp");
		} else {

			
//			  session object which is used to store the user details and pass it to the
//			  server
			HttpSession session = request.getSession();
			session.setAttribute("presentUserDetails", user);
			response.sendRedirect("index.jsp");

		}
	}

}
