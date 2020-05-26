package com.sist.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVO {

	private int no;
	private String name;
	private String subject;
	private String content;
	private String pwd;
	private String regdate;
	private int hit;
}
