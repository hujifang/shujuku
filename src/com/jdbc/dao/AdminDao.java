package com.jdbc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.javabean.Admin;
import com.jdbc.utils.JDBCUtils;

public class AdminDao {
	public Admin findById(String id) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM admin WHERE id='" + id + "'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Admin admin = new Admin();
				admin.setId(rs.getString("id"));
				admin.setPassword(rs.getString("password"));
				admin.setName(rs.getString("name"));
				admin.setContact(rs.getString("contact"));
				admin.setRemarks(rs.getString("remarks"));
				return admin;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return null;
	}
	
	public boolean update(Admin admin) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "UPDATE admin set password='" + admin.getPassword() + "',name='"
					+ admin.getName() + "',contact='" + admin.getContact() + "',remarks='" 
					+ admin.getRemarks() + "' WHERE id='" + admin.getId() + "'";
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
