package net.cedu.biz.worklog.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.orgstructure.DepartmentBiz;
import net.cedu.biz.worklog.WorklogBiz;
import net.cedu.common.Constants;
import net.cedu.common.string.HtmlRegexpUtil;
import net.cedu.dao.worklog.WorklogDao;
import net.cedu.entity.admin.User;
import net.cedu.entity.worklog.Worklog;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 日报bizimpl中的方法
 * @author Jack
 *
 */
@Service
public class WorklogBizImpl implements WorklogBiz {
	@Autowired
	private WorklogDao worklogDao;
	@Autowired
	private UserBiz userBiz;
	@Autowired
	private DepartmentBiz departmentBiz;
	
	
	/**
	 * 按主键删除
	 */
	public void deleteMonthWorklog(int id) throws Exception {
		worklogDao.deleteById(id);
	}
    /**
     * 按主键进行查询
     */
	public Worklog getById(int id) throws Exception {
		return worklogDao.findById(id);
	}
	/**
	 * 添加工作日志
	 */
	public void createNew(Worklog log) throws Exception {
		worklogDao.save(log);
	}
	
	/**
	 * 修改工作日志
	 */
	public void update(Worklog log) throws Exception {
		worklogDao.update(log);
	}

	
	/**
	 * 根据条件查找日报数量_个人
	 */
	public int findCountByConditionForHQL(Worklog worklog,String starttime,String endtime)
	{
		String hqlcon="";
		PageParame p = new PageParame();
		List<Object> paramList=new ArrayList<Object>();

		//创建人
		if(0<worklog.getCreateBy())
		{
			hqlcon+=" and createBy="+ Constants.PLACEHOLDER;
			paramList.add(worklog.getCreateBy());
		}
		//开始日期
		if(StringUtils.isNotBlank(starttime))
		{
			hqlcon+=" and createOn >= "+Constants.PLACEHOLDER;
			paramList.add(starttime);
		}
		//结束日期
		if(StringUtils.isNotBlank(endtime))
		{
			hqlcon+=" and createOn <= "+Constants.PLACEHOLDER;
			paramList.add(endtime);
		}
		//状态status ID集合
		if(worklog.getStatusIds()!=null&&!"".equals(worklog.getStatusIds())){
			hqlcon+=" and status in ( "+Constants.PLACEHOLDER+")";
			paramList.add("$"+worklog.getStatusIds());
		}
		//状态
		if(worklog.getStatus()!=0){
			hqlcon+=" and status = "+Constants.PLACEHOLDER;
			paramList.add(worklog.getStatus());
		}
		hqlcon+=" and originalId = "+Constants.PLACEHOLDER;
		paramList.add(0);

		
		p.setHqlConditionExpression(hqlcon);
		p.setValues(paramList.toArray());
		return worklogDao.getCounts(p);
	}
	
	/**
	 * 根据条件查找日报列表_分页
	 */
	public List<Worklog> findListByCondition(PageResult<Worklog> pr,Worklog worklog,String starttime,String endtime)
	{
		try {
			List<Worklog> worklogs = null;
			
			PageParame p = new PageParame(pr);
			
			String hqlcon="";
			List<Object> paramList=new ArrayList<Object>();
			
			//创建人
			if(0<worklog.getCreateBy())
			{
				hqlcon+=" and createBy="+ Constants.PLACEHOLDER;
				paramList.add(worklog.getCreateBy());
			}
			//开始日期
			if(StringUtils.isNotBlank(starttime))
			{
				hqlcon+=" and createOn >= "+Constants.PLACEHOLDER;
				paramList.add(starttime);
			}
			//结束日期
			if(StringUtils.isNotBlank(endtime))
			{
				hqlcon+=" and createOn <= "+Constants.PLACEHOLDER;
				paramList.add(endtime);
			}
			//状态status ID集合
			if(worklog.getStatusIds()!=null&&!"".equals(worklog.getStatusIds())){
				hqlcon+=" and status in ( "+Constants.PLACEHOLDER+")";
				paramList.add("$"+worklog.getStatusIds());
			}
			//状态status ID集合
			if(worklog.getStatusIds()!=null&&!"".equals(worklog.getStatusIds())){
				hqlcon+=" and status in ( "+Constants.PLACEHOLDER+")";
				paramList.add("$"+worklog.getStatusIds());
			}
			//状态
			if(worklog.getStatus()!=0){
				hqlcon+=" and status = "+Constants.PLACEHOLDER;
				paramList.add(worklog.getStatus());
			}	
			hqlcon+=" and originalId = "+Constants.PLACEHOLDER;
			paramList.add(0);
			hqlcon+=" order by create_on desc";
						
			if(!hqlcon.equals(""))
			{
				p.setHqlConditionExpression(hqlcon);
				p.setValues(paramList.toArray());
			}
			
			// Ids集合
			Long[] worklogIds = worklogDao.getIDs(p);
			if (worklogIds != null && worklogIds.length != 0) 
			{
				worklogs = new ArrayList<Worklog>();
				for (int i = 0; i < worklogIds.length; i++) {
					// 内存获取
					Worklog worklogObj = worklogDao.findById(Integer.valueOf(worklogIds[i].toString()));
					if (worklogObj != null) 
					{
						List<Worklog> worklogObj2List=worklogDao.getByProperty("originalId", worklogObj.getId());
						for (Worklog worklog2 : worklogObj2List) {
							if(worklog2.getAuditId()!=0){
								worklog2.setAuditUser(userBiz.findUserById(worklog2.getAuditId()));
							}
						}
						
						//添加审批纪录
						worklogObj.setRecords(worklogObj2List);
						worklogObj.setCreateUser(userBiz.findUserById(worklogObj.getCreateBy()));
						worklogObj.setCuDepartment(departmentBiz.findDepartmentById(worklogObj.getCuDepartmentId()));
						if(0!=worklogObj.getOriginalId())
						{
							worklogObj.setOriginal(worklogDao.findById(worklogObj.getOriginalId()));
						}
						worklogs.add(worklogObj);
					}
				}
			}
			return worklogs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据条件查找日报数量_审核
	 */
	public int findCountByConditionForHQLAudit(Worklog worklog,String starttime,String endtime,String departmentIds)
	{
		String hqlcon="";
		PageParame p = new PageParame();
		List<Object> paramList=new ArrayList<Object>();

		
		//开始日期
		if(StringUtils.isNotBlank(starttime))
		{
			hqlcon+=" and createOn >= "+Constants.PLACEHOLDER;
			paramList.add(starttime);
		}
		//结束日期
		if(StringUtils.isNotBlank(endtime))
		{
			hqlcon+=" and createOn <= "+Constants.PLACEHOLDER;
			paramList.add(endtime);
		}
		//状态status ID集合
		if(worklog.getStatusIds()!=null&&!"".equals(worklog.getStatusIds())){
			hqlcon+=" and status in ( "+Constants.PLACEHOLDER+")";
			paramList.add("$"+worklog.getStatusIds());
		}
		//状态
		if(worklog.getStatus()!=0){
			hqlcon+=" and status = "+Constants.PLACEHOLDER;
			paramList.add(worklog.getStatus());
		}
		//创建人
		if(worklog.getCreateBy()!=0){
			hqlcon+=" and createBy = "+Constants.PLACEHOLDER;
			paramList.add(worklog.getCreateBy());
		}
		//创建人IDs
		if(worklog.getUserIds()!=null&&!worklog.getUserIds().equals("")){
			hqlcon+=" and createBy in ( "+Constants.PLACEHOLDER+")";
			paramList.add("$"+worklog.getUserIds());
		}
		
		hqlcon+=" and cuDepartmentId in("+Constants.PLACEHOLDER+")";
		paramList.add("$"+departmentIds);
		
		//hqlcon+=" and score=0";
		
		p.setHqlConditionExpression(hqlcon);
		p.setValues(paramList.toArray());
		return worklogDao.getCounts(p);
	}
	
	/**
	 * 根据条件查找日报列表_分页
	 */
	public List<Worklog> findListByConditionAudit(PageResult<Worklog> pr,Worklog worklog,String starttime,String endtime,String departmentIds)
	{
		try {
			List<Worklog> worklogs = null;
			
			String hqlcon="";
			PageParame p = new PageParame(pr);
			List<Object> paramList=new ArrayList<Object>();			
			
			
			//开始日期
			if(StringUtils.isNotBlank(starttime))
			{
				hqlcon+=" and createOn >= "+Constants.PLACEHOLDER;
				paramList.add(starttime);
			}
			//结束日期
			if(StringUtils.isNotBlank(endtime))
			{
				hqlcon+=" and createOn <= "+Constants.PLACEHOLDER;
				paramList.add(endtime);
			}
			//状态status ID集合
			if(worklog.getStatusIds()!=null&&!"".equals(worklog.getStatusIds())){
				hqlcon+=" and status in ( "+Constants.PLACEHOLDER+")";
				paramList.add("$"+worklog.getStatusIds());
			}
			//状态
			if(worklog.getStatus()!=0){
				hqlcon+=" and status = "+Constants.PLACEHOLDER;
				paramList.add(worklog.getStatus());
			}
			
			//创建人
			if(worklog.getCreateBy()!=0){
				hqlcon+=" and createBy = "+Constants.PLACEHOLDER;
				paramList.add(worklog.getCreateBy());
			}
			//创建人IDs
			if(worklog.getUserIds()!=null&&!worklog.getUserIds().equals("")){
				hqlcon+=" and createBy in ( "+Constants.PLACEHOLDER+")";
				paramList.add("$"+worklog.getUserIds());
			}
			
			hqlcon+=" and cuDepartmentId in("+Constants.PLACEHOLDER+")";
			paramList.add("$"+departmentIds);
			
			//hqlcon+=" and score=0";
			
			hqlcon+=" order by create_on desc";
						
			p.setHqlConditionExpression(hqlcon);
			p.setValues(paramList.toArray());
			
			List<User> userList=userBiz.findUserForModel();
			Map<Integer,User> userMap=new HashMap<Integer,User>();
			for (User user : userList) {
				userMap.put(user.getId(), user);
			}
			
			// Ids集合
			Long[] worklogIds = worklogDao.getIDs(p);
			if (worklogIds != null && worklogIds.length != 0) 
			{
				worklogs = new ArrayList<Worklog>();
				for (int i = 0; i < worklogIds.length; i++) {
					// 内存获取
					Worklog worklogObj = worklogDao.findById(Integer.valueOf(worklogIds[i].toString()));
					if (worklogObj != null) 
					{
						List<Worklog> worklogObj2List=worklogDao.getByProperty("originalId", worklogObj.getId());
						for (Worklog worklog2 : worklogObj2List) {
							
							//if(worklog2.getAuditId()!=0){
								//worklog2.setAuditUser(userBiz.findUserById(worklog2.getAuditId()));
								User user=userMap.get(worklog2.getAuditId());
								//System.out.println(user+"---------------------------");
								if(user!=null){
									worklog2.setAuditUser(user);
								}else{
									worklog2.setAuditUser(new User());
								}
								worklog2.setContent(HtmlRegexpUtil.filterHtml(worklog2.getContent()));
							//}
							
						}
						//添加审批纪录
						worklogObj.setRecords(worklogObj2List);
						
						//worklogObj.setCreateUser(userBiz.findUserById(worklogObj.getCreateBy()));
						
						//优化以后
						User user=userMap.get(worklogObj.getCreateBy());
						if(user!=null){
							worklogObj.setCreateUser(user);
						}else{
							worklogObj.setCreateUser(new User());
						}
						worklogObj.setCuDepartment(departmentBiz.findDepartmentById(worklogObj.getCuDepartmentId()));
						worklogs.add(worklogObj);
					}
				}
			}
			return worklogs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 日报是否存在
	  * @see net.cedu.biz.worklog.WorklogBiz#findWorklogByParams(int, java.lang.String)
	 */
	public boolean findWorklogByParams(int useId, String date) {
		List<Worklog> worklogList=worklogDao.getByProperty(" and original_id=0 and create_by="+Constants.PLACEHOLDER+" and create_on = "+Constants.PLACEHOLDER+"", new Object[]{useId,date});
		return worklogList!=null&&worklogList.size()>0?true:false;
	}
	/**
	 * 查询评论List
	  * @see net.cedu.biz.worklog.WorklogBiz#findChilds(int)
	 */
	public List<Worklog> findChilds(int id) {
		return worklogDao.getByProperty("originalId", id);
	}
	
}
