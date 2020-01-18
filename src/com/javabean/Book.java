package com.javabean;

public class Book {
	private char classNum;
	private String isbn;
	private String name;
	private String author;
	private String press;
	private String pubDate;
	private double price;
	private int copyNum;
	private int stockNum;
	
	public char getClassNum() {
		return classNum;
	}
	
	public void setClassNum(char classNum) {
		this.classNum = classNum;
	}
	
	public String getISBN() {
		return isbn;
	}
	
	public void setISBN(String isbn) {
		this.isbn = isbn;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getPress() {
		return press;
	}
	
	public void setPress(String press) {
		this.press = press;
	}
	
	public String getPubDate() {
		return pubDate;
	}
	
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getCopyNum() {
		return copyNum;
	}
	
	public void setCopyNum(int copyNum) {
		this.copyNum = copyNum;
	}
	
	public int getStockNum() {
		return stockNum;
	}
	
	public void setStockNum(int stockNum) {
		this.stockNum = stockNum;
	}
}
