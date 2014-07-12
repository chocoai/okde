package net.cedu.action.finance.academybill;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.biz.finance.PlanedAcademyBillBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.BaseDict;
import net.cedu.entity.finance.PlanedAcademyBill;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 应收院校款详情
 * 
 * @author Sauntor
 *
 */
public class ViewAcademyBillAction extends BaseAction
{
	private static final long serialVersionUID = -4028717788401412463L;

	private int billId;
	
	private PlanedAcademyBill planedAcademyBill;
	
	@Autowired
	private PlanedAcademyBillBiz planedAcademyBillBiz;
	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private BaseDictBiz baseDictBiz;
	@Autowired
	private BranchBiz branchBiz; //学习中心_业务层接口
	
	public String execute() throws Exception
	{
		planedAcademyBill = planedAcademyBillBiz.findById(billId);
		if(planedAcademyBill!=null)
		{		
			Academy academy = academyBiz.findAcademyById(planedAcademyBill.getAcademyId());
			planedAcademyBill.setAcademyName(academy.getName());
			
			BaseDict receivedWay = baseDictBiz.findBaseDictById(planedAcademyBill.getReceivedWay());
			planedAcademyBill.setReceivedWayName(receivedWay.getName());
			
			Branch branch=this.branchBiz.findBranchById(planedAcademyBill.getBranchId());
			if(branch!=null)
			{
				planedAcademyBill.setBranchName(branch.getName());
			}
		}
		
		return SUCCESS;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public PlanedAcademyBill getPlanedAcademyBill() {
		return planedAcademyBill;
	}
}
