package com.sist.databoard;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;
import com.sist.manager.RManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("board/")
public class DataBoardController {
	
	@Autowired
	private DataBoardDAO dao;
	
	// R
	@Autowired
	private RManager rm;	
	
	// [글 목록]
	@RequestMapping("list.do")
	public String board_list(Model model, String page)
	{
		// 1. 매개변수 => 사용자의 요청값을 받는다. 
		if (page == null)
			page = "1";
		int curpage = Integer.parseInt(page);
		int rowSize = 10;
		int start = rowSize * (curpage - 1) + 1;
		int end = rowSize * curpage;

		// 2. 요청에 대한 처리
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		List<DataBoardVO> list = dao.databoardListData(map);
		int totalpage = dao.databoardTotalpage();

		// 3. 처리된 결과값 전송
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);

		return "board/list";
		/* 
		 * application-context.xml에서 아래와 같이 코딩했으므로 prefix, suffix가 붙어서 
		 * return "board/list";는   "/board/list.jsp";가 된다.
		 * <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver" 
				p:prefix="/"
				p:suffix=".jsp"
			/>
		 */
	}
	
	// [글쓰기] - 글쓰기 화면만 보여줌
	@RequestMapping("insert.do")
	public String board_insert()
	{
		return "board/insert";		
	}
	
	// [글쓰기] - 실제처리
	@RequestMapping("insert_ok.do")
	public String board_insert_ok(DataBoardVO vo) throws Exception
	{
		List<MultipartFile> list=vo.getFiles();
		String temp1="";
		String temp2="";
		if(list!=null && list.size()>0)
		{
			for(MultipartFile mf:list) // mf: 파일 하나 
			{
				String fn=mf.getOriginalFilename();
				File file=new File("c:\\upload\\"+fn);
				mf.transferTo(file);  // transferTo: 실제 물리경로에 파일을 옮겨준다. 
				// 만약 물리경로에 저장을 안 해주면 임시경로에 잠시 저장되었다가 사라지게 된다. 
				
				// 1.jpg,2.jpg,3.jpg.... 이런식으로 저장한 후 나중에 , 잘라서 쓰자. 
				temp1+=fn+",";
				temp2+=file.length()+",";
			}
			
			// 맨 마지막 ,(콤마) 제거
			temp1=temp1.substring(0,temp1.lastIndexOf(",")); 
			temp2=temp2.substring(0,temp2.lastIndexOf(",")); 
			
			vo.setFilecount(list.size());
			vo.setFilename(temp1);
			vo.setFilesize(temp2);
		}
		else // 파일이 하나도 업로드 되지 않은 경우 
		{
			vo.setFilecount(0);
			vo.setFilename("");
			vo.setFilesize("");
		}
		
		dao.databoardInsert(vo);
		return "redirect:list.do";
	}
	
	// [상세보기]
	@RequestMapping("detail.do")
	public String board_detail(Model model,int no)
	{
		DataBoardVO vo=dao.databoardDetailData(no);
		if(vo.getFilecount()>0)
		{
			StringTokenizer st1=new StringTokenizer(vo.getFilename(),",");
			List<String> fList=new ArrayList<String>();
			while(st1.hasMoreTokens())
			{
				fList.add(st1.nextToken());
			}
			
			StringTokenizer st2=new StringTokenizer(vo.getFilesize(),",");
			List<String> sList=new ArrayList<String>();
			while(st2.hasMoreTokens())
			{
				sList.add(st2.nextToken());
			}
			
			model.addAttribute("fList",fList);
			model.addAttribute("sList",sList);
			
			// fList, sList를 하나의 List로 묶어야지, fList[0]과 sList[0] 이런식으로 index 번호가 같은 fList,sList를 for문으로 한 번에 출력할 수 있다.    
		}
		model.addAttribute("vo",vo);
		
		// ★★★ R로 만든 그래프 출력 ★★★
		try {
			FileWriter fw=new FileWriter("c:\\data\\board.txt");
			fw.write(vo.getContent()+"\r\n");
			fw.close();
			
			rm.rGraph(no);
		} catch (Exception ex) {}
		
		return "board/detail";
		
	}
	
	// [상세보기 > 파일 다운로드] 
	@RequestMapping("download.do")
	public void databoard_download(String fn,HttpServletResponse response)
	{
		try 
		{
			File file=new File("c:\\upload\\"+fn);
			response.setHeader("Content-Disposition","attachement;filename="
					+ URLEncoder.encode(fn,"UTF-8"));
			response.setContentLength((int)file.length());
			
			// 서버 => c:\\upload\\a.jpg 를 읽어서 클라이언트한테 보냄  
			// 서버에서 클라이언트한테 복사해줌 
			BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
			// 클라이언트 영역 
			BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream());
			
			int i=0;
			byte[] buffer=new byte[1024];
			
			// i: byte개수. ==> 몇 바이트 읽었는지 
			while((i=bis.read(buffer, 0, 1024))!=-1) // 문장이 끝나기 전까지 읽어라 
			{
				bos.write(buffer, 0, i); // ==> 파일크기 바이트수만큼 write해라
			}
			bis.close();
			bos.close();
			
		} catch (Exception ex) {}
	}
	
	// [수정하기] - 수정하기 화면 보여줌. 수정 폼에 글 정보를 불러와서 넣어준다
	@RequestMapping("update.do")
	public String update_board(Model model,int no)
	{
		DataBoardVO vo=dao.databoardUpdateData(no);
		model.addAttribute("vo",vo);
		return "board/update";
	}
	
	// [삭제하기] - 화면출력. no 넘겨줘야함. 
	/*
	 *    JSP ==> DispatcherServlet ==> @Controller 
	 *      							=========== @RequestMapping ==> 결과값 출력(JSP) 
	 *      														DAO
	 *      														Model
	 */
	@RequestMapping("delete.do")
	public String delete_board(Model model,int no) // no 보내줘야해서 model 필요함 
	{
		model.addAttribute("no",no);
		return "board/delete";
	}
	
}






