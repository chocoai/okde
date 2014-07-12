package net.cedu.biz.basesetting.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.common.Constants;
import net.cedu.common.enums.FeeSubjectEnum;
import net.cedu.dao.basesetting.FeeSubjectDao;
import net.cedu.entity.basesetting.FeeSubject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 缴费科目
 * @author HXJ
 *
 */
@Service
public class FeeSubjectBizImpl implements FeeSubjectBiz{
	@Autowired
	private FeeSubjectDao feeSubjectDao;
	
	/* 查询所有缴费科目列表
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.FeeSubjectBiz#findAllFeeSubjects()
	 */
	public List<FeeSubject> findAllFeeSubjects() throws Exception{
		return feeSubjectDao.findAll();
	}
	
	/* 查询所有缴费科目列表(delete_flag=0)
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.FeeSubjectBiz#findAllFeeSubjectByDeleteFlag()
	 */
	public List<FeeSubject> findAllFeeSubjectByDeleteFlag() throws Exception{
		return feeSubjectDao.getByProperty("deleteFlag", Constants.DELETE_FALSE);
	}
	
	/* 按主键id查询FeeSubject
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.FeeSubjectBiz#findFeeSubjectById(java.io.Serializable)
	 */
	public FeeSubject findFeeSubjectById(Serializable id){
		return feeSubjectDao.findById(id);
	}
	
	/* 添加费用科目
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.FeeSubjectBiz#addFeeSubject(net.cedu.entity.basesetting.FeeSubject)
	 */
	public boolean addFeeSubject(FeeSubject feeSubject) throws Exception{
		if (0>=findTotalByProperty(feeSubject)) {
			feeSubjectDao.save(feeSubject);
			return true;
		}
		return false;
	}
	
	/* 修改费用科目
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.FeeSubjectBiz#modifyFeeSubject(net.cedu.entity.basesetting.FeeSubject)
	 */
	public boolean modifyFeeSubject(FeeSubject feeSubject)throws Exception{
		 if (0>=findTotalByProperty(feeSubject)) {
			feeSubjectDao.update(feeSubject);
			return true;
		}
		 return false;
	}
	
//	//按主键删除(物理删除)
//	public FeeSubject deleteFeeSubjectById(Serializable id){
//		return feeSubjectDao.deleteById(id);
//	}
//	
//	//按主键删除(逻辑删除)
//	public int deleteFeeSubjectByFlag(int id){
//		return feeSubjectDao.deleteByFlag(id);
//	}
	
	/* 按批次类型
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.FeeSubjectBiz#findAllFeeSubjectBybatchType(int)
	 */
	public List<FeeSubject> findAllFeeSubjectBybatchType(int batchtype){
		try {
			List<FeeSubject> feesubjectlst = null;
			// Ids集合
			feesubjectlst = feeSubjectDao.getByProperty(" and deleteFlag="+Constants.PLACEHOLDER+" and batchType="+Constants.PLACEHOLDER, Constants.DELETE_FALSE,batchtype);
			return feesubjectlst;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*	删除(读取配置文件)
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.FeeSubjectBiz#deleteConfigFeeSubject(int)
	 */
	public FeeSubject deleteConfigFeeSubject(int id){
		return feeSubjectDao.deleteConfig(id);
	}
	
	/*查询数据中是否有重复的数据
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.BaseDictBiz#findTotalByProperty(net.cedu.entity.basesetting.BaseDict)
	 */
	@SuppressWarnings("unchecked")
	private int findTotalByProperty(FeeSubject feeSubject)throws Exception{
		String hql = "";
		List objs = new ArrayList();
		
		if(feeSubject.getId()>0){
			hql+=" and id <> "+Constants.PLACEHOLDER;
			objs.add(feeSubject.getId());
		}
		if (StringUtils.isNotBlank(feeSubject.getName())) {
			hql += " and name = " + Constants.PLACEHOLDER;
			objs.add(feeSubject.getName());
		}
	
		return feeSubjectDao.findCountByProperty(hql, objs);
	}
	
	/*
	 * 查询所有缴费科目列表(delete_flag=0，过滤掉报名费和测试费)
	 * @see net.cedu.biz.basesetting.FeeSubjectBiz#findSchoolFeeSubjectByDeleteFlag()
	 */
	public List<FeeSubject> findSchoolFeeSubjectByDeleteFlag() throws Exception
	{
		return feeSubjectDao.getByProperty(" and deleteFlag="+Constants.PLACEHOLDER+" and id > "+Constants.PLACEHOLDER, Constants.DELETE_FALSE,FeeSubjectEnum.TestFee.value());
	}
}
