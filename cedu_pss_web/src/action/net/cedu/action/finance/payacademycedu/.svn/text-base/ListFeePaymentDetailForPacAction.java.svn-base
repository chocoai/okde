package net.cedu.action.finance.payacademycedu;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.finance.PaymentBiz;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 查询 与院校返款单对应的院校已返款的打款单
 * @author Sauntor
 */
@ParentPackage("json-default")
@Results({
	@Result(name="success", type="json")
})
public class ListFeePaymentDetailForPacAction extends BaseAction //implements ModelDriven<PayCeduAcademy>
{
	private static final long serialVersionUID = -8777217537270089138L;

	private int payCeduAcademyId;
	
//	private PayCeduAcademy model = new PayCeduAcademy();
	
	PageResult<FeePaymentDetail> result = new PageResult<FeePaymentDetail>();
	
//	@Autowired
//	private AcademyBiz academyBiz;
//	@Autowired
//	private BranchBiz branchBiz;
//	@Autowired
//	private PayCeduAcademyBiz payCeduAcademyBiz;
	@Autowired
	private PaymentBiz paymentBiz;
	
	public String execute() throws Exception
	{
		List<FeePaymentDetail> fpdList = 
			paymentBiz.findDetailByOrderCeduAcademyId(payCeduAcademyId);
		
		paymentBiz.wrapFeePaymentDetail(fpdList);
		
		result.setList(fpdList);
		
		return SUCCESS;
	}

	public PageResult<FeePaymentDetail> getResult() {
		return result;
	}

	public int getPayCeduAcademyId() {
		return payCeduAcademyId;
	}

	public void setPayCeduAcademyId(int payCeduAcademyId) {
		this.payCeduAcademyId = payCeduAcademyId;
	}
}
