package net.cedu.biz.orgstructure;


import net.cedu.entity.orgstructure.Jurisdiction;

/**
 * 管辖范围业务逻辑层接口
 * @author Jack
 *
 */
public interface JurisdictionBiz {
	
	/**
	 * 添加管辖范围
	 * @param SubPrivilege
	 * @throws Exception
	 */
	public boolean addNew(Jurisdiction jurisdiction)throws Exception;
	
	/**
	 * 修改子系统
	 * @param Job
	 * @throws Exception
	 */
	public void modify(Jurisdiction jurisdiction)throws Exception;

	
	/**
	 * 根据ID查询管辖范围
	 */
	public Jurisdiction findById(int userId) throws Exception;
	/**
	 * 
	 * @功能：通过用户ID查询授权部门ID集合
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-5 上午11:24:52
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @param userId
	 * @return 不存在返回0
	 * @throws Exception
	 */
	public String findByUserId(int userId)throws Exception;
}
