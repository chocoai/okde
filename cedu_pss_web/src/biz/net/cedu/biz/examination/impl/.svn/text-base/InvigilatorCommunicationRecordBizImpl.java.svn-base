package net.cedu.biz.examination.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.examination.InvigilatorCommunicationRecordBiz;
import net.cedu.common.Constants;
import net.cedu.dao.examination.InvigilatorCommunicationRecordDao;

import net.cedu.entity.examination.InvigilatorCommunicationRecord;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvigilatorCommunicationRecordBizImpl implements
		InvigilatorCommunicationRecordBiz {
	@Autowired
	private InvigilatorCommunicationRecordDao invigilatorCommunicationRecorddao;

	public boolean createNew(
			InvigilatorCommunicationRecord invigilatorcommunicationrecord)
			throws Exception {
		// TODO Auto-generated method stub
		if(invigilatorcommunicationrecord!=null){
		invigilatorCommunicationRecorddao.save(invigilatorcommunicationrecord);
		return true;
		}
		return false;
	}

	public int findAllrecordCount(PageResult<InvigilatorCommunicationRecord> pr)
			throws Exception {
		// TODO Auto-generated method stub
		PageParame p = new PageParame(pr);
		return  invigilatorCommunicationRecorddao.getCounts(p);
	}
	public InvigilatorCommunicationRecord findByInvigilatorCommunicationRecordId(int id)throws Exception{
		
	return invigilatorCommunicationRecorddao.findById(id);
	}
	public List<InvigilatorCommunicationRecord> page(
			PageResult<InvigilatorCommunicationRecord> result) throws Exception {
		// TODO Auto-generated method stub
		List<InvigilatorCommunicationRecord> lists=new ArrayList<InvigilatorCommunicationRecord>();
		PageParame pp = new PageParame(result);
       Long[]list=invigilatorCommunicationRecorddao.getIDs(pp);
		
		for(int i=0;i<list.length;i++)
		{
			lists.add(invigilatorCommunicationRecorddao.findById(list[i].intValue()));
		}
		
		return lists;
	}
	
		
	
public Object deleteInvigilatorCommunicationRecordById(int id)throws Exception{
	return invigilatorCommunicationRecorddao.deleteById(id);
}
public Object updateInvigilatorcommunicationrecord(InvigilatorCommunicationRecord invigilatorrecord)throws Exception{
	return invigilatorCommunicationRecorddao.update(invigilatorrecord);
}
public List<InvigilatorCommunicationRecord> findByConditions(int id,InvigilatorCommunicationRecord invigilatorrecord, PageResult<InvigilatorCommunicationRecord> pr)
throws Exception{
	List<InvigilatorCommunicationRecord> ilist = new ArrayList<InvigilatorCommunicationRecord>();
	PageParame p = new PageParame(pr);
	List<Object> list = new ArrayList<Object>();
	String hql="";
	if(invigilatorrecord!=null){
		if(id>0){
			hql+="and invigilatorId = "+Constants.PLACEHOLDER;
			list.add(id);
		}
		p.setHqlConditionExpression(hql);
		p.setValues(list.toArray());
		
	}
	Long[] invigilatorcommunicationIds = invigilatorCommunicationRecorddao.getIDs(p);
	for(int i=0;i<invigilatorcommunicationIds.length;i++)
	{
		ilist.add(invigilatorCommunicationRecorddao.findById(invigilatorcommunicationIds[i].intValue()));
	}
	return ilist;
	
	
	
}
 public int findInvigilatorCommunicationRecordPageCount(int id,InvigilatorCommunicationRecord invigilatorrecord, PageResult<InvigilatorCommunicationRecord> pr)
throws Exception{
	// List<InvigilatorCommunicationRecord> ilist = new ArrayList<InvigilatorCommunicationRecord>();
		PageParame p = new PageParame(pr);
		List<Object> list = new ArrayList<Object>();
		String hql="";
		if(invigilatorrecord!=null){
			if(id>0){
				hql+="and invigilatorId = "+Constants.PLACEHOLDER;
				list.add(id);
			}
			p.setHqlConditionExpression(hql);
			p.setValues(list.toArray());
			
		}
		return  invigilatorCommunicationRecorddao.getCounts(p);}
		
	}
	


