package net.cedu.action.finance.academyrebatereview;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.common.enums.BranchEnum;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.FeeSubject;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 添加院校返款单（重构2012-05-07）
 * 
 * @author xiao
 *
 */
public class AddAcademyRebateReviewAction extends BaseAction
{
	@Autowired
	private AcademyBiz academyBiz;//院校_业务层接口
	
	@Autowired
	private BranchBiz branchBiz;//学习中心_业务层接口
	
	private Branch cedu=new Branch(); //总部实体
	
	private List<Branch> branchlist=new ArrayList<Branch>();//学习中心集合
	private List<Academy> academies=new ArrayList<Academy>();//院校集合
	
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;//费用科目_业务层接口
	private List<FeeSubject> feesubjectlist=new ArrayList<FeeSubject>();//费用科目集合
	
	
	public String execute() throws Exception 
	{
		if(super.isGetRequest())
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
			cedu = branchBiz.findBranchById(BranchEnum.Admin.value());
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

	public Branch getCedu() {
		return cedu;
	}

	public void setCedu(Branch cedu) {
		this.cedu = cedu;
	}

	public List<Academy> getAcademies() {
		return academies;
	}

	public void setAcademies(List<Academy> academies) {
		this.academies = academies;
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
