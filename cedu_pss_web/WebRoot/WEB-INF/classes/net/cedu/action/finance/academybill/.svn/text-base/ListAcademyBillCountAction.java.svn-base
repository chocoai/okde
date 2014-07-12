package net.cedu.action.finance.academybill;

import net.cedu.action.BaseAction;
import net.cedu.biz.finance.PlanedAcademyBillBiz;
import net.cedu.common.Constants;
import net.cedu.entity.finance.PlanedAcademyBill;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 应收院校款 Ajax列表 (数量) 查询
 * 
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results({
	@Result(name="success", type="json")
})
public class ListAcademyBillCountAction extends BaseAction implements ModelDriven<PlanedAcademyBill>
{
	private static final long serialVersionUID = -3226868257678228777L;

	PlanedAcademyBill model = new PlanedAcademyBill();
	
	private PageResult<PlanedAcademyBill> result = new PageResult<PlanedAcademyBill>();
	
	@Autowired
	private PlanedAcademyBillBiz planedAcademyBillBiz;
	
	public String execute() throws Exception
	{
		model.setDeleteFlag(Constants.DELETE_FALSE);
		
		int count = planedAcademyBillBiz.count(model, result);
		
		result.setRecordCount(count);
		
		return SUCCESS;
	}

	public PlanedAcademyBill getModel() {
		return model;
	}

	public PageResult<PlanedAcademyBill> getResult() {
		return result;
	}
}
