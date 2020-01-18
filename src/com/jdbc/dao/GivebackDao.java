package com.jdbc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javabean.Giveback;
import com.jdbc.utils.JDBCUtils;

public class GivebackDao {
	public boolean insert(Giveback giveback) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "INSERT INTO giveback(readerId,isbn,borrowDate,returnDate) " +
			"VALUES('" + giveback.getReaderId() + "','" + giveback.getISBN() + "','" + giveback.getBorrowDate() 
			+ "','" + giveback.getReturnDate() + "')";
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
	
	public ArrayList<Giveback> findAll() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Giveback> list = new ArrayList<Giveback>();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM giveback";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Giveback giveback = new Giveback();
				giveback.setReaderId(rs.getString("readerId"));
				giveback.setISBN(rs.getString("isbn"));
				giveback.setBorrowDate(rs.getString("borrowDate"));
				giveback.setReturnDate(rs.getString("returnDate"));
				list.add(giveback);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return null;
	}
	
	public ArrayList<Giveback> search(String search_type, String keyword) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Giveback> list = new ArrayList<Giveback>();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM giveback WHERE " + search_type + " LIKE '%" + keyword + "%'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Giveback giveback = new Giveback();
				giveback.setReaderId(rs.getString("readerId"));
				giveback.setISBN(rs.getString("isbn"));
				giveback.setBorrowDate(rs.getString("borrowDate"));
				giveback.setReturnDate(rs.getString("returnDate"));
				list.add(giveback);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return null;
	}
	
	public ArrayList<Giveback> findById(String readerId) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Giveback> list = new ArrayList<Giveback>();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM giveback WHERE readerId='" + readerId + "'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Giveback giveback = new Giveback();
				giveback.setReaderId(rs.getString("readerId"));
				giveback.setISBN(rs.getString("isbn"));
				giveback.setBorrowDate(rs.getString("borrowDate"));
				giveback.setReturnDate(rs.getString("returnDate"));
				list.add(giveback);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return null;
	}
	
	public boolean delete(String readerId,String isbn) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "DELETE FROM giveback WHERE readerId='" + readerId + "' AND isbn='" + isbn + "'";
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
