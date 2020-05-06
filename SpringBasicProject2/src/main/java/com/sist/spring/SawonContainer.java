package com.sist.spring;

import java.util.*;

public class SawonContainer {
	private List<Sawon> list=new ArrayList<Sawon>();

	public void setList(List<Sawon> list) {
		this.list = list;
	}
	
	public void display()
	{
		for(Sawon s:list)
		{
			System.out.println(s.getSabun()+" "+s.getName()+" "+s.getSex()+" "+s.getAge());
		}
	}
	
}


