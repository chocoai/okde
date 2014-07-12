package net.cedu.biz.enrollment.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.basesetting.GlobalEnrollBatchBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.common.Constants;
import net.cedu.dao.enrollment.AcademyEnrollBatchDao;
import net.cedu.entity.basesetting.GlobalEnrollBatch;
import net.cedu.entity.enrollment.AcademyEnrollBatch;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 院校招生批次 业务层
 * @author 
 *
 */
@Service
public class AcademyEnrollBatchBizImpl implements AcademyEnrollBatchBiz{
	
	@Autowired
	private AcademyEnrollBatchDao academyEnrollBatchDao;
	@Autowired
	private AcademyLevelBiz academyLevelBiz;
	@Autowired
	private GlobalEnrollBatchBiz globalBiz;
	
	/**
	 * 根据ID查找院校招生批次
	 * @param id
	 * @return
	 */
	public AcademyEnrollBatch findAcademyEnrollBatchById(int id) throws Exception
	{
		return this.academyEnrollBatchDao.findById(id);
	}
	
	/**
	 * 添加院校招生批次
	 * @param 
	 */
	public boolean addAcademyEnrollBatch(AcademyEnrollBatch  academyEnrollBatch) throws Exception
	{
		if (0>=findTotalByProperty(academyEnrollBatch))
		{
			academyEnrollBatchDao.save(academyEnrollBatch);
			return true;
		}
		return false;
	}
	
//	/**
//	 * 删除院校招生批次(物理删除)
//	 * @param 
//	 */
//	public boolean deleteAcademyEnrollBatchById(int id) throws Exception
//	{
//		if (id != 0) 
//		{
//			Object object = academyEnrollBatchDao.deleteById(id);
//			if (object != null) 
//			{
//				return true;
//			}
//		}
//		return false;
//	}
//	
//	/**
//	 * 删除院校招生批次(逻辑删除)
//	 * @param 
//	 */
//	public boolean deleteAcademyEnrollBatchByFlag(int id) throws Exception
//	{
//		if (id != 0) 
//		{
//			int num = academyEnrollBatchDao.deleteByFlag(id);
//			if (num>0) 
//			{
//				return true;
//			}
//		}
//	
//		return false;
//	}
	
	/**
	 * 修改院校招生批次
	 * @param 
	 */
	public boolean modifyAcademyEnrollBatch(AcademyEnrollBatch academyEnrollBatch) throws Exception
	{
		if (academyEnrollBatch != null) 
		{
			Object object = academyEnrollBatchDao.update(academyEnrollBatch);
			if (object != null)
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 查询院校所有招生批次
	 * @param academyId
	 * @return
	 * @throws Exception
	 */
	public List<AcademyEnrollBatch> findByAcademyId(int academyId) throws Exception
	{
		return this.academyEnrollBatchDao.getByProperty("and academyId="+Constants.PLACEHOLDER+" order by enrollmentName desc", new Object[]{academyId});
	}
	
	/**
	 * 查询院校所有招生批次(deleteFlag=0)
	 * @param academyId
	 * @return
	 * @throws Exception
	 */
	public List<AcademyEnrollBatch> findByAcademyIdAndFlag(int academyId) throws Exception
	{
		String sql=" and academyId="+Constants.PLACEHOLDER+" and deleteFlag="+Constants.PLACEHOLDER+" order by enrollmentName desc";
		return academyEnrollBatchDao.getByProperty(sql, new Object[]{academyId,Constants.DELETE_FALSE});
	}
	
	/**
	 * 查询院校所有启用的招生批次(deleteFlag=0)
	 * @param academyId
	 * @return
	 * @throws Exception
	 */
	public List<AcademyEnrollBatch> findByAcademyIdAndFlagAndIsEnable(int academyId,int isEnable) throws Exception
	{
		String sql=" and academyId="+Constants.PLACEHOLDER+" and deleteFlag="+Constants.PLACEHOLDER+" and isEnable="+Constants.PLACEHOLDER+" order by id desc";
		return academyEnrollBatchDao.getByProperty(sql, new Object[]{academyId,Constants.DELETE_FALSE,isEnable});
	}
	
	//根据获取的院校批次ID 循环获取其对应的层次(院校批次),然后循环赋给List<AcademyEnrollBatch>
	public List<AcademyEnrollBatch> addAcademyLevelforAcademyEnrollBatch(List<AcademyEnrollBatch> academyEnrollBatchList) throws Exception{
		if(academyEnrollBatchList!=null&&academyEnrollBatchList.size()>0){
			for(int i=0;i<academyEnrollBatchList.size();i++){
				//根据获取的院校批次ID 循环获取其对应的层次(院校批次),然后循环赋给academyEnrollBatchList
					academyEnrollBatchList.get(i).setAcademyLevelList(academyLevelBiz.findBatchId(academyEnrollBatchList.get(i).getId()));
				}
			}
		return academyEnrollBatchList;
	}
	
	/*查询所非结束状态的招生批次
	 * (non-Javadoc)
	 * @see net.cedu.biz.enrollment.AcademyEnrollBatchBiz#findBatchNotInFinishedByAcademyId(int)
	 */
	public List<AcademyEnrollBatch> findBatchNotInFinishedByAcademyId(int academyId) throws Exception{
		String sql=" and academyId="+Constants.PLACEHOLDER+" and deleteFlag="+Constants.PLACEHOLDER+" and isEnable <>"+Constants.PLACEHOLDER+" order by enrollmentName desc";
		return academyEnrollBatchDao.getByProperty(sql, new Object[]{academyId,Constants.DELETE_FALSE,Constants.STATUS_FINISHED});
	}
	
	/*查询所非结束状态的招生批次_定时任务用
	 * (non-Javadoc)
	 * @see net.cedu.biz.enrollment.AcademyEnrollBatchBiz#findBatchNotInFinishedByAcademyId(int)
	 */
	public List<AcademyEnrollBatch> findBatchNotInFinishedByAcademyIdForTask(List<Integer> idList) throws Exception{
		List<Object> paramList=new ArrayList<Object>();
		String sql="";
		if(null!=idList&&idList.size()>0)
		{
			sql+=" and academyId in(";
			StringBuffer idsSB = new StringBuffer("0");
			for(int i=0;i<idList.size();i++)
			{
				if(0==i){
//					sql+=Constants.PLACEHOLDER;
					idsSB = new StringBuffer(Constants.PLACEHOLDER);
				}
				else{
//					sql+=","+Constants.PLACEHOLDER;
					idsSB.append(","+Constants.PLACEHOLDER);
				}
				paramList.add(idList.get(i));
			}
			sql+=idsSB.toString();
			sql+=")";
		}
		sql+=" and deleteFlag="+Constants.PLACEHOLDER+" and isEnable <>"+Constants.PLACEHOLDER;
		paramList.add(Constants.DELETE_FALSE);
		paramList.add(Constants.STATUS_FINISHED);
		return academyEnrollBatchDao.getByProperty(sql,paramList.toArray());
	}
	
	/*查询除当前院校招生批次以外的非结束状态的招生批次
	 * (non-Javadoc)
	 * @see net.cedu.biz.enrollment.AcademyEnrollBatchBiz#findOtherBatchNotInFinishedByAcademyId(int, int)
	 */
	public List<AcademyEnrollBatch> findOtherBatchByAcademyId(int academyid,int batchid) throws Exception{
		String sql=" and academyId="+Constants.PLACEHOLDER+" and deleteFlag="+Constants
						.PLACEHOLDER+" and id <>"+Constants.PLACEHOLDER+" order by enrollmentName desc";
		return academyEnrollBatchDao.getByProperty(sql, new Object[]{academyid,Constants.DELETE_FALSE,batchid});
	}
	
	/*删除(读取配置文件)
	 * (non-Javadoc)
	 * @see net.cedu.biz.enrollment.AcademyEnrollBatchBiz#deleteConfigAcademyEnrollBatch(int)
	 */
	public AcademyEnrollBatch deleteConfigAcademyEnrollBatch(int id){
		return academyEnrollBatchDao.deleteConfig(id);
	}
	
	/*根据全局招生批次id查询其对应的全部院校招生批次
	 * (non-Javadoc)
	 * @see net.cedu.biz.enrollment.AcademyEnrollBatchBiz#findAllAcademyBatchbyGlobalBatchId(int)
	 */
	public List<AcademyEnrollBatch> findAllAcademyBatchbyGlobalBatchId(int batchid) throws Exception{
		List<AcademyEnrollBatch> academybacthlist = new ArrayList<AcademyEnrollBatch>();
		if (batchid>0) {
			String sql = " and deleteFlag=" + Constants.PLACEHOLDER
					+ " and globalEnrollBatchId=" + Constants.PLACEHOLDER;
			academybacthlist = academyEnrollBatchDao.getByProperty(sql,new Object[]{Constants.DELETE_FALSE,batchid});
		}else{
			academybacthlist=null;
		}
		
		return academybacthlist;
	}
	
	/* 根据全局招生批次id和院校id查询院校招生批次(包含院校招生批次状态为 结束状态的)
	 * (non-Javadoc)
	 * @see net.cedu.biz.enrollment.AcademyEnrollBatchBiz#findAcademyBatchByGlobalBatchIdAndAcademyId(int, int)
	 */
	public AcademyEnrollBatch findAcademyBatchByGlobalBatchIdAndAcademyId(int batchid,int academyid)throws Exception{
		List<AcademyEnrollBatch> batchlist=new ArrayList<AcademyEnrollBatch>();
		if(batchid>0&&academyid>0){
			String sql=" and academyId="+Constants.PLACEHOLDER+" and deleteFlag="+Constants.PLACEHOLDER+
					" and globalEnrollBatchId="+Constants.PLACEHOLDER;
			batchlist = academyEnrollBatchDao.getByProperty(sql, new Object[]{academyid,Constants.DELETE_FALSE,batchid});
		}
		if(batchlist!=null&&batchlist.size()>0){
			return batchlist.get(0);
		}
		return null;
	}
	
	/* 根据全局招生批次id和院校id查询院校招生批次(过滤掉 院校招生批次为结束状态的)
	 * (non-Javadoc)
	 * @see net.cedu.biz.enrollment.AcademyEnrollBatchBiz#findNonFinishedAcademyBatchByGlobalBatchIdAndAcademyId(int, int)
	 */
	public AcademyEnrollBatch findNonFinishedAcademyBatchByGlobalBatchIdAndAcademyId(int batchid,int academyid)throws Exception{
		List<AcademyEnrollBatch> batchlist=new ArrayList<AcademyEnrollBatch>();
		if(batchid>0&&academyid>0){
			String sql=" and academyId="+Constants.PLACEHOLDER+" and deleteFlag="+Constants.PLACEHOLDER+
					" and globalEnrollBatchId="+Constants.PLACEHOLDER+" and isEnable <>"+Constants.PLACEHOLDER;
			batchlist = academyEnrollBatchDao.getByProperty(sql, new Object[]{academyid,Constants.DELETE_FALSE,batchid,Constants.STATUS_FINISHED});
		}
		if(batchlist!=null&&batchlist.size()>0){
			return batchlist.get(0);
		}
		return null;
	}
	
	/*
	 * 根据全局招生批次id和院校id查询院校招生批次( 院校招生批次为启用状态的)
	 * 
	 * @see net.cedu.biz.enrollment.AcademyEnrollBatchBiz#findQiYongAcademyBatchByGlobalBatchIdAndAcademyId(int, int)
	 */
	public AcademyEnrollBatch findQiYongAcademyBatchByGlobalBatchIdAndAcademyId(int batchid,int academyid)throws Exception{
		List<AcademyEnrollBatch> batchlist=new ArrayList<AcademyEnrollBatch>();
		if(batchid>0&&academyid>0){
			String sql=" and academyId="+Constants.PLACEHOLDER+" and deleteFlag="+Constants.PLACEHOLDER+
					" and globalEnrollBatchId="+Constants.PLACEHOLDER+" and isEnable ="+Constants.PLACEHOLDER;
			batchlist = academyEnrollBatchDao.getByProperty(sql, new Object[]{academyid,Constants.DELETE_FALSE,batchid,Constants.STATUS_ENABLED});
		}
		if(batchlist!=null&&batchlist.size()>0){
			return batchlist.get(0);
		}
		return null;
	}
	
	/***
	 * 检查是否有重复数据
	 * @param baseDict
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private int findTotalByProperty(AcademyEnrollBatch academyEnrollBatch)throws Exception{
		String hql = "";
		List objs = new ArrayList();
		
		if(academyEnrollBatch.getId()>0){
			hql+=" and id <> "+Constants.PLACEHOLDER;
			objs.add(academyEnrollBatch.getId());
		}
		
		if (StringUtils.isNotBlank(academyEnrollBatch.getEnrollmentName())) {
			hql += " and enrollmentName = " + Constants.PLACEHOLDER+" and academyId = "+Constants
				.PLACEHOLDER+" and deleteFlag = "+Constants.PLACEHOLDER;
			objs.add(academyEnrollBatch.getEnrollmentName());
			objs.add(academyEnrollBatch.getAcademyId());
			objs.add(Constants.DELETE_FALSE);
		}
		
		return academyEnrollBatchDao.findCountByProperty(hql, objs);
	}

	/*
	 * 招生批次
	 * @see net.cedu.biz.enrollment.AcademyEnrollBatchBiz#findAcademyEnrollBatchByEnrollmentName(int, java.lang.String)
	 */
	public AcademyEnrollBatch findAcademyEnrollBatchByEnrollmentName(
			int academyid, String enrollmentName) throws Exception {
		
		return academyEnrollBatchDao.getObjByProperty(" and academyId="+Constants.PLACEHOLDER+" and enrollmentName="+Constants.PLACEHOLDER, new Object []{academyid,enrollmentName});
	}
	/*
	 * 学籍批次
	 * @see net.cedu.biz.enrollment.AcademyEnrollBatchBiz#findAcademyEnrollBatchByRegisterName(int, java.lang.String)
	 */
	public AcademyEnrollBatch findAcademyEnrollBatchByRegisterName(
			int academyid, String registerName) throws Exception {
		
		return academyEnrollBatchDao.getObjByProperty(" and academyId="+Constants.PLACEHOLDER+" and registerName="+Constants.PLACEHOLDER, new Object[]{academyid,registerName});
	}
	
	/**
	 *  通过院校招生批次ID，院校ID查询全局批次ID
	  * @see net.cedu.biz.enrollment.AcademyEnrollBatchBiz#findGlobalBatchIdByAcademyEnrollBatchIdAndAcademyId(int, int)
	 */
	public GlobalEnrollBatch findGlobalBatchIdByAcademyEnrollBatchIdAndAcademyId(int academyEnrollBatchId,int academyId)throws Exception{
		List<AcademyEnrollBatch> academyBatchList = null;
		GlobalEnrollBatch globalBatch = null;
		if (academyEnrollBatchId>0 && academyId>0) {
			academyBatchList = new ArrayList<AcademyEnrollBatch>();
			academyBatchList = academyEnrollBatchDao.getByProperty(
					" and academyId = " + Constants.PLACEHOLDER
							+ " and id = "+Constants.PLACEHOLDER, new Object[] {
							academyId, academyEnrollBatchId });
			if(academyBatchList!=null && academyBatchList.size()>0){
				globalBatch = new GlobalEnrollBatch();
				globalBatch = globalBiz.findGlobalEnrollBatchById(academyBatchList.get(0).getGlobalEnrollBatchId());
			}
		}
		return globalBatch;
	}

	
	/*
	 * 根据多个院校批次查询全局批次(多)
	 * @see net.cedu.biz.enrollment.AcademyEnrollBatchBiz#findGlobalBatchIdByEnrollBatchIds(java.lang.String)
	 */
	public List<GlobalEnrollBatch> findGlobalBatchIdByEnrollBatchIds(
			String enrollBatchIds) throws Exception {
		List<AcademyEnrollBatch> academyBatchList = null;
//		List<GlobalEnrollBatch> globalBatchlst = null;
		String glbatchIds="";
		if(null!=enrollBatchIds && !"".equals(enrollBatchIds))
		{
			academyBatchList = new ArrayList<AcademyEnrollBatch>();
			academyBatchList=academyEnrollBatchDao.getByProperty(
					" and id in (" + Constants.PLACEHOLDER + " ) ", new Object[] {enrollBatchIds});
		}
		if(null!=academyBatchList && academyBatchList.size()>0)
		{
			
			for (int i = 0; i < academyBatchList.size(); i++) {
				glbatchIds+=","+academyBatchList.get(i).getGlobalEnrollBatchId();
			}
			//globalBatchlst=gl
			
			
		}
		
		
		return null;
	}

	/*
	 * 根据全局批次ID查找院校招生批次
	 * @see net.cedu.biz.enrollment.AcademyEnrollBatchBiz#findAcademyEnrollBatchByGId(int)
	 */
	public List<AcademyEnrollBatch> findAcademyEnrollBatchByGId(int gid)
			throws Exception {
		return academyEnrollBatchDao.getByProperty(" and globalEnrollBatchId="+Constants.PLACEHOLDER+" and deleteFlag="+Constants.PLACEHOLDER,new Object[]{gid,Constants.DELETE_FALSE});
	}
}