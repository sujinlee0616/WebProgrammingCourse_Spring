package com.sist.myapp2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import com.sist.dao.*;
import com.sist.mgr.NewsManager;
import com.sist.mgr.NewsVO;

@Controller
@RequestMapping("movie/")
public class MovieController {
	@Autowired
	private MovieDAO dao;
	
	@Autowired
	private NewsManager mgr;
	
	@RequestMapping("list.do") // 위의 @RequestMapping("movie/") + @RequestMapping("list.do") ==> movie/list.do
	public String movie_list(Model model,String page)
	{
		if(page==null)
			page="1";
		
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowSize=12;
		int start=rowSize*(curpage-1)+1;
		int end=rowSize*curpage;
		
		map.put("start", start);
		map.put("end", end);
		map.put("type", 1);
		
		// DAO에서 데이터를 받아옴 
		List<MovieVO> list=dao.movieListData(map);
		int totalpage=dao.movieTotalPage(1);
		model.addAttribute("list",list);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("curpage",curpage);
		//model.addAttribute("main_jsp","../movie/react_list.jsp");  //React로 한 것
		model.addAttribute("main_jsp","../movie/list.jsp");
		model.addAttribute("title","현재 상영영화");
		return "main/main";
	}
	
	@RequestMapping("shc.do") // 위의 @RequestMapping("movie/") + @RequestMapping("list.do") ==> movie/list.do
	public String movie_shc(Model model,String page)
	{
		if(page==null)
			page="1";
		
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowSize=12;
		int start=rowSize*(curpage-1)+1;
		int end=rowSize*curpage;
		
		map.put("start", start);
		map.put("end", end);
		map.put("type", 2);
		
		// DAO에서 데이터를 받아옴 
		List<MovieVO> list=dao.movieListData(map);
		int totalpage=dao.movieTotalPage(2);
		model.addAttribute("list",list);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("curpage",curpage);
		model.addAttribute("main_jsp","../movie/list.jsp");
		model.addAttribute("title","개봉예정영화");
		return "main/main";
	}
	
	@RequestMapping("box.do") // 위의 @RequestMapping("movie/") + @RequestMapping("list.do") ==> movie/list.do
	public String movie_box(Model model,String page)
	{
		if(page==null)
			page="1";
		
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowSize=12;
		int start=rowSize*(curpage-1)+1;
		int end=rowSize*curpage;
		
		map.put("start", start);
		map.put("end", end);
		map.put("type", 5);
		
		// DAO에서 데이터를 받아옴 
		List<MovieVO> list=dao.movieListData(map);
		int totalpage=dao.movieTotalPage(5);
		model.addAttribute("list",list);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("curpage",curpage);
		model.addAttribute("main_jsp","../movie/list.jsp");
		model.addAttribute("title","박스오피스");
		return "main/main";
	}
	
	@RequestMapping("news.do")
	public String movie_news(Model model)
	{
		List<NewsVO> list=mgr.newsAllData();
		model.addAttribute("list",list);
		model.addAttribute("main_jsp", "../movie/news.jsp");
		return "main/main";
	}
	
	@RequestMapping("detail.do")
	public String movie_detail(Model model,int mno)
	{
		MovieVO vo=dao.movieDetailData(mno);
		
		// main.jsp를 실행하는건데 그 안에 detail.jsp를 include한 것임
		// return "main/main"했으므로 현재 내 위치는 main 폴더 ==> detail.jsp를 include하려면 ../moive해서 폴더 이동해주어야함 
		model.addAttribute("main_jsp","../movie/detail.jsp");
		model.addAttribute("vo",vo);
		return "main/main";
	}
	
	
	
}








