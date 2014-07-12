package net.cedu.biz.examination.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.examination.ExamCommunicationRecordBiz;
import net.cedu.common.Constants;
import net.cedu.dao.examination.ExamCommunicationRecordDao;
import net.cedu.entity.examination.ExamCommunicationRecord;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ExamCommunicationRecordBizImpl implements ExamCommunicationRecordBiz {
	@Autowired
	private ExamCommunicationRecordDao examcommunicationrecorddao;
	
	

	public boolean createNew(ExamCommunicationRecord record) throws Exception {
		// TODO Auto-generated method stub
		if(record.getTitle()!=null){
			examcommunicationrecorddao.save(record);
			return true;
		}
		return false;
	}
	public Object deleteExamCommunicationRecordById(int id) throws Exception {
		// TODO Auto-generated method stub
		return examcommunicationrecorddao.deleteById(id);
	}

	public ExamCommunicationRecord findByExamCommunicationRecordId(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	 

	public int findallrecord(PageResult<ExamCommunicationRecord> pr)
			throws Exception {
		// TODO Auto-generated method stub
		PageParame pp = new PageParame(pr);
		
		return examcommunicationrecorddao.getCounts(pp);
	}

	public List<ExamCommunicationRecord> page(
			PageResult<ExamCommunicationRecord> result) throws Exception {
		 List<ExamCommunicationRecord> recordlist =new ArrayList<ExamCommunicationRecord>();

			PageParame pp = new PageParame(result);
			Long[]list=examcommunicationrecorddao.getIDs(pp);
			
			for(int i=0;i<list.length;i++)
			{
				recordlist.add(examcommunicationrecorddao.findById(list[i].intValue()));
			}
			return recordlist;
		}
	public int findExamCommunicationRecordPageCount(int id,PageResult<ExamCommunicationRecord> pr) throws Exception{
		PageParame p = new PageParame(pr);
		List<Object> list=new ArrayList<Object>();
		String hql="";
			if (id>0) 
			{	hql+="and scheduleId = "+Constants.PLACEHOLDER;
					list.add(id);
				}
				p.setHqlConditionExpression(hql);
				p.setValues(list.toArray());
			return examcommunicationrecorddao.getCounts(p);
    }
	public List<ExamCommunicationRecord> findExamCommunicationRecordPage(int id,PageResult<ExamCommunicationRecord> pr) throws Exception{
		PageParame p = new PageParame(pr);
		List<Object> list=new ArrayList<Object>();
		List<ExamCommunicationRecord> lists=new ArrayList<ExamCommunicationRecord>();
		String hql="";
		System.out.println(id);
			if (id>0) 
			{hql+="and scheduleId = "+Constants.PLACEHOLDER;
					list.add(id);
				}
				p.setHqlConditionExpression(hql);
				p.setValues(list.toArray());
				Long[] Ids = examcommunicationrecorddao.getIDs(p);
				if (Ids != null && Ids.length != 0) {
					for (int i = 0; i < Ids.length; i++) {
						ExamCommunicationRecord u = examcommunicationrecorddao.findById(Integer.parseInt(Ids[i].toString()));
						if (u != null) {
							lists.add(u);
						}
					}
			
			}
				return lists;
    }
	 public List<ExamCommunicationRecord> findByExamscheduleId(int id)throws Exception{
		 return examcommunicationrecorddao.getByProperty("scheduleId", id);
	 }

	

}
