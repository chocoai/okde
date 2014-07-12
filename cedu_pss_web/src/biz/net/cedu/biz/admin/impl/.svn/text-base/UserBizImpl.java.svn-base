package net.cedu.biz.admin.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.UserEnrollQuotaBiz;
import net.cedu.biz.orgstructure.DepartmentBiz;
import net.cedu.biz.orgstructure.JobBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.enums.BranchEnum;
import net.cedu.common.enums.UserEnum;
import net.cedu.common.md5.PwdCoder;
import net.cedu.dao.admin.UserDao;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.User;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.UserEnrollQuota;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务层实现类
 * 
 * @author Jack
 * 
 */
@Service
public class UserBizImpl implements UserBiz {

	@Autowired
	private UserDao userDao;
	@Autowired
	private BranchBiz branchBiz;
	@Autowired
	private DepartmentBiz departmentBiz;
	@Autowired
	private JobBiz jobBiz;
	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private StudentBiz studentBiz;
	@Autowired
	private UserEnrollQuotaBiz userEnrollQuotaBiz;
	
	/**
	 * 查询用户(供其他模块调用)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<User> findUserForModel() throws Exception {
		return userDao.findAll();
	}

	/**
	 * 根据ID查询用户
	 */
	public User findUserById(int id) throws Exception {
		User user=userDao.findById(id);
		user.setOrg(branchBiz.findBranchById(user.getOrgId()));
		return user;
	}

	/**
	 * 添加用户
	 * 
	 * @param User
	 * @throws Exception
	 */
	public boolean createNew(User user) throws Exception 
	{
		if(null==findByUserName(user.getUserName()))
		{
			user.setPassword(PwdCoder.getMD5(user.getPassword()));
			userDao.save(user);
			return true;
		}
		return false;
	}

	
	/**
	 * 根据ID删除用户
	 * 
	 * @param User
	 * @throws Exception
	 */
	public void deleteConfigById(int id) throws Exception {
		userDao.deleteConfig(id);

	}

	/**
	 * 修改用户
	 * 
	 * @param User
	 * @throws Exception
	 */
	public void modify(User user) throws Exception {
		userDao.update(user);

	}

	/**
	 * 按用户名查找用户
	 */
	public User findByUserName(String userName) throws Exception {
		String hqlcon = "";
		List<User> list = null;
		List<Object> paramList = new ArrayList<Object>();

		// 名称
		if (StringUtils.isNotBlank(userName)) {
			hqlcon += " and userName=" + Constants.PLACEHOLDER;
			paramList.add(userName);
		}
		if (0 < hqlcon.length())
			list = userDao.getByProperty(hqlcon, paramList);
		if (null != list && 0 < list.size())
			return list.get(0);
		return null;
	}

	/**
	 * 按学习中心ID查找用户_供业务模块调用
	 */
	public List<User> findUserPageListByBranchId(User user, PageResult<User> pr)throws Exception 
	{
		List<User> users = new ArrayList<User>();
		PageParame p = new PageParame(pr);
		List<Object> list=new ArrayList<Object>();
		String hql="";
		if (user != null) 
		{
			if(0<user.getOrgId())
			{
				hql+=" and orgId=" + Constants.PLACEHOLDER;
				list.add(user.getOrgId());
			}
			if(-1<user.getIsManager())
			{
				hql+=" and isManager=" + Constants.PLACEHOLDER;
				list.add(user.getIsManager());
			}
			if(-1<user.getStatus())
			{
				hql+=" and status=" + Constants.PLACEHOLDER;
				list.add(UserEnum.StatusStart.value());
			}
			if(-1<user.getDeleteFlag())
			{
				hql+=" and deleteFlag=" + Constants.PLACEHOLDER;
				list.add(UserEnum.DeleteNo.value());
			}
			p.setHqlConditionExpression(hql);
			p.setValues(list.toArray());
		}

		Long[] userIds = userDao.getIDs(p);
		if (userIds != null && userIds.length != 0) {
			for (int i = 0; i < userIds.length; i++) {
				User u = userDao.findById(Integer.parseInt(userIds[i]
						.toString()));
				if (u != null) {
					users.add(u);
				}
			}
		}

		return users;

	}

	/**
	 * 按学习中心ID查找用户数量_供业务模块调用
	 */
	public int findUserPageCountByBranchId(User user, PageResult<User> pr)
			throws Exception {
		PageParame p = new PageParame(pr);
		List<Object> list=new ArrayList<Object>();
		String hql="";
		if (user != null) 
		{
			if(0<user.getOrgId())
			{
				hql+=" and orgId=" + Constants.PLACEHOLDER;
				list.add(user.getOrgId());
			}
			if(-1<user.getIsManager())
			{
				hql+=" and isManager=" + Constants.PLACEHOLDER;
				list.add(user.getIsManager());
			}
			if(-1<user.getStatus())
			{
				hql+=" and status=" + Constants.PLACEHOLDER;
				list.add(UserEnum.StatusStart.value());
			}
			if(-1<user.getDeleteFlag())
			{
				hql+=" and deleteFlag=" + Constants.PLACEHOLDER;
				list.add(UserEnum.DeleteNo.value());
			}
			p.setHqlConditionExpression(hql);
			p.setValues(list.toArray());
		}
		return userDao.getCounts(p);
	}
	
	
	
	/**
	 * 按学习中心ID查找用户_权限系统用
	 */
	public List<User> findUserPageListByBranchIdAdmin(User user, PageResult<User> pr)
			throws Exception {
		List<User> users = new ArrayList<User>();
		PageParame p = new PageParame(pr);
		
		List<Object> paramlist=new ArrayList<Object>();
		String hql="";
		if (user != null) {
			
			if(StringUtils.isNotBlank(user.getUserName()))
			{
				hql+="and userName like "+Constants.PLACEHOLDER;
				paramlist.add("%"+user.getUserName()+"%");
			}
			if(StringUtils.isNotBlank(user.getFullName()))
			{
				hql+="and fullName like "+Constants.PLACEHOLDER;
				paramlist.add("%"+user.getFullName()+"%");
			}
			if(-1<user.getDeleteFlag())
			{
				hql+=" and deleteFlag=" + Constants.PLACEHOLDER;
				paramlist.add(UserEnum.DeleteNo.value());
			}
			if(-2<user.getOrgId())
			{
				hql+="and orgId=" + Constants.PLACEHOLDER;
				paramlist.add(user.getOrgId());
				
			}
			p.setHqlConditionExpression(hql);
			p.setValues(paramlist.toArray());
		}

		Long[] userIds = userDao.getIDs(p);
		if (userIds != null && userIds.length != 0) {
			for (int i = 0; i < userIds.length; i++) {
				User u = userDao.findById(Integer.parseInt(userIds[i]
						.toString()));
				if (u != null) {
					u.setDepartment(departmentBiz.findDepartmentById(u.getDepartmentId()));
					u.setJob(jobBiz.findJobById(u.getJobId()));
					users.add(u);
				}
			}
		}

		return users;

	}

	/**
	 * 按学习中心ID查找用户数量_权限系统用
	 */
	public int findUserPageCountByBranchIdAdmin(User user, PageResult<User> pr)
			throws Exception {
		PageParame p = new PageParame(pr);
		List<Object> paramlist=new ArrayList<Object>();
		String hql="";
		if (user != null) {
			
			if(StringUtils.isNotBlank(user.getUserName()))
			{
				hql+="and userName like "+Constants.PLACEHOLDER;
				paramlist.add("%"+user.getUserName()+"%");
			}
			if(StringUtils.isNotBlank(user.getFullName()))
			{
				hql+="and fullName like "+Constants.PLACEHOLDER;
				paramlist.add("%"+user.getFullName()+"%");
			}
			if(-1<user.getDeleteFlag())
			{
				hql+=" and deleteFlag=" + Constants.PLACEHOLDER;
				paramlist.add(UserEnum.DeleteNo.value());
			}
			if(-2<user.getOrgId())
			{
				hql+="and orgId=" + Constants.PLACEHOLDER;
				paramlist.add(user.getOrgId());
				
			}
			p.setHqlConditionExpression(hql);
			p.setValues(paramlist.toArray());
		}

		return userDao.getCounts(p);
	}
	
	/**
	 * 按条件查询用户列表
	 * return 启用状态未删除的用户
	 */
	public List<User> findUsersByCondition(User user) throws Exception 
	{
		String hqlcon = "";
		List<User> list = null;
		List<Object> paramList = new ArrayList<Object>();

		if(user.getOrgId()==-2){
			hqlcon += " and orgId!=" + Constants.PLACEHOLDER;
			paramList.add(BranchEnum.Root.value());
		}else{
			//学习中心ID
			if (0<user.getOrgId()||user.getOrgId()==BranchEnum.Root.value()) {
				hqlcon += " and orgId=" + Constants.PLACEHOLDER;
				paramList.add(user.getOrgId());
			}
		}
		hqlcon+=" and status="+ Constants.PLACEHOLDER;
		paramList.add(UserEnum.StatusStart.value());
		hqlcon+=" and deleteFlag="+ Constants.PLACEHOLDER;
		paramList.add(UserEnum.DeleteNo.value());
		list = userDao.getByProperty(hqlcon, paramList);
		return list;
	}
	
	/**
	 * 查询总部用户
	 * 
	 * return 启用状态未删除的用户
	 */
	public List<User> findUsersForAdmin() throws Exception 
	{
		String hqlcon = "";
		List<User> list = null;
		List<Object> paramList = new ArrayList<Object>();
		hqlcon += " and orgId=" + Constants.PLACEHOLDER;
		paramList.add(BranchEnum.Admin.value());
		hqlcon+=" and status="+ Constants.PLACEHOLDER;
		paramList.add(UserEnum.StatusStart.value());
		hqlcon+=" and deleteFlag="+ Constants.PLACEHOLDER;
		paramList.add(UserEnum.DeleteNo.value());
		list = userDao.getByProperty(hqlcon, paramList);
		return list;
	}

	/**
	 * 通过中心ID查询用户集合_供业务模块调用
	 * @param orgId 学习中心ID
	 * return 启用状态未删除的用户
	 */
	public List<User> findUsersByOrgId(int orgId) throws Exception 
	{
		User user=new User();
		user.setOrgId(orgId);
		return findUsersByCondition(user);
	}

	/*
	 * 通过中心ID查询用户集合_供业务模块调用
	 * @see net.cedu.biz.admin.UserBiz#findUsersByOrgId(int, int)
	 */
	public List<User> findUsersByOrgId(int orgId, int areaManager)
			throws Exception {
		String hqlcon = "";
		List<User> list = null;
		List<Object> paramList = new ArrayList<Object>();
		//学习中心ID
		if (0<orgId) {
			hqlcon += " and orgId=" + Constants.PLACEHOLDER;
			paramList.add(orgId);
		}
		//是否为区域经理
		if (areaManager>=0) {
			hqlcon += " and isManager=" + Constants.PLACEHOLDER;
			paramList.add(areaManager);
		}
		hqlcon+=" and status="+ Constants.PLACEHOLDER;
		paramList.add(UserEnum.StatusStart.value());
		hqlcon+=" and deleteFlag="+ Constants.PLACEHOLDER;
		paramList.add(UserEnum.DeleteNo.value());
		list = userDao.getByProperty(hqlcon, paramList);
		return list;
	}

	/*
	 * 通过中心ID查询用户集合
	 * @see net.cedu.biz.admin.UserBiz#countUserByOrgId(int)
	 */
	public int countUserByOrgId(int orgId) throws Exception {
		PageParame p = new PageParame();
		List<Object> list=new ArrayList<Object>();
		String hql="";
		if(orgId!=0)
		{
			hql += " and orgId=" + Constants.PLACEHOLDER;
			list.add(orgId);
		}
		hql+=" and deleteFlag="+ Constants.PLACEHOLDER;
		list.add(UserEnum.DeleteNo.value());
		p.setHqlConditionExpression(hql);
		p.setValues(list.toArray());
		return userDao.getCounts(p);
	}

	
	/*
	 * 获取用户Ids
	 * @see net.cedu.biz.admin.UserBiz#findUserIds(net.cedu.model.page.PageParame)
	 */
	public Long[] findUserIds(PageParame p) throws Exception {
		
		return userDao.getIDs(p);
	}

	
	/*
	 *  查询中心用户
	 * @see net.cedu.biz.admin.UserBiz#findUsersForBranch()
	 */
	public List<User> findUsersForBranch() throws Exception {
		
		return userDao.getByProperty(" and type = "+ Constants.PLACEHOLDER+" and deleteFlag = "+ Constants.PLACEHOLDER,new Object[]{UserEnum.TypeBranch.value(),Constants.DELETE_FALSE});
	}
	
	
	/*
	 * 通过中心ID查询跟进人集合
	 * 
	 * @see net.cedu.biz.admin.UserBiz#findUserFollowUpListByOrgId(int)
	 */
	public List<User> findUserFollowUpListByOrgId(int orgId) throws Exception 
	{
		List<User> ulist=null;
		PageParame p = new PageParame();
		List<Object> list=new ArrayList<Object>();
		String hql="";
		if(orgId!=0)
		{
			hql += " and orgId=" + Constants.PLACEHOLDER;
			list.add(orgId);
		}
		//hql+=" and type="+ Constants.PLACEHOLDER;
		//list.add(UserEnum.TypeBranch.value());
		hql+=" and status="+ Constants.PLACEHOLDER;
		list.add(UserEnum.StatusStart.value());
		hql+=" and deleteFlag="+ Constants.PLACEHOLDER;
		list.add(UserEnum.DeleteNo.value());
		hql+=" and id<>"+ Constants.PLACEHOLDER;
		list.add(UserEnum.Root.value());
		p.setHqlConditionExpression(hql);
		p.setValues(list.toArray());
		ulist=userDao.getByProperty(hql, list);
		return ulist;
	}

	/**
	 * 通过部门ID字符串查询查询用户集合
	  * @see net.cedu.biz.admin.UserBiz#findUsersByDepartmentIds(java.lang.String)
	 */
	public List<User> findUsersByDepartmentIds(String departmentIds)
			throws Exception {
		String hqlcon = "";
		List<User> list = null;
		List<Object> paramList = new ArrayList<Object>();
		hqlcon+=" and department_id in ("+ Constants.PLACEHOLDER+")";
		paramList.add("$"+departmentIds);
		
		hqlcon+=" and deleteFlag="+ Constants.PLACEHOLDER;
		paramList.add(UserEnum.DeleteNo.value());
		list = userDao.getByProperty(hqlcon, paramList);
		return list;
	}

	/**
	 * 查询所有区域经理包括已停用的
	  * @see net.cedu.biz.admin.UserBiz#findAllAreaManager()
	 */
	public List<User> findAllAreaManager() throws Exception {
		return userDao.getByProperty("ismanager", UserEnum.ManagerYes.value());
	}
	
	/**
	 * 查询所有区域经理包括(不包括已停用 已删除)
	  * @see net.cedu.biz.admin.UserBiz#findAllAreaManagerByStatusAndDeleteFlag()
	 */
	public List<User> findAllAreaManagerByStatusAndDeleteFlag() throws Exception {
		return userDao.getByProperty(" and status="+Constants.PLACEHOLDER+" and deleteFlag="+Constants.PLACEHOLDER+" and ismanager="+ Constants.PLACEHOLDER, new Object[]{0,Constants.DELETE_FALSE,UserEnum.ManagerYes.value()});
	}
	
	/**
	 * 查询所有申请人
	 */
	public Long[] findUserByNames(String name) throws Exception {
		List<Object> list=new ArrayList<Object>();
		Long [] ids =  null;
		String hql="";
		if(StringUtils.isNotBlank(name))
		{
			hql += " and fullName like " + Constants.PLACEHOLDER;
			list.add('%'+name+'%');
		}
		 
		
		ids = userDao.getIDs(hql, list.toArray());
		return ids;
	}

	/*
	 * 假删除用户 有验证条件
	 * @see net.cedu.biz.admin.UserBiz#deleteFalseUserById(int)
	 */
	public List<String> deleteFalseUserById(int deleteUserId,int currentUserId) throws Exception {
		// 每个错误消息最多输出条数
		int errorCount = 3;
		List<String> errorList = null;
		// 没有用户id
		if(deleteUserId==0){
			errorList = new ArrayList<String>();
			errorList.add("请选择要删除的用户！");
			return errorList;
		}
		// 用户不可删除自己
		if(deleteUserId!=0 && deleteUserId==currentUserId){
			errorList = new ArrayList<String>();
			errorList.add("用户不可删除自己！");
			return errorList;
		}
		// root等特殊用户不可删除
		if(deleteUserId<0){
			errorList = new ArrayList<String>();
			errorList.add("特殊用户不可删除！");
			return errorList;
		}
		User user = userDao.findById(deleteUserId);
		if(user!=null){
			// 如果是假删除状态则跳过
			if(user.getDeleteFlag()==Constants.DELETE_TRUE){
				errorList = new ArrayList<String>();
				errorList.add(user.getFullName()+":该用户已被删除！");
				return errorList;
			}
			// 院校项目经理
			Academy academy = new Academy();
			academy.setProjectManagerId(user.getId());
			List<Academy> academyList = academyBiz.findAcademyListByParams(academy,errorCount);
			int academyCount = academyList==null?0:academyList.size();
			// 学生跟进人
			Student student = new Student();
			student.setUserId(user.getId());
			List<Student> studentList = studentBiz.findStudentListByParams(student,errorCount);
			int studentCount = studentList==null?0:studentList.size();
			// 中心人员指标
			UserEnrollQuota userEnrollQuota = new UserEnrollQuota();
			userEnrollQuota.setUserId(user.getId());
			userEnrollQuota.setBranchId(user.getOrgId());
			List<UserEnrollQuota> userEnrollQuotaList = userEnrollQuotaBiz.findUserEnrollQuotaListByParams(userEnrollQuota,errorCount);
			int userEnrollQuotaCount = userEnrollQuotaList==null?0:userEnrollQuotaList.size();
			// 如果均为空，则可删除
			if(academyCount==0 && studentCount==0 && userEnrollQuotaCount==0){
				// 删除相关垃圾数据，将用户设置为禁用，假删除
				try {
					// 删除相关中心人员招生指标
					UserEnrollQuota ueq = new UserEnrollQuota();
					ueq.setUserId(user.getId());
					userEnrollQuotaBiz.deleteUserEnrollQuotaByParams(ueq);
					// 假删除用户
					user.setStatus(1);
					user.setDeleteFlag(Constants.DELETE_TRUE);
					user.setUpdaterId(currentUserId);
					user.setUpdatedTime(DateUtil.getNow());
					this.modify(user);
					return null;
				} catch (Exception e) {
					errorList = new ArrayList<String>();
					errorList.add("数据异常！");
					return errorList;
				}
			}
			// 不为空，输出错误消息
			else{
				errorList = new ArrayList<String>();
				// 输出院校错误信息
				if(academyCount>0){
					errorList.add(user.getFullName()+":此用户为下列院校的项目经理：(最少"+academyCount+"条)");
					for(int i=0;i<academyCount;i++){
						errorList.add((i+1)+"."+academyList.get(i).getName());
					}
				}
				// 输出学生错误信息
				if(studentCount>0){
					errorList.add(user.getFullName()+":此用户为下列学生的跟进人：(最少"+studentCount+"条)");
					for(int i=0;i<studentCount;i++){
						errorList.add((i+1)+"."+studentList.get(i).getName());
					}
				}
				// 输出中心人员招生指标错误信息
				if(userEnrollQuotaCount>0){
					errorList.add(user.getFullName()+":此用户在该批次下有招生指标：(最少"+userEnrollQuotaCount+"条)");
					for(int i=0;i<userEnrollQuotaCount;i++){
						errorList.add((i+1)+"."+userEnrollQuotaList.get(i).getBatchName());
					}
				}
				return errorList;
			}
		}else{
			errorList = new ArrayList<String>();
			errorList.add("id:"+deleteUserId+"无此用户！");
			return errorList;
		}
	}

	/*
	 * 还原假删除的用户
	 * @see net.cedu.biz.admin.UserBiz#updateReductionUserById(int, int)
	 */
	public boolean updateReductionUserById(int reductionUserId, int currentUserId)
			throws Exception {
		// 没有用户id
		if(reductionUserId==0){
			return false;
		}
		User user = userDao.findById(reductionUserId);
		// 用户存在 且是假删除状态
		if(user!=null && user.getDeleteFlag()==Constants.DELETE_TRUE){
			// 默认禁用
			user.setStatus(1);
			user.setDeleteFlag(Constants.DELETE_FALSE);
			user.setUpdaterId(currentUserId);
			user.setUpdatedTime(DateUtil.getNow());
			this.modify(user);
			return true;
		}
		return false;
	}

	/*
	 * 根据条件查询全部用户数量(分页)
	 * @see net.cedu.biz.admin.UserBiz#findAllUserCountByParams(net.cedu.entity.admin.User, net.cedu.model.page.PageResult)
	 */
	public int findAllUserCountByParams(User user, PageResult<User> pr)
			throws Exception {
		PageParame p = new PageParame();
		String hqlConditionExpression = "";
		List<Object> list = null;
		if(user!=null){
			list = new ArrayList<Object>();
			// 用户名
			if(user.getUserName()!=null&&!user.getUserName().equals("")){
				hqlConditionExpression += " and userName like " + Constants.PLACEHOLDER;
				list.add("%" + user.getUserName() + "%");
			}
			// 姓名
			if(user.getFullName()!=null&&!user.getFullName().equals("")){
				hqlConditionExpression += " and fullName = " + Constants.PLACEHOLDER;
				list.add("%" + user.getFullName() + "%");
			}
			// 学习中心
			if(user.getOrgId()!=-2){
				hqlConditionExpression += " and orgId = " + Constants.PLACEHOLDER;
				list.add(user.getOrgId());
			}
			// 删除状态
			if(user.getDeleteFlag()!=-1){
				hqlConditionExpression += " and deleteFlag = " + Constants.PLACEHOLDER;
				list.add(user.getDeleteFlag());
			}
			
			p.setHqlConditionExpression(hqlConditionExpression);
			p.setValues(list.toArray());
			return userDao.getCounts(p);
		}
		return 0;
	}

	/*
	 * 根据条件查询全部用户集合(分页)
	 * @see net.cedu.biz.admin.UserBiz#findAllUserListByParams(net.cedu.entity.admin.User, net.cedu.model.page.PageResult)
	 */
	public List<User> findAllUserListByParams(User user, PageResult<User> pr)
			throws Exception {
		List<User> userList = null;
		PageParame p = new PageParame(pr);
		String hqlConditionExpression = "";
		List<Object> list = null;
		if(user!=null){
			list = new ArrayList<Object>();
			// 用户名
			if(user.getUserName()!=null&&!user.getUserName().equals("")){
				hqlConditionExpression += " and userName like " + Constants.PLACEHOLDER;
				list.add("%" + user.getUserName() + "%");
			}
			// 姓名
			if(user.getFullName()!=null&&!user.getFullName().equals("")){
				hqlConditionExpression += " and fullName = " + Constants.PLACEHOLDER;
				list.add("%" + user.getFullName() + "%");
			}
			// 学习中心
			if(user.getOrgId()!=-2){
				hqlConditionExpression += " and orgId = " + Constants.PLACEHOLDER;
				list.add(user.getOrgId());
			}
			// 删除状态
			if(user.getDeleteFlag()!=-1){
				hqlConditionExpression += " and deleteFlag = " + Constants.PLACEHOLDER;
				list.add(user.getDeleteFlag());
			}
			
			p.setHqlConditionExpression(hqlConditionExpression);
			p.setValues(list.toArray());
			Long[] userIds = userDao.getIDs(p);
			if (userIds != null && userIds.length != 0){
				userList = new ArrayList<User>();
				User u = null;
				for (Long id : userIds) {
					u = userDao.findById(Integer.parseInt(id.toString()));
					if (u != null) {
						// 部门
						u.setDepartment(departmentBiz.findDepartmentById(u.getDepartmentId()));
						// 岗位
						u.setJob(jobBiz.findJobById(u.getJobId()));
						userList.add(u);
					}
				}
				return userList;
			}
		}
		return null;
	}
	
	/*
	 * 按学习中心ID查找用户（总部管理学习中心用户使用）
	 * 
	 * @see net.cedu.biz.admin.UserBiz#findUserPageListByUserForBranch(net.cedu.entity.admin.User, net.cedu.model.page.PageResult)
	 */
	public List<User> findUserPageListByUserForBranch(User user, PageResult<User> pr)
			throws Exception {
		List<User> users = new ArrayList<User>();
		PageParame p = new PageParame(pr);
		
		List<Object> paramlist=new ArrayList<Object>();
		String hql="";
		if (user != null) {
			
			if(StringUtils.isNotBlank(user.getUserName()))
			{
				hql+="and userName like "+Constants.PLACEHOLDER;
				paramlist.add("%"+user.getUserName()+"%");
			}
			if(StringUtils.isNotBlank(user.getFullName()))
			{
				hql+="and fullName like "+Constants.PLACEHOLDER;
				paramlist.add("%"+user.getFullName()+"%");
			}
			if(-1<user.getDeleteFlag())
			{
				hql+=" and deleteFlag=" + Constants.PLACEHOLDER;
				paramlist.add(UserEnum.DeleteNo.value());
			}
			if(-2<user.getOrgId())
			{
				hql+="and orgId=" + Constants.PLACEHOLDER;
				paramlist.add(user.getOrgId());
				
			}
			if(user.getObjParams().get("pibiOrgId")!=null)
			{
				hql+="and orgId<>" + Constants.PLACEHOLDER;
				paramlist.add(Integer.valueOf(user.getObjParams().get("pibiOrgId").toString()));
			}
			p.setHqlConditionExpression(hql);
			p.setValues(paramlist.toArray());
		}

		Long[] userIds = userDao.getIDs(p);
		if (userIds != null && userIds.length != 0) {
			for (int i = 0; i < userIds.length; i++) {
				User u = userDao.findById(Integer.parseInt(userIds[i]
						.toString()));
				if (u != null) {
					u.setDepartment(departmentBiz.findDepartmentById(u.getDepartmentId()));
					u.setJob(jobBiz.findJobById(u.getJobId()));
					users.add(u);
				}
			}
		}

		return users;

	}
	
	/*
	 * 按学习中心ID查找用户数量（总部管理学习中心用户使用）
	 * 
	 * @see net.cedu.biz.admin.UserBiz#findUserPageCountByUserForBranch(net.cedu.entity.admin.User, net.cedu.model.page.PageResult)
	 */
	public int findUserPageCountByUserForBranch(User user, PageResult<User> pr)
			throws Exception {
		PageParame p = new PageParame(pr);
		List<Object> paramlist=new ArrayList<Object>();
		String hql="";
		if (user != null) {
			
			if(StringUtils.isNotBlank(user.getUserName()))
			{
				hql+="and userName like "+Constants.PLACEHOLDER;
				paramlist.add("%"+user.getUserName()+"%");
			}
			if(StringUtils.isNotBlank(user.getFullName()))
			{
				hql+="and fullName like "+Constants.PLACEHOLDER;
				paramlist.add("%"+user.getFullName()+"%");
			}
			if(-1<user.getDeleteFlag())
			{
				hql+=" and deleteFlag=" + Constants.PLACEHOLDER;
				paramlist.add(UserEnum.DeleteNo.value());
			}
			if(-2<user.getOrgId())
			{
				hql+="and orgId=" + Constants.PLACEHOLDER;
				paramlist.add(user.getOrgId());
				
			}
			if(user.getObjParams().get("pibiOrgId")!=null)
			{
				hql+="and orgId<>" + Constants.PLACEHOLDER;
				paramlist.add(Integer.valueOf(user.getObjParams().get("pibiOrgId").toString()));
			}
			p.setHqlConditionExpression(hql);
			p.setValues(paramlist.toArray());
		}

		return userDao.getCounts(p);
	}


}
