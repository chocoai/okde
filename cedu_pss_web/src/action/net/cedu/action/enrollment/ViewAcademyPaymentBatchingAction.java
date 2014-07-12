package net.cedu.action.enrollment;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.biz.enrollment.AcademyBatchBranchBiz;
import net.cedu.common.Constants;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.BaseDict;

import org.springframework.beans.factory.annotation.Autowired;

public class ViewAcademyPaymentBatchingAction extends BaseAction
{
	private static final long serialVersionUID = 2358875502644165668L;

	private int academyId;
	private int batchId;
	
	private Academy academy;
	private List<Branch> branches;
	
//	private List<BaseDict> oldFeeWays; //原有缴费方式
	private List<BaseDict> otherFeeWays; //可选缴费方式
	
	@Autowired
	private BaseDictBiz baseDictBiz;
	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private AcademyBatchBranchBiz academyBatchBrachBiz;
	
	@Override
	public String execute() throws Exception
	{
		academy = academyBiz.findAcademyById(academyId);
		branches = academyBatchBrachBiz.findGrantedBranch(academyId, batchId);
		
		otherFeeWays = baseDictBiz.findAllBaseDictsByTypeAndFlag(Constants.BASEDICT_STYLE_FEEWAY);
		
		return SUCCESS;
	}

	public Academy getAcademy() {
		return academy;
	}

	public List<Branch> getBranches() {
		return branches;
	}

	public List<BaseDict> getOtherFeeWays() {
		return otherFeeWays;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}
}
