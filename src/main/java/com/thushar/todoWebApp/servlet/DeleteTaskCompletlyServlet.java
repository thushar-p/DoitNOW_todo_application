package com.thushar.todoWebApp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thushar.todoWebApp.DAO.TaskDAO;

@WebServlet("/deleteTaskComplete")
public class DeleteTaskCompletlyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int taskId = Integer.parseInt(request.getParameter("id"));
		
		if(taskId != 0) {
			TaskDAO taskDAO = new TaskDAO();
			boolean update = taskDAO.deleteTaskByTaskId(taskId);
			if(update) {
				response.sendRedirect("index.jsp");
			}
			else {
				// display error message saying cannot delete task
			}
		}
		
	}

}
