package edu.shawnhamilton.advancedjava;

import java.sql.*;

public class DatabaseUtils {
	
	// in a real program these values would be a configurable property and not hardcoded.
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/stocks?useTimezone=true&serverTimezone=UTC";
	
	// Database credentials
	private static final String USER = "monty";
	private static final String PASSWORD = "some_pass";
	
	public static Connection getConnection() throws DatabaseConnectionException {
	Connection connection = null;
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
	    }
	    catch (ClassNotFoundException | SQLException e) {
	    throw new DatabaseConnectionException("Could not connection to database." + e.getMessage(), e);
	    }
	    return connection;
	}
}
