package com.cafe24.mysite.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mysite.exception.UserDaoException;
import com.cafe24.mysite.repository.vo.UserVo;
import com.cafe24.mysite.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	

	
	
	@RequestMapping(value="/join",method=RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	
	@ResponseBody
	@RequestMapping(value="/join",method=RequestMethod.POST)
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println(userVo);
		//userDao.insert(userVo);
		userService.join(userVo);
		return "reirect:/user/joinsuccess";
	}
	
	@RequestMapping(value="/joinsuccess")
	public String joinSuccess(@ModelAttribute UserVo userVo) {
		return "/user/joinsuccess";
	}
	
	@RequestMapping(value="/login" ,method=RequestMethod.GET)
	public String login() {
		return "/user/login";
	}
	
	//Dao는 기술침투 
	@RequestMapping(value="/login" ,method=RequestMethod.POST)
	public String login(@RequestParam(value="email",required=true,defaultValue="")String email,
			@RequestParam(value="password",required=true,defaultValue="")String password,
			HttpSession session,Model model) {
		
		UserVo authUser = userService.getUser(new UserVo(email, password));
		if(authUser==null) {
			model.addAttribute("result","fail");
			return "user/login";
		}
		//세션처리
		session.setAttribute("authUser",authUser);
		
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
	
	/*
	@ExceptionHandler(UserDaoException.class)
	public String handleUserDaoException() {
		return "error/exception";
	}*/

}
