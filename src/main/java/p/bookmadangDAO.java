package p;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class bookmadangDAO {
	Connection conn = null;
	PreparedStatement pstmt;

	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost:9092/C:/JAVA/H2/data/jwbookdb";

	public void open() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, "sa", "sa");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Book> bookFindAll() { // 북리스트 모두 가져옴
		open();
		List<Book> bookList = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement("SELECT * FROM BOOKS");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Book b = new Book();
				b.setId(rs.getInt("id"));
				b.setTitle(rs.getString("title"));
				b.setPublisher(rs.getString("publisher"));
				b.setPrice(rs.getInt("price"));
				bookList.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return bookList;
	}
	
	public synchronized void add(Book book) {
		open();
		try {
			pstmt = conn.prepareStatement("select max(id) from books ");
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				int indexMax = rs.getInt("max(id)"); //10
				pstmt = conn.prepareStatement("INSERT INTO books(id,title,publisher,price) VALUES (?,?,?,?)");
				pstmt.setInt(1, indexMax+1);
				pstmt.setString(2, book.getTitle());
				pstmt.setString(3, book.getPublisher());
				pstmt.setInt(4, book.getPrice());
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public synchronized void set(Book book) {
		open();
		try {
			pstmt = conn.prepareStatement("update books SET title= ?, publisher= ?,price=? WHERE id =? ");
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getPublisher());
			pstmt.setInt(3, book.getPrice());
			pstmt.setInt(4, book.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

	}

	public Book find(int id) {
		open();
		Book rtn = null;
		if (id == -1) {
			rtn = new Book();
			rtn.setId(-1);
			rtn.setTitle("");
			rtn.setPublisher("");
			rtn.setPrice(-1);
		} else {
			try {
				pstmt = conn.prepareStatement("SELECT * FROM BOOKS WHERE id = ?");
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					rtn = new Book();
					rtn.setId(rs.getInt("id"));
					rtn.setTitle(rs.getString("title"));
					rtn.setPublisher(rs.getString("publisher"));
					rtn.setPrice(rs.getInt("price"));
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close();
			}
		}
		return rtn;
	}

	public synchronized void bookRemove(int id) {
		open();
		
		try {
			pstmt = conn.prepareStatement("DELETE FROM BOOKS WHERE ID = ? ");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

	}

}
