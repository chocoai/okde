package net.cedu.action.enrollment.academyrebatepolicy;

import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.AcademyRebatePolicyDetailBiz;
import net.cedu.common.Constants;
import net.cedu.entity.enrollment.AcademyRebatePolicyDetail;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 更新招生返款政策，仅改变政策标准（业务要求这么做）
 * 
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results({
	@Result(name=SUCCESS, type="json"),
	@Result(name=INPUT, type="json")
})
public class BatchAuditAcdmRbtPlcDtlAction extends BaseAction
{
	private static final long serialVersionUID = 970247478512234022L;
	
	private int[] detailIds;
	private int auditStatus;

	@Autowired
	private AcademyRebatePolicyDetailBiz academyRebatePolicyDetailBiz;

	public String execute() throws Exception
	{
		if(detailIds==null){ return null; }
		
		List<AcademyRebatePolicyDetail> list = new LinkedList<AcademyRebatePolicyDetail>();
		
		for(int id : detailIds){
			AcademyRebatePolicyDetail detail  = academyRebatePolicyDetailBiz.findPolicyDetailById(id);
			detail.setAuditStatus(auditStatus);
			if(auditStatus==Constants.POLICY_AUDIT_STATUS_GOOD)
			{
				detail.setEnable(Constants.STATUS_ENABLED);
			}
			detail.setAuditDate(new Date());
			detail.setAuditerId(getUser().getUserId());
			detail.setUpdatedTime(new Date());
			detail.setUpdaterId(getUser().getUserId());
			
			//detail.setEnable(Constants.FALSE);
			
			list.add(detail);
		}
		
		academyRebatePolicyDetailBiz.updateList(list);
		
		return SUCCESS;
	}

	public void setDetailIds(int[] detailIds) {
		this.detailIds = detailIds;
	}

	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}
}
