package com.sist.dao;

import java.util.*;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// 게시판 DAO 따로, 댓글 DAO 따로 만들 예정 ==> DAO가 여러개 ==> @Service
// DAO ==> @Repository
// 일반 클래스 ==> @Component

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
	
	public List<DataBoardVO> databoardListData(Map map)
	{
		return mapper.databoardListData(map);
	}
	
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

	
	
}







