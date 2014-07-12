package net.cedu.action.finance.refund;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.RefundBiz;
import net.cedu.biz.finance.RefundBranchBiz;
import net.cedu.biz.finance.StudentAccountAmountManagementBiz;
import net.cedu.biz.finance.StudentAccountManagementBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.string.MoneyUtil;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyLevel;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.RefundBranch;
import net.cedu.entity.finance.StudentAccountAmountManagement;
import net.cedu.entity.finance.StudentAccountManagement;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 退费单
 * @author xiao
 *
 */
@ParentPackage("json-default")
public class JsonLisRefundPaymentBranchAction extends BaseAction
{
	@Autowired
	private FeePaymentBiz feePaymentBiz;//缴费单业务层接口
	private int feePaymentId;//缴费单Id
	private int status=0;//确认状态
	private String refundBranchIds = "";
	
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;//缴费单明细业务层接口
	private List<FeePaymentDetail> feePaymentDetailList=new ArrayList<FeePaymentDetail>();//缴费单明细实体
	
	@Autowired
	private StudentAccountAmountManagementBiz studentAccountAmountManagementBiz;//学生充值账户明细业务接口
	
	@Autowired
	private StudentAccountManagementBiz studentAccountManagementBiz;//学生充值账户_业务层接口
	
	@Autowired
	private RefundBranchBiz refundBranchBiz;//总部/院校/中心费用_业务层接口
	
	@Autowired
	private AcademyLevelBiz academyLevelBiz;//层次_业务层接口
	
	@Autowired
	private RefundBiz refundBiz;//退费单_业务层接口 
	
	// 分页结果
	private PageResult<RefundBranch> result = new PageResult<RefundBranch>();
	//分页参数
	private RefundBranch refundBranch = new RefundBranch();//总部/院校/中心费用
	private Student student = new Student();//学生信息
	private FeePayment feePayment = new FeePayment();//退费单
	private String starttime = "";//开始时间
	private String endtime = "";//结束时间
	
	
	//返回值
	private boolean isback=false;
	//退费总额
	private String allRefundPaymentMoney = "0";
	//批量选择退费总额
	private double batchRefundPaymentMoney = 0;
	
	/**
	 * 更新退费单
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_refund_payment_status_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String upRefundPaymentStatus() throws Exception 
	{	
		//退费单
		FeePayment feePayment=this.feePaymentBiz.findFeePaymentById(feePaymentId);
		if(feePayment!=null && status!=0 && feePayment.getStatus()==Constants.PAYMENT_STATUS_YI_TIAN_TUI_FEI_DAN)
		{		
			//历史缴费单
			List<FeePaymentDetail> historyfpdlist=new ArrayList<FeePaymentDetail>();
			//充值账户明细
			List<StudentAccountAmountManagement> saamlist=new ArrayList<StudentAccountAmountManagement>();
			
			feePayment.setStatus(status);
			feePayment.setUpdaterId(super.getUser().getUserId());
			feePayment.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
			//退费单明细
			feePaymentDetailList=this.feePaymentDetailBiz.findFeePaymentDetailListByFeePaymentId(feePayment.getId());
			if(feePaymentDetailList!=null && feePaymentDetailList.size()>0)
			{
				for(FeePaymentDetail fpd:feePaymentDetailList)
				{
					fpd.setStatus(status);
					fpd.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
					fpd.setUpdaterId(super.getUser().getUserId());
					if(fpd.getSupperId()!=0 && this.feePaymentDetailBiz.findById(fpd.getSupperId())!=null)
					{
						FeePaymentDetail feepd=this.feePaymentDetailBiz.findById(fpd.getSupperId());
						if(status==Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_TONG_GUO)
						{	
							
						}
						else
						{
							feepd.setRefundLock(Constants.LOCKING_FALSE);//解除原始缴费单的锁定
						}
							
						feepd.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
						feepd.setUpdaterId(super.getUser().getUserId());
						historyfpdlist.add(feepd);
					}
				}
			}
			//学生充值账户
			StudentAccountManagement studentAccountManagement = this.studentAccountManagementBiz
					.updateStudentAccountManagementByStudentIdForFee(
							feePayment.getStudentId(),
							super.getUser().getUserId());
			BigDecimal allacountmoney=new BigDecimal(0);
			
			//充值账户明细状态修改
			saamlist=this.studentAccountAmountManagementBiz.findStudentAccountAmountManagementListByFeePaymentIdTypes(feePayment.getId(), Constants.STATUS_APPLY_CONSUMPTION_TRUE);
			if(saamlist!=null && saamlist.size()>0)
			{
				for(StudentAccountAmountManagement saam:saamlist)
				{
					if(status==Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_TONG_GUO)
					{
						
					}
					else
					{
						saam.setTypes(Constants.STATUS_APPLY_CONSUMPTION_FALSE);
					}
				}
			}
			studentAccountManagement
			.setAccountBalance(studentAccountManagement
					.getAccountBalance().subtract(allacountmoney));
			
			if((feePaymentDetailList!=null && feePaymentDetailList.size()>0) || (saamlist!=null && saamlist.size()>0))
			{
				isback=this.feePaymentBiz.updateRefundPaymentForConfirm(feePayment, feePaymentDetailList,studentAccountManagement, saamlist, historyfpdlist);
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 收总部退费确认/院校退费确认/学习中心退费/学生退费_数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_refund_payment_cedu_academy_branch_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String countRefundPaymentCeduBranch() throws Exception 
	{	
		if (student != null) {
			// 层次
			if (student.getLevelId() != 0) {
				AcademyLevel academyLevel = academyLevelBiz.findById(student.getLevelId());
				student.setLevelId(academyLevel.getLevelId());
			}
			//中心(退费确认表的中心和学生中心一样)
			refundBranch.setBranchId(student.getBranchId());
		}
		result.setRecordCount(refundBranchBiz.findRefundBranchPageCount(refundBranch, student, feePayment, starttime, endtime, result));
		return SUCCESS;
	}
	
	/**
	 * 收总部退费确认/院校退费确认/学习中心退费/学生退费退费确认_集合
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_refund_payment_cedu_academy_branch_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String listRefundPaymentCeduBranch() throws Exception 
	{	
		if (student != null) {
			// 层次
			if (student.getLevelId() != 0) {
				AcademyLevel academyLevel = academyLevelBiz.findById(student.getLevelId());
				student.setLevelId(academyLevel.getLevelId());
			}
			//中心(退费确认表的中心和学生中心一样)
			refundBranch.setBranchId(student.getBranchId());
		}
		result.setList(refundBranchBiz.findRefundBranchPageList(refundBranch, student, feePayment, starttime, endtime, result));
		return SUCCESS;
	}
	
	/**
	 * 收总部退费确认/院校退费确认/学习中心退费/学生退费_总退费金额
	 * @return
	 * @throws Exception
	 */
	@Action(value = "sum_refund_payment_cedu_academy_branch_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String sumRefundPaymentCeduBranch() throws Exception 
	{	
		if (student != null) {
			// 层次
			if (student.getLevelId() != 0) {
				AcademyLevel academyLevel = academyLevelBiz.findById(student.getLevelId());
				student.setLevelId(academyLevel.getLevelId());
			}
			//中心(退费确认表的中心和学生中心一样)
			refundBranch.setBranchId(student.getBranchId());
		}
		allRefundPaymentMoney = String.valueOf(refundBranchBiz.findPaymentSum(refundBranch, student, feePayment, starttime, endtime));
		return SUCCESS;
	}
	
	/**
	 * 批量修改状态(可以是一个id，非收院校退费确认，收总部退费确认)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "batch_update_refund_payment_status_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String batchUpdateRefundPaymentStatus() throws Exception 
	{
		if(refundBranchIds!=null && !refundBranchIds.equals("") && status!=0)
		{
			//查询要批量修改的费用确认状态，如果有其中一条费用状态大于要修改的状态则跳出（避免多人同时操作BUG）
			List<RefundBranch> rplist = refundBranchBiz.findRefundBranchByIds(refundBranchIds);
			if(rplist!=null && rplist.size()>0)
			{
				for(RefundBranch rb : rplist)
				{
					if(rb.getStatus()>=status)
					{
						return SUCCESS;
					}
				}
			}
			isback = refundBranchBiz.updateRefundBranchStatusByIds(refundBranchIds, status,super.getUser().getUserId());
		}
		return SUCCESS;
	}
	
	/**
	 * 中心收退费确认(注：只适用 收院校退费确认，收总部退费确认！)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_branch_refund_payment_status_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String updateBranchRefundPaymentStatus() throws Exception 
	{
		if(refundBranchIds!=null && !refundBranchIds.equals("") && status!=0)
		{
			try {
				int refundBranchId = Integer.parseInt(refundBranchIds);
				RefundBranch rbOld = refundBranchBiz.findRefundBranchById(refundBranchId);
				//当前状态如果高于或者等于当前确认状态则不可修改（避免多人同时操作BUG）
				if(rbOld!=null && rbOld.getStatus()<Constants.REFUND_BRANCH_STATUS_YI_HUI_KUAN_QUE_REN)
				{
					//修改费用状态
					isback = refundBranchBiz.updateRefundBranchStatusByIds(refundBranchIds, status,super.getUser().getUserId());
					//修改退费单明细状态
					FeePaymentDetail fpdOld = feePaymentDetailBiz.findById(rbOld.getRefundPaymentDetailId());
					if(fpdOld!=null)
					{
						fpdOld.setStatus(Constants.PAYMENT_STATUS_KE_YI_ZHI_JIE_TUI_FEI);
						fpdOld.setUpdaterId(super.getUser().getUserId());
						fpdOld.setUpdatedTime(DateUtil.getNow());
						feePaymentDetailBiz.updateFeePaymentDetail(fpdOld);
						//修改退费单状态（判断明细是否全部确认）
						FeePayment fpOld = feePaymentBiz.findFeePaymentById(fpdOld.getFeePaymentId());
						if(fpOld!=null)
						{
							fpOld.setStatus(Constants.PAYMENT_STATUS_KE_YI_ZHI_JIE_TUI_FEI);
							fpOld.setUpdaterId(super.getUser().getUserId());
							fpOld.setUpdatedTime(DateUtil.getNow());
							refundBiz.updateFeepaymentsStatusByIdAndDetailStatus(fpOld);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 查询退费总费用(可以是一个id)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "batch_find_refund_payment_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String batchFindRefundPayment() throws Exception 
	{
		if(refundBranchIds!=null && !refundBranchIds.equals(""))
		{
			batchRefundPaymentMoney = refundBranchBiz.findRefundBranchSumPaymentByIds(refundBranchIds);
		}
		return SUCCESS;
	}

	public int getFeePaymentId() {
		return feePaymentId;
	}

	public void setFeePaymentId(int feePaymentId) {
		this.feePaymentId = feePaymentId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isIsback() {
		return isback;
	}

	public void setIsback(boolean isback) {
		this.isback = isback;
	}

	public List<FeePaymentDetail> getFeePaymentDetailList() {
		return feePaymentDetailList;
	}

	public void setFeePaymentDetailList(List<FeePaymentDetail> feePaymentDetailList) {
		this.feePaymentDetailList = feePaymentDetailList;
	}

	public PageResult<RefundBranch> getResult() {
		return result;
	}

	public void setResult(PageResult<RefundBranch> result) {
		this.result = result;
	}

	public RefundBranch getRefundBranch() {
		return refundBranch;
	}

	public void setRefundBranch(RefundBranch refundBranch) {
		this.refundBranch = refundBranch;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public FeePayment getFeePayment() {
		return feePayment;
	}

	public void setFeePayment(FeePayment feePayment) {
		this.feePayment = feePayment;
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

	public String getAllRefundPaymentMoney() {
		allRefundPaymentMoney=MoneyUtil.formatMoney(allRefundPaymentMoney);
		return allRefundPaymentMoney;
	}

	public void setAllRefundPaymentMoney(String allRefundPaymentMoney) {
		this.allRefundPaymentMoney = allRefundPaymentMoney;
	}

	public String getRefundBranchIds() {
		return refundBranchIds;
	}

	public void setRefundBranchIds(String refundBranchIds) {
		this.refundBranchIds = refundBranchIds;
	}

	public double getBatchRefundPaymentMoney() {
		return batchRefundPaymentMoney;
	}

	public void setBatchRefundPaymentMoney(double batchRefundPaymentMoney) {
		this.batchRefundPaymentMoney = batchRefundPaymentMoney;
	}
	
	
}
