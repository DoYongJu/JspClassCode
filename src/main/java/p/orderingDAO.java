package p;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class orderingDAO {
	Connection conn = null;
	PreparedStatement pstmt;
	
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL="jdbc:h2:tcp://localhost:9092/C:/JAVA/H2/data/jwbookdb";

	

	public void open() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL,"sa","sa");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			pstmt.close();
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public List<Ordering> orderingFindAll() { //주문리스트 모두 가져옴
		open();
		List<Ordering> orderingList= new ArrayList<>();
		try {
			pstmt =conn.prepareStatement("SELECT bookid,title,customerid,name,sellingPrice,orderingDate "
					+ "FROM books, ordering, customers "
					+ "WHERE books.id = ordering.bookid AND customers.id=ordering.customerid");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Ordering o =new Ordering();
				o.setCustomerId(rs.getInt("customerid"));
				o.setName(rs.getString("name"));
				o.setBookId(rs.getInt("bookid"));
				o.setTitle(rs.getString("title"));
				o.setSellingPrice(rs.getInt("sellingPrice"));
				o.setOrderingDate(rs.getDate("orderingDate"));
				orderingList.add(o);		
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {close();}
		
		return orderingList;
	}
	
	public synchronized void add(Ordering ordering) {
		open();
		try {
			pstmt = conn.prepareStatement("select max(id) from ordering ");
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				int indexMax = rs.getInt("max(id)"); //10
				pstmt = conn.prepareStatement("INSERT INTO ordering(id,customerId,bookId,sellingPrice,orderingDate) VALUES (?,?,?,?,?)");
				pstmt.setInt(1, indexMax+1);
				pstmt.setInt(2, ordering.getCustomerId());
				pstmt.setInt(3, ordering.getBookId());
				pstmt.setInt(4, ordering.getSellingPrice());
				pstmt.setDate(5, new java.sql.Date(new Date().getTime()));
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	
	
	
	
}
