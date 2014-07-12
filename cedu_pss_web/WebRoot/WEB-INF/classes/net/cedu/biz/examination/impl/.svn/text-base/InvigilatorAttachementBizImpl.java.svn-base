package net.cedu.biz.examination.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.examination.InvigilatorAttachmentBiz;
import net.cedu.common.Constants;
import net.cedu.dao.examination.InvigilatorAttachmentDao;
import net.cedu.entity.admin.User;
import net.cedu.entity.examination.InvigilatorAttachment;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvigilatorAttachementBizImpl implements InvigilatorAttachmentBiz {

	@Autowired
	private InvigilatorAttachmentDao invigilatorattachedao;
	@Autowired
	private UserBiz userbiz;
	public boolean addInvigilatorAttachment(
			InvigilatorAttachment invigilatorattachment) throws Exception {
		// TODO Auto-generated method stub
		Object object=invigilatorattachedao.save(invigilatorattachment);
		if(object!=null){
			return true;
		}
		return  false;
	}

	public boolean deleteInvigilatorAttachment(int id) throws Exception {
		// TODO Auto-generated method stub
		InvigilatorAttachment invigilatorachement=invigilatorattachedao.deleteById(id);
		if(invigilatorachement!=null){
			return true;
		}
		return false;
	}

	public List<InvigilatorAttachment> findAllInvigilatorAttachment(
			int invigilatorId, PageResult<InvigilatorAttachment> pr)
			throws Exception {
		// TODO Auto-generated method stub
		List<InvigilatorAttachment> attachementlist =new ArrayList<InvigilatorAttachment>();
		List<Object> list=new ArrayList<Object>();
		PageParame pp = new PageParame(pr);
		String hql="";
		if(invigilatorId>0){
			hql+="and invigilatorId = "+Constants.PLACEHOLDER;
			list.add(invigilatorId);
		}
		pp.setHqlConditionExpression(hql);
		pp.setValues(list.toArray());
		Long[]lists=invigilatorattachedao.getIDs(pp);
		for(int i=0;i<lists.length;i++)
		{
			InvigilatorAttachment invigilatorattachments=invigilatorattachedao.findById(lists[i].intValue());
			if(invigilatorattachments!=null){
				
				User user=userbiz.findUserById(invigilatorattachments.getUploaderUid());
				if(user!=null)
				{
					invigilatorattachments.setUserName(user.getFullName());
				}
			}
			attachementlist.add(invigilatorattachments);
		}
		return attachementlist;
	}

	public int findAllInvigilatorAttachmentCount(int invigilatorId,
			PageResult<InvigilatorAttachment> pr) throws Exception {
		// TODO Auto-generated method stubList<Object> list=new ArrayList<Object>();
		PageParame pp = new PageParame(pr);
		List<Object> list=new ArrayList<Object>();
		String hql="";
		if(invigilatorId>0){
			hql+="and invigilatorId = "+Constants.PLACEHOLDER;
			list.add(invigilatorId);
		}
		pp.setHqlConditionExpression(hql);
		pp.setValues(list.toArray());
		return invigilatorattachedao.getCounts(pp);
	}

	public InvigilatorAttachment findInvigilatorAttachmentById(int id)
			throws Exception {
		// TODO Auto-generated method stub
		return invigilatorattachedao.findById(id);
	}

	public boolean updateInvigilatorAttachment(
			InvigilatorAttachment invigilatorattachment) throws Exception {
		// TODO Auto-generated method stub
		InvigilatorAttachment  invigilator=invigilatorattachedao.update(invigilatorattachment);
		if(invigilator!=null){
			return true;
		}
		return false;
	}

}
