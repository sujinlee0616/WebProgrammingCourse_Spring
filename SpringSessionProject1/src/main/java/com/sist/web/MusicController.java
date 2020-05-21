package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.dao.*;

@RestController  // @RestController: 일반 문자열,JSON,XML  <=====>  @Controller: 파일명 또는 .do 
public class MusicController {
	
	// [MusicController]: 데이터를 뿌려줌 
	
	@Autowired
	private MusicDAO dao;
	
	// [목록 데이터]
	// main/music_data.do ==> JSON 출력됨 
	@RequestMapping("main/music_data.do")
	public String main_list()
	{
		String result="";
		List<MusicVO> list=dao.musicListData();
		JSONArray arr=new JSONArray();
		// Q) 왜 JSON Array 사용? 
		// A) 여러개 넘겨야 하니까..^^...
		// [참고] JSON
		// https://developer.mozilla.org/ko/docs/Learn/JavaScript/Objects/JSON
		// - 데이터 하나: {key:value}
		// - 데이터 여러개(배열): [{key:value},{key:value},{key:value}, ... , {key:value}]
		for(MusicVO vo:list)
		{
			JSONObject obj=new JSONObject();
			obj.put("mno", vo.getMno());
			obj.put("title", vo.getTitle());
			obj.put("singer", vo.getSinger());
			obj.put("album", vo.getAlbum());
			obj.put("state", vo.getState());
			obj.put("idcrement", vo.getIdcrement());
			obj.put("poster", vo.getPoster());
			arr.add(obj);
		}
		
		result=arr.toJSONString();
		System.out.println(result);
		return result;
	}
	
	// [상세보기 데이터]
//	@RequestMapping("main/detail_data.do")
//	public String detail_data(int mno)
//	{
//		MusicVO vo=dao.musicDetailData(mno);
//		JSONObject obj=new JSONObject();
//		obj.put("mno", vo.getMno());
//		obj.put("title", vo.getTitle());
//		obj.put("singer", vo.getSinger());
//		obj.put("album", vo.getAlbum());
//		obj.put("state", vo.getState());
//		obj.put("idcrement", vo.getIdcrement());
//		obj.put("poster", vo.getPoster());
//		obj.put("key", vo.getKey()); // key: 동영상 key ★★★
//		return obj.toJSONString();
//		
//	}
	
	
	
}







