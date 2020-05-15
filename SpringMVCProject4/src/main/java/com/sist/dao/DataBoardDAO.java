package com.sist.dao;

import java.util.*;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// �Խ��� DAO ����, ��� DAO ���� ���� ���� ==> DAO�� ������ ==> @Service
// DAO ==> @Repository
// �Ϲ� Ŭ���� ==> @Component

@Repository
public class DataBoardDAO {
	
	@Autowired
	private DataBoardMapper mapper;
	
	/*
	 * @Autowired: id ���� �Ұ�. ==> ������ class�� ���� bean���� id�� ������ �� ����. ==> �̷� ��� ���� ����
	 * ==> @Autowired + @Qualifire("id") OR @Resource("id") ��� 
	 * ex) application-datasource.xml���� �Ʒ��� ���� ������ class���� bean �� ���� ���� 
	 *     <bean id="mapper1" class="org.mybatis.spring.mapper.MapperFactoryBean"  .... />
	 *     <bean id="mapper2" class="org.mybatis.spring.mapper.MapperFactoryBean"  .... />
	 *     ==> �� �� bean�� �����ϱ� ���ؼ��� @Autowired �ܵ����θ� �� �� ����
	 *         1. @Autowired + @Qualifire("id")
	 *         2. @Resource("id")
	 *         �䷸�� ��� �Ѵ�.      
	 */
	
	// [�� ���] - �� ������ 
	public List<DataBoardVO> databoardListData(Map map)
	{
		return mapper.databoardListData(map);
	}
	
	// [�� ���] - ��������
	public int databoardTotalpage()
	{
		return mapper.databoardTotalpage();
	}
	
	public void databoardInsert(DataBoardVO vo)
	{
		mapper.databoardInsert(vo);
	}
	
	public DataBoardVO databoardDetailData(int no)
	{
		mapper.hitIncrement(no);
		return mapper.databoardDetailData(no);
	}

	// [�� ����] - ���� ���� �� ������ �ҷ��ͼ� �־��ش�
	public DataBoardVO databoardUpdateData(int no)
	{
		return mapper.databoardDetailData(no);
	}
	
	// [�� ����] - ���üũ 
	public String databoardGetPassword(int no)
	{
		return mapper.databoardGetPassword(no);
	}
	
	// [�� ����] - ������ ���� 
	public void databoardUpdate(DataBoardVO vo)
	{
		mapper.databoardUpdate(vo);
	}
	
	// [�� ����] - ��� ������ ���� 
	public boolean databoardDelete(int no, String pwd)
	{
		boolean bCheck=false;
		String db_pwd=mapper.databoardGetPassword(no);
		if(db_pwd.equals(pwd))
		{
			mapper.databoardDelete(no);
			bCheck=true;
		}		
		return bCheck;
	}
	// [�� ����] - ���� �ִ��� üũ
	public DataBoardVO databoardFileInfoData(int no)
	{
		return mapper.databoardFileInfoData(no);
	}


}






