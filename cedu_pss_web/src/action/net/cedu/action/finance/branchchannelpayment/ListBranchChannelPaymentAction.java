package net.cedu.action.finance.branchchannelpayment;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.common.Constants;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.EnrollmentSource;

import org.springframework.beans.factory.annotation.Autowired;

public class ListBranchChannelPaymentAction extends BaseAction
{

	private List<EnrollmentSource> channelTypes=new ArrayList<EnrollmentSource>();
	private Branch branch;
	
	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;
	@Autowired
	private BranchBiz branchBiz;
	
	public String execute() throws Exception
	{
		if(isGetRequest()){
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
	
	
}
