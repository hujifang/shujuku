package com.javabean;

public class Giveback {
	private String readerId;
	private String isbn;
	private String borrowDate;
	private String returnDate;
	
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
	
	public String getBorrowDate() {
		return borrowDate;
	}
	
	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}
	
	public String getReturnDate() {
		return returnDate;
	}
	
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
}
