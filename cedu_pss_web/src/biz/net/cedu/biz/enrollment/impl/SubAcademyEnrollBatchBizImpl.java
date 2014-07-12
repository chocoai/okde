package net.cedu.biz.enrollment.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.enrollment.SubAcademyEnrollBatchBiz;
import net.cedu.common.Constants;
import net.cedu.dao.enrollment.SubAcademyEnrollBatchDao;
import net.cedu.entity.enrollment.SubAcademyEnrollBatch;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubAcademyEnrollBatchBizImpl implements SubAcademyEnrollBatchBiz{

	@Autowired
	private SubAcademyEnrollBatchDao subAcademyEnrollBatchDao;

	/* 根据院校招生批次ID，查询其对应的全部子批次
	 * (non-Javadoc)
	 * @see net.cedu.biz.enrollment.SubAcademyEnrollBatchBiz#findSubAcademyEnrollBatchByAcademyEnrollBatchId(int)
	 */
	public List<SubAcademyEnrollBatch> findSubAcademyEnrollBatchByAcademyEnrollBatchId(
			int academyEnrollBatchId) throws Exception {
		String sql=" and deleteFlag="+Constants.PLACEHOLDER+" and academyEnrollBatchId="+Constants.PLACEHOLDER;
		return subAcademyEnrollBatchDao.getByProperty(sql, new Object[]{Constants.DELETE_FALSE,academyEnrollBatchId});
	}

	/* 添加
	 * (non-Javadoc)
	 * @see net.cedu.biz.enrollment.SubAcademyEnrollBatchBiz#addSubAcademyEnrollBatch(net.cedu.entity.enrollment.SubAcademyEnrollBatch)
	 */
	public boolean addSubAcademyEnrollBatch(
			SubAcademyEnrollBatch subAcademyEnrollBatch) throws Exception{
		
		if(0>=findTotalByProperty(subAcademyEnrollBatch)){
			subAcademyEnrollBatchDao.save(subAcademyEnrollBatch);
			return true;
		}
		return false;
	}

	/* 修改
	 * (non-Javadoc)
	 * @see net.cedu.biz.enrollment.SubAcademyEnrollBatchBiz#modifySubAcademyEnrollBatch(net.cedu.entity.enrollment.SubAcademyEnrollBatch)
	 */
	public boolean modifySubAcademyEnrollBatch (
			SubAcademyEnrollBatch subAcademyEnrollBatch) throws Exception{
			if (subAcademyEnrollBatch != null) {
				subAcademyEnrollBatchDao
						.update(subAcademyEnrollBatch);
					return true;
			}
	
		return false;
	}
	
	/* 根据主键ID查询
	 * (non-Javadoc)
	 * @see net.cedu.biz.enrollment.SubAcademyEnrollBatchBiz#findSubAcademyEnrollBatchById(int)
	 */
	public SubAcademyEnrollBatch findSubAcademyEnrollBatchById(int id){
		return subAcademyEnrollBatchDao.findById(id);
	}
	
	/* 查询所有院校招生批次设置(delete_flag=0)
	 * (non-Javadoc)
	 * @see net.cedu.biz.enrollment.SubAcademyEnrollBatchBiz#findAllSubAcademyEnrollBatchByDeleteFlag()
	 */
	public List<SubAcademyEnrollBatch> findAllSubAcademyEnrollBatchByDeleteFlag() throws Exception{
		return subAcademyEnrollBatchDao.getByProperty("deleteFlag", Constants.DELETE_FALSE);
	}
	
	/* 根据院校招生批次ID 查询当前子批次(isCurrent=1)
	 * (non-Javadoc)
	 * @see net.cedu.biz.enrollment.SubAcademyEnrollBatchBiz#findByAcademyBatchIdAndCurrentStatus(int)
	 */
	public List<SubAcademyEnrollBatch> findByAcademyBatchIdAndCurrentStatus(int id) throws Exception{
		String sql=" and deleteFlag="+Constants.PLACEHOLDER+" and academyEnrollBatchId="+Constants.PLACEHOLDER +" and isCurrent="+Constants.PLACEHOLDER;
		return subAcademyEnrollBatchDao.getByProperty(sql, new Object[]{Constants.DELETE_FALSE,id,Constants.IS_CURRENT_TRUE});
	}
	
	/***
	 * 检查是否有重复数据
	 * @param baseDict
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private int findTotalByProperty(SubAcademyEnrollBatch subAcademyEnrollBatch)throws Exception{
		String hql = "";
		List objs = new ArrayList();
		
		if (StringUtils.isNotBlank(subAcademyEnrollBatch.getSubEnrollmentName())) {
			hql += " and subEnrollmentName = " + Constants.PLACEHOLDER+" and academyEnrollBatchId = "+Constants.PLACEHOLDER;
			objs.add(subAcademyEnrollBatch.getSubEnrollmentName());
			objs.add(subAcademyEnrollBatch.getAcademyEnrollBatchId());
		}
		
		return subAcademyEnrollBatchDao.findCountByProperty(hql, objs);
	}
}
