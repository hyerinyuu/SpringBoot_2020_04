package com.biz.sec.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.sec.domain.UserVO;
import com.biz.sec.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {
	
	private final UserService userService;

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		
		return "login";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(Model model) {
		
		model.addAttribute("userVO", new UserVO());
		return "join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(UserVO userVO, Model model) {
		
		/*
		 * Optional에 객체를 담는 2가지 방법
		 * 
		 * 1. Optional<class> 객체1 = Optional.of(객체);
		 *  ==> 만약 객체가 null이면 아예 exception을 여기서 발생
		 * 
		 * 2. Optional<class> 객체1 = Optional.ofNullable(객체);
		 * 	 ==> 객체가 null이어도 무시하고 진행
		 */
		Optional<UserVO> opUserVO = Optional.of(userVO);
		userService.insert(opUserVO);
		return "redirect:/";
	}
	
}
