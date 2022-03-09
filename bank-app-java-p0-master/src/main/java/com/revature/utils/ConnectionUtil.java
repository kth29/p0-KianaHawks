package com.revature.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Singleton utility for creating and retrieving database connection
 */
public class ConnectionUtil {
	private static ConnectionUtil cu = null;
	private static Properties prop;

	/**
	 * This method should read in the "database.properties" file and load the values
	 * into the Properties variable
	 */
	private ConnectionUtil() {
	}

	public static synchronized ConnectionUtil getConnectionUtil() {
		if (cu == null)
			cu = new ConnectionUtil();
		return cu;
	}

	/**
	 * This method should create and return a Connection object
	 * 
	 * @return a Connection to the database
	 */
	public static Connection getConnection() {
		// Hint: use the Properties variable to setup your Connection object
		try {
			try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/p0mac", prop)) {
			}

			FileInputStream fs = new FileInputStream("/bank-app-java-p0-master/src/main/resources/database.properties");
			prop.load(fs);

			String url = prop.getProperty("url");
			String usr = prop.getProperty("usr");
			String pswd = prop.getProperty("pswd");
			url = System.getenv(url);
			usr = System.clearProperty(usr);
			pswd = System.getenv(pswd);

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
