package com.sist.spring;

import java.util.*;
import org.apache.ibatis.annotations.Select;

public interface DeptMapper {
	@Select("SELECT * FROM dept")
	public List<DeptVO> deptAllData();  
	
}
