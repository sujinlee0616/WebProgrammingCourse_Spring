package com.sist.spring;

import java.util.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.sist.dao.EmpDAO;
import com.sist.dao.EmpVO;

public class MainClass {

	public static void main(String[] args) {
		
		// 1. XML 파일 읽기 
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		// 2. getBean으로 클래스 객체 찾아서 얻어온다 
		EmpDAO dao=(EmpDAO)app.getBean("dao");
		List<EmpVO> list=dao.empAllData();
		
		for(EmpVO vo:list)
		{
			System.out.println(vo.getEname()+" "+vo.getJob());
		}
		

	}

}









