package com.sist.spring;

import java.util.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
	
	private EmpDAO dao;
	// EmpDAO dao=new EmpDAO();
	// ==> app.xml에서 <bean id="mc" class="com.sist.spring.MainClass" p:dao-ref="dao"/> 
	
	public void setDao(EmpDAO dao) {
		this.dao = dao;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml"); // XML 파싱 끝
		
		// [DL]: Dependency Lookup: 등록된 클래스를 읽어올 때. Main에서는 DI가 아니라 DL 
		//EmpDAO dao=(EmpDAO)app.getBean("dao"); // id=dao인 bean을 찾아옴 ==> DL 
		//List<EmpVO> list=dao.empAllData();
		MainClass mc=(MainClass)app.getBean("mc");
		List<EmpVO> list=mc.dao.empAllData();
		for(EmpVO vo:list)
		{
			System.out.println(vo.getEmpno()+" "
					+vo.getEname()+" "
					+vo.getJob()+" "
					+vo.getHiredate().toString()+" "
					+vo.getSal());
		}
		
	}
	
	

}
