package net.cedu.action.finance.channelrebatereview;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.ChannelRebateTimeLimitBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.basesetting.GlobalEnrollBatchBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.ChannelRebateTimeLimit;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.basesetting.GlobalEnrollBatch;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 添加招生返款单_单院校（按院校政策匹配）
 * 
 * @author xiao
 *
 */
public class ListChannelRebateHasFpdAction extends BaseAction
{
	
	@Autowired
	private BranchBiz branchBiz; //学习中心_业务层接口
	private Branch branch;
	private List<Branch> branchlist=new ArrayList<Branch>();
	
	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;//招生途径业务接口(合作方类型)
	private List<EnrollmentSource> channeltypelist=new ArrayList<EnrollmentSource>();//招生途径集合
	
	@Autowired 
	private AcademyBiz academyBiz;//院校biz
	private List<Academy> academylist=new ArrayList<Academy>();//院校集合
	
	@Autowired
	private ChannelRebateTimeLimitBiz channelRebateTimeLimitBiz;//招生返款期_业务层接口
	private List<ChannelRebateTimeLimit> channelRebateTimeLimitList=new ArrayList<ChannelRebateTimeLimit>();
	
	@Autowired
	private GlobalEnrollBatchBiz globalEnrollBatchBiz;// 全局批次_业务层接口
	private List<GlobalEnrollBatch> globalBatchList = new ArrayList<GlobalEnrollBatch>();// 全局批次集合
	
	private int viewtype;
	

	public String execute() throws Exception
	{
		if(isGetRequest()){
			branch = branchBiz.findBranchById(getUser().getOrgId());
			academylist=this.academyBiz.findAllAcademyByFlagFalse();
			Collections.sort(academylist, new Comparator() {
				public int compare(Object arg0, Object arg1) {
					Comparator cmp = Collator
							.getInstance(java.util.Locale.CHINA);
					String name1 = ((Academy) arg0).getName();
					String name2 = ((Academy) arg1).getName();
					return cmp.compare(name1, name2);
				}
			});
			branchlist=this.branchBiz.findBranchForModel();
			Collections.sort(branchlist, new Comparator() {
				public int compare(Object arg0, Object arg1) {
					Comparator cmp = Collator
							.getInstance(java.util.Locale.CHINA);
					String name1 = ((Branch) arg0).getName();
					String name2 = ((Branch) arg1).getName();
					return cmp.compare(name1, name2);
				}
			});
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
			
			//全局批次
			globalBatchList = this.globalEnrollBatchBiz.findAllGlobalEnrollBatchByDeleteFlag();
			
			//招生返款期
			channelRebateTimeLimitList=channelRebateTimeLimitBiz.findAllChannelRebateTimeLimit();
			
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

	public List<Academy> getAcademylist() {
		return academylist;
	}

	public void setAcademylist(List<Academy> academylist) {
		this.academylist = academylist;
	}

	public List<ChannelRebateTimeLimit> getChannelRebateTimeLimitList() {
		return channelRebateTimeLimitList;
	}

	public void setChannelRebateTimeLimitList(
			List<ChannelRebateTimeLimit> channelRebateTimeLimitList) {
		this.channelRebateTimeLimitList = channelRebateTimeLimitList;
	}

	public int getViewtype() {
		return viewtype;
	}

	public void setViewtype(int viewtype) {
		this.viewtype = viewtype;
	}

	public List<Branch> getBranchlist() {
		return branchlist;
	}

	public void setBranchlist(List<Branch> branchlist) {
		this.branchlist = branchlist;
	}

	public List<GlobalEnrollBatch> getGlobalBatchList() {
		return globalBatchList;
	}

	public void setGlobalBatchList(List<GlobalEnrollBatch> globalBatchList) {
		this.globalBatchList = globalBatchList;
	}
	
	
}

