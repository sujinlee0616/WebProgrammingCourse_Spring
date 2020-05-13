package com.sist.manager;

import java.util.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.stereotype.Component;

/*
 *  	Spring ==> 수집 (Hadoop) ==> mapreduce, spark: 분석 ==> R에서 만든 그래프 ==> React로 출력  
 *                                  ===============							  ===========
 *                                   mongodb 사용							  Android에서도 출력 가능(Kotlin 또는 React-Native 이용)
 */

@Component
public class RManager {
	
	public static void main(String[] args) {
		try 
		{
			// [데이터 CSV파일로 추출하기]
			// 메인 돌리면 "C:\\upload" 경로에 CSV 파일이 만들어진다. 
			/*Class.forName("oracle.jdbc.driver.OracleDriver");
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			Connection conn=DriverManager.getConnection(url,"hr","happy");
			String sql="SELECT empno,ename,job,sal FROM emp";
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			String temp="empno,ename,job,sal\n";
			while(rs.next())
			{
				temp+=rs.getInt(1)+","+rs.getString(2)+","+rs.getString(3)+","
						+rs.getInt(4)+"\n";
			}
			rs.close();
			ps.close();
			conn.close();
			temp=temp.substring(0,temp.lastIndexOf("\n"));
			
			FileWriter fw=new FileWriter("c:\\upload\\emp.csv");
			fw.write(temp);
			fw.close();*/
			
			
			// []
			RConnection rc=new RConnection();
			rc.voidEval(""); // voidEval("R명령어"): R명령어를 입력하면 send 해준다.
			rc.voidEval("emp<-read.csv(\"c:/upload/emp.csv\",header=T,sep=\",\")"); // header=T(True)
			
			
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}

}






