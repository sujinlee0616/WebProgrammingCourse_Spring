package com.sist.databoard;

// RestController: javascript�� ���� �ÿ� ��� ex) AJAX ó��
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
	
	// [�� ����] - AJAX ó�� 
	@RequestMapping("update_ok.do")
	public String databoard_update_ok(DataBoardVO vo)
	{
		String result="";
		
		// ��й�ȣ üũ 
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
	
	// [�� ����] - AJAX ó��  
	@RequestMapping("delete_ok.do")
	public String databoard_delete_ok(int no,String pwd)
	{
		String result="";
		DataBoardVO vo=dao.databoardFileInfoData(no);
		boolean bCheck=dao.databoardDelete(no, pwd);
		
		// [����� ���� ��] (DAO���� �̹� ��� üũ����)
		if(bCheck==true) 
		{
			// 1. �Խñ� ���� - ��� ������ DAO���� ������Ŵ  
			// 2,3
			result="OK";
			try 
			{
				// 2. ���� ���� 
				// - �Խñۿ� ������ ������(���� ������ 0�� �̻��̸�) ���� ����  
				if(vo.getFilecount()>0) 
				{
					StringTokenizer st=new StringTokenizer(vo.getFilename(),",");
					while(st.hasMoreTokens())
					{
						File file=new File("c:\\upload\\"+st.nextToken());
						file.delete();
					}
				}
				// 3. �󼼺��� �ϴܿ� R�� �׸� �̹��� ���� ����
				// - R�� Linux ����̶� ��θ� \\ ������ �� ���� ����Ѵ�. 
				File file2=new File("C:\\springDev\\springStudy\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\SpringMVCProject4\\board\\"+no+".png");
				file2.delete();
			} catch (Exception ex) {}
		}
		// [����� Ʋ�� ��] (DAO���� �̹� ��� üũ����)
		else
		{
			result="NOPWD";
		}
		
		return result;
	}
	
}









