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
	
	// C:\springDev\springStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\SpringMVCProject4\board
	public void rGraph(int no)
	{
		try {
			// [R 연결] ★★★ 
			// - Rserve가 동작하고 있어야 한다. (디폴트:localhost. 특정 IP 지정도 가능.)
			// - Rserver는 R.exe로 돌렸음 
			RConnection rc=new RConnection();
			rc.voidEval("library(rJava)"); // library import 
			rc.voidEval("library(KoNLP)"); // library import 
			rc.voidEval("png(\"C:/springDev/springStudy/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SpringMVCProject4/board/"+no+".png\", width=700, height=450)"); // (��������� �׷��� ���� �̹�����) png ���Ϸ� ���� ������ ��ο� �����϶�
			rc.voidEval("data<-readLines(\"c:/data/board.txt\")");   // voidEval은 send와 비슷한 기능. 
			rc.voidEval("data2<-sapply(data, extractNoun,USE.NAMES = F)");
			rc.voidEval("data3<-unlist(data2)");  // unlist: 2차원 배열을 1차원 배열로 바꿨음 
			rc.voidEval("data3<-gsub(\"[A-Za-z]\",\"[]\",data3)");  // 영어를 공백으로 만들어서 없애라
			rc.voidEval("data3<-gsub(\"[0-9]\",\"[]\",data3)"); // 숫자를 공백으로 만들어서 없애라 (gsub: replace 함수임)
			rc.voidEval("data3<-Filter(function(x){nchar(x)>=2},data3)"); // 2글자 이상만 가지고 와라
			
			rc.voidEval("data4<-table(data3)"); // table: 단어가 몇번씩 언급됐는지 (횟수) 
			rc.voidEval("data5<-head(sort(data4,decreasing = T),10)"); // 개수 많은 순으로 10개 가져와라 
			rc.voidEval("barplot(data5,col=rainbow(15))"); // 그래프 그려달라
			rc.voidEval("dev.off()");
			rc.close(); 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	
	
	}
	
	
	/*public static void main(String[] args) {
		try 
		{
			// [방법1. 데이터 CSV파일로 추출하기]
			// 메인 돌리면 "C:\\upload" 경로에 CSV 파일이 만들어진다. 
			Class.forName("oracle.jdbc.driver.OracleDriver");
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
			fw.close();
			
			
			// [방법2. 데이터 CSV파일로 추출하기]
			RConnection rc=new RConnection();
			rc.voidEval(""); // voidEval("R명령어"): R명령어를 입력하면 send 해준다.
			rc.voidEval("emp<-read.csv(\"c:/upload/emp.csv\",header=T,sep=\",\")"); // header=T(True)
			
			
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}*/

}






