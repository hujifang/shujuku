package com.jdbc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javabean.Reserve;
import com.jdbc.utils.JDBCUtils;

public class ReserveDao {
	public boolean insert(Reserve reserve) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "INSERT INTO reserve(readerId,isbn,reserveDate) " +
			"VALUES('" + reserve.getReaderId() + "','" + reserve.getISBN() + "','" + reserve.getReserveDate() + "')";
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

	public ArrayList<Reserve> findAll(String readerId) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Reserve> list = new ArrayList<Reserve>();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM reserve WHERE readerId='" + readerId + "'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Reserve reserve = new Reserve();
				reserve.setId(rs.getInt("id"));
				reserve.setReaderId(rs.getString("readerId"));
				reserve.setISBN(rs.getString("isbn"));
				reserve.setReserveDate(rs.getString("reserveDate"));
				list.add(reserve);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return null;
	}
	
	public ArrayList<Reserve> findAllISBN(String isbn) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Reserve> list = new ArrayList<Reserve>();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM reserve WHERE isbn='" + isbn + "' ORDER BY id ASC";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Reserve reserve = new Reserve();
				reserve.setId(rs.getInt("id"));
				reserve.setReaderId(rs.getString("readerId"));
				reserve.setISBN(rs.getString("isbn"));
				reserve.setReserveDate(rs.getString("reserveDate"));
				list.add(reserve);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return null;
	}
	
	public Reserve findByISBN(String readerId,String isbn) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM reserve WHERE readerId='" + readerId + "' AND isbn='" + isbn + "'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Reserve reserve = new Reserve();
				reserve.setId(rs.getInt("id"));
				reserve.setReaderId(rs.getString("readerId"));
				reserve.setISBN(rs.getString("isbn"));
				reserve.setReserveDate(rs.getString("reserveDate"));
				return reserve;
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
			String sql = "DELETE FROM reserve WHERE readerId='" + readerId + "' AND isbn='" + isbn + "'";
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
