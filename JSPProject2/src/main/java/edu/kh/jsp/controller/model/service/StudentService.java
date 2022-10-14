package edu.kh.jsp.controller.model.service;

import static edu.kh.jsp.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import edu.kh.jsp.controller.model.dao.StudentDAO;
import edu.kh.jsp.controller.vo.Student;


public class StudentService {
	private StudentDAO dao = new StudentDAO();
	
	// alt + shift + j : 클래스/메서드 설명용 주석(HTML 작성법)
	/** 학생 전체 조회
	 * @return stdList
	 * @throws Exception
	 */
	public List<Student> selectAll() throws Exception {
		
		// Connection 생성
		Connection conn = getConnection();
		
		// dao 메서드 호출 후 결과 반환 받기
		List<Student> stdList = dao.selectAll(conn);
		
		// DML인 경우 트랜잭션 처리
		
		// Connection 객체 반환
		close(conn);
		
		// 결과 반환
		return stdList;
	}

	public List<Student> selectDepartment(String keyword) throws Exception{
		Connection conn = getConnection();
		
		List<Student> stdList = dao.selectDepartment(conn, keyword);
		
		// DML인 경우 트랜잭션 처리
		
		// Connection 객체 반환
		close(conn);
		
		// 결과 반환
		return stdList;
	}
	
	
}
