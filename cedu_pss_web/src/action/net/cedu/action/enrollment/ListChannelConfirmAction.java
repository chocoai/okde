package net.cedu.action.enrollment;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.common.Constants;
import net.cedu.common.enums.BranchEnum;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.EnrollmentSource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 合作方审批
 * @author lixiaojun
 *
 */

public class ListChannelConfirmAction extends BaseAction {

	@Autowired 
	private BranchBiz branchBiz;                            //学习中心biz
	private List<Branch> branchlist=new ArrayList<Branch>();//学习中心集合
	
	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;        //招生途径biz
	private List<EnrollmentSource> feelist=new ArrayList<EnrollmentSource>();//招生途径集合

	public String execute() throws Exception 
	{
		//执行get请求
		if(super.isGetRequest())
		{	
			feelist=enrollmentSourceBiz.findAllEnrollmentSourceByIsNeedRebatesAndFlag(Constants.ISNEED_REBATES_TRUE);
//			List<EnrollmentSource> list = enrollmentSourceBiz.findAllEnrollmentSourceByIsNeedRebatesAndFlag(Constants.ISNEED_REBATES_TRUE);
//			for(EnrollmentSource e : list)
//			{
//				if(e.getId() == Constants.WEB_STU_LAO_SHENG_XU_DU)
//				{
//					continue;
//				}
//				feelist.add(e);
//			}
			Collections.sort(feelist, new Comparator() {
				public int compare(Object arg0, Object arg1) {
					Comparator cmp = Collator
							.getInstance(java.util.Locale.CHINA);
					String name1 = ((EnrollmentSource) arg0).getName();
					String name2 = ((EnrollmentSource) arg1).getName();
					return cmp.compare(name1, name2);
				}
			});
			branchlist=branchBiz.findBranchForModel();
			Collections.sort(branchlist, new Comparator() {
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

	public List<Branch> getBranchlist() {
		return branchlist;
	}

	public void setBranchlist(List<Branch> branchlist) {
		this.branchlist = branchlist;
	}

	public List<EnrollmentSource> getFeelist() {
		return feelist;
	}

	public void setFeelist(List<EnrollmentSource> feelist) {
		this.feelist = feelist;
	}
	
	
}
