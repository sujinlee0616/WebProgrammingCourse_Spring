package com.sist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

public interface MemberMapper {
	
	// ������ mapper������ resultType => ������, parameterType => �Ű�����, id => �޼ҵ��
	// �Ʒ��� @SelectKey~@Insert �ڵ��� ������ mapper���� ������ ���� �ڵ��ϴ� �Ͱ� ����.
	/* 
	 * <insert id="memberInsert" parameterType="MemberVO">
	 * 		<selectKey keyProperty="no" resultType="int" order="BEFORE">
	 * 			SELECT NVL(MAX(no)+1,1) as no FROM spring_member
	 * 		</selectKey>
	 * </insert>
	 */
	@SelectKey(keyProperty="no",resultType=int.class,before=true,
			statement="SELECT NVL(MAX(no)+1,1) as no FROM spring_member")	
	@Insert("INSERT INTO spring_member VALUES("
			+ "#{no},#{name},#{sex},#{addr},#{tel}"
			)	
	public void memberInsert(MemberVO vo);
	//     ====              ============
	//     resultType        parameterType

	
	
	// �Ʒ��� @Select �ڵ��� ������ mapper���� ������ ���� �ڵ��ϴ� �Ͱ� ����.
	/* 
	 * <select id="memberAllData" parameterType="MemberVO">
	 * 		SELECT * FROM spring_member
	 * </insert>
	 */
	@Select("SELECT * FROM spring_member")
	public List<MemberVO> memberAllData();
	
	
	
	@Select("SELECT * FROM spring_member "
			+ "WHERE no=#{no}")
	public MemberVO memberDetailData(int no);
	
}






