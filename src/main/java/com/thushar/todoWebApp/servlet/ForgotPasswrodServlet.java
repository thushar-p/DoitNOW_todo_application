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
import com.thushar.todoWebApp.helper.OTPGenerator;

/**
 * Servlet implementation class ForgotPasswrodServlet
 */

@WebServlet("/forgotPassword")
public class ForgotPasswrodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userEmail = request.getParameter("email");
		
		String header = request.getHeader("Referer");
		
		HttpSession session = request.getSession();
		
		
		UserDAO userDAO = new UserDAO();
		String userIdFromDB = userDAO.getUserIdFromUser(userEmail);
		
		if(userIdFromDB == null) {
			Message msg = new Message("The email you entered is incorrect", "error");
			session.setAttribute("incorrectEmailAddress", msg);
			response.sendRedirect(header);
		}
		else {
			
				session.setAttribute("userEmailSession", userEmail);
				OTPGenerator otpGenerator = new OTPGenerator();
				int otp = otpGenerator.sendOTPToEmail(userEmail);
				session.setAttribute("generatedOTP", 123456);
				response.sendRedirect(header);
		}
	
	}
}
