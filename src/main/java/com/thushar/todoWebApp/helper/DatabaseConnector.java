package com.thushar.todoWebApp.helper;

import java.sql.Connection;
import java.sql.DriverManager;

/*
Helper class where it provides the connection to the mysql database
*/
public class DatabaseConnector {
	
	public static Connection getConnection() {
//		Properties properties = new Properties();
//		FileReader fileReader = null;
		Connection connection = null;
		
		try {
//			fileReader = new FileReader(config/dbConfig.properties);
//			properties.load(fileReader);
			
			
//			String user = properties.getProperty("user");
//			String password = properties.getProperty("password");
//			String driver = properties.getProperty("driver");
//			String url = properties.getProperty("url");
			
//			Class.forName(driver);
//			connection = DriverManager.getConnection(url, user, password);
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "thus8848");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;
	}
}
