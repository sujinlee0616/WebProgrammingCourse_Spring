package com.sist.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import com.sist.dao.*;

@Controller
public class MongoBoardController {

	@Autowired
	private BoardDAO dao;
	
	@RequestMapping("board/list.do")
	public String board_list(String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		List<BoardVO> list=dao.boardListData(curpage);
		int totalpage=dao.boardTotalPage();
		
		model.addAttribute("list",list);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("curpage",curpage);
		
		return "board/list";
	}
	
	// [글 작성] - 화면만 띄움
	@RequestMapping("board/insert.do")
	public String board_insert()
	{
		return "board/insert"; 
	}
	
	// [글 작성] - 데이터 처리
	@RequestMapping("board/insert_ok.do")
	public String board_insert_ok(BoardVO vo)
	{
		dao.boardInsert(vo);
		return "redirect:list.do"; 
	}
	
	// [글 상세보기]
	@RequestMapping("board/detail.do")
	public String board_detail(int no,Model model)
	{
		BoardVO vo=dao.boardDetailData(no);
		model.addAttribute("vo",vo);
		return "board/detail";
	}
	
	// [글 수정] - 화면만 띄움
	@RequestMapping("board/update.do")
	public String board_update(int no,Model model)
	{
		BoardVO vo=dao.boardUpdateData(no);
		model.addAttribute("vo",vo);
		return "board/update";
	}
	
	// [글 수정] - 데이터 처리
	@RequestMapping("board/update_ok.do")
	@ResponseBody // <== 얘는 RestController 같은 역할. 사이트 주소/코딩파일이 아니라, ★★★'일반 문자'★★★를 보냄 
	public String board_update_ok(BoardVO vo) // detail.do로 redirect니까 Model 필요X
	{
		String result="";
		boolean bCheck=dao.boardUpdate(vo);
		if(bCheck==true)
		{
			result="<script>location.href=\"detail.do?no="+vo.getNo()+"\";</script>";
		}
		else
		{
			result="<script>alert('비밀번호가 틀립니다.'); history.back(); </script>";
		}
		return result;
	}
	
	// [글 삭제] - 화면만
	@RequestMapping("board/delete.do")
	public String board_delete(int no,Model model)
	{
		model.addAttribute("no",no);
		return "board/delete"; 
	}
	
	// [글 삭제]
	@RequestMapping("board/delete_ok.do")
	@ResponseBody // <=== ★★★얘는 문자 전송임을 알려줘야 (return값이 문자!!!★★★  즉, result.jsp를 찾는게 아니라 그냥 result String을 보냄)  
	public String board_delete_ok(int no,String pwd) // detail.do로 redirect니까 Model 필요X
	{
		String result = "";
		boolean bCheck = dao.boardDelete(no, pwd);
		if (bCheck==true) {
			result="OK";
		} 
		else {
			result="NOPWD";
		}
		return result;
	}
	
}







