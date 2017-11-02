package database;

import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

public class MySQLAccess {
	
	// JDBC driver name and database URL
   	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   	static final String DB_URL = "jdbc:mysql://localhost/minisis";

   	//  Database credentials
   	static final String USER = "root";
   	static final String PASS = "123456";
	
	public Connection getConnection(){
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return connection;
		
	}
	
	public void closeConnection(Connection connection){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
