package com.iu.s7;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.member.MemberDTO;
import com.iu.member.MemberService;

@Controller
@RequestMapping(value="/member/**")
public class MemberController {
	
	@Inject
	private MemberService memberService;

	//Join
	@RequestMapping(value="memberJoin", method=RequestMethod.GET)
	public void memberJoin() { }
	
	@RequestMapping(value="memberJoin", method=RequestMethod.POST)
	public ModelAndView memberJoin(MemberDTO memberDTO, MultipartFile file, HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = 0;
		result = memberService.memberJoin(memberDTO, file, session);
		if(result>0){
			mv.addObject("message", "Success");
		}else{
			mv.addObject("message", "Fail");
		}
		mv.addObject("path", "../");
		mv.setViewName("common/result");
		return mv;
	}
	
	//ID Check
	@RequestMapping(value="memberIdCheck", method=RequestMethod.GET)
	public ModelAndView memberIdCheck(String id) throws Exception{
		ModelAndView mv = new ModelAndView();
		MemberDTO memberDTO =  memberService.memberIdCheck(id);
		if(memberDTO == null){
			mv.addObject("result", "Possible ID");
		}else{
			mv.addObject("result", "Duplicate ID");
		}
		mv.setViewName("common/memberResult");
		return mv;
	}
	
	//Login
	@RequestMapping(value="memberLogin", method=RequestMethod.GET)
	public void memberLogin(){ }
	
	@RequestMapping(value="memberLogin", method=RequestMethod.POST)
	public ModelAndView memberLogin(MemberDTO memberDTO, HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		memberDTO = memberService.memberLogin(memberDTO);
		if(memberDTO != null){
			session.setAttribute("member", memberDTO);
			mv.addObject("message", "Login Success");
		}else{
			mv.addObject("message", "Login Fail");
		}
		mv.addObject("path", "../");
		mv.setViewName("common/result");
		return mv;
		}
	
	//MyPage
	@RequestMapping(value="memberMyPage", method=RequestMethod.GET)
	public void memberMyPage(Model model, String id) throws Exception{
		MemberDTO memberDTO = memberService.memberMyPage(id);
		model.addAttribute("my", memberDTO);
	}
		
	//LogOut
	@RequestMapping(value="memberLogOut")
	public ModelAndView memberLogOut(HttpSession session){
		ModelAndView mv  = new ModelAndView();
		mv.addObject("message", "LogOut Success");
		session.invalidate();
		mv.addObject("path", "../");
		mv.setViewName("common/result");
		return mv;
	}
	
}
