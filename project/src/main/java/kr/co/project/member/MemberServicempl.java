package kr.co.project.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.SendMail;

@Service
public class MemberServicempl implements MemberService {
	@Autowired
	MemberMapper mapper;
	
	@Override
	public int insert(MemberVO vo) {
		return mapper.insert(vo);
	}

	@Override
	public int emailDupCheck(String email) {
		return mapper.emailDupCheck(email);
	}

	@Override
	public boolean loginCheck(MemberVO vo,HttpSession sess) {
		boolean r = false;
		MemberVO loginInfo = mapper.loginCheck(vo);
		if(mapper.loginCheck(vo) !=null) {
			r = true;//로그인 성공시 세션에 저장
			sess.setAttribute("loginInfo", loginInfo);
		}
		return r; //마이바티스는 select가 안되면 null 값을 리턴함
	}

	@Override
	public MemberVO findEmail(MemberVO vo) {
		return mapper.findEmail(vo);
	}

	@Override
	public MemberVO findPwd(MemberVO vo) {
		//update
		System.out.println("11111@#@#@#@#@#@#@#@#@#@#@#");
		System.out.println(vo);
		MemberVO mv = mapper.findPwd(vo);
		System.out.println("@#@#@#@#@#@#@#@#@#@#@#");
		System.out.println(mv);
		if(mv != null) {
			//임시비밀번호 생성
			//영문두자리(65~91),숫자두자리
			String temp = "";
			for(int i=0;i<2;i++) {
				temp += (char)(Math.random()*26+65);
			}
			for(int i=0;i<2;i++) {
				temp += (int)(Math.random()*9);
			}
			//임시 비밀번호 update
			vo.setPwd(temp);
			mapper.updateTempPwd(vo);
			
			//email발송
			SendMail.sendMail("tkdzma0@naver.com", vo.getEmail(), "[더조은]임시비밀번호", "임시비밀번호 :"+temp);
			return mv;
		}else {
			return null;	
		}
		
	}

}
