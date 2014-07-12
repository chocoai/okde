package net.cedu.action.enrollment;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.ChannelPolicyBiz;
import net.cedu.common.Constants;
import net.cedu.entity.enrollment.ChannelPolicy;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 合作方招生返款政策 列表
 * 
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results({@Result(name="success", type="json"), @Result(name="error", type="json")})
public class ListChannelPolicyAction extends BaseAction
{
	private static final long serialVersionUID = -6376035033852106333L;
//	
//	private int academyId;
	private String policyName;
	
	private int pageSize;
	private int pageIndex;
	
	private int totalSize;
	
	private List<ChannelPolicy> list;
	
	@Autowired
	private ChannelPolicyBiz channelPolicyBiz;

	@Override
	public String execute() throws Exception
	{
		ChannelPolicy condition = new ChannelPolicy();
		condition.setDeleteFlag(Constants.DELETE_FALSE);
		condition.setTitle(policyName);
		PageResult<ChannelPolicy> prPageResult=new PageResult<ChannelPolicy>();
		prPageResult.setCurrentPageIndex(pageIndex);
		prPageResult.setPageSize(pageSize);
		list = channelPolicyBiz.findByExample(condition, prPageResult);
//		list = channelPolicyBiz.findByExample(condition, pageIndex, pageSize);
		totalSize = channelPolicyBiz.countByExample(condition);
		
		return SUCCESS;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public List<ChannelPolicy> getList() {
		return list;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
}
