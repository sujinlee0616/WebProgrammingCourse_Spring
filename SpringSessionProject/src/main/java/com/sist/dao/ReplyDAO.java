package com.sist.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
public class ReplyDAO {
	@Autowired
	ReplyMapper mapper;
	
	public void replyInsert(ReplyVO vo)
	{
		mapper.replyInsert(vo);
	}
	
	public List<ReplyVO> replyListData(int mno)
	{
		return mapper.replyListData(mno);
	}
	
	// 트랜잭션은 두개이상의 sql문장을 실행할시에 고려해야할 사항
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	// , 앞부분:  / 뒷부분: try-catch의 catch절에서 rollback처리(에러발생시 메세지출력)
	public void replyReplyInsert(int pno,ReplyVO vo)
	{
		ReplyVO pvo=mapper.replyParentInfoData(pno);
		mapper.replyGroupStepIncrement(pvo);
		vo.setGroup_id(pvo.getGroup_id());
		vo.setGroup_step(pvo.getGroup_step()+1);
		vo.setGroup_tab(pvo.getGroup_tab()+1);
		vo.setRoot(pno);
		mapper.replyReplyInsert(vo);
		mapper.replyDepthIncrement(pno);
	}
	// 수정하기
	public void replyUpdate(ReplyVO vo)
	{
		mapper.replyUpdate(vo);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void replyDelete(int no)
	{
		ReplyVO vo=mapper.replyInfoData(no); // depth,root
		if(vo.getDepth()==0)
		{
			mapper.replyDelete(no);
		}
		else
		{
			ReplyVO rvo=new ReplyVO();
			rvo.setNo(no);
			rvo.setMsg("관리자가 삭제한 메세지입니다.");
			mapper.replyMsgUpdate(rvo);
		}
		mapper.replyDepthDecrement(vo.getRoot());
	}
}
