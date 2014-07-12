package net.cedu.action.finance.branchchannelpayment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.ChannelPolicyDetailStandardBiz;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.OrderCeduChannelBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.ChannelPolicyDetailStandard;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.OrderCeduChannel;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 招生返款单更新
 * 
 * @author lixiaojun
 * 
 */
@ParentPackage("json-default")
public class JsonUpdateOrderCeduChannelAction extends BaseAction
{
	
	@Autowired
	private OrderCeduChannelBiz orderCeduChannelBiz;//合作方返款单_业务层接口
	
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;//缴费单明细_业务层接口
	
	@Autowired
	private FeePaymentBiz feePaymentBiz;//缴费单_业务接口
	
	@Autowired
	private ChannelPolicyDetailStandardBiz channelPolicyDetailStandardBiz;  //招生返款政策明细 业务接口
	
	@Autowired
	private StudentBiz studentBiz;//学生_业务层接口
	
	private int status;//状态
	private int orderCeduChannelId;//招生返款单Id
	private boolean isback=false;//返回参数
	
	/**
	 * 更新招生返款单
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_pay_branch_channel_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String upPayBranchChannelList() throws Exception 
	{	
		if(orderCeduChannelId!=0 && status!=0)
		{
			OrderCeduChannel orderCeduChannel=this.orderCeduChannelBiz.findById(orderCeduChannelId);
			List<FeePaymentDetail> fpdlist= feePaymentDetailBiz.findByOrderCeduChannelId(orderCeduChannelId);
			if(orderCeduChannel!=null && fpdlist!=null && fpdlist.size()>0)
			{
				orderCeduChannel.setStatus(status);
				for(FeePaymentDetail fpd:fpdlist)
				{
					//fpd.setStatus(status);
					fpd.setRebateStatus(status);
				}
				isback=this.orderCeduChannelBiz.updateOrderCeduChannel(orderCeduChannel, fpdlist);
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 更新招生返款单(hr汇款渠道)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_hr_pay_branch_channel_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String upPayHrBranchChannel() throws Exception 
	{	
		if(orderCeduChannelId!=0)
		{
			OrderCeduChannel orderCeduChannel=this.orderCeduChannelBiz.findById(orderCeduChannelId);
			List<FeePaymentDetail> fpdlist= feePaymentDetailBiz.findByOrderCeduChannelId(orderCeduChannelId);
			
			List<FeePaymentDetail> hislist = new ArrayList<FeePaymentDetail>();//退费单明细集合
			
			//每个学生第一次汇款渠道时要更新学生信息绑定招生返款标准
			List<Student> stulist=new ArrayList<Student>();//学生列表
			String stuIds="";
			
			if(orderCeduChannel!=null && fpdlist!=null && fpdlist.size()>0)
			{
				orderCeduChannel.setStatus(Constants.PAYMENT_STATUS_YI_HUI_KUAN_QU_DAO);
				for(FeePaymentDetail fp:fpdlist)
				{
					
					//统计退费明细审批通过但是不可以退费的情况（已经处理过，这种状态下一个缴费单只允许有一条）
					List<FeePaymentDetail> refundlist=this.feePaymentBiz.findRefundPaymentDetailListByFpdIdStartIdEndIdTfStatusId(fp.getId(), Constants.PAYMENT_STATUS_YI_TIAN_ZHAO_SHENG_FAN_KUAN, Constants.PAYMENT_STATUS_YI_HUI_KUAN_QU_DAO, Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_DAI_QUE_REN);
					if(refundlist!=null && refundlist.size()>0)
					{
						
						BigDecimal allrefundmon=new BigDecimal(0);
//						for(FeePaymentDetail rfpd:refundlist)
//						{
//							allrefundmon=allrefundmon.add(new BigDecimal(rfpd.getAmountPaied()));
//							//还原历史数据
//							fp.setCeduAccount((new BigDecimal(fp.getCeduAccount()).subtract(new BigDecimal(rfpd.getPayAcademyCedu()))).doubleValue());
//							fp.setAcademyAccount((new BigDecimal(fp.getAcademyAccount()).subtract(new BigDecimal(rfpd.getPayCeduAcademy()))).doubleValue());
//							fp.setChannelAccount((new BigDecimal(fp.getChannelAccount()).subtract(new BigDecimal(rfpd.getPaymentByChannel()))).doubleValue());
//							fp.setBranchAccount((new BigDecimal(fp.getBranchAccount()).subtract(new BigDecimal(rfpd.getPayBranchCedu()))).doubleValue());
//							
//						}
						FeePaymentDetail rfpd=refundlist.get(0);
						//还原历史数据
						fp.setCeduAccount((new BigDecimal(fp.getCeduAccount()).subtract(new BigDecimal(rfpd.getCeduAccount()))).doubleValue());
						fp.setAcademyAccount((new BigDecimal(fp.getAcademyAccount()).subtract(new BigDecimal(rfpd.getAcademyAccount()))).doubleValue());
						fp.setChannelAccount((new BigDecimal(fp.getChannelAccount()).subtract(new BigDecimal(rfpd.getChannelAccount()))).doubleValue());
						fp.setBranchAccount((new BigDecimal(fp.getBranchAccount()).subtract(new BigDecimal(rfpd.getBranchAccount()))).doubleValue());
						
						//fp.setStatus(Constants.PAYMENT_STATUS_YI_HUI_KUAN_QU_DAO);
						fp.setRebateStatus(Constants.PAYMENT_STATUS_YI_HUI_KUAN_QU_DAO);
						fp.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
						fp.setUpdaterId(super.getUser().getUserId());
						fp.setCeduAccount((new BigDecimal(fp.getCeduAccount()).subtract(new BigDecimal(fp.getPaymentByChannel()))).doubleValue());
						fp.setChannelAccount((new BigDecimal(fp.getChannelAccount()).add(new BigDecimal(fp.getPaymentByChannel()))).doubleValue());
						
						//重新计算退费的值
						if(new BigDecimal(rfpd.getRefundBase()).compareTo(new BigDecimal(0))==0)
						{
							rfpd.setRefundBase(1);
						}
						rfpd.setCeduAccount(0-((new BigDecimal(fp.getCeduAccount()).multiply(new BigDecimal(0-rfpd.getAmountPaied())).divide(new BigDecimal(rfpd.getRefundBase()),2,BigDecimal.ROUND_HALF_UP)).doubleValue()));
						rfpd.setAcademyAccount(0-((new BigDecimal(fp.getAcademyAccount()).multiply(new BigDecimal(0-rfpd.getAmountPaied())).divide(new BigDecimal(rfpd.getRefundBase()),2,BigDecimal.ROUND_HALF_UP)).doubleValue()));
						rfpd.setChannelAccount(0-((new BigDecimal(fp.getChannelAccount()).multiply(new BigDecimal(0-rfpd.getAmountPaied())).divide(new BigDecimal(rfpd.getRefundBase()),2,BigDecimal.ROUND_HALF_UP)).doubleValue()));
						rfpd.setBranchAccount(0-((new BigDecimal(fp.getBranchAccount()).multiply(new BigDecimal(0-rfpd.getAmountPaied())).divide(new BigDecimal(rfpd.getRefundBase()),2,BigDecimal.ROUND_HALF_UP)).doubleValue()));
						rfpd.setStatus(Constants.PAYMENT_STATUS_YI_TUI_FEI_QUE_REN);
						rfpd.setUpdaterId(super.getUser().getUserId());
						rfpd.setUpdatedTime(new Date());
						hislist.add(rfpd);
						//更新历史缴费单(重新计算各个账户的值)
						fp.setCeduAccount((new BigDecimal(fp.getCeduAccount()).add(new BigDecimal(rfpd.getCeduAccount()))).doubleValue());
						fp.setAcademyAccount((new BigDecimal(fp.getAcademyAccount()).add(new BigDecimal(rfpd.getAcademyAccount()))).doubleValue());
						fp.setChannelAccount((new BigDecimal(fp.getChannelAccount()).add(new BigDecimal(rfpd.getChannelAccount()))).doubleValue());
						fp.setBranchAccount((new BigDecimal(fp.getBranchAccount()).add(new BigDecimal(rfpd.getBranchAccount()))).doubleValue());
						fp.setRefundLock(Constants.LOCKING_FALSE);//解除原始缴费单的锁定
						
					}
					else
					{
						//fp.setStatus(Constants.PAYMENT_STATUS_YI_HUI_KUAN_QU_DAO);
						fp.setRebateStatus(Constants.PAYMENT_STATUS_YI_HUI_KUAN_QU_DAO);
						fp.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
						fp.setUpdaterId(super.getUser().getUserId());
						fp.setCeduAccount((new BigDecimal(fp.getCeduAccount()).subtract(new BigDecimal(fp.getPaymentByChannel()))).doubleValue());
						fp.setChannelAccount((new BigDecimal(fp.getChannelAccount()).add(new BigDecimal(fp.getPaymentByChannel()))).doubleValue());
					}
					
					//每个学生第一次汇款渠道时要更新学生信息绑定招生返款标准
					Student stu=this.studentBiz.findStudentById(fp.getStudentId());
					if(stu!=null && stu.getChannelPolicyStandardLock()!=Constants.LOCKING_TRUE)
					{
						if(stuIds!=null && !stuIds.equals(""))
						{
							String zuhefpd=","+stuIds+",";
							if(zuhefpd.indexOf(","+stu.getId()+",")==-1)
							{
								stu.setChannelPolicyStandardId(fp.getChannelPolicyStandardId());
								stu.setChannelPolicyStandardLock(Constants.LOCKING_TRUE);
								ChannelPolicyDetailStandard cpds=this.channelPolicyDetailStandardBiz.findChannelPolicyDetailStandardchannelId(fp.getChannelPolicyStandardId());
								//标准为固定金额的只返款一次
								if(cpds!=null && cpds.getRebatesId()==Constants.MONEY_FORM_AMOUNT)
								{
									stu.setIsChannelRebate(Constants.STUDENT_IS_CHANNEL_REBATE_TRUE);
								}
								//合作方类型为老带新的  只返款一次
								if(stu.getEnrollmentSource()==Constants.WEB_STU_ENROLLMENT_SOURCE)
								{
									stu.setIsChannelRebate(Constants.STUDENT_IS_CHANNEL_REBATE_TRUE);
								}
								stulist.add(stu);
								stuIds+=","+stu.getId();
								
							}
						}
						else
						{
							stu.setChannelPolicyStandardId(fp.getChannelPolicyStandardId());
							stu.setChannelPolicyStandardLock(Constants.LOCKING_TRUE);
							ChannelPolicyDetailStandard cpds=this.channelPolicyDetailStandardBiz.findChannelPolicyDetailStandardchannelId(fp.getChannelPolicyStandardId());
							//标准为固定金额的只返款一次
							if(cpds!=null && cpds.getRebatesId()==Constants.MONEY_FORM_AMOUNT)
							{
								stu.setIsChannelRebate(Constants.STUDENT_IS_CHANNEL_REBATE_TRUE);
							}
							//合作方类型为老带新的  只返款一次
							if(stu.getEnrollmentSource()==Constants.WEB_STU_ENROLLMENT_SOURCE)
							{
								stu.setIsChannelRebate(Constants.STUDENT_IS_CHANNEL_REBATE_TRUE);
							}
							stu.setUpdaterId(super.getUser().getUserId());
							stu.setModifiedTime(new Date());
							stulist.add(stu);
							stuIds+=stu.getId();
						}
					}
					
				}
				//把退费单和缴费单明细整合一起（防止类似错误）
				if(fpdlist!=null && fpdlist.size()>0)
				{
					if(hislist!=null && hislist.size()>0)
					{
						for(FeePaymentDetail rfpd:hislist)
						{
							fpdlist.add(rfpd);
						}
					}
				}
				
				isback=this.orderCeduChannelBiz.updateOrderCeduChannel(orderCeduChannel, fpdlist,stulist);
			}
		}
		return SUCCESS;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getOrderCeduChannelId() {
		return orderCeduChannelId;
	}

	public void setOrderCeduChannelId(int orderCeduChannelId) {
		this.orderCeduChannelId = orderCeduChannelId;
	}

	public boolean isIsback() {
		return isback;
	}

	public void setIsback(boolean isback) {
		this.isback = isback;
	}
	
}
