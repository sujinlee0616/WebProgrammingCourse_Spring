package com.sist.dao;

import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface DataBoardMapper {
	
	// [글 목록] 
	@Select("SELECT no,subject,name,regdate,hit,num "
			+ "FROM (SELECT no,subject,name,regdate,hit,rownum as num "
			+ "FROM (SELECT no,subject,name,regdate,hit "
			+ "FROM spring_databoard ORDER BY no DESC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<DataBoardVO> databoardListData(Map map);
	
	// [글목록] total page
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM spring_databoard")
	public int databoardTotalpage();
	
	// [글쓰기]
	@SelectKey(keyProperty="no", resultType=int.class, before=true,
			statement="SELECT NVL(MAX(no)+1,1) as no FROM spring_databoard")
	@Insert("INSERT INTO spring_databoard VALUES("
			+ "#{no},#{name},#{subject},#{content},#{pwd},"
			+ "SYSDATE,0,#{filename},#{filesize},#{filecount})")
	public void databoardInsert(DataBoardVO vo);
	
	// [상세보기] - 1. 조회수 증가
	@Update("UPDATE spring_databoard SET "
			+ "hit=hit+1 "
			+ "WHERE no=#{no}")
	public void hitIncrement(int no);
	// 2. 글 내용 노출 
	@Select("SELECT no,name,subject,content,regdate,hit,filename,filesize,filecount "
			+ "FROM spring_databoard "
			+ "WHERE no=#{no}")
	public DataBoardVO databoardDetailData(int no);
	
	// [수정하기] - 1. Password 체크 
	@Select("SELECT pwd FROM spring_databoard "
			+ "WHERE no=#{no}")
	public String databoardGetPassword(int no);
	// 2. 글 내용 수정
	@Update("UPDATE spring_databoard SET "
			+ "name=#{name},subject=#{subject},content=#{content} "
			+ "WHERE no=#{no}")
	public void databoardUpdate(DataBoardVO vo);
	
	// [삭제하기] - 1. 삭제하려는 게시글에 파일이 업로드 되어 있는지 확인 
	@Select("SELECT filename,filecount FROM spring_databoard "
			+ "WHERE no=#{no}")
	public DataBoardVO databoardFileInfoData(int no);
	// 2.게시판 데이터 삭제
	@Delete("DELETE FROM spring_databoard "
			+ "WHERE no=#{no}")
	public void databoardDelete(int no);
	
	 
	
}




