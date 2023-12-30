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


@WebServlet("/forgetPasswordFromLogin")
public class ForgotPasswordFromLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String header = request.getHeader("Referer");
		
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("userEmailSession");
		session.removeAttribute("userEmailSession");
		
		String updatedPassword = request.getParameter("newPassword");
		
		
		UserDAO userDAO = new UserDAO();
		boolean result = userDAO.changePassword(email, updatedPassword);
		if(result) {
			Message msg = new Message("Successfully updated your new password", "success");
			session.setAttribute("passwordUpdationSuccess", msg);
			response.sendRedirect(header);
		}
		else {
			Message msg = new Message("Sorry! Something went wrong", "error");
			session.setAttribute("passwordUpdationFailed", msg);
			response.sendRedirect(header);
		}
		
	}

}
