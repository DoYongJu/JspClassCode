package mandart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ch09.Student;

import java.util.*;


public class MandaraDAO {
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
	
	public String getFirst() {
		String rtn=null;
		
		open();
		try {
			pstmt =conn.prepareStatement("SELECT name FROM FIRST_GOAL where id=1");
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				rtn =rs.getString("name"); //8구단 드래프트 1순위
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {close();}
		
		return rtn;
	}
	
	public List<String> getSecond(){
		open();
		List<String> seconds = new ArrayList<>();
		
		try {
			pstmt =conn.prepareStatement("SELECT * FROM SECOND_GOAL WHERE firstId=1");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				seconds.add(rs.getString("name"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {close();}
	return seconds;
	}
	public int getSecondIdByName(String goal) {
		open();
		int rtn=0;
		if(goal!=null) {
			try {
				pstmt =conn.prepareStatement("SELECT * FROM SECOND_GOAL WHERE Name= ?");
				pstmt.setString(1, goal);
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					rtn = rs.getInt("id");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {close();}
		}	
		return rtn;
	}
	
	public List<String> getThirdsBySecondId(int secondId){
		open();
		List<String> third= new ArrayList<>();
		
		try {
			pstmt =conn.prepareStatement("SELECT * FROM THIRD_GOAL WHERE secondId=?");
			pstmt.setInt(1, secondId);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				third.add(rs.getString("name"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {close();}
	return third;
	}
	

	
}
