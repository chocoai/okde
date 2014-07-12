package net.cedu.biz.enrollment.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.DiscountApplicationBiz;
import net.cedu.biz.enrollment.StudentDiscountDetailBiz;
import net.cedu.biz.enrollment.StudentDiscountPolicyBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.il8n.ResourcesTool;
import net.cedu.dao.enrollment.StudentDiscountPolicyDao;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.StudentDiscountPolicy;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 学生优惠政策标准
 * 
 * @author lixaojun
 * 
 */
@Service
public class StudentDiscountPolicyBizImpl implements StudentDiscountPolicyBiz
{
	
	@Autowired
	private StudentDiscountPolicyDao studentDiscountPolicyDao;
	
	@Autowired
	private StudentDiscountDetailBiz studentDiscountDetailBiz;
	
	@Autowired
	private AcademyBiz academyBiz;//院校业务接口
	
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;//费用科目业务接口
	
	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;//合作方类型业务接口
	
	@Autowired
	private StudentBiz studentBiz;//学生业务接口
	
	@Autowired
	private DiscountApplicationBiz daBiz;//学生优惠卷_业务层接口
	
	/*
	 * 根据Id查找学生优惠政策标准
	 * 
	 * @see net.cedu.biz.enrollment.StudentDiscountPolicyBiz#findStudentDiscountPolicyById(int)
	 */
	public StudentDiscountPolicy findStudentDiscountPolicyById(int id)throws Exception 
	{
		return this.studentDiscountPolicyDao.findById(id);
	}
	
	/*
	 * 根据Id查找学生优惠政策标准详细信息
	 * 
	 * @see net.cedu.biz.enrollment.StudentDiscountPolicyBiz#findStudentDiscountPolicyDetailsById(int)
	 */
	public StudentDiscountPolicy findStudentDiscountPolicyDetailsById(int id)throws Exception
	{
		StudentDiscountPolicy obj=this.studentDiscountPolicyDao.findById(id);
		if(obj!=null)
		{
			//院校名称
			if(obj.getAcademyId()!=0)
			{
				if(obj.getAcademyId()==-1)
				{
					obj.setAcademyname("");
				}
				else
				{
					obj.setAcademyname(this.academyBiz.findAcademyById(obj.getAcademyId()).getName());
				}
			}
			
			//费用科目
			if(obj.getFeeSubjectId()!=0)
			{
				obj.setFeesubjectname(this.feeSubjectBiz.findFeeSubjectById(obj.getFeeSubjectId()).getName());
			}
			
			
			//优惠标准
			String standardes="";
			standardes+=ResourcesTool.getText("enrollment","activitydate")+DateUtil.getDateStr(obj.getActivityBeginDate(), "yyyy-MM-dd")+ResourcesTool.getText("enrollment","tag")+DateUtil.getDateStr(obj.getActivityEndDate(), "yyyy-MM-dd")+"<br/>";
			standardes+=ResourcesTool.getText("enrollment","usedate")+DateUtil.getDateStr(obj.getUseBeginDate(), "yyyy-MM-dd")+ResourcesTool.getText("enrollment","tag")+DateUtil.getDateStr(obj.getUseEndDate(), "yyyy-MM-dd")+"<br/>";
			if(obj.getDiscountWayId()==Constants.MONEY_FORM_AMOUNT)
			{
				standardes+=ResourcesTool.getText("enrollment","money")+obj.getMoney()+ResourcesTool.getText("enrollment","moneyunit")+"<br/>";
			}
			else
			{
				standardes+=ResourcesTool.getText("enrollment","proportion")+obj.getMoney()+ResourcesTool.getText("enrollment","percent")+"<br/>";
			}
			if(obj.getMutable()==Constants.MONEY_FORM_MUTABLE)
			{
				standardes+=ResourcesTool.getText("enrollment","mutable");
			}
			else
			{
				if(obj.getDiscountWayId()==Constants.MONEY_FORM_AMOUNT)
				{
					standardes+=ResourcesTool.getText("enrollment","gradient")+obj.getDelta()+ResourcesTool.getText("enrollment","moneyunit");
				}
				else
				{
					standardes+=ResourcesTool.getText("enrollment","gradient")+obj.getDelta()+ResourcesTool.getText("enrollment","percent");
				}
			}
			obj.setDiscountstandard(standardes);
		}
		return obj;
	}

	/*
	 * 添加学生优惠政策标准
	 * 
	 * @see net.cedu.biz.enrollment.StudentDiscountPolicyBiz#addStudentDiscountPolicy(net.cedu.entity.enrollment.StudentDiscountPolicy)
	 */
	public boolean addStudentDiscountPolicy(StudentDiscountPolicy studentDiscountPolicy) throws Exception 
	{
		if (studentDiscountPolicy != null)
		{
			Object object = studentDiscountPolicyDao.save(studentDiscountPolicy);
			if (object != null) 
			{
				return true;
			}
		}
		
		return false;
	}
	
	/*
	 * 删除学生优惠政策标准(读取配置文件)
	 * 
	 * @see net.cedu.biz.enrollment.StudentDiscountPolicyBiz#deleteStudentDiscountPolicyById(int)
	 */
	public boolean deleteConfigStudentDiscountPolicyById(int id) throws Exception
	{
		if (id != 0) {
			Object object = studentDiscountPolicyDao.deleteConfig(id);
			if (object != null) {
				return true;
			}
		}
		
		return false;
	}

//	/*
//	 * 删除学生优惠政策标准(物理删除)
//	 * 
//	 * @see net.cedu.biz.enrollment.StudentDiscountPolicyBiz#deleteStudentDiscountPolicyById(int)
//	 */
//	public boolean deleteStudentDiscountPolicyById(int id) throws Exception
//	{
//		if (id != 0) {
//			Object object = studentDiscountPolicyDao.deleteById(id);
//			if (object != null) {
//				return true;
//			}
//		}
//		
//		return false;
//	}
//
//	/*
//	 * 删除学生优惠政策标准(逻辑删除)
//	 * 
//	 * @see net.cedu.biz.enrollment.StudentDiscountPolicyBiz#deleteStudentDiscountPolicyFlag(int)
//	 */
//	public boolean deleteStudentDiscountPolicyFlag(int id) throws Exception 
//	{
//		if (id != 0)
//		{
//			int num = studentDiscountPolicyDao.deleteByFlag(id);
//			if (num>0) 
//			{
//				return true;
//			}
//		}
//		
//		return false;
//	}

	/*
	 * 修改学生优惠政策标准
	 * 
	 * @see net.cedu.biz.enrollment.StudentDiscountPolicyBiz#modifyStudentDiscountPolicy(net.cedu.entity.enrollment.StudentDiscountPolicy)
	 */
	public boolean modifyStudentDiscountPolicy(StudentDiscountPolicy studentDiscountPolicy) throws Exception 
	{
		if (studentDiscountPolicy != null) 
		{
			Object object = studentDiscountPolicyDao.update(studentDiscountPolicy);
			if (object != null) 
			{
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 获取学生优惠政策标准数量
	 * 
	 * @see net.cedu.biz.enrollment.StudentDiscountPolicyBiz#findStudentDiscountPolicyCountByDetails(net.cedu.entity.enrollment.StudentDiscountPolicy, net.cedu.model.page.PageResult)
	 */
	public int findStudentDiscountPolicyCountByDetails(StudentDiscountPolicy studentDiscountPolicy,PageResult<StudentDiscountPolicy> pr) throws Exception 
	{
		PageParame p = new PageParame();
		String params="";
		String canshu="";
		if(studentDiscountPolicy.getAcademyId()!=0)
		{
			params+=" and academyId="+ Constants.PLACEHOLDER;
			canshu+=studentDiscountPolicy.getAcademyId()+",";
		}
		if(studentDiscountPolicy.getFeeSubjectId()!=0)
		{
			params+=" and feeSubjectId="+ Constants.PLACEHOLDER;
			canshu+=studentDiscountPolicy.getFeeSubjectId()+",";
		}
		
		if(studentDiscountPolicy.getFeePaymentId()!=0)
		{
			params+=" and feePaymentId="+ Constants.PLACEHOLDER;
			canshu+=studentDiscountPolicy.getFeePaymentId()+",";
		}
		
		params+=" and deleteFlag="+ Constants.PLACEHOLDER;
		canshu+=0;
		if(!params.equals(""))
		{
			p.setHqlConditionExpression(params);
			p.setValues(canshu.split(","));
		}
		return studentDiscountPolicyDao.getCounts(p);
	}
	
	/*
	 * 获取学生优惠政策标准列表
	 * 
	 * @see net.cedu.biz.enrollment.StudentDiscountPolicyBiz#findStudentDiscountPolicyListByDetails(net.cedu.entity.enrollment.StudentDiscountPolicy, net.cedu.model.page.PageResult)
	 */
	public List<StudentDiscountPolicy> findStudentDiscountPolicyListByDetails(StudentDiscountPolicy studentDiscountPolicy,PageResult<StudentDiscountPolicy> pr) throws Exception 
	{
		List<StudentDiscountPolicy> policys = null;
		// Ids集合
		PageParame p = new PageParame(pr);
//		p.setCurrentPageIndex(pr.getCurrentPageIndex());
//		p.setPageSize(pr.getPageSize());
//		p.setOrder(pr.getOrder());
//		p.setSort(pr.getSort());
//		// 分页或者不分页
//		p.setPage(pr.isPage());
		String params="";
		String canshu="";
		if(studentDiscountPolicy.getAcademyId()!=0)
		{
			params+=" and academyId="+ Constants.PLACEHOLDER;
			canshu+=studentDiscountPolicy.getAcademyId()+",";
		}
		if(studentDiscountPolicy.getFeeSubjectId()!=0)
		{
			params+=" and feeSubjectId="+ Constants.PLACEHOLDER;
			canshu+=studentDiscountPolicy.getFeeSubjectId()+",";
		}
		
		if(studentDiscountPolicy.getFeePaymentId()!=0)
		{
			params+=" and feePaymentId="+ Constants.PLACEHOLDER;
			canshu+=studentDiscountPolicy.getFeePaymentId()+",";
		}
		params+=" and deleteFlag="+ Constants.PLACEHOLDER;
		canshu+=0;
		if(!params.equals(""))
		{
			p.setHqlConditionExpression(params);
			p.setValues(canshu.split(","));
		}
		
		
		Long[] feeIds = studentDiscountPolicyDao.getIDs(p);
		if (feeIds != null && feeIds.length != 0) {
			policys = new ArrayList<StudentDiscountPolicy>();
			for (int i = 0; i < feeIds.length; i++) {
				// 内存获取
				StudentDiscountPolicy policyObj = studentDiscountPolicyDao.findById(Integer.valueOf(feeIds[i].toString()));
				if (policyObj != null) {
					StudentDiscountPolicy obj = policyObj;
					//院校名称
					if(obj.getAcademyId()!=0)
					{
						if(obj.getAcademyId()==-1)
						{
							obj.setAcademyname("");
						}
						else
						{
							obj.setAcademyname(this.academyBiz.findAcademyById(obj.getAcademyId()).getName());
						}
					}
					
					//费用科目
					if(obj.getFeeSubjectId()!=0)
					{
						obj.setFeesubjectname(this.feeSubjectBiz.findFeeSubjectById(obj.getFeeSubjectId()).getName());
					}
					
					
					//优惠标准
					String standardes="";
					standardes+=ResourcesTool.getText("enrollment","activitydate")+DateUtil.getDateStr(obj.getActivityBeginDate(), "yyyy-MM-dd")+ResourcesTool.getText("enrollment","tag")+DateUtil.getDateStr(obj.getActivityEndDate(), "yyyy-MM-dd")+"<br/>";
					standardes+=ResourcesTool.getText("enrollment","usedate")+DateUtil.getDateStr(obj.getUseBeginDate(), "yyyy-MM-dd")+ResourcesTool.getText("enrollment","tag")+DateUtil.getDateStr(obj.getUseEndDate(), "yyyy-MM-dd")+"<br/>";
					if(obj.getDiscountWayId()==Constants.MONEY_FORM_AMOUNT)
					{
						standardes+=ResourcesTool.getText("enrollment","money")+obj.getMoney()+ResourcesTool.getText("enrollment","moneyunit")+"<br/>";
					}
					else
					{
						standardes+=ResourcesTool.getText("enrollment","proportion")+obj.getMoney()+ResourcesTool.getText("enrollment","percent")+"<br/>";
					}
					if(obj.getMutable()==Constants.MONEY_FORM_MUTABLE)
					{
						standardes+=ResourcesTool.getText("enrollment","mutable");
					}
					else
					{
						if(obj.getDiscountWayId()==Constants.MONEY_FORM_AMOUNT)
						{
							standardes+=ResourcesTool.getText("enrollment","gradient")+obj.getDelta()+ResourcesTool.getText("enrollment","moneyunit");
						}
						else
						{
							standardes+=ResourcesTool.getText("enrollment","gradient")+obj.getDelta()+ResourcesTool.getText("enrollment","percent");
						}
					}
					obj.setDiscountstandard(standardes);
					//优惠卷数量
					obj.setIndexcount(this.daBiz.findDaCountForHasUseByDpId(obj.getId(),Constants.POLICY_USING_STATUS_TRUE));
					
					policys.add(obj);
				}
			}
		}

		return policys;

	}
	
	/*
	 * 通过学生Id和是否需要申请  获取该学生优惠政策标准集合
	 * 
	 * @see net.cedu.biz.enrollment.StudentDiscountPolicyBiz#findStudentDiscountPolicyListByStudentId(int, int)
	 */
	public List<StudentDiscountPolicy> findStudentDiscountPolicyListByStudentId(int studentId,int isApplicationNeeded) throws Exception
	{
		List<StudentDiscountPolicy> discountList = new ArrayList<StudentDiscountPolicy>();
		//报名前优惠标准
		List<StudentDiscountPolicy> discountListBefore=new ArrayList<StudentDiscountPolicy>();
		//报名后优惠标准
		List<StudentDiscountPolicy> discountListAfter=new ArrayList<StudentDiscountPolicy>();
		Student student=this.studentBiz.findStudentById(studentId);
		if(student!=null && student.getEnrollmentBatchId()!=0)
		{
			discountListBefore=this.studentDiscountDetailBiz.findStudentDiscountPolicyListByStudent(student);
			if(student.getBatchId()!=0)
			{
				student.setEnrollmentBatchId(student.getBatchId());//没有学籍批次就默认是招生批次(参考政策批次匹配图)
			}			
			discountListAfter=this.studentDiscountDetailBiz.findStudentDiscountPolicyListByStudent(student);
		}
		//报名前的所有优惠标准
		if(discountListBefore!=null && discountListBefore.size()>0)
		{
			for(StudentDiscountPolicy discountPolicy:discountListBefore)
			{
				//报名前的费用科目类型是招生阶段
				if(this.feeSubjectBiz.findFeeSubjectById(discountPolicy.getFeeSubjectId())!=null && this.feeSubjectBiz.findFeeSubjectById(discountPolicy.getFeeSubjectId()).getBatchType()==Constants.BATCH_TYPE_ENROLL)
				{
					//判断是不是需要申请还是需要全部的优惠标准
					if(isApplicationNeeded != Constants.POLICY_APPLICATION_NEEDED_ALL)
					{	
//						if(discountPolicy.getIsApplicationNeeded()==isApplicationNeeded)//筛选需要申请和不需要申请的政策（全部的不需要筛选）
//						{
//							//只有获取要申请的优惠标准才要判断时间
//							if(isApplicationNeeded==Constants.POLICY_APPLICATION_NEEDED_TRUE)
//							{
//								//申请时的时间必须在活动时间之内才能申请
//								if(DateUtil.compareDate(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss").toString(),discountPolicy.getActivityBeginDate().toString()) && DateUtil.compareDate(discountPolicy.getActivityEndDate().toString(),DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss").toString()))
//								{	
//									discountPolicy.setFeesubjectname(this.feeSubjectBiz.findFeeSubjectById(discountPolicy.getFeeSubjectId()).getName());
//									discountList.add(discountPolicy);
//								}
//							}
//							else   //不需要申请的政策
//							{
//								discountPolicy.setFeesubjectname(this.feeSubjectBiz.findFeeSubjectById(discountPolicy.getFeeSubjectId()).getName());
//								discountList.add(discountPolicy);
//							}
//						}
						//12.30重构优惠政策
						//需要申请的政策
						if(isApplicationNeeded==Constants.POLICY_APPLICATION_NEEDED_TRUE)
						{
							if(discountPolicy.getIsApplicationNeeded()==Constants.STUDENT_DISCOUNT_AUDIT_CEDU || discountPolicy.getIsApplicationNeeded()==Constants.STUDENT_DISCOUNT_AUDIT_BRANCH)
							{
								//申请时的时间必须在活动时间之内才能申请
								if(DateUtil.compareDate(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss").toString(),discountPolicy.getActivityBeginDate().toString()) && DateUtil.compareDate(discountPolicy.getActivityEndDate().toString(),DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss").toString()))
								{	
									discountPolicy.setFeesubjectname(this.feeSubjectBiz.findFeeSubjectById(discountPolicy.getFeeSubjectId()).getName());
									discountList.add(discountPolicy);
								}
							}
						}
						else if(isApplicationNeeded==Constants.POLICY_APPLICATION_NEEDED_FALSE)//不需要申请的政策
						{
							if(discountPolicy.getIsApplicationNeeded()==Constants.STUDENT_DISCOUNT_AUDIT_FALSE)
							{
								discountPolicy.setFeesubjectname(this.feeSubjectBiz.findFeeSubjectById(discountPolicy.getFeeSubjectId()).getName());
								discountList.add(discountPolicy);
							}
						}
						else if(isApplicationNeeded==Constants.STUDENT_DISCOUNT_CUSTOM_BRANCH)//中心自定义政策
						{
							if(discountPolicy.getIsApplicationNeeded()==Constants.STUDENT_DISCOUNT_CUSTOM_BRANCH)
							{
								discountPolicy.setFeesubjectname(this.feeSubjectBiz.findFeeSubjectById(discountPolicy.getFeeSubjectId()).getName());
								discountList.add(discountPolicy);
							}
						}
						
					}
					else		//所有的优惠标准
					{
						discountPolicy.setFeesubjectname(this.feeSubjectBiz.findFeeSubjectById(discountPolicy.getFeeSubjectId()).getName());
						discountList.add(discountPolicy);
					}
				}
			}
		}
		//报名前的费用科目类型是非招生阶段
		if(discountListAfter!=null && discountListAfter.size()>0)
		{
			for(StudentDiscountPolicy discountPolicy:discountListAfter)
			{
				if(this.feeSubjectBiz.findFeeSubjectById(discountPolicy.getFeeSubjectId())!=null && this.feeSubjectBiz.findFeeSubjectById(discountPolicy.getFeeSubjectId()).getBatchType()==Constants.BATCH_TYPE_NON_ENROLL)
				{
					//判断是不是需要申请还是需要全部的优惠标准
					if(isApplicationNeeded != Constants.POLICY_APPLICATION_NEEDED_ALL)
					{	
//						if(discountPolicy.getIsApplicationNeeded()==isApplicationNeeded)//筛选需要申请和不需要申请的政策（全部的不需要筛选）
//						{
//							//只有获取要申请的优惠标准才要判断时间
//							if(isApplicationNeeded==Constants.POLICY_APPLICATION_NEEDED_TRUE)
//							{
//								//申请时的时间必须在活动时间之内才能申请
//								if(DateUtil.compareDate(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss").toString(),discountPolicy.getActivityBeginDate().toString()) && DateUtil.compareDate(discountPolicy.getActivityEndDate().toString(),DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss").toString()))
//								{	
//									discountPolicy.setFeesubjectname(this.feeSubjectBiz.findFeeSubjectById(discountPolicy.getFeeSubjectId()).getName());
//									discountList.add(discountPolicy);
//								}
//							}
//							else   //不需要申请的政策
//							{
//								discountPolicy.setFeesubjectname(this.feeSubjectBiz.findFeeSubjectById(discountPolicy.getFeeSubjectId()).getName());
//								discountList.add(discountPolicy);
//							}
//						}
						//12.30重构优惠政策
						//需要申请的政策
						if(isApplicationNeeded==Constants.POLICY_APPLICATION_NEEDED_TRUE)
						{
							if(discountPolicy.getIsApplicationNeeded()==Constants.STUDENT_DISCOUNT_AUDIT_CEDU || discountPolicy.getIsApplicationNeeded()==Constants.STUDENT_DISCOUNT_AUDIT_BRANCH)
							{
								//申请时的时间必须在活动时间之内才能申请
								if(DateUtil.compareDate(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss").toString(),discountPolicy.getActivityBeginDate().toString()) && DateUtil.compareDate(discountPolicy.getActivityEndDate().toString(),DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss").toString()))
								{	
									discountPolicy.setFeesubjectname(this.feeSubjectBiz.findFeeSubjectById(discountPolicy.getFeeSubjectId()).getName());
									discountList.add(discountPolicy);
								}
							}
						}
						else if(isApplicationNeeded==Constants.POLICY_APPLICATION_NEEDED_FALSE)//不需要申请的政策
						{
							if(discountPolicy.getIsApplicationNeeded()==Constants.STUDENT_DISCOUNT_AUDIT_FALSE)
							{
								discountPolicy.setFeesubjectname(this.feeSubjectBiz.findFeeSubjectById(discountPolicy.getFeeSubjectId()).getName());
								discountList.add(discountPolicy);
							}
						}
						else if(isApplicationNeeded==Constants.STUDENT_DISCOUNT_CUSTOM_BRANCH)//中心自定义政策
						{
							if(discountPolicy.getIsApplicationNeeded()==Constants.STUDENT_DISCOUNT_CUSTOM_BRANCH)
							{
								discountPolicy.setFeesubjectname(this.feeSubjectBiz.findFeeSubjectById(discountPolicy.getFeeSubjectId()).getName());
								discountList.add(discountPolicy);
							}
						}
					}
					else		//所有的优惠标准
					{
						discountPolicy.setFeesubjectname(this.feeSubjectBiz.findFeeSubjectById(discountPolicy.getFeeSubjectId()).getName());
						discountList.add(discountPolicy);
					}
				}
			}
		}
		
		if(discountList.size()>0)
		{
			return discountList;
		}
		else
		{
			return null;
		}
	}
	
	/*
	 * 通过学生Id和是否需要申请  获取只有中心跟院校其他条件无关的优惠政策标准集合
	 * @see net.cedu.biz.enrollment.StudentDiscountPolicyBiz#findStudentDiscountPolicyListByStudentIdAndBranch(int, int)
	 */
	public List<StudentDiscountPolicy> findStudentDiscountPolicyListByStudentIdAndBranch(int studentId,int isApplicationNeeded) throws Exception
	{
		List<StudentDiscountPolicy> discountList = new ArrayList<StudentDiscountPolicy>();
		//优惠标准
		List<StudentDiscountPolicy> discountListBefore=new ArrayList<StudentDiscountPolicy>();
		
		Student student=this.studentBiz.findStudentById(studentId);
		if(student!=null && student.getBranchId()!=0)
		{
			Student stud=new Student();
			stud.setAcademyId(-1);//无院校
			stud.setBranchId(student.getBranchId());//只有中心
			discountListBefore=this.studentDiscountDetailBiz.findStudentDiscountPolicyListByStudent(stud);
			//所有优惠标准
			if(discountListBefore!=null && discountListBefore.size()>0)
			{
				for(StudentDiscountPolicy discountPolicy:discountListBefore)
				{
					//判断是不是需要申请还是需要全部的优惠标准
					if(isApplicationNeeded != Constants.POLICY_APPLICATION_NEEDED_ALL)
					{	
//						if(discountPolicy.getIsApplicationNeeded()==isApplicationNeeded)//筛选需要申请和不需要申请的政策（全部的不需要筛选）
//						{
//							//只有获取要申请的优惠标准才要判断时间
//							if(isApplicationNeeded==Constants.POLICY_APPLICATION_NEEDED_TRUE)
//							{
//								//申请时的时间必须在活动时间之内才能申请
//								if(DateUtil.compareDate(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss").toString(),discountPolicy.getActivityBeginDate().toString()) && DateUtil.compareDate(discountPolicy.getActivityEndDate().toString(),DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss").toString()))
//								{	
//									discountPolicy.setFeesubjectname(this.feeSubjectBiz.findFeeSubjectById(discountPolicy.getFeeSubjectId()).getName());
//									discountList.add(discountPolicy);
//								}
//							}
//							else   //不需要申请的政策
//							{
//								discountPolicy.setFeesubjectname(this.feeSubjectBiz.findFeeSubjectById(discountPolicy.getFeeSubjectId()).getName());
//								discountList.add(discountPolicy);
//							}
//						}
						//12.30重构优惠政策
						//需要申请的政策
						if(isApplicationNeeded==Constants.POLICY_APPLICATION_NEEDED_TRUE)
						{
							if(discountPolicy.getIsApplicationNeeded()==Constants.STUDENT_DISCOUNT_AUDIT_CEDU || discountPolicy.getIsApplicationNeeded()==Constants.STUDENT_DISCOUNT_AUDIT_BRANCH)
							{
								//申请时的时间必须在活动时间之内才能申请
								if(DateUtil.compareDate(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss").toString(),discountPolicy.getActivityBeginDate().toString()) && DateUtil.compareDate(discountPolicy.getActivityEndDate().toString(),DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss").toString()))
								{	
									discountPolicy.setFeesubjectname(this.feeSubjectBiz.findFeeSubjectById(discountPolicy.getFeeSubjectId()).getName());
									discountList.add(discountPolicy);
								}
							}
						}
						else if(isApplicationNeeded==Constants.POLICY_APPLICATION_NEEDED_FALSE)//不需要申请的政策
						{
							if(discountPolicy.getIsApplicationNeeded()==Constants.STUDENT_DISCOUNT_AUDIT_FALSE)
							{
								discountPolicy.setFeesubjectname(this.feeSubjectBiz.findFeeSubjectById(discountPolicy.getFeeSubjectId()).getName());
								discountList.add(discountPolicy);
							}
						}
						else if(isApplicationNeeded==Constants.STUDENT_DISCOUNT_CUSTOM_BRANCH)//中心自定义政策
						{
							if(discountPolicy.getIsApplicationNeeded()==Constants.STUDENT_DISCOUNT_CUSTOM_BRANCH)
							{
								discountPolicy.setFeesubjectname(this.feeSubjectBiz.findFeeSubjectById(discountPolicy.getFeeSubjectId()).getName());
								discountList.add(discountPolicy);
							}
						}
					}
					else		//所有的优惠标准
					{
						discountPolicy.setFeesubjectname(this.feeSubjectBiz.findFeeSubjectById(discountPolicy.getFeeSubjectId()).getName());
						discountList.add(discountPolicy);
					}
				}
			}
		}
		if(discountList.size()>0)
		{
			return discountList;
		}
		else
		{
			return null;
		}
	}
}
