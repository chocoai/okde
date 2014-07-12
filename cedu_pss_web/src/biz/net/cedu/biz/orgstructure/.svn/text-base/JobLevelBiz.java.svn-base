package net.cedu.biz.orgstructure;

import java.util.List;

import net.cedu.entity.orgstructure.JobLevel;
import net.cedu.model.page.PageResult;


public interface JobLevelBiz
{
	/**
	 * 添加子系统
	 * @param SubPrivilege
	 * @throws Exception
	 */
	public boolean addNew(JobLevel jobLevel)throws Exception;
	
	/**
	 * 修改子系统
	 * @param JobLevel
	 * @throws Exception
	 */
	public void modify(JobLevel jobLevel)throws Exception;
	
	/**
	 * 根据ID删除子系统
	 * @param JobLevel
	 * @throws Exception
	 */
	public void deleteConfigById(int id)throws Exception;
	
	/**
	 * 查询子系统(供其他模块调用)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<JobLevel> findJobLevelForModel() throws Exception;
	
	/**
	 * 根据条件查找子系统列表_分页
	 */
	public List<JobLevel> findListByCondition(PageResult<JobLevel> pr,JobLevel jobLevel);
	
	/**
	 * 根据条件查找子系统列表
	 */
	public List<JobLevel> findListByCondition(JobLevel jobLevel);
	
	/**
	 * 根据条件查找子系统数量
	 */
	public int findCountByConditionForHQL(JobLevel jobLevel);
	
	/**
	 * 查询所有子系统(包含总部)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<JobLevel> findAll() throws Exception;
	
	/**
	 * 根据ID查询子系统
	 */
	public JobLevel findJobLevelById(int id) throws Exception;
	
	/**
	 * 
	 * @功能：通过主键IDs获取岗位级别
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-7 下午05:34:20
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @param jobLevelIds
	 * @return
	 * @throws Exception
	 */
	public List<JobLevel> findJobLevelListByJobLevelIds(String jobLevelIds)throws Exception;
}
