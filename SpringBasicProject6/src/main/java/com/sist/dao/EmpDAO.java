package com.sist.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;

/* 
 * DAO는 아래의 값을 받아야 ==> Setter 필요 
 * <bean id="ssf" class="org.mybatis.spring.SqlSessionFactoryBean" 
		p:dataSource-ref="ds"
		p:configLocation="classpath:Config.xml"	
	/> 
 * 
 */

@Repository
public class EmpDAO extends SqlSessionDaoSupport{

	@Autowired // ==> 생성된 sqlSessionFactory 객체를 집어넣어줌 
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	public List<EmpVO> empAllData()
	{
		return getSqlSession().selectList("empAllData");
	}
	
	public EmpVO empFindData(int empno)
	{
		return getSqlSession().selectOne("empFindData",empno);
	}
}







