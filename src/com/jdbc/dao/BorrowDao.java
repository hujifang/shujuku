package com.jdbc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javabean.Borrow;
import com.jdbc.utils.JDBCUtils;

public class BorrowDao {
	public boolean insert(Borrow borrow) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "INSERT INTO borrow(readerId,isbn,borrowDate,returnDate) " +
			"VALUES('" + borrow.getReaderId() + "','" + borrow.getISBN() + "','" + borrow.getBorrowDate() 
			+ "','" + borrow.getReturnDate() + "')";
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
	
	public ArrayList<Borrow> findAll() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Borrow> list = new ArrayList<Borrow>();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM borrow";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Borrow borrow = new Borrow();
				borrow.setReaderId(rs.getString("readerId"));
				borrow.setISBN(rs.getString("isbn"));
				borrow.setBorrowDate(rs.getString("borrowDate"));
				borrow.setReturnDate(rs.getString("returnDate"));
				list.add(borrow);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return null;
	}
	
	public ArrayList<Borrow> search(String search_type, String keyword) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Borrow> list = new ArrayList<Borrow>();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM borrow WHERE " + search_type + " LIKE '%" + keyword + "%'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Borrow borrow = new Borrow();
				borrow.setReaderId(rs.getString("readerId"));
				borrow.setISBN(rs.getString("isbn"));
				borrow.setBorrowDate(rs.getString("borrowDate"));
				borrow.setReturnDate(rs.getString("returnDate"));
				list.add(borrow);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return null;
	}
	
	public ArrayList<Borrow> findById(String readerId) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Borrow> list = new ArrayList<Borrow>();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM borrow WHERE readerId='" + readerId + "'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Borrow borrow = new Borrow();
				borrow.setReaderId(rs.getString("readerId"));
				borrow.setISBN(rs.getString("isbn"));
				borrow.setBorrowDate(rs.getString("borrowDate"));
				borrow.setReturnDate(rs.getString("returnDate"));
				list.add(borrow);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return null;
	}
	
	public Borrow findByISBN(String readerId,String isbn) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM borrow WHERE readerId='" + readerId + "' AND isbn='" + isbn + "'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Borrow borrow = new Borrow();
				borrow.setReaderId(rs.getString("readerId"));
				borrow.setISBN(rs.getString("isbn"));
				borrow.setBorrowDate(rs.getString("borrowDate"));
				borrow.setReturnDate(rs.getString("returnDate"));
				return borrow;
			}
			return null;
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
			String sql = "DELETE FROM borrow WHERE readerId='" + readerId + "' AND isbn='" + isbn + "'";
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
