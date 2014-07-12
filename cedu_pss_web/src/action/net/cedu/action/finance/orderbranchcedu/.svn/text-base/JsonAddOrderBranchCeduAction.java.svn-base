package net.cedu.action.finance.orderbranchcedu;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.OrderBranchCeduBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.enums.CodeEnum;
import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.OrderBranchCedu;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 汇款总部(中心)_添加
 * @author lixiaojun
 *
 */
@ParentPackage("json-default")
public class JsonAddOrderBranchCeduAction extends BaseAction
{
	// 分页结果
	private PageResult<FeePaymentDetail> result = new PageResult<FeePaymentDetail>();
	
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;//缴费单明细_业务层接口
	
	//分页参数
	private int branchId;//学习中心Id
	
	//统计选择的缴费单明细
	@Autowired
	private StudentBiz studentBiz;//学生_业务层接口
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;//费用科目业务层接口
	@Autowired
	private AcademyBiz academyBiz;// 院校_业务接口
	@Autowired
	private BranchBiz branchBiz; // 学习中心_业务接口
	@Autowired
	private AcademyEnrollBatchBiz academyenrollbatchBiz;// 院校招生批次 _业务接口
	
	private Student student=new Student();//学生实体
	private List<FeePaymentDetail> feepdList=new ArrayList<FeePaymentDetail>();//缴费单明细集合
	private String feePaymentDetailIds;//缴费单明细ids字符串集合
	private String playAcademyMoney;//打款院校的钱数
	
	//*****************************添加汇款单************************************//
	@Autowired
	private OrderBranchCeduBiz orderBranchCeduBiz;//汇款单_业务层接口
	@Autowired
	private FeePaymentBiz feePaymentBiz;//缴费单_业务层接口
	private OrderBranchCedu orderBranchCedu=new OrderBranchCedu();//打款单实体
	private String feepaymentIds;//缴费单明细ids集合
	private boolean isback=false;//返回参数
	private int indexcount;//判断重复添加问题
	private boolean replayadd=false;//
	
	@Autowired
	private BuildCodeBiz buildCodeBiz;//生成编号_业务层接口
	
	
	/**
	 * 获取缴费单明细数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_feepaymentdetail_orderbranchcedu_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String playmoneyCount() throws Exception 
	{
		FeePaymentDetail feePaymentDetail=new FeePaymentDetail();
		feePaymentDetail.setStatus(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);
		FeePayment feePayment=new FeePayment();
		feePayment.setFeeWayId(Constants.PAYMENT_METHOD_XIAN_JIN_HUI_ZONG_BU);
		
		//Student student=new Student();
		student.setBranchId(branchId);
		
		result.setRecordCount(this.feePaymentDetailBiz.findFeePaymentDetailCountByPage(feePaymentDetail, feePayment, student, result));
		return SUCCESS;
	}
	
	/**
	 * 获取缴费单明细列表
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_feepaymentdetail_orderbranchcedu_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String playmoneyList() throws Exception 
	{	
		FeePaymentDetail feePaymentDetail=new FeePaymentDetail();
		feePaymentDetail.setStatus(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);
		FeePayment feePayment=new FeePayment();
		feePayment.setFeeWayId(Constants.PAYMENT_METHOD_XIAN_JIN_HUI_ZONG_BU);
		
		
		student.setBranchId(branchId);
		
		result.setList(this.feePaymentDetailBiz.findFeePaymentDetailListByPage(feePaymentDetail, feePayment, student, result));
		return SUCCESS;
	}
	
	/**
	 * 统计选择的缴费单明细
	 * @return
	 * @throws Exception
	 */
	@Action(value = "feepaymentdetail_count_all_orderbranchcedu_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String playmoneyallList() throws Exception 
	{	
		if(feePaymentDetailIds!=null && !feePaymentDetailIds.equals(""))
		{
			String[] ids=feePaymentDetailIds.split(",");
			for(int i=0;i<ids.length;i++)
			{
				boolean isfail=false;
				FeePaymentDetail feepd=this.feePaymentDetailBiz.findById(Integer.valueOf(ids[i]));
				if(feepd!=null)
				{
					//设置招生批次id
					Student student=this.studentBiz.findStudentById(feepd.getStudentId());
					if(null!=student)
					{
						//批次
						feepd.setFeeWayId(student.getEnrollmentBatchId());
						//院校
						feepd.setRebateWay(student.getAcademyId());
						
						//汇款金额
						//基数
						BigDecimal jishu=new BigDecimal(1);
						if((new BigDecimal(feepd.getAmountPaied()).add(new BigDecimal(feepd.getRechargeAmount()))).compareTo(new BigDecimal(0))!=0)
						{
							jishu=(new BigDecimal(feepd.getAmountPaied()).add(new BigDecimal(feepd.getRechargeAmount())).subtract(new BigDecimal(feepd.getRefundAmount()))).divide(new BigDecimal(feepd.getAmountPaied()).add(new BigDecimal(feepd.getRechargeAmount())),12,BigDecimal.ROUND_HALF_UP);
						}
						DecimalFormat df = new DecimalFormat("0.00"); // 保留几位小数
						//feepd.setPayBranchCedu(Double.valueOf(new BigDecimal(feepd.getAmountPaied()).add(new BigDecimal(feepd.getRechargeAmount())).toString()));
						//feepd.setPayBranchCedu(((new BigDecimal(feepd.getAmountPaied()).add(new BigDecimal(feepd.getRechargeAmount()))).multiply(new BigDecimal(feepd.getAmountPaied()).add(new BigDecimal(feepd.getRechargeAmount())).subtract(new BigDecimal(feepd.getRefundAmount()))).divide(new BigDecimal(feepd.getAmountPaied()).add(new BigDecimal(feepd.getRechargeAmount())),2,BigDecimal.ROUND_HALF_UP)).doubleValue());
						feepd.setPayBranchCedu(Double.valueOf(df.format(((new BigDecimal(feepd.getAmountPaied()).add(new BigDecimal(feepd.getRechargeAmount()))).multiply(jishu)).doubleValue())));
					}
					//判断
					if(feepdList==null || feepdList.size()<=0)
					{				
						//feepdList.add(feepd);
					}
					else
					{
						for(FeePaymentDetail fp:feepdList)
						{
							if(fp.getFeeSubjectId()==feepd.getFeeSubjectId() && fp.getFeeWayId()==feepd.getFeeWayId() && fp.getRebateWay()==feepd.getRebateWay())
							{
								fp.setPayBranchCedu(Double.valueOf(new BigDecimal(fp.getPayBranchCedu()).add(new BigDecimal(feepd.getPayBranchCedu())).toString()));
								isfail=true;
								break;
							}
						}
					}
					//不相等则不添加
					if(!isfail)
					{
						feepdList.add(feepd);
					}
				}
			}
			if(feepdList!=null && feepdList.size()>0)
			{
				for(FeePaymentDetail feeObj:feepdList)
				{
					//费用科目
					if(feeObj.getFeeSubjectId()>0 && feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId())!=null)
					{
						feeObj.setPaymentSubjectName(feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId()).getName());
					}
					//学生相关
					if(feeObj.getStudentId()>0 && studentBiz.findStudentById(feeObj.getStudentId())!=null)
					{
						//院校
						if(studentBiz.findStudentById(feeObj.getStudentId()).getAcademyId()>0 && academyBiz.findAcademyById(studentBiz.findStudentById(feeObj.getStudentId()).getAcademyId())!=null)
						{
							feeObj.setSchoolName(academyBiz.findAcademyById(studentBiz.findStudentById(feeObj.getStudentId()).getAcademyId()).getName());
						}
						//学习中心
						if(studentBiz.findStudentById(feeObj.getStudentId()).getBranchId()>0 && branchBiz.findBranchById(studentBiz.findStudentById(feeObj.getStudentId()).getBranchId())!=null)
						{
							feeObj.setBranchName(branchBiz.findBranchById(studentBiz.findStudentById(feeObj.getStudentId()).getBranchId()).getName());
						}
						//批次
						if(studentBiz.findStudentById(feeObj.getStudentId()).getEnrollmentBatchId()>0 && academyenrollbatchBiz.findAcademyEnrollBatchById(studentBiz.findStudentById(feeObj.getStudentId()).getEnrollmentBatchId())!=null)
						{
							feeObj.setAcademyenrollbatchName(academyenrollbatchBiz.findAcademyEnrollBatchById(studentBiz.findStudentById(feeObj.getStudentId()).getEnrollmentBatchId()).getEnrollmentName());
						}
					}
				}
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 添加汇款单
	 * @return
	 * @throws Exception
	 */
	@Action(value = "add_order_branch_cedu_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String addplaymoney() throws Exception 
	{
		if(indexcount==1)
		{
			replayadd=true;
			return SUCCESS;
		}
		if(null!=orderBranchCedu && null!=feepaymentIds && !"".equals(feepaymentIds))
		{
			orderBranchCedu.setRemittanceDate(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
			orderBranchCedu.setCode(buildCodeBiz.getCodes(CodeEnum.OrderBranchCeduTB.getCode(),
					CodeEnum.OrderBranchCedu.getCode()));
			orderBranchCedu.setStatus(Constants.PAYMENT_STATUS_YI_TIAN_HUI_KUAN_DAN);
			orderBranchCedu.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
			orderBranchCedu.setDeleteFlag(Constants.DELETE_FALSE);
			orderBranchCedu.setCreatorId(super.getUser().getUserId());
			orderBranchCedu.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
			orderBranchCedu.setUpdaterId(super.getUser().getUserId());
			orderBranchCedu.setTypes(Constants.FALLBACK_TYPES_SUBMIT);
			 
			List<FeePaymentDetail> fpdlist=new ArrayList<FeePaymentDetail>();//缴费单明细集合
			List<FeePayment> fplist=new ArrayList<FeePayment>();//缴费单集合
			String[] ids=feepaymentIds.split(",");
			for(int i=0;i<ids.length;i++)
			{
				boolean isfail=false;
				FeePaymentDetail feepd=this.feePaymentDetailBiz.findById(Integer.valueOf(ids[i]));
				if(feepd!=null)
				{
					//汇款金额.multiply(new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())).subtract(new BigDecimal(feeObj.getRefundAmount()))).divide(new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())),2,BigDecimal.ROUND_HALF_UP)
					//基数
					BigDecimal jishu=new BigDecimal(1);
					if((new BigDecimal(feepd.getAmountPaied()).add(new BigDecimal(feepd.getRechargeAmount()))).compareTo(new BigDecimal(0))!=0)
					{
						jishu=(new BigDecimal(feepd.getAmountPaied()).add(new BigDecimal(feepd.getRechargeAmount())).subtract(new BigDecimal(feepd.getRefundAmount()))).divide(new BigDecimal(feepd.getAmountPaied()).add(new BigDecimal(feepd.getRechargeAmount())),12,BigDecimal.ROUND_HALF_UP);
					}
					DecimalFormat df = new DecimalFormat("0.00");
					//feepd.setPayBranchCedu(Double.valueOf(new BigDecimal(feepd.getAmountPaied()).add(new BigDecimal(feepd.getRechargeAmount())).toString()));
					//feepd.setPayBranchCedu(((new BigDecimal(feepd.getAmountPaied()).add(new BigDecimal(feepd.getRechargeAmount()))).multiply(new BigDecimal(feepd.getAmountPaied()).add(new BigDecimal(feepd.getRechargeAmount())).subtract(new BigDecimal(feepd.getRefundAmount()))).divide(new BigDecimal(feepd.getAmountPaied()).add(new BigDecimal(feepd.getRechargeAmount())),2,BigDecimal.ROUND_HALF_UP)).doubleValue());
					feepd.setPayBranchCedu(Double.valueOf(df.format(((new BigDecimal(feepd.getAmountPaied()).add(new BigDecimal(feepd.getRechargeAmount()))).multiply(jishu)).doubleValue())));
					//状态
					feepd.setStatus(Constants.PAYMENT_STATUS_YI_TIAN_HUI_KUAN_DAN);
					feepd.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
					feepd.setUpdaterId(super.getUser().getUserId());
					fpdlist.add(feepd);
				}
			}
			isback=this.orderBranchCeduBiz.addOrderBranchCeduUpdateFeePaymentDetails(orderBranchCedu, fpdlist);
		}
		return SUCCESS;
	}
	
	public PageResult<FeePaymentDetail> getResult() {
		return result;
	}

	public void setResult(PageResult<FeePaymentDetail> result) {
		this.result = result;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}


	public List<FeePaymentDetail> getFeepdList() {
		return feepdList;
	}

	public void setFeepdList(List<FeePaymentDetail> feepdList) {
		this.feepdList = feepdList;
	}


	public String getFeePaymentDetailIds() {
		return feePaymentDetailIds;
	}

	public void setFeePaymentDetailIds(String feePaymentDetailIds) {
		this.feePaymentDetailIds = feePaymentDetailIds;
	}

	public String getPlayAcademyMoney() {
		return playAcademyMoney;
	}

	public void setPlayAcademyMoney(String playAcademyMoney) {
		this.playAcademyMoney = playAcademyMoney;
	}

	public String getFeepaymentIds() {
		return feepaymentIds;
	}

	public void setFeepaymentIds(String feepaymentIds) {
		this.feepaymentIds = feepaymentIds;
	}

	public boolean isIsback() {
		return isback;
	}

	public void setIsback(boolean isback) {
		this.isback = isback;
	}

	public int getIndexcount() {
		return indexcount;
	}

	public void setIndexcount(int indexcount) {
		this.indexcount = indexcount;
	}

	public boolean isReplayadd() {
		return replayadd;
	}

	public void setReplayadd(boolean replayadd) {
		this.replayadd = replayadd;
	}

	public OrderBranchCedu getOrderBranchCedu() {
		return orderBranchCedu;
	}

	public void setOrderBranchCedu(OrderBranchCedu orderBranchCedu) {
		this.orderBranchCedu = orderBranchCedu;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
}