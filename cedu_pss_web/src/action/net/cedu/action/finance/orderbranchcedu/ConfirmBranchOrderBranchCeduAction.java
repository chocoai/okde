package net.cedu.action.finance.orderbranchcedu;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.common.enums.BranchEnum;
import net.cedu.entity.admin.Branch;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 汇款总部单 中心确认页面
 * 
 * @author Sauntor
 *
 */
public class ConfirmBranchOrderBranchCeduAction extends BaseAction
{
	private static final long serialVersionUID = 4536300447124741256L;

//	private int branchId;
	
	private Branch branch;
	private Branch cedu;

	@Autowired
	private BranchBiz branchBiz;

	public String execute() throws Exception
	{
		branch = branchBiz.findBranchById(getUser().getOrgId());
		cedu = branchBiz.findBranchById(BranchEnum.Admin.value());
		
		setIl8nName("finance_payment");

		return SUCCESS;
	}

	public Branch getBranch() {
		return branch;
	}

	public Branch getCedu() {
		return cedu;
	}

//	public int getBranchId() {
//		return branchId;
//	}

//	public void setBranchId(int branchId) {
//		this.branchId = branchId;
//	}
}
