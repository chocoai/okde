package net.cedu.action.enrollment.chnlplcy;

import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.ChannelPolicyDetailBiz;
import net.cedu.entity.enrollment.ChannelPolicyDetail;

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
public class UpdateChannelPolicyDetailEnableStatusAction extends BaseAction
{
	private static final long serialVersionUID = 970247478512234022L;
	
	private int detailId;
	private int enable; //政策标准ID

	@Autowired
	private ChannelPolicyDetailBiz channelPolicyDetailBiz;

	public String execute() throws Exception
	{
		ChannelPolicyDetail detail = channelPolicyDetailBiz.findById(detailId);
		
		detail.setEnable(enable);
		
		detail.setUpdatedTime(new Date());
		detail.setUpdaterId(getUser().getUserId());
		
		channelPolicyDetailBiz.update(detail);
		
		return SUCCESS;
	}

	public int getDetailId() {
		return detailId;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}
}
