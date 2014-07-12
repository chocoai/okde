package net.cedu.action.enrollment.chnlplcy;

import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.ChannelPolicyDetailBiz;
import net.cedu.common.Constants;
import net.cedu.entity.enrollment.ChannelPolicyDetail;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 更新招生返款政策，仅改变政策标准（业务要求这么做）
 * 
 * @author Sauntor
 *
 */
@Results({
	@Result(name=SUCCESS, type="redirect", location="audit_list_chnl_plc_dtl?channelId=${channelId}"),
	@Result(name=INPUT, type="redirect", location="audit_view_chnl_plcy_dtl?detailId=${detailId}")
})
public class UpdateChnlPlcyDtlAuditStatusAction extends BaseAction
{
	private static final long serialVersionUID = 970247478512234022L;
	
	private int detailId;
	private int auditStatus; //政策标准ID
	
	private int channelId;

	@Autowired
	private ChannelPolicyDetailBiz channelPolicyDetailBiz;

	public String execute() throws Exception
	{
		ChannelPolicyDetail detail = channelPolicyDetailBiz.findById(detailId);
		
		detail.setAuditStatus(auditStatus);
		if(auditStatus==Constants.POLICY_AUDIT_STATUS_GOOD)
		{
			detail.setEnable(Constants.STATUS_ENABLED);
		}
		detail.setAuditDate(new Date());
		detail.setAuditer(getUser().getUserId());
		
		detail.setUpdatedTime(new Date());
		detail.setUpdaterId(getUser().getUserId());
		
		channelPolicyDetailBiz.update(detail);
		
		channelId = detail.getChannelId();
		
		return SUCCESS;
	}

	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}

	public int getDetailId() {
		return detailId;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}

	public int getChannelId() {
		return channelId;
	}
}
