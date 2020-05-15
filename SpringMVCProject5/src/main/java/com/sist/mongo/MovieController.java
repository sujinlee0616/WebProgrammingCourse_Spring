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

	/* [Model ��ü]: �ڡڡ� view�� data�� ������ �ڡڡ�
	 * - Controller���� ������ �����͸� ��Ƽ� View�� ������ �� ����ϴ� ��ü.
	 * - Servelt�� request.setAttribute()�� ������ ����.
	 * - addAttribute("Ű", "��") �޼ҵ带 ����Ͽ� ������ ������ ����.
	 * - Model�� ������ forward() (request �����ؾ��ϴϱ�)
	 * - ����) �ڹ� ������-model
	 *        https://lopicit.tistory.com/224 
	 * - ����) Spring - Model�� �̿��Ͽ� View�� ������ �Ѱ��ֱ� - 6
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
		// DAO ����
		List<MovieVO> list=dao.movieFindData(search_keyword);
		model.addAttribute("list",list);
		
		return "movie/find";
	}
	
	@GetMapping("movie/detail.do")
	public String movie_detail(Model model,int mno)
	{
		// DAO ���� 
		// ����� => model�� �ƴ´� (model�� request�� ���� �ֱ� ����) 
		MovieVO vo=dao.movieDetailData(mno);
		model.addAttribute("vo",vo);
		return "movie/detail";
	}
	
	
	
	
}








