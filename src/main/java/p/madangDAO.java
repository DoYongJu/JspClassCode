package p;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch09.Student;

public class madangDAO {
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

	public List<Customer> customerFindAll() { // 주문리스트 모두 가져옴
		open();
		List<Customer> customerList = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement("SELECT * FROM CUSTOMERS ");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Customer c = new Customer();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setAddress(rs.getString("address"));
				c.setPhone(rs.getString("phone"));
				customerList.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return customerList;
	}

	public Customer getCustomer(int customerid) { // 고객 아이디와 이름을 조회
		open();
		Customer rtn = null;
		try {
			pstmt = conn.prepareStatement("SELECT ID, NAME FROM customers WHERE Id= ?");
			pstmt.setInt(1, customerid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				rtn.setId(rs.getInt("id"));
				rtn.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return rtn;
	}

	public synchronized void customerRemove(int id) {
		open();
		try {
			pstmt = conn.prepareStatement("DELETE FROM CUSTOMERS WHERE ID = ? ");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public synchronized void add(Customer customer) {
		open();
		try {
			pstmt = conn.prepareStatement("select max(id) from customers ");
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				int indexMax = rs.getInt("max(id)"); //5
				pstmt = conn.prepareStatement("INSERT INTO customers(id,name,address,phone) VALUES (?,?,?,?)");
				pstmt.setInt(1, indexMax+1);
				pstmt.setString(2, customer.getName());
				pstmt.setString(3, customer.getAddress());
				pstmt.setString(4, customer.getPhone());
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public synchronized void set(Customer customer) {
		open();
		try {
			pstmt = conn.prepareStatement("update customers SET name= ?, address= ?,phone=? WHERE id =? ");
			pstmt.setString(1, customer.getName());
			pstmt.setString(2, customer.getAddress());
			pstmt.setString(3, customer.getPhone());
			pstmt.setInt(4, customer.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

	}

	public Customer find(int id) {
		open();
		Customer rtn = null;
		if (id == -1) {
			rtn = new Customer();
			rtn.setId(-1);
			rtn.setName("");
			rtn.setAddress("");
			rtn.setPhone("");
		} else {
			try {
				pstmt = conn.prepareStatement("select * from customers where id =?");
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					rtn = new Customer();
					rtn.setId(rs.getInt("id"));
					rtn.setName(rs.getString("name"));
					rtn.setAddress(rs.getString("address"));
					rtn.setPhone(rs.getString("phone"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close();
			}
		}
		return rtn;
	}
	
	

}
