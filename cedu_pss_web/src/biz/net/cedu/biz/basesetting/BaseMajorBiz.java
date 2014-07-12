package net.cedu.biz.basesetting;

import java.io.Serializable;
import java.util.List;

import net.cedu.entity.basesetting.BaseMajor;

/**
 * 专业(基础设置)
 * @author Hxj
 *
 */
public interface BaseMajorBiz {
	
	/**
	 * 增加
	 * @param baseMajor
	 * @return
	 * @throws Exception
	 */
	public boolean addBaseMajor(BaseMajor baseMajor) throws Exception;
	
//	//物理删除
//	public BaseMajor deleteBaseMajorById(Serializable id);
//	
//	//逻辑删除
//	public int deleteBaseMajorByflag(int id);
	
	/**
	 * 修改
	 */
	public boolean modifyBaseMajor(BaseMajor baseMajor) throws Exception;
	
	/**
	 * 按主键ID查询
	 * @param id
	 * @return
	 */
	public BaseMajor findBaseMajorbyId(Serializable id);
	
	/**
	 * 查询全部数据
	 * @return
	 * @throws Exception
	 */
	public List<BaseMajor> findAllBaseMajors() throws Exception;
	
	/**
	 * 查询全部deleteFlag=0的数据
	 * @return
	 * @throws Exception
	 */
	public List<BaseMajor> findBaseMajorByFlag() throws Exception;
	
	/**
	 * 删除(读取配置文件)
	 * @param id
	 * @return
	 */
	public BaseMajor deleteConfigBaseMajor(int id);
	
}
