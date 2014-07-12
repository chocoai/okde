package net.cedu.biz.admin.privilege;

import java.util.List;

import net.cedu.entity.admin.privilege.SubSystem;
import net.cedu.model.page.PageResult;

/**
 * 子系统业务层接口
 * @author Jack
 *
 */
public interface SubSystemBiz {
	
	/**
	 * 添加子系统
	 * @param SubPrivilege
	 * @throws Exception
	 */
	public boolean addNew(SubSystem subSystem)throws Exception;
	
	/**
	 * 修改子系统
	 * @param SubSystem
	 * @throws Exception
	 */
	public void modify(SubSystem subSystem)throws Exception;
	
	/**
	 * 根据ID删除子系统
	 * @param SubSystem
	 * @throws Exception
	 */
	public void deleteConfigById(int id)throws Exception;
	
	/**
	 * 查询子系统(供其他模块调用)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<SubSystem> findSubSystemForModel() throws Exception;
	
	/**
	 * 根据条件查找子系统列表_分页
	 */
	public List<SubSystem> findListByCondition(PageResult<SubSystem> pr,SubSystem subSystem);
	
	/**
	 * 根据条件查找子系统列表
	 */
	public List<SubSystem> findListByCondition(SubSystem subSystem);
	
	/**
	 * 根据条件查找子系统数量
	 */
	public int findCountByConditionForHQL(SubSystem subSystem);
	
	/**
	 * 查询所有子系统(包含总部)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<SubSystem> findAll() throws Exception;
	
	/**
	 * 根据ID查询子系统
	 */
	public SubSystem findSubSystemById(int id) throws Exception;
}
