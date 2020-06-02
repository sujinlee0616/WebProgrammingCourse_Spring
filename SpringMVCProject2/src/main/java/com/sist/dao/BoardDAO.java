package com.sist.dao;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
	@Autowired
	private BoardMapper mapper;
	
	// [글 목록]
	// 1. 목록 가져오기 
	public List<BoardVO> boardListData(Map map)
	{
		return mapper.boardListData(map);
	}
	// 2. 토탈페이지 가져오기 
	public int boardTotalPage()
	{
		return mapper.boardTotalPage();
	}
	
	
	// [글 작성]
	public void boardInsert(BoardVO vo)
	{
		mapper.boardInsert(vo);
	}
	
	
	// [글 상세보기] 
	public BoardVO boardDetailData(int no)
	{
		mapper.hitIncrement(no);
		return mapper.boardDetailData(no);
	}
	
	
	// [글 수정하기]
	// 1. 수정하기 화면에 이전에 입력되어 있던 글의 정보 노출   
	public BoardVO boardUpdateData(int no)
	{
		return mapper.boardDetailData(no);
	}
	// 2. 비번 체크하고, 비번 맞으면 업데이트.
	public boolean boardUpdate(BoardVO vo)
	{
		boolean bCheck=false;
		String db_pwd=mapper.boardGetPwd(vo.getNo());
		
		if(db_pwd.equals(vo.getPwd()))
		{
			bCheck=true;
			mapper.boardUpdate(vo);
		}
		else
		{
			bCheck=false;
		}
		return bCheck;
	}

	
	// [글 삭제하기]
	// 비번 체크하고, 비번 맞으면 삭제.
	public boolean boardDelete(int no, String pwd)
	{
		boolean bCheck=false;
		String db_pwd=mapper.boardGetPwd(no);
		if(db_pwd.equals(pwd))
		{
			bCheck=true;
			mapper.boardDelete(no);
		}
		else
		{
			bCheck=false;
		}
		return bCheck;
	}
	
}







