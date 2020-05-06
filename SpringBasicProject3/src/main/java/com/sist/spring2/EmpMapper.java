package com.sist.spring2;

import org.apache.ibatis.annotations.Select;
import java.util.*;

// MyBatis�ε� XML ���� �ڵ� 
public interface EmpMapper {
	
	@Select("SELECT * FROM emp")
	public List<EmpVO> empAllData();  
	//     ==========            ==
	//     ResultType            parameterType
	
	// ���� �ڵ�� �Ʒ��� XML �ڵ�� ���� ���� 
	// <select id="empAllData" resyltType="EmpVO">SELECT * FROM emp</select>
	
	
	
	
}
