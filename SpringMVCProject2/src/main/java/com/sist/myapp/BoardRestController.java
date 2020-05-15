package com.sist.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sist.dao.*;

@RestController
public class BoardRestController {
	// [Controller vs RestController ������] 
	// - Controller: ���ϸ� 
	// - RestController: �ڡڡڡڽ�ũ��Ʈ ����, JSON ==> �Ϲ� ���ڿ��ڡڡڡ�
	// - ������) @ResponseBody�� ���׷��̵� ����(?)�� @ResController��. 
	
	@Autowired
	private BoardDAO dao; // ����/���� �ÿ� DAO ���ǹǷ� �ٱ����� ���ٳ��� ���� 
	
	@PostMapping("board/update_ok.do")
	public String board_update_ok(BoardVO vo)
	{
		String result="";
		boolean bCheck=dao.boardUpdate(vo); //DAO

		if(bCheck==true) // ��й�ȣ ���� 
		{
			result="<script>location.href=\"detail.do?no="+vo.getNo()+"\";</script>";
		}
		else // ��й�ȣ Ʋ�� 
		{
			result="<script>alert(\"Password Fail!!\"); history.back(); </script>";
		}
		return result;
	}
	
	@PostMapping("board/delete_ok.do")
	public String board_delete_ok(int no,String pwd)
	{
		String result="";
		boolean bCheck=dao.boardDelete(no,pwd); //DAO

		if(bCheck==true) // ��й�ȣ ���� 
		{
			result="<script>location.href=\"list.do\"</script>";
		}
		else // ��й�ȣ Ʋ�� 
		{
			result="<script>alert(\"Password Fail!!\"); history.back(); </script>";
		}
		return result;
	}
	
}




