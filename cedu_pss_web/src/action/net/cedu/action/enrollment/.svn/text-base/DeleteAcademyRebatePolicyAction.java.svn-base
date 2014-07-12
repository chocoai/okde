package net.cedu.action.enrollment;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.AcademyRebatePolicyBiz;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

/**
 * 根据ID删除院校返款政策信息
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results(@Result(name="success",type="json"))
public class DeleteAcademyRebatePolicyAction extends BaseAction
{
	private int id;
	
	private AcademyRebatePolicyBiz academyRebatePolicyBiz;
	
	public String execute() throws Exception
	{
		academyRebatePolicyBiz.deleteAcademyRebatePolicyById(id);
		
		return SUCCESS;
	}
}
