package com.spring.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.dto.BoardDTO;
import com.spring.dto.PageDTO;
import com.spring.service.BFileService;
import com.spring.service.BoardService;
import com.spring.service.ReplyService;

@Controller
@RequestMapping("/board")
@SessionAttributes("pdto")
public class BoardController {
	
//	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Resource
	private BoardService bservice;
	@Resource
	private BFileService fservice;
	@Resource
	private ReplyService rservice;
	
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String list() {
//		return "board/list";
//	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert() {
		return "board/add";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(BoardDTO bdto, List<MultipartFile> bfiles) throws Exception {
		
		bservice.insert(bdto, bfiles);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/")
	public String moveList(PageDTO pdto, Model model) throws Exception {
		// @SessionAttributes("pdto") 생성
		model.addAttribute("pdto", pdto);		
		return "board/main";
	}
	
	@RequestMapping(value = "/sessionDelete")
	public String sessionDelete(SessionStatus status) {
		status.setComplete();
		return "redirect:/board/";
	}	
	
	// @ModelAttribute("pdto") view 까지 정보 전달
	// @ModelAttribute는 SessionAttributes의 값 세팅
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void selectList(@ModelAttribute("pdto") PageDTO pdto, Model model) throws Exception {
		
		List<BoardDTO> list = bservice.selectList(pdto);

		model.addAttribute("list", list);		
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public void selectOne(int bnum, Model model) throws Exception {
		
		bservice.readcnt_update(bnum);
		
		Map<String, Object> map = bservice.selectOne(bnum);
		model.addAttribute("board", map.get("board"));
		model.addAttribute("flist", map.get("flist"));
	}
	
	@RequestMapping(value = "/downloads", method = RequestMethod.GET)
	public void downloads(String filename, HttpServletResponse response) throws Exception {
		
		fservice.fileDownload(filename, response);
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void update(int bnum, Model model) throws Exception {
		
		Map<String, Object> map = bservice.selectOne(bnum);
		model.addAttribute("board", map.get("board"));
		model.addAttribute("flist", map.get("flist"));
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String update(BoardDTO bdto, @RequestParam(value = "fnum", required = false) List<Integer> fnumList, List<MultipartFile> bfiles, RedirectAttributes ra) throws Exception {
		
		bservice.update(bdto, fnumList, bfiles);
		
		ra.addFlashAttribute("msg", "Succese");
		ra.addAttribute("bnum", bdto.getBnum());
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String delete(int bnum, Model model, RedirectAttributes ra) throws Exception {

		fservice.delete(bnum);
		bservice.delete(bnum);
		
		ra.addFlashAttribute("msg", "Succese");
		
		return "redirect:/board/list";
	}
}
