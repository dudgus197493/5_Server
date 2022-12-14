package edu.kh.project.member.model.service;

import static edu.kh.project.common.JDBCTemplate.getConnection;
import static edu.kh.project.common.JDBCTemplate.close;
import static edu.kh.project.common.JDBCTemplate.commit;
import static edu.kh.project.common.JDBCTemplate.rollback;

import java.sql.Connection;

import edu.kh.project.member.model.dao.MemberDAO;
import edu.kh.project.member.model.vo.Member;

/** 회원 전용 기능 제공 서비스
 * @author KYH
 * 
 */
public class MemberService {
	private MemberDAO dao = new MemberDAO();

	/** 로그인 Service
	 * @param member
	 * @return loginMember
	 * @throws Exception
	 */
	public Member login(Member member) throws Exception{
		
		Connection conn = getConnection();
		
		Member loginMember = dao.login(conn, member);
		
		close(conn);
		return loginMember;
	}

	/** 회원 가입 서비스
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Member member) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.signUp(conn, member);
		
		if(result > 0) commit(conn); 
		else		   rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 회원 정보 수정 서비스
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int updateMember(Member member) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.updateMember(conn, member);
		
		if(result > 0) commit(conn); 
		else		   rollback(conn);
		
		close(conn);
		
		return result;
	}
}
