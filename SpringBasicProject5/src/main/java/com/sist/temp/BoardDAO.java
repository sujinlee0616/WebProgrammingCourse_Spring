package com.sist.temp;

import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO implements MyDAO{

	@Override
	public void select() {
		System.out.println("BoardDAO: select() Call...");
	}

	@Override
	public void insert() {
		System.out.println("BoardDAO: insert() Call...");
	}
	
	

}
