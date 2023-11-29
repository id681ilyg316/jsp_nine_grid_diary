package tools;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnDB {
	public Connection conn = null;
	public Statement stmt = null;
	public ResultSet rs = null;
	private static String propFileName = "connDB.properties";
	private static Properties prop = new Properties();

	private static String dbClassname;
	private static String dbURL;

	public ConnDB() {
		try {
			InputStream in = getClass().getResourceAsStream(propFileName);
			prop.load(in);
			dbClassname = prop.getProperty("DB_CLASS_NAME");
			dbURL = prop.getProperty("DB_URL");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		try {
			Class.forName(dbClassname);
			conn = DriverManager.getConnection(dbURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public ResultSet executeQuery(String sql) {
		if (conn == null)
			conn = getConnection();
		try {
			if (stmt == null)
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return rs;
	}
	
	public int executeUpdate(String sql){
		int result = 0;
		if (conn == null)
			conn = getConnection();
		try {
			if (stmt == null)
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return result;
	}
	
	public void close(){
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
