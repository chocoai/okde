package net.cedu.action.enrollment.chnlplcystd;

import java.util.Date;
import java.util.LinkedList;

import net.cedu.action.BaseAction;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.biz.enrollment.ChannelPolicyBiz;
import net.cedu.common.enums.CodeEnum;
import net.cedu.entity.enrollment.ChannelPolicy;
import net.cedu.entity.enrollment.ChannelPolicyDetailStandard;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 新增招生返款政策标准
 */
//@ParentPackage("json-default")
@Results({
	@Result(name="success", type="redirect", location="/enrollment/chnlplcystd/view_chnl_plcy_std?id=${policyId}"),
	@Result(name="input", type="redirect", location="/enrollment/chnlplcystd/add_chnl_plcy_std")
})
public class SaveChnlPlcyStdAction extends BaseAction implements ModelDriven<ChannelPolicy>
{
	private static final long serialVersionUID = -6153056501297652983L;
	
	private int policyId;

	private ChannelPolicy model = new ChannelPolicy(new LinkedList<ChannelPolicyDetailStandard>());
	
	@Autowired
	private ChannelPolicyBiz channelPolicyBiz;
	@Autowired
	private BuildCodeBiz buildCodeBiz;
	
	@Override
	public String execute() throws Exception
	{
		Date now = new Date();
		String code = buildCodeBiz.getCodes(CodeEnum.ChannelPolicyTB.getCode(), CodeEnum.ChannelPolicy.getCode());
		model.setCode(code);
		model.setCreatedTime(now);
		model.setCreatorId(getUser().getUserId());
		model.setUpdatedTime(now);
		model.setUpdaterId(getUser().getUserId());
		policyId = channelPolicyBiz.addPolicyWithStandards(model);
		
		return SUCCESS;
	}

	public ChannelPolicy getModel() {
		return model;
	}

	public int getPolicyId() {
		return policyId;
	}
}
