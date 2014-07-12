package net.cedu.action.enrollment.academyrebatepolicy;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.AcademyRebatePolicyDetailBiz;
import net.cedu.entity.enrollment.AcademyRebatePolicyDetail;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 修改院校返款政策明细,仅修改政策标准ID
 */
@ParentPackage("json-default")
@Results({
	@Result(name="success", type="json")
})
public class UpdateAcdmRbtPlcyDtlAction extends BaseAction
{
	private static final long serialVersionUID = 8203286991309960798L;
	
	private int policyId;
	private int detailId;
	
	@Autowired
	private AcademyRebatePolicyDetailBiz academyRebatePolicyDetailBiz;

	@Override
	public String execute() throws Exception
	{
		AcademyRebatePolicyDetail detail = academyRebatePolicyDetailBiz.findPolicyDetailById(detailId);
		
		detail.setUpdaterId(getUser().getUserId());
		detail.setUpdatedTime(new Date());
		detail.setPolicyId(policyId);
		
		academyRebatePolicyDetailBiz.updateDetail(detail);
		
		return SUCCESS;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public int getDetailId() {
		return detailId;
	}

	public int getPolicyId() {
		return policyId;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}
	
}
