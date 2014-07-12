package net.cedu.biz.basesetting;

import java.io.Serializable;
import java.util.List;

import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.crm.Student;
import net.cedu.model.page.PageResult;

/**
 * 招生途径
 * @author HXJ
 *
 */
public interface EnrollmentSourceBiz {

	/**
	 * 增加招生途径
	 * @param enrollmentsource
	 * @return
	 * @throws Exception
	 */
	public boolean addEnrollmentSource(EnrollmentSource enrollmentsource) throws Exception; 
	
	/**
	 * 修改招生途径
	 * @param enrollmentsource
	 * @return
	 */
	public boolean modifyEnrollmentSource(EnrollmentSource enrollmentsource)throws Exception;
	
//	//按主键删除招生途径(物理删除)
//	public EnrollmentSource deleteEnrollmentSourceById(Serializable id);
//	
//	//按主键删除招生途径(逻辑删除)
//	public int deleteEnrollmentSourceByFlag(int id);
	
	/**
	 * 查询所有招生途径
	 */
	public List<EnrollmentSource> findAllEnrollmentSources() throws Exception;
	
	/**
	 * 查询所有招生途径(delete_flag=0)
	 * @return
	 * @throws Exception
	 */
	public List<EnrollmentSource> findAllEnrollmentSourceByDeleteFlag() throws Exception;
	
	/**
	 * 按主键id查询招生途径
	 * @param id
	 * @return
	 */
	public EnrollmentSource findEnrollmentSourceById(Serializable id);
	
	/**
	 * 按是否合作方返款查询delete_flag=0的列表
	 * @param isneedRebates
	 * @return
	 * @throws Exception
	 */
	public List<EnrollmentSource> findAllEnrollmentSourceByIsNeedRebatesAndFlag(int isneedRebates) throws Exception;
	
	/**
	 * 删除(读取配置文件)
	 * @param id
	 * @return
	 */
	public EnrollmentSource deleteConfigEnrollmentSource(int id);
	
	/**
	 * 根据每个招生途径的合作方返款科目ids查询其对应的返款科目中文名称
	 * @return
	 * @throws Exception
	 */
	public List<EnrollmentSource> findSubjectNamesBySubjectIds(List<EnrollmentSource> enrollsourcelist) throws Exception;
	
	/**
	 * 把一组费用科目的字符串转成int数组
	 * source:招生途径id
	 * @return
	 * @throws Exception
	 */
	public Integer[] changeStringIdsIntoIntArray(int sourceid) throws Exception;

	/**
	 * 学生来源统计
	 * @param student 学生信息
	 * @return
	 * @throws Exception
	 */
	public List<EnrollmentSource> findEnrollmentSourceReport(Student student) throws Exception;
	
	/**
	 * 查询所有招生途径(delete_flag=0)去除社招类型
	 * @return
	 * @throws Exception
	 */
	public List<EnrollmentSource> findEnrollmentSourceByDeleteFlagAndId() throws Exception;
	
}
