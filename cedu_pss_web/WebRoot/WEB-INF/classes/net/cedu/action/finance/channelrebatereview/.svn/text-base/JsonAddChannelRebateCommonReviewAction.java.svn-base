package net.cedu.action.finance.channelrebatereview;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.ChannelPolicyDetailStandardBiz;
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
 * 单院校返款单计算新增(添加)    2012-05-25重构
 * 
 * @author lixiaojun
 * 
 */
@ParentPackage("json-default")
public class JsonAddChannelRebateCommonReviewAction extends BaseAction
{
	
	@Autowired
	private OrderCeduChannelBiz orderCeduChannelBiz;//招生返款单_业务层接口	
	private OrderCeduChannel orderCeduChannel=new OrderCeduChannel();//招生返款单实体
	
	@Autowired
	private FeePaymentDetailBiz fpdBiz;//缴费单明细_业务层接口
	private FeePaymentDetail feePaymentDetail=new FeePaymentDetail();//缴费单明细实体
	
	@Autowired
	private ChannelPolicyDetailStandardBiz cpdsBiz;//合作方返款标准明细_业务层接口
	
	@Autowired
	private StudentBiz studentBiz;//学生_业务层接口
	
	private Student student;
	
	private String newNeedFpdIds;//缴费单明细ids字符串
	private boolean isback=false;//返回参数
	private boolean isfail=false;//判断是否已经添加过
	
	/**
	 * 添加招生返款单
	 * @return
	 * @throws Exception
	 */
	@Action(value = "add_channel_rebate_common_review_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String apbca() throws Exception 
	{
		if(orderCeduChannel!=null && newNeedFpdIds!=null && newNeedFpdIds.length()>0)
		{
			//判断该合作方是否在招生返款期内已经添加过招生返款单
			OrderCeduChannel occ=new OrderCeduChannel();
			occ.setChannelId(orderCeduChannel.getChannelId());
			occ.setChannelRebateTimeId(orderCeduChannel.getChannelRebateTimeId());
			int count=this.orderCeduChannelBiz.findOrderCeduChannelCountByPage(occ, null, null, null);
			if(count>0)
			{
				isfail=true;
				return SUCCESS;
			}
			else
			{
				//招生返款单 
				orderCeduChannel.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
				orderCeduChannel.setCreatorId(super.getUser().getUserId());
				orderCeduChannel.setDeleteFlag(Constants.DELETE_FALSE);
				orderCeduChannel.setStatus(Constants.PAYMENT_STATUS_YI_TIAN_ZHAO_SHENG_FAN_KUAN);
				orderCeduChannel.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
				orderCeduChannel.setPayDate(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
				orderCeduChannel.setUpdaterId(super.getUser().getUserId());
				orderCeduChannel.setBranchId(getUser().getOrgId());
				orderCeduChannel.setPoliceStatus(Constants.CHANNEL_REBATE_POLICE_STATUS_COMMON);
				orderCeduChannel.setTypes(Constants.FALLBACK_TYPES_SUBMIT);
				orderCeduChannel.setAdjustPaied(orderCeduChannel.getAmountPaied().subtract(orderCeduChannel.getAmountToPay()));
				
				//每个学生第一次填汇款单时如果标准是固定金额则该学生标记为已招生返款（防止第二次添加时又出现这个学生）
				List<Student> stulist=new ArrayList<Student>();//学生列表
				String stuIds="";
				
				//缴费单明细
				List<FeePaymentDetail> fpdlist=new ArrayList<FeePaymentDetail>();
				if(newNeedFpdIds!=null && !newNeedFpdIds.equals(""))
				{
					String[] fpdIds=newNeedFpdIds.split(",");
					for(int i=0;i<fpdIds.length;i++)
					{
						FeePaymentDetail feeObj=this.fpdBiz.findById(Integer.parseInt(fpdIds[i]));
						if(feeObj!=null)
						{
							feeObj.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
							feeObj.setUpdaterId(super.getUser().getUserId());
							//feeObj.setStatus(Constants.PAYMENT_STATUS_YI_TIAN_ZHAO_SHENG_FAN_KUAN);
							feeObj.setRebateStatus(Constants.PAYMENT_STATUS_YI_TIAN_ZHAO_SHENG_FAN_KUAN);
							fpdlist.add(feeObj);
							
							
							//2012-04-18每个学生第一次填汇款单时如果标准是固定金额则该学生标记为已招生返款（防止第二次添加时又出现这个学生）
							Student stu=this.studentBiz.findStudentById(feeObj.getStudentId());
							if(stu!=null && stu.getIsChannelRebate()!=Constants.STUDENT_IS_CHANNEL_REBATE_TRUE)
							{
								if(stuIds!=null && !stuIds.equals(""))
								{
									String zuhefpd=","+stuIds+",";
									if(zuhefpd.indexOf(","+stu.getId()+",")==-1)
									{
										ChannelPolicyDetailStandard cpds=this.cpdsBiz.findChannelPolicyDetailStandardchannelId(feeObj.getChannelPolicyStandardId());
										//标准为固定金额的只返款一次
										if(cpds!=null && cpds.getRebatesId()==Constants.MONEY_FORM_AMOUNT)
										{
											stu.setIsChannelRebate(Constants.STUDENT_IS_CHANNEL_REBATE_TRUE);
										}
										stulist.add(stu);
										stuIds+=","+stu.getId();
										
									}
								}
								else
								{
									ChannelPolicyDetailStandard cpds=this.cpdsBiz.findChannelPolicyDetailStandardchannelId(feeObj.getChannelPolicyStandardId());
									//标准为固定金额的只返款一次
									if(cpds!=null && cpds.getRebatesId()==Constants.MONEY_FORM_AMOUNT)
									{
										stu.setIsChannelRebate(Constants.STUDENT_IS_CHANNEL_REBATE_TRUE);
									}
									stulist.add(stu);
									stuIds+=stu.getId();
								}
							}
						}
						
					}
				}
				isback=this.orderCeduChannelBiz.addOrderCeduChannel(orderCeduChannel, fpdlist, stulist);
			}
		}
		return SUCCESS;
	}

	public OrderCeduChannel getOrderCeduChannel() {
		return orderCeduChannel;
	}

	public void setOrderCeduChannel(OrderCeduChannel orderCeduChannel) {
		this.orderCeduChannel = orderCeduChannel;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getNewNeedFpdIds() {
		return newNeedFpdIds;
	}

	public void setNewNeedFpdIds(String newNeedFpdIds) {
		this.newNeedFpdIds = newNeedFpdIds;
	}

	public boolean isIsback() {
		return isback;
	}

	public void setIsback(boolean isback) {
		this.isback = isback;
	}

	public boolean isIsfail() {
		return isfail;
	}

	public void setIsfail(boolean isfail) {
		this.isfail = isfail;
	}

	public FeePaymentDetail getFeePaymentDetail() {
		return feePaymentDetail;
	}

	public void setFeePaymentDetail(FeePaymentDetail feePaymentDetail) {
		this.feePaymentDetail = feePaymentDetail;
	}
	
	
}
