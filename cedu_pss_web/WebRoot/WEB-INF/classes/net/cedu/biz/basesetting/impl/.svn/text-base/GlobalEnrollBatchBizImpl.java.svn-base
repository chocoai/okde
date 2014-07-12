package net.cedu.biz.basesetting.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.basesetting.GlobalEnrollBatchBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.common.Constants;
import net.cedu.dao.basesetting.GlobalEnrollBatchDao;
import net.cedu.entity.basesetting.GlobalEnrollBatch;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.model.page.PageParame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 全局招生批次设置
 * @author HXJ
 *
 */
@Service
public class GlobalEnrollBatchBizImpl implements GlobalEnrollBatchBiz{

	@Autowired
	private GlobalEnrollBatchDao globalEnrollBatchDao;
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;
	
	/* 增加全局招生批次设置
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.GlobalEnrollBatchBiz#addGlobalEnrollBatch(net.cedu.entity.basesetting.GlobalEnrollBatch)
	 */
	public boolean addGlobalEnrollBatch(GlobalEnrollBatch globalEnrollBatch) throws Exception{
		if(0>=this.findTotalByProperty(globalEnrollBatch)){
			globalEnrollBatchDao.save(globalEnrollBatch);
			return true;
		}
		return false;
	}
	
	/* 修改全局招生批次设置
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.GlobalEnrollBatchBiz#modifyGlobalEnrollBatch(net.cedu.entity.basesetting.GlobalEnrollBatch)
	 */
	public boolean modifyGlobalEnrollBatch(GlobalEnrollBatch globalEnrollBatch)throws Exception{
		if (0>=findTotalByProperty(globalEnrollBatch)) {
			globalEnrollBatchDao.update(globalEnrollBatch);
			return true;
		}
		return false;
	}
	
//	//按主键删除全局招生批次设置(物理删除)
//	public GlobalEnrollBatch deleteGlobalEnrollBatchById(Serializable id){
//		return globalEnrollBatchDao.deleteById(id);
//	}
//	
//	//按主键删除全局招生批次设置(逻辑删除)
//	public int deleteGlobalEnrollBatchByFlag(int id){
//		return globalEnrollBatchDao.deleteByFlag(id);
//	}
	
	/*
	 * 查询所有全局招生批次设置
	 */
	public List<GlobalEnrollBatch> findAllGlobalEnrollBatchs() throws Exception{
		return globalEnrollBatchDao.findAll();
	}
	
	/* 查询所有全局招生批次设置(delete_flag=0)
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.GlobalEnrollBatchBiz#findAllGlobalEnrollBatchByDeleteFlag()
	 */
	public List<GlobalEnrollBatch> findAllGlobalEnrollBatchByDeleteFlag() throws Exception{
		//return globalEnrollBatchDao.getByProperty("deleteFlag", Constants.DELETE_FALSE);
		return globalEnrollBatchDao.getByProperty(" and deleteFlag="+Constants.PLACEHOLDER+" order by title desc ",new Object[]{Constants.DELETE_FALSE});
	}
	
	/* 按主键id查询全局招生批次设置
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.GlobalEnrollBatchBiz#findGlobalEnrollBatchById(java.io.Serializable)
	 */
	public GlobalEnrollBatch findGlobalEnrollBatchById(Serializable id){
		return globalEnrollBatchDao.findById(id);
	}
	
	/* 查询未设置对应院校招生批次的全局招生批次
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.GlobalEnrollBatchBiz#findOtherGlobalEnrollBatchs(java.lang.Object[])
	 */
	public List<GlobalEnrollBatch> findOtherGlobalEnrollBatchs(Object...objects) throws Exception{
		String sql="";
		if(null!=objects&&objects.length>0){
			sql=" and deleteFlag="+Constants.PLACEHOLDER+" and id not in ('-1'";
			for(int i=0;i<objects.length-1;i++){
				sql+=","+Constants.PLACEHOLDER;
			}
			sql+=") order by id desc";
		}else{
			return this.findAllGlobalEnrollBatchByDeleteFlag();
		}
		
		return globalEnrollBatchDao.getByProperty(sql, objects);
	}
	
	/*查询数据苦中是否有重复的数据
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.GlobalEnrollBatchBiz#findTotalByProperty(net.cedu.entity.basesetting.GlobalEnrollBatch)
	 */
	@SuppressWarnings("unchecked")
	private int findTotalByProperty(GlobalEnrollBatch globalEnrollBatch)throws Exception{
		String sql="";
		List list= new ArrayList();
		
		if(globalEnrollBatch.getId()>0){
			sql+=" and id <> "+Constants.PLACEHOLDER;
			list.add(globalEnrollBatch.getId());
		}
		
		sql+=" and (batch="+Constants.PLACEHOLDER+" or title="+Constants.PLACEHOLDER+")";
		list.add(globalEnrollBatch.getBatch());
		list.add(globalEnrollBatch.getTitle());
	
		return globalEnrollBatchDao.findCountByProperty(sql, list);
	}
	
	/*删除(读取配置文件)
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.GlobalEnrollBatchBiz#deleteConfigGlobalEnrollBatch(int)
	 */
	public GlobalEnrollBatch deleteConfigGlobalEnrollBatch(int id){
		return globalEnrollBatchDao.deleteConfig(id);
	}
	
	/*	根据全局招生批次所属年份查询  全局招生批次列表
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.GlobalEnrollBatchBiz#findGlobalEnrollBatchByYear()
	 */
	public List<GlobalEnrollBatch> findGlobalEnrollBatchByYear(int belongyear) throws Exception{
		List<GlobalEnrollBatch> batchlist = new ArrayList<GlobalEnrollBatch>();
		if(belongyear==0){
			batchlist = this.findAllGlobalEnrollBatchByDeleteFlag();
		}else if(belongyear>0){
			String sql=" and deleteFlag="+Constants.PLACEHOLDER+" and belongYear="+Constants.PLACEHOLDER;
			batchlist = globalEnrollBatchDao.getByProperty(sql, new Object[]{Constants.DELETE_FALSE,belongyear});
		}else{
			batchlist=null;
		}
		return batchlist;
	}
	
	/*根据全局招生批次id查询其对应的全部院校招生批次id数组
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.GlobalEnrollBatchBiz#findAcademyBatchIdsbyGlobalBatchId()
	 */
	public int[] findAcademyBatchIdsbyGlobalBatchId(int batchid)throws Exception{
		int[] batchIds = null;
		List<AcademyEnrollBatch> academybacthlist = new ArrayList<AcademyEnrollBatch>();
		if (batchid>0) {
			academybacthlist = academyEnrollBatchBiz.findAllAcademyBatchbyGlobalBatchId(batchid);
			if(academybacthlist!=null&&academybacthlist.size()>0){
				batchIds = new int[academybacthlist.size()];
				for(int i=0,len=academybacthlist.size();i<len;i++){
					batchIds[i]=academybacthlist.get(i).getId();
				}
			}
		}
		return batchIds;
	}

	
	/*
	 * 根据全局批次Id(多)查询全局批次
	 * @see net.cedu.biz.basesetting.GlobalEnrollBatchBiz#findGlobalEnrollBatchByIds(int)
	 */
	public List<GlobalEnrollBatch> findGlobalEnrollBatchByIds(String ids)
			throws Exception {
		
		if(null!=ids && !"".equals(ids))
		{
			return globalEnrollBatchDao.getByProperty(" and id in ( "+Constants.PLACEHOLDER+" ) ", new Object[]{ids});
		}
		return null;
	}

	/*
	 * 根据当前全局批次id获取上一个全局批次id
	 * @see net.cedu.biz.basesetting.GlobalEnrollBatchBiz#findLastGlobalEnrollBatchIdByGlobalBatchId(int)
	 */
	public int findLastGlobalEnrollBatchIdByGlobalBatchId(int batchId)
			throws Exception {
		PageParame p = new PageParame();
		List<Object> list = new ArrayList<Object>();
		String hqlConditionExpression = "";
		if(batchId!=0)
		{
			hqlConditionExpression += " and id < " + Constants.PLACEHOLDER;
			list.add(batchId);
			hqlConditionExpression += " order by id desc limit 1 ";
			p.setHqlConditionExpression(hqlConditionExpression);
			p.setValues(list.toArray());
			Long[] ids = globalEnrollBatchDao.getIDs(p);
			if(ids != null && ids.length != 0)
			{
				return Integer.valueOf(ids[0].toString());
			}
		}
		return 0;
	}
	
	/*
	 * 查询所有全局招生批次所在的年份(delete_flag=0)
	 * 
	 * @see net.cedu.biz.basesetting.GlobalEnrollBatchBiz#findAllGlobalEnrollBatchYearsByDeleteFlag()
	 */
	public List<GlobalEnrollBatch> findAllGlobalEnrollBatchYearsByDeleteFlag() throws Exception{
		return globalEnrollBatchDao.getByProperty(" and deleteFlag="+Constants.PLACEHOLDER+" group by belongYear order by belongYear desc ",new Object[]{Constants.DELETE_FALSE});
	}
	
}
