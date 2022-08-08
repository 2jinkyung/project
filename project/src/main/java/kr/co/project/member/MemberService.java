package kr.co.project.member;

import javax.servlet.http.HttpSession;

public interface MemberService {
	int insert(MemberVO vo);
	int emailDupCheck(String email);
	boolean loginCheck(MemberVO vo,HttpSession sess); //mapper에서 체크하고 서비스 영역에서는 틀린지같은지만 체크하면되므로 boolean으로 해줌
	MemberVO findEmail(MemberVO vo);
	MemberVO findPwd(MemberVO vo);
}
