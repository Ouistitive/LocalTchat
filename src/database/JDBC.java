package database;

import java.sql.*;

public class JDBC {
	private static final String url = "jdbc:mysql://localhost:3306/Tchat"; // URL of the table
	private static final String username = "root"; // User to connect in the database
	private static final String password = "root"; // Password to connect in the database
	private static Connection connect;
	private static Statement stmt;
	
	/**
	 * @brief Connect to the database Tchat with root as username and password
	 */
	public static void connection() {
	    try
	    {
	      connect = DriverManager.getConnection(url, username, password);
	      stmt = connect.createStatement();
	      
	    }
	    catch(Exception e){ 
	    	System.err.println(e);
	    }
	}
	
	/**
	 * @brief Send a query to the database
	 * @param query : the query SQL to execute
	 * @return the result
	 */
	public static ResultSet sendQuery(String query) {
		ResultSet res = null;
		try {
			res = stmt.executeQuery(query);
			while(res.next())
				System.out.println(res.getInt(1)+"  "+res.getString(2) + "  " + res.getString(3));
		} catch (SQLException e) {
			System.err.println(e);
		}
		return res;
	}
	
	/**
	 * @brief close the database
	 */
	public static void closeDatabase() {
		try {
			connect.close();
		} catch (SQLException e) {
			System.err.println(e);
		}
	}
}
