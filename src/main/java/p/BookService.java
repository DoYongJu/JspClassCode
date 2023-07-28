package p;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookService {
	Map<Integer, Book> books = new HashMap<>();
	public BookService() {
		Book b = new Book(1,"축구의역사","굿스포츠","7000");
		books.put(b.getId(), b);
		
		b = new Book(2,"축구아는여자","나무수","13000");
		books.put(b.getId(), b);
		
		b = new Book(3,"축구의이해","대한미디어","22000");
		books.put(b.getId(), b);
		
		b = new Book(4,"골프바이블","대한미디어","35000");
		books.put(b.getId(), b);
		
		b = new Book(5,"피겨교본","굿스포츠","8000");
		books.put(b.getId(), b);
		
		b = new Book(6,"역도의단계별기술","굿스포츠","6000");
		books.put(b.getId(), b);
		
		b = new Book(7,"야구를부탁해","이상미디어","13000");
		books.put(b.getId(), b);
		
		b = new Book(8,"야구의추억","이상미디어","20000");
		books.put(b.getId(), b);
		
		b = new Book(9,"올림픽이야기","삼성당","7500");
		books.put(b.getId(), b);
		
		b = new Book(10,"단편화","삼성당","2500");
		books.put(b.getId(), b);
		
		
		
	}
	
	public synchronized void add(Book book){
		int max = Collections.max(books.keySet());
		book.setId(max+1);
		books.put(book.getId(), book);
	}
	
	public synchronized void remove(int id) {
		books.remove(id);
	}
	
	public Book find(int id) {
		Book rtn = null;
		rtn= books.get(id);
		
		if(rtn == null) {
			rtn = new Book();
			rtn.setId(-1);
			rtn.setTitle("");
			rtn.setPublisher("");
			rtn.setPrice(-1);
		}
		return rtn;
	}
	
	public synchronized void set(Book book) {
		book.setId(book.getId());
		book.setTitle(book.getTitle());
		book.setPublisher(book.getPublisher());
		book.setPrice(book.getPrice());
		books.put(book.getId(), book);
	}
	
	public Map<Integer, Book> getBooks() {
		return books;
	}
	
	public List<Book>findAll(){
		return new ArrayList(books.values());
	}
	
	public Book find(String id) {
		return books.get(id);
	}
}
