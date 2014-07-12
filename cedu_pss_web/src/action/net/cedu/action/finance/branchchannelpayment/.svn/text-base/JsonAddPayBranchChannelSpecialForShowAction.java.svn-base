package net.cedu.action.finance.branchchannelpayment;

import net.cedu.action.BaseAction;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.common.Constants;
import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 招生返款（中心）显示可以返款的缴费单明细     __特殊合作方返款
 * @author xiao
 *
 */
@ParentPackage("json-default")
public class JsonAddPayBranchChannelSpecialForShowAction extends BaseAction
{
	
	// 分页结果
	private PageResult<FeePaymentDetail> result = new PageResult<FeePaymentDetail>();
	
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;//缴费单明细_业务层接口
	
	private Student student=new Student(); //学生实体
	
	private int minorChannelType;//次合作方类型
	
	private int minorChannelId;//次合作方Id
	
	
	/**
	 * 获取适合招生返款的缴费单明细数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_channel_rebate_special_fpd_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String channelRebateCount() throws Exception 
	{
		FeePaymentDetail feePaymentDetail=new FeePaymentDetail();
		//feePaymentDetail.setStatus(Constants.PAYMENT_STATUS_FAN_KUAN_QUE_REN);
		feePaymentDetail.setRebateStatus(Constants.PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN);
		student.setStartStatusId(Constants.STU_CALL_STATUS_YI_DAO_MING);
		result.setRecordCount(this.feePaymentDetailBiz.findFpdCountByPageForChannelRebateSpecial(feePaymentDetail, student, minorChannelType, minorChannelId));
		return SUCCESS;
	}

	/**
	 * 获取适合招生返款的缴费单明细列表
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_channel_rebate_special_fpd_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String channelRebateList() throws Exception 
	{	
		FeePaymentDetail feePaymentDetail=new FeePaymentDetail();
		//feePaymentDetail.setStatus(Constants.PAYMENT_STATUS_FAN_KUAN_QUE_REN);
		feePaymentDetail.setRebateStatus(Constants.PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN);
		student.setStartStatusId(Constants.STU_CALL_STATUS_YI_DAO_MING);
		result.setList(this.feePaymentDetailBiz.findFpdListByPageForChannelRebateSpecial(feePaymentDetail, student, minorChannelType, minorChannelId, result));
		return SUCCESS;
	}

	public PageResult<FeePaymentDetail> getResult() {
		return result;
	}

	public void setResult(PageResult<FeePaymentDetail> result) {
		this.result = result;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getMinorChannelType() {
		return minorChannelType;
	}

	public void setMinorChannelType(int minorChannelType) {
		this.minorChannelType = minorChannelType;
	}

	public int getMinorChannelId() {
		return minorChannelId;
	}

	public void setMinorChannelId(int minorChannelId) {
		this.minorChannelId = minorChannelId;
	}
	
	
}
