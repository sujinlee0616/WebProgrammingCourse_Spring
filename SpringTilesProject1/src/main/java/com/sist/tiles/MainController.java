package com.sist.tiles;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping("main.do")
	public String main_page()
	{
		return "main";
	}
	
	@RequestMapping("board/list.do")
	public String board_list()
	{
		return "board/list";
	}
	
	@RequestMapping("notice/list.do")
	public String notice_list()
	{
		return "notice/list";
	}

}
