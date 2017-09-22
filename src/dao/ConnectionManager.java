package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {

	public static Connection cnx = null;
	public static String driver = "oracle.jdbc.driver.OracleDriver";
	public static String url = "jdbc:oracle:thin:@srvdlo:1521:dl1";//Changer @localhost:1521:xe | @srvdlo:1521:dl1
	public static String user = "filrouge101";//Changer dl101 | filrouge101 
	public static String password = "afpa";

	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName(driver);

		//Singleton
		if (cnx == null)
			cnx = DriverManager.getConnection(url, user, password);

		return cnx;
	}

	public static void closeConnection() throws SQLException{
		cnx.close();
	}
}
