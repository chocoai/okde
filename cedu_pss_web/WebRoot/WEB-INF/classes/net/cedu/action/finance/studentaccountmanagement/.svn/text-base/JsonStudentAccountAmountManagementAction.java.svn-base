package net.cedu.action.finance.studentaccountmanagement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.biz.finance.StudentAccountAmountManagementBiz;
import net.cedu.biz.finance.StudentAccountManagementBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.enums.CodeEnum;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.StudentAccountAmountManagement;
import net.cedu.entity.finance.StudentAccountManagement;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * json学生账户金额变动管理
 * @author gaolei
 *
 */
@ParentPackage("json-default")
public class JsonStudentAccountAmountManagementAction extends BaseAction {

	
	@Autowired
	private StudentAccountAmountManagementBiz studentaccountamountmanagementbiz;   //学生账户金额管理biz
	@Autowired 
	private StudentAccountManagementBiz studentaccountmanagementbiz;               //学生账户biz
	
	@Autowired 
	private FeeSubjectBiz feesubjectBiz;               //费用科目biz
	
	@Autowired
	private BuildCodeBiz buildCodeBiz;
	
	// 分页结果
	private PageResult<StudentAccountAmountManagement> result = new PageResult<StudentAccountAmountManagement>();
	private StudentAccountAmountManagement studentaccountamountmanagement;         //学生账户金额管理实体
	private int accountId;               //学生账户Id
	private BigDecimal accountRecharge;  //充值金额 
	private String description;          //学生身份证
	private int types;                   //操作类型 0--充值 1--消费
	private int feeSubjectId;            //费用科目Id
	private List<FeeSubject> fslist=new ArrayList<FeeSubject>();
	
	private int studentId;//学生Id
	
	/**
	 * 批量创建学生账户
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "studentaccountrecharge", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String AddStudentAccounts() throws Exception {
	
		//账户充值
		studentaccountamountmanagement =new StudentAccountAmountManagement();
		studentaccountamountmanagement.setAccountId(accountId);
		studentaccountamountmanagement.setAccountMoney(accountRecharge);
		studentaccountamountmanagement.setFeeSubject(feeSubjectId);
		studentaccountamountmanagement.setTypes(types);
		studentaccountamountmanagement.setDescription(description);
		studentaccountamountmanagement.setCreatorId(super.getUser().getUserId());
		studentaccountamountmanagement.setCreatedTime(new Date());
		
		FeePayment fp=new FeePayment();
		fp.setRechargeAmount(accountRecharge.doubleValue());
		fp.setTotalAmount(accountRecharge.doubleValue());
		
		//studentaccountamountmanagementbiz.addStudentAccountAmountManagement(studentaccountamountmanagement);
		//修改账户余额
		StudentAccountManagement sam=studentaccountmanagementbiz.findStudentAccountManagementById(accountId);
		accountRecharge=new BigDecimal(accountRecharge.toString()).add(new BigDecimal(sam.getAccountBalance().toString()));
		sam.setAccountBalance(accountRecharge);
		//studentaccountmanagementbiz.updateStudentAccountManagementById(sam);
		//增加学生预缴费
		
		fp.setStudentId(studentId);
		fp.setCode(buildCodeBiz.getCodes(CodeEnum.Payment.getCode(),
						CodeEnum.PaymentPrefix.getCode()));
		fp.setStatus(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);
		fp.setPamentType(Constants.FEE_PAYMENT_TYPE_PRE_BILLING);
		fp.setNote(description);
		fp.setDeleteFlag(Constants.DELETE_FALSE);
		fp.setCreatorId(super.getUser().getUserId());
		fp.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd"));
		fp.setUpdaterId(super.getUser().getUserId());
		fp.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
		
		//fp.setCommonBatch(this.feePaymentDao.getCommonBatch(feePayment
			//	.getStudentId()));
		this.studentaccountamountmanagementbiz.addBatchStuAccAmManag(studentaccountamountmanagement, sam, fp);
		return SUCCESS;
	}

	/**
	 * 查询学生账户金额变动(数量)
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "countstudentaccountamountmanagement", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String CountStudentAccountAmountManagement() throws Exception {
		
		try 
		{	
			StudentAccountManagement sam=studentaccountmanagementbiz.findStudentAccountManagementByStudentId(accountId);
			if(sam!=null)
			{
				accountId=sam.getId();
			}
			result.setRecordCount(studentaccountamountmanagementbiz.countStudentAccountAmountManagementByAccountId(accountId,types==Constants.Fee_STATUS_TRUE?Constants.Fee_STATUS_FALSE:Constants.Fee_STATUS_TRUE,result));
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 查询学生账户金额变动(集合)
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "liststudentaccountamountmanagement", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String ListStudentAccountAmountManagement() throws Exception {
		
		try 
		{
			StudentAccountManagement sam=studentaccountmanagementbiz.findStudentAccountManagementByStudentId(accountId);
			if(sam!=null)
			{
				accountId=sam.getId();
			}
			result.setList(studentaccountamountmanagementbiz.findStudentAccountAmountManagementByAccountId(accountId,types==Constants.Fee_STATUS_TRUE?Constants.Fee_STATUS_FALSE:Constants.Fee_STATUS_TRUE,result));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	/**
	 * 查询费用科目
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "listfeesubjects", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String ListFeeSubjects() throws Exception {
		
		try {
				fslist=feesubjectBiz.findAllFeeSubjectByDeleteFlag();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public BigDecimal getAccountRecharge() {
		return accountRecharge;
	}

	public void setAccountRecharge(BigDecimal accountRecharge) {
		this.accountRecharge = accountRecharge;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTypes() {
		return types;
	}

	public void setTypes(int types) {
		this.types = types;
	}

	public PageResult<StudentAccountAmountManagement> getResult() {
		return result;
	}

	public void setResult(PageResult<StudentAccountAmountManagement> result) {
		this.result = result;
	}

	public StudentAccountAmountManagement getStudentaccountamountmanagement() {
		return studentaccountamountmanagement;
	}

	public void setStudentaccountamountmanagement(
			StudentAccountAmountManagement studentaccountamountmanagement) {
		this.studentaccountamountmanagement = studentaccountamountmanagement;
	}

	public List<FeeSubject> getFslist() {
		return fslist;
	}

	public void setFslist(List<FeeSubject> fslist) {
		this.fslist = fslist;
	}

	public int getFeeSubjectId() {
		return feeSubjectId;
	}

	public void setFeeSubjectId(int feeSubjectId) {
		this.feeSubjectId = feeSubjectId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	 
	
	
	

}
