package net.cedu.biz.examination.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.examination.InvigilatorRecordBiz;
import net.cedu.common.Constants;
import net.cedu.dao.examination.InvigilatorRecordDao;
import net.cedu.entity.examination.InvigilatorRecord;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvigilatorRecordBizImpl implements InvigilatorRecordBiz {
	@Autowired
	private InvigilatorRecordDao invigilatorrecordao;
	

	public boolean createNew(InvigilatorRecord invigilatorrecord)
			throws Exception {
		// TODO Auto-generated method stub
	Object obj=invigilatorrecordao.save(invigilatorrecord);
	if(obj!=null){
		return true;
	}
		return false;
	}

	public Object deleteInvigilatorRecordById(int id) throws Exception {
		// TODO Auto-generated method stub
		return invigilatorrecordao.deleteById(id);
	}

	public int findAllrecordCount(PageResult<InvigilatorRecord> pr)
			throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<InvigilatorRecord> findByConditions(int id,
			InvigilatorRecord invigilatorrecord,
			PageResult<InvigilatorRecord> pr) throws Exception {
		PageParame p = new PageParame(pr);
		List<InvigilatorRecord> rlist=new ArrayList<InvigilatorRecord>();
		List<Object> list=new ArrayList<Object>();
		String hql="";
		if(id>0){
			hql+="and invigilatorId = "+Constants.PLACEHOLDER;
			list.add(id);
		}
		p.setHqlConditionExpression(hql);
		p.setValues(list.toArray());
		Long[] invigilatorIds = invigilatorrecordao.getIDs(p);
		for(int i=0;i<invigilatorIds.length;i++)
		{
			rlist.add(invigilatorrecordao.findById(invigilatorIds[i].intValue()));
		}
		
		
		// TODO Auto-generated method stub
		return rlist;
	}

	public InvigilatorRecord findByInvigilatorRecordId(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public int findInvigilatorRecordPageCount(int id,
			InvigilatorRecord invigilatorrecord,
			PageResult<InvigilatorRecord> pr) throws Exception {
		PageParame p = new PageParame(pr);
		List<Object> list=new ArrayList<Object>();
		String hql="";
		if(id>0){
			hql+="and invigilatorId = "+Constants.PLACEHOLDER;
			list.add(id);
		}
		p.setHqlConditionExpression(hql);
		p.setValues(list.toArray());
		return invigilatorrecordao.getCounts(p);
	}

	public List<InvigilatorRecord> page(PageResult<InvigilatorRecord> result)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Object updateInvigilatorrecord(InvigilatorRecord invigilatorrecord)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
