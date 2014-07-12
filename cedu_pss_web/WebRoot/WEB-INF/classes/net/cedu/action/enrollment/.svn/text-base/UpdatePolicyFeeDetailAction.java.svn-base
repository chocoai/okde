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

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 修改收费政策
 * @author lixiaojun
 *
 */
public class UpdatePolicyFeeDetailAction extends BaseAction
{
	
	@Autowired 
	private PolicyFeeDetailBiz policyFeeDetailBiz;//收费政策业务接口接口
	private PolicyFeeDetail policyFeeDetail=new PolicyFeeDetail();//收费政策实体
	
	
	//跳转参数
	private int id;//收费政策Id
	
	public String execute() throws Exception 
	{
		if(super.isGetRequest())
		{	
			if(id!=0)
			{
				policyFeeDetail=this.policyFeeDetailBiz.findPolicyFeeDetailDetailsById(id);				
								
			}
			return INPUT;
		}
		return SUCCESS;
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

	
}

