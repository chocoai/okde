package net.cedu.action.finance.refund;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.entity.admin.Branch;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 院校替总部垫付退费(院校确认)
 * 
 * @author dongminghao
 * 
 */
public class ListRefundPaymentAcademyCeduAcademyAction extends BaseAction{
	@Autowired
	private BranchBiz branchBiz;// 学习中心业务接口
	private List<Branch> branchlist=new ArrayList<Branch>();//学习中心集合

	@Override
	public String execute() throws Exception {
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
		return super.execute();
	}

	public List<Branch> getBranchlist() {
		return branchlist;
	}

	public void setBranchlist(List<Branch> branchlist) {
		this.branchlist = branchlist;
	}
	
}
