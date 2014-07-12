package net.cedu.action.finance.channelrebatereview;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.finance.OrderCeduChannelBiz;
import net.cedu.common.Constants;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.finance.OrderCeduChannel;
import net.cedu.model.page.PageResult;

/**
 * 招生返款 之 财务审核 页面
 * @author Sauntor
 *
 */

public class FaOrderCeduChannelReviewAction extends BaseAction
{
	private List<EnrollmentSource> channelTypes=new ArrayList<EnrollmentSource>();
	private List<Branch> branchList=new ArrayList<Branch>();//学习中心_集合
	
	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;
	@Autowired
	private BranchBiz branchBiz;
	
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
			branchList = this.branchBiz.findBranchForModel();
			Collections.sort(branchList, new Comparator() {
				public int compare(Object arg0, Object arg1) {
					Comparator cmp = Collator
							.getInstance(java.util.Locale.CHINA);
					String name1 = ((Branch) arg0).getName();
					String name2 = ((Branch) arg1).getName();
					return cmp.compare(name1, name2);
				}
			});
			
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

	public List<Branch> getBranchList() {
		return branchList;
	}

	public void setBranchList(List<Branch> branchList) {
		this.branchList = branchList;
	}
	
}
