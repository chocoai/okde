package net.cedu.biz.enrollment;

import java.io.Serializable;
import java.util.List;

import net.cedu.entity.basesetting.BaseMajor;
import net.cedu.entity.enrollment.Major;

/**
 * 专业(院校)
 * @author HXJ
 */
public interface MajorBiz {

	//增加专业
	public boolean addMajor(Major major) throws Exception; 
	
	//修改专业
	public boolean modifyMajor(Major major) throws Exception;
	
//	//按主键删除(物理删除)
//	public Major deleteMajorById(Serializable id);
//	
//	//按主键删除(逻辑删除)
//	public int deleteMajorByFlag(int id);
	
	//查询所有专业列
	public List<Major> findAllMajors(int academyId) throws Exception;
	
	//查询所有专业列(delete_flag=0)
	public List<Major> findAllMajorsByDeleteFlag(int academyId) throws Exception;
	
	//按主键id查询major
	public Major findMajorById(Serializable id);
	
	//在基础数据中查询某院校批次下未设置过的专业
	public List<Major> findOtherMajors(int academyId,Object...objects) throws Exception;
	
	//循环查询major所属basemajor的名称
	public List<Major> findAllBelongMajorNames(List<Major> majorlist) throws Exception;
	
	/**
	 * 删除(读取配置文件)
	 * @param id
	 * @return
	 */
	public Major deleteConfigMajor(int id);
	
	/**
	 * 
	 * @功能：通过专业名称查询专业
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2011-11-28 下午11:56:44
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @param academyId 院校ID
	 * @param majorName 专业名称
	 * @param levelName 层次名称
	 * @return
	 */
	public Major findMajorByName(int academyId,String majorName,String levelName);
	
	/**
	 * 查询基础专业下的院校专业
	 * @param baseMajorId  基础专业Id
	 * @return
	 * @throws Exception
	 */
	public List<Major> findMajorListByBaseMajorId(int baseMajorId) throws Exception;
}
