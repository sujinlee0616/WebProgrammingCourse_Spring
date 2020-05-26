package com.sist.dao;

import lombok.Getter;
import lombok.Setter;

/*
 * MNO       NOT NULL NUMBER        
RANK               NUMBER        
TITLE              VARCHAR2(500) 
SINGER             VARCHAR2(200) 
ALBUM              VARCHAR2(300) 
POSTER             VARCHAR2(260) 
IDCREMENT          NUMBER        
STATE              CHAR(6)       
KEY                VARCHAR2(100) 
HIT                NUMBER        

 * 
 */
@Getter
@Setter
public class MusicVO {
	private int mno;
	private int rank;
	private String title;
	private String singer;
	private String album;
	private String poster;
	private int idcrement;
	private String state;
	private String key;
	private int hit;
}
