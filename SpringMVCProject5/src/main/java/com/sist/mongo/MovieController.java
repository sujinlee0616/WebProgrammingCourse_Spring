package com.sist.mongo;

import java.util.*;
import com.sist.dao.*;

import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MovieController {
	@Autowired
	private MovieDAO dao;

	/* [Model 객체]: ★★★ view에 data를 보낸다 ★★★
	 * - Controller에서 생성한 데이터를 담아서 View로 전달할 때 사용하는 객체.
	 * - Servelt의 request.setAttribute()와 유사한 역할.
	 * - addAttribute("키", "값") 메소드를 사용하여 전달할 데이터 세팅.
	 * - Model이 나오면 forward() (request 유지해야하니까)
	 * - 참고) 자바 스프링-model
	 *        https://lopicit.tistory.com/224 
	 * - 참고) Spring - Model을 이용하여 View에 데이터 넘겨주기 - 6
	 *         https://galid1.tistory.com/504
	 */
	
	@RequestMapping("movie/list.do")
	public String movie_list(Model model,String page)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		List<MovieVO> list=dao.movieListData(curpage);
		int totalpage=dao.movieTotalPage();
		
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);

		return "movie/list";
	}
	
	@PostMapping("movie/find.do")
	public String movie_find(Model model,String search_keyword)
	{
		// DAO 연결
		List<MovieVO> list=dao.movieFindData(search_keyword);
		model.addAttribute("list",list);
		
		return "movie/find";
	}
	
	@GetMapping("movie/detail.do")
	public String movie_detail(Model model,int mno)
	{
		// DAO 연결 
		// 결과값 => model에 싣는다 (model이 request를 갖고 있기 때문) 
		MovieVO vo=dao.movieDetailData(mno);
		model.addAttribute("vo",vo);
		return "movie/detail";
	}
	
	
	
	
}









