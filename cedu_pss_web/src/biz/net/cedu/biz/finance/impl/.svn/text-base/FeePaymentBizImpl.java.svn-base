package net.cedu.biz.finance.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.InvoiceManagementBiz;
import net.cedu.biz.finance.RefundAcademyCeduBiz;
import net.cedu.biz.finance.RefundBranchBiz;
import net.cedu.biz.finance.RefundCeduAcademyBiz;
import net.cedu.biz.finance.StudentAccountAmountManagementBiz;
import net.cedu.biz.finance.StudentAccountManagementBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.enums.BranchEnum;
import net.cedu.common.string.StringUtil;
import net.cedu.dao.finance.FeePaymentDao;
import net.cedu.dao.finance.FeePaymentDetailDao;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.InvoiceManagement;
import net.cedu.entity.finance.RefundAcademyCedu;
import net.cedu.entity.finance.RefundBranch;
import net.cedu.entity.finance.RefundCeduAcademy;
import net.cedu.entity.finance.StudentAccountAmountManagement;
import net.cedu.entity.finance.StudentAccountManagement;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * 缴费单  业务逻辑实现
 * 
 * @author gaole
 *
 */

@Service
public class FeePaymentBizImpl implements FeePaymentBiz {

	@Autowired
	private FeePaymentDao feepaymentDao;               //缴费单接口
	
	@Autowired
	private FeePaymentDetailBiz feepaymentdetailBiz;   //缴费单明细Biz
	
	@Autowired
	private FeePaymentDetailDao feePaymentDetailDao; //缴费单_数据层接口
	
	@Autowired
	private FeeSubjectBiz feesubjectBiz;               //缴费科目Biz
	
	@Autowired
	private InvoiceManagementBiz invoiceManagementBiz; //发票Biz
	
	@Autowired
	private StudentAccountManagementBiz studentAccountManagementBiz;// 学生账户业务接口

	@Autowired
	private StudentAccountAmountManagementBiz studentAccountAmountManagementBiz;// 学生账户明细业务接口
	
	@Autowired
	private RefundAcademyCeduBiz refundAcademyCeduBiz;//院校替总部垫付退费金额
	
	@Autowired
	private RefundBranchBiz refundBranchBiz;//总部/院校退中心费用
	
	@Autowired
	private RefundCeduAcademyBiz refundCeduAcademyBiz;//总部替院校垫付退费
	
	@Autowired
	private StudentBiz studentBiz;//学生_业务层接口
	
	
	
	/*
	 * 按主键ID查询缴费单
	 * @see net.cedu.biz.finance.FeePaymentBiz#findFeePaymentById(int)
	 */
	public FeePayment findFeePaymentById(int id) throws Exception {
		
		return feepaymentDao.findById(id);
	}
	
	/*
	 * 新增缴费单
	 * 
	 * @see net.cedu.biz.finance.FeePaymentBiz#addFeePayment(net.cedu.entity.finance.FeePayment)
	 */
	public boolean addFeePayment(FeePayment feePayment) throws Exception
	{
		if (feePayment != null)
		{
			Object object = feepaymentDao.save(feePayment);
			if (object != null) 
			{
				return true;
			}
		}		
		return false;
	}
	
	
	/*
	 * 查看学生缴费单   
	 * @see net.cedu.biz.finance.FeePaymentBiz#findFeePaymentByStudentId(int)
	 */
	public List<FeePayment> findFeePaymentByStudentId(int studentId,int status) throws Exception {
		List<FeePayment> feepaymentlst=null;
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if(studentId!=0)
		{
			hqlparam+=" and studentId="+ Constants.PLACEHOLDER;
			list.add(studentId);
		}
		//屏蔽按缴费单状态查询
		//hqlparam+=" and status="+ Constants.PLACEHOLDER;
		//list.add(status);
		hqlparam+=" and deleteFlag="+ Constants.PLACEHOLDER;
		list.add(Constants.DELETE_FALSE);
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		Long[] feepaymentids=feepaymentDao.getIDs(p);
		
		if(feepaymentids!=null && feepaymentids.length>0)
		{
			List<InvoiceManagement> imlst=invoiceManagementBiz.findInvoiceManagementByStudentId(studentId);
			feepaymentlst=new ArrayList<FeePayment>();
			for(int i=0;i<feepaymentids.length;i++)
			{
				FeePayment fp=this.findFeePaymentById(Integer.valueOf(feepaymentids[i].toString()));
				FeePayment feepayment=fp;
				
				//缴费单明细
				List<FeePaymentDetail> feepaymentdetailList=feepaymentdetailBiz.findFeePaymentDetailListByFeePaymentId(feepayment.getId());
				if(feepaymentdetailList!=null&&feepaymentdetailList.size()!=0){
					for (FeePaymentDetail feePaymentDetail2 : feepaymentdetailList) {
						//费用ID
						int feeSubjectId=feePaymentDetail2.getFeeSubjectId();
						FeeSubject feesubject=feesubjectBiz.findFeeSubjectById(feeSubjectId);
						feePaymentDetail2.setPaymentSubjectName(feesubject!=null?feesubject.getName():"");
						//查询发票号
						if(imlst!=null && imlst.size()>0)
						{
							String codes=";";
							for (InvoiceManagement invoiceManagement : imlst) {
								if(invoiceManagement.getFeePaymentDetailId().indexOf("_"+feePaymentDetail2.getId()+"_")!=-1){
								if(codes.equals(";")){
									codes=invoiceManagement.getInvoiceCode();
								}else{
									codes+=";"+invoiceManagement.getInvoiceCode();
								}
								}
							}
							if(codes.equals(";")){
								codes="";
							}
							feePaymentDetail2.setInvoiceCodes(codes);
						}
						
					}
				}
				feepayment.setFeePaymentDetailList(feepaymentdetailList);
				feepaymentlst.add(feepayment);
			}
		}
		return feepaymentlst;

	}

	/*
	 * 查看发票缴费单
	 * @see net.cedu.biz.finance.FeePaymentBiz#findFeePaymentByFeePaymentIds(java.lang.String, net.cedu.model.page.PageResult)
	 */
	public List<FeePayment> findFeePaymentByFeePaymentIds(String FeePaymentIds,
			PageResult<FeePayment> pr) throws Exception {
		List<FeePayment> feepaymentlst=null;
		PageParame p = new PageParame(pr);
		Object[] feepaymentids=StringUtil.strToObject(FeePaymentIds);
		if(feepaymentids!=null && feepaymentids.length>0)
		{
			feepaymentlst=new ArrayList<FeePayment>();
			for(int i=0;i<feepaymentids.length;i++)
			{
				FeePayment fp=this.findFeePaymentById(Integer.valueOf(feepaymentids[i].toString()));
				FeePayment feepayment=fp;
				FeePaymentDetail feepaymentdetail=feepaymentdetailBiz.findFeePaymentDetailByFeePaymentId(feepayment.getId());
				FeeSubject feesubject=new FeeSubject();
				if(feepaymentdetail!=null)
				{
					feesubject=feesubjectBiz.findFeeSubjectById(feepaymentdetail.getFeeSubjectId());
					if(feesubject!=null)
					{
						feepayment.setFeeSubject(feesubject.getName());
					}
					feepayment.setFeePaymentDetailCode(feepaymentdetail.getCode());
					feepayment.setAmountPaied(feepaymentdetail.getAmountPaied());
				}
				feepaymentlst.add(feepayment);
			}
		}
		return feepaymentlst;
	}


	/*
	 *按缴费单号查询缴费单
	 * @see net.cedu.biz.finance.FeePaymentBiz#findFeePaymentByCode(java.lang.String)
	 */
	public FeePayment findFeePaymentByCode(String code) throws Exception {
		
		return feepaymentDao.getByProperty("and code="+Constants.PLACEHOLDER+" and deleteFlag="+Constants.PLACEHOLDER,code,Constants.DELETE_FALSE).get(0);
	}


	public List<FeePayment> findFeePaymentBySId(int studentId, int status)
			throws Exception {
		List<FeePayment> feepaymentlst=null;
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if(studentId!=0)
		{
			hqlparam+=" and studentId="+ Constants.PLACEHOLDER;
			list.add(studentId);
		}
		hqlparam+=" and status>"+ Constants.PLACEHOLDER;
		list.add(status);
		hqlparam+=" and deleteFlag="+ Constants.PLACEHOLDER;
		list.add(Constants.DELETE_FALSE);
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		Long[] feepaymentids=feepaymentDao.getIDs(p);
		
		if(feepaymentids!=null && feepaymentids.length>0)
		{
			feepaymentlst=new ArrayList<FeePayment>();
			for(int i=0;i<feepaymentids.length;i++)
			{
				FeePayment fp=this.findFeePaymentById(Integer.valueOf(feepaymentids[i].toString()));
				feepaymentlst.add(fp);
			}
		}
		return feepaymentlst;

	}

	/*
	 * 修改学生缴费单
	 * 
	 * @see net.cedu.biz.finance.FeePaymentBiz#updateFeePayment(net.cedu.entity.finance.FeePayment)
	 */
	public boolean updateFeePayment(FeePayment feePayment) throws Exception
	{
		if (feePayment != null) 
		{
			Object object = feepaymentDao.update(feePayment);
			if (object != null) 
			{
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 统计缴费单总金额(如果缴费单没有打印条件查询则赋值为isPrint=-1)
	 * 
	 * @see net.cedu.biz.finance.FeePaymentBiz#countFeePaymentAllMoneyByConditions()
	 */
	public String countFeePaymentAllMoneyByConditions(FeePayment feePayment, Student student,
			String feemoney,String starttime,String endtime) throws Exception
	{
		return this.feepaymentDao.countFeePaymentAllMoneyByConditions(feePayment, student, feemoney, starttime, endtime);
	}
	
	/*
	 * (重载方法)统计缴费单总金额(如果缴费单没有打印条件查询则赋值为isPrint=-1)
	 * 
	 * @see net.cedu.biz.finance.FeePaymentBiz#countFeePaymentAllMoneyByConditions()
	 */
	public String countFeePaymentAllMoneyByConditions(FeePayment feePayment, Student student,
			String feemoney,String starttime,String endtime,String globalids) throws Exception
	{
		return this.feepaymentDao.countFeePaymentAllMoneyByConditions(feePayment, student, feemoney, starttime, endtime, globalids);
	}
	
	/*
	 * 添加退费单
	 * 
	 * @see net.cedu.biz.finance.FeePaymentBiz#addRefundPayment(net.cedu.entity.finance.FeePayment, java.util.List, java.util.List, java.util.List)
	 */
	public boolean addRefundPayment(FeePayment feePayment,List<FeePaymentDetail> feePaymentDetailList,List<StudentAccountAmountManagement> stuaaList,List<FeePaymentDetail> historyfpdList) throws Exception
	{
		boolean isback=false;
		String allMoney="0";//退费总金额
		double rechargeAmount=0;//充值账户退费金额
		if(feePaymentDetailList!=null && feePaymentDetailList.size()>0)
		{
			for(FeePaymentDetail fpd:feePaymentDetailList)
			{
				allMoney=(new BigDecimal(allMoney).add(new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount())))).toString();
			}
		}
		//充值账户
		if(stuaaList!=null && stuaaList.size()>0)
		{
			for(StudentAccountAmountManagement saam:stuaaList)
			{
				rechargeAmount=(new BigDecimal(rechargeAmount).subtract(saam.getAccountMoney())).doubleValue();
			}
		}
		//添加退费单
		feePayment.setFeePayment(Double.valueOf(allMoney));
		feePayment.setRechargeAmount(rechargeAmount);
		feePayment.setTotalAmount((new BigDecimal(allMoney).add(new BigDecimal(rechargeAmount))).doubleValue());
		feePayment.setStatus(Constants.PAYMENT_STATUS_YI_TIAN_TUI_FEI_DAN);
		feePayment.setPamentType(Constants.FEE_PAYMENT_TYPE_REFUND_SINGLE);
		feePayment.setDeleteFlag(Constants.DELETE_FALSE);
		
		feePayment.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
		
		feePayment.setCommonBatch(this.feepaymentDao.getCommonBatch(feePayment.getStudentId()));
		isback=this.addFeePayment(feePayment);
		
		//添加退费单明细
		if(feePaymentDetailList!=null && feePaymentDetailList.size()>0)
		{
			int index=1;
			for(FeePaymentDetail fpd:feePaymentDetailList)
			{
				fpd.setCode(index+"");
				fpd.setStudentId(feePayment.getStudentId());
				fpd.setFeePaymentId(feePayment.getId());
				fpd.setStatus(feePayment.getStatus());
				fpd.setTypes(feePayment.getPamentType());
				fpd.setDeleteFlag(Constants.DELETE_FALSE);
				fpd.setCreatedTime(feePayment.getCreatedTime());
				fpd.setCreatorId(feePayment.getCreatorId());
				fpd.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
				fpd.setUpdaterId(feePayment.getCreatorId());
				isback=this.feepaymentdetailBiz.addFeePaymentDetail(fpd);				
				index++;
			}
		}
		//充值账户
		if(stuaaList!=null && stuaaList.size()>0)
		{
			// 学生账户     申请退费状态   不改变总账户金额
			StudentAccountManagement sam = this.studentAccountManagementBiz
					.updateStudentAccountManagementByStudentIdForFee(
							feePayment.getStudentId(),
							feePayment.getCreatorId());
			//String accmoney="0";
			if(sam!=null)
			{
				for(StudentAccountAmountManagement saam:stuaaList)
				{
					saam.setAccountId(sam.getId());
					saam.setFeePaymentId(feePayment.getId());
					isback=this.studentAccountAmountManagementBiz.addStudentAccountAmountManagement(saam);
					//accmoney=(new BigDecimal(accmoney).add(saam.getAccountMoney())).toString();
				}
				
				// 申请退费状态   不改变总账户金额
//				sam.setAccountBalance(sam.getAccountBalance().subtract(new BigDecimal(accmoney)));
//				this.studentAccountManagementBiz
//						.updateStudentAccountManagementById(sam);
			}
		}	
		//历史缴费单处理
		if(historyfpdList!=null && historyfpdList.size()>0)
		{
			for(FeePaymentDetail hfpd:historyfpdList)
			{
				//hfpd.setStatus(Constants.PAYMENT_STATUS_YI_TUI_FEI);
				hfpd.setRefundLock(Constants.LOCKING_TRUE);
				isback=this.feepaymentdetailBiz.updateFeePaymentDetail(hfpd);
			}
		}
		return isback;
	}
	
	/*
	 * 审批退费单
	 * 
	 * @see net.cedu.biz.finance.FeePaymentBiz#updateRefundPaymentForConfirm(net.cedu.entity.finance.FeePayment, java.util.List, net.cedu.entity.finance.StudentAccountManagement, java.util.List, java.util.List)
	 */
	public boolean updateRefundPaymentForConfirm(FeePayment feePayment,List<FeePaymentDetail> feePaymentDetailList,StudentAccountManagement studentAccountManagement,List<StudentAccountAmountManagement> stuaaList,List<FeePaymentDetail> historyfpdList) throws Exception
	{
		boolean isback=false;
		
		//********************2012-04-11增加退费后续流程判断归属***********************//
		boolean isfail=addRefundHouXuLiuCheng(feePayment,feePaymentDetailList,stuaaList);
	
		//*****************************************************************//
		
		//退费单
		if(feePayment!=null)
		{
			if(isfail)
			{
				feePayment.setStatus(Constants.PAYMENT_STATUS_KE_YI_ZHI_JIE_TUI_FEI);
			}
			this.feepaymentDao.update(feePayment);
		}
		//退费单明细
		if(feePaymentDetailList!=null && feePaymentDetailList.size()>0)
		{
			for(FeePaymentDetail fpd:feePaymentDetailList)
			{
				if(isfail)
				{
					fpd.setStatus(Constants.PAYMENT_STATUS_KE_YI_ZHI_JIE_TUI_FEI);
				}
				isback=this.feepaymentdetailBiz.updateFeePaymentDetail(fpd);
			}
		}
		//充值账户
		if(studentAccountManagement!=null)
		{
			this.studentAccountManagementBiz.updateStudentAccountManagementById(studentAccountManagement);
		}
		//充值账户明细
		if(stuaaList!=null && stuaaList.size()>0)
		{
			for(StudentAccountAmountManagement saam:stuaaList)
			{
				isback=this.studentAccountAmountManagementBiz.updateStudentAccountAmountManagement(saam);
			}
		}
		//历史缴费单
		if(historyfpdList!=null && historyfpdList.size()>0)
		{
			for(FeePaymentDetail hfpd:historyfpdList)
			{
				isback=this.feepaymentdetailBiz.updateFeePaymentDetail(hfpd);
			}
		}
		return isback;
	}
	
	/**
	 * 增加审批退费单通过的后续流程
	 * 
	 */
	private boolean addRefundHouXuLiuCheng(FeePayment feePayment,List<FeePaymentDetail> feePaymentDetailList,List<StudentAccountAmountManagement> stuaaList) throws Exception 
	{
		if(feePayment.getStatus()<=Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_DAI_QUE_REN)
		{
			Student stu=this.studentBiz.findStudentById(feePayment.getStudentId());//学生
			RefundAcademyCedu rac= null;//new RefundAcademyCedu();//院校替总部垫付退费
			List<RefundAcademyCedu> raclist=new ArrayList<RefundAcademyCedu>();//院校替总部垫付退费集合
			RefundBranch br= null;//new RefundBranch();//总部/院校退中心费用
			RefundBranch rb=null;
			RefundCeduAcademy rca= null;//new RefundCeduAcademy();//总部替院校垫付退费
			List<RefundCeduAcademy> rcalist=new ArrayList<RefundCeduAcademy>();//总部替院校垫付退费退费集合
			List<RefundBranch> rblist=new ArrayList<RefundBranch>();//总部/院校退中心费用集合
			int index=0;//控制退费单整体状态，以便能够直接退费
			int fpdcount=0;
			//退费单明细
			if(stu!=null && feePaymentDetailList!=null && feePaymentDetailList.size()>0)
			{
				fpdcount=feePaymentDetailList.size();

				for(FeePaymentDetail fpd:feePaymentDetailList)
				{
					
					if(fpd.getStatus()==Constants.PAYMENT_STATUS_YI_TUI_FEI_QUE_REN)
					{
						if(fpd.getSupperStatus()==Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN)
						{
							index++;
						}
						else if(fpd.getSupperStatus()==Constants.PAYMENT_STATUS_ZONG_BU_QUE_REN)
						{
							br=new RefundBranch();
							br.setAmount(new BigDecimal(fpd.getCeduAccount()));
							br.setBranchId(stu.getBranchId());
							br.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
							br.setCreatorId(feePayment.getUpdaterId());
							br.setDeleteFlag(Constants.DELETE_FALSE);
							//br.setNote(note)
							br.setRefundDepId(BranchEnum.Admin.value());//总部
							br.setRefundPaymentDetailId(fpd.getId());
							br.setRefundPaymentId(feePayment.getId());
							br.setStatus(Constants.REFUND_BRANCH_STATUS_WEI_HUI_KUAN);
							br.setStudentId(feePayment.getStudentId());
							br.setTypes(Constants.REFUND_BRANCH_TYPES_CEDU);
							br.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
							br.setUpdaterId(feePayment.getUpdaterId());
							rblist.add(br);
							
						}
						else if(fpd.getSupperStatus()==Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO || fpd.getSupperStatus()==Constants.PAYMENT_STATUS_FAN_KUAN_QUE_REN)
						{
							if((Constants.ACADEMY_CHONGDA_DONGCAI).indexOf(","+stu.getAcademyId()+",")!=-1)
							{
								br=new RefundBranch();
								br.setAmount(new BigDecimal(fpd.getAcademyAccount()).add(new BigDecimal(fpd.getCeduAccount())));
								br.setBranchId(stu.getBranchId());
								br.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
								br.setCreatorId(feePayment.getUpdaterId());
								br.setDeleteFlag(Constants.DELETE_FALSE);
								br.setRefundDepId(stu.getAcademyId());//院校
								br.setRefundPaymentDetailId(fpd.getId());
								br.setRefundPaymentId(feePayment.getId());
								br.setStatus(Constants.REFUND_BRANCH_STATUS_WEI_HUI_KUAN);
								br.setStudentId(feePayment.getStudentId());
								br.setTypes(Constants.REFUND_BRANCH_TYPES_ACADEMY);
								br.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
								br.setUpdaterId(feePayment.getUpdaterId());
								rblist.add(br);
								
								if((new BigDecimal(fpd.getCeduAccount()).compareTo(new BigDecimal(0)))!=0)
								{
									//和0比较大于返回1小于返回-1等于返回0
									rac=new RefundAcademyCedu();
									rac.setAcademyId(stu.getAcademyId());
									rac.setAmount(new BigDecimal(fpd.getCeduAccount()));
									rac.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
									rac.setCreatorId(feePayment.getUpdaterId());
									rac.setDeleteFlag(Constants.DELETE_FALSE);
									rac.setRefundPaymentDetailId(fpd.getId());
									rac.setRefundPaymentId(feePayment.getId());
									
									rac.setStatus(Constants.REFUND_ADVANCE_STATUS_NOT_PAYMENT);
									rac.setStudentId(feePayment.getStudentId());
									//rac.setTypes(types)
									rac.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
									rac.setUpdaterId(feePayment.getUpdaterId());
									raclist.add(rac);
									
								}
							}
							else if((Constants.ACADEMY_NANDA_DONGSHI_DIDA).indexOf(","+stu.getAcademyId()+",")!=-1)
							{
								br=new RefundBranch();
								br.setAmount(new BigDecimal(fpd.getCeduAccount()));
								br.setBranchId(stu.getBranchId());
								br.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
								br.setCreatorId(feePayment.getUpdaterId());
								br.setDeleteFlag(Constants.DELETE_FALSE);
								//br.setNote(note)
								br.setRefundDepId(BranchEnum.Admin.value());//总部
								br.setRefundPaymentDetailId(fpd.getId());
								br.setRefundPaymentId(feePayment.getId());
								br.setStatus(Constants.REFUND_BRANCH_STATUS_WEI_HUI_KUAN);
								br.setStudentId(feePayment.getStudentId());
								br.setTypes(Constants.REFUND_BRANCH_TYPES_CEDU);
								br.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
								br.setUpdaterId(feePayment.getUpdaterId());
								rblist.add(br);
								
								rb=new RefundBranch();
								rb.setAmount(new BigDecimal(fpd.getAcademyAccount()));
								rb.setBranchId(stu.getBranchId());
								rb.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
								rb.setCreatorId(feePayment.getUpdaterId());
								rb.setDeleteFlag(Constants.DELETE_FALSE);
								rb.setRefundDepId(stu.getAcademyId());//院校
								rb.setRefundPaymentDetailId(fpd.getId());
								rb.setRefundPaymentId(feePayment.getId());
								rb.setStatus(Constants.REFUND_BRANCH_STATUS_WEI_HUI_KUAN);
								rb.setStudentId(feePayment.getStudentId());
								rb.setTypes(Constants.REFUND_BRANCH_TYPES_ACADEMY);
								rb.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
								rb.setUpdaterId(feePayment.getUpdaterId());
								rblist.add(rb);
								
							}
							else
							{
								br=new RefundBranch();
								br.setAmount(new BigDecimal(fpd.getCeduAccount()).add(new BigDecimal(fpd.getAcademyAccount())));
								br.setBranchId(stu.getBranchId());
								br.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
								br.setCreatorId(feePayment.getUpdaterId());
								br.setDeleteFlag(Constants.DELETE_FALSE);
								//br.setNote(note)
								br.setRefundDepId(BranchEnum.Admin.value());//总部
								br.setRefundPaymentDetailId(fpd.getId());
								br.setRefundPaymentId(feePayment.getId());
								br.setStatus(Constants.REFUND_BRANCH_STATUS_WEI_HUI_KUAN);
								br.setStudentId(feePayment.getStudentId());
								br.setTypes(Constants.REFUND_BRANCH_TYPES_CEDU);
								br.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
								br.setUpdaterId(feePayment.getUpdaterId());
								rblist.add(br);
								
								if((new BigDecimal(fpd.getAcademyAccount()).compareTo(new BigDecimal(0)))!=0)
								{
									rca=new RefundCeduAcademy();
									rca.setAcademyId(stu.getAcademyId());
									rca.setAmount(new BigDecimal(fpd.getAcademyAccount()));
									rca.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
									rca.setCreatorId(feePayment.getUpdaterId());
									rca.setDeleteFlag(Constants.DELETE_FALSE);
									rca.setRefundPaymentDetailId(fpd.getId());
									rca.setRefundPaymentId(feePayment.getId());
									
									rca.setStatus(Constants.REFUND_ADVANCE_STATUS_NOT_PAYMENT);
									rca.setStudentId(feePayment.getStudentId());
									//rca.setTypes(types)
									rca.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
									rca.setUpdaterId(feePayment.getUpdaterId());
									rcalist.add(rca);
								}
							}
						}
						
					}
				}
			}
			int indexcz=0;//控制退费单整体状态，以便能够直接退费
			//充值账户明细
			if(stuaaList!=null && stuaaList.size()>0)
			{
				for(StudentAccountAmountManagement saam:stuaaList)
				{
					indexcz++;
					rb=new RefundBranch();
					rb.setAmount(saam.getAccountMoney());
					rb.setBranchId(stu.getBranchId());
					rb.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
					rb.setCreatorId(feePayment.getUpdaterId());
					rb.setDeleteFlag(Constants.DELETE_FALSE);
					
					rb.setRefundDepId(BranchEnum.Admin.value());//总部
					rb.setRefundAccountAmountId(saam.getId());
					rb.setRefundPaymentId(feePayment.getId());
					rb.setStatus(Constants.REFUND_BRANCH_STATUS_WEI_HUI_KUAN);
					rb.setStudentId(feePayment.getStudentId());
					rb.setTypes(Constants.REFUND_BRANCH_TYPES_CEDU);
					rb.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
					rb.setUpdaterId(feePayment.getUpdaterId());
					rblist.add(rb);
				}
			}
			//院校替总部垫付添加
			if(raclist!=null && raclist.size()>0)
			{
				for(RefundAcademyCedu refac:raclist)
				{
					this.refundAcademyCeduBiz.addRefundAcademyCedu(refac);
				}
			}
			//总部替院校垫付添加
			if(rcalist!=null && rcalist.size()>0)
			{
				for(RefundCeduAcademy refca:rcalist)
				{
					this.refundCeduAcademyBiz.addRefundCeduAcademy(refca);
				}
			}
			//退中心费用添加
			if(rblist!=null && rblist.size()>0)
			{
				for(RefundBranch ref:rblist)
				{
					this.refundBranchBiz.addRefundBranch(ref);
				}
			}
			//判断可不可以直接中心退费
			if(index==fpdcount && indexcz==0)
			{
				return true;
			}
		}
		return false;
	}

	/*
	 * 处理钱在途   退费单审批通过但是钱最终归属未确定的情况
	 * 
	 * 增加审批退费单通过的后续流程
	 * @see net.cedu.biz.finance.FeePaymentBiz#addRefundHouXuLiuChengOtherConfirm(java.util.List)
	 */
	public boolean addRefundHouXuLiuChengOtherConfirm(List<FeePaymentDetail> refundFpdList) throws Exception 
	{
		boolean isback=false;
		if(refundFpdList!=null && refundFpdList.size()>0)
		{
			RefundAcademyCedu rac= null;//new RefundAcademyCedu();//院校替总部垫付退费
			List<RefundAcademyCedu> raclist=new ArrayList<RefundAcademyCedu>();//院校替总部垫付退费集合
			RefundBranch br= null;//new RefundBranch();//总部/院校退中心费用
			RefundBranch rb=null;
			RefundCeduAcademy rca= null;//new RefundCeduAcademy();//总部替院校垫付退费
			List<RefundCeduAcademy> rcalist=new ArrayList<RefundCeduAcademy>();//总部替院校垫付退费集合
			List<RefundBranch> rblist=new ArrayList<RefundBranch>();//总部/院校退中心费用集合
			
			//退费单明细
			for(FeePaymentDetail fpd:refundFpdList)
			{
				Student stu=this.studentBiz.findStudentById(fpd.getStudentId());
				if(fpd.getStatus()==Constants.PAYMENT_STATUS_YI_TUI_FEI_QUE_REN)
				{
					if(fpd.getSupperStatus()>Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN && fpd.getSupperStatus()<Constants.PAYMENT_STATUS_ZONG_BU_QUE_REN)
					{
						br=new RefundBranch();
						br.setAmount(new BigDecimal(fpd.getCeduAccount()));
						br.setBranchId(stu.getBranchId());
						br.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
						br.setCreatorId(fpd.getUpdaterId());
						br.setDeleteFlag(Constants.DELETE_FALSE);
						//br.setNote(note)
						br.setRefundDepId(BranchEnum.Admin.value());//总部
						br.setRefundPaymentDetailId(fpd.getId());
						br.setRefundPaymentId(fpd.getFeePaymentId());
						br.setStatus(Constants.REFUND_BRANCH_STATUS_WEI_HUI_KUAN);
						br.setStudentId(fpd.getStudentId());
						br.setTypes(Constants.REFUND_BRANCH_TYPES_CEDU);
						br.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
						br.setUpdaterId(fpd.getUpdaterId());
						rblist.add(br);
							
					}
					else if(fpd.getSupperStatus()>Constants.PAYMENT_STATUS_ZONG_BU_QUE_REN && fpd.getSupperStatus()<Constants.PAYMENT_STATUS_FAN_KUAN_QUE_REN)
					{
						if((Constants.ACADEMY_CHONGDA_DONGCAI).indexOf(","+stu.getAcademyId()+",")!=-1)
						{
							br=new RefundBranch();
							br.setAmount(new BigDecimal(fpd.getAcademyAccount()).add(new BigDecimal(fpd.getCeduAccount())));
							br.setBranchId(stu.getBranchId());
							br.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
							br.setCreatorId(fpd.getUpdaterId());
							br.setDeleteFlag(Constants.DELETE_FALSE);
							br.setRefundDepId(stu.getAcademyId());//院校
							br.setRefundPaymentDetailId(fpd.getId());
							br.setRefundPaymentId(fpd.getFeePaymentId());
							br.setStatus(Constants.REFUND_BRANCH_STATUS_WEI_HUI_KUAN);
							br.setStudentId(fpd.getStudentId());
							br.setTypes(Constants.REFUND_BRANCH_TYPES_ACADEMY);
							br.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
							br.setUpdaterId(fpd.getUpdaterId());
							rblist.add(br);
							
							if((new BigDecimal(fpd.getCeduAccount()).compareTo(new BigDecimal(0)))!=0)
							{
								//和0比较大于返回1小于返回-1等于返回0
								rac=new RefundAcademyCedu();
								rac.setAcademyId(stu.getAcademyId());
								rac.setAmount(new BigDecimal(fpd.getCeduAccount()));
								rac.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
								rac.setCreatorId(fpd.getUpdaterId());
								rac.setDeleteFlag(Constants.DELETE_FALSE);
								rac.setRefundPaymentDetailId(fpd.getId());
								rac.setRefundPaymentId(fpd.getFeePaymentId());
									
								rac.setStatus(Constants.REFUND_ADVANCE_STATUS_NOT_PAYMENT);
								rac.setStudentId(fpd.getStudentId());
								//rac.setTypes(types)
								rac.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
								rac.setUpdaterId(fpd.getUpdaterId());
								raclist.add(rac);
									
							}
						}
						else if((Constants.ACADEMY_NANDA_DONGSHI_DIDA).indexOf(","+stu.getAcademyId()+",")!=-1)
						{
							br=new RefundBranch();
							br.setAmount(new BigDecimal(fpd.getCeduAccount()));
							br.setBranchId(stu.getBranchId());
							br.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
							br.setCreatorId(fpd.getUpdaterId());
							br.setDeleteFlag(Constants.DELETE_FALSE);
							//br.setNote(note)
							br.setRefundDepId(BranchEnum.Admin.value());//总部
							br.setRefundPaymentDetailId(fpd.getId());
							br.setRefundPaymentId(fpd.getFeePaymentId());
							br.setStatus(Constants.REFUND_BRANCH_STATUS_WEI_HUI_KUAN);
							br.setStudentId(fpd.getStudentId());
							br.setTypes(Constants.REFUND_BRANCH_TYPES_CEDU);
							br.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
							br.setUpdaterId(fpd.getUpdaterId());
							rblist.add(br);
								
							rb=new RefundBranch();
							rb.setAmount(new BigDecimal(fpd.getAcademyAccount()));
							rb.setBranchId(stu.getBranchId());
							rb.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
							rb.setCreatorId(fpd.getUpdaterId());
							rb.setDeleteFlag(Constants.DELETE_FALSE);
							rb.setRefundDepId(stu.getAcademyId());//院校
							rb.setRefundPaymentDetailId(fpd.getId());
							rb.setRefundPaymentId(fpd.getFeePaymentId());
							rb.setStatus(Constants.REFUND_BRANCH_STATUS_WEI_HUI_KUAN);
							rb.setStudentId(fpd.getStudentId());
							rb.setTypes(Constants.REFUND_BRANCH_TYPES_ACADEMY);
							rb.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
							rb.setUpdaterId(fpd.getUpdaterId());
							rblist.add(rb);
								
						}
						else
						{
							br=new RefundBranch();
							br.setAmount(new BigDecimal(fpd.getCeduAccount()).add(new BigDecimal(fpd.getAcademyAccount())));
							br.setBranchId(stu.getBranchId());
							br.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
							br.setCreatorId(fpd.getUpdaterId());
							br.setDeleteFlag(Constants.DELETE_FALSE);
							//br.setNote(note)
							br.setRefundDepId(BranchEnum.Admin.value());//总部
							br.setRefundPaymentDetailId(fpd.getId());
							br.setRefundPaymentId(fpd.getFeePaymentId());
							br.setStatus(Constants.REFUND_BRANCH_STATUS_WEI_HUI_KUAN);
							br.setStudentId(fpd.getStudentId());
							br.setTypes(Constants.REFUND_BRANCH_TYPES_CEDU);
							br.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
							br.setUpdaterId(fpd.getUpdaterId());
							rblist.add(br);
								
							if((new BigDecimal(fpd.getAcademyAccount()).compareTo(new BigDecimal(0)))!=0)
							{
								rca=new RefundCeduAcademy();
								rca.setAcademyId(stu.getAcademyId());
								rca.setAmount(new BigDecimal(fpd.getAcademyAccount()));
								rca.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
								rca.setCreatorId(fpd.getUpdaterId());
								rca.setDeleteFlag(Constants.DELETE_FALSE);
								rca.setRefundPaymentDetailId(fpd.getId());
								rca.setRefundPaymentId(fpd.getFeePaymentId());
									
								rca.setStatus(Constants.REFUND_ADVANCE_STATUS_NOT_PAYMENT);
								rca.setStudentId(fpd.getStudentId());
								
								rca.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
							
								rca.setUpdaterId(fpd.getUpdaterId());
								rcalist.add(rca);
							}
						}
					}
						
				}
			}
			
			//院校替总部垫付添加
			if(raclist!=null && raclist.size()>0)
			{
				for(RefundAcademyCedu refac:raclist)
				{
					isback=this.refundAcademyCeduBiz.addRefundAcademyCedu(refac);
				}
			}
			//总部替院校垫付添加
			if(rcalist!=null && rcalist.size()>0)
			{
				for(RefundCeduAcademy refca:rcalist)
				{
					isback=this.refundCeduAcademyBiz.addRefundCeduAcademy(refca);
				}
			}
			//退中心费用添加
			if(rblist!=null && rblist.size()>0)
			{
				for(RefundBranch ref:rblist)
				{
					isback=this.refundBranchBiz.addRefundBranch(ref);
				}
			}
			
		}
		return isback;
	}

	/*
	 * 查询一定条件下的退费单明细（供缴费单 明细流程确认时使用）
	 * 
	 * @see net.cedu.biz.finance.FeePaymentBiz#findRefundPaymentDetailListByFpdIdStartIdEndIdTfStatusId(int, int, int, int)
	 */
	public List<FeePaymentDetail> findRefundPaymentDetailListByFpdIdStartIdEndIdTfStatusId(int feePaymentDetailId,int stratStatusId,int endStatusId,int tfStatusId) throws Exception
	{
		String hqlcon = "";
		List<FeePaymentDetail> list = null;
		List<Object> paramList = new ArrayList<Object>();
		//历史缴费单ID
		if (0<feePaymentDetailId)
		{
			hqlcon += " and supperId=" + Constants.PLACEHOLDER;
			paramList.add(feePaymentDetailId);
		}
		//历史缴费单状态范围
		if (stratStatusId>=0) 
		{
			hqlcon += " and supperStatus>=" + Constants.PLACEHOLDER;
			paramList.add(stratStatusId);
		}
		if (endStatusId>=0) 
		{
			hqlcon += " and supperStatus<=" + Constants.PLACEHOLDER;
			paramList.add(endStatusId);
		}
		//退费单状态
		if (tfStatusId>=0) 
		{
			hqlcon += " and status =" + Constants.PLACEHOLDER;
			paramList.add(tfStatusId);
		}
		//类型是退费单
		hqlcon+=" and types="+ Constants.PLACEHOLDER;
		paramList.add(Constants.FEE_PAYMENT_TYPE_REFUND_SINGLE);
		hqlcon+=" and deleteFlag="+ Constants.PLACEHOLDER;
		paramList.add(Constants.DELETE_FALSE);
		list = this.feePaymentDetailDao.getByProperty(hqlcon, paramList);
		return list;
	}
	
	/*
	 * 修复缴费单明细各个账户值（只能是完成缴费还没进行其他缴费流程才能修复）
	 * 
	 * @see net.cedu.biz.finance.FeePaymentBiz#updateRepairFeePamymentDetailAllAccount()
	 */
	public void updateRepairFeePamymentDetailAllAccount() throws Exception
	{
		this.feepaymentDao.repairFeePamymentDetailAllAccount();
	}
	
	
	public List<FeePaymentDetail> findRefundFpdListByFeePaymentDetailId(int feePaymentDetailId) throws Exception
	{
		return this.feePaymentDetailDao.getByProperty(" and supperId="+Constants.PLACEHOLDER +" and status <>"+Constants.PLACEHOLDER , new Object[]{feePaymentDetailId,Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_SHI_BAI});	
	}
	/**
	 * 通过主键ID集合查询缴费单集合
	  * @see net.cedu.biz.finance.FeePaymentBiz#findFeePaymentByIds(java.lang.String)
	 */
	public List<FeePayment> findFeePaymentByIds(String ids) throws Exception {
		return feepaymentDao.getByProperty(" and id in ("+Constants.PLACEHOLDER+")", new Object[]{"$"+ids});
	}
	
	/*
	 * 按收据号查询缴费单
	 * 
	 * @see net.cedu.biz.finance.FeePaymentBiz#findFeePaymentByBarCode(java.lang.String)
	 */
	public FeePayment findFeePaymentByBarCode(String barCode) throws Exception
	{		
		List<FeePayment> fplist=feepaymentDao.getByProperty("and barCode="+Constants.PLACEHOLDER+" and deleteFlag="+Constants.PLACEHOLDER,new Object[]{barCode,Constants.DELETE_FALSE});
		if(fplist!=null && fplist.size()>0)
		{
			return fplist.get(0);
		}
		else
		{
			return null;
		}		
	}
	
	/*
	 * 修改学生缴费单和缴费单明细集合
	 * 
	 * @see net.cedu.biz.finance.FeePaymentBiz#updateFeePaymentAndFeePaymentDetailList(net.cedu.entity.finance.FeePayment, java.util.List)
	 */
	public boolean updateFeePaymentAndFeePaymentDetailList(FeePayment feePayment,List<FeePaymentDetail> fpdList) throws Exception
	{
		boolean isback=false;
		if (feePayment != null ) 
		{
			isback=this.updateFeePayment(feePayment);
		}
		if(fpdList!=null && fpdList.size()>0)
		{
			for(FeePaymentDetail fpd:fpdList)
			{
				isback=this.feepaymentdetailBiz.updateFeePaymentDetail(fpd);
			}
		}
		return isback;
	}
	
	/*
	 * 更新缴费单日期
	 * 
	 * 0：更新成功    1   缴费单明细已总部确认     2  退费单已确认
	 * @see net.cedu.biz.finance.FeePaymentBiz#updateFeePaymentCreatedTime(int, java.lang.String, int)
	 */
	public int updateFeePaymentCreatedTime(int fpId,String createdTime,int userId) throws Exception
	{
		int count = 0;
		// 缴费单实体
		FeePayment feePayment = feepaymentDao.findById(fpId);
		if (feePayment != null) 
		{
			// 缴费单明细集合
			List<FeePaymentDetail> feePaymentDetailList = this.feepaymentdetailBiz
					.findFeePaymentDetailListByFeePaymentId(feePayment.getId());
			if(feePaymentDetailList!=null && feePaymentDetailList.size()>0)
			{
				//判断
				for(FeePaymentDetail fpd:feePaymentDetailList)
				{
					if(fpd.getRebateStatus()>=Constants.PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN )
					{
						count =1;
						return count;
					}	
					else if(fpd.getStatus()<=Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_DAI_QUE_REN)
					{
						count =2;
						return count;
					}
				}
				for(FeePaymentDetail fpd:feePaymentDetailList)
				{
					fpd.setCreatedTime(DateUtil.getDate(createdTime));
					fpd.setUpdaterId(userId);
					fpd.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
					this.feepaymentdetailBiz.updateFeePaymentDetail(fpd);
				}
				
			}
			feePayment.setCreatedTime(DateUtil.getDate(createdTime));
			feePayment.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
			feePayment.setUpdaterId(userId);
			this.feepaymentDao.update(feePayment);
		}
		return count;
	}

	/*
	 * 根据学生id查询未作废的缴费单数量
	 * @see net.cedu.biz.finance.FeePaymentBiz#findFeePaymentNoInvalidByStudentId(int)
	 */
	public int findFeePaymentNoInvalidByStudentId(int studentId) throws Exception {
		List<Object> list = null;
		String hqlConditionExpression = "";
		PageParame p = new PageParame();
		if (studentId!=0)
		{
			list = new ArrayList<Object>();
			//学生id
			hqlConditionExpression += " and studentId = "+ Constants.PLACEHOLDER;
			list.add(studentId);
			//非作废缴费单
			hqlConditionExpression += " and status <> "+ Constants.PLACEHOLDER;
			list.add(Constants.PAYMENT_STATUS_ZUO_FEI);
			
			if (!hqlConditionExpression.equals("")) {
				p.setHqlConditionExpression(hqlConditionExpression);
				p.setValues(list.toArray());
			}
			return feepaymentDao.getCounts(p);
		}
		return 0;
	}
			
}
