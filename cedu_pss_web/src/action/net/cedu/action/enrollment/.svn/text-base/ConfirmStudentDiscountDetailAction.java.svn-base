package net.cedu.action.enrollment;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.StudentDiscountDetailBiz;
import net.cedu.biz.enrollment.StudentDiscountPolicyBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.entity.enrollment.StudentDiscountDetail;
import net.cedu.entity.enrollment.StudentDiscountPolicy;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 学生优惠政策详细审批
 * @author lixiaojun
 *
 */
@Results({
	@Result(name="index",location="list_student_discount_auditing",type="redirectAction")
	})
public class ConfirmStudentDiscountDetailAction extends BaseAction
{
	
	@Autowired
	private StudentDiscountDetailBiz studentDiscountDetailBiz;//优惠政策业务接口
	private StudentDiscountDetail studentDiscountDetail=new StudentDiscountDetail();//优惠政策实体
	
	@Autowired
	private StudentDiscountPolicyBiz studentDiscountpolicyBiz;//优惠标准业务接口
	private List<StudentDiscountPolicy> discountPolicyList=new ArrayList<StudentDiscountPolicy>();//优惠标准实体集合
	
	
	//跳转参数
	private int id;//优惠政策Id
	
	//页面传参
	private int confrimrad;//通过/不通过
	
	public String execute() throws Exception 
	{
		if(super.isGetRequest())
		{	
			if(id!=0)
			{
				studentDiscountDetail=this.studentDiscountDetailBiz.findStudentDiscountDetailDetailsById(id);
				String[] ids=studentDiscountDetail.getDiscountPolicyId().split(",");
				for(int i=0;i<ids.length;i++)
				{
					if(this.studentDiscountpolicyBiz.findStudentDiscountPolicyById(Integer.valueOf(ids[i]))!=null)
					{
						discountPolicyList.add(this.studentDiscountpolicyBiz.findStudentDiscountPolicyDetailsById(Integer.valueOf(ids[i])));
					}
				}
			}
			return INPUT;
		}
		studentDiscountDetail=this.studentDiscountDetailBiz.findStudentDiscountDetailDetailsById(id);
		if(confrimrad==1)
		{
			studentDiscountDetail.setAduitStatus(Constants.AUDIT_STATUS_TRUE);
			studentDiscountDetail.setIsUsing(Constants.STATUS_ENABLED);
		}
		else
		{
			studentDiscountDetail.setAduitStatus(Constants.AUDIT_STATUS_FAIL);
		}
		studentDiscountDetail.setAuditDate(DateUtil.getNowTimestamp("yyyy-MM-dd"));
		studentDiscountDetail.setAuditer(super.getUser().getUserId());
		boolean rs=this.studentDiscountDetailBiz.modifyStudentDiscountDetail(studentDiscountDetail);
		if(rs)
		{
			return "index";
		}
		else
		{
			return SUCCESS;
		}
	}

	public StudentDiscountDetail getStudentDiscountDetail() {
		return studentDiscountDetail;
	}

	public void setStudentDiscountDetail(StudentDiscountDetail studentDiscountDetail) {
		this.studentDiscountDetail = studentDiscountDetail;
	}

	public List<StudentDiscountPolicy> getDiscountPolicyList() {
		return discountPolicyList;
	}

	public void setDiscountPolicyList(List<StudentDiscountPolicy> discountPolicyList) {
		this.discountPolicyList = discountPolicyList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getConfrimrad() {
		return confrimrad;
	}

	public void setConfrimrad(int confrimrad) {
		this.confrimrad = confrimrad;
	}
	
	
}
