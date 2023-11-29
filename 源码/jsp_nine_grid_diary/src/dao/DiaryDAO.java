package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Diary;
import tools.ConnDB;

public class DiaryDAO {
	ConnDB conn;

	public DiaryDAO() {
		conn = new ConnDB();
	}

	public int InsertDiary(String title, int userid, String username) {
		String sql = "insert into diary values(null,'" + title + "', now()," + userid + ",'" + username + "')";
		return conn.executeUpdate(sql);
	}

	public ArrayList<Diary> SelectAllDiary() {
		String sql = "select * from diary order by id desc";
		ResultSet rs = conn.executeQuery(sql);
		ArrayList<Diary> diaries = new ArrayList<Diary>();
		try {
			while (rs.next()) {
				Diary diary = new Diary();
				diary.setId(rs.getInt(1));
				diary.setTitle(rs.getString(2));
				diary.setTime(rs.getString(3));
				diary.setUserid(rs.getInt(4));
				diary.setUsername(rs.getString("username"));
				diaries.add(diary);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return diaries;
	}

	public ArrayList<Diary> SelectDiary(int userid) {
		String sql = "select * from diary where userid=" + userid + " order by id desc";
		ResultSet rs = conn.executeQuery(sql);
		ArrayList<Diary> diaries = new ArrayList<Diary>();
		try {
			while (rs.next()) {
				Diary diary = new Diary();
				diary.setId(rs.getInt(1));
				diary.setTitle(rs.getString(2));
				diary.setTime(rs.getString(3));
				diary.setUserid(rs.getInt(4));
				diary.setUsername(rs.getString("username"));
				diaries.add(diary);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return diaries;
	}

	public int DeleteDiary(int id) {
		String sql = "delete from diary where id=" + id;
		return conn.executeUpdate(sql);
	}
}
