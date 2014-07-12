package net.cedu.biz.orgstructure.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.orgstructure.JobBiz;
import net.cedu.biz.orgstructure.JobLevelBiz;
import net.cedu.common.Constants;
import net.cedu.dao.orgstructure.JobDao;
import net.cedu.entity.orgstructure.Job;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @功能：岗位业务层实现类
 *
 * @作者： 谭必庆
 * @作成时间：2011-12-29 上午11:55:51
 *
 * @修改者：
 * @修改内容：
 * @修改时间：
 *
 */
@Service
public class JobBizImpl implements JobBiz {
	
	@Autowired
	private JobDao jobDao;				//团队DAO
	@Autowired
	private JobLevelBiz jobLevelBiz;

	/**
	 * 添加子系统
	 * @param SubPrivilege
	 * @throws Exception
	 */
	public boolean addNew(Job job)throws Exception
	{
		if(0>=findCountByConditionForHQL(job))
		{
			jobDao.save(job);
			return true;
		}
		return false;
	}
	
	/**
	 * 修改子系统
	 * @param Job
	 * @throws Exception
	 */
	public void modify(Job job)throws Exception
	{
		jobDao.update(job);
	}
	
	/**
	 * 根据ID删除子系统
	 * @param Job
	 * @throws Exception
	 */
	public void deleteConfigById(int id)throws Exception
	{
		jobDao.deleteConfig(id);
	}
	
	/**
	 * 查询子系统(供其他模块调用)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Job> findJobForModel() throws Exception {
		return findAll();
	}
	
	/**
	 * 根据条件查找子系统列表_分页
	 */
	public List<Job> findListByCondition(PageResult<Job> pr,Job job)
	{
		try {
			List<Job> jobs = null;
			
			PageParame p = new PageParame(pr);
			
			String params="";
			String canshu="";
			
//			//名称
//			if(StringUtils.isNotBlank(job.getName()))
//			{
//				params+=" and name="+ Constants.PLACEHOLDER;
//				canshu+=job.getName()+",";
//			}
			
			//部门
			if(0<job.getDepartmentId())
			{
				params+=" and departmentId="+ Constants.PLACEHOLDER;
				canshu+=job.getDepartmentId()+",";
			}
			//岗位级别
			if(0<job.getJobLevelId())
			{
				params+=" and jobLevelId="+ Constants.PLACEHOLDER;
				canshu+=job.getJobLevelId()+",";
			}
			
			params+=" order by jobLevelId asc";
						
			if(!params.equals(""))
			{
				p.setHqlConditionExpression(params);
				p.setValues(canshu.split(","));
			}
			
			// Ids集合
			Long[] jobIds = jobDao.getIDs(p);
			if (jobIds != null && jobIds.length != 0) 
			{
				jobs = new ArrayList<Job>();
				for (int i = 0; i < jobIds.length; i++) {
					// 内存获取
					Job jobObj = jobDao.findById(Integer.valueOf(jobIds[i].toString()));
					if (jobObj != null) 
					{
						jobObj.setJobLevel(jobLevelBiz.findJobLevelById(jobObj.getJobLevelId()));
						jobs.add(jobObj);
					}
				}
			}
			return jobs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据条件查找子系统列表
	 */
	public List<Job> findListByCondition(Job job)
	{
		String hqlcon="";
		List<Object> paramList=new ArrayList<Object>();
		
//		//名称
//		if(StringUtils.isNotBlank(job.getName()))
//		{
//			hqlcon+=" and name="+ Constants.PLACEHOLDER;
//			paramList.add(job.getName());
//		}
		
		//部门
		if(0<job.getDepartmentId())
		{
			hqlcon+=" and departmentId="+ Constants.PLACEHOLDER;
			paramList.add(job.getDepartmentId());
		}
		//岗位级别
		if(0<job.getJobLevelId())
		{
			hqlcon+=" and jobLevelId="+ Constants.PLACEHOLDER;
			paramList.add(job.getDepartmentId());
		}
				
		hqlcon+=" order by jobLevelId asc";
		
		return jobDao.getByProperty(hqlcon,paramList);
	}
	
	/**
	 * 根据条件查找子系统数量
	 */
	public int findCountByConditionForHQL(Job job)
	{
		String hqlcon="";
		PageParame p = new PageParame();
		List<Object> paramList=new ArrayList<Object>();
//		//权限ID
//		if(StringUtils.isNotBlank(job.getName()))
//		{
//			hqlcon+=" and name="+ Constants.PLACEHOLDER;
//			paramList.add(job.getName());
//		}
		//部门
		if(0<job.getDepartmentId())
		{
			hqlcon+=" and departmentId="+ Constants.PLACEHOLDER;
			paramList.add(job.getDepartmentId());
		}
		//岗位级别
		if(0<job.getJobLevelId())
		{
			hqlcon+=" and jobLevelId="+ Constants.PLACEHOLDER;
			paramList.add(job.getJobLevelId());
		}
		p.setHqlConditionExpression(hqlcon);
		p.setValues(paramList.toArray());
		return jobDao.getCounts(p);
	}
	
	/**
	 * 查询所有子系统(包含总部)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Job> findAll() throws Exception {
		return jobDao.findAll();
	}
	
	/**
	 * 根据ID查询子系统
	 */
	public Job findJobById(int id) throws Exception
	{
		return jobDao.findById(id);
	}

	/**
	 * 通过部门ID获取岗位IDs
	  * @see net.cedu.biz.orgstructure.JobBiz#findJobLevelIdsByDepartmentId(java.lang.Integer)
	 */
	public String findJobLevelIdsByDepartmentId(int departmentId) {
//		String ids=",";
		StringBuffer idsSB = new StringBuffer(",");
		Long[] idArray=jobDao.getIDs(" and departmentId= "+Constants.PLACEHOLDER, new Object[]{departmentId});
		if(idArray!=null){
			for (Long l : idArray) {
//				if(ids.equals(",")){
//					ids=l+"";
//				}else{
//					ids+=","+l;
//				}
				if(idsSB.toString().equals(",")){
					idsSB = new StringBuffer(l+"");
				}else{
					idsSB.append(","+l);
				}
			}
		}
		
//		if(ids.equals(",")){
//			ids="0";
//		}
		if(idsSB.toString().equals(",")){
			idsSB = new StringBuffer("0");
		}
		return idsSB.toString();
	}

	/**
	 * 通过主键ID获取岗位级别Ids
	  * @see net.cedu.biz.orgstructure.JobBiz#findJobLevelIdsByJobIds(java.lang.String)
	 */
	public String findJobLevelIdsByJobIds(String jobIds)
			throws Exception {
//		String ids=",";
		StringBuffer idsSB = new StringBuffer(",");
		List<Job> jobList=jobDao.getByProperty(" and id in ( "+Constants.PLACEHOLDER+")", new Object[]{"$"+jobIds});
		if(jobList!=null){
			for (Job j : jobList) {
//				if(ids.equals(",")){
//					ids=j.getJobLevelId()+"";
//				}else{
//					ids+=","+j.getJobLevelId();
//				}
				if(idsSB.toString().equals(",")){
					idsSB = new StringBuffer(j.getJobLevelId()+"");
				}else{
					idsSB.append(","+j.getJobLevelId());
				}
			}
		}
		
//		if(ids.equals(",")){
//			ids="0";
//		}
		if(idsSB.toString().equals(",")){
			idsSB = new StringBuffer("0");
		}
		return idsSB.toString();
	}
}
