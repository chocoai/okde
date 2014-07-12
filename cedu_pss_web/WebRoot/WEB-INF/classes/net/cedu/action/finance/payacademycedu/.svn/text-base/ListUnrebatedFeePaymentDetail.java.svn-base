package net.cedu.action.finance.payacademycedu;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.cedu.action.BaseAction;
import net.cedu.biz.finance.PayCeduAcademyBiz;
import net.cedu.biz.finance.PaymentBiz;
import net.cedu.entity.enrollment.AcademyRebatePolicyDetail;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.PayCeduAcademy;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 查询指定打款单下缴费单应返款情况
 * 
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results({
	@Result(name="success", type="json")
})
public class ListUnrebatedFeePaymentDetail extends BaseAction
{
	private static final long serialVersionUID = 1276354386142777489L;

	private int payCeduAcademyId; //院校打款单ID
	
	PageResult<FeePaymentDetail> result = new PageResult<FeePaymentDetail>();
	
//	@Autowired
//	private AcademyBiz academyBiz;
//	@Autowired
//	private BranchBiz branchBiz;
	@Autowired
	private PayCeduAcademyBiz payCeduAcademyBiz;
	@Autowired
	private PaymentBiz paymentBiz;
	
	public String execute() throws Exception
	{
		PayCeduAcademy pca = payCeduAcademyBiz.findById(payCeduAcademyId);
				
//		// 收款单位为院校
//		Academy academy = academyBiz.findAcademyById(pca.getRemitteeId());
//		pca.setRemitteeName(academy.getName());
//		
//		// 汇款单位为学习中心或总部
//		Branch branch = branchBiz.findBranchById(pca.getRemitterId());
//		pca.setRemitterName(branch.getName());
		
		// 计算应返款金额
		Map<AcademyRebatePolicyDetail, List<FeePaymentDetail>> groups = payCeduAcademyBiz.calculateMoneyToCedu(pca);
		
		List<FeePaymentDetail> fpdList = new LinkedList<FeePaymentDetail>();
		
		if(groups != null) {
			Iterator<AcademyRebatePolicyDetail> policyIter = groups.keySet().iterator();
			while(policyIter.hasNext()){
				AcademyRebatePolicyDetail policy = policyIter.next();
				fpdList.addAll(groups.get(policy));
			}
		}
		
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
