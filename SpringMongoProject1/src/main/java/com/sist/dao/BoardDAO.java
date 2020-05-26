package com.sist.dao;

import java.text.SimpleDateFormat;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
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

}









