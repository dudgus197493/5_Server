package edu.kh.jsp.controller.model.dao;

import static edu.kh.jsp.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jsp.controller.vo.Student;

public class StudentDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public StudentDAO() {
		try {
			String filePath = StudentDAO.class.getResource("/edu/kh/jsp/sql/student-sql.xml").getPath();
							// 이 클래스가 있는 위치에서 ()안에 있는 경로로 파일을 찾은 후 컴퓨터안에서의 경로를 가져와라
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(filePath));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/** 학생 전체 조회 DAO
	 * @param conn
	 * @return stdList
	 * @throws Exception
	 */
	public List<Student> selectAll(Connection conn) throws Exception{
		// 결과 저장용 변수 선언
		List<Student> stdList = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("selectAll");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Student student = new Student();
				student.setStudentNo(rs.getString("STUDENT_NO"));
				student.setStudentName(rs.getString("STUDENT_NAME"));
				student.setStudentAddress(rs.getString("STUDENT_ADDRESS"));
				student.setDepartmentName(rs.getString("DEPARTMENT_NAME"));
				
				stdList.add(student);
			}
			
		} finally {
			// 사용한 JDBC 객체 자원 반환
			close(rs);
			close(stmt);
		}
		
		// 결과 반환
		return stdList;
	}

	public List<Student> selectDepartment(Connection conn, String keyword) throws Exception{
			List<Student> stdList = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("selectDepartment");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Student student = new Student();
				student.setStudentNo(rs.getString("STUDENT_NO"));
				student.setStudentName(rs.getString("STUDENT_NAME"));
				student.setStudentAddress(rs.getString("STUDENT_ADDRESS"));
				student.setDepartmentName(rs.getString("DEPARTMENT_NAME"));
				
				stdList.add(student);
			}
			
		} finally {
			// 사용한 JDBC 객체 자원 반환
			close(rs);
			close(pstmt);
		}
		
		// 결과 반환
		return stdList;
	}
}
