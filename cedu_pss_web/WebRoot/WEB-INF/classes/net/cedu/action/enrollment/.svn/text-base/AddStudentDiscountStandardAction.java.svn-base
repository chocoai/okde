package net.cedu.action.enrollment;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.biz.enrollment.StudentDiscountPolicyBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.enums.CodeEnum;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.enrollment.StudentDiscountPolicy;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 添加学生优惠政策标准
 * @author lixiaojun
 *
 */
@Results({
	@Result(name="index",location="list_student_discount_standard",type="redirectAction")
	})
public class AddStudentDiscountStandardAction extends BaseAction
{
	
	@Autowired 
	private AcademyBiz academyBiz;//院校biz
	private List<Academy> academylist=new ArrayList<Academy>();//院校集合	
	
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;//费用科目biz
	private List<FeeSubject> feesubjectlist=new ArrayList<FeeSubject>();//费用科目集合
	
	@Autowired
	private StudentDiscountPolicyBiz studentDiscountPolicyBiz;//学生优惠标准业务接口
	private StudentDiscountPolicy studentDiscount=new StudentDiscountPolicy();//学生优惠标准实体
	
	boolean isfail=false;
	
	@Autowired
	private BuildCodeBiz buildCodeBiz;//code生成业务接口
	
	public String execute() throws Exception 
	{		
		academylist=this.academyBiz.findAllAcademyByFlagFalse();
		if(academylist!=null && academylist.size()>0)
		{
			Collections.sort(academylist, new Comparator() {
				public int compare(Object arg0, Object arg1) {
					Comparator cmp = Collator
							.getInstance(java.util.Locale.CHINA);
					String name1 = ((Academy) arg0).getName();
					String name2 = ((Academy) arg1).getName();
					return cmp.compare(name1, name2);
				}
			});
		}
		feesubjectlist=this.feeSubjectBiz.findAllFeeSubjectByDeleteFlag();
		if(super.isGetRequest())
		{				
			return INPUT;
		}
		
		studentDiscount.setCode(buildCodeBiz.getCodes(CodeEnum.StudentDiscountPolicyTB.getCode(),CodeEnum.StudentDiscountPolicyPrefix.getCode()));
		studentDiscount.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
		studentDiscount.setCreatorId(super.getUser().getUserId());
		studentDiscount.setDeleteFlag(Constants.DELETE_FALSE);
		studentDiscount.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
		studentDiscount.setUpdaterId(super.getUser().getUserId());
		//优惠主体院校才有是否共同承担
		if(studentDiscount.getTargetObjectId()!=Constants.POLICY_TARGET_OBJECT_ACADEMY)
		{
			studentDiscount.setIsShared(Constants.POLICY_IS_SHARED_FLASE);
		}
		isfail=this.studentDiscountPolicyBiz.addStudentDiscountPolicy(studentDiscount);
		if(isfail)
		{
			return "index";
		}
		return SUCCESS;
	}


	public List<Academy> getAcademylist() {
		return academylist;
	}


	public void setAcademylist(List<Academy> academylist) {
		this.academylist = academylist;
	}


	public List<FeeSubject> getFeesubjectlist() {
		return feesubjectlist;
	}


	public void setFeesubjectlist(List<FeeSubject> feesubjectlist) {
		this.feesubjectlist = feesubjectlist;
	}


	public StudentDiscountPolicy getStudentDiscount() {
		return studentDiscount;
	}


	public void setStudentDiscount(StudentDiscountPolicy studentDiscount) {
		this.studentDiscount = studentDiscount;
	}
	
	
}
