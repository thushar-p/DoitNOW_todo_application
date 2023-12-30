package com.thushar.todoWebApp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thushar.todoWebApp.DAO.TaskDAO;
import com.thushar.todoWebApp.entities.Message;
import com.thushar.todoWebApp.entities.Task;
import com.thushar.todoWebApp.entities.User;


/*
	AddTaskServlet servlet which is used to take the input from the user and store it to the database using 	DAO classes

	this class is used to take new task input from the user and pass it to the database
*/
@WebServlet("/addTask")
public class AddTaskServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String taskDetail = request.getParameter("taskDetail");
		String dueTime = request.getParameter("dueTime");
		String dueDate = request.getParameter("dueDate");

		HttpSession session = request.getSession();
		User userSession = (User)session.getAttribute("presentUserDetails");

		if(taskDetail == null) {
			response.sendRedirect("index.jsp");
		}
		else {
			TaskDAO taskDAO = new TaskDAO();
			Task task = new Task(taskDetail, dueDate, dueTime);
			int confirm = taskDAO.addTask(task,userSession);

			if(confirm == 1) {
				response.sendRedirect("errorPage.jsp");
			}
			else {
				Message msg = new Message("You have added a task", "success");
				session.setAttribute("succuessfullyAddedTask", msg);
				
				response.sendRedirect("addTask.jsp");
			}
		}
	}

}
