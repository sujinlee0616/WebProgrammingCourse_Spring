package com.sist.databoard;

//RestController: javascript와 연결 시에 사용 ex) AJAX 처리
import com.sist.dao.*;
import java.io.File;
import java.util.StringTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("board/")
public class DataBoardRestController {
	@Autowired
	private DataBoardDAO dao;
	
	// [글 수정] - AJAX 처리 
	@RequestMapping("update_ok.do")
	public String databoard_update_ok(DataBoardVO vo)
	{
		String result="";
		
		// 비밀번호 체크 
		String db_pwd=dao.databoardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd()))
		{
			result="OK";
			dao.databoardUpdate(vo);
		}
		else
		{
			result="NOPWD";
		}
		return result;
	}
	
	// [글 삭제] - AJAX 처리   
	@RequestMapping("delete_ok.do")
	public String databoard_delete_ok(int no,String pwd)
	{
		String result="";
		DataBoardVO vo=dao.databoardFileInfoData(no);
		boolean bCheck=dao.databoardDelete(no, pwd);
		
		// [비번이 맞을 때] (DAO에서 이미 비번 체크했음)
		if(bCheck==true) 
		{
			// 1. 게시글 삭제 - 비번 맞으면 DAO에서 삭제시킴  
			// 2,3
			result="OK";
			try 
			{
				// 2. 파일 삭제 
				// - 게시글에 파일이 있으면(파일 개수가 0개 이상이면) 파일 삭제  
				if(vo.getFilecount()>0) 
				{
					StringTokenizer st=new StringTokenizer(vo.getFilename(),",");
					while(st.hasMoreTokens())
					{
						File file=new File("c:\\upload\\"+st.nextToken());
						file.delete();
					}
				}
				// 3. 상세보기 하단에 R로 그린 이미지 파일 삭제
				// - R은 Linux 기반이라서 경로를 \\ 슬러시 두 개로 줘야한다. 
				File file2=new File("C:\\springDev\\springStudy\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\SpringMVCProject4\\board\\"+no+".png");
				file2.delete();
			} catch (Exception ex) {}
		}
		// [비번이 틀릴 때] (DAO에서 이미 비번 체크했음)
		else
		{
			result="NOPWD";
		}
		
		return result;
	}
	
}










