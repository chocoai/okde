package net.cedu.action.finance.channelrecruitrebate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.ChannelRebateTimeLimitBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.ChannelRebateTimeLimit;
import net.cedu.entity.basesetting.EnrollmentSource;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 招生返款(中心)
 * 
 * @author xiao
 *
 */
public class ListChannelRecruitRebateAction extends BaseAction
{
	
	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;//招生途径
	private List<EnrollmentSource> channelTypes=new ArrayList<EnrollmentSource>();
	
	@Autowired
	private BranchBiz branchBiz;//学习中心
	private Branch branch;
	
	@Autowired
	private ChannelRebateTimeLimitBiz channelRebateTimeLimitBiz;//	招生返款期
	private ChannelRebateTimeLimit channelRebateTimeLimit=new ChannelRebateTimeLimit();
	private boolean isback=true;
		
	
	public String execute() throws Exception
	{
		if(isGetRequest())
		{
			channelTypes = enrollmentSourceBiz.findAllEnrollmentSourceByIsNeedRebatesAndFlag(Constants.ISNEED_REBATES_TRUE);
//			List<EnrollmentSource> list = enrollmentSourceBiz.findAllEnrollmentSourceByIsNeedRebatesAndFlag(Constants.ISNEED_REBATES_TRUE);
//			for(EnrollmentSource e : list)
//			{
//				if(e.getId() == Constants.WEB_STU_LAO_SHENG_XU_DU)
//				{
//					continue;
//				}
//				channelTypes.add(e);
//			}
			branch = branchBiz.findBranchById(getUser().getOrgId());
			//招生返款期判断
			channelRebateTimeLimit=this.channelRebateTimeLimitBiz.findChannelRebateTimeLimitById(Constants.CHANNEL_REBATE_TIME_LIMIT_PEROID);
			if(channelRebateTimeLimit!=null && channelRebateTimeLimit.getEndTime()!=null)
			{
//				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				String endtime=channelRebateTimeLimit.getEndTime().toString().substring(0,10)+" 23:59:59";
				if(channelRebateTimeLimit.getStartTime().before(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss")) && channelRebateTimeLimit.getEndTime().after(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss")))
				{
					isback=false;
				}
			}
			return INPUT;
		} 
		return SUCCESS;
	}

	public List<EnrollmentSource> getChannelTypes() {
		return channelTypes;
	}

	public void setChannelTypes(List<EnrollmentSource> channelTypes) {
		this.channelTypes = channelTypes;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public boolean isIsback() {
		return isback;
	}

	public void setIsback(boolean isback) {
		this.isback = isback;
	}

	public ChannelRebateTimeLimit getChannelRebateTimeLimit() {
		return channelRebateTimeLimit;
	}

	public void setChannelRebateTimeLimit(
			ChannelRebateTimeLimit channelRebateTimeLimit) {
		this.channelRebateTimeLimit = channelRebateTimeLimit;
	}
	
	
}
