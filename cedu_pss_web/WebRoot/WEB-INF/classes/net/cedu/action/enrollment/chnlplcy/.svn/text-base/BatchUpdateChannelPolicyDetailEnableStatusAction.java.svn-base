package net.cedu.action.enrollment.chnlplcy;

import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.ChannelPolicyDetailBiz;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 更新招生返款政策，仅改变政策标准（业务要求这么做）
 * 
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results({
	@Result(name=SUCCESS, type="json"),
	@Result(name=INPUT, type="json")
})
public class BatchUpdateChannelPolicyDetailEnableStatusAction extends BaseAction
{
	private static final long serialVersionUID = 970247478512234022L;
	
	private int[] detailIds;
	private int auditStatus;

	@Autowired
	private ChannelPolicyDetailBiz channelPolicyDetailBiz;

	public String execute() throws Exception
	{
		channelPolicyDetailBiz.updateAuditStatusBatched(detailIds, auditStatus, getUser().getUserId());
		
		return SUCCESS;
	}

	public void setDetailIds(int[] detailIds) {
		this.detailIds = detailIds;
	}

	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}
}
