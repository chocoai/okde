package net.cedu.biz.finance.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.ChannelRebateTimeLimitBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.basesetting.GlobalEnrollBatchBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyBatchFeeSubjectBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.AcademyRebatePolicyDetailStandardBiz;
import net.cedu.biz.enrollment.ChannelBiz;
import net.cedu.biz.enrollment.ChannelPolicyBiz;
import net.cedu.biz.enrollment.ChannelPolicyDetailBiz;
import net.cedu.biz.enrollment.ChannelPolicyDetailStandardBiz;
import net.cedu.biz.enrollment.ChannelPolicyDetailStandardOverLoadBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.biz.finance.AcademyBatchRebateCountBiz;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.common.Constants;
import net.cedu.common.enums.FeeSubjectEnum;
import net.cedu.common.il8n.ResourcesTool;
import net.cedu.dao.admin.BranchDao;
import net.cedu.dao.crm.StudentDao;
import net.cedu.dao.finance.FeePaymentDao;
import net.cedu.dao.finance.FeePaymentDetailDao;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.ChannelRebateTimeLimit;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.basesetting.GlobalEnrollBatch;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyBatchFeeSubject;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.AcademyRebatePolicyDetailStandard;
import net.cedu.entity.enrollment.Channel;
import net.cedu.entity.enrollment.ChannelPolicyDetail;
import net.cedu.entity.enrollment.ChannelPolicyDetailStandard;
import net.cedu.entity.enrollment.Major;
import net.cedu.entity.finance.AcademyBatchRebateCount;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * 缴费单明细  业务逻辑实现
 * 
 * @author gaole
 *
 */

@Service
public class FeePaymentDetailBizImpl implements FeePaymentDetailBiz {

	@Autowired
	private FeePaymentDetailDao feePaymentDetailDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private ChannelPolicyDetailBiz channelPolicyDetailBiz;
	@Autowired
	private ChannelPolicyBiz channelPolicyBiz;
	@Autowired
	private ChannelPolicyDetailStandardBiz channelPolicyDetailStandardBiz;
	
	@Autowired
	private ChannelPolicyDetailStandardOverLoadBiz cpdsOverLoadBiz;//招生返款标准_业务层接口
	
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;//费用科目业务层接口
	
	@Autowired
	private FeePaymentDao feePaymentDao;//缴费单_数据层接口
	
	@Autowired
	private FeePaymentBiz feePaymentBiz;//缴费单_业务层接口
	@Autowired
	private StudentBiz studentBiz; //学生_业务层接口
	@Autowired
	private AcademyBiz academyBiz;// 院校_业务接口
	@Autowired
	private AcademyEnrollBatchBiz academyenrollbatchBiz;// 院校招生批次 _业务接口
	@Autowired
	private LevelBiz levelbiz; // 层次_业务接口
	@Autowired
	private MajorBiz majorbiz; // 专业_业务接口
	@Autowired
	private BranchBiz branchBiz; // 学习中心_业务接口
	@Autowired
	private BranchDao branchDao; // 学习中心_数据访问层接口
	
	@Autowired
	private GlobalEnrollBatchBiz globalEnrollBatchBiz;//全局批次_业务层接口
	
	@Autowired
	private ChannelBiz channelBiz; // 合作方_业务接口
	
	@Autowired
	private AcademyBatchFeeSubjectBiz academyBatchFeeSubjectBiz;//院校应该返款的费用科目
	
	@Autowired
	private AcademyRebatePolicyDetailStandardBiz academyRebatePolicyDetailStandardBiz;//院校返款标准明细_业务层接口
	
	@Autowired
	private ChannelRebateTimeLimitBiz channelRebateTimeLimitBiz;//招生返款期_业务层接口
	
	@Autowired
	private AcademyBatchRebateCountBiz academyBatchRebateCountBiz;//院校返款每个批次的返款总人数
	
	
	/*
	 * 按缴费单Id查询缴费单明细
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findFeePaymentDetailByFeePaymentId(int)
	 */
	public FeePaymentDetail findFeePaymentDetailByFeePaymentId(int feePaymentId)
			throws Exception {
	
		return feePaymentDetailDao.getObjByProperty("id", feePaymentId);
	}
	
	/*
	 * 根据缴费单Id查询缴费单明细列表
	 * @author xiao
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findFeePaymentDetailListByFeePaymentId(int)
	 */
	public List<FeePaymentDetail> findFeePaymentDetailListByFeePaymentId(int feePaymentId)throws Exception
	{
		List<FeePaymentDetail> list=null;
		if(feePaymentId!=0)
		{
			list=feePaymentDetailDao.getByProperty("feePaymentId",feePaymentId);
			if(list!=null && list.size()>0)
			{
				for(FeePaymentDetail feed:list)
				{
					if(feed.getFeeSubjectId()!=0 && this.feeSubjectBiz.findFeeSubjectById(feed.getFeeSubjectId())!=null)
					{
						feed.setPaymentSubjectName(this.feeSubjectBiz.findFeeSubjectById(feed.getFeeSubjectId()).getName());
					}
				}
			}
		}
		return list;
	}
	
	/*
	 * 根据收费政策标准明细Id和学生Id查询缴费单明细数量
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findFeePaymentCountByStudentIdFeeStandardId(int, int)
	 */
	public int findFeePaymentCountByStudentIdFeeStandardId(int studentId,int feeStandardId)throws Exception
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List list = new ArrayList();
		hqlparam += " and studentId= " + Constants.PLACEHOLDER;
		list.add(studentId);
		hqlparam += " and policyStandardId= " + Constants.PLACEHOLDER;
		list.add(feeStandardId);
		hqlparam += " and status <> " + Constants.PLACEHOLDER;
		list.add(Constants.PAYMENT_STATUS_ZUO_FEI);
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		return feePaymentDetailDao.getCounts(p);
	}
	
	/*
	 * 添加缴费单明细
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#addFeePaymentDetail(net.cedu.entity.finance.FeePaymentDetail)
	 */
	public boolean addFeePaymentDetail(FeePaymentDetail feePaymentDetail) throws Exception
	{
		if (feePaymentDetail != null)
		{
			Object object = feePaymentDetailDao.save(feePaymentDetail);
			if (object != null) 
			{
				return true;
			}
		}
		
		return false;
	}
	
	/*
	 * 查询可招生返款的缴费单明细
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findForCreatePayBranchChannel(net.cedu.entity.crm.Student, net.cedu.model.page.PageResult)
	 */
	public List<FeePaymentDetail> findForCreatePayBranchChannel(Student student, PageResult<FeePaymentDetail> result) throws Exception {
		List<FeePaymentDetail> list = null;
		
		PageParame studentParam = new PageParame(result);
		
		buildStuInfoCondition(student, studentParam);
		
		Long[] ids = studentDao.getIDs(studentParam);
		if(ids != null && ids.length>0){
			StringBuilder hql = new StringBuilder();
			for(Long id : ids){
				hql.append(id).append(",");
			}
			
			hql.deleteCharAt(hql.length()-1);
			
			PageParame fpdParam = new PageParame(result);
			
			fpdParam.setHqlConditionExpression(" and studentId in ("+hql+") and status="+Constants.PAYMENT_STATUS_FAN_KUAN_QUE_REN+" and 1="+Constants.PLACEHOLDER);
			fpdParam.setValues(new Object[]{ 1 });
			
			Long[] fpdIds = feePaymentDetailDao.getIDs(fpdParam);
			
			list = new LinkedList<FeePaymentDetail>();
			
			for(Long id : fpdIds){
				FeePaymentDetail fpd = feePaymentDetailDao.findById(id.intValue());
				list.add(fpd);
			}
		}
		
		return list;
	}
	
	private void figureOutRebate(List<FeePaymentDetail> fpdList) throws Exception{
		FigureHelper helper = new FigureHelper();
		
		for(FeePaymentDetail fpd : fpdList){
			Student stu = studentDao.findById(fpd.getStudentId());
			ChannelPolicyDetail cpd = channelPolicyDetailBiz.findByStudent(stu, fpd.getFeeSubjectId());
			helper.add(cpd, fpd);
		}
		
		if(helper.groups.isEmpty())
			return;
		
		ChannelPolicyDetailStandard matchedStd = null;
		
		Iterator<ChannelPolicyDetail> cpdIter = helper.keyIter();
		while(cpdIter.hasNext()){
			ChannelPolicyDetail groupKey = cpdIter.next();
			
			List<ChannelPolicyDetailStandard> stdList = channelPolicyDetailStandardBiz.findByPolicyId(groupKey.getPolicyId());
			
			int count = helper.get(groupKey).size();
			for(ChannelPolicyDetailStandard std : stdList){
				if(count >= std.getEnrollmentFloor() && count <= std.getEnrollmentCeil()){
					matchedStd = std;
					break;
				}
			}
			
			groupKey.setMatchedStandard(matchedStd);
			
			for(FeePaymentDetail fpd : helper.get(groupKey)){
				if(matchedStd.getRebatesId()==Constants.MONEY_FORM_RATIO){
					//TODO sum up the money to channel
				}
			}
		}
	}
	
	private void buildStuInfoCondition(Student student, PageParame param){
		StringBuilder hql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		
		if(student.getMajorId() != -1){
			hql.append(" and majorId=").append(Constants.PLACEHOLDER);
			params.add(student.getMajorId());
		}
		
		if(student.getLevelId() != -1){
			hql.append(" and levelId=").append(Constants.PLACEHOLDER);
			params.add(student.getLevelId());
		}
		
		if(student.getBatchId() != -1){
			hql.append(" and batchId=").append(Constants.PLACEHOLDER);
			params.add(student.getBatchId());
		}
		
		if(student.getAcademyId() != -1){
			hql.append(" and academyId=").append(Constants.PLACEHOLDER);
			params.add(student.getAcademyId());
		}
		
		if(student.getBranchId() != -1){
			hql.append(" and branchId=").append(Constants.PLACEHOLDER);
			params.add(student.getBranchId());
		}
		
		if(StringUtils.isNotBlank(student.getName())){
			hql.append(" and name ilike ").append(Constants.PLACEHOLDER);
			params.add("'%"+student.getName()+"%'");
		}
		
		if(StringUtils.isNotBlank(student.getCertNo())){
			hql.append(" and certNo =").append(Constants.PLACEHOLDER);
			params.add(student.getCertNo());
		}
		
		if(hql.length() > 0){
			if(StringUtils.isNotBlank(param.getHqlConditionExpression())){
				hql.insert(0, param.getHqlConditionExpression());
				if(param.getValues()!=null){
					params.addAll(Arrays.asList(param.getValues()));
				}
				
				param.setHqlConditionExpression(hql.toString());
				param.setValues(params.toArray());
			}
		}
	}
	
	private static class FigureHelper{
		private Map<ChannelPolicyDetail, List<FeePaymentDetail>> groups
			=new HashMap<ChannelPolicyDetail, List<FeePaymentDetail>>();
		
		public void add(ChannelPolicyDetail cpd, FeePaymentDetail fpd){
			if(cpd == null) return;
			
			List<FeePaymentDetail> list = groups.get(cpd);
			if(list == null){
				list = new LinkedList<FeePaymentDetail>();
				groups.put(cpd, list);
			}
			
			list.add(fpd);
		}
		
		public void figure(){
			//TODO
		}
		
		public List<FeePaymentDetail> get(ChannelPolicyDetail cpd){
			return groups.get(cpd);
		}
		
		public Iterator<ChannelPolicyDetail> keyIter(){
			return groups.keySet().iterator();
		}
		
		public Iterator<List<FeePaymentDetail>> valueIter(){
			return groups.values().iterator();
		}
	}

	/*
	 * 查询合作方返款单对应的缴费单明细
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findByOrderCeduChannelId(int)
	 */
	public List<FeePaymentDetail> findByOrderCeduChannelId(int id) throws Exception
	{
		return feePaymentDetailDao.getByProperty(" and orderCeduChannelId="+Constants.PLACEHOLDER+" order by createdTime asc ", new Object[]{id});
	}

	/*
	 * 根据合作方返款单ID查询已返款缴费单明细
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findFpdListByOrderCeduChannelId(int)
	 */
	public List<FeePaymentDetail> findFpdListByOrderCeduChannelId(int id) throws Exception
	{
		List<FeePaymentDetail> feepdlist=null;
		PageResult<FeePaymentDetail> pr=new PageResult<FeePaymentDetail>();
		pr.setOrder("studentId");
		pr.setSort("desc");
		pr.setPage(false);//不分页
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (id !=0)
		{						
			hqlparam += " and orderCeduChannelId ="+ Constants.PLACEHOLDER;
			list.add(id);
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		Long[] feeIds = feePaymentDetailDao.getIDs(p);
		if (feeIds != null && feeIds.length != 0) 
		{
			feepdlist=new ArrayList<FeePaymentDetail>();
			for (int i = 0; i < feeIds.length; i++) 
			{
				// 内存获取
				FeePaymentDetail feeObj = feePaymentDetailDao.findById(Integer.valueOf(feeIds[i].toString()));
				if(feeObj!=null)
				{
					//缴费单的编号
					FeePayment fp=feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId());
					if(feeObj.getFeePaymentId()>0 && fp!=null)
					{
						feeObj.setPaymentCode(fp.getCode());
						feeObj.setFeeWayId(fp.getFeeWayId());//缴费方式
					}
					else
					{
						feeObj.setPaymentCode("");
					}
					//学生相关
					Student stu=studentBiz.findStudentById(feeObj.getStudentId());
					if(feeObj.getStudentId()>0 && stu!=null)
					{
						//学生姓名
						feeObj.setStudentName(stu.getName());
						//院校
						Academy academy=academyBiz.findAcademyById(stu.getAcademyId());
						if(stu.getAcademyId()>0 && academy!=null)
						{
							feeObj.setSchoolName(academy.getName());
						}
						//学习中心
						Branch branch=branchBiz.findBranchById(stu.getBranchId());
						if(stu.getBranchId()>0 && branch!=null)
						{
							feeObj.setBranchName(branch.getName());
						}
						//批次
						AcademyEnrollBatch aeb=academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId());
						if(stu.getEnrollmentBatchId()>0 && aeb!=null)
						{
							feeObj.setAcademyenrollbatchName(aeb.getEnrollmentName());
						}
						//层次
						Level level=levelbiz.findLevelById(stu.getLevelId());
						if(stu.getLevelId()>0 && level!=null)
						{
							feeObj.setLevelName(level.getName());
						}
						//专业
						Major major=majorbiz.findMajorById(stu.getMajorId());
						if(stu.getMajorId()>0 && major!=null)
						{
							feeObj.setMajorName(major.getName());
						}
						//合作方
						Channel channel=this.channelBiz.findChannel(stu.getChannelId());
						if(stu.getChannelId()>0 && channel!=null)
						{
							feeObj.setChannelName(channel.getName());
						}
						//全局批次
						GlobalEnrollBatch gb=this.globalEnrollBatchBiz.findGlobalEnrollBatchById(stu.getGlobalBatch());
						if(stu.getGlobalBatch()>0 && gb!=null)
						{
							feeObj.setGlobalBatchName(gb.getBatch());
						}
						
					}
					//费用科目
					FeeSubject fs=feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId());
					if(feeObj.getFeeSubjectId()>0 && fs!=null)
					{
						feeObj.setPaymentSubjectName(fs.getName());
					}
					//缴费金额
					feeObj.setJiaofeiValue((new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())).subtract(new BigDecimal(feeObj.getRefundAmount()))).doubleValue());
					
					feepdlist.add(feeObj);
				}
			}
		}
		
		return feepdlist;
	}
	
	public FeePaymentDetail findById(int id) throws Exception {
		return feePaymentDetailDao.findById(id);
	}
	public void modify(FeePaymentDetail fpd) throws Exception {
		feePaymentDetailDao.update(fpd);
	}
	
	/*
	 * 修改缴费单明细
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#updateFeePaymentDetail(net.cedu.entity.finance.FeePaymentDetail)
	 */
	public boolean updateFeePaymentDetail(FeePaymentDetail feePaymentDetail) throws Exception
	{
		if (feePaymentDetail != null) 
		{
			Object object = feePaymentDetailDao.update(feePaymentDetail);
			if (object != null) 
			{
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 修改缴费单明细及其对应的退费单明细
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#updateFeePaymentDetailAndTfd(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.finance.FeePaymentDetail)
	 */
	public boolean updateFeePaymentDetailAndTfd(FeePaymentDetail feePaymentDetail,FeePaymentDetail tfd) throws Exception
	{
		boolean isback=false;
		isback=this.updateFeePaymentDetail(feePaymentDetail);
		if(tfd!=null)
		{
			isback=this.updateFeePaymentDetail(tfd);
			
			//*********2012-04-11重构******* 退费后续流程
			List<FeePaymentDetail> fpdlist=new ArrayList<FeePaymentDetail>();
			fpdlist.add(tfd);
			this.feePaymentBiz.addRefundHouXuLiuChengOtherConfirm(fpdlist);
			
		}
		return isback;
	}
	
	/*
	 * 修改缴费单明细集合及其对应的退费单明细集合
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#updateFpdListAndTfdList(java.util.List, java.util.List)
	 */
	public boolean updateFpdListAndTfdList(List<FeePaymentDetail> fpdlist,List<FeePaymentDetail> tfdlist) throws Exception
	{
		boolean isback=false;
		if(fpdlist!=null && fpdlist.size()>0)
		{
			for(FeePaymentDetail fpd : fpdlist)
			{
				isback=this.updateFeePaymentDetail(fpd);
			}
		}
		if(tfdlist!=null && tfdlist.size()>0)
		{
			for(FeePaymentDetail tfd:tfdlist)
			{
				isback=this.updateFeePaymentDetail(tfd);
			}
			
			//*********2012-04-11重构******* 退费后续流程
		
			this.feePaymentBiz.addRefundHouXuLiuChengOtherConfirm(tfdlist);
			
		}
		return isback;
	}
	
	/*
	 *  根据费用科目Id和学生Id、缴费单明细状态查询缴费单明细集合
	 *  
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findFeePaymentDetailListByStudentIdFeeSubjectId(int, int, int)
	 */
	public List<FeePaymentDetail> findFeePaymentDetailListByStudentIdFeeSubjectId(int studentId,int feeSubjectId,int stauts)throws Exception
	{
		List<FeePaymentDetail> fpdetaillist=null;
		PageParame p = new PageParame();
		String hqlparam = "";
		List list = new ArrayList();
		hqlparam += " and studentId= " + Constants.PLACEHOLDER;
		list.add(studentId);
		hqlparam += " and feeSubjectId= " + Constants.PLACEHOLDER;
		list.add(feeSubjectId);
		hqlparam += " and status >= " + Constants.PLACEHOLDER;
		list.add(stauts);
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		Long[] feeIds = feePaymentDetailDao.getIDs(p);
		if (feeIds != null && feeIds.length != 0) 
		{
			fpdetaillist=new ArrayList<FeePaymentDetail>();;
			for (int i = 0; i < feeIds.length; i++) 
			{
				// 内存获取
				FeePaymentDetail feeObj = feePaymentDetailDao.findById(Integer.valueOf(feeIds[i].toString()));
				if (feeObj != null) 
				{
					FeePaymentDetail obj = feeObj;
					
					//费用科目
					if(obj.getFeeSubjectId()!=0 && this.feeSubjectBiz.findFeeSubjectById(obj.getFeeSubjectId())!=null)
					{
						obj.setPaymentSubjectName(this.feeSubjectBiz.findFeeSubjectById(obj.getFeeSubjectId()).getName());
					}
					fpdetaillist.add(obj);
				}
			}
		}

		return fpdetaillist;
	}
	
	/*
	 * 按条件查询缴费单明细数量（分页）(打款单(院校打款(中心)))
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findFeePaymentDetailCountByPage(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.finance.FeePayment, net.cedu.entity.crm.Student, net.cedu.model.page.PageResult)
	 */
	public int findFeePaymentDetailCountByPage(FeePaymentDetail feePaymentDetail,FeePayment feePayment,Student student,PageResult<FeePaymentDetail> pr) throws Exception
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List list = new ArrayList();
		if (feePaymentDetail != null)
		{						
			// 缴费单状态
			if (feePaymentDetail.getStatus() != 0) {
				hqlparam += " and status ="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getStatus());
			}
			
		}
//		String feePaymentIds=findFeePaymentIds(feePayment,student);
//		//缴费单Ids
//		if (feePaymentIds != null && !feePaymentIds.equals("")) 
//		{
//			hqlparam += " and feePaymentId in ("
//					+ Constants.PLACEHOLDER + ")";
//			list.add("$" + feePaymentIds);
//		}
//		else
//		{
//			return 0;
//		}
		//缴费单Ids__Hql
		String fpHql=this.findFeePaymentIds_hql(feePayment,student);
		if (fpHql != null && !fpHql.equals("select id from FeePayment where 1=1 ")) 
		{
			hqlparam += " and feePaymentId in ("+ fpHql + ")";
		}
		else
		{
			return 0;
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}

		return feePaymentDetailDao.getCounts(p);
		
	}
	
	/*
	 * 按条件查询缴费单明细集合（分页）(打款单(院校打款(中心)))
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findFeePaymentDetailListByPage(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.finance.FeePayment, net.cedu.entity.crm.Student, net.cedu.model.page.PageResult)
	 */
	public List<FeePaymentDetail> findFeePaymentDetailListByPage(FeePaymentDetail feePaymentDetail,FeePayment feePayment,Student student,PageResult<FeePaymentDetail> pr) throws Exception
	{
		List<FeePaymentDetail> feepdlist=null;
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List list = new ArrayList();
		if (feePaymentDetail != null)
		{						
			// 缴费单状态
			if (feePaymentDetail.getStatus() != 0) {
				hqlparam += " and status ="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getStatus());
			}
			
		}
//		String feePaymentIds=findFeePaymentIds(feePayment,student);
//		//缴费单Ids
//		if (feePaymentIds != null && !feePaymentIds.equals("")) 
//		{
//			hqlparam += " and feePaymentId in ("
//					+ Constants.PLACEHOLDER + ")";
//			list.add("$" + feePaymentIds);
//		}
//		else
//		{
//			return null;
//		}
		//缴费单Ids__Hql
		String fpHql=this.findFeePaymentIds_hql(feePayment,student);
		if (fpHql != null && !fpHql.equals("select id from FeePayment where 1=1 ")) 
		{
			hqlparam += " and feePaymentId in ("+ fpHql + ")";
		}
		else
		{
			return null;
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		Long[] feeIds = feePaymentDetailDao.getIDs(p);
		if (feeIds != null && feeIds.length != 0) 
		{
			feepdlist=new ArrayList<FeePaymentDetail>();
			for (int i = 0; i < feeIds.length; i++) 
			{
				// 内存获取
				FeePaymentDetail feeObj = feePaymentDetailDao.findById(Integer.valueOf(feeIds[i].toString()));
				if(feeObj!=null)
				{
					//缴费单的编号和缴费方式
					if(feeObj.getFeePaymentId()>0 && feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId())!=null)
					{
						feeObj.setFeeWayId(feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId()).getFeeWayId());
						feeObj.setPaymentCode(feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId()).getCode());
					}
					else
					{
						feeObj.setPaymentCode("");
					}
					//学生相关
					Student stu=studentBiz.findStudentById(feeObj.getStudentId());
					if(feeObj.getStudentId()>0 && stu!=null)
					{
						//学生姓名
						feeObj.setStudentName(stu.getName());
						//院校
						if(stu.getAcademyId()>0 && academyBiz.findAcademyById(stu.getAcademyId())!=null)
						{
							feeObj.setSchoolName(academyBiz.findAcademyById(stu.getAcademyId()).getName());
						}
						//学习中心
						if(stu.getBranchId()>0 && branchBiz.findBranchById(stu.getBranchId())!=null)
						{
							feeObj.setBranchName(branchBiz.findBranchById(stu.getBranchId()).getName());
						}
						//批次
						if(stu.getEnrollmentBatchId()>0 && academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId())!=null)
						{
							feeObj.setAcademyenrollbatchName(academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId()).getEnrollmentName());
						}
						//层次
						if(stu.getLevelId()>0 && levelbiz.findLevelById(stu.getLevelId())!=null)
						{
							feeObj.setLevelName(levelbiz.findLevelById(stu.getLevelId()).getName());
						}
						//专业
						if(stu.getMajorId()>0 && majorbiz.findMajorById(stu.getMajorId())!=null)
						{
							feeObj.setMajorName(majorbiz.findMajorById(stu.getMajorId()).getName());
						}
						
					}
					//费用科目
					if(feeObj.getFeeSubjectId()>0 && feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId())!=null)
					{
						feeObj.setPaymentSubjectName(feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId()).getName());
					}
					//基数
					BigDecimal jishu=new BigDecimal(1);
					if((new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount()))).compareTo(new BigDecimal(0))!=0)
					{
						jishu=(new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())).subtract(new BigDecimal(feeObj.getRefundAmount()))).divide(new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())),12,BigDecimal.ROUND_HALF_UP);
					}
					DecimalFormat df = new DecimalFormat("0.00"); // 保留几位小数
					//打款金额
					//feeObj.setPayCeduAcademy(Double.valueOf(new BigDecimal(feeObj.getMoneyToPay()).subtract(new BigDecimal(feeObj.getAcademyDiscount()).add(new BigDecimal(feeObj.getAcademyCeduDiscount()))).toString()));
					//feeObj.setPayCeduAcademy(((new BigDecimal(feeObj.getMoneyToPay()).subtract(new BigDecimal(feeObj.getAcademyDiscount()).add(new BigDecimal(feeObj.getAcademyCeduDiscount())))).multiply(new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())).subtract(new BigDecimal(feeObj.getRefundAmount()))).divide(new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())),2,BigDecimal.ROUND_HALF_UP)).doubleValue());
					feeObj.setPayCeduAcademy(Double.valueOf(df.format(((new BigDecimal(feeObj.getMoneyToPay()).subtract(new BigDecimal(feeObj.getAcademyDiscount()).add(new BigDecimal(feeObj.getAcademyCeduDiscount())))).multiply(jishu)).doubleValue())));
					
					//汇款金额.multiply(new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())).subtract(new BigDecimal(feeObj.getRefundAmount()))).divide(new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())),2,BigDecimal.ROUND_HALF_UP)
					//feeObj.setPayBranchCedu(Double.valueOf(new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())).toString()));
					//feeObj.setPayBranchCedu(((new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount()))).multiply(new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())).subtract(new BigDecimal(feeObj.getRefundAmount()))).divide(new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())),2,BigDecimal.ROUND_HALF_UP)).doubleValue());
					feeObj.setPayBranchCedu(Double.valueOf(df.format(((new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount()))).multiply(jishu)).doubleValue())));
					
					//缴费金额
					feeObj.setJiaofeiValue((new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())).subtract(new BigDecimal(feeObj.getRefundAmount()))).doubleValue());
					
					feepdlist.add(feeObj);
				}
			}
		}
		
		return feepdlist;
	}
	
	/*
	 * 按条件查询缴费单明细数量（分页）(打款单(院校打款(总部)))
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findFeePaymentDetailCountByCeduPlaymoneyAcademy(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.crm.Student)
	 */
	public int findFeePaymentDetailCountByCeduPlaymoneyAcademy(FeePaymentDetail feePaymentDetail,Student student) throws Exception
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List list = new ArrayList();
		if (feePaymentDetail != null)
		{						
			// 缴费单状态
			if (feePaymentDetail.getStatus() != 0) {
				hqlparam += " and status ="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getStatus());
			}
			
		}
		if (checkStudentInfo(student)) 
		{
			String studentIds=this.findStudentIds(student);
			//学生Ids
			if (studentIds != null && !studentIds.equals("")) 
			{
				hqlparam += " and studentId in ("
						+ Constants.PLACEHOLDER + ")";
				list.add("$" + studentIds);
			}
			else
			{
				return 0;
			}
		}	
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}

		return feePaymentDetailDao.getCounts(p);
		
	}
	
	/*
	 * 按条件查询缴费单明细集合（分页）(打款单(院校打款(总部)))
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findFeePaymentDetailListByCeduPlaymoneyAcademy(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.crm.Student)
	 */
	public List<FeePaymentDetail> findFeePaymentDetailListByCeduPlaymoneyAcademy(FeePaymentDetail feePaymentDetail,Student student,String starttime,String endtime,PageResult<FeePaymentDetail> pr) throws Exception
	{
		List<FeePaymentDetail> feepdlist=null;
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List list = new ArrayList();
		if (feePaymentDetail != null)
		{						
			// 缴费单状态
			if (feePaymentDetail.getStatus() != 0) {
				hqlparam += " and status ="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getStatus());
			}
			//费用科目
			if (feePaymentDetail.getFeeSubjectId() != 0) {
				hqlparam += " and feeSubjectId ="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getFeeSubjectId());
			}
			//缴费起止时间
			if(starttime!=null && !starttime.equals(""))
			{
				hqlparam += " and createdTime >="
					+ Constants.PLACEHOLDER;
				list.add(starttime);
			}
			if(endtime!=null && !endtime.equals(""))
			{
				hqlparam += " and createdTime <="
					+ Constants.PLACEHOLDER;
				list.add(endtime);
			}
			
			
		}
		if (checkStudentInfo(student)) 
		{
//			String studentIds=this.findStudentIds(student);
//			//学生Ids
//			if (studentIds != null && !studentIds.equals("")) 
//			{
//				hqlparam += " and studentId in ("
//						+ Constants.PLACEHOLDER + ")";
//				list.add("$" + studentIds);
//			}
//			else
//			{
//				return null;
//			}
			//学生Ids__Hql
			String stuHql=this.findStudentIds_hql(student);
			if (stuHql != null && !stuHql.equals("select id from Student where 1=1 ")) 
			{
				hqlparam += " and studentId in ("+ stuHql + ")";
			}
			else
			{
				return null;
			}
			
		}	
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		Long[] feeIds = feePaymentDetailDao.getIDs(p);
		if (feeIds != null && feeIds.length != 0) 
		{
			feepdlist=new ArrayList<FeePaymentDetail>();
			for (int i = 0; i < feeIds.length; i++) 
			{
				// 内存获取
				FeePaymentDetail feeObj = feePaymentDetailDao.findById(Integer.valueOf(feeIds[i].toString()));
				if(feeObj!=null)
				{
					//缴费单的编号和缴费方式
					if(feeObj.getFeePaymentId()>0 && feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId())!=null)
					{
						feeObj.setFeeWayId(feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId()).getFeeWayId());
						feeObj.setPaymentCode(feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId()).getCode());
					}
					else
					{
						feeObj.setPaymentCode("");
					}
					//学生相关
					Student stu=studentBiz.findStudentById(feeObj.getStudentId());
					if(feeObj.getStudentId()>0 && stu!=null)
					{
						//学生姓名
						feeObj.setStudentName(stu.getName());
						//院校
						if(stu.getAcademyId()>0 && academyBiz.findAcademyById(stu.getAcademyId())!=null)
						{
							feeObj.setSchoolName(academyBiz.findAcademyById(stu.getAcademyId()).getName());
						}
						//学习中心
						if(stu.getBranchId()>0 && branchBiz.findBranchById(stu.getBranchId())!=null)
						{
							feeObj.setBranchName(branchBiz.findBranchById(stu.getBranchId()).getName());
						}
						//批次
						if(stu.getEnrollmentBatchId()>0 && academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId())!=null)
						{
							feeObj.setAcademyenrollbatchName(academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId()).getEnrollmentName());
						}
						//层次
						if(stu.getLevelId()>0 && levelbiz.findLevelById(stu.getLevelId())!=null)
						{
							feeObj.setLevelName(levelbiz.findLevelById(stu.getLevelId()).getName());
						}
						//专业
						if(stu.getMajorId()>0 && majorbiz.findMajorById(stu.getMajorId())!=null)
						{
							feeObj.setMajorName(majorbiz.findMajorById(stu.getMajorId()).getName());
						}
						
					}
					//费用科目
					if(feeObj.getFeeSubjectId()>0 && feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId())!=null)
					{
						feeObj.setPaymentSubjectName(feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId()).getName());
					}
					//打款金额
					//基数
					BigDecimal jishu=new BigDecimal(1);
					if((new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount()))).compareTo(new BigDecimal(0))!=0)
					{
						jishu=(new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())).subtract(new BigDecimal(feeObj.getRefundAmount()))).divide(new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())),2,BigDecimal.ROUND_HALF_UP);
					}
					//feeObj.setPayCeduAcademy(Double.valueOf(new BigDecimal(feeObj.getMoneyToPay()).subtract(new BigDecimal(feeObj.getAcademyDiscount()).add(new BigDecimal(feeObj.getAcademyCeduDiscount()))).toString()));
					feeObj.setPayCeduAcademy(((new BigDecimal(feeObj.getMoneyToPay()).subtract(new BigDecimal(feeObj.getAcademyDiscount()).add(new BigDecimal(feeObj.getAcademyCeduDiscount())))).multiply(jishu)).doubleValue());
					//缴费金额
					feeObj.setJiaofeiValue((new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())).subtract(new BigDecimal(feeObj.getRefundAmount()))).doubleValue());
					
					feepdlist.add(feeObj);
				}
			}
		}
		
		return feepdlist;
	}
	
	/**
	 * 查询缴费单Ids
	 * 
	 * @param feePayment
	 * @return
	 */
	private String findFeePaymentIds(FeePayment feePayment, Student student)throws Exception 
	{
		List list2 = null;
		String hqlConditionExpression2 = "";
		PageParame p2 = new PageParame();
		list2 = new ArrayList();
		if (feePayment != null) 
		{
			// 缴费方式
			if (feePayment.getFeeWayId() != 0) 
			{
				hqlConditionExpression2 += " and feeWayId ="
						+ Constants.PLACEHOLDER;
				list2.add(feePayment.getFeeWayId());
			}
			// 缴费单号
			if (feePayment.getCode() != null && feePayment.getCode().length()>0) 
			{
				hqlConditionExpression2 += " and code ="
						+ Constants.PLACEHOLDER;
				list2.add(feePayment.getCode());
			}
		}
		if (checkStudentInfo(student)) 
		{
			// 学生ID
			String studentIds = findStudentIds(student);
			if (studentIds != null) 
			{
				hqlConditionExpression2 += " and studentId in ("
						+ Constants.PLACEHOLDER + ")";
				list2.add("$" + studentIds);
			}		
			else
			{
				return null;
			}
		}	
		if (!hqlConditionExpression2.equals("")) {
			p2.setHqlConditionExpression(hqlConditionExpression2);
			p2.setValues(list2.toArray());
		}

		//String pidString = ",";
		StringBuffer pidString=null;
		Long[] pidsLongs = feePaymentDao.getIDs(p2);
		// System.out.println(hqlConditionExpression2);
		if (pidsLongs == null || pidsLongs.length == 0) 
		{
			return null;
		}
		for (Long id : pidsLongs) 
		{
			if(pidString==null){
				pidString=new StringBuffer(id+"");
			}else{
				pidString.append(","+id);
			}
			
		}
		if(pidString==null){
			pidString=new StringBuffer("0");
		}
		return pidString.toString();
	}
	
	/**
	 * 查询缴费单Ids_Hql语句
	 * 
	 * @param feePayment
	 * @return
	 */
	private String findFeePaymentIds_hql(FeePayment feePayment, Student student)throws Exception 
	{
		// 查询学生集合ID
		String fpHql= "select id from FeePayment where 1=1 ";
		if (feePayment != null) 
		{
			// 缴费方式
			if (feePayment.getFeeWayId() != 0) 
			{
				fpHql += " and feeWayId ="+feePayment.getFeeWayId();
			}
			// 缴费单号
			if (feePayment.getCode() != null && feePayment.getCode().length()>0) 
			{
				fpHql += " and code ='"+feePayment.getCode()+" ' ";
			}
		}
		//学生Ids__Hql
		String stuHql=this.findStudentIds_hql(student);
		if (stuHql != null && !stuHql.equals("select id from Student where 1=1 ")) 
		{
			fpHql += " and studentId in ("+ stuHql + ")";
		}
		else
		{
			return null;
		}
		return fpHql;
	}
	
	/**
	 * 检查学生的查询条件是否为空    为空则不查询学生ID （为空的时候  学生为全部in学生id太多）
	 * @param student
	 * @return
	 */
	private boolean checkStudentInfo(Student student) {
		String hqlConditionExpression = "";
		if (student != null) {
			// 姓名
			if (student.getName() != null && !student.getName().equals("")) {
				hqlConditionExpression += "and name like"
						+ Constants.PLACEHOLDER;
			}
			// 证件号
			if (student.getCertNo() != null && !"".equals(student.getCertNo())) {
				hqlConditionExpression += "and certNo like"
						+ Constants.PLACEHOLDER;
			}
			//招生途径enrollmentSource
			if (student.getEnrollmentSource() != 0) {
				hqlConditionExpression += "and enrollmentSource ="
						+ Constants.PLACEHOLDER;
			}
			//合作方channelId
			if (student.getChannelId() != 0) {
				hqlConditionExpression += "and channelId ="
						+ Constants.PLACEHOLDER;
				
			}
			// 院校
			if (student.getAcademyId() != 0) {
				hqlConditionExpression += "and academyId="
						+ Constants.PLACEHOLDER;
			}
			// 批次
			if (student.getEnrollmentBatchId() != 0) {
				hqlConditionExpression += " and  enrollmentBatchId= "
						+ Constants.PLACEHOLDER;
			}
			//多批次
			if(student.getAcademyenrollbatchName()!=null && !"".equals(student.getAcademyenrollbatchName()))
			{
				hqlConditionExpression += " and  enrollmentBatchId in ("
					+ Constants.PLACEHOLDER+ ")";
				
			}
			// 层次
			if (student.getLevelId() != 0) {
				hqlConditionExpression += " and  levelId= "
						+ Constants.PLACEHOLDER;
			}
			// 专业
			if (student.getMajorId() != 0) {
				hqlConditionExpression += " and  majorId= "
						+ Constants.PLACEHOLDER;
			}
			// 学习中心
			if (student.getBranchId() != 0) {
				hqlConditionExpression += " and  branchId= "
						+ Constants.PLACEHOLDER;
			}
			// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
			// 如果起始状态ID>0,结束状态ID=0;则为无穷大
			// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
			// 如果都大于0,则取交集
			if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0) {
				hqlConditionExpression += " and  status <"
						+ Constants.PLACEHOLDER;
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0) {
				hqlConditionExpression += " and  status >"
						+ Constants.PLACEHOLDER;
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0) {
				hqlConditionExpression += " and  status> "
						+ Constants.PLACEHOLDER + " and status<"
						+ Constants.PLACEHOLDER;
			}
			if (!hqlConditionExpression.equals("")) {

			}

		}
		if (hqlConditionExpression != null
				&& !hqlConditionExpression.equals("")) {
			return true;
		}
		return false;
	}
	
	/**
	 * 查询学生ID集合
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	private String findStudentIds(Student student) throws Exception 
	{
		// 查询学生集合ID
		PageParame p = new PageParame();
		List list = null;
		String hqlConditionExpression = "";
		if (student != null)
		{
			list = new ArrayList();
			// 姓名
			if (student.getName() != null && !student.getName().equals("")) {
				hqlConditionExpression += "and name like"
						+ Constants.PLACEHOLDER;
				list.add("%" + student.getName() + "%");
			}
			// 证件号
			if (student.getCertNo() != null
					&& !"".equals(student.getCertNo())) {
				hqlConditionExpression += "and certNo like"
						+ Constants.PLACEHOLDER;
				list.add("%" + student.getCertNo() + "%");
			}
			//招生途径enrollmentSource
			if (student.getEnrollmentSource() != 0) {
				hqlConditionExpression += "and enrollmentSource ="
						+ Constants.PLACEHOLDER;
				list.add(student.getEnrollmentSource());
			}
			//合作方channelId
			if (student.getChannelId() != 0) {
				hqlConditionExpression += "and channelId ="
						+ Constants.PLACEHOLDER;
				list.add(student.getChannelId());
			}
			// 院校
			if (student.getAcademyId() != 0) {
				hqlConditionExpression += "and academyId="
						+ Constants.PLACEHOLDER;
				list.add(student.getAcademyId());
			}
			// 批次
			if (student.getEnrollmentBatchId() != 0) {
				hqlConditionExpression += " and  enrollmentBatchId= "
						+ Constants.PLACEHOLDER;
				list.add(student.getEnrollmentBatchId());
			}
			//多批次
			if(student.getAcademyenrollbatchName()!=null && !"".equals(student.getAcademyenrollbatchName()))
			{
				hqlConditionExpression += " and  enrollmentBatchId in ("
					+ Constants.PLACEHOLDER+ ")";
				list.add("$" +student.getAcademyenrollbatchName());
				
			}
			// 层次
			if (student.getLevelId() != 0) {
				hqlConditionExpression += " and  levelId= "
						+ Constants.PLACEHOLDER;
				list.add(student.getLevelId());
			}
			// 专业
			if (student.getMajorId() != 0) {
				hqlConditionExpression += " and  majorId= "
						+ Constants.PLACEHOLDER;
				list.add(student.getMajorId());
			}
			// 学习中心
			if (student.getBranchId() != 0) {
				hqlConditionExpression += " and  branchId= "
						+ Constants.PLACEHOLDER;
				list.add(student.getBranchId());
			}
			// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
			// 如果起始状态ID>0,结束状态ID=0;则为无穷大
			// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
			// 如果都大于0,则取交集
			if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0) {
				hqlConditionExpression += " and  status <"
						+ Constants.PLACEHOLDER;
				list.add(student.getEndStatusId());
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0) {
				hqlConditionExpression += " and  status >"
						+ Constants.PLACEHOLDER;
				list.add(student.getStartStatusId());
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0) {
				hqlConditionExpression += " and  status> "
						+ Constants.PLACEHOLDER + " and status<"
						+ Constants.PLACEHOLDER;
				list.add(student.getStartStatusId());
				list.add(student.getEndStatusId());
			}
			if (!hqlConditionExpression.equals("")) {
				p.setHqlConditionExpression(hqlConditionExpression);
				p.setValues(list.toArray());
			}
		}
		String studentIds = ",";
		Long[] sidsLongs = studentDao.getIDs(p);
		if (sidsLongs == null || sidsLongs.length == 0) 
		{
			return null;
		}
		for (Long id : sidsLongs)
		{
			if (studentIds.startsWith(","))
			{
				studentIds = id + "";
			} 
			else 
			{
				studentIds += "," + id;
			}
		}
		return studentIds;
	}
	
	/**
	 * 查询学生ID集合——hql
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	private String findStudentIds_hql(Student student) throws Exception 
	{
		// 查询学生集合ID
		String stuHql= "select id from Student where 1=1 ";
		if (student != null)
		{
			// 姓名
			if (student.getName() != null && !student.getName().equals(""))
			{
				stuHql += " and name like '%" + student.getName() + "%' ";
			}
			// 证件号
			if (student.getCertNo() != null && !"".equals(student.getCertNo()))
			{
				stuHql += " and certNo like '%" + student.getCertNo() + "%' ";
			}
			// 手机或座机
			if (student.getPhone() != null && !student.getPhone().equals(""))
			{
				stuHql+= " and (phone like '%"+ student.getPhone() + "%' or mobile like '%"
						+ student.getPhone() + "%' ) ";
			}
			//招生途径enrollmentSource
			if (student.getEnrollmentSource() != 0)
			{
				stuHql += " and enrollmentSource ="+student.getEnrollmentSource();
			}
			//合作方channelId
			if (student.getChannelId() != 0)
			{
				stuHql += " and channelId ="+student.getChannelId();						
			}
			// 院校
			if (student.getAcademyId() != 0) {
				stuHql += " and academyId="+student.getAcademyId();
						
			}
			// 批次
			if (student.getEnrollmentBatchId() != 0) {
				stuHql += " and  enrollmentBatchId= "+student.getEnrollmentBatchId();
						
			}
			//多批次
			if(student.getAcademyenrollbatchName()!=null && !"".equals(student.getAcademyenrollbatchName()))
			{
				stuHql += " and  enrollmentBatchId in ("+ student.getAcademyenrollbatchName()+ ")";
				
			}
			// 层次
			if (student.getLevelId() != 0) {
				stuHql += " and  levelId= "+student.getLevelId();
					
			}
			// 专业
			if (student.getMajorId() != 0) {
				stuHql += " and  majorId= "+student.getMajorId();
			}
			// 学习中心
			if (student.getBranchId() != 0) {
				stuHql += " and  branchId= "+student.getBranchId();
			
			}
			// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
			// 如果起始状态ID>0,结束状态ID=0;则为无穷大
			// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
			// 如果都大于0,则取交集
			if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0) {
				stuHql += " and  status <"+student.getEndStatusId();
						
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0) {
				stuHql += " and  status >"+student.getStartStatusId();
						
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0) {
				stuHql += " and  status> "
						+ student.getStartStatusId() + " and status<"
						+ student.getEndStatusId();
			}
		}
		
		return stuHql;
	}
	
	
	/**
	 * 查询学生ID集合(招生返款用)
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	private String findStudentIdsForChannel(Student student) throws Exception 
	{
		// 查询学生集合ID
		PageParame p = new PageParame();
		List<Object> list = null;
		String hqlConditionExpression = "";
		if (student != null)
		{
			list = new ArrayList<Object>();
			// 姓名
			if (student.getName() != null && !student.getName().equals("")) {
				hqlConditionExpression += "and name like"
						+ Constants.PLACEHOLDER;
				list.add("%" + student.getName() + "%");
			}
			// 证件号
			if (student.getCertNo() != null
					&& !"".equals(student.getCertNo())) {
				hqlConditionExpression += "and certNo like"
						+ Constants.PLACEHOLDER;
				list.add("%" + student.getCertNo() + "%");
			}
			//招生途径enrollmentSource
			if (student.getEnrollmentSource() != 0) {
				hqlConditionExpression += "and enrollmentSource ="
						+ Constants.PLACEHOLDER;
				list.add(student.getEnrollmentSource());
			}
			//合作方channelId
			if (student.getChannelId() != 0) {
				hqlConditionExpression += "and channelId ="
						+ Constants.PLACEHOLDER;
				list.add(student.getChannelId());
			}
			// 院校
			if (student.getAcademyId() != 0) {
				hqlConditionExpression += "and academyId="
						+ Constants.PLACEHOLDER;
				list.add(student.getAcademyId());
			}
			// 批次
			if (student.getEnrollmentBatchId() != 0) {
				hqlConditionExpression += " and  enrollmentBatchId= "
						+ Constants.PLACEHOLDER;
				list.add(student.getEnrollmentBatchId());
			}
			//多批次
			if(student.getAcademyenrollbatchName()!=null && !"".equals(student.getAcademyenrollbatchName()))
			{
				hqlConditionExpression += " and  enrollmentBatchId in ("
					+ Constants.PLACEHOLDER+ ")";
				list.add("$" +student.getAcademyenrollbatchName());
				
			}
			// 层次
			if (student.getLevelId() != 0) {
				hqlConditionExpression += " and  levelId= "
						+ Constants.PLACEHOLDER;
				list.add(student.getLevelId());
			}
			// 专业
			if (student.getMajorId() != 0) {
				hqlConditionExpression += " and  majorId= "
						+ Constants.PLACEHOLDER;
				list.add(student.getMajorId());
			}
			// 学习中心
			if (student.getBranchId() != 0) {
				hqlConditionExpression += " and  branchId= "
						+ Constants.PLACEHOLDER;
				list.add(student.getBranchId());
			}
			//已缴学费   且已开课的学生才能招生返款
			hqlConditionExpression += " and  tuitionStatus >= "
				+ Constants.PLACEHOLDER;
			list.add(Constants.STU_TUITION_STATUS_SHOU_CI_JIAO_FEI);
			hqlConditionExpression += " and  isStartCourse= "
				+ Constants.PLACEHOLDER;
			list.add(Constants.STU_IS_START_COURSE_TRUE);
			
			// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
			// 如果起始状态ID>0,结束状态ID=0;则为无穷大
			// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
			// 如果都大于0,则取交集
			if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0) {
				hqlConditionExpression += " and  status <"
						+ Constants.PLACEHOLDER;
				list.add(student.getEndStatusId());
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0) {
				hqlConditionExpression += " and  status >"
						+ Constants.PLACEHOLDER;
				list.add(student.getStartStatusId());
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0) {
				hqlConditionExpression += " and  status> "
						+ Constants.PLACEHOLDER + " and status<"
						+ Constants.PLACEHOLDER;
				list.add(student.getStartStatusId());
				list.add(student.getEndStatusId());
			}
			if (!hqlConditionExpression.equals("")) {
				p.setHqlConditionExpression(hqlConditionExpression);
				p.setValues(list.toArray());
			}
		}
		String studentIds = ",";
		Long[] sidsLongs = studentDao.getIDs(p);
		if (sidsLongs == null || sidsLongs.length == 0) 
		{
			return null;
		}
		for (Long id : sidsLongs)
		{
			if (studentIds.startsWith(","))
			{
				studentIds = id + "";
			} 
			else 
			{
				studentIds += "," + id;
			}
		}
		return studentIds;
	}
	
	/**
	 * 查询学生ID集合(招生返款用_特殊合作方时用)
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	private String findStudentIdsForChannelSpecial(Student student,int minorChannelType,int minorChannelId) throws Exception 
	{
		// 查询学生集合ID
		PageParame p = new PageParame();
		List<Object> list = null;
		String hqlConditionExpression = "";
		if (student != null)
		{
			list = new ArrayList<Object>();
			// 姓名
			if (student.getName() != null && !student.getName().equals("")) {
				hqlConditionExpression += "and name like"
						+ Constants.PLACEHOLDER;
				list.add("%" + student.getName() + "%");
			}
			// 证件号
			if (student.getCertNo() != null
					&& !"".equals(student.getCertNo())) {
				hqlConditionExpression += "and certNo like"
						+ Constants.PLACEHOLDER;
				list.add("%" + student.getCertNo() + "%");
			}
			//招生途径enrollmentSource
			if (student.getEnrollmentSource() != 0 && minorChannelType==0) {
				hqlConditionExpression += "and enrollmentSource ="
						+ Constants.PLACEHOLDER;
				list.add(student.getEnrollmentSource());
			}
			if (student.getEnrollmentSource() != 0 && minorChannelType!=0) {
				hqlConditionExpression += "and ( enrollmentSource ="
						+ Constants.PLACEHOLDER+" or enrollmentSource ="+ Constants.PLACEHOLDER+" ) ";
				list.add(student.getEnrollmentSource());
				list.add(minorChannelType);//次合作方类型
			}
			//合作方channelId
			if (student.getChannelId() != 0 && minorChannelId==0) {
				hqlConditionExpression += "and channelId ="
						+ Constants.PLACEHOLDER;
				list.add(student.getChannelId());
			}
			if (student.getChannelId() != 0 && minorChannelType!=0) {
				hqlConditionExpression += "and ( channelId ="
						+ Constants.PLACEHOLDER +" or channelId="+ Constants.PLACEHOLDER+" ) ";
				list.add(student.getChannelId());
				list.add(minorChannelId);//次合作方Id
			}
			// 院校
			if (student.getAcademyId() != 0) {
				hqlConditionExpression += "and academyId="
						+ Constants.PLACEHOLDER;
				list.add(student.getAcademyId());
			}
			// 批次
			if (student.getEnrollmentBatchId() != 0) {
				hqlConditionExpression += " and  enrollmentBatchId= "
						+ Constants.PLACEHOLDER;
				list.add(student.getEnrollmentBatchId());
			}
			//多批次
			if(student.getAcademyenrollbatchName()!=null && !"".equals(student.getAcademyenrollbatchName()))
			{
				hqlConditionExpression += " and  enrollmentBatchId in ("
					+ Constants.PLACEHOLDER+ ")";
				list.add("$" +student.getAcademyenrollbatchName());
				
			}
			// 层次
			if (student.getLevelId() != 0) {
				hqlConditionExpression += " and  levelId= "
						+ Constants.PLACEHOLDER;
				list.add(student.getLevelId());
			}
			// 专业
			if (student.getMajorId() != 0) {
				hqlConditionExpression += " and  majorId= "
						+ Constants.PLACEHOLDER;
				list.add(student.getMajorId());
			}
			// 学习中心
			if (student.getBranchId() != 0) {
				hqlConditionExpression += " and  branchId= "
						+ Constants.PLACEHOLDER;
				list.add(student.getBranchId());
			}
			//已缴学费   且已开课的学生才能招生返款
			hqlConditionExpression += " and  tuitionStatus >= "
				+ Constants.PLACEHOLDER;
			list.add(Constants.STU_TUITION_STATUS_SHOU_CI_JIAO_FEI);
			hqlConditionExpression += " and  isStartCourse= "
				+ Constants.PLACEHOLDER;
			list.add(Constants.STU_IS_START_COURSE_TRUE);
			
			// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
			// 如果起始状态ID>0,结束状态ID=0;则为无穷大
			// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
			// 如果都大于0,则取交集
			if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0) {
				hqlConditionExpression += " and  status <"
						+ Constants.PLACEHOLDER;
				list.add(student.getEndStatusId());
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0) {
				hqlConditionExpression += " and  status >"
						+ Constants.PLACEHOLDER;
				list.add(student.getStartStatusId());
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0) {
				hqlConditionExpression += " and  status> "
						+ Constants.PLACEHOLDER + " and status<"
						+ Constants.PLACEHOLDER;
				list.add(student.getStartStatusId());
				list.add(student.getEndStatusId());
			}
			if (!hqlConditionExpression.equals("")) {
				p.setHqlConditionExpression(hqlConditionExpression);
				p.setValues(list.toArray());
			}
		}
		String studentIds = ",";
		Long[] sidsLongs = studentDao.getIDs(p);
		if (sidsLongs == null || sidsLongs.length == 0) 
		{
			return null;
		}
		for (Long id : sidsLongs)
		{
			if (studentIds.startsWith(","))
			{
				studentIds = id + "";
			} 
			else 
			{
				studentIds += "," + id;
			}
		}
		return studentIds;
	}
	
	/*
	 * 根据打款单Id查询该打款单下的缴费单明细集合
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findFeePaymentDetailListByPayCeduAcademyId(int)
	 */
	public List<FeePaymentDetail> findFeePaymentDetailListByPayCeduAcademyId(int payCeduAcademyId) throws Exception
	{
		List<FeePaymentDetail> feepdlist=new ArrayList<FeePaymentDetail>();
		String con=" and orderCeduAcademyId="+Constants.PLACEHOLDER;
		feepdlist=this.feePaymentDetailDao.getByProperty(con, new Object[]{payCeduAcademyId});
		if(feepdlist!=null && feepdlist.size()>0)
		{
			for(FeePaymentDetail feeObj:feepdlist)
			{
				//缴费单的编号和缴费方式
				if(feeObj.getFeePaymentId()>0 && feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId())!=null)
				{
					feeObj.setFeeWayId(feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId()).getFeeWayId());
					feeObj.setPaymentCode(feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId()).getCode());
				}
				else
				{
					feeObj.setPaymentCode("");
				}
				//学生相关
				Student stu=studentBiz.findStudentById(feeObj.getStudentId());
				if(feeObj.getStudentId()>0 && stu!=null)
				{
					//学生姓名
					feeObj.setStudentName(stu.getName());
					//院校
					if(stu.getAcademyId()>0 && academyBiz.findAcademyById(stu.getAcademyId())!=null)
					{
						feeObj.setSchoolName(academyBiz.findAcademyById(stu.getAcademyId()).getName());
					}
					//学习中心
					if(stu.getBranchId()>0 && branchBiz.findBranchById(stu.getBranchId())!=null)
					{
						feeObj.setBranchName(branchBiz.findBranchById(stu.getBranchId()).getName());
					}
					//批次
					if(stu.getEnrollmentBatchId()>0 && academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId())!=null)
					{
						feeObj.setAcademyenrollbatchName(academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId()).getEnrollmentName());
					}
					//层次
					if(stu.getLevelId()>0 && levelbiz.findLevelById(stu.getLevelId())!=null)
					{
						feeObj.setLevelName(levelbiz.findLevelById(stu.getLevelId()).getName());
					}
					//专业
					if(stu.getMajorId()>0 && majorbiz.findMajorById(stu.getMajorId())!=null)
					{
						feeObj.setMajorName(majorbiz.findMajorById(stu.getMajorId()).getName());
					}
					
				}
				//费用科目
				if(feeObj.getFeeSubjectId()>0 && feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId())!=null)
				{
					feeObj.setPaymentSubjectName(feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId()).getName());
				}
			}
		}
		return feepdlist;
	}
	
	/*
	 * 按条件查询缴费单明细数量（分页）(汇款总部确认（总部）中的(pos直汇总部))
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findFeePaymentDetailCountByPagePosCedu(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.finance.FeePayment, net.cedu.entity.crm.Student, java.lang.String, java.lang.String, net.cedu.model.page.PageResult)
	 */
	public int findFeePaymentDetailCountByPagePosCedu(FeePaymentDetail feePaymentDetail,FeePayment feePayment,Student student,String starttime,String endtime,PageResult<FeePaymentDetail> pr) throws Exception
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List list = new ArrayList();
		if (feePaymentDetail != null)
		{						
			// 缴费单状态
			if (feePaymentDetail.getStatus() != 0) {
				hqlparam += " and status ="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getStatus());
			}
			//缴费单起止状态判断
			if (feePaymentDetail.getStartStatusId() == 0 && feePaymentDetail.getEndStatusId() > 0) 
			{
				hqlparam += " and  status <= "
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getEndStatusId());
			}
			if (feePaymentDetail.getStartStatusId() > 0 && feePaymentDetail.getEndStatusId() == 0) 
			{
				hqlparam += " and  status >= "
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getStartStatusId());
			}
			if (feePaymentDetail.getStartStatusId() > 0 && feePaymentDetail.getEndStatusId() > 0) 
			{
				hqlparam += " and  status>=  "
						+ Constants.PLACEHOLDER + " and status<= "
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getStartStatusId());
				list.add(feePaymentDetail.getEndStatusId());
			}
			//缴费科目
			if (feePaymentDetail.getFeeSubjectId()!=0) 
			{
				hqlparam += " and  feeSubjectId = "
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getFeeSubjectId());
			}
			//缴费开始时间
			if (starttime!=null && !starttime.equals("")) 
			{
				hqlparam += " and  createdTime >= "
						+ Constants.PLACEHOLDER;
				list.add(starttime);
			}
			// //缴费结束时间
			if (endtime!=null && !endtime.equals("")) 
			{
				hqlparam += " and  createdTime <= "
						+ Constants.PLACEHOLDER;
				list.add(endtime);
			}			
			
		}
//		String feePaymentIds=findFeePaymentIds(feePayment,student);
//		//缴费单Ids
//		if (feePaymentIds != null && !feePaymentIds.equals("")) 
//		{
//			hqlparam += " and feePaymentId in ("
//					+ Constants.PLACEHOLDER + ")";
//			list.add("$" + feePaymentIds);
//		}
//		else
//		{
//			return 0;
//		}
		//缴费单(如果没有查询条件则跳过)
		String feepaymentHql = getFeePaymentHql(feePayment,student);
		if(feepaymentHql!="select id from FeePayment where 1=1 ")
		{
			hqlparam += " and feePaymentId in ("+feepaymentHql+")";
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}

		return feePaymentDetailDao.getCounts(p);
		
	}
	
	/*
	 * 获取查询缴费单hql语句(in堆栈溢出解决方案)
	 */
	private String getFeePaymentHql(FeePayment feePayment,Student student)
	{
		String hql = "select id from FeePayment where 1=1 ";
		if(feePayment!=null)
		{
			// 缴费方式
			if (feePayment.getFeeWayId() != 0) 
			{
				hql += " and feeWayId = " + feePayment.getFeeWayId();
			}
			// 缴费单号
			if (feePayment.getCode() != null && feePayment.getCode().length()>0) 
			{
				hql += " and code = '" + feePayment.getCode() + "'";
			}
			if(student!=null)
			{
				String stuHql = "select id from Student where 1=1 ";
				// 姓名
				if (student.getName() != null && !student.getName().equals("")) {
					stuHql += " and name like '%"+student.getName()+"%'";
				}
				// 证件号
				if (student.getCertNo() != null && !"".equals(student.getCertNo())) {
					stuHql += " and certNo like '%"+student.getCertNo()+"%'";
				}
				//招生途径enrollmentSource
				if (student.getEnrollmentSource() != 0) {
					stuHql += " and enrollmentSource = " + student.getEnrollmentSource();
				}
				//合作方channelId
				if (student.getChannelId() != 0) {
					stuHql += " and channelId = " + student.getChannelId();
				}
				// 院校
				if (student.getAcademyId() != 0) {
					stuHql += " and academyId= " + student.getAcademyId();
				}
				// 批次
				if (student.getEnrollmentBatchId() != 0) {
					stuHql += " and  enrollmentBatchId= " + student.getEnrollmentBatchId();
				}
				//多批次
				if(student.getAcademyenrollbatchName()!=null && !"".equals(student.getAcademyenrollbatchName()))
				{
					stuHql += " and  enrollmentBatchId in (" + student.getAcademyenrollbatchName() + ")";
				}
				// 层次
				if (student.getLevelId() != 0) {
					stuHql += " and  levelId= " + student.getLevelId();
				}
				// 专业
				if (student.getMajorId() != 0) {
					stuHql += " and  majorId= " + student.getMajorId();
				}
				// 学习中心
				if (student.getBranchId() != 0) {
					stuHql += " and  branchId= " + student.getBranchId();
				}
				// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
				// 如果起始状态ID>0,结束状态ID=0;则为无穷大
				// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
				// 如果都大于0,则取交集
				if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0) {
					stuHql += " and  status <" + student.getEndStatusId();
				}
				if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0) {
					stuHql += " and  status >" + student.getStartStatusId();
				}
				if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0) {
					stuHql += " and  status> " + student.getStartStatusId()
							+ " and status< " + student.getEndStatusId();
				}
				//如果没有查询条件则跳过
				if(stuHql!="select id from Student where 1=1 ")
				{
					hql += " and studentId in ("+stuHql+")";
				}
			}
		}
		return hql;
	}
	
	/*
	 * 按条件查询缴费单明细集合（分页）(汇款总部确认（总部）中的(pos直汇总部))
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findFeePaymentDetailListByPagePosCedu(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.finance.FeePayment, net.cedu.entity.crm.Student, java.lang.String, java.lang.String, net.cedu.model.page.PageResult)
	 */
	public List<FeePaymentDetail> findFeePaymentDetailListByPagePosCedu(FeePaymentDetail feePaymentDetail,FeePayment feePayment,Student student,String starttime,String endtime,PageResult<FeePaymentDetail> pr) throws Exception
	{
		List<FeePaymentDetail> feepdlist=null;
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List list = new ArrayList();
		if (feePaymentDetail != null)
		{						
			// 缴费单状态
			if (feePaymentDetail.getStatus() != 0)
			{
				hqlparam += " and status ="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getStatus());
			}
			//缴费单起止状态判断
			if (feePaymentDetail.getStartStatusId() == 0 && feePaymentDetail.getEndStatusId() > 0) 
			{
				hqlparam += " and  status <= "
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getEndStatusId());
			}
			if (feePaymentDetail.getStartStatusId() > 0 && feePaymentDetail.getEndStatusId() == 0) 
			{
				hqlparam += " and  status >= "
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getStartStatusId());
			}
			if (feePaymentDetail.getStartStatusId() > 0 && feePaymentDetail.getEndStatusId() > 0) 
			{
				hqlparam += " and  status>= "
						+ Constants.PLACEHOLDER + " and status<= "
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getStartStatusId());
				list.add(feePaymentDetail.getEndStatusId());
			}
			
			//缴费开始时间
			if (starttime!=null && !starttime.equals("")) 
			{
				hqlparam += " and  createdTime >= "
						+ Constants.PLACEHOLDER;
				list.add(starttime);
			}
			// //缴费结束时间
			if (endtime!=null && !endtime.equals("")) 
			{
				hqlparam += " and  createdTime <= "
						+ Constants.PLACEHOLDER;
				list.add(endtime);
			}
			//缴费科目
			if (feePaymentDetail.getFeeSubjectId()!=0) 
			{
				hqlparam += " and  feeSubjectId = "
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getFeeSubjectId());
			}
		}
//		String feePaymentIds=findFeePaymentIds(feePayment,student);
//		//缴费单Ids
//		if (feePaymentIds != null && !feePaymentIds.equals("")) 
//		{
//			hqlparam += " and feePaymentId in ("
//					+ Constants.PLACEHOLDER + ")";
//			list.add("$" + feePaymentIds);
//		}
//		else
//		{
//			return null;
//		}
		//缴费单(如果没有查询条件则跳过)
		String feepaymentHql = getFeePaymentHql(feePayment,student);
		if(feepaymentHql!="select id from FeePayment where 1=1 ")
		{
			hqlparam += " and feePaymentId in ("+feepaymentHql+")";
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		Long[] feeIds = feePaymentDetailDao.getIDs(p);
		if (feeIds != null && feeIds.length != 0) 
		{
			feepdlist=new ArrayList<FeePaymentDetail>();
			for (int i = 0; i < feeIds.length; i++) 
			{
				// 内存获取
				FeePaymentDetail feeObj = feePaymentDetailDao.findById(Integer.valueOf(feeIds[i].toString()));
				if(feeObj!=null)
				{
					//缴费单的编号和缴费方式
					FeePayment feePayment_=feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId());
					if(feeObj.getFeePaymentId()>0 && feePayment!=null)
					{
						feeObj.setFeeWayId(feePayment_.getFeeWayId());
						feeObj.setPaymentCode(feePayment_.getCode());
						feeObj.setFeePayment(feePayment);
					}
					else
					{
						feeObj.setPaymentCode("");
					}
					//学生相关
					Student stu=studentDao.findById(feeObj.getStudentId());
					if(feeObj.getStudentId()>0 && stu!=null)
					{
						//学生姓名
						feeObj.setStudentName(stu.getName());
						//院校
						Academy academy = academyBiz.findAcademyById(stu.getAcademyId());
						if(stu.getAcademyId()>0 && academy!=null)
						{
							feeObj.setSchoolName(academy.getName());
						}
						//学习中心
						Branch branch=branchBiz.findBranchById(stu.getBranchId());
						if(stu.getBranchId()>0 && branch!=null)
						{
							feeObj.setBranchName(branch.getName());
						}
						//批次
						AcademyEnrollBatch academyEnrollBatch=academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId());
						if(stu.getEnrollmentBatchId()>0 && academyEnrollBatch!=null)
						{
							feeObj.setAcademyenrollbatchName(academyEnrollBatch.getEnrollmentName());
						}
						//层次
						Level level=levelbiz.findLevelById(stu.getLevelId());
						if(stu.getLevelId()>0 && level!=null)
						{
							feeObj.setLevelName(level.getName());
						}
						//专业
						Major major=majorbiz.findMajorById(stu.getMajorId());
						if(stu.getMajorId()>0 && major!=null)
						{
							feeObj.setMajorName(major.getName());
						}
						
					}
					//费用科目
					FeeSubject feeSubject=feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId());
					if(feeObj.getFeeSubjectId()>0 && feeSubject!=null)
					{
						feeObj.setPaymentSubjectName(feeSubject.getName());
					}
					//缴费金额
					feeObj.setJiaofeiValue((new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())).subtract(new BigDecimal(feeObj.getRefundAmount()))).doubleValue());
					//打款金额
					feeObj.setPayCeduAcademy(Double.valueOf(new BigDecimal(feeObj.getMoneyToPay()).subtract(new BigDecimal(feeObj.getAcademyDiscount()).add(new BigDecimal(feeObj.getAcademyCeduDiscount()))).toString()));
					//汇款金额
					feeObj.setPayBranchCedu(Double.valueOf(new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())).toString()));
					
					feepdlist.add(feeObj);
				}
			}
		}
		
		return feepdlist;
	}
	
	/*
	 * 修改缴费单及其明细
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#updateFeePaymentAndDetailList(net.cedu.entity.finance.FeePayment, java.util.List)
	 */
	public boolean updateFeePaymentAndDetailList(FeePayment feePayment,List<FeePaymentDetail> feePaymentDetailList) throws Exception
	{
		boolean isback=false;
		if(feePayment!=null && feePaymentDetailList!=null && feePaymentDetailList.size()>0)
		{
			isback=this.feePaymentBiz.updateFeePayment(feePayment);
			for(FeePaymentDetail fpd:feePaymentDetailList)
			{
				this.feePaymentDetailDao.update(fpd);
			}
		}
		return isback;
	}
	
	/*
	 *  根据费用科目Id和学生Id、缴费单明细状态查询缴费单明细集合(缴费（学费）模块使用)
	 *  
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findFeePaymentDetailListByStudentIdFeeSubjectId(int, int, int)
	 */
	public List<FeePaymentDetail> findFeePaymentDetailListByStudentIdFeeSubjectIdStartStatusIdEndStatusId(FeePaymentDetail feePaymentDetail)throws Exception
	{
		List<FeePaymentDetail> fpdetaillist=null;
		PageParame p = new PageParame();
		String hqlparam = "";
		List list = new ArrayList();
		if(feePaymentDetail!=null)
		{
			//学生ID
			if(feePaymentDetail.getStudentId()>0)
			{
				hqlparam += " and studentId= " + Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getStudentId());
			}
			//费用科目ID
			if(feePaymentDetail.getFeeSubjectId()>0)
			{
				hqlparam += " and feeSubjectId= " + Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getFeeSubjectId());
			}
			//缴费单起止状态判断
			if (feePaymentDetail.getStartStatusId() == 0 && feePaymentDetail.getEndStatusId() != 0) 
			{
				hqlparam += " and  status <= "
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getEndStatusId());
			}
			if (feePaymentDetail.getStartStatusId() != 0 && feePaymentDetail.getEndStatusId() == 0) 
			{
				hqlparam += " and  status >= "
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getStartStatusId());
			}
			if (feePaymentDetail.getStartStatusId() != 0 && feePaymentDetail.getEndStatusId() != 0 ) 
			{
				//and、or 关系
				if(feePaymentDetail.getEndStatusId()>=feePaymentDetail.getStartStatusId())
				{
					hqlparam += " and  status>= "
							+ Constants.PLACEHOLDER + " and status<= "
							+ Constants.PLACEHOLDER;
					list.add(feePaymentDetail.getStartStatusId());
					list.add(feePaymentDetail.getEndStatusId());
				}
				else 
				{
					hqlparam += " and ( status>= "
						+ Constants.PLACEHOLDER + " or status<= "
						+ Constants.PLACEHOLDER+" ) ";
					list.add(feePaymentDetail.getStartStatusId());
					list.add(feePaymentDetail.getEndStatusId());
				}
			}
			
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
			Long[] feeIds = feePaymentDetailDao.getIDs(p);
			if (feeIds != null && feeIds.length != 0) 
			{
				fpdetaillist=new ArrayList<FeePaymentDetail>();;
				for (int i = 0; i < feeIds.length; i++) 
				{
					// 内存获取
					FeePaymentDetail feeObj = feePaymentDetailDao.findById(Integer.valueOf(feeIds[i].toString()));
					if (feeObj != null) 
					{
						FeePaymentDetail obj = feeObj;
						
						//费用科目
						if(obj.getFeeSubjectId()!=0 && this.feeSubjectBiz.findFeeSubjectById(obj.getFeeSubjectId())!=null)
						{
							obj.setPaymentSubjectName(this.feeSubjectBiz.findFeeSubjectById(obj.getFeeSubjectId()).getName());
						}
						fpdetaillist.add(obj);
					}
				}
			}
		}
		return fpdetaillist;
	}
	/**
	 * 通过主键Ids查询缴费单明细字符串
	  * @see net.cedu.biz.finance.FeePaymentDetailBiz#findFeePaymentDetailByIds(java.lang.String)
	 */
	public List<FeePaymentDetail> findFeePaymentDetailByIds(String ids)
			throws Exception {
		
		return feePaymentDetailDao.getByProperty(" and id in ("+Constants.PLACEHOLDER+")", new Object[]{"$"+ids});
	}
	
	/*
	 * 查询符合院校返款的所有缴费单明细数量（添加院校返款单）（分页）
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findFeePaymentDetailCountByPageForAcademyRebate(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.crm.Student, net.cedu.model.page.PageResult)
	 */
	public int findFeePaymentDetailCountByPageForAcademyRebate(FeePaymentDetail feePaymentDetail,Student student,String feepdIds,PageResult<FeePaymentDetail> pr) throws Exception
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feePaymentDetail != null)
		{						
			// 缴费单状态
			if (feePaymentDetail.getStatus() != 0) {
				hqlparam += " and status ="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getStatus());
			}
			//过滤掉已选择的缴费单明细
			if(feepdIds!=null && !feepdIds.equals(""))
			{
				hqlparam += " and id not in ( "
					+ Constants.PLACEHOLDER+" ) ";
				list.add("$"+feepdIds);
			}
			//费用科目
			if (feePaymentDetail.getFeeSubjectId()!= 0) {
				hqlparam += " and feeSubjectId ="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getFeeSubjectId());
			}
			//学生ID
			if (checkStudentInfo(student)) 
			{
//				String studentIds=this.findStudentIds(student);
//				//学生Ids
//				if (studentIds != null && !studentIds.equals("")) 
//				{
//					hqlparam += " and studentId in ("
//							+ Constants.PLACEHOLDER + ")";
//					list.add("$" + studentIds);
//				}
//				else
//				{
//					return 0;
//				}
				//学生Ids__Hql
				String stuHql=this.findStudentIds_hql(student);
				if (stuHql != null && !stuHql.equals("select id from Student where 1=1 ")) 
				{
					hqlparam += " and studentId in ("+ stuHql + ")";
				}
				else
				{
					return 0;
				}
				
				//查询该院校、该批次下的所有要返款的费用科目
				List<AcademyBatchFeeSubject> abfslist=academyBatchFeeSubjectBiz.findByAcademyAndBatch(student.getAcademyId(), student.getEnrollmentBatchId());
				String feesubIds=",";
				if(abfslist!=null && abfslist.size()>0)
				{
					for(AcademyBatchFeeSubject abfs:abfslist)
					{
						if (feesubIds.startsWith(","))
						{
							feesubIds = abfs.getFeeSubjectId() + "";
						} 
						else 
						{
							feesubIds += "," + abfs.getFeeSubjectId();
						}
					}
					if(feesubIds!=null && !feesubIds.startsWith(","))
					{
						hqlparam += " and feeSubjectId in ("
							+ Constants.PLACEHOLDER + ")";
						list.add("$" + feesubIds);
					}
				}
				else
				{
					return 0;
				}
			}
			else
			{
				return 0;
			}
			
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}

		return feePaymentDetailDao.getCounts(p);
		
	}
	
	/*
	 * 查询符合院校返款的所有缴费单明细集合（添加院校返款单）（分页）
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findFeePaymentDetailListByPageForAcademyRebate(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.crm.Student, net.cedu.model.page.PageResult)
	 */
	public List<FeePaymentDetail> findFeePaymentDetailListByPageForAcademyRebate(FeePaymentDetail feePaymentDetail,Student student,String feepdIds,PageResult<FeePaymentDetail> pr) throws Exception
	{
		List<FeePaymentDetail> feepdlist=null;
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feePaymentDetail != null)
		{						
			// 缴费单状态
			if (feePaymentDetail.getStatus() != 0) 
			{
				hqlparam += " and status ="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getStatus());
			}
			//过滤掉已选择的缴费单明细
			if(feepdIds!=null && !feepdIds.equals(""))
			{
				hqlparam += " and id not in ( "
					+ Constants.PLACEHOLDER+" ) ";
				list.add("$"+feepdIds);
			}
			//费用科目
			if (feePaymentDetail.getFeeSubjectId()!= 0) {
				hqlparam += " and feeSubjectId ="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getFeeSubjectId());
			}
			//学生ID
			if (checkStudentInfo(student)) 
			{
//				String studentIds=this.findStudentIds(student);
//				//学生Ids
//				if (studentIds != null && !studentIds.equals("")) 
//				{
//					hqlparam += " and studentId in ("
//							+ Constants.PLACEHOLDER + ")";
//					list.add("$" + studentIds);
//				}
//				else
//				{
//					return null;
//				}
				//学生Ids__Hql
				String stuHql=this.findStudentIds_hql(student);
				if (stuHql != null && !stuHql.equals("select id from Student where 1=1 ")) 
				{
					hqlparam += " and studentId in ("+ stuHql + ")";
				}
				else
				{
					return null;
				}
				//查询该院校、该批次下的所有要返款的费用科目
				List<AcademyBatchFeeSubject> abfslist=academyBatchFeeSubjectBiz.findByAcademyAndBatch(student.getAcademyId(), student.getEnrollmentBatchId());
				String feesubIds=",";
				if(abfslist!=null && abfslist.size()>0)
				{
					for(AcademyBatchFeeSubject abfs:abfslist)
					{
						if (feesubIds.startsWith(","))
						{
							feesubIds = abfs.getFeeSubjectId() + "";
						} 
						else 
						{
							feesubIds += "," + abfs.getFeeSubjectId();
						}
					}
					if(feesubIds!=null && !feesubIds.startsWith(","))
					{
						hqlparam += " and feeSubjectId in ("
							+ Constants.PLACEHOLDER + ")";
						list.add("$" + feesubIds);
					}
				}
				else
				{
					return null;
				}
			}
			else
			{
				return null;
			}
			
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		Long[] feeIds = feePaymentDetailDao.getIDs(p);
		if (feeIds != null && feeIds.length != 0) 
		{
			feepdlist=new ArrayList<FeePaymentDetail>();
			for (int i = 0; i < feeIds.length; i++) 
			{
				// 内存获取
				FeePaymentDetail feeObj = feePaymentDetailDao.findById(Integer.valueOf(feeIds[i].toString()));
				if(feeObj!=null)
				{
					//缴费单的编号和缴费方式
					if(feeObj.getFeePaymentId()>0 && feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId())!=null)
					{
						feeObj.setPaymentCode(feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId()).getCode());
					}
					else
					{
						feeObj.setPaymentCode("");
					}
					//学生相关
					Student stu=studentBiz.findStudentById(feeObj.getStudentId());
					if(feeObj.getStudentId()>0 && stu!=null)
					{
						//学生姓名
						feeObj.setStudentName(stu.getName());
						//院校
						if(stu.getAcademyId()>0 && academyBiz.findAcademyById(stu.getAcademyId())!=null)
						{
							feeObj.setSchoolName(academyBiz.findAcademyById(stu.getAcademyId()).getName());
						}
						//学习中心
						if(stu.getBranchId()>0 && branchBiz.findBranchById(stu.getBranchId())!=null)
						{
							feeObj.setBranchName(branchBiz.findBranchById(stu.getBranchId()).getName());
						}
						//批次
						if(stu.getEnrollmentBatchId()>0 && academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId())!=null)
						{
							feeObj.setAcademyenrollbatchName(academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId()).getEnrollmentName());
						}
						//层次
						if(stu.getLevelId()>0 && levelbiz.findLevelById(stu.getLevelId())!=null)
						{
							feeObj.setLevelName(levelbiz.findLevelById(stu.getLevelId()).getName());
						}
						//专业
						if(stu.getMajorId()>0 && majorbiz.findMajorById(stu.getMajorId())!=null)
						{
							feeObj.setMajorName(majorbiz.findMajorById(stu.getMajorId()).getName());
						}
						
					}
					//费用科目
					if(feeObj.getFeeSubjectId()>0 && feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId())!=null)
					{
						feeObj.setPaymentSubjectName(feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId()).getName());
					}
					//缴费金额
					feeObj.setJiaofeiValue((new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())).subtract(new BigDecimal(feeObj.getRefundAmount()))).doubleValue());
					feepdlist.add(feeObj);
				}
			}
		}
		return feepdlist;
		
	}
	
	/*
	 * 查询需要院校返款的所有缴费单明细集合（添加院校返款单）（分页）
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findFeePaymentDetailListByPageForNowAddAcademyRebate(java.lang.String, net.cedu.model.page.PageResult)
	 */
	public List<FeePaymentDetail> findFeePaymentDetailListByPageForNowAddAcademyRebate(String feepdIds,PageResult<FeePaymentDetail> pr) throws Exception
	{
		List<FeePaymentDetail> feepdlist=null;
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feepdIds!= null && feepdIds.length()>0)
		{						
			hqlparam += " and id in ("
				+ Constants.PLACEHOLDER + ")";
			list.add("$" + feepdIds);
		}
		else
		{
			return null;
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		Long[] feeIds = feePaymentDetailDao.getIDs(p);
		if (feeIds != null && feeIds.length != 0) 
		{
			feepdlist=new ArrayList<FeePaymentDetail>();
			for (int i = 0; i < feeIds.length; i++) 
			{
				// 内存获取
				FeePaymentDetail feeObj = feePaymentDetailDao.findById(Integer.valueOf(feeIds[i].toString()));
				if(feeObj!=null)
				{
					//缴费单的编号
					if(feeObj.getFeePaymentId()>0 && feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId())!=null)
					{
						feeObj.setPaymentCode(feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId()).getCode());
					}
					else
					{
						feeObj.setPaymentCode("");
					}
					//学生相关
					Student stu=studentBiz.findStudentById(feeObj.getStudentId());
					if(feeObj.getStudentId()>0 && stu!=null)
					{
						//学生姓名
						feeObj.setStudentName(stu.getName());
						//院校
						if(stu.getAcademyId()>0 && academyBiz.findAcademyById(stu.getAcademyId())!=null)
						{
							feeObj.setSchoolName(academyBiz.findAcademyById(stu.getAcademyId()).getName());
						}
						//学习中心
						if(stu.getBranchId()>0 && branchBiz.findBranchById(stu.getBranchId())!=null)
						{
							feeObj.setBranchName(branchBiz.findBranchById(stu.getBranchId()).getName());
						}
						//批次
						if(stu.getEnrollmentBatchId()>0 && academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId())!=null)
						{
							feeObj.setAcademyenrollbatchName(academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId()).getEnrollmentName());
						}
						//层次
						if(stu.getLevelId()>0 && levelbiz.findLevelById(stu.getLevelId())!=null)
						{
							feeObj.setLevelName(levelbiz.findLevelById(stu.getLevelId()).getName());
						}
						//专业
						if(stu.getMajorId()>0 && majorbiz.findMajorById(stu.getMajorId())!=null)
						{
							feeObj.setMajorName(majorbiz.findMajorById(stu.getMajorId()).getName());
						}
						
					}
					//费用科目
					if(feeObj.getFeeSubjectId()>0 && feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId())!=null)
					{
						feeObj.setPaymentSubjectName(feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId()).getName());
					}
					//缴费金额
					feeObj.setJiaofeiValue((new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())).subtract(new BigDecimal(feeObj.getRefundAmount()))).doubleValue());
					feepdlist.add(feeObj);
				}
			}
		}
		return feepdlist;
		
	}
	
	/*
	 * 查询院校返款单的所有缴费单明细数量（查看院校返款单）（分页）
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findFeePaymentDetailCountByPageForViewAcademyRebate(net.cedu.entity.finance.FeePaymentDetail, net.cedu.model.page.PageResult)
	 */
	public int findFeePaymentDetailCountByPageForViewAcademyRebate(FeePaymentDetail feePaymentDetail,PageResult<FeePaymentDetail> pr) throws Exception
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feePaymentDetail != null)
		{						
			// 院校返款单Id
			if (feePaymentDetail.getOrderAcademyCeduId() != 0) {
				hqlparam += " and orderAcademyCeduId ="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getOrderAcademyCeduId());
			}
			// 院校年度返款单Id
			if (feePaymentDetail.getAcademyYearRebateId() != 0) {
				hqlparam += " and academyYearRebateId ="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getAcademyYearRebateId());
			}
			
		}
		else
		{
			return 0;
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}

		return feePaymentDetailDao.getCounts(p);
		
	}
	
	/*
	 * 查询院校返款单的所有缴费单明细集合（查看院校返款单）（分页）
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findFeePaymentDetailListByPageForViewAcademyRebate(net.cedu.entity.finance.FeePaymentDetail, net.cedu.model.page.PageResult)
	 */
	public List<FeePaymentDetail> findFeePaymentDetailListByPageForViewAcademyRebate(FeePaymentDetail feePaymentDetail,PageResult<FeePaymentDetail> pr) throws Exception
	{
		List<FeePaymentDetail> feepdlist=null;
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feePaymentDetail != null)
		{						
			// 院校返款单Id
			if (feePaymentDetail.getOrderAcademyCeduId() != 0) {
				hqlparam += " and orderAcademyCeduId ="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getOrderAcademyCeduId());
			}	
			// 院校年度返款单Id
			if (feePaymentDetail.getAcademyYearRebateId() != 0) {
				hqlparam += " and academyYearRebateId ="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getAcademyYearRebateId());
			}
		}
		else
		{
			return null;
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		Long[] feeIds = feePaymentDetailDao.getIDs(p);
		if (feeIds != null && feeIds.length != 0) 
		{
			feepdlist=new ArrayList<FeePaymentDetail>();
			for (int i = 0; i < feeIds.length; i++) 
			{
				// 内存获取
				FeePaymentDetail feeObj = feePaymentDetailDao.findById(Integer.valueOf(feeIds[i].toString()));
				if(feeObj!=null)
				{
					//缴费单的编号和缴费方式
					if(feeObj.getFeePaymentId()>0 && feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId())!=null)
					{
						feeObj.setPaymentCode(feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId()).getCode());
					}
					else
					{
						feeObj.setPaymentCode("");
					}
					//学生相关
					Student stu=studentBiz.findStudentById(feeObj.getStudentId());
					if(feeObj.getStudentId()>0 && stu!=null)
					{
						//学生姓名
						feeObj.setStudentName(stu.getName());
						//院校
						if(stu.getAcademyId()>0 && academyBiz.findAcademyById(stu.getAcademyId())!=null)
						{
							feeObj.setSchoolName(academyBiz.findAcademyById(stu.getAcademyId()).getName());
						}
						//学习中心
						if(stu.getBranchId()>0 && branchBiz.findBranchById(stu.getBranchId())!=null)
						{
							feeObj.setBranchName(branchBiz.findBranchById(stu.getBranchId()).getName());
						}
						//批次
						if(stu.getEnrollmentBatchId()>0 && academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId())!=null)
						{
							feeObj.setAcademyenrollbatchName(academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId()).getEnrollmentName());
						}
						//层次
						if(stu.getLevelId()>0 && levelbiz.findLevelById(stu.getLevelId())!=null)
						{
							feeObj.setLevelName(levelbiz.findLevelById(stu.getLevelId()).getName());
						}
						//专业
						if(stu.getMajorId()>0 && majorbiz.findMajorById(stu.getMajorId())!=null)
						{
							feeObj.setMajorName(majorbiz.findMajorById(stu.getMajorId()).getName());
						}
					}
					//费用科目
					if(feeObj.getFeeSubjectId()>0 && feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId())!=null)
					{
						feeObj.setPaymentSubjectName(feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId()).getName());
					}
					//院校已返款金额
					feeObj.setMoneyToCedu(feeObj.getAcademyRebateAddMoney().add(new BigDecimal(feeObj.getPayAcademyCedu())).subtract(feeObj.getAcademyYearRebateAddMoney()));
					//缴费金额
					feeObj.setJiaofeiValue((new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())).subtract(new BigDecimal(feeObj.getRefundAmount()))).doubleValue());
					feepdlist.add(feeObj);
				}
			}
		}
		return feepdlist;
		
	}
	
	/*
	 * 根据院校返款单Id查询缴费单明细列表
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findFeePaymentDetailListByOrderAcademyCeduId(int)
	 */
	public List<FeePaymentDetail> findFeePaymentDetailListByOrderAcademyCeduId(int orderAcademyCeduId)throws Exception
	{
		List<FeePaymentDetail> list=null;
		if(orderAcademyCeduId!=0)
		{
			list=feePaymentDetailDao.getByProperty("orderAcademyCeduId",orderAcademyCeduId);
		}
		return list;
	}
	
	/*
	 * 根据院校年度返款单Id查询缴费单明细列表
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findFeePaymentDetailListByOrderAcademyCeduId(int)
	 */
	public List<FeePaymentDetail> findFpdListByOrderAcademyCeduIdForAcademyYearRebate(int orderAcademyCeduId)throws Exception
	{
		List<FeePaymentDetail> list=null;
		if(orderAcademyCeduId!=0)
		{
			list=feePaymentDetailDao.getByProperty("academyYearRebateId",orderAcademyCeduId);
		}
		return list;
	}
	
	/*
	 * 查询符合招生返款的所有缴费单明细数量（添加招生返款单）（分页）-------暂时只对学费进行招生返款
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findFeePaymentDetailCountByPageForChannelRebate(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.crm.Student, net.cedu.model.page.PageResult)
	 */
	public int findFeePaymentDetailCountByPageForChannelRebate(FeePaymentDetail feePaymentDetail,Student student,PageResult<FeePaymentDetail> pr) throws Exception
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feePaymentDetail != null)
		{						
			// 缴费单招生返款状态
			if (feePaymentDetail.getRebateStatus() != 0) {
				hqlparam += " and rebate_status ="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getRebateStatus());
			}
			//费用科目    暂时只对学费进行招生返款
			hqlparam += " and feeSubjectId ="+ Constants.PLACEHOLDER;
			list.add(FeeSubjectEnum.TuitionFee.value());
			
			//退费中的不能进行招生返款__2012-04-03
			hqlparam += " and refundLock <> "+ Constants.PLACEHOLDER;
			list.add(Constants.LOCKING_TRUE);
			
			
			//学生ID
			if (checkStudentInfo(student)) 
			{
				String studentIds=this.findStudentIdsForChannel(student);
				//学生Ids
				if (studentIds != null && !studentIds.equals("")) 
				{
					hqlparam += " and studentId in ("
							+ Constants.PLACEHOLDER + ")";
					list.add("$" + studentIds);
				}
				else
				{
					return 0;
				}
				
			}
			else
			{
				return 0;
			}
			
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}

		return feePaymentDetailDao.getCounts(p);
		
	}
	
	/*
	 * 查询符合招生返款的所有缴费单明细集合（添加招生返款单）（分页）-------暂时只对学费进行招生返款
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findFeePaymentDetailListByPageForChannelRebate(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.crm.Student, net.cedu.model.page.PageResult)
	 */
	public List<FeePaymentDetail> findFeePaymentDetailListByPageForChannelRebate(FeePaymentDetail feePaymentDetail,Student student,PageResult<FeePaymentDetail> pr) throws Exception
	{
		List<FeePaymentDetail> feepdlist=null;
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feePaymentDetail != null)
		{						
			// 缴费单招生返款状态
			if (feePaymentDetail.getRebateStatus() != 0) {
				hqlparam += " and rebate_status ="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getRebateStatus());
			}
			//费用科目    暂时只对学费进行招生返款
			hqlparam += " and feeSubjectId ="+ Constants.PLACEHOLDER;
			list.add(FeeSubjectEnum.TuitionFee.value());
			
			//退费中的不能进行招生返款__2012-04-03
			hqlparam += " and refundLock <> "+ Constants.PLACEHOLDER;
			list.add(Constants.LOCKING_TRUE);
			
			//学生ID
			if (checkStudentInfo(student)) 
			{
				String studentIds=this.findStudentIdsForChannel(student);
				//学生Ids
				if (studentIds != null && !studentIds.equals("")) 
				{
					hqlparam += " and studentId in ("
							+ Constants.PLACEHOLDER + ")";
					list.add("$" + studentIds);
				}
				else
				{
					return null;
				}
				
			}
			else
			{
				return null;
			}
			
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}

		Long[] feeIds = feePaymentDetailDao.getIDs(p);
		if (feeIds != null && feeIds.length != 0) 
		{
			feepdlist=new ArrayList<FeePaymentDetail>();
			for (int i = 0; i < feeIds.length; i++) 
			{
				// 内存获取
				FeePaymentDetail feeObj = feePaymentDetailDao.findById(Integer.valueOf(feeIds[i].toString()));
				if(feeObj!=null)
				{
					//缴费单的编号
					if(feeObj.getFeePaymentId()>0 && feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId())!=null)
					{
						feeObj.setPaymentCode(feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId()).getCode());
					}
					else
					{
						feeObj.setPaymentCode("");
					}
					//学生相关
					Student stu=studentBiz.findStudentById(feeObj.getStudentId());
					if(feeObj.getStudentId()>0 && stu!=null)
					{
						//学生姓名
						feeObj.setStudentName(stu.getName());
						//院校
						if(stu.getAcademyId()>0 && academyBiz.findAcademyById(stu.getAcademyId())!=null)
						{
							feeObj.setSchoolName(academyBiz.findAcademyById(stu.getAcademyId()).getName());
						}
						//学习中心
						if(stu.getBranchId()>0 && branchBiz.findBranchById(stu.getBranchId())!=null)
						{
							feeObj.setBranchName(branchBiz.findBranchById(stu.getBranchId()).getName());
						}
						//批次
						if(stu.getEnrollmentBatchId()>0 && academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId())!=null)
						{
							feeObj.setAcademyenrollbatchName(academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId()).getEnrollmentName());
						}
						//层次
						if(stu.getLevelId()>0 && levelbiz.findLevelById(stu.getLevelId())!=null)
						{
							feeObj.setLevelName(levelbiz.findLevelById(stu.getLevelId()).getName());
						}
						//专业
						if(stu.getMajorId()>0 && majorbiz.findMajorById(stu.getMajorId())!=null)
						{
							feeObj.setMajorName(majorbiz.findMajorById(stu.getMajorId()).getName());
						}
					}
					//费用科目
					if(feeObj.getFeeSubjectId()>0 && feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId())!=null)
					{
						feeObj.setPaymentSubjectName(feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId()).getName());
					}
					
					feepdlist.add(feeObj);
				}
			}
		}
		return feepdlist;
		
	}
	
	/*
	 * 查询需要招生返款的所有缴费单明细集合（添加招生返款单）（分页）
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findFeePaymentDetailListByPageForNowAddAcademyRebate(java.lang.String, net.cedu.model.page.PageResult)
	 */
	public List<FeePaymentDetail> findFeePaymentDetailListByPageForNowAddChannelRebate(String feepdIds,PageResult<FeePaymentDetail> pr) throws Exception
	{
		List<FeePaymentDetail> feepdlist=null;
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feepdIds!= null && feepdIds.length()>0)
		{						
			hqlparam += " and id in ("
				+ Constants.PLACEHOLDER + ")";
			list.add("$" + feepdIds);
		}
		else
		{
			return null;
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		Long[] feeIds = feePaymentDetailDao.getIDs(p);
		if (feeIds != null && feeIds.length != 0) 
		{
			feepdlist=new ArrayList<FeePaymentDetail>();
			for (int i = 0; i < feeIds.length; i++) 
			{
				// 内存获取
				FeePaymentDetail feeObj = feePaymentDetailDao.findById(Integer.valueOf(feeIds[i].toString()));
				if(feeObj!=null)
				{
					//缴费单的编号
					if(feeObj.getFeePaymentId()>0 && feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId())!=null)
					{
						feeObj.setPaymentCode(feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId()).getCode());
					}
					else
					{
						feeObj.setPaymentCode("");
					}
				
					//学生相关
					Student stu=studentBiz.findStudentById(feeObj.getStudentId());
					if(feeObj.getStudentId()>0 && stu!=null)
					{
						//学生姓名
						feeObj.setStudentName(stu.getName());
						//院校
						if(stu.getAcademyId()>0 && academyBiz.findAcademyById(stu.getAcademyId())!=null)
						{
							feeObj.setSchoolName(academyBiz.findAcademyById(stu.getAcademyId()).getName());
						}
						//学习中心
						if(stu.getBranchId()>0 && branchBiz.findBranchById(stu.getBranchId())!=null)
						{
							feeObj.setBranchName(branchBiz.findBranchById(stu.getBranchId()).getName());
						}
						//批次
						if(stu.getEnrollmentBatchId()>0 && academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId())!=null)
						{
							feeObj.setAcademyenrollbatchName(academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId()).getEnrollmentName());
						}
						//层次
						if(stu.getLevelId()>0 && levelbiz.findLevelById(stu.getLevelId())!=null)
						{
							feeObj.setLevelName(levelbiz.findLevelById(stu.getLevelId()).getName());
						}
						//专业
						if(stu.getMajorId()>0 && majorbiz.findMajorById(stu.getMajorId())!=null)
						{
							feeObj.setMajorName(majorbiz.findMajorById(stu.getMajorId()).getName());
						}
						//招生返款金额
						feeObj.setPaymentByChannel(this.channelPolicyDetailStandardBiz.findChannelRebateByfeePaymentDetailId(feeObj.getId()));
					}
					//费用科目
					if(feeObj.getFeeSubjectId()>0 && feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId())!=null)
					{
						feeObj.setPaymentSubjectName(feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId()).getName());
					}
					
					feepdlist.add(feeObj);
				}
			}
		}
		return feepdlist;
		
	}
	
	/*
	 * 查询需要招生返款的所有缴费单明细集合（添加招生返款单）（分页）通用政策
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findFeePaymentDetailListByPageForNowAddChannelRebateCommon(java.lang.String, net.cedu.model.page.PageResult)
	 */
	public List<FeePaymentDetail> findFeePaymentDetailListByPageForNowAddChannelRebateCommon(String feepdIds,PageResult<FeePaymentDetail> pr) throws Exception
	{
		List<FeePaymentDetail> feepdlist=null;
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feepdIds!= null && feepdIds.length()>0)
		{						
			hqlparam += " and id in ("
				+ Constants.PLACEHOLDER + ")";
			list.add("$" + feepdIds);
		}
		else
		{
			return null;
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		Long[] feeIds = feePaymentDetailDao.getIDs(p);
		if (feeIds != null && feeIds.length != 0) 
		{
			feepdlist=new ArrayList<FeePaymentDetail>();
			for (int i = 0; i < feeIds.length; i++) 
			{
				// 内存获取
				FeePaymentDetail feeObj = feePaymentDetailDao.findById(Integer.valueOf(feeIds[i].toString()));
				if(feeObj!=null)
				{
					//缴费单的编号
					if(feeObj.getFeePaymentId()>0 && feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId())!=null)
					{
						feeObj.setPaymentCode(feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId()).getCode());
					}
					else
					{
						feeObj.setPaymentCode("");
					}
				
					//学生相关
					Student stu=studentBiz.findStudentById(feeObj.getStudentId());
					if(feeObj.getStudentId()>0 && stu!=null)
					{
						//学生姓名
						feeObj.setStudentName(stu.getName());
						//院校
						if(stu.getAcademyId()>0 && academyBiz.findAcademyById(stu.getAcademyId())!=null)
						{
							feeObj.setSchoolName(academyBiz.findAcademyById(stu.getAcademyId()).getName());
						}
						//学习中心
						if(stu.getBranchId()>0 && branchBiz.findBranchById(stu.getBranchId())!=null)
						{
							feeObj.setBranchName(branchBiz.findBranchById(stu.getBranchId()).getName());
						}
						//批次
						if(stu.getEnrollmentBatchId()>0 && academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId())!=null)
						{
							feeObj.setAcademyenrollbatchName(academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId()).getEnrollmentName());
						}
						//层次
						if(stu.getLevelId()>0 && levelbiz.findLevelById(stu.getLevelId())!=null)
						{
							feeObj.setLevelName(levelbiz.findLevelById(stu.getLevelId()).getName());
						}
						//专业
						if(stu.getMajorId()>0 && majorbiz.findMajorById(stu.getMajorId())!=null)
						{
							feeObj.setMajorName(majorbiz.findMajorById(stu.getMajorId()).getName());
						}
						//招生返款金额
						feeObj.setPaymentByChannel(this.channelPolicyDetailStandardBiz.findChannelRebateCommonByfeePaymentDetailId(feeObj.getId()));
					}
					//费用科目
					if(feeObj.getFeeSubjectId()>0 && feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId())!=null)
					{
						feeObj.setPaymentSubjectName(feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId()).getName());
					}
					
					feepdlist.add(feeObj);
				}
			}
		}
		return feepdlist;
		
	}
	
	/*
	 * 查询需要汇款总部的所有缴费单明细集合（添加汇款总部）（分页）
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findFeePaymentDetailListByPageForNowAddOrderBranchCedu(java.lang.String, net.cedu.model.page.PageResult)
	 */
	public List<FeePaymentDetail> findFeePaymentDetailListByPageForNowAddOrderBranchCedu(String feepdIds,PageResult<FeePaymentDetail> pr) throws Exception
	{
		List<FeePaymentDetail> feepdlist=null;
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feepdIds!= null && feepdIds.length()>0)
		{						
			hqlparam += " and id in ("
				+ Constants.PLACEHOLDER + ")";
			list.add("$" + feepdIds);
		}
		else
		{
			return null;
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		Long[] feeIds = feePaymentDetailDao.getIDs(p);
		if (feeIds != null && feeIds.length != 0) 
		{
			feepdlist=new ArrayList<FeePaymentDetail>();
			for (int i = 0; i < feeIds.length; i++) 
			{
				// 内存获取
				FeePaymentDetail feeObj = feePaymentDetailDao.findById(Integer.valueOf(feeIds[i].toString()));
				if(feeObj!=null)
				{
					//缴费单的编号和缴费方式
					if(feeObj.getFeePaymentId()>0 && feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId())!=null)
					{
						feeObj.setFeeWayId(feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId()).getFeeWayId());
						feeObj.setPaymentCode(feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId()).getCode());
					}
					else
					{
						feeObj.setPaymentCode("");
					}
					//学生相关
					Student stu=studentBiz.findStudentById(feeObj.getStudentId());
					if(feeObj.getStudentId()>0 && stu!=null)
					{
						//学生姓名
						feeObj.setStudentName(stu.getName());
						//院校
						if(stu.getAcademyId()>0 && academyBiz.findAcademyById(stu.getAcademyId())!=null)
						{
							feeObj.setSchoolName(academyBiz.findAcademyById(stu.getAcademyId()).getName());
						}
						//学习中心
						if(stu.getBranchId()>0 && branchBiz.findBranchById(stu.getBranchId())!=null)
						{
							feeObj.setBranchName(branchBiz.findBranchById(stu.getBranchId()).getName());
						}
						//批次
						if(stu.getEnrollmentBatchId()>0 && academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId())!=null)
						{
							feeObj.setAcademyenrollbatchName(academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId()).getEnrollmentName());
						}
						//层次
						if(stu.getLevelId()>0 && levelbiz.findLevelById(stu.getLevelId())!=null)
						{
							feeObj.setLevelName(levelbiz.findLevelById(stu.getLevelId()).getName());
						}
						//专业
						if(stu.getMajorId()>0 && majorbiz.findMajorById(stu.getMajorId())!=null)
						{
							feeObj.setMajorName(majorbiz.findMajorById(stu.getMajorId()).getName());
						}
						
					}
					//费用科目
					if(feeObj.getFeeSubjectId()>0 && feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId())!=null)
					{
						feeObj.setPaymentSubjectName(feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId()).getName());
					}
					//基数
					BigDecimal jishu=new BigDecimal(1);
					if((new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount()))).compareTo(new BigDecimal(0))!=0)
					{
						jishu=(new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())).subtract(new BigDecimal(feeObj.getRefundAmount()))).divide(new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())),12,BigDecimal.ROUND_HALF_UP);
					}
					DecimalFormat df = new DecimalFormat("0.00"); // 保留几位小数
					//打款金额
					//feeObj.setPayCeduAcademy(Double.valueOf(new BigDecimal(feeObj.getMoneyToPay()).subtract(new BigDecimal(feeObj.getAcademyDiscount()).add(new BigDecimal(feeObj.getAcademyCeduDiscount()))).toString()));
					//feeObj.setPayCeduAcademy(((new BigDecimal(feeObj.getMoneyToPay()).subtract(new BigDecimal(feeObj.getAcademyDiscount()).add(new BigDecimal(feeObj.getAcademyCeduDiscount())))).multiply(new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())).subtract(new BigDecimal(feeObj.getRefundAmount()))).divide(new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())),2,BigDecimal.ROUND_HALF_UP)).doubleValue());
					//feeObj.setPayCeduAcademy(Double.valueOf(df.format(((new BigDecimal(feeObj.getMoneyToPay()).subtract(new BigDecimal(feeObj.getAcademyDiscount()).add(new BigDecimal(feeObj.getAcademyCeduDiscount())))).multiply(jishu)).doubleValue())));
					
					//汇款金额.multiply(new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())).subtract(new BigDecimal(feeObj.getRefundAmount()))).divide(new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())),2,BigDecimal.ROUND_HALF_UP)
					//feeObj.setPayBranchCedu(Double.valueOf(new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())).toString()));
					//feeObj.setPayBranchCedu(((new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount()))).multiply(new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())).subtract(new BigDecimal(feeObj.getRefundAmount()))).divide(new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())),2,BigDecimal.ROUND_HALF_UP)).doubleValue());
					feeObj.setPayBranchCedu(Double.valueOf(df.format(((new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount()))).multiply(jishu)).doubleValue())));
					
					feepdlist.add(feeObj);
				}
			}
		}
		return feepdlist;
	
	}
	
	/*
	 * 查询符合招生返款的所有缴费单明细数量（添加招生返款单_特殊合作方）（分页）-------暂时只对学费进行招生返款
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findFpdCountByPageForChannelRebateSpecial(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.crm.Student, int, int)
	 */
	public int findFpdCountByPageForChannelRebateSpecial(FeePaymentDetail feePaymentDetail,Student student,int minorChannelType,int minorChannelId) throws Exception
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feePaymentDetail != null)
		{						
			// 缴费单招生返款状态
			if (feePaymentDetail.getRebateStatus() != 0) {
				hqlparam += " and rebate_status ="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getRebateStatus());
			}
			//费用科目    暂时只对学费进行招生返款
			hqlparam += " and feeSubjectId ="+ Constants.PLACEHOLDER;
			list.add(FeeSubjectEnum.TuitionFee.value());
			
			//退费中的不能进行招生返款__2012-04-03
			hqlparam += " and refundLock <> "+ Constants.PLACEHOLDER;
			list.add(Constants.LOCKING_TRUE);
			
			//学生ID
			if (checkStudentInfo(student)) 
			{
				String studentIds=this.findStudentIdsForChannelSpecial(student,minorChannelType,minorChannelId);
				//学生Ids
				if (studentIds != null && !studentIds.equals("")) 
				{
					hqlparam += " and studentId in ("
							+ Constants.PLACEHOLDER + ")";
					list.add("$" + studentIds);
				}
				else
				{
					return 0;
				}
				
			}
			else
			{
				return 0;
			}
			
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}

		return feePaymentDetailDao.getCounts(p);
		
	}
	
	/*
	 * 查询符合招生返款的所有缴费单明细集合（添加招生返款单_特殊合作方）（分页）-------暂时只对学费进行招生返款
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findFpdListByPageForChannelRebateSpecial(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.crm.Student, int, int, net.cedu.model.page.PageResult)
	 */
	public List<FeePaymentDetail> findFpdListByPageForChannelRebateSpecial(FeePaymentDetail feePaymentDetail,Student student,int minorChannelType,int minorChannelId,PageResult<FeePaymentDetail> pr) throws Exception
	{
		List<FeePaymentDetail> feepdlist=null;
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feePaymentDetail != null)
		{						
			// 缴费单招生返款状态
			if (feePaymentDetail.getRebateStatus() != 0) {
				hqlparam += " and rebate_status ="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getRebateStatus());
			}
			//费用科目    暂时只对学费进行招生返款
			hqlparam += " and feeSubjectId ="+ Constants.PLACEHOLDER;
			list.add(FeeSubjectEnum.TuitionFee.value());
			
			//退费中的不能进行招生返款__2012-04-03
			hqlparam += " and refundLock <> "+ Constants.PLACEHOLDER;
			list.add(Constants.LOCKING_TRUE);
			
			//学生ID
			if (checkStudentInfo(student)) 
			{
				String studentIds=this.findStudentIdsForChannelSpecial(student,minorChannelType,minorChannelId);
				//学生Ids
				if (studentIds != null && !studentIds.equals("")) 
				{
					hqlparam += " and studentId in ("
							+ Constants.PLACEHOLDER + ")";
					list.add("$" + studentIds);
				}
				else
				{
					return null;
				}
				
			}
			else
			{
				return null;
			}
			
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}

		Long[] feeIds = feePaymentDetailDao.getIDs(p);
		if (feeIds != null && feeIds.length != 0) 
		{
			feepdlist=new ArrayList<FeePaymentDetail>();
			for (int i = 0; i < feeIds.length; i++) 
			{
				// 内存获取
				FeePaymentDetail feeObj = feePaymentDetailDao.findById(Integer.valueOf(feeIds[i].toString()));
				if(feeObj!=null)
				{
					//缴费单的编号
					FeePayment feePayment=feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId());
					if(feeObj.getFeePaymentId()>0 && feePayment!=null)
					{
						feeObj.setPaymentCode(feePayment.getCode());
					}
					else
					{
						feeObj.setPaymentCode("");
					}
					//学生相关
					Student stu=studentBiz.findStudentById(feeObj.getStudentId());
					if(feeObj.getStudentId()>0 && stu!=null)
					{
						//学生姓名
						feeObj.setStudentName(stu.getName());
						//院校
						Academy academy=academyBiz.findAcademyById(stu.getAcademyId());
						if(stu.getAcademyId()>0 && academy!=null)
						{
							feeObj.setSchoolName(academy.getName());
						}
						//学习中心
						Branch branch=branchBiz.findBranchById(stu.getBranchId());
						if(stu.getBranchId()>0 && branch!=null)
						{
							feeObj.setBranchName(branch.getName());
						}
						//批次
						AcademyEnrollBatch academyenrollbatch=academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId());
						if(stu.getEnrollmentBatchId()>0 && academyenrollbatch!=null)
						{
							feeObj.setAcademyenrollbatchName(academyenrollbatch.getEnrollmentName());
						}
						//层次
						Level level=levelbiz.findLevelById(stu.getLevelId());
						if(stu.getLevelId()>0 && level!=null)
						{
							feeObj.setLevelName(level.getName());
						}
						//专业
						Major major=majorbiz.findMajorById(stu.getMajorId());
						if(stu.getMajorId()>0 && major!=null)
						{
							feeObj.setMajorName(major.getName());
						}
					}
					//费用科目
					FeeSubject feeSubject=feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId());
					if(feeObj.getFeeSubjectId()>0 && feeSubject!=null)
					{
						feeObj.setPaymentSubjectName(feeSubject.getName());
					}
					
					feepdlist.add(feeObj);
				}
			}
		}
		return feepdlist;
		
	}
	
	/*
	 * 查询需要招生返款的所有缴费单明细集合（添加招生返款单_特殊合作方）（分页）
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findfpdListByPageForNowAddChannelRebateSpecial(java.lang.String, int, int, int, net.cedu.model.page.PageResult)
	 */
	public List<FeePaymentDetail> findfpdListByPageForNowAddChannelRebateSpecial(String feepdIds,int channelId, int minorChannelId, int policeStatus, PageResult<FeePaymentDetail> pr) throws Exception
	{
		List<FeePaymentDetail> feepdlist=null;
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feepdIds!= null && feepdIds.length()>0)
		{						
			hqlparam += " and id in ("
				+ Constants.PLACEHOLDER + ")";
			list.add("$" + feepdIds);
		}
		else
		{
			return null;
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		Long[] feeIds = feePaymentDetailDao.getIDs(p);
		if (feeIds != null && feeIds.length != 0) 
		{
			feepdlist=new ArrayList<FeePaymentDetail>();
			for (int i = 0; i < feeIds.length; i++) 
			{
				// 内存获取
				FeePaymentDetail feeObj = feePaymentDetailDao.findById(Integer.valueOf(feeIds[i].toString()));
				if(feeObj!=null)
				{
					//缴费单的编号
					FeePayment feePayment=feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId());
					if(feeObj.getFeePaymentId()>0 && feePayment!=null)
					{
						feeObj.setPaymentCode(feePayment.getCode());
					}
					else
					{
						feeObj.setPaymentCode("");
					}
				
					//学生相关
					Student stu1=studentBiz.findStudentById(feeObj.getStudentId());
					if(feeObj.getStudentId()>0 && stu1!=null)
					{
						//学生姓名
						feeObj.setStudentName(stu1.getName());
						//院校
						Academy academy=academyBiz.findAcademyById(stu1.getAcademyId());
						if(stu1.getAcademyId()>0 && academy!=null)
						{
							feeObj.setSchoolName(academy.getName());
						}
						//学习中心
						Branch branch=branchBiz.findBranchById(stu1.getBranchId());
						if(stu1.getBranchId()>0 && branch!=null)
						{
							feeObj.setBranchName(branch.getName());
						}
						//批次
						AcademyEnrollBatch aeb=academyenrollbatchBiz.findAcademyEnrollBatchById(stu1.getEnrollmentBatchId());
						if(stu1.getEnrollmentBatchId()>0 && aeb!=null)
						{
							feeObj.setAcademyenrollbatchName(aeb.getEnrollmentName());
						}
						//层次
						Level level=levelbiz.findLevelById(stu1.getLevelId());
						if(stu1.getLevelId()>0 && level!=null)
						{
							feeObj.setLevelName(level.getName());
						}
						//专业
						Major major=majorbiz.findMajorById(stu1.getMajorId());
						if(stu1.getMajorId()>0 && major!=null)
						{
							feeObj.setMajorName(major.getName());
						}
						//招生返款金额
						feeObj.setPaymentByChannel(this.channelPolicyDetailStandardBiz.findChannelRebateSpecialByfeePaymentDetailIdChannelIdPolicyStatus(feeObj.getId(), channelId, minorChannelId, policeStatus));
					}
					//费用科目
					FeeSubject feeSubject=feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId());
					if(feeObj.getFeeSubjectId()>0 && feeSubject!=null)
					{
						feeObj.setPaymentSubjectName(feeSubject.getName());
					}
					
					feepdlist.add(feeObj);
				}
			}
		}
		return feepdlist;
		
	}
	
	
	//TODO ******************************************************招生返款重构2012-04-05*******************************************************//
	
	/**
	 * 查询学生ID集合(招生返款用)__重构
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	private String findStudentIdsForChannelReview(Student student) throws Exception 
	{
		// 查询学生集合ID
		PageParame p = new PageParame();
		List<Object> list = null;
		String hqlConditionExpression = "";
		if (student != null)
		{
			list = new ArrayList<Object>();
			// 姓名
			if (student.getName() != null && !student.getName().equals("")) 
			{
				hqlConditionExpression += "and name like"
						+ Constants.PLACEHOLDER;
				list.add("%" + student.getName() + "%");
			}
			// 证件号
			if (student.getCertNo() != null
					&& !"".equals(student.getCertNo()))
			{
				hqlConditionExpression += "and certNo like"
						+ Constants.PLACEHOLDER;
				list.add("%" + student.getCertNo() + "%");
			}
			//招生途径enrollmentSource
			if (student.getEnrollmentSource() != 0) {
				hqlConditionExpression += "and enrollmentSource ="
						+ Constants.PLACEHOLDER;
				list.add(student.getEnrollmentSource());
			}
			//合作方channelId
			if (student.getChannelId() != 0) {
				hqlConditionExpression += "and channelId ="
						+ Constants.PLACEHOLDER;
				list.add(student.getChannelId());
			}
			// 院校
			if (student.getAcademyId() != 0) {
				hqlConditionExpression += "and academyId="
						+ Constants.PLACEHOLDER;
				list.add(student.getAcademyId());
			}
			// 批次
			if (student.getEnrollmentBatchId() != 0) {
				hqlConditionExpression += " and  enrollmentBatchId= "
						+ Constants.PLACEHOLDER;
				list.add(student.getEnrollmentBatchId());
			}
			// 全局批次
			if (student.getGlbtach() > 0) 
			{
				List<AcademyEnrollBatch> aeblst = academyenrollbatchBiz
							.findAcademyEnrollBatchByGId(student.getGlbtach());
				String gbatchIds = "";
				if (null != aeblst && aeblst.size() > 0)
				{
					for (int i = 0; i < aeblst.size(); i++)
					{
						gbatchIds += "," + aeblst.get(i).getId();
					}
					hqlConditionExpression += " and enrollmentBatchId in ( "
								+ Constants.PLACEHOLDER + " ) ";
					list.add("$"+ gbatchIds.substring(1, gbatchIds.length()));
					
				} 
				else 
				{
					return null;
				}
			}
			//多批次
			if(student.getAcademyenrollbatchName()!=null && !"".equals(student.getAcademyenrollbatchName()))
			{
				hqlConditionExpression += " and  enrollmentBatchId in ("
					+ Constants.PLACEHOLDER+ ")";
				list.add("$" +student.getAcademyenrollbatchName());
				
			}
			// 层次
			if (student.getLevelId() != 0) {
				hqlConditionExpression += " and  levelId= "
						+ Constants.PLACEHOLDER;
				list.add(student.getLevelId());
			}
			// 专业
			if (student.getMajorId() != 0) {
				hqlConditionExpression += " and  majorId= "
						+ Constants.PLACEHOLDER;
				list.add(student.getMajorId());
			}
			// 学习中心
			if (student.getBranchId() != 0) {
				hqlConditionExpression += " and  branchId= "
						+ Constants.PLACEHOLDER;
				list.add(student.getBranchId());
			}
			//已缴学费   且已开课、已招生途径复核的学生才能招生返款
			hqlConditionExpression += " and  tuitionStatus >= "
				+ Constants.PLACEHOLDER;
			list.add(Constants.STU_TUITION_STATUS_SHOU_CI_JIAO_FEI);
			
			//2012-04-18重构  屏蔽掉已开课和已招生途径复核的学生筛选项
//			hqlConditionExpression += " and  isStartCourse= "
//				+ Constants.PLACEHOLDER;
//			list.add(Constants.STU_IS_START_COURSE_TRUE);
//			
//			hqlConditionExpression += " and  isChannelTypeChecked= "
//				+ Constants.PLACEHOLDER;
//			list.add(Constants.STUDENT_CHANNEL_TYPE_CHECKED_TRUE);
			
			//是否已经招生返款          主要是控制老带新只招生返款一次；大客户和渠道等其他多次返款的如果第一匹配政策是固定金额，后面就不再返款
			hqlConditionExpression += " and  isChannelRebate= "
				+ Constants.PLACEHOLDER;
			list.add(Constants.STUDENT_IS_CHANNEL_REBATE_FALSE);
			
			
			// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
			// 如果起始状态ID>0,结束状态ID=0;则为无穷大
			// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
			// 如果都大于0,则取交集
			if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0) {
				hqlConditionExpression += " and  status <"
						+ Constants.PLACEHOLDER;
				list.add(student.getEndStatusId());
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0) {
				hqlConditionExpression += " and  status >"
						+ Constants.PLACEHOLDER;
				list.add(student.getStartStatusId());
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0) {
				hqlConditionExpression += " and  status> "
						+ Constants.PLACEHOLDER + " and status<"
						+ Constants.PLACEHOLDER;
				list.add(student.getStartStatusId());
				list.add(student.getEndStatusId());
			}
			if (!hqlConditionExpression.equals("")) {
				p.setHqlConditionExpression(hqlConditionExpression);
				p.setValues(list.toArray());
			}
		}
		StringBuffer stuString=null;
		Long[] sidsLongs = studentDao.getIDs(p);
		if (sidsLongs == null || sidsLongs.length == 0) 
		{
			return null;
		}
		for (Long id : sidsLongs) 
		{
			if(stuString==null)
			{
				stuString=new StringBuffer(id+"");
			}else{
				stuString.append(","+id);
			}
			
		}
		if(stuString==null)
		{
			return null;
		}
		return stuString.toString();
	}
	
	
	/*
	 * 查询符合招生返款的所有缴费单明细数量（添加招生返款单_重构）（分页）-------暂时只对学费进行招生返款
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findfpdCountByPageForChannelRebateAcademyReview(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.crm.Student,java.lang.String, java.lang.String, java.lang.String)
	 */
	public int findfpdCountByPageForChannelRebateAcademyReview(FeePaymentDetail feePaymentDetail,Student student,String feepdIds,String starttime,String endtime) throws Exception
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feePaymentDetail != null)
		{				
			
			// 缴费单招生返款状态
			if (feePaymentDetail.getRebateStatus() != 0) 
			{
				hqlparam += " and rebateStatus <="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getRebateStatus());
			}
			//缴费单状态
			if (feePaymentDetail.getStartStatusId()!= 0) 
			{
				hqlparam += " and status >="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getStartStatusId());
			}
			//选择过的缴费单Ids字符串
			if (feepdIds!=null && !feepdIds.equals("")) 
			{
				hqlparam += " and id not in ( "
						+ Constants.PLACEHOLDER+" ) ";
				list.add("$" + feepdIds);
			}
			
			//开始时间
			if (starttime!=null && !starttime.equals("")) 
			{
				hqlparam += " and createdTime >="
						+ Constants.PLACEHOLDER;
				list.add(starttime);
			}
			//结束时间
			if (endtime!=null && !endtime.equals("")) 
			{
				hqlparam += " and createdTime <="
						+ Constants.PLACEHOLDER;
				list.add(endtime);
			}
			
			//费用科目    暂时只对学费进行招生返款
			hqlparam += " and feeSubjectId ="+ Constants.PLACEHOLDER;
			list.add(FeeSubjectEnum.TuitionFee.value());
			
			//退费中的不能进行招生返款__2012-04-03
			//hqlparam += " and refundLock <> "+ Constants.PLACEHOLDER;
			//list.add(Constants.LOCKING_TRUE);
			
			
			//学生ID
			if (checkStudentInfo(student)) 
			{
				String studentIds=this.findStudentIdsForChannelReview(student);
				//学生Ids
				if (studentIds != null && !studentIds.equals("")) 
				{
					hqlparam += " and studentId in ("
							+ Constants.PLACEHOLDER + ")";
					list.add("$" + studentIds);
				}
				else
				{
					return 0;
				}
				
			}
			else
			{
				return 0;
			}
			
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}

		return feePaymentDetailDao.getCounts(p);
		
	}
	
	/*
	 * 查询符合招生返款的所有缴费单明细集合（添加招生返款单_重构）（分页）-------暂时只对学费进行招生返款
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findfpdListByPageForChannelRebateAcademyReview(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.crm.Student,java.lang.String, java.lang.String, java.lang.String, net.cedu.model.page.PageResult)
	 */
	public List<FeePaymentDetail> findfpdListByPageForChannelRebateAcademyReview(FeePaymentDetail feePaymentDetail,Student student,String feepdIds,String starttime,String endtime,PageResult<FeePaymentDetail> pr) throws Exception
	{
		List<FeePaymentDetail> feepdlist=null;
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feePaymentDetail != null)
		{						
			// 缴费单招生返款状态
			if (feePaymentDetail.getRebateStatus() != 0) 
			{
				hqlparam += " and rebateStatus <="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getRebateStatus());
			}
			//缴费单状态
			if (feePaymentDetail.getStartStatusId()!= 0) 
			{
				hqlparam += " and status >="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getStartStatusId());
			}
			//选择过的缴费单Ids字符串
			if (feepdIds!=null && !feepdIds.equals("")) 
			{
				hqlparam += " and id not in ( "
						+ Constants.PLACEHOLDER+" ) ";
				list.add("$" + feepdIds);
			}
			
			//开始时间
			if (starttime!=null && !starttime.equals("")) 
			{
				hqlparam += " and createdTime >="
						+ Constants.PLACEHOLDER;
				list.add(starttime);
			}
			//结束时间
			if (endtime!=null && !endtime.equals("")) 
			{
				hqlparam += " and createdTime <="
						+ Constants.PLACEHOLDER;
				list.add(endtime);
			}
			//费用科目    暂时只对学费进行招生返款
			hqlparam += " and feeSubjectId ="+ Constants.PLACEHOLDER;
			list.add(FeeSubjectEnum.TuitionFee.value());
			
			////退费中的不能进行招生返款__2012-04-03
			//hqlparam += " and refundLock <> "+ Constants.PLACEHOLDER;
			//list.add(Constants.LOCKING_TRUE);
			
			//学生ID
			if (checkStudentInfo(student)) 
			{
				String studentIds=this.findStudentIdsForChannelReview(student);
				//学生Ids
				if (studentIds != null && !studentIds.equals("")) 
				{
					hqlparam += " and studentId in ("
							+ Constants.PLACEHOLDER + ")";
					list.add("$" + studentIds);
				}
				else
				{
					return null;
				}
				
			}
			else
			{
				return null;
			}
			
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}

		Long[] feeIds = feePaymentDetailDao.getIDs(p);
		if (feeIds != null && feeIds.length != 0) 
		{
			feepdlist=new ArrayList<FeePaymentDetail>();
			for (int i = 0; i < feeIds.length; i++) 
			{
				// 内存获取
				FeePaymentDetail feeObj = feePaymentDetailDao.findById(Integer.valueOf(feeIds[i].toString()));
				if(feeObj!=null)
				{
					//缴费单的编号
					FeePayment fp=feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId());
					if(feeObj.getFeePaymentId()>0 && fp!=null)
					{
						feeObj.setPaymentCode(fp.getCode());
						feeObj.setFeeWayId(fp.getFeeWayId());//缴费方式
					}
					else
					{
						feeObj.setPaymentCode("");
					}
					//学生相关
					Student stu=studentBiz.findStudentById(feeObj.getStudentId());
					if(feeObj.getStudentId()>0 && stu!=null)
					{
						//学生姓名
						feeObj.setStudentName(stu.getName());
						//院校
						Academy academy=academyBiz.findAcademyById(stu.getAcademyId());
						if(stu.getAcademyId()>0 && academy!=null)
						{
							feeObj.setSchoolName(academy.getName());
						}
						//学习中心
						Branch branch=branchBiz.findBranchById(stu.getBranchId());
						if(stu.getBranchId()>0 && branch!=null)
						{
							feeObj.setBranchName(branch.getName());
						}
						//批次
						AcademyEnrollBatch aeb=academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId());
						if(stu.getEnrollmentBatchId()>0 && aeb!=null)
						{
							feeObj.setAcademyenrollbatchName(aeb.getEnrollmentName());
						}
						//层次
						Level level=levelbiz.findLevelById(stu.getLevelId());
						if(stu.getLevelId()>0 && level!=null)
						{
							feeObj.setLevelName(level.getName());
						}
						//专业
						Major major=majorbiz.findMajorById(stu.getMajorId());
						if(stu.getMajorId()>0 && major!=null)
						{
							feeObj.setMajorName(major.getName());
						}
						//是否招生途径复核
						feeObj.setIsChannelTypeChecked(stu.getIsChannelTypeChecked());
						//是否开课
						feeObj.setIsStartCourse(stu.getIsStartCourse());
					}
					//费用科目
					FeeSubject fs=feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId());
					if(feeObj.getFeeSubjectId()>0 && fs!=null)
					{
						feeObj.setPaymentSubjectName(fs.getName());
					}
					//缴费金额
					feeObj.setJiaofeiValue((new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())).subtract(new BigDecimal(feeObj.getRefundAmount()))).doubleValue());
					
					feepdlist.add(feeObj);
				}
			}
		}
		return feepdlist;
		
	}
	
	/*
	 * 查询需要招生返款的所有缴费单明细集合（添加招生返款单_重构_2012-04-05）（分页）
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findfpdListByPageForChannelRebateAcademyNeedReview(java.lang.String, net.cedu.model.page.PageResult)
	 */
	public List<FeePaymentDetail> findfpdListByPageForChannelRebateAcademyNeedReview(String feepdIds,PageResult<FeePaymentDetail> pr) throws Exception
	{
		List<FeePaymentDetail> feepdlist=null;
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feepdIds!= null && feepdIds.length()>0)
		{						
			hqlparam += " and id in ("
				+ Constants.PLACEHOLDER + ")";
			list.add("$" + feepdIds);
		}
		else
		{
			return null;
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		Long[] feeIds = feePaymentDetailDao.getIDs(p);
		if (feeIds != null && feeIds.length != 0) 
		{
			feepdlist=new ArrayList<FeePaymentDetail>();
			for (int i = 0; i < feeIds.length; i++) 
			{
				// 内存获取
				FeePaymentDetail feeObj = feePaymentDetailDao.findById(Integer.valueOf(feeIds[i].toString()));
				if(feeObj!=null)
				{
					//缴费单的编号
					FeePayment fp=feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId());
					if(feeObj.getFeePaymentId()>0 && fp!=null)
					{
						feeObj.setPaymentCode(fp.getCode());
					}
					else
					{
						feeObj.setPaymentCode("");
					}
				
					//学生相关
					Student stu=studentBiz.findStudentById(feeObj.getStudentId());
					if(feeObj.getStudentId()>0 && stu!=null)
					{
						//学生姓名
						feeObj.setStudentName(stu.getName());
						//院校
						Academy academy=academyBiz.findAcademyById(stu.getAcademyId());
						if(stu.getAcademyId()>0 && academy!=null)
						{
							feeObj.setSchoolName(academy.getName());
						}
						//学习中心
						Branch branch=branchBiz.findBranchById(stu.getBranchId());
						if(stu.getBranchId()>0 && branch!=null)
						{
							feeObj.setBranchName(branch.getName());
						}
						//批次
						AcademyEnrollBatch aeb=academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId());
						if(stu.getEnrollmentBatchId()>0 && aeb!=null)
						{
							feeObj.setAcademyenrollbatchName(aeb.getEnrollmentName());
						}
						//层次
						Level level=levelbiz.findLevelById(stu.getLevelId());
						if(stu.getLevelId()>0 && level!=null)
						{
							feeObj.setLevelName(level.getName());
						}
						//专业
						Major major=majorbiz.findMajorById(stu.getMajorId());
						if(stu.getMajorId()>0 && major!=null)
						{
							feeObj.setMajorName(major.getName());
						}
						//合作方
						Channel channel=this.channelBiz.findChannel(stu.getChannelId());
						if(channel!=null)
						{
							feeObj.setChannelName(channel.getName());
						}
					}
					//费用科目
					FeeSubject fs=feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId());
					if(feeObj.getFeeSubjectId()>0 && fs!=null)
					{
						feeObj.setPaymentSubjectName(fs.getName());
					}
					//缴费金额
					feeObj.setJiaofeiValue((new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())).subtract(new BigDecimal(feeObj.getRefundAmount()))).doubleValue());
				
					feepdlist.add(feeObj);
				}
			}
		}
		return feepdlist;
		
	}
	
	/**
	 * 查询学生ID集合(匹配该合作方的人数)__重构
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	private String findStudentIdsForChannelCountReview(Student student) throws Exception 
	{
		// 查询学生集合ID
		PageParame p = new PageParame();
		List<Object> list = null;
		String hqlConditionExpression = "";
		if (student != null)
		{
			list = new ArrayList<Object>();
			
			//招生途径enrollmentSource
			if (student.getEnrollmentSource() > 0) {
				hqlConditionExpression += "and enrollmentSource ="
						+ Constants.PLACEHOLDER;
				list.add(student.getEnrollmentSource());
			}
			//合作方channelId
			if (student.getChannelId() > 0) {
				hqlConditionExpression += "and channelId ="
						+ Constants.PLACEHOLDER;
				list.add(student.getChannelId());
			}
			// 院校
			if (student.getAcademyId() > 0) {
				hqlConditionExpression += "and academyId="
						+ Constants.PLACEHOLDER;
				list.add(student.getAcademyId());
			}
			// 批次
			if (student.getEnrollmentBatchId() > 0) {
				hqlConditionExpression += " and  enrollmentBatchId= "
						+ Constants.PLACEHOLDER;
				list.add(student.getEnrollmentBatchId());
			}
			// 全局批次
			if (student.getGlbtach() > 0) 
			{
				List<AcademyEnrollBatch> aeblst = academyenrollbatchBiz
							.findAcademyEnrollBatchByGId(student.getGlbtach());
				String gbatchIds = "";
				if (null != aeblst && aeblst.size() > 0)
				{
					for (int i = 0; i < aeblst.size(); i++)
					{
						gbatchIds += "," + aeblst.get(i).getId();
					}
					hqlConditionExpression += " and enrollmentBatchId in ( "
								+ Constants.PLACEHOLDER + " ) ";
					list.add("$"+ gbatchIds.substring(1, gbatchIds.length()));
					
				} 
				else 
				{
					return null;
				}
			}
			
			//已缴学费   且已开课、已招生途径复核的学生才能招生返款
			hqlConditionExpression += " and  tuitionStatus >= "
				+ Constants.PLACEHOLDER;
			list.add(Constants.STU_TUITION_STATUS_SHOU_CI_JIAO_FEI);
			
			hqlConditionExpression += " and  isStartCourse= "
				+ Constants.PLACEHOLDER;
			list.add(Constants.STU_IS_START_COURSE_TRUE);
			
			hqlConditionExpression += " and  isChannelTypeChecked= "
				+ Constants.PLACEHOLDER;
			list.add(Constants.STUDENT_CHANNEL_TYPE_CHECKED_TRUE);
					
			// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
			// 如果起始状态ID>0,结束状态ID=0;则为无穷大
			// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
			// 如果都大于0,则取交集
			if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0) {
				hqlConditionExpression += " and  status <"
						+ Constants.PLACEHOLDER;
				list.add(student.getEndStatusId());
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0) {
				hqlConditionExpression += " and  status >"
						+ Constants.PLACEHOLDER;
				list.add(student.getStartStatusId());
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0) {
				hqlConditionExpression += " and  status> "
						+ Constants.PLACEHOLDER + " and status<"
						+ Constants.PLACEHOLDER;
				list.add(student.getStartStatusId());
				list.add(student.getEndStatusId());
			}
			if (!hqlConditionExpression.equals("")) {
				p.setHqlConditionExpression(hqlConditionExpression);
				p.setValues(list.toArray());
			}
		}
		StringBuffer stuString=null;
		Long[] sidsLongs = studentDao.getIDs(p);
		if (sidsLongs == null || sidsLongs.length == 0) 
		{
			return null;
		}
		for (Long id : sidsLongs) 
		{
			if(stuString==null)
			{
				stuString=new StringBuffer(id+"");
			}else{
				stuString.append(","+id);
			}
			
		}
		if(stuString==null)
		{
			return null;
		}
		return stuString.toString();
	}
	
	/*
	 * 通过缴费单明细匹配该合作方招生总人数（必须符合: i.已缴学费_总部或院校已确认 ii.已开课 iii.已招生途径复核、）__重构
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findfpdCountByChannelAllPeopleCount(net.cedu.entity.crm.Student)
	 */
	public int findfpdCountByChannelAllPeopleCount(Student student) throws Exception
	{
		String studentIds=this.findStudentIdsForChannelCountReview(student);
		return feePaymentDetailDao.countStudCountForChannelAdmissionsByStuIds(studentIds);
		
	}
	
	/*
	 * * 特殊合作方
	 * 根据缴费单明细统计满足条件的学生个数_招生返款匹配合作方招生总人数用（根据学生实体）
	 * 暂时只对学费的缴费单明细进行统计
	 * （必须符合: i.已缴学费_总部或院校已确认 ii.已开课 iii.已招生途径复核、）__重构
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#countStudCountForChannelAdmissionsSpecialByStudent(net.cedu.entity.crm.Student)
	 */
	public int countStudCountForChannelAdmissionsSpecialByStudent(Student student) throws Exception
	{
		return this.feePaymentDetailDao.countStudCountForChannelAdmissionsSpecialByStudent(student);
	}
	
	/*
	 * 指定缴费单id字符串集合，查处属于某个学生的缴费单数量
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findfpdCountByFpdIdsAndStuId(java.lang.String, int)
	 */
	public int findfpdCountByFpdIdsAndStuId(String feepdIds,int stuId) throws Exception
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feepdIds!= null && feepdIds.length()>0)
		{						
			hqlparam += " and id in ( "
				+ Constants.PLACEHOLDER + " ) ";
			list.add("$" + feepdIds);
			
			hqlparam += " and studentId ="
				+ Constants.PLACEHOLDER ;
			list.add(stuId);
		}
		else
		{
			return 0;
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		return feePaymentDetailDao.getCounts(p);
	}
	
	/*
	 * 指定缴费单id字符串集合，查处属于某个学生的缴费单列表
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findfpdListByFpdIdsAndStuId(java.lang.String, int)
	 */
	public List<FeePaymentDetail> findfpdListByFpdIdsAndStuId(String feepdIds,int stuId) throws Exception
	{
		List<FeePaymentDetail> feepdlist=null;
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feepdIds!= null && feepdIds.length()>0)
		{						
			hqlparam += " and id in ( "
				+ Constants.PLACEHOLDER + " ) ";
			list.add("$" + feepdIds);
			
			hqlparam += " and studentId ="
				+ Constants.PLACEHOLDER ;
			list.add(stuId);
		}
		else
		{
			return null;
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		Long[] feeIds = feePaymentDetailDao.getIDs(p);
		if (feeIds != null && feeIds.length != 0) 
		{
			feepdlist=new ArrayList<FeePaymentDetail>();
			for (int i = 0; i < feeIds.length; i++) 
			{
				// 内存获取
				FeePaymentDetail feeObj = feePaymentDetailDao.findById(Integer.valueOf(feeIds[i].toString()));
				if(feeObj!=null)
				{
					feepdlist.add(feeObj);
				}
			}
		}
		return feepdlist;
	}
	
	/*
	 * 统计需要添加招生返款单的缴费单招生返款总金额（根据缴费单明细ids字符串）
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#countFpdAllChannelRebateMoneyByFpdIds(java.lang.String)
	 */
	public String countFpdAllChannelRebateMoneyByFpdIds(String fpdIds) throws Exception
	{
		return this.feePaymentDetailDao.countFpdAllChannelRebateMoneyByFpdIds(fpdIds);
	}
	
	/*
	 * 统计(pos直汇总部)金额
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#countFpdAllMoneyForPOSEbank(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.finance.FeePayment, net.cedu.entity.crm.Student, java.lang.String, java.lang.String)
	 */
	public String countFpdAllMoneyForPOSEbank(FeePaymentDetail feePaymentDetail,FeePayment feePayment,Student student,String starttime,String endtime) throws Exception
	{
		return feePaymentDetailDao.countFpdAllMoneyForPOSEbank(feePaymentDetail, feePayment, student, starttime, endtime);
	}
	
	/*
	 * 统计(pos直汇院校金额)金额
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#countFpdPayAcademyMoneyForPOSEbank(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.finance.FeePayment, net.cedu.entity.crm.Student, java.lang.String, java.lang.String)
	 */
	public String countFpdPayAcademyMoneyForPOSEbank(FeePaymentDetail feePaymentDetail,FeePayment feePayment,Student student,String starttime,String endtime) throws Exception
	{
		return feePaymentDetailDao.countFpdPayAcademyMoneyForPOSEbank(feePaymentDetail, feePayment, student, starttime, endtime);
	}
	
	/*
	 * * 特殊合作方
	 * 
	 * 查询符合招生返款的所有缴费单明细数量（添加招生返款单_重构2012-04-09）（分页）-------暂时只对学费进行招生返款
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findfpdCountByPageForChannelRebateSpecialReview(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.crm.Student, java.lang.String, java.lang.String, java.lang.String)
	 */
	public int findfpdCountByPageForChannelRebateSpecialReview(FeePaymentDetail feePaymentDetail,Student student,String feepdIds,String starttime,String endtime) throws Exception
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feePaymentDetail != null)
		{				
			
			// 缴费单招生返款状态
			if (feePaymentDetail.getRebateStatus() != 0) 
			{
				hqlparam += " and rebateStatus <="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getRebateStatus());
			}
			//缴费单状态
			if (feePaymentDetail.getStartStatusId()!= 0) 
			{
				hqlparam += " and status >="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getStartStatusId());
			}
			//选择过的缴费单Ids字符串
			if (feepdIds!=null && !feepdIds.equals("")) 
			{
				hqlparam += " and id not in ( "
						+ Constants.PLACEHOLDER+" ) ";
				list.add("$" + feepdIds);
			}
			
			//开始时间
			if (starttime!=null && !starttime.equals("")) 
			{
				hqlparam += " and createdTime >="
						+ Constants.PLACEHOLDER;
				list.add(starttime);
			}
			//结束时间
			if (endtime!=null && !endtime.equals("")) 
			{
				hqlparam += " and createdTime <="
						+ Constants.PLACEHOLDER;
				list.add(endtime);
			}
			
			//费用科目    暂时只对学费进行招生返款
			hqlparam += " and feeSubjectId ="+ Constants.PLACEHOLDER;
			list.add(FeeSubjectEnum.TuitionFee.value());
			
			//退费中的不能进行招生返款__2012-04-03
			//hqlparam += " and refundLock <> "+ Constants.PLACEHOLDER;
			//list.add(Constants.LOCKING_TRUE);
			
			//学生Ids__Hql
			String stuHql=this.findStuIdsHqlForChannelSpecialReview(student);
			if (stuHql != null && !stuHql.equals("select id from Student where 1=1 ")) 
			{
				hqlparam += " and studentId in ("
							+ stuHql + ")";
			}
//			if(student!=null)
//			{
//				String stuHql= "select id from Student where 1=1 ";
//				// 姓名
//				if (student.getName() != null && !student.getName().equals("")) 
//				{
//					stuHql += " and name like "+Constants.PLACEHOLDER;
//					list.add("%"+student.getName()+"%");
//				}
//				// 证件号
//				if (student.getCertNo() != null && !"".equals(student.getCertNo()))
//				{
//					stuHql += " and certNo like "+Constants.PLACEHOLDER;
//					list.add("%"+student.getCertNo()+"%");
//				}
//				//招生途径enrollmentSource
//				if (student.getEnrollmentSource() != 0)
//				{
//					stuHql += " and enrollmentSource ="+Constants.PLACEHOLDER;	
//					list.add(student.getEnrollmentSource());
//				}
//				//合作方channelIds
//				if(student.getParamsString().get("channelIds")!=null)
//				{
//					stuHql += " and channelId in ( "+Constants.PLACEHOLDER+" ) ";
//					list.add("$"+student.getParamsString().get("channelIds"));
//				}
//				else if (student.getChannelId() != 0) 
//				{
//					stuHql += " and channelId ="+Constants.PLACEHOLDER;
//					list.add(student.getChannelId());
//				}
//				// 院校
//				if (student.getAcademyId() != 0)
//				{
//					stuHql += " and academyId="+Constants.PLACEHOLDER;	
//					list.add(student.getAcademyId());
//				}
//				// 批次
//				if (student.getEnrollmentBatchId() != 0)
//				{
//					stuHql += " and  enrollmentBatchId= "+Constants.PLACEHOLDER;
//					list.add(student.getEnrollmentBatchId());
//				}
//				
//				// 层次
//				if (student.getLevelId() != 0) 
//				{
//					stuHql += " and  levelId= "+Constants.PLACEHOLDER;
//					list.add(student.getLevelId());
//				}
//				// 专业
//				if (student.getMajorId() != 0) 
//				{
//					stuHql += " and  majorId= "+Constants.PLACEHOLDER;
//					list.add(student.getMajorId());
//				}
//				// 学习中心
//				if (student.getBranchId() != 0) 
//				{
//					stuHql += " and  branchId= "+Constants.PLACEHOLDER;
//					list.add(student.getBranchId());
//				}
//				//已缴学费   且已开课、已招生途径复核的学生才能招生返款
//				stuHql += " and  tuitionStatus >= "+Constants.PLACEHOLDER;
//				list.add(Constants.STU_TUITION_STATUS_SHOU_CI_JIAO_FEI);
//				stuHql += " and  isStartCourse= "+Constants.PLACEHOLDER;
//				list.add(Constants.STU_IS_START_COURSE_TRUE);
//				stuHql += " and  isChannelTypeChecked= "+Constants.PLACEHOLDER;
//				list.add(Constants.STUDENT_CHANNEL_TYPE_CHECKED_TRUE);
//				//是否已经招生返款          主要是控制老带新只招生返款一次；大客户和渠道等其他多次返款的如果第一匹配政策是固定金额，后面就不再返款
//				stuHql += " and  isChannelRebate= "+Constants.PLACEHOLDER;			
//				list.add(Constants.STUDENT_IS_CHANNEL_REBATE_FALSE);
//				// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
//				// 如果起始状态ID>0,结束状态ID=0;则为无穷大
//				// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
//				// 如果都大于0,则取交集
//				if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0)
//				{
//					stuHql += " and  status <"+Constants.PLACEHOLDER;	
//					list.add(student.getEndStatusId());
//				}
//				if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0)
//				{
//					stuHql += " and  status >"+Constants.PLACEHOLDER;	
//					list.add(student.getStartStatusId());
//				}
//				if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0)
//				{
//					stuHql += " and  status> "+Constants.PLACEHOLDER + " and status<"+Constants.PLACEHOLDER;
//					list.add(student.getStartStatusId());
//					list.add(student.getEndStatusId());
//				}
//			}
			else
			{
				return 0;
			}
			
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}

		return feePaymentDetailDao.getCounts(p);
		
	}
	
	/**
	 * 获取查询学生hql语句(in堆栈溢出解决方案)
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	private String findStuIdsHqlForChannelSpecialReview(Student student) throws Exception 
	{
		// 查询学生集合ID
		String stuHql= "select id from Student where 1=1 ";
		if (student != null)
		{
			// 姓名
			if (student.getName() != null && !student.getName().equals("")) 
			{
				stuHql += " and name like '%"+student.getName()+"%' ";
			}
			// 证件号
			if (student.getCertNo() != null && !"".equals(student.getCertNo()))
			{
				stuHql += " and certNo like '%"+student.getCertNo()+"%' ";
			}
			//招生途径enrollmentSource
			if (student.getEnrollmentSource() != 0)
			{
				stuHql += " and enrollmentSource ="+student.getEnrollmentSource();						
			}
			//合作方channelIds
			if(student.getParamsString().get("channelIds")!=null)
			{
				stuHql += " and channelId in ( "+student.getParamsString().get("channelIds")+" ) ";
			}
			else if (student.getChannelId() != 0) 
			{
				stuHql += " and channelId ="+student.getChannelId();
			}
			// 院校
			if (student.getAcademyId() != 0)
			{
				stuHql += " and academyId="+student.getAcademyId();					
			}
			// 全局批次
			if (student.getGlbtach() > 0) 
			{
				List<AcademyEnrollBatch> aeblst = academyenrollbatchBiz
							.findAcademyEnrollBatchByGId(student.getGlbtach());
				String gbatchIds = "";
				if (null != aeblst && aeblst.size() > 0)
				{
					for (int i = 0; i < aeblst.size(); i++)
					{
						gbatchIds += "," + aeblst.get(i).getId();
					}
					stuHql += " and enrollmentBatchId in ( "+ gbatchIds.substring(1, gbatchIds.length()) + " ) ";	
				} 
				else 
				{
					return null;
				}
			}
			// 批次
			if (student.getEnrollmentBatchId() != 0)
			{
				stuHql += " and  enrollmentBatchId= "+student.getEnrollmentBatchId();
			}
			
			// 层次
			if (student.getLevelId() != 0) 
			{
				stuHql += " and  levelId= "+student.getLevelId();
			}
			// 专业
			if (student.getMajorId() != 0) 
			{
				stuHql += " and  majorId= "+student.getMajorId();
			}
			// 学习中心
			if (student.getBranchId() != 0) 
			{
				stuHql += " and  branchId= "+student.getBranchId();
			}
			//已缴学费   且已开课、已招生途径复核的学生才能招生返款
			stuHql += " and  tuitionStatus >= "+Constants.STU_TUITION_STATUS_SHOU_CI_JIAO_FEI;
				
			//2012-04-18重构  屏蔽掉已开课和已招生途径复核的学生筛选项
			//stuHql += " and  isStartCourse= "+Constants.STU_IS_START_COURSE_TRUE;
			
			//stuHql += " and  isChannelTypeChecked= "+Constants.STUDENT_CHANNEL_TYPE_CHECKED_TRUE;
				
			//是否已经招生返款          主要是控制老带新只招生返款一次；大客户和渠道等其他多次返款的如果第一匹配政策是固定金额，后面就不再返款
			stuHql += " and  isChannelRebate= "+Constants.STUDENT_IS_CHANNEL_REBATE_FALSE;			
			
			// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
			// 如果起始状态ID>0,结束状态ID=0;则为无穷大
			// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
			// 如果都大于0,则取交集
			if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0)
			{
				stuHql += " and  status <"+student.getEndStatusId();
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0)
			{
				stuHql += " and  status >"+student.getStartStatusId();
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0)
			{
				stuHql += " and  status> "+student.getStartStatusId() + " and status<"+student.getEndStatusId();
			}
		}
		return stuHql;
	}
	
	/*
	 *  * 特殊合作方
	 * 查询符合招生返款的所有缴费单明细集合（添加招生返款单_重构2012-04-09）（分页）-------暂时只对学费进行招生返款
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findfpdListByPageForChannelRebateSpecialReview(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.crm.Student, java.lang.String, java.lang.String, java.lang.String, net.cedu.model.page.PageResult)
	 */
	public List<FeePaymentDetail> findfpdListByPageForChannelRebateSpecialReview(FeePaymentDetail feePaymentDetail,Student student,String feepdIds,String starttime,String endtime,PageResult<FeePaymentDetail> pr) throws Exception
	{
		List<FeePaymentDetail> feepdlist=null;
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feePaymentDetail != null)
		{						
			// 缴费单招生返款状态
			if (feePaymentDetail.getRebateStatus() != 0) 
			{
				hqlparam += " and rebateStatus <="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getRebateStatus());
			}
			//缴费单状态
			if (feePaymentDetail.getStartStatusId()!= 0) 
			{
				hqlparam += " and status >="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getStartStatusId());
			}
			//选择过的缴费单Ids字符串
			if (feepdIds!=null && !feepdIds.equals("")) 
			{
				hqlparam += " and id not in ( "
						+ Constants.PLACEHOLDER+" ) ";
				list.add("$" + feepdIds);
			}
			
			//开始时间
			if (starttime!=null && !starttime.equals("")) 
			{
				hqlparam += " and createdTime >="
						+ Constants.PLACEHOLDER;
				list.add(starttime);
			}
			//结束时间
			if (endtime!=null && !endtime.equals("")) 
			{
				hqlparam += " and createdTime <="
						+ Constants.PLACEHOLDER;
				list.add(endtime);
			}
			//费用科目    暂时只对学费进行招生返款
			hqlparam += " and feeSubjectId ="+ Constants.PLACEHOLDER;
			list.add(FeeSubjectEnum.TuitionFee.value());
			
			////退费中的不能进行招生返款__2012-04-03
			//hqlparam += " and refundLock <> "+ Constants.PLACEHOLDER;
			//list.add(Constants.LOCKING_TRUE);
			
			//学生Ids__Hql
			String stuHql=this.findStuIdsHqlForChannelSpecialReview(student);
			if (stuHql != null && !stuHql.equals("select id from Student where 1=1 ")) 
			{
				hqlparam += " and studentId in ("
							+ stuHql + ")";
			}
			else
			{
				return null;
			}
			
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}

		Long[] feeIds = feePaymentDetailDao.getIDs(p);
		if (feeIds != null && feeIds.length != 0) 
		{
			feepdlist=new ArrayList<FeePaymentDetail>();
			for (int i = 0; i < feeIds.length; i++) 
			{
				// 内存获取
				FeePaymentDetail feeObj = feePaymentDetailDao.findById(Integer.valueOf(feeIds[i].toString()));
				if(feeObj!=null)
				{
					//缴费单的编号
					FeePayment feePayment=feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId());
					if(feeObj.getFeePaymentId()>0 && feePayment!=null)
					{
						feeObj.setPaymentCode(feePayment.getCode());
						feeObj.setFeeWayId(feePayment.getFeeWayId());//缴费方式
					}
					else
					{
						feeObj.setPaymentCode("");
					}
					//学生相关
					Student stu=studentBiz.findStudentById(feeObj.getStudentId());
					if(feeObj.getStudentId()>0 && stu!=null)
					{
						//学生姓名
						feeObj.setStudentName(stu.getName());
						//院校
						Academy academy=academyBiz.findAcademyById(stu.getAcademyId());
						if(stu.getAcademyId()>0 && academy!=null)
						{
							feeObj.setSchoolName(academy.getName());
						}
						//学习中心
						Branch branch=branchBiz.findBranchById(stu.getBranchId());
						if(stu.getBranchId()>0 && branch!=null)
						{
							feeObj.setBranchName(branch.getName());
						}
						//批次
						AcademyEnrollBatch aeb=academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId());
						if(stu.getEnrollmentBatchId()>0 && aeb!=null)
						{
							feeObj.setAcademyenrollbatchName(aeb.getEnrollmentName());
						}
						//层次
						Level level=levelbiz.findLevelById(stu.getLevelId());
						if(stu.getLevelId()>0 && level!=null)
						{
							feeObj.setLevelName(level.getName());
						}
						//专业
						Major major=majorbiz.findMajorById(stu.getMajorId());
						if(stu.getMajorId()>0 && major!=null)
						{
							feeObj.setMajorName(major.getName());
						}
						//合作方
						Channel channel=this.channelBiz.findChannel(stu.getChannelId());
						if(channel!=null)
						{
							feeObj.setChannelName(channel.getName());
						}
						//是否招生途径复核
						feeObj.setIsChannelTypeChecked(stu.getIsChannelTypeChecked());
						//是否开课
						feeObj.setIsStartCourse(stu.getIsStartCourse());
					}
					//费用科目
					FeeSubject feeSubject=feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId());
					if(feeObj.getFeeSubjectId()>0 && feeSubject!=null)
					{
						feeObj.setPaymentSubjectName(feeSubject.getName());
					}
					//缴费金额
					feeObj.setJiaofeiValue((new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())).subtract(new BigDecimal(feeObj.getRefundAmount()))).doubleValue());
					
					
					feepdlist.add(feeObj);
				}
			}
		}
		return feepdlist;
	}
	
	/*
	 * 2012-05-08 重构
	 * 根据缴费单明细统计满足条件的学生个数_院校返款匹配院校返款标准用（根据学生实体）
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#countStudCountForAcademyRebateByStudent(net.cedu.entity.crm.Student, int)
	 */
	public int countStudCountForAcademyRebateByStudent(Student student,int feeSubjectId) throws Exception
	{
		return feePaymentDetailDao.countStudCountForAcademyRebateByStudent(student, feeSubjectId);
	}
	
	/*
	 * 2012-07-04 重构
	 * 根据缴费单明细统计满足条件的学生个数_院校返款匹配院校返款标准用（根据学生实体）
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#countStudCountForAcademyRebateReviewByStudentFeeSubjectIdFpdIds(net.cedu.entity.crm.Student, int, java.lang.String)
	 */
	public int countStudCountForAcademyRebateReviewByStudentFeeSubjectIdFpdIds(Student student,int feeSubjectId,String fpdIds) throws Exception
	{
		return feePaymentDetailDao.countStudCountForAcademyRebateReviewByStudentFeeSubjectIdFpdIds(student,feeSubjectId,fpdIds);
	}
	
	/*
	 * 统计需要添加院校返款单的缴费单院校返款总金额（根据缴费单明细ids字符串）
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#countFpdAllAcademyRebateMoneyByFpdIds(java.lang.String)
	 */
	public String countFpdAllAcademyRebateMoneyByFpdIds(String fpdIds) throws Exception
	{
		return feePaymentDetailDao.countFpdAllAcademyRebateMoneyByFpdIds(fpdIds);
	}
	
	
	
	//TODO ******************************************************招生返款重构2012-05-18*******************************************************//
	
	
	/*
	 * 2012-05-29 重构  暂时用于显示符合条件的招生返款单查询
	 * 查询符合招生返款的所有缴费单明细数量（添加院校招生返款单_重构）（分页）-------暂时只对学费进行招生返款
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findfpdCountByPageForChannelRecruitRebate(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.crm.Student,java.lang.String)
	 */
	public int findfpdCountByPageForChannelRecruitRebate(FeePaymentDetail feePaymentDetail,Student student,String feepdIds) throws Exception
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feePaymentDetail != null && student!=null)
		{				
			//选择过的缴费单Ids字符串
			if (feepdIds!=null && !feepdIds.equals("")) 
			{
				hqlparam += " and id not in ( "+ Constants.PLACEHOLDER+" ) ";
				list.add("$" + feepdIds);
			}
			
			// 缴费单招生返款状态
			if (feePaymentDetail.getRebateStatus() != 0) 
			{
				hqlparam += " and rebateStatus ="+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getRebateStatus());
			}
			
			//总部确认时间
			if(feePaymentDetail.getCeduConfirmTime()!=null)
			{
				hqlparam += " and ( ceduConfirmTime <="+ Constants.PLACEHOLDER+" or ceduConfirmTime is null ) ";
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");				
				list.add(df.format(feePaymentDetail.getCeduConfirmTime()));
			}
			//缴费金额
			if (feePaymentDetail.getJiaofeiValue()>0) 
			{
				hqlparam += " and (amountPaied+rechargeAmount-refundAmount) >="+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getJiaofeiValue());
			}
			
			//缴费单状态     根据招生途径不同，满足缴费的状态也不同        老带新、大客户总部确认后就能返款，其他的要打款院校才能返款
			if (student.getEnrollmentSource()==Constants.WEB_STU_ENROLLMENT_SOURCE || student.getEnrollmentSource()==Constants.WEB_STU_DA_KE_HU)
			{
				hqlparam += " and status >="+ Constants.PLACEHOLDER;
				list.add(Constants.PAYMENT_STATUS_ZONG_BU_QUE_REN);
			}
			else
			{
				hqlparam += " and status >="+ Constants.PLACEHOLDER;
				list.add(Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO);
			}
			
			
			//费用科目    暂时只对学费进行招生返款
			hqlparam += " and feeSubjectId ="+ Constants.PLACEHOLDER;
			list.add(FeeSubjectEnum.TuitionFee.value());
			
			//退费中的不能进行招生返款
			hqlparam += " and refundLock <> "+ Constants.PLACEHOLDER;
			list.add(Constants.LOCKING_TRUE);
						
			//学生Ids__Hql
			String stuHql=this.findStudentIdsHqlForChannelRecruitRebate(student);
			if (stuHql != null && !stuHql.equals("select id from Student where 1=1 ")) 
			{
				hqlparam += " and studentId in ("
							+ stuHql + ")";
			}
			else
			{
				return 0;
			}
			
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}

		return feePaymentDetailDao.getCounts(p);
		
	}
	
	/*
	 * 2012-05-29 重构  暂时用于显示符合条件的招生返款单查询
	 * 查询符合招生返款的所有缴费单明细集合（添加院校招生返款单_重构）（分页）-------暂时只对学费进行招生返款
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findfpdListByPageForChannelRecruitRebate(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.crm.Student, java.lang.String, net.cedu.model.page.PageResult)
	 */
	public List<FeePaymentDetail> findfpdListByPageForChannelRecruitRebate(FeePaymentDetail feePaymentDetail,Student student,String feepdIds,PageResult<FeePaymentDetail> pr) throws Exception
	{
		List<FeePaymentDetail> feepdlist=null;
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feePaymentDetail != null && student!=null)
		{				
			
			//选择过的缴费单Ids字符串
			if (feepdIds!=null && !feepdIds.equals("")) 
			{
				hqlparam += " and id not in ( "+ Constants.PLACEHOLDER+" ) ";
				list.add("$" + feepdIds);
			}
			
			// 缴费单招生返款状态
			if (feePaymentDetail.getRebateStatus() != 0) 
			{
				hqlparam += " and rebateStatus ="+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getRebateStatus());
			}
			
			//总部确认时间
			if(feePaymentDetail.getCeduConfirmTime()!=null)
			{
				hqlparam += " and ( ceduConfirmTime <="+ Constants.PLACEHOLDER+" or ceduConfirmTime is null ) ";
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");				
				list.add(df.format(feePaymentDetail.getCeduConfirmTime()));
			}
			
			//缴费金额
			if (feePaymentDetail.getJiaofeiValue()>0) 
			{
				hqlparam += " and (amountPaied+rechargeAmount-refundAmount) >="+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getJiaofeiValue());
			}
			
			//缴费单状态     根据招生途径不同，满足缴费的状态也不同        老带新、大客户总部确认后就能返款，其他的要打款院校才能返款
			if (student.getEnrollmentSource()==Constants.WEB_STU_ENROLLMENT_SOURCE || student.getEnrollmentSource()==Constants.WEB_STU_DA_KE_HU)
			{
				hqlparam += " and status >="+ Constants.PLACEHOLDER;
				list.add(Constants.PAYMENT_STATUS_ZONG_BU_QUE_REN);
			}
			else
			{
				hqlparam += " and status >="+ Constants.PLACEHOLDER;
				list.add(Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO);
			}
			
			//费用科目    暂时只对学费进行招生返款
			hqlparam += " and feeSubjectId ="+ Constants.PLACEHOLDER;
			list.add(FeeSubjectEnum.TuitionFee.value());
			
			//退费中的不能进行招生返款
			hqlparam += " and refundLock <> "+ Constants.PLACEHOLDER;
			list.add(Constants.LOCKING_TRUE);
						
			//学生Ids__Hql
			String stuHql=this.findStudentIdsHqlForChannelRecruitRebate(student);
			if (stuHql != null && !stuHql.equals("select id from Student where 1=1 ")) 
			{
				hqlparam += " and studentId in ("
							+ stuHql + ")";
			}
			else
			{
				return null;
			}
			
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		
		Long[] feeIds = feePaymentDetailDao.getIDs(p);
		if (feeIds != null && feeIds.length != 0) 
		{
			feepdlist=new ArrayList<FeePaymentDetail>();
			for (int i = 0; i < feeIds.length; i++) 
			{
				// 内存获取
				FeePaymentDetail feeObj = feePaymentDetailDao.findById(Integer.valueOf(feeIds[i].toString()));
				if(feeObj!=null)
				{
					//缴费单的编号
					FeePayment fp=feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId());
					if(feeObj.getFeePaymentId()>0 && fp!=null)
					{
						feeObj.setPaymentCode(fp.getCode());
						feeObj.setFeeWayId(fp.getFeeWayId());//缴费方式
					}
					else
					{
						feeObj.setPaymentCode("");
					}
					//学生相关
					Student stu=studentBiz.findStudentById(feeObj.getStudentId());
					if(feeObj.getStudentId()>0 && stu!=null)
					{
						//学生姓名
						feeObj.setStudentName(stu.getName());
						//院校
						Academy academy=academyBiz.findAcademyById(stu.getAcademyId());
						if(stu.getAcademyId()>0 && academy!=null)
						{
							feeObj.setSchoolName(academy.getName());
						}
						//学习中心
						Branch branch=branchBiz.findBranchById(stu.getBranchId());
						if(stu.getBranchId()>0 && branch!=null)
						{
							feeObj.setBranchName(branch.getName());
						}
						//批次
						AcademyEnrollBatch aeb=academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId());
						if(stu.getEnrollmentBatchId()>0 && aeb!=null)
						{
							feeObj.setAcademyenrollbatchName(aeb.getEnrollmentName());
						}
						//层次
						Level level=levelbiz.findLevelById(stu.getLevelId());
						if(stu.getLevelId()>0 && level!=null)
						{
							feeObj.setLevelName(level.getName());
						}
						//专业
						Major major=majorbiz.findMajorById(stu.getMajorId());
						if(stu.getMajorId()>0 && major!=null)
						{
							feeObj.setMajorName(major.getName());
						}
						// 合作方
						Channel channel=this.channelBiz.findChannel(stu.getChannelId());
						if(stu.getChannelId()>0 && channel!=null)
						{
							feeObj.setChannelName(channel.getName());
						}
					}
					//费用科目
					FeeSubject fs=feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId());
					if(feeObj.getFeeSubjectId()>0 && fs!=null)
					{
						feeObj.setPaymentSubjectName(fs.getName());
					}
					//缴费金额
					feeObj.setJiaofeiValue((new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())).subtract(new BigDecimal(feeObj.getRefundAmount()))).doubleValue());
					
					feepdlist.add(feeObj);
				}
			}
		}
		return feepdlist;
		
	}
	
	/**
	 * 查询学生ID_Hql 集合(招生返款用)__重构
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	private String findStudentIdsHqlForChannelRecruitRebate(Student student) throws Exception 
	{
		String stuHql= "select id from Student where 1=1 ";
		if (student != null)
		{
			//招生途径enrollmentSource
			if (student.getEnrollmentSource() != 0)
			{
				stuHql += " and enrollmentSource ="+student.getEnrollmentSource();						
			}
			//合作方channelIds
			if(student.getParamsString().get("channelIds")!=null)
			{
				stuHql += " and channelId in ( "+student.getParamsString().get("channelIds")+" ) ";
			}
			else if (student.getChannelId() != 0) 
			{
				stuHql += " and channelId ="+student.getChannelId();
			}
			// 姓名
			if (student.getName() != null && !student.getName().equals("")) 
			{
				stuHql += " and name like '%"+student.getName()+"%' ";
			}
			// 证件号
			if (student.getCertNo() != null && !"".equals(student.getCertNo()))
			{
				stuHql += " and certNo like '%"+student.getCertNo()+"%' ";
			}
			// 院校
			if (student.getAcademyId() != 0)
			{
				stuHql += " and academyId="+student.getAcademyId();					
			}
			// 全局批次
			if (student.getGlbtach() > 0) 
			{
				List<AcademyEnrollBatch> aeblst = academyenrollbatchBiz
							.findAcademyEnrollBatchByGId(student.getGlbtach());
				String gbatchIds = "";
				if (null != aeblst && aeblst.size() > 0)
				{
					for (int i = 0; i < aeblst.size(); i++)
					{
						gbatchIds += "," + aeblst.get(i).getId();
					}
					stuHql += " and enrollmentBatchId in ( "+ gbatchIds.substring(1, gbatchIds.length()) + " ) ";	
				} 
				else 
				{
					return null;
				}
			}
			// 学习中心
			if (student.getBranchId() != 0) 
			{
				stuHql += " and  branchId= "+student.getBranchId();
			}
			// 批次
			if (student.getEnrollmentBatchId() != 0)
			{
				stuHql += " and  enrollmentBatchId= "+student.getEnrollmentBatchId();
			}
			
			// 层次
			if (student.getLevelId() != 0) 
			{
				stuHql += " and  levelId= "+student.getLevelId();
			}
			// 专业
			if (student.getMajorId() != 0) 
			{
				stuHql += " and  majorId= "+student.getMajorId();
			}
			
			//已缴学费   且已开课、已招生途径复核的学生才能招生返款
			stuHql += " and tuitionStatus >= "+Constants.STU_TUITION_STATUS_SHOU_CI_JIAO_FEI;
			stuHql += " and  isStartCourse= "+Constants.STU_IS_START_COURSE_TRUE;	
			stuHql += " and  isChannelTypeChecked= "+ Constants.STUDENT_CHANNEL_TYPE_CHECKED_TRUE;	
//			//是否开课
//			if(student.getParamsInt().get("isStartCourse")!=null)
//			{
//				stuHql += " and isStartCourse= "+student.getParamsInt().get("isStartCourse");
//			}
//			//是否已招生途径复核
//			if(student.getParamsInt().get("isChannelTypeChecked")!=null)
//			{
//				stuHql += " and isChannelTypeChecked= "+student.getParamsInt().get("isChannelTypeChecked");
//			}
			
				
			//是否已经招生返款          主要是控制老带新只招生返款一次；大客户和渠道等其他多次返款的如果第一匹配政策是固定金额，后面就不再返款
			stuHql += " and  isChannelRebate= "+Constants.STUDENT_IS_CHANNEL_REBATE_FALSE;			
			
			// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
			// 如果起始状态ID>0,结束状态ID=0;则为无穷大
			// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
			// 如果都大于0,则取交集
			if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0)
			{
				stuHql += " and  status <"+student.getEndStatusId();
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0)
			{
				stuHql += " and  status >"+student.getStartStatusId();
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0)
			{
				stuHql += " and  status> "+student.getStartStatusId() + " and status<"+student.getEndStatusId();
			}
		}
		return stuHql;
	}
		
	
	/*
	 * 查询符合招生返款的所有缴费单明细数量（添加招生返款单_重构_2012-05-24）（分页）-------暂时只对学费进行招生返款
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findfpdCountByPageForChannelRecruitRebateAcademyReview(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.crm.Student, java.lang.String)
	 */
	public int findfpdCountByPageForChannelRecruitRebateAcademyReview(FeePaymentDetail feePaymentDetail,Student student,String feepdIds) throws Exception
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feePaymentDetail != null)
		{					
			// 缴费单招生返款状态
			if (feePaymentDetail.getRebateStatus() != 0) 
			{
				hqlparam += " and rebateStatus <="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getRebateStatus());
			}
			//缴费单状态
			if (feePaymentDetail.getStartStatusId()!= 0) 
			{
				hqlparam += " and status >="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getStartStatusId());
			}
			//缴费单总部确认时间
			if (feePaymentDetail.getCeduConfirmTime()!= null && !feePaymentDetail.getCeduConfirmTime().equals("")) 
			{
				hqlparam += " and ( ceduConfirmTime <="+ Constants.PLACEHOLDER+" or ceduConfirmTime is null ) ";
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");				
				list.add(df.format(feePaymentDetail.getCeduConfirmTime()));
			}
			
			//选择过的缴费单Ids字符串
			if (feepdIds!=null && !feepdIds.equals("")) 
			{
				hqlparam += " and id not in ( "
						+ Constants.PLACEHOLDER+" ) ";
				list.add("$" + feepdIds);
			}			
			
			//费用科目    暂时只对学费进行招生返款
			hqlparam += " and feeSubjectId ="+ Constants.PLACEHOLDER;
			list.add(FeeSubjectEnum.TuitionFee.value());
			
			//退费中的不能进行招生返款__2012-04-03
			//hqlparam += " and refundLock <> "+ Constants.PLACEHOLDER;
			//list.add(Constants.LOCKING_TRUE);
			
			//学生Ids__Hql
			String stuHql=this.findStudentIdsHqlForChannelReview(student);
			if (stuHql != null && !stuHql.equals("select id from Student where 1=1 ")) 
			{
				hqlparam += " and studentId in ("+ stuHql + ")";
			}
			else
			{
				return 0;
			}			
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		return feePaymentDetailDao.getCounts(p);		
	}
	
	/*
	 * 查询符合招生返款的所有缴费单明细集合（添加招生返款单_重构_2012-05-24）（分页）-------暂时只对学费进行招生返款
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findfpdListByPageForChannelRebateAcademyReview(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.crm.Student,java.lang.String, java.lang.String, java.lang.String, net.cedu.model.page.PageResult)
	 */
	public List<FeePaymentDetail> findfpdListByPageForChannelRecruitRebateAcademyReview(FeePaymentDetail feePaymentDetail,Student student,String feepdIds,PageResult<FeePaymentDetail> pr) throws Exception
	{
		List<FeePaymentDetail> feepdlist=null;
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feePaymentDetail != null)
		{						
			// 缴费单招生返款状态
			if (feePaymentDetail.getRebateStatus() != 0) 
			{
				hqlparam += " and rebateStatus <="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getRebateStatus());
			}
			//缴费单状态
			if (feePaymentDetail.getStartStatusId()!= 0) 
			{
				hqlparam += " and status >="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getStartStatusId());
			}
			//缴费单总部确认时间
			if (feePaymentDetail.getCeduConfirmTime()!= null && !feePaymentDetail.getCeduConfirmTime().equals("")) 
			{
				hqlparam += " and ( ceduConfirmTime <="+ Constants.PLACEHOLDER+" or ceduConfirmTime is null ) ";
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");				
				list.add(df.format(feePaymentDetail.getCeduConfirmTime()));
			}
			
			//选择过的缴费单Ids字符串
			if (feepdIds!=null && !feepdIds.equals("")) 
			{
				hqlparam += " and id not in ( "
						+ Constants.PLACEHOLDER+" ) ";
				list.add("$" + feepdIds);
			}			
			
			//费用科目    暂时只对学费进行招生返款
			hqlparam += " and feeSubjectId ="+ Constants.PLACEHOLDER;
			list.add(FeeSubjectEnum.TuitionFee.value());
			
			//退费中的不能进行招生返款__2012-04-03
			//hqlparam += " and refundLock <> "+ Constants.PLACEHOLDER;
			//list.add(Constants.LOCKING_TRUE);
			
			//学生Ids__Hql
			String stuHql=this.findStudentIdsHqlForChannelReview(student);
			if (stuHql != null && !stuHql.equals("select id from Student where 1=1 ")) 
			{
				hqlparam += " and studentId in ("+ stuHql + ")";
			}
			else
			{
				return null;
			}			
			
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}

		Long[] feeIds = feePaymentDetailDao.getIDs(p);
		if (feeIds != null && feeIds.length != 0) 
		{
			feepdlist=new ArrayList<FeePaymentDetail>();
			for (int i = 0; i < feeIds.length; i++) 
			{
				// 内存获取
				FeePaymentDetail feeObj = feePaymentDetailDao.findById(Integer.valueOf(feeIds[i].toString()));
				if(feeObj!=null)
				{
					//缴费单的编号
					FeePayment fp=feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId());
					if(feeObj.getFeePaymentId()>0 && fp!=null)
					{
						feeObj.setPaymentCode(fp.getCode());
						feeObj.setFeeWayId(fp.getFeeWayId());//缴费方式
					}
					else
					{
						feeObj.setPaymentCode("");
					}
					//学生相关
					Student stu=studentBiz.findStudentById(feeObj.getStudentId());
					if(feeObj.getStudentId()>0 && stu!=null)
					{
						//学生姓名
						feeObj.setStudentName(stu.getName());
						//院校
						Academy academy=academyBiz.findAcademyById(stu.getAcademyId());
						if(stu.getAcademyId()>0 && academy!=null)
						{
							feeObj.setSchoolName(academy.getName());
						}
						//学习中心
						Branch branch=branchBiz.findBranchById(stu.getBranchId());
						if(stu.getBranchId()>0 && branch!=null)
						{
							feeObj.setBranchName(branch.getName());
						}
						//批次
						AcademyEnrollBatch aeb=academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId());
						if(stu.getEnrollmentBatchId()>0 && aeb!=null)
						{
							feeObj.setAcademyenrollbatchName(aeb.getEnrollmentName());
						}
						//层次
						Level level=levelbiz.findLevelById(stu.getLevelId());
						if(stu.getLevelId()>0 && level!=null)
						{
							feeObj.setLevelName(level.getName());
						}
						//专业
						Major major=majorbiz.findMajorById(stu.getMajorId());
						if(stu.getMajorId()>0 && major!=null)
						{
							feeObj.setMajorName(major.getName());
						}
						//合作方
						Channel channel=this.channelBiz.findChannel(stu.getChannelId());
						if(channel!=null)
						{
							feeObj.setChannelName(channel.getName());
						}
						//是否招生途径复核
						feeObj.setIsChannelTypeChecked(stu.getIsChannelTypeChecked());
						//是否开课
						feeObj.setIsStartCourse(stu.getIsStartCourse());
						//招生途径
						feeObj.setEnrollmentSource(stu.getEnrollmentSource());
					}
					//费用科目
					FeeSubject fs=feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId());
					if(feeObj.getFeeSubjectId()>0 && fs!=null)
					{
						feeObj.setPaymentSubjectName(fs.getName());
					}
					//缴费金额
					feeObj.setJiaofeiValue((new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())).subtract(new BigDecimal(feeObj.getRefundAmount()))).doubleValue());
					
					feepdlist.add(feeObj);
				}
			}
		}
		return feepdlist;
		
	}
	
	/**
	 * 查询学生ID_Hql 集合(招生返款用)__重构2012-05-24
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	private String findStudentIdsHqlForChannelReview(Student student) throws Exception 
	{
		String stuHql= "select id from Student where 1=1 ";
		if (student != null)
		{
			//招生途径enrollmentSource
			if (student.getEnrollmentSource() != 0)
			{
				stuHql += " and enrollmentSource ="+ student.getEnrollmentSource();
			}
			//合作方channelIds
			if(student.getParamsString().get("channelIds")!=null)
			{
				stuHql += " and channelId in ( "+student.getParamsString().get("channelIds")+" ) ";
			}
			else if (student.getChannelId() != 0) 
			{
				stuHql += " and channelId ="+student.getChannelId();
			}
			
			// 院校
			if (student.getAcademyId() != 0)
			{
				stuHql += " and academyId="+ student.getAcademyId();
			}
			// 批次
			if (student.getEnrollmentBatchId() != 0)
			{
				stuHql += " and  enrollmentBatchId= "+ student.getEnrollmentBatchId();
			}
			// 全局批次
			if (student.getGlbtach() > 0) 
			{
				List<AcademyEnrollBatch> aeblst = academyenrollbatchBiz
							.findAcademyEnrollBatchByGId(student.getGlbtach());
				String gbatchIds = "";
				if (null != aeblst && aeblst.size() > 0)
				{
					for (int i = 0; i < aeblst.size(); i++)
					{
						gbatchIds += "," + aeblst.get(i).getId();
					}
					stuHql += " and enrollmentBatchId in ( "+gbatchIds.substring(1, gbatchIds.length())+ " ) ";		
				} 
				else 
				{
					return null;
				}
			}
			//多批次
			if(student.getAcademyenrollbatchName()!=null && !"".equals(student.getAcademyenrollbatchName()))
			{
				stuHql += " and  enrollmentBatchId in ("+student.getAcademyenrollbatchName()+ ")";
			}
			// 层次
			if (student.getLevelId() != 0)
			{
				stuHql += " and  levelId= "+ student.getLevelId();
			}
			// 专业
			if (student.getMajorId() != 0)
			{
				stuHql += " and  majorId= "+ student.getMajorId();
			}
			// 学习中心
			if (student.getBranchId() != 0)
			{
				stuHql += " and  branchId= "+ student.getBranchId();
			}
			// 姓名
			if (student.getName() != null && !student.getName().equals("")) 
			{
				stuHql += " and name like '%"+student.getName()+"%' ";
			}
			// 证件号
			if (student.getCertNo() != null && !"".equals(student.getCertNo()))
			{
				stuHql += " and certNo like '%"+student.getCertNo()+"%' ";
			}
			//已缴学费   且已开课、已招生途径复核的学生才能招生返款
			stuHql += " and  tuitionStatus >= "+Constants.STU_TUITION_STATUS_SHOU_CI_JIAO_FEI;
			
			//2012-04-18重构  屏蔽掉已开课和已招生途径复核的学生筛选项
//			stuHql += " and  isStartCourse= "
//				+Constants.STU_IS_START_COURSE_TRUE;
//			
//			stuHql += " and  isChannelTypeChecked= "
//				+ Constants.STUDENT_CHANNEL_TYPE_CHECKED_TRUE;
			
			
			//是否已经招生返款          主要是控制老带新只招生返款一次；大客户和渠道等其他多次返款的如果第一匹配政策是固定金额，后面就不再返款
			stuHql += " and  isChannelRebate= "+ Constants.STUDENT_IS_CHANNEL_REBATE_FALSE;
			
			
			// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
			// 如果起始状态ID>0,结束状态ID=0;则为无穷大
			// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
			// 如果都大于0,则取交集
			if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0) 
			{
				stuHql += " and  status <"+ student.getEndStatusId();
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0)
			{
				stuHql += " and  status >"+ student.getStartStatusId();
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0) 
			{
				stuHql += " and  status> "+ student.getStartStatusId() + " and status<"+ student.getEndStatusId();
			}
		}
		return stuHql;
	}
	
	/*
	 * 2012-05-24重构
	 * 
	 * 通过缴费单明细匹配该合作方招生总人数（必须符合: i.已缴学费_总部或院校已确认 ii.已开课 iii.已招生途径复核、）__重构
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findfpdCountByChannelAllPeopleCount(net.cedu.entity.crm.Student)
	 */
	public int findfpdCountByChannelRebateReviewAllPeopleCount(Student student,String fpdIds) throws Exception
	{
		return feePaymentDetailDao.countStuCountForChannelRebateReviewByStuAndFpdIds(student,fpdIds);
	}
	
	/*
	 * * 2012-05-26 重构   跨中心合作方返款
	 * 根据缴费单明细统计满足条件的学生个数_招生返款匹配合作方招生总人数用（根据学生实体和缴费单Ids字符串）
	 * 暂时只对学费的缴费单明细进行统计
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#countStuCountForChannelRebateSpecialReviewByStuAndFpdIds(net.cedu.entity.crm.Student, java.lang.String)
	 */
	public int countStuCountForChannelRebateSpecialReviewByStuAndFpdIds(Student student,String fpdIds) throws Exception
	{
		return feePaymentDetailDao.countStuCountForChannelRebateSpecialReviewByStuAndFpdIds(student,fpdIds);
	}
	
	
	/*
	 * 2012-05-26 重构    招生返款后续功能
	 *  如果学生有招生返款单则不让操作来源复核
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findfpdCountForChannelTypeChecked(int)
	 */
	public int findfpdCountForChannelTypeChecked(int studentId) throws Exception
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (studentId >0)
		{				
			hqlparam += " and studentId ="+ Constants.PLACEHOLDER;
			list.add(studentId);
			
			//缴费单状态和招生返款状态
			hqlparam += " and status >="+ Constants.PLACEHOLDER;
			list.add(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);
			hqlparam += " and rebateStatus >="+ Constants.PLACEHOLDER;
			list.add(Constants.PAYMENT_STATUS_YI_TIAN_ZHAO_SHENG_FAN_KUAN);
			
		}
		else
		{
			return 0;
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}

		return feePaymentDetailDao.getCounts(p);
		
	}
	
	/*
	 * * 2012-06-07 重构  
	 * 不符合未返款查询数量
	 * 查询不符合招生返款的所有缴费单明细数量（招生返款单未提交查询_重构）（分页）-------暂时只对学费进行招生返款
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findfpdCountByPageForAllNotChannelRecruitRebate(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.crm.Student, java.lang.String)
	 */
	public int findfpdCountByPageForAllNotChannelRecruitRebate(FeePaymentDetail feePaymentDetail,Student student,String feepdIds) throws Exception
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feePaymentDetail != null && student!=null)
		{				
			//选择过的缴费单Ids字符串
			if (feepdIds!=null && !feepdIds.equals("")) 
			{
				hqlparam += " and id not in ( "+ Constants.PLACEHOLDER+" ) ";
				list.add("$" + feepdIds);
			}
			
			//费用科目    暂时只对学费进行招生返款
			hqlparam += " and feeSubjectId ="+ Constants.PLACEHOLDER;
			list.add(FeeSubjectEnum.TuitionFee.value());
			
			//缴费单状态
			if (feePaymentDetail.getStartStatusId()!= 0) 
			{
				hqlparam += " and status >="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getStartStatusId());
			}
			
			//总部确认时间
			if(feePaymentDetail.getCeduConfirmTime()!=null)
			{
				hqlparam += " and ( ceduConfirmTime <="+ Constants.PLACEHOLDER+" or ceduConfirmTime is null ) ";
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");				
				list.add(df.format(feePaymentDetail.getCeduConfirmTime()));
			}
			
			// 缴费单招生返款状态
			if (feePaymentDetail.getRebateStatus() != 0) 
			{
				hqlparam += " and rebateStatus <="+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getRebateStatus());
			}
			
			//所有的学生集合
			//学生Ids__Hql
			String stuHql=this.findStudentIdsHqlForAllNotChannelRecruitRebate(student);
			if (stuHql != null && !stuHql.equals("select id from Student where 1=1 ")) 
			{
				hqlparam += " and studentId in ("
							+ stuHql + ")";
			}
			else
			{
				return 0;
			}
			
			//不满足条件的各种情况
			
			//招生返款状态不符合的
			hqlparam += " and (  rebateStatus <"+ Constants.PLACEHOLDER;
			list.add(Constants.PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN);
			
			//缴费金额
			if (feePaymentDetail.getJiaofeiValue()>0) 
			{
				hqlparam += " or (amountPaied+rechargeAmount-refundAmount) <"+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getJiaofeiValue());
			}
			
			//缴费单状态     根据招生途径不同，满足缴费的状态也不同        老带新、大客户总部确认后就能返款，其他的要打款院校才能返款
			if (student.getEnrollmentSource()==Constants.WEB_STU_ENROLLMENT_SOURCE || student.getEnrollmentSource()==Constants.WEB_STU_DA_KE_HU)
			{
				hqlparam += " or status <"+ Constants.PLACEHOLDER;
				list.add(Constants.PAYMENT_STATUS_ZONG_BU_QUE_REN);
			}
			else
			{
				hqlparam += " or status <"+ Constants.PLACEHOLDER;
				list.add(Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO);
			}
			
			//学生Ids__Hql  or关系的
			String stuHqlload=this.findStudentIdsHqlForAllNotChannelRecruitRebateLoad(student);
			if (stuHqlload != null && !stuHqlload.equals("select id from Student where 1=1 ")) 
			{
				hqlparam += " or studentId in ("
							+ stuHqlload + ")";
			}
			else
			{
				return 0;
			}
			
			//退费中的不能进行招生返款
			hqlparam += " or refundLock = "+ Constants.PLACEHOLDER+" ) ";
			list.add(Constants.LOCKING_TRUE);
			
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}

		return feePaymentDetailDao.getCounts(p);
		
	}
	
	/*
	 * * 2012-06-07 重构  
	 * 不符合未返款查询集合
	 * 查询不符合招生返款的所有缴费单明细集合（招生返款单未提交查询_重构）（分页）-------暂时只对学费进行招生返款
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findfpdListByPageForAllNotChannelRecruitRebate(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.crm.Student, java.lang.String, net.cedu.model.page.PageResult)
	 */
	public List<FeePaymentDetail> findfpdListByPageForAllNotChannelRecruitRebate(FeePaymentDetail feePaymentDetail,Student student,String feepdIds,PageResult<FeePaymentDetail> pr) throws Exception
	{
		List<FeePaymentDetail> feepdlist=null;
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feePaymentDetail != null && student!=null)
		{				
			
			//选择过的缴费单Ids字符串
			if (feepdIds!=null && !feepdIds.equals("")) 
			{
				hqlparam += " and id not in ( "+ Constants.PLACEHOLDER+" ) ";
				list.add("$" + feepdIds);
			}
			
			//费用科目    暂时只对学费进行招生返款
			hqlparam += " and feeSubjectId ="+ Constants.PLACEHOLDER;
			list.add(FeeSubjectEnum.TuitionFee.value());
			
			//缴费单状态
			if (feePaymentDetail.getStartStatusId()!= 0) 
			{
				hqlparam += " and status >="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getStartStatusId());
			}
			
			//总部确认时间
			if(feePaymentDetail.getCeduConfirmTime()!=null)
			{
				hqlparam += " and ( ceduConfirmTime <="+ Constants.PLACEHOLDER+" or ceduConfirmTime is null ) ";
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");				
				list.add(df.format(feePaymentDetail.getCeduConfirmTime()));
			}
			
			// 缴费单招生返款状态
			if (feePaymentDetail.getRebateStatus() != 0) 
			{
				hqlparam += " and rebateStatus <="+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getRebateStatus());
			}
			
			//所有的学生集合
			//学生Ids__Hql
			String stuHql=this.findStudentIdsHqlForAllNotChannelRecruitRebate(student);
			if (stuHql != null && !stuHql.equals("select id from Student where 1=1 ")) 
			{
				hqlparam += " and studentId in ("
							+ stuHql + ")";
			}
			else
			{
				return null;
			}
			
			//不满足条件的各种情况
			
			//招生返款状态不符合的
			hqlparam += " and (  rebateStatus <"+ Constants.PLACEHOLDER;
			list.add(Constants.PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN);
			
			//缴费金额
			if (feePaymentDetail.getJiaofeiValue()>0) 
			{
				hqlparam += " or (amountPaied+rechargeAmount-refundAmount) <"+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getJiaofeiValue());
			}
			
			//缴费单状态     根据招生途径不同，满足缴费的状态也不同        老带新、大客户总部确认后就能返款，其他的要打款院校才能返款
			if (student.getEnrollmentSource()==Constants.WEB_STU_ENROLLMENT_SOURCE || student.getEnrollmentSource()==Constants.WEB_STU_DA_KE_HU)
			{
				hqlparam += " or status <"+ Constants.PLACEHOLDER;
				list.add(Constants.PAYMENT_STATUS_ZONG_BU_QUE_REN);
			}
			else
			{
				hqlparam += " or status <"+ Constants.PLACEHOLDER;
				list.add(Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO);
			}
			
			//学生Ids__Hql  or关系的
			String stuHqlload=this.findStudentIdsHqlForAllNotChannelRecruitRebateLoad(student);
			if (stuHqlload != null && !stuHqlload.equals("select id from Student where 1=1 ")) 
			{
				hqlparam += " or studentId in ("
							+ stuHqlload + ")";
			}
			else
			{
				return null;
			}
			
			//退费中的不能进行招生返款
			hqlparam += " or refundLock = "+ Constants.PLACEHOLDER+" ) ";
			list.add(Constants.LOCKING_TRUE);
			
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		
		Long[] feeIds = feePaymentDetailDao.getIDs(p);
		if (feeIds != null && feeIds.length != 0) 
		{
			feepdlist=new ArrayList<FeePaymentDetail>();
			for (int i = 0; i < feeIds.length; i++) 
			{
				// 内存获取
				FeePaymentDetail feeObj = feePaymentDetailDao.findById(Integer.valueOf(feeIds[i].toString()));
				if(feeObj!=null)
				{
					//缴费单的编号
					FeePayment fp=feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId());
					if(feeObj.getFeePaymentId()>0 && fp!=null)
					{
						feeObj.setPaymentCode(fp.getCode());
						feeObj.setFeeWayId(fp.getFeeWayId());//缴费方式
					}
					else
					{
						feeObj.setPaymentCode("");
					}
					//学生相关
					Student stu=studentBiz.findStudentById(feeObj.getStudentId());
					if(feeObj.getStudentId()>0 && stu!=null)
					{
						//学生姓名
						feeObj.setStudentName(stu.getName());
						//院校
						Academy academy=academyBiz.findAcademyById(stu.getAcademyId());
						if(stu.getAcademyId()>0 && academy!=null)
						{
							feeObj.setSchoolName(academy.getName());
						}
						//学习中心
						Branch branch=branchBiz.findBranchById(stu.getBranchId());
						if(stu.getBranchId()>0 && branch!=null)
						{
							feeObj.setBranchName(branch.getName());
						}
						//批次
						AcademyEnrollBatch aeb=academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId());
						if(stu.getEnrollmentBatchId()>0 && aeb!=null)
						{
							feeObj.setAcademyenrollbatchName(aeb.getEnrollmentName());
						}
						//层次
						Level level=levelbiz.findLevelById(stu.getLevelId());
						if(stu.getLevelId()>0 && level!=null)
						{
							feeObj.setLevelName(level.getName());
						}
						//专业
						Major major=majorbiz.findMajorById(stu.getMajorId());
						if(stu.getMajorId()>0 && major!=null)
						{
							feeObj.setMajorName(major.getName());
						}
						// 合作方
						Channel channel=this.channelBiz.findChannel(stu.getChannelId());
						if(stu.getChannelId()>0 && channel!=null)
						{
							feeObj.setChannelName(channel.getName());
						}
						//是否招生途径复核
						feeObj.setIsChannelTypeChecked(stu.getIsChannelTypeChecked());
						//是否开课
						feeObj.setIsStartCourse(stu.getIsStartCourse());
						//招生途径
						feeObj.setEnrollmentSource(stu.getEnrollmentSource());
					}
					//费用科目
					FeeSubject fs=feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId());
					if(feeObj.getFeeSubjectId()>0 && fs!=null)
					{
						feeObj.setPaymentSubjectName(fs.getName());
					}
					//缴费金额
					feeObj.setJiaofeiValue((new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())).subtract(new BigDecimal(feeObj.getRefundAmount()))).doubleValue());
					
					feepdlist.add(feeObj);
				}
			}
		}
		return feepdlist;
		
	}
	
	/**
	 * 查询学生ID_Hql 集合(招生返款未提交查询用)__重构
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	private String findStudentIdsHqlForAllNotChannelRecruitRebate(Student student) throws Exception 
	{
		String stuHql= "select id from Student where 1=1 ";
		if (student != null)
		{
			//招生途径enrollmentSource
			if (student.getEnrollmentSource() != 0)
			{
				stuHql += " and enrollmentSource ="+student.getEnrollmentSource();						
			}
			//合作方channelIds
			if(student.getParamsString().get("channelIds")!=null)
			{
				stuHql += " and channelId in ( "+student.getParamsString().get("channelIds")+" ) ";
			}
			else if (student.getChannelId() != 0) 
			{
				stuHql += " and channelId ="+student.getChannelId();
			}
			// 姓名
			if (student.getName() != null && !student.getName().equals("")) 
			{
				stuHql += " and name like '%"+student.getName()+"%' ";
			}
			// 证件号
			if (student.getCertNo() != null && !"".equals(student.getCertNo()))
			{
				stuHql += " and certNo like '%"+student.getCertNo()+"%' ";
			}
			// 院校
			if (student.getAcademyId() != 0)
			{
				stuHql += " and academyId="+student.getAcademyId();					
			}
			// 全局批次
			if (student.getGlbtach() > 0) 
			{
				List<AcademyEnrollBatch> aeblst = academyenrollbatchBiz
							.findAcademyEnrollBatchByGId(student.getGlbtach());
				String gbatchIds = "";
				if (null != aeblst && aeblst.size() > 0)
				{
					for (int i = 0; i < aeblst.size(); i++)
					{
						gbatchIds += "," + aeblst.get(i).getId();
					}
					stuHql += " and enrollmentBatchId in ( "+ gbatchIds.substring(1, gbatchIds.length()) + " ) ";	
				} 
				else 
				{
					return null;
				}
			}
			// 学习中心
			if (student.getBranchId() != 0) 
			{
				stuHql += " and  branchId= "+student.getBranchId();
			}
			// 批次
			if (student.getEnrollmentBatchId() != 0)
			{
				stuHql += " and  enrollmentBatchId= "+student.getEnrollmentBatchId();
			}
			
			// 层次
			if (student.getLevelId() != 0) 
			{
				stuHql += " and  levelId= "+student.getLevelId();
			}
			// 专业
			if (student.getMajorId() != 0) 
			{
				stuHql += " and  majorId= "+student.getMajorId();
			}
			
			//已缴学费   且已开课、已招生途径复核的学生才能招生返款
			stuHql += " and  tuitionStatus >= "+Constants.STU_TUITION_STATUS_SHOU_CI_JIAO_FEI;
			
//			stuHql += " and ( tuitionStatus < "+Constants.STU_TUITION_STATUS_SHOU_CI_JIAO_FEI;
//			stuHql += " or  isStartCourse= "+Constants.STU_IS_START_COURSE_FALSE;	
//			stuHql += " or  isChannelTypeChecked= "+ Constants.STUDENT_CHANNEL_TYPE_CHECKED_FALSE+" )";	

				
			//是否已经招生返款          主要是控制老带新只招生返款一次；大客户和渠道等其他多次返款的如果第一匹配政策是固定金额，后面就不再返款
			stuHql += " and  isChannelRebate= "+Constants.STUDENT_IS_CHANNEL_REBATE_FALSE;			
			
			// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
			// 如果起始状态ID>0,结束状态ID=0;则为无穷大
			// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
			// 如果都大于0,则取交集
			if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0)
			{
				stuHql += " and  status <"+student.getEndStatusId();
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0)
			{
				stuHql += " and  status >"+student.getStartStatusId();
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0)
			{
				stuHql += " and  status> "+student.getStartStatusId() + " and status<"+student.getEndStatusId();
			}
		}
		return stuHql;
	}
	
	/**
	 * 查询学生ID_Hql 集合(招生返款未提交查询用load)__重构
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	private String findStudentIdsHqlForAllNotChannelRecruitRebateLoad(Student student) throws Exception 
	{
		String stuHql= "select id from Student where 1=1 ";
		if (student != null)
		{
			//招生途径enrollmentSource
			if (student.getEnrollmentSource() != 0)
			{
				stuHql += " and enrollmentSource ="+student.getEnrollmentSource();						
			}
			//合作方channelIds
			if(student.getParamsString().get("channelIds")!=null)
			{
				stuHql += " and channelId in ( "+student.getParamsString().get("channelIds")+" ) ";
			}
			else if (student.getChannelId() != 0) 
			{
				stuHql += " and channelId ="+student.getChannelId();
			}
			// 姓名
			if (student.getName() != null && !student.getName().equals("")) 
			{
				stuHql += " and name like '%"+student.getName()+"%' ";
			}
			// 证件号
			if (student.getCertNo() != null && !"".equals(student.getCertNo()))
			{
				stuHql += " and certNo like '%"+student.getCertNo()+"%' ";
			}
			// 院校
			if (student.getAcademyId() != 0)
			{
				stuHql += " and academyId="+student.getAcademyId();					
			}
			// 全局批次
			if (student.getGlbtach() > 0) 
			{
				List<AcademyEnrollBatch> aeblst = academyenrollbatchBiz
							.findAcademyEnrollBatchByGId(student.getGlbtach());
				String gbatchIds = "";
				if (null != aeblst && aeblst.size() > 0)
				{
					for (int i = 0; i < aeblst.size(); i++)
					{
						gbatchIds += "," + aeblst.get(i).getId();
					}
					stuHql += " and enrollmentBatchId in ( "+ gbatchIds.substring(1, gbatchIds.length()) + " ) ";	
				} 
				else 
				{
					return null;
				}
			}
			// 学习中心
			if (student.getBranchId() != 0) 
			{
				stuHql += " and  branchId= "+student.getBranchId();
			}
			// 批次
			if (student.getEnrollmentBatchId() != 0)
			{
				stuHql += " and  enrollmentBatchId= "+student.getEnrollmentBatchId();
			}
			
			// 层次
			if (student.getLevelId() != 0) 
			{
				stuHql += " and  levelId= "+student.getLevelId();
			}
			// 专业
			if (student.getMajorId() != 0) 
			{
				stuHql += " and  majorId= "+student.getMajorId();
			}
			
			//已缴学费   且已开课、已招生途径复核的学生才能招生返款
			stuHql += " and  tuitionStatus >= "+Constants.STU_TUITION_STATUS_SHOU_CI_JIAO_FEI;
			
			stuHql += " and (  isStartCourse= "+Constants.STU_IS_START_COURSE_FALSE;	
			stuHql += " or  isChannelTypeChecked= "+ Constants.STUDENT_CHANNEL_TYPE_CHECKED_FALSE+" )";	

				
			//是否已经招生返款          主要是控制老带新只招生返款一次；大客户和渠道等其他多次返款的如果第一匹配政策是固定金额，后面就不再返款
			stuHql += " and  isChannelRebate= "+Constants.STUDENT_IS_CHANNEL_REBATE_FALSE;			
			
			// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
			// 如果起始状态ID>0,结束状态ID=0;则为无穷大
			// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
			// 如果都大于0,则取交集
			if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0)
			{
				stuHql += " and  status <"+student.getEndStatusId();
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0)
			{
				stuHql += " and  status >"+student.getStartStatusId();
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0)
			{
				stuHql += " and  status> "+student.getStartStatusId() + " and status<"+student.getEndStatusId();
			}
		}
		return stuHql;
	}
	
	
	/*
	 * 2012-06-11	用于显示已招生返款单查询
	 * 查询已招生返款的所有缴费单明细数量（查询招生返款缴费单_重构）（分页）-------暂时只对学费进行招生返款
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findfpdCountByPageForChannelRecruitRebateSearch(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.crm.Student)
	 */
	public int findfpdCountByPageForChannelRecruitRebateSearch(FeePaymentDetail feePaymentDetail,Student student) throws Exception
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feePaymentDetail != null && student!=null)
		{							
			// 缴费单招生返款状态
			if (feePaymentDetail.getRebateStatus() != 0) 
			{
				hqlparam += " and rebateStatus ="+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getRebateStatus());
			}
			else
			{
				hqlparam += " and rebateStatus >="+ Constants.PLACEHOLDER;
				list.add(Constants.PAYMENT_STATUS_YI_TIAN_ZHAO_SHENG_FAN_KUAN);
			}
			
			//总部确认时间
			if(feePaymentDetail.getCeduConfirmTime()!=null)
			{
				hqlparam += " and ( ceduConfirmTime <="+ Constants.PLACEHOLDER+" or ceduConfirmTime is null ) ";
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");				
				list.add(df.format(feePaymentDetail.getCeduConfirmTime()));
			}
			
			//招生返款期
			if(feePaymentDetail.getChannelRebateTimeId()>0)
			{
				hqlparam += " and channelRebateTimeId ="+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getChannelRebateTimeId());
			}
			
			//缴费单状态
			hqlparam += " and status >="+ Constants.PLACEHOLDER;
			list.add(Constants.PAYMENT_STATUS_ZONG_BU_QUE_REN);			
			
			//费用科目    暂时只对学费进行招生返款
			hqlparam += " and feeSubjectId ="+ Constants.PLACEHOLDER;
			list.add(FeeSubjectEnum.TuitionFee.value());
						
			//学生Ids__Hql
			String stuHql=this.findStudentIdsHqlForChannelRecruitRebateSearch(student);
			if (stuHql != null && !stuHql.equals("select id from Student where 1=1 ")) 
			{
				hqlparam += " and studentId in ("
							+ stuHql + ")";
			}
			else
			{
				return 0;
			}
			
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}

		return feePaymentDetailDao.getCounts(p);
		
	}
	
	/*
	 * 2012-06-11	用于显示已招生返款单查询
	 * 查询已招生返款的所有缴费单明细集合（查询招生返款缴费单_重构）（分页）-------暂时只对学费进行招生返款
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findfpdListByPageForChannelRecruitRebateSearch(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.crm.Student, net.cedu.model.page.PageResult)
	 */
	public List<FeePaymentDetail> findfpdListByPageForChannelRecruitRebateSearch(FeePaymentDetail feePaymentDetail,Student student,PageResult<FeePaymentDetail> pr) throws Exception
	{
		List<FeePaymentDetail> feepdlist=null;
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feePaymentDetail != null && student!=null)
		{				
			// 缴费单招生返款状态
			if (feePaymentDetail.getRebateStatus() != 0) 
			{
				hqlparam += " and rebateStatus ="+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getRebateStatus());
			}
			else
			{
				hqlparam += " and rebateStatus >="+ Constants.PLACEHOLDER;
				list.add(Constants.PAYMENT_STATUS_YI_TIAN_ZHAO_SHENG_FAN_KUAN);
			}
			
			//总部确认时间
			if(feePaymentDetail.getCeduConfirmTime()!=null)
			{
				hqlparam += " and ( ceduConfirmTime <="+ Constants.PLACEHOLDER+" or ceduConfirmTime is null ) ";
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");				
				list.add(df.format(feePaymentDetail.getCeduConfirmTime()));
			}
			
			//招生返款期
			if(feePaymentDetail.getChannelRebateTimeId()>0)
			{
				hqlparam += " and channelRebateTimeId ="+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getChannelRebateTimeId());
			}
			
			//缴费单状态
			hqlparam += " and status >="+ Constants.PLACEHOLDER;
			list.add(Constants.PAYMENT_STATUS_ZONG_BU_QUE_REN);			
			
			//费用科目    暂时只对学费进行招生返款
			hqlparam += " and feeSubjectId ="+ Constants.PLACEHOLDER;
			list.add(FeeSubjectEnum.TuitionFee.value());
						
			//学生Ids__Hql
			String stuHql=this.findStudentIdsHqlForChannelRecruitRebateSearch(student);
			if (stuHql != null && !stuHql.equals("select id from Student where 1=1 ")) 
			{
				hqlparam += " and studentId in ("
							+ stuHql + ")";
			}
			else
			{
				return null;
			}
			
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		
		Long[] feeIds = feePaymentDetailDao.getIDs(p);
		if (feeIds != null && feeIds.length != 0) 
		{
			feepdlist=new ArrayList<FeePaymentDetail>();
			for (int i = 0; i < feeIds.length; i++) 
			{
				// 内存获取
				FeePaymentDetail feeObj = feePaymentDetailDao.findById(Integer.valueOf(feeIds[i].toString()));
				if(feeObj!=null)
				{
					//缴费单的编号
					FeePayment fp=feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId());
					if(feeObj.getFeePaymentId()>0 && fp!=null)
					{
						feeObj.setPaymentCode(fp.getCode());
						feeObj.setFeeWayId(fp.getFeeWayId());//缴费方式
					}
					else
					{
						feeObj.setPaymentCode("");
					}
					//学生相关
					Student stu=studentBiz.findStudentById(feeObj.getStudentId());
					if(feeObj.getStudentId()>0 && stu!=null)
					{
						//学生姓名
						feeObj.setStudentName(stu.getName());
						//院校
						Academy academy=academyBiz.findAcademyById(stu.getAcademyId());
						if(stu.getAcademyId()>0 && academy!=null)
						{
							feeObj.setSchoolName(academy.getName());
						}
						//学习中心
						Branch branch=branchBiz.findBranchById(stu.getBranchId());
						if(stu.getBranchId()>0 && branch!=null)
						{
							feeObj.setBranchName(branch.getName());
						}
						//批次
						AcademyEnrollBatch aeb=academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId());
						if(stu.getEnrollmentBatchId()>0 && aeb!=null)
						{
							feeObj.setAcademyenrollbatchName(aeb.getEnrollmentName());
						}
						//层次
						Level level=levelbiz.findLevelById(stu.getLevelId());
						if(stu.getLevelId()>0 && level!=null)
						{
							feeObj.setLevelName(level.getName());
						}
						//专业
						Major major=majorbiz.findMajorById(stu.getMajorId());
						if(stu.getMajorId()>0 && major!=null)
						{
							feeObj.setMajorName(major.getName());
						}
						// 合作方
						Channel channel=this.channelBiz.findChannel(stu.getChannelId());
						if(stu.getChannelId()>0 && channel!=null)
						{
							feeObj.setChannelName(channel.getName());
						}
					}
					//费用科目
					FeeSubject fs=feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId());
					if(feeObj.getFeeSubjectId()>0 && fs!=null)
					{
						feeObj.setPaymentSubjectName(fs.getName());
					}
					//缴费金额
					feeObj.setJiaofeiValue((new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())).subtract(new BigDecimal(feeObj.getRefundAmount()))).doubleValue());
//					//合作方返款标准
//					ChannelPolicyDetailStandard cpds=this.cpdsOverLoadBiz.findChannelPolicyDetailStandardById(feeObj.getPolicyStandardId());
//					if(cpds!=null)
//					{
//						//返款标准显示字符串ResourcesTool.getText("finance_payment","jinedanwei")
//						String cpdsstring =ResourcesTool.getText("finance_payment","rebatestandardshow")+cpds.getEnrollmentFloor()+"--"+cpds.getEnrollmentCeil()+ResourcesTool.getText("finance_payment","peopleunit")+cpds.getValue();
//						if(cpds.getRebatesId()==Constants.MONEY_FORM_RATIO)
//						{
//							cpdsstring+=ResourcesTool.getText("finance_payment","baifenhao");
//						}
//						else
//						{
//							cpdsstring+=ResourcesTool.getText("finance_payment","jinedanwei");
//						}
//						feeObj.getParamsString().put("channelpds", cpdsstring);
//					}
					//招生返款期
					ChannelRebateTimeLimit crtl=channelRebateTimeLimitBiz.findChannelRebateTimeLimitById(feeObj.getChannelRebateTimeId());
					if(crtl!=null)
					{
						feeObj.setChannelRebateTimeName(crtl.getRebateName());
					}
										
					feepdlist.add(feeObj);
				}
			}
		}
		return feepdlist;
		
	}
	
	/**
	 * 查询学生ID_Hql 集合(招生返款查询用)__重构
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	private String findStudentIdsHqlForChannelRecruitRebateSearch(Student student) throws Exception 
	{
		String stuHql= "select id from Student where 1=1 ";
		if (student != null)
		{
			//招生途径enrollmentSource
			if (student.getEnrollmentSource() != 0)
			{
				stuHql += " and enrollmentSource ="+student.getEnrollmentSource();						
			}
			//合作方channelIds
			if(student.getParamsString().get("channelIds")!=null)
			{
				stuHql += " and channelId in ( "+student.getParamsString().get("channelIds")+" ) ";
			}
			else if (student.getChannelId() != 0) 
			{
				stuHql += " and channelId ="+student.getChannelId();
			}
			// 姓名
			if (student.getName() != null && !student.getName().equals("")) 
			{
				stuHql += " and name like '%"+student.getName()+"%' ";
			}
			// 证件号
			if (student.getCertNo() != null && !"".equals(student.getCertNo()))
			{
				stuHql += " and certNo like '%"+student.getCertNo()+"%' ";
			}
			// 院校
			if (student.getAcademyId() != 0)
			{
				stuHql += " and academyId="+student.getAcademyId();					
			}
			// 全局批次
			if (student.getGlbtach() > 0) 
			{
				List<AcademyEnrollBatch> aeblst = academyenrollbatchBiz
							.findAcademyEnrollBatchByGId(student.getGlbtach());
				String gbatchIds = "";
				if (null != aeblst && aeblst.size() > 0)
				{
					for (int i = 0; i < aeblst.size(); i++)
					{
						gbatchIds += "," + aeblst.get(i).getId();
					}
					stuHql += " and enrollmentBatchId in ( "+ gbatchIds.substring(1, gbatchIds.length()) + " ) ";	
				} 
				else 
				{
					return null;
				}
			}
			// 学习中心
			if (student.getBranchId() != 0) 
			{
				stuHql += " and  branchId= "+student.getBranchId();
			}
			// 批次
			if (student.getEnrollmentBatchId() != 0)
			{
				stuHql += " and  enrollmentBatchId= "+student.getEnrollmentBatchId();
			}
			
			// 层次
			if (student.getLevelId() != 0) 
			{
				stuHql += " and  levelId= "+student.getLevelId();
			}
			// 专业
			if (student.getMajorId() != 0) 
			{
				stuHql += " and  majorId= "+student.getMajorId();
			}
			
			//已缴学费   且已开课、已招生途径复核的学生才能招生返款
			stuHql += " and tuitionStatus >= "+Constants.STU_TUITION_STATUS_SHOU_CI_JIAO_FEI;		
			
			// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
			// 如果起始状态ID>0,结束状态ID=0;则为无穷大
			// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
			// 如果都大于0,则取交集
			if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0)
			{
				stuHql += " and  status <"+student.getEndStatusId();
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0)
			{
				stuHql += " and  status >"+student.getStartStatusId();
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0)
			{
				stuHql += " and  status> "+student.getStartStatusId() + " and status<"+student.getEndStatusId();
			}
		}
		return stuHql;
	}
	
	/*
	 * 统计已招生返款的缴费单招生返款总金额
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#countFpdAllChannelRebateMoneyForChannelRebateSearch(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.crm.Student)
	 */
	public String countFpdAllChannelRebateMoneyForChannelRebateSearch(FeePaymentDetail feePaymentDetail, Student student) throws Exception
	{
		return this.feePaymentDetailDao.countFpdAllChannelRebateMoneyForChannelRebateSearch(feePaymentDetail,student);
	}
	
	/*
	 * 2012-07-06 重构
	 * 
	 * 根据需要添加院校返款单的缴费单追加其他已经院校返款的缴费单金额(包括已填打款单的缴费单明细)（根据缴费单明细ids字符串）
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#countFpdAddAcademyRebateMoneyByFpdIds(java.lang.String)
	 */
	public String countFpdAddAcademyRebateMoneyByFpdIds(String fpdIds) throws Exception
	{
		BigDecimal countall=new BigDecimal(0);//金额
		if(fpdIds!=null && fpdIds.length()>0)
		{
			String[] fIds=fpdIds.split(",");
			String abamString="";//院校批次人数已经追加过的
			for(int i=0;i<fIds.length;i++)
			{
				FeePaymentDetail fpd=feePaymentDetailDao.findById(Integer.valueOf(fIds[i]));
				if(fpd!=null)
				{
					//学生相关
					Student stu=studentBiz.findStudentById(fpd.getStudentId());
					if(stu!=null)
					{
						//过滤掉重复添加的问题    按院校批次 费用科目过滤（主要是按记录的人数去过滤掉重复的计算）
						if(abamString!=null && !abamString.equals(""))
						{
							String zuhecbrs=","+abamString+",";
							if(zuhecbrs.indexOf(","+stu.getAcademyId()+"#"+stu.getEnrollmentBatchId()+"#"+fpd.getFeeSubjectId()+",")==-1)
							{
								countall=countall.add(findAddMoneyByFpdStu(fpd,stu));
								abamString+=","+stu.getAcademyId()+"#"+stu.getEnrollmentBatchId()+"#"+fpd.getFeeSubjectId();
							}
						}
						else
						{	
							countall=countall.add(findAddMoneyByFpdStu(fpd,stu));
							abamString+=stu.getAcademyId()+"#"+stu.getEnrollmentBatchId()+"#"+fpd.getFeeSubjectId();
						}
					}
				}
			}
		}
		return countall.toString();
	}
	
	/**
	 * 根据缴费单明细和学生实体查询该学生所在院校和批次已经添加返款单的缴费单追加金额
	 * @param fpd
	 * @param stu
	 * @return
	 * @throws Exception
	 */
	private BigDecimal findAddMoneyByFpdStu(FeePaymentDetail fpd ,Student stu) throws Exception
	{
		BigDecimal countall=new BigDecimal(0);
		if(fpd!=null && stu!=null)
		{
			AcademyRebatePolicyDetailStandard arpdone=null;
			AcademyRebatePolicyDetailStandard arpdtwo=null;
			Student stuone=null;
			BigDecimal countone=new BigDecimal(0);
			BigDecimal counttwo=new BigDecimal(0);
			BigDecimal money=new BigDecimal(0);//金额
			//查询该学生所在院校批次的最新返款人数记录
			AcademyBatchRebateCount abrcount=new AcademyBatchRebateCount();
			abrcount.setAcademyId(stu.getAcademyId());
			abrcount.setBatchId(stu.getEnrollmentBatchId());
			abrcount.setFeeSubjectId(fpd.getFeeSubjectId());
			AcademyBatchRebateCount abrct=this.academyBatchRebateCountBiz.findAcademyBatchRebateCountListBy(abrcount);
			if(abrct!=null && abrct.getRebateCount()<fpd.getAcademyRebateCount())
			{
				//计算追加金额     按批次和院校、费用科目计算
				List<FeePaymentDetail> fpdlist=findfpdListByAcademyIdBatchIdFeeSubjectIdForAcademyRebateAddMoney(stu,fpd.getFeeSubjectId());
				if(fpdlist!=null && fpdlist.size()>0)
				{
					for(FeePaymentDetail feep:fpdlist)
					{
						stuone=studentBiz.findStudentById(feep.getStudentId());
						arpdone=academyRebatePolicyDetailStandardBiz.findAcademyRebateReviewStandardByStudentIdFeeSubjectIdCount(stuone,feep.getFeeSubjectId(),abrct.getRebateCount());
						arpdtwo=academyRebatePolicyDetailStandardBiz.findAcademyRebateReviewStandardByStudentIdFeeSubjectIdCount(stuone,feep.getFeeSubjectId(),fpd.getAcademyRebateCount());
						if(arpdone.getId()!=arpdtwo.getId())
						{
							//基数  由于退费，所以要乘以一个基数
							BigDecimal jishu=new BigDecimal(1);
							if((new BigDecimal(feep.getAmountPaied()).add(new BigDecimal(feep.getRechargeAmount()))).compareTo(new BigDecimal(0))!=0)
							{
								jishu=(new BigDecimal(feep.getAmountPaied()).add(new BigDecimal(feep.getRechargeAmount())).subtract(new BigDecimal(feep.getRefundAmount()))).divide(new BigDecimal(feep.getAmountPaied()).add(new BigDecimal(feep.getRechargeAmount())),12,BigDecimal.ROUND_HALF_UP);
							}						
							money=(new BigDecimal(feep.getMoneyToPay()).subtract(new BigDecimal(feep.getAcademyCeduDiscount()))).multiply(jishu);				
							//第一个标准计算金额
							if(arpdone.getValueForm()==Constants.MONEY_FORM_RATIO)
							{
								countone=money.multiply(arpdone.getValue()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
							}
							else if(arpdone.getValueForm()==Constants.MONEY_FORM_AMOUNT)
							{
								countone=arpdone.getValue();
							}
							//第二个标准计算金额
							if(arpdtwo.getValueForm()==Constants.MONEY_FORM_RATIO)
							{
								counttwo=money.multiply(arpdtwo.getValue()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
							}
							else if(arpdtwo.getValueForm()==Constants.MONEY_FORM_AMOUNT)
							{
								counttwo=arpdtwo.getValue();
							}
							//比较计算的金额是否大于0
							if(((counttwo.subtract(countone)).compareTo(new BigDecimal(0)))>0)//和0比较大于返回1小于返回-1等于返回0
							{
								countall=countall.add(counttwo.subtract(countone));
							}
						}
					}
				}
			}
		}
		return countall;
	}
	
	/*
	 * 2012-07-06	用于查询已添加院校返款单之后的缴费单明细
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findfpdListByAcademyIdBatchIdFeeSubjectIdForAcademyRebateAddMoney(net.cedu.entity.crm.Student, int)
	 */
	public List<FeePaymentDetail> findfpdListByAcademyIdBatchIdFeeSubjectIdForAcademyRebateAddMoney(Student student,int feeSubjectId) throws Exception
	{
		List<FeePaymentDetail> feepdlist=null;
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (student!=null && feeSubjectId!=0)
		{				
			//缴费单状态
			hqlparam += " and status >="+ Constants.PLACEHOLDER;
			list.add(Constants.PAYMENT_STATUS_YI_TIAN_FAN_KUAN_DAN);			
			
			//费用科目   
			if(feeSubjectId>0)
			{
				hqlparam += " and feeSubjectId ="+ Constants.PLACEHOLDER;
				list.add(feeSubjectId);
			}
						
			//学生Ids__Hql
			String stuHql="select id from Student where 1=1 ";
			if (student.getAcademyId()>0) 
			{
				stuHql += " and academyId="+ Constants.PLACEHOLDER;
				list.add(student.getAcademyId());					
			}
			if (student.getEnrollmentBatchId()>0) 
			{
				stuHql += " and enrollmentBatchId="+ Constants.PLACEHOLDER;
				list.add(student.getEnrollmentBatchId());					
			}
			if(!stuHql.equals("select id from Student where 1=1 "))
			{
				hqlparam += " and studentId in ("+ stuHql + ")";
			}
			
		}
		else
		{
			return null;
		}
		
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		
		Long[] feeIds = feePaymentDetailDao.getIDs(p);
		if (feeIds != null && feeIds.length != 0) 
		{
			feepdlist=new ArrayList<FeePaymentDetail>();
			for (int i = 0; i < feeIds.length; i++) 
			{
				// 内存获取
				FeePaymentDetail feeObj = feePaymentDetailDao.findById(Integer.valueOf(feeIds[i].toString()));
				if(feeObj!=null)
				{
					feepdlist.add(feeObj);
				}
			}
		}
		return feepdlist;		
	}
	
	/*
	 * 2012-07-10	
	 * 根据缴费单明细Ids查询学习中心列表
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findBranchListByFpdIds(java.lang.String)
	 */
	public List<Branch> findBranchListByFpdIds(String fpdIds) throws Exception
	{
		List<Branch> branchList=null;
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (fpdIds != null && fpdIds.length()>0)
		{					
			hqlparam += " and id in (select branchId from Student where id in (select studentId from FeePaymentDetail where id in ("+Constants.PLACEHOLDER+")))";
			list.add(fpdIds);
		}
		else
		{
			return null;
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		
		Long[] branchIds = branchDao.getIDs(p);
		if (branchIds != null && branchIds.length != 0) 
		{
			branchList=new ArrayList<Branch>();
			for (int i = 0; i < branchIds.length; i++) 
			{
				// 内存获取
				Branch branch = branchDao.findById(Integer.valueOf(branchIds[i].toString()));
				if(branch!=null)
				{
					branchList.add(branch);
				}
			}
		}
		return branchList;		
	}
	
	/*
	 * 2012-07-22
	 * 查询符合年度院校返款的所有缴费单明细数量（添加院校年度返款单）（分页）
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findFpdCountByPageForAcademyYearRebate(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.crm.Student, java.util.List)
	 */
	public int findFpdCountByPageForAcademyYearRebate(FeePaymentDetail feePaymentDetail,Student student,List<AcademyEnrollBatch> aeblist) throws Exception
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feePaymentDetail != null)
		{						
			// 缴费单状态
			if (feePaymentDetail.getStatus() != 0) {
				hqlparam += " and status >="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getStatus());
			}
			//招生批次集合
			if (aeblist!=null && aeblist.size()>0) 
			{
				hqlparam += "and ( ";
				int sqlcount=0;//控制sql 语句
				for(AcademyEnrollBatch aeb:aeblist)
				{
					if(aeb!=null)
					{
						// 查询学生集合ID
						String stuHql= "select id from Student where 1=1 ";
						String fsHql="";
						//查询该院校、该批次下的所有要返款的费用科目
						List<AcademyBatchFeeSubject> abfslist=academyBatchFeeSubjectBiz.findByAcademyAndBatch(student.getAcademyId(), aeb.getId());
						String feesubIds=",";
						if (student != null && abfslist!=null && abfslist.size()>0)
						{							
							// 院校
							if (student.getAcademyId() != 0) 
							{
								stuHql += " and academyId="+ Constants.PLACEHOLDER;
								list.add(student.getAcademyId());										
							}
							// 批次
							if (aeb.getId() != 0) 
							{
								stuHql += " and  enrollmentBatchId= "+Constants.PLACEHOLDER;
								list.add(aeb.getId());		
							}
							// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
							// 如果起始状态ID>0,结束状态ID=0;则为无穷大
							// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
							// 如果都大于0,则取交集
							if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0) 
							{
								stuHql += " and  status <"+Constants.PLACEHOLDER;
								list.add(student.getEndStatusId());	
							}
							if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0)
							{
								stuHql += " and  status >"+Constants.PLACEHOLDER;
								list.add(student.getStartStatusId());			
							}
							if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0) 
							{
								stuHql += " and  status> "
										+ Constants.PLACEHOLDER + " and status<"
										+ Constants.PLACEHOLDER;
								list.add(student.getStartStatusId());	
								list.add(student.getEndStatusId());	
							}
							
							//招生返款费用科目
							for(AcademyBatchFeeSubject abfs:abfslist)
							{
								if (feesubIds.startsWith(","))
								{
									feesubIds = abfs.getFeeSubjectId() + "";
								} 
								else 
								{
									feesubIds += "," + abfs.getFeeSubjectId();
								}
							}
							if(feesubIds!=null && !feesubIds.startsWith(","))
							{
								fsHql = " and feeSubjectId in ("
									+ Constants.PLACEHOLDER + ")";
								list.add("$" + feesubIds);
							}
						}
						if(stuHql!=null && !stuHql.equals("select id from Student where 1=1 ") && !fsHql.equals(""))
						{
							if(sqlcount>0)
							{
								hqlparam += " or ( studentId in ( "+stuHql+" ) "+fsHql+" ) ";
							}
							else
							{
								hqlparam += " ( studentId in ( "+stuHql+" ) "+fsHql+" ) ";
							}
							sqlcount++;
						}
					}
				}
				hqlparam += " ) ";
			}
			else
			{
				return 0;
			}
			
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}

		return feePaymentDetailDao.getCounts(p);
		
	}
	
	/*
	 * 2012-07-22
	 * 查询符合年度院校返款的所有缴费单明细数量（添加院校年度返款单）（分页）
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findFpdCountByPageForAcademyYearRebateLoad(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.crm.Student, java.util.List)
	 */
	public int findFpdCountByPageForAcademyYearRebateLoad(FeePaymentDetail feePaymentDetail,Student student,List<AcademyEnrollBatch> aeblist) throws Exception
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feePaymentDetail != null)
		{						
			// 缴费单状态
			if (feePaymentDetail.getStatus() != 0) 
			{
				hqlparam += " and status >="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getStatus());
			}
			//费用科目
			if (feePaymentDetail.getFeeSubjectId() != 0)
			{
				hqlparam += " and feeSubjectId ="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getFeeSubjectId());
			}
			
			//未年度结算返款
			hqlparam += " and isAcademyRebateYearCount ="+ Constants.PLACEHOLDER;
			list.add(Constants.ACADEMY_REBATE_IS_YEAR_COUNT_FALSE);
			
			//招生批次集合
			if (aeblist!=null && aeblist.size()>0 && student!=null) 
			{
				String stuHql= "select id from Student where 1=1 ";							
				// 院校
				if (student.getAcademyId() != 0) 
				{
					stuHql += " and academyId="+ Constants.PLACEHOLDER;
					list.add(student.getAcademyId());										
				}
				//招生批次
				String aebIds="";
				for(AcademyEnrollBatch aebatch:aeblist)
				{
					if(aebatch!=null)
					{
						if(!aebIds.equals(""))
						{
							aebIds+=","+aebatch.getId();
						}
						else
						{
							aebIds+=aebatch.getId();
						}
					}
				}
				if (!aebIds.equals("")) 
				{
					stuHql += " and  enrollmentBatchId in ( "+Constants.PLACEHOLDER+" ) ";
					list.add("$"+aebIds);		
				}
				// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
				// 如果起始状态ID>0,结束状态ID=0;则为无穷大
				// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
				// 如果都大于0,则取交集
				if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0) 
				{
					stuHql += " and  status <"+Constants.PLACEHOLDER;
					list.add(student.getEndStatusId());	
				}
				if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0)
				{
					stuHql += " and  status >"+Constants.PLACEHOLDER;
					list.add(student.getStartStatusId());			
				}
				if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0) 
				{
					stuHql += " and  status> "
							+ Constants.PLACEHOLDER + " and status<"
							+ Constants.PLACEHOLDER;
					list.add(student.getStartStatusId());	
					list.add(student.getEndStatusId());	
				}
				if(!stuHql.equals("select id from Student where 1=1 "))
				{
					hqlparam += " and studentId in ( "+stuHql+" ) ";
				}
			}
			else
			{
				return 0;
			}
			
		}
		else
		{
			return 0;
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}

		return feePaymentDetailDao.getCounts(p);
		
	}
	
	/*
	 * 2012-07-22
	 * 查询符合年度院校返款的所有缴费单明细集合（添加院校年度返款单）（分页）
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findFpdlListByPageForAcademyYearRebate(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.crm.Student, java.util.List, net.cedu.model.page.PageResult)
	 */
	public List<FeePaymentDetail> findFpdlListByPageForAcademyYearRebate(FeePaymentDetail feePaymentDetail,Student student,List<AcademyEnrollBatch> aeblist,PageResult<FeePaymentDetail> pr) throws Exception
	{
		List<FeePaymentDetail> feepdlist=null;
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feePaymentDetail != null)
		{						
			// 缴费单状态
			if (feePaymentDetail.getStatus() != 0) 
			{
				hqlparam += " and status >="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getStatus());
			}
			
			//费用科目
			if (feePaymentDetail.getFeeSubjectId() != 0)
			{
				hqlparam += " and feeSubjectId ="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getFeeSubjectId());
			}
			
			//未年度结算返款
			hqlparam += " and isAcademyRebateYearCount ="+ Constants.PLACEHOLDER;
			list.add(Constants.ACADEMY_REBATE_IS_YEAR_COUNT_FALSE);
			
			//招生批次集合
			if (aeblist!=null && aeblist.size()>0 && student!=null) 
			{
				String stuHql= "select id from Student where 1=1 ";							
				// 院校
				if (student.getAcademyId() != 0) 
				{
					stuHql += " and academyId="+ Constants.PLACEHOLDER;
					list.add(student.getAcademyId());										
				}
				//招生批次
				String aebIds="";
				for(AcademyEnrollBatch aebatch:aeblist)
				{
					if(aebatch!=null)
					{
						if(!aebIds.equals(""))
						{
							aebIds+=","+aebatch.getId();
						}
						else
						{
							aebIds+=aebatch.getId();
						}
					}
				}
				if (!aebIds.equals("")) 
				{
					stuHql += " and  enrollmentBatchId in ( "+Constants.PLACEHOLDER+" ) ";
					list.add("$"+aebIds);		
				}
				// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
				// 如果起始状态ID>0,结束状态ID=0;则为无穷大
				// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
				// 如果都大于0,则取交集
				if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0) 
				{
					stuHql += " and  status <"+Constants.PLACEHOLDER;
					list.add(student.getEndStatusId());	
				}
				if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0)
				{
					stuHql += " and  status >"+Constants.PLACEHOLDER;
					list.add(student.getStartStatusId());			
				}
				if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0) 
				{
					stuHql += " and  status> "
							+ Constants.PLACEHOLDER + " and status<"
							+ Constants.PLACEHOLDER;
					list.add(student.getStartStatusId());	
					list.add(student.getEndStatusId());	
				}
				if(!stuHql.equals("select id from Student where 1=1 "))
				{
					hqlparam += " and studentId in ( "+stuHql+" ) ";
				}
			}
			else
			{
				return null;
			}			
		}
		else
		{
			return null;
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		Long[] feeIds = feePaymentDetailDao.getIDs(p);
		if (feeIds != null && feeIds.length != 0) 
		{
			feepdlist=new ArrayList<FeePaymentDetail>();
			for (int i = 0; i < feeIds.length; i++) 
			{
				// 内存获取
				FeePaymentDetail feeObj = feePaymentDetailDao.findById(Integer.valueOf(feeIds[i].toString()));
				if(feeObj!=null)
				{
					//缴费单的编号和缴费方式
					FeePayment fp=feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId());
					if(feeObj.getFeePaymentId()>0 && fp!=null)
					{
						feeObj.setPaymentCode(fp.getCode());
					}
					else
					{
						feeObj.setPaymentCode("");
					}
					//学生相关
					Student stu=studentBiz.findStudentById(feeObj.getStudentId());
					if(feeObj.getStudentId()>0 && stu!=null)
					{
						//学生姓名
						feeObj.setStudentName(stu.getName());
						//院校
						Academy academy=academyBiz.findAcademyById(stu.getAcademyId());
						if(stu.getAcademyId()>0 && academy!=null)
						{
							feeObj.setSchoolName(academy.getName());
						}
						//学习中心
						Branch branch=branchBiz.findBranchById(stu.getBranchId());
						if(stu.getBranchId()>0 && branch!=null)
						{
							feeObj.setBranchName(branch.getName());
						}
						//批次
						AcademyEnrollBatch aeb=academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId());
						if(stu.getEnrollmentBatchId()>0 && aeb!=null)
						{
							feeObj.setAcademyenrollbatchName(aeb.getEnrollmentName());
						}
						//层次
						Level level=levelbiz.findLevelById(stu.getLevelId());
						if(stu.getLevelId()>0 && level!=null)
						{
							feeObj.setLevelName(level.getName());
						}
						//专业
						Major major= majorbiz.findMajorById(stu.getMajorId());
						if(stu.getMajorId()>0 && major!=null)
						{
							feeObj.setMajorName(major.getName());
						}
						//院校已返款金额
						feeObj.setMoneyToCedu(feeObj.getAcademyRebateAddMoney().add(new BigDecimal(feeObj.getPayAcademyCedu())));
						//年度追加金额
						//feeObj.setYearAddMoneyShow(findAddMoneyByFpdStuForAcademyYearRebate(feeObj,stu,aeblist));
					}
					//费用科目
					FeeSubject fs=feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId());
					if(feeObj.getFeeSubjectId()>0 && fs!=null)
					{
						feeObj.setPaymentSubjectName(fs.getName());
					}
					//缴费金额
					feeObj.setJiaofeiValue((new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())).subtract(new BigDecimal(feeObj.getRefundAmount()))).doubleValue());
					
					feepdlist.add(feeObj);
				}
			}
		}
		return feepdlist;
		
	}
	
	/**
	 * 年度返款
	 * 根据缴费单明细和学生实体查询该学生所在院校和批次已经添加返款单的缴费单追加金额
	 * @param fpd 缴费单明细
	 * @param stu 学生实体
	 * @param aeblist 招生批次列表
	 * @return
	 * @throws Exception
	 */
	private BigDecimal findAddMoneyByFpdStuForAcademyYearRebate(FeePaymentDetail fpd ,Student stu,List<AcademyEnrollBatch> aeblist) throws Exception
	{
		BigDecimal countall=new BigDecimal(0);
		if(fpd!=null && stu!=null)
		{
			AcademyRebatePolicyDetailStandard arpdone=null;
			AcademyRebatePolicyDetailStandard arpdtwo=null;
			BigDecimal countone=new BigDecimal(0);
			BigDecimal counttwo=new BigDecimal(0);
			BigDecimal money=new BigDecimal(0);//金额
			//查询该学生所在院校批次的最新返款人数记录
			AcademyBatchRebateCount abrcount=new AcademyBatchRebateCount();
			abrcount.setAcademyId(stu.getAcademyId());
			abrcount.setBatchId(stu.getEnrollmentBatchId());
			abrcount.setFeeSubjectId(fpd.getFeeSubjectId());
			AcademyBatchRebateCount abrct=this.academyBatchRebateCountBiz.findAcademyBatchRebateCountListBy(abrcount);
			//全局院校费用科目返款人数
			int countPe=findJiaoFeiCountByFpdStudentAebForAcademyYearRebate(fpd,stu,aeblist);
			if(abrct!=null && abrct.getRebateCount()<countPe)
			{
				arpdone=academyRebatePolicyDetailStandardBiz.findAcademyRebateReviewStandardByStudentIdFeeSubjectIdCount(stu,fpd.getFeeSubjectId(),abrct.getRebateCount());
				arpdtwo=academyRebatePolicyDetailStandardBiz.findAcademyRebateReviewStandardByStudentIdFeeSubjectIdCount(stu,fpd.getFeeSubjectId(),countPe);
				if(arpdone.getId()!=arpdtwo.getId())
				{
					//基数  由于退费，所以要乘以一个基数
					BigDecimal jishu=new BigDecimal(1);
					if((new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount()))).compareTo(new BigDecimal(0))!=0)
					{
						jishu=(new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount())).subtract(new BigDecimal(fpd.getRefundAmount()))).divide(new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount())),12,BigDecimal.ROUND_HALF_UP);
					}						
					money=(new BigDecimal(fpd.getMoneyToPay()).subtract(new BigDecimal(fpd.getAcademyCeduDiscount()))).multiply(jishu);				
					//第一个标准计算金额
					if(arpdone.getValueForm()==Constants.MONEY_FORM_RATIO)
					{
						countone=money.multiply(arpdone.getValue()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
					}
					else if(arpdone.getValueForm()==Constants.MONEY_FORM_AMOUNT)
					{
						countone=arpdone.getValue();
					}
					//第二个标准计算金额
					if(arpdtwo.getValueForm()==Constants.MONEY_FORM_RATIO)
					{
						counttwo=money.multiply(arpdtwo.getValue()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
					}
					else if(arpdtwo.getValueForm()==Constants.MONEY_FORM_AMOUNT)
					{
						counttwo=arpdtwo.getValue();
					}
					//比较计算的金额是否大于0
					if(((counttwo.subtract(countone)).compareTo(new BigDecimal(0)))>0)//和0比较大于返回1小于返回-1等于返回0
					{
						countall=counttwo.subtract(countone);
					}
				}
			}
		}
		return countall;
	}
	
	/*
	 * 2012-07-22
	 * 
	 * 查询符合年度院校返款的缴费单明细数量（每种费用科目都不一样）（计算院校年度返款单）（分页）
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findFeePaymentDetailCountByPageForAcademyRebate(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.crm.Student, net.cedu.model.page.PageResult)
	 */
	public int findJiaoFeiCountByFpdStudentAebForAcademyYearRebate(FeePaymentDetail feePaymentDetail,Student student,List<AcademyEnrollBatch> aeblist) throws Exception
	{
		return this.feePaymentDetailDao.findJiaoFeiCountByFpdStudentAebForAcademyYearRebate(feePaymentDetail, student, aeblist);
	}
	
	/*
	 * 2012-07-22	
	 * 查询学习中心列表（年度返款用）
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findBranchListByFpdStuAebList(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.crm.Student, java.util.List)
	 */
	public List<Branch> findBranchListByFpdStuAebList(FeePaymentDetail feePaymentDetail ,Student student,List<AcademyEnrollBatch> aeblist) throws Exception
	{
		List<Branch> branchList=null;
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feePaymentDetail != null && student!=null && aeblist!=null && aeblist.size()>0)
		{					
			hqlparam += " and id in (select branchId from Student where id in (select studentId from FeePaymentDetail where 1=1 ";
			
			// 缴费单状态
			if (feePaymentDetail.getStatus() != 0)
			{
				hqlparam += " and status >="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getStatus());
			}
			//费用科目
			if (feePaymentDetail.getFeeSubjectId() != 0)
			{
				hqlparam += " and feeSubjectId ="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getFeeSubjectId());
			}
			//招生批次集合
			if (aeblist!=null && aeblist.size()>0 && student!=null) 
			{
				String stuHql= "select id from Student where 1=1 ";							
				// 院校
				if (student.getAcademyId() != 0) 
				{
					stuHql += " and academyId="+ Constants.PLACEHOLDER;
					list.add(student.getAcademyId());										
				}
				//招生批次
				String aebIds="";
				for(AcademyEnrollBatch aebatch:aeblist)
				{
					if(aebatch!=null)
					{
						if(!aebIds.equals(""))
						{
							aebIds+=","+aebatch.getId();
						}
						else
						{
							aebIds+=aebatch.getId();
						}
					}
				}
				if (!aebIds.equals("")) 
				{
					stuHql += " and  enrollmentBatchId in ( "+Constants.PLACEHOLDER+" ) ";
					list.add("$"+aebIds);		
				}
				// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
				// 如果起始状态ID>0,结束状态ID=0;则为无穷大
				// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
				// 如果都大于0,则取交集
				if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0) 
				{
					stuHql += " and  status <"+Constants.PLACEHOLDER;
					list.add(student.getEndStatusId());	
				}
				if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0)
				{
					stuHql += " and  status >"+Constants.PLACEHOLDER;
					list.add(student.getStartStatusId());			
				}
				if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0) 
				{
					stuHql += " and  status> "
							+ Constants.PLACEHOLDER + " and status<"
							+ Constants.PLACEHOLDER;
					list.add(student.getStartStatusId());	
					list.add(student.getEndStatusId());	
				}
				if(!stuHql.equals("select id from Student where 1=1 "))
				{
					hqlparam += " and studentId in ( "+stuHql+" ) ";
				}
			}
			else
			{
				return null;
			}
			hqlparam +=	" )) ";
		}
		else
		{
			return null;
		}	
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		
		Long[] branchIds = branchDao.getIDs(p);
		if (branchIds != null && branchIds.length != 0) 
		{
			branchList=new ArrayList<Branch>();
			for (int i = 0; i < branchIds.length; i++) 
			{
				// 内存获取
				Branch branch = branchDao.findById(Integer.valueOf(branchIds[i].toString()));
				if(branch!=null)
				{
					branchList.add(branch);
				}
			}
		}
		return branchList;		
	}
	
	/*
	 * 2012-07-23
	 * 统计符合年度院校返款的所有缴费单明细集合的追加金额总和（添加院校年度返款单）
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#updateFpdForAcademyYearRebateCountAllAddMoney(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.crm.Student, java.util.List)
	 */
	public BigDecimal updateFpdForAcademyYearRebateCountAllAddMoney(FeePaymentDetail feePaymentDetail,Student student,List<AcademyEnrollBatch> aeblist) throws Exception
	{
		BigDecimal countall=new BigDecimal(0);
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feePaymentDetail != null)
		{						
			// 缴费单状态
			if (feePaymentDetail.getStatus() != 0) 
			{
				hqlparam += " and status >="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getStatus());
			}
			//费用科目
			if (feePaymentDetail.getFeeSubjectId() != 0)
			{
				hqlparam += " and feeSubjectId ="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getFeeSubjectId());
			}
			//未年度结算返款
			hqlparam += " and isAcademyRebateYearCount ="+ Constants.PLACEHOLDER;
			list.add(Constants.ACADEMY_REBATE_IS_YEAR_COUNT_FALSE);
			
			//招生批次集合
			if (aeblist!=null && aeblist.size()>0 && student!=null) 
			{
				String stuHql= "select id from Student where 1=1 ";							
				// 院校
				if (student.getAcademyId() != 0) 
				{
					stuHql += " and academyId="+ Constants.PLACEHOLDER;
					list.add(student.getAcademyId());										
				}
				//招生批次
				String aebIds="";
				for(AcademyEnrollBatch aebatch:aeblist)
				{
					if(aebatch!=null)
					{
						if(!aebIds.equals(""))
						{
							aebIds+=","+aebatch.getId();
						}
						else
						{
							aebIds+=aebatch.getId();
						}
					}
				}
				if (!aebIds.equals("")) 
				{
					stuHql += " and  enrollmentBatchId in ( "+Constants.PLACEHOLDER+" ) ";
					list.add("$"+aebIds);		
				}
				// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
				// 如果起始状态ID>0,结束状态ID=0;则为无穷大
				// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
				// 如果都大于0,则取交集
				if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0) 
				{
					stuHql += " and  status <"+Constants.PLACEHOLDER;
					list.add(student.getEndStatusId());	
				}
				if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0)
				{
					stuHql += " and  status >"+Constants.PLACEHOLDER;
					list.add(student.getStartStatusId());			
				}
				if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0) 
				{
					stuHql += " and  status> "
							+ Constants.PLACEHOLDER + " and status<"
							+ Constants.PLACEHOLDER;
					list.add(student.getStartStatusId());	
					list.add(student.getEndStatusId());	
				}
				if(!stuHql.equals("select id from Student where 1=1 "))
				{
					hqlparam += " and studentId in ( "+stuHql+" ) ";
				}
			}
			else
			{
				return null;
			}			
		}
		else
		{
			return null;
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		Long[] feeIds = feePaymentDetailDao.getIDs(p);
		if (feeIds != null && feeIds.length != 0) 
		{
			boolean isback=false;
			for (int i = 0; i < feeIds.length; i++) 
			{
				// 内存获取
				FeePaymentDetail feeObj = feePaymentDetailDao.findById(Integer.valueOf(feeIds[i].toString()));
				if(feeObj!=null)
				{
					//学生相关
					Student stu=studentBiz.findStudentById(feeObj.getStudentId());
					if(feeObj.getStudentId()>0 && stu!=null)
					{
						//年度追加金额
						feeObj.setAcademyYearRebateAddMoney(findAddMoneyByFpdStuForAcademyYearRebate(feeObj,stu,aeblist));
						isback=this.updateFeePaymentDetail(feeObj);
						if(isback==true)
						{
							countall=countall.add(feeObj.getAcademyYearRebateAddMoney());
						}
					}
				}
			}
		}
		return countall;
		
	}
	
	/*
	 * 2012-07-23
	 * 查询符合年度院校返款的所有缴费单明细集合的追加金额总和（添加院校年度返款单）
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#updateFpdForAcademyYearRebateCountAllAddMoney(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.crm.Student, java.util.List)
	 */
	public List<FeePaymentDetail> findFpdListForAddAcademyYearRebateCountAllAddMoney(FeePaymentDetail feePaymentDetail,Student student,List<AcademyEnrollBatch> aeblist) throws Exception
	{
		List<FeePaymentDetail> feepdlist=null;
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (feePaymentDetail != null)
		{						
			// 缴费单状态
			if (feePaymentDetail.getStatus() != 0)
			{
				hqlparam += " and status >="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getStatus());
			}
			//费用科目
			if (feePaymentDetail.getFeeSubjectId() != 0)
			{
				hqlparam += " and feeSubjectId ="
						+ Constants.PLACEHOLDER;
				list.add(feePaymentDetail.getFeeSubjectId());
			}
			//未年度结算返款
			hqlparam += " and isAcademyRebateYearCount ="+ Constants.PLACEHOLDER;
			list.add(Constants.ACADEMY_REBATE_IS_YEAR_COUNT_FALSE);
			
			//招生批次集合
			if (aeblist!=null && aeblist.size()>0 && student!=null) 
			{
				String stuHql= "select id from Student where 1=1 ";							
				// 院校
				if (student.getAcademyId() != 0) 
				{
					stuHql += " and academyId="+ Constants.PLACEHOLDER;
					list.add(student.getAcademyId());										
				}
				//招生批次
				String aebIds="";
				for(AcademyEnrollBatch aebatch:aeblist)
				{
					if(aebatch!=null)
					{
						if(!aebIds.equals(""))
						{
							aebIds+=","+aebatch.getId();
						}
						else
						{
							aebIds+=aebatch.getId();
						}
					}
				}
				if (!aebIds.equals("")) 
				{
					stuHql += " and  enrollmentBatchId in ( "+Constants.PLACEHOLDER+" ) ";
					list.add("$"+aebIds);		
				}
				// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
				// 如果起始状态ID>0,结束状态ID=0;则为无穷大
				// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
				// 如果都大于0,则取交集
				if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0) 
				{
					stuHql += " and  status <"+Constants.PLACEHOLDER;
					list.add(student.getEndStatusId());	
				}
				if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0)
				{
					stuHql += " and  status >"+Constants.PLACEHOLDER;
					list.add(student.getStartStatusId());			
				}
				if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0) 
				{
					stuHql += " and  status> "
							+ Constants.PLACEHOLDER + " and status<"
							+ Constants.PLACEHOLDER;
					list.add(student.getStartStatusId());	
					list.add(student.getEndStatusId());	
				}
				if(!stuHql.equals("select id from Student where 1=1 "))
				{
					hqlparam += " and studentId in ( "+stuHql+" ) ";
				}
			}
			else
			{
				return null;
			}			
		}
		else
		{
			return null;
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		Long[] feeIds = feePaymentDetailDao.getIDs(p);
		if (feeIds != null && feeIds.length != 0) 
		{
			feepdlist=new ArrayList<FeePaymentDetail>() ;
			for (int i = 0; i < feeIds.length; i++) 
			{
				// 内存获取
				FeePaymentDetail feeObj = feePaymentDetailDao.findById(Integer.valueOf(feeIds[i].toString()));
				if(feeObj!=null)
				{
					feepdlist.add(feeObj);
				}
			}
		}
		return feepdlist;	
	}
	
	/*
	 * 根据学生Id缴费单为作废的数量
	 * 
	 * @see net.cedu.biz.finance.FeePaymentDetailBiz#findFpdCountByStudentIdStatus(int)
	 */
	public int findFpdCountByStudentIdStatus(int studentId)throws Exception
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if(studentId>0)
		{
			//学生Id
			hqlparam += " and studentId= " + Constants.PLACEHOLDER;
			list.add(studentId);
			//状态
			hqlparam += " and status <> " + Constants.PLACEHOLDER;
			list.add(Constants.PAYMENT_STATUS_ZUO_FEI);
			
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		else
		{
			return 0;
		}
		return feePaymentDao.getCounts(p);
	}
	
}
