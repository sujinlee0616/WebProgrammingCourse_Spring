package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

// @Service: DAO 여러개를 연결해서 사용 ★★★
// - DAO보다 @Service 더 많이 사용한다. 

@Service
public class FoodService {
	@Autowired
	private CategoryDAO cdao;
	@Autowired
	private FoodDAO fdao;
	
	public List<CategoryVO> categoryListData()
	{
		return cdao.categoryListData();
	}
	
}
