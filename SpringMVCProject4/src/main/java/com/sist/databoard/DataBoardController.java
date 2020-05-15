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
	
	// [�� ���]
	@RequestMapping("list.do")
	public String board_list(Model model, String page)
	{
		// 1. �Ű����� => ������� ��û���� �޴´�. 
		if (page == null)
			page = "1";
		int curpage = Integer.parseInt(page);
		int rowSize = 10;
		int start = rowSize * (curpage - 1) + 1;
		int end = rowSize * curpage;

		// 2. ��û�� ���� ó��
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		List<DataBoardVO> list = dao.databoardListData(map);
		int totalpage = dao.databoardTotalpage();

		// 3. ó���� ����� ����
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);

		return "board/list";
		/* 
		 * application-context.xml���� �Ʒ��� ���� �ڵ������Ƿ� prefix, suffix�� �پ 
		 * return "board/list";��   "/board/list.jsp";�� �ȴ�.
		 * <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver" 
				p:prefix="/"
				p:suffix=".jsp"
			/>
		 */
	}
	
	// [�۾���] - �۾��� ȭ�鸸 ������
	@RequestMapping("insert.do")
	public String board_insert()
	{
		return "board/insert";		
	}
	
	// [�۾���] - ����ó��
	@RequestMapping("insert_ok.do")
	public String board_insert_ok(DataBoardVO vo) throws Exception
	{
		List<MultipartFile> list=vo.getFiles();
		String temp1="";
		String temp2="";
		if(list!=null && list.size()>0)
		{
			for(MultipartFile mf:list) // mf: ���� �ϳ� 
			{
				String fn=mf.getOriginalFilename();
				File file=new File("c:\\upload\\"+fn);
				mf.transferTo(file); // transferTo: ���� ������ο� ������ �Ű��ش�. 
				// ���� ������ο� ������ �� ���ָ� �ӽð�ο� ��� ����Ǿ��ٰ� ������� �ȴ�. 
				
				// 1.jpg,2.jpg,3.jpg.... �̷������� ������ �� ���߿� , �߶� ����. 
				temp1+=fn+",";
				temp2+=file.length()+",";
			}
			
			// �� ������ ,(�޸�) ����
			temp1=temp1.substring(0,temp1.lastIndexOf(",")); 
			temp2=temp2.substring(0,temp2.lastIndexOf(",")); 
			
			vo.setFilecount(list.size());
			vo.setFilename(temp1);
			vo.setFilesize(temp2);
		}
		else // ������ �ϳ��� ���ε� ���� ���� ��� 
		{
			vo.setFilecount(0);
			vo.setFilename("");
			vo.setFilesize("");
		}
		
		dao.databoardInsert(vo);
		return "redirect:list.do";
	}
	
	// [�󼼺���]
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
			
			// fList, sList�� �ϳ��� List�� �������, fList[0]�� sList[0] �̷������� index ��ȣ�� ���� fList,sList�� for������ �� ���� ����� �� �ִ�.  
		}
		model.addAttribute("vo",vo);
		
		// �ڡڡ� R�� ���� �׷��� ��� �ڡڡ�
		try {
			FileWriter fw=new FileWriter("c:\\data\\board.txt");
			fw.write(vo.getContent()+"\r\n");
			fw.close();
			
			rm.rGraph(no);
		} catch (Exception ex) {}
		
		return "board/detail";
		
	}
	
	// [�󼼺��� > ���� �ٿ�ε�] 
	@RequestMapping("download.do")
	public void databoard_download(String fn,HttpServletResponse response)
	{
		try 
		{
			File file=new File("c:\\upload\\"+fn);
			response.setHeader("Content-Disposition","attachement;filename="
					+ URLEncoder.encode(fn,"UTF-8"));
			response.setContentLength((int)file.length());
			
			// ���� => c:\\upload\\a.jpg �� �о Ŭ���̾�Ʈ���� ����  
			// �������� Ŭ���̾�Ʈ���� �������� 
			BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
			// Ŭ���̾�Ʈ ���� 
			BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream());
			
			int i=0;
			byte[] buffer=new byte[1024];
			
			// i: byte����. ==> �� ����Ʈ �о����� 
			while((i=bis.read(buffer, 0, 1024))!=-1) // ������ ������ ������ �о�� 
			{
				bos.write(buffer, 0, i); // ==> ����ũ�� ����Ʈ����ŭ write�ض�
			}
			bis.close();
			bos.close();
			
		} catch (Exception ex) {}
	}
	
	// [�����ϱ�] - �����ϱ� ȭ�� ������. ���� ���� �� ������ �ҷ��ͼ� �־��ش�
	@RequestMapping("update.do")
	public String update_board(Model model,int no)
	{
		DataBoardVO vo=dao.databoardUpdateData(no);
		model.addAttribute("vo",vo);
		return "board/update";
	}
	
	// [�����ϱ�] - ȭ�����. no �Ѱ������. 
	/*
	 *    JSP ==> DispatcherServlet ==> @Controller 
	 *      							=========== @RequestMapping ==> ����� ���(JSP) 
	 *      														DAO
	 *      														Model
	 */
	@RequestMapping("delete.do")
	public String delete_board(Model model,int no) // no ��������ؼ� model �ʿ��� 
	{
		model.addAttribute("no",no);
		return "board/delete";
	}
	
}





