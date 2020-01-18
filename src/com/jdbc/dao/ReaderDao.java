package com.jdbc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javabean.Reader;
import com.jdbc.utils.JDBCUtils;

public class ReaderDao {
	public boolean insert(Reader reader) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "INSERT INTO reader(id,password,name,sex,birthday,major,grade,classId,borrowedNum,contact,remarks) " +
			"VALUES('" + reader.getId() + "','" + reader.getPassword() + "','" + reader.getName() 
			+ "','" + reader.getSex() + "','" + reader.getBirthday() + "','" + reader.getMajor() 
			+ "'," + reader.getGrade() + ",'" + reader.getClassId() + "'," + reader.getBorrowedNum() 
			+ ",'" + reader.getContact() + "','" + reader.getRemarks() + "')";
			int num = stmt.executeUpdate(sql);
			if (num > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return false;
	}

	public ArrayList<Reader> findAll() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Reader> list = new ArrayList<Reader>();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM reader";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Reader reader = new Reader();
				reader.setId(rs.getString("id"));
				reader.setPassword(rs.getString("password"));
				reader.setName(rs.getString("name"));
				reader.setSex(rs.getString("sex").charAt(0));
				reader.setBirthday(rs.getString("birthday"));
				reader.setMajor(rs.getString("major"));
				reader.setGrade(rs.getInt("grade"));
				reader.setClassId(rs.getString("classId"));
				reader.setBorrowedNum(rs.getInt("borrowedNum"));
				reader.setContact(rs.getString("contact"));
				reader.setRemarks(rs.getString("remarks"));
				list.add(reader);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return null;
	}
	
	public ArrayList<Reader> search(String search_type, String keyword) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Reader> list = new ArrayList<Reader>();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM reader WHERE " + search_type + " LIKE '%" + keyword + "%'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Reader reader = new Reader();
				reader.setId(rs.getString("id"));
				reader.setPassword(rs.getString("password"));
				reader.setName(rs.getString("name"));
				reader.setSex(rs.getString("sex").charAt(0));
				reader.setBirthday(rs.getString("birthday"));
				reader.setMajor(rs.getString("major"));
				reader.setGrade(rs.getInt("grade"));
				reader.setClassId(rs.getString("classId"));
				reader.setBorrowedNum(rs.getInt("borrowedNum"));
				reader.setContact(rs.getString("contact"));
				reader.setRemarks(rs.getString("remarks"));
				list.add(reader);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return null;
	}

	public Reader findById(String id) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM reader WHERE id='" + id + "'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Reader reader = new Reader();
				reader.setId(rs.getString("id"));
				reader.setPassword(rs.getString("password"));
				reader.setName(rs.getString("name"));
				reader.setSex(rs.getString("sex").charAt(0));
				reader.setBirthday(rs.getString("birthday"));
				reader.setMajor(rs.getString("major"));
				reader.setGrade(rs.getInt("grade"));
				reader.setClassId(rs.getString("classId"));
				reader.setBorrowedNum(rs.getInt("borrowedNum"));
				reader.setContact(rs.getString("contact"));
				reader.setRemarks(rs.getString("remarks"));
				return reader;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return null;
	}
	
	public ArrayList<Reader> findByName(String name) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Reader> list = new ArrayList<Reader>();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM reader WHERE name='" + name + "'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Reader reader = new Reader();
				reader.setId(rs.getString("id"));
				reader.setPassword(rs.getString("password"));
				reader.setName(rs.getString("name"));
				reader.setSex(rs.getString("sex").charAt(0));
				reader.setBirthday(rs.getString("birthday"));
				reader.setMajor(rs.getString("major"));
				reader.setGrade(rs.getInt("grade"));
				reader.setClassId(rs.getString("classId"));
				reader.setBorrowedNum(rs.getInt("borrowedNum"));
				reader.setContact(rs.getString("contact"));
				reader.setRemarks(rs.getString("remarks"));
				list.add(reader);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return null;
	}

	public boolean delete(String id) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "DELETE FROM reader WHERE id='" + id + "'";
			int num = stmt.executeUpdate(sql);
			if (num > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return false;
	}

	public boolean update(Reader reader) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "UPDATE reader set password='" + reader.getPassword() + "',name='"
					+ reader.getName() + "',sex='" + reader.getSex() + "',birthday='"
					+ reader.getBirthday() + "',major='" + reader.getMajor() + "',grade=" + reader.getGrade() 
					+ ",classId='" + reader.getClassId() + "',borrowedNum=" + reader.getBorrowedNum() 
					+ ",contact='" + reader.getContact() + "',remarks='" + reader.getRemarks() + "' WHERE id='" + reader.getId() + "'";
			int num = stmt.executeUpdate(sql);
			if (num > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return false;
	}
}
