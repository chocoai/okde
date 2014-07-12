package net.cedu.biz.admin.privilege;

import java.util.List;

import net.cedu.entity.admin.privilege.Modular;
import net.cedu.model.page.PageResult;

/**
 * 模块业务层接口
 * @author Jack
 *
 */
public interface ModularBiz {
	
	/**
	 * 添加模块
	 * @param SubPrivilege
	 * @throws Exception
	 */
	public boolean addNew(Modular modular)throws Exception;
	
	/**
	 * 修改模块
	 * @param Modular
	 * @throws Exception
	 */
	public void modify(Modular modular)throws Exception;
	
	/**
	 * 根据ID删除模块
	 * @param Modular
	 * @throws Exception
	 */
	public void deleteConfigById(int id)throws Exception;
	
	/**
	 * 查询模块(供其他模块调用)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Modular> findModularForModel() throws Exception;
	
	/**
	 * 根据条件查找模块列表_分页
	 */
	public List<Modular> findListByCondition(PageResult<Modular> pr,Modular modular);
	
	/**
	 * 根据条件查找模块列表
	 */
	public List<Modular> findListByCondition(Modular modular);
	
	/**
	 * 根据条件查找模块数量
	 */
	public int findCountByConditionForHQL(Modular modular);
	
	/**
	 * 查询所有模块(包含总部)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Modular> findAll() throws Exception;
	
	/**
	 * 根据ID查询模块
	 */
	public Modular findModularById(int id) throws Exception;
	
	/**
	 * 根据子系统ID查询模块列表
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Modular> findModularBySubSystemId(int id)throws Exception ;
}
