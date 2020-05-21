package com.sist.web;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.MusicDAO;
import com.sist.dao.MusicVO;

@Controller
public class MainController {
	
	// [MainController]: 
	// - [방법1]일 때: 화면을 이동시켜줌 ex) 리스트 → 상세보기
	// - [방법2]일 때: 화면 이동 + 데이터 넘겨줌 
	
	@Autowired
	private MusicDAO dao;

	// [리스트]
	@RequestMapping("main/list.do")
	public String main_list()
	{
		// [방법1]: React+Spring 조합으로 데이터를 다루는 방법1 ★★★
		// - MainController: 화면 이동 담당 
		// - MusicController: 데이터 담당 ==> music_data.do 하면 JSON 데이터 넘겨줌 
		// - JSP: ★componentDidMount()에서 music_data.do에 연결해서 데이터 받아옴★ 
		return "main/list";
	}
	
	// [상세보기]
	@RequestMapping("main/detail.do")
	public String main_detail(int mno,Model model)
	{
		// [방법2]: React+Spring 조합으로 데이터를 다루는 방법2 ★★★
		// - MainController: 1) 화면 이동 + 2) 데이터 넘겨줌 
		// - MusicController: 아무 역할X. 주석처리해놔도 동작한다. 
		// - JSP: EL을 사용해서(${json}) MainController로부터 데이터를 받아온다.
		//   ==> componentDidMount()가 데이터 받아오는 거 아님 ==> componentDidMount() 주석처리해도 동작함. 
		MusicVO vo=dao.musicDetailData(mno);
		JSONObject obj=new JSONObject();
		obj.put("mno", vo.getMno());
		obj.put("title", vo.getTitle());
		obj.put("singer", vo.getSinger());
		obj.put("album", vo.getAlbum());
		obj.put("state", vo.getState());
		obj.put("idcrement", vo.getIdcrement());
		obj.put("poster", vo.getPoster());
		obj.put("key", vo.getKey());
		model.addAttribute("json",obj.toJSONString());
		
		model.addAttribute("mno",mno);
		return "main/detail";
	}

	
	// [로그인 화면]
	//@RequestMapping("main/login.do") // 요거 써도 됨. GetMapping인지 PostMappin인지 잘 모르겠으면 걍 RequestMapping쓰면 됨.  
	@GetMapping("main/login.do")
	public String main_login()
	{
		return "main/login";
	}
	
	/*
	 * <내장객체> 
	 * : request, response, session, pageContext, page, exception, config, out
	 *   ==> DispatcherServlet이 가지고 있다. 
	 *  참고) JSP/Servlet 의 내장 객체  
	 *  https://valuefactory.tistory.com/195
	 */
	
	/*
	 * [Spring에서의 Session/Cookie 처리]
	 * 1. Session 처리
	 *  - 매개변수로 받는다
	 * 2. Cookie 처리 
	 *  - 
	 */
	
	// [로그인 실제 처리]
	@PostMapping("main/login_ok.do")
	public String main_login_ok(String id,String pwd,Model model,HttpSession session)  // <== ★★★ session을 매개변수로 받았다 ★★★
	{
		// 이전에는 request를 통해서 session을 넘겼는데, 이제는 request 사용하지 않으니까 DispatcherServlet 통해서 session을 넘겨준다.

		String result="";
		int count=dao.idCount(id);
		if(count==0) // ID가 없는 경우 
		{
			result="NOID";
		}
		else // ID가 존재하는 경우 
		{
			String db_pwd=dao.memberGetPassword(id);
			if(db_pwd.equals(pwd))
			{
				result="OK";
				session.setAttribute("id", id);
			}
			else
			{
				result="NOPWD";
			}
		}
		
		model.addAttribute("result",result);
		return "main/login_ok";
	}
	
	@RequestMapping("main/logout.do")
	public String main_logout(HttpSession session)
	{
		session.invalidate(); // session 해제 
		return "redirect:list.do";
	}
	
}




