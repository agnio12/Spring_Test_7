package com.iu.s7;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.board.BoardDTO;
import com.iu.notice.NoticeDTO;
import com.iu.notice.NoticeService;
import com.iu.util.ListData;

@Controller
@RequestMapping(value="/notice/**")
public class NoticeController {

	@Inject
	private NoticeService noticeService;

	//List (SelcetList)
	@RequestMapping(value="noticeList", method=RequestMethod.GET)
	public ModelAndView selectList(ListData listData) throws Exception{
		ModelAndView mv = new ModelAndView();
		List<BoardDTO> ar = noticeService.selectList(listData);
		mv.addObject("list", ar);
		mv.addObject("page", listData);
		mv.addObject("board", "notice");
		mv.setViewName("board/boardList");
		return mv;
	}


	//Write (Insert) & FileUpload
	@RequestMapping(value="noticeWrite", method=RequestMethod.GET)
	public String noticeWrite(Model model) throws Exception{
		model.addAttribute("board", "notice");
		return "board/boardWrite";
	}
	@RequestMapping(value="noticeWrite", method=RequestMethod.POST)
	public ModelAndView noticeWrite(NoticeDTO noticeDTO, MultipartFile [] file, HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = 0;
		result = noticeService.insert(noticeDTO, file, session);
		
		// Result 방식 - 리턴 ModelAndView
		if(result>0){
			mv.addObject("message", "Success");
		}else{
			mv.addObject("message", "Fail");
		}
		mv.addObject("path", "noticeList");
		mv.setViewName("common/result");
		return mv;
		
		/* Redirect 방식 - 리턴타입 → String / 매개변수 → RedirectAttributes
		rd.addFlashAttribute("message", "Success");
		return "redirect:./noticeList?curPage="; 
		*/
	}
	
	
	//View (SelectOne)
	@RequestMapping(value="noticeView")
	public ModelAndView selectOne(int num)throws Exception{
		 BoardDTO boardDTO = noticeService.selectOne(num);
		 ModelAndView mv = new ModelAndView();
		 mv.addObject("view", boardDTO);
		 mv.addObject("board", "notice");
		 mv.setViewName("board/boardView");
		return mv;
	}
	
	
	//Update & FileInsert
	@RequestMapping(value="noticeUpdate", method=RequestMethod.GET)
	public String update(Model model, int num) throws Exception{
		BoardDTO boardDTO = noticeService.selectOne(num);
		model.addAttribute("board", "notice");
		model.addAttribute("view", boardDTO);
		return "board/boardUpdate";
	}
	@RequestMapping(value="noticeUpdate", method=RequestMethod.POST)
	public ModelAndView update(BoardDTO boardDTO, MultipartFile[] file, HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = noticeService.update(boardDTO, file, session);
		if(result > 0){
			mv.addObject("message", "Success");
		}else{
			mv.addObject("message", "Fail");
		}
		mv.addObject("path", "noticeList");
		mv.setViewName("common/result");
		return mv;
	}
	
	
	//Delete
	@RequestMapping(value="noticeDelete")
	public ModelAndView delete(int num, HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = noticeService.delete(num, session);
		if(result > 0){
			mv.addObject("message", "Success");
		}else{
			mv.addObject("message", "Fail");
		}
		mv.addObject("path", "noticeList");
		mv.setViewName("common/result");
		return mv;
	}

	
}
