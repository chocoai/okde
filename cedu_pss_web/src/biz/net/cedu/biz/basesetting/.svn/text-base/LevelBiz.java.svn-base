package net.cedu.biz.basesetting;

import java.io.Serializable;
import java.util.List;

import net.cedu.entity.basesetting.Level;
import net.cedu.entity.enrollment.AcademyLevel;


/**
 * 层次
 * @author HXJ
 */
public interface LevelBiz {

	/**
	 * 增加层次
	 * @param level
	 * @return
	 * @throws Exception
	 */
	public boolean addLevel(Level level) throws Exception; 
	
	/**
	 * 修改层次
	 * @param level
	 * @return
	 */
	public boolean modifyLevel(Level level) throws Exception ;
	
//	//按主键删除(物理删除)
//	public Level deleteLevelById(Serializable id);
//	
//	//按主键删除(逻辑删除)
//	public int deleteLevelByFlag(int id);
	
	/**
	 * 查询所有层次列
	 */
	public List<Level> findAllLevels() throws Exception;
	
	/**
	 * 查询所有层次列(delete_flag=0)
	 * @return
	 * @throws Exception
	 */
	public List<Level> findAllLevelsByDeleteFlag() throws Exception;
	
	/**
	 * 按主键id查询level
	 * @param id
	 * @return
	 */
	public Level findLevelById(Serializable id);

	/**
	 * 根据层次(院校批次)的属性levelId 查询对应的Level实体
	 * @param academylevel
	 * @return
	 */
	public List<AcademyLevel> addLevelforAcademyLevel(List<AcademyLevel> academylevel);
	
	/**
	 * 在基础数据中查询某院校批次下未设置过的层次
	 * @param objects
	 * @return
	 * @throws Exception
	 */
	public List<Level> findOtherLevels(Object...objects) throws Exception;
	
	/**
	 * 	删除(读取配置文件)
	 * @param id
	 * @return
	 */
	public Level deleteConfigLevel(int id);
	
	/**
	 * 通过层次名称查询层次
	 * @param name
	 * @return
	 */
	public Level findLevelByName(String name);
}
