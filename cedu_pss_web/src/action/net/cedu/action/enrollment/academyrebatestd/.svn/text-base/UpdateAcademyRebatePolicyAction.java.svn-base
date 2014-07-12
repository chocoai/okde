package net.cedu.action.enrollment.academyrebatestd;

import java.util.Date;
import java.util.LinkedList;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.AcademyRebatePolicyBiz;
import net.cedu.entity.enrollment.AcademyRebatePolicy;
import net.cedu.entity.enrollment.AcademyRebatePolicyDetailStandard;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 更新院校返款政策
 * 
 * @author Sauntor
 *
 */
@Results(@Result(name="success", type="redirect", location="/enrollment/academyrebatestd/view_academy_rbt_std?policyId=${id}"))
public class UpdateAcademyRebatePolicyAction extends BaseAction implements ModelDriven<AcademyRebatePolicy>
{
	private static final long serialVersionUID = -7374113518348156723L;

	private AcademyRebatePolicy policy = new AcademyRebatePolicy(new LinkedList<AcademyRebatePolicyDetailStandard>());

	@Autowired
	private AcademyRebatePolicyBiz academyRebatePolicyBiz;
	
	@Override
	public String execute() throws Exception
	{
		AcademyRebatePolicy entity = academyRebatePolicyBiz.getAcademyRebatePolicyById(policy.getId());
		entity.setAcademyId(policy.getAcademyId());
		entity.setFeeSubjectId(policy.getFeeSubjectId());
		entity.setTitle(policy.getTitle());
		entity.setUpdatedTime(new Date());
		entity.setUpdaterId(getUser().getUserId());
		
		academyRebatePolicyBiz.updateWithStandards(policy);
		
		return SUCCESS;
	}

	public AcademyRebatePolicy getModel() {
		return policy;
	}
}
