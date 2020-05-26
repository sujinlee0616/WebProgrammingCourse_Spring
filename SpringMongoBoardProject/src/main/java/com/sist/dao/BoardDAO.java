package com.sist.dao;

import java.text.SimpleDateFormat;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
	
	@Autowired
	private MongoTemplate mt;
	
	public List<BoardVO> boardListData(int page)
	{
		List<BoardVO> list=new ArrayList<BoardVO>();
		Query query=new Query(); // String sql="SELECT ~~~ WHERE no=1" find({no:1})
		int rowSize=10;
		int skip=rowSize*(page-1);
		query.skip(skip).limit(rowSize);
		query.with(new Sort(Sort.Direction.DESC,"no")); // 정렬 // no: 컬럼명 
		list=mt.find(query, BoardVO.class,"board"); // board: 테이블명
		// 'query'라는 쿼리문장을 실행하면, BoardVO에 이 값을 싣어줘라  ==> List<BoardVO>
		// 위의 코딩은 예전 버젼의 selectList와 똑같은 코딩임 
		
		return list;
	}
	
	public int boardTotalPage()
	{
		int total=0;
		Query query=new Query(); // Query(): 조건이 없을 때. 즉, find({})
		int count=(int)mt.count(query, "board");
		// 위의 코딩은 예전 버젼의 'SELECT COUNT(*) FROM board'와 똑같은 코딩임
		total=(int)(Math.ceil(count/10.0));
		return total;
	}
	
	
	public void boardInsert(BoardVO vo)
	{
		Query query=new Query();
		query.with(new Sort(Sort.Direction.DESC,"no")); // with: 정렬
		BoardVO rvo=mt.findOne(query, BoardVO.class,"board");
		vo.setNo(rvo.getNo()+1);
		vo.setRegdate(new SimpleDateFormat("yyyy-MM-dd").format(new Date())); // SYSDATE
		vo.setHit(0);
		mt.insert(vo,"board"); // 'board' 테이블에 'vo'를 넣어라 
	}
	
	// [글 상세보기] - 1. 데이터 가져오고 2. 조회수 증가시킴
	public BoardVO boardDetailData(int no)
	{
		BoardVO vo=new BoardVO();
		BasicQuery query=new BasicQuery("{no:"+no+"}"); // WHERE 문장 (WHERE no=#{no}) ==> BasicQuery 사용하면 편하다. 
		// 값 갖고오고 
		vo=mt.findOne(query, BoardVO.class,"board"); // find: list. ArrayList로 받는다. vs findOne: Object 단위. 한 개.
		// 조회수 증가시키고 
		Update update=new Update();
		update.set("hit",vo.getHit()+1); // 조회수 증가
		mt.updateFirst(query, update, "board"); // "board": 테이블명
		// 증가된 값 다시 받아온다 
		vo=mt.findOne(query, BoardVO.class,"board");
		// 증가된 값 다시 받아온 걸 return 
		return vo;
	}
	
	// [글 수정] - 데이터만 가져옴
	public BoardVO boardUpdateData(int no)
	{
		BoardVO vo=new BoardVO();
		BasicQuery query=new BasicQuery("{no:"+no+"}"); // WHERE 문장 (WHERE no=#{no}) ==> BasicQuery 사용하면 편하다. 
		vo=mt.findOne(query, BoardVO.class,"board");
		return vo;
	}
	
	// [글 수정] - 1.비번 비교 2. 
	public boolean boardUpdate(BoardVO vo)
	{
		boolean bCheck=false;
		// 1. 비번 비교
		// 저장된 비번 갖고옴
		// SELECT pwd from board WHERE no=1
		BasicQuery query=new BasicQuery("{no:"+vo.getNo()+"}"); // WHERE 부분. (WHERE no=1) 
		BoardVO dbvo=mt.findOne(query, BoardVO.class,"board"); // SELECT pwd 부분. 
		//                             ==============
		// 								VO에 값을 채워라
		// 비번 비교 후 맞으면 업뎃
		if(vo.getPwd().equals(dbvo.getPwd()))
		{
			bCheck=true;
			Update update=new Update();
			update.set("name", vo.getName());
			update.set("subject", vo.getSubject());
			update.set("content",vo.getContent());
			mt.updateFirst(query, update, "board");
		}
		return bCheck;
	}
	
	// [글 삭제]
	public boolean boardDelete(int no,String pwd)
	{
		boolean bCheck=false;
		// 1. 비번 비교
		// 저장된 비번 갖고옴
		// SELECT pwd from board WHERE no=1
		BasicQuery query=new BasicQuery("{no:"+no+"}"); // WHERE 부분. (WHERE no=1) 
		BoardVO dbvo=mt.findOne(query, BoardVO.class,"board"); // SELECT pwd 부분. 
		//                             ==============
		// 								VO에 값을 채워라
		// 비번 비교 후 맞으면 업뎃
		if(pwd.equals(dbvo.getPwd()))
		{
			bCheck=true;
			mt.remove(query,"board");
		}
		return bCheck;
	}
	
}









