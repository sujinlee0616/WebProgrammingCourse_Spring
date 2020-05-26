package com.sist.dao;

import java.util.*;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//게시판 DAO 따로, 댓글 DAO 따로 만들 예정 ==> DAO가 여러개 ==> @Service
//DAO ==> @Repository
//일반 클래스 ==> @Component

@Repository
public class DataBoardDAO {
	
	@Autowired
	private DataBoardMapper mapper;
	
	/*
	 * @Autowired: id 지정 불가. ==> 동일한 class를 가진 bean들을 id로 구분할 수 없다. ==> 이런 경우 오류 뱉음
	 * ==> @Autowired + @Qualifire("id") OR @Resource("id") 사용 
	 * ex) application-datasource.xml에서 아래와 같이 동일한 class가진 bean 두 개가 있음 
	 *     <bean id="mapper1" class="org.mybatis.spring.mapper.MapperFactoryBean"  .... />
	 *     <bean id="mapper2" class="org.mybatis.spring.mapper.MapperFactoryBean"  .... />
	 *     ==> 이 두 bean을 구분하기 위해서는 @Autowired 단독으로만 쓸 수 없고
	 *         1. @Autowired + @Qualifire("id")
	 *         2. @Resource("id")
	 *         요렇게 써야 한다.      
	 */
	
	// [글 목록] - 글 데이터 
	public List<DataBoardVO> databoardListData(Map map)
	{
		return mapper.databoardListData(map);
	}
	
	// [글 목록] - 총페이지
	public int databoardTotalpage()
	{
		return mapper.databoardTotalpage();
	}
	
	public void databoardInsert(DataBoardVO vo)
	{
		mapper.databoardInsert(vo);
	}
	
	public DataBoardVO databoardDetailData(int no)
	{
		mapper.hitIncrement(no);
		return mapper.databoardDetailData(no);
	}

	// [글 수정] - 수정 폼에 글 정보를 불러와서 넣어준다
	public DataBoardVO databoardUpdateData(int no)
	{
		return mapper.databoardDetailData(no);
	}
	
	// [글 수정] - 비번체크 
	public String databoardGetPassword(int no)
	{
		return mapper.databoardGetPassword(no);
	}
	
	// [글 수정] - 데이터 수정 
	public void databoardUpdate(DataBoardVO vo)
	{
		mapper.databoardUpdate(vo);
	}
	
	// [글 삭제] - 비번 맞으면 삭제 
	public boolean databoardDelete(int no, String pwd)
	{
		boolean bCheck=false;
		String db_pwd=mapper.databoardGetPassword(no);
		if(db_pwd.equals(pwd))
		{
			mapper.databoardDelete(no);
			bCheck=true;
		}		
		return bCheck;
	}
	// [글 삭제] - 파일 있는지 체크
	public DataBoardVO databoardFileInfoData(int no)
	{
		return mapper.databoardFileInfoData(no);
	}


}







