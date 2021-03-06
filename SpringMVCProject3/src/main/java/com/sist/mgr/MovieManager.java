package com.sist.mgr;
// ★★★ JSON 사용 ★★★
// [{키:값,키:값}]

import java.util.*;
import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import javax.net.ssl.HttpsURLConnection;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class MovieManager {
	
	public static void main(String[] args) {
		MovieManager m=new MovieManager();
		m.movieGetJson("searchMainDailyBoxOffice.do");
	}
	
	public String movieGetJson(String url)
	{
		String json="";
		
		try 
		{
			// [방법1]
			URL strUrl=new URL("http://www.kobis.or.kr/kobis/business/main/"+url);
			HttpURLConnection conn=(HttpURLConnection)strUrl.openConnection();
			StringBuffer sb=new StringBuffer();
			if(conn!=null)
			{
				BufferedReader br=new BufferedReader(
						new InputStreamReader(conn.getInputStream(),"UTF-8"));
				while(true)
				{
					String temp=br.readLine();
					if(temp==null)
						break;
					sb.append(temp);
				}
			}
			json=sb.toString();
			
			// [방법2]
			/*Document doc=Jsoup.connect("http://www.kobis.or.kr/kobis/business/main/"+url).get();
			json=doc.toString();*/
			
			System.out.println(json);
			
		} catch (Exception ex) {}
		return json;
	}
}
