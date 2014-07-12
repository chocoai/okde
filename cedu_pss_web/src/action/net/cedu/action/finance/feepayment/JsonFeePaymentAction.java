package net.cedu.action.finance.feepayment;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.common.string.StringUtil;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * json收据授权
 * @author gaolei
 *
 */
@ParentPackage("json-default")
public class JsonFeePaymentAction extends BaseAction {

	
	@Autowired
	private FeePaymentBiz feePaymentBiz;                  //缴费单biz
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;
	
	
	
	

	// 缴费单集合
	private List<FeePayment> feePayments = new ArrayList<FeePayment>();
	
	// 分页结果
	private PageResult<FeePaymentDetail> result = new PageResult<FeePaymentDetail>();
	
	private String feePaymentIds;                           //缴费单Ids

	
	
	
	
	/**
	 * 缴费单集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "listfeepaymentids", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String ListFeePaymentIds() throws Exception {
		//result.setList(feePaymentBiz.findFeePaymentByFeePaymentIds(feePaymentIds, result));
		Object[] feepaymentids=StringUtil.strToObject(feePaymentIds);
		if(feepaymentids!=null&&feepaymentids.length!=0){
			String ids=",";
			for (Object object : feepaymentids) {
				if(object!=null){
					if(ids.equals(",")){
						ids=object.toString();
					}else{
						ids+=","+object.toString();
					}
				}
			}
			List<FeePaymentDetail> feePaymentDetailList=feePaymentDetailBiz.findFeePaymentDetailByIds(ids);
			if(feePaymentDetailList!=null){
				for (FeePaymentDetail feePaymentDetail : feePaymentDetailList) {
					//费用ID
					int feeSubjectId=feePaymentDetail.getFeeSubjectId();
					FeeSubject feesubject=feeSubjectBiz.findFeeSubjectById(feeSubjectId);
					feePaymentDetail.setPaymentSubjectName(feesubject!=null?feesubject.getName():"");
					FeePayment feePayment=feePaymentBiz.findFeePaymentById(feePaymentDetail.getFeePaymentId());
					feePaymentDetail.setPaymentCode(feePayment!=null?feePayment.getCode():"");
				}
			}
			
			result.setList(feePaymentDetailList);
		}
		
		return SUCCESS;
	}

	public List<FeePayment> getFeePayments() {
		return feePayments;
	}

	public void setFeePayments(List<FeePayment> feePayments) {
		this.feePayments = feePayments;
	}

	
	public PageResult<FeePaymentDetail> getResult() {
		return result;
	}

	public void setResult(PageResult<FeePaymentDetail> result) {
		this.result = result;
	}

	public String getFeePaymentIds() {
		return feePaymentIds;
	}

	public void setFeePaymentIds(String feePaymentIds) {
		this.feePaymentIds = feePaymentIds;
	}
	
}
