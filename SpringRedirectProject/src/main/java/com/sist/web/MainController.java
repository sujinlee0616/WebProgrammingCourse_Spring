package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {
	
	@RequestMapping("main/list.do")
	public String main_list(Model model) {
		model.addAttribute("msg", "Redirect를 이용시 데이터 전송하는 방법");
		return "main/list";
	}
	
	@RequestMapping("main/insert.do")
	public String main_insert() {
		return "redirect:result.do?id=admin";
	}
	
	// ★★★ Redirect ★★★ - ID나 중요한 내용이 넘어갈 때는 보안을 위해 RedirectAttributes를 쓰는 것이 좋다. 
	@RequestMapping("main/update.do")
	public String main_update(RedirectAttributes r) { // <== 이렇게도 가능하다 
		
		r.addFlashAttribute("id","admin");  
		// addFlashAttribute: 값을 지워준다 ==> 보안을 위해 
		// result에 model.addAttribute하는 역할도 한다. 
		
		return "redirect:result.do";
	}
	
	@RequestMapping("main/result.do")
	public String main_result(String id, Model model) {
		//model.addAttribute("id", id);
		return "main/result";
	}
	
}
