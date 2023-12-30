package com.thushar.todoWebApp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thushar.todoWebApp.entities.Message;

/*
this servlet is used to delete the httpSession and logout the user(by clearing the httpSession)
*/
@WebServlet("/logoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.removeAttribute("presentUserDetails");

		  Message logoutMessage = new Message("Logged out successfully", "success");
		  
		  session.setAttribute("logoutMessage", logoutMessage);
		  response.sendRedirect("loginPage.jsp");
		 

	}

}
