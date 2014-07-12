package net.cedu.action.enrollment;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.ChannelPolicyBiz;
import net.cedu.entity.enrollment.ChannelPolicy;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;

public class JsonListChannelPolicyAction extends BaseAction
{
	private int academyId;
	private String policyName;
	
	private int pageSize;
	private int pageIndex;
	
	private List<ChannelPolicy> list;
	private int total;
	
	private ChannelPolicyBiz channelPolicyBiz;
	
	@Action("list_channel_policy")
	public String execute() throws Exception
	{
		ChannelPolicy condition = new ChannelPolicy();
		
		condition.setTitle(policyName);
		
		total = channelPolicyBiz.countByExample(condition);
		
		if(total>0){
			PageResult<ChannelPolicy> prPageResult=new PageResult<ChannelPolicy>();
			prPageResult.setCurrentPageIndex(pageIndex);
			prPageResult.setPageSize(pageSize);
			list = channelPolicyBiz.findByExample(condition, prPageResult);
//			list = channelPolicyBiz.findByExample(condition, pageIndex, pageSize);
		}
		
		return SUCCESS;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public List<ChannelPolicy> getList() {
		return list;
	}

	public int getTotal() {
		return total;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
}
