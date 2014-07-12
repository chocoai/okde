package net.cedu.action.finance.academyrebatereview;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.GlobalEnrollBatchBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.common.Constants;
import net.cedu.common.string.MoneyUtil;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.GlobalEnrollBatch;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 院校返款单新增(可以返款的缴费单明细ajax)
 * 
 * @author lixiaojun
 * 
 */
@ParentPackage("json-default")
public class JsonAddAcademyYearRebateReviewShowAction extends BaseAction
{
	// 分页结果
	private PageResult<FeePaymentDetail> result = new PageResult<FeePaymentDetail>();
	
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;//缴费单明细_业务层接口
	
	//查询条件
	private Student student=new Student();
	private FeePaymentDetail feePaymentDetail=new FeePaymentDetail();
	private int year;//年度
	
	@Autowired
	private GlobalEnrollBatchBiz globalEnrollBatchBiz;//全局批次_业务层接口
	private List<GlobalEnrollBatch> glblist=new ArrayList<GlobalEnrollBatch>();//全局批次集合
	
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;//招生批次_业务层接口
	
	
	//************统计学习中心***************//
	private String fpdIds;//根据缴费单明细ids字符冲统计其所在的学习中心集合
	List<Branch> branchList=new ArrayList<Branch>();//学习中心列表
	
	//************统计院校年度追加返款的总金额*********************//
	private String allAddMoney="0";//追加返款总金额	
	
	
	
	/**
	 * 获取适合年度返款的缴费单明细数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_academy_year_rebate_review_fpd_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String playmoneyCount() throws Exception 
	{		
		feePaymentDetail.setStatus(Constants.PAYMENT_STATUS_YI_TIAN_FAN_KUAN_DAN);		
		student.setStartStatusId(Constants.STU_CALL_STATUS_YI_DAO_MING);
		List<AcademyEnrollBatch> aeblist=new ArrayList<AcademyEnrollBatch>();
		//通过年份查询全局批次   通过全局批次和院校查询该院校下的招生批次
		if(year>0)
		{
			glblist=this.globalEnrollBatchBiz.findGlobalEnrollBatchByYear(year);
			if(glblist!=null && glblist.size()>0)
			{
				AcademyEnrollBatch aeb=null;
				for(GlobalEnrollBatch geb:glblist)
				{
					if(geb!=null)
					{
						aeb=this.academyEnrollBatchBiz.findNonFinishedAcademyBatchByGlobalBatchIdAndAcademyId(geb.getId(),student.getAcademyId());
						if(aeb!=null)
						{
							aeblist.add(aeb);
						}
					}
				}
			}
		}
		if(aeblist!=null && aeblist.size()>0)
		{
			result.setRecordCount(this.feePaymentDetailBiz.findFpdCountByPageForAcademyYearRebateLoad(feePaymentDetail, student,aeblist));
		}
		else
		{
			result.setRecordCount(0);
		}		
		return SUCCESS;
	}

	/**
	 * 获取适合返款的缴费单明细列表
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_academy_year_rebate_review_fpd_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String playmoneyList() throws Exception 
	{	
		feePaymentDetail.setStatus(Constants.PAYMENT_STATUS_YI_TIAN_FAN_KUAN_DAN);		
		student.setStartStatusId(Constants.STU_CALL_STATUS_YI_DAO_MING);
		List<AcademyEnrollBatch> aeblist=new ArrayList<AcademyEnrollBatch>();
		//通过年份查询全局批次   通过全局批次和院校查询该院校下的招生批次
		if(year>0)
		{
			glblist=this.globalEnrollBatchBiz.findGlobalEnrollBatchByYear(year);
			if(glblist!=null && glblist.size()>0)
			{
				AcademyEnrollBatch aeb=null;
				for(GlobalEnrollBatch geb:glblist)
				{
					if(geb!=null)
					{
						aeb=this.academyEnrollBatchBiz.findNonFinishedAcademyBatchByGlobalBatchIdAndAcademyId(geb.getId(),student.getAcademyId());
						if(aeb!=null)
						{
							aeblist.add(aeb);
						}
					}
				}
			}
		}
		if(aeblist!=null && aeblist.size()>0)
		{
			result.setList(this.feePaymentDetailBiz.findFpdlListByPageForAcademyYearRebate(feePaymentDetail, student,aeblist,result));
		}
		else
		{
			result.setList(null);
		}	
		return SUCCESS;
	}
	
	/**
	 * 获取年度院校返款学习中心列表
	 * @return
	 * @throws Exception
	 */
	@Action(value = "find_list_branch_by_year_fpdIds_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String branchList() throws Exception 
	{	
		feePaymentDetail.setStatus(Constants.PAYMENT_STATUS_YI_TIAN_FAN_KUAN_DAN);		
		student.setStartStatusId(Constants.STU_CALL_STATUS_YI_DAO_MING);
		List<AcademyEnrollBatch> aeblist=new ArrayList<AcademyEnrollBatch>();
		//通过年份查询全局批次   通过全局批次和院校查询该院校下的招生批次
		if(year>0)
		{
			glblist=this.globalEnrollBatchBiz.findGlobalEnrollBatchByYear(year);
			if(glblist!=null && glblist.size()>0)
			{
				AcademyEnrollBatch aeb=null;
				for(GlobalEnrollBatch geb:glblist)
				{
					if(geb!=null)
					{
						aeb=this.academyEnrollBatchBiz.findNonFinishedAcademyBatchByGlobalBatchIdAndAcademyId(geb.getId(),student.getAcademyId());
						if(aeb!=null)
						{
							aeblist.add(aeb);
						}
					}
				}
			}
		}
		if(aeblist!=null && aeblist.size()>0)
		{
			branchList=this.feePaymentDetailBiz.findBranchListByFpdStuAebList(feePaymentDetail, student,aeblist);
		}
		return SUCCESS;
	}
	
	/**
	 * 统计年度院校返款追加金额
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_add_money_for_academy_year_rebate_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String AddMoneyForAcademyYear() throws Exception 
	{	
		feePaymentDetail.setStatus(Constants.PAYMENT_STATUS_YI_TIAN_FAN_KUAN_DAN);		
		student.setStartStatusId(Constants.STU_CALL_STATUS_YI_DAO_MING);
		List<AcademyEnrollBatch> aeblist=new ArrayList<AcademyEnrollBatch>();
		//通过年份查询全局批次   通过全局批次和院校查询该院校下的招生批次
		if(year>0)
		{
			glblist=this.globalEnrollBatchBiz.findGlobalEnrollBatchByYear(year);
			if(glblist!=null && glblist.size()>0)
			{
				AcademyEnrollBatch aeb=null;
				for(GlobalEnrollBatch geb:glblist)
				{
					if(geb!=null)
					{
						aeb=this.academyEnrollBatchBiz.findNonFinishedAcademyBatchByGlobalBatchIdAndAcademyId(geb.getId(),student.getAcademyId());
						if(aeb!=null)
						{
							aeblist.add(aeb);
						}
					}
				}
			}
		}
		if(aeblist!=null && aeblist.size()>0)
		{
			allAddMoney=this.feePaymentDetailBiz.updateFpdForAcademyYearRebateCountAllAddMoney(feePaymentDetail, student,aeblist).toString();
		}
		return SUCCESS;
	}

	public PageResult<FeePaymentDetail> getResult() {
		return result;
	}

	public void setResult(PageResult<FeePaymentDetail> result) {
		this.result = result;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public FeePaymentDetail getFeePaymentDetail() {
		return feePaymentDetail;
	}

	public void setFeePaymentDetail(FeePaymentDetail feePaymentDetail) {
		this.feePaymentDetail = feePaymentDetail;
	}

	public String getFpdIds() {
		return fpdIds;
	}

	public void setFpdIds(String fpdIds) {
		this.fpdIds = fpdIds;
	}

	public List<Branch> getBranchList() {
		return branchList;
	}

	public void setBranchList(List<Branch> branchList) {
		this.branchList = branchList;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getAllAddMoney() {
		//转换金额
		//allAddMoney=MoneyUtil.formatMoney(allAddMoney);
		return allAddMoney;
	}

	public void setAllAddMoney(String allAddMoney) {
		this.allAddMoney = allAddMoney;
	}

	
}
