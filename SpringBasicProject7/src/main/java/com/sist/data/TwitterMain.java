package com.sist.data;

import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

public class TwitterMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			TwitterStream ts=new TwitterStreamFactory().getInstance();
			String[] data={"�ڷγ�"};
			TwitterListener listener=new TwitterListener();
			ts.addListener(listener);
			
			// �� �����͸� ������ �о�� 
			FilterQuery fq=new FilterQuery();
			fq.track(data);
			
			ts.filter(fq);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}