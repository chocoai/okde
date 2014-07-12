package net.cedu.biz.orgstructure;


import java.util.List;

import net.cedu.entity.orgstructure.Job;
import net.cedu.entity.orgstructure.JobLevel;
import net.cedu.model.page.PageResult;

/**
 * 岗位业务逻辑层接口
 * @author lina
 *
 */
public interface JobBiz {
	/**
	 * 添加子系统
	 * @param SubPrivilege
	 * @throws Exception
	 */
	public boolean addNew(Job job)throws Exception;
	
	/**
	 * 修改子系统
	 * @param Job
	 * @throws Exception
	 */
	public void modify(Job job)throws Exception;
	
	/**
	 * 根据ID删除子系统
	 * @param Job
	 * @throws Exception
	 */
	public void deleteConfigById(int id)throws Exception;
	
	/**
	 * 查询子系统(供其他模块调用)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Job> findJobForModel() throws Exception;
	
	/**
	 * 根据条件查找子系统列表_分页
	 */
	public List<Job> findListByCondition(PageResult<Job> pr,Job job);
	
	/**
	 * 根据条件查找子系统列表
	 */
	public List<Job> findListByCondition(Job job);
	
	/**
	 * 根据条件查找子系统数量
	 */
	public int findCountByConditionForHQL(Job job);
	
	/**
	 * 查询所有子系统(包含总部)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Job> findAll() throws Exception;
	
	/**
	 * 根据ID查询子系统
	 */
	public Job findJobById(int id) throws Exception;
	/**
	 * 
	 * @功能：通过部门ID获取岗位IDs
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-7 下午05:28:02
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @param departmentId
	 * @return
	 */
	public String findJobLevelIdsByDepartmentId(int departmentId);
	
	/**
	 * 
	 * @功能：通过主键ID获取岗位级别Ids
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-8 下午07:21:00
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @param jobIds
	 * @return
	 * @throws Exception
	 */
	public String  findJobLevelIdsByJobIds(String jobIds)throws Exception;
}
