package com.sist.recipe;

import java.io.Serializable;
/*
 *   Object������ ���� => ����ȭ(implements Serializable)
 *      => ObjectOutputStream 
 *   ����� �� => ������ȭ 
 *      => ObjectInputStream 
 *      
 *   class A
 *   {
 *     int a=10;
 *   }
 *   
 *   A a=new A();
 *   
 *   A b=new A();
 */
public class RecipeVO implements Serializable{
	private int no;
    private String title;
    private String poster;
    private String chef;
    private String link;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getChef() {
		return chef;
	}
	public void setChef(String chef) {
		this.chef = chef;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
    
}








