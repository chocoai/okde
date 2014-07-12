package net.cedu.action.enrollment.chnlplcystd;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.ChannelPolicyBiz;
import net.cedu.entity.enrollment.ChannelPolicy;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 招生返款政策标准 列表查询
 * 
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results({
	@Result(name="input", type="dispatcher", location="list_channel_policy_standard.jsp"),
	@Result(name="success", type="json"),
	@Result(name="error", type="json")
})
public class CountChnlPlcyStdAction extends BaseAction
{
	private static final long serialVersionUID = -5227388966391326495L;

	/* 查询条件 */
	private int academyId;
	private int channelTypeId;
	private int feeSubjectId;
	private int branchId;
	
	private PageResult<ChannelPolicy> result = new PageResult<ChannelPolicy>();
	
	@Autowired
	private ChannelPolicyBiz channelPolciyBiz;

	@Override
	public String execute() throws Exception
	{	
		ChannelPolicy condition = new ChannelPolicy();
		condition.setAcademyId(academyId);
		condition.setFeeSubjectId(feeSubjectId);
		condition.setChannelTypeId(channelTypeId);
		if(branchId>0)
		{
			condition.setBranchIds("#"+branchId+"#");
		}
		int total = channelPolciyBiz.countByExample(condition);
		
		result.setRecordCount(total);
		
		return SUCCESS;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public void setChannelTypeId(int channelTypeId) {
		this.channelTypeId = channelTypeId;
	}

	public void setFeeSubjectId(int feeSubjectId) {
		this.feeSubjectId = feeSubjectId;
	}

	public PageResult<ChannelPolicy> getResult() {
		return result;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	
}
