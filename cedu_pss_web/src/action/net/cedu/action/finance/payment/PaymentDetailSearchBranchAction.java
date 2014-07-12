package net.cedu.action.finance.payment;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.basesetting.GlobalEnrollBatchBiz;
import net.cedu.common.Constants;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.BaseDict;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.basesetting.GlobalEnrollBatch;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 缴费单明细查询(中心)
 * 
 * @author xiao
 *
 */
public class PaymentDetailSearchBranchAction extends BaseAction
{
	
	@Autowired
	private BranchBiz branchBiz; //学习中心_业务层接口
	private Branch branch=new Branch();
	
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;//费用科目biz
	private List<FeeSubject> feesubjectlist=new ArrayList<FeeSubject>();//费用科目集合
	
	@Autowired
	private GlobalEnrollBatchBiz globalEnrollBatchBiz;// 全局批次_业务层接口
	private List<GlobalEnrollBatch> globalBatchList = new ArrayList<GlobalEnrollBatch>();// 全局批次集合
	
	@Autowired
	private BaseDictBiz baseDictBiz;//基础设置_业务层接口
	private List<BaseDict> feeWayList=new ArrayList<BaseDict>();//缴费方式集合
	
	
	public String execute() throws Exception
	{
		if(isGetRequest())
		{
			//学习中心	
			branch=this.branchBiz.findBranchById(super.getUser().getOrgId());
			//费用科目
			feesubjectlist=this.feeSubjectBiz.findAllFeeSubjectByDeleteFlag();
			//全局批次
			globalBatchList = this.globalEnrollBatchBiz.findAllGlobalEnrollBatchByDeleteFlag();
			//缴费方式
			feeWayList=baseDictBiz.findAllBaseDictsByTypeAndFlag(Constants.BASEDICT_STYLE_FEEWAY);
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

	public List<FeeSubject> getFeesubjectlist() {
		return feesubjectlist;
	}

	public void setFeesubjectlist(List<FeeSubject> feesubjectlist) {
		this.feesubjectlist = feesubjectlist;
	}

	public List<GlobalEnrollBatch> getGlobalBatchList() {
		return globalBatchList;
	}

	public void setGlobalBatchList(List<GlobalEnrollBatch> globalBatchList) {
		this.globalBatchList = globalBatchList;
	}

	public List<BaseDict> getFeeWayList() {
		return feeWayList;
	}

	public void setFeeWayList(List<BaseDict> feeWayList) {
		this.feeWayList = feeWayList;
	}
	
	
	
}
