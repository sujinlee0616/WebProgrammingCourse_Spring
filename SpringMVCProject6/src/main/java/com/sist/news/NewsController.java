package com.sist.news;

import java.util.*;
import com.sist.xml.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NewsController {
	@Autowired
	private NewsManager nm;
	
	@RequestMapping("main/news.do")
	public String main_new(Model model,String search_keyword)
	{
		if(search_keyword==null)
			search_keyword="코로나";
		// A에서 정보를 보내서 A(자기자신)에게 출력 ==> null값 신경써줘야 
		
		List<Item> list=nm.newsAllData(search_keyword);
		model.addAttribute("list",list);
		
		return "main/news";
	}
	
}





