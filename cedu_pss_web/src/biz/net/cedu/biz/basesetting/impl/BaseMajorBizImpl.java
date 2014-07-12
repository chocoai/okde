package net.cedu.biz.basesetting.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.basesetting.BaseMajorBiz;
import net.cedu.common.Constants;
import net.cedu.dao.basesetting.BaseMajorDao;
import net.cedu.entity.basesetting.BaseMajor;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 专业(基础设置)
 * @author Hxj
 *
 */
@Service
public class BaseMajorBizImpl implements BaseMajorBiz{

	@Autowired
	private BaseMajorDao baseMajorDao;
	
	/*	增加
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.BaseMajorBiz#addBaseMajor(net.cedu.entity.basesetting.BaseMajor)
	 */
	public boolean addBaseMajor(BaseMajor baseMajor) throws Exception{
		if(0>=this.findTotalByProperty(baseMajor)){
			 baseMajorDao.save(baseMajor);
			 return true;
		}
		return false;
	}
	
//	//物理删除
//	public BaseMajor deleteBaseMajorById(Serializable id){
//		return baseMajorDao.deleteById(id);
//	}
//	
//	//逻辑删除
//	public int deleteBaseMajorByflag(int id){
//		return baseMajorDao.deleteByFlag(id);
//	}
	
	/*
	 * 修改
	 */
	public boolean modifyBaseMajor(BaseMajor baseMajor)throws Exception{
		if (0>=findTotalByProperty(baseMajor)) {
			baseMajorDao.update(baseMajor);
			return true;
		}
		return false;
	}
	
	/*	按主键ID查询
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.BaseMajorBiz#findBaseMajorbyId(java.io.Serializable)
	 */
	public BaseMajor findBaseMajorbyId(Serializable id){
		return baseMajorDao.findById(id);
	}
	
	/*	查询全部数据
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.BaseMajorBiz#findAllBaseMajors()
	 */
	public List<BaseMajor> findAllBaseMajors() throws Exception{
		String sql=" order by name";
		return baseMajorDao.getByProperty(sql, new Object[]{});
	}
	
	/*	查询全部deleteFlag=0的数据
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.BaseMajorBiz#findBaseMajorByFlag()
	 */
	public List<BaseMajor> findBaseMajorByFlag() throws Exception{
		String sql=" and deleteFlag = "+Constants.PLACEHOLDER+" order by name";
		return baseMajorDao.getByProperty(sql,new Object[]{Constants.DELETE_FALSE});
	}
	
	/*查询数据苦中是否有重复的数据
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.BaseMajorBiz#findTotalByProperty(net.cedu.entity.basesetting.BaseMajor)
	 */
	@SuppressWarnings("unchecked")
	private int findTotalByProperty(BaseMajor basemajor)throws Exception{
		String sql="";
		List list=new ArrayList();
		
		if(basemajor.getId()>0){
			sql+=" and id <> "+Constants.PLACEHOLDER;
			list.add(basemajor.getId());
		}
		
		if(StringUtils.isNotBlank(basemajor.getCode())){
			sql+=" and code="+Constants.PLACEHOLDER;
			list.add(basemajor.getCode());
		}
	
		return baseMajorDao.findCountByProperty(sql, list);
	}
	
	/*删除(读取配置文件)
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.BaseMajorBiz#deleteConfigBaseMajor(int)
	 */
	public BaseMajor deleteConfigBaseMajor(int id){
		return baseMajorDao.deleteConfig(id);
	}

	
}
