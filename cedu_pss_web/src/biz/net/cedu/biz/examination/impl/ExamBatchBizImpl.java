package net.cedu.biz.examination.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.examination.ExamBatchBiz;
import net.cedu.common.Constants;
import net.cedu.dao.examination.ExamBatchDao;
import net.cedu.entity.examination.ExamBatch;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamBatchBizImpl implements ExamBatchBiz {
	@Autowired
	private ExamBatchDao exambatchdao;
	
	

	public boolean createNew(ExamBatch exambatch) throws Exception {
		// TODO Auto-generated method stub
		if(findByExambatchName(exambatch.getName())==null){
			exambatchdao.save(exambatch);
			return true;
		}
		
		return false;
	}

	public Object deleteExamBatchById(int id) throws Exception {
		// TODO Auto-generated method stub
		return exambatchdao.deleteById(id);
	}
	 public ExamBatch findByExambatchName(String name)throws Exception{
		 String hqlcon = "";
			List<ExamBatch> list = null;
			List<Object> paramList = new ArrayList<Object>();
			// 名称
			if (StringUtils.isNotBlank(name)) {
				hqlcon += " and  name like " + Constants.PLACEHOLDER;
				paramList.add("%"+name+"%");
			}
			if (0 < hqlcon.length())
				list = exambatchdao.getByProperty(hqlcon, paramList);
			if (null != list && 0 < list.size())
				return list.get(0);
			return null;
	 }

	public List<ExamBatch> findByConditions(ExamBatch exambatch,
			PageResult<ExamBatch> pr) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public ExamBatch findByExamBatchId(int id) throws Exception {
		// TODO Auto-generated method stub
		return exambatchdao.findById(id);
	}
	 public List<ExamBatch> findAllexambatch()throws Exception{
		 return exambatchdao.findAll();
	 }

	public int findExamRoomPageCount(ExamBatch exambatch,
			PageResult<ExamBatch> pr) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<ExamBatch> page(PageResult<ExamBatch> result) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
