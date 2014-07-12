package net.cedu.action.enrollment.academyrebatepolicy;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.AcademyRebatePolicyDetailBiz;
import net.cedu.entity.enrollment.AcademyRebatePolicyDetail;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
@Results({
	@Result(name="success", type="json"),
	@Result(name="error", type="json")
})
public class ModifyAcademyRebatePolicyDetailEnableStatusAction extends BaseAction
{
	private static final long serialVersionUID = 4997810960044595112L;
	
	private int enable;
	private int detailId;
	
	@Autowired
	private AcademyRebatePolicyDetailBiz academyRebatePolicyDetailBiz;

	@Override
	public String execute() throws Exception
	{
		AcademyRebatePolicyDetail detail = academyRebatePolicyDetailBiz.findPolicyDetailById(detailId);
		
		detail.setEnable(enable);
		detail.setUpdatedTime(new Date());
		detail.setUpdaterId(getUser().getUserId());
		
		academyRebatePolicyDetailBiz.updateDetail(detail);
		
		return SUCCESS;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}
}
