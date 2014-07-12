package net.cedu.biz.admin.privilege.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.admin.privilege.SubSystemBiz;
import net.cedu.common.Constants;
import net.cedu.dao.admin.privilege.SubSystemDao;
import net.cedu.entity.admin.privilege.SubSystem;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 子系统业务层实现类
 * @author Jack
 *
 */
@Service
public class SubSystemBizImpl implements SubSystemBiz {

	@Autowired
	private SubSystemDao subSystemDao;
	
	/**
	 * 添加子系统
	 * @param SubPrivilege
	 * @throws Exception
	 */
	public boolean addNew(SubSystem subSystem)throws Exception
	{
		if(0>=findCountByConditionForHQL(subSystem))
		{
			subSystemDao.save(subSystem);
			return true;
		}
		return false;
	}
	
	/**
	 * 修改子系统
	 * @param SubSystem
	 * @throws Exception
	 */
	public void modify(SubSystem subSystem)throws Exception
	{
		subSystemDao.update(subSystem);
	}
	
	/**
	 * 根据ID删除子系统
	 * @param SubSystem
	 * @throws Exception
	 */
	public void deleteConfigById(int id)throws Exception
	{
		subSystemDao.deleteConfig(id);
	}
	
	/**
	 * 查询子系统(供其他模块调用)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<SubSystem> findSubSystemForModel() throws Exception {
		return findAll();
	}
	
	/**
	 * 根据条件查找子系统列表_分页
	 */
	public List<SubSystem> findListByCondition(PageResult<SubSystem> pr,SubSystem subSystem)
	{
		try {
			List<SubSystem> subSystems = null;
			
			PageParame p = new PageParame(pr);
			
			String params="";
			String canshu="";
			
			//名称
			if(StringUtils.isNotBlank(subSystem.getName()))
			{
				params+=" and name="+ Constants.PLACEHOLDER;
				canshu+=subSystem.getName()+",";
			}
			
			params+=" order by orders asc";
						
			if(!params.equals(""))
			{
				p.setHqlConditionExpression(params);
				p.setValues(canshu.split(","));
			}
			
			// Ids集合
			Long[] subSystemIds = subSystemDao.getIDs(p);
			if (subSystemIds != null && subSystemIds.length != 0) 
			{
				subSystems = new ArrayList<SubSystem>();
				for (int i = 0; i < subSystemIds.length; i++) {
					// 内存获取
					SubSystem subSystemObj = subSystemDao.findById(Integer.valueOf(subSystemIds[i].toString()));
					if (subSystemObj != null) 
					{
						subSystems.add(subSystemObj);
					}
				}
			}
			return subSystems;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据条件查找子系统列表
	 */
	public List<SubSystem> findListByCondition(SubSystem subSystem)
	{
		String hqlcon="";
		List<Object> paramList=new ArrayList<Object>();
		
		//名称
		if(StringUtils.isNotBlank(subSystem.getName()))
		{
			hqlcon+=" and name="+ Constants.PLACEHOLDER;
			paramList.add(subSystem.getName());
		}
				
		hqlcon+=" order by orders asc";
		
		return subSystemDao.getByProperty(hqlcon,paramList);
	}
	
	/**
	 * 根据条件查找子系统数量
	 */
	public int findCountByConditionForHQL(SubSystem subSystem)
	{
		String hqlcon="";
		PageParame p = new PageParame();
		List<Object> paramList=new ArrayList<Object>();
		//权限ID
		if(StringUtils.isNotBlank(subSystem.getName()))
		{
			hqlcon+=" and name="+ Constants.PLACEHOLDER;
			paramList.add(subSystem.getName());
		}
		p.setHqlConditionExpression(hqlcon);
		p.setValues(paramList.toArray());
		return subSystemDao.getCounts(p);
	}
	
	/**
	 * 查询所有子系统(包含总部)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<SubSystem> findAll() throws Exception {
		return subSystemDao.findAll();
	}
	
	/**
	 * 根据ID查询子系统
	 */
	public SubSystem findSubSystemById(int id) throws Exception
	{
		return subSystemDao.findById(id);
	}

}
