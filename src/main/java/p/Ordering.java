package p;

import java.sql.Date;

public class Ordering {
	private int id;
	private int customerId;
	private int bookId;
	private int sellingPrice;
	private Date orderingDate;
	
	private String title; //책제목

	private String name; //고객이름
	
	public Ordering() {
		
	}
	public Ordering(int id, int customerId, int bookId, int sellingPrice, Date orderingDate,String title, String name ) {
		this.id= id;
		this.customerId= customerId;
		this.bookId= bookId;
		this.sellingPrice= sellingPrice;
		this.orderingDate= orderingDate;
		this.title=title;
		this.name=name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(int sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public Date getOrderingDate() {
		return orderingDate;
	}
	public void setOrderingDate(Date orderingDate) {
		this.orderingDate = orderingDate;
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
