package net.cedu.biz.finance.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.finance.AcademyBatchRebateCountBiz;
import net.cedu.biz.finance.AcademyRebateAddRecordsBiz;
import net.cedu.biz.finance.AcademyRebateFenPeiBranchBiz;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.PayAcademyCeduBiz;
import net.cedu.biz.finance.PayCeduAcademyBiz;
import net.cedu.biz.finance.PlanedAcademyBillBiz;
import net.cedu.biz.finance.StudentAcademyRebateBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.sql.RangeUtil;
import net.cedu.dao.finance.FeePaymentDetailDao;
import net.cedu.dao.finance.PayAcademyCeduDao;
import net.cedu.dao.finance.PayCeduAcademyDao;
import net.cedu.dao.finance.PlanedAcademyBillDao;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.enrollment.AcademyRebatePolicyDetail;
import net.cedu.entity.finance.AcademyBatchRebateCount;
import net.cedu.entity.finance.AcademyRebateAddRecords;
import net.cedu.entity.finance.AcademyRebateFenPeiBranch;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.PayAcademyCedu;
import net.cedu.entity.finance.PayCeduAcademy;
import net.cedu.entity.finance.PlanedAcademyBill;
import net.cedu.entity.finance.StudentAcademyRebate;
import net.cedu.model.common.Range;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 院校返款总部 业务逻辑层实现
 * @author Sauntor
 *
 */
@Service
public class PayAcademyCeduBizImpl implements PayAcademyCeduBiz {
	@Autowired
	private PayAcademyCeduDao payAcademyCeduDao;
	@Autowired
	private FeePaymentDetailDao feePaymentDetailDao;
	@Autowired
	private FeePaymentDetailBiz fpdBiz;
	@Autowired
	private PlanedAcademyBillDao planedAcademyBillDao;
	@Autowired
	private PayCeduAcademyDao payCeduAcademyDao;
	
	@Autowired
	private PlanedAcademyBillBiz planedAcademiyBillBiz;
	@Autowired
	private PayCeduAcademyBiz payCeduAcademyBiz;
//	@Autowired
//	private PaymentBiz paymentBiz;
	
	@Autowired
	private AcademyBiz academyBiz;//院校_业务层接口
	
	@Autowired
	private FeePaymentBiz feePaymentBiz;//缴费单_业务层接口
	
	@Autowired
	private StudentAcademyRebateBiz studentAcademyRebateBiz;//学生绑定院校返款标准关系表_业务层接口
	
	@Autowired
	private AcademyBatchRebateCountBiz abrcBiz;//院校返款每个批次的返款总人数
	
	@Autowired
	private AcademyRebateFenPeiBranchBiz arfpbBiz;//院校返款追加金额分配给学习中心
	
	@Autowired
	private AcademyRebateAddRecordsBiz ararBiz;//院校返款追加记录
	

	/*
	 * 新增
	 * @see net.cedu.biz.finance.PayAcademyCeduBiz#add(net.cedu.entity.finance.PayAcademyCedu)
	 */
	public int add(PayAcademyCedu entity) throws Exception {
		Object ret = payAcademyCeduDao.save(entity);
		
		if(ret != null && ret instanceof Integer)
			return (Integer)ret;
		
		return 0;
	}

	/*
	 * 删除
	 * @see net.cedu.biz.finance.PayAcademyCeduBiz#deleteById(int)
	 */
	public void deleteById(int id) throws Exception {
		payAcademyCeduDao.deleteById(id);
	}

	/*
	 * 分页条件查询
	 * @see net.cedu.biz.finance.PayAcademyCeduBiz#find(net.cedu.entity.finance.PayAcademyCedu, net.cedu.model.page.PageResult)
	 */
	public List<PayAcademyCedu> find(PayAcademyCedu example, Range<Date> remittanceDateRange,
			PageResult<PayAcademyCedu> result) throws Exception {
		PageParame param = new PageParame(result);
		
		if(example != null)
			buildCoundition(example, param);
		
		if(RangeUtil.validateDate(remittanceDateRange)){
			String hql = " and " + RangeUtil.formatDate(remittanceDateRange, "remittanceDate");
			if(StringUtils.isNotBlank(param.getHqlConditionExpression())){
				hql = param.getHqlConditionExpression() + hql;
			}
			param.setHqlConditionExpression(hql);
		}
		
		Long[] ids = payAcademyCeduDao.getIDs(param);
		
		if(ids != null){
			List<PayAcademyCedu> list = new LinkedList<PayAcademyCedu>();
			
			for(Long id : ids){
				list.add(payAcademyCeduDao.findById(id.intValue()));
			}
			
			return list;
		}
		
		return null;
	}
	
	/*
	 * 条件查询总数
	 * 
	 * @see net.cedu.biz.finance.PayAcademyCeduBiz#count(net.cedu.entity.finance.PayAcademyCedu, net.cedu.model.common.DateTimeRange, net.cedu.model.page.PageResult)
	 */
	public int count(PayAcademyCedu example, Range<Date> remittanceDateRange,
			PageResult<PayAcademyCedu> result) throws Exception {
		PageParame param = new PageParame(result);
		
		if(example != null)
			buildCoundition(example, param);
		
		if(RangeUtil.validateDate(remittanceDateRange)){
			String hql = " and " + RangeUtil.formatDate(remittanceDateRange, "remittanceDate");
			if(StringUtils.isNotBlank(param.getHqlConditionExpression())){
				hql = param.getHqlConditionExpression() + hql;
			}
			param.setHqlConditionExpression(hql);
		}
		
		int count = payAcademyCeduDao.getCounts(param);
		
		return count;
	}

	/*
	 * 按ID查询
	 * @see net.cedu.biz.finance.PayAcademyCeduBiz#findById(int)
	 */
	public PayAcademyCedu findById(int id) throws Exception {
		return payAcademyCeduDao.findById(id);
	}

	/*
	 * 更新
	 * @see net.cedu.biz.finance.PayAcademyCeduBiz#updateById(net.cedu.entity.finance.PayAcademyCedu)
	 */
	public void update(PayAcademyCedu entity) throws Exception {
		payAcademyCeduDao.update(entity);
	}
	
	/*
	 * 更新院校返款单
	 * 
	 * @see net.cedu.biz.finance.PayAcademyCeduBiz#updatePayAcademyCedu(net.cedu.entity.finance.PayAcademyCedu)
	 */
	public boolean updatePayAcademyCedu(PayAcademyCedu pac) throws Exception 
	{
		if (pac != null) 
		{
			Object object = payAcademyCeduDao.update(pac);
			if (object != null) 
			{
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 添加院校返款单
	 * @see net.cedu.biz.finance.PayAcademyCeduBiz#add(net.cedu.entity.finance.PayAcademyCedu, int[], int[])
	 */
	public int add(PayAcademyCedu entity, int[] payCeduAcademyIds, int[] academyBillIds) throws Exception{
		BigDecimal sum = new BigDecimal(0); //返款总金额
		
		List<FeePaymentDetail> payList = new LinkedList<FeePaymentDetail>(); //将要返款的缴费单明细列表
		if(payCeduAcademyIds != null){
			for(int id : payCeduAcademyIds){
				PayCeduAcademy payCeduAcademy = payCeduAcademyBiz.findById(id);
				payCeduAcademyBiz.calculateMoneyToCedu(payCeduAcademy);
				sum = sum.add(new BigDecimal(payCeduAcademy.getMoneyToCedu()));
				
				Map<AcademyRebatePolicyDetail, List<FeePaymentDetail>> map = payCeduAcademyBiz.calculateMoneyToCedu(payCeduAcademy);
				if(map != null){
					Iterator<List<FeePaymentDetail>> iter = map.values().iterator();
					while(iter.hasNext()){
						payList.addAll(iter.next());
					}
				}
			}
		}
		
		if(academyBillIds != null){
			for(int id : academyBillIds){
				PlanedAcademyBill bill = planedAcademiyBillBiz.findById(id);
				sum = sum.add(bill.getAmount());
			}
		}
		
		entity.setAmount(sum);
		
		Integer payAcademyCeduId = (Integer)payAcademyCeduDao.save(entity);
		
		if(payList != null){
			for(FeePaymentDetail feePaymentDetail : payList){
				feePaymentDetail.setStatus(Constants.PAYMENT_STATUS_YI_TIAN_FAN_KUAN_DAN); //将缴费单明细状态置为已填返款单
				feePaymentDetail.setOrderAcademyCeduId(payAcademyCeduId); //设置缴费单明细对应返款单为当前返款单
				
				feePaymentDetailDao.update(feePaymentDetail); //保存返款金额等
			}
		}
		
		StringBuilder otherIds = new StringBuilder();
		if(academyBillIds != null){
			for(int id : academyBillIds){
				otherIds.append(id).append(",");
			}
		}
		if(otherIds.length()>0){
			otherIds.deleteCharAt(otherIds.length()-1);
			planedAcademyBillDao.update(" set isRebate="+Constants.PLACEHOLDER+" , academyRebateId="+Constants.PLACEHOLDER, otherIds.toString(), Constants.TRUE, entity.getId());
		}
		
		StringBuilder pcaIds = new StringBuilder();
		if(payCeduAcademyIds != null){
			for(int id : payCeduAcademyIds){
				pcaIds.append(id).append(",");
			}
		}
		if(pcaIds.length()>0){
			pcaIds.deleteCharAt(pcaIds.length()-1);
			payCeduAcademyDao.update(" set payAcademyCeduId="+Constants.PLACEHOLDER, pcaIds.toString(), entity.getId());
		}
		
		return entity.getId();
	}

	/*****************  Helper Functions   ***************/
	private void buildCoundition(PayAcademyCedu condition, PageParame pageParam){
		StringBuilder hql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		
		if(condition.getRemitterId() > -1){
			hql.append(" and remitterId=").append(Constants.PLACEHOLDER);
			params.add(condition.getRemitterId());
		}
		
		if(StringUtils.isNotBlank(condition.getRemittanceNo())){
			hql.append(" and remittanceNo=").append(Constants.PLACEHOLDER);
			params.add(condition.getRemittanceNo());
		}
		
		if(condition.getStatus() == -1){
			hql.append(" and (status=").append(Constants.PLACEHOLDER).append(" or status=").append(Constants.PLACEHOLDER).append(")");
			params.add(Constants.PAYMENT_STATUS_YI_TIAN_FAN_KUAN_DAN);
			params.add(Constants.PAYMENT_STATUS_FAN_KUAN_QUE_REN);
		} else {
			hql.append(" and status=").append(Constants.PLACEHOLDER);
			params.add(condition.getStatus());
		}
		
		if(params.size()>0){
			String hce = hql.toString();
			if(StringUtils.isNotBlank(pageParam.getHqlConditionExpression())){
				hce = pageParam.getHqlConditionExpression() + hql.toString();
			}
			pageParam.setHqlConditionExpression(hce);
			pageParam.setValues(params.toArray());
		}
	}
	
	/*
	 * 添加院校返款单
	 * 
	 * @see net.cedu.biz.finance.PayAcademyCeduBiz#addPayAcademyCedu(net.cedu.entity.finance.PayAcademyCedu, java.util.List, java.util.List)
	 */
	public boolean addPayAcademyCedu(PayAcademyCedu pac, List<FeePaymentDetail> fpdlist, List<PlanedAcademyBill> pablist) throws Exception
	{
		boolean isback=false;
		if(pac!=null && ((fpdlist!=null && fpdlist.size()>0) || (pablist!=null && pablist.size()>0)))
		{
			this.add(pac);
			if(pablist!=null && pablist.size()>0)
			{
				for(PlanedAcademyBill pab:pablist)
				{
					pab.setAcademyRebateId(pac.getId());
					this.planedAcademyBillDao.update(pab);
				}
				isback=true;
			}
			if(fpdlist!=null && fpdlist.size()>0)
			{
				for(FeePaymentDetail fpd:fpdlist)
				{
					fpd.setOrderAcademyCeduId(pac.getId());
					isback=this.fpdBiz.updateFeePaymentDetail(fpd);
				}
			}
			
		}
		return isback;
	}
	
	/*
	 * 添加院校返款单
	 * 
	 * @see net.cedu.biz.finance.PayAcademyCeduBiz#addPayAcademyCeduReview(net.cedu.entity.finance.PayAcademyCedu, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List)
	 */
	public boolean addPayAcademyCeduReview(PayAcademyCedu pac, List<FeePaymentDetail> fpdlist, List<PlanedAcademyBill> pablist,List<AcademyRebateFenPeiBranch> arfpblist,List<AcademyBatchRebateCount> abrclist,List<AcademyRebateAddRecords> ararlist) throws Exception
	{
		boolean isback=false;
		if(pac!=null && ((fpdlist!=null && fpdlist.size()>0) || (pablist!=null && pablist.size()>0)))
		{
			this.add(pac);
			//应收院校款
			if(pablist!=null && pablist.size()>0)
			{
				for(PlanedAcademyBill pab:pablist)
				{
					pab.setAcademyRebateId(pac.getId());
					this.planedAcademyBillDao.update(pab);
				}
				isback=true;
			}
			//缴费单明细
			if(fpdlist!=null && fpdlist.size()>0)
			{
				for(FeePaymentDetail fpd:fpdlist)
				{
					if(fpd.getOrderAcademyCeduId()==0)
					{
						fpd.setOrderAcademyCeduId(pac.getId());
					}
					isback=this.fpdBiz.updateFeePaymentDetail(fpd);
				}
			}
			//学习中心分配调整金额
			if(arfpblist!=null && arfpblist.size()>0)
			{
				for(AcademyRebateFenPeiBranch arfpb:arfpblist)
				{
					arfpb.setPayAcademyCeduId(pac.getId());
					isback=this.arfpbBiz.addAcademyRebateFenPeiBranch(arfpb);
				}
			}
			//院校返款每个批次的返款总人数
			if(abrclist!=null && abrclist.size()>0)
			{
				for(AcademyBatchRebateCount abrc:abrclist)
				{
					abrc.setPayAcademyCeduId(pac.getId());
					isback=this.abrcBiz.addAcademyBatchRebateCount(abrc);
				}
			}			
			//追加院校返款记录
			if(ararlist!=null && ararlist.size()>0)
			{
				for(AcademyRebateAddRecords arar:ararlist)
				{
					arar.setPayAcademyCeduId(pac.getId());
					isback=this.ararBiz.addAcademyRebateAddRecords(arar);
				}
			}
			
		}
		return isback;
	}
	
	/*
	 * 删除院校返款单
	 * 
	 * @see net.cedu.biz.finance.PayAcademyCeduBiz#deletePayAcademyCedu(int, java.util.List, java.util.List)
	 */
	public boolean deletePayAcademyCedu(int pacId, List<FeePaymentDetail> fpdlist, List<PlanedAcademyBill> pablist) throws Exception
	{
		boolean isback=false;
		if(pacId!=0 && ((fpdlist!=null && fpdlist.size()>0) || (pablist!=null && pablist.size()>0)))
		{
			this.deleteById(pacId);
			if(pablist!=null && pablist.size()>0)
			{
				for(PlanedAcademyBill pab:pablist)
				{
					pab.setAcademyRebateId(0);
					pab.setIsRebate(Constants.FEE_PAYMENT_CHANNEL_REBATE_FALSE);
					this.planedAcademyBillDao.update(pab);
				}
				isback=true;
			}
			if(fpdlist!=null && fpdlist.size()>0)
			{
				for(FeePaymentDetail fpd:fpdlist)
				{
					fpd.setStatus(Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO);
					fpd.setOrderAcademyCeduId(0);
					isback=this.fpdBiz.updateFeePaymentDetail(fpd);
				}
			}
			
		}
		return isback;
	}
	
	/*
	 * 删除院校返款单
	 * 
	 * @see net.cedu.biz.finance.PayAcademyCeduBiz#deletePayAcademyCeduReview(int, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List)
	 */
	public boolean deletePayAcademyCeduReview(int pacId, List<FeePaymentDetail> fpdlist, List<PlanedAcademyBill> pablist,List<AcademyBatchRebateCount> abrclist,List<AcademyRebateFenPeiBranch> arfpblist,List<AcademyRebateAddRecords> ararlist) throws Exception
	{
		boolean isback=false;
		if(pacId!=0 && ((fpdlist!=null && fpdlist.size()>0) || (pablist!=null && pablist.size()>0)))
		{
			//应收院校款
			if(pablist!=null && pablist.size()>0)
			{
				for(PlanedAcademyBill pab:pablist)
				{
					pab.setAcademyRebateId(0);
					pab.setIsRebate(Constants.FEE_PAYMENT_CHANNEL_REBATE_FALSE);
					this.planedAcademyBillDao.update(pab);
				}
				isback=true;
			}
			//缴费单明细
			if(fpdlist!=null && fpdlist.size()>0)
			{
				for(FeePaymentDetail fpd:fpdlist)
				{
					fpd.setStatus(Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO);
					fpd.setOrderAcademyCeduId(0);
					isback=this.fpdBiz.updateFeePaymentDetail(fpd);
				}
			}
			//学习中心分配调整金额
			if(arfpblist!=null && arfpblist.size()>0)
			{
				for(AcademyRebateFenPeiBranch arfpb:arfpblist)
				{
					isback=this.arfpbBiz.deleteAcademyRebateFenPeiBranchById(arfpb.getId());
				}
			}
			//院校返款每个批次的返款总人数
			if(abrclist!=null && abrclist.size()>0)
			{
				for(AcademyBatchRebateCount abrc:abrclist)
				{
					isback=this.abrcBiz.deleteAcademyBatchRebateCountById(abrc.getId());
				}
			}			
			//追加院校返款记录
			if(ararlist!=null && ararlist.size()>0)
			{
				FeePaymentDetail feep=null;
				for(AcademyRebateAddRecords arar:ararlist)
				{
					feep=this.fpdBiz.findById(arar.getFeePaymentDetailId());
					if(feep!=null)
					{
						//修改追加返款记录要删除掉
						feep.setAcademyRebateAddMoney(feep.getAcademyRebateAddMoney().subtract(arar.getAddMoney()));
						isback=this.fpdBiz.updateFeePaymentDetail(feep);
					}
					isback=this.ararBiz.deleteAcademyRebateAddRecordsById(arar.getId());
				}
			}			
			this.deleteById(pacId);
		}
		return isback;
	}
	
	/*
	 * 更新院校返款单
	 * 
	 * @see net.cedu.biz.finance.PayAcademyCeduBiz#deletePayAcademyCedu(int, java.util.List, java.util.List)
	 */
	public boolean updatePayAcademyCedu(PayAcademyCedu pac, List<FeePaymentDetail> fpdlist, List<PlanedAcademyBill> pablist) throws Exception
	{
		boolean isback=false;
		if(pac!=null && ((fpdlist!=null && fpdlist.size()>0) || (pablist!=null && pablist.size()>0)))
		{
			this.update(pac);
			if(fpdlist!=null && fpdlist.size()>0)
			{
				for(FeePaymentDetail fpd:fpdlist)
				{
					isback=this.fpdBiz.updateFeePaymentDetail(fpd);
				}
			}
			//TODO 应收院校返款单单的后续处理   暂时不需处理
			isback=true;
		}
		return isback;
	}
	
	/*
	 * 更新院校返款单
	 * 
	 * @see net.cedu.biz.finance.PayAcademyCeduBiz#updatePayAcademyCeduForRefund(net.cedu.entity.finance.PayAcademyCedu, java.util.List, java.util.List, java.util.List)
	 */
	public boolean updatePayAcademyCeduForRefund(PayAcademyCedu pac, List<FeePaymentDetail> fpdlist,List<FeePaymentDetail> refundlist, List<PlanedAcademyBill> pablist) throws Exception
	{
		boolean isback=false;
		if(pac!=null && ((fpdlist!=null && fpdlist.size()>0) || (pablist!=null && pablist.size()>0)))
		{
			this.update(pac);
			if(fpdlist!=null && fpdlist.size()>0)
			{
				for(FeePaymentDetail fpd:fpdlist)
				{
					isback=this.fpdBiz.updateFeePaymentDetail(fpd);
				}
			}
			
			//退费单集合
			if(refundlist!=null && refundlist.size()>0)
			{
				//*********2012-04-11重构******* 退费后续流程
				this.feePaymentBiz.addRefundHouXuLiuChengOtherConfirm(refundlist);
			}
			
			//TODO 应收院校返款单单的后续处理   暂时不需处理
			isback=true;
		}
		return isback;
	}
	
	/*
	 * 更新院校返款单     2012-05-16重载
	 * 
	 * @see net.cedu.biz.finance.PayAcademyCeduBiz#updatePayAcademyCeduForRefund(net.cedu.entity.finance.PayAcademyCedu, java.util.List, java.util.List, java.util.List)
	 */
	public boolean updatePayAcademyCeduForRefund(PayAcademyCedu pac, List<FeePaymentDetail> fpdlist,List<FeePaymentDetail> refundlist, List<PlanedAcademyBill> pablist, List<StudentAcademyRebate> sarList) throws Exception
	{
		boolean isback=false;
		if(pac!=null && ((fpdlist!=null && fpdlist.size()>0) || (pablist!=null && pablist.size()>0)))
		{
			this.update(pac);
			if(fpdlist!=null && fpdlist.size()>0)
			{
				for(FeePaymentDetail fpd:fpdlist)
				{
					isback=this.fpdBiz.updateFeePaymentDetail(fpd);
				}
			}
			
			//退费单集合
			if(refundlist!=null && refundlist.size()>0)
			{
				//*********2012-04-11重构******* 退费后续流程
				this.feePaymentBiz.addRefundHouXuLiuChengOtherConfirm(refundlist);
			}
			
			//学生与院校返款标准关系表集合
			if(sarList!=null && sarList.size()>0)
			{
				for(StudentAcademyRebate sar:sarList)
				{
					this.studentAcademyRebateBiz.addStudentAcademyRebate(sar);
				}
			}
			
			//TODO 应收院校返款单单的后续处理   暂时不需处理
			isback=true;
		}
		return isback;
	}
	
	/*
	 * 查询院校返款单数量（分页）
	 * 
	 * @see net.cedu.biz.finance.PayAcademyCeduBiz#findPayAcademyCeduCountByPage(net.cedu.entity.finance.PayAcademyCedu, java.lang.String, java.lang.String, java.lang.String)
	 */
	public int findPayAcademyCeduCountByPage(PayAcademyCedu pac,String starttime,String endtime,String amount) throws Exception
	{
		PageParame p = new PageParame();
		List<Object> list = null;
		String hqlConditionExpression = "";
		if (pac != null)
		{
			list = new ArrayList<Object>();
			// 返款院校
			if (pac.getRemitterId()!=0) 
			{
				hqlConditionExpression += " and remitterId="
						+ Constants.PLACEHOLDER;
				list.add(pac.getRemitterId());
			}
			
			// 状态
			if (pac.getStatus()!=0) 
			{
				hqlConditionExpression += " and  status = "
						+ Constants.PLACEHOLDER;
				list.add(pac.getStatus());
			}		
			//回退状态
			if(pac.getTypes()!=0)
			{
				hqlConditionExpression += " and  types = "
					+ Constants.PLACEHOLDER;
				list.add(pac.getTypes());
			}
			
			// 返款日期起
			if (starttime!=null && !starttime.equals("")) 
			{
				hqlConditionExpression += " and  remittanceDate >= "
						+ Constants.PLACEHOLDER;
				list.add(starttime);
			}
			// 返款日期止
			if (endtime!=null && !endtime.equals("")) 
			{
				hqlConditionExpression += " and  remittanceDate <= "
						+ Constants.PLACEHOLDER;
				list.add(endtime);
			}
			//实返款金额
			if(amount!=null && !amount.equals(""))
			{
				hqlConditionExpression += " and  amountPaied = "+ Constants.PLACEHOLDER;
				list.add(Double.valueOf(amount+""));
			}	
			//是否年度返款
			if(pac.getIsYearCount()!=0)
			{
				hqlConditionExpression += " and  isYearCount = "+ Constants.PLACEHOLDER;
				list.add(pac.getIsYearCount());
			}	
			//年度返款的具体年数
			if(pac.getCurrentYear()!=0)
			{
				hqlConditionExpression += " and  currentYear = "+ Constants.PLACEHOLDER;
				list.add(pac.getCurrentYear());
			}
		}
		if (!hqlConditionExpression.equals(""))
		{
			p.setHqlConditionExpression(hqlConditionExpression);
			p.setValues(list.toArray());
		}
		return this.payAcademyCeduDao.getCounts(p);
	}
	
	/*
	 * 查询院校返款单列表（分页）
	 * 
	 * @see net.cedu.biz.finance.PayAcademyCeduBiz#findPayAcademyCeduListByPage(net.cedu.entity.finance.PayAcademyCedu, java.lang.String, java.lang.String, java.lang.String, net.cedu.model.page.PageResult)
	 */
	public List<PayAcademyCedu> findPayAcademyCeduListByPage(PayAcademyCedu pac,String starttime,String endtime,String amount,PageResult<PayAcademyCedu> pr) throws Exception
	{
		List<PayAcademyCedu> paclist=null;
		PageParame p = new PageParame(pr);
		List<Object> list = null;
		String hqlConditionExpression = "";
		if (pac != null)
		{
			list = new ArrayList<Object>();
			// 返款院校
			if (pac.getRemitterId()!=0) 
			{
				hqlConditionExpression += " and remitterId="
						+ Constants.PLACEHOLDER;
				list.add(pac.getRemitterId());
			}
			
			// 状态
			if (pac.getStatus()!=0) 
			{
				hqlConditionExpression += " and  status = "
						+ Constants.PLACEHOLDER;
				list.add(pac.getStatus());
			}		
			//回退状态
			if(pac.getTypes()!=0)
			{
				hqlConditionExpression += " and  types = "
					+ Constants.PLACEHOLDER;
				list.add(pac.getTypes());
			}
			// 返款日期起
			if (starttime!=null && !starttime.equals("")) 
			{
				hqlConditionExpression += " and  remittanceDate >= "
						+ Constants.PLACEHOLDER;
				list.add(starttime);
			}
			// 返款日期止
			if (endtime!=null && !endtime.equals("")) 
			{
				hqlConditionExpression += " and  remittanceDate <= "
						+ Constants.PLACEHOLDER;
				list.add(endtime);
			}
			//实返金额
			if(amount!=null && !amount.equals(""))
			{
				hqlConditionExpression += " and  amountPaied = "+ Constants.PLACEHOLDER;
				list.add(Double.valueOf(amount+""));
			}			
			//是否年度返款
			if(pac.getIsYearCount()!=0)
			{
				hqlConditionExpression += " and  isYearCount = "+ Constants.PLACEHOLDER;
				list.add(pac.getIsYearCount());
			}	
			//年度返款的具体年数
			if(pac.getCurrentYear()!=0)
			{
				hqlConditionExpression += " and  currentYear = "+ Constants.PLACEHOLDER;
				list.add(pac.getCurrentYear());
			}
		}
		if (!hqlConditionExpression.equals(""))
		{
			p.setHqlConditionExpression(hqlConditionExpression);
			p.setValues(list.toArray());
		}
		Long[] payIds = payAcademyCeduDao.getIDs(p);
		if (payIds != null && payIds.length != 0) 
		{
			paclist=new ArrayList<PayAcademyCedu>();
			for (int i = 0; i < payIds.length; i++)
			{
				// 内存获取
				PayAcademyCedu pacobj=this.payAcademyCeduDao.findById(Integer.valueOf(payIds[i].toString()));
				if(pacobj!=null)
				{
					//返款单位
					Academy academy=this.academyBiz.findAcademyById(pacobj.getRemitterId());
					if(academy!=null)
					{
						pacobj.setRemitterName(academy.getName());
					}
					paclist.add(pacobj);
				}
			}
		}
		return paclist;
	}
	
	/*
	 * 统计院校返款单总金额
	 * 
	 * @see net.cedu.biz.finance.PayAcademyCeduBiz#countPayAcademyCeduAllMoneyByConditions(net.cedu.entity.finance.PayAcademyCedu, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String countPayAcademyCeduAllMoneyByConditions(PayAcademyCedu payAcademyCedu,String starttime,String endtime,String amount) throws Exception
	{
		return payAcademyCeduDao.countPayAcademyCeduAllMoneyByConditions(payAcademyCedu, starttime, endtime, amount);
	}
	
	/*
	 * 添加院校年度返款单
	 * 
	 * @see net.cedu.biz.finance.PayAcademyCeduBiz#addPayAcademyCeduReview(net.cedu.entity.finance.PayAcademyCedu, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List)
	 */
	public boolean addAcademyYearRebateReview(PayAcademyCedu pac, List<FeePaymentDetail> fpdlist, List<AcademyRebateFenPeiBranch> arfpblist,List<AcademyBatchRebateCount> abrclist,List<AcademyRebateAddRecords> ararlist) throws Exception
	{
		boolean isback=false;
		if(pac!=null && fpdlist!=null && fpdlist.size()>0)
		{
			this.add(pac);
			
			//缴费单明细
			if(fpdlist!=null && fpdlist.size()>0)
			{
				for(FeePaymentDetail fpd:fpdlist)
				{
					fpd.setAcademyYearRebateId(pac.getId());
					isback=this.fpdBiz.updateFeePaymentDetail(fpd);
				}
			}
			//学习中心分配调整金额
			if(arfpblist!=null && arfpblist.size()>0)
			{
				for(AcademyRebateFenPeiBranch arfpb:arfpblist)
				{
					arfpb.setPayAcademyCeduId(pac.getId());
					isback=this.arfpbBiz.addAcademyRebateFenPeiBranch(arfpb);
				}
			}
			//院校返款每个批次的返款总人数
			if(abrclist!=null && abrclist.size()>0)
			{
				for(AcademyBatchRebateCount abrc:abrclist)
				{
					abrc.setPayAcademyCeduId(pac.getId());
					isback=this.abrcBiz.addAcademyBatchRebateCount(abrc);
				}
			}			
			//追加院校返款记录
			if(ararlist!=null && ararlist.size()>0)
			{
				for(AcademyRebateAddRecords arar:ararlist)
				{
					arar.setPayAcademyCeduId(pac.getId());
					isback=this.ararBiz.addAcademyRebateAddRecords(arar);
				}
			}
			
		}
		return isback;
	}
	
	/*
	 * 删除院校年度返款单
	 * 
	 * @see net.cedu.biz.finance.PayAcademyCeduBiz#deleteAcademyYearRebateReview(int, java.util.List, java.util.List, java.util.List, java.util.List)
	 */
	public boolean deleteAcademyYearRebateReview(int pacId, List<FeePaymentDetail> fpdlist, List<AcademyBatchRebateCount> abrclist,List<AcademyRebateFenPeiBranch> arfpblist,List<AcademyRebateAddRecords> ararlist) throws Exception
	{
		boolean isback=false;
		if(pacId!=0 && fpdlist!=null && fpdlist.size()>0)
		{
			
			//缴费单明细
			if(fpdlist!=null && fpdlist.size()>0)
			{
				for(FeePaymentDetail fpd:fpdlist)
				{
					fpd.setAcademyRebateAddMoney(fpd.getAcademyRebateAddMoney().subtract(fpd.getAcademyYearRebateAddMoney()));
					fpd.setIsAcademyRebateYearCount(Constants.ACADEMY_REBATE_IS_YEAR_COUNT_FALSE);
					fpd.setAcademyYearRebateId(0);
					isback=this.fpdBiz.updateFeePaymentDetail(fpd);
				}
			}
			//学习中心分配调整金额
			if(arfpblist!=null && arfpblist.size()>0)
			{
				for(AcademyRebateFenPeiBranch arfpb:arfpblist)
				{
					isback=this.arfpbBiz.deleteAcademyRebateFenPeiBranchById(arfpb.getId());
				}
			}
			//院校返款每个批次的返款总人数
			if(abrclist!=null && abrclist.size()>0)
			{
				for(AcademyBatchRebateCount abrc:abrclist)
				{
					isback=this.abrcBiz.deleteAcademyBatchRebateCountById(abrc.getId());
				}
			}			
			//追加院校返款记录
			if(ararlist!=null && ararlist.size()>0)
			{
				for(AcademyRebateAddRecords arar:ararlist)
				{
					isback=this.ararBiz.deleteAcademyRebateAddRecordsById(arar.getId());
				}
			}			
			this.deleteById(pacId);
		}
		return isback;
	}
	
	
}
