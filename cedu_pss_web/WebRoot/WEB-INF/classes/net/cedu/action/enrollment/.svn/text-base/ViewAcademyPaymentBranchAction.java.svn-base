package net.cedu.action.enrollment;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.biz.enrollment.AcademyBranchFeeWayBiz;
import net.cedu.common.Constants;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.BaseDict;
import net.cedu.entity.enrollment.AcademyBranchFeeWay;

import org.springframework.beans.factory.annotation.Autowired;

public class ViewAcademyPaymentBranchAction extends BaseAction
{
	private static final long serialVersionUID = -2403905875159658752L;

	private int academyId;
	private int batchId;
	private int branchId;
	
	private Academy academy;
	private Branch branch;
	
	private List<BaseDict> oldFeeWays; //原有缴费方式
	private List<BaseDict> otherFeeWays; //可选缴费方式
	
	@Autowired
	private BaseDictBiz baseDictBiz;
	@Autowired
	private BranchBiz branchBiz;
	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private AcademyBranchFeeWayBiz academyBranchFeeWayBiz;
	
	@Override
	public String execute() throws Exception
	{
		oldFeeWays = new LinkedList<BaseDict>();
		otherFeeWays = new LinkedList<BaseDict>();
		
		academy = academyBiz.findAcademyById(academyId);
		branch = branchBiz.findBranchById(branchId);
		
		otherFeeWays = baseDictBiz.findAllBaseDictsByTypeAndFlag(Constants.BASEDICT_STYLE_FEEWAY);
		
		List<AcademyBranchFeeWay> abfwList = academyBranchFeeWayBiz.findByBatchAndBranch(batchId, branchId);
		Iterator<AcademyBranchFeeWay> abfwIter = abfwList.iterator();
		while(abfwIter.hasNext())
		{
			AcademyBranchFeeWay abfw = abfwIter.next();
			oldFeeWays.add(baseDictBiz.findBaseDictById(abfw.getFeeWayId()));
		}
		
		Iterator<BaseDict> otherIter = otherFeeWays.iterator();
		while(otherIter.hasNext())
		{
			BaseDict other = otherIter.next();
			
			Iterator<BaseDict> oldIter = oldFeeWays.iterator();
			while(oldIter.hasNext())
			{
				BaseDict old = oldIter.next();
				if(old.getId() == other.getId())
				{
					otherIter.remove();
					break;
				}
			}
		}

		return SUCCESS;
	}

	public Academy getAcademy() {
		return academy;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public List<BaseDict> getOldFeeWays() {
		return oldFeeWays;
	}

	public List<BaseDict> getOtherFeeWays() {
		return otherFeeWays;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}
}
