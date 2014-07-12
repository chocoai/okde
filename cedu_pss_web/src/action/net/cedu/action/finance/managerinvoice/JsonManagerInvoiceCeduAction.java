package net.cedu.action.finance.managerinvoice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyBatchBranchBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.enrollment.AcademyMajorBiz;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.InvoiceManagementBiz;
import net.cedu.common.Constants;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.AcademyLevel;
import net.cedu.entity.enrollment.Major;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.InvoiceManagement;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * json收据授权
 * @author gaolei
 *
 */
@ParentPackage("json-default")
public class JsonManagerInvoiceCeduAction extends BaseAction {

	
	@Autowired
	private AcademyBiz academyBiz;                         //院校biz
	@Autowired
	private AcademyBatchBranchBiz academybatchbranchBiz;   //学习中心授权院校biz
	
	@Autowired
	private AcademyLevelBiz academyLevelBiz;               //院校层次biz
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;   //院校招生批次biz
	@Autowired
	private AcademyMajorBiz academyMajorBiz;               //院校专业biz
	@Autowired
	private StudentBiz studentBiz;                         //学生biz
	@Autowired
	private FeePaymentBiz feePaymentBiz;                   //缴费单biz
	@Autowired
	private InvoiceManagementBiz invoiceManagementBiz;     //发票biz
	
	

	// 院校集合
	private List<Academy> academysAcademies = new ArrayList<Academy>();
	// 批次集合
	private List<AcademyEnrollBatch> academyEnrollBatchs = new ArrayList<AcademyEnrollBatch>();
	// 层次集合
	private List<AcademyLevel> academyLevels = new ArrayList<AcademyLevel>();
	// 专业集合
	private List<Major> academyMajors = new ArrayList<Major>();
	// 缴费单集合
	private List<FeePayment> feePayments = new ArrayList<FeePayment>();
	
	// 发票集合
	private List<InvoiceManagement> invoiceManagements = new ArrayList<InvoiceManagement>();
	
	// 分页结果
	private PageResult<InvoiceManagement> result = new PageResult<InvoiceManagement>();
	

	
	// 学生集合
	private List<Student> studentlst = new ArrayList<Student>();//学生数据
	private int id;                                         //Id
	private int branchId;                                   //中心ID
	private int globalBatchId;                              //全局批次ID
	private int academyId;                                  //院校ID
	private int batchId;                                    //批次ID
	private int levelId;                                    //招生层次ID
	private int majorId;                                    //专业ID
	private String name;                                    //学生姓名
	private String invoiceIds;                              //发票Ids
	private int isPost;                                     //是否邮寄
	private String feePaymentIds;                           //缴费单Ids
	private String postalNo;                                //邮寄包号
	private String invoiceNo;                               //发票号
	private String feePaymentNo;                            //缴费单号
	private int isSign;                                     //是否签领
	
	private Student student;

	
	
	
	
	
	/**
	 * 学生集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "liststudents", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"studentlst.*,name,branchId,academyId,batchId,globalBatchId,levelId,majorId"
	}) })
	public String ListStudents() throws Exception {
		Student students=new Student();
		students.setName(name);
		students.setBranchId(branchId);
		students.setAcademyId(academyId);
		students.setEnrollmentBatchId(batchId);
		students.setGlobalBatch(globalBatchId);
		AcademyLevel academyLevel=academyLevelBiz.findById(levelId);
		if(academyLevel!=null)
		{
			students.setLevelId(academyLevel.getLevelId());
		}
		students.setMajorId(majorId);
		studentlst = studentBiz.findStudentsByParam(students);
		return SUCCESS;
	}
	

	/**
	 * 院校集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "listacademy", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String ListAcademy() throws Exception {
		academysAcademies = academybatchbranchBiz.findAcademyByBranchIdAndGlobalBatchId(branchId, globalBatchId);
		return SUCCESS;
	}

	/**
	 * 批次集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "listacademyenrollbatch", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String ListAcademyEnrollBatch() throws Exception {
		academyEnrollBatchs = academyEnrollBatchBiz.findByAcademyId(id);
		return SUCCESS;
	}

	/**
	 * 层次集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "listlevel", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String ListLevel() throws Exception {
		academyLevels = academyLevelBiz.findAcademyLevelNameBatchId(id);
		
		return SUCCESS;
	}

	/**
	 * 专业集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "listbasemajors", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String ListbaseMajor() throws Exception {
		academyMajors = academyMajorBiz.findMajorListByLevelId(id);
		return SUCCESS;
	}
	
	/**
	 * 缴费单集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "listfeepayment", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String ListFeePayment() throws Exception {
		try {
			feePayments = feePaymentBiz.findFeePaymentByStudentId(id,Constants.Fee_STATUS_TRUE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	
	
	
	/**
	 * 发票集合(分页)
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "listmanagementinvoicecedu", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"result.*,branchId,isPost"
			
	}) })
	public String ListManagementInvoiceCedu() throws Exception {
		result.setList(invoiceManagementBiz.findInvoiceManagementByBranchId(branchId,isPost, result)); 
		return SUCCESS;
	}
	

	
	/**
	 * 发票(分页数量)
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "countmanagementinvoicecedu", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"result.*,branchId,isPost"
	}) })
	public String CountManagementInvoiceCedu() throws Exception {
		result.setRecordCount(invoiceManagementBiz.countInvoiceManagementByBranchId(branchId,isPost, result)); 
		return SUCCESS;
	}
	
	
	
	/**
	 * 发票集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "listmanagementinvoicecedufalse", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"result.*,branchId,isPost"
	}) })
	public String ListManagementInvoiceCeduFalse() throws Exception {
		try {
			result.setList(invoiceManagementBiz.findInvoiceManagementByBranchId(branchId,isPost,result));
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		return SUCCESS;
	}
	
	/**
	 * 发票签领
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "updatemanagementinvoicesign", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String UpdateManagementInvoiceSign() throws Exception {
		
		InvoiceManagement im=invoiceManagementBiz.findInvoiceManagementById(id);
		if(im!=null)
		{
			im.setIsSign(Constants.IS_SIGN_TRUE);
			im.setUpdaterId(super.getUser().getUserId());
			im.setUpdatedTime(new Date());
			invoiceManagementBiz.updateInvoiceManagement(im);
		}
		
		
		return SUCCESS;
	}
	
	
	
	
	/**
	 * 邮寄单发票集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "listmanagementinvoicepost", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String ListManagementInvoiceCeduPost() throws Exception {
		result.setList(invoiceManagementBiz.findInvoiceManagementByInvoiceIds(invoiceIds, result));
		return SUCCESS;
	}
	
	
	
	/**
	 * (已)未签领发票集合(分页)
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "listmanagementinvoicecenterbyparams", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"student.*,result.*,branchId,postalNo,invoiceNo,feePaymentNo,isSign"
	}) })
	public String ListManagementInvoiceCenter() throws Exception {
		try{
			// 层次
			if (student.getLevelId() != 0) {
				AcademyLevel academyLevel = academyLevelBiz.findById(student
						.getLevelId());
				student.setLevelId(academyLevel.getLevelId());
			}
			result.setList(invoiceManagementBiz.findInvoiceManagementByParams(branchId,studentBiz.findStudentIdsByStudentParams(student), postalNo, invoiceNo, feePaymentNo, isSign, result)); 
			
		}catch(Exception e){
			e.printStackTrace();
		}

		return SUCCESS;
	}
	

	
	/**
	 * (已)未签领发票(分页数量)
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "countmanagementinvoicecenterbyparams", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"student.*,result.*,branchId,postalNo,invoiceNo,feePaymentNo,isSign"
	}) })
	public String CountManagementInvoiceCenter() throws Exception {
		// 层次
		if (student.getLevelId() != 0) {
			AcademyLevel academyLevel = academyLevelBiz.findById(student
					.getLevelId());
			student.setLevelId(academyLevel.getLevelId());
		}
		result.setRecordCount(invoiceManagementBiz.countInvoiceManagementByParams(branchId,studentBiz.findStudentIdsByStudentParams(student), postalNo, invoiceNo, feePaymentNo, isSign, result)); 
		return SUCCESS;
	}
	
	public List<Academy> getAcademysAcademies() {
		return academysAcademies;
	}

	public void setAcademysAcademies(List<Academy> academysAcademies) {
		this.academysAcademies = academysAcademies;
	}

	public List<AcademyEnrollBatch> getAcademyEnrollBatchs() {
		return academyEnrollBatchs;
	}

	public void setAcademyEnrollBatchs(List<AcademyEnrollBatch> academyEnrollBatchs) {
		this.academyEnrollBatchs = academyEnrollBatchs;
	}

	public List<AcademyLevel> getAcademyLevels() {
		return academyLevels;
	}

	public void setAcademyLevels(List<AcademyLevel> academyLevels) {
		this.academyLevels = academyLevels;
	}
	
	public List<Major> getAcademyMajors() {
		return academyMajors;
	}

	public void setAcademyMajors(List<Major> academyMajors) {
		this.academyMajors = academyMajors;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public int getMajorId() {
		return majorId;
	}

	public void setMajorId(int majorId) {
		this.majorId = majorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Student> getStudentlst() {
		return studentlst;
	}

	public void setStudentlst(List<Student> studentlst) {
		this.studentlst = studentlst;
	}


	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	
	public List<FeePayment> getFeePayments() {
		return feePayments;
	}
	
	public void setFeePayments(List<FeePayment> feePayments) {
		this.feePayments = feePayments;
	}


	public List<InvoiceManagement> getInvoiceManagements() {
		return invoiceManagements;
	}

	public void setInvoiceManagements(List<InvoiceManagement> invoiceManagements) {
		this.invoiceManagements = invoiceManagements;
	}


	public PageResult<InvoiceManagement> getResult() {
		return result;
	}


	public void setResult(PageResult<InvoiceManagement> result) {
		this.result = result;
	}


	public String getInvoiceIds() {
		return invoiceIds;
	}


	public void setInvoiceIds(String invoiceIds) {
		this.invoiceIds = invoiceIds;
	}


	public int getIsPost() {
		return isPost;
	}


	public void setIsPost(int isPost) {
		this.isPost = isPost;
	}


	public String getFeePaymentIds() {
		return feePaymentIds;
	}


	public void setFeePaymentIds(String feePaymentIds) {
		this.feePaymentIds = feePaymentIds;
	}


	public String getPostalNo() {
		return postalNo;
	}


	public void setPostalNo(String postalNo) {
		this.postalNo = postalNo;
	}


	public String getInvoiceNo() {
		return invoiceNo;
	}


	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}


	public String getFeePaymentNo() {
		return feePaymentNo;
	}


	public void setFeePaymentNo(String feePaymentNo) {
		this.feePaymentNo = feePaymentNo;
	}


	public int getIsSign() {
		return isSign;
	}


	public void setIsSign(int isSign) {
		this.isSign = isSign;
	}




	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}


	public int getGlobalBatchId() {
		return globalBatchId;
	}


	public void setGlobalBatchId(int globalBatchId) {
		this.globalBatchId = globalBatchId;
	}



	
}
