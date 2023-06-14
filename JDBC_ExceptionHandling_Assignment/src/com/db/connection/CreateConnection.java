package com.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class CreateConnection {
	Connection cp;
	public Connection create() {
		try {
			// Load the driver
			Class.forName("com.mysql.jdbc.Driver");
			//create connection
			cp = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcexample", "root", "root");
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return cp;
	}
}
