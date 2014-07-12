package net.cedu.action.enrollment.academyrebatepolicy;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.AcademyRebatePolicyDetailBiz;
import net.cedu.common.Constants;
import net.cedu.entity.enrollment.AcademyRebatePolicyDetail;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 招生返款政策审批
 * 
 * @author Sauntor
 */
@Results({
	@Result(name="success", type="redirect", location="/enrollment/academyrebatepolicy/audit_academy_rebate_policy_list")
})
public class ModifyAcademyRebatePolicyDetailAuditStatusAction extends BaseAction
{
	private static final long serialVersionUID = -3470551804011526192L;
	
	private int detailId;
	private int auditStatus;
	
	@Autowired
	private AcademyRebatePolicyDetailBiz academyRebatePolicyDetailBiz;

	public String execute() throws Exception
	{
		AcademyRebatePolicyDetail detail = academyRebatePolicyDetailBiz.findPolicyDetailById(detailId);
		detail.setAcademyId(getUser().getUserId());
		detail.setAuditStatus(auditStatus);
		detail.setAuditerId(getUser().getUserId());
		detail.setAuditDate(new Date());
		
		detail.setEnable(Constants.FALSE);
		detail.setUpdaterId(super.getUser().getUserId());
		detail.setUpdatedTime(new Date());
		academyRebatePolicyDetailBiz.updateDetail(detail);
		
		return SUCCESS;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}

	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}
}
