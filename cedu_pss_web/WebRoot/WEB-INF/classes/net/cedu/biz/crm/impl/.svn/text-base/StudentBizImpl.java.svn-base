package net.cedu.biz.crm.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.basesetting.GlobalEnrollBatchBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.PendingFeePaymentBiz;
import net.cedu.biz.finance.StudentAccountManagementBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.enums.FeeSubjectEnum;
import net.cedu.common.il8n.ResourcesTool;
import net.cedu.common.string.IdcardUtil;
import net.cedu.dao.admin.UserDao;
import net.cedu.dao.crm.FollowUpDao;
import net.cedu.dao.crm.OperationLogDao;
import net.cedu.dao.crm.StudentDao;
import net.cedu.dao.crm.StudentEnrollmentSourceChangeLogDao;
import net.cedu.dao.crm.StudentSlaveDao;
import net.cedu.dao.enrollment.ReturningVisitDao;
import net.cedu.dao.finance.FeePaymentDao;
import net.cedu.dao.finance.FeePaymentDetailDao;
import net.cedu.dao.finance.PendingFeePaymentDao;
import net.cedu.dao.finance.StudentAccountAmountManagementDao;
import net.cedu.dao.finance.StudentAccountManagementDao;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.admin.User;
import net.cedu.entity.basesetting.BaseDict;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.basesetting.GlobalEnrollBatch;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.crm.FollowUp;
import net.cedu.entity.crm.OperationLog;
import net.cedu.entity.crm.Student;
import net.cedu.entity.crm.StudentSlave;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.AcademyLevel;
import net.cedu.entity.enrollment.Major;
import net.cedu.entity.finance.PendingFeePayment;
import net.cedu.entity.finance.StudentAccountManagement;
import net.cedu.model.crm.ImportResult;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * student实现类
 * 
 * @author yangdongdong
 * 
 */
@Service
public class StudentBizImpl implements StudentBiz {

	@Autowired
	private StudentDao studentDao;
	@Autowired
	private FollowUpDao followUpDao;
	@Autowired
	private OperationLogDao operationLogDao;
	// @Autowired
	// private UserStudentDao userStudentDao;
	@Autowired
	private StudentSlaveDao studentSlaveDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private BaseDictBiz basedictBiz;

	@Autowired
	private UserBiz userBiz;
	@Autowired
	private BaseDictBiz baseDictBiz;
	@Autowired
	private BranchBiz branchBiz;

	@Autowired
	private AcademyBiz academyBiz;// 院校招生批次 业务接口
	@Autowired
	private AcademyEnrollBatchBiz academyenrollbatchBiz;// 院校招生批次 业务接口
	@Autowired
	private AcademyLevelBiz academylevelBiz;// 院校招生批次 业务接口
	@Autowired
	private LevelBiz levelbiz; // 层次业务接口
	@Autowired
	private MajorBiz majorbiz; // 专业业务接口

	@Autowired
	private EnrollmentSourceBiz enrollmentsourceBiz;
	@Autowired
	private PendingFeePaymentBiz pendingFeePaymentBiz;// 待缴费单业务接口
	@Autowired
	private GlobalEnrollBatchBiz globalEnrollBatchBiz;//全局批次业务接口
	@Autowired
	private FeePaymentBiz feePaymentBiz;// 缴费单业务接口
	@Autowired
	private FeePaymentDao feePaymentDao;// 缴费单数据接口(用于批量删除)
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;// 缴费单明细业务接口
	@Autowired
	private FeePaymentDetailDao feePaymentDetailDao;// 缴费单明细数据接口(用于批量删除)
	@Autowired
	private PendingFeePaymentDao pendingFeePaymentDao;
	@Autowired
	private StudentEnrollmentSourceChangeLogDao studentEnrollmentSourceChangeLogDao;
	@Autowired
	private StudentAccountManagementBiz studentAccountManagementBiz;
	@Autowired
	private StudentAccountManagementDao studentAccountManagementDao;
	@Autowired
	private StudentAccountAmountManagementDao studentAccountAmountManagementDao;
	@Autowired
	private ReturningVisitDao returningVisitDao;

	/*
	 * 新建学生以及跟进纪录,以及操作日志
	 * 
	 * @see net.cedu.biz.crm.StudentBiz#addStudent(net.cedu.entity.crm.Student,
	 * net.cedu.entity.crm.FollowUp, net.cedu.entity.crm.OperationLog)
	 */
	public int addStudent(Student student, FollowUp followUp,
			OperationLog operationLog) throws Exception {
		try {
			// 层次
			if (student.getLevelId() != 0) {
				AcademyLevel academyLevel = academylevelBiz.findById(student
						.getLevelId());
				if(academyLevel!=null)
				{
					student.setLevelId(academyLevel.getLevelId());
				}
			}
			//全局批次(edited by dongminghao 根据招生批次匹配学生全局批次)
			if(student.getEnrollmentBatchId()!=0)
			{
				AcademyEnrollBatch aeb = academyenrollbatchBiz.findAcademyEnrollBatchById(student.getEnrollmentBatchId());
				if(aeb!=null)
				{
					student.setGlobalBatch(aeb.getGlobalEnrollBatchId());
				}
			}

			Student s = isExistStudent(student);
			if (s == null) {
				// 去掉空格
				student.setName(student.getName().trim());
				Object object = studentDao.save(student);
				int id = Integer.valueOf(object.toString());
				if (followUp != null && followUp.getRemark() != null
						&& !"".equals(followUp.getRemark())) {

					followUp.setStudentId(id);
					followUpDao.save(followUp);
				}
				if (operationLog != null) {
					// 操作日志
					operationLogDao.save(operationLog);
				}
				return id;
			} else {
				if (followUp != null && followUp.getRemark() != null
						&& !"".equals(followUp.getRemark())) {
					followUp.setStudentId(s.getId());
					followUpDao.save(followUp);
				}
				if (operationLog != null) {
					// 操作日志
					operationLogDao.save(operationLog);
				}
			}

			return s.getId();
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(student.getCertNo());
			return 0;
		}
	}

	/*
	 * 删除学生
	 * 
	 * @see net.cedu.biz.crm.StudentBiz#deleteStudentById(int)
	 */
	public String deleteStudentById(int id) throws Exception {
		Student student = studentDao.findById(id);
		if (student != null) {
			if (student.getStatus() == Constants.STU_CALL_STATUS_YU_BAO_MING
					|| student.getStatus() == Constants.STU_CALL_STATUS_YI_DAO_RU
					|| student.getStatus() == Constants.STU_CALL_STATUS_YI_FENG_PEI
					|| student.getStatus() == Constants.STU_CALL_STATUS_WU_YI_YUAN
					|| student.getStatus() == Constants.STU_CALL_STATUS_GENG_JIN_ZHONG
					|| student.getStatus() == Constants.STU_CALL_STATUS_YI_DAO_MING
					|| student.getStatus() == Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI) {
				try {
					// 删除副表信息
					studentSlaveDao.deleteByProperty(" and studentId="
							+ Constants.PLACEHOLDER, id);
					// 删除跟进纪录
					followUpDao.deleteByProperty(" and studentId="
							+ Constants.PLACEHOLDER, new Object[] { id });
					// 删除学生信息
					studentDao.deleteById(id);
					// 成功删除
					return "200001";
				} catch (Exception e) {
					return "500";
				}
			} else {
				// 请求成功但该状态不能删除
				return "200002";
			}

		} else {
			// 已删除
			return "404";
		}

	}
	
	/*
	 * (重写)删除学生(删除所有学生相关表)
	 * @see net.cedu.biz.crm.StudentBiz#deleteStudentAllById(int)
	 */
	public String deleteStudentAllById(int id) throws Exception {
		Student student = studentDao.findById(id);
		if(student!=null)
		{
			Student stu = new Student();
			stu.setNewstuId(id);
			//是否为老生续读的新生
			if(this.isExistOldStudent(id)==null)
			{
				// 学生未作废的缴费单数量
				int count = feePaymentBiz.findFeePaymentNoInvalidByStudentId(student.getId());
				if(count==0)
				{
					try {
						// 删除缴费单信息
						feePaymentDao.deleteByProperty(" and studentId="
								+ Constants.PLACEHOLDER, id);
						// 删除缴费单明细信息
						feePaymentDetailDao.deleteByProperty(" and studentId="
								+ Constants.PLACEHOLDER, id);
						// 删除待缴费单信息
						pendingFeePaymentDao.deleteByProperty(" and studentId="
								+ Constants.PLACEHOLDER, id);
						// 删除跟进记录信息
						followUpDao.deleteByProperty(" and studentId="
								+ Constants.PLACEHOLDER, id);
						// 删除呼叫记录信息（目前未使用过的表 跳过删除）
						
						// 删除来源修改记录信息
						studentEnrollmentSourceChangeLogDao.deleteByProperty(" and studentId="
								+ Constants.PLACEHOLDER, id);
						// 删除副表信息
						studentSlaveDao.deleteByProperty(" and studentId="
								+ Constants.PLACEHOLDER, id);
						// 删除回访记录信息
						returningVisitDao.deleteByProperty(" and studentId="
								+ Constants.PLACEHOLDER, id);
						// 删除学生信息
						studentDao.deleteById(id);
						// 充值帐户信息
						StudentAccountManagement sam = studentAccountManagementBiz.findStudentAccountManagementByStudentId(id);
						if(sam!=null)
						{
							// 删除充值帐户明细信息
							studentAccountAmountManagementDao.deleteByProperty(" and accountId="
									+ Constants.PLACEHOLDER, sam.getId());
							// 删除充值帐户信息
							studentAccountManagementDao.deleteByProperty(" and studentId="
									+ Constants.PLACEHOLDER, id);
						}
						// 成功删除
						return "200001";
					} catch (Exception e) {
						e.printStackTrace();
						return "500";
					}
				}
				else
				{
					// 请求成功但缴费单存在未作废不能删除
					return "200003";
				}
			}
			else
			{
				// 请求成功但该学生为老生续读关联的新生不能删除
				return "200004";
			}
		}
		else
		{
			// 已删除
			return "404";
		}
	}

	/*
	 * 查看学生
	 * 
	 * @see net.cedu.biz.crm.StudentBiz#findStudentById(int)
	 */
	public Student findStudentById(int id) throws Exception {
		Student student = studentDao.findById(id);
		if (student != null) {
			// System.out.println(ResourcesTool.getText("crm", "ip", null));
			GlobalEnrollBatch globalEnrollBatch = academyenrollbatchBiz
					.findGlobalBatchIdByAcademyEnrollBatchIdAndAcademyId(
							student.getEnrollmentBatchId(),
							student.getAcademyId());
			if (globalEnrollBatch != null) {
				student.setGlobalBatch(globalEnrollBatch.getId());
			}
			return student;
		} else {
			return null;
		}
	}

	/*
	 * 查询学生总数量
	 * 
	 * @see
	 * net.cedu.biz.crm.StudentBiz#findStudentsPageCount(net.cedu.entity.crm
	 * .Student, net.cedu.model.page.PageResult)
	 */
	public int findStudentsPageCount(Student student, PageResult<Student> pr)
			throws Exception {

		PageParame p = new PageParame(pr);
		// p.setOrder(pr.getOrder());
		List<Object> list = null;
		StringBuffer hqlConditionExpression = new StringBuffer();
			if (student != null) {
				list = new ArrayList<Object>();
				// 学号
				if (student.getNumber() != null
						&& !student.getNumber().equals("")) {
					hqlConditionExpression.append("and number like"
							+ Constants.PLACEHOLDER);
					list.add("%" + student.getNumber() + "%");
				}
				// 姓名
				if (student.getName() != null && !student.getName().equals("")) {
					hqlConditionExpression.append("and name like"
							+ Constants.PLACEHOLDER);
					list.add("%" + student.getName() + "%");
				}
				// 证件号
				if (student.getCertNo() != null
						&& !"".equals(student.getCertNo())) {
					hqlConditionExpression.append("and certNo like"
							+ Constants.PLACEHOLDER);
					list.add("%" + student.getCertNo() + "%");
				}
				// 学历
				if (student.getDegree() != 0) {
					hqlConditionExpression.append("and degree ="
							+ Constants.PLACEHOLDER);
					list.add(student.getDegree());
				}

				// 状态
				if (student.getStatus() != 0) {
					hqlConditionExpression.append("and status="
							+ Constants.PLACEHOLDER);
					list.add(student.getStatus());
				}
				// 性别
				if (student.getGender() != -1) {
					hqlConditionExpression.append("and gender="
							+ Constants.PLACEHOLDER);
					list.add(student.getGender());
				}

				// 数据来源
				if (student.getStudentDataSource() != 0) {
					hqlConditionExpression.append("and studentDataSource="
							+ Constants.PLACEHOLDER);
					list.add(student.getStudentDataSource());
				}
				// 批量数据来源
				if (student.getStudentDataSourceIds() !=null && !student.getStudentDataSourceIds().equals("")){
					hqlConditionExpression.append("and studentDataSource in (" + Constants.PLACEHOLDER + ") ");
					list.add("$" + student.getStudentDataSourceIds());
				}
				// 学生招生途径
				if (student.getEnrollmentSource() != 0) {
					hqlConditionExpression.append("and enrollmentSource="
							+ Constants.PLACEHOLDER);
					list.add(student.getEnrollmentSource());
				}
				// 院校
				if (student.getAcademyId() != 0) {
					hqlConditionExpression.append("and academyId="
							+ Constants.PLACEHOLDER);
					list.add(student.getAcademyId());
				}
				// 院校Id集合
				if (student.getAcademyIds() != null
						&& !student.getAcademyIds().equals("")) {
					hqlConditionExpression.append("and academyId in ("
							+ Constants.PLACEHOLDER + ") ");
					list.add("$" + student.getAcademyIds());
				}
				// 是否呼叫过
				if (student.getCallStatus() == 0) {
					hqlConditionExpression.append("and callStatus="
							+ Constants.PLACEHOLDER);
					list.add(0);
				} else if (student.getCallStatus() > 0) {
					hqlConditionExpression.append("and callStatus>"
							+ Constants.PLACEHOLDER);
					list.add(0);
				}
				// 用户
				if (student.getUserId() == -1) {
					hqlConditionExpression.append("and userId="
							+ Constants.PLACEHOLDER);
					list.add(0);
				} else {
					if (student.getUserId() != 0) {
						hqlConditionExpression.append("and userId="
								+ Constants.PLACEHOLDER);
						list.add(student.getUserId());
					}
				}
				// 手机
				if (student.getMobile() != null
						&& !student.getMobile().equals("")) {
					hqlConditionExpression.append("and  mobile like"
							+ Constants.PLACEHOLDER);
					list.add("%" + student.getMobile() + "%");
				}
				// 手机或座机
				if (student.getPhone() != null
						&& !student.getPhone().equals("")) {
					hqlConditionExpression.append("and (phone like"
							+ Constants.PLACEHOLDER + "or mobile like"
							+ Constants.PLACEHOLDER + ")");
					list.add("%" + student.getPhone() + "%");
					list.add("%" + student.getPhone() + "%");
				}
				// 邮箱
				if (student.getEmail() != null
						&& !"".equals(student.getEmail())) {
					hqlConditionExpression.append("and email like"
							+ Constants.PLACEHOLDER);
					list.add("%" + student.getEmail() + "%");
				}
				// 所在城市
				if (student.getLivingPlace() != null
						&& !"0".equals(student.getLivingPlace())
						&& !"".equals(student.getLivingPlace())) {

					hqlConditionExpression.append("and livingPlace="
							+ Constants.PLACEHOLDER);
					list.add(student.getLivingPlace());
				}
				// 批次
				if (student.getEnrollmentBatchId() != 0) {
					hqlConditionExpression.append(" and  enrollmentBatchId= "
							+ Constants.PLACEHOLDER);
					list.add(student.getEnrollmentBatchId());
				}
				// 全局批次
				String gbatchIds = "";
				StringBuffer gbatchIdsSB = new StringBuffer("");
				if (student.getGlbtach() != 0) {
					List<AcademyEnrollBatch> aeblst = academyenrollbatchBiz
							.findAcademyEnrollBatchByGId(student.getGlbtach());
					
					if (null != aeblst && aeblst.size() > 0) {
						for (int i = 0; i < aeblst.size(); i++) {
//							gbatchIds += "," + aeblst.get(i).getId();
							if(gbatchIdsSB.toString().equals(""))
							{
								gbatchIdsSB = new StringBuffer(aeblst.get(i).getId()+"");
							}
							else
							{
								gbatchIdsSB.append("," + aeblst.get(i).getId());
							}
						}
						if(gbatchIdsSB.toString().equals(""))
						{
							gbatchIdsSB = new StringBuffer("0");
						}
						gbatchIds = gbatchIdsSB.toString();
						hqlConditionExpression.append(" and ( enrollmentBatchId in ( "
								+ Constants.PLACEHOLDER + " ) ");
						list.add("$" + gbatchIds);
						hqlConditionExpression.append(" or  globalBatch = "+ Constants.PLACEHOLDER+") ");
						list.add(student.getGlbtach());
					}
					else
					{
						hqlConditionExpression.append(" and  globalBatch = "+ Constants.PLACEHOLDER);
						list.add(student.getGlbtach());
					}
				}
				if(gbatchIdsSB.toString().equals(""))
				{
					gbatchIdsSB = new StringBuffer("0");
				}
				gbatchIds = gbatchIdsSB.toString();
				// 层次
				if (student.getLevelId() != 0) {
					hqlConditionExpression.append(" and  levelId= "
							+ Constants.PLACEHOLDER);
					list.add(student.getLevelId());
				}
				// 全局层次
				String glevelIds = "";
				StringBuffer glevelIdsSB = new StringBuffer("");
				if (student.getGllevel() != 0) {
					List<AcademyLevel> allst = academylevelBiz
							.findByGllevel(student.getGllevel());
					
					if (null != allst && allst.size() > 0) {
						for (int i = 0; i < allst.size(); i++) {
//							glevelIds += "," + allst.get(i).getId();
							if(glevelIdsSB.toString().equals(""))
							{
								glevelIdsSB = new StringBuffer(allst.get(i).getId()+"");
							}
							else
							{
								glevelIdsSB.append("," + allst.get(i).getId());
							}
						}
						if(glevelIdsSB.toString().equals(""))
						{
							glevelIdsSB = new StringBuffer("0");
						}
						glevelIds = glevelIdsSB.toString();
						hqlConditionExpression.append(" and  levelId in ( "
								+ Constants.PLACEHOLDER + " ) ");
						list.add("$" + glevelIds);
					}
				}
				if(glevelIdsSB.toString().equals(""))
				{
					glevelIdsSB = new StringBuffer("0");
				}
				glevelIds = glevelIdsSB.toString();
				// 专业
				if (student.getMajorId() != 0) {
					hqlConditionExpression.append(" and  majorId= "
							+ Constants.PLACEHOLDER);
					list.add(student.getMajorId());
				}
				// 基础专业
				String glmajors = "";
				StringBuffer glmajorsSB = new StringBuffer("");
				if (student.getGlmajor() != 0) {
					List<Major> majorList = this.majorbiz
							.findMajorListByBaseMajorId(student.getGlmajor());
					
					if (null != majorList && majorList.size() > 0) {
						for (int i = 0; i < majorList.size(); i++) {
//							glmajors += "," + majorList.get(i).getId();
							if(glmajorsSB.toString().equals(""))
							{
								glmajorsSB = new StringBuffer(majorList.get(i).getId()+"");
							}
							else
							{
								glmajorsSB.append("," + majorList.get(i).getId());
							}
						}
						if(glmajorsSB.toString().equals(""))
						{
							glmajorsSB = new StringBuffer("0");
						}
						glmajors = glmajorsSB.toString();
						hqlConditionExpression.append(" and  majorId in ( "
								+ Constants.PLACEHOLDER + " ) ");
						list.add("$" + glmajors);
					} else{
						hqlConditionExpression.append(" and  majorId in ( "
							+ Constants.PLACEHOLDER + " ) ");
					list.add("$0");
					}
				}
				if(glmajorsSB.toString().equals(""))
				{
					glmajorsSB = new StringBuffer("0");
				}
				glmajors = glmajorsSB.toString();
				// 市场途径
				if (student.getEnrollmentWay() != 0) {
					hqlConditionExpression.append(" and  enrollmentWay= "
							+ Constants.PLACEHOLDER);
					list.add(student.getEnrollmentWay());
				}
				// 监控状态
				if (student.getMonitorStatus() != -1) {
					hqlConditionExpression.append(" and  monitorStatus= "
							+ Constants.PLACEHOLDER);
					list.add(student.getMonitorStatus());
				}
				// 缴费状态
				if (student.getTuitionStatus() != -1) {
					if (student.getTuitionStatus() > 1
							&& student.getTuitionStatus() < 9999) {
						hqlConditionExpression.append(" and  tuitionStatus > "
								+ Constants.PLACEHOLDER + " and tuitionStatus<"
								+ Constants.PLACEHOLDER);
						list.add(Constants.STU_TUITION_STATUS_SHOU_CI_JIAO_FEI);
						list.add(Constants.STU_TUITION_STATUS_JIAO_FEI_WAN_CHENG);
					} else {
						hqlConditionExpression.append(" and  tuitionStatus= "
								+ Constants.PLACEHOLDER);
						list.add(student.getTuitionStatus());
					}
				}

				// 批量查询学生集合
				if (student.getStatusIds() != null
						&& !"".equals(student.getStatusIds())) {
					hqlConditionExpression.append(" and  status in("
							+ Constants.PLACEHOLDER + ")");
					list.add("$" + student.getStatusIds());
				}
				// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
				// 如果起始状态ID>0,结束状态ID=0;则为无穷大
				// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
				// 如果都大于0,则取交集
				if (student.getStartStatusId() == 0
						&& student.getEndStatusId() > 0) {
					hqlConditionExpression.append(" and  status <"
							+ Constants.PLACEHOLDER);
					list.add(student.getEndStatusId());
				}
				if (student.getStartStatusId() > 0
						&& student.getEndStatusId() == 0) {
					hqlConditionExpression.append(" and  status >"
							+ Constants.PLACEHOLDER);
					list.add(student.getStartStatusId());
				}
				if (student.getStartStatusId() > 0
						&& student.getEndStatusId() > 0) {
					hqlConditionExpression.append(" and  status> "
							+ Constants.PLACEHOLDER + " and status<"
							+ Constants.PLACEHOLDER);
					list.add(student.getStartStatusId());
					list.add(student.getEndStatusId());
				}
				
				//最新跟进日期
				if (student.getStartDate() ==null &&student.getEndDate()!=null) {
					hqlConditionExpression.append(" and  latestFollowUpDate <= "
							+ Constants.PLACEHOLDER);
					list.add(DateUtil.getDate(student.getEndDate(), "yyyy-MM-dd")+" 23:59:59");
				}
				if (student.getStartDate() !=null &&student.getEndDate()==null) {
					hqlConditionExpression.append(" and  latestFollowUpDate >="
							+ Constants.PLACEHOLDER);
					list.add(DateUtil.getDate(student.getStartDate(), "yyyy-MM-dd")+" 00:00:00");
				}
				if (student.getStartDate() !=null &&student.getEndDate()!=null) {
					hqlConditionExpression.append(" and  latestFollowUpDate>= "
							+ Constants.PLACEHOLDER + " and latestFollowUpDate<="
							+ Constants.PLACEHOLDER);
					list.add(DateUtil.getDate(student.getStartDate(), "yyyy-MM-dd")+" 00:00:00");
					list.add(DateUtil.getDate(student.getEndDate(), "yyyy-MM-dd")+" 23:59:59");
				}
				//推送日期
				if (student.getPushStartDate() ==null &&student.getPushEndDate()!=null) {
					hqlConditionExpression.append(" and  pushDate <= "
							+ Constants.PLACEHOLDER);
					list.add(DateUtil.getDate(student.getPushEndDate(), "yyyy-MM-dd")+" 23:59:59");
				}
				if (student.getPushStartDate() !=null &&student.getPushEndDate()==null) {
					hqlConditionExpression.append(" and  pushDate >="
							+ Constants.PLACEHOLDER);
					list.add(DateUtil.getDate(student.getPushStartDate(), "yyyy-MM-dd")+" 00:00:00");
				}
				if (student.getPushStartDate() !=null &&student.getPushEndDate()!=null) {
					hqlConditionExpression.append(" and  pushDate>= "
							+ Constants.PLACEHOLDER + " and pushDate<="
							+ Constants.PLACEHOLDER);
					list.add(DateUtil.getDate(student.getPushStartDate(), "yyyy-MM-dd")+" 00:00:00");
					list.add(DateUtil.getDate(student.getPushEndDate(), "yyyy-MM-dd")+" 23:59:59");
				}
				//推送人
				if(student.getPushId()!=0){
					hqlConditionExpression.append(" and  pushId= "
						+ Constants.PLACEHOLDER);
					list.add(student.getPushId());
				}

				// qq msn
				if (student.getQq() != null && !student.getQq().equals("")) {
					hqlConditionExpression.append("and (qq like"
							+ Constants.PLACEHOLDER + "or msn like"
							+ Constants.PLACEHOLDER + ")");
					list.add("%" + student.getQq() + "%");
					list.add("%" + student.getQq() + "%");
				}
				// 学习中心
				if (student.getBranchId() != 0) {
					hqlConditionExpression.append(" and  branchId= "
							+ Constants.PLACEHOLDER);
					list.add(student.getBranchId());
				}
				// 跟进人
				if (student.getFollowUpId() != 0) {
					hqlConditionExpression.append(" and  followUpId= "
							+ Constants.PLACEHOLDER);
					list.add(student.getFollowUpId());
				}
				// 学生监控状态
				if (student.getMonitorStatus() != -1) {
					hqlConditionExpression.append(" and  monitorStatus= "
							+ Constants.PLACEHOLDER);
					list.add(student.getMonitorStatus());
				}
				if (student.getMonitorStatusIds() != null
						&& !"".equals(student.getStatusIds())) {
					hqlConditionExpression.append(" and  monitorStatus in("
							+ Constants.PLACEHOLDER + ")");
					list.add("$" + student.getMonitorStatusIds());
				}
				
				// 呼叫等级
				if (student.getCallStatusId() != 0) {
					hqlConditionExpression.append(" and  callStatusId= "
							+ Constants.PLACEHOLDER);
					list.add(student.getCallStatusId());
				}
				// 创建人
				if (student.getCreatorId() !=0){
					hqlConditionExpression.append(" and  creatorId= "
						+ Constants.PLACEHOLDER);
					list.add(student.getCreatorId());
				}
				// 创建日期
				if (student.getCreateStartDate() ==null &&student.getCreateEndDate()!=null) {
					hqlConditionExpression.append(" and  createDate <= "
							+ Constants.PLACEHOLDER);
					list.add(DateUtil.getDate(student.getCreateEndDate(), "yyyy-MM-dd")+" 23:59:59");
				}
				if (student.getCreateStartDate() !=null &&student.getCreateEndDate()==null) {
					hqlConditionExpression.append(" and  createDate >="
							+ Constants.PLACEHOLDER);
					list.add(DateUtil.getDate(student.getCreateStartDate(), "yyyy-MM-dd")+" 00:00:00");
				}
				if (student.getCreateStartDate() !=null &&student.getCreateEndDate()!=null) {
					hqlConditionExpression.append(" and  createDate>= "
							+ Constants.PLACEHOLDER + " and createDate<="
							+ Constants.PLACEHOLDER);
					list.add(DateUtil.getDate(student.getCreateStartDate(), "yyyy-MM-dd")+" 00:00:00");
					list.add(DateUtil.getDate(student.getCreateEndDate(), "yyyy-MM-dd")+" 23:59:59");
				}
				//是否查询重复学生
				if(student.getIsEmphasisVerification()>0){
					hqlConditionExpression.append(" and  id in ( "
						+ Constants.PLACEHOLDER+")");
					list.add("$"+studentDao.repeatStudentIds(student.getIsEmphasisVerification(),student,gbatchIds, glevelIds, glmajors));
				}
				//最新监控状态
				if(student.getLastMonitorResult()!=0){
					hqlConditionExpression.append(" and  lastMonitorResult = "+ Constants.PLACEHOLDER);
					list.add(student.getLastMonitorResult());
				}
				//不包括的监控结果
				if(student.getNoLastMonitorResults()!=null){
					hqlConditionExpression.append(" and lastMonitorResult not in ("+ Constants.PLACEHOLDER+")");
					list.add("$"+student.getNoLastMonitorResults());
				}
				//提示回访跟进日期
				if (student.getReturnVisitTimeEnd()!=null) {
					hqlConditionExpression.append(" and  returnVisitTime <= "
							+ Constants.PLACEHOLDER);
					list.add(DateUtil.getDate(student.getReturnVisitTimeEnd(), "yyyy-MM-dd")+" 23:59:59");
				}
				if (student.getReturnVisitTimeBegin()!=null) {
					hqlConditionExpression.append(" and  returnVisitTime >="
							+ Constants.PLACEHOLDER);
					list.add(DateUtil.getDate(student.getReturnVisitTimeBegin(), "yyyy-MM-dd")+" 00:00:00");
				}
				// 跟进时间（全部跟进记录）
				String followUpHql= "select studentId from FollowUp where 1=1 ";
				if (student.getFollowUpTimeBegin() ==null && student.getFollowUpTimeEnd()!=null) {
					followUpHql += " and createdTime <= " + Constants.PLACEHOLDER;
					list.add(DateUtil.getDate(student.getFollowUpTimeEnd(), "yyyy-MM-dd HH:mm:ss")+" ");
				}
				if (student.getFollowUpTimeBegin() !=null && student.getFollowUpTimeEnd()==null) {
					followUpHql += " and createdTime >= " + Constants.PLACEHOLDER;
					list.add(DateUtil.getDate(student.getFollowUpTimeBegin(), "yyyy-MM-dd HH:mm:ss")+" ");
				}
				if (student.getFollowUpTimeBegin() !=null && student.getFollowUpTimeEnd()!=null) {
					followUpHql += " and createdTime >= " + Constants.PLACEHOLDER;
					list.add(DateUtil.getDate(student.getFollowUpTimeBegin(), "yyyy-MM-dd HH:mm:ss")+" ");
					followUpHql += " and createdTime <= " + Constants.PLACEHOLDER;
					list.add(DateUtil.getDate(student.getFollowUpTimeEnd(), "yyyy-MM-dd HH:mm:ss")+" ");
				}
				if(!followUpHql.equals("select studentId from FollowUp where 1=1 ")){
					hqlConditionExpression.append(" and id in ( " + followUpHql + " )");
				}
				p.setHqlConditionExpression(hqlConditionExpression.toString());
				p.setValues(list.toArray());
			}

		return studentDao.getCounts(p);
	}

	/*
	 * 查询学生总集合
	 * 
	 * @see
	 * net.cedu.biz.crm.StudentBiz#findStudentsPageList(net.cedu.entity.crm.
	 * Student, net.cedu.model.page.PageResult)
	 */
	public List<Student> findStudentsPageList(Student student,
			PageResult<Student> pr) throws Exception {
		List<Student> students = null;
		try {
			// Ids集合
			PageParame p = new PageParame(pr);
			StringBuffer hqlConditionExpression = new StringBuffer();
			List<Object> list = null;
			if (student != null) {
				list = new ArrayList<Object>();
				// 学号
				if (student.getNumber() != null
						&& !student.getNumber().equals("")) {
					hqlConditionExpression.append("and number like"
							+ Constants.PLACEHOLDER);
					list.add("%" + student.getNumber() + "%");
				}
				// 姓名
				if (student.getName() != null && !student.getName().equals("")) {
					hqlConditionExpression.append("and name like"
							+ Constants.PLACEHOLDER);
					list.add("%" + student.getName() + "%");
				}
				// 证件号
				if (student.getCertNo() != null
						&& !"".equals(student.getCertNo())) {
					hqlConditionExpression.append("and certNo like"
							+ Constants.PLACEHOLDER);
					list.add("%" + student.getCertNo() + "%");
				}
				// 学历
				if (student.getDegree() != 0) {
					hqlConditionExpression.append("and degree ="
							+ Constants.PLACEHOLDER);
					list.add(student.getDegree());
				}

				// 状态
				if (student.getStatus() != 0) {
					hqlConditionExpression.append("and status="
							+ Constants.PLACEHOLDER);
					list.add(student.getStatus());
				}
				// 性别
				if (student.getGender() != -1) {
					hqlConditionExpression.append("and gender="
							+ Constants.PLACEHOLDER);
					list.add(student.getGender());
				}

				// 数据来源
				if (student.getStudentDataSource() != 0) {
					hqlConditionExpression.append("and studentDataSource="
							+ Constants.PLACEHOLDER);
					list.add(student.getStudentDataSource());
				}
				// 批量数据来源
				if (student.getStudentDataSourceIds() !=null && !student.getStudentDataSourceIds().equals("")){
					hqlConditionExpression.append("and studentDataSource in (" + Constants.PLACEHOLDER + ") ");
					list.add("$" + student.getStudentDataSourceIds());
				}
				// 学生招生途径
				if (student.getEnrollmentSource() != 0) {
					hqlConditionExpression.append("and enrollmentSource="
							+ Constants.PLACEHOLDER);
					list.add(student.getEnrollmentSource());
				}
				// 院校
				if (student.getAcademyId() != 0) {
					hqlConditionExpression.append("and academyId="
							+ Constants.PLACEHOLDER);
					list.add(student.getAcademyId());
				}
				// 院校Id集合
				if (student.getAcademyIds() != null
						&& !student.getAcademyIds().equals("")) {
					hqlConditionExpression.append("and academyId in ("
							+ Constants.PLACEHOLDER + ") ");
					list.add("$" + student.getAcademyIds());
				}
				// 是否呼叫过
				if (student.getCallStatus() == 0) {
					hqlConditionExpression.append("and callStatus="
							+ Constants.PLACEHOLDER);
					list.add(0);
				} else if (student.getCallStatus() > 0) {
					hqlConditionExpression.append("and callStatus>"
							+ Constants.PLACEHOLDER);
					list.add(0);
				}
				// 用户
				if (student.getUserId() == -1) {
					hqlConditionExpression.append("and userId="
							+ Constants.PLACEHOLDER);
					list.add(0);
				} else {
					if (student.getUserId() != 0) {
						hqlConditionExpression.append("and userId="
								+ Constants.PLACEHOLDER);
						list.add(student.getUserId());
					}
				}
				// 手机
				if (student.getMobile() != null
						&& !student.getMobile().equals("")) {
					hqlConditionExpression.append("and  mobile like"
							+ Constants.PLACEHOLDER);
					list.add("%" + student.getMobile() + "%");
				}
				// 手机或座机
				if (student.getPhone() != null
						&& !student.getPhone().equals("")) {
					hqlConditionExpression.append("and (phone like"
							+ Constants.PLACEHOLDER + "or mobile like"
							+ Constants.PLACEHOLDER + ")");
					list.add("%" + student.getPhone() + "%");
					list.add("%" + student.getPhone() + "%");
				}
				// 邮箱
				if (student.getEmail() != null
						&& !"".equals(student.getEmail())) {
					hqlConditionExpression.append("and email like"
							+ Constants.PLACEHOLDER);
					list.add("%" + student.getEmail() + "%");
				}
				// 所在城市
				if (student.getLivingPlace() != null
						&& !"0".equals(student.getLivingPlace())
						&& !"".equals(student.getLivingPlace())) {

					hqlConditionExpression.append("and livingPlace="
							+ Constants.PLACEHOLDER);
					list.add(student.getLivingPlace());
				}
				// 批次
				if (student.getEnrollmentBatchId() != 0) {
					hqlConditionExpression.append(" and  enrollmentBatchId= "
							+ Constants.PLACEHOLDER);
					list.add(student.getEnrollmentBatchId());
				}
				// 全局批次
				String gbatchIds = "";
				StringBuffer gbatchIdsSB = new StringBuffer("");
				if (student.getGlbtach() != 0) {
					List<AcademyEnrollBatch> aeblst = academyenrollbatchBiz
							.findAcademyEnrollBatchByGId(student.getGlbtach());
					
					if (null != aeblst && aeblst.size() > 0) {
						for (int i = 0; i < aeblst.size(); i++) {
//							gbatchIds += "," + aeblst.get(i).getId();
							if(gbatchIdsSB.toString().equals(""))
							{
								gbatchIdsSB = new StringBuffer(aeblst.get(i).getId()+"");
							}
							else
							{
								gbatchIdsSB.append("," + aeblst.get(i).getId());
							}
						}
						if(gbatchIdsSB.toString().equals(""))
						{
							gbatchIdsSB = new StringBuffer("0");
						}
						gbatchIds = gbatchIdsSB.toString();
						hqlConditionExpression.append(" and ( enrollmentBatchId in ( "
								+ Constants.PLACEHOLDER + " ) ");
						list.add("$" + gbatchIds);
						hqlConditionExpression.append(" or  globalBatch = "+ Constants.PLACEHOLDER+") ");
						list.add(student.getGlbtach());
					}
					else
					{
						hqlConditionExpression.append(" and  globalBatch = "+ Constants.PLACEHOLDER);
						list.add(student.getGlbtach());
					}
				}
				if(gbatchIdsSB.toString().equals(""))
				{
					gbatchIdsSB = new StringBuffer("0");
				}
				gbatchIds = gbatchIdsSB.toString();
				// 层次
				if (student.getLevelId() != 0) {
					hqlConditionExpression.append(" and  levelId= "
							+ Constants.PLACEHOLDER);
					list.add(student.getLevelId());
				}
				// 全局层次
				String glevelIds = "";
				StringBuffer glevelIdsSB = new StringBuffer("");
				if (student.getGllevel() != 0) {
					List<AcademyLevel> allst = academylevelBiz
							.findByGllevel(student.getGllevel());
					
					if (null != allst && allst.size() > 0) {
						for (int i = 0; i < allst.size(); i++) {
//							glevelIds += "," + allst.get(i).getId();
							if(glevelIdsSB.toString().equals(""))
							{
								glevelIdsSB = new StringBuffer(allst.get(i).getId()+"");
							}
							else
							{
								glevelIdsSB.append("," + allst.get(i).getId());
							}
						}
						if(glevelIdsSB.toString().equals(""))
						{
							glevelIdsSB = new StringBuffer("0");
						}
						glevelIds = glevelIdsSB.toString();
						hqlConditionExpression.append(" and  levelId in ( "
								+ Constants.PLACEHOLDER + " ) ");
						list.add("$" + glevelIds);
					}
				}
				if(glevelIdsSB.toString().equals(""))
				{
					glevelIdsSB = new StringBuffer("0");
				}
				glevelIds = glevelIdsSB.toString();
				// 专业
				if (student.getMajorId() != 0) {
					hqlConditionExpression.append(" and  majorId= "
							+ Constants.PLACEHOLDER);
					list.add(student.getMajorId());
				}
				// 基础专业
				String glmajors = "";
				StringBuffer glmajorsSB = new StringBuffer("");
				if (student.getGlmajor() != 0) {
					List<Major> majorList = this.majorbiz
							.findMajorListByBaseMajorId(student.getGlmajor());
					
					if (null != majorList && majorList.size() > 0) {
						for (int i = 0; i < majorList.size(); i++) {
//							glmajors += "," + majorList.get(i).getId();
							if(glmajorsSB.toString().equals(""))
							{
								glmajorsSB = new StringBuffer(majorList.get(i).getId()+"");
							}
							else
							{
								glmajorsSB.append("," + majorList.get(i).getId());
							}
						}
						if(glmajorsSB.toString().equals(""))
						{
							glmajorsSB = new StringBuffer("0");
						}
						glmajors = glmajorsSB.toString();
						hqlConditionExpression.append(" and  majorId in ( "
								+ Constants.PLACEHOLDER + " ) ");
						list.add("$" + glmajors);
					} else{
						hqlConditionExpression.append(" and  majorId in ( "
							+ Constants.PLACEHOLDER + " ) ");
					list.add("$0");
					}
				}
				if(glmajorsSB.toString().equals(""))
				{
					glmajorsSB = new StringBuffer("0");
				}
				glmajors = glmajorsSB.toString();
				// 市场途径
				if (student.getEnrollmentWay() != 0) {
					hqlConditionExpression.append(" and  enrollmentWay= "
							+ Constants.PLACEHOLDER);
					list.add(student.getEnrollmentWay());
				}
				// 监控状态
				if (student.getMonitorStatus() != -1) {
					hqlConditionExpression.append(" and  monitorStatus= "
							+ Constants.PLACEHOLDER);
					list.add(student.getMonitorStatus());
				}
				// 缴费状态
				if (student.getTuitionStatus() != -1) {
					if (student.getTuitionStatus() > 1
							&& student.getTuitionStatus() < 9999) {
						hqlConditionExpression.append(" and  tuitionStatus > "
								+ Constants.PLACEHOLDER + " and tuitionStatus<"
								+ Constants.PLACEHOLDER);
						list.add(Constants.STU_TUITION_STATUS_SHOU_CI_JIAO_FEI);
						list.add(Constants.STU_TUITION_STATUS_JIAO_FEI_WAN_CHENG);
					} else {
						hqlConditionExpression.append(" and  tuitionStatus= "
								+ Constants.PLACEHOLDER);
						list.add(student.getTuitionStatus());
					}
				}

				// 批量查询学生集合
				if (student.getStatusIds() != null
						&& !"".equals(student.getStatusIds())) {
					hqlConditionExpression.append(" and  status in("
							+ Constants.PLACEHOLDER + ")");
					list.add("$" + student.getStatusIds());
				}
				// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
				// 如果起始状态ID>0,结束状态ID=0;则为无穷大
				// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
				// 如果都大于0,则取交集
				if (student.getStartStatusId() == 0
						&& student.getEndStatusId() > 0) {
					hqlConditionExpression.append(" and  status <"
							+ Constants.PLACEHOLDER);
					list.add(student.getEndStatusId());
				}
				if (student.getStartStatusId() > 0
						&& student.getEndStatusId() == 0) {
					hqlConditionExpression.append(" and  status >"
							+ Constants.PLACEHOLDER);
					list.add(student.getStartStatusId());
				}
				if (student.getStartStatusId() > 0
						&& student.getEndStatusId() > 0) {
					hqlConditionExpression.append(" and  status> "
							+ Constants.PLACEHOLDER + " and status<"
							+ Constants.PLACEHOLDER);
					list.add(student.getStartStatusId());
					list.add(student.getEndStatusId());
				}
				
				//最新跟进日期
				if (student.getStartDate() ==null &&student.getEndDate()!=null) {
					hqlConditionExpression.append(" and  latestFollowUpDate <= "
							+ Constants.PLACEHOLDER);
					list.add(DateUtil.getDate(student.getEndDate(), "yyyy-MM-dd")+" 23:59:59");
				}
				if (student.getStartDate() !=null &&student.getEndDate()==null) {
					hqlConditionExpression.append(" and  latestFollowUpDate >="
							+ Constants.PLACEHOLDER);
					list.add(DateUtil.getDate(student.getStartDate(), "yyyy-MM-dd")+" 00:00:00");
				}
				if (student.getStartDate() !=null &&student.getEndDate()!=null) {
					hqlConditionExpression.append(" and  latestFollowUpDate>= "
							+ Constants.PLACEHOLDER + " and latestFollowUpDate<="
							+ Constants.PLACEHOLDER);
					list.add(DateUtil.getDate(student.getStartDate(), "yyyy-MM-dd")+" 00:00:00");
					list.add(DateUtil.getDate(student.getEndDate(), "yyyy-MM-dd")+" 23:59:59");
				}
				//推送日期
				if (student.getPushStartDate() ==null &&student.getPushEndDate()!=null) {
					hqlConditionExpression.append(" and  pushDate <= "
							+ Constants.PLACEHOLDER);
					list.add(DateUtil.getDate(student.getPushEndDate(), "yyyy-MM-dd")+" 23:59:59");
				}
				if (student.getPushStartDate() !=null &&student.getPushEndDate()==null) {
					hqlConditionExpression.append(" and  pushDate >="
							+ Constants.PLACEHOLDER);
					list.add(DateUtil.getDate(student.getPushStartDate(), "yyyy-MM-dd")+" 00:00:00");
				}
				if (student.getPushStartDate() !=null &&student.getPushEndDate()!=null) {
					hqlConditionExpression.append(" and  pushDate>= "
							+ Constants.PLACEHOLDER + " and pushDate<="
							+ Constants.PLACEHOLDER);
					list.add(DateUtil.getDate(student.getPushStartDate(), "yyyy-MM-dd")+" 00:00:00");
					list.add(DateUtil.getDate(student.getPushEndDate(), "yyyy-MM-dd")+" 23:59:59");
				}
				//推送人
				if(student.getPushId()!=0){
					hqlConditionExpression.append(" and  pushId= "
						+ Constants.PLACEHOLDER);
					list.add(student.getPushId());
				}

				// qq msn
				if (student.getQq() != null && !student.getQq().equals("")) {
					hqlConditionExpression.append("and (qq like"
							+ Constants.PLACEHOLDER + "or msn like"
							+ Constants.PLACEHOLDER + ")");
					list.add("%" + student.getQq() + "%");
					list.add("%" + student.getQq() + "%");
				}
				// 学习中心
				if (student.getBranchId() != 0) {
					hqlConditionExpression.append(" and  branchId= "
							+ Constants.PLACEHOLDER);
					list.add(student.getBranchId());
				}
				// 跟进人
				if (student.getFollowUpId() != 0) {
					hqlConditionExpression.append(" and  followUpId= "
							+ Constants.PLACEHOLDER);
					list.add(student.getFollowUpId());
				}
				// 学生监控状态
				if (student.getMonitorStatus() != -1) {
					hqlConditionExpression.append(" and  monitorStatus= "
							+ Constants.PLACEHOLDER);
					list.add(student.getMonitorStatus());
				}
				if (student.getMonitorStatusIds() != null
						&& !"".equals(student.getStatusIds())) {
					hqlConditionExpression.append(" and  monitorStatus in("
							+ Constants.PLACEHOLDER + ")");
					list.add("$" + student.getMonitorStatusIds());
				}
				
				// 呼叫等级
				if (student.getCallStatusId() != 0) {
					hqlConditionExpression.append(" and  callStatusId= "
							+ Constants.PLACEHOLDER);
					list.add(student.getCallStatusId());
				}
				// 创建人
				if (student.getCreatorId() !=0){
					hqlConditionExpression.append(" and  creatorId= "
						+ Constants.PLACEHOLDER);
					list.add(student.getCreatorId());
				}
				// 创建日期
				if (student.getCreateStartDate() ==null &&student.getCreateEndDate()!=null) {
					hqlConditionExpression.append(" and  createDate <= "
							+ Constants.PLACEHOLDER);
					list.add(DateUtil.getDate(student.getCreateEndDate(), "yyyy-MM-dd")+" 23:59:59");
				}
				if (student.getCreateStartDate() !=null &&student.getCreateEndDate()==null) {
					hqlConditionExpression.append(" and  createDate >="
							+ Constants.PLACEHOLDER);
					list.add(DateUtil.getDate(student.getCreateStartDate(), "yyyy-MM-dd")+" 00:00:00");
				}
				if (student.getCreateStartDate() !=null &&student.getCreateEndDate()!=null) {
					hqlConditionExpression.append(" and  createDate>= "
							+ Constants.PLACEHOLDER + " and createDate<="
							+ Constants.PLACEHOLDER);
					list.add(DateUtil.getDate(student.getCreateStartDate(), "yyyy-MM-dd")+" 00:00:00");
					list.add(DateUtil.getDate(student.getCreateEndDate(), "yyyy-MM-dd")+" 23:59:59");
				}
				//是否查询重复学生
				if(student.getIsEmphasisVerification()>0){
					hqlConditionExpression.append(" and  id in ( "
						+ Constants.PLACEHOLDER+")");
					list.add("$"+studentDao.repeatStudentIds(student.getIsEmphasisVerification(),student,gbatchIds, glevelIds, glmajors));
				}
				//最新监控状态
				if(student.getLastMonitorResult()!=0){
					hqlConditionExpression.append(" and  lastMonitorResult = "+ Constants.PLACEHOLDER);
					list.add(student.getLastMonitorResult());
				}
				//不包括的监控结果
				if(student.getNoLastMonitorResults()!=null){
					hqlConditionExpression.append(" and lastMonitorResult not in ("+ Constants.PLACEHOLDER+")");
					list.add("$"+student.getNoLastMonitorResults());
				}
				//提示回访跟进日期
				if (student.getReturnVisitTimeEnd()!=null) {
					hqlConditionExpression.append(" and  returnVisitTime <= "
							+ Constants.PLACEHOLDER);
					list.add(DateUtil.getDate(student.getReturnVisitTimeEnd(), "yyyy-MM-dd")+" 23:59:59");
				}
				if (student.getReturnVisitTimeBegin()!=null) {
					hqlConditionExpression.append(" and  returnVisitTime >="
							+ Constants.PLACEHOLDER);
					list.add(DateUtil.getDate(student.getReturnVisitTimeBegin(), "yyyy-MM-dd")+" 00:00:00");
				}
				// 跟进时间（全部跟进记录）
				String followUpHql= "select studentId from FollowUp where 1=1 ";
				if (student.getFollowUpTimeBegin() ==null && student.getFollowUpTimeEnd()!=null) {
					followUpHql += " and createdTime <= " + Constants.PLACEHOLDER;
					list.add(DateUtil.getDate(student.getFollowUpTimeEnd(), "yyyy-MM-dd HH:mm:ss")+" ");
				}
				if (student.getFollowUpTimeBegin() !=null && student.getFollowUpTimeEnd()==null) {
					followUpHql += " and createdTime >= " + Constants.PLACEHOLDER;
					list.add(DateUtil.getDate(student.getFollowUpTimeBegin(), "yyyy-MM-dd HH:mm:ss")+" ");
				}
				if (student.getFollowUpTimeBegin() !=null && student.getFollowUpTimeEnd()!=null) {
					followUpHql += " and createdTime >= " + Constants.PLACEHOLDER;
					list.add(DateUtil.getDate(student.getFollowUpTimeBegin(), "yyyy-MM-dd HH:mm:ss")+" ");
					followUpHql += " and createdTime <= " + Constants.PLACEHOLDER;
					list.add(DateUtil.getDate(student.getFollowUpTimeEnd(), "yyyy-MM-dd HH:mm:ss")+" ");
				}
				if(!followUpHql.equals("select studentId from FollowUp where 1=1 ")){
					hqlConditionExpression.append(" and id in ( " + followUpHql + " )");
				}
				p.setHqlConditionExpression(hqlConditionExpression.toString());
				p.setValues(list.toArray());
			}
			Long[] studentIds = studentDao.getIDs(p);

			if (studentIds != null && studentIds.length != 0) {
				students = new ArrayList<Student>();
				Student d = null;
				for (Long id : studentIds) {
					if (null != (d = studentDao.findById(Integer.parseInt(id.toString())))) {
						
						//学习中心
						Branch branch=branchBiz.findBranchById(d.getBranchId());
						if(branch!=null){
							d.setBranchName(branch.getName());
						}
						//市场途径
						BaseDict basedict = basedictBiz.findBaseDictById(d.getEnrollmentWay());
						if(basedict!=null)
						{
							d.setEnrollmentWayName(basedict.getName());
						}
						//招生途径
						EnrollmentSource enrollmentsource = enrollmentsourceBiz.findEnrollmentSourceById(d.getEnrollmentSource());
						if(enrollmentsource!=null)
						{
							d.setEnrollmentSourceName1(enrollmentsource.getName());
						}
						
						Academy academy = academyBiz.findAcademyById(d
								.getAcademyId());

						if (academy != null) {
							d.setSchoolName(academy.getName());
						}
						// 数据来源名称

						AcademyEnrollBatch academyenrollbatch = academyenrollbatchBiz
								.findAcademyEnrollBatchById(d
										.getEnrollmentBatchId());
						if (academyenrollbatch != null) {
							d.setAcademyenrollbatchName(academyenrollbatch
									.getEnrollmentName());
						}

						Level level = levelbiz.findLevelById(d.getLevelId());
						if (level != null) {
							d.setLevelName(level.getName());
						}

						Major major = majorbiz.findMajorById(d.getMajorId());
						if (major != null)
							d.setMajorName(major.getName());
						// }

						User user = null;
						user = userDao.findById(d.getUserId());
						if (user != null) {
							d.setFollowUpName(user.getFullName());
						}
//						// 跟进人与上一跟进人
//						if (d.getFollowUpId() != 0) {
//							user = userDao.findById(d.getUserId());
//							if (user != null) {
//								d.setFollowUpName(user.getFullName());
//							}
//
//						}
						if (d.getUpFollowUpId() != 0) {
							user = userDao.findById(d.getUpFollowUpId());
							if (user != null) {
								d.setUpFollowUpName(user.getFullName());
							}
						}
						//推送人
						if(d.getPushId()!=0){
							user = userDao.findById(d.getPushId());
							if (user != null) {
								d.setPushName(user.getFullName());
							}
						}
						//全局批次
						if(d.getGlobalBatch()!=0)
						{
							GlobalEnrollBatch globalEnrollBatch = globalEnrollBatchBiz.findGlobalEnrollBatchById(d.getGlobalBatch());
							if(globalEnrollBatch!=null)
							{
								d.setGlobalBatchName(globalEnrollBatch.getBatch());
							}
						}

						students.add(d);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return students;
	}
	
	/*
	 * 查询学生集合查询条件（姓名,院校，批次，层次，专业） 增加学生状态查询条件
	 * 
	 * @param student 查询条件
	 * 
	 * @param pr 分页实体
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	public List<Student> findStudentsByParam(Student student) throws Exception {
		List<Student> studentlst = null;
		// Ids集合
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (student.getName() != null && !student.getName().equals("")) {
			hqlparam += " and  name like " + Constants.PLACEHOLDER;
			list.add("%" + student.getName().trim() + "%");
		}
		if (student.getAcademyId() != 0) {
			hqlparam += " and  academyId= " + Constants.PLACEHOLDER;
			list.add(student.getAcademyId());
		}
		if (student.getBranchId() != 0) {
			hqlparam += " and  branchId= " + Constants.PLACEHOLDER;
			list.add(student.getBranchId());
		}
		if (student.getEnrollmentBatchId() != 0) {
			hqlparam += " and  enrollmentBatchId= " + Constants.PLACEHOLDER;
			list.add(student.getEnrollmentBatchId());
		}
		if (student.getLevelId() != 0) {
			hqlparam += " and  levelId= " + Constants.PLACEHOLDER;
			list.add(student.getLevelId());
		}
		if (student.getMajorId() != 0) {
			hqlparam += " and  majorId= " + Constants.PLACEHOLDER;
			list.add(student.getMajorId());
		}
		if (student.getGlobalBatch() != 0) {
			hqlparam += " and  globalBatch= " + Constants.PLACEHOLDER;
			list.add(student.getGlobalBatch());
		}
		// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
		// 如果起始状态ID>0,结束状态ID=0;则为无穷大
		// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
		// 如果都大于0,则取交集
		if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0) {
			hqlparam += " and  status <" + Constants.PLACEHOLDER;
			list.add(student.getEndStatusId());
		}
		if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0) {
			hqlparam += " and  status >" + Constants.PLACEHOLDER;
			list.add(student.getStartStatusId());
		}
		if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0) {
			hqlparam += " and  status> " + Constants.PLACEHOLDER
					+ " and status<" + Constants.PLACEHOLDER;
			list.add(student.getStartStatusId());
			list.add(student.getEndStatusId());
		}
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());

		Long[] studentIds = studentDao.getIDs(p);

		if (studentIds != null && studentIds.length != 0) {
			studentlst = new ArrayList<Student>();
			for (int i = 0; i < studentIds.length; i++) {
				Student s = this.findStudentById(Integer.valueOf(studentIds[i]
						.toString()));
				Student students = s;
				Academy academy = academyBiz.findAcademyById(students
						.getAcademyId());
				if (academy != null) {
					students.setSchoolName(academy.getName());
				}
				AcademyEnrollBatch academyenrollbatch = academyenrollbatchBiz
						.findAcademyEnrollBatchById(students
								.getEnrollmentBatchId());
				if (academyenrollbatch != null) {
					students.setAcademyenrollbatchName(academyenrollbatch
							.getEnrollmentName());
				}
				Level level = levelbiz.findLevelById(students.getLevelId());
				if (level != null) {
					students.setLevelName(level.getName());
				}
				Major major = majorbiz.findMajorById(students.getMajorId());
				if (major != null) {
					students.setMajorName(major.getName());
				}
				studentlst.add(students);
			}
		}

		return studentlst;
	}
	
	/*
	 * 查询学生集合(xiao)
	 * 
	 * @see
	 * net.cedu.biz.crm.StudentBiz#findStudentListsByParams(net.cedu.entity.crm.
	 * Student, net.cedu.model.page.PageResult)
	 */
	public List<Student> findStudentListsByParams(Student student,
			PageResult<Student> pr) throws Exception {
		List<Student> students = null;
		try {
			// Ids集合
			PageParame p = new PageParame(pr);
			String hqlConditionExpression = "";
			List<Object> list = null;
			if (student != null) {
				list = new ArrayList<Object>();
				// 学号
				if (student.getNumber() != null
						&& !student.getNumber().equals("")) {
					hqlConditionExpression += "and number like"
							+ Constants.PLACEHOLDER;
					list.add("%" + student.getNumber() + "%");
				}
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
				// 学历
				if (student.getDegree() != 0) {
					hqlConditionExpression += "and degree ="
							+ Constants.PLACEHOLDER;
					list.add(student.getDegree());
				}

				// 状态
				if (student.getStatus() != 0) {
					hqlConditionExpression += "and status="
							+ Constants.PLACEHOLDER;
					list.add(student.getStatus());
				}
				// 性别
				if (student.getGender() != -1) {
					hqlConditionExpression += "and gender="
							+ Constants.PLACEHOLDER;
					list.add(student.getGender());
				}

				// 数据来源
				if (student.getStudentDataSource() != 0) {
					hqlConditionExpression += "and studentDataSource="
							+ Constants.PLACEHOLDER;
					list.add(student.getStudentDataSource());
				}
				// 学生招生途径
				if (student.getEnrollmentSource() != 0) {
					hqlConditionExpression += "and enrollmentSource="
							+ Constants.PLACEHOLDER;
					list.add(student.getEnrollmentSource());
				}
				// 院校
				if (student.getAcademyId() != 0) {
					hqlConditionExpression += "and academyId="
							+ Constants.PLACEHOLDER;
					list.add(student.getAcademyId());
				}
				// 院校Id集合
				if (student.getAcademyIds() != null
						&& !student.getAcademyIds().equals("")) {
					hqlConditionExpression += "and academyId in ("
							+ Constants.PLACEHOLDER + ") ";
					list.add("$" + student.getAcademyIds());
				}
				// 是否呼叫过
				if (student.getCallStatus() == 0) {
					hqlConditionExpression += "and callStatus="
							+ Constants.PLACEHOLDER;
					list.add(0);
				} else if (student.getCallStatus() > 0) {
					hqlConditionExpression += "and callStatus>"
							+ Constants.PLACEHOLDER;
					list.add(0);
				}
				// 用户
				if (student.getUserId() != 0) {
					hqlConditionExpression += "and userId="
							+ Constants.PLACEHOLDER;
					list.add(student.getUserId());
				}
				// 手机
				if (student.getMobile() != null
						&& !student.getMobile().equals("")) {
					hqlConditionExpression += "and  mobile like"
							+ Constants.PLACEHOLDER;
					list.add("%" + student.getMobile() + "%");
				}
				// 手机或座机
				if (student.getPhone() != null
						&& !student.getPhone().equals("")) {
					hqlConditionExpression += "and (phone like"
							+ Constants.PLACEHOLDER + "or mobile like"
							+ Constants.PLACEHOLDER + ")";
					list.add("%" + student.getPhone() + "%");
					list.add("%" + student.getPhone() + "%");
				}
				// 邮箱
				if (student.getEmail() != null
						&& !"".equals(student.getEmail())) {
					hqlConditionExpression += "and email like"
							+ Constants.PLACEHOLDER;
					list.add("%" + student.getEmail() + "%");
				}
				// 所在城市
				if (student.getLivingPlace() != null
						&& !"0".equals(student.getLivingPlace())
						&& !"".equals(student.getLivingPlace())) {

					hqlConditionExpression += "and livingPlace="
							+ Constants.PLACEHOLDER;
					list.add(student.getLivingPlace());
				}
				// 批次
				if (student.getEnrollmentBatchId() != 0) {
					hqlConditionExpression += " and  enrollmentBatchId= "
							+ Constants.PLACEHOLDER;
					list.add(student.getEnrollmentBatchId());
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
				// 市场途径
				if (student.getEnrollmentWay() != 0) {
					hqlConditionExpression += " and  enrollmentWay= "
							+ Constants.PLACEHOLDER;
					list.add(student.getEnrollmentWay());
				}
				// 批量查询学生集合
				if (student.getStatusIds() != null
						&& !"".equals(student.getStatusIds())) {
					hqlConditionExpression += " and  status in("
							+ Constants.PLACEHOLDER + ")";
					list.add("$" + student.getStatusIds());
				}
				// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
				// 如果起始状态ID>0,结束状态ID=0;则为无穷大
				// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
				// 如果都大于0,则取交集
				if (student.getStartStatusId() == 0
						&& student.getEndStatusId() > 0) {
					hqlConditionExpression += " and  status <"
							+ Constants.PLACEHOLDER;
					list.add(student.getEndStatusId());
				}
				if (student.getStartStatusId() > 0
						&& student.getEndStatusId() == 0) {
					hqlConditionExpression += " and  status >"
							+ Constants.PLACEHOLDER;
					list.add(student.getStartStatusId());
				}
				if (student.getStartStatusId() > 0
						&& student.getEndStatusId() > 0) {
					hqlConditionExpression += " and  status> "
							+ Constants.PLACEHOLDER + " and status<"
							+ Constants.PLACEHOLDER;
					list.add(student.getStartStatusId());
					list.add(student.getEndStatusId());
				}

				// qq msn
				if (student.getQq() != null && !student.getQq().equals("")) {
					hqlConditionExpression += "and (qq like"
							+ Constants.PLACEHOLDER + "or msn like"
							+ Constants.PLACEHOLDER + ")";
					list.add("%" + student.getQq() + "%");
					list.add("%" + student.getQq() + "%");
				}
				// 学习中心
				if (student.getBranchId() != 0) {
					hqlConditionExpression += " and  branchId= "
							+ Constants.PLACEHOLDER;
					list.add(student.getBranchId());
				}
				p.setHqlConditionExpression(hqlConditionExpression);
				p.setValues(list.toArray());

			}
			Long[] studentIds = studentDao.getIDs(p);

			if (studentIds != null && studentIds.length != 0) {
				students = new ArrayList<Student>();
				Student d = null;
				for (Long id : studentIds) {
					if (null != (d = studentDao.findById(Integer.parseInt(id
							.toString())))) {
						Academy academy = academyBiz.findAcademyById(d
								.getAcademyId());

						if (academy != null) {
							d.setSchoolName(academy.getName());
						}
						// 保存数据来源名称
						BaseDict bd = baseDictBiz.findBaseDictById(d
								.getEnrollmentSource());
						if (null != bd) {
							d.setEnrollmentSourceName(bd.getName());
						}
						
						//学习中心
						Branch branch=this.branchBiz.findBranchById(d.getBranchId());
						if(branch!=null)
						{
							d.setBranchName(branch.getName());
						}

						AcademyEnrollBatch academyenrollbatch = academyenrollbatchBiz
								.findAcademyEnrollBatchById(d
										.getEnrollmentBatchId());
						if (academyenrollbatch != null) {
							d.setAcademyenrollbatchName(academyenrollbatch
									.getEnrollmentName());
						}

						Level level = levelbiz.findLevelById(d.getLevelId());
						if (level != null) {
							d.setLevelName(level.getName());
						}

						Major major = majorbiz.findMajorById(d.getMajorId());
						if (major != null)
							d.setMajorName(major.getName());
						// }

						User user = null;
						// 跟进人与上一跟进人
						if (d.getFollowUpId() != 0) {
							user = userDao.findById(d.getFollowUpId());
							if (user != null) {
								d.setFollowUpName(user.getFullName());
							}

						}
						if (d.getUpFollowUpId() != 0) {
							user = userDao.findById(d.getUpFollowUpId());
							if (user != null) {
								d.setUpFollowUpName(user.getFullName());
							}
						}
						// 测试费
						List<PendingFeePayment> pendinglist = this.pendingFeePaymentBiz
								.findStudentsPendingFeePaymentListByStudentIdFeeSubjectId(
										d.getId(),
										FeeSubjectEnum.TestFee.value());
						if (pendinglist != null && pendinglist.size() > 0) {
							d.setPendingTestCounts(pendinglist.size());
						}

						students.add(d);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return students;
	}

	/*
	 * 更新学生
	 * 
	 * @see
	 * net.cedu.biz.crm.StudentBiz#updateStudent(net.cedu.entity.crm.Student,
	 * net.cedu.entity.crm.FollowUp, net.cedu.entity.crm.OperationLog)
	 */
	public void updateStudent(Student student, FollowUp followUp,
			OperationLog operationLog) throws Exception {
		if (student != null) {
			// 层次
			if (student.getLevelId() != 0) {
				AcademyLevel academyLevel = academylevelBiz.findById(student
						.getLevelId());
				if (academyLevel != null) {
					student.setLevelId(academyLevel.getLevelId());
				}
			}
			//全局批次(edited by dongminghao 根据招生批次匹配学生全局批次)
			if(student.getEnrollmentBatchId()!=0)
			{
				AcademyEnrollBatch aeb = academyenrollbatchBiz.findAcademyEnrollBatchById(student.getEnrollmentBatchId());
				if(aeb!=null)
				{
					student.setGlobalBatch(aeb.getGlobalEnrollBatchId());
				}
			}

			// //专业
			// if (student.getMajorId()!=0) {
			// AcademyMajor academyMajor
			// =academymajorBiz.findById(student.getMajorId());
			// student.setMajorId(academyMajor.getMajorId());
			// }
			// 去掉空格
			student.setName(student.getName().trim());
			studentDao.update(student);
		}

		if (followUp != null) {
			if (followUp.getRemark() != null
					&& !"".equals(followUp.getRemark())) {
				followUpDao.save(followUp);
			}
		}

		if (operationLog != null) {
			operationLogDao.save(operationLog);
		}

	}

	/*
	 * 通过电话号码查询学生信息
	 * 
	 * @see net.cedu.biz.crm.StudentBiz#findStudentsByPhone(java.lang.String)
	 */
	public List<Student> findStudentsByPhone(String phone) {
		List<Student> students = null;
		students = studentDao.getByProperty("and (phone="
				+ Constants.PLACEHOLDER + " or mobile=" + Constants.PLACEHOLDER
				+ ")", new Object[] { phone, phone });
		return students != null && students.size() != 0 ? students : null;
	}

	/*
	 * 统计学生各状态的数量
	 * 
	 * @see net.cedu.biz.crm.StudentBiz#searchStatusCounts(int, int[])
	 */
	public int searchStatusCounts(int userId, int status, int call_status)
			throws Exception {
		// 查询所有呼叫状态
		// String sidsString = "";
		// sidsString = sidsString.substring(0, sidsString.lastIndexOf(","));
		PageParame p = new PageParame();
		if (call_status == -1) {
			if (status == Constants.STU_CALL_STATUS_YI_DAO_MING) {
				p.setHqlConditionExpression(" and callStatus>0 and status>="
						+ Constants.PLACEHOLDER + " and userId ="
						+ Constants.PLACEHOLDER);
				p.setValues(new Object[] { status, userId });
			} else {
				p.setHqlConditionExpression(" and callStatus>0 and status="
						+ Constants.PLACEHOLDER + " and userId ="
						+ Constants.PLACEHOLDER);
				p.setValues(new Object[] { status, userId });
			}

		} else {
			p.setHqlConditionExpression(" and status=" + Constants.PLACEHOLDER
					+ " and callStatusId=" + Constants.PLACEHOLDER
					+ " and userId=" + Constants.PLACEHOLDER);
			p.setValues(new Object[] { status, call_status, userId });
		}
		return studentDao.getCounts(p);
	}

	// /*
	// * 查询所有分配给我的学生数量
	// *
	// * @see
	// * net.cedu.biz.crm.StudentBiz#findUserStudentsPageCount(net.cedu.entity
	// * .crm.UserStudent, net.cedu.model.page.PageResult)
	// */
	// public int findUserStudentsPageCount(UserStudent userStudent,
	// PageResult<UserStudent> pr) throws Exception {
	// PageParame p = new PageParame();
	// p.setOrder(pr.getOrder());
	// if (userStudent != null) {
	// p.setHqlConditionExpression(" and userId=" + Constants.PLACEHOLDER);
	// p.setValues(new Object[] { userStudent.getUserId() });
	// }
	// return userStudentDao.getCounts(p);
	// }

	// /*
	// * 查询所有分配给我的学生集合
	// *
	// * @see
	// * net.cedu.biz.crm.StudentBiz#findUserStudentsPageList(net.cedu.entity.
	// * crm.UserStudent, net.cedu.model.page.PageResult)
	// */
	// public List<Student> findUserStudentsPageList(Student student,
	// PageResult<UserStudent> pr) throws Exception {
	// List<UserStudent> userStudents = null;
	// // Ids集合
	// PageParame p = new PageParame();
	// p.setCurrentPageIndex(pr.getCurrentPageIndex());
	// p.setPageSize(pr.getPageSize());
	// p.setOrder(pr.getOrder());
	// p.setSort(pr.getSort());
	// // 分页或者不分页
	// p.setPage(pr.isPage());
	// if (userStudent != null) {
	// p.setHqlConditionExpression(" and userId=" + Constants.PLACEHOLDER);
	// p.setValues(new Object[] { userStudent.getUserId() });
	// }
	//
	// Long[] userIdLongs = userStudentDao.getIDs(p);
	// if (userIdLongs != null && userIdLongs.length != 0) {
	// userStudents = new ArrayList<UserStudent>();
	// for (int i = 0; i < userIdLongs.length; i++) {
	// // 内存获取成绩
	// UserStudent us = userStudentDao.findById(Integer
	// .parseInt(userIdLongs[i].toString()));
	// if (us != null) {
	// Student student = studentDao.findById(us.getStudentId());
	// if (student != null) {
	// us.setCreateDate(student.getCreateDate());
	// us.setFullName(student.getName());
	// us.setStatus(student.getStatus());
	// }
	// userStudents.add(us);
	// }
	// }
	// }
	//
	// return userStudents;
	// }

	/*
	 * 分配学生
	 * 
	 * @see net.cedu.biz.crm.StudentBiz#addUserStudent(int, java.lang.String)
	 */
	public void addUserStudent(int userId, String studentIds) throws Exception {
		// Integer[] idsIntegers = StringUtil.strToIntegers(Constants.SPLITTER,
		// studentIds);
		// if (idsIntegers != null) {
		// UserStudent userStudent = null;
		// for (Integer studentId : idsIntegers) {
		// userStudent = new UserStudent();
		// userStudent.setStudentId(studentId);
		// userStudent.setUserId(userId);
		// userStudentDao.save(userStudent);

		// Student student=studentDao.findById(studentIds);
		// student.setUserId(userId);
		// student.setStatus(Constants.STU_CALL_STATUS_3);
		// studentDao.update(student);
		// }
		studentDao.update(" set callStatusId=0,userId=" + Constants.PLACEHOLDER
				+ ",status=" + Constants.PLACEHOLDER, studentIds, new Object[] {
				userId, Constants.STU_CALL_STATUS_YI_FENG_PEI });

		// }
	}

	/*
	 * 转移学生
	 * 
	 * @see net.cedu.biz.crm.StudentBiz#shiftStudent(int, int, java.lang.String)
	 */
	public void shiftStudent(int shiftUserId, String studentIds)
			throws Exception {
		// if (studentIds != null) {
		// studentDao.update(" set callStatusId=0, userId="
		// + Constants.PLACEHOLDER + ",status="
		// + Constants.PLACEHOLDER, studentIds, new Object[] {
		// shiftUserId, Constants.STU_CALL_STATUS_YI_FENG_PEI });
		//
		// }
		// 转移的时候不修改学生状态，只修改用户id
		if (studentIds != null) {
			studentDao.update(" set callStatusId=0, userId="
					+ Constants.PLACEHOLDER, studentIds,
					new Object[] { shiftUserId });

		}
	}

	/*
	 * 更新学生状态
	 * 
	 * @see net.cedu.biz.crm.StudentBiz#updateStudentStatus(java.lang.String,
	 * int)
	 */
	public void updateStudentStatus(String studentIds, int status)
			throws Exception {
		if (studentIds != null) {
			studentDao.update(" set status=" + Constants.PLACEHOLDER,
					studentIds, new Object[] { status });
		}
	}

	/*
	 * 统计分配的学生人数
	 * 
	 * @see net.cedu.biz.crm.StudentBiz#distributionStudentCount(int)
	 */
	public int distributionStudentCount(int userId) throws Exception {
		try {

			PageParame p = new PageParame();
			p.setHqlConditionExpression(" and userId=" + Constants.PLACEHOLDER);
			p.setValues(new Object[] { userId });
			return studentDao.getCounts(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	/*
	 * 是否存在学生
	 * 
	 * @see
	 * net.cedu.biz.crm.StudentBiz#isExistStudent(net.cedu.entity.crm.Student)
	 */
	public Student isExistStudent(Student student) throws Exception {
		List<Object> list = null;
		String hqlConditionExpression = "or";
		if (student != null) {
			list = new ArrayList<Object>();

			// 手机
			if ((student.getMobile() != null && !student.getMobile().equals(""))) {
				if(hqlConditionExpression.equals("or")){
					hqlConditionExpression = " mobile ="+ Constants.PLACEHOLDER;
				}else{
					hqlConditionExpression += " or mobile ="+ Constants.PLACEHOLDER;
				}
				list.add(student.getMobile());
			}
			// 座机
			if ((student.getPhone() != null && !student.getPhone().equals(""))) {
				
				if(hqlConditionExpression.equals("or")){
					hqlConditionExpression = " phone =" + Constants.PLACEHOLDER;
				}else{
					hqlConditionExpression += " or phone =" + Constants.PLACEHOLDER;
				}
				list.add(student.getPhone());
			}
			//身份证
			if (student.getCertNo() != null && !student.getCertNo().equals("")) {// 身份证号
				if(hqlConditionExpression.equals("or")){
					hqlConditionExpression = " certNo =" + Constants.PLACEHOLDER;
				}else{
					hqlConditionExpression += " or certNo =" + Constants.PLACEHOLDER;
				}
				
				list.add(student.getCertNo());
			}

//			if ((student.getMobile() != null && !student.getMobile().equals(""))|| (student.getCertNo() != null && !student.getCertNo().equals(""))) {
//				hqlConditionExpression += " and status <=" + Constants.PLACEHOLDER;
//				list.add(Constants.STU_CALL_STATUS_YI_XIU_XUE);
//			}
		}
		if("or".equals(hqlConditionExpression)){
			return null;
		}
		//System.out.println(" and ("+hqlConditionExpression+")");
		//List<Student> students = studentDao.getByProperty(" and ("+hqlConditionExpression+")", list.toArray());
		hqlConditionExpression=" and ("+hqlConditionExpression+")";
		//如果是老生续读的新生   得屏蔽掉老生后再验证
		if(student.getId()>0)
		{
			hqlConditionExpression += " and newstuId <>" + Constants.PLACEHOLDER;
			list.add(student.getId());
		}
		List<Student> students = studentDao.getByProperty(hqlConditionExpression, list.toArray());
		if (students != null && students.size() != 0) {
			return students.get(0);
		}
		return null;
	}

	/*
	 * 查询学生信息通过姓名或者身份证号
	 * 
	 * @see
	 * net.cedu.biz.crm.StudentBiz#findStudentsByNameOrCertNo(java.lang.String,
	 * java.lang.String)
	 */
	public Student findStudentsByNameOrCertNo(String name, String certNo)
			throws Exception {
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (!name.trim().equals("")) {
			hqlparam += " and name=" + Constants.PLACEHOLDER;
			list.add(name);
		}
		if (!certNo.trim().equals("")) {
			hqlparam += " and certNo=" + Constants.PLACEHOLDER;
			list.add(certNo);
		}
		List<Student> studentlst = studentDao.getByProperty(hqlparam, list);
		if (studentlst != null && studentlst.size() > 0) {
			hqlparam += "  and status>" + Constants.PLACEHOLDER;
			list.add(Constants.STU_CALL_STATUS_YU_BAO_MING);
			hqlparam += " and status <=" + Constants.PLACEHOLDER;
			list.add(Constants.STU_CALL_STATUS_YI_XIU_XUE);
			if (studentlst != null && studentlst.size() > 0) {
				return studentlst.get(0);
			}
		}
		return null;

	}

	/*
	 * 查询没有创建账户，状态大于18，未删除的学生信息
	 * 
	 * @see net.cedu.biz.crm.StudentBiz#findStudentIdsByHasAccount()
	 */
	public Long[] findStudentIdsByHasAccount() throws Exception {
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		hqlparam += " and hasAccount=" + Constants.PLACEHOLDER;
		list.add(Constants.IS_CREATE_ACCOUNT_FALSE);
		hqlparam += "  and status>" + Constants.PLACEHOLDER;
		list.add(Constants.STU_CALL_STATUS_YU_BAO_MING);
		hqlparam += "  and status<=" + Constants.PLACEHOLDER;
		list.add(Constants.STU_CALL_STATUS_YI_XIU_XUE);
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
//		Long[] ls = studentDao.getIDs(p);
		return studentDao.getIDs(p);
	}

	/*
	 * 精确查找
	 * 
	 * @see
	 * net.cedu.biz.crm.StudentBiz#findSyudentByExact(net.cedu.entity.crm.Student
	 * )
	 */
	public List<Student> findSyudentByExact(Student student) throws Exception {
		List<Student> students = null;
		List<Object> list = null;
		String hqlConditionExpression = "";
		if (student != null) {
			list = new ArrayList<Object>();
			// 学习中心
			if (student.getBranchId() != 0) {
				hqlConditionExpression += " and branchId ="
						+ Constants.PLACEHOLDER;
				list.add(student.getBranchId());
			}
			// 状态
			if (student.getStatus() != 0) {
				hqlConditionExpression += " and status <"
						+ Constants.PLACEHOLDER;
				list.add(student.getStatus());
			}
			// 姓名
			if (student.getName() != null && !student.getName().equals("")) {
				hqlConditionExpression += " and name =" + Constants.PLACEHOLDER;
				list.add(student.getName());
			}
			// 证件号
			if (student.getCertNo() != null && !"".equals(student.getCertNo())) {
				hqlConditionExpression += " and certNo ="
						+ Constants.PLACEHOLDER;
				list.add(student.getCertNo());
			}
			// 手机
			if (student.getMobile() != null && !student.getMobile().equals("")) {
				hqlConditionExpression += "and  mobile ="
						+ Constants.PLACEHOLDER;
				list.add(student.getMobile());
			}
			hqlConditionExpression+=" and status in ("+Constants.PLACEHOLDER+")";
			list.add("$"+Constants.STU_CALL_STATUS_YI_FENG_PEI+","+Constants.STU_CALL_STATUS_WU_YI_YUAN+","+Constants.STU_CALL_STATUS_GENG_JIN_ZHONG+","+Constants.STU_CALL_STATUS_YI_DAO_MING);
		}
		students = studentDao.getByProperty(hqlConditionExpression,
				list.toArray());
		return students;
	}

	/*
	 * 查询学生按招生批次(多)学生状态(数量)
	 * 
	 * @see
	 * net.cedu.biz.crm.StudentBiz#findStudentByEnrollBatchIdsStatus(java.lang
	 * .String, int)
	 */
	public int findStudentByEnrollBatchIdsStatus(String enrollBatchIds,
			int status) throws Exception {

		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		hqlparam += " and enrollmentBatchId in (" + Constants.PLACEHOLDER + ")";
		list.add("$" + enrollBatchIds);
		hqlparam += " and status >" + Constants.PLACEHOLDER;
		list.add(status);
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		return studentDao.getCounts(p);
	}

	/*
	 * 查询学生完成数量
	 * 
	 * @see net.cedu.biz.crm.StudentBiz#findStudentCount(int, int, int)
	 */
	public int findStudentCount(int batchId, int academyId, int userId)
			throws Exception {
		PageParame p = new PageParame();

		String hqlConditionExpression = "";
		List<Object> list = null;
		list = new ArrayList<Object>();
		// 招生批次
		AcademyEnrollBatch academyEnrollBatch = academyenrollbatchBiz
				.findNonFinishedAcademyBatchByGlobalBatchIdAndAcademyId(
						batchId, academyId);
		if (academyEnrollBatch != null) {
			hqlConditionExpression += " and  enrollmentBatchId= "
					+ Constants.PLACEHOLDER;
			list.add(academyEnrollBatch.getId());
		}
		// 状态
		hqlConditionExpression += "and status>" + Constants.PLACEHOLDER;
		list.add(Constants.STU_CALL_STATUS_YI_DAO_MING);
		if (academyId != 0) {
			hqlConditionExpression += "and academyId=" + Constants.PLACEHOLDER;
			list.add(academyId);
		}

		if (userId != 0) {
			hqlConditionExpression += "and userId=" + Constants.PLACEHOLDER;
			list.add(userId);
		}
		p.setHqlConditionExpression(hqlConditionExpression);
		p.setValues(list.toArray());

		return studentDao.getCounts(p);
	}

	/*
	 * 更新学生实体
	 * 
	 * @see net.cedu.biz.crm.StudentBiz#updateStudentInfo(int)
	 */
	public void updateStudentInfo(Student stu) throws Exception {
		//全局批次(edited by dongminghao 根据招生批次匹配学生全局批次)
		if(stu.getEnrollmentBatchId()!=0)
		{
			AcademyEnrollBatch aeb = academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId());
			if(aeb!=null)
			{
				stu.setGlobalBatch(aeb.getGlobalEnrollBatchId());
			}
		}
		// 去掉空格
		stu.setName(stu.getName().trim());
		studentDao.update(stu);
	}
	
	/*
	 * 批量更新学生开课状态
	 * 
	 * @see net.cedu.biz.crm.StudentBiz#updateBatchStudentStartStatus(int)
	 */
	public void updateBatchStudentStartStatus(String stuIds,int startStatus) throws Exception
	{
		if (stuIds != null) 
		{
			studentDao.update(" set isStartCourse=" + Constants.PLACEHOLDER,
					stuIds, new Object[] { startStatus });
		}
	}

	/*
	 * 导入历史学生信息
	 * 
	 * @see
	 * net.cedu.biz.crm.StudentBiz#importStudents(net.cedu.entity.crm.Student,
	 * net.cedu.model.crm.ImportResult)
	 */
	public ImportResult<Student> importStudents(Student student,ImportResult<Student> ir) throws Exception {
			try {
				//导入失败信息
				String errmsg = "第"+student.getSerialNumber()+"行" + "[导入失败]:";
				boolean isImport=true;
				// 姓名，手机号码（手机号码和固定电话必填其一，且必须验证手机号码或固定电话是否与系统中的电话号码是否重复）
				if ((student.getMobile() != null && !"".equals(student.getMobile()) || student.getPhone() != null && !"".equals(student.getPhone()))&& (student.getName() != null&& !"".equals(student.getName()))&&(student.getCertNo() != null&& !"".equals(student.getCertNo()))) {
					//验证手机号码是否合法（暂时略去）
					//验证身份证号是否合法（暂时略去）
					//验证学生是否存在，通过手机，或座机，或证件号
					Student s = isExistStudent(student);
					if (s==null) {
						// 学习中心
						Branch branch=null;
						if (student.getBranchName() != null&& !"".equals(student.getBranchName())){
							 branch = branchBiz.findBranchByName(student.getBranchName());
							//学习中心名称不存在［警告］
							if(branch==null){
								errmsg+="学习中心名称["+student.getBranchName()+"]匹配失败,";
								isImport=false;
							}
						}
						
						// 院校
						Academy academy = null;
						if (student.getSchoolName() != null&& !"".equals(student.getSchoolName())){
							academy = academyBiz.findAcademyByName(student.getSchoolName());
							//院校不存在［警告］
							if(academy==null){
								errmsg+="院校名称["+student.getSchoolName()+"]匹配失败,";
								isImport=false;
							}
						}
						// 层次
						Level level = null;
						if (student.getLevelName() != null&& !"".equals(student.getLevelName())){
							level = levelbiz.findLevelByName(student.getLevelName());
							//层次不存在［警告］
							if(level==null){
								errmsg+="层次名称["+student.getLevelName()+"]匹配失败,";
								isImport=false;
							}
						}
						// 专业
						Major m = null;
						if (academy != null && student.getMajorName() != null&& !"".equals(student.getMajorName())){
							m = majorbiz.findMajorByName(academy.getId(),student.getMajorName(),level != null ? level.getName() : "");
							//专业不存在［警告］
							if(m==null){
								errmsg+="专业名称["+student.getMajorName()+"]匹配失败,";
								isImport=false;
							}
						}
						// 学籍批次
						AcademyEnrollBatch ae = null;
						if (student.getBatchName() != null&& !"".equals(student.getBatchName())){
							ae = academyenrollbatchBiz.findAcademyEnrollBatchByRegisterName(academy.getId(), student.getBatchName());
							//学籍批次不存在［警告］
							if(ae==null){
								errmsg+="学籍批次名称["+student.getBatchName()+"]匹配失败,";
								isImport=false;
							}
						}
						// 招生批次
						AcademyEnrollBatch aeb = null;
						if (null != student.getEnrollmentBatchName()&& !"".equals(student.getEnrollmentBatchName())){
							if(academy!=null){
								aeb = academyenrollbatchBiz.findAcademyEnrollBatchByEnrollmentName(academy.getId(),student.getEnrollmentBatchName());
								//招生批次不存在［警告］
								if(aeb==null){
									errmsg+="招生批次名称["+student.getEnrollmentBatchName()+"]匹配失败,";
									isImport=false;
								}
							}else{
								errmsg+="院校名称为空或者匹配错误，导致招生批次名称["+student.getEnrollmentBatchName()+"]匹配失败,";
								isImport=false;
							}
							
							
						}
						// 入学日期
						if(student.getAdmissionTime()!=null && student.getAdmissionTime().getYear()>2099){
							errmsg+="入学日期格式不正确";
							isImport=false;
						}
						
						
						
						//不能导入
						if(!isImport){
							ir.setErrorCount(ir.getErrorCount() + 1);
							student.setErrorMessage(errmsg);
							ir.getErrorList().add(student);
						}else{
							//入学前最高学历
							if (student.getHighestEducation() != null) {
								student.setRemark(ResourcesTool.getText("crm","ru.xue.qian.zui.gao.xue.li")+ student.getHighestEducation());
							}
							if (branch != null) {
								student.setBranchId(branch.getId());
							}
							if (academy != null) {
								student.setAcademyId(academy.getId());
							}
		
							if (level != null) {
								student.setLevelId(level.getId());
							}
							if (m != null) {
								student.setMajorId(m.getId());
							}
							if (ae != null) {
								student.setBatchId(ae.getId());
							}
							if (aeb != null) {
								student.setEnrollmentBatchId(aeb.getId());
								//全局批次(edited by dongminghao)
								student.setGlobalBatch(aeb.getGlobalEnrollBatchId());
							}
		
							// 去掉空格
							student.setName(student.getName().trim());
							student.setCreateDate(new Date());
							Object object = studentDao.save(student);
							int id = Integer.valueOf(object.toString());
							StudentSlave studentSlave = new StudentSlave();
							studentSlave.setStudentId(id);
							studentSlave.setAdmissionWay(student.getAdmissionWay());
							studentSlave.setAdmissionTime(student.getAdmissionTime());
							studentSlave.setPoliticalStatus(student.getPoliticalStatus());
							studentSlave.setHomeplace(student.getHomeplace());
							studentSlave.setEthnicGroup(student.getEthnicGroup());
							studentSlaveDao.save(studentSlave);
							ir.setSuccessCount(ir.getSuccessCount() + 1);
						}
					}
					 else {
						 errmsg += "学生的手机号或座机或证件号已存在,";
						 ir.setErrorCount(ir.getErrorCount() + 1);
						 student.setErrorMessage(errmsg);
						 ir.getErrorList().add(student);
		//				 student.setErrorMessage(ResourcesTool.getText("crm","message.found.error",
		//						 new Object[] {
		//						 	student.getCertNo()==null?"身份证信息":student.getCertNo() }));
		//				 			ir.setErrorCount(ir.getErrorCount() + 1);
		//				 			ir.getErrorList().add(student);
					 }
				} else {
					if (student.getName() == null || "".equals(student.getName())) {
						errmsg += "姓名为空,";
					}
					if (!(student.getMobile() != null && !"".equals(student.getMobile()) || student.getPhone() != null && !"".equals(student.getPhone()))) {
						errmsg += "手机号码和固定电话必填其一,";
					}
					if(!(student.getCertNo() != null&& !"".equals(student.getCertNo()))){
						errmsg += "身份证号码为空,";
					}
					ir.setErrorCount(ir.getErrorCount() + 1);
					student.setErrorMessage(errmsg);
					ir.getErrorList().add(student);
				}
			} catch (Exception e) {
				e.printStackTrace();
				ir.setErrorCount(ir.getErrorCount() + 1);
				student.setErrorMessage(student.getCertNo() + e.getMessage());
				ir.getErrorList().add(student);
			}
			return ir;
				
	}

	/*
	 * 导入学习中心用户
	 * 
	 * @see
	 * net.cedu.biz.crm.StudentBiz#importBranchStudents(net.cedu.entity.crm.
	 * Student, net.cedu.model.crm.ImportResult)
	 */
	public ImportResult<Student> importBranchStudents(Student student,ImportResult<Student> ir) throws Exception {
		try {
			//导入失败信息
			String errmsg = "第"+student.getSerialNumber()+"行" + "[导入失败]:";
			boolean isImport=true;
			// 姓名，手机号码（手机号码和固定电话必填其一，且必须验证手机号码或固定电话是否与系统中的电话号码是否重复）
			if ((student.getMobile() != null && !"".equals(student.getMobile()) || student.getPhone() != null && !"".equals(student.getPhone()))&& student.getName() != null&& !"".equals(student.getName())) {
				boolean isRegex = true;//正则表达式验证
				String errorMessage = "";
				//验证手机号码是否合法
				String regexMobile = "^(13|15|18|14|17)[0-9]{9}$";
				if (isRegex &&
						student.getMobile()!=null &&
						!student.getMobile().equals("") &&
						!student.getMobile().matches(regexMobile)) {
					isRegex = false;
					errorMessage = "手机号码不合法";
				}
				//验证座机号码是否合法
				String regexPhone = "^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{1,5}))?$";
				if (isRegex &&
						student.getPhone()!=null &&
						!student.getPhone().equals("") &&
						!student.getPhone().matches(regexPhone)) {
					isRegex = false;
					errorMessage = "座机号码不合法";
				}
				//验证身份证号码是否合法
//				String regexCertNo = "^[1-9]([0-9]{14}|[0-9]{17})$";
//				String regexCertNo = "/(^\\d{15}$)|(^\\d{17}([0-9]|X)$)/";
				if (isRegex &&
						student.getCertType()==Constants.CERTIFICATE_TYPE_ID &&
						StringUtils.isNotBlank(student.getCertNo()) &&
						!IdcardUtil.isIdCard(student.getCertNo())) {
					isRegex = false;
					errorMessage = "身份证号码不合法";
				}
				//验证入学日期是否有效
				if(isRegex && 
						student.getAdmissionTime()!=null &&
						student.getAdmissionTime().getYear()>2099){
					isRegex = false;
					errorMessage = "入学日期不合法";
				}
				// 全部合法
				if (isRegex) {
					//验证学生是否存在，通过手机，或座机，或证件号
					Student s = isExistStudent(student);
					if (s==null) {
						// 学习中心
						Branch branch=null;
						if (student.getBranchName() != null&& !"".equals(student.getBranchName())){
							 branch = branchBiz.findBranchByName(student.getBranchName());
							//学习中心名称不存在［警告］
							if(branch==null){
								errmsg+="学习中心名称["+student.getBranchName()+"]匹配失败,";
								isImport=false;
							}
						}
						// 院校
						Academy academy = null;
						if (student.getSchoolName() != null&& !"".equals(student.getSchoolName())){
							academy = academyBiz.findAcademyByName(student.getSchoolName());
							//院校不存在［警告］
							if(academy==null){
								errmsg+="院校名称["+student.getSchoolName()+"]匹配失败,";
								isImport=false;
							}
						}
						// 层次
						Level level = null;
						if (student.getLevelName() != null&& !"".equals(student.getLevelName())){
							level = levelbiz.findLevelByName(student.getLevelName());
							//层次不存在［警告］
							if(level==null){
								errmsg+="层次名称["+student.getLevelName()+"]匹配失败,";
								isImport=false;
							}
						}
						// 专业
						Major m = null;
						if (academy != null && student.getMajorName() != null&& !"".equals(student.getMajorName())){
							m = majorbiz.findMajorByName(academy.getId(),student.getMajorName(),level != null ? level.getName() : "");
							//专业不存在［警告］
							if(m==null){
								errmsg+="专业名称["+student.getMajorName()+"]匹配失败,";
								isImport=false;
							}
						}
						// 学籍批次
						AcademyEnrollBatch ae = null;
						if (student.getBatchName() != null&& !"".equals(student.getBatchName())){
							ae = academyenrollbatchBiz.findAcademyEnrollBatchByRegisterName(academy.getId(), student.getBatchName());
							//学籍批次不存在［警告］
							if(ae==null){
								errmsg+="学籍批次名称["+student.getBatchName()+"]匹配失败,";
								isImport=false;
							}
						}
						// 招生批次
						AcademyEnrollBatch aeb = null;
						if (null != student.getEnrollmentBatchName()&& !"".equals(student.getEnrollmentBatchName())){
							if(academy!=null){
								aeb = academyenrollbatchBiz.findAcademyEnrollBatchByEnrollmentName(academy.getId(),student.getEnrollmentBatchName());
								//招生批次不存在［警告］
								if(aeb==null){
									errmsg+="招生批次名称["+student.getEnrollmentBatchName()+"]匹配失败,";
									isImport=false;
								}
								else if(aeb!=null && aeb.getIsEnable()!=Constants.STATUS_ENABLED)
								{
									errmsg+="招生批次["+student.getEnrollmentBatchName()+"]招生已结束,";
									isImport=false;
								}
							}else{
								errmsg+="院校名称为空或者匹配错误，导致招生批次名称["+student.getEnrollmentBatchName()+"]匹配失败,";
								isImport=false;
							}
							
							
						}
						//不能导入
						if(!isImport){
							ir.setErrorCount(ir.getErrorCount() + 1);
							student.setErrorMessage(errmsg);
							ir.getErrorList().add(student);
						}else{
							//入学前最高学历
							if (student.getHighestEducation() != null) {
								student.setRemark(ResourcesTool.getText("crm","ru.xue.qian.zui.gao.xue.li")+ student.getHighestEducation());
							}
							
							if (branch != null) {
								student.setBranchId(branch.getId());
							}
							if (academy != null) {
								student.setAcademyId(academy.getId());
							}
	
							if (level != null) {
								student.setLevelId(level.getId());
							}
							if (m != null) {
								student.setMajorId(m.getId());
							}
							if (ae != null) {
								student.setBatchId(ae.getId());
							}
							if (aeb != null) {
								student.setEnrollmentBatchId(aeb.getId());
								//全局批次(edited by dongminghao)
								student.setGlobalBatch(aeb.getGlobalEnrollBatchId());
							}
	
							// 去掉空格
							student.setName(student.getName().trim());
							student.setCreateDate(new Date());
							Object object = studentDao.save(student);
							int id = Integer.valueOf(object.toString());
							StudentSlave studentSlave = new StudentSlave();
							studentSlave.setStudentId(id);
							studentSlave.setAdmissionWay(student.getAdmissionWay());
							studentSlave.setAdmissionTime(student.getAdmissionTime());
							studentSlave.setPoliticalStatus(student.getPoliticalStatus());
							studentSlave.setHomeplace(student.getHomeplace());
							studentSlave.setEthnicGroup(student.getEthnicGroup());
							studentSlaveDao.save(studentSlave);
							ir.setSuccessCount(ir.getSuccessCount() + 1);
						}
					}
					else {
						errmsg += "学生的手机号或座机或证件号已存在,";
						ir.setErrorCount(ir.getErrorCount() + 1);
						student.setErrorMessage(errmsg);
						ir.getErrorList().add(student);
//						student.setErrorMessage(ResourcesTool.getText("crm","message.found.error",
//								new Object[] {
//									student.getCertNo()==null?"身份证信息":student.getCertNo() }));
//						 			ir.setErrorCount(ir.getErrorCount() + 1);
//						 			ir.getErrorList().add(student);
					}
				}
				// 验证不合法
				else {
					errmsg += errorMessage;
					ir.setErrorCount(ir.getErrorCount() + 1);
					student.setErrorMessage(errmsg);
					ir.getErrorList().add(student);
				}
			}else {
				if (student.getName() == null || "".equals(student.getName())) {
					errmsg += "姓名不能为空,";
				}
				if (!(student.getMobile() != null && !"".equals(student.getMobile()) || student.getPhone() != null && !"".equals(student.getPhone()))) {
					errmsg += "手机号码和固定电话必填其一,";
				}
				ir.setErrorCount(ir.getErrorCount() + 1);
				student.setErrorMessage(errmsg);
				ir.getErrorList().add(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ir.setErrorCount(ir.getErrorCount() + 1);
			student.setErrorMessage(student.getCertNo() + e.getMessage());
			ir.getErrorList().add(student);
		}
		return ir;
	}

	/*
	 * 平均分配学生
	 * 
	 * @see net.cedu.biz.crm.StudentBiz#updateAverageDistribution(int)
	 */
	public String updateAverageDistribution(int studentImportRecordId,
			int branchId) throws Exception {

		String fenpeilog = "";
		// 获取中心所有用户ID
		List<User> userList = userBiz.findUsersByOrgId(branchId);

		if (userList != null && userList.size() != 0) {
			int userCount = userList.size();
			// 获取导入的所有学生ID
			PageParame p = new PageParame();
			p.setHqlConditionExpression(" and studentImportRecordId="
					+ Constants.PLACEHOLDER);
			p.setValues(new Object[] { studentImportRecordId });
			Long[] studentIds = studentDao.getIDs(p);
			if(studentIds!=null){

					// 临时
					List<String> idsList = new ArrayList<String>();
		
					List<String> newIdsList = new ArrayList<String>();
					if(studentIds.length >= userCount)
					{
						// 余数
						int yuCount = studentIds.length % userCount;
						
						//
						Long[] mainStudentIds = new Long[studentIds.length - yuCount];
						// 余数数组
						Long[] yuStudentIds = new Long[yuCount];
			
						for (int i = 0; i < mainStudentIds.length; i++) {
							mainStudentIds[i] = studentIds[i];
						}
			
						for (int i = (studentIds.length - 1), j = 0; j < yuStudentIds.length; i--, j++) {
							yuStudentIds[j] = studentIds[i];
						}
			
	//					String ids = ",";
						StringBuffer idsSB = new StringBuffer(",");
						for (int i = 0; i < mainStudentIds.length; i++) {
							Long id = mainStudentIds[i];
	//						if (ids.equals(",")) {
	//							ids = id + "";
	//						} else {
	//							ids += "," + id;
	//						}
	//						if ((i + 1) % (studentIds.length / userList.size()) == 0) {
	//							idsList.add(ids);
	//							ids = ",";
	//						}
							if(idsSB.toString().equals(",")){
								idsSB = new StringBuffer(id+"");
							} else{
								idsSB.append(","+id);
							}
							if ((i + 1) % (studentIds.length / userList.size()) == 0){
								idsList.add(idsSB.toString());
								idsSB = new StringBuffer(",");
							}
						}
						for (int i = 0; i < idsList.size(); i++) {
							String id = idsList.get(i);
							if (i < yuStudentIds.length) {
								newIdsList.add(id + "," + yuStudentIds[i]);
							} else {
								newIdsList.add(id);
							}
			
						}
						// 循环
						for (int i = 0; i < userList.size(); i++) {
							User user = userList.get(i);
							if (user != null) {
			
								fenpeilog += (ResourcesTool.getText("crm",
										"message.distribution.success",
										new Object[] { user.getId(), user.getFullName(),
												newIdsList.get(i) }));
								// fenpeilog+=("ID为"+user.getId()+"的"+user.getFullName()+"分配学生Ids集合为："+newIdsList.get(i)+"\n");
								// System.out.println(user.getFullName()+"----"+newIdsList.get(i));
								//shiftStudent(user.getId(), newIdsList.get(i));
								addUserStudent(user.getId(), newIdsList.get(i));
							}
						}
					}
					else
					{
						// 循环
						for (int i = 0; i < studentIds.length; i++) {
							User user = userList.get(i);
							if (user != null) {
			
								fenpeilog += (ResourcesTool.getText("crm",
										"message.distribution.success",
										new Object[] { user.getId(), user.getFullName(),
										studentIds[i] }));
								// fenpeilog+=("ID为"+user.getId()+"的"+user.getFullName()+"分配学生Ids集合为："+newIdsList.get(i)+"\n");
								// System.out.println(user.getFullName()+"----"+newIdsList.get(i));
								//shiftStudent(user.getId(), newIdsList.get(i));
								addUserStudent(user.getId(), studentIds[i]+"");
							}
						}
					}
					
				return fenpeilog;
			}else{
				return "没有要分配的学生！";
			}
			
		}else{
			return "没有被分配的用户！";
		}
		

	}

	/**
	 * 修复学生专业
	 * 
	 * @see net.cedu.biz.crm.StudentBiz#updateRepairProfessionalStudents()
	 */
	public void updateRepairProfessionalStudents() throws Exception {
		studentDao.repairProfessionalStudents();

	}

	/*
	 * 按照学生来源查询报名人数
	 * 
	 * @see
	 * net.cedu.biz.crm.StudentBiz#findStudentByWayAndStatus(net.cedu.entity
	 * .crm.Student)
	 */
	public int countStudentByWayAndStatus(Student student) throws Exception {
		PageParame p = new PageParame();
		String hql = "";
		List<Object> list = new ArrayList<Object>();
		if (null != student) {
			// 院校
			if (student.getAcademyId() != 0) {
				hql += " and academyId = " + Constants.PLACEHOLDER;
				list.add(student.getAcademyId());
			}
			// 学习中心
			if (student.getBranchId() != 0) {
				hql += " and branchId = " + Constants.PLACEHOLDER;
				list.add(student.getBranchId());
			}
			// 批次
			if (student.getEnrollmentBatchId() != 0) {
				hql += " and enrollmentBatchId = " + Constants.PLACEHOLDER;
				list.add(student.getEnrollmentBatchId());
			}
			// //区域经理
			// if(student.getUserId()!=0)
			// {
			// hql+=" and userId = "+Constants.PLACEHOLDER;
			// list.add(student.getUserId());
			// }
			// 已报名
			if (student.getStatus() != 0) {
				hql += " and status > " + Constants.PLACEHOLDER;
				list.add(Constants.STU_CALL_STATUS_GENG_JIN_ZHONG);
			}
			// 招生途径
			if (student.getEnrollmentSource() != 0) {
				hql += " and enrollmentSource = " + Constants.PLACEHOLDER;
				list.add(student.getEnrollmentSource());
			}
			// 市场途径
			if (student.getEnrollmentWay() != 0) {
				hql += " and enrollmentWay = " + Constants.PLACEHOLDER;
				list.add(student.getEnrollmentWay());
			}
			// 数据来源
			if (student.getStudentDataSource() != 0) {
				hql += " and studentDataSource = " + Constants.PLACEHOLDER;
				list.add(student.getStudentDataSource());
			}
			if (null != student.getCreateDate()
					&& null != student.getModifiedTime()) {
				hql += " and  createDate  between " + Constants.PLACEHOLDER
						+ " and " + Constants.PLACEHOLDER;
				list.add(student.getCreateDate());
				list.add(student.getModifiedTime());
			}
		}
		p.setHqlConditionExpression(hql);
		p.setValues(list.toArray());
		return studentDao.getCounts(p);
	}

	public List<Student> findStudentByWayAndStatus(Student student)
			throws Exception {

		String hql = "";
		List<Object> list = new ArrayList<Object>();
		if (null != student) {
			// 院校
			if (student.getAcademyId() != 0) {
				hql += " and academyId = " + Constants.PLACEHOLDER;
				list.add(student.getAcademyId());
			}
			// 学习中心
			if (student.getBranchId() != 0) {
				hql += " and branchId = " + Constants.PLACEHOLDER;
				list.add(student.getBranchId());
			}
			// 批次
			if (student.getEnrollmentBatchId() != 0) {
				hql += " and enrollmentBatchId = " + Constants.PLACEHOLDER;
				list.add(student.getEnrollmentBatchId());
			}
			// //区域经理
			// if(student.getUserId()!=0)
			// {
			// hql+=" and userId = "+Constants.PLACEHOLDER;
			// list.add(student.getUserId());
			// }
			// 已报名
			if (student.getStatus() != 0) {
				hql += " and status > " + Constants.PLACEHOLDER;
				list.add(Constants.STU_CALL_STATUS_GENG_JIN_ZHONG);
			}
			// 招生途径
			if (student.getEnrollmentSource() != 0) {
				hql += " and enrollmentSource = " + Constants.PLACEHOLDER;
				list.add(student.getEnrollmentSource());
			}
			// 市场途径
			if (student.getEnrollmentWay() != 0) {
				hql += " and enrollmentWay = " + Constants.PLACEHOLDER;
				list.add(student.getEnrollmentWay());
			}
			// 数据来源
			if (student.getStudentDataSource() != 0) {
				hql += " and studentDataSource = " + Constants.PLACEHOLDER;
				list.add(student.getStudentDataSource());
			}
			if (null != student.getCreateDate()
					&& null != student.getModifiedTime()) {
				hql += " and  createDate  between " + Constants.PLACEHOLDER
						+ " and " + Constants.PLACEHOLDER;
				list.add(student.getCreateDate());
				list.add(student.getModifiedTime());
			}
		}

		return studentDao.getByProperty(hql, list);
	}

	/*
	 * 查询学生总数量(通过最基本的查询条件)
	 * 
	 * @see
	 * net.cedu.biz.crm.StudentBiz#findStudentsPageCountByBaseConditions(net
	 * .cedu.entity.crm.Student)
	 */
	public int findStudentsPageCountByBaseConditions(Student student)
			throws Exception {
		PageParame p = new PageParame();
		StringBuffer hqlConditionExpression = new StringBuffer();
		List<Object> list = null;
		if (student != null) {
			list = new ArrayList<Object>();
			// 学习中心
			if (student.getBranchId() != 0) {
				hqlConditionExpression.append(" and  branchId= "
						+ Constants.PLACEHOLDER);
				list.add(student.getBranchId());
			}
			// 院校
			if (student.getAcademyId() != 0) {
				hqlConditionExpression.append("and academyId="
						+ Constants.PLACEHOLDER);
				list.add(student.getAcademyId());
			}
			// 全局批次
			String gbatchIds = "";
			StringBuffer gbatchIdsSB = new StringBuffer("");
			if (student.getGlbtach() != 0) {
				// 全局批次和院校批次可以固定一个招生批次，这样减轻数据库负荷
				if (student.getAcademyId() != 0) {
					AcademyEnrollBatch aeb = academyenrollbatchBiz
							.findNonFinishedAcademyBatchByGlobalBatchIdAndAcademyId(
									student.getGlbtach(),
									student.getAcademyId());
					if (aeb != null) {
						hqlConditionExpression.append(" and ( enrollmentBatchId = "
								+ Constants.PLACEHOLDER);
						list.add(aeb.getId());
						hqlConditionExpression.append(" or  globalBatch = "+ Constants.PLACEHOLDER+") ");
						list.add(student.getGlbtach());
					} else {
						return 0;
					}
				} else {
					List<AcademyEnrollBatch> aeblst = academyenrollbatchBiz
							.findAcademyEnrollBatchByGId(student.getGlbtach());
					
					if (null != aeblst && aeblst.size() > 0) {
						for (int i = 0; i < aeblst.size(); i++) {
//							gbatchIds += "," + aeblst.get(i).getId();
							if(gbatchIdsSB.toString().equals(""))
							{
								gbatchIdsSB = new StringBuffer(aeblst.get(i).getId()+"");
							}
							else
							{
								gbatchIdsSB.append("," + aeblst.get(i).getId());
							}
						}
						if(gbatchIdsSB.toString().equals(""))
						{
							gbatchIdsSB = new StringBuffer("0");
						}
						gbatchIds = gbatchIdsSB.toString();
						hqlConditionExpression.append(" and (  enrollmentBatchId in ( "
								+ Constants.PLACEHOLDER + " ) ");
						list.add("$" + gbatchIds);
						hqlConditionExpression.append(" or  globalBatch = "+ Constants.PLACEHOLDER+") ");
						list.add(student.getGlbtach());
					} else {
						return 0;
					}
				}
			}
			if(gbatchIdsSB.toString().equals(""))
			{
				gbatchIdsSB = new StringBuffer("0");
			}
			gbatchIds = gbatchIdsSB.toString();
			// 层次
			if (student.getLevelId() != 0) {
				hqlConditionExpression.append(" and  levelId= "
						+ Constants.PLACEHOLDER);
				list.add(student.getLevelId());
			}
			// 全局层次
			String glevelIds = "";
			StringBuffer glevelIdsSB = new StringBuffer("");
			if (student.getGllevel() != 0) {
				List<AcademyLevel> allst = academylevelBiz
						.findByGllevel(student.getGllevel());
				
				if (null != allst && allst.size() > 0) {
					for (int i = 0; i < allst.size(); i++) {
//						glevelIds += "," + allst.get(i).getId();
						if(glevelIdsSB.toString().equals(""))
						{
							glevelIdsSB = new StringBuffer(allst.get(i).getId()+"");
						}
						else
						{
							glevelIdsSB.append("," + allst.get(i).getId());
						}
					}
					if(glevelIdsSB.toString().equals(""))
					{
						glevelIdsSB = new StringBuffer("0");
					}
					glevelIds = glevelIdsSB.toString();
					hqlConditionExpression.append(" and  levelId in ( "
							+ Constants.PLACEHOLDER + " ) ");
					list.add("$" + glevelIds);
				}
			}
			if(glevelIdsSB.toString().equals(""))
			{
				glevelIdsSB = new StringBuffer("0");
			}
			glevelIds = glevelIdsSB.toString();
			// 基础专业
			String glmajors = "";
			StringBuffer glmajorsSB = new StringBuffer("");
			if (student.getGlmajor() != 0) {
				List<Major> majorList = this.majorbiz
						.findMajorListByBaseMajorId(student.getGlmajor());
				
				if (null != majorList && majorList.size() > 0) {
					for (int i = 0; i < majorList.size(); i++) {
//						glmajors += "," + majorList.get(i).getId();
						if(glmajorsSB.toString().equals(""))
						{
							glmajorsSB = new StringBuffer(majorList.get(i).getId()+"");
						}
						else
						{
							glmajorsSB.append("," + majorList.get(i).getId());
						}
					}
					if(glmajorsSB.toString().equals(""))
					{
						glmajorsSB = new StringBuffer("0");
					}
					glmajors = glmajorsSB.toString();
					hqlConditionExpression.append(" and  majorId in ( "
							+ Constants.PLACEHOLDER + " ) ");
					list.add("$" + glmajors);
				} else {
					return 0;
				}
			}
			if(glmajorsSB.toString().equals(""))
			{
				glmajorsSB = new StringBuffer("0");
			}
			glmajors = glmajorsSB.toString();
			// 数据来源
			if (student.getStudentDataSource() != 0) {
				hqlConditionExpression.append("and studentDataSource="
						+ Constants.PLACEHOLDER);
				list.add(student.getStudentDataSource());
			}
			// 学生招生途径
			if (student.getEnrollmentSource() != 0) {
				hqlConditionExpression.append("and enrollmentSource="
						+ Constants.PLACEHOLDER);
				list.add(student.getEnrollmentSource());
			}
			// 合作方
			if (student.getChannelId() != 0) {
				hqlConditionExpression.append("and channelId="
						+ Constants.PLACEHOLDER);
				list.add(student.getChannelId());
			}
			// 市场途径
			if (student.getEnrollmentWay() != 0) {
				hqlConditionExpression.append(" and  enrollmentWay= "
						+ Constants.PLACEHOLDER);
				list.add(student.getEnrollmentWay());
			}
			// 呼叫等级
			if (student.getCallStatusId() != 0) {
				hqlConditionExpression.append(" and  callStatusId= "
						+ Constants.PLACEHOLDER);
				list.add(student.getCallStatusId());
			}
			// 状态
			if (student.getStatus() != 0) {
				hqlConditionExpression.append("and status=" + Constants.PLACEHOLDER);
				list.add(student.getStatus());
			}
			// 批量查询学生集合
			if (student.getStatusIds() != null
					&& !"".equals(student.getStatusIds())) {
				hqlConditionExpression.append(" and  status in("
						+ Constants.PLACEHOLDER + ")");
				list.add("$" + student.getStatusIds());
			}
			// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
			// 如果起始状态ID>0,结束状态ID=0;则为无穷大
			// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
			// 如果都大于0,则取交集
			if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0) {
				hqlConditionExpression.append(" and  status <"
						+ Constants.PLACEHOLDER);
				list.add(student.getEndStatusId());
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0) {
				hqlConditionExpression.append(" and  status >"
						+ Constants.PLACEHOLDER);
				list.add(student.getStartStatusId());
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0) {
				hqlConditionExpression.append(" and  status> "
						+ Constants.PLACEHOLDER + " and status<"
						+ Constants.PLACEHOLDER);
				list.add(student.getStartStatusId());
				list.add(student.getEndStatusId());
			}
			// 姓名
			if (student.getName() != null && !student.getName().equals("")) {
				hqlConditionExpression.append("and name like"
						+ Constants.PLACEHOLDER);
				list.add("%" + student.getName() + "%");
			}
			// 证件号
			if (student.getCertNo() != null && !"".equals(student.getCertNo())) {
				hqlConditionExpression.append("and certNo like"
						+ Constants.PLACEHOLDER);
				list.add("%" + student.getCertNo() + "%");
			}
			// 手机或座机
			if (student.getPhone() != null && !student.getPhone().equals("")) {
				hqlConditionExpression.append("and (phone like"
						+ Constants.PLACEHOLDER + "or mobile like"
						+ Constants.PLACEHOLDER + ")");
				list.add("%" + student.getPhone() + "%");
				list.add("%" + student.getPhone() + "%");
			}
			// 学号
			if (student.getNumber() != null && !student.getNumber().equals("")) {
				hqlConditionExpression.append("and number like"
						+ Constants.PLACEHOLDER);
				list.add("%" + student.getNumber() + "%");
			}
			// 监控状态
			if (student.getMonitorStatus() != -1) {
				hqlConditionExpression.append(" and  monitorStatus= "
						+ Constants.PLACEHOLDER);
				list.add(student.getMonitorStatus());
			}
			// 缴费状态
			if (student.getTuitionStatus() != -1) {
				if (student.getTuitionStatus() > 1
						&& student.getTuitionStatus() < 9999) {
					hqlConditionExpression.append(" and  tuitionStatus > "
							+ Constants.PLACEHOLDER + " and tuitionStatus<"
							+ Constants.PLACEHOLDER);
					list.add(Constants.STU_TUITION_STATUS_SHOU_CI_JIAO_FEI);
					list.add(Constants.STU_TUITION_STATUS_JIAO_FEI_WAN_CHENG);
				} else {
					hqlConditionExpression.append(" and  tuitionStatus= "
							+ Constants.PLACEHOLDER);
					list.add(student.getTuitionStatus());
				}
			}

			// 跟进人   最终跟进人是userId
			if (student.getFollowUpId() != 0) {
				hqlConditionExpression.append(" and  userId= "
						+ Constants.PLACEHOLDER);
				list.add(student.getFollowUpId());
			}
			//是否查询重复学生
			if(student.getIsEmphasisVerification()>0){
				hqlConditionExpression.append(" and  id in ( "
					+ Constants.PLACEHOLDER+")");
				list.add("$"+studentDao.repeatStudentIds(student.getIsEmphasisVerification(),student,gbatchIds, glevelIds, glmajors));
			}
			//开课状态
			if(student.getParamsInt().get("isStartCourse")!=null)
			{
				hqlConditionExpression.append(" and  isStartCourse= "
					+ Constants.PLACEHOLDER);
				list.add(student.getParamsInt().get("isStartCourse"));
			}
			//开课时要过滤掉老生续读和社招的学生
			if(student.getParamsString().get("removeEnrollmentSource")!=null)
			{
				hqlConditionExpression.append(" and  enrollmentSource not in ( "
					+ Constants.PLACEHOLDER+" ) ");
				list.add("$" +student.getParamsString().get("removeEnrollmentSource"));
			}

			p.setHqlConditionExpression(hqlConditionExpression.toString());
			p.setValues(list.toArray());

		}
		return studentDao.getCounts(p);
	}

	/*
	 * 查询学生总集合(通过最基本的查询条件)
	 * 
	 * @see
	 * net.cedu.biz.crm.StudentBiz#findStudentsPageListByBaseConditions(net.
	 * cedu.entity.crm.Student, net.cedu.model.page.PageResult)
	 */
	public List<Student> findStudentsPageListByBaseConditions(Student student,
			PageResult<Student> pr) throws Exception {
		List<Student> students = null;
		try {
			// Ids集合
			PageParame p = new PageParame(pr);
			StringBuffer hqlConditionExpression = new StringBuffer();
			List<Object> list = null;
			if (student != null) {
				list = new ArrayList<Object>();
				// 学习中心
				if (student.getBranchId() != 0) {
					hqlConditionExpression.append(" and  branchId= "
							+ Constants.PLACEHOLDER);
					list.add(student.getBranchId());
				}
				// 院校
				if (student.getAcademyId() != 0) {
					hqlConditionExpression.append("and academyId="
							+ Constants.PLACEHOLDER);
					list.add(student.getAcademyId());
				}
				// 全局批次
				String gbatchIds = "";
				StringBuffer gbatchIdsSB = new StringBuffer("");
				if (student.getGlbtach() != 0) {
					// 全局批次和院校批次可以固定一个招生批次，这样减轻数据库负荷
					if (student.getAcademyId() != 0) {
						AcademyEnrollBatch aeb = academyenrollbatchBiz
								.findNonFinishedAcademyBatchByGlobalBatchIdAndAcademyId(
										student.getGlbtach(),
										student.getAcademyId());
						if (aeb != null) {
							hqlConditionExpression.append(" and ( enrollmentBatchId = "
									+ Constants.PLACEHOLDER);
							list.add(aeb.getId());
							hqlConditionExpression.append(" or  globalBatch = "+ Constants.PLACEHOLDER+") ");
							list.add(student.getGlbtach());
						} else {
							return null;
						}
					} else {
						List<AcademyEnrollBatch> aeblst = academyenrollbatchBiz
								.findAcademyEnrollBatchByGId(student.getGlbtach());
						
						if (null != aeblst && aeblst.size() > 0) {
							for (int i = 0; i < aeblst.size(); i++) {
//								gbatchIds += "," + aeblst.get(i).getId();
								if(gbatchIdsSB.toString().equals(""))
								{
									gbatchIdsSB = new StringBuffer(aeblst.get(i).getId()+"");
								}
								else
								{
									gbatchIdsSB.append("," + aeblst.get(i).getId());
								}
							}
							if(gbatchIdsSB.toString().equals(""))
							{
								gbatchIdsSB = new StringBuffer("0");
							}
							gbatchIds = gbatchIdsSB.toString();
							hqlConditionExpression.append(" and (  enrollmentBatchId in ( "
									+ Constants.PLACEHOLDER + " ) ");
							list.add("$" + gbatchIds);
							hqlConditionExpression.append(" or  globalBatch = "+ Constants.PLACEHOLDER+") ");
							list.add(student.getGlbtach());
						} else {
							return null;
						}
					}
				}
				if(gbatchIdsSB.toString().equals(""))
				{
					gbatchIdsSB = new StringBuffer("0");
				}
				gbatchIds = gbatchIdsSB.toString();
				// 层次
				if (student.getLevelId() != 0) {
					hqlConditionExpression.append(" and  levelId= "
							+ Constants.PLACEHOLDER);
					list.add(student.getLevelId());
				}
				// 全局层次
				String glevelIds = "";
				StringBuffer glevelIdsSB = new StringBuffer("");
				if (student.getGllevel() != 0) {
					List<AcademyLevel> allst = academylevelBiz
							.findByGllevel(student.getGllevel());
					
					if (null != allst && allst.size() > 0) {
						for (int i = 0; i < allst.size(); i++) {
//							glevelIds += "," + allst.get(i).getId();
							if(glevelIdsSB.toString().equals(""))
							{
								glevelIdsSB = new StringBuffer(allst.get(i).getId()+"");
							}
							else
							{
								glevelIdsSB.append("," + allst.get(i).getId());
							}
						}
						if(glevelIdsSB.toString().equals(""))
						{
							glevelIdsSB = new StringBuffer("0");
						}
						glevelIds = glevelIdsSB.toString();
						hqlConditionExpression.append(" and  levelId in ( "
								+ Constants.PLACEHOLDER + " ) ");
						list.add("$" + glevelIds);
					}
				}
				if(glevelIdsSB.toString().equals(""))
				{
					glevelIdsSB = new StringBuffer("0");
				}
				glevelIds = glevelIdsSB.toString();
				// 基础专业
				String glmajors = "";
				StringBuffer glmajorsSB = new StringBuffer("");
				if (student.getGlmajor() != 0) {
					List<Major> majorList = this.majorbiz
							.findMajorListByBaseMajorId(student.getGlmajor());
					
					if (null != majorList && majorList.size() > 0) {
						for (int i = 0; i < majorList.size(); i++) {
//							glmajors += "," + majorList.get(i).getId();
							if(glmajorsSB.toString().equals(""))
							{
								glmajorsSB = new StringBuffer(majorList.get(i).getId()+"");
							}
							else
							{
								glmajorsSB.append("," + majorList.get(i).getId());
							}
						}
						if(glmajorsSB.toString().equals(""))
						{
							glmajorsSB = new StringBuffer("0");
						}
						glmajors = glmajorsSB.toString();
						hqlConditionExpression.append(" and  majorId in ( "
								+ Constants.PLACEHOLDER + " ) ");
						list.add("$" + glmajors);
					} else {
						return null;
					}
				}
				if(glmajorsSB.toString().equals(""))
				{
					glmajorsSB = new StringBuffer("0");
				}
				glmajors = glmajorsSB.toString();
				// 数据来源
				if (student.getStudentDataSource() != 0) {
					hqlConditionExpression.append("and studentDataSource="
							+ Constants.PLACEHOLDER);
					list.add(student.getStudentDataSource());
				}
				// 学生招生途径
				if (student.getEnrollmentSource() != 0) {
					hqlConditionExpression.append("and enrollmentSource="
							+ Constants.PLACEHOLDER);
					list.add(student.getEnrollmentSource());
				}
				// 合作方
				if (student.getChannelId() != 0) {
					hqlConditionExpression.append("and channelId="
							+ Constants.PLACEHOLDER);
					list.add(student.getChannelId());
				}
				// 市场途径
				if (student.getEnrollmentWay() != 0) {
					hqlConditionExpression.append(" and  enrollmentWay= "
							+ Constants.PLACEHOLDER);
					list.add(student.getEnrollmentWay());
				}
				// 呼叫等级
				if (student.getCallStatusId() != 0) {
					hqlConditionExpression.append(" and  callStatusId= "
							+ Constants.PLACEHOLDER);
					list.add(student.getCallStatusId());
				}
				// 状态
				if (student.getStatus() != 0) {
					hqlConditionExpression.append("and status=" + Constants.PLACEHOLDER);
					list.add(student.getStatus());
				}
				// 批量查询学生集合
				if (student.getStatusIds() != null
						&& !"".equals(student.getStatusIds())) {
					hqlConditionExpression.append(" and  status in("
							+ Constants.PLACEHOLDER + ")");
					list.add("$" + student.getStatusIds());
				}
				// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
				// 如果起始状态ID>0,结束状态ID=0;则为无穷大
				// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
				// 如果都大于0,则取交集
				if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0) {
					hqlConditionExpression.append(" and  status <"
							+ Constants.PLACEHOLDER);
					list.add(student.getEndStatusId());
				}
				if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0) {
					hqlConditionExpression.append(" and  status >"
							+ Constants.PLACEHOLDER);
					list.add(student.getStartStatusId());
				}
				if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0) {
					hqlConditionExpression.append(" and  status> "
							+ Constants.PLACEHOLDER + " and status<"
							+ Constants.PLACEHOLDER);
					list.add(student.getStartStatusId());
					list.add(student.getEndStatusId());
				}
				// 姓名
				if (student.getName() != null && !student.getName().equals("")) {
					hqlConditionExpression.append("and name like"
							+ Constants.PLACEHOLDER);
					list.add("%" + student.getName() + "%");
				}
				// 证件号
				if (student.getCertNo() != null && !"".equals(student.getCertNo())) {
					hqlConditionExpression.append("and certNo like"
							+ Constants.PLACEHOLDER);
					list.add("%" + student.getCertNo() + "%");
				}
				// 手机或座机
				if (student.getPhone() != null && !student.getPhone().equals("")) {
					hqlConditionExpression.append("and (phone like"
							+ Constants.PLACEHOLDER + "or mobile like"
							+ Constants.PLACEHOLDER + ")");
					list.add("%" + student.getPhone() + "%");
					list.add("%" + student.getPhone() + "%");
				}
				// 学号
				if (student.getNumber() != null && !student.getNumber().equals("")) {
					hqlConditionExpression.append("and number like"
							+ Constants.PLACEHOLDER);
					list.add("%" + student.getNumber() + "%");
				}
				// 监控状态
				if (student.getMonitorStatus() != -1) {
					hqlConditionExpression.append(" and  monitorStatus= "
							+ Constants.PLACEHOLDER);
					list.add(student.getMonitorStatus());
				}
				// 缴费状态
				if (student.getTuitionStatus() != -1) {
					if (student.getTuitionStatus() > 1
							&& student.getTuitionStatus() < 9999) {
						hqlConditionExpression.append(" and  tuitionStatus > "
								+ Constants.PLACEHOLDER + " and tuitionStatus<"
								+ Constants.PLACEHOLDER);
						list.add(Constants.STU_TUITION_STATUS_SHOU_CI_JIAO_FEI);
						list.add(Constants.STU_TUITION_STATUS_JIAO_FEI_WAN_CHENG);
					} else {
						hqlConditionExpression.append(" and  tuitionStatus= "
								+ Constants.PLACEHOLDER);
						list.add(student.getTuitionStatus());
					}
				}

				// 跟进人   最终跟进人是userId
				if (student.getFollowUpId() != 0) {
					hqlConditionExpression.append(" and  userId= "
							+ Constants.PLACEHOLDER);
					list.add(student.getFollowUpId());
				}
				//是否查询重复学生
				if(student.getIsEmphasisVerification()>0){
					hqlConditionExpression.append(" and  id in ( "
						+ Constants.PLACEHOLDER+")");
					list.add("$"+studentDao.repeatStudentIds(student.getIsEmphasisVerification(),student,gbatchIds, glevelIds, glmajors));
				}
				//开课状态
				if(student.getParamsInt().get("isStartCourse")!=null)
				{
					hqlConditionExpression.append(" and  isStartCourse= "
						+ Constants.PLACEHOLDER);
					list.add(student.getParamsInt().get("isStartCourse"));
				}
				//开课时要过滤掉老生续读和社招的学生
				if(student.getParamsString().get("removeEnrollmentSource")!=null)
				{
					hqlConditionExpression.append(" and  enrollmentSource not in ( "
						+ Constants.PLACEHOLDER+" ) ");
					list.add("$" +student.getParamsString().get("removeEnrollmentSource"));
				}

				p.setHqlConditionExpression(hqlConditionExpression.toString());
				p.setValues(list.toArray());

			}
			Long[] studentIds = studentDao.getIDs(p);

			if (studentIds != null && studentIds.length != 0) {
				students = new ArrayList<Student>();
				Student d = null;
				for (Long id : studentIds) {
					d = studentDao.findById(Integer.parseInt(id.toString()));
					if (null != d) {
						// 学习中心
						Branch branch = this.branchBiz.findBranchById(d
								.getBranchId());
						if (branch != null) {
							d.setBranchName(branch.getName());
						}
						// 院校
						Academy academy = academyBiz.findAcademyById(d
								.getAcademyId());

						if (academy != null) {
							d.setSchoolName(academy.getName());
						}
						// 保存数据来源名称
						AcademyEnrollBatch academyenrollbatch = academyenrollbatchBiz
								.findAcademyEnrollBatchById(d
										.getEnrollmentBatchId());
						if (academyenrollbatch != null) {
							d.setAcademyenrollbatchName(academyenrollbatch
									.getEnrollmentName());
						}
						// 层次
						Level level = levelbiz.findLevelById(d.getLevelId());
						if (level != null) {
							d.setLevelName(level.getName());
						}
						// 专业
						Major major = majorbiz.findMajorById(d.getMajorId());
						if (major != null) {
							d.setMajorName(major.getName());
						}
						//招生途径
						EnrollmentSource enrollmentsource = enrollmentsourceBiz.findEnrollmentSourceById(d.getEnrollmentSource());
						if(enrollmentsource!=null)
						{
							d.setEnrollmentSourceName1(enrollmentsource.getName());
						}
						
						// 跟进人
						User user = null;
						// 跟进人与上一跟进人
						if (d.getUserId() != 0) {
							//最终跟进人是userId
							user = userDao.findById(d.getUserId());
							if (user != null) {
								d.setFollowUpName(user.getFullName());
							}

						}
						if (d.getUpFollowUpId() != 0) {
							user = userDao.findById(d.getUpFollowUpId());
							if (user != null) {
								d.setUpFollowUpName(user.getFullName());
							}
						}
						//推送人
						if(d.getPushId()!=0){
							user = userDao.findById(d.getPushId());
							if (user != null) {
								d.setPushName(user.getFullName());
							}
						}
						// 首次跟进人、时间
						FollowUp followUp = followUpDao.findFirstFollowUpByStudentId(d.getId());
						d.getParamsString().put("firstFollowUpUser", null);
						d.getParamsString().put("firstFollowUpTime", null);
						if(followUp != null){
							user = userDao.findById(followUp.getCreatorId());
							if (user != null) {
								d.getParamsString().put("firstFollowUpUser", user.getFullName());
								d.getParamsString().put("firstFollowUpTime", DateUtil.getDate(followUp.getCreatedTime(), "yyyy-MM-dd HH:mm"));
							}
						}

						students.add(d);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return students;
	}

	/**
	 * 通过数量随机获取学生IDs集合
	 * 
	 * @see net.cedu.biz.crm.StudentBiz#randomStudentIdsByCount(int, int)
	 */
	public String randomStudentIdsByCount(int branchId, int count)
			throws Exception {
//		String studentIds = ",";
		StringBuffer studentIdsSB = new StringBuffer(",");

		Long[] studentIdArray = studentDao.getIDs(" and userId=0 and branchId="
				+ Constants.PLACEHOLDER, new Object[] { branchId });

		if (studentIdArray != null && studentIdArray.length != 0) {

			if (studentIdArray.length <= count) {
				for (Long l : studentIdArray) {
//					if (studentIds.equals(",")) {
//						studentIds = l + "";
//					} else {
//						studentIds += "," + l;
//					}
					if (studentIdsSB.toString().equals(",")){
						studentIdsSB = new StringBuffer(l+"");
					} else {
						studentIdsSB.append(","+l);
					}
				}
			} else {
				// 不重复，从大到小排列
				TreeSet<Long> ts = new TreeSet<Long>();
				while (ts.size() < count) {
					int n = (int) (Math.random() * count);
					ts.add(studentIdArray[n]);
				}

				Iterator<Long> iter;
				for (iter = ts.iterator(); iter.hasNext();) {
					// System.out.println(iter.next()) ;
//					if (studentIds.equals(",")) {
//						studentIds = iter.next() + "";
//					} else {
//						studentIds += "," + iter.next();
//					}
					if (studentIdsSB.toString().equals(",")){
						studentIdsSB = new StringBuffer(iter.next() + "");
					} else {
						studentIdsSB.append("," + iter.next());
					}
				}
			}

		}

//		if (studentIds.equals(",")) {
//			studentIds = "0";
//		}
		if (studentIdsSB.toString().equals(",")) {
			studentIdsSB = new StringBuffer("0");
		}
		return studentIdsSB.toString();
	}

	/**
	 * 未分配学生数量
	 * 
	 * @see net.cedu.biz.crm.StudentBiz#findNoDistributeStudentCount(int)
	 */
	public int findNoDistributeStudentCount(int branchId) throws Exception {
		PageParame pageParame = new PageParame();
		pageParame.setHqlConditionExpression(" and status="+Constants.PLACEHOLDER+" and userId=0 and branchId="
				+ Constants.PLACEHOLDER);
		pageParame.setValues(new Object[] {Constants.STU_CALL_STATUS_YI_DAO_RU, branchId });
		return studentDao.getCounts(pageParame);
	}

	/**
	 * 通过查询条件查询学生ID集合
	 * 
	 * @see net.cedu.biz.crm.StudentBiz#findStudentIdsByStudentParams(net.cedu.entity.crm.Student)
	 */
	public String findStudentIdsByStudentParams(Student student)
			throws Exception {
		// Ids集合
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (student.getName() != null && !student.getName().equals("")) {
			hqlparam += " and  name like " + Constants.PLACEHOLDER;
			list.add("%"
					+ (student.getName() != null ? student.getName().trim()
							: student.getName()) + "%");
		}
		if (student.getAcademyId() != 0) {
			hqlparam += " and  academyId= " + Constants.PLACEHOLDER;
			list.add(student.getAcademyId());
		}
		if (student.getBranchId() != 0) {
			hqlparam += " and  branchId= " + Constants.PLACEHOLDER;
			list.add(student.getBranchId());
		}
		if (student.getEnrollmentBatchId() != 0) {
			hqlparam += " and  enrollmentBatchId= " + Constants.PLACEHOLDER;
			list.add(student.getEnrollmentBatchId());
		}
		if (student.getLevelId() != 0) {
			hqlparam += " and  levelId= " + Constants.PLACEHOLDER;
			list.add(student.getLevelId());
		}
		if (student.getMajorId() != 0) {
			hqlparam += " and  majorId= " + Constants.PLACEHOLDER;
			list.add(student.getMajorId());
		}
		if (student.getGlobalBatch() !=0) {
			hqlparam += " and  globalBatch= " + Constants.PLACEHOLDER;
			list.add(student.getGlobalBatch());
		}
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
//		String ids = ",";
		StringBuffer idsSB = new StringBuffer(",");
		Long[] idArray = studentDao.getIDs(p);
		if (idArray != null) {
			for (Long id : idArray) {
//				if (ids.equals(",")) {
//					ids = "" + id;
//				} else {
//					ids += "," + id;
//				}
				if(idsSB.toString().equals(",")) {
					idsSB = new StringBuffer("" + id);
				} else {
					idsSB.append("," + id);
				}
			}
		}
//		if (ids.equals(",")) {
//			return "0";
//		}
		if (idsSB.toString().equals(",")){
			return "0";
		}
		return idsSB.toString();
	}
	
	/*
	 * 根据院校Id和招生批次Id查询已报名的学生人数 （院校返款，匹配政策标准用）
	 * 
	 * @see net.cedu.biz.crm.StudentBiz#findHaveSignedUpStudentCountByAcademyIdenrollmentBatchId(int, int)
	 */
	public int findHaveSignedUpStudentCountByAcademyIdenrollmentBatchId(int academyId,int enrollmentBatchId) throws Exception
	{
		PageParame pageParame = new PageParame();
		pageParame.setHqlConditionExpression(" and academyId="+Constants.PLACEHOLDER +" and enrollmentBatchId="
				+ Constants.PLACEHOLDER+" and status>="+Constants.PLACEHOLDER);
		pageParame.setValues(new Object[] { academyId,enrollmentBatchId,Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI });
		return studentDao.getCounts(pageParame);
	}
	
	/*
	 * 根据院校Id和招生批次Id、合作方Id查询已报名的学生人数 (学生已开课且已缴学费)（招生返款，匹配政策标准用）
	 * 
	 * @see net.cedu.biz.crm.StudentBiz#findHaveSignedUpStudentCountByAcademyIdEnrollmentBatchIdChannelId(int, int, int,int)
	 */
	public int findHaveSignedUpStudentCountByAcademyIdEnrollmentBatchIdChannelId(int academyId,int enrollmentBatchId,int channelId,int glbatchId) throws Exception
	{
		PageParame pageParame = new PageParame();
		String hqlConditionExpression = "";
		List<Object> list = new ArrayList<Object>();
		if(academyId>=0 && enrollmentBatchId>=0)
		{
			pageParame.setHqlConditionExpression(" and academyId="+Constants.PLACEHOLDER +" and enrollmentBatchId="
				+ Constants.PLACEHOLDER+" and channelId="+Constants.PLACEHOLDER+" and isStartCourse="+Constants.PLACEHOLDER+" and tuitionStatus>="+Constants.PLACEHOLDER+" and status>="+Constants.PLACEHOLDER);
			pageParame.setValues(new Object[] { academyId,enrollmentBatchId,channelId,Constants.STU_IS_START_COURSE_TRUE,Constants.STU_TUITION_STATUS_SHOU_CI_JIAO_FEI,Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI });
		}
		else
		{
			// 全局批次
			if (glbatchId != 0)
			{
				List<AcademyEnrollBatch> aeblst = academyenrollbatchBiz
							.findAcademyEnrollBatchByGId(glbatchId);
				String gbatchIds = "";
				StringBuffer gbatchIdsSB = new StringBuffer("");
				if (null != aeblst && aeblst.size() > 0)
				{
					for (int i = 0; i < aeblst.size(); i++)
					{
//						gbatchIds += "," + aeblst.get(i).getId();
						if(gbatchIdsSB.toString().equals(""))
						{
							gbatchIdsSB = new StringBuffer(aeblst.get(i).getId()+"");
						}
						else
						{
							gbatchIdsSB.append("," + aeblst.get(i).getId());
						}
					}
					if(gbatchIdsSB.toString().equals(""))
					{
						gbatchIdsSB = new StringBuffer("0");
					}
					gbatchIds = gbatchIdsSB.toString();
					hqlConditionExpression += " and ( enrollmentBatchId in ( "
								+ Constants.PLACEHOLDER + " ) ";
					list.add("$"+ gbatchIds);
					hqlConditionExpression += " or  globalBatch = "+ Constants.PLACEHOLDER+") ";
					list.add(glbatchId);
				}
				else 
				{
					return 0;
				}
			}
			hqlConditionExpression += " and channelId = "+ Constants.PLACEHOLDER ;
			list.add(channelId);
			hqlConditionExpression += " and isStartCourse = "+ Constants.PLACEHOLDER ;
			list.add(Constants.STU_IS_START_COURSE_TRUE);
			hqlConditionExpression += " and tuitionStatus >= "+ Constants.PLACEHOLDER ;
			list.add(Constants.STU_TUITION_STATUS_SHOU_CI_JIAO_FEI);
			hqlConditionExpression += " and status >= "+ Constants.PLACEHOLDER ;
			list.add(Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI);
			//pageParame.setHqlConditionExpression(" and channelId="+Constants.PLACEHOLDER+" and isStartCourse="+Constants.PLACEHOLDER+" and tuitionStatus>="+Constants.PLACEHOLDER+" and status>="+Constants.PLACEHOLDER);
			//pageParame.setValues(new Object[] {channelId,Constants.STU_IS_START_COURSE_TRUE,Constants.STU_TUITION_STATUS_SHOU_CI_JIAO_FEI,Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI });
			pageParame.setHqlConditionExpression(hqlConditionExpression);
			pageParame.setValues(list.toArray());
		}
		return studentDao.getCounts(pageParame);
	}
	
	/*
	 * 根据院校Id和招生批次Id、主合作方Id、次合作方Id查询已报名的学生人数 (学生已开课且已缴学费)（招生返款，匹配政策标准用）__特殊合作方
	 * 
	 * @see net.cedu.biz.crm.StudentBiz#findHaveSignedUpStudentCountByAcademyIdEnrollmentBatchIdChannelId(int, int, int,int,int)
	 */
	public int findHaveSignedUpStudentCountByAcademyIdEnrollmentBatchIdChannelId(int academyId,int enrollmentBatchId,int channelId,int minorChannel,int glbatchId) throws Exception
	{
		PageParame pageParame = new PageParame();
		String hqlConditionExpression = "";
		List<Object> list = new ArrayList<Object>();
		if(academyId>=0 && enrollmentBatchId>=0)
		{
			pageParame.setHqlConditionExpression(" and academyId="+Constants.PLACEHOLDER +" and enrollmentBatchId="
				+ Constants.PLACEHOLDER+" and ( channelId="+Constants.PLACEHOLDER+" or channelId="+Constants.PLACEHOLDER+" ) and isStartCourse="+Constants.PLACEHOLDER+" and tuitionStatus>="+Constants.PLACEHOLDER+" and status>="+Constants.PLACEHOLDER);
			pageParame.setValues(new Object[] { academyId,enrollmentBatchId,channelId,minorChannel,Constants.STU_IS_START_COURSE_TRUE,Constants.STU_TUITION_STATUS_SHOU_CI_JIAO_FEI,Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI });
		}
		else
		{
			// 全局批次
			if (glbatchId != 0)
			{
				List<AcademyEnrollBatch> aeblst = academyenrollbatchBiz
							.findAcademyEnrollBatchByGId(glbatchId);
				String gbatchIds = "";
				StringBuffer gbatchIdsSB = new StringBuffer("");
				if (null != aeblst && aeblst.size() > 0)
				{
					for (int i = 0; i < aeblst.size(); i++)
					{
//						gbatchIds += "," + aeblst.get(i).getId();
						if(gbatchIdsSB.toString().equals(""))
						{
							gbatchIdsSB = new StringBuffer(aeblst.get(i).getId()+"");
						}
						else
						{
							gbatchIdsSB.append("," + aeblst.get(i).getId());
						}
					}
					if(gbatchIdsSB.toString().equals(""))
					{
						gbatchIdsSB = new StringBuffer("0");
					}
					gbatchIds = gbatchIdsSB.toString();
					hqlConditionExpression += " and ( enrollmentBatchId in ( "
								+ Constants.PLACEHOLDER + " ) ";
					list.add("$"+ gbatchIds);
					hqlConditionExpression += " or  globalBatch = "+ Constants.PLACEHOLDER+") ";
					list.add(glbatchId);
				}
				else 
				{
					return 0;
				}
			}
			hqlConditionExpression += " and ( channelId = "+ Constants.PLACEHOLDER+" or channelId="+Constants.PLACEHOLDER+" ) ";
			list.add(channelId);
			list.add(minorChannel);
			hqlConditionExpression += " and isStartCourse = "+ Constants.PLACEHOLDER ;
			list.add(Constants.STU_IS_START_COURSE_TRUE);
			hqlConditionExpression += " and tuitionStatus >= "+ Constants.PLACEHOLDER ;
			list.add(Constants.STU_TUITION_STATUS_SHOU_CI_JIAO_FEI);
			hqlConditionExpression += " and status >= "+ Constants.PLACEHOLDER ;
			list.add(Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI);
			
			//pageParame.setHqlConditionExpression(" and ( channelId="+Constants.PLACEHOLDER+" or channelId="+Constants.PLACEHOLDER+" ) and isStartCourse="+Constants.PLACEHOLDER+" and tuitionStatus>="+Constants.PLACEHOLDER+" and status>="+Constants.PLACEHOLDER);
			//pageParame.setValues(new Object[] {channelId,minorChannel,Constants.STU_IS_START_COURSE_TRUE,Constants.STU_TUITION_STATUS_SHOU_CI_JIAO_FEI,Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI });
			pageParame.setHqlConditionExpression(hqlConditionExpression);
			pageParame.setValues(list.toArray());
			
		}
		return studentDao.getCounts(pageParame);
	}
	
	/*
	 * 批量录取学生
	 * 
	 * @see net.cedu.biz.crm.StudentBiz#updateStudentSenrolled(java.lang.String, int)
	 */
	public void updateStudentSenrolled(String studentIds, int status) throws Exception
	{
		if (studentIds != null) {
			studentDao.update(" set status=" + Constants.PLACEHOLDER+" ,admitTime="+Constants.PLACEHOLDER,
					studentIds, new Object[] { status,DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss").toString() });
		}
	}
	
	/**
	 * 根据学院id查询学生
	 */
	public Student findAcademyIdByStudent(int id) {
		 String sql="";
		 List<Object> list= new ArrayList<Object>();
		 Student stu=new Student();
		 if(0!=id)
		 {
			 sql+="and academyId="+Constants.PLACEHOLDER;
			 list.add(id);
		 }
		 stu=studentDao.getObjByProperty(sql, list.toArray());
		return stu;	
	}
	
	/**
	 * 模糊查询人员
	 */
	public Long[] findStudentByNames(String name) throws Exception {
		List<Object> list=new ArrayList<Object>();
		Long[] ids =  null;
		String hql="";
		if(StringUtils.isNotBlank(name))
		{
			hql += " and name like " + Constants.PLACEHOLDER;
			list.add('%'+name+'%');
		}
		 
		
		ids = studentDao.getIDs(hql, list.toArray());
		return ids;
	}

	
	/*
	 * 新增学生（老师续读功能用、其他慎用、无验证）
	 * 
	 * @see net.cedu.biz.crm.StudentBiz#addStudentForLaoShengXuDu(net.cedu.entity.crm.Student)
	 */
	public boolean addStudentForLaoShengXuDu(Student student) throws Exception
	{
		if (student != null)
		{
			//全局批次(edited by dongminghao 根据招生批次匹配学生全局批次)
			if(student.getEnrollmentBatchId()!=0)
			{
				AcademyEnrollBatch aeb = academyenrollbatchBiz.findAcademyEnrollBatchById(student.getEnrollmentBatchId());
				if(aeb!=null)
				{
					student.setGlobalBatch(aeb.getGlobalEnrollBatchId());
				}
			}
			Object object = studentDao.save(student);
			if (object != null) 
			{
				return true;
			}
		}
		
		return false;
	}
	
	/*
	 * 查询学生总数量(招生途径复核用)
	 * 
	 * @see
	 * net.cedu.biz.crm.StudentBiz#findStudentsPageCountByChannelTypeChecked(net
	 * .cedu.entity.crm.Student)
	 */
	public int findStudentsPageCountByChannelTypeChecked(Student student)
			throws Exception {
		PageParame p = new PageParame();
		String hqlConditionExpression = "";
		List<Object> list = null;
		if (student != null) {
			list = new ArrayList<Object>();
			// 学习中心
			if (student.getBranchId() != 0) {
				hqlConditionExpression += " and  branchId= "
						+ Constants.PLACEHOLDER;
				list.add(student.getBranchId());
			}
			// 院校
			if (student.getAcademyId() != 0) {
				hqlConditionExpression += "and academyId="
						+ Constants.PLACEHOLDER;
				list.add(student.getAcademyId());
			}
			// 全局批次
			if (student.getGlbtach() != 0) {
				// 全局批次和院校批次可以固定一个招生批次，这样减轻数据库负荷
				if (student.getAcademyId() != 0) {
					AcademyEnrollBatch aeb = academyenrollbatchBiz
							.findNonFinishedAcademyBatchByGlobalBatchIdAndAcademyId(
									student.getGlbtach(),
									student.getAcademyId());
					if (aeb != null) {
						hqlConditionExpression += " and ( enrollmentBatchId = "
								+ Constants.PLACEHOLDER;
						list.add(aeb.getId());
						hqlConditionExpression += " or  globalBatch = "+ Constants.PLACEHOLDER+") ";
						list.add(student.getGlbtach());
						
					} else {
						return 0;
					}
				} else {
					List<AcademyEnrollBatch> aeblst = academyenrollbatchBiz
							.findAcademyEnrollBatchByGId(student.getGlbtach());
					String gbatchIds = "";
					StringBuffer gbatchIdsSB = new StringBuffer("");
					if (null != aeblst && aeblst.size() > 0) {
						for (int i = 0; i < aeblst.size(); i++) {
//							gbatchIds += "," + aeblst.get(i).getId();
							if(gbatchIdsSB.toString().equals(""))
							{
								gbatchIdsSB = new StringBuffer(aeblst.get(i).getId()+"");
							}
							else
							{
								gbatchIdsSB.append("," + aeblst.get(i).getId());
							}
						}
						if(gbatchIdsSB.toString().equals(""))
						{
							gbatchIdsSB = new StringBuffer("0");
						}
						gbatchIds = gbatchIdsSB.toString();
						hqlConditionExpression += " and ( enrollmentBatchId in ( "
								+ Constants.PLACEHOLDER + " ) ";
						list.add("$" + gbatchIds);
						hqlConditionExpression += " or  globalBatch = "+ Constants.PLACEHOLDER+") ";
						list.add(student.getGlbtach());
					} else {
						return 0;
					}
				}
			}
			// 层次
			if (student.getLevelId() != 0) {
				hqlConditionExpression += " and  levelId= "
						+ Constants.PLACEHOLDER;
				list.add(student.getLevelId());
			}
			// 基础专业
			if (student.getGlmajor() != 0) {
				List<Major> majorList = this.majorbiz
						.findMajorListByBaseMajorId(student.getGlmajor());
				String glmajors = "";
				StringBuffer glmajorsSB = new StringBuffer("");
				if (null != majorList && majorList.size() > 0) {
					for (int i = 0; i < majorList.size(); i++) {
//						glmajors += "," + majorList.get(i).getId();
						if(glmajorsSB.toString().equals(""))
						{
							glmajorsSB = new StringBuffer(majorList.get(i).getId()+"");
						}
						else
						{
							glmajorsSB.append("," + majorList.get(i).getId());
						}
					}
					if(glmajorsSB.toString().equals(""))
					{
						glmajorsSB = new StringBuffer("0");
					}
					glmajors = glmajorsSB.toString();
					hqlConditionExpression += " and  majorId in ( "
							+ Constants.PLACEHOLDER + " ) ";
					list.add("$" + glmajors);
				} else {
					return 0;
				}
			}
			// 数据来源
			if (student.getStudentDataSource() != 0) {
				hqlConditionExpression += "and studentDataSource="
						+ Constants.PLACEHOLDER;
				list.add(student.getStudentDataSource());
			}
			// 学生招生途径
			if (student.getEnrollmentSource() != 0) {
				hqlConditionExpression += "and enrollmentSource="
						+ Constants.PLACEHOLDER;
				list.add(student.getEnrollmentSource());
			}
			// 合作方
			if (student.getChannelId() != 0) {
				hqlConditionExpression+="and channelId="
						+ Constants.PLACEHOLDER;
				list.add(student.getChannelId());
			}
			// 市场途径
			if (student.getEnrollmentWay() != 0) {
				hqlConditionExpression += " and  enrollmentWay= "
						+ Constants.PLACEHOLDER;
				list.add(student.getEnrollmentWay());
			}
			// 状态
			if (student.getStatus() != 0) {
				hqlConditionExpression += "and status=" + Constants.PLACEHOLDER;
				list.add(student.getStatus());
			}
			// 批量查询学生集合
			if (student.getStatusIds() != null
					&& !"".equals(student.getStatusIds())) {
				hqlConditionExpression += " and  status in("
						+ Constants.PLACEHOLDER + ")";
				list.add("$" + student.getStatusIds());
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
			// 姓名
			if (student.getName() != null && !student.getName().equals("")) {
				hqlConditionExpression += "and name like"
						+ Constants.PLACEHOLDER;
				list.add("%" + student.getName() + "%");
			}
			// 证件号
			if (student.getCertNo() != null && !"".equals(student.getCertNo())) {
				hqlConditionExpression += "and certNo like"
						+ Constants.PLACEHOLDER;
				list.add("%" + student.getCertNo() + "%");
			}
			// 手机或座机
			if (student.getPhone() != null && !student.getPhone().equals("")) {
				hqlConditionExpression += "and (phone like"
						+ Constants.PLACEHOLDER + "or mobile like"
						+ Constants.PLACEHOLDER + ")";
				list.add("%" + student.getPhone() + "%");
				list.add("%" + student.getPhone() + "%");
			}
			// 学号
			if (student.getNumber() != null && !student.getNumber().equals("")) {
				hqlConditionExpression += "and number like"
						+ Constants.PLACEHOLDER;
				list.add("%" + student.getNumber() + "%");
			}
			// 监控状态
			if (student.getMonitorStatus() != -1) {
				hqlConditionExpression += " and  monitorStatus= "
						+ Constants.PLACEHOLDER;
				list.add(student.getMonitorStatus());
			}
			// 缴费状态
			if (student.getTuitionStatus() != -1) {
				if (student.getTuitionStatus() > 1
						&& student.getTuitionStatus() < 9999) {
					hqlConditionExpression += " and  tuitionStatus > "
							+ Constants.PLACEHOLDER + " and tuitionStatus<"
							+ Constants.PLACEHOLDER;
					list.add(Constants.STU_TUITION_STATUS_SHOU_CI_JIAO_FEI);
					list.add(Constants.STU_TUITION_STATUS_JIAO_FEI_WAN_CHENG);
				} else {
					hqlConditionExpression += " and  tuitionStatus= "
							+ Constants.PLACEHOLDER;
					list.add(student.getTuitionStatus());
				}
			}

			// 跟进人 最终跟进人是userId
			if (student.getFollowUpId() != 0) {
				hqlConditionExpression += " and  userId= "
						+ Constants.PLACEHOLDER;
				list.add(student.getFollowUpId());
			}
			
			//最新监控状态
			if(student.getLastMonitorResult()!=0){
				hqlConditionExpression += " and  lastMonitorResult = "+ Constants.PLACEHOLDER;
				list.add(student.getLastMonitorResult());
			}
			//招生途径复核
			hqlConditionExpression += " and  isChannelTypeChecked= "
				+ Constants.PLACEHOLDER;
			list.add(student.getIsChannelTypeChecked());

			p.setHqlConditionExpression(hqlConditionExpression);
			p.setValues(list.toArray());

		}
		return studentDao.getCounts(p);
	}

	/*
	 * 查询学生总集合(招生途径复核用)
	 * 
	 * @see
	 * net.cedu.biz.crm.StudentBiz#findStudentsPageListByChannelTypeChecked(net.
	 * cedu.entity.crm.Student, net.cedu.model.page.PageResult)
	 */
	public List<Student> findStudentsPageListByChannelTypeChecked(Student student,
			PageResult<Student> pr) throws Exception {
		List<Student> students = null;
		try {
			// Ids集合
			PageParame p = new PageParame(pr);
			String hqlConditionExpression = "";
			List<Object> list = null;
			if (student != null) {
				list = new ArrayList<Object>();
				// 学习中心
				if (student.getBranchId() != 0) {
					hqlConditionExpression += " and  branchId= "
							+ Constants.PLACEHOLDER;
					list.add(student.getBranchId());
				}
				// 院校
				if (student.getAcademyId() != 0) {
					hqlConditionExpression += "and academyId="
							+ Constants.PLACEHOLDER;
					list.add(student.getAcademyId());
				}
				// 全局批次
				if (student.getGlbtach() != 0) {
					// 全局批次和院校批次可以固定一个招生批次，这样减轻数据库负荷
					if (student.getAcademyId() != 0) {
						AcademyEnrollBatch aeb = academyenrollbatchBiz
								.findNonFinishedAcademyBatchByGlobalBatchIdAndAcademyId(
										student.getGlbtach(),
										student.getAcademyId());
						if (aeb != null) {
							hqlConditionExpression += " and ( enrollmentBatchId = "
									+ Constants.PLACEHOLDER;
							list.add(aeb.getId());
							hqlConditionExpression += " or  globalBatch = "+ Constants.PLACEHOLDER+") ";
							list.add(student.getGlbtach());
						} else {
							return null;
						}
					} else {
						List<AcademyEnrollBatch> aeblst = academyenrollbatchBiz
								.findAcademyEnrollBatchByGId(student
										.getGlbtach());
						String gbatchIds = "";
						StringBuffer gbatchIdsSB = new StringBuffer("");
						if (null != aeblst && aeblst.size() > 0) {
							for (int i = 0; i < aeblst.size(); i++) {
//								gbatchIds += "," + aeblst.get(i).getId();
								if(gbatchIdsSB.toString().equals(""))
								{
									gbatchIdsSB = new StringBuffer(aeblst.get(i).getId()+"");
								}
								else
								{
									gbatchIdsSB.append("," + aeblst.get(i).getId());
								}
							}
							if(gbatchIdsSB.toString().equals(""))
							{
								gbatchIdsSB = new StringBuffer("0");
							}
							gbatchIds = gbatchIdsSB.toString();
							hqlConditionExpression += " and ( enrollmentBatchId in ( "
									+ Constants.PLACEHOLDER + " ) ";
							list.add("$" + gbatchIds);
							hqlConditionExpression += " or  globalBatch = "+ Constants.PLACEHOLDER+") ";
							list.add(student.getGlbtach());
						} else {
							return null;
						}
					}
				}
				// 层次
				if (student.getLevelId() != 0) {
					hqlConditionExpression += " and  levelId= "
							+ Constants.PLACEHOLDER;
					list.add(student.getLevelId());
				}
				// 基础专业
				if (student.getGlmajor() != 0) {
					List<Major> majorList = this.majorbiz
							.findMajorListByBaseMajorId(student.getGlmajor());
					String glmajors = "";
					StringBuffer glmajorsSB = new StringBuffer("");
					if (null != majorList && majorList.size() > 0) {
						for (int i = 0; i < majorList.size(); i++) {
//							glmajors += "," + majorList.get(i).getId();
							if(glmajorsSB.toString().equals(""))
							{
								glmajorsSB = new StringBuffer(majorList.get(i).getId()+"");
							}
							else
							{
								glmajorsSB.append("," + majorList.get(i).getId());
							}
						}
						if(glmajorsSB.toString().equals(""))
						{
							glmajorsSB = new StringBuffer("0");
						}
						glmajors = glmajorsSB.toString();
						hqlConditionExpression += " and  majorId in ( "
								+ Constants.PLACEHOLDER + " ) ";
						list.add("$" + glmajors);
					} else {
						return null;
					}
				}
				// 数据来源
				if (student.getStudentDataSource() != 0) {
					hqlConditionExpression += "and studentDataSource="
							+ Constants.PLACEHOLDER;
					list.add(student.getStudentDataSource());
				}
				// 学生招生途径
				if (student.getEnrollmentSource() != 0) {
					hqlConditionExpression += "and enrollmentSource="
							+ Constants.PLACEHOLDER;
					list.add(student.getEnrollmentSource());
				}
				// 合作方
				if (student.getChannelId() != 0) {
					hqlConditionExpression+="and channelId="
							+ Constants.PLACEHOLDER;
					list.add(student.getChannelId());
				}
				// 市场途径
				if (student.getEnrollmentWay() != 0) {
					hqlConditionExpression += " and  enrollmentWay= "
							+ Constants.PLACEHOLDER;
					list.add(student.getEnrollmentWay());
				}
				// 状态
				if (student.getStatus() != 0) {
					hqlConditionExpression += "and status="
							+ Constants.PLACEHOLDER;
					list.add(student.getStatus());
				}
				// 批量查询学生集合
				if (student.getStatusIds() != null
						&& !"".equals(student.getStatusIds())) {
					hqlConditionExpression += " and  status in("
							+ Constants.PLACEHOLDER + ")";
					list.add("$" + student.getStatusIds());
				}
				// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
				// 如果起始状态ID>0,结束状态ID=0;则为无穷大
				// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
				// 如果都大于0,则取交集
				if (student.getStartStatusId() == 0
						&& student.getEndStatusId() > 0) {
					hqlConditionExpression += " and  status <"
							+ Constants.PLACEHOLDER;
					list.add(student.getEndStatusId());
				}
				if (student.getStartStatusId() > 0
						&& student.getEndStatusId() == 0) {
					hqlConditionExpression += " and  status >"
							+ Constants.PLACEHOLDER;
					list.add(student.getStartStatusId());
				}
				if (student.getStartStatusId() > 0
						&& student.getEndStatusId() > 0) {
					hqlConditionExpression += " and  status> "
							+ Constants.PLACEHOLDER + " and status<"
							+ Constants.PLACEHOLDER;
					list.add(student.getStartStatusId());
					list.add(student.getEndStatusId());
				}
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
				// 手机或座机
				if (student.getPhone() != null
						&& !student.getPhone().equals("")) {
					hqlConditionExpression += "and (phone like"
							+ Constants.PLACEHOLDER + "or mobile like"
							+ Constants.PLACEHOLDER + ")";
					list.add("%" + student.getPhone() + "%");
					list.add("%" + student.getPhone() + "%");
				}
				// 学号
				if (student.getNumber() != null
						&& !student.getNumber().equals("")) {
					hqlConditionExpression += "and number like"
							+ Constants.PLACEHOLDER;
					list.add("%" + student.getNumber() + "%");
				}
				// 监控状态
				if (student.getMonitorStatus() != -1) {
					hqlConditionExpression += " and  monitorStatus= "
							+ Constants.PLACEHOLDER;
					list.add(student.getMonitorStatus());
				}
				// 缴费状态
				if (student.getTuitionStatus() != -1) {
					if (student.getTuitionStatus() > 1
							&& student.getTuitionStatus() < 9999) {
						hqlConditionExpression += " and  tuitionStatus > "
								+ Constants.PLACEHOLDER + " and tuitionStatus<"
								+ Constants.PLACEHOLDER;
						list.add(Constants.STU_TUITION_STATUS_SHOU_CI_JIAO_FEI);
						list.add(Constants.STU_TUITION_STATUS_JIAO_FEI_WAN_CHENG);
					} else {
						hqlConditionExpression += " and  tuitionStatus= "
								+ Constants.PLACEHOLDER;
						list.add(student.getTuitionStatus());
					}
				}

				// 跟进人 最终跟进人是userId
				if (student.getFollowUpId() != 0) {
					hqlConditionExpression += " and  userId= "
							+ Constants.PLACEHOLDER;
					list.add(student.getFollowUpId());
				}
				//最新监控状态
				if(student.getLastMonitorResult()!=0){
					hqlConditionExpression += " and  lastMonitorResult = "+ Constants.PLACEHOLDER;
					list.add(student.getLastMonitorResult());
				}
				//招生途径复核
				hqlConditionExpression += " and  isChannelTypeChecked= "
					+ Constants.PLACEHOLDER;
				list.add(student.getIsChannelTypeChecked());
				
				p.setHqlConditionExpression(hqlConditionExpression);
				p.setValues(list.toArray());

			}
			Long[] studentIds = studentDao.getIDs(p);

			if (studentIds != null && studentIds.length != 0) {
				students = new ArrayList<Student>();
				Student d = null;
				for (Long id : studentIds) {
					d = studentDao.findById(Integer.parseInt(id.toString()));
					if (null != d) {
						// 学习中心
						Branch branch = this.branchBiz.findBranchById(d
								.getBranchId());
						if (branch != null) {
							d.setBranchName(branch.getName());
						}
						// 院校
						Academy academy = academyBiz.findAcademyById(d
								.getAcademyId());

						if (academy != null) {
							d.setSchoolName(academy.getName());
						}
						// 保存数据来源名称
						AcademyEnrollBatch academyenrollbatch = academyenrollbatchBiz
								.findAcademyEnrollBatchById(d
										.getEnrollmentBatchId());
						if (academyenrollbatch != null) {
							d.setAcademyenrollbatchName(academyenrollbatch
									.getEnrollmentName());
						}
						// 层次
						Level level = levelbiz.findLevelById(d.getLevelId());
						if (level != null) {
							d.setLevelName(level.getName());
						}
						// 专业
						Major major = majorbiz.findMajorById(d.getMajorId());
						if (major != null) {
							d.setMajorName(major.getName());
						}
						//招生途径
						EnrollmentSource enrollmentsource = enrollmentsourceBiz.findEnrollmentSourceById(d.getEnrollmentSource());
						if(enrollmentsource!=null)
						{
							d.setEnrollmentSourceName1(enrollmentsource.getName());
						}
						
						// 跟进人
						User user = null;
						// 跟进人与上一跟进人
						// 最终跟进人
						if (d.getUserId() != 0) {
							user = userDao.findById(d.getUserId());
							if (user != null) {
								d.setFollowUpName(user.getFullName());
							}
						}
						if (d.getUpFollowUpId() != 0) {
							user = userDao.findById(d.getUpFollowUpId());
							if (user != null) {
								d.setUpFollowUpName(user.getFullName());
							}
						}
						//缴费单招生返款数量
						d.setRebateFpdCount(this.feePaymentDetailBiz.findfpdCountForChannelTypeChecked(d.getId()));

						students.add(d);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return students;
	}
	
	/**
	 * 按照中心预申请条件查询学生
	 */
	public List<Student> findStudentByPrePurchaseCenter(Student student) throws Exception {
		List<Object> list=new ArrayList<Object>();
		 String sql="";
		 List<Student> stulist= new ArrayList<Student>();
		 if(null!=student)
		 {
			 //院校名称
			 if(0!=student.getAcademyId())
			 {
				 sql+="and academyId="+Constants.PLACEHOLDER;
				 list.add(student.getAcademyId());
			 }
			 //批次
			 if(0!=student.getBatchId())
			 {
				 sql+="and batchId="+Constants.PLACEHOLDER;
				 list.add(student.getBatchId());
			 }
			 //层次
			 if(0!=student.getLevelId())
			 {
				 sql+="and levelId="+Constants.PLACEHOLDER;
				 list.add(student.getLevelId());
			 }
			 //专业
			 if(0!=student.getMajorId())
			 {
				 sql+="and majorId="+Constants.PLACEHOLDER;
				 list.add(student.getMajorId());
			 }
			 //状态 
			 if(student.getPrePurchaseStatus() != null && !student.getPrePurchaseStatus().equals(""))
			 {
				 String[] ary =student.getPrePurchaseStatus().split(",");
				 StringBuffer sb = new StringBuffer();
 
					 for(String ar :ary){
						if(sb.equals(",")){
							sb.replace(0, sb.length(), ar+"");
						}else{
							sb.append(","+ar);
						}
					 }
					 sql+="and status in ("+Constants.PLACEHOLDER+")";
					  list.add("$"+sb.toString());	
			 }
		 }
		 sql+="and id<"+Constants.PLACEHOLDER;		 
		 list.add(10);		 
		stulist=studentDao.getByProperty(sql, list);
		return stulist;	
	}

	/*
	 * 是否存在老生(老生续读新生id查老生信息)
	 * @see net.cedu.biz.crm.StudentBiz#isExistOldStudent(int)
	 */
	public Student isExistOldStudent(int id) throws Exception {
		List<Object> list = null;
		String hqlConditionExpression = "";
		if (id != 0) {
			list = new ArrayList<Object>();
			hqlConditionExpression += " and newstuId =" + Constants.PLACEHOLDER;
			list.add(id);
			List<Student> students = studentDao.getByProperty(hqlConditionExpression, list.toArray());
			if (students != null && students.size() != 0) {
				return students.get(0);
			}
		}
		return null;
	}

	/*
	 * 根据全局批次ids查询学生报名数
	 * @see net.cedu.biz.crm.StudentBiz#getBaoMingCountByGlobalEnrollBatchIds(java.lang.String)
	 */
	public Map<String, Integer> getBaoMingCountByGlobalEnrollBatchIds(
			String globalEnrollBatchIds) throws Exception {
		return studentDao.getBaoMingCountByGlobalEnrollBatchIds(globalEnrollBatchIds);
	}

	/*
	 * 根据全局批次ids查询学生缴费数(学费)
	 * @see net.cedu.biz.crm.StudentBiz#getJiaoFeiCountByGlobalEnrollBatchIds(java.lang.String)
	 */
	public Map<String, Integer> getJiaoFeiCountByGlobalEnrollBatchIds(
			String globalEnrollBatchIds) throws Exception {
		return studentDao.getJiaoFeiCountByGlobalEnrollBatchIds(globalEnrollBatchIds);
	}

	/*
	 * 根据全局批次ids查询学生录取数
	 * @see net.cedu.biz.crm.StudentBiz#getLuQuCountByGlobalEnrollBatchIds(java.lang.String)
	 */
	public Map<String, Integer> getLuQuCountByGlobalEnrollBatchIds(
			String globalEnrollBatchIds) throws Exception {
		return studentDao.getLuQuCountByGlobalEnrollBatchIds(globalEnrollBatchIds);
	}

	/*
	 * 通过条件查询学生集合(删除用户功能的查询方法)
	 * @see net.cedu.biz.crm.StudentBiz#findStudentListByParams(net.cedu.entity.crm.Student, int)
	 */
	public List<Student> findStudentListByParams(Student student,int count)
			throws Exception {
		List<Student> studentsList = null;
		PageResult<Student> pr = new PageResult<Student>();
		pr.setPageSize(count);
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = null;
		if(student!=null){
			list = new ArrayList<Object>();
			//跟进人
			if(student.getUserId()!=0){
				hqlparam += " and userId = " + Constants.PLACEHOLDER;
				list.add(student.getUserId());
			}
			
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
			Long[] studentIds = studentDao.getIDs(p);
			if (studentIds != null && studentIds.length != 0){
				studentsList = new ArrayList<Student>();
				Student s = null;
				for(Long id : studentIds){
					s = studentDao.findById(id);
					if(s!=null){
						studentsList.add(s);
					}
				}
				return studentsList;
			}
		}
		return null;
	}
	
	/*
	 * 批量更新学生复核
	 * @see net.cedu.biz.crm.StudentBiz#updateStudentIsChannelTypeChecked(java.lang.String)
	 */
	public void updateStudentIsChannelTypeChecked(String studentIds) throws Exception {
		if (studentIds != null && !studentIds.equals("")) {
			studentDao.update(" set isChannelTypeChecked=" + Constants.PLACEHOLDER,
					studentIds, new Object[] { Constants.STUDENT_CHANNEL_TYPE_CHECKED_TRUE });
		}
	}
	
	/*
	 * 根据招生批次Id、层次Id、专业Id查询已报名学生人数 （删除专业用到）
	 * 
	 * @see net.cedu.biz.crm.StudentBiz#findStudentCountByDeleteAcademyMajor(int, int, int)
	 */
	public int findStudentCountByDeleteAcademyMajor(int enrollmentBatchId,int levelId,int majorId) throws Exception
	{
		PageParame pageParame = new PageParame();
		pageParame.setHqlConditionExpression(" and enrollmentBatchId="+Constants.PLACEHOLDER +" and levelId="
				+ Constants.PLACEHOLDER+" and majorId="+Constants.PLACEHOLDER+" and status>="+Constants.PLACEHOLDER);
		pageParame.setValues(new Object[] { enrollmentBatchId,levelId,majorId,Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI });
		return studentDao.getCounts(pageParame);
	}
}
