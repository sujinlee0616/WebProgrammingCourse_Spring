package com.sist.spring2;

import org.apache.ibatis.annotations.Select;
import java.util.*;

// MyBatis인데 XML 없이 코딩 
public interface EmpMapper {
	
	@Select("SELECT * FROM emp")
	public List<EmpVO> empAllData();  
	//     ==========            ==
	//     ResultType            parameterType
	
	// 위의 코드는 아래의 XML 코드와 같은 역할 
	// <select id="empAllData" resyltType="EmpVO">SELECT * FROM emp</select>
	
	
	
	
}
