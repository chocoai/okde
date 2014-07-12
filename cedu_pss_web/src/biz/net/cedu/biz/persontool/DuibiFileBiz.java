package net.cedu.biz.persontool;

import java.util.List;

import net.cedu.entity.persontool.DuibiFile;

/**
 * 对比文件
 * 
 * @author 谭必庆
 * 
 */
public interface DuibiFileBiz {
	/**
	 * 新建对比文件以及跟进纪录
	 */
	public int addDuibiFile(DuibiFile duibiFile) throws Exception;

	/**
	 * 删除对比文件
	 */
	public String deleteById(int id) throws Exception;
	
	/**
	 * 修改对比文件
	 */
	public String updateById(DuibiFile duibiFile) throws Exception;

	/**
	 * 查看对比文件
	 */
	public DuibiFile findStudentById() throws Exception;
	
	/**
	 * 按照中心预申请条件查询对比文件
	 */
	public List<DuibiFile> findStudentByPrePurchaseCenter() throws Exception;
}
