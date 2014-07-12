package net.cedu.biz.crm.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.crm.StudentImportRecordBiz;
import net.cedu.common.Constants;
import net.cedu.dao.crm.StudentImportRecordDao;
import net.cedu.entity.crm.StudentImportRecord;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentImportRecordBizImpl implements StudentImportRecordBiz {

	@Autowired
	private StudentImportRecordDao studentImportRecordDao;

	/*
	 * 新建学生信息导入
	 * 
	 * @see
	 * net.cedu.biz.crm.StudentImportRecordBiz#addStudentImportRecord(net.cedu
	 * .entity.crm.StudentImportRecord)
	 */
	public Object addStudentImportRecord(StudentImportRecord studentImportRecord)
			throws Exception {
		return studentImportRecordDao.save(studentImportRecord);
	}

	/*
	 * 删除学生信息导入
	 * 
	 * @see
	 * net.cedu.biz.crm.StudentImportRecordBiz#deleteStudentImportRecordById
	 * (int)
	 */
	public void deleteStudentImportRecordById(int id) throws Exception {
		studentImportRecordDao.deleteById(id);

	}

	/*
	 * 更新学生信息导入
	 * 
	 * @see
	 * net.cedu.biz.crm.StudentImportRecordBiz#updateStudentImportRecord(net
	 * .cedu.entity.crm.StudentImportRecord)
	 */
	public void updateStudentImportRecord(
			StudentImportRecord studentImportRecord) throws Exception {
		studentImportRecordDao.update(studentImportRecord);

	}

	/*
	 * 查看学生信息导入
	 * 
	 * @see
	 * net.cedu.biz.crm.StudentImportRecordBiz#findStudentImportRecordById(int)
	 */
	public StudentImportRecord findStudentImportRecordById(int id)
			throws Exception {
		return studentImportRecordDao.findById(id);
	}

	/*
	 * 查询学生信息导入总条数
	 * 
	 * @see
	 * net.cedu.biz.crm.StudentImportRecordBiz#findStudentImportRecordsPageCount
	 * (net.cedu.entity.crm.StudentImportRecord, net.cedu.model.page.PageResult)
	 */
	public int findStudentImportRecordsPageCount(
			StudentImportRecord studentImportRecord,
			PageResult<StudentImportRecord> pr) throws Exception {
		PageParame p = new PageParame();

		List<Object> objs = new ArrayList<Object>();
		String hqlConditionExpression = "";
		if (studentImportRecord != null) {
			//导入的数据类型
			if (studentImportRecord.getType() != 0) {
				hqlConditionExpression += " and type=" + Constants.PLACEHOLDER;
				objs.add(studentImportRecord.getType());
				
				switch (studentImportRecord.getType()) {
				case StudentImportRecord.IMPORT_TYPE_MANAGER://总部

					break;
				
				case StudentImportRecord.IMPORT_TYPE_MANAGER_CC://总部(呼叫中心)

					break;

				case StudentImportRecord.IMPORT_TYPE_Branch: //学习中心
						if(studentImportRecord.getOrgId()!=0){//学习中心ID
							hqlConditionExpression += " and org_id=" + Constants.PLACEHOLDER;
							objs.add(studentImportRecord.getOrgId());
						}else{
							return 0;
						}
					break;
				default:
					return 0;//没有匹配类型则返回0条纪录
				}
			} else {
				return 0;//没有匹配类型则返回0条纪录
			}

		} else {
			return 0;
		}
		p.setHqlConditionExpression(hqlConditionExpression);
		p.setValues(objs.toArray());

		return studentImportRecordDao.getCounts(p);
	}

	/*
	 * 查询学生信息导入集合
	 * 
	 * @see
	 * net.cedu.biz.crm.StudentImportRecordBiz#findStudentImportRecordsPageList
	 * (net.cedu.entity.crm.StudentImportRecord, net.cedu.model.page.PageResult)
	 */
	public List<StudentImportRecord> findStudentImportRecordsPageList(
			StudentImportRecord studentImportRecord,
			PageResult<StudentImportRecord> pr) throws Exception {
		List<StudentImportRecord> studentImportRecords = null;
		// Ids集合
		PageParame p = new PageParame(pr);

		List<Object> objs = new ArrayList<Object>();
		String hqlConditionExpression = "";
		if (studentImportRecord != null) {
			//导入的数据类型
			if (studentImportRecord.getType() != 0) {
				hqlConditionExpression += " and type=" + Constants.PLACEHOLDER;
				objs.add(studentImportRecord.getType());
				
				switch (studentImportRecord.getType()) {
				case StudentImportRecord.IMPORT_TYPE_MANAGER://总部

					break;
				case StudentImportRecord.IMPORT_TYPE_MANAGER_CC://总部(呼叫中心)

					break;

				case StudentImportRecord.IMPORT_TYPE_Branch: //学习中心
						if(studentImportRecord.getOrgId()!=0){//学习中心ID
							hqlConditionExpression += " and org_id=" + Constants.PLACEHOLDER;
							objs.add(studentImportRecord.getOrgId());
						}else{
							return new ArrayList<StudentImportRecord>();
						}
					break;
				default:
					return new ArrayList<StudentImportRecord>();//没有匹配类型则返回0条纪录
				}
			} else {
				return new ArrayList<StudentImportRecord>();//没有匹配类型则返回0条纪录
			}

		} else {
			return new ArrayList<StudentImportRecord>();
		}
		p.setHqlConditionExpression(hqlConditionExpression);
		p.setValues(objs.toArray());
		//获取记录ID
		Long[] studentImportRecordLongs = studentImportRecordDao.getIDs(p);

		if (studentImportRecordLongs != null&& studentImportRecordLongs.length != 0) {
			studentImportRecords = new ArrayList<StudentImportRecord>();
			StudentImportRecord e = null;
			for (Long id : studentImportRecordLongs) {
				if (null != (e = studentImportRecordDao.findById(Integer.parseInt(id.toString())))) {
					e.setErrorLog(null);
					studentImportRecords.add(e);
				}
			}
		}
		return studentImportRecords;
	}

}
