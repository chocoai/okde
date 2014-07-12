package net.cedu.action.enrollment.academyrebatepolicy;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.AcademyRebatePolicyDetailBiz;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 批量更新院校返款明细
 * 
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results({
	@Result(name="success", type="json")
})
public class UpdateAcademyRbtPlcyDtlListAction extends BaseAction
{
	private static final long serialVersionUID = -5402171535763038798L;
	
	private int[] detailIds;
	private int policyId;
	
	@Autowired
	private AcademyRebatePolicyDetailBiz academyRebatePolicyDetailBiz;

	@Override
	public String execute() throws Exception
	{
		academyRebatePolicyDetailBiz.updateListToStandard(detailIds, policyId, getUser().getUserId());
		return SUCCESS;
	}

	public void setDetailIds(int[] detailIds) {
		this.detailIds = detailIds;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}
}
