package net.cedu.action.enrollment;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.PolicyFeeBiz;
import net.cedu.biz.enrollment.PolicyFeeDetailBiz;
import net.cedu.entity.enrollment.PolicyFee;
import net.cedu.entity.enrollment.PolicyFeeDetail;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 收费政策详细
 * @author lixiaojun
 *
 */
public class ViewPolicyFeeDetailAction extends BaseAction
{

	@Autowired
	private PolicyFeeBiz policyFeeBiz;//收费标准业务接口
	private PolicyFee policyFee=new PolicyFee();//收费标准实体
	
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
				
				//收费标准
				policyFee=this.policyFeeBiz.findPolicyFeeAndFeeStandardById(policyFeeDetail.getPolicyFeeId());
			}
			return INPUT;
		}
		return SUCCESS;
	}

	public PolicyFee getPolicyFee() {
		return policyFee;
	}

	public void setPolicyFee(PolicyFee policyFee) {
		this.policyFee = policyFee;
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
