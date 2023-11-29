package dao;

import java.sql.ResultSet;

import tools.ConnDB;

public class UserDAO {
	ConnDB conn;

	public UserDAO() {
		conn = new ConnDB();
	}
	
	public int InsertUser(String username, String password, String email) {
		String sql = "insert into user values(null,'" + username + "','" + password + "','" + email + "')";
		return conn.executeUpdate(sql);
	}

	public ResultSet SelectUser(int id) {
		String sql = "select * from user where id='" + id + "'";
		return conn.executeQuery(sql);
	}
	
	public ResultSet SelectUser(String name) {
		String sql = "select * from user where username='" + name + "'";
		return conn.executeQuery(sql);
	}

	public int UpdateUser(String id, String username, String password, String email) {
		String sql = "update user set uesrname=" + username + ",password=" + password + ",email=" + email
				+ " where id=" + id;
		return conn.executeUpdate(sql);
	}
}
