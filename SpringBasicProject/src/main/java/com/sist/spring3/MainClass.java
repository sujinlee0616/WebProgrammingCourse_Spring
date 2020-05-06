package com.sist.spring3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] data={"sawon.xml","member.xml"};
		// ApplicationContext: 클래스 관리하는 애.
		ApplicationContext app=new ClassPathXmlApplicationContext(data);
		
		Sawon sa=(Sawon)app.getBean("sa");
		sa.display();
		Member mem=(Member)app.getBean("mem");
		mem.display();
	}

}
