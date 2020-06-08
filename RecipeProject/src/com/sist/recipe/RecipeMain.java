package com.sist.recipe;
import java.io.*;
import java.net.URL;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/*
 *  <div class="col-xs-4">
        <a class="thumbnail" href="/recipe/6907357">
			            <span class="thumbnail_over"><img src="http://recipe1.ezmember.co.kr/img/thumb_over.png"></span>
          <img src="http://recipe1.ezmember.co.kr/cache/recipe/2019/02/26/d7f2629195c05e84979b4b02af7f1a8c1_m.jpg" style="width:275px; height:275px;">
          <div class="caption">
            <h4 class="ellipsis_title2">���ڻ� �������� �ְ�</h4>
            <p>by �����޺�</p>
          </div>
        </a>
          <div style="position:absolute;top:365px;width:100%;text-align:right;right:20px;"><span style="color:#74b243;font-size:10px;" class="glyphicon glyphicon-certificate"></span></div>
    </div>

 */
public class RecipeMain {
	RecipeDAO dao=new RecipeDAO();
	
	public void recipeAllData()
	{
	     
	     int k=1;
	     try
	     {
	    	 for(int i=1;i<=3357;i++)
	    	 {
	    		 Document doc=Jsoup.connect("http://www.10000recipe.com/recipe/list.html?order=accuracy&page="+i).get();
	    		 Elements title=doc.select("div.caption h4.ellipsis_title2");
	    		 Elements poster=doc.select("img[src*=/recipe/]");
	    		 // 여러개의 img 중, src에 'recipe'가 들어간 img만 가지고 온다 
	    		 Elements chef=doc.select("div.caption p");
	    		 Elements link=doc.select("div.col-xs-3 a.thumbnail");
	    		 
	    		 for(int j=0;j<title.size();j++)
	    		 {
	    			try
	    			{
		    			 RecipeVO vo=new RecipeVO();
		    			 // vo.setNo(k); // NVL로 시퀀스 만들었으니까 setNo필요X
		    			 vo.setTitle(title.get(j).text());
		    			 vo.setPoster(poster.get(j).attr("src"));
		    			 vo.setChef(chef.get(j).text());
		    			 vo.setLink(link.get(j).attr("href"));
		    			 System.out.println("번호:"+k);
		    			 System.out.println("Title:"+vo.getTitle());
		    			 System.out.println("Chef:"+vo.getChef());
		    			 System.out.println("Poster:"+vo.getPoster());
		    			 System.out.println("Link:"+vo.getLink());
		    			 System.out.println("k="+k);
		    			 
		    			 //dao.recipeInsert(vo);
		    			 RecipeDAO.recipeInsert(vo);
		    			 //recipeDetailData(vo.getLink(),k);
		    			 k++;
		    			 Thread.sleep(50);
		    			 
	    			}catch(Exception e) {e.printStackTrace();}
	    		 }
	    	 }
	    	 System.out.println("End...");
	     }catch(Exception ex){ex.printStackTrace();}
	    
	}
	
	public ArrayList<ChefVO> chefAllData()
	{
		ArrayList<ChefVO> list=new ArrayList<ChefVO>();
		try
		{
			int k=1;
			for(int i=1;i<=23;i++)
			{
				// https://www.10000recipe.com/chef/chef_list.html?order=chef_no_follower&term=all&page=2
				Document doc=Jsoup.connect("http://www.10000recipe.com/chef/chef_list.html?order=chef_no_follower&term=all&page="+i).get();
				Elements poster=doc.select("div.list_mem3 a.mem_pic img");
				Elements chef=doc.select("div.list_cont4 a");
				Elements mem_cont1=doc.select("span.mem_cont1");
				Elements mem_cont3=doc.select("span.mem_cont3");
				Elements mem_cont7=doc.select("span.mem_cont7");
				Elements mem_cont2=doc.select("span.mem_cont2");
				
				for(int j=0;j<poster.size();j++)
				{
					try
					{
						ChefVO vo=new ChefVO();
						vo.setPoster(poster.get(j).attr("src"));
						vo.setChef(chef.get(j).text());
						vo.setMem_cont1(mem_cont1.get(j).text());
						vo.setMem_cont3(mem_cont3.get(j).text());
						vo.setMem_cont7(mem_cont7.get(j).text());
						vo.setMem_cont2(mem_cont2.get(j).text());
						System.out.println("Poster:"+vo.getPoster());
						System.out.println("Chef:"+vo.getChef());
						System.out.println("Mem-cont1:"+vo.getMem_cont1());
						System.out.println("Mem-cont3:"+vo.getMem_cont3());
						System.out.println("Mem-cont7:"+vo.getMem_cont7());
						System.out.println("Mem-cont2:"+vo.getMem_cont2());
						System.out.println("k="+k);
						System.out.println("---------------------------------------------------------");

						RecipeDAO.chefInsert(vo);

					    k++;
					    Thread.sleep(50);
					    
					    
					}catch(Exception ex){}
					//list.add(vo);
				}
			}
		}catch(Exception ex){ex.printStackTrace();}
		return list;
	}
	
	public RecipeDetailVO recipeDetailData(String url,int no)
    {
    	RecipeDetailVO vo=new RecipeDetailVO();
    	// http://www.10000recipe.com/recipe/6907454
    	int k=1;
    	try
    	{
    		Document doc=Jsoup.connect("http://www.10000recipe.com"+url).get();
    		Element poster=doc.selectFirst("div.centeredcrop img");// doc.select("").get(0)
    		/*
    		 *  <div class="view2_summary">
            <h3>�ʰ���, �ʽ��ǵ�, �������� ��̳� ���� �����</h3>
    		 */
    		Element title=doc.selectFirst("div.view2_summary h3");
    		Element chef=doc.selectFirst("div.profile_cont p.cont_name");
    		Element chef_poster=doc.selectFirst("div.profile_pic img");
    		Element chef_profile=doc.selectFirst("div.profile_cont p.cont_intro");
    		Element content=doc.selectFirst("div.view2_summary_in");
    		Elements foodmake=doc.select("div.view_step_cont");
    		Element info1=doc.selectFirst("span.view2_summary_info1");
    		Element info2=doc.selectFirst("span.view2_summary_info2");
    		Element info3=doc.selectFirst("span.view2_summary_info3");
    		
    		String food="";
    		for(int i=0;i<foodmake.size();i++) // 요리순서
    		{
    			food+=(i+1)+"."+foodmake.get(i).text()+"\n";
    		}
    		vo.setPoster(poster.attr("src"));
    		vo.setChef(chef.text());
    		vo.setChef_poster(chef_poster.attr("src"));
    		vo.setChef_profile(chef_profile.text());
    		vo.setContent(content.text());
    		vo.setFoodmake(food);
    		vo.setTitle(title.text());
    		vo.setInfo1(info1.text());
    		vo.setInfo2(info2.text());
    		vo.setInfo3(info3.text());
    		vo.setNo(no);
    		
    		//dao.recipeDetailData(vo);
    		System.out.println("Title:"+vo.getTitle());
    		System.out.println("Chef:"+vo.getChef());
    		System.out.println("Content:"+vo.getContent());
    		System.out.println("Foodmake:"+vo.getFoodmake());
    		System.out.println("Info1:"+vo.getInfo1());
    		System.out.println("Info2:"+vo.getInfo2());
    		System.out.println("Info3:"+vo.getInfo3());
    		
    		RecipeDAO.recipeDetailInsert(vo);
    		System.out.println("k="+k);
    		k++;
    		
    		Thread.sleep(50);
    	}catch(Exception ex){}
    	return vo;
    }
	
	public void test() throws Exception
	{
		URL location = this.getClass().getResource("/src");
		String path = location.getPath();
		System.out.println(path);
		String rightPath = path.substring(1, path.lastIndexOf("build"))+"src";
		System.out.println(rightPath);
	}
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		RecipeMain rm=new RecipeMain();
		
		// rm.test();
		// rm.chefAllData();
		//rm.recipeAllData();
		
		List<RecipeVO> list = RecipeDAO.recipeData();
		for(RecipeVO vo:list)
		{
			rm.recipeDetailData(vo.getLink(), vo.getNo());
		}		
		
		
	}
	
}












