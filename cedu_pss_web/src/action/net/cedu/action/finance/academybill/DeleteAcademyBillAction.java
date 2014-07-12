package net.cedu.action.finance.academybill;

import net.cedu.action.BaseAction;
import net.cedu.biz.finance.PlanedAcademyBillBiz;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 删除应收院校款
 * 
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results({
	@Result(name="success", type="json")
})
public class DeleteAcademyBillAction extends BaseAction
{
	private static final long serialVersionUID = -5396738452608227775L;

	private int billId;
	
	private int errno = 0;
	
	@Autowired
	private PlanedAcademyBillBiz planedAcademyBillBiz;
	
	public String execute() throws Exception
	{
		try {
			planedAcademyBillBiz.deleteById(billId);
		} catch (Exception e) {
			e.printStackTrace();
			errno = -1;
		}
		
		return SUCCESS;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getErrno() {
		return errno;
	}
}
