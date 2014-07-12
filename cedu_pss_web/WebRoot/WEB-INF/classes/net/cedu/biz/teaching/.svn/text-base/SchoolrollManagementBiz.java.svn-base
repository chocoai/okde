package net.cedu.biz.teaching;

import java.util.List;

import net.cedu.entity.crm.Student;
import net.cedu.entity.teaching.SchoolrollManagement;
import net.cedu.model.page.PageResult;

/**
 * 学籍管理业务层
 * @author YY
 *
 */
public interface SchoolrollManagementBiz   {

	/**
	 * 学籍管理分页（集合）
	 * @param student
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	List<Student> findScoolrollManagementPageListByCondition(Student student,PageResult<Student> pr) throws Exception;

	/**
	 * 学籍管理分页（数量）
	 * @param student
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	int findScoolrollManagementPageCountByCondition(Student student,PageResult<Student> pr) throws Exception;
	/**
	 * 增加学籍
	 * @param schoolrollManageMent
	 * @throws Exception
	 */
	void addSchoolrollManagement(SchoolrollManagement schoolrollManageMent)throws Exception;
}
