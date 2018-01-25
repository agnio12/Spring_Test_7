package com.iu.s7;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
			mv.addObject("message", "Join Success");
		}else{
			mv.addObject("message", "Join Fail");
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
	@RequestMapping(value="memberMyPage")
	public void memberMyPage(HttpSession session) throws Exception{
		/*Login 으로 인한 session으로 MyPage에서 뿌려줄것이기 때문에 굳이 Service와 DAO에 만들어주지 않아도 된다
		하지만 session에 모든 데이터가 담겨져 있지 않다면 새로 DAO와 Service를 만드는것이 좋다
		memberService.memberMyPage 를 가져올때는 session으로 id 를 가져와야 한다
		그러지 않으면 memberService.memberMyPage의 id와 이미 session으로 가져오는 id가 겹쳐 sql문도 겹쳐서 에러발생
		MemberDTO memberDTO = memberService.memberMyPage(((MemberDTO)session.getAttribute("member")).getId());
		return memberDTO;*/
	}
	
	
	//Update
	@RequestMapping(value="memberUpdate", method=RequestMethod.GET)
	public void memberUpdate(){}
	
	@RequestMapping(value="memberUpdate", method=RequestMethod.POST)
	public ModelAndView memberUpdate(MemberDTO memberDTO, MultipartFile file, HttpSession session) throws Exception{
		int result = memberService.memberUpdate(memberDTO, file, session);
		String message = "Update Fail";
		if(result > 0){
			session.setAttribute("member", memberDTO);
			message = "Update Success";
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", message);
		mv.addObject("path", "memberMyPage");
		mv.setViewName("common/result");
		return mv;
	}
	
	
	//Delete
	@RequestMapping(value="memberDelete")
	public ModelAndView memberDelete(HttpSession session, RedirectAttributes rd)throws Exception{
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		int result = memberService.memberDelete(memberDTO, session);
		ModelAndView mv  = new ModelAndView();
		if(result > 0){
			mv.addObject("message", "Delete Success");
			session.invalidate();
		}else{
			mv.addObject("message", "Delete Fail");
		}
		mv.addObject("path", "../");
		mv.setViewName("common/result");
		return mv;
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
