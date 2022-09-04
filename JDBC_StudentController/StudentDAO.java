package ch09;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
	Connection conn = null; //실제 db와의 연결을 만들기 위한 Connection 클래스의 인스턴스 레퍼런스 
	PreparedStatement pstmt; //sql문을 미리 작성해두고 변수를 따로 입력하는 방식의 preparestatement
	
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/jwbookdb";
	
	public void open() { //db 열기
		try {
			Class.forName(JDBC_DRIVER); //jdbc 드라이버 로드
			conn = DriverManager.getConnection(JDBC_URL,"jwbook","1234"); //jdbc url과 아이디, 비밀번호
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void close() { //db 닫기
		try {
			pstmt.close(); //pstmt와 conn 연결 해제
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insert(Student s) { //db에 학생 정보 추가(학생 객체를 인자로 받아서)
		open(); //db열고
		String sql = "INSERT INTO student(username,univ,birth,email) VALUES(?,?,?,?)"; //pstmt에 들어갈 sql문
		
		try {
			pstmt = conn.prepareStatement(sql); //미리 작성된 sql문을 통해 변수를 따로 입력한다. ?에 해당하는 데이터를 매핑한다.
			pstmt.setString(1,s.getUsername()); //첫번째 물음표에 student 클래스 객체의 get메서드 이용 
			pstmt.setString(2,s.getUniv());
			pstmt.setDate(3,s.getBirth());
			pstmt.setString(4,s.getEmail());
			
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(); //db닫기
		}
	}
	
	public List<Student> getAll(){ //db내에 모든 학생 정보를 받아오는 메서드. student 타입의 리스트를 반환한다.
		open(); //db열고
		List<Student> students = new ArrayList<>(); //student객체 담아 반환할  students 리스트
		
		try {
			pstmt = conn.prepareStatement("select * from student"); //pstmt 작성
			ResultSet rs = pstmt.executeQuery(); //데이터를 가져오는 경우 이용하는 ResultSet객체(데이터 처리)는 해당 정보를 로우 단위로 가져온다. 
			
			while(rs.next()) { //받아온 로우 단위 정보를 다 할때까지
				Student s = new Student(); //student객체에
				s.setId(rs.getInt("id")); //
				s.setUsername(rs.getString("username"));
				s.setUniv(rs.getString("univ"));
				s.setBirth(rs.getDate("birth"));
				s.setEmail(rs.getString("email"));
				
				students.add(s); //리스트에 추가후 반복
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(); //끝나면 db닫기
		}
		return students;//리스트 반환
	}
	
}
