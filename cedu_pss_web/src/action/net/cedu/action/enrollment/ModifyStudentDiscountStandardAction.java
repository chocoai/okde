package net.cedu.action.enrollment;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.enrollment.DiscountApplicationBiz;
import net.cedu.biz.enrollment.StudentDiscountPolicyBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.enrollment.DiscountApplication;
import net.cedu.entity.enrollment.StudentDiscountPolicy;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 修改院校优惠收费政策标准
 * @author lixiaojun
 *
 */
@Results({
	@Result(name="index",location="list_student_discount_standard",type="redirectAction")
	})
public class ModifyStudentDiscountStandardAction extends BaseAction
{

	@Autowired
	private StudentDiscountPolicyBiz studentDiscountPolicyBiz;//学生优惠标准业务接口
	private StudentDiscountPolicy studentDiscount=new StudentDiscountPolicy();//学生优惠标准实体
	
	@Autowired 
	private AcademyBiz academyBiz;//院校biz
	private List<Academy> academylist=new ArrayList<Academy>();//院校集合	
	
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;//费用科目biz
	private List<FeeSubject> feesubjectlist=new ArrayList<FeeSubject>();//费用科目集合
	private FeeSubject feeSucject =new FeeSubject();//费用科目实体
	
	@Autowired
	private DiscountApplicationBiz discountApplicationBiz;//优惠券业务接口
	
	//返回参数
	boolean isfail=false;
	
	//跳转参数
	private int id;//收费政策Id
	
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
			if(id!=0)
			{
				studentDiscount=this.studentDiscountPolicyBiz.findStudentDiscountPolicyById(id);
				feeSucject=this.feeSubjectBiz.findFeeSubjectById(studentDiscount.getFeeSubjectId());
			}
			return INPUT;
		}
		StudentDiscountPolicy oldstudentDiscount=this.studentDiscountPolicyBiz.findStudentDiscountPolicyById(id);
		studentDiscount.setId(id);
		studentDiscount.setCode(oldstudentDiscount.getCode());
		studentDiscount.setCreatedTime(oldstudentDiscount.getCreatedTime());
		studentDiscount.setCreatorId(oldstudentDiscount.getCreatorId());
		studentDiscount.setDeleteFlag(oldstudentDiscount.getDeleteFlag());
		studentDiscount.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
		studentDiscount.setUpdaterId(super.getUser().getUserId());
		//优惠主体院校才有是否共同承担
		if(studentDiscount.getTargetObjectId()!=Constants.POLICY_TARGET_OBJECT_ACADEMY)
		{
			studentDiscount.setIsShared(Constants.POLICY_IS_SHARED_FLASE);
		}
		isfail=this.studentDiscountPolicyBiz.modifyStudentDiscountPolicy(studentDiscount);
		
		//修改优惠券
		List<DiscountApplication> dapplist=this.discountApplicationBiz.findDiscountApplicationListByDiscountPolicyId(id);
		if(dapplist!=null && dapplist.size()>0)
		{
			for(DiscountApplication dpp:dapplist)
			{
				dpp.setStartTime(studentDiscount.getUseBeginDate());
				dpp.setEndTime(studentDiscount.getUseEndDate());
				this.discountApplicationBiz.updateDiscountApplication(dpp);
			}
		}
		
		
		if(isfail)
		{
			return "index";
		}
		else
		{
			if(id!=0)
			{
				studentDiscount=this.studentDiscountPolicyBiz.findStudentDiscountPolicyById(id);
				feeSucject=this.feeSubjectBiz.findFeeSubjectById(studentDiscount.getFeeSubjectId());
			}
		}
		return SUCCESS;
	}

	public StudentDiscountPolicy getStudentDiscount() {
		return studentDiscount;
	}

	public void setStudentDiscount(StudentDiscountPolicy studentDiscount) {
		this.studentDiscount = studentDiscount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public boolean isIsfail() {
		return isfail;
	}

	public void setIsfail(boolean isfail) {
		this.isfail = isfail;
	}

	public FeeSubject getFeeSucject() {
		return feeSucject;
	}

	public void setFeeSucject(FeeSubject feeSucject) {
		this.feeSucject = feeSucject;
	}
	
}
