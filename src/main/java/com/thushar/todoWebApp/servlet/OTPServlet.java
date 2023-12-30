package com.thushar.todoWebApp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thushar.todoWebApp.entities.Message;

/**
 * Servlet implementation class OTPServlet
 */
@WebServlet("/otp")
public class OTPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String header = request.getHeader("Referer");
		
		HttpSession session = request.getSession();
		String otpString = request.getParameter("otp");
		System.out.println(otpString+" hello");
		if(otpString.equals("")) {
			
			// display error message saying that
			// something went wrong please try again
			response.sendRedirect("errorPage.jsp");
			
		}
		else {
			int otpUserEntered = Integer.parseInt(otpString);
			

			Integer otpGenerated = (Integer)session.getAttribute("transferedOTP");

			System.out.println(otpGenerated);

			if(otpGenerated == otpUserEntered) {
				Message msg = new Message("otpSuccess", "succcess");
				session.setAttribute("otpSuccess", msg);
				response.sendRedirect(header);
			}
			else {
				Message  msg = new Message("The OTP you entered is not matching, Try again", "error");
				System.out.println("else");
				session.setAttribute("otpEnteredWrong", msg);
				response.sendRedirect(header);
			}
		}

	}

}
