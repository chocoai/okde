package net.cedu.biz.finance.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyRebatePolicyDetailBiz;
import net.cedu.biz.enrollment.AcademyRebatePolicyDetailStandardBiz;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.PayCeduAcademyBiz;
import net.cedu.biz.finance.PaymentBiz;
import net.cedu.common.Constants;
import net.cedu.common.sql.RangeUtil;
import net.cedu.dao.finance.PayCeduAcademyDao;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyRebatePolicyDetail;
import net.cedu.entity.enrollment.AcademyRebatePolicyDetailStandard;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.PayCeduAcademy;
import net.cedu.model.common.Range;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 院校打款单  业务逻辑层实现
 * @author Sauntor
 */
@Service
public class PayCeduAcademyBizImpl implements PayCeduAcademyBiz {
	@Autowired
	private PayCeduAcademyDao payCeduAcademyDao;
	@Autowired
	private AcademyRebatePolicyDetailBiz academyRebatePolicyDetailBiz;
	@Autowired
	private AcademyRebatePolicyDetailStandardBiz academyRebatePolicyDetailStandardBiz;
//	@Autowired
//	private AcademyBatchFeeSubjectBiz academyBatchFeeSubjectBiz;
	@Autowired
	private StudentBiz studentBiz;
	@Autowired
	private PaymentBiz paymentBiz;
	
	@Autowired
	private AcademyBiz academyBiz;//院校业务接口
	
	@Autowired
	private BranchBiz branchBiz;//学习中心业务接口
	
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;//缴费单明细_业务层接口
	
	@Autowired
	private FeePaymentBiz feePaymentBiz;//缴费单_业务层接口

	/*
	 * 新增
	 * @see net.cedu.biz.finance.PayCeduAcademyBiz#add(net.cedu.entity.finance.PayCeduAcademy)
	 */
	public int add(PayCeduAcademy entity) throws Exception {
		Object ret = payCeduAcademyDao.save(entity);
		
		if(ret != null && ret instanceof Integer)
			return (Integer)ret;
		
		return 0;
	}

	/*
	 * 删除
	 * @see net.cedu.biz.finance.PayCeduAcademyBiz#deleteById(int)
	 */
	public void deleteById(int id) throws Exception {
		payCeduAcademyDao.deleteById(id);
	}

	/*
	 * 分页条件查询
	 * @see net.cedu.biz.finance.PayCeduAcademyBiz#find(net.cedu.entity.finance.PayCeduAcademy, net.cedu.model.common.DateTimeRange, net.cedu.model.page.PageResult)
	 */
	public List<PayCeduAcademy> find(PayCeduAcademy example, Range<Date> remittanceDateRange,
			PageResult<PayCeduAcademy> result, SearchCase searchCase) throws Exception {
		PageParame param = new PageParame(result);
		
		if(example != null)
			buildCoundition(example, param, searchCase);
		
		if(RangeUtil.validateDate(remittanceDateRange)){
			String hql = " and " + RangeUtil.formatDate(remittanceDateRange, "remittanceDate");
			if(StringUtils.isNotBlank(param.getHqlConditionExpression())){
				hql = param.getHqlConditionExpression() + hql;
			}
			param.setHqlConditionExpression(hql);
		}
		
		Long[] ids = payCeduAcademyDao.getIDs(param);
		
		if(ids != null){
			List<PayCeduAcademy> list = new LinkedList<PayCeduAcademy>();
			
			for(Long id : ids){
				list.add(payCeduAcademyDao.findById(id.intValue()));
			}
			
			return list;
		}
		
		return null;
	}
	
	/*
	 * 条件查询总数
	 * @see net.cedu.biz.finance.PayCeduAcademyBiz#count(net.cedu.entity.finance.PayCeduAcademy, net.cedu.model.common.DateTimeRange, net.cedu.model.page.PageResult)
	 */
	public int count(PayCeduAcademy example, Range<Date> remittanceDateRange,
			PageResult<PayCeduAcademy> result, SearchCase searchCase) throws Exception {
		PageParame param = new PageParame(result);
		
		if(example != null)
			buildCoundition(example, param, searchCase);
		
		if(RangeUtil.validateDate(remittanceDateRange)){
			String hql = " and " + RangeUtil.formatDate(remittanceDateRange, "remittanceDate");
			if(StringUtils.isNotBlank(param.getHqlConditionExpression())){
				hql = param.getHqlConditionExpression() + hql;
			}
			param.setHqlConditionExpression(hql);
		}
		
		int count = payCeduAcademyDao.getCounts(param);
		
		return count;
	}

	/*
	 * 按ID查询
	 * @see net.cedu.biz.finance.PayCeduAcademyBiz#findById(int)
	 */
	public PayCeduAcademy findById(int id) throws Exception {
		return payCeduAcademyDao.findById(id);
	}

	/*
	 * 更新
	 * @see net.cedu.biz.finance.PayCeduAcademyBiz#updateById(net.cedu.entity.finance.PayCeduAcademy)
	 */
	public void updateById(PayCeduAcademy entity) throws Exception {
		payCeduAcademyDao.update(entity);
	}
	
	/*
	 * 更新打款单
	 * 
	 * @see net.cedu.biz.finance.PayCeduAcademyBiz#updatePayCeduAcademy(net.cedu.entity.finance.PayCeduAcademy)
	 */
	public boolean updatePayCeduAcademy(PayCeduAcademy pca) throws Exception
	{
		if (pca != null) 
		{
			Object object = payCeduAcademyDao.update(pca);
			if (object != null) 
			{
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 计算院校应返款
	 * @see net.cedu.biz.finance.PayCeduAcademyBiz#CalculateMoneyToCedu(net.cedu.entity.finance.PayCeduAcademy)
	 */
	public Map<AcademyRebatePolicyDetail, List<FeePaymentDetail>> calculateMoneyToCedu(PayCeduAcademy payCeduAcademy) throws Exception
	{
		BigDecimal totalToCedu = new BigDecimal(0);
		
		CalculateHelper calcHelper = new CalculateHelper();
		
		/** 取出打款单所附缴费单明细 **/
		List<FeePaymentDetail> rawFpdList = paymentBiz.findDetailByOrderCeduAcademyId(payCeduAcademy.getId());

		/** 遍历缴费单明细，按适用的院校返款政策分组 **/
		Iterator<FeePaymentDetail> rawFpdIter = rawFpdList.iterator();
		while(rawFpdIter.hasNext()){
			FeePaymentDetail rawFpd = rawFpdIter.next();

			Student student = studentBiz.findStudentById(rawFpd.getStudentId());
			// 找出适用于缴费单明细的 院校返款政策
			AcademyRebatePolicyDetail academyRebatePolicyDetail = academyRebatePolicyDetailBiz.findForStudnet(student, rawFpd.getFeeSubjectId(), false);
			
			// 按返款政策分组保存
			calcHelper.add(academyRebatePolicyDetail, rawFpd);
		}
	
		/** 分组遍历缴费单，并计算各个缴费单组应返款金额 **/
		Iterator<AcademyRebatePolicyDetail> keyIter = calcHelper.keyIterator();
		while(keyIter.hasNext()){
			AcademyRebatePolicyDetail policy = keyIter.next(); //当前缴费单适用政策
			List<FeePaymentDetail> value = calcHelper.get(policy); //缴费单列表
			AcademyRebatePolicyDetailStandard matchedStd = null; //适用的政策标准档(按缴费单总数计算)
			
			List<AcademyRebatePolicyDetailStandard> standardList = academyRebatePolicyDetailStandardBiz.findByPolicyId(policy.getPolicyId());
			Iterator<AcademyRebatePolicyDetailStandard> stdIter = standardList.iterator();
			while(stdIter.hasNext()){
				AcademyRebatePolicyDetailStandard std = stdIter.next();
				if(std.getFloor() <= value.size() && std.getCeil() >= value.size()){ //缴费单总数符合此档标准
					matchedStd = std;
					break;
				}
			}
			if(matchedStd != null){ //若有适用的标准
				Iterator<FeePaymentDetail> fpdIter = value.iterator();
				while(fpdIter.hasNext()){
					FeePaymentDetail fpd = fpdIter.next();
					
					fpd.setRebateWay(matchedStd.getValueForm());
					fpd.setRebateValue(matchedStd.getValue().doubleValue());
					
					BigDecimal moneyToCedu = matchedStd.getValue();
					
					if(matchedStd.getValueForm()==Constants.MONEY_FORM_RATIO){ //比例存储的为百分比的数字部分
						moneyToCedu = moneyToCedu.divide(new BigDecimal(100));
						moneyToCedu = moneyToCedu.multiply(new BigDecimal(fpd.getPayCeduAcademy()).add(new BigDecimal(fpd.getAcademyDiscount())));
					} else if(matchedStd.getValueForm() != Constants.MONEY_FORM_AMOUNT){
						throw new RuntimeException("Unknown value form for academy rebate standard");
					}
					
					fpd.setPayAcademyCedu(moneyToCedu.doubleValue()); //记录院校返款金额
					
					fpd.setMoneyToCedu(moneyToCedu); //在缴费单明细中记录院校应返款金额
					
					totalToCedu = totalToCedu.add(moneyToCedu); //累计院校应返款金额
				}
			}
		}
		
		payCeduAcademy.setMoneyToCedu(totalToCedu.doubleValue());
		
		return calcHelper.getMap();
	}
	
	/**
	 * 院校打款单应返款金额计算辅助工具
	 * 
	 * @author Sauntor
	 */
	private class CalculateHelper
	{
		private Map<AcademyRebatePolicyDetail, List<FeePaymentDetail>> map = new HashMap<AcademyRebatePolicyDetail, List<FeePaymentDetail>>();
		
		/**
		 * 按适用的院校返款政策来分组缴费单明细
		 * @param arpd 适用院校返款政策
		 * @param fpd 待添加之缴费单明细
		 */
		public void add(AcademyRebatePolicyDetail arpd, FeePaymentDetail fpd)
		{
			if(arpd==null) return; //没有使用政策的缴费单不参与返款计算
			
			List<FeePaymentDetail> fpdList = null;
			
			Iterator<AcademyRebatePolicyDetail> keyIter = map.keySet().iterator();
			
			// 查看此政策的缴费单组是否已存在
			AcademyRebatePolicyDetail policyDetail = null;
			while(keyIter.hasNext()){
				AcademyRebatePolicyDetail key = keyIter.next();
				if(key.getId()==arpd.getId()){
					policyDetail = key;
					break;
				}
			}
			
			// 若不存在，则新建此政策的缴费单组
			if(policyDetail != null){
				fpdList = map.get(arpd);
			} else {
				fpdList = new LinkedList<FeePaymentDetail>();
				map.put(arpd, fpdList);
			}
			
			fpdList.add(fpd); //把缴费单添加到当前适用政策的缴费单组中
		}
		
		public Map<AcademyRebatePolicyDetail, List<FeePaymentDetail>> getMap(){
			return map;
		}
		
		public Iterator<AcademyRebatePolicyDetail> keyIterator(){
			return map.keySet().iterator();
		}
		
		public List<FeePaymentDetail> get(AcademyRebatePolicyDetail key){
			return map.get(key);
		}
	}
	
	/*****************  Helper Functions   ***************/
	private void buildCoundition(PayCeduAcademy condition, PageParame pageParam, SearchCase searchCase){
		StringBuilder hql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		
		if(condition.getRemitterId() > 0){
			hql.append(" and remitterId=").append(Constants.PLACEHOLDER);
			params.add(condition.getRemitterId());
		}
		
		if(condition.getRemitteeId() > 0){
			hql.append(" and remitteeId=").append(Constants.PLACEHOLDER);
			params.add(condition.getRemitteeId());
		}
		
		if(StringUtils.isNotBlank(condition.getRemittanceNo())){
			hql.append(" and remittanceNo=").append(Constants.PLACEHOLDER);
			params.add(condition.getRemittanceNo());
		}
		
		if(searchCase==SearchCase.AddPayAcademyCedu){ //用于添加反款单
//			if(condition.getStatus() == -1){
//				hql.append(" and (status>=").append(Constants.PLACEHOLDER).append(" and status<=").append(Constants.PLACEHOLDER).append(")");
//				params.add(Constants.PAYMENT_STATUS_YI_TIAN_DA_KUAN_DAN);
//				params.add(Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO);
//			}else {
//				hql.append(" and status=").append(Constants.PLACEHOLDER);
//				params.add(condition.getStatus());
//			}
			hql.append(" and status=").append(Constants.PLACEHOLDER);
			params.add(Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO);
			
			hql.append(" and payAcademyCeduId=").append(Constants.PLACEHOLDER);
			params.add(0); //未返款的打款单  的返款单ID为0
		}
		
		if(searchCase==SearchCase.ViewPayAcademyCedu){ //用于查看反款单
			if(condition.getPayAcademyCeduId() != -1){ //返款单ID
				hql.append(" and payAcademyCeduId=").append(Constants.PLACEHOLDER);
				params.add(condition.getPayAcademyCeduId());
			}
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
	 * 查询打款单数量
	 * @author xiao
	 * @see net.cedu.biz.finance.PayCeduAcademyBiz#findPayCeduAcademyCountByDetails(net.cedu.entity.finance.PayCeduAcademy, net.cedu.model.page.PageResult)
	 */
	public int findPayCeduAcademyCountByDetails(PayCeduAcademy payCeduAcademy,String starttime,String endtime,int status,String amount, PageResult<PayCeduAcademy> pr) throws Exception
	{
		PageParame p = new PageParame();
		List list = null;
		String hqlConditionExpression = "";
		if (payCeduAcademy != null) {
			list = new ArrayList();
			// 汇款单位
			if (payCeduAcademy.getRemitterId()!=0) 
			{
				hqlConditionExpression += " and remitterId="
						+ Constants.PLACEHOLDER;
				list.add(payCeduAcademy.getRemitterId());
			}
			//总部确认中心打款院校用到
			if (payCeduAcademy.getCeduId()!=0) 
			{
				hqlConditionExpression += " and remitterId <>"
						+ Constants.PLACEHOLDER;
				list.add(payCeduAcademy.getCeduId());
			}
			// 收款单位
			if (payCeduAcademy.getRemitteeId()!=0)
			{
				hqlConditionExpression += " and remitteeId="
						+ Constants.PLACEHOLDER;
				list.add(payCeduAcademy.getRemitteeId());
			}
			// 汇款单号
			if (payCeduAcademy.getRemittanceNo()!=null && !payCeduAcademy.getRemittanceNo().equals("")) 
			{
				hqlConditionExpression += " and  remittanceNo like "
						+ Constants.PLACEHOLDER;
				list.add('%'+payCeduAcademy.getRemittanceNo()+'%');
			}
			// 汇款账号
			if (payCeduAcademy.getRemitterAccount()!=null && !payCeduAcademy.getRemitterAccount().equals(""))
			{
				hqlConditionExpression += " and remitterAccount like "
						+ Constants.PLACEHOLDER;
				list.add('%'+payCeduAcademy.getRemitterAccount()+'%');
			}
			// 收款账号
			if (payCeduAcademy.getRemitteeAccount()!=null && !payCeduAcademy.getRemitteeAccount().equals("")) 
			{
				hqlConditionExpression += " and  remitteeAccount like "
						+ Constants.PLACEHOLDER;
				list.add('%'+payCeduAcademy.getRemitteeAccount()+'%');
			}
			
			//回退状态
			if(payCeduAcademy.getTypes()!=0)
			{
				hqlConditionExpression += " and  types = "
					+ Constants.PLACEHOLDER;
				list.add(payCeduAcademy.getTypes());
			}
			
			// 汇款单状态
			if (payCeduAcademy.getStatus()!=0) 
			{
				hqlConditionExpression += " and  status = "
						+ Constants.PLACEHOLDER;
				list.add(payCeduAcademy.getStatus());
			}
			// 汇款单状态  出纳汇款时不需要前面的状态
			if (status!=0) 
			{
				hqlConditionExpression += " and  status >= "
						+ Constants.PLACEHOLDER;
				list.add(status);
			}
			
			// 汇款日期起
			if (starttime!=null && !starttime.equals("")) 
			{
				hqlConditionExpression += " and  remittanceDate >= "
						+ Constants.PLACEHOLDER;
				list.add(starttime);
			}
			// 汇款日期止
			if (endtime!=null && !endtime.equals("")) 
			{
				hqlConditionExpression += " and  remittanceDate <= "
						+ Constants.PLACEHOLDER;
				list.add(endtime);
			}
			//汇款金额
			if(amount!=null && !amount.equals(""))
			{
				hqlConditionExpression += " and  amount = "+ Constants.PLACEHOLDER;
				list.add(Double.valueOf(amount+""));
			}
			
			if (!hqlConditionExpression.equals("")) {
				p.setHqlConditionExpression(hqlConditionExpression);
				p.setValues(list.toArray());
			}
			return this.payCeduAcademyDao.getCounts(p);
		}
		return 0;
	}
	
	/*
	 * 查询打款单列表
	 * @author xiao
	 * @see net.cedu.biz.finance.PayCeduAcademyBiz#findPayCeduAcademyListByDetails(net.cedu.entity.finance.PayCeduAcademy, net.cedu.model.page.PageResult)
	 */
	public List<PayCeduAcademy> findPayCeduAcademyListByDetails(PayCeduAcademy payCeduAcademy,String starttime,String endtime,int status,String amount, PageResult<PayCeduAcademy> pr) throws Exception
	{
		List<PayCeduAcademy> payCeduAcademyList=null;
		PageParame p = new PageParame(pr);
		List list = null;
		String hqlConditionExpression = "";
		if (payCeduAcademy != null) {
			list = new ArrayList();
			// 汇款单位
			if (payCeduAcademy.getRemitterId()!=0) 
			{
				hqlConditionExpression += " and remitterId="
						+ Constants.PLACEHOLDER;
				list.add(payCeduAcademy.getRemitterId());
			}
			//总部确认中心打款院校用到
			if (payCeduAcademy.getCeduId()!=0) 
			{
				hqlConditionExpression += " and remitterId <>"
						+ Constants.PLACEHOLDER;
				list.add(payCeduAcademy.getCeduId());
			}
			// 收款单位
			if (payCeduAcademy.getRemitteeId()!=0)
			{
				hqlConditionExpression += " and remitteeId="
						+ Constants.PLACEHOLDER;
				list.add(payCeduAcademy.getRemitteeId());
			}
			// 汇款单号
			if (payCeduAcademy.getRemittanceNo()!=null && !payCeduAcademy.getRemittanceNo().equals("")) 
			{
				hqlConditionExpression += " and  remittanceNo like "
						+ Constants.PLACEHOLDER;
				list.add('%'+payCeduAcademy.getRemittanceNo()+'%');
			}
			// 汇款账号
			if (payCeduAcademy.getRemitterAccount()!=null && !payCeduAcademy.getRemitterAccount().equals(""))
			{
				hqlConditionExpression += " and remitterAccount like "
						+ Constants.PLACEHOLDER;
				list.add('%'+payCeduAcademy.getRemitterAccount()+'%');
			}
			// 收款账号
			if (payCeduAcademy.getRemitteeAccount()!=null && !payCeduAcademy.getRemitteeAccount().equals("")) 
			{
				hqlConditionExpression += " and  remitteeAccount like "
						+ Constants.PLACEHOLDER;
				list.add('%'+payCeduAcademy.getRemitteeAccount()+'%');
			}
			
			//回退状态
			if(payCeduAcademy.getTypes()!=0)
			{
				hqlConditionExpression += " and  types = "
					+ Constants.PLACEHOLDER;
				list.add(payCeduAcademy.getTypes());
			}
			
			// 汇款单状态
			if (payCeduAcademy.getStatus()!=0) 
			{
				hqlConditionExpression += " and  status = "
						+ Constants.PLACEHOLDER;
				list.add(payCeduAcademy.getStatus());
			}
			
			// 汇款单状态  出纳汇款时不需要前面的状态
			if (status!=0) 
			{
				hqlConditionExpression += " and  status >= "
						+ Constants.PLACEHOLDER;
				list.add(status);
			}
			
			// 汇款日期起
			if (starttime!=null && !starttime.equals("")) 
			{
				hqlConditionExpression += " and  remittanceDate >= "
						+ Constants.PLACEHOLDER;
				list.add(starttime);
			}
			// 汇款日期止
			if (endtime!=null && !endtime.equals("")) 
			{
				hqlConditionExpression += " and  remittanceDate <= "
						+ Constants.PLACEHOLDER;
				list.add(endtime);
			}
			//汇款金额
			if(amount!=null && !amount.equals(""))
			{
				hqlConditionExpression += " and  amount = "+ Constants.PLACEHOLDER;
				list.add(Double.valueOf(amount+""));
			}
			if (!hqlConditionExpression.equals("")) {
				p.setHqlConditionExpression(hqlConditionExpression);
				p.setValues(list.toArray());
			}
			
			
			Long[] payIds = payCeduAcademyDao.getIDs(p);
			if (payIds != null && payIds.length != 0) {
				payCeduAcademyList = new ArrayList<PayCeduAcademy>();
				for (int i = 0; i < payIds.length; i++) {
					// 内存获取
					PayCeduAcademy payObj = payCeduAcademyDao.findById(Integer.valueOf(payIds[i].toString()));
					if (payObj != null) {
						PayCeduAcademy obj = payObj;
						//院校(收款单位)
						if(obj.getRemitteeId()!=0 && this.academyBiz.findAcademyById(obj.getRemitteeId())!=null)
						{
							obj.setRemitteeName(this.academyBiz.findAcademyById(obj.getRemitteeId()).getName());
						}						
						//学习中心(汇款单位)
						if(obj.getRemitterId()!=0 && this.branchBiz.findBranchById(obj.getRemitterId())!=null)
						{
							obj.setRemitterName(this.branchBiz.findBranchById(obj.getRemitterId()).getName());
						}
											
						payCeduAcademyList.add(obj);
					}
				}
			}
		}
		return payCeduAcademyList;
	}
	
	/*
	 * 新增打款单及其修改相应的缴费单明细和缴费单
	 * 
	 * @see net.cedu.biz.finance.PayCeduAcademyBiz#addPayCeduAcademyFeePaymentDetails(net.cedu.entity.finance.PayCeduAcademy, java.util.List, java.util.List)
	 */
	public boolean addPayCeduAcademyFeePaymentDetails(PayCeduAcademy entity,List<FeePaymentDetail> feePaymentDetailList,List<FeePayment> feePaymentList) throws Exception
	{
		boolean isback=false;
		if(entity!=null && feePaymentDetailList!=null && feePaymentDetailList.size()>0 && feePaymentList!=null && feePaymentList.size()>0)
		{
			this.add(entity);
			for(FeePaymentDetail fpd:feePaymentDetailList)
			{
				fpd.setOrderCeduAcademyId(entity.getId());
				this.feePaymentDetailBiz.modify(fpd);
			}
			for(FeePayment fp:feePaymentList)
			{
				isback=this.feePaymentBiz.updateFeePayment(fp);
			}
		}
		return isback;
	}
	
	/*
	 * 新增打款单及其修改相应的缴费单明细(相应缴费单暂时不做修改)
	 * 
	 * @see net.cedu.biz.finance.PayCeduAcademyBiz#addPayCeduAcademyUpdateFeePaymentDetails(net.cedu.entity.finance.PayCeduAcademy, java.util.List)
	 */
	public boolean addPayCeduAcademyUpdateFeePaymentDetails(PayCeduAcademy entity,List<FeePaymentDetail> feePaymentDetailList) throws Exception
	{
		boolean isback=false;
		if(entity!=null && feePaymentDetailList!=null && feePaymentDetailList.size()>0)
		{
			this.add(entity);
			for(FeePaymentDetail fpd:feePaymentDetailList)
			{
				fpd.setOrderCeduAcademyId(entity.getId());
				isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpd);
			}
			
		}
		return isback;
	}
	
	/*
	 * 删除打款单及其修改相应的缴费单明细
	 * 
	 * @see net.cedu.biz.finance.PayCeduAcademyBiz#delPayCeduAcademyUpdateFeePaymentDetails(int, java.util.List)
	 */
	public boolean deletePayCeduAcademyUpdateFeePaymentDetails(int payCeduAcademyId,List<FeePaymentDetail> feePaymentDetailList) throws Exception
	{
		boolean isback=false;
		if(payCeduAcademyId>0 && feePaymentDetailList!=null)
		{
			payCeduAcademyDao.deleteById(payCeduAcademyId);
			for(FeePaymentDetail fpd:feePaymentDetailList)
			{
				isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpd);
			}
			
		}
		return isback;
	}
	
	/*
	 * 更新打款单及其相应的缴费单明细
	 * 
	 * @see net.cedu.biz.finance.PayCeduAcademyBiz#updatePayCeduAcademyUpdateFeePaymentDetails(net.cedu.entity.finance.PayCeduAcademy, java.util.List)
	 */
	public boolean updatePayCeduAcademyUpdateFeePaymentDetails(PayCeduAcademy payCeduAcademy,List<FeePaymentDetail> feePaymentDetailList) throws Exception
	{
		boolean isback=false;
		if(payCeduAcademy!=null && feePaymentDetailList!=null)
		{
			payCeduAcademyDao.update(payCeduAcademy);
			for(FeePaymentDetail fpd:feePaymentDetailList)
			{
				isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpd);
			}
			
		}
		return isback;
	}
	
	/*
	 * 更新打款单及其相应的缴费单明细和缴费单明细对应的退费单明细（退费单明细状态为审核通过待确认的）---方法有问题    session报错   别用
	 * 
	 * @see net.cedu.biz.finance.PayCeduAcademyBiz#updatePayCeduAcademyFeePaymentDetailAndRefundfpdlist(net.cedu.entity.finance.PayCeduAcademy, java.util.List, java.util.List)
	 */
	public boolean updatePayCeduAcademyFeePaymentDetailAndRefundfpdlist(PayCeduAcademy payCeduAcademy,List<FeePaymentDetail> feePaymentDetailList,List<FeePaymentDetail> refundFpdList) throws Exception
	{
		boolean isback=false;
		if(payCeduAcademy!=null && feePaymentDetailList!=null)
		{
			//打款单
			payCeduAcademyDao.update(payCeduAcademy);
			//缴费单明细
			for(FeePaymentDetail fpd:feePaymentDetailList)
			{
				isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpd);
			}
			//退费单明细
			if(refundFpdList!=null && refundFpdList.size()>0)
			{
				//*********2012-04-11重构******* 退费后续流程
				this.feePaymentBiz.addRefundHouXuLiuChengOtherConfirm(refundFpdList);
			}
			
		}
		return isback;
	}
	
	/*
	 * 统计院校打款单总金额
	 * 
	 * @see net.cedu.biz.finance.PayCeduAcademyBiz#countPayCeduAcademyAllMoneyByConditions(net.cedu.entity.finance.PayCeduAcademy, java.lang.String, java.lang.String, int, java.lang.String)
	 */
	public String countPayCeduAcademyAllMoneyByConditions(PayCeduAcademy payCeduAcademy,String starttime,String endtime,int status,String amount) throws Exception
	{
		return payCeduAcademyDao.countPayCeduAcademyAllMoneyByConditions(payCeduAcademy, starttime, endtime, status, amount);
	}
	
	
}
