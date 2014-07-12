package net.cedu.action.enrollment;

import java.math.BigDecimal;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyBatchBranchBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.enrollment.DiscountApplicationBiz;
import net.cedu.biz.enrollment.StudentDiscountPolicyBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.enums.CodeEnum;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.basesetting.GlobalEnrollBatch;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.DiscountApplication;
import net.cedu.entity.enrollment.StudentDiscountPolicy;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 学生优惠申请
 * @author lixiaojun
 *
 */
@ParentPackage("json-default")
public class JsonListStudentDiscountApplicationAction extends BaseAction
{
	// 分页结果
	private PageResult<Student> result = new PageResult<Student>();
	
	@Autowired
	private StudentBiz studentBiz;//学生业务层接口
	private Student student=new Student();//学生实体
	
	@Autowired
	private AcademyLevelBiz academyLevelBiz;//院校层次关系
	
	
	//***************级联*********************//
	@Autowired
	private AcademyBatchBranchBiz academyBztchBranchBiz;//院校授权中心
	private List<GlobalEnrollBatch> globalBatchList=new ArrayList<GlobalEnrollBatch>();//全局批次集合
	private List<Academy> academyList=new ArrayList<Academy>();//院校集合
	private int branchId;//学习中心Id
	private int globalBatchId;//全局批次Id
	
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;//院校招生批次 业务层
	private AcademyEnrollBatch batch=new AcademyEnrollBatch();//院校招生批次
	private int academyId;//院校Id
	
	
	//***********************优惠卷相关*************************//
	
	@Autowired
	private StudentDiscountPolicyBiz studentDiscountPolicyBiz;//优惠标准业务层接口
	private int studentId;//学生Id
	private List<StudentDiscountPolicy> discountPolicyList=new ArrayList<StudentDiscountPolicy>();//优惠标准集合
	//添加优惠卷
	@Autowired
	private DiscountApplicationBiz discountApplicationBiz;//优惠卷业务层接口
	private List<DiscountApplication> discountApplicationList=new ArrayList<DiscountApplication>();//优惠卷集合
	private int usageFlag;//优惠卷使用状态
	@Autowired
	private BuildCodeBiz buildCodeBiz;//生成code业务层接口
	private int discountPolicyId;//优惠标准Id
	private String remark;//优惠备注
	private boolean isfail=false;//返回参数
	private boolean isfailcount=false;//返回参数
	
	//删除参数
	private int discountApplicationId;//优惠卷Id
	
	//审批参数
	private int auditing;//审批结果
	
	/**
	 * 获取学生数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_student_discount_application_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"result.*"
	}) })
	public String applicationCount() throws Exception {
		//转换为基础的层次id
		if(student.getLevelId()!=0)
		{
			student.setLevelId(this.academyLevelBiz.findById(student.getLevelId()).getLevelId());
		}
		student.setStartStatusId(Constants.STU_CALL_STATUS_GENG_JIN_ZHONG);
		student.setEndStatusId(Constants.STU_CALL_STATUS_YI_XIU_XUE);
		student.setMonitorStatus(-1);
		student.setTuitionStatus(-1);
		result.setRecordCount(this.studentBiz.findStudentsPageCount(student, result));
		return SUCCESS;
	}
	
	/**
	 * 获取学生列表
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_student_discount_application_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"result.*"
	}) })
	public String applicationList() throws Exception {
		//转换为基础的层次id
		if(student.getLevelId()!=0)
		{
			student.setLevelId(this.academyLevelBiz.findById(student.getLevelId()).getLevelId());
		}
		student.setStartStatusId(Constants.STU_CALL_STATUS_GENG_JIN_ZHONG);
		student.setEndStatusId(Constants.STU_CALL_STATUS_YI_XIU_XUE);
		student.setMonitorStatus(-1);
		student.setTuitionStatus(-1);
		result.setList(this.studentBiz.findStudentsPageList(student, result));
		return SUCCESS;
	}
	
	
	/**
	 * 学习中心与全局批次级联(通过授权中心级联)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "cascade_global_batch_branch_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"globalBatchList.*,branchId"
	}) })
	public String cascadeGlobalBatch() throws Exception {
		globalBatchList=this.academyBztchBranchBiz.findGlobalEnrollBatchByBranchId(branchId);
		if(globalBatchList!=null && globalBatchList.size()>0){
			Collections.sort(globalBatchList, new Comparator() {
				public int compare(Object arg0, Object arg1) {
					Comparator cmp = Collator
							.getInstance(java.util.Locale.CHINA);
					String name1 = ((GlobalEnrollBatch) arg0).getBatch();
					String name2 = ((GlobalEnrollBatch) arg1).getBatch();
					return cmp.compare(name2, name1);
				}
			});
		}
		return SUCCESS;
	}
	
	/**
	 * 院校与学习中心、全局批次级联(通过授权中心级联)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "cascade_branch_global_batch_academy_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"academyList.*,branchId,globalBatchId",
			"excludeProperties",
			"academyList.*.account,academyList.*.address,academyList.*.auditStatus," +
			"academyList.*.auditedDate,academyList.*.auditer,academyList.*.code," +
			"academyList.*.complete,academyList.*.contractEndtime,academyList.*.createdTime," +
			"academyList.*.creatorId,academyList.*.deleteFlag,academyList.*.expectTarget," +
			"academyList.*.foundedYear,academyList.*.interfaceSettingStatus,academyList.*.introduction," +
			"academyList.*.isForceFeePolicy,academyList.*.pictureUrl,academyList.*.projectManagerId," +
			"academyList.*.projectManagerName,academyList.*.rebatesFeesubject,academyList.*.scale," +
			"academyList.*.shortName,academyList.*.target,academyList.*.telephone," +
			"academyList.*.updatedTime,academyList.*.updaterId,academyList.*.usingStatus," +
			"academyList.*.website"
	}) })
	public String cascadeAcademy() throws Exception {
		academyList=this.academyBztchBranchBiz.findAcademyByBranchIdAndGlobalBatchId(branchId, globalBatchId);
		return SUCCESS;
	}
	
	/**
	 * 招生批次与院校、全局批次级联(通过院校招生批次级联)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "cascade_global_batch_academy_batch_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"batch.*,globalBatchId,academyId",
			"excludeProperties",
			"batch.*.academyLevelList,batch.*.createdTime,batch.*.creatorId," +
			"batch.*.deleteFlag,batch.*.globalEnrollBatch,batch.*.isEnable," +
			"batch.*.registerName,batch.*.subAcademyEnrollBatchlist,batch.*.updatedTime," +
			"batch.*.updaterId"
	}) })
	public String cascadeEnrollBatch() throws Exception {
		batch=this.academyEnrollBatchBiz.findNonFinishedAcademyBatchByGlobalBatchIdAndAcademyId(globalBatchId,academyId);
		return SUCCESS;
	}
	
	/**
	 * 招生批次与院校、全局批次级联(通过院校招生批次级联)只查询启用的，录入学生用到，其他地方用上面的
	 * @return
	 * @throws Exception
	 */
	@Action(value = "cascade_global_batch_academy_qiyong_batch_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"batch.*,globalBatchId,academyId",
			"excludeProperties",
			"batch.*.academyLevelList,batch.*.createdTime,batch.*.creatorId," +
			"batch.*.deleteFlag,batch.*.globalEnrollBatch,batch.*.isEnable," +
			"batch.*.registerName,batch.*.subAcademyEnrollBatchlist,batch.*.updatedTime," +
			"batch.*.updaterId"
	}) })
	public String cascadeQiYongEnrollBatch() throws Exception {
		batch=this.academyEnrollBatchBiz.findQiYongAcademyBatchByGlobalBatchIdAndAcademyId(globalBatchId,academyId);
		return SUCCESS;
	}

	/**
	 * 查询可以申请的单个学生的所有优惠政策
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_all_student_apply_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String studentDiscount() throws Exception {
		discountPolicyList=this.studentDiscountPolicyBiz.findStudentDiscountPolicyListByStudentId(studentId,Constants.POLICY_APPLICATION_NEEDED_TRUE);
		return SUCCESS;
	}
	
	/**
	 * 查询可以申请的单个学生优惠政策（学生报名后，显示中心自定义的报名政策，无需审批）
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_branch_discount_student_apply_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String branchstudentDiscount() throws Exception {
		discountPolicyList=this.studentDiscountPolicyBiz.findStudentDiscountPolicyListByStudentId(studentId,Constants.STUDENT_DISCOUNT_CUSTOM_BRANCH);
		return SUCCESS;
	}
	
	/**
	 * 查询可以申请的单个学生的所有优惠政策(学生跟进表中，只有中心，无院校等其他匹配项)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_all_student_apply_unadademy_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String studentunacademyDiscount() throws Exception {
		discountPolicyList=this.studentDiscountPolicyBiz.findStudentDiscountPolicyListByStudentIdAndBranch(studentId,Constants.POLICY_APPLICATION_NEEDED_TRUE);
		return SUCCESS;
	}
	
	/**
	 * 添加单个学生的优惠卷
	 * @return
	 * @throws Exception
	 */
	@Action(value = "add_student_application_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String addStudentApplication() throws Exception {
		if(discountPolicyId!=0 && studentId!=0)
		{
			//判断是否添加重复
			int counts=this.discountApplicationBiz.findDiscountApplicationcountsByStudentIdAndDiscountPolicyId(studentId, discountPolicyId);
			if(counts>0)
			{
				isfailcount=true;
			}
			else
			{
				StudentDiscountPolicy studentDiscountPolicy=this.studentDiscountPolicyBiz.findStudentDiscountPolicyById(discountPolicyId);
				if(studentDiscountPolicy!=null)
				{
					DiscountApplication discountApplication=new DiscountApplication();
					discountApplication.setAuditStatus(Constants.AUDIT_STATUS_FALSE);
					discountApplication.setCode(buildCodeBiz.getCodes(CodeEnum.DiscountApplicationTB.getCode(),CodeEnum.DiscountApplicationPrefix.getCode()));
					discountApplication.setEndTime(studentDiscountPolicy.getUseEndDate());
					discountApplication.setNote(remark);
					discountApplication.setPolicyStandardId(discountPolicyId);
					discountApplication.setStartTime(studentDiscountPolicy.getUseBeginDate());
					discountApplication.setStudentId(studentId);
					discountApplication.setUsageFlag(Constants.POLICY_USING_STATUS_APPLY);
					//新增
					discountApplication.setDiscountWay(studentDiscountPolicy.getDiscountWayId());
					//discountApplication.setMoney(money)
					discountApplication.setCreateTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
					//折算金额    按申请时间是多少优惠金额就是多少优惠金额
					long timeLong = 0;
					int dayNum = 0;
					String delta="0";//渐变差额
					String nowmoney="0";//当前优惠
					if(studentDiscountPolicy.getMutable()==Constants.MONEY_FORM_GRADIENT)
					{
						timeLong =DateUtil.getNowTimestamp("yyyy-MM-dd").getTime()-studentDiscountPolicy.getActivityBeginDate().getTime();
						dayNum = (int) (((timeLong / 1000) / 3600) / 24);
						delta=(new BigDecimal(dayNum).multiply(new BigDecimal(studentDiscountPolicy.getDelta()))).toString();
					}
					if(studentDiscountPolicy.getDiscountWayId()==Constants.MONEY_FORM_RATIO)
					{
						nowmoney=(studentDiscountPolicy.getMoney().add(new BigDecimal(delta))).toString();
					}
					else if(studentDiscountPolicy.getDiscountWayId()==Constants.MONEY_FORM_AMOUNT)
					{
						nowmoney=(studentDiscountPolicy.getMoney().add(new BigDecimal(delta))).toString();
					}
					if((new BigDecimal(nowmoney).compareTo(new BigDecimal(0)))>0)//和0比较大于返回1小于返回-1等于返回0
					{
						discountApplication.setMoney(new BigDecimal(nowmoney));
					}
					else
					{
						discountApplication.setMoney(new BigDecimal(0));
					}
					//12.30重构
					if(studentDiscountPolicy.getIsApplicationNeeded()==Constants.STUDENT_DISCOUNT_AUDIT_BRANCH)//中心审批
					{
						discountApplication.setAuditee(Constants.DISCOUNT_APPLICATION_AUDIT_BRANCH);
					}	
					//中心自定义政策的添加
					if(studentDiscountPolicy.getIsApplicationNeeded()==Constants.STUDENT_DISCOUNT_CUSTOM_BRANCH)
					{
						discountApplication.setAuditStatus(Constants.AUDIT_STATUS_TRUE);
						discountApplication.setUsageFlag(Constants.POLICY_USING_STATUS_FALSE);
					}
					
					isfail=this.discountApplicationBiz.addDiscountApplication(discountApplication);
				}
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 查询已经申请的单个学生的所有优惠政策
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_all_student_applied_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String appliedDiscount() throws Exception {
		discountApplicationList=this.discountApplicationBiz.findDiscountApplicationByStudentId(studentId,usageFlag);
		return SUCCESS;
	}
	
	/**
	 * 审批单个学生的优惠政策
	 * @return
	 * @throws Exception
	 */
	@Action(value = "auditing_student_application_apply_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String auditingDiscount() throws Exception {
		if(discountApplicationId!=0 && auditing!=0)
		{
			DiscountApplication discountApplication=this.discountApplicationBiz.findDiscountApplicationById(discountApplicationId);
			if(discountApplication!=null)
			{
				if(auditing==1)
				{
					discountApplication.setAuditStatus(Constants.AUDIT_STATUS_TRUE);
					discountApplication.setUsageFlag(Constants.POLICY_USING_STATUS_FALSE);
				}
				else
				{
					discountApplication.setAuditStatus(Constants.AUDIT_STATUS_FAIL);
				}
				isfail=this.discountApplicationBiz.updateDiscountApplication(discountApplication);
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 删除已经申请的单个学生的优惠政策
	 * @return
	 * @throws Exception
	 */
	@Action(value = "delete_student_application_apply_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String deleDiscount() throws Exception {
		if(discountApplicationId!=0)
		{
			isfail=this.discountApplicationBiz.deleteConfigDiscountApplicationById(discountApplicationId);
		}
		return SUCCESS;
	}
	
	/**
	 * 刷新并添加单个学生的所有不需要申请的优惠政策
	 * @return
	 * @throws Exception
	 */
	@Action(value = "refresh_student_application_unapply_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String refreshDiscount() throws Exception {
		List<StudentDiscountPolicy> dlAcademy=new ArrayList<StudentDiscountPolicy>();//优惠标准集合(有院校)
		List<StudentDiscountPolicy> dlUnAcademy=new ArrayList<StudentDiscountPolicy>();//优惠标准集合(无院校)
		
		List<DiscountApplication> applicationList=new ArrayList<DiscountApplication>();//优惠卷集合(待添加的)
		dlAcademy=this.studentDiscountPolicyBiz.findStudentDiscountPolicyListByStudentId(studentId, Constants.POLICY_APPLICATION_NEEDED_FALSE);
		dlUnAcademy=this.studentDiscountPolicyBiz.findStudentDiscountPolicyListByStudentIdAndBranch(studentId, Constants.POLICY_APPLICATION_NEEDED_FALSE);
		if(dlAcademy!=null && dlAcademy.size()>0)
		{
			for(StudentDiscountPolicy policy:dlAcademy)
			{
				if(policy!=null)
				{
					int counts=this.discountApplicationBiz.findDiscountApplicationcountsByStudentIdAndDiscountPolicyId(studentId, policy.getId());
					if(counts<=0)
					{					
						DiscountApplication discountApplication=new DiscountApplication();
						discountApplication.setAuditStatus(Constants.AUDIT_STATUS_TRUE);
						discountApplication.setCode(buildCodeBiz.getCodes(CodeEnum.DiscountApplicationTB.getCode(),CodeEnum.DiscountApplicationPrefix.getCode()));
						discountApplication.setEndTime(policy.getUseEndDate());
						discountApplication.setPolicyStandardId(policy.getId());
						discountApplication.setStartTime(policy.getUseBeginDate());
						discountApplication.setStudentId(studentId);
						discountApplication.setUsageFlag(Constants.POLICY_USING_STATUS_FALSE);
						
						//新增
						discountApplication.setDiscountWay(policy.getDiscountWayId());
						//discountApplication.setMoney(money)
						discountApplication.setCreateTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
						//折算金额    按申请时间是多少优惠金额就是多少优惠金额
						long timeLong = 0;
						int dayNum = 0;
						String delta="0";//渐变差额
						String nowmoney="0";//当前优惠
						if(policy.getMutable()==Constants.MONEY_FORM_GRADIENT)
						{
							timeLong =DateUtil.getNowTimestamp("yyyy-MM-dd").getTime()-policy.getActivityBeginDate().getTime();
							dayNum = (int) (((timeLong / 1000) / 3600) / 24);
							delta=(new BigDecimal(dayNum).multiply(new BigDecimal(policy.getDelta()))).toString();
						}
						if(policy.getDiscountWayId()==Constants.MONEY_FORM_RATIO)
						{
							nowmoney=(policy.getMoney().add(new BigDecimal(delta))).toString();
						}
						else if(policy.getDiscountWayId()==Constants.MONEY_FORM_AMOUNT)
						{
							nowmoney=(policy.getMoney().add(new BigDecimal(delta))).toString();
						}
						if((new BigDecimal(nowmoney).compareTo(new BigDecimal(0)))>0)//和0比较大于返回1小于返回-1等于返回0
						{
							discountApplication.setMoney(new BigDecimal(nowmoney));
						}
						else
						{
							discountApplication.setMoney(new BigDecimal(0));
						}
						//12.30重构
						if(policy.getIsApplicationNeeded()==Constants.STUDENT_DISCOUNT_AUDIT_BRANCH)//中心审批
						{
							discountApplication.setAuditee(Constants.DISCOUNT_APPLICATION_AUDIT_BRANCH);
						}	
						applicationList.add(discountApplication);
					}
				}
			}
		}
		if(dlUnAcademy!=null && dlUnAcademy.size()>0)
		{
			for(StudentDiscountPolicy policy:dlUnAcademy)
			{
				if(policy!=null)
				{
					int counts=this.discountApplicationBiz.findDiscountApplicationcountsByStudentIdAndDiscountPolicyId(studentId, policy.getId());
					if(counts<=0)
					{	
						DiscountApplication discountApplication=new DiscountApplication();
						discountApplication.setAuditStatus(Constants.AUDIT_STATUS_TRUE);
						discountApplication.setCode(buildCodeBiz.getCodes(CodeEnum.DiscountApplicationTB.getCode(),CodeEnum.DiscountApplicationPrefix.getCode()));
						discountApplication.setEndTime(policy.getUseEndDate());
						discountApplication.setPolicyStandardId(policy.getId());
						discountApplication.setStartTime(policy.getUseBeginDate());
						discountApplication.setStudentId(studentId);
						discountApplication.setUsageFlag(Constants.POLICY_USING_STATUS_FALSE);
						
						//新增
						discountApplication.setDiscountWay(policy.getDiscountWayId());
						//discountApplication.setMoney(money)
						discountApplication.setCreateTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
						//折算金额    按申请时间是多少优惠金额就是多少优惠金额
						long timeLong = 0;
						int dayNum = 0;
						String delta="0";//渐变差额
						String nowmoney="0";//当前优惠
						if(policy.getMutable()==Constants.MONEY_FORM_GRADIENT)
						{
							timeLong =DateUtil.getNowTimestamp("yyyy-MM-dd").getTime()-policy.getActivityBeginDate().getTime();
							dayNum = (int) (((timeLong / 1000) / 3600) / 24);
							delta=(new BigDecimal(dayNum).multiply(new BigDecimal(policy.getDelta()))).toString();
						}
						if(policy.getDiscountWayId()==Constants.MONEY_FORM_RATIO)
						{
							nowmoney=(policy.getMoney().add(new BigDecimal(delta))).toString();
						}
						else if(policy.getDiscountWayId()==Constants.MONEY_FORM_AMOUNT)
						{
							nowmoney=(policy.getMoney().add(new BigDecimal(delta))).toString();
						}
						if((new BigDecimal(nowmoney).compareTo(new BigDecimal(0)))>0)//和0比较大于返回1小于返回-1等于返回0
						{
							discountApplication.setMoney(new BigDecimal(nowmoney));
						}
						else
						{
							discountApplication.setMoney(new BigDecimal(0));
						}
						//12.30重构
						if(policy.getIsApplicationNeeded()==Constants.STUDENT_DISCOUNT_AUDIT_BRANCH)//中心审批
						{
							discountApplication.setAuditee(Constants.DISCOUNT_APPLICATION_AUDIT_BRANCH);
						}	
						applicationList.add(discountApplication);
					}
				}
			}
		}
		//批量添加优惠政策
		if(applicationList!=null && applicationList.size()>0)
		{
			isfail=this.discountApplicationBiz.addBatchDiscountApplication(applicationList);
		}
		else
		{
			isfail=true;
		}
		return SUCCESS;
	}
	
	public PageResult<Student> getResult() {
		return result;
	}

	public void setResult(PageResult<Student> result) {
		this.result = result;
	}

	public List<GlobalEnrollBatch> getGlobalBatchList() {
		return globalBatchList;
	}

	public void setGlobalBatchList(List<GlobalEnrollBatch> globalBatchList) {
		this.globalBatchList = globalBatchList;
	}

	public List<Academy> getAcademyList() {
		return academyList;
	}

	public void setAcademyList(List<Academy> academyList) {
		this.academyList = academyList;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getGlobalBatchId() {
		return globalBatchId;
	}

	public void setGlobalBatchId(int globalBatchId) {
		this.globalBatchId = globalBatchId;
	}

	public AcademyEnrollBatch getBatch() {
		return batch;
	}

	public void setBatch(AcademyEnrollBatch batch) {
		this.batch = batch;
	}

	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public List<StudentDiscountPolicy> getDiscountPolicyList() {
		return discountPolicyList;
	}

	public void setDiscountPolicyList(List<StudentDiscountPolicy> discountPolicyList) {
		this.discountPolicyList = discountPolicyList;
	}

	public int getDiscountPolicyId() {
		return discountPolicyId;
	}

	public void setDiscountPolicyId(int discountPolicyId) {
		this.discountPolicyId = discountPolicyId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public boolean isIsfail() {
		return isfail;
	}

	public void setIsfail(boolean isfail) {
		this.isfail = isfail;
	}

	public boolean isIsfailcount() {
		return isfailcount;
	}

	public void setIsfailcount(boolean isfailcount) {
		this.isfailcount = isfailcount;
	}

	public List<DiscountApplication> getDiscountApplicationList() {
		return discountApplicationList;
	}

	public void setDiscountApplicationList(
			List<DiscountApplication> discountApplicationList) {
		this.discountApplicationList = discountApplicationList;
	}

	public int getUsageFlag() {
		return usageFlag;
	}

	public void setUsageFlag(int usageFlag) {
		this.usageFlag = usageFlag;
	}

	public int getDiscountApplicationId() {
		return discountApplicationId;
	}

	public void setDiscountApplicationId(int discountApplicationId) {
		this.discountApplicationId = discountApplicationId;
	}

	public int getAuditing() {
		return auditing;
	}

	public void setAuditing(int auditing) {
		this.auditing = auditing;
	}
	
	
}
