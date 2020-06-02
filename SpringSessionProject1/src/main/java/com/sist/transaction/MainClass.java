package com.sist.transaction;

// [트랜잭션]
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MemberDAO dao=new MemberDAO();
		
		// 1 2 
		/*MemberVO vo1=new MemberVO();
		vo1.setNo(1);
		vo1.setName("홍길동");
		vo1.setSex("남자;"); // Error. check 옵션이 '남','여' 이기 때문. 
		
		MemberVO vo2=new MemberVO();
		vo2.setNo(2);
		vo2.setName("심청이");
		vo2.setSex("여");
		
		dao.memberInsert(vo1, vo2);*/
		
		
		
	}

}



//[트랜잭션] - ALL OR NOTHING
/*
 * 선생님 설명) 
 * 
*  public void insert(){}
*  public void update(){}
*  
*  @Transactional 
*  public void replyInsert()
*  {
*  	try
*  	{
*  		// insert/udpate/insert가 모두 날아가거나 또는 모두 날아가지 않아야 한다면? ==> 트랜잭션 프로그램.  
*  		// 그동안은 이런 식으로 트랜잭션을 처리해왔다.
*  		// ==> 이제는 Spring에서 어노테이션을 배웠으니까 각각을 어노테이션을 써서 처리할 수 있고 
*  		// ==> 이제는 Oracle에서 트랜잭션을 배웠으니까 다르게 할 수 있다. 
*  
*  		// 1. 오토커밋 해제시키고  (자바는 기본이 오토커밋이니까..)
*  		ssf.openSession(); 
*   	// 2. 수행해야 할 것 명시해주고 커밋 날림 
*  		insert(); 
*  		update(); 
*  		insert(); 
*  		commit(); ===> Around
*  		    
*  	}catch(Exception ex)
*  	{
*  		// 3. 원래대로 오토커밋으로 돌려놓는다. 
*  		rollback(); // ===> After-Throwing
*  	}
*  	finally
*  	{
*	  // 원래대로 오토커밋으로 돌려놓는다
*  		conn.setAutoCommit();  // ===> After  
*  	}
*  }
*  
*/

/*
 * 우리가 예전에 짰던 코드) 2차 팀 프로젝트 코드에서의 트랜잭션 처리 
 * // [답글쓰기]
	public static void boardReplyInsert(int pno,ReplyBoardVO vo)
	{
		SqlSession session=null;
		
		try
		{
			session=ssf.openSession();
			
			// 1. 엄마 글의 정보를 먼저 읽어 들어온다
			ReplyBoardVO pvo=session.selectOne("getParentInfo",pno); // 상위 글의 데이터 모음(pvo) 가져옴  
			System.out.println("1번 수행 완료");
			
			// 2. 같은 그룹 안에 있는 글들의 group_step 1씩 증가시킨다
			session.update("boardGroupStepIncrement",pvo);
			System.out.println("2번 수행 완료");
			
			// 3. 답글 insert함
			vo.setGroup_id(pvo.getGroup_id());
			vo.setGroup_step(pvo.getGroup_step()+1);
			vo.setGroup_tab(pvo.getGroup_tab()+1);
			vo.setRoot(pno);
			session.insert("boardReplyInsert",vo);
			System.out.println("3번 수행 완료");
			
			// 4.엄마글의 depth(자기 밑에 몇 개를 달고 있는지) 1개 증가시킴
			session.update("parentDetphIncrement",pno);
			System.out.println("4번 수행 완료");
			
			// 커밋 날림 - 1~4 다 정상수행하면 커밋하고 
			session.commit();
			
		}catch(Exception ex)
		{
			System.out.println("boardReplyInsert: "+ex.getMessage());
			session.rollback(); // 1~4 중 하나라도 정상수행 못한다면 롤백 
		}
		finally
		{
			if(session!=null)
				session.close(); 
		}
	}
*/