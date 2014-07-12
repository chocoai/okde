package net.cedu.biz.crm;

import java.util.List;

import net.cedu.entity.crm.StudentEnrollmentSourceChangeLog;
import net.cedu.model.page.PageResult;

public interface StudentEnrollmentSourceChangeLogBiz {

	/**
	 * 创建招生途径变更纪录
	 * @param StudentEnrollmentSourceChangeLog
	 * @throws Exception
	 */
	public void addStudentEnrollmentSourceChangeLog(StudentEnrollmentSourceChangeLog studentEnrollmentSourceChangeLog)throws Exception;
	/**
	 * 创建招生途径变更纪录
	 * @param studentId
	 * @param changeId
	 * @param oldStudentEnrollmentSource
	 * @param newStudentEnrollmentSource
	 * @throws Exception
	 */
	public void addStudentEnrollmentSourceChangeLog(int studentId,int changeId,int oldStudentEnrollmentSource,int newStudentEnrollmentSource)throws Exception;

	/**
	 * 招生途径变更纪录分页总数
	 * @param studentEnrollmentSourceChangeLog
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int findStudentEnrollmentSourceChangeLogsPageCount(StudentEnrollmentSourceChangeLog studentEnrollmentSourceChangeLog,PageResult<StudentEnrollmentSourceChangeLog> pr) throws Exception;

	/**
	 * 招生途径变更纪录分页显示的集合
	 * @param studentEnrollmentSourceChangeLog
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<StudentEnrollmentSourceChangeLog> findStudentEnrollmentSourceChangeLogsPageList(StudentEnrollmentSourceChangeLog studentEnrollmentSourceChangeLog,PageResult<StudentEnrollmentSourceChangeLog> pr) throws Exception;
}
