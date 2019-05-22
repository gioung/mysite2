package com.cafe24.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.repository.vo.BoardVo;
import com.cafe24.mysite.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService boardservice;
	
	@RequestMapping("")
	public String list(Model model,HttpSession session) {
		List<BoardVo> list = boardservice.getContentList();
		model.addAttribute("list", list);
		System.out.println(list);
		return "board/list";
	}
	
	@RequestMapping(value="/write",method=RequestMethod.GET)
	public String write(HttpSession session) {
		
		return "board/write";
		
	}
	
	@RequestMapping(value="/write",method=RequestMethod.POST)
	public String write(@ModelAttribute BoardVo boardVo ,HttpSession session) {
		boardservice.writeContent(boardVo);
		return "board/list";
	}
	
	
	
}
