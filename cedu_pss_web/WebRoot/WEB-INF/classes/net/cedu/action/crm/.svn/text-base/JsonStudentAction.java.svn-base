package net.cedu.action.crm;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.crm.StudentEnrollmentSourceChangeLogBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.finance.PendingFeePaymentBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.enums.CodeEnum;
import net.cedu.common.enums.FeeSubjectEnum;
import net.cedu.common.string.ValidationUtil;
import net.cedu.entity.basesetting.BaseDict;
import net.cedu.entity.crm.FollowUp;
import net.cedu.entity.crm.OperationLog;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.finance.PendingFeePayment;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 学生列表Action
 * 
 * @author yangdongdong
 * 
 */
@ParentPackage("json-default")
public class JsonStudentAction extends BaseAction {

	@Autowired
	private StudentBiz studentBiz;
	@Autowired
	private BaseDictBiz baseDictBiz;
	@Autowired
	private StudentEnrollmentSourceChangeLogBiz studentEnrollmentSourceChangeLogBiz;

	/**
	 * 查询条件
	 */
	private Student student;

	private FollowUp followUp;

	// 分页结果
	private PageResult<Student> result = new PageResult<Student>();

	// 是否存在
	private boolean exist = false;

	// 错误代码
	private String errorCode;

	public JsonStudentAction() {
		super();
		super.setIl8nName("crm");
	}

	// *******************批量生成待缴费单**********************//
	@Autowired
	private PendingFeePaymentBiz pendingFeePaymentBiz;// 待缴费单业务接口
	private List<PendingFeePayment> pendinglist = new ArrayList<PendingFeePayment>();// 待缴费集合
	@Autowired
	private BuildCodeBiz buildCodeBiz;// code生成业务接口

	private String other[];

	// 参数
	private boolean isfail = false;
	private boolean isture = false;

	private boolean isback = false;
	
	//验证有没有身份证
	private boolean ishascertno=false;
	
	@Autowired
	private AcademyEnrollBatchBiz aebBiz;// 院校招生批次 _业务接口
	//验证招生批次是否已经结束
	private boolean isbatch=false;
	
	

	/**
	 * 增加学生，客服中心
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "add_student_cc", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String addStudentCC() throws Exception {
		// 学生状态预报名
		// student.setStatus(Constants.STU_CALL_STATUS_1);
		// 数据来源
		student.setStudentDataSource(Constants.STU_SOURCE_CC);
		// 证件类型
		// student.setCertType(Constants.CERTIFICATE_TYPE_ID);
		// //跟进次数,新建学生跟进次数为1
		// student.setFollowCount(1);
		student.setCreateDate(new Date());
		student.setCreatorId(super.getUser().getUserId());
		// 在线客服默认招生途径为社招
		student.setEnrollmentSource(Constants.WEB_STU_SOURCE_DEFAULT);
		
		// 锁定身份证
		if (student.getCertNo() != null
				&& !student.getCertNo().equals("")
				&& student.getCertNoLockingStatus() == 1) {
			student.setCertNoLockingStatus(1);
		}else{
			student.setCertNoLockingStatus(0);
		}

		if (followUp == null) {
			followUp = new FollowUp();
		}

		// 如果状态为已导入未分配，记录跟进时间
		Date date = new Date();
		if (student.getStatus() == Constants.STU_CALL_STATUS_YI_DAO_RU) {
			student.setPushDate(date);
			student.setPushId(super.getUser().getUserId());
			student.setLatestFollowUpDate(date);
			//student.setFollowUpId(super.getUser().getUserId());
			student.setUpFollowUpId(super.getUser().getUserId());
		}
		// 学生状态预报名(标记呼叫中心的跟进内容)
		followUp.setStatusId(Constants.STU_CALL_STATUS_YU_BAO_MING);
		// 跟进人
		followUp.setCreatorId(super.getUser().getUserId());
		// 跟进时间
		followUp.setCreatedTime(date);
		// 操作日志
		OperationLog operationLog = new OperationLog();
		operationLog.setContent(super
				.getText("crm.create.student.3", new Object[] {
						super.getUser().getFullName(), student.getName() }));// 内容
		operationLog.setIp(request.getRemoteHost());
		operationLog.setCreateId(super.getUser().getUserId());
		operationLog.setCreateTime(new Date());
		// 学生ID
		int sid = studentBiz.addStudent(student, followUp, operationLog);

		// 返回增加成功的学生ID
		if (student == null) {
			student = new Student();
		}
		student.setId(sid);
		return SUCCESS;
	}

	/**
	 * 增加学生，学习中心
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "add_student_lc", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String addStudentLC() throws Exception {
		if (studentBiz.isExistStudent(student) == null) {
			// 学生状态已分配
			student.setStatus(Constants.STU_CALL_STATUS_YI_FENG_PEI);
			// 锁定姓名,身份证
			// 锁定名称
			if (student.getName() != null && !student.getName().equals("")) {
				//student.setNameLockingStatus(1);
			}
			if (student.getCertNo() != null && !student.getCertNo().equals("")) {
				//student.setCertNoLockingStatus(1);
			}

			// 数据来源
			student.setStudentDataSource(Constants.STU_SOURCE_LC);
			// 是否呼叫
			student.setCallStatus(1);
			// 如果招生途径为社招
			if (student.getEnrollmentSource() != Constants.WEB_STU_SOURCE_DEFAULT) {
				if (student.getEnrollmentWayName() != null
						&& !student.getEnrollmentWayName().equals("")) {
					// 根据来源名称查询市场途径
					BaseDict bd = baseDictBiz.findBaseDictsByTypeAndName(
							Constants.BASEDICT_STYLE_ENROLLMENTWAY,
							student.getEnrollmentWayName());
					if (bd != null) {
						student.setEnrollmentWay(bd.getId());
					}

				}
			}
			Date date = new Date();
			student.setPushDate(date);
			student.setPushId(super.getUser().getUserId());
			student.setCreateDate(new Date());
			student.setCreatorId(super.getUser().getUserId());
			student.setUserId(super.getUser().getUserId());
			student.setBranchId(super.getUser().getOrgId());
			studentBiz.addStudent(student, followUp, null);
			exist = false;
		} else {
			exist = true;
		}
		return SUCCESS;
	}

	/**
	 * 增加学生，网推部门
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "add_student_np", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String addStudentNP() throws Exception {
		String remark = "";
		if (other != null) {
			for (String o : other) {
				remark += o + "\n";
			}
		}
		// 没有填写任何一项必填信息 错误代码:0001
		// 学生姓名不能为空 错误代号:1001
		// 身份证不能为空 错误代号:1002
		// 手机和座机必须填一项 错误代号:1003
		// 学生存在 错误代号:1004
		// 手机和座机验证失败 错误代码:1005
		// 身份证号码验证失败 错误代码:1006
		// 成功 成功代号:2001
		
		/*
		 * 修改纪录：1
		 * 发件人: 金鑫
		 * 发送时间: 2012年4月5日 20:41
         * 收件人: 金彦
         * 主题: 公服需要完善的内容
         * 前台 手机以及身份证的验证功能
         * 位数 以及有效性验证
		 */
		String code = "";
		if (student == null) {
			code = "0001";
		} else {
			if (student.getName() == null|| student.getName().trim().equals("")) {
				code = "1001";
			} else if ((student.getPhone() == null || student.getPhone().trim().equals(""))&& (student.getMobile() == null || student.getMobile().trim().equals(""))) {
				code = "1003";
			} else if (student.getMobile() != null && !student.getMobile().trim().equals("")&&!ValidationUtil.MatchMobile(student.getMobile().trim())){
				code = "1005";
			} else if (student.getPhone() != null && !student.getPhone().trim().equals("")&&!ValidationUtil.MatchTel(student.getPhone().trim())){
				code = "1005";
//			} else if (student.getCertNo() == null|| student.getCertNo().trim().equals("")) {
//				code = "1002";
			} else if (student.getCertNo() != null&&!student.getCertNo().trim().equals("")&&!ValidationUtil.IdCardNo(student.getCertNo().trim())) {
				code = "1006";
			} else {
				if (studentBiz.isExistStudent(student) == null) {
					// 锁定姓名,身份证
					// 锁定名称
					if (student.getName() != null&& !student.getName().equals("")) {
						student.setNameLockingStatus(1);
					}
					if (student.getCertNo() != null&& !student.getCertNo().equals("")) {
						student.setCertNoLockingStatus(1);
					}
					// 学生状态已分配
					student.setStatus(Constants.STU_CALL_STATUS_YU_BAO_MING);
					// 数据来源
					student.setStudentDataSource(Constants.STU_SOURCE_NP);
					//招生途径(社招)
					student.setEnrollmentSource(Constants.WEB_STU_SOURCE_DEFAULT);
					//市场途径(网络报名)
					student.setEnrollmentWay(46);
					// 服务时间
					// student.setServiceTime(serviceTime);
					student.setRemark(remark);
					student.setCreateDate(new Date());
					//student.setCreatorId(super.getUser().getUserId());
					student.setCreatorId(-1);
					studentBiz.addStudent(student, null, null);
					// exist = false;
					code = "2001";
				} else {
					// exist = true;
					code = "1004";
				}
			}
		}
		String callback = request.getParameter("callback");
		PrintWriter writer = response.getWriter();
		writer.println(callback + "({code:'" + code + "'})");
		return null;
	}

	/**
	 * 学生列表纪录数量
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "crm_student_count", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json","includeProperties","result.*" }) })
	public String crmStudentCount() throws Exception {
		// 查询数量
		// 未分配学生添加学习中心查询条件 为了独立查询页面学习中心我赋值为-1
		if (student.getBranchId() == -1) {
			student.setBranchId(super.getUser().getOrgId());
		}
		student.setMonitorStatus(-1);
		student.setTuitionStatus(-1);
		//本月
		if(student.getReturnVisitTimeType()==Constants.STUDENT_FOLLOW_GEN_JIN_LEI_XING_MONTH)
		{
			Calendar cal = Calendar.getInstance();  
			student.setReturnVisitTimeBegin(DateUtil.getTimestamp((DateUtil.getTimestamp(DateUtil.getNowTimestamp(),"yyyy-MM")+"-01"),"yyyy-MM-dd"));
		    cal.setTime(student.getReturnVisitTimeBegin());
		    cal.add(Calendar.MONTH,1); 
		    cal.add(Calendar.DATE, -1);
		    student.setReturnVisitTimeEnd(DateUtil.getTimestamp(cal.getTime(),"yyyy-MM-dd"));
		}
		else if(student.getReturnVisitTimeType()==Constants.STUDENT_FOLLOW_GEN_JIN_LEI_XING_WEEK)
		{
			//本周
			student.setReturnVisitTimeBegin(DateUtil.getMonday());
			student.setReturnVisitTimeEnd(DateUtil.getSunday());
		}
		result.setRecordCount(studentBiz.findStudentsPageCount(student, result));
		return SUCCESS;
	}

	/**
	 * 查询学生列表集合通过条件
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "crm_student_list", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json","excludeProperties","result.list.*.remark","includeProperties","result.*" }) })
	public String crmStudentList() throws Exception {
		// 查询集合
		// 未分配学生添加学习中心查询条件 为了独立查询页面学习中心我赋值为-1
		if (student.getBranchId() == -1) {
			student.setBranchId(super.getUser().getOrgId());
		}
		student.setMonitorStatus(-1);
		student.setTuitionStatus(-1);
		//本月
		if(student.getReturnVisitTimeType()==Constants.STUDENT_FOLLOW_GEN_JIN_LEI_XING_MONTH)
		{
			Calendar cal = Calendar.getInstance();  
			student.setReturnVisitTimeBegin(DateUtil.getTimestamp((DateUtil.getTimestamp(DateUtil.getNowTimestamp(),"yyyy-MM")+"-01"),"yyyy-MM-dd"));
		    cal.setTime(student.getReturnVisitTimeBegin());
		    cal.add(Calendar.MONTH,1); 
		    cal.add(Calendar.DATE, -1);
		    student.setReturnVisitTimeEnd(DateUtil.getTimestamp(cal.getTime(),"yyyy-MM-dd"));
		}
		else if(student.getReturnVisitTimeType()==Constants.STUDENT_FOLLOW_GEN_JIN_LEI_XING_WEEK)
		{
			//本周
			student.setReturnVisitTimeBegin(DateUtil.getMonday());
			student.setReturnVisitTimeEnd(DateUtil.getSunday());
		}
		result.setList(studentBiz.findStudentsPageList(student, result));
		return SUCCESS;
	}

	/**
	 * 删除学生
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "crm_delete_student", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String crmDeleteStudent() throws Exception {
		if (student != null && student.getId() != 0) {
			errorCode = studentBiz.deleteStudentById(student.getId());
		}
		return SUCCESS;
	}
	
	/**
	 * 删除学生全部信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "crm_delete_student_all", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String crmDeleteStudentAll() throws Exception {
		if (student != null && student.getId() != 0) {
			errorCode = studentBiz.deleteStudentAllById(student.getId());
		}
		return SUCCESS;
	}

	/**
	 * 呼叫中心更新学生
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_student_cc", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String updateStudentCC() throws Exception {
		// 更新学生信息

		if (student != null && student.getId() != 0) {
			Student s = studentBiz.findStudentById(student.getId());
			if (s != null) {
				//招生途径变更
				if(student.getEnrollmentSource()!=0&&student.getEnrollmentSource()!=s.getEnrollmentSource()&&s.getEnrollmentSource()!=0){
					studentEnrollmentSourceChangeLogBiz.addStudentEnrollmentSourceChangeLog(s.getId(), super.getUser().getUserId(), s.getEnrollmentSource(), student.getEnrollmentSource());				
				}
				// 名称未锁定
				if (s.getNameLockingStatus() == 0) {
					s.setName(student.getName());
					// 锁定名称
					if (student.getName() != null
							&& !student.getName().equals("")
							&& student.getNameLockingStatus() == 1) {
						s.setNameLockingStatus(1);
					}
				}

				// 身份证未锁定
				if (s.getCertNoLockingStatus() == 0) {
					s.setCertNo(student.getCertNo());
					// 证件类型
					s.setCertType(student.getCertType());
					// 锁定身份证
					if (student.getCertNo() != null
							&& !student.getCertNo().equals("")
							&& student.getCertNoLockingStatus() == 1) {
						s.setCertNoLockingStatus(1);
					}
				}
				// 已有的不能改，没有的可以改 电话手机
				if (s.getMobile() == null) {
					s.setMobile(student.getMobile());
				}
				if (s.getPhone() == null) {
					s.setPhone(student.getPhone());
				}

				s.setGender(student.getGender());
				s.setLivingPlace(student.getLivingPlace());
				// s.setPhone(student.getPhone());

				s.setMsn(student.getMsn());
				s.setQq(student.getQq());
				s.setEmail(student.getEmail());
				s.setRemark(student.getRemark());

				if (s.getStatus() == Constants.STU_CALL_STATUS_YU_BAO_MING
						|| s.getStatus() == Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI) {
					s.setAcademyId(student.getAcademyId());// 院校ID
					s.setEnrollmentBatchId(student.getEnrollmentBatchId());// 院校批次
					s.setBranchId(student.getBranchId());// 学习中心
					s.setLevelId(student.getLevelId());// 层次
					s.setMajorId(student.getMajorId());// 专业
					s.setGlobalBatch(student.getGlobalBatch());//全局批次
				}

				// 招生途径
				s.setEnrollmentSource(student.getEnrollmentSource());
				// 合作方ID
				s.setChannelId(student.getChannelId());
				// 市场途径
				s.setEnrollmentWay(student.getEnrollmentWay());

				s.setServiceTime(student.getServiceTime());// 希望联系时间
				s.setWorkUnitInfo(student.getWorkUnitInfo());// 单位信息

				// 最后修改人
				s.setUpdaterId(super.getUser().getUserId());
				// 最后修改时间
				s.setModifiedTime(new Date());
				if (s.getStatus() == Constants.STU_CALL_STATUS_YU_BAO_MING
						|| s.getStatus() == Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI) {
					s.setStatus(student.getStatus());
				}
				// 如果状态为已导入未分配，记录跟进时间
				Date date = new Date();
				if (s.getStatus() == Constants.STU_CALL_STATUS_YI_DAO_RU) {
					s.setLatestFollowUpDate(date);
					s.setFollowUpId(super.getUser().getUserId());
					
					s.setPushId(super.getUser().getUserId());
					s.setPushDate(date);
				}

				if (followUp == null) {
					followUp = new FollowUp();
				}
				// 学生状态预报名(标记呼叫中心的跟进内容)
				followUp.setStatusId(Constants.STU_CALL_STATUS_YU_BAO_MING);
				// 跟进人
				followUp.setCreatorId(super.getUser().getUserId());
				// 跟进时间
				followUp.setCreatedTime(date);
				followUp.setStudentId(s.getId());
				// 操作日志
				OperationLog operationLog = new OperationLog();
				operationLog.setContent(super.getText(
						"crm.update.student.3",
						new Object[] { super.getUser().getFullName(),
								s.getName(), student.getId() }));// 内容
				operationLog.setIp(request.getRemoteHost());
				operationLog.setCreateId(super.getUser().getUserId());
				operationLog.setCreateTime(new Date());
				studentBiz.updateStudent(s, followUp, operationLog);
			}

		}

		return SUCCESS;
	}

	/**
	 * 学习中心更新学生意愿信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_student_yiyuan_lc", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String updateStudentYiYuanLC() throws Exception {
		// 学生
		Student s = studentBiz.findStudentById(student.getId());
		if (s != null) {
			//招生途径变更
			if(student.getEnrollmentSource()!=0&&student.getEnrollmentSource()!=s.getEnrollmentSource()&&s.getEnrollmentSource()!=0){
				studentEnrollmentSourceChangeLogBiz.addStudentEnrollmentSourceChangeLog(s.getId(), super.getUser().getUserId(), s.getEnrollmentSource(), student.getEnrollmentSource());				
			}
			s.setAcademyId(student.getAcademyId());
			s.setEnrollmentBatchId(student.getEnrollmentBatchId());
			//学籍批次和招生批次
			if(s.getBatchId()>0 && s.getBatchId()!=s.getEnrollmentBatchId())
			{
				s.setBatchId(s.getEnrollmentBatchId());
			}
			//招生批次状态为结束的批次不允许报名
			AcademyEnrollBatch aeb=aebBiz.findAcademyEnrollBatchById(s.getEnrollmentBatchId());
			if(aeb!=null && aeb.getIsEnable()==Constants.STATUS_FINISHED)	
			{
				isbatch=true;
				return SUCCESS;
			}
			s.setLevelId(student.getLevelId());
			s.setMajorId(student.getMajorId());
			s.setIsExemption(student.getIsExemption());
			s.setNotApplyFee(student.getNotApplyFee());
			if(s.getCertNo()==null || s.getCertNo().equals("") || ((s.getPhone()==null || s.getPhone().equals(""))&&(s.getMobile()==null || s.getMobile().equals(""))))//验证有没有身份证联系电话
			{
				ishascertno=true;
				return SUCCESS;
			}
			if (s.getStatus() > Constants.STU_CALL_STATUS_YI_DAO_MING) {
				isture = true;
				return SUCCESS;
			}
			if (s.getNotApplyFee() == Constants.STUDENT_IS_EXEMPTION_TRUE
					&& s.getIsExemption() == Constants.STUDENT_IS_EXEMPTION_TRUE) {
				s.setStatus(Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI);
				isback = true;
			} else {
				s.setStatus(Constants.STU_CALL_STATUS_YI_DAO_MING);
			}
			//判断有没有跟进人
			if(s.getUserId()==0)
			{
				s.setUserId(super.getUser().getUserId());
			}
			s.setUpdaterId(super.getUser().getUserId());
			s.setModifiedTime(new Date());
			studentBiz.updateStudent(s, null, null);
			student = s;

			// 未了防止学生点击报名后发现意向填错，所以在刷新待缴费单时，先删除以前生成的
			List<PendingFeePayment> oldpendingList = this.pendingFeePaymentBiz
					.findPendingFeePaymentListByStudentId(s.getId());
			if (oldpendingList != null && oldpendingList.size() > 0) {
				this.pendingFeePaymentBiz
						.deleteBatchPendingFeePayment(oldpendingList);
			}

			// 刷新待缴费单(生成该学生要缴纳的费用)(优化方法)
			// List<PendingFeePayment> pendingList = this.pendingFeePaymentBiz
			// .generatePendingFeePaymentListByAcademyIdFeeSubjectId(
			// s.getAcademyId(), -1);
			List<PendingFeePayment> pendingList = this.pendingFeePaymentBiz
					.generatePendingFeePaymentListByStudentIdFeeSubjectId(
							s.getId(), -1);
			if (pendingList != null && pendingList.size() > 0) {
				for (PendingFeePayment pending : pendingList) {
					pending.setCode(buildCodeBiz.getCodes(
							CodeEnum.PendingFeePaymentTB.getCode(),
							CodeEnum.PendingFeePaymentPrefix.getCode()));
					pending.setCreatorId(super.getUser().getUserId());
					pending.setUpdaterId(super.getUser().getUserId());
				}
				isfail = this.pendingFeePaymentBiz
						.addBatchPendingFeePayment(pendingList);
			}
			// 查询报名费和测试费的待缴费单
			// 报名费
			// if (s.getNotApplyFee() == Constants.STUDENT_IS_EXEMPTION_FALSE)
			// {
			List<PendingFeePayment> pendingname = this.pendingFeePaymentBiz
					.findStudentsPendingFeePaymentListByStudentIdFeeSubjectId(
							s.getId(), FeeSubjectEnum.RegistrationFee.value());

			if (pendingname != null && pendingname.size() > 0) {
				for (PendingFeePayment pend : pendingname) {
					pendinglist.add(pend);
				}
			}
			// }
			// if (s.getIsExemption() == Constants.STUDENT_IS_EXEMPTION_FALSE)//
			// 是否是免试生
			// {
			// 测试费
			List<PendingFeePayment> pendingtest = this.pendingFeePaymentBiz
					.findStudentsPendingFeePaymentListByStudentIdFeeSubjectId(
							s.getId(), FeeSubjectEnum.TestFee.value());
			if (pendingtest != null && pendingtest.size() > 0) {
				for (PendingFeePayment pend : pendingtest) {
					pendinglist.add(pend);
				}
			}
			// }

		}

		return SUCCESS;
	}

	/**
	 * 学习中心更新学生
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_student_lc", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String updateStudentLC() throws Exception {
		// 更新学生信息
		if (student != null && student.getId() != 0) {
			Student s = studentBiz.findStudentById(student.getId());
			if (s != null) {
				//招生途径变更
				if(student.getEnrollmentSource()!=0&&student.getEnrollmentSource()!=s.getEnrollmentSource()&&s.getEnrollmentSource()!=0){
					studentEnrollmentSourceChangeLogBiz.addStudentEnrollmentSourceChangeLog(s.getId(), super.getUser().getUserId(), s.getEnrollmentSource(), student.getEnrollmentSource());				
				}
				// 名称未锁定
				if (s.getNameLockingStatus() == 0) {
					s.setName(student.getName());
					// 锁定名称
					if (student.getName() != null
							&& !student.getName().equals("")
							&& student.getNameLockingStatus() == 1) {
						s.setNameLockingStatus(1);
					}
				}
				// 社招
				if (s.getEnrollmentSource() == Constants.WEB_STU_SOURCE_DEFAULT) {
					// 已有的不能改，没有的可以改 电话手机
					if (s.getMobile() == null || s.getMobile().equals("")) {
						s.setMobile(student.getMobile());
					}
					if (s.getPhone() == null || s.getPhone().equals("")) {
						s.setPhone(student.getPhone());
					}
				} else {
					// 大客户、渠道、老带新、老生续读、加盟、共建 可以修改
					s.setMobile(student.getMobile());
					s.setPhone(student.getPhone());
				}

				s.setGender(student.getGender());
				s.setLivingPlace(student.getLivingPlace());

				// 已报名以后不允许修改学生身份证号，院校，批次，层次，学习中心，专业
				if (s.getStatus() < Constants.STU_CALL_STATUS_YI_DAO_MING) {
					// 身份证未锁定
					if (s.getCertNoLockingStatus() == 0) {
						s.setCertNo(student.getCertNo());
						// 证件类型
						s.setCertType(student.getCertType());
						// 锁定身份证
						if (student.getCertNo() != null
								&& !student.getCertNo().equals("")
								&& student.getCertNoLockingStatus() == 1) {
							s.setCertNoLockingStatus(1);
						}
					}
					s.setAcademyId(student.getAcademyId());// 院校ID
					s.setEnrollmentBatchId(student.getEnrollmentBatchId());// 院校批次
					// s.setBranchId(student.getBranchId());// 学习中心
					s.setLevelId(student.getLevelId());// 层次
					s.setMajorId(student.getMajorId());// 专业
					s.setServiceTime(student.getServiceTime());// 希望联系时间
					s.setReturnVisitTime(student.getReturnVisitTime());// 提醒回访跟进
					s.setStatus(student.getStatus());

					// 修改 无意愿也和跟进中流程一样(记录跟进记录等操作) edited by dongminghao
					if (student.getStatus() == Constants.STU_CALL_STATUS_GENG_JIN_ZHONG ||
							student.getStatus() == Constants.STU_CALL_STATUS_WU_YI_YUAN) {
						// 最新跟进时间
						Date date = new Date();
						s.setLatestFollowUpDate(date);
						
						if(s.getUpFollowUpId()==0){
							// 上一跟进人
							//s.setUpFollowUpId(s.getFollowUpId());
							s.setUpFollowUpId(super.getUser().getUserId());
						}
						
						// 当前跟进人
						s.setFollowUpId(super.getUser().getUserId());

						// 当前跟进等级
						s.setCallStatusId(followUp.getCallStatusId());
						// 跟进次数
						s.setFollowCount(s.getFollowCount() + 1);

						if (followUp == null) {
							followUp = new FollowUp();
						}
						// 学生状态预报名(标记呼叫中心的跟进内容)
						followUp.setStatusId(Constants.STU_CALL_STATUS_GENG_JIN_ZHONG);
						// 跟进人
						followUp.setCreatorId(super.getUser().getUserId());
						followUp.setCallStatusId(followUp.getCallStatusId());
						// 跟进时间
						followUp.setCreatedTime(date);
						followUp.setStudentId(s.getId());
					} else {
						followUp = null;
					}
				}
				// }

				s.setMsn(student.getMsn());
				s.setQq(student.getQq());
				s.setEmail(student.getEmail());
				s.setRemark(student.getRemark());
				// s.setPhone(student.getPhone());

				// 最后修改人
				s.setUpdaterId(super.getUser().getUserId());
				// 最后修改时间
				s.setModifiedTime(new Date());
				s.setWorkUnitInfo(student.getWorkUnitInfo());// 单位信息
				//学历
				s.setDegree(student.getDegree());

				// 操作日志
				OperationLog operationLog = new OperationLog();
				operationLog.setContent(super.getText(
						"crm.update.student.1",
						new Object[] { super.getUser().getFullName(),
								s.getName(), s.getId() }));// 内容
				operationLog.setIp(request.getRemoteHost());
				operationLog.setCreateId(super.getUser().getUserId());
				operationLog.setCreateTime(new Date());
				studentBiz.updateStudent(s, followUp, operationLog);
			}

		}
		return SUCCESS;
	}

	/**
	 * 学生是否存在通过手机
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "exist_student_phone", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String existStudentPhone() throws Exception {
		if (student != null && student.getMobile() != null
				&& !student.getMobile().equals("")) {
			Student s = new Student();
			s.setMobile(student.getMobile());
			s.setId(student.getId());  //学习中心监控再申请   老生续读的新生手机号与老生冲突验证不通过  现在屏蔽掉老生
			if (studentBiz.isExistStudent(s) == null) {
				exist = true;
			}
		} else {
			exist = true;
		}

		return SUCCESS;
	}
	
	/**
	 * 学生是否存在通过座机
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "exist_student_tel", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String existStudentTel() throws Exception {
		if (student != null && student.getPhone() != null
				&& !student.getPhone().equals("")) {
			Student s = new Student();
			s.setPhone(student.getPhone());
			s.setId(student.getId()); //  老生续读的新生座机号与老生冲突验证不通过  现在屏蔽掉老生
			if (studentBiz.isExistStudent(s) == null) {
				exist = true;
			}
		} else {
			exist = true;
		}

		return SUCCESS;
	}

	// /**
	// * 通过手机查询学生信息
	// *
	// * @return
	// * @throws Exception
	// */
	// @Action(value = "select_student_phone", results = { @Result(name =
	// "success", type = "json", params = {
	// "contentType", "text/json" }) })
	// public String selectStudentPhone() throws Exception {
	// //高雷原来代码
	// List<Student>
	// studentlst=studentBiz.findStudentsByPhone(student.getMobile());
	// if(null!=studentlst && studentlst.size()>0)
	// {
	// student=studentlst.get(0);
	// }
	// //重构以后
	//
	// return SUCCESS;
	// }

	/**
	 * 学生是否存在通过身份证
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "exist_student_certno", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String existStudentCertNo() throws Exception {
		if (student != null && student.getCertNo() != null
				&& !student.getCertNo().equals("")) {
			Student s = new Student();
			s.setCertNo(student.getCertNo());
			s.setId(student.getId()); //  老生续读的新生身份证与老生冲突验证不通过  现在屏蔽掉老生
			if (studentBiz.isExistStudent(s) == null) {
				exist = true;
			}
		} else {
			exist = true;
		}

		return SUCCESS;
	}

	/**
	 * 学生报名
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "student_reg", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"student.*,result.*"
	}) })
	public String studentReg() throws Exception {

		student.setBranchId(super.getUser().getOrgId());
		student.setStatus(Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI);
		result.setList(studentBiz.findSyudentByExact(student));
		return SUCCESS;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public PageResult<Student> getResult() {
		return result;
	}

	public void setResult(PageResult<Student> result) {
		this.result = result;
	}

	public FollowUp getFollowUp() {
		return followUp;
	}

	public void setFollowUp(FollowUp followUp) {
		this.followUp = followUp;
	}

	public boolean isExist() {
		return exist;
	}

	public void setExist(boolean exist) {
		this.exist = exist;
	}

	public boolean isIsfail() {
		return isfail;
	}

	public void setIsfail(boolean isfail) {
		this.isfail = isfail;
	}

	public List<PendingFeePayment> getPendinglist() {
		return pendinglist;
	}

	public void setPendinglist(List<PendingFeePayment> pendinglist) {
		this.pendinglist = pendinglist;
	}

	public boolean isIsture() {
		return isture;
	}

	public void setIsture(boolean isture) {
		this.isture = isture;
	}

	public String[] getOther() {
		return other;
	}

	public void setOther(String[] other) {
		this.other = other;
	}

	public boolean isIsback() {
		return isback;
	}

	public void setIsback(boolean isback) {
		this.isback = isback;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public boolean isIshascertno() {
		return ishascertno;
	}

	public void setIshascertno(boolean ishascertno) {
		this.ishascertno = ishascertno;
	}

	public boolean isIsbatch() {
		return isbatch;
	}

	public void setIsbatch(boolean isbatch) {
		this.isbatch = isbatch;
	}

}
