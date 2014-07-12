package net.cedu.action.finance.branchchannelpayment;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.finance.OrderCeduChannelBiz;
import net.cedu.biz.finance.PaymentBiz;
import net.cedu.common.Constants;
import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 查询 与院校返款单对应的院校已返款的打款单
 * @author Sauntor
 */
@ParentPackage("json-default")
@Results({
	@Result(name="success", type="json")
})
public class JsonListFeePaymentDetailForPbcAction extends BaseAction implements ModelDriven<Student>
{
	private static final long serialVersionUID = -8777217537270089138L;

	private int payCeduAcademyId;
	
	private Student model = new Student();
	
	PageResult<FeePaymentDetail> result = new PageResult<FeePaymentDetail>();
	
//	@Autowired
//	private AcademyBiz academyBiz;
//	@Autowired
//	private BranchBiz branchBiz;
//	@Autowired
//	private PayCeduAcademyBiz payCeduAcademyBiz;
	@Autowired
	private PaymentBiz paymentBiz;
	@Autowired
	private OrderCeduChannelBiz orderCeduChannelBiz;
	
	public String execute() throws Exception
	{
		model.setBranchId(getUser().getOrgId());
		
		//TODO paymentBiz.findFeePaymentDetailsPageList 的用法
		FeePaymentDetail feePaymentDetail = new FeePaymentDetail();
		feePaymentDetail.setStatus(Constants.PAYMENT_STATUS_FAN_KUAN_QUE_REN);
		
		List<FeePaymentDetail> fpdList = 
			paymentBiz.findFeePaymentDetailsPageList(feePaymentDetail, null, model, result);
		
		paymentBiz.wrapFeePaymentDetail(fpdList);
		
		if(model.getMajorId() != -1){
			orderCeduChannelBiz.calcMoneyToChannel(model, fpdList);
		}
		
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

	public Student getModel() {
		return model;
	}
}
