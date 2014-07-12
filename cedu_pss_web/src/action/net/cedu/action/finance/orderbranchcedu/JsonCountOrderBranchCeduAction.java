package net.cedu.action.finance.orderbranchcedu;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.OrderBranchCeduBiz;
import net.cedu.biz.finance.PaymentBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.string.MoneyUtil;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.OrderBranchCedu;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 汇款总部(中心)_查询数量
 * @author lixiaojun
 *
 */
@ParentPackage("json-default")
public class JsonCountOrderBranchCeduAction extends BaseAction
{
	@Autowired
	private OrderBranchCeduBiz orderBranchCeduBiz;//汇款单_业务层接口
	private OrderBranchCedu order = new OrderBranchCedu();//汇款单实体
	
	private Date startDate;
	private Date endDate;
	
	private PageResult<OrderBranchCedu> result = new PageResult<OrderBranchCedu>();
	
	
	//跟新汇款单状态参数
	@Autowired
	private PaymentBiz paymentBiz; //缴费单_业务层接口
	@Autowired
	private FeePaymentBiz feePaymentBiz; //缴费单_业务层接口
	private int orderId;//汇款单Id
	private int orderStatus;//汇款单状态
	private String amount;//汇款金额
	
	//汇款单回退
	private int orderTypes;//汇款单回退状态
	private boolean isback;//返回参数
	
	//***********  统计汇款的总金额    ********************//
	private String allordermoney;
	
	//**********还原总部确认后的汇款单
	private int count=0;
	
	/**
	 * 汇款单数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_order_branch_cedu_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"result.*,order, startDate, endDate, amount"
	}) })
	public String playmoneyCount() throws Exception 
	{
		result.setRecordCount(this.orderBranchCeduBiz.countOrderBranchCeduByPage(order, startDate, endDate, amount));
		return SUCCESS;
	}
	
	/**
	 * 汇款单集合
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_order_branch_cedu_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"result.*,order, startDate, endDate, amount"
	}) })
	public String playmoneyList() throws Exception 
	{
		List<OrderBranchCedu> list = orderBranchCeduBiz.find(order, startDate, endDate, amount, result);
		
		orderBranchCeduBiz.wrap(list); //查询汇款/收款单位名称

		result.setList(list);
		return SUCCESS;
	}
	
	/**
	 * 统计汇款单总金额
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_order_branch_cedu_all_money_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"allordermoney.*,order,amount,endDate,startDate"
	}) })
	public String playmoneyCountAllMoney() throws Exception 
	{
		SimpleDateFormat sdf =new SimpleDateFormat(Constants.DATE_FORMAT);
		String starttime="";
		String endtime="";
		if(startDate!=null)
		{
			starttime=sdf.format(startDate);
		}
		if(endDate!=null)
		{
			endtime=sdf.format(endDate);
		}
		allordermoney=this.orderBranchCeduBiz.countOrderBranchCeduAllMoneyByConditions(order, starttime, endtime, amount);
		
		return SUCCESS;
	}
	
	/**
	 * 更新汇款单状态(中心确认)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_order_branch_cedu_status_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String updstatusList() throws Exception 
	{
		if(orderId>0)
		{
			OrderBranchCedu order=this.orderBranchCeduBiz.findById(orderId);
			List<FeePaymentDetail> list = paymentBiz.findDetailByOrderBranchCeduId(orderId);
			if(order!=null && list!=null)
			{
				order.setStatus(orderStatus);
				for(FeePaymentDetail fp:list)
				{
					fp.setStatus(orderStatus);
					fp.setUpdaterId(super.getUser().getUserId());
					fp.setUpdatedTime(new Date());
				}
				order.setUpdaterId(super.getUser().getUserId());
				order.setUpdatedTime(new Date());
			}
			this.orderBranchCeduBiz.updateStatus(order, list);
		}
		return SUCCESS;
	}
	
	/**
	 * 更新汇款单状态（总部确认）
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_order_branch_cedu_status_for_cedu_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String updstatusforceduList() throws Exception 
	{
		if(orderId>0)
		{
			OrderBranchCedu order=this.orderBranchCeduBiz.findById(orderId);
			List<FeePaymentDetail> list = paymentBiz.findDetailByOrderBranchCeduId(orderId);//历史缴费单明细集合
			List<FeePaymentDetail> hislist = new ArrayList<FeePaymentDetail>();//退费单明细集合
			if(order!=null && list!=null)
			{
				order.setStatus(Constants.PAYMENT_STATUS_ZONG_BU_QUE_REN);
				order.setUpdaterId(super.getUser().getUserId());
				order.setUpdatedTime(new Date());
				for(FeePaymentDetail fp:list)
				{
					//TODO　fp.setStatus(orderStatus);
					//统计退费明细审批通过但是不可以退费的情况（已经处理过，这种状态下一个缴费单只允许有一条）
					List<FeePaymentDetail> refundlist=this.feePaymentBiz.findRefundPaymentDetailListByFpdIdStartIdEndIdTfStatusId(fp.getId(), Constants.PAYMENT_STATUS_YI_TIAN_HUI_KUAN_DAN, Constants.PAYMENT_STATUS_ZONG_BU_QUE_REN, Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_DAI_QUE_REN);
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
						
						fp.setStatus(Constants.PAYMENT_STATUS_ZONG_BU_QUE_REN);						
						fp.setBranchAccount((new BigDecimal(fp.getBranchAccount()).subtract(new BigDecimal(fp.getPayBranchCedu()))).doubleValue());
						fp.setCeduAccount((new BigDecimal(fp.getCeduAccount()).add(new BigDecimal(fp.getPayBranchCedu()))).doubleValue());
						
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
						//2012-05-09  屏蔽   招生返款需求变更   所有缴费单都必须打款院校才能招生返款
						fp.setRebateStatus(Constants.PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN);//可以开始招生返款
						//总部确认时间和操作相关人员2012-04-01
						fp.setCeduConfirmTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
						fp.setCeduConfirmId(super.getUser().getUserId());
						fp.setUpdaterId(super.getUser().getUserId());
						fp.setUpdatedTime(new Date());
					}
					else
					{
						fp.setStatus(Constants.PAYMENT_STATUS_ZONG_BU_QUE_REN);						
						fp.setBranchAccount((new BigDecimal(fp.getBranchAccount()).subtract(new BigDecimal(fp.getPayBranchCedu()))).doubleValue());
						fp.setCeduAccount((new BigDecimal(fp.getCeduAccount()).add(new BigDecimal(fp.getPayBranchCedu()))).doubleValue());
						//2012-05-09  屏蔽   招生返款需求变更   所有缴费单都必须打款院校才能招生返款
						fp.setRebateStatus(Constants.PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN);//可以开始招生返款
						//总部确认时间和操作相关人员2012-04-01
						fp.setCeduConfirmTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
						fp.setCeduConfirmId(super.getUser().getUserId());
						fp.setUpdaterId(super.getUser().getUserId());
						fp.setUpdatedTime(new Date());
					}
				}
			}
			this.orderBranchCeduBiz.updateOrderBranchCeduFpdListRefundfpdList(order, list, hislist);
		}
		return SUCCESS;
	}
	
	/**
	 * 汇款单回退
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_order_branch_cedu_types_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String updorctypes() throws Exception 
	{
		if(orderId>0)
		{
			OrderBranchCedu order=this.orderBranchCeduBiz.findById(orderId);
			order.setTypes(orderTypes);
			order.setUpdaterId(super.getUser().getUserId());
			order.setUpdatedTime(new Date());
			isback=this.orderBranchCeduBiz.updateOrderBranchCedu(order);
		}
		return SUCCESS;
	}
	
	/**
	 * 还原总部确认后的汇款单
	 * @return
	 * @throws Exception
	 */
	@Action(value = "fallback_order_branch_cedu_status_for_cedu_confirm_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String fallbackdstatusforceduList() throws Exception 
	{
		if(orderId>0)
		{
			boolean isfail=false;
			OrderBranchCedu order=this.orderBranchCeduBiz.findById(orderId);
			List<FeePaymentDetail> list = paymentBiz.findDetailByOrderBranchCeduId(orderId);//历史缴费单明细集合
			if(order!=null && list!=null)
			{
				order.setStatus(Constants.PAYMENT_STATUS_YI_TIAN_HUI_KUAN_DAN);
				order.setTypes(Constants.FALLBACK_TYPES_ROLLED_BACK);
				order.setUpdaterId(super.getUser().getUserId());
				order.setUpdatedTime(new Date());
				for(FeePaymentDetail fpd:list)
				{
					if(fpd.getStatus()==Constants.PAYMENT_STATUS_ZONG_BU_QUE_REN && fpd.getRefundAmount()==0 && fpd.getRefundLock()==Constants.LOCKING_FALSE && fpd.getRebateStatus()<=Constants.PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN)
					{
						//汇款金额(不需要乘以基数)
						fpd.setPayBranchCedu(Double.valueOf(new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount())).toString()));
						
						fpd.setStatus(Constants.PAYMENT_STATUS_YI_TIAN_HUI_KUAN_DAN); //回退一个状态
						fpd.setRebateStatus(Constants.PAYMENT_REBATE_STATUS_CHU_SHI_ZHUANG_TAI);//返款  初始状态
						
						//总部确认时间和操作相关人员
						fpd.setCeduConfirmTime(null);
						fpd.setCeduConfirmId(0);
								
						//账户金额变动   回退到原来状态
						fpd.setBranchAccount((new BigDecimal(fpd.getBranchAccount()).add(new BigDecimal(fpd.getPayBranchCedu()))).doubleValue());
						fpd.setCeduAccount((new BigDecimal(fpd.getCeduAccount()).subtract(new BigDecimal(fpd.getPayBranchCedu()))).doubleValue());			
						
						fpd.setUpdaterId(super.getUser().getUserId());
						fpd.setUpdatedTime(new Date());
					}
					else
					{
						isfail=true;
						if(fpd.getStatus()>Constants.PAYMENT_STATUS_ZONG_BU_QUE_REN)
						{
							count=1;
						}
						else if(fpd.getRefundAmount()>0 || fpd.getRefundLock()==Constants.LOCKING_TRUE)
						{
							count=2;
						}
						else if(fpd.getRebateStatus()>Constants.PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN)
						{
							count=3;
						}
						break;
					}
				}
			}
			if(!isfail)
			{
				isback=this.orderBranchCeduBiz.updateOrderAncFpdStatus(order, list);
			}
		}
		return SUCCESS;
	}
	
	
	public OrderBranchCedu getOrder() {
		return order;
	}

	public void setOrder(OrderBranchCedu order) {
		this.order = order;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public PageResult<OrderBranchCedu> getResult() {
		return result;
	}

	public void setResult(PageResult<OrderBranchCedu> result) {
		this.result = result;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAllordermoney() {
		//转换金额
		allordermoney=MoneyUtil.formatMoney(allordermoney);
		return allordermoney;
	}

	public void setAllordermoney(String allordermoney) {
		this.allordermoney = allordermoney;
	}

	public int getOrderTypes() {
		return orderTypes;
	}

	public void setOrderTypes(int orderTypes) {
		this.orderTypes = orderTypes;
	}

	public boolean isIsback() {
		return isback;
	}

	public void setIsback(boolean isback) {
		this.isback = isback;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	
	
}
