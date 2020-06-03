package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// MainController는 그냥 톰캣 서버 돌리려고 만들었음. 

@Controller
public class MainController {
	@RequestMapping("main/main.do")
	public String main_page()
	{
		return "main/main";
	}
	
}
