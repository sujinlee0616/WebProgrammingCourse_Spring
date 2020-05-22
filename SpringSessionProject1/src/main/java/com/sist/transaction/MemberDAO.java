package com.sist.transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// 쌩 JDBC 코딩...

public class MemberDAO {
	private Connection conn;
	private PreparedStatement ps;
	private String URL="jdbc:oracle:thin:@localhostL:1521:XE";
	
	public MemberDAO()
	{
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception ex) {}
	}
	
	public void getConnection()
	{
		try 
		{
			conn=DriverManager.getConnection(URL,"hr","happy");
		} catch (Exception ex) {}
	}

	public void disConnection()
	{
		try 
		{
			if(ps!=null)
				ps.close();
			if(conn!=null)
				conn.close();
		} catch (Exception ex) {}
	}
	
	public List<MemberVO> memberListData()
	{
		/*
		 * MyBatis....
		 * 
		 *  <select id="memberListData" resultType="MeberVO" parameterType="">
		 *  	SELECT * FROM trans_member
		 *  </select>
		 */
		
		List<MemberVO> list=new ArrayList<MemberVO>();
		try {
			getConnection();
			String sql="SELECT * FROM trans_member";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				MemberVO vo=new MemberVO();
				vo.setNo(rs.getInt("no"));
				vo.setName(rs.getString("name"));
				vo.setSex(rs.getString("sex"));
				list.add(vo);
			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			disConnection();
		}
		return list;
	}
	
	
	public void memberInsert(MemberVO vo1,MemberVO vo2)
	{
		try {
			getConnection();
			
			// 1이 실패하면 1,2 둘 수행 안 됨 ==> 1 실패하면 바로 catch 절로 넘어가니까. 
			// 1은 괜찮은데 2가 실패하면 1은 수행되고 2는 수행 안됨   

			// 1. 
			String sql="INSERT INTO trans_member VALUES(?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo1.getNo());
			ps.setString(2, vo1.getName());
			ps.setString(3, vo1.getSex());
			ps.executeUpdate(); // <== 오토커밋임 
			
			// 2. 
			sql="INSERT INTO trans_member VALUES(?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo2.getNo());
			ps.setString(2, vo2.getName());
			ps.setString(3, vo2.getSex());
			ps.executeUpdate(); // <== 오토커밋임 
			
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
	}
	
	
}











