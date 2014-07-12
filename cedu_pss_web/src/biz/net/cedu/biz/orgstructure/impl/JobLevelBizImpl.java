package net.cedu.biz.orgstructure.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.orgstructure.JobLevelBiz;
import net.cedu.common.Constants;
import net.cedu.dao.orgstructure.JobLevelDao;
import net.cedu.entity.orgstructure.JobLevel;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobLevelBizImpl implements JobLevelBiz 
{
	@Autowired
	private JobLevelDao jobLevelDao;				//团队DAO

	/**
	 * 添加子系统
	 * @param SubPrivilege
	 * @throws Exception
	 */
	public boolean addNew(JobLevel jobLevel)throws Exception
	{
		if(0>=findCountByConditionForHQL(jobLevel))
		{
			jobLevelDao.save(jobLevel);
			return true;
		}
		return false;
	}
	
	/**
	 * 修改子系统
	 * @param JobLevel
	 * @throws Exception
	 */
	public void modify(JobLevel jobLevel)throws Exception
	{
		jobLevelDao.update(jobLevel);
	}
	
	/**
	 * 根据ID删除子系统
	 * @param JobLevel
	 * @throws Exception
	 */
	public void deleteConfigById(int id)throws Exception
	{
		jobLevelDao.deleteConfig(id);
	}
	
	/**
	 * 查询子系统(供其他模块调用)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<JobLevel> findJobLevelForModel() throws Exception {
		return findAll();
	}
	
	/**
	 * 根据条件查找子系统列表_分页
	 */
	public List<JobLevel> findListByCondition(PageResult<JobLevel> pr,JobLevel jobLevel)
	{
		try {
			List<JobLevel> jobLevels = null;
			
			PageParame p = new PageParame(pr);
			
			String params="";
			String canshu="";
			
			//名称
			if(StringUtils.isNotBlank(jobLevel.getName()))
			{
				params+=" and name="+ Constants.PLACEHOLDER;
				canshu+=jobLevel.getName()+",";
			}
			
			params+=" order by levels asc";
						
			if(!params.equals(""))
			{
				p.setHqlConditionExpression(params);
				p.setValues(canshu.split(","));
			}
			
			// Ids集合
			Long[] jobLevelIds = jobLevelDao.getIDs(p);
			if (jobLevelIds != null && jobLevelIds.length != 0) 
			{
				jobLevels = new ArrayList<JobLevel>();
				for (int i = 0; i < jobLevelIds.length; i++) {
					// 内存获取
					JobLevel jobLevelObj = jobLevelDao.findById(Integer.valueOf(jobLevelIds[i].toString()));
					if (jobLevelObj != null) 
					{
						jobLevels.add(jobLevelObj);
					}
				}
			}
			return jobLevels;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据条件查找子系统列表
	 */
	public List<JobLevel> findListByCondition(JobLevel jobLevel)
	{
		String hqlcon="";
		List<Object> paramList=new ArrayList<Object>();
		
		//名称
		if(StringUtils.isNotBlank(jobLevel.getName()))
		{
			hqlcon+=" and name="+ Constants.PLACEHOLDER;
			paramList.add(jobLevel.getName());
		}
				
		hqlcon+=" order by levels asc";
		
		return jobLevelDao.getByProperty(hqlcon,paramList);
	}
	
	/**
	 * 根据条件查找子系统数量
	 */
	public int findCountByConditionForHQL(JobLevel jobLevel)
	{
		String hqlcon="";
		PageParame p = new PageParame();
		List<Object> paramList=new ArrayList<Object>();
		//权限ID
		if(StringUtils.isNotBlank(jobLevel.getName()))
		{
			hqlcon+=" and name="+ Constants.PLACEHOLDER;
			paramList.add(jobLevel.getName());
		}
		p.setHqlConditionExpression(hqlcon);
		p.setValues(paramList.toArray());
		return jobLevelDao.getCounts(p);
	}
	
	/**
	 * 查询所有子系统(包含总部)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<JobLevel> findAll() throws Exception {
		return jobLevelDao.findAll();
	}
	
	/**
	 * 根据ID查询子系统
	 */
	public JobLevel findJobLevelById(int id) throws Exception
	{
		return jobLevelDao.findById(id);
	}

	public List<JobLevel> findJobLevelListByJobLevelIds(String jobLevelIds)
			throws Exception {
		return jobLevelDao.getByProperty(" and id in ("+Constants.PLACEHOLDER+")", new Object[]{"$"+jobLevelIds});
	}

}
