package com.thushar.todoWebApp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thushar.todoWebApp.DAO.CompletedTaskDAO;
import com.thushar.todoWebApp.DAO.TaskDAO;
import com.thushar.todoWebApp.entities.CompletedTask;
import com.thushar.todoWebApp.entities.User;

/**
 * Servlet implementation class IncompleteTask
 */

@WebServlet("/incompleteTask")
public class IncompleteTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("presentUserDetails");
		
		int userId = user.getUserId();
		
		TaskDAO taskDAO = new TaskDAO();
		CompletedTaskDAO ctDAO = new CompletedTaskDAO();
		
		List<CompletedTask> completedTaskList = ctDAO.getAllCompletedTask(userId);
		
		taskDAO.addAllTask(completedTaskList);
		
		ctDAO.deleteCompletedTaskByUserId(userId);
		
		response.sendRedirect("index.jsp");
		
	}

}
