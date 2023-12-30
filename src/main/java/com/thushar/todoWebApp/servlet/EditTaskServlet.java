package com.thushar.todoWebApp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thushar.todoWebApp.DAO.TaskDAO;
import com.thushar.todoWebApp.entities.Task;

/**
 * Servlet implementation class EditTaskServlet
 */
@WebServlet("/editTaskServlet")
public class EditTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int taskId = Integer.parseInt(request.getParameter("id"));
		
		System.out.println(taskId);
		
		if(taskId != 0) {
			
			String editedTaskDetails = request.getParameter("editedTask");
			String editedDueTime = request.getParameter("dueTime");
			String editedDueDate = request.getParameter("dueDate");
			
			Task editedTask = new Task(editedTaskDetails, editedDueDate, editedDueTime);
			
			TaskDAO taskDAO = new TaskDAO();
			boolean updated = taskDAO.editExisitingTaskById(editedTask,taskId);
			if(updated) {
				response.sendRedirect("index.jsp");
			}
			else {
				// couldn't get edited error message
			}
			
		}
		
	}

}
