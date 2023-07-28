package ch09;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
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
	
	
	
	
	public void insert(Student s) {
		open();
		String sql = 
				"INSERT INTO student(username,univ,birth,email) values(?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s.getUsername());
			pstmt.setString(2, s.getUniv());
			pstmt.setDate(3, s.getBirth());
			pstmt.setString(4, s.getEmail());
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally { close(); }
	}
	public Student get(int id) {
		Student rtn = null;
		try {
			pstmt =conn.prepareStatement("select * from student WHERE id = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				rtn =new Student();
				rtn.setId(rs.getInt("id"));
				rtn.setUsername(rs.getString("username"));
				rtn.setUniv(rs.getString("univ"));
				rtn.setBirth(rs.getDate("birth"));
				rtn.setEmail(rs.getString("email"));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {close();}
		
		return rtn;
	}
	
	
	public List<Student> getAll(){
		open();
		List<Student>students = new ArrayList<>();
		
		try {
			pstmt =conn.prepareStatement("select * from student");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Student s =new Student();
				s.setId(rs.getInt("id"));
				s.setUsername(rs.getString("username"));
				s.setUniv(rs.getString("univ"));
				s.setBirth(rs.getDate("birth"));
				s.setEmail(rs.getString("email"));
				
				students.add(s);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {close();}
	return students;
	}
	
	
}


