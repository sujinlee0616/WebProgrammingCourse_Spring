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
 *  	Spring ==> ���� (Hadoop) ==> mapreduce, spark: �м� ==> R���� ���� �׷��� ==> React�� ���  
 *                                  ===============							  ===========
 *                                   mongodb ���							  Android������ ��� ����(Kotlin �Ǵ� React-Native �̿�)
 */

@Component
public class RManager {
	
	// C:\springDev\springStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\SpringMVCProject4\board
	public void rGraph(int no)
	{
		try {
			// [R ����] �ڡڡ� 
			// - Rserve�� �����ϰ� �־�� �Ѵ�. (����Ʈ:localhost. Ư�� IP ������ ����.)
			// - Rserver�� R.exe�� ������ 
			RConnection rc=new RConnection();
			rc.voidEval("library(rJava)"); // library import 
			rc.voidEval("library(KoNLP)"); // library import 
			rc.voidEval("png(\"C:/springDev/springStudy/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SpringMVCProject4/board/"+no+".png\", width=700, height=450)"); // (��������� �׷��� ���� �̹�����) png ���Ϸ� ���� ������ ��ο� �����϶�
			rc.voidEval("data<-readLines(\"c:/data/board.txt\")");   // voidEval�� send�� ����� ���. 
			rc.voidEval("data2<-sapply(data, extractNoun,USE.NAMES = F)");
			rc.voidEval("data3<-unlist(data2)"); // unlist: 2���� �迭�� 1���� �迭�� �ٲ��� 
			rc.voidEval("data3<-gsub(\"[A-Za-z]\",\"[]\",data3)"); // ��� �������� ���� ���ֶ�
			rc.voidEval("data3<-gsub(\"[0-9]\",\"[]\",data3)"); // ���ڸ� �������� ���� ���ֶ� (gsub: replace �Լ���)
			rc.voidEval("data3<-Filter(function(x){nchar(x)>=2},data3)"); // 2���� �̻� ������ �Ͷ�
			
			rc.voidEval("data4<-table(data3)"); // table: �ܾ ����� ��޵ƴ��� (Ƚ��) 
			rc.voidEval("data5<-head(sort(data4,decreasing = T),10)"); // ���� ���� ������ 10�� �����Ͷ� 
			rc.voidEval("barplot(data5,col=rainbow(15))"); // �׷��� �׷��޶�
			rc.voidEval("dev.off()");
			rc.close(); 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	
	
	}
	
	
	/*public static void main(String[] args) {
		try 
		{
			// [���1. ������ CSV���Ϸ� �����ϱ�]
			// ���� ������ "C:\\upload" ��ο� CSV ������ ���������. 
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
			
			
			// [���2. ������ CSV���Ϸ� �����ϱ�]
			RConnection rc=new RConnection();
			rc.voidEval(""); // voidEval("R���ɾ�"): R���ɾ �Է��ϸ� send ���ش�.
			rc.voidEval("emp<-read.csv(\"c:/upload/emp.csv\",header=T,sep=\",\")"); // header=T(True)
			
			
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}*/

}





