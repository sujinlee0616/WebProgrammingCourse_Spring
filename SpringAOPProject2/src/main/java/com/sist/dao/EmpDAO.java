package com.sist.dao;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository // 메모리 할당
public class EmpDAO {
	@Autowired
	private EmpMapper mapper;
	
	public List<EmpVO> empAllData()
	{
		return mapper.empALlData();
	}
	
	
	public List<Integer> empGetMgr()
	{
		return mapper.empGetMgr();
	}
	
	public List<String> empGetJob()
	{
		return mapper.empGetJob();
	}
	
	
	public void empInsert(EmpVO vo)
	{
		mapper.empInsert(vo);
	}
}


/* 
 * <메모리 할당>  
 *  - 넷 다 메모리 할당임. 다만, 어떤 애인지 알려주기 위해서 이름을 다르게 해준 것.
 * @Component: 일반 클래스. 
 * @Controller: MVC 구조에서 나옴. Model 관련
 * @Service: Manager. JSoup 등. 
 * @Repository: DAO, 데이터베이스 관련. 
 */


