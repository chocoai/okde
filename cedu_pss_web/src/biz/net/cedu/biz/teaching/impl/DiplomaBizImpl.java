package net.cedu.biz.teaching.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.biz.teaching.DiplomaBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.dao.crm.StudentDao;
import net.cedu.dao.teaching.DiplomaDao;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.Major;
import net.cedu.entity.teaching.Diploma;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 毕业证书业务逻辑
 * @author wangmingjie
 *
 */
@Service
public class DiplomaBizImpl implements DiplomaBiz {
	@Autowired
	private DiplomaDao diplomaDao;
	@Autowired
	private StudentDao studentDao;//学生数据访问成接口
	@Autowired
	private StudentBiz studentBiz;//学生业务层接口
	
	@Autowired
	private AcademyBiz academyBiz;//院校业务层接口
	
	@Autowired
	private AcademyEnrollBatchBiz academyenrollbatchBiz;//招生批次业务层接口
	
	@Autowired
	private LevelBiz levelBiz;//层次业务层接口
	
	@Autowired
	private MajorBiz majorBiz;//专业业务层接口
	/**
	 * 检测查询学生的条件是否为空
	 * @param student
	 * @return
	 */
	private boolean checkStudentInfo(Student student) {
		String hqlConditionExpression = "";
		if (student != null) {
			//学号
			if(student.getNumber()!=null  && !student.getNumber().equals(""))
			{
				hqlConditionExpression += "and number like"
					+ Constants.PLACEHOLDER;
			}
			// 姓名
			if (student.getName() != null && !student.getName().equals("")) {
				hqlConditionExpression += "and name like"
						+ Constants.PLACEHOLDER;
			}

			// 院校
			if (student.getAcademyId() != 0) {
				hqlConditionExpression += "and academyId="
						+ Constants.PLACEHOLDER;
			}
			// 批次
			if (student.getEnrollmentBatchId() != 0) {
				hqlConditionExpression += " and  enrollmentBatchId= "
						+ Constants.PLACEHOLDER;
			}
			// 层次
			if (student.getLevelId() != 0) {
				hqlConditionExpression += " and  levelId= "
						+ Constants.PLACEHOLDER;
			}
			// 专业
			if (student.getMajorId() != 0) {
				hqlConditionExpression += " and  majorId= "
						+ Constants.PLACEHOLDER;
			}
		

		}
		if (hqlConditionExpression != null
				&& !hqlConditionExpression.equals("")) {
			return true;
		}
		return false;
	}
	
	/**
	 * 查询毕业证书列表集合
	 * @param student
	 * @param diploma
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<Student> findDiplomaListByConditions(Student student,PageResult<Student> pr) throws Exception
	{
		List<Student>  stuList=null;
		PageParame p = new PageParame(pr);
		List list = null;
		String hqlConditionExpression = "";
		if (student != null ) {
			list = new ArrayList();
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

			if(student.getStatus()>0)
			{//学生状态
				hqlConditionExpression += " and status ="
					+ Constants.PLACEHOLDER;
			   list.add(student.getStatus());
			}
			
			if(student.getGraduateTime()!=null)
			{
				hqlConditionExpression += " and graduateTime ="
					+ Constants.PLACEHOLDER;
			   list.add(student.getStatus());
			}
			
			if (student.getStartDate() ==null && student.getEndDate()!=null) {
				hqlConditionExpression += " and  graduateTime <= "
						+ Constants.PLACEHOLDER;
				list.add(DateUtil.getDate(student.getEndDate(), "yyyy-MM-dd")+" 23:59:59");
			}
			if (student.getStartDate() !=null &&student.getEndDate()==null) {
				hqlConditionExpression += " and  graduateTime >="
						+ Constants.PLACEHOLDER;
				list.add(DateUtil.getDate(student.getStartDate(), "yyyy-MM-dd")+" 00:00:00");
			}
			if (student.getStartDate() !=null && student.getEndDate()!=null) {
				hqlConditionExpression += " and  graduateTime>= "
						+ Constants.PLACEHOLDER + " and graduateTime<="
						+ Constants.PLACEHOLDER;
				list.add(DateUtil.getDate(student.getStartDate(), "yyyy-MM-dd")+" 00:00:00");
				list.add(DateUtil.getDate(student.getEndDate(), "yyyy-MM-dd")+" 23:59:59");
			}
			hqlConditionExpression += " and status >="
				+ Constants.PLACEHOLDER+" and status<="+ Constants.PLACEHOLDER;
			list.add(Constants.STU_CALL_STATUS_YI_LU_QU);
			list.add(Constants.STU_CALL_STATUS_YI_BI_YI);
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
					Diploma diploma=findDiplomaByStudentId(s.getId());//毕业状态
					if(diploma!=null)
					{
						s.setDiplomaStatus(diploma.getStatus());//?
					}
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
	
	
	
	public   Diploma findDiplomaByStudentId(int  studentId) throws Exception
	{
		List<Diploma> diplist=diplomaDao.getByProperty("and studentId="+Constants.PLACEHOLDER,new Object[]{studentId});
		if(diplist!=null && diplist.size()>0)
		{
			return diplist.get(0);
		}
		return null;
	}

	
     /**
      * 查询毕业证书数量
      * @param student
      * @param diploma
      * @param pr
      * @return
      * @throws Exception
      */
	public int findDiplomaCountByConditions(Student student,PageResult<Student> pr) throws Exception
	{
		PageParame p = new PageParame();
		List list = null;
		String hqlConditionExpression = "";
		if (student != null ) {
			list = new ArrayList();
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
			
			
			if(student.getStatus()>0)
			{//学生状态
				hqlConditionExpression += " and status ="
					+ Constants.PLACEHOLDER;
			   list.add(student.getStatus());
			}
			if(student.getGraduateTime()!=null)
			{
				hqlConditionExpression += " and graduateTime ="
					+ Constants.PLACEHOLDER;
			   list.add(student.getStatus());
			}
			
			if (student.getStartDate() ==null && student.getEndDate()!=null) {
				hqlConditionExpression += " and  graduateTime <= "
						+ Constants.PLACEHOLDER;
				list.add(DateUtil.getDate(student.getEndDate(), "yyyy-MM-dd")+" 23:59:59");
			}
			if (student.getStartDate() !=null &&student.getEndDate()==null) {
				hqlConditionExpression += " and  graduateTime >="
						+ Constants.PLACEHOLDER;
				list.add(DateUtil.getDate(student.getStartDate(), "yyyy-MM-dd")+" 00:00:00");
			}
			if (student.getStartDate() !=null && student.getEndDate()!=null) {
				hqlConditionExpression += " and  graduateTime>= "
						+ Constants.PLACEHOLDER + " and graduateTime<="
						+ Constants.PLACEHOLDER;
				list.add(DateUtil.getDate(student.getStartDate(), "yyyy-MM-dd")+" 00:00:00");
				list.add(DateUtil.getDate(student.getEndDate(), "yyyy-MM-dd")+" 23:59:59");
			}
			
			hqlConditionExpression += " and status >="
				+ Constants.PLACEHOLDER+" and status<="+ Constants.PLACEHOLDER;
			list.add(Constants.STU_CALL_STATUS_YI_LU_QU);
			list.add(Constants.STU_CALL_STATUS_YI_BI_YI);
			p.setHqlConditionExpression(hqlConditionExpression);
			p.setValues(list.toArray());
			
		}
		
		Long[] sidsLongs = studentDao.getIDs(p);
		if (sidsLongs == null) {
			return 0;
		}
		
		return sidsLongs.length;
		
	}
	/*
	 * 查询所有毕业证书
	 * 
	 * 
	 */
	public List<Diploma> findAllDiplomas() throws Exception {
		return diplomaDao.findAll();
	}
	
	/*
	 * 更新毕业证书实体
	 * 
	 * 
	 */
	public void updateDiploma(Diploma diploma) throws Exception {
		if(diploma!=null)
		{
			diplomaDao.update(diploma)	;
		}
		
	}
	/**
	 * 添加毕业证书
	 */
	
	public void addDiploma(Diploma diploma) throws Exception
	{
		if(diploma!=null)
		{
			diplomaDao.save(diploma);
		}
	}
	/**
	 * 查看学生
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Student findStudentById(int id) throws Exception
	{
		if(id!=0)
		{
			Student student = studentDao.findById(id);
			return student;
		}
		return null;
		
	}
}
