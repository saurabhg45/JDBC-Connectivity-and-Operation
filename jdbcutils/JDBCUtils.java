package jdbcutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/articles";
		String uid = "root";
		String pwd = "saurabh";
		
		Connection dbConnection = DriverManager.getConnection(url,uid,pwd);
		return dbConnection;
		
	}
}
