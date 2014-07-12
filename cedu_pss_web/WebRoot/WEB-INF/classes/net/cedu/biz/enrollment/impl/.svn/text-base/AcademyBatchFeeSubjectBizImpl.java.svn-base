package net.cedu.biz.enrollment.impl;

import java.util.LinkedList;
import java.util.List;

import net.cedu.biz.enrollment.AcademyBatchFeeSubjectBiz;
import net.cedu.common.Constants;
import net.cedu.dao.enrollment.AcademyBatchFeeSubjectDao;
import net.cedu.entity.enrollment.AcademyBatchFeeSubject;
import net.cedu.model.page.PageParame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 院校费用科目(招生/非招生阶段) 业务层实现
 * @author Sauntor
 *
 */
@Service
public class AcademyBatchFeeSubjectBizImpl implements AcademyBatchFeeSubjectBiz {
	@Autowired
	private AcademyBatchFeeSubjectDao academyBatchFeeSubjectDao;
	
	/**
	 * 批量添加 院校费用科目
	 * @param batch
	 * @throws Exception
	 */
	public void addList(List<AcademyBatchFeeSubject> batch) throws Exception
	{
		if(batch==null) return;
		
		for(AcademyBatchFeeSubject entity : batch){
			List<AcademyBatchFeeSubject> list = academyBatchFeeSubjectDao.getByProperty(" and academyId="+Constants.PLACEHOLDER+" and batchId="+Constants.PLACEHOLDER+" and feeSubjectId="+Constants.PLACEHOLDER, entity.getAcademyId(), entity.getBatchId(), entity.getFeeSubjectId());
			if(list==null || list.size()==0)
				academyBatchFeeSubjectDao.save(entity);
		}
	}
	
	/**
	 * 查找院校所有费用科目
	 * @param academyId
	 * @return
	 * @throws Exception
	 */
	public List<AcademyBatchFeeSubject> findByAcademyId(int academyId) throws Exception
	{
		List<AcademyBatchFeeSubject> list = new LinkedList<AcademyBatchFeeSubject>();
		
		PageParame param = new PageParame();
		
		param.setHqlConditionExpression(" and academyId="+Constants.PLACEHOLDER);
		param.setValues(new Object[]{academyId});
		
		Long[] ids = academyBatchFeeSubjectDao.getIDs(param);
		
		if(ids==null) return null;
		
		for(int i=0; i<ids.length; i++){
			list.add(academyBatchFeeSubjectDao.findById(ids[i].intValue()));
		}
		
		return list;
	}
	
	/**
	 * 根据院校ID、招生批次查询所有费用科目
	 * @param academyId
	 * @return
	 * @throws Exception
	 */
	public List<AcademyBatchFeeSubject> findByAcademyAndBatch(int academyId, int batchId) throws Exception
	{
		List<AcademyBatchFeeSubject> list = academyBatchFeeSubjectDao.getByProperty(" and academyId="+Constants.PLACEHOLDER+" and batchId="+Constants.PLACEHOLDER, academyId, batchId);
		
		return list;
	}
	
	/**
	 * 根据院校ID、招生批次查询指定阶段的费用科目
	 * @param academyId
	 * @param batchId
	 * @param stage 阶段（招生／非招生）<code>Constants.BATCH_TYPE_ENROLL/Constants.BATCH_TYPE_NON_ENROLL</code>
	 * @return
	 * @throws Exception
	 */
	public List<AcademyBatchFeeSubject> findByAcademyAndBatchAndStage(int academyId, int batchId, int stage) throws Exception
	{
		return academyBatchFeeSubjectDao.getByProperty(" and academyId="+Constants.PLACEHOLDER+" and batchId="+Constants.PLACEHOLDER+" and stage="+Constants.PLACEHOLDER, academyId, batchId, stage);
	}
	
	/**
	 * 查询指定院校招生批次下所有可用费用科目
	 * @param batchId
	 * @param stage 阶段（招生／非招生）<code>Constants.BATCH_TYPE_ENROLL/Constants.BATCH_TYPE_NON_ENROLL</code>
	 * @return
	 * @throws Exception
	 */
	public List<AcademyBatchFeeSubject> findByBatchAndStage(int batchId, int stage) throws Exception
	{
		return academyBatchFeeSubjectDao.getByProperty(" and batchId="+Constants.PLACEHOLDER+" and stage="+Constants.PLACEHOLDER, batchId, stage);
	}
	
	/**
	 * 根据Id列表删除
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public int deleteByIds(String ids) throws Exception
	{
		return academyBatchFeeSubjectDao.deleteByIds(ids);
	}
	
	/**
	 * 批量列表删除
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public int deleteList(List<AcademyBatchFeeSubject> list) throws Exception
	{
		StringBuilder ids = new StringBuilder();
		
		for(AcademyBatchFeeSubject aefs : list){
			ids.append(aefs.getId()).append(",");
		}
		
		if(ids.length()==0)
			return 0;
		
		ids.deleteCharAt(ids.length()-1);
		
		return academyBatchFeeSubjectDao.deleteByIds(ids.toString());
	}

	/*
	 * 费用科目是否存在指定批次中
	 * @see net.cedu.biz.enrollment.AcademyBatchFeeSubjectBiz#isFeeSubjectInBatch(int, int)
	 */
	public boolean isFeeSubjectInBatch(int batchId, int feeSubjectId) throws Exception {
		String hql = " and feeSubjectId="+Constants.PLACEHOLDER+" and batchId="+Constants.PLACEHOLDER;
		
		List<AcademyBatchFeeSubject> list = academyBatchFeeSubjectDao.getByProperty(hql, new Object[]{feeSubjectId, batchId});
		
		if(list==null || list.size()==0) return false;
		
		return true;
	}
}
