package net.cedu.action.enrollment.academyrebatestd;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import net.cedu.action.BaseAction;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.biz.enrollment.AcademyRebatePolicyBiz;
import net.cedu.biz.enrollment.AcademyRebatePolicyDetailStandardBiz;
import net.cedu.common.Constants;
import net.cedu.common.enums.CodeEnum;
import net.cedu.entity.enrollment.AcademyRebatePolicy;
import net.cedu.entity.enrollment.AcademyRebatePolicyDetailStandard;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 新增院校返款政策
 * 
 * @author Sauntor
 *
 */
@Results(@Result(name="success", type="redirect", location="view_academy_rbt_std?policyId=${policyId}"))
public class SaveAcdmRbtPlcyAction extends BaseAction implements ModelDriven<AcademyRebatePolicy>
{
	private static final long serialVersionUID = -2743786192912483076L;

	private int errno = 0;
	private int policyId;
	
	private AcademyRebatePolicy policy = new AcademyRebatePolicy(new LinkedList<AcademyRebatePolicyDetailStandard>());

	@Autowired
	private AcademyRebatePolicyBiz academyRebatePolicyBiz;
	@Autowired
	private AcademyRebatePolicyDetailStandardBiz academyRebatePolicyDetailStandardBiz;
	@Autowired
	private BuildCodeBiz buildCodeBiz;
	
	public String execute() throws Exception
	{
		policy.setDeleteFlag(Constants.DELETE_FALSE);
		policy.setCreatedTime(new Date());
		policy.setUpdatedTime(new Date());
		
		policy.setUpdaterId(getUser().getUserId());
		policy.setCreatorId(getUser().getUserId());
		
		String code = buildCodeBiz.getCodes(CodeEnum.AcademyRebatePolicyTB.getCode(), CodeEnum.AcademyRebatePolicy.getCode());
		policy.setCode(code);
		
		policyId = academyRebatePolicyBiz.addAcademyRebatePolicy(policy);
		
		Iterator<AcademyRebatePolicyDetailStandard> stdIter = policy.getStandards().iterator();
		while(stdIter.hasNext())
		{
			AcademyRebatePolicyDetailStandard std = stdIter.next();
			std.setPolicyId(policyId);
		}
		
		academyRebatePolicyDetailStandardBiz.addList(policy.getStandards());
		
		return SUCCESS;
	}

	public AcademyRebatePolicy getPolicy() {
		return policy;
	}

	public int getErrno() {
		return errno;
	}

	public AcademyRebatePolicy getModel() {
		return policy;
	}

	public int getPolicyId() {
		return policyId;
	}
}
