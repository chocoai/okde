package net.cedu.biz.crm.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.crm.StudentEnrollmentSourceChangeLogBiz;
import net.cedu.common.Constants;
import net.cedu.common.il8n.ResourcesTool;
import net.cedu.dao.crm.StudentEnrollmentSourceChangeLogDao;
import net.cedu.entity.admin.User;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.crm.StudentEnrollmentSourceChangeLog;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class StudentEnrollmentSourceChangeLogBizImpl implements
		StudentEnrollmentSourceChangeLogBiz {

	@Autowired
	private StudentEnrollmentSourceChangeLogDao studentEnrollmentSourceChangeLogDao;
	@Autowired
	private UserBiz userBiz;
	@Autowired
	private EnrollmentSourceBiz enrollmentsourceBiz;
	
	public void addStudentEnrollmentSourceChangeLog(
			StudentEnrollmentSourceChangeLog studentEnrollmentSourceChangeLog)
			throws Exception {
		studentEnrollmentSourceChangeLogDao.save(studentEnrollmentSourceChangeLog);
	}

	public int findStudentEnrollmentSourceChangeLogsPageCount(
			StudentEnrollmentSourceChangeLog studentEnrollmentSourceChangeLog,
			PageResult<StudentEnrollmentSourceChangeLog> pr) throws Exception {
		PageParame p = new PageParame(pr);
		if (studentEnrollmentSourceChangeLog != null) {
			if(studentEnrollmentSourceChangeLog.getStudentId()!=0){
				p.setHqlConditionExpression(" and studentId = "+Constants.PLACEHOLDER);
				p.setValues(new Object[]{studentEnrollmentSourceChangeLog.getStudentId()});
			}else{
				return 0;
			}
			
		}
		return studentEnrollmentSourceChangeLogDao.getCounts(p);
	}

	public List<StudentEnrollmentSourceChangeLog> findStudentEnrollmentSourceChangeLogsPageList(
			StudentEnrollmentSourceChangeLog studentEnrollmentSourceChangeLog,
			PageResult<StudentEnrollmentSourceChangeLog> pr) throws Exception {
		List<StudentEnrollmentSourceChangeLog> studentEnrollmentSourceChangeLogList=null;
		PageParame p = new PageParame(pr);
		if (studentEnrollmentSourceChangeLog != null) {
			if(studentEnrollmentSourceChangeLog.getStudentId()!=0){
				p.setHqlConditionExpression(" and studentId = "+Constants.PLACEHOLDER);
				p.setValues(new Object[]{studentEnrollmentSourceChangeLog.getStudentId()});
			}else{
				return new ArrayList<StudentEnrollmentSourceChangeLog>();
			}
			
		}
		Long[] idArray=studentEnrollmentSourceChangeLogDao.getIDs(p);
		
		if(idArray!=null&&idArray.length!=0){
			studentEnrollmentSourceChangeLogList=new ArrayList<StudentEnrollmentSourceChangeLog>();
			StudentEnrollmentSourceChangeLog s=null;
			EnrollmentSource newEnrollmentsource = null;
			EnrollmentSource oldEnrollmentsource = null;
			User user=null;
			for (Long id : idArray) {
				s=studentEnrollmentSourceChangeLogDao.findById(id);
				if(s!=null){
					user=userBiz.findUserById(s.getChangeId());
					if(user!=null){
						s.getParams().put("changeName",user.getFullName());
					}else{
						s.getParams().put("changeName","");
					}
					//变更前招生途径
					oldEnrollmentsource = enrollmentsourceBiz.findEnrollmentSourceById(s.getOldEnrollmentSourceId());
					//变更后招生途径
					newEnrollmentsource = enrollmentsourceBiz.findEnrollmentSourceById(s.getNewEnrollmentSourceId());
					if(oldEnrollmentsource!=null&&newEnrollmentsource!=null){
						
						s.getParams().put("changeContent", ResourcesTool.getText("student_enrollment_source_change_log", "change.content", new Object[]{oldEnrollmentsource.getName(),newEnrollmentsource.getName()}));
					}else{
						s.getParams().put("changeContent", "");
					}
					
					studentEnrollmentSourceChangeLogList.add(s);
				}
			}
		}
		
		return studentEnrollmentSourceChangeLogList;
	}

	public void addStudentEnrollmentSourceChangeLog(int studentId,
			int changeId, int oldStudentEnrollmentSource,
			int newStudentEnrollmentSource) throws Exception {
		StudentEnrollmentSourceChangeLog studentEnrollmentSourceChangeLog=new StudentEnrollmentSourceChangeLog();
		studentEnrollmentSourceChangeLog.setChangeDate(new Date());
		studentEnrollmentSourceChangeLog.setChangeId(changeId);
		studentEnrollmentSourceChangeLog.setNewEnrollmentSourceId(newStudentEnrollmentSource);
		studentEnrollmentSourceChangeLog.setOldEnrollmentSourceId(oldStudentEnrollmentSource);
		studentEnrollmentSourceChangeLog.setStudentId(studentId);
		addStudentEnrollmentSourceChangeLog(studentEnrollmentSourceChangeLog);
	}

}
