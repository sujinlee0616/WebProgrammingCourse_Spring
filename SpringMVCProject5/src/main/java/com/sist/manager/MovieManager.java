package com.sist.manager;

import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.dao.MovieDAO;
import com.sist.dao.MovieVO;

// [���̹� ��ȭ ũ�Ѹ�]
// main ������ MongoDB�� ���� 
public class MovieManager {
	
	public List<String> movieLinkData()
	{
		List<String> list=new ArrayList<String>();
		
		try 
		{
			for(int i=1;i<=40;i++) // �� 40������ 	
			{
				Document doc=Jsoup.connect("https://movie.naver.com/movie/sdb/rank/rmovie.nhn?sel=pnt&date=20200513&page="+i).get();
				Elements link=doc.select("td.title div.tit5 a");
				for(int j=0;j<link.size();j++)
				{
					String strLink=link.get(j).attr("href");
					list.add("https://movie.naver.com"+strLink);
				}
			}
			
		} catch (Exception ex) {}
		
		return list;
	}
	
	public void movieAllData()
	{
		try {
			
			// DAO ����
			MovieDAO dao=new MovieDAO();
			
			List<String> list=movieLinkData();
			int k=1;
			for(String url:list)
			{
				// ���ο�ȭ�� ũ�Ѹ� �Ұ� ==> ũ�Ѹ� �Ұ��� �ֵ� ���Ŀ��� ũ�Ѹ� �ǰ� �ϱ� ���ؼ� for�� �ȿ� try~catch�� �̷��� �޾����� 
				try {
					Document doc=Jsoup.connect(url).get();

					Element title=doc.selectFirst("div.mv_info h3.h_movie a");
					Element poster=doc.selectFirst("div.poster a img");
					Element genre=doc.selectFirst("dl.info_spec span a");
					Element grade=doc.select("dl.info_spec dd").get(3);
					Element director=doc.selectFirst("div.info_spec2 dl.step1 a");
					Element actor=doc.selectFirst("div.info_spec2 dl.step2 a");
					Element story=doc.selectFirst("div.video div.story_area p.con_tx");
					
					MovieVO vo=new MovieVO();
					vo.setMno(Integer.parseInt(url.substring(url.lastIndexOf("=")+1))); 
					// <a href="/movie/bi/mi/basic.nhn?code=174830" title="��������"> ���� href�� = ���� ���� ����
					// ��ȭ ������ȣ 
					System.out.println("================== ��ȭ���� ==================");
					System.out.println("k="+k);
					System.out.println("1.��ȭ��ȣ="+vo.getMno());
					System.out.println("2.����="+title.text());
					System.out.println("3.������="+poster.attr("src"));
					System.out.println("4.�帣="+genre.text());
					System.out.println("5.�������="+grade.text());
					System.out.println("6.����="+director.text());
					System.out.println("7.���="+actor.text());
					System.out.println("8.�ٰŸ�="+story.text());
					
					vo.setTitle(title.text());
					vo.setPoster(poster.attr("src"));
					vo.setGenre(genre.text());
					vo.setGrade(grade.text());
					vo.setDirector(director.text());
					vo.setActor(actor.text());
					vo.setStory(story.text());
					
					// insert
					dao.movieInsert(vo);
					
					k++;
					
				} catch (Exception ex) {}
				
				
			}
		} catch (Exception ex) {}
	}

	public static void main(String[] args) {
		MovieManager m=new MovieManager();
		m.movieAllData();
	}
}





