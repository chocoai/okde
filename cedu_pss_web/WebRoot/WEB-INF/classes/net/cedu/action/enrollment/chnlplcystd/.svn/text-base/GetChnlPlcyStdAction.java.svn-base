package net.cedu.action.enrollment.chnlplcystd;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.ChannelPolicyBiz;
import net.cedu.biz.enrollment.ChannelPolicyDetailStandardBiz;
import net.cedu.entity.enrollment.ChannelPolicy;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
@Results({
	@Result(name="success", type="json"),
	@Result(name="error", type="json")
})
public class GetChnlPlcyStdAction extends BaseAction
{
	private static final long serialVersionUID = -2861340947795589347L;
	
	private int id;
	
	private ChannelPolicy policy;
	
	@Autowired
	private ChannelPolicyBiz channelPolicyBiz;
	@Autowired
	private ChannelPolicyDetailStandardBiz channelPolicyDetailStandardBiz;

	@Override
	public String execute() throws Exception
	{
		policy = channelPolicyBiz.findChannelPolicyById(id);
		policy.setStandards(channelPolicyDetailStandardBiz.findByPolicyId(id));
		
		return SUCCESS;
	}

	public ChannelPolicy getPolicy() {
		return policy;
	}

	public void setId(int id) {
		this.id = id;
	}
}
