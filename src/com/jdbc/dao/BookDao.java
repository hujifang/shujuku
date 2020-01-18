package com.jdbc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javabean.Book;
import com.jdbc.utils.JDBCUtils;

public class BookDao {
	public boolean insert(Book book) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "INSERT INTO book(classNum,isbn,name,author,press,pubDate,price,copyNum,stockNum) " +
			"VALUES('" + book.getClassNum() + "','" + book.getISBN() + "','" + book.getName() 
			+ "','" + book.getAuthor() + "','" + book.getPress() + "','" + book.getPubDate() 
			+ "'," + book.getPrice() + "," + book.getCopyNum() + "," + book.getStockNum()  + ")";
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

	public char[] findClassNum() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String list = "";
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT DISTINCT classNum FROM book ORDER BY classNum ASC";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				list += rs.getString("classNum").charAt(0);
			}
			return list.toCharArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return null;
	}
	
	public ArrayList<Book> findAll() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Book> list = new ArrayList<Book>();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM book";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Book book = new Book();
				book.setClassNum(rs.getString("classNum").charAt(0));
				book.setISBN(rs.getString("isbn"));
				book.setName(rs.getString("name"));
				book.setAuthor(rs.getString("author"));
				book.setPress(rs.getString("press"));
				book.setPubDate(rs.getString("pubDate"));
				book.setPrice(rs.getDouble("price"));
				book.setCopyNum(rs.getInt("copyNum"));
				book.setStockNum(rs.getInt("stockNum"));
				list.add(book);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return null;
	}
	
	public ArrayList<Book> find(String classNum, String search_type, String keyword) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Book> list = new ArrayList<Book>();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql;
			if(!classNum.equals("all")) {
				if(keyword != "") {
					sql = "SELECT * FROM book WHERE classNum='" + classNum + "' AND " + search_type + " LIKE '%" + keyword + "%'";
				}else {
					sql = "SELECT * FROM book WHERE classNum='" + classNum + "'";
				}
			}else {
				sql = "SELECT * FROM book WHERE " + search_type + " LIKE '%" + keyword + "%'";
			}
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Book book = new Book();
				book.setClassNum(rs.getString("classNum").charAt(0));
				book.setISBN(rs.getString("isbn"));
				book.setName(rs.getString("name"));
				book.setAuthor(rs.getString("author"));
				book.setPress(rs.getString("press"));
				book.setPubDate(rs.getString("pubDate"));
				book.setPrice(rs.getDouble("price"));
				book.setCopyNum(rs.getInt("copyNum"));
				book.setStockNum(rs.getInt("stockNum"));
				list.add(book);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return null;
	}

	public boolean delete(String isbn) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "DELETE FROM book WHERE isbn='" + isbn + "'";
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

	public boolean update(Book book) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "UPDATE book set classNum='" + book.getClassNum() + "',name='"
					+ book.getName() + "',author='" + book.getAuthor() + "',press='"
					+ book.getPress() + "',pubDate='" + book.getPubDate() + "',price=" + book.getPrice() 
					+ ",copyNum=" + book.getCopyNum() + ",stockNum=" + book.getStockNum() + " WHERE isbn='" + book.getISBN() + "'";
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
