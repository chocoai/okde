package net.cedu.action.finance.channelrebatereview;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.common.Constants;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyLevel;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.OrderCeduChannel;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 招生返款申请（中心）显示可以返款的缴费单明细  2012-05-24 重构
 * 
 * @author xiao
 *
 */
@ParentPackage("json-default")
public class JsonAddChannelRebateAcademyReviewShowAction extends BaseAction
{
	
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;//缴费单明细_业务层接口
	
	// 分页结果
	private PageResult<FeePaymentDetail> result = new PageResult<FeePaymentDetail>();
	
	private String feepdIds;//选中的缴费单明细Ids字符串集合
	
	private Student student=new Student(); //学生实体
	private FeePaymentDetail feePaymentDetail=new FeePaymentDetail();//缴费单明细实体	
	
	private OrderCeduChannel orderCeduChannel=new OrderCeduChannel();//招生返款单实体
	
	@Autowired
	private AcademyLevelBiz academyLevelBiz;//院校招生批次与层次的关系
	private AcademyLevel academyLevel=new AcademyLevel();//院校招生批次与层次的关系
	
	/**
	 * 获取适合招生返款的缴费单明细数量_重构_2012-05-24
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_channel_rebate_review_fpd_academy_review_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String channelRebateReviewCount() throws Exception 
	{
		feePaymentDetail.setRebateStatus(Constants.PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN);
		feePaymentDetail.setStartStatusId(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);
		student.setStartStatusId(Constants.STU_CALL_STATUS_YI_DAO_MING);
		//转换为基础的层次id
		if(student.getLevelId()!=0)
		{
			academyLevel=this.academyLevelBiz.findById(student.getLevelId());
			if(academyLevel!=null)
			{
				student.setLevelId(academyLevel.getLevelId());
			}
			else
			{
				student.setLevelId(0);
			}
		}
		result.setRecordCount(this.feePaymentDetailBiz.findfpdCountByPageForChannelRecruitRebateAcademyReview(feePaymentDetail, student,feepdIds));
		return SUCCESS;
	}

	/**
	 * 获取适合招生返款的缴费单明细列表_重构_2012-05-24
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_channel_rebate_review_fpd_academy_review_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String channelRebateReviewList() throws Exception 
	{			
		feePaymentDetail.setRebateStatus(Constants.PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN);
		feePaymentDetail.setStartStatusId(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);
		student.setStartStatusId(Constants.STU_CALL_STATUS_YI_DAO_MING);
		//转换为基础的层次id
		if(student.getLevelId()!=0)
		{
			academyLevel=this.academyLevelBiz.findById(student.getLevelId());
			if(academyLevel!=null)
			{
				student.setLevelId(academyLevel.getLevelId());
			}
			else
			{
				student.setLevelId(0);
			}
		}
		result.setList(this.feePaymentDetailBiz.findfpdListByPageForChannelRecruitRebateAcademyReview(feePaymentDetail, student,feepdIds, result));
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

	public OrderCeduChannel getOrderCeduChannel() {
		return orderCeduChannel;
	}

	public void setOrderCeduChannel(OrderCeduChannel orderCeduChannel) {
		this.orderCeduChannel = orderCeduChannel;
	}

	public String getFeepdIds() {
		return feepdIds;
	}

	public void setFeepdIds(String feepdIds) {
		this.feepdIds = feepdIds;
	}

	public FeePaymentDetail getFeePaymentDetail() {
		return feePaymentDetail;
	}

	public void setFeePaymentDetail(FeePaymentDetail feePaymentDetail) {
		this.feePaymentDetail = feePaymentDetail;
	}
	
	
	
}
