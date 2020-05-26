package com.sist.temp;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MemberDAO dao=new MemberDAO();
		MemberVO vo=new MemberVO();
		vo.setNo(1);
		vo.setName("홍길동");
		vo.setSex("남");
		
		MemberVO vo1=new MemberVO();
		vo1.setNo(2);
		vo1.setName("심청이");
		vo1.setSex("여");
		
		dao.memberInsert(vo, vo1);
	}

}
