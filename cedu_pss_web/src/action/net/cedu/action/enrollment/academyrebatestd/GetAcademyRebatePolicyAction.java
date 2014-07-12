package net.cedu.action.enrollment.academyrebatestd;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.enrollment.AcademyRebatePolicyBiz;
import net.cedu.biz.enrollment.AcademyRebatePolicyDetailStandardBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.enrollment.AcademyRebatePolicy;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 获取院校返款政策标准，连带取出起各项［准则］、相应院校名称等
 * 
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results(value={@Result(name="success", type="json")})
public class GetAcademyRebatePolicyAction extends BaseAction
{
	private static final long serialVersionUID = -6506753138835028974L;
	
	private int policyId;
	
	private AcademyRebatePolicy policy;
	
	@Autowired
	private AcademyRebatePolicyBiz academyRebatePolicyBiz;
	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;
	@Autowired
	private AcademyRebatePolicyDetailStandardBiz academyRebatePolicyDetailStandardBiz;

	@Override
	public String execute() throws Exception
	{
		policy = academyRebatePolicyBiz.getAcademyRebatePolicyById(policyId);
		policy.setStandards(academyRebatePolicyDetailStandardBiz.findByPolicyId(policyId));
		
		Academy academy = academyBiz.findAcademyById(policy.getAcademyId());
		if(academy!=null){
			policy.setAcademyName(academy.getName());
		}
		
		FeeSubject fs = feeSubjectBiz.findFeeSubjectById(policy.getFeeSubjectId());
		if(fs != null){
			policy.setFeeSubjectName(fs.getName());
		}
		
		return SUCCESS;
	}

	public AcademyRebatePolicy getPolicy() {
		return policy;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}
}
