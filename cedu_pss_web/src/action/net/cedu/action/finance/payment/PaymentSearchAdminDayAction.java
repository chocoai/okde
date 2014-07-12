package net.cedu.action.finance.payment;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.common.date.DateUtil;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.FeeSubject;

/**
 * 日收款单查询(总部)
 * 
 * @author xiao
 * 
 */
public class PaymentSearchAdminDayAction extends BaseAction
{
	
	@Autowired
	private BranchBiz branchBiz; //学习中心_业务层接口
	private List<Branch> branchlist=new ArrayList<Branch>();
	
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;//费用科目biz
	private List<FeeSubject> feesubjectlist=new ArrayList<FeeSubject>();//费用科目集合
	
	public String execute() throws Exception
	{
		if(isGetRequest())
		{
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
			feesubjectlist=this.feeSubjectBiz.findAllFeeSubjectByDeleteFlag();
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

	public List<FeeSubject> getFeesubjectlist() {
		return feesubjectlist;
	}

	public void setFeesubjectlist(List<FeeSubject> feesubjectlist) {
		this.feesubjectlist = feesubjectlist;
	}
	
}

