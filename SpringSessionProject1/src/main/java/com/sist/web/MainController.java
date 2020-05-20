package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	// MainController: 화면을 바꿔줌 
	
	@RequestMapping("main/list.do")
	public String main_list()
	{
		return "main/list";
	}
	
	@RequestMapping("main/detail.do")
	public String main_detail()
	{
		// no 받아서 처리하는 걸 js로 하니까 Java는 굳이 no 받을 필요도 없다. ==> no 안 넘겼음 
		// MusicController(@RestController)에서 JSON으로 값 보내서 쓰면 됨
		return "main/detail";
	}

}
