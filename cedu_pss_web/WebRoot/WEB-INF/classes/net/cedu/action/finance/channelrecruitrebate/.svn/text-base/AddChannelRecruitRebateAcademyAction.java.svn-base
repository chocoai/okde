package net.cedu.action.finance.channelrecruitrebate;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.ChannelRebateTimeLimitBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.common.Constants;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.ChannelRebateTimeLimit;
import net.cedu.entity.basesetting.EnrollmentSource;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 添加招生返款单_单院校（按院校政策匹配）
 * 
 * @author xiao
 *
 */
public class AddChannelRecruitRebateAcademyAction extends BaseAction
{
	
	@Autowired
	private BranchBiz branchBiz; //学习中心_业务层接口
	private Branch branch;
	
	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;//招生途径业务接口(合作方类型)
	private List<EnrollmentSource> channeltypelist=new ArrayList<EnrollmentSource>();//招生途径集合
	
	@Autowired
	private ChannelRebateTimeLimitBiz channelRebateTimeLimitBiz;//	
	private ChannelRebateTimeLimit channelRebateTimeLimit=new ChannelRebateTimeLimit();

	public String execute() throws Exception
	{
		if(isGetRequest()){
			branch = branchBiz.findBranchById(getUser().getOrgId());
			channeltypelist=enrollmentSourceBiz.findAllEnrollmentSourceByIsNeedRebatesAndFlag(Constants.ISNEED_REBATES_TRUE);
//			List<EnrollmentSource> list = enrollmentSourceBiz.findAllEnrollmentSourceByIsNeedRebatesAndFlag(Constants.ISNEED_REBATES_TRUE);
//			for(EnrollmentSource e : list)
//			{
//				if(e.getId() == Constants.WEB_STU_LAO_SHENG_XU_DU)
//				{
//					continue;
//				}
//				channeltypelist.add(e);
//			}
			//总部到账时间
			channelRebateTimeLimit=this.channelRebateTimeLimitBiz.findChannelRebateTimeLimitById(Constants.CHANNEL_REBATE_TIME_LIMIT_CONFIRM);
			return INPUT;
		}
		
		return SUCCESS;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public List<EnrollmentSource> getChanneltypelist() {
		return channeltypelist;
	}

	public void setChanneltypelist(List<EnrollmentSource> channeltypelist) {
		this.channeltypelist = channeltypelist;
	}

	public ChannelRebateTimeLimit getChannelRebateTimeLimit() {
		return channelRebateTimeLimit;
	}

	public void setChannelRebateTimeLimit(
			ChannelRebateTimeLimit channelRebateTimeLimit) {
		this.channelRebateTimeLimit = channelRebateTimeLimit;
	}

	
	
}

