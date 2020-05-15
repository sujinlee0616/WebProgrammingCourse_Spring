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
	
	// [�� ���]
	@GetMapping("board/list.do")
	public String board_list(Model model,String page) // int page�� �ָ� �� �� �ڡڡ�
	{
		// �׻� ���� �����Ѵٸ� int�� �ް�,  
		// ��� ��쿡�� ���� �������� �ʴ´ٸ� int�� ������ �� �ǰ� Strin��� �޾ƾ� �Ѵ�. 
		// ==> page�� ó������ ���� �����Ƿ� String���� ��ƾ� �h��.
		
		// 1. �Ű����� => ������� ��û���� �޴´�. 
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=10;
		int start=rowSize*(curpage-1)+1;
		int end=rowSize*curpage;

		// 2. ��û�� ���� ó�� 
		Map map=new HashMap();
		map.put("start",start);
		map.put("end",end);
		List<BoardVO> list=dao.boardListData(map);
		int totalpage=dao.boardTotalPage();
		
		// 3. ó���� ����� ���� 
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		
		return "board/list";
	}
	
	// [�� �ۼ�_ȭ�鸸]
	@GetMapping("board/insert.do")
	public String board_insert()
	{
		// ���� �� ����
		return "board/insert"; // ȭ�鸸 �̵��ϸ� ��
	}
	
	// @PostMapping: form�϶���. @GetMapping: ������.
	// �� �𸣰����� �׳� @RequestMapping ���
	
	// http://localhost/myapp/board/list.do
	// =======================
	// myapp/���� �⺻������ �д´�.   ==> @GetMapping("board/insert.do")
	
	// [�� �ۼ�_����ó��]
	@PostMapping("board/insert_ok.do")
	public String board_insert_ok(BoardVO vo)
	{
		// DAO ����
		dao.boardInsert(vo);
		
		return "redirect:../board/list.do";
	}
	
	// [�󼼺���]
	@GetMapping("board/detail.do")
	public String board_detail(Model model,int no) // �����ִ� ���� �����Ƿ� Model ����, no�� �޾ƾ� �ϹǷ� int no
	//                         =========== model �ȿ� request�� �����Ѵ�. 
	{
		// DAO ����
		BoardVO vo=dao.boardDetailData(no);
		model.addAttribute("vo",vo);
		return "board/detail";
	}
	
	// [�����ϱ�]
	@GetMapping("board/update.do")
	public String board_update(Model model,int no)
	{
		BoardVO vo=dao.boardUpdateData(no);
		model.addAttribute("vo",vo);
		return "board/update";
	}
	
	// [�����ϱ�] -ȭ�鸸
	@GetMapping("board/delete.do")
	public String board_delete(Model model,int no)
	{
		model.addAttribute("no",no);
		return "board/delete";  // 
	}
	
}




