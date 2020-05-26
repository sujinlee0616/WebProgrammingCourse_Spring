package com.sist.dao;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
 * 	Spring에서의 메모리할당
 * 	@Component: 일반 클래스(외부에서 데이터를 읽어온다)
 * 	@Repository: DAO클래스
 * 	@Controller: Model클래스
 * 	@RestController: JSON, XML (ajax,React사용시)
 *  @Service: DAO를 여러개 연결해서 사용 => BI
 *  
 *  Spring에서의 DI
 *  @Autowired
 *  @Resource
 *  @Inject
 *  @Import
 */

@Repository
public class MusicDAO {
	@Autowired
	private MusicMapper mapper;
	
	public List<MusicVO> musicListData()
	{
		return mapper.musicListData();
	}
	public MusicVO musicDetailData(int mno)
	{
		return mapper.musicDetailData(mno);
	}
	public int idCount(String id)
	{
		return mapper.idCount(id);
	}
	public String memberGetPassword(String id)
	{
		return mapper.memberGetPassword(id);
	}
	
}
