/**
 * 文件名：ExportYITuiSongStudentAction.java
 * 包名：net.cedu.action.crm
 * 工程：cedu_pss_web
 * 功能： TODO /请自行添加
 *
 * 作者：yangdongdong    
 * 日期：2012-2-2 上午09:43:25
 *
*/
package net.cedu.action.crm.export;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.common.Constants;
import net.cedu.common.ConstantsSexMap;
import net.cedu.common.ConstantsStudentDataSourceMap;
import net.cedu.common.ConstantsStudentStatusMap;
import net.cedu.common.date.DateUtil;
import net.cedu.entity.crm.Student;
import net.cedu.model.crm.CCStudentExportTemplate;
import net.cedu.model.crm.CCStudentExportTemplate1;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;

public class ExportYiTuiSongStudentAction1 extends BaseAction{

	@Autowired
	private StudentBiz studentBiz;
	
	private Student student;
	@Override
	public String execute() throws Exception {
		//导出的excel数据集合
		List<CCStudentExportTemplate1> ccStudentExportTemplateList=new ArrayList<CCStudentExportTemplate1>();
		
		//不分页
		PageResult<Student> pr=new PageResult<Student>();
		pr.setPageSize(3000);
		pr.setCurrentPageIndex(1);
		
		student.setStartStatusId(Constants.STU_CALL_STATUS_YU_BAO_MING);
		student.setEndStatusId(Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI);
		student.setStudentDataSource(Constants.STU_SOURCE_CC);
		student.setCallStatus(-1);
		student.setMonitorStatus(-1);
		student.setTuitionStatus(-1);
		
		student.setGender(Integer.parseInt(request.getParameter("student.gender")));
		
		if(request.getParameter("student.startDate")!=null&&!request.getParameter("student.startDate").equals("")){
			student.setStartDate(DateUtil.getDate(request.getParameter("student.startDate")));
		}
		if(request.getParameter("student.endDate")!=null&&!request.getParameter("student.endDate").equals("")){
			student.setEndDate(DateUtil.getDate(request.getParameter("student.endDate")));
		}
		
		List<Student> studentList=studentBiz.findStudentsPageList(student, pr);
		if(studentList!=null){
			for (Student student : studentList) {
				CCStudentExportTemplate1 ccStudentExportTemplate=new CCStudentExportTemplate1();
				ccStudentExportTemplate.setBranchName(student.getBranchName());
				ccStudentExportTemplate.setCengCi(student.getLevelName());
				ccStudentExportTemplate.setFollowUpUserName(student.getFollowUpName());
				ccStudentExportTemplate.setLatestFollowUpDate(student.getLatestFollowUpDate()!=null?DateUtil.getDate(student.getLatestFollowUpDate(), "yyyy-MM-dd"):"");
				ccStudentExportTemplate.setMobile(student.getMobile());
				ccStudentExportTemplate.setName(student.getName());
				ccStudentExportTemplate.setPhone(student.getPhone());
				ccStudentExportTemplate.setPiCi(student.getAcademyenrollbatchName());
				ccStudentExportTemplate.setSexName(ConstantsSexMap.getCode(student.getGender()));
				ccStudentExportTemplate.setStatusName(ConstantsStudentStatusMap.getCode(student.getStatus()));
				ccStudentExportTemplate.setPushDate(student.getPushDate()!=null?DateUtil.getDate(student.getPushDate(), "yyyy-MM-dd"):"");
				ccStudentExportTemplate.setCreateDate(student.getCreateDate()!=null?DateUtil.getDate(student.getCreateDate(), "yyyy-MM-dd"):"");
				ccStudentExportTemplate.setPushName(student.getPushName());
				ccStudentExportTemplate.setYuanXiao(student.getSchoolName());
				ccStudentExportTemplate.setZhuanYe(student.getMajorName());
				ccStudentExportTemplate.setIdCode(student.getCertNo());
				ccStudentExportTemplate.setShuJuLaiYuan(ConstantsStudentDataSourceMap.getCode(student.getStudentDataSource()));
				ccStudentExportTemplate.setZhaoshengTuJin(student.getEnrollmentSourceName1());
				ccStudentExportTemplate.setShichangTuJin(student.getEnrollmentWayName());
				ccStudentExportTemplate.setReturnVisitContent("");
				//跟进次数
				ccStudentExportTemplate.setFollowCount(student.getFollowCount()+"");
				
				ccStudentExportTemplateList.add(ccStudentExportTemplate);
			}
		}
		
		return super.exportExcel("已推送的学生数据", ccStudentExportTemplateList);
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Student getStudent() {
		return student;
	}
	
}
