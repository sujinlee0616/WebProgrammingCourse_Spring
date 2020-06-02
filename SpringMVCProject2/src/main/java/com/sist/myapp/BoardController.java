package com.sist.myapp;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sist.dao.BoardDAO;
import com.sist.dao.BoardVO;

@Controller
public class BoardController {
	@Autowired
	private BoardDAO dao;
	
	// [글 목록]
	@GetMapping("board/list.do")
	public String board_list(Model model,String page) // int page�� �ָ� �� �� �ڡڡ�
	{
		// 항상 값이 존재한다면 int로 받고,  
		// 어떠한 경우에는 값이 존재하지 않는다면 int로 받으면 안 되고 Strin흥로 받아야 한다. 
		// ==> page는 처음에는 값이 없으므로 String으로 잡아야 h다.
		
		// 1. 매개변수 => 사용자의 요청값을 받는다. 
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=10;
		int start=rowSize*(curpage-1)+1;
		int end=rowSize*curpage;

		// 2. 요청에 대한 처리 
		Map map=new HashMap();
		map.put("start",start);
		map.put("end",end);
		List<BoardVO> list=dao.boardListData(map);
		int totalpage=dao.boardTotalPage();
		
		// 3. 처리된 결과값 전송 
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		
		return "board/list";
	}
	
	// [글 작성_화면만]
	@GetMapping("board/insert.do")
	public String board_insert()
	{
		// 보낼 거 없음
		return "board/insert";  // 화면만 이동하면 됨
	}
	
	// @PostMapping: form일때만. @GetMapping: 나머지.
	// 잘 모르겠으면 그냥 @RequestMapping 써라
	
	// http://localhost/myapp/board/list.do
	// =======================
	// myapp/까지 기본적으로 읽는다.   ==> @GetMapping("board/insert.do")
	
	// [글 작성_실제처리]
	@PostMapping("board/insert_ok.do")
	public String board_insert_ok(BoardVO vo)
	{
		// DAO 연결
		dao.boardInsert(vo);
		
		return "redirect:../board/list.do";
	}
	
	// [상세보기]
	@GetMapping("board/detail.do")
	public String board_detail(Model model,int no) // 보내주는 값이 있으므로 Model 설정, no값 받아야 하므로 int no
	//                         =========== model 안에 request가 존재한다. 
	{
		// DAO 연결
		BoardVO vo=dao.boardDetailData(no);
		model.addAttribute("vo",vo);
		return "board/detail";
	}
	
	// [수정하기]
	@GetMapping("board/update.do")
	public String board_update(Model model,int no)
	{
		BoardVO vo=dao.boardUpdateData(no);
		model.addAttribute("vo",vo);
		return "board/update";
	}
	
	// [삭제하기] -화면만
	@GetMapping("board/delete.do")
	public String board_delete(Model model,int no)
	{
		model.addAttribute("no",no);
		return "board/delete";  // 
	}
	
}





