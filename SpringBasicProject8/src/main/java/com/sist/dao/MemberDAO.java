package com.sist.dao;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
 *  Controller: Spring������ Model�� 'Controller'��� �θ� ex) BoardController 
 *  VO
 *  DAO 
 *  Service
 *  Manager
 *  ==================================================================================================
 *  ��׸� ���� Model�̶�� �Ѵ�. �� �� VO�� ������ ������ ��δ� Spring���� �������ش�. (��ü ����/�Ҹ� ���� Spring���� ��������) 
 */

@Repository
public class MemberDAO {
	@Autowired
	private MemberMapper mapper;
	
	public List<MemberVO> memberAllData()
	{
		return mapper.memberAllData();
	}
	
	public MemberVO memberDetailData(int no)
	{
		return mapper.memberDetailData(no);
	}
	
	public void MemberInsert(MemberVO vo)
	{
		mapper.memberInsert(vo);
	}
	
}

/*
 * <����> (Spring�� ��ü ���� �����ֱ�) 
 * 		0. (�޸� �Ҵ� ��) c: �Ǵ� <constructor-arg>�±װ� �ִ��� Ȯ�� 
 * 		1. �޸� �Ҵ� => ��ϵ� ��� Ŭ���� => Map�� ���� 
 * 		2. p: �Ǵ� <property>�±װ� �ִ��� Ȯ��  (DI ���翩�� Ȯ��)
 * 		3. �����ϸ� => setXxx() �޼ҵ忡 ����  
 * 		4. �޼ҵ� DI�� �����ϴ��� ���� Ȯ�� (init-method)
 * 		5. �����ϸ� => �޼ҵ� ȣ�� 
 * 		=======================================================
 * 		6. ���α׷��Ӱ� �ʿ��� �޼ҵ� ȣ��(����ڰ� ���) 
 * 			==> ���α׷� ����, ����Ʈ �̵� ==> Container�� ������. ==> ��� ��ü�� �Ҹ�ȴ�. 
 * 		=======================================================
 * 		7. �޼ҵ� ���翩�� Ȯ�� (destroy-method) 
 * 		8. ��ü �Ҹ� 
 */





