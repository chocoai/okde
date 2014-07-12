package net.cedu.biz.enrollment.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.basesetting.MonitorResultsBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.EnrollMonitorBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.crm.Student;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 报名监控
 * @author HXJ
 *
 */
@Service
public class EnrollMonitorBizImpl implements EnrollMonitorBiz{
	
	@Autowired
	private StudentBiz studentbiz;
	@Autowired
	private MonitorResultsBiz monitorResultsBiz; 
	
	/*查询学生信息(分页)
	 * (non-Javadoc)
	 * @see net.cedu.biz.enrollment.EnrollMonitorBiz#findAllStudent(net.cedu.entity.crm.Student, net.cedu.model.page.PageResult)
	 */
	public List<Student> findAllStudent(Student student,PageResult<Student> pr)
			throws Exception {
		List<Student> stulist = new ArrayList<Student>(); 
		String monitorname="";
		//查询学生信息列表(分页)
		//student.setMonitorStatus(0);
		//student.setTuitionStatus(-1);
		stulist = studentbiz.findStudentsPageList(student,pr);
		if(stulist!=null&&stulist.size()>0){
			for(int i=0,len=stulist.size();i<len;i++){
				//查询每个学生最新一条监控记录
				monitorname = monitorResultsBiz.findMoniterResultsNameByid(stulist
						.get(i).getLastMonitorResult());
				if (monitorname!=null&&!("").equals(monitorname)) {
					stulist.get(i).setLastMonitorResultName(monitorname);
				}
			}
		}
		return stulist;
	}

	/*查询学生总数量
	 * (non-Javadoc)
	 * @see net.cedu.biz.enrollment.EnrollMonitorBiz#findAllStudentCount(net.cedu.entity.crm.Student, net.cedu.model.page.PageResult)
	 */
	public int findAllStudentCount(Student student,PageResult<Student> pr) throws Exception {
		//student.setMonitorStatus(0);
		//student.setTuitionStatus(-1);
		return studentbiz.findStudentsPageCount(student, pr);
	}


}
