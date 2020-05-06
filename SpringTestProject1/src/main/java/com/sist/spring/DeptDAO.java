package com.sist.spring;
import java.util.*;

public class DeptDAO {
	private DeptMapper mapper;

	public void setMapper(DeptMapper mapper) {
		this.mapper = mapper;
	}
	
	public List<DeptVO> deptAllData()
	{
		return mapper.deptAllData();
	}
}
