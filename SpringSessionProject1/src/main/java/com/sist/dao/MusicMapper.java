package com.sist.dao;

import java.util.*;
import org.apache.ibatis.annotations.Select;

/*
 *  public void insert(){}
 *  public void update(){}
 *  
 *  @Transactional 
 *  public void replyInsert()
 *  {
 *  	try
 *  	{
 *  		ssf.openSession();  // 오토커밋 해제 
 *  		insert(); // 정상  ==> 
 *  		update(); // 오류  ==> 
 *  		insert(); // 정상  ==> 
 *  		// 위의 셋 모두가 커밋날리거나 OR 셋 모두 다 날아가지 않아야 한다면 ===>  
 *  		commit(); 
 *  		    
 *  	}catch(Exception ex)
 *  	{
 *  		rollback();
 *  	}
 *  	finally
 *  	{
 *  		conn.setAutoCommit(); // 원래대로 오토커밋으로 돌려놓는다 
 *  	}
 *  }
 *  
 */

public interface MusicMapper {
	
	// 아래의 메소드들을 DAO에서 구현
	
	// [목록출력]
	@Select("SELECT * FROM music_genie ORDER BY mno ASC")
	public List<MusicVO> musicListData();
	
	// [상세보기]
	@Select("SELECT * FROM music_genie "
			+ "WHERE mno=#{mno}")
	public MusicVO musicDetailData(int mno);
	
	// [아이디 존재여부 확인]
	@Select("SELECT COUNT(*) FROM member "
			+ "WHERE id=#{id}")
	public int idCount(String id);
	
	// [비번확인]
	@Select("SELECT pwd FROM member "
			+ "WHERE id=#{id}")
	public String memberGetPassword(String id);
	
	
	
}





