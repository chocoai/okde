package net.cedu.action.enrollment.chnlplcystd;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.ChannelBiz;
import net.cedu.biz.enrollment.ChannelPolicyBiz;
import net.cedu.entity.enrollment.Channel;
import net.cedu.entity.enrollment.ChannelPolicy;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
@Results({
	@Result(name="success", type="json"),
	@Result(name="success", type="json")
})
public class ListChnlPlcyStdForChnlAction extends BaseAction
{
	private static final long serialVersionUID = -4479929945720450245L;
	
	private int channelId;
	private int academyId;
	private int feeSubjectId;

	private List<ChannelPolicy> list;
	
	@Autowired
	private ChannelPolicyBiz channelPolicyBiz;
	@Autowired
	private ChannelBiz channelBiz;

	@Override
	public String execute() throws Exception
	{
		int branchId=0;
		if(channelId>0)
		{
			Channel channel =this.channelBiz.findChannel(channelId);
			if(channel!=null)
			{
				branchId=channel.getBranchId();
			}
		}
		list = channelPolicyBiz.findAvaliableToChannel(channelId, academyId, feeSubjectId,branchId);
		return SUCCESS;
	}

	public List<ChannelPolicy> getList() {
		return list;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public void setFeeSubjectId(int feeSubjectId) {
		this.feeSubjectId = feeSubjectId;
	}
}
