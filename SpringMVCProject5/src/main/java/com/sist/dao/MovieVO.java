package com.sist.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieVO {
	private int mno;
	private String title;
	private String poster;
	private String genre;
	private String grade;
	private String director;
	private String actor;
	private int like;
	private String story;	
}
