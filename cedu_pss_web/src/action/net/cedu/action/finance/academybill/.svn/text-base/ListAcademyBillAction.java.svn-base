package net.cedu.action.finance.academybill;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.common.Constants;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.BaseDict;
import net.cedu.entity.finance.PlanedAcademyBill;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 应收院校款  列表查询
 * 
 * @author Sauntor
 *
 */
public class ListAcademyBillAction extends BaseAction implements ModelDriven<PlanedAcademyBill>
{
	private static final long serialVersionUID = -7297588660617461934L;

	private PlanedAcademyBill planedAcademyBill = new PlanedAcademyBill();
	
	private List<Academy> academies;
	private List<BaseDict> billWays;

	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private BaseDictBiz baseDictBiz;
	
	@Autowired
	private BranchBiz branchBiz; //学习中心_业务层接口
	private List<Branch> branchlist=new ArrayList<Branch>();

	public String execute() throws Exception
	{
		academies = academyBiz.findAllAcademyByFlagFalse();
		Collections.sort(academies, new Comparator() {
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
		billWays = baseDictBiz.findAllBaseDictsByTypeAndFlag(Constants.BASEDICT_STYLE_BILLRECEIVEDWAY);
		
		return SUCCESS;
	}

	public List<Academy> getAcademies() {
		return academies;
	}

	public List<BaseDict> getReceivedWays() {
		return billWays;
	}

	public PlanedAcademyBill getModel() {
		return planedAcademyBill;
	}

	public List<Branch> getBranchlist() {
		return branchlist;
	}

	public void setBranchlist(List<Branch> branchlist) {
		this.branchlist = branchlist;
	}
	
}
