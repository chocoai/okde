package net.cedu.action.finance.playmoney;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.common.enums.BranchEnum;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.basesetting.Level;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * POS直汇院校    缴费单明细总部确认
 * 
 * @author xiao
 *
 */
public class ConfirmPosAcademyFpdAction extends BaseAction
{
	
	private List<Branch> branches;
	private Branch cedu;
	
	@Autowired
	private AcademyBiz academyBiz;//院校_业务层接口
	private List<Academy> academyList=new ArrayList<Academy>();//院校集合
	
	@Autowired
	private LevelBiz levelBiz;//层次_业务层接口
	private List<Level> levelList=new ArrayList<Level>();//层次集合
	
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;//费用科目biz
	private List<FeeSubject> feesubjectlist=new ArrayList<FeeSubject>();//费用科目集合

	@Autowired
	private BranchBiz branchBiz;
	
	public String execute() throws Exception 
	{
		//学习中心
		branches = branchBiz.findBranchForModel();
		Collections.sort(branches, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				Comparator cmp = Collator
						.getInstance(java.util.Locale.CHINA);
				String name1 = ((Branch) arg0).getName();
				String name2 = ((Branch) arg1).getName();
				return cmp.compare(name1, name2);
			}
		});
		//总部
		cedu = branchBiz.findBranchById(BranchEnum.Admin.value());
		//院校集合
		academyList=this.academyBiz.findAllAcademyByFlagFalse();
		Collections.sort(academyList, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				Comparator cmp = Collator
						.getInstance(java.util.Locale.CHINA);
				String name1 = ((Academy) arg0).getName();
				String name2 = ((Academy) arg1).getName();
				return cmp.compare(name1, name2);
			}
		});
		//层次集合
		levelList=this.levelBiz.findAllLevelsByDeleteFlag();
		//费用科目
		feesubjectlist=this.feeSubjectBiz.findAllFeeSubjectByDeleteFlag();
		
		return SUCCESS;
	}

	public List<Branch> getBranches() {
		return branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}

	public Branch getCedu() {
		return cedu;
	}

	public void setCedu(Branch cedu) {
		this.cedu = cedu;
	}

	public List<Academy> getAcademyList() {
		return academyList;
	}

	public void setAcademyList(List<Academy> academyList) {
		this.academyList = academyList;
	}

	public List<Level> getLevelList() {
		return levelList;
	}

	public void setLevelList(List<Level> levelList) {
		this.levelList = levelList;
	}

	public List<FeeSubject> getFeesubjectlist() {
		return feesubjectlist;
	}

	public void setFeesubjectlist(List<FeeSubject> feesubjectlist) {
		this.feesubjectlist = feesubjectlist;
	}
	
	
}
