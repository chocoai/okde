package net.cedu.biz.code;

import net.cedu.entity.code.BuildCode;

/**
 * 编码生成器业务层接口
 * @author Jack
 *
 */
public interface BuildCodeBiz {
	
	/**
	 * 添加编码表
	 * 
	 * @param UserGroup
	 * @throws Exception
	 */
	public void createNew(BuildCode buildCode) throws Exception ;

	/**
	 * 根据ID删除编码表
	 * 
	 * @param UserGroup
	 * @throws Exception
	 */
	public void deleteConfigById(int id) throws Exception ;

	/**
	 * 修改编码表
	 * 
	 * @param UserGroup
	 * @throws Exception
	 */
	public void modify(BuildCode buildCode) throws Exception ;
	
	/**
	 * 获取编码_供调用
	 * @param tbName
	 * @param codeKey
	 * @return
	 * @throws Exception
	 */
	public String getCodes(String tbName,String codeKey)throws Exception;
	
	/**
	 * 获取编码_供调用
	 * @param tbName
	 * @param codeKey
	 * @return
	 * @throws Exception
	 */
	public BuildCode getCodes2(String tbName)throws Exception;
	
	/**
	 * 获取短编码_供调用
	 * @param tbName
	 * @param codeKey
	 * @return
	 * @throws Exception
	 */
	public String getShortCodes(String tbName,String codeKey)throws Exception;
}
