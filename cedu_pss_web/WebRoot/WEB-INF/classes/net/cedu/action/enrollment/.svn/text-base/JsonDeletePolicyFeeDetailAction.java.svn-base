package net.cedu.action.enrollment;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.PolicyFeeDetailBiz;
import net.cedu.entity.enrollment.PolicyFeeDetail;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 删除收费政策
 * @author lixiaojun
 *
 */
@ParentPackage("json-default")
public class JsonDeletePolicyFeeDetailAction extends BaseAction
{
	@Autowired
	private PolicyFeeDetailBiz policyFeeDetailBiz;//收费政策明细biz
	private int pfdId;//收费政策明细Id
	private boolean isback=false;
	
	/**
	 * 删除院校收费政策
	 * @return
	 * @throws Exception
	 */
	@Action(value = "del_policy_fee_detail_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String deldetail() throws Exception
	{
		if(pfdId>0)
		isback=this.policyFeeDetailBiz.deletePolicyFeeDetailById(pfdId);
		return SUCCESS;
	}

	public int getPfdId() {
		return pfdId;
	}

	public void setPfdId(int pfdId) {
		this.pfdId = pfdId;
	}

	public boolean isIsback() {
		return isback;
	}

	public void setIsback(boolean isback) {
		this.isback = isback;
	}
	
}
