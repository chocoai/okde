package net.cedu.action.finance.orderbranchcedu;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.common.enums.BranchEnum;
import net.cedu.entity.admin.Branch;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 汇款总部单总部确认页面
 * 
 * @author Sauntor
 *
 */
public class ConfirmCeduOrderBranchCeduAction extends BaseAction
{
	private static final long serialVersionUID = -2539727459044143834L;
	private List<Branch> branches;
	private Branch cedu;

	@Autowired
	private BranchBiz branchBiz;

	public String execute() throws Exception
	{
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
		cedu = branchBiz.findBranchById(BranchEnum.Admin.value());
		
		setIl8nName("finance_payment");

		return SUCCESS;
	}

	public List<Branch> getBranches() {
		return branches;
	}

	public Branch getCedu() {
		return cedu;
	}
}
