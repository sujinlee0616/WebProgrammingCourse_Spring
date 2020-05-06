package com.sist.spring2;
import java.util.*;

// SpringBasicProject3 > com.sist.spring > EmpDAO와 비교해볼 것 
public class EmpDAO {
	private EmpMapper mapper;

	public void setMapper(EmpMapper mapper) {
		this.mapper = mapper;
	}
	
	public List<EmpVO> empAllData()
	{
		return mapper.empAllData();
	}
	
}
