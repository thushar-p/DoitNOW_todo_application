package com.thushar.todoWebApp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thushar.todoWebApp.DAO.CompletedTaskDAO;

@WebServlet("/taskNotFinished")
public class TaskNotFinishedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	int taskId = Integer.parseInt(request.getParameter("id"));
    	
    	CompletedTaskDAO completedTaskDAO = new CompletedTaskDAO();
    	
    	boolean finish = completedTaskDAO.unfinishedTask(taskId);
    	
    	if(finish) {
    		response.sendRedirect("index.jsp");
    	}
    	else {
    		// display error page saying something went wrong due to techincal problems
    	}
    	
	}

}
