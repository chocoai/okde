package net.cedu.action.finance.orderbranchcedu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.common.enums.BranchEnum;
import net.cedu.entity.admin.Branch;

/**
 * 第三方支付缴费单明细确认
 * 
 * @author Sauntor
 *
 */
public class ConfirmEBankFpdAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	private List<Branch> branches;
	private Branch cedu;

	@Autowired
	private BranchBiz branchBiz;
	public String execute() throws Exception {
		branches = branchBiz.findBranchForModel();
		cedu = branchBiz.findBranchById(BranchEnum.Admin.value());
		
		return SUCCESS;
	}
	public List<Branch> getBranches() {
		return branches;
	}
	public Branch getCedu() {
		return cedu;
	}
}
