package net.cedu.action.finance.channelrebate;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.common.Constants;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.EnrollmentSource;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 添加招生返款单_多院校（按院校政策匹配）
 * 
 * @author xiao
 *
 */
public class AddChannelRebateCommonAction extends BaseAction
{
	
	@Autowired
	private BranchBiz branchBiz; //学习中心_业务层接口
	private Branch branch;
	
	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;//招生途径业务接口(合作方类型)
	private List<EnrollmentSource> channeltypelist=new ArrayList<EnrollmentSource>();//招生途径集合
	
	@Autowired 
	private AcademyBiz academyBiz;//院校biz
	private List<Academy> academylist=new ArrayList<Academy>();//院校集合
	

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
	
}


