package net.cedu.action.enrollment.academyrebatestd;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.AcademyRebatePolicyBiz;
import net.cedu.entity.enrollment.AcademyRebatePolicy;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 院校返款政策 列表查询(Ajax)
 * 
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results(@Result(name="success", type="json", params={"contentType", "text/json"}))
public class ListAcademyRebatePolicyAvailableToAcademyAction extends BaseAction
{
	private static final long serialVersionUID = -2492437830647073530L;

	/* 查询条件 */
	private int academyId;
	private int feeSubjectId;

	private PageResult<AcademyRebatePolicy> result = new PageResult<AcademyRebatePolicy>();
	
	@Autowired
	private AcademyRebatePolicyBiz academyRebatePolicyBiz;
	
	/**
	 * 列表数据
	 * @return
	 * @throws Exception
	 */
	@Override
	public String execute() throws Exception
	{	
		result.setList(academyRebatePolicyBiz.findAvailableForAcademy(academyId, feeSubjectId));
		
		return SUCCESS;
	}

	public PageResult<AcademyRebatePolicy> getResult() {
		return result;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public void setFeeSubjectId(int feeSubjectId) {
		this.feeSubjectId = feeSubjectId;
	}
}
