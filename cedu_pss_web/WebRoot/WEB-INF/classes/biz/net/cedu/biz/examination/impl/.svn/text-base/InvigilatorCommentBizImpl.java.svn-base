package net.cedu.biz.examination.impl;

import net.cedu.biz.examination.InvigilatorCommentBiz;
import net.cedu.dao.examination.InvigilatorCommentDao;
import net.cedu.entity.examination.InvigilatorComment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvigilatorCommentBizImpl implements InvigilatorCommentBiz {
	@Autowired
	private InvigilatorCommentDao invigilatorcommentdao;
	

	public boolean createNew(InvigilatorComment invigilatorcomment)
			throws Exception {
		// TODO Auto-generated method stub
		Object o=invigilatorcommentdao.save(invigilatorcomment);
		if(o!=null){
			return true;
		}
		return false;
	}

}
