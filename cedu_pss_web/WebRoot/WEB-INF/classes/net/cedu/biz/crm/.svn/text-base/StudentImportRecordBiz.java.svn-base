package net.cedu.biz.crm;

import java.util.List;

import net.cedu.entity.crm.StudentImportRecord;
import net.cedu.model.page.PageResult;

/**
 * 学生信息导入数据业务接口
 * 
 * @author yangdongdong
 * 
 */
public interface StudentImportRecordBiz {
	/**
	 * 新建学生信息导入
	 * 
	 * @param studentImportRecord
	 *            学生信息导入实体
	 * @return
	 * @throws Exception
	 */
	public Object addStudentImportRecord(StudentImportRecord studentImportRecord)
			throws Exception;

	/**
	 * 删除学生信息导入
	 * 
	 * @param id
	 *            学生信息导入标识ID
	 * @return
	 * @throws Exception
	 */
	public void deleteStudentImportRecordById(int id) throws Exception;

	/**
	 * 更新学生信息导入
	 * 
	 * @param studentImportRecord
	 *            学生信息导入实体
	 * @return
	 * @throws Exception
	 */
	public void updateStudentImportRecord(
			StudentImportRecord studentImportRecord) throws Exception;

	/**
	 * 查看学生信息导入
	 * 
	 * @param id
	 *            学生信息导入ID
	 * @return
	 * @throws Exception
	 */
	public StudentImportRecord findStudentImportRecordById(int id)
			throws Exception;

	/**
	 * 查询学生信息导入总条数
	 * 
	 * @param studentImportRecord
	 *            查询条件
	 * @param pr
	 *            分页实体
	 * @return
	 * @throws Exception
	 */
	public int findStudentImportRecordsPageCount(
			StudentImportRecord studentImportRecord,
			PageResult<StudentImportRecord> pr) throws Exception;

	/**
	 * 查询学生信息导入集合
	 * 
	 * @param studentImportRecord
	 *            查询条件
	 * @param pr
	 *            分页实体
	 * @return
	 * @throws Exception
	 */
	public List<StudentImportRecord> findStudentImportRecordsPageList(
			StudentImportRecord studentImportRecord,
			PageResult<StudentImportRecord> pr) throws Exception;

}
