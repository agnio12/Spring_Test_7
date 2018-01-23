package com.iu.s7;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iu.member.MemberService;

@Controller
@RequestMapping(value="/member/**")
public class MemberController {
	
	@Inject
	private MemberService memberService;
	
	//회원가입
	@RequestMapping(value="memberJoin", method=RequestMethod.GET)
	public void memberJoin(){
		
	}
	
	//로그인
	@RequestMapping(value="memberLogin", method=RequestMethod.POST)
	public void memberLogin(){
		
	}
	
	//MyPage
	public void memberMyPage(){
		
	}
	
	//회원탈퇴
	
	//로그아웃

}
