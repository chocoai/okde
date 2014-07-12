package net.cedu.action.enrollment;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.enrollment.AcademyBatchBranchBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.enrollment.AcademyMajorBiz;
import net.cedu.biz.enrollment.PolicyFeeBiz;
import net.cedu.biz.enrollment.PolicyFeeDetailBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.AcademyLevel;
import net.cedu.entity.enrollment.AcademyMajor;
import net.cedu.entity.enrollment.Major;
import net.cedu.entity.enrollment.PolicyFee;
import net.cedu.entity.enrollment.PolicyFeeDetail;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 审批院校收费政策
 * @author lixiaojun
 *
 */
@Results({
	@Result(name="index",location="list_policy_fee_auditing",type="redirectAction")
	})
public class ConfirmPolicyFeeDetailAction extends BaseAction
{
			
	@Autowired
	private PolicyFeeBiz policyFeeBiz;//收费标准业务接口
	private PolicyFee policyFee=new PolicyFee();//收费标准实体
	
	@Autowired 
	private PolicyFeeDetailBiz policyFeeDetailBiz;//收费政策业务接口接口
	private PolicyFeeDetail policyFeeDetail=new PolicyFeeDetail();//收费政策实体
	
	
	//跳转参数
	private int id;//收费政策Id
	
	//页面传参
	private int confrimrad;//通过/不通过
	
	public String execute() throws Exception 
	{
		if(super.isGetRequest())
		{	
			if(id!=0)
			{
				policyFeeDetail=this.policyFeeDetailBiz.findPolicyFeeDetailDetailsById(id);				
				//收费标准
				policyFee=this.policyFeeBiz.findPolicyFeeAndFeeStandardById(policyFeeDetail.getPolicyFeeId());				
			}
			return INPUT;
		}
		policyFeeDetail=this.policyFeeDetailBiz.findPolicyFeeDetailById(id);
		if(confrimrad==1)
		{
			policyFeeDetail.setAduitStatus(Constants.AUDIT_STATUS_TRUE);
			policyFeeDetail.setIsUsing(Constants.STATUS_ENABLED);
		}
		else
		{
			policyFeeDetail.setAduitStatus(Constants.AUDIT_STATUS_FAIL);
		}
		policyFeeDetail.setAuditDate(DateUtil.getNowTimestamp("yyyy-MM-dd"));
		policyFeeDetail.setAuditer(super.getUser().getUserId());
		boolean results=this.policyFeeDetailBiz.modifyPolicyFeeDetail(policyFeeDetail);
		if(results)
		{
			return "index";
		}
		else
		{
			return SUCCESS;
		}
	}

	public PolicyFeeDetail getPolicyFeeDetail() {
		return policyFeeDetail;
	}

	public void setPolicyFeeDetail(PolicyFeeDetail policyFeeDetail) {
		this.policyFeeDetail = policyFeeDetail;
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

	public PolicyFee getPolicyFee() {
		return policyFee;
	}

	public void setPolicyFee(PolicyFee policyFee) {
		this.policyFee = policyFee;
	}
	
	
}

