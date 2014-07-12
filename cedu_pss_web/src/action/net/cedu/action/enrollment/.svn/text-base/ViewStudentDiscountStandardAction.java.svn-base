package net.cedu.action.enrollment;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.enrollment.StudentDiscountPolicyBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.enrollment.StudentDiscountPolicy;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 院校优惠收费政策标准详细
 * @author lixiaojun
 *
 */
public class ViewStudentDiscountStandardAction extends BaseAction
{

	@Autowired
	private StudentDiscountPolicyBiz studentDiscountPolicyBiz;//学生优惠标准业务接口
	private StudentDiscountPolicy studentDiscount=new StudentDiscountPolicy();//学生优惠标准实体
	
	@Autowired 
	private AcademyBiz academyBiz;//院校biz
	
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;//费用科目biz
	
	//跳转参数
	private int id;//收费政策Id
	
	public String execute() throws Exception 
	{
		if(super.isGetRequest())
		{	
			if(id!=0)
			{
				studentDiscount=this.studentDiscountPolicyBiz.findStudentDiscountPolicyById(id);
				if(studentDiscount.getAcademyId()!=0 && this.academyBiz.findAcademyById(studentDiscount.getAcademyId())!=null)
				{
					studentDiscount.setAcademyname(this.academyBiz.findAcademyById(studentDiscount.getAcademyId()).getName());
				}
				if(studentDiscount.getFeeSubjectId()!=0 && this.feeSubjectBiz.findFeeSubjectById(studentDiscount.getFeeSubjectId())!=null)
				{
					studentDiscount.setFeesubjectname(this.feeSubjectBiz.findFeeSubjectById(studentDiscount.getFeeSubjectId()).getName());
				}
			}
			return INPUT;
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

	
}
