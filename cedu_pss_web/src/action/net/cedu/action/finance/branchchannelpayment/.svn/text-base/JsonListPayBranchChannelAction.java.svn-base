package net.cedu.action.finance.branchchannelpayment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.ChannelPolicyDetailStandardBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.OrderCeduChannelBiz;
import net.cedu.common.Constants;
import net.cedu.common.string.MoneyUtil;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.ChannelPolicyDetailStandard;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.OrderCeduChannel;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 招生返款单查询
 * 
 * @author lixiaojun
 * 
 */
@ParentPackage("json-default")
public class JsonListPayBranchChannelAction extends BaseAction
{
	
	@Autowired
	private OrderCeduChannelBiz orderCeduChannelBiz;//合作方返款单_业务层接口
	
	// 分页结果
	private PageResult<OrderCeduChannel> result = new PageResult<OrderCeduChannel>();
	
	private OrderCeduChannel orderCeduChannel=new OrderCeduChannel();//返款单实体
	private String starttime;//汇款起始时间
	private String endtime;//汇款结束时间
	private String amount;//实返金额
	
	//删除招生返款单
	@Autowired
	private ChannelPolicyDetailStandardBiz cpdsBiz;//合作方返款标准明细_业务层接口	
	@Autowired
	private StudentBiz studentBiz;//学生_业务层接口
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;
	private int orderCeduChannelId;//招生返款单Id
	private boolean isback=false;//返回参数
	
	//回退招生返款单
	private int orderId;//招生返款单Id
	private int orderTypes;//招生返款单回退状态
	private boolean isfail=false;//返回参数
	
	//***********  统计招生返款的总金额    ********************//
	private String allchannelmoney;
	
	/**
	 * 招生返款单数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_pay_branch_channel_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"result.*,orderCeduChannel, starttime, endtime, amount"
	}) })
	public String channelForNowCount() throws Exception 
	{
		result.setRecordCount(this.orderCeduChannelBiz.findOrderCeduChannelCountByPage(orderCeduChannel, starttime, endtime, amount));
		return SUCCESS;
	}
	
	/**
	 * 招生返款单列表
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_pay_branch_channel_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String channelForNowList() throws Exception 
	{	
		result.setList(this.orderCeduChannelBiz.findOrderCeduChannelListByPage(orderCeduChannel, starttime, endtime, amount, result));
		return SUCCESS;
	}
	
	/**
	 * 删除招生返款单
	 * @return
	 * @throws Exception
	 */
	@Action(value = "del_pay_branch_channel_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String delPayBranchChannelList() throws Exception 
	{	
		if(orderCeduChannelId!=0)
		{
			//由于每个学生第一次填汇款单时如果标准是固定金额则该学生标记为已招生返款，所以删除时要释放该学生
			List<Student> stulist=new ArrayList<Student>();//学生列表
			String stuIds="";
			List<FeePaymentDetail> fpdlist= feePaymentDetailBiz.findByOrderCeduChannelId(orderCeduChannelId);
			if(fpdlist!=null && fpdlist.size()>0)
			{
				for(FeePaymentDetail fpd:fpdlist)
				{
					//fpd.setStatus(Constants.PAYMENT_STATUS_FAN_KUAN_QUE_REN);
					fpd.setRebateStatus(Constants.PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN);
					fpd.setOrderCeduChannelId(0);
					fpd.setChannelRebateCount(0);//本次招生返款该院校该批次总人数
					fpd.setChannelRebateTimeId(0);//本次招生返款期Id
					
					//2012-04-18每个学生第一次天汇款单时如果标准是固定金额则该学生标记为已招生返款（防止第二次添加时又出现这个学生）
					Student stu=this.studentBiz.findStudentById(fpd.getStudentId());
					if(stu!=null && stu.getIsChannelRebate()==Constants.STUDENT_IS_CHANNEL_REBATE_TRUE)
					{
						if(stuIds!=null && !stuIds.equals(""))
						{
							String zuhefpd=","+stuIds+",";
							if(zuhefpd.indexOf(","+stu.getId()+",")==-1)
							{
								ChannelPolicyDetailStandard cpds=this.cpdsBiz.findChannelPolicyDetailStandardchannelId(fpd.getChannelPolicyStandardId());
								//标准为固定金额的只返款一次
								if(cpds!=null && cpds.getRebatesId()==Constants.MONEY_FORM_AMOUNT)
								{
									stu.setIsChannelRebate(Constants.STUDENT_IS_CHANNEL_REBATE_FALSE);
								}
								stulist.add(stu);
								stuIds+=","+stu.getId();
								
							}
						}
						else
						{
							ChannelPolicyDetailStandard cpds=this.cpdsBiz.findChannelPolicyDetailStandardchannelId(fpd.getChannelPolicyStandardId());
							//标准为固定金额的只返款一次
							if(cpds!=null && cpds.getRebatesId()==Constants.MONEY_FORM_AMOUNT)
							{
								stu.setIsChannelRebate(Constants.STUDENT_IS_CHANNEL_REBATE_FALSE);
							}
							stulist.add(stu);
							stuIds+=stu.getId();
						}
					}
					
				}
				isback=this.orderCeduChannelBiz.deleteOrderCeduChannel(orderCeduChannelId, fpdlist, stulist);
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 回退招生返款单
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_pay_branch_channel_types_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String upPayBranchChannelTypes() throws Exception 
	{	
		OrderCeduChannel orderCeduChannel=this.orderCeduChannelBiz.findById(orderId);
		if(orderCeduChannel!=null)
		{
			orderCeduChannel.setTypes(orderTypes);
			orderCeduChannel.setUpdaterId(super.getUser().getUserId());
			orderCeduChannel.setUpdatedTime(new Date());
			isfail=this.orderCeduChannelBiz.updateOrderCeduChannel(orderCeduChannel);
		}
		return SUCCESS;
	}


	
	/**
	 * 统计招生返款单总金额
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_order_cedu_channel_all_money_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String playmoneyCountAllMoney() throws Exception 
	{
		
		allchannelmoney=this.orderCeduChannelBiz.countOrderCeduChannelAllMoneyByConditions(orderCeduChannel, starttime, endtime, amount);
		if(allchannelmoney==null || allchannelmoney.equals(""))
		{
			allchannelmoney="0";
		}
		return SUCCESS;
	}
	
	public PageResult<OrderCeduChannel> getResult() {
		return result;
	}

	public void setResult(PageResult<OrderCeduChannel> result) {
		this.result = result;
	}

	public OrderCeduChannel getOrderCeduChannel() {
		return orderCeduChannel;
	}

	public void setOrderCeduChannel(OrderCeduChannel orderCeduChannel) {
		this.orderCeduChannel = orderCeduChannel;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
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

	public String getAllchannelmoney() {
		//转换金额
		allchannelmoney=MoneyUtil.formatMoney(allchannelmoney);
		return allchannelmoney;
	}

	public void setAllchannelmoney(String allchannelmoney) {
		this.allchannelmoney = allchannelmoney;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public boolean isIsfail() {
		return isfail;
	}

	public void setIsfail(boolean isfail) {
		this.isfail = isfail;
	}

	public int getOrderTypes() {
		return orderTypes;
	}

	public void setOrderTypes(int orderTypes) {
		this.orderTypes = orderTypes;
	}
	
	
	
}
