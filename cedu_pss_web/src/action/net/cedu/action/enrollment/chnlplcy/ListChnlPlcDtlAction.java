package net.cedu.action.enrollment.chnlplcy;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.ChannelPolicyDetailBiz;
import net.cedu.biz.enrollment.ChannelPolicyDetailBiz.WrapType;
import net.cedu.entity.enrollment.ChannelPolicyDetail;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
@Results({
	@Result(name="success", type="json")
})
public class ListChnlPlcDtlAction extends BaseAction
{
	private static final long serialVersionUID = 2562857272027524989L;
	private int channelId;
	private int branchId;
	private int academyId;
	private int batchId;
	private int levelId;
	private int majorId;
	private int feeSubjectId;
	
	// 分页结果
	private PageResult<ChannelPolicyDetail> result = new PageResult<ChannelPolicyDetail>();

	@Autowired
	private ChannelPolicyDetailBiz channelPolicyDetailBiz;
	
	public String execute() throws Exception
	{
		List<ChannelPolicyDetail> list = channelPolicyDetailBiz.findForChannel(channelId, branchId, academyId, batchId, levelId, majorId, feeSubjectId,-1, result);
		
		channelPolicyDetailBiz.wrapList(list, WrapType.ToName);
		
		result.setList(list);
		
		return SUCCESS;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public PageResult<ChannelPolicyDetail> getResult() {
		return result;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public void setMajorId(int majorId) {
		this.majorId = majorId;
	}

	public void setFeeSubjectId(int feeSubjectId) {
		this.feeSubjectId = feeSubjectId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
}
