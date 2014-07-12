package net.cedu.biz.enrollment.impl;

import java.util.LinkedList;
import java.util.List;

import net.cedu.biz.enrollment.AcademyRegisterFeeSubjectBiz;
import net.cedu.common.Constants;
import net.cedu.dao.enrollment.AcademyRegisterFeeSubjectDao;
import net.cedu.entity.enrollment.AcademyRegisterFeeSubject;
import net.cedu.model.page.PageParame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 院校费用科目(学籍阶段) 业务层实现
 * @author Sauntor
 *
 */
@Service
public class AcademyRegisterFeeSubjectBizImpl implements AcademyRegisterFeeSubjectBiz {
	@Autowired
	private AcademyRegisterFeeSubjectDao academyRegisterFeeSubjectDao;
	
	/**
	 * 批量添加 院校费用科目
	 * @param batch
	 * @throws Exception
	 */
	public void addList(List<AcademyRegisterFeeSubject> batch) throws Exception
	{
		if(batch==null) return;
		
		for(AcademyRegisterFeeSubject entity : batch){
			academyRegisterFeeSubjectDao.save(entity);
		}
	}
	
	/**
	 * 查找院校所有费用科目
	 * @param academyId
	 * @return
	 * @throws Exception
	 */
	public List<AcademyRegisterFeeSubject> findByAcademyId(int academyId) throws Exception
	{
		List<AcademyRegisterFeeSubject> list = new LinkedList<AcademyRegisterFeeSubject>();
		
		PageParame param = new PageParame();
		
		param.setHqlConditionExpression(" and academyId="+Constants.PLACEHOLDER);
		param.setValues(new Object[]{academyId});
		
		Long[] ids = academyRegisterFeeSubjectDao.getIDs(param);
		
		if(ids==null) return null;
		
		for(int i=0; i<ids.length; i++){
			list.add(academyRegisterFeeSubjectDao.findById(ids[i].intValue()));
		}
		
		return list;
	}
	
	
	/**
	 * 根据院校ID、招生批次查询所有费用科目
	 * @param academyId
	 * @return
	 * @throws Exception
	 */
	public List<AcademyRegisterFeeSubject> findByAcademyAndBatch(int academyId, int batchId) throws Exception
	{
		List<AcademyRegisterFeeSubject> list = academyRegisterFeeSubjectDao.getByProperty(" and academyId="+Constants.PLACEHOLDER+" and registerBatchId="+Constants.PLACEHOLDER, academyId, batchId);
		
		return list;
	}
	
	/**
	 * 批量列表删除
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public int deleteList(List<AcademyRegisterFeeSubject> list) throws Exception
	{
		StringBuilder ids = new StringBuilder();
		
		for(AcademyRegisterFeeSubject aefs : list){
			ids.append(aefs.getId()).append(",");
		}
		
		if(ids.length()==0)
			return 0;
		
		ids.deleteCharAt(ids.length()-1);
		
		return academyRegisterFeeSubjectDao.deleteByIds(ids.toString());
	}
}
