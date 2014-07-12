package net.cedu.biz.finance.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.ChannelBiz;
import net.cedu.biz.enrollment.ChannelPolicyDetailBiz;
import net.cedu.biz.enrollment.ChannelPolicyDetailStandardBiz;
import net.cedu.biz.finance.ChannelBatchRecruitRebateStandardBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.OrderCeduChannelBiz;
import net.cedu.common.Constants;
import net.cedu.common.enums.CodeEnum;
import net.cedu.dao.enrollment.ChannelDao;
import net.cedu.dao.finance.FeePaymentDetailDao;
import net.cedu.dao.finance.OrderCeduChannelDao;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.Channel;
import net.cedu.entity.enrollment.ChannelPolicyDetail;
import net.cedu.entity.enrollment.ChannelPolicyDetailStandard;
import net.cedu.entity.finance.ChannelBatchRecruitRebateStandard;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.OrderCeduChannel;
import net.cedu.entity.finance.PayCeduAcademy;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 招生返款业务实现
 * 
 * @author Sauntor
 *
 */
@Service
public class OrderCeduChannelBizImpl implements OrderCeduChannelBiz {
	@Autowired
	private OrderCeduChannelDao orderCeduChannelDao;
	@Autowired
	private FeePaymentDetailDao feePaymentDetailDao;
	@Autowired
	private ChannelPolicyDetailBiz channelPolicyDetailBiz;
	@Autowired
	private ChannelPolicyDetailStandardBiz channelPolicyDetailStandardBiz;
	@Autowired
	private BuildCodeBiz buildCodeBiz;
	@Autowired
	private ChannelDao channelDao;
	
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;//缴费单明细_业务层接口
	
	@Autowired
	private ChannelBiz channelBiz;//合作方_业务层接口
	
	@Autowired
	private BranchBiz branchBiz;//学习中心_业务层接口
	
	@Autowired
	private StudentBiz studentBiz;//学生_业务层接口
	
	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;//招生途径_业务层接口
	
	@Autowired
	private ChannelBatchRecruitRebateStandardBiz cbrrsBiz;//合作方绑定招生返款标准关系_业务层接口

	/*
	 * 添加招生返款单
	 * 
	 * @see net.cedu.biz.finance.OrderCeduChannelBiz#add(net.cedu.biz.finance.OrderCeduChannelBiz, int[])
	 */
	public void add(OrderCeduChannel order, double adjustedAmount, Student condition, int[] fpdIds) throws Exception {
		List<FeePaymentDetail> fpdList = new LinkedList<FeePaymentDetail>();
		for(int fpdId : fpdIds){
			fpdList.add(feePaymentDetailDao.findById(fpdId));
		}
		
		CalcResult calcResult = calcMoneyToChannel(condition, fpdList);
		
		String code = buildCodeBiz.getCodes(CodeEnum.OrderCeduChannelTB.getCode(), CodeEnum.OrderCeduChannel.getCode());
		
		order.setCode(code);
		
		Date now = new Date();
		order.setUpdatedTime(now);
		order.setCreatedTime(now);
		order.setPayDate(now);
		
		order.setAmountToPay(calcResult.getAmount());
		order.setAmountPaied(calcResult.getAmount().subtract(new BigDecimal(adjustedAmount))); //TODO 调整金额为正时代表减少返款？
		
		order.setStatus(Constants.PAYMENT_STATUS_YI_TIAN_ZHAO_SHENG_FAN_KUAN);
		
		orderCeduChannelDao.save(order);
		
		for(List<FeePaymentDetail> fpds : calcResult.getGroups().values()){
			for(FeePaymentDetail fpd : fpds){
				fpd.setOrderCeduChannelId(order.getId());
				fpd.setStatus(Constants.PAYMENT_STATUS_YI_TIAN_ZHAO_SHENG_FAN_KUAN);
				feePaymentDetailDao.update(fpd);
			}
		}
	}

	/*
	 * 根据招生返款政策计算合作方应返款金额
	 * 
	 * @see net.cedu.biz.finance.OrderCeduChannelBiz#calcMoneyToChannel(net.cedu.entity.crm.Student, java.util.List)
	 */
	public CalcResult calcMoneyToChannel(Student condition, List<FeePaymentDetail> fpdList) throws Exception
	{
		BigDecimal total = new BigDecimal(0);
		
		Map<Integer, List<FeePaymentDetail>> groups = new HashMap<Integer, List<FeePaymentDetail>>();
		for(FeePaymentDetail fpd : fpdList){
			List<FeePaymentDetail> fpdGroup = groups.get(fpd.getFeeSubjectId());;
			
			if(fpdGroup == null){
				fpdGroup = new LinkedList<FeePaymentDetail>();
				groups.put(fpd.getFeeSubjectId(), fpdGroup);
			}
			
			fpdGroup.add(fpd);
		}
		
		Iterator<Integer> keyIter = groups.keySet().iterator();
		while(keyIter.hasNext()){
			Integer fsId = keyIter.next();
			List<FeePaymentDetail> fpdGroup = groups.get(fsId);
			
			ChannelPolicyDetail policyDetail = channelPolicyDetailBiz.findByStudent(condition, fsId);

			if(policyDetail != null){
				List<ChannelPolicyDetailStandard> stdList = channelPolicyDetailStandardBiz.findByPolicyId(policyDetail.getPolicyId());
				
				int count = fpdGroup.size();
				
				ChannelPolicyDetailStandard matchedStd = null;
				
				for(ChannelPolicyDetailStandard std : stdList){
					if(std.getEnrollmentCeil()>=count && std.getEnrollmentFloor()<=count){
						matchedStd = std;
						break;
					}
				}
				
				if(matchedStd != null){
					for(FeePaymentDetail fpd : fpdGroup){
						double amount = 0;
						if(matchedStd.getRebatesId()==Constants.MONEY_FORM_RATIO)
							amount = fpd.getPayBranchCedu() * (matchedStd.getValue() / 100d);
						else if(matchedStd.getRebatesId()==Constants.MONEY_FORM_AMOUNT){
							amount = fpd.getPayBranchCedu() + matchedStd.getValue();
						}
						
						fpd.setMoneyToChannel(amount);
						fpd.setPaymentByChannel(amount);
						
						total = total.add(new BigDecimal(amount));
					}
				}
			}
		}
		
		return new CalcResult(total, groups);
	}
	
	/*
	 * 招生返单查询
	 * 
	 * @see net.cedu.biz.finance.OrderCeduChannelBiz#find(int, int, int, int)
	 */
	public List<OrderCeduChannel> find(int branchId, int channelType, int channelId, int status)
	{
		StringBuilder hql = new StringBuilder();
		hql.append(" and 1=").append(Constants.PLACEHOLDER);
		
		if(channelType != -1){
			hql.append(" and channelType=").append(channelType);
		}
		
		if(channelId != -1){
			hql.append(" and channelId=").append(channelId);
		}
		if(status == -1){
			hql.append(" and status>=").append(Constants.PAYMENT_STATUS_YI_TIAN_ZHAO_SHENG_FAN_KUAN);
			hql.append(" and status<=").append(Constants.PAYMENT_STATUS_YI_HUI_KUAN_QU_DAO);
		} else {
			hql.append(" and status=").append(status);
		}
		
//		hql.append(" and deleteFlag=").append(Constants.DELETE_FALSE);
		
		List<OrderCeduChannel> list = orderCeduChannelDao.getByProperty(hql.toString(), new Object[]{ 1 });
		
		if(list != null){
			Iterator<OrderCeduChannel> iter = list.iterator();
			while(iter.hasNext()){
				OrderCeduChannel order = iter.next();
				if(channelDao.findById(order.getChannelId()).getBranchId()!=branchId)
					iter.remove();
			}
		}
		
		return list;
	}

	/*
	 * 根据ID查询招生反款单
	 * 
	 * @see net.cedu.biz.finance.OrderCeduChannelBiz#findById(int)
	 */
	public OrderCeduChannel findById(int id) throws Exception {
		OrderCeduChannel order = orderCeduChannelDao.findById(id);
		
		return order;
	}
	
	/*
	 * 修改招生返款单
	 * 
	 * @see net.cedu.biz.finance.OrderCeduChannelBiz#updateOrderCeduChannel(net.cedu.entity.finance.OrderCeduChannel)
	 */
	public boolean updateOrderCeduChannel(OrderCeduChannel order) throws Exception
	{
		if (order != null) 
		{
			Object object = orderCeduChannelDao.update(order);
			if (object != null) 
			{
				return true;
			}
		}
		return false;
	}

	/*
	 * 招生返款审核
	 * 
	 * @see net.cedu.biz.finance.OrderCeduChannelBiz#updateForAudition(int, int, int)
	 */
	public void updateForAudition(int id, int newStatus, int auditorId) throws Exception {
		OrderCeduChannel order = orderCeduChannelDao.findById(id);
		
		order.setStatus(newStatus);
		order.setUpdaterId(auditorId);
		order.setUpdatedTime(new Date());
		
		orderCeduChannelDao.update(order);
		
		List<FeePaymentDetail> fpdList = feePaymentDetailDao.getByProperty("orderCeduChannelId", id);
		for(FeePaymentDetail fpd : fpdList){
			fpd.setStatus(newStatus);
			fpd.setUpdaterId(auditorId);
			fpd.setUpdatedTime(new Date());
			
			feePaymentDetailDao.update(fpd);
		}
	}
	
	
	/*
	 * 添加招生返款单----招生返款用
	 * 
	 * @see net.cedu.biz.finance.OrderCeduChannelBiz#addOrderCeduChannel(net.cedu.entity.finance.OrderCeduChannel, java.util.List)
	 */
	public boolean addOrderCeduChannel(OrderCeduChannel orderCeduChannel,List<FeePaymentDetail> fpdList) throws Exception
	{
		boolean isback=false;
		if(orderCeduChannel!=null && fpdList!=null && fpdList.size()>0)
		{
			this.orderCeduChannelDao.save(orderCeduChannel);
			for(FeePaymentDetail fpd:fpdList)
			{
				fpd.setOrderCeduChannelId(orderCeduChannel.getId());
				isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpd);
			}
		}
		return isback;
	}
	
	/*
	 * 添加招生返款单----招生返款用(2012-04-18重构   更新学生信息   消除固定金额重复选择)
	 * 
	 * @see net.cedu.biz.finance.OrderCeduChannelBiz#addOrderCeduChannel(net.cedu.entity.finance.OrderCeduChannel, java.util.List, java.util.List)
	 */
	public boolean addOrderCeduChannel(OrderCeduChannel orderCeduChannel,List<FeePaymentDetail> fpdList,List<Student> stuList) throws Exception
	{
		boolean isback=false;
		if(orderCeduChannel!=null && fpdList!=null && fpdList.size()>0)
		{
			this.orderCeduChannelDao.save(orderCeduChannel);
			for(FeePaymentDetail fpd:fpdList)
			{
				fpd.setOrderCeduChannelId(orderCeduChannel.getId());
				isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpd);
			}
			if(stuList!=null && stuList.size()>0)
			{
				for(Student stu:stuList)
				{
					this.studentBiz.updateStudentInfo(stu);
				}
			}
		}
		return isback;
	}
	
	
	/*
	 * 查询招生返款单数量 (分页)
	 * 
	 * @see net.cedu.biz.finance.OrderCeduChannelBiz#findOrderCeduChannelCountByPage(net.cedu.entity.finance.OrderCeduChannel, java.lang.String, java.lang.String, java.lang.String)
	 */
	public int findOrderCeduChannelCountByPage(OrderCeduChannel orderCeduChannel,String starttime,String endtime,String amount) throws Exception
	{
		PageParame p = new PageParame();
		List<Object> list = null;
		String hqlConditionExpression = "";
		if (orderCeduChannel != null)
		{
			list = new ArrayList<Object>();
			//招生返款期
			if(orderCeduChannel.getChannelRebateTimeId()!=0)
			{
				hqlConditionExpression += " and channelRebateTimeId="
					+ Constants.PLACEHOLDER;
				list.add(orderCeduChannel.getChannelRebateTimeId());
			}
			// 学习中心
			if (orderCeduChannel.getBranchId()!=0) 
			{
				hqlConditionExpression += " and branchId="
						+ Constants.PLACEHOLDER;
				list.add(orderCeduChannel.getBranchId());
			}
			else if(orderCeduChannel.getParamsString().get("branchIds")!=null && !orderCeduChannel.getParamsString().get("branchIds").equals(""))
			{
				//渠道经理审核只需看到管辖下的学习中心
				hqlConditionExpression += " and branchId in ("
					+ Constants.PLACEHOLDER +" ) ";
				list.add("$"+orderCeduChannel.getParamsString().get("branchIds"));
			}
			// 合作方类型
			if (orderCeduChannel.getChannelType()!=0)
			{
				hqlConditionExpression += " and channelType="
						+ Constants.PLACEHOLDER;
				list.add(orderCeduChannel.getChannelType());
			}
			// 合作方
			if (orderCeduChannel.getChannelId()!=0) 
			{
				hqlConditionExpression += " and channelId="
					+ Constants.PLACEHOLDER;
				list.add(orderCeduChannel.getChannelId());
			}			
			// 状态
			if (orderCeduChannel.getStatus()!=0) 
			{
				hqlConditionExpression += " and  status = "
						+ Constants.PLACEHOLDER;
				list.add(orderCeduChannel.getStatus());
			}			
			// 回退状态
			if (orderCeduChannel.getTypes()!=0) 
			{
				hqlConditionExpression += " and  types = "
						+ Constants.PLACEHOLDER;
				list.add(orderCeduChannel.getTypes());
			}			
			// 汇款日期起
			if (starttime!=null && !starttime.equals("")) 
			{
				hqlConditionExpression += " and  payDate >= "
						+ Constants.PLACEHOLDER;
				list.add(starttime);
			}
			// 汇款日期止
			if (endtime!=null && !endtime.equals("")) 
			{
				hqlConditionExpression += " and  payDate <= "
						+ Constants.PLACEHOLDER;
				list.add(endtime);
			}
			//汇款金额
			if(amount!=null && !amount.equals(""))
			{
				hqlConditionExpression += " and  amountPaied = "+ Constants.PLACEHOLDER;
				list.add(Double.valueOf(amount+""));
			}
			if (!hqlConditionExpression.equals("")) {
				p.setHqlConditionExpression(hqlConditionExpression);
				p.setValues(list.toArray());
			}
			return this.orderCeduChannelDao.getCounts(p);
		}
		return 0;
	}
	
	/*
	 * 查询招生返款单列表(分页)
	 * 
	 * @see net.cedu.biz.finance.OrderCeduChannelBiz#findOrderCeduChannelListByPage(net.cedu.entity.finance.OrderCeduChannel, java.lang.String, java.lang.String, java.lang.String, net.cedu.model.page.PageResult)
	 */
	public List<OrderCeduChannel> findOrderCeduChannelListByPage(OrderCeduChannel orderCeduChannel,String starttime,String endtime,String amount,PageResult<OrderCeduChannel> pr) throws Exception
	{
		List<OrderCeduChannel> occlist=null;
		PageParame p = new PageParame(pr);
		List<Object> list = null;
		String hqlConditionExpression = "";
		if (orderCeduChannel != null)
		{
			list = new ArrayList<Object>();
			//招生返款期
			if(orderCeduChannel.getChannelRebateTimeId()!=0)
			{
				hqlConditionExpression += " and channelRebateTimeId="
					+ Constants.PLACEHOLDER;
				list.add(orderCeduChannel.getChannelRebateTimeId());
			}
			// 学习中心
			if (orderCeduChannel.getBranchId()!=0) 
			{
				hqlConditionExpression += " and branchId="
						+ Constants.PLACEHOLDER;
				list.add(orderCeduChannel.getBranchId());
			}
			else if(orderCeduChannel.getParamsString().get("branchIds")!=null && !orderCeduChannel.getParamsString().get("branchIds").equals(""))
			{
				//渠道经理审核只需看到管辖下的学习中心
				hqlConditionExpression += " and branchId in ("
					+ Constants.PLACEHOLDER +" ) ";
				list.add("$"+orderCeduChannel.getParamsString().get("branchIds"));
			}
			// 合作方类型
			if (orderCeduChannel.getChannelType()!=0)
			{
				hqlConditionExpression += " and channelType="
						+ Constants.PLACEHOLDER;
				list.add(orderCeduChannel.getChannelType());
			}
			// 合作方
			if (orderCeduChannel.getChannelId()!=0) 
			{
				hqlConditionExpression += " and channelId="
					+ Constants.PLACEHOLDER;
				list.add(orderCeduChannel.getChannelId());
			}			
			// 状态
			if (orderCeduChannel.getStatus()!=0) 
			{
				hqlConditionExpression += " and  status = "
						+ Constants.PLACEHOLDER;
				list.add(orderCeduChannel.getStatus());
			}	
			// 回退状态
			if (orderCeduChannel.getTypes()!=0) 
			{
				hqlConditionExpression += " and  types = "
						+ Constants.PLACEHOLDER;
				list.add(orderCeduChannel.getTypes());
			}
			// 汇款日期起
			if (starttime!=null && !starttime.equals("")) 
			{
				hqlConditionExpression += " and  payDate >= "
						+ Constants.PLACEHOLDER;
				list.add(starttime);
			}
			// 汇款日期止
			if (endtime!=null && !endtime.equals("")) 
			{
				hqlConditionExpression += " and  payDate <= "
						+ Constants.PLACEHOLDER;
				list.add(endtime);
			}
			//汇款金额
			if(amount!=null && !amount.equals(""))
			{
				hqlConditionExpression += " and  amountPaied = "+ Constants.PLACEHOLDER;
				list.add(Double.valueOf(amount+""));
			}
			if (!hqlConditionExpression.equals("")) {
				p.setHqlConditionExpression(hqlConditionExpression);
				p.setValues(list.toArray());
			}
			Long[] payIds = orderCeduChannelDao.getIDs(p);
			if (payIds != null && payIds.length != 0) 
			{
				occlist=new ArrayList<OrderCeduChannel>();
				for (int i = 0; i < payIds.length; i++)
				{
					// 内存获取
					OrderCeduChannel occ=this.orderCeduChannelDao.findById(Integer.valueOf(payIds[i].toString()));
					if(occ!=null)
					{
						//学习中心
						Branch branch=this.branchBiz.findBranchById(occ.getBranchId());
						if(branch!=null)
						{
							occ.setBranchName(branch.getName());
						}
						//招生途径
						EnrollmentSource enrollmentSource =this.enrollmentSourceBiz.findEnrollmentSourceById(occ.getChannelType());
						if(enrollmentSource!=null)
						{
							occ.setChannelTypeName(enrollmentSource.getName());
						}
						//合作方
						Channel channel=this.channelBiz.findChannel(occ.getChannelId());
						if(channel!=null)
						{
							occ.setChannelName(channel.getName());
						}
						occlist.add(occ);
					}
				}
			}
			
		}
		return occlist;
	}
	
	/*
	 * 删除招生返款单----招生返款用
	 * 
	 * @see net.cedu.biz.finance.OrderCeduChannelBiz#deleteOrderCeduChannel(int, java.util.List)
	 */
	public boolean deleteOrderCeduChannel(int orderCeduChannelId,List<FeePaymentDetail> fpdList) throws Exception
	{
		boolean isback=false;
		if(orderCeduChannelId!=0 && fpdList!=null && fpdList.size()>0)
		{
			orderCeduChannelDao.deleteById(orderCeduChannelId);
			for(FeePaymentDetail fpd:fpdList)
			{
				isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpd);
			}
		}
		return isback;
	}
	
	/*
	 * 删除招生返款单----招生返款用((2012-04-18重构   更新学生信息   释放该学生以便可以继续返款))
	 * 
	 * @see net.cedu.biz.finance.OrderCeduChannelBiz#deleteOrderCeduChannel(int, java.util.List)
	 */
	public boolean deleteOrderCeduChannel(int orderCeduChannelId,List<FeePaymentDetail> fpdList,List<Student> stuList) throws Exception
	{
		boolean isback=false;
		if(orderCeduChannelId!=0 && fpdList!=null && fpdList.size()>0)
		{
			orderCeduChannelDao.deleteById(orderCeduChannelId);
			for(FeePaymentDetail fpd:fpdList)
			{
				isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpd);
			}
			if(stuList!=null && stuList.size()>0)
			{
				for(Student stu:stuList)
				{
					this.studentBiz.updateStudentInfo(stu);
				}
			}
		}
		return isback;
	}
	
	/*
	 * 更新招生返款单----招生返款用
	 * 
	 * @see net.cedu.biz.finance.OrderCeduChannelBiz#updateOrderCeduChannel(net.cedu.entity.finance.OrderCeduChannel, java.util.List)
	 */
	public boolean updateOrderCeduChannel(OrderCeduChannel orderCeduChannel,List<FeePaymentDetail> fpdList) throws Exception
	{
		boolean isback=false;
		if(orderCeduChannel!=null && fpdList!=null && fpdList.size()>0)
		{
			orderCeduChannelDao.update(orderCeduChannel);
			for(FeePaymentDetail fpd:fpdList)
			{
				isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpd);
			}
		}
		return isback;
	}
	
	/*
	 * 批量更新招生返款单----招生返款用
	 * 
	 * @see net.cedu.biz.finance.OrderCeduChannelBiz#updateOrderCeduChannel(net.cedu.entity.finance.OrderCeduChannel, java.util.List)
	 */
	public boolean updateBatchOrderCeduChannel(List<OrderCeduChannel> orderCeduChannelList,List<FeePaymentDetail> fpdList) throws Exception
	{
		boolean isback=false;
		if(orderCeduChannelList!=null && orderCeduChannelList.size()>0 && fpdList!=null && fpdList.size()>0)
		{
			for(OrderCeduChannel occ:orderCeduChannelList)
			{
				orderCeduChannelDao.update(occ);
			}
			for(FeePaymentDetail fpd:fpdList)
			{
				isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpd);
			}
		}
		return isback;
	}
	
	/*
	 * 更新招生返款单----招生返款用_hr汇款渠道时要级联更新学生
	 * 
	 * @see net.cedu.biz.finance.OrderCeduChannelBiz#updateOrderCeduChannel(net.cedu.entity.finance.OrderCeduChannel, java.util.List, java.util.List)
	 */
	public boolean updateOrderCeduChannel(OrderCeduChannel orderCeduChannel,List<FeePaymentDetail> fpdList,List<Student> stulist) throws Exception
	{
		boolean isback=false;
		if(orderCeduChannel!=null && fpdList!=null && fpdList.size()>0)
		{
			orderCeduChannelDao.update(orderCeduChannel);
			for(FeePaymentDetail fpd:fpdList)
			{
				isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpd);
			}
		}
		if(stulist!=null && stulist.size()>0)
		{
			for(Student stu:stulist)
			{
				this.studentBiz.updateStudentInfo(stu);
			}
		}
		return isback;
	}
	
	/*
	 * 方法重载
	 * 更新招生返款单----招生返款用_hr汇款渠道时要级联更新学生——2012-05-28重载招生返款标准绑定到合作方上
	 * @see net.cedu.biz.finance.OrderCeduChannelBiz#updateOrderCeduChannel(net.cedu.entity.finance.OrderCeduChannel, java.util.List, java.util.List, java.util.List)
	 */
	public boolean updateOrderCeduChannel(OrderCeduChannel orderCeduChannel,List<FeePaymentDetail> fpdList,List<Student> stulist,List<ChannelBatchRecruitRebateStandard> cbrslist) throws Exception
	{
		boolean isback=false;
		if(orderCeduChannel!=null && fpdList!=null && fpdList.size()>0)
		{
			orderCeduChannelDao.update(orderCeduChannel);
			for(FeePaymentDetail fpd:fpdList)
			{
				isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpd);
			}
		}
		if(stulist!=null && stulist.size()>0)
		{
			for(Student stu:stulist)
			{
				this.studentBiz.updateStudentInfo(stu);
			}
		}
		if(cbrslist!=null && cbrslist.size()>0)
		{
			for(ChannelBatchRecruitRebateStandard cbrrs:cbrslist)
			{
				this.cbrrsBiz.addChannelBatchRecruitRebateStandard(cbrrs);
			}
		}
		return isback;
	}
	
	
	/*
	 * 统计招生返款单总金额
	 * 
	 * @see net.cedu.biz.finance.OrderCeduChannelBiz#countOrderCeduChannelAllMoneyByConditions(net.cedu.entity.finance.OrderCeduChannel, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String countOrderCeduChannelAllMoneyByConditions(OrderCeduChannel orderCeduChannel,String starttime,String endtime,String amount) throws Exception
	{
		return orderCeduChannelDao.countOrderCeduChannelAllMoneyByConditions(orderCeduChannel, starttime, endtime, amount);
	}
	
	/*
	 * 根据招生返款期id和状态查询还未汇款的招生返款单数量
	 * 
	 * @see net.cedu.biz.finance.OrderCeduChannelBiz#findOrderCeduChannelCountByChannelRebateTimeIdStatus(int, int)
	 */
	public int findOrderCeduChannelCountByChannelRebateTimeIdStatus(int channelRebateTimeId,int status) throws Exception
	{
		PageParame p = new PageParame();
		List<Object> list = new ArrayList<Object>();
		String hqlConditionExpression = "";
		//招生返款期
		if(channelRebateTimeId!=0)
		{
			hqlConditionExpression += " and channelRebateTimeId<>"
					+ Constants.PLACEHOLDER;
			list.add(channelRebateTimeId);
		}
		//状态
		if(status!=0)
		{
			hqlConditionExpression += " and status < "+ Constants.PLACEHOLDER;
			list.add(status);
		}
			
		if (!hqlConditionExpression.equals(""))
		{
			p.setHqlConditionExpression(hqlConditionExpression);
			p.setValues(list.toArray());
		}
		return this.orderCeduChannelDao.getCounts(p);
		
	}
	
	/*
	 * 统计招生返款单调整金额（招生返款查询中已招生返款页签统计用）
	 * 
	 * @see net.cedu.biz.finance.OrderCeduChannelBiz#countOrderCeduChannelAdjustPaiedByConditions(net.cedu.entity.crm.Student, net.cedu.entity.finance.FeePaymentDetail)
	 */
	public String countOrderCeduChannelAdjustPaiedByConditions(Student student,FeePaymentDetail fpd) throws Exception
	{
		return this.orderCeduChannelDao.countOrderCeduChannelAdjustPaiedByConditions(student, fpd);
	}
	
}
