package net.cedu.action.finance.channelrebatereview;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.AreaManagerBranchBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.common.Constants;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.EnrollmentSource;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 招生返款_渠道审核
 * 
 * @author xiao
 *
 */
public class QuOrderCeduChannelReviewAction extends BaseAction
{
	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz; //招生途径_业务层接口
	@Autowired
	private BranchBiz branchBiz;//学习中心_业务层接口
	@Autowired
	private AreaManagerBranchBiz areaManagerBranchBiz;//区域经理_业务层接口
	
	private List<EnrollmentSource> channelTypes=new ArrayList<EnrollmentSource>();
	private List<Branch> branchList=new ArrayList<Branch>();//学习中心_集合
	
	private int areaManagerId;//渠道经理ID
	
	
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
			areaManagerId=super.getUser().getUserId();
			branchList = this.areaManagerBranchBiz.findBranchListByManagerId(super.getUser().getUserId());
			if(branchList!=null && branchList.size()>0)
			{
				Collections.sort(branchList, new Comparator() {
					public int compare(Object arg0, Object arg1) {
						Comparator cmp = Collator
								.getInstance(java.util.Locale.CHINA);
						String name1 = ((Branch) arg0).getName();
						String name2 = ((Branch) arg1).getName();
						return cmp.compare(name1, name2);
					}
				});
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

	public List<Branch> getBranchList() {
		return branchList;
	}

	public void setBranchList(List<Branch> branchList) {
		this.branchList = branchList;
	}

	public int getAreaManagerId() {
		return areaManagerId;
	}

	public void setAreaManagerId(int areaManagerId) {
		this.areaManagerId = areaManagerId;
	}

	
}

