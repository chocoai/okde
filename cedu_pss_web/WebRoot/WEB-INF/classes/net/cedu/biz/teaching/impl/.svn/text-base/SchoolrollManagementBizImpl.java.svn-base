package net.cedu.biz.teaching.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.biz.teaching.SchoolrollManagementBiz;
import net.cedu.common.Constants;
import net.cedu.dao.crm.StudentDao;
import net.cedu.dao.teaching.SchoolrollManagementDao;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.Major;
import net.cedu.entity.teaching.SchoolrollManagement;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 学籍管理业务层
 * @author YY
 *
 */
@Service
public class SchoolrollManagementBizImpl implements SchoolrollManagementBiz {
	
	
	@Autowired
	private SchoolrollManagementDao SchoolrollManagementDao; //学籍管理业务层
	@Autowired
	private StudentDao studentDao;  //学生数据层
	@Autowired
	private AcademyBiz academyBiz;  //院校业务层
	@Autowired
	private MajorBiz majorBiz;  //专业业务层
	@Autowired
	private LevelBiz levelBiz;  //层次业务层
	@Autowired
	private AcademyEnrollBatchBiz academyenrollbatchBiz; //批次业务层
	
	
	/**
	 * 学籍管理分页（集合）
	 * @param student
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<Student> findScoolrollManagementPageListByCondition(Student student,PageResult<Student> pr) throws Exception
	{
		List<Student>  stuList=null;
		PageParame p = new PageParame(pr);
		List<Object> list = null;
		String hqlConditionExpression = "";
		if (student != null ) {
			list = new ArrayList<Object>();
			//学号
				if(student.getNumber()!=null  && !student.getNumber().equals(""))
				{
					hqlConditionExpression += "and number like"
						+ Constants.PLACEHOLDER;
				list.add("%" + student.getNumber() + "%");
				}
			// 姓名
			if (student.getName() != null && !student.getName().equals("")) {
				hqlConditionExpression += "and name like"
						+ Constants.PLACEHOLDER;
				list.add("%" + student.getName() + "%");
			}
			// 院校
			if (student.getAcademyId() != 0) {
				hqlConditionExpression += "and academyId="
						+ Constants.PLACEHOLDER;
				list.add(student.getAcademyId());
			}
			// 批次
			if (student.getEnrollmentBatchId() != 0) {
				hqlConditionExpression += " and  enrollmentBatchId= "
						+ Constants.PLACEHOLDER;
				list.add(student.getEnrollmentBatchId());
			}
			// 层次
			if (student.getLevelId() != 0) {
				hqlConditionExpression += " and  levelId= "
						+ Constants.PLACEHOLDER;
				list.add(student.getLevelId());
			}
			// 专业
			if (student.getMajorId() != 0) {
				hqlConditionExpression += " and  majorId= "
						+ Constants.PLACEHOLDER;
				list.add(student.getMajorId());
			}
 
			p.setHqlConditionExpression(hqlConditionExpression);
			p.setValues(list.toArray());
		}
		Long[] sidsLongs = studentDao.getIDs(p);
		if(sidsLongs!=null && sidsLongs.length!=0)
		{ 
			stuList=new ArrayList<Student>();
			Student s=null;
			for (int i = 0; i < sidsLongs.length; i++) {
				s=studentDao.findById(Integer.parseInt(sidsLongs[i].toString()));
				if(s!=null)
				{
					 
					// 院校
					Academy academy = academyBiz.findAcademyById(s
							.getAcademyId());

					if (academy != null) {
						s.setSchoolName(academy.getName());
					}
					//招生批次
					AcademyEnrollBatch academyenrollbatch = academyenrollbatchBiz.findAcademyEnrollBatchById(s.getEnrollmentBatchId());
					if (academyenrollbatch != null) 
					{
						s.setEnrollmentBatchName(academyenrollbatch
								.getEnrollmentName());
					}
					// 层次
					Level level = levelBiz.findLevelById(s.getLevelId());
					if (level != null) {
						s.setLevelName(level.getName());
					}
					// 专业
					Major major = majorBiz.findMajorById(s.getMajorId());
					if (major != null) {
						s.setMajorName(major.getName());
					}
					 if(s!=null)
				      {
				    	  stuList.add(s);
				      }
				}
			}
		}
		
		return stuList;
	}
	
	/**
	 * 学籍管理分页（数量）
	 * @param student
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int findScoolrollManagementPageCountByCondition(Student student,PageResult<Student> pr) throws Exception
	{	
		PageParame p = new PageParame();
		List<Object> list = null;
		String hqlConditionExpression = "";
		if (student != null ) {
			list = new ArrayList<Object>();
			//学号
				if(student.getNumber()!=null  && !student.getNumber().equals(""))
				{
					hqlConditionExpression += "and number like"
						+ Constants.PLACEHOLDER;
				list.add("%" + student.getNumber() + "%");
				}
			// 姓名
			if (student.getName() != null && !student.getName().equals("")) {
				hqlConditionExpression += "and name like"
						+ Constants.PLACEHOLDER;
				list.add("%" + student.getName() + "%");
			}
			// 院校
			if (student.getAcademyId() != 0) {
				hqlConditionExpression += "and academyId="
						+ Constants.PLACEHOLDER;
				list.add(student.getAcademyId());
			}
			// 批次
			if (student.getEnrollmentBatchId() != 0) {
				hqlConditionExpression += " and  enrollmentBatchId= "
						+ Constants.PLACEHOLDER;
				list.add(student.getEnrollmentBatchId());
			}
			// 层次
			if (student.getLevelId() != 0) {
				hqlConditionExpression += " and  levelId= "
						+ Constants.PLACEHOLDER;
				list.add(student.getLevelId());
			}
			// 专业
			if (student.getMajorId() != 0) {
				hqlConditionExpression += " and  majorId= "
						+ Constants.PLACEHOLDER;
				list.add(student.getMajorId());
			}
			p.setHqlConditionExpression(hqlConditionExpression);
			p.setValues(list.toArray());
			
		}
		
		Long[] sidsLongs = studentDao.getIDs(p);
		if (sidsLongs == null) {
			return 0;
		}
		
		return sidsLongs.length;
	}
	
	/**
	 * 增加学籍
	 */
	public void addSchoolrollManagement(
			SchoolrollManagement schoolrollManageMent) throws Exception {
		if(schoolrollManageMent!=null)
		SchoolrollManagementDao.save(schoolrollManageMent);
		
	}
	
}
