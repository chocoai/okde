package net.cedu.action.enrollment.academyrebatepolicy;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.AcademyRebatePolicyDetailBiz;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
@Results({@Result(name="success", type="json")})
public class DeleteAcdmRbtPlcDtlAction extends BaseAction
{
	private static final long serialVersionUID = 4886877757248760844L;

	private int detailId;
	
	@Autowired
	private AcademyRebatePolicyDetailBiz academyRebatePolicyDetailBiz;
	
	public String execute() throws Exception
	{
		academyRebatePolicyDetailBiz.deleteById(detailId);
		
		return SUCCESS;
	}

	public int getDetailId() {
		return detailId;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}
}
