package net.cedu.action.basesetting.channelrebatetimelimit;

import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.ChannelRebateTimeLimitBiz;
import net.cedu.common.Constants;
import net.cedu.entity.basesetting.ChannelRebateTimeLimit;

/**
 * 基础设置_招生返款时间限定（返款期）
 * 
 * @author xiao
 *
 */
public class ListChannelRebateTimeLimitPeriodAction extends BaseAction
{
	
	@Autowired
	private ChannelRebateTimeLimitBiz channelRebateTimeLimitBiz;//	
	private ChannelRebateTimeLimit channelRebateTimeLimit=new ChannelRebateTimeLimit();
	private int viewtype;//用于判断是否是当前选中页面跳转action;
	
	public String execute() throws Exception
	{
		if(isGetRequest())
		{
			channelRebateTimeLimit=this.channelRebateTimeLimitBiz.findChannelRebateTimeLimitById(Constants.CHANNEL_REBATE_TIME_LIMIT_PEROID);
			return INPUT;
		} 
		return SUCCESS;
	}

	public ChannelRebateTimeLimit getChannelRebateTimeLimit() {
		return channelRebateTimeLimit;
	}

	public void setChannelRebateTimeLimit(
			ChannelRebateTimeLimit channelRebateTimeLimit) {
		this.channelRebateTimeLimit = channelRebateTimeLimit;
	}

	public int getViewtype() {
		return viewtype;
	}

	public void setViewtype(int viewtype) {
		this.viewtype = viewtype;
	}
	
	
}
