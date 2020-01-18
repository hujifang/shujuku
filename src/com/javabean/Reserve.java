package com.javabean;

public class Reserve {
	private int id;
	private String readerId;
	private String isbn;
	private String reserveDate;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getReaderId() {
		return readerId;
	}
	
	public void setReaderId(String readerId) {
		this.readerId = readerId;
	}
	
	public String getISBN() {
		return isbn;
	}
	
	public void setISBN(String isbn) {
		this.isbn = isbn;
	}
	
	public String getReserveDate() {
		return reserveDate;
	}
	
	public void setReserveDate(String reserveDate) {
		this.reserveDate = reserveDate;
	}
}
