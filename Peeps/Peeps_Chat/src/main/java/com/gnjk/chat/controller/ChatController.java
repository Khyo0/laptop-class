package com.gnjk.chat.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChatController {

	private Timestamp time;
	@RequestMapping("/chat")
	public ModelAndView chat(
			ModelAndView mv,
			@RequestParam("id") String id,
			HttpSession session
			) {
		mv.setViewName("chat/chat");
		mv.addObject("user", id);
		mv.addObject("Id", "");
		mv.addObject("Owner", "");
		
		session.setAttribute("user", id);
		
		return mv;
	}
}