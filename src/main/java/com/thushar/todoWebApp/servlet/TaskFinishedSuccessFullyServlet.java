package com.thushar.todoWebApp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thushar.todoWebApp.DAO.CompletedTaskDAO;


@WebServlet("/taskFinishedSuccessfully")
public class TaskFinishedSuccessFullyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("1 . inside doPost method");
		
		int taskId = Integer.parseInt(request.getParameter("id"));


		if(taskId != 0) {
			CompletedTaskDAO ctDAO = new CompletedTaskDAO();
			
			System.out.println(2);
			boolean success = ctDAO.addCompletedTask(taskId);
			
			System.out.println("3"+success);
			
			if(success) {
				System.out.println("inside success if");
				response.sendRedirect("index.jsp");
			}
			else {
				// display error message saying task couldn't be completed due to the technical error
			}
		}

	}

}
