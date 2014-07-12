package net.cedu.biz.persontool;

import java.util.List;

import net.cedu.entity.persontool.DuibiStudentGongfu;

/**
 * 对比文件
 * 
 * @author 谭必庆
 * 
 */
public interface DuibiStudentGongfuBiz {
	/**
	 * 新建对比文件以及跟进纪录
	 */
	public int addDuibiFile(DuibiStudentGongfu duibiStudentGongfu) throws Exception;

	/**
	 * 删除对比文件
	 */
	public String deleteById(int id) throws Exception;
	
	/**
	 * 删除对比结果
	 */
	public void deleteByAll()throws Exception;
	
	/**
	 * 修改对比文件
	 */
	public String updateById(DuibiStudentGongfu duibiStudentGongfu) throws Exception;

	/**
	 * 查看对比文件
	 */
	public DuibiStudentGongfu findStudentById(int id) throws Exception;
	
	/**
	 * 按照中心预申请条件查询对比文件
	 */
	public List<DuibiStudentGongfu> findStudentByPrePurchaseCenter() throws Exception;
}
