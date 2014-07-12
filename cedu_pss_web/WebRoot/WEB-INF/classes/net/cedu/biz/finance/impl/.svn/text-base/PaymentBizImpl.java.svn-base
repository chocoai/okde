package net.cedu.biz.finance.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.DiscountApplicationBiz;
import net.cedu.biz.enrollment.FeeStandardBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.biz.enrollment.PolicyFeeBiz;
import net.cedu.biz.enrollment.StudentDiscountPolicyBiz;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.PaymentBiz;
import net.cedu.biz.finance.PendingFeePaymentBiz;
import net.cedu.biz.finance.ReceiptBiz;
import net.cedu.biz.finance.StudentAccountAmountManagementBiz;
import net.cedu.biz.finance.StudentAccountManagementBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.enums.FeeSubjectEnum;
import net.cedu.common.il8n.ResourcesTool;
import net.cedu.dao.crm.StudentDao;
import net.cedu.dao.finance.FeePaymentDao;
import net.cedu.dao.finance.FeePaymentDetailDao;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.DiscountApplication;
import net.cedu.entity.enrollment.FeeStandard;
import net.cedu.entity.enrollment.Major;
import net.cedu.entity.enrollment.StudentDiscountPolicy;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.PendingFeePayment;
import net.cedu.entity.finance.StudentAccountAmountManagement;
import net.cedu.entity.finance.StudentAccountManagement;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 缴费实现类
 * 
 * @author yangdongdong
 * 
 */
@Service
public class PaymentBizImpl implements PaymentBiz {

	@Autowired
	private FeePaymentDao feePaymentDao;
	@Autowired
	private FeePaymentBiz feePaymentBiz;
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;// 费用科目
	@Autowired
	private FeePaymentDetailDao feePaymentDetailDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private StudentBiz studentBiz;
	@Autowired
	private AcademyBiz academyBiz;// 院校招生批次 业务接口
	@Autowired
	private AcademyEnrollBatchBiz academyenrollbatchBiz;// 院校招生批次 业务接口
	@Autowired
	private LevelBiz levelbiz; // 层次业务接口
	@Autowired
	private MajorBiz majorbiz; // 专业业务接口
	@Autowired
	private BranchBiz branchBiz; // 学习中心业务接口

	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;// 缴费单明细业务接口

	@Autowired
	private PendingFeePaymentBiz pendingFeePaymentBiz;// 待缴费单业务接口

	@Autowired
	private DiscountApplicationBiz discountApplicationBiz;// 优惠卷业务接口

	@Autowired
	private ReceiptBiz receiptBiz;// 收据业务接口

	@Autowired
	private FeeStandardBiz feeStandardBiz;// 收费标准明细业务接口

	@Autowired
	private PolicyFeeBiz policyFeeBiz;// 收费标准业务接口

	@Autowired
	private StudentAccountManagementBiz studentAccountManagementBiz;// 学生账户业务接口

	@Autowired
	private StudentAccountAmountManagementBiz studentAccountAmountManagementBiz;// 学生账户明细业务接口

	@Autowired
	private StudentDiscountPolicyBiz studentDiscountPolicyBiz;// 学生优惠标准业务接口

	/*
	 * 查询缴费单数量
	 * 
	 * @see
	 * net.cedu.biz.finance.PaymentBiz#findFeePaymentsPageCount(net.cedu.entity
	 * .finance.FeePayment, net.cedu.entity.crm.Student,
	 * net.cedu.model.page.PageResult)
	 */
	public int findFeePaymentsPageCount(FeePayment feePayment, Student student,
			PageResult<FeePayment> pr) throws Exception {
		List list2 = null;
		String hqlConditionExpression2 = "";
		PageParame p2 = new PageParame(pr);
		list2 = new ArrayList();

		if (feePayment != null) {
			// POS流水号
			if (feePayment.getPosSerialNo() != null
					&& !feePayment.getPosSerialNo().equals("")) {
				hqlConditionExpression2 += " and posSerialNo like"
						+ Constants.PLACEHOLDER;
				list2.add("%" + feePayment.getPosSerialNo() + "%");
			}
			// 缴费方式
			if (feePayment.getFeeWayId() != 0) {
				hqlConditionExpression2 += " and feeWayId ="
						+ Constants.PLACEHOLDER;
				list2.add(feePayment.getFeeWayId());
			}
			// // 缴费单类别
			// if (feePayment.getPamentType() != -1) {
			// hqlConditionExpression2 += " and pamentType ="
			// + Constants.PLACEHOLDER;
			// list2.add(feePayment.getPamentType());
			// }
			// 缴费单状态
			if (feePayment.getStatus() != 0) {
				hqlConditionExpression2 += " and status ="
						+ Constants.PLACEHOLDER;
				list2.add(feePayment.getStatus());
			}
		}

		if (checkStudentInfo(student)) {
			// 学生ID
			String studentIds = findStudentIds(student);
			if (studentIds != null) {
				hqlConditionExpression2 += " and studentId in ("
						+ Constants.PLACEHOLDER + ")";
				list2.add("$" + studentIds);
			}
		}

		if (!hqlConditionExpression2.equals("")) {
			p2.setHqlConditionExpression(hqlConditionExpression2);
			p2.setValues(list2.toArray());
		}

		return feePaymentDao.getCounts(p2);
	}

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

	/*
	 * 缴费单集合
	 * 
	 * @see
	 * net.cedu.biz.finance.PaymentBiz#findFeePaymentsPageList(net.cedu.entity
	 * .finance.FeePayment, net.cedu.entity.crm.Student,
	 * net.cedu.model.page.PageResult)
	 */
	public List<FeePayment> findFeePaymentsPageList(FeePayment feePayment,
			Student student, PageResult<FeePayment> pr) throws Exception {

		List<FeePayment> feePayments = new ArrayList<FeePayment>();

		List list2 = null;
		String hqlConditionExpression2 = "";
		PageParame p2 = new PageParame(pr);
		list2 = new ArrayList();
		if (feePayment != null) {
			// POS流水号
			if (feePayment.getPosSerialNo() != null
					&& !feePayment.getPosSerialNo().equals("")) {
				hqlConditionExpression2 += " and posSerialNo like"
						+ Constants.PLACEHOLDER;
				list2.add("%" + feePayment.getPosSerialNo() + "%");
			}
			// 缴费方式
			if (feePayment.getFeeWayId() != 0) {
				hqlConditionExpression2 += " and feeWayId ="
						+ Constants.PLACEHOLDER;
				list2.add(feePayment.getFeeWayId());
			}
			// // 缴费单类别
			// if (feePayment.getPamentType() != -1) {
			// hqlConditionExpression2 += " and pamentType ="
			// + Constants.PLACEHOLDER;
			// list2.add(feePayment.getPamentType());
			// }
			// 缴费单状态
			if (feePayment.getStatus() != 0) {
				hqlConditionExpression2 += " and status ="
						+ Constants.PLACEHOLDER;
				list2.add(feePayment.getStatus());
			}
		}

		if (checkStudentInfo(student)) {
			// 学生ID
			String studentIds = findStudentIds(student);
			if (studentIds != null) {
				hqlConditionExpression2 += " and studentId in ("
						+ Constants.PLACEHOLDER + ")";
				list2.add("$" + studentIds);
			}
		}
		if (!hqlConditionExpression2.equals("")) {
			p2.setHqlConditionExpression(hqlConditionExpression2);
			p2.setValues(list2.toArray());
		}
		// Ids集合
		Long[] idsLongs = feePaymentDao.getIDs(p2);
		for (int i = 0; i < idsLongs.length; i++) {
			FeePayment f = feePaymentDao.findById(Integer.parseInt(idsLongs[i]
					.toString()));
			if (f != null) {
				Student d = studentBiz.findStudentById(f.getStudentId());
				if (d != null) {
					// 姓名
					f.setStudentName(d.getName());
					Academy academy = academyBiz.findAcademyById(d
							.getAcademyId());

					if (academy != null) {
						f.setSchoolName(academy.getName());
					}

					AcademyEnrollBatch academyenrollbatch = academyenrollbatchBiz
							.findAcademyEnrollBatchById(d
									.getEnrollmentBatchId());
					if (academyenrollbatch != null) {
						f.setAcademyenrollbatchName(academyenrollbatch
								.getEnrollmentName());
					}

					Level level = levelbiz.findLevelById(d.getLevelId());
					if (level != null) {
						f.setLevelName(level.getName());
					}

					Major major = majorbiz.findMajorById(d.getMajorId());
					if (major != null)
						f.setMajorName(major.getName());
				}

				feePayments.add(f);
			}
		}
		return feePayments;
	}

	/*
	 * 缴费单明细数量
	 * 
	 * @see
	 * net.cedu.biz.finance.PaymentBiz#findFeePaymentDetailsPageCount(net.cedu
	 * .entity.finance.FeePaymentDetail, net.cedu.entity.finance.FeePayment,
	 * net.cedu.entity.crm.Student, net.cedu.model.page.PageResult)
	 */
	public int findFeePaymentDetailsPageCount(
			FeePaymentDetail feePaymentDetail, FeePayment feePayment,
			Student student, PageResult<FeePaymentDetail> pr) throws Exception {

		List list2 = null;
		String hqlConditionExpression2 = "";
		PageParame p2 = new PageParame(pr);
		try {
			list2 = new ArrayList();
			String paymentIds = findPaymentIds(feePayment, student);
			if (paymentIds != null) {
				hqlConditionExpression2 += " and feePaymentId in ("
						+ Constants.PLACEHOLDER + ")";
				list2.add("$" + paymentIds);
			}

			if (feePaymentDetail != null) {
				// 缴费单类型
				if (feePaymentDetail.getTypes() != 0) {
					hqlConditionExpression2 += " and types = "
							+ Constants.PLACEHOLDER;
					list2.add(feePaymentDetail.getTypes());
				}

				// 缴费单状态
				if (feePaymentDetail.getStatus() != 0) {
					hqlConditionExpression2 += " and status = "
							+ Constants.PLACEHOLDER;
					list2.add(feePaymentDetail.getStatus());
				}
			}

			// 缴费单号
			if (!hqlConditionExpression2.equals("")) {
				p2.setHqlConditionExpression(hqlConditionExpression2);
				p2.setValues(list2.toArray());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return feePaymentDetailDao.getCounts(p2);
	}

	/*
	 * 缴费单明细集合
	 * 
	 * @see
	 * net.cedu.biz.finance.PaymentBiz#findFeePaymentDetailsPageList(net.cedu
	 * .entity.finance.FeePaymentDetail, net.cedu.entity.finance.FeePayment,
	 * net.cedu.entity.crm.Student, net.cedu.model.page.PageResult)
	 */
	public List<FeePaymentDetail> findFeePaymentDetailsPageList(
			FeePaymentDetail feePaymentDetail, FeePayment feePayment,
			Student student, PageResult<FeePaymentDetail> pr) throws Exception {
		List<FeePaymentDetail> feePaymentDetails = new ArrayList<FeePaymentDetail>();
		List list2 = null;
		String hqlConditionExpression2 = "";
		PageParame p2 = new PageParame(pr);
		list2 = new ArrayList();
		String paymentIds = findPaymentIds(feePayment, student);
		if (paymentIds != null) {
			hqlConditionExpression2 += " and feePaymentId in ("
					+ Constants.PLACEHOLDER + ")";
			list2.add("$" + paymentIds);
		}
		if (feePaymentDetail != null) {
			// 缴费单类型
			if (feePaymentDetail.getTypes() != 0) {
				hqlConditionExpression2 += " and types = "
						+ Constants.PLACEHOLDER;
				list2.add(feePaymentDetail.getTypes());
			}
			// 缴费单状态
			if (feePaymentDetail.getStatus() != 0) {
				hqlConditionExpression2 += " and status = "
						+ Constants.PLACEHOLDER;
				list2.add(feePaymentDetail.getStatus());
			}
		}
		// 缴费单号
		if (!hqlConditionExpression2.equals("")) {
			p2.setHqlConditionExpression(hqlConditionExpression2);
			p2.setValues(list2.toArray());
		}
		Long[] fidsLongs = feePaymentDetailDao.getIDs(p2);
		if (fidsLongs != null) {
			for (int i = 0; i < fidsLongs.length; i++) {
				// 缴费单详情
				FeePaymentDetail f = feePaymentDetailDao.findById(Integer
						.parseInt(fidsLongs[i].toString()));
				if (f != null) {
					FeePayment fp = feePaymentDao.findById(f.getFeePaymentId());

					if (fp != null) {
						Student d = studentBiz.findStudentById(fp
								.getStudentId());
						if (d != null) {
							// 姓名
							f.setStudentName(d.getName());
							Academy academy = academyBiz.findAcademyById(d
									.getAcademyId());

							if (academy != null) {
								f.setSchoolName(academy.getName());
							}

							AcademyEnrollBatch academyenrollbatch = academyenrollbatchBiz
									.findAcademyEnrollBatchById(d
											.getEnrollmentBatchId());
							if (academyenrollbatch != null) {
								f.setAcademyenrollbatchName(academyenrollbatch
										.getEnrollmentName());
							}

							Level level = levelbiz
									.findLevelById(d.getLevelId());
							if (level != null) {
								f.setLevelName(level.getName());
							}

							Major major = majorbiz
									.findMajorById(d.getMajorId());
							if (major != null)
								f.setMajorName(major.getName());

						}
						// 费用项目名称
						FeeSubject feeSubject = feeSubjectBiz
								.findFeeSubjectById(f.getFeeSubjectId());
						if (feeSubject != null) {
							f.setPaymentSubjectName(feeSubject.getName());
						}
						f.setFeeWayId(fp.getFeeWayId());
						f.setPaymentCode(fp.getCode());
						feePaymentDetails.add(f);
					}
				}
			}
		}
		return feePaymentDetails;
	}

	/**
	 * 查询缴费单Ids
	 * 
	 * @param feePayment
	 * @return
	 */
	private String findPaymentIds(FeePayment feePayment, Student student)
			throws Exception {
		List list2 = null;
		String hqlConditionExpression2 = "";
		PageParame p2 = new PageParame();
		list2 = new ArrayList();

		if (feePayment != null) {
			// 缴费方式
			if (feePayment.getFeeWayId() != 0) {
				hqlConditionExpression2 += " and feeWayId ="
						+ Constants.PLACEHOLDER;
				list2.add(feePayment.getFeeWayId());
			}
		}
		// 学生ID
		if (checkStudentInfo(student)) {
			String studentIds = findStudentIds(student);
			if (studentIds != null) {
				hqlConditionExpression2 += " and studentId in ("
						+ Constants.PLACEHOLDER + ")";
				list2.add("$" + studentIds);
			}
		}
		if (!hqlConditionExpression2.equals("")) {
			p2.setHqlConditionExpression(hqlConditionExpression2);
			p2.setValues(list2.toArray());
		}

		String pidString = ",";
		Long[] pidsLongs = feePaymentDao.getIDs(p2);
		// System.out.println(hqlConditionExpression2);
		if (pidsLongs == null || pidsLongs.length == 0) {
			return "0";
		}
		for (Long id : pidsLongs) {
			if (pidString.startsWith(",")) {
				pidString = id + "";
			} else {
				pidString += "," + id;
			}
		}
		return pidString;
	}

	/**
	 * 查询学生ID集合
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	private String findStudentIds(Student student) throws Exception {
		// 查询学生集合ID
		PageParame p = new PageParame();
		List list = null;
		String hqlConditionExpression = "";
		if (student != null) {
			list = new ArrayList();
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
		if (sidsLongs == null || sidsLongs.length == 0) {
			return "0";
		}
		for (Long id : sidsLongs) {
			if (studentIds.startsWith(",")) {
				studentIds = id + "";
			} else {
				studentIds += "," + id;
			}
		}
		return studentIds;
	}
	
	/**
	 * 查询学生ID集合__Hql语句
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	private String findStudentHql(Student student) throws Exception {
		// 查询学生Sql
		String stuHql = "select id from Student where 1=1 ";
		if (student != null) {
			// 姓名
			if (student.getName() != null && !student.getName().equals("")) {
				stuHql += " and name like '%"+student.getName()+"%' ";
					
			}
			// 证件号
			if (student.getCertNo() != null && !"".equals(student.getCertNo())) {
				stuHql += " and certNo like '%"+student.getCertNo()+"%' ";
				
			}
			// 院校
			if (student.getAcademyId() != 0) {
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
					stuHql += " and enrollmentBatchId in ( "+gbatchIds.substring(1, gbatchIds.length())+ " ) ";	
				} 
				else 
				{
					return null;
				}
			}
			// 批次
			if (student.getEnrollmentBatchId() != 0) {
				stuHql += " and  enrollmentBatchId= "+student.getEnrollmentBatchId();
						
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
		else
		{
			return null;
		}
		return stuHql;
	}

	/*
	 * 更新缴费单状态 (收费确认用)
	 * 
	 * @see net.cedu.biz.finance.PaymentBiz#updateFeePaymentStatus(int)
	 */
	public void updateFeePaymentStatus(int status, int fpId) throws Exception {
		// 更新缴费单
		feePaymentDao.update("set status=" + Constants.PLACEHOLDER, fpId + "",
				new Object[] { status });
		PageParame p = new PageParame();
		p.setHqlConditionExpression("and feePaymentId=" + Constants.PLACEHOLDER);
		p.setValues(new Object[] { fpId });
		Long[] fpdIds = feePaymentDetailDao.getIDs(p);
		String ids = ",";
		for (int i = 0; i < fpdIds.length; i++) {
			if (ids.startsWith(",")) {
				ids = fpdIds[i] + "";
			} else {
				ids += "," + fpdIds[i];
			}
		}
		// 判断有没有缴纳学费
		boolean isfail = false;
		if (!ids.startsWith(",")) {
			String[] sids = ids.split(",");
			for (int i = 0; i < sids.length; i++) {
				if (this.feePaymentDetailBiz.findById(Integer.valueOf(sids[i])) != null
						&& this.feePaymentDetailBiz.findById(
								Integer.valueOf(sids[i])).getFeeSubjectId() == FeeSubjectEnum.TuitionFee
								.value()) {
					isfail = true;
					break;
				}
			}

		}
		// 更新缴费单明细状态
		feePaymentDetailDao.update("set status=" + Constants.PLACEHOLDER, ids,
				new Object[] { status });

		// 更改学生状态(报名费和测试费确认时，得修改学生状态)
		if (this.feePaymentDao.findById(fpId) != null
				&& this.feePaymentDao.findById(fpId).getStudentId() != 0
				&& status >= Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN) {
			Student s = this.studentBiz.findStudentById(this.feePaymentDao
					.findById(fpId).getStudentId());
			if (s != null) {
				if (isfail) {
					s.setTuitionStatus(s.getTuitionStatus() + 1);
				}
				if (s.getStatus() < Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI) {
					s.setStatus(Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI);
					s.setRegistrationTime(this.feePaymentDao.findById(fpId)
							.getCreatedTime());
				}
				studentBiz.updateStudentInfo(s);
			}
		}
	}

	/*
	 * 缴费单作废（更新缴费单状态，以及缴费单明细状态，及其所有关联缴费单的表都还原）
	 * 
	 * @see net.cedu.biz.finance.PaymentBiz#updateFeePaymentInvalidStatus(int,
	 * int)
	 */
	public void updateFeePaymentInvalidStatus(int status, int fpId, int userId)
			throws Exception {

		// 缴费单实体
		FeePayment feepayment = feePaymentDao.findById(fpId);
		// 缴费单明细集合
		List<FeePaymentDetail> feePaymentDetailList = new ArrayList<FeePaymentDetail>();

		// 更新缴费单
		feePaymentDao.update("set status=" + Constants.PLACEHOLDER, fpId + "",
				new Object[] { status });
		PageParame p = new PageParame();
		p.setHqlConditionExpression("and feePaymentId=" + Constants.PLACEHOLDER);
		p.setValues(new Object[] { fpId });
		Long[] fpdIds = feePaymentDetailDao.getIDs(p);
		String ids = ",";
		for (int i = 0; i < fpdIds.length; i++) {
			if (this.feePaymentDetailDao.findById(Integer.valueOf(fpdIds[i]
					.toString())) != null) {
				feePaymentDetailList.add(this.feePaymentDetailDao
						.findById(Integer.valueOf(fpdIds[i].toString())));
			}
			if (ids.startsWith(",")) {
				ids = fpdIds[i] + "";
			} else {
				ids += "," + fpdIds[i];
			}
		}
		// 更新缴费单明细状态
		feePaymentDetailDao.update("set status=" + Constants.PLACEHOLDER, ids,
				new Object[] { status });

		// 更新其他表 主要是优惠卷、待缴费单、学生账户、学生账户明细
		if (feePaymentDetailList != null && feePaymentDetailList.size() > 0) {
			for (FeePaymentDetail feedetail : feePaymentDetailList) {
				// 修改优惠卷
				String yhids = feedetail.getDiscountPolicyDetailId();
				if (yhids != null && !yhids.equals("") && yhids != ""
						&& yhids.length() > 0) {
					String[] did = yhids.split(",");
					for (int i = 0; i < did.length; i++) {
						DiscountApplication discount = this.discountApplicationBiz
								.findDiscountApplicationById(Integer
										.valueOf(did[i]));
						discount.setUsageFlag(Constants.POLICY_USING_STATUS_FALSE);
						this.discountApplicationBiz
								.updateDiscountApplication(discount);
					}
				}
				// 修改学生账户及其明细
				String zhmoney = "0";// 缴费单明细使用账户金额的情况
				String allzhmoney = "0";// 为待缴费单服务，不管是充值还是消费都累加
				List<StudentAccountAmountManagement> stulist = this.studentAccountAmountManagementBiz
						.findStudentAccountAmountManagementByFeePaymentDetailId(feedetail
								.getId());
				if (stulist != null && stulist.size() > 0) {
					for (StudentAccountAmountManagement stuamount : stulist) {
						allzhmoney = (new BigDecimal(allzhmoney).add(stuamount
								.getAccountMoney())).toString();
						// 添加账户明细
						StudentAccountAmountManagement newstuamount = new StudentAccountAmountManagement();
						newstuamount.setAccountId(stuamount.getAccountId());
						newstuamount.setAccountMoney(stuamount
								.getAccountMoney());
						// newstuamount.setCode(code);
						newstuamount.setCreatedTime(DateUtil
								.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
						newstuamount.setCreatorId(userId);
						newstuamount.setDeleteFlag(Constants.DELETE_FALSE);
						newstuamount.setDescription(ResourcesTool.getText(
								"finance", "tag"));
						newstuamount.setFeePaymentId(stuamount
								.getFeePaymentId());
						newstuamount.setFeeSubject(stuamount.getFeeSubject());
						if (stuamount.getTypes() == Constants.STATUS_RECHARGE) {
							zhmoney = (new BigDecimal(zhmoney)
									.subtract(stuamount.getAccountMoney()))
									.toString();
							newstuamount.setTypes(Constants.STATUS_CONSUMPTION);
						} else {
							zhmoney = (new BigDecimal(zhmoney).add(stuamount
									.getAccountMoney())).toString();
							newstuamount.setTypes(Constants.STATUS_RECHARGE);
						}
						this.studentAccountAmountManagementBiz
								.addStudentAccountAmountManagement(newstuamount);

						// 修改学生账户
						StudentAccountManagement newaccount = this.studentAccountManagementBiz
								.findStudentAccountManagementById(stuamount
										.getAccountId());
						if (newaccount != null) {
							newaccount.setAccountBalance(newaccount
									.getAccountBalance().add(
											new BigDecimal(zhmoney)));
							this.studentAccountManagementBiz
									.updateStudentAccountManagementById(newaccount);
						}
					}
				}

				// 修改待缴费单
				if (feedetail.getPendingPaymentId() != 0) {
					// 待缴费单使用的所有金额
					String allusemoney = ((new BigDecimal(
							feedetail.getAmountPaied()).add(new BigDecimal(
							feedetail.getDiscountAmount())))
							.add(new BigDecimal(allzhmoney))).toString();
					PendingFeePayment pend = this.pendingFeePaymentBiz
							.findPendingFeePaymentById(feedetail
									.getPendingPaymentId());
					if (pend != null) {
						pend.setAmountPaid(pend.getAmountPaid().subtract(
								new BigDecimal(allusemoney)));
						pend.setAmountSurplus(pend.getAmountSurplus().add(
								new BigDecimal(allusemoney)));
						if (((pend.getAmountSurplus())
								.compareTo(new BigDecimal(0))) <= 0)// 和0比较大于返回1小于返回-1等于返回0
						{
							pend.setStatus(Constants.PENDING_STATUS_PAID);
						} else {
							if (((pend.getAmountPaid())
									.compareTo(new BigDecimal(0))) <= 0) {
								pend.setStatus(Constants.PENDING_STATUS_UNPAID);
							} else {
								pend.setStatus(Constants.PENDING_STATUS_UNFINISHEDPAID);
							}
						}
						pend.setUpdatedTime(DateUtil
								.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
						pend.setUpdaterId(userId);
						this.pendingFeePaymentBiz.updatePendingFeePayment(pend);
					}

				}
			}
		}

	}

	/*
	 * 更新缴费单明细类型
	 * 
	 * @see net.cedu.biz.finance.PaymentBiz#updateFeePaymentDetailType(int, int)
	 */
	public void updateFeePaymentDetailType(int type, int fpdId)
			throws Exception {
		feePaymentDetailDao.update("set types=" + Constants.PLACEHOLDER, fpdId
				+ "", new Object[] { type });

	}

	/*
	 * 更新缴费单打印状态
	 * 
	 * @see net.cedu.biz.finance.PaymentBiz#updateFeePaymentPrintStatus(int,
	 * int)
	 */
	public void updateFeePaymentPrintStatus(int isPrint, int fpId)
			throws Exception {
		feePaymentDao.update("set isPrint=" + Constants.PLACEHOLDER, fpId + "",
				new Object[] { isPrint });

	}

	/**
	 * 查询属于 <b>汇款总部单</b> 的所有缴费单明细
	 * 
	 * @param orderBranchCeduId
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findDetailByOrderBranchCeduId(
			int orderBranchCeduId) throws Exception {
		return feePaymentDetailDao.getByProperty("orderBranchCeduId",
				orderBranchCeduId);
	}

	/*
	 * 查询反款单对应的缴费单明细
	 * 
	 * @see net.cedu.biz.finance.PaymentBiz#findDetailByOrderAcademyCeduId(int)
	 */
	public List<FeePaymentDetail> findDetailByOrderAcademyCeduId(
			int orderAcademyCeduId) throws Exception {
		return feePaymentDetailDao.getByProperty("orderBranchCeduId",
				orderAcademyCeduId);
	}

	/**
	 * 查询缴费单明细 关联（外键）字段（填充到对应的名称中）
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void wrapFeePaymentDetail(List<FeePaymentDetail> list)
			throws Exception {
		if (list == null)
			return;

		Iterator<FeePaymentDetail> iter = list.iterator();

		while (iter.hasNext()) {
			FeePaymentDetail detail = iter.next();
			FeePayment fp = feePaymentDao.findById(detail.getFeePaymentId());
			Student student = studentBiz.findStudentById(fp.getStudentId());

			detail.setStudentName(student.getName());
			detail.setPaymentCode(fp.getCode());

			FeeSubject fs = feeSubjectBiz.findFeeSubjectById(detail
					.getFeeSubjectId());
			if (fs != null)
				detail.setPaymentSubjectName(fs.getName());

			Academy academy = academyBiz
					.findAcademyById(student.getAcademyId());
			if (academy != null)
				detail.setSchoolName(academy.getName());

			// TODO 需要查询 学习中心（大多是相同的）吗？
			Branch branch = branchBiz.findBranchById(student.getBranchId());
			if (branch != null)
				detail.setBranchName(branch.getName());

			AcademyEnrollBatch batch = academyenrollbatchBiz
					.findAcademyEnrollBatchById(student.getEnrollmentBatchId());
			if (batch != null)
				detail.setAcademyenrollbatchName(batch.getEnrollmentName());
			// else
			// System.out.println("ha....");

			Level level = levelbiz.findLevelById(student.getLevelId());
			if (level != null)
				detail.setLevelName(level.getName());

			Major major = majorbiz.findMajorById(student.getMajorId());
			if (major != null)
				detail.setMajorName(major.getName());
		}
	}

	/*
	 * 缴测试费 操纵4个表(缴费单、缴费单明细、待缴费单、优惠券)
	 * 
	 * @see
	 * net.cedu.biz.finance.PaymentBiz#addFeePaymentDetailFeePaymentPending(
	 * net.cedu.entity.finance.FeePaymentDetail,
	 * net.cedu.entity.finance.FeePayment, int)
	 */
	public boolean addFeePaymentDetailFeePaymentPending(
			FeePaymentDetail feePaymentDetail, FeePayment feePayment, int isFee)
			throws Exception {
		boolean isfail = false;
		// 查询待缴测试费
		PendingFeePayment pendingFeePayment = pendingFeePaymentBiz
				.findPendingFeePaymentById(feePaymentDetail
						.getPendingPaymentId());
		if (pendingFeePayment != null) {
			// 添加缴费单
			// feePayment.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
			feePayment.setUpdatedTime(DateUtil
					.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
			feePayment.setDeleteFlag(Constants.DELETE_FALSE);
			feePayment.setFeePayment(Double.valueOf(pendingFeePayment
					.getAmountSurplus().toString()));
			feePayment.setTotalAmount(feePayment.getFeePayment());
			feePayment
					.setPamentType(Constants.FEE_PAYMENT_TYPE_OFFICIALLY_SINGLE);

			feePayment.setCommonBatch(this.feePaymentDao
					.getCommonBatch(feePayment.getStudentId()));
			// feePayment.setPosSerialNo(posSerialNo) pos流水号
			if (isFee == 1) {
				// 缴费方式不同收费时的缴费单状态不同
				if (feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_DI_SAN_FAN_ZHI_FU) {
					feePayment
							.setStatus(Constants.PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU);// 缴费单状态(前期跟明细一样)
				} else if (feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_XIAN_JIN_HUI_ZONG_BU) {
					feePayment
							.setStatus(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);// 缴费单状态(前期跟明细一样)
				} else if (feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_XIAN_JIN_HUI_YUAN_XIAO) {
					feePayment
							.setStatus(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);// 缴费单状态(前期跟明细一样)
				} else if (feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_POS_ZHI_HUI_ZONG_BU
						|| feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_WANG_YIN_ZONG_BU) {
					feePayment
							.setStatus(Constants.PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU);// 缴费单状态(前期跟明细一样)
				} else if (feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_POS_ZHI_HUI_YUAN_XIAO
						|| feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_WANG_YIN_YUAN_XIAO) {
					feePayment
							.setStatus(Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO);// 缴费单状态(前期跟明细一样)
				}
			} else {
				feePayment.setStatus(Constants.PAYMENT_STATUS_YI_KAI_DAN);
			}
			this.feePaymentDao.save(feePayment);

			// 修改优惠卷
			String surplusmoney = pendingFeePayment.getAmountSurplus()
					.toString();// 应缴金额
			String ids = feePaymentDetail.getDiscountPolicyDetailId();// 优惠卷Id集合
			if (ids != null && !ids.equals("") && ids != "" && ids.length() > 0) {
				String[] did = ids.split(",");
				for (int i = 0; i < did.length; i++) {
					DiscountApplication discount = this.discountApplicationBiz
							.findDiscountApplicationById(Integer
									.valueOf(did[i]));
					discount.setUsageFlag(Constants.POLICY_USING_STATUS_TRUE);
					isfail = this.discountApplicationBiz
							.updateDiscountApplication(discount);

					// 给相应的缴费单明细附上优惠的值
					String nowdiscount = "0";// 当前优惠金额
					if (discount.getDiscountWay() == Constants.MONEY_FORM_RATIO) {
						if ((discount.getMoney().compareTo(new BigDecimal(0))) > 0) {
							nowdiscount = (new BigDecimal(surplusmoney)
									.multiply((discount.getMoney().divide(
											new BigDecimal("100"), 2))))
									.toString();
						}
					} else if (discount.getDiscountWay() == Constants.MONEY_FORM_AMOUNT) {
						nowdiscount = discount.getMoney().toString();
					}
					// surplusmoney=(new BigDecimal(surplusmoney).subtract(new
					// BigDecimal(nowdiscount))).toString();
					// 计算出优惠是属于哪种优惠
					StudentDiscountPolicy sdpolicy = studentDiscountPolicyBiz
							.findStudentDiscountPolicyById(discount
									.getPolicyStandardId());
					if (sdpolicy != null && sdpolicy.getTargetObjectId() != 0) {
						if (sdpolicy.getTargetObjectId() == Constants.POLICY_TARGET_OBJECT_CEDU) {
							feePaymentDetail.setCeduDiscount(Double
									.valueOf((new BigDecimal(feePaymentDetail
											.getCeduDiscount())
											.add(new BigDecimal(nowdiscount)))
											.toString()));
						} else if (sdpolicy.getTargetObjectId() == Constants.POLICY_TARGET_OBJECT_BRANCH) {
							feePaymentDetail.setBranchDiscount(Double
									.valueOf((new BigDecimal(feePaymentDetail
											.getBranchDiscount())
											.add(new BigDecimal(nowdiscount)))
											.toString()));
						} else if (sdpolicy.getTargetObjectId() == Constants.POLICY_TARGET_OBJECT_ACADEMY) {
							if (sdpolicy.getIsShared() == Constants.POLICY_IS_SHARED_FLASE) {
								feePaymentDetail
										.setAcademyDiscount(Double.valueOf((new BigDecimal(
												feePaymentDetail
														.getAcademyDiscount())
												.add(new BigDecimal(nowdiscount)))
												.toString()));
							} else if (sdpolicy.getIsShared() == Constants.POLICY_IS_SHARED_TRUE) {
								feePaymentDetail
										.setAcademyCeduDiscount(Double.valueOf((new BigDecimal(
												feePaymentDetail
														.getAcademyCeduDiscount())
												.add(new BigDecimal(nowdiscount)))
												.toString()));
							}
						} else {
							feePaymentDetail.setChannelDiscount(Double
									.valueOf((new BigDecimal(feePaymentDetail
											.getChannelDiscount())
											.add(new BigDecimal(nowdiscount)))
											.toString()));
						}
					}
				}
			}

			// 添加缴费单明细
			feePaymentDetail
					.setAmountPaied(Double.valueOf(pendingFeePayment
							.getAmountSurplus()
							.subtract(
									new BigDecimal(feePaymentDetail
											.getDiscountAmount())).toString()));
			feePaymentDetail.setCode("1");
			feePaymentDetail.setCreatedTime(feePayment.getCreatedTime());
			// feePaymentDetail.setCreditPointPaied(creditPointPaied)实缴学分数
			// feePaymentDetail.setCreditPointToPay(creditPointToPay)应缴学分数
			feePaymentDetail.setDeleteFlag(Constants.DELETE_FALSE);
			feePaymentDetail.setFeePaymentId(feePayment.getId());
			feePaymentDetail
					.setIsFromChannel(Constants.FEE_PAYMENT_CHANNEL_REBATE_FALSE);
			feePaymentDetail
					.setIsInvoiced(Constants.FEE_PAYMENT_IS_INVOICED_FALSE);
			feePaymentDetail.setIsSupper(Constants.FEE_PAYMENT_IS_SUPPER_FALSE);
			feePaymentDetail.setMoneyToPay(Double.valueOf(pendingFeePayment
					.getAmountSurplus().toString()));
			feePaymentDetail.setPendingPaymentId(pendingFeePayment.getId());
			feePaymentDetail.setPolicyFeeDetailId(pendingFeePayment
					.getPolicyFeeDetailId());// 收费政策Id
			// 通过缴费单方式确定缴费单明细账户金额归属问题（2012-01-09 重构）
			if (feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_POS_ZHI_HUI_YUAN_XIAO
					|| feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_WANG_YIN_YUAN_XIAO) {
				// 涉及到优惠金额
				feePaymentDetail.setBranchAccount(Double
						.valueOf((new BigDecimal(feePaymentDetail
								.getAmountPaied()).add(new BigDecimal(
								feePaymentDetail.getRechargeAmount())))
								.toString()));

				feePaymentDetail.setAcademyAccount(((new BigDecimal(
						feePaymentDetail.getAmountPaied()).add(new BigDecimal(
						feePaymentDetail.getRechargeAmount()))).add(
						new BigDecimal(feePaymentDetail.getDiscountAmount()))
						.subtract(
								new BigDecimal(feePaymentDetail
										.getAcademyDiscount()))
						.subtract(new BigDecimal(feePaymentDetail
								.getAcademyCeduDiscount()))).doubleValue());
				feePaymentDetail.setBranchAccount((new BigDecimal(
						feePaymentDetail.getBranchAccount())
						.subtract(new BigDecimal(feePaymentDetail
								.getAcademyAccount()))).doubleValue());
			} else {
				feePaymentDetail.setBranchAccount(Double
						.valueOf((new BigDecimal(feePaymentDetail
								.getAmountPaied()).add(new BigDecimal(
								feePaymentDetail.getRechargeAmount())))
								.toString()));
			}
			// if(isFee==1)
			// {
			// feePaymentDetail.setStatus(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);
			// }
			// else
			// {
			// feePaymentDetail.setStatus(Constants.PAYMENT_STATUS_YI_KAI_DAN);
			// }
			feePaymentDetail.setStatus(feePayment.getStatus());

			feePaymentDetail.setStudentId(feePayment.getStudentId());
			feePaymentDetail
					.setTypes(Constants.FEE_PAYMENT_TYPE_OFFICIALLY_SINGLE);
			feePaymentDetail.setUpdatedTime(DateUtil
					.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
			isfail = this.feePaymentDetailBiz
					.addFeePaymentDetail(feePaymentDetail);

			// 修改待缴测试费
			pendingFeePayment.setAmountPaid(pendingFeePayment
					.getAmountSurplus());
			pendingFeePayment.setAmountSurplus(new BigDecimal(0));
			pendingFeePayment.setStatus(Constants.PENDING_STATUS_PAID);
			pendingFeePayment.setUpdatedTime(DateUtil
					.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
			pendingFeePayment.setUpdaterId(feePaymentDetail.getCreatorId());
			isfail = this.pendingFeePaymentBiz
					.updatePendingFeePayment(pendingFeePayment);

		}
		return isfail;
	}

	/*
	 * 缴报名费和测试费(通用接口) 操纵6个表(缴费单、缴费单明细、待缴费单、优惠券、收据、学生状态)
	 * 
	 * @see
	 * net.cedu.biz.finance.PaymentBiz#addAllFeePaymentDetailFeePaymentPending
	 * (java.util.List, net.cedu.entity.finance.FeePayment, int, boolean)
	 */
	public int addAllFeePaymentDetailFeePaymentPending(
			List<FeePaymentDetail> feePaymentDetailList, FeePayment feePayment,
			int isFee, boolean isstatus) throws Exception {
		int count = 0;
		// 收据号的使用
		if (feePayment.getIsPrint() == 1 && feePayment.getBarCode() != null
				&& !feePayment.getBarCode().equals("")
				&& feePayment.getBarCode() != "") {
			if (this.receiptBiz.updateReceiptStatusByCode(feePayment
					.getBarCode()) != 1) {
				count = 1;
				return count;
			}
		}
		// 查询总缴费金额
		double allmoney = 0;
		double fpDisa=0;//总优惠金额
		for (FeePaymentDetail feedetail : feePaymentDetailList) {
			allmoney += Double.valueOf((new BigDecimal(feedetail
					.getAmountPaied()).add(new BigDecimal(feedetail
					.getDiscountAmount()))).toString());
			
			fpDisa=(new BigDecimal(fpDisa).add(new BigDecimal(feedetail.getDiscountAmount()))).doubleValue();
		}
		// 添加缴费单
		// feePayment.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
		feePayment.setUpdatedTime(DateUtil
				.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
		feePayment.setDeleteFlag(Constants.DELETE_FALSE);
		feePayment.setFeePayment(allmoney);
		feePayment.setTotalAmount((new BigDecimal(allmoney).subtract(new BigDecimal(fpDisa))).doubleValue());
		feePayment.setDiscountAmount(fpDisa);
		feePayment.setPamentType(Constants.FEE_PAYMENT_TYPE_OFFICIALLY_SINGLE);
		feePayment.setCommonBatch(this.feePaymentDao.getCommonBatch(feePayment
				.getStudentId()));
		// feePayment.setPosSerialNo(posSerialNo) pos流水号
		if (isFee == 1) {
			// 缴费方式不同收费时的缴费单状态不同
			if (feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_DI_SAN_FAN_ZHI_FU) {
				feePayment
						.setStatus(Constants.PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU);// 缴费单状态(前期跟明细一样)
			} else if (feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_XIAN_JIN_HUI_ZONG_BU) {
				feePayment
						.setStatus(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);// 缴费单状态(前期跟明细一样)
			} else if (feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_XIAN_JIN_HUI_YUAN_XIAO) {
				feePayment
						.setStatus(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);// 缴费单状态(前期跟明细一样)
			} else if (feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_POS_ZHI_HUI_ZONG_BU
					|| feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_WANG_YIN_ZONG_BU) {
				feePayment
						.setStatus(Constants.PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU);// 缴费单状态(前期跟明细一样)
			} else if (feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_POS_ZHI_HUI_YUAN_XIAO
					|| feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_WANG_YIN_YUAN_XIAO) {
				feePayment
						.setStatus(Constants.PAYMENT_STATUS_YI_QUE_REN_DAI_HUI_KUAN);// 缴费单状态(前期跟明细一样)
			}
		} else {
			feePayment.setStatus(Constants.PAYMENT_STATUS_YI_KAI_DAN);
		}
		this.feePaymentDao.save(feePayment);

		// 添加缴费单明细
		int index = 1;
		for (FeePaymentDetail feePaymentDetail : feePaymentDetailList) {
			// 未缴费的不计入缴费单
			if ((new BigDecimal(feePaymentDetail.getAmountPaied())
					.add(new BigDecimal(feePaymentDetail.getDiscountAmount()))
					.compareTo(new BigDecimal(0))) <= 0) {
				continue;
			}
			// 收费标准明细 查询缴费次数和费用科目
			FeeStandard feeStandard = this.feeStandardBiz
					.findFeeStandardById(feePaymentDetail.getPolicyStandardId());
			// 待缴费
			PendingFeePayment pendingFeePayment = pendingFeePaymentBiz
					.findPendingFeePaymentById(feePaymentDetail
							.getPendingPaymentId());

			// 修改优惠卷
			String surplusmoney = pendingFeePayment.getAmountSurplus()
					.toString();// 应缴金额
			String ids = feePaymentDetail.getDiscountPolicyDetailId();// 优惠卷Id集合
			if (ids != null && !ids.equals("") && ids != "" && ids.length() > 0) {
				String[] did = ids.split(",");
				for (int i = 0; i < did.length; i++) {
					DiscountApplication discount = this.discountApplicationBiz
							.findDiscountApplicationById(Integer
									.valueOf(did[i]));
					discount.setUsageFlag(Constants.POLICY_USING_STATUS_TRUE);
					this.discountApplicationBiz
							.updateDiscountApplication(discount);

					// 给相应的缴费单明细附上优惠的值
					String nowdiscount = "0";// 当前优惠金额
					if (discount.getDiscountWay() == Constants.MONEY_FORM_RATIO) {
						if ((discount.getMoney().compareTo(new BigDecimal(0))) > 0) {
							nowdiscount = (new BigDecimal(surplusmoney)
									.multiply((discount.getMoney().divide(
											new BigDecimal("100"), 2))))
									.toString();
						}
					} else if (discount.getDiscountWay() == Constants.MONEY_FORM_AMOUNT) {
						nowdiscount = discount.getMoney().toString();
					}
					// surplusmoney=(new BigDecimal(surplusmoney).subtract(new
					// BigDecimal(nowdiscount))).toString();//比例跟优惠先后无关，都是应缴金额作为基数
					// 计算出优惠是属于哪种优惠
					StudentDiscountPolicy sdpolicy = studentDiscountPolicyBiz
							.findStudentDiscountPolicyById(discount
									.getPolicyStandardId());
					if (sdpolicy != null && sdpolicy.getTargetObjectId() != 0) {
						if (sdpolicy.getTargetObjectId() == Constants.POLICY_TARGET_OBJECT_CEDU) {
							feePaymentDetail.setCeduDiscount(Double
									.valueOf((new BigDecimal(feePaymentDetail
											.getCeduDiscount())
											.add(new BigDecimal(nowdiscount)))
											.toString()));
						} else if (sdpolicy.getTargetObjectId() == Constants.POLICY_TARGET_OBJECT_BRANCH) {
							feePaymentDetail.setBranchDiscount(Double
									.valueOf((new BigDecimal(feePaymentDetail
											.getBranchDiscount())
											.add(new BigDecimal(nowdiscount)))
											.toString()));
						} else if (sdpolicy.getTargetObjectId() == Constants.POLICY_TARGET_OBJECT_ACADEMY) {
							if (sdpolicy.getIsShared() == Constants.POLICY_IS_SHARED_FLASE) {
								feePaymentDetail
										.setAcademyDiscount(Double.valueOf((new BigDecimal(
												feePaymentDetail
														.getAcademyDiscount())
												.add(new BigDecimal(nowdiscount)))
												.toString()));
							} else if (sdpolicy.getIsShared() == Constants.POLICY_IS_SHARED_TRUE) {
								feePaymentDetail
										.setAcademyCeduDiscount(Double.valueOf((new BigDecimal(
												feePaymentDetail
														.getAcademyCeduDiscount())
												.add(new BigDecimal(nowdiscount)))
												.toString()));
							}
						} else {
							feePaymentDetail.setChannelDiscount(Double
									.valueOf((new BigDecimal(feePaymentDetail
											.getChannelDiscount())
											.add(new BigDecimal(nowdiscount)))
											.toString()));
						}
					}
				}
			}

			// 添加缴费单明细
			feePaymentDetail.setCode(index + "");
			feePaymentDetail.setCreatedTime(feePayment.getCreatedTime());
			// feePaymentDetail.setCreditPointPaied(creditPointPaied)实缴学分数
			// feePaymentDetail.setCreditPointToPay(creditPointToPay)应缴学分数

			feePaymentDetail.setFeeSubjectId(this.policyFeeBiz
					.findPolicyFeeById(feeStandard.getPolicyFeeDetailId())
					.getFeeSubjectId());
			feePaymentDetail.setBatchId(feeStandard.getFeeBatchId());
			feePaymentDetail.setDeleteFlag(Constants.DELETE_FALSE);
			feePaymentDetail.setFeePaymentId(feePayment.getId());
			feePaymentDetail
					.setIsFromChannel(Constants.FEE_PAYMENT_CHANNEL_REBATE_FALSE);
			feePaymentDetail
					.setIsInvoiced(Constants.FEE_PAYMENT_IS_INVOICED_FALSE);
			feePaymentDetail.setIsSupper(Constants.FEE_PAYMENT_IS_SUPPER_FALSE);
			feePaymentDetail.setMoneyToPay(Double.valueOf(pendingFeePayment
					.getAmountSurplus().toString()));
			feePaymentDetail.setPolicyFeeDetailId(pendingFeePayment
					.getPolicyFeeDetailId());// 收费政策Id

//2012-03-29// 通过缴费单方式确定缴费单明细账户金额归属问题（2012-01-09 重构）
//			if (feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_POS_ZHI_HUI_YUAN_XIAO
//					|| feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_WANG_YIN_YUAN_XIAO) {
//				// feePaymentDetail.setAcademyAccount(Double.valueOf((new
//				// BigDecimal(feePaymentDetail.getAmountPaied()).add(new
//				// BigDecimal(feePaymentDetail.getRechargeAmount()))).toString()));
//				feePaymentDetail.setBranchAccount(Double
//						.valueOf((new BigDecimal(feePaymentDetail
//								.getAmountPaied()).add(new BigDecimal(
//								feePaymentDetail.getRechargeAmount())))
//								.toString()));
//
//				feePaymentDetail.setAcademyAccount(((new BigDecimal(
//						feePaymentDetail.getAmountPaied()).add(new BigDecimal(
//						feePaymentDetail.getRechargeAmount()))).add(
//						new BigDecimal(feePaymentDetail.getDiscountAmount()))
//						.subtract(
//								new BigDecimal(feePaymentDetail
//										.getAcademyDiscount()))
//						.subtract(new BigDecimal(feePaymentDetail
//								.getAcademyCeduDiscount()))).doubleValue());
//				feePaymentDetail.setBranchAccount((new BigDecimal(
//						feePaymentDetail.getBranchAccount())
//						.subtract(new BigDecimal(feePaymentDetail
//								.getAcademyAccount()))).doubleValue());
//			} else {
				feePaymentDetail.setBranchAccount(Double
						.valueOf((new BigDecimal(feePaymentDetail
								.getAmountPaied()).add(new BigDecimal(
								feePaymentDetail.getRechargeAmount())))
								.toString()));
//			}
			// if(isFee==1)
			// {
			// feePaymentDetail.setStatus(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);
			// }
			// else
			// {
			// feePaymentDetail.setStatus(Constants.PAYMENT_STATUS_YI_KAI_DAN);
			// }
			feePaymentDetail.setStatus(feePayment.getStatus());

			feePaymentDetail.setStudentId(feePayment.getStudentId());
			feePaymentDetail
					.setTypes(Constants.FEE_PAYMENT_TYPE_OFFICIALLY_SINGLE);
			feePaymentDetail.setUpdatedTime(DateUtil
					.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
			feePaymentDetail.setCreatorId(feePayment.getCreatorId());
			feePaymentDetail.setUpdaterId(feePayment.getUpdaterId());
			this.feePaymentDetailBiz.addFeePaymentDetail(feePaymentDetail);
			index++;

			// 修改待缴测试费
			pendingFeePayment.setAmountPaid((new BigDecimal(feePaymentDetail
					.getAmountPaied()).add(new BigDecimal(feePaymentDetail
					.getDiscountAmount()))));
			pendingFeePayment.setAmountSurplus(pendingFeePayment
					.getAmountSurplus().subtract(
							pendingFeePayment.getAmountPaid()));
			if ((pendingFeePayment.getAmountSurplus().compareTo(new BigDecimal(
					0))) <= 0)// 和0比较大于返回1小于返回-1等于返回0
			{
				pendingFeePayment.setStatus(Constants.PENDING_STATUS_PAID);
			} else {
				pendingFeePayment
						.setStatus(Constants.PENDING_STATUS_UNFINISHEDPAID);
			}
			pendingFeePayment.setUpdatedTime(DateUtil
					.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
			pendingFeePayment.setUpdaterId(feePayment.getCreatorId());
			this.pendingFeePaymentBiz
					.updatePendingFeePayment(pendingFeePayment);

			// //修改优惠卷
			// String ids =feePaymentDetail.getDiscountPolicyDetailId();
			// if(ids!=null && !ids.equals("") && ids!="" && ids.length()>0)
			// {
			// String[] did=ids.split(",");
			// for(int i=0;i<did.length;i++)
			// {
			// DiscountApplication
			// discount=this.discountApplicationBiz.findDiscountApplicationById(Integer.valueOf(did[i]));
			// discount.setUsageFlag(Constants.POLICY_USING_STATUS_TRUE);
			// this.discountApplicationBiz.updateDiscountApplication(discount);
			// }
			// }

		}
		if (isstatus) {
			// 更改学生状态
			Student s = studentBiz.findStudentById(feePayment.getStudentId());
			s.setStatus(Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI);
			s.setRegistrationTime(feePayment.getCreatedTime());
			studentBiz.updateStudentInfo(s);
		}

		return count;
	}

	/*
	 * 缴其他所有费用(除去报名费和测试费(通用接口)) 操纵7个表(缴费单、缴费单明细、待缴费单、优惠券、收据、学生账户、学生账户明细)
	 * 
	 * @see
	 * net.cedu.biz.finance.PaymentBiz#addAllOtherFeePaymentDetailFeePaymentPending
	 * (java.util.List, net.cedu.entity.finance.FeePayment, int)
	 */
	public int addAllOtherFeePaymentDetailFeePaymentPending(
			List<FeePaymentDetail> feePaymentDetailList, FeePayment feePayment,
			int isFee) throws Exception {
		int count = 0;// 返回值
		String shenyumoney = "0";// 该阶段缴纳的费用 为学生账户判断所用
		String shijiaoMoney = "0";// 实缴金额
		// 收据号的使用
		if (feePayment.getIsPrint() == 1 && feePayment.getBarCode() != null
				&& !feePayment.getBarCode().equals("")
				&& feePayment.getBarCode() != "") {
			if (this.receiptBiz.updateReceiptStatusByCode(feePayment
					.getBarCode()) != 1) {
				count = 1;
				return count;
			}
		}

		// 查询总缴费金额
		double allmoney = 0;
		for (FeePaymentDetail feedetail : feePaymentDetailList) {
			// 待缴费
			PendingFeePayment pendingfeepayment = pendingFeePaymentBiz
					.findPendingFeePaymentById(feedetail.getPendingPaymentId());
			if (((new BigDecimal(feedetail.getAmountPaied())
					.add(new BigDecimal(feedetail.getDiscountAmount())))
					.compareTo(pendingfeepayment.getAmountSurplus())) >= 0) {
				allmoney += Double.valueOf(pendingfeepayment.getAmountSurplus()
						.toString());
			} else {
				if ((new BigDecimal(feedetail.getAmountPaied())
						.add(new BigDecimal(feedetail.getRechargeAmount()))
						.add(new BigDecimal(feedetail.getDiscountAmount())))
						.compareTo(pendingfeepayment.getAmountSurplus()) < 0) {
					// 多次缴费的情况 优惠金额的基数按实缴金额为准
					String ids = feedetail.getDiscountPolicyDetailId();// 优惠卷Id集合
					double alldiscount = 0;
					if (ids != null && !ids.equals("") && ids != ""
							&& ids.length() > 0) {
						String[] did = ids.split(",");
						for (int i = 0; i < did.length; i++) {
							// 算出优惠总金额
							String nowdiscount = "0";// 当前优惠金额
							DiscountApplication discount = this.discountApplicationBiz
									.findDiscountApplicationById(Integer
											.valueOf(did[i]));
							if (discount.getDiscountWay() == Constants.MONEY_FORM_RATIO) {
								if ((discount.getMoney()
										.compareTo(new BigDecimal(0))) > 0) {
									nowdiscount = ((new BigDecimal(
											feedetail.getAmountPaied())
											.add(new BigDecimal(feedetail
													.getRechargeAmount())))
											.multiply((discount.getMoney()
													.divide(new BigDecimal(
															"100"), 2))))
											.toString();
								}
							} else if (discount.getDiscountWay() == Constants.MONEY_FORM_AMOUNT) {
								nowdiscount = discount.getMoney().toString();
							}
							alldiscount += Double.valueOf(nowdiscount);
						}
					}
					feedetail.setDiscountAmount(alldiscount);
				}
				allmoney += Double.valueOf(((new BigDecimal(feedetail
						.getAmountPaied()).add(new BigDecimal(feedetail
						.getDiscountAmount()))).add(new BigDecimal(feedetail
						.getRechargeAmount()))).toString());
			}
		}
		// 添加缴费单
		// feePayment.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
		feePayment.setUpdatedTime(DateUtil
				.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
		feePayment.setDeleteFlag(Constants.DELETE_FALSE);
		feePayment.setFeePayment(allmoney);
		feePayment.setPamentType(Constants.FEE_PAYMENT_TYPE_OFFICIALLY_SINGLE);
		feePayment.setCommonBatch(this.feePaymentDao.getCommonBatch(feePayment
				.getStudentId()));
		// feePayment.setPosSerialNo(posSerialNo) pos流水号
		if (isFee == 1) {
			// 缴费方式不同收费时的缴费单状态不同
			if (feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_DI_SAN_FAN_ZHI_FU) {
				feePayment
						.setStatus(Constants.PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU);// 缴费单状态(前期跟明细一样)
			} else if (feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_XIAN_JIN_HUI_ZONG_BU) {
				feePayment
						.setStatus(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);// 缴费单状态(前期跟明细一样)
			} else if (feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_XIAN_JIN_HUI_YUAN_XIAO) {
				feePayment
						.setStatus(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);// 缴费单状态(前期跟明细一样)
			} else if (feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_POS_ZHI_HUI_ZONG_BU) {
				feePayment
						.setStatus(Constants.PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU);// 缴费单状态(前期跟明细一样)
			} else if (feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_POS_ZHI_HUI_YUAN_XIAO) {
				feePayment
						.setStatus(Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO);// 缴费单状态(前期跟明细一样)
			}
		} else {
			feePayment.setStatus(Constants.PAYMENT_STATUS_YI_KAI_DAN);
		}
		this.feePaymentDao.save(feePayment);

		// 添加缴费单明细
		int index = 1;
		for (FeePaymentDetail feePaymentDetail : feePaymentDetailList) {
			shijiaoMoney = feePaymentDetail.getAmountPaied() + "";
			// 未缴费的不计入缴费单
			if ((((new BigDecimal(feePaymentDetail.getAmountPaied())
					.add(new BigDecimal(feePaymentDetail.getDiscountAmount())))
					.add(new BigDecimal(feePaymentDetail.getRechargeAmount())))
					.compareTo(new BigDecimal(0))) <= 0) {

				continue;
			}
			// 收费标准明细 查询缴费次数和费用科目
			FeeStandard feeStandard = this.feeStandardBiz
					.findFeeStandardById(feePaymentDetail.getPolicyStandardId());
			// 待缴费
			PendingFeePayment pendingFeePayment = pendingFeePaymentBiz
					.findPendingFeePaymentById(feePaymentDetail
							.getPendingPaymentId());

			// 修改优惠卷
			String surplusmoney = pendingFeePayment.getAmountSurplus()
					.toString();// 应缴金额
			String ids = feePaymentDetail.getDiscountPolicyDetailId();// 优惠卷Id集合
			if (ids != null && !ids.equals("") && ids != "" && ids.length() > 0) {
				String[] did = ids.split(",");
				for (int i = 0; i < did.length; i++) {
					DiscountApplication discount = this.discountApplicationBiz
							.findDiscountApplicationById(Integer
									.valueOf(did[i]));
					discount.setUsageFlag(Constants.POLICY_USING_STATUS_TRUE);
					this.discountApplicationBiz
							.updateDiscountApplication(discount);

					if ((new BigDecimal(feePaymentDetail.getAmountPaied())
							.add(new BigDecimal(feePaymentDetail
									.getRechargeAmount())).add(new BigDecimal(
							feePaymentDetail.getDiscountAmount())))
							.compareTo(pendingFeePayment.getAmountSurplus()) < 0) {
						surplusmoney = (new BigDecimal(
								feePaymentDetail.getAmountPaied())
								.add(new BigDecimal(feePaymentDetail
										.getRechargeAmount()))).toString();
					}

					// 给相应的缴费单明细附上优惠的值
					String nowdiscount = "0";// 当前优惠金额
					if (discount.getDiscountWay() == Constants.MONEY_FORM_RATIO) {
						if ((discount.getMoney().compareTo(new BigDecimal(0))) > 0) {
							nowdiscount = (new BigDecimal(surplusmoney)
									.multiply((discount.getMoney().divide(
											new BigDecimal("100"), 2))))
									.toString();
						}
					} else if (discount.getDiscountWay() == Constants.MONEY_FORM_AMOUNT) {
						nowdiscount = discount.getMoney().toString();
					}
					// surplusmoney=(new BigDecimal(surplusmoney).subtract(new
					// BigDecimal(nowdiscount))).toString();
					// 计算出优惠是属于哪种优惠
					StudentDiscountPolicy sdpolicy = studentDiscountPolicyBiz
							.findStudentDiscountPolicyById(discount
									.getPolicyStandardId());
					if (sdpolicy != null && sdpolicy.getTargetObjectId() != 0) {
						if (sdpolicy.getTargetObjectId() == Constants.POLICY_TARGET_OBJECT_CEDU) {
							feePaymentDetail.setCeduDiscount(Double
									.valueOf((new BigDecimal(feePaymentDetail
											.getCeduDiscount())
											.add(new BigDecimal(nowdiscount)))
											.toString()));
						} else if (sdpolicy.getTargetObjectId() == Constants.POLICY_TARGET_OBJECT_BRANCH) {
							feePaymentDetail.setBranchDiscount(Double
									.valueOf((new BigDecimal(feePaymentDetail
											.getBranchDiscount())
											.add(new BigDecimal(nowdiscount)))
											.toString()));
						} else if (sdpolicy.getTargetObjectId() == Constants.POLICY_TARGET_OBJECT_ACADEMY) {
							if (sdpolicy.getIsShared() == Constants.POLICY_IS_SHARED_FLASE) {
								feePaymentDetail
										.setAcademyDiscount(Double.valueOf((new BigDecimal(
												feePaymentDetail
														.getAcademyDiscount())
												.add(new BigDecimal(nowdiscount)))
												.toString()));
							} else if (sdpolicy.getIsShared() == Constants.POLICY_IS_SHARED_TRUE) {
								feePaymentDetail
										.setAcademyCeduDiscount(Double.valueOf((new BigDecimal(
												feePaymentDetail
														.getAcademyCeduDiscount())
												.add(new BigDecimal(nowdiscount)))
												.toString()));
							}
						} else {
							feePaymentDetail.setChannelDiscount(Double
									.valueOf((new BigDecimal(feePaymentDetail
											.getChannelDiscount())
											.add(new BigDecimal(nowdiscount)))
											.toString()));
						}
					}
				}
			}

			// 添加单个缴费单明细
			feePaymentDetail.setCode(index + "");
			feePaymentDetail.setCreatedTime(feePayment.getCreatedTime());
			// feePaymentDetail.setCreditPointPaied(creditPointPaied)实缴学分数
			// feePaymentDetail.setCreditPointToPay(creditPointToPay)应缴学分数
			// 实缴金额 多余的钱放入充值账户
			if (((new BigDecimal(feePaymentDetail.getAmountPaied())
					.add(new BigDecimal(feePaymentDetail.getDiscountAmount())))
					.compareTo(pendingFeePayment.getAmountSurplus())) >= 0) {
				feePaymentDetail.setAmountPaied(Double
						.valueOf(pendingFeePayment
								.getAmountSurplus()
								.subtract(
										(new BigDecimal(feePaymentDetail
												.getDiscountAmount())))
								.toString()));
			}

			feePaymentDetail.setFeeSubjectId(this.policyFeeBiz
					.findPolicyFeeById(feeStandard.getPolicyFeeDetailId())
					.getFeeSubjectId());
			feePaymentDetail.setBatchId(feeStandard.getFeeBatchId());
			feePaymentDetail.setDeleteFlag(Constants.DELETE_FALSE);
			feePaymentDetail.setFeePaymentId(feePayment.getId());
			feePaymentDetail
					.setIsFromChannel(Constants.FEE_PAYMENT_CHANNEL_REBATE_FALSE);
			feePaymentDetail
					.setIsInvoiced(Constants.FEE_PAYMENT_IS_INVOICED_FALSE);
			feePaymentDetail.setIsSupper(Constants.FEE_PAYMENT_IS_SUPPER_FALSE);
			feePaymentDetail.setMoneyToPay(Double.valueOf(pendingFeePayment
					.getAmountSurplus().toString()));
			feePaymentDetail.setPolicyFeeDetailId(pendingFeePayment
					.getPolicyFeeDetailId());// 收费政策Id
			// 通过缴费单方式确定缴费单明细账户金额归属问题（2012-01-09 重构）
			if (feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_POS_ZHI_HUI_YUAN_XIAO
					|| feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_WANG_YIN_YUAN_XIAO) {
				// feePaymentDetail.setAcademyAccount(Double.valueOf((new
				// BigDecimal(feePaymentDetail.getAmountPaied()).add(new
				// BigDecimal(feePaymentDetail.getRechargeAmount()))).toString()));
				feePaymentDetail.setBranchAccount(Double
						.valueOf((new BigDecimal(feePaymentDetail
								.getAmountPaied()).add(new BigDecimal(
								feePaymentDetail.getRechargeAmount())))
								.toString()));

				feePaymentDetail.setAcademyAccount(((new BigDecimal(
						feePaymentDetail.getAmountPaied()).add(new BigDecimal(
						feePaymentDetail.getRechargeAmount()))).add(
						new BigDecimal(feePaymentDetail.getDiscountAmount()))
						.subtract(
								new BigDecimal(feePaymentDetail
										.getAcademyDiscount()))
						.subtract(new BigDecimal(feePaymentDetail
								.getAcademyCeduDiscount()))).doubleValue());
				feePaymentDetail.setBranchAccount((new BigDecimal(
						feePaymentDetail.getBranchAccount())
						.subtract(new BigDecimal(feePaymentDetail
								.getAcademyAccount()))).doubleValue());
			} else {
				feePaymentDetail.setBranchAccount(Double
						.valueOf((new BigDecimal(feePaymentDetail
								.getAmountPaied()).add(new BigDecimal(
								feePaymentDetail.getRechargeAmount())))
								.toString()));
			}
			// if(isFee==1)
			// {
			// feePaymentDetail.setStatus(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);
			// }
			// else
			// {
			// feePaymentDetail.setStatus(Constants.PAYMENT_STATUS_YI_KAI_DAN);
			// }
			feePaymentDetail.setStatus(feePayment.getStatus());

			feePaymentDetail.setStudentId(feePayment.getStudentId());
			feePaymentDetail
					.setTypes(Constants.FEE_PAYMENT_TYPE_OFFICIALLY_SINGLE);
			feePaymentDetail.setUpdatedTime(DateUtil
					.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
			feePaymentDetail.setCreatorId(feePayment.getCreatorId());
			feePaymentDetail.setUpdaterId(feePayment.getUpdaterId());
			this.feePaymentDetailBiz.addFeePaymentDetail(feePaymentDetail);
			index++;

			// 修改待缴测试费
			shenyumoney = pendingFeePayment.getAmountSurplus().toString();
			pendingFeePayment.setAmountPaid(pendingFeePayment.getAmountPaid()
					.add((new BigDecimal(shijiaoMoney).add(new BigDecimal(
							feePaymentDetail.getDiscountAmount())))
							.add(new BigDecimal(feePaymentDetail
									.getRechargeAmount()))));
			pendingFeePayment.setAmountSurplus(pendingFeePayment
					.getAmountSurplus().subtract(
							(new BigDecimal(shijiaoMoney).add(new BigDecimal(
									feePaymentDetail.getDiscountAmount())))
									.add(new BigDecimal(feePaymentDetail
											.getRechargeAmount()))));
			if ((pendingFeePayment.getAmountSurplus().compareTo(new BigDecimal(
					0))) <= 0)// 和0比较大于返回1小于返回-1等于返回0
			{
				pendingFeePayment.setStatus(Constants.PENDING_STATUS_PAID);
			} else {
				pendingFeePayment
						.setStatus(Constants.PENDING_STATUS_UNFINISHEDPAID);
			}
			pendingFeePayment.setUpdatedTime(DateUtil
					.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
			pendingFeePayment.setUpdaterId(feePayment.getCreatorId());
			this.pendingFeePaymentBiz
					.updatePendingFeePayment(pendingFeePayment);

			// //修改优惠卷
			// String ids =feePaymentDetail.getDiscountPolicyDetailId();
			// if(ids!=null && !ids.equals("") && ids!="" && ids.length()>0)
			// {
			// String[] did=ids.split(",");
			// for(int i=0;i<did.length;i++)
			// {
			// DiscountApplication
			// discount=this.discountApplicationBiz.findDiscountApplicationById(Integer.valueOf(did[i]));
			// discount.setUsageFlag(Constants.POLICY_USING_STATUS_TRUE);
			// this.discountApplicationBiz.updateDiscountApplication(discount);
			// }
			// }

			// 学生账户 //学生账户明细
			if (((new BigDecimal(shijiaoMoney).add(new BigDecimal(
					feePaymentDetail.getDiscountAmount())))
					.compareTo(new BigDecimal(shenyumoney))) >= 0) {
				// studentAccountManagementBiz
				// studentAccountAmountManagementBiz
				// 学生账户
				StudentAccountManagement studentAccountManagement = this.studentAccountManagementBiz
						.updateStudentAccountManagementByStudentIdForFee(
								feePayment.getStudentId(),
								feePayment.getCreatorId());
				studentAccountManagement
						.setAccountBalance(studentAccountManagement
								.getAccountBalance()
								.add((new BigDecimal(shijiaoMoney)
										.add(new BigDecimal(feePaymentDetail
												.getDiscountAmount())))
										.subtract(new BigDecimal(shenyumoney))));
				this.studentAccountManagementBiz
						.updateStudentAccountManagementById(studentAccountManagement);

				// 学生账户明细
				StudentAccountAmountManagement studentAccountAmountManagement = new StudentAccountAmountManagement();
				studentAccountAmountManagement
						.setAccountId(studentAccountManagement.getId());
				studentAccountAmountManagement.setAccountMoney((new BigDecimal(
						shijiaoMoney).add(new BigDecimal(feePaymentDetail
						.getDiscountAmount()))).subtract(new BigDecimal(
						shenyumoney)));
				// studentAccountAmountManagement.setCode(code)
				studentAccountAmountManagement.setCreatedTime(DateUtil
						.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
				studentAccountAmountManagement.setCreatorId(feePayment
						.getCreatorId());
				studentAccountAmountManagement
						.setDeleteFlag(Constants.DELETE_FALSE);
				// studentAccountAmountManagement.setDescription(description)
				studentAccountAmountManagement.setFeePaymentId(feePaymentDetail
						.getId());
				studentAccountAmountManagement.setFeeSubject(feePaymentDetail
						.getFeeSubjectId());
				studentAccountAmountManagement
						.setTypes(Constants.STATUS_RECHARGE);
				this.studentAccountAmountManagementBiz
						.addStudentAccountAmountManagement(studentAccountAmountManagement);
			} else if (new BigDecimal(feePaymentDetail.getRechargeAmount())
					.compareTo(new BigDecimal(0)) != 0) {
				// 学生账户
				StudentAccountManagement studentAccountManagement = this.studentAccountManagementBiz
						.updateStudentAccountManagementByStudentIdForFee(
								feePayment.getStudentId(),
								feePayment.getCreatorId());
				studentAccountManagement
						.setAccountBalance(studentAccountManagement
								.getAccountBalance().subtract(
										new BigDecimal(feePaymentDetail
												.getRechargeAmount())));
				this.studentAccountManagementBiz
						.updateStudentAccountManagementById(studentAccountManagement);

				// 学生账户明细
				StudentAccountAmountManagement studentAccountAmountManagement = new StudentAccountAmountManagement();
				studentAccountAmountManagement
						.setAccountId(studentAccountManagement.getId());
				studentAccountAmountManagement.setAccountMoney(new BigDecimal(
						feePaymentDetail.getRechargeAmount()));
				// studentAccountAmountManagement.setCode(code)
				studentAccountAmountManagement.setCreatedTime(DateUtil
						.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
				studentAccountAmountManagement.setCreatorId(feePayment
						.getCreatorId());
				studentAccountAmountManagement
						.setDeleteFlag(Constants.DELETE_FALSE);
				// studentAccountAmountManagement.setDescription(description)
				studentAccountAmountManagement.setFeePaymentId(feePaymentDetail
						.getId());
				studentAccountAmountManagement.setFeeSubject(feePaymentDetail
						.getFeeSubjectId());
				studentAccountAmountManagement
						.setTypes(Constants.STATUS_CONSUMPTION);
				this.studentAccountAmountManagementBiz
						.addStudentAccountAmountManagement(studentAccountAmountManagement);
			}

		}

		return count;
	}

	/*
	 * 查询院校打款单的所有缴费单明细
	 * 
	 * @see net.cedu.biz.finance.PaymentBiz#findDetailByOrderCeduAcademyId(int)
	 */
	public List<FeePaymentDetail> findDetailByOrderCeduAcademyId(
			int orderCeduAcademyId) throws Exception {
		String hql = " and orderCeduAcademyId=" + Constants.PLACEHOLDER;
		// hql += " and status!="+Constants.PAYMENT_STATUS_ZUO_FEI;
		// hql += " and deleteFlag=" + Constants.DELETE_FALSE;

		List<FeePaymentDetail> list = feePaymentDetailDao.getByProperty(hql,
				new Object[] { orderCeduAcademyId });

		return list;
	}

	/*
	 * 添加历史缴费单
	 * 
	 * @see
	 * net.cedu.biz.finance.PaymentBiz#addHistoryFeePaymenAndDetails(java.util
	 * .List, net.cedu.entity.finance.FeePayment)
	 */
	public int addHistoryFeePaymenAndDetails(
			List<FeePaymentDetail> feePaymentDetailList, FeePayment feePayment,
			int isFee) throws Exception {
		int count = 0;// 返回值
		double allmoney = 0;// 该阶段缴纳的费用 为学生账户判断所用
		// 收据号的使用
		if (feePayment.getIsPrint() == 1 && feePayment.getBarCode() != null
				&& !feePayment.getBarCode().equals("")
				&& feePayment.getBarCode() != "") {
			if (this.receiptBiz.updateReceiptStatusByCode(feePayment
					.getBarCode()) != 1) {
				count = 1;
				return count;
			}
		} else {
			feePayment.setBarCode("");
		}
		
		double fpDisa = 0;// 总充值金额
		// 添加缴费单
		if (feePaymentDetailList != null && feePaymentDetailList.size() > 0) {
			for (FeePaymentDetail fpd : feePaymentDetailList) {
				allmoney = Double.valueOf((new BigDecimal(allmoney)
						.add(new BigDecimal(fpd.getMoneyToPay()))).toString());
				
				fpDisa=(new BigDecimal(fpDisa).add(new BigDecimal(fpd.getDiscountAmount()))).doubleValue();
			}
		}
		feePayment.setCommonBatch(this.feePaymentDao.getCommonBatch(feePayment
				.getStudentId()));
		feePayment.setFeePayment(allmoney);
		feePayment.setTotalAmount((new BigDecimal(allmoney).subtract(new BigDecimal(fpDisa))).doubleValue());
		feePayment.setDiscountAmount(fpDisa);
		this.feePaymentDao.save(feePayment);

		// 添加缴费单明细
		if (feePaymentDetailList != null && feePaymentDetailList.size() > 0) {
			for (FeePaymentDetail fpd : feePaymentDetailList) {
				fpd.setFeePaymentId(feePayment.getId());
//2012-03-29	// 通过缴费单方式确定缴费单明细账户金额归属问题（2012-01-09 重构）
//				if (feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_POS_ZHI_HUI_YUAN_XIAO
//						|| feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_WANG_YIN_YUAN_XIAO) {
//					// fpd.setAcademyAccount(Double.valueOf((new
//					// BigDecimal(fpd.getAmountPaied()).add(new
//					// BigDecimal(fpd.getRechargeAmount()))).toString()));
//					fpd.setBranchAccount(Double.valueOf((new BigDecimal(fpd
//							.getAmountPaied()).add(new BigDecimal(fpd
//							.getRechargeAmount()))).toString()));
//
//					fpd.setAcademyAccount(((new BigDecimal(fpd.getAmountPaied())
//							.add(new BigDecimal(fpd.getRechargeAmount()))).add(
//							new BigDecimal(fpd.getDiscountAmount())).subtract(
//							new BigDecimal(fpd.getAcademyDiscount()))
//							.subtract(new BigDecimal(fpd
//									.getAcademyCeduDiscount()))).doubleValue());
//					fpd.setBranchAccount((new BigDecimal(fpd.getBranchAccount())
//							.subtract(new BigDecimal(fpd.getAcademyAccount())))
//							.doubleValue());
//				} else {
					fpd.setBranchAccount(Double.valueOf((new BigDecimal(fpd
							.getAmountPaied()).add(new BigDecimal(fpd
							.getRechargeAmount()))).toString()));
//				}
				this.feePaymentDetailBiz.addFeePaymentDetail(fpd);

				// 修改学生缴学费状态
				if (fpd.getFeeSubjectId() == FeeSubjectEnum.TuitionFee.value()
						&& this.studentBiz.findStudentById(fpd.getStudentId()) != null) {
					Student student = this.studentBiz.findStudentById(fpd
							.getStudentId());
					if (student.getTuitionStatus() < Constants.STU_TUITION_STATUS_JIAO_FEI_WAN_CHENG
							&& isFee == 1) {
						student.setTuitionStatus(student.getTuitionStatus() + 1);
						this.studentBiz.updateStudentInfo(student);
					}
				}
			}
		}
		return count;
	}

	/*
	 * 缴其他所有费用(除去报名费和测试费(通用接口))--重构之后的方法 操纵6个表(缴费单、缴费单明细、优惠券、收据、学生账户、学生账户明细)
	 * 
	 * @see
	 * net.cedu.biz.finance.PaymentBiz#addSchoolPaymentRewrite(java.util.List,
	 * net.cedu.entity.finance.FeePayment, int)
	 */
	public int addSchoolPaymentRewrite(
			List<FeePaymentDetail> feePaymentDetailList, FeePayment feePayment,
			int isFee) throws Exception {
		int count = 0;// 返回值
		double zhangmianMoney = 0;// 账面金额
		// 收据号的使用
		if (feePayment.getIsPrint() == 1 && feePayment.getBarCode() != null
				&& !feePayment.getBarCode().equals("")
				&& feePayment.getBarCode() != "") {
			if (this.receiptBiz.updateReceiptStatusByCode(feePayment
					.getBarCode()) != 1) {
				count = 1;
				return count;
			}
		}

		// 查询实缴金额
		double allmoney = 0;
		double rechargeAmount = 0;// 充值金额
		double usedrecharge = 0;// 使用的充值金额
		double fpDisa=0;//总优惠金额
		for (FeePaymentDetail feedetail : feePaymentDetailList) {
			if (feedetail.getMoneyToPay() > feedetail.getRebateValue()) {
				allmoney = Double.valueOf(new BigDecimal(allmoney).add(
						new BigDecimal(feedetail.getRebateValue())).toString());
				rechargeAmount = (new BigDecimal(rechargeAmount)
						.add(new BigDecimal(feedetail.getMoneyToPay())
								.subtract(new BigDecimal(feedetail
										.getRebateValue())))).doubleValue();
			} else {
				allmoney = Double.valueOf(new BigDecimal(allmoney).add(
						new BigDecimal(feedetail.getMoneyToPay())).toString());
			}
			// 使用的充值总金额
			if (feedetail.getRechargeAmount() > 0) {
				usedrecharge = (new BigDecimal(usedrecharge)
						.add(new BigDecimal(feedetail.getRechargeAmount())))
						.doubleValue();
			}
			//优惠金额
			fpDisa=(new BigDecimal(fpDisa).add(new BigDecimal(feedetail.getDiscountAmount())).add(new BigDecimal(feedetail.getCeduDiscount()))).doubleValue();
		
		}
		// 添加缴费单
		// feePayment.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
		feePayment.setUpdatedTime(DateUtil
				.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
		feePayment.setDeleteFlag(Constants.DELETE_FALSE);
		feePayment.setFeePayment(allmoney);// 除去充值的总金额
		feePayment.setRechargeAmount((new BigDecimal(rechargeAmount)
				.subtract(new BigDecimal(usedrecharge))).doubleValue());// 充值金额(减去使用充值金额)
		feePayment.setTotalAmount((new BigDecimal(allmoney).add(new BigDecimal(
				rechargeAmount)).subtract(new BigDecimal(usedrecharge)).subtract(new BigDecimal(fpDisa)))
				.doubleValue());// 总金额(减去使用充值金额   减去优惠金额)
		feePayment.setDiscountAmount(fpDisa);
		feePayment.setPamentType(Constants.FEE_PAYMENT_TYPE_OFFICIALLY_SINGLE);
		feePayment.setCommonBatch(this.feePaymentDao.getCommonBatch(feePayment
				.getStudentId()));
		// feePayment.setPosSerialNo(posSerialNo) pos流水号
		if (isFee == 1) {
			// 缴费方式不同收费时的缴费单状态不同
			if (feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_DI_SAN_FAN_ZHI_FU) {
				feePayment
						.setStatus(Constants.PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU);// 缴费单状态(前期跟明细一样)
			} else if (feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_XIAN_JIN_HUI_ZONG_BU) {
				feePayment
						.setStatus(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);// 缴费单状态(前期跟明细一样)
			} else if (feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_XIAN_JIN_HUI_YUAN_XIAO) {
				feePayment
						.setStatus(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);// 缴费单状态(前期跟明细一样)
			} else if (feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_POS_ZHI_HUI_ZONG_BU
					|| feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_WANG_YIN_ZONG_BU) {
				feePayment
						.setStatus(Constants.PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU);// 缴费单状态(前期跟明细一样)
			} else if (feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_POS_ZHI_HUI_YUAN_XIAO
					|| feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_WANG_YIN_YUAN_XIAO) {
				feePayment
						.setStatus(Constants.PAYMENT_STATUS_YI_QUE_REN_DAI_HUI_KUAN);// 缴费单状态(前期跟明细一样)
			}
		} else {
			feePayment.setStatus(Constants.PAYMENT_STATUS_YI_KAI_DAN);
		}
		if (allmoney > 0)
		{
			this.feePaymentDao.save(feePayment);
		} else {
			// 单纯充值暂时作为预缴费处理
			feePayment.setPamentType(Constants.FEE_PAYMENT_TYPE_PRE_BILLING);
			feePayment.setStatus(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);//默认为收费确认
			this.feePaymentDao.save(feePayment);
		}

		// 添加缴费单明细
		int index = 1;
		for (FeePaymentDetail feePaymentDetail : feePaymentDetailList) {
			zhangmianMoney = feePaymentDetail.getMoneyToPay();
			// 输入优惠金额时必须加到总优惠金额里
			feePaymentDetail.setDiscountAmount(Double.valueOf(new BigDecimal(
					feePaymentDetail.getDiscountAmount()).add(
					new BigDecimal(feePaymentDetail.getCeduDiscount()))
					.toString()));
			// 未缴费的不计入缴费单
			if ((((new BigDecimal(feePaymentDetail.getAmountPaied())
					.add(new BigDecimal(feePaymentDetail.getDiscountAmount())))
					.add(new BigDecimal(feePaymentDetail.getRechargeAmount())))
					.compareTo(new BigDecimal(0))) <= 0) {
				continue;
			}

			// 修改优惠卷
			double surplusMoney = 0.0;// 优惠卷基数
			if (feePaymentDetail.getMoneyToPay() > feePaymentDetail
					.getRebateValue()) {
				surplusMoney = feePaymentDetail.getRebateValue();
			} else {
				surplusMoney = feePaymentDetail.getMoneyToPay();
			}
			double discAllMoney = 0.0;// 总优惠金额

			String ids = feePaymentDetail.getDiscountPolicyDetailId();// 优惠卷Id集合
			if (ids != null && !ids.equals("") && ids != "" && ids.length() > 0) {
				String[] did = ids.split(",");
				for (int i = 0; i < did.length; i++) {
					DiscountApplication discount = this.discountApplicationBiz
							.findDiscountApplicationById(Integer
									.valueOf(did[i]));
					if (allmoney > 0)// 单纯充值不需要修改优惠券
					{
						discount.setUsageFlag(Constants.POLICY_USING_STATUS_TRUE);
					}
					this.discountApplicationBiz
							.updateDiscountApplication(discount);

					// 给相应的缴费单明细附上优惠的值
					String nowdiscount = "0";// 当前优惠金额
					if (discount.getDiscountWay() == Constants.MONEY_FORM_RATIO) {
						if ((discount.getMoney().compareTo(new BigDecimal(0))) > 0) {
							nowdiscount = (new BigDecimal(surplusMoney)
									.multiply((discount.getMoney().divide(
											new BigDecimal("100"), 2))))
									.toString();
						}
					} else if (discount.getDiscountWay() == Constants.MONEY_FORM_AMOUNT) {
						nowdiscount = discount.getMoney().toString();
					}
					discAllMoney = Double.valueOf(new BigDecimal(discAllMoney)
							.add(new BigDecimal(nowdiscount)).toString());
					if (discAllMoney > surplusMoney) {
						nowdiscount = new BigDecimal(nowdiscount)
								.add(new BigDecimal(surplusMoney)
										.subtract(new BigDecimal(discAllMoney)))
								.toString();
						// 防止后续的出错
						discAllMoney = surplusMoney;
					}
					// 计算出优惠是属于哪种优惠
					StudentDiscountPolicy sdpolicy = studentDiscountPolicyBiz
							.findStudentDiscountPolicyById(discount
									.getPolicyStandardId());
					if (sdpolicy != null && sdpolicy.getTargetObjectId() != 0) {
						if (sdpolicy.getTargetObjectId() == Constants.POLICY_TARGET_OBJECT_CEDU) {
							feePaymentDetail.setCeduDiscount(Double
									.valueOf((new BigDecimal(feePaymentDetail
											.getCeduDiscount())
											.add(new BigDecimal(nowdiscount)))
											.toString()));
						} else if (sdpolicy.getTargetObjectId() == Constants.POLICY_TARGET_OBJECT_BRANCH) {
							feePaymentDetail.setBranchDiscount(Double
									.valueOf((new BigDecimal(feePaymentDetail
											.getBranchDiscount())
											.add(new BigDecimal(nowdiscount)))
											.toString()));
						} else if (sdpolicy.getTargetObjectId() == Constants.POLICY_TARGET_OBJECT_ACADEMY) {
							if (sdpolicy.getIsShared() == Constants.POLICY_IS_SHARED_FLASE) {
								feePaymentDetail
										.setAcademyDiscount(Double.valueOf((new BigDecimal(
												feePaymentDetail
														.getAcademyDiscount())
												.add(new BigDecimal(nowdiscount)))
												.toString()));
							} else if (sdpolicy.getIsShared() == Constants.POLICY_IS_SHARED_TRUE) {
								feePaymentDetail
										.setAcademyCeduDiscount(Double.valueOf((new BigDecimal(
												feePaymentDetail
														.getAcademyCeduDiscount())
												.add(new BigDecimal(nowdiscount)))
												.toString()));
							}
						} else {
							feePaymentDetail.setChannelDiscount(Double
									.valueOf((new BigDecimal(feePaymentDetail
											.getChannelDiscount())
											.add(new BigDecimal(nowdiscount)))
											.toString()));
						}
					}
				}
			}

			// 添加单个缴费单明细
			feePaymentDetail.setCode(index + "");
			feePaymentDetail.setCreatedTime(feePayment.getCreatedTime());
			// feePaymentDetail.setCreditPointPaied(creditPointPaied)实缴学分数
			// feePaymentDetail.setCreditPointToPay(creditPointToPay)应缴学分数
			// 实缴金额 多余的钱放入充值账户
			if (feePaymentDetail.getMoneyToPay() > feePaymentDetail
					.getRebateValue()) {
				feePaymentDetail.setAmountPaied(Double.valueOf(new BigDecimal(
						feePaymentDetail.getAmountPaied()).add(
						new BigDecimal(feePaymentDetail.getRebateValue())
								.subtract(new BigDecimal(feePaymentDetail
										.getMoneyToPay()))).toString()));
				feePaymentDetail.setMoneyToPay(feePaymentDetail
						.getRebateValue());
			}
			// feePaymentDetail.setBatchId(feeStandard.getFeeBatchId());
			feePaymentDetail.setDeleteFlag(Constants.DELETE_FALSE);
			feePaymentDetail.setFeePaymentId(feePayment.getId());
			feePaymentDetail
					.setIsFromChannel(Constants.FEE_PAYMENT_CHANNEL_REBATE_FALSE);
			feePaymentDetail
					.setIsInvoiced(Constants.FEE_PAYMENT_IS_INVOICED_FALSE);
			feePaymentDetail.setIsSupper(Constants.FEE_PAYMENT_IS_SUPPER_FALSE);

			// feePaymentDetail.setPolicyFeeDetailId(pendingFeePayment.getPolicyFeeDetailId());//收费政策Id
//2012-03-29// 通过缴费单方式确定缴费单明细账户金额归属问题（2012-01-09 重构）
//			if (feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_POS_ZHI_HUI_YUAN_XIAO
//					|| feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_WANG_YIN_YUAN_XIAO) {
//				// feePaymentDetail.setAcademyAccount(Double.valueOf((new
//				// BigDecimal(feePaymentDetail.getAmountPaied()).add(new
//				// BigDecimal(feePaymentDetail.getRechargeAmount()))).toString()));
//				feePaymentDetail.setBranchAccount(Double
//						.valueOf((new BigDecimal(feePaymentDetail
//								.getAmountPaied()).add(new BigDecimal(
//								feePaymentDetail.getRechargeAmount())))
//								.toString()));
//
//				feePaymentDetail.setAcademyAccount(((new BigDecimal(
//						feePaymentDetail.getAmountPaied()).add(new BigDecimal(
//						feePaymentDetail.getRechargeAmount()))).add(
//						new BigDecimal(feePaymentDetail.getDiscountAmount()))
//						.subtract(
//								new BigDecimal(feePaymentDetail
//										.getAcademyDiscount()))
//						.subtract(new BigDecimal(feePaymentDetail
//								.getAcademyCeduDiscount()))).doubleValue());
//				feePaymentDetail.setBranchAccount((new BigDecimal(
//						feePaymentDetail.getBranchAccount())
//						.subtract(new BigDecimal(feePaymentDetail
//								.getAcademyAccount()))).doubleValue());
//			} else {
				feePaymentDetail.setBranchAccount(Double
						.valueOf((new BigDecimal(feePaymentDetail
								.getAmountPaied()).add(new BigDecimal(
								feePaymentDetail.getRechargeAmount())))
								.toString()));
//			}
			feePaymentDetail.setStatus(feePayment.getStatus());

			feePaymentDetail.setStudentId(feePayment.getStudentId());
			feePaymentDetail
					.setTypes(Constants.FEE_PAYMENT_TYPE_OFFICIALLY_SINGLE);
			feePaymentDetail.setUpdatedTime(DateUtil
					.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
			feePaymentDetail.setCreatorId(feePayment.getCreatorId());
			feePaymentDetail.setUpdaterId(feePayment.getUpdaterId());
			if (feePaymentDetail.getRebateValue() > 0)// 单纯充值不需添加缴费单明细
			{
				this.feePaymentDetailBiz.addFeePaymentDetail(feePaymentDetail);
				index++;

				// 修改学生缴学费状态
				if (feePaymentDetail.getFeeSubjectId() == FeeSubjectEnum.TuitionFee
						.value()
						&& this.studentBiz.findStudentById(feePaymentDetail
								.getStudentId()) != null) {
					Student student = this.studentBiz
							.findStudentById(feePaymentDetail.getStudentId());
					if (student.getTuitionStatus() < Constants.STU_TUITION_STATUS_JIAO_FEI_WAN_CHENG
							&& isFee == 1) {
						student.setTuitionStatus(student.getTuitionStatus() + 1);
						this.studentBiz.updateStudentInfo(student);
					}
				}
			}

			// 学生账户 //学生账户明细
			if (zhangmianMoney > feePaymentDetail.getRebateValue()) {

				// 学生账户
				StudentAccountManagement studentAccountManagement = this.studentAccountManagementBiz
						.updateStudentAccountManagementByStudentIdForFee(
								feePayment.getStudentId(),
								feePayment.getCreatorId());
				studentAccountManagement
						.setAccountBalance(studentAccountManagement
								.getAccountBalance()
								.add(new BigDecimal(zhangmianMoney)
										.subtract(new BigDecimal(
												feePaymentDetail
														.getRebateValue()))));
				this.studentAccountManagementBiz
						.updateStudentAccountManagementById(studentAccountManagement);

				// 学生账户明细
				StudentAccountAmountManagement studentAccountAmountManagement = new StudentAccountAmountManagement();
				studentAccountAmountManagement
						.setAccountId(studentAccountManagement.getId());
				studentAccountAmountManagement.setAccountMoney(new BigDecimal(
						zhangmianMoney).subtract(new BigDecimal(
						feePaymentDetail.getRebateValue())));
				// studentAccountAmountManagement.setCode(code)
//				studentAccountAmountManagement.setCreatedTime(DateUtil
//						.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
				studentAccountAmountManagement.setCreatedTime(feePayment.getCreatedTime());
				studentAccountAmountManagement.setCreatorId(feePayment
						.getCreatorId());
				studentAccountAmountManagement
						.setDeleteFlag(Constants.DELETE_FALSE);
				// studentAccountAmountManagement.setDescription(description)
				if (feePaymentDetail.getRebateValue() > 0)// 单个充值
				{
					// studentAccountAmountManagement
					// .setFeePaymentId(feePaymentDetail.getId());
					studentAccountAmountManagement
							.setFeePaymentDetailId(feePaymentDetail.getId());
				}
				studentAccountAmountManagement.setFeePaymentId(feePayment
						.getId());
				studentAccountAmountManagement.setFeeSubject(feePaymentDetail
						.getFeeSubjectId());
				studentAccountAmountManagement
						.setTypes(Constants.STATUS_RECHARGE);
				this.studentAccountAmountManagementBiz
						.addStudentAccountAmountManagement(studentAccountAmountManagement);
			} else if (new BigDecimal(feePaymentDetail.getRechargeAmount())
					.compareTo(new BigDecimal(0)) != 0) {
				// 学生账户
				StudentAccountManagement studentAccountManagement = this.studentAccountManagementBiz
						.updateStudentAccountManagementByStudentIdForFee(
								feePayment.getStudentId(),
								feePayment.getCreatorId());
				studentAccountManagement
						.setAccountBalance(studentAccountManagement
								.getAccountBalance().subtract(
										new BigDecimal(feePaymentDetail
												.getRechargeAmount())));
				this.studentAccountManagementBiz
						.updateStudentAccountManagementById(studentAccountManagement);

				// 学生账户明细
				StudentAccountAmountManagement studentAccountAmountManagement = new StudentAccountAmountManagement();
				studentAccountAmountManagement
						.setAccountId(studentAccountManagement.getId());
				studentAccountAmountManagement.setAccountMoney(new BigDecimal(
						feePaymentDetail.getRechargeAmount()));
				// studentAccountAmountManagement.setCode(code)
				studentAccountAmountManagement.setCreatedTime(feePayment.getCreatedTime());
				studentAccountAmountManagement.setCreatorId(feePayment
						.getCreatorId());
				studentAccountAmountManagement
						.setDeleteFlag(Constants.DELETE_FALSE);
				// studentAccountAmountManagement.setDescription(description)
				studentAccountAmountManagement
						.setFeePaymentDetailId(feePaymentDetail.getId());
				studentAccountAmountManagement.setFeePaymentId(feePayment
						.getId());
				studentAccountAmountManagement.setFeeSubject(feePaymentDetail
						.getFeeSubjectId());
				studentAccountAmountManagement
						.setTypes(Constants.STATUS_CONSUMPTION);
				this.studentAccountAmountManagementBiz
						.addStudentAccountAmountManagement(studentAccountAmountManagement);
			}

		}

		return count;
	}

	/*
	 * 查询缴费单数量（缴费单查询用 是否打印要设置为-1）
	 * 
	 * @see
	 * net.cedu.biz.finance.PaymentBiz#findFeePaymentCountBySearch(net.cedu.
	 * entity.finance.FeePayment, net.cedu.entity.crm.Student, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public int findFeePaymentCountBySearch(FeePayment feePayment,
			Student student, String feemoney, String starttime, String endtime)
			throws Exception {
		List list2 = null;
		String hqlConditionExpression2 = "";
		PageParame p2 = new PageParame();
		list2 = new ArrayList();

		if (feePayment != null) {
			// 缴费方式
			if (feePayment.getFeeWayId() != 0) {
				hqlConditionExpression2 += " and feeWayId ="
						+ Constants.PLACEHOLDER;
				list2.add(feePayment.getFeeWayId());
			}
			// 缴费单类别
			if (feePayment.getPamentType() != 0) {
				hqlConditionExpression2 += " and pamentType ="
						+ Constants.PLACEHOLDER;
				list2.add(feePayment.getPamentType());
			}
			// 缴费单状态
			if (feePayment.getStatus() != 0) {
				hqlConditionExpression2 += " and status ="
						+ Constants.PLACEHOLDER;
				list2.add(feePayment.getStatus());
			}
			// 是否打印
			if (feePayment.getIsPrint() != -1) {
				hqlConditionExpression2 += " and isPrint ="
						+ Constants.PLACEHOLDER;
				list2.add(feePayment.getIsPrint());
			}
			// 缴费单收据号
			if (feePayment.getBarCode() != null
					&& !feePayment.getBarCode().equals("")) {
				hqlConditionExpression2 += " and barCode like "
						+ Constants.PLACEHOLDER;
				list2.add("%" + feePayment.getBarCode() + "%");
			}
			// 缴费单号
			if (feePayment.getCode() != null
					&& !feePayment.getCode().equals("")) {
				hqlConditionExpression2 += " and code like "
						+ Constants.PLACEHOLDER;
				list2.add("%" + feePayment.getCode() + "%");
			}
			// 缴费金额
			if (feemoney != null && !feemoney.equals("")) {
				hqlConditionExpression2 += " and feePayment = "
						+ Constants.PLACEHOLDER;
				list2.add(Double.valueOf(feemoney + ""));
			}
			// 缴费日期起
			if (starttime != null && !starttime.equals("")) {
				hqlConditionExpression2 += " and  createdTime >= "
						+ Constants.PLACEHOLDER;
				list2.add(starttime);
			}
			// 缴费日期止
			if (endtime != null && !endtime.equals("")) {
				hqlConditionExpression2 += " and  createdTime <= "
						+ Constants.PLACEHOLDER;
				list2.add(endtime);
			}
		}

//		if (checkStudentInfo(student)) {
//			// 学生ID
//			String studentIds = findStudentIds(student);
//			if (studentIds != null) {
//				hqlConditionExpression2 += " and studentId in ("
//						+ Constants.PLACEHOLDER + ")";
//				list2.add("$" + studentIds);
//			}
//		}
		//缴费单(如果没有查询条件则跳过)
		String stuHql = findStudentHql(student);
		if(stuHql!=null && !stuHql.equals("select id from Student where 1=1 "))
		{
			hqlConditionExpression2 += " and studentId in ("
				+stuHql+ ")";
		}
		else if(stuHql==null)
		{
			return 0;
		}

		if (!hqlConditionExpression2.equals("")) {
			p2.setHqlConditionExpression(hqlConditionExpression2);
			p2.setValues(list2.toArray());
		}

		return feePaymentDao.getCounts(p2);
	}

	/*
	 * 查询缴费单集合（缴费单查询用 是否打印要设置为-1）
	 * 
	 * @see
	 * net.cedu.biz.finance.PaymentBiz#findFeePaymentListBySearch(net.cedu.entity
	 * .finance.FeePayment, net.cedu.entity.crm.Student, java.lang.String,
	 * java.lang.String, java.lang.String, net.cedu.model.page.PageResult)
	 */
	public List<FeePayment> findFeePaymentListBySearch(FeePayment feePayment,
			Student student, String feemoney, String starttime, String endtime,
			PageResult<FeePayment> pr) throws Exception {

		List<FeePayment> feePayments = new ArrayList<FeePayment>();
		List list2 = null;
		String hqlConditionExpression2 = "";
		PageParame p2 = new PageParame(pr);
		list2 = new ArrayList();
		if (feePayment != null) {

			// 缴费方式
			if (feePayment.getFeeWayId() != 0) {
				hqlConditionExpression2 += " and feeWayId ="
						+ Constants.PLACEHOLDER;
				list2.add(feePayment.getFeeWayId());
			}
			// 缴费单类别
			if (feePayment.getPamentType() != 0) {
				hqlConditionExpression2 += " and pamentType ="
						+ Constants.PLACEHOLDER;
				list2.add(feePayment.getPamentType());
			}
			// 缴费单状态
			if (feePayment.getStatus() != 0) {
				hqlConditionExpression2 += " and status ="
						+ Constants.PLACEHOLDER;
				list2.add(feePayment.getStatus());
			}
			// 是否打印
			if (feePayment.getIsPrint() != -1) {
				hqlConditionExpression2 += " and isPrint ="
						+ Constants.PLACEHOLDER;
				list2.add(feePayment.getIsPrint());
			}
			// 缴费单号
			if (feePayment.getCode() != null
					&& !feePayment.getCode().equals("")) {
				hqlConditionExpression2 += " and code like "
						+ Constants.PLACEHOLDER;
				list2.add("%" + feePayment.getCode() + "%");
			}
			// 缴费单收据号
			if (feePayment.getBarCode() != null
					&& !feePayment.getBarCode().equals("")) {
				hqlConditionExpression2 += " and barCode like "
						+ Constants.PLACEHOLDER;
				list2.add("%" + feePayment.getBarCode() + "%");
			}
			// 缴费金额
			if (feemoney != null && !feemoney.equals("")) {
				hqlConditionExpression2 += " and feePayment = "
						+ Constants.PLACEHOLDER;
				list2.add(Double.valueOf(feemoney + ""));
			}
			// 缴费日期起
			if (starttime != null && !starttime.equals("")) {
				hqlConditionExpression2 += " and  createdTime >= "
						+ Constants.PLACEHOLDER;
				list2.add(starttime);
			}
			// 缴费日期止
			if (endtime != null && !endtime.equals("")) {
				hqlConditionExpression2 += " and  createdTime <= "
						+ Constants.PLACEHOLDER;
				list2.add(endtime);
			}
		}

//		if (checkStudentInfo(student)) {
//			// 学生ID
//			String studentIds = findStudentIds(student);
//			if (studentIds != null) {
//				hqlConditionExpression2 += " and studentId in ("
//						+ Constants.PLACEHOLDER + ")";
//				list2.add("$" + studentIds);
//			}
//		}
		//缴费单(如果没有查询条件则跳过)
		String stuHql = findStudentHql(student);
		if(stuHql!=null && !stuHql.equals("select id from Student where 1=1 "))
		{
			hqlConditionExpression2 += " and studentId in ("
				+stuHql+ ")";
		}
		else if(stuHql==null)
		{
			return null;
		}
		
		if (!hqlConditionExpression2.equals("")) {
			p2.setHqlConditionExpression(hqlConditionExpression2);
			p2.setValues(list2.toArray());
		}
		// Ids集合
		Long[] idsLongs = feePaymentDao.getIDs(p2);
		for (int i = 0; i < idsLongs.length; i++) {
			FeePayment f = feePaymentDao.findById(Integer.parseInt(idsLongs[i]
					.toString()));
			if (f != null) {
				Student d = studentBiz.findStudentById(f.getStudentId());
				if (d != null) {
					f.setStudent(d);
					// 姓名
					f.setStudentName(d.getName());
					Academy academy = academyBiz.findAcademyById(d
							.getAcademyId());

					if (academy != null) {
						f.setSchoolName(academy.getName());
					}

					AcademyEnrollBatch academyenrollbatch = academyenrollbatchBiz
							.findAcademyEnrollBatchById(d
									.getEnrollmentBatchId());
					if (academyenrollbatch != null) {
						f.setAcademyenrollbatchName(academyenrollbatch
								.getEnrollmentName());
					}

					Level level = levelbiz.findLevelById(d.getLevelId());
					if (level != null) {
						f.setLevelName(level.getName());
					}

					Major major = majorbiz.findMajorById(d.getMajorId());
					if (major != null)
						f.setMajorName(major.getName());
				}

				feePayments.add(f);
			}
		}
		return feePayments;
	}

	/*
	 * (重载方法)查询缴费单数量（缴费单查询用 是否打印要设置为-1）
	 * 
	 * @see
	 * net.cedu.biz.finance.PaymentBiz#findFeePaymentCountBySearch(net.cedu.
	 * entity.finance.FeePayment, net.cedu.entity.crm.Student, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public int findFeePaymentCountBySearch(FeePayment feePayment,
			Student student, String feemoney, String starttime, String endtime,
			String globalids) throws Exception {
		List list2 = null;
		String hqlConditionExpression2 = "";
		PageParame p2 = new PageParame();
		list2 = new ArrayList();

		if (feePayment != null) {
			// 缴费方式
			if (feePayment.getFeeWayId() != 0) {
				hqlConditionExpression2 += " and feeWayId ="
						+ Constants.PLACEHOLDER;
				list2.add(feePayment.getFeeWayId());
			}
			// 缴费单类别
			if (feePayment.getPamentType() != 0) {
				hqlConditionExpression2 += " and pamentType ="
						+ Constants.PLACEHOLDER;
				list2.add(feePayment.getPamentType());
			}
			// 缴费单状态
			if (feePayment.getStatus() != 0) {
				hqlConditionExpression2 += " and status ="
						+ Constants.PLACEHOLDER;
				list2.add(feePayment.getStatus());
			}
			// 是否打印
			if (feePayment.getIsPrint() != -1) {
				hqlConditionExpression2 += " and isPrint ="
						+ Constants.PLACEHOLDER;
				list2.add(feePayment.getIsPrint());
			}
			// 缴费单号
			if (feePayment.getCode() != null
					&& !feePayment.getCode().equals("")) {
				hqlConditionExpression2 += " and code like "
						+ Constants.PLACEHOLDER;
				list2.add("%" + feePayment.getCode() + "%");
			}
			// 缴费金额
			if (feemoney != null && !feemoney.equals("")) {
				hqlConditionExpression2 += " and feePayment = "
						+ Constants.PLACEHOLDER;
				list2.add(Double.valueOf(feemoney + ""));
			}
			// 缴费日期起
			if (starttime != null && !starttime.equals("")) {
				hqlConditionExpression2 += " and  createdTime >= "
						+ Constants.PLACEHOLDER;
				list2.add(starttime);
			}
			// 缴费日期止
			if (endtime != null && !endtime.equals("")) {
				hqlConditionExpression2 += " and  createdTime <= "
						+ Constants.PLACEHOLDER;
				list2.add(endtime);
			}
			// 全局批次
			if (globalids != null && !globalids.equals("")) {
				Object[] ids = globalids.split(",");
				hqlConditionExpression2 += " and ( ";
				for (int i = 0; i < ids.length; i++) {
					if (i != 0) {
						hqlConditionExpression2 += " or ";
					}
					hqlConditionExpression2 += " commonBatch = "
							+ Constants.PLACEHOLDER;
					list2.add(ids[i].toString());
				}
				hqlConditionExpression2 += " ) ";
			}
		}

		if (checkStudentInfo(student)) {
			// 学生ID
			String studentIds = findStudentIds(student);
			if (studentIds != null) {
				hqlConditionExpression2 += " and studentId in ("
						+ Constants.PLACEHOLDER + ")";
				list2.add("$" + studentIds);
			}
		}

		if (!hqlConditionExpression2.equals("")) {
			p2.setHqlConditionExpression(hqlConditionExpression2);
			p2.setValues(list2.toArray());
		}

		return feePaymentDao.getCounts(p2);
	}

	/*
	 * (重载方法)查询缴费单集合（缴费单查询用 是否打印要设置为-1）
	 * 
	 * @see
	 * net.cedu.biz.finance.PaymentBiz#findFeePaymentListBySearch(net.cedu.entity
	 * .finance.FeePayment, net.cedu.entity.crm.Student, java.lang.String,
	 * java.lang.String, java.lang.String, net.cedu.model.page.PageResult)
	 */
	public List<FeePayment> findFeePaymentListBySearch(FeePayment feePayment,
			Student student, String feemoney, String starttime, String endtime,
			String globalids, PageResult<FeePayment> pr) throws Exception {

		List<FeePayment> feePayments = new ArrayList<FeePayment>();
		List list2 = null;
		String hqlConditionExpression2 = "";
		PageParame p2 = new PageParame(pr);
		list2 = new ArrayList();
		if (feePayment != null) {

			// 缴费方式
			if (feePayment.getFeeWayId() != 0) {
				hqlConditionExpression2 += " and feeWayId ="
						+ Constants.PLACEHOLDER;
				list2.add(feePayment.getFeeWayId());
			}
			// 缴费单类别
			if (feePayment.getPamentType() != 0) {
				hqlConditionExpression2 += " and pamentType ="
						+ Constants.PLACEHOLDER;
				list2.add(feePayment.getPamentType());
			}
			// 缴费单状态
			if (feePayment.getStatus() != 0) {
				hqlConditionExpression2 += " and status ="
						+ Constants.PLACEHOLDER;
				list2.add(feePayment.getStatus());
			}
			// 是否打印
			if (feePayment.getIsPrint() != -1) {
				hqlConditionExpression2 += " and isPrint ="
						+ Constants.PLACEHOLDER;
				list2.add(feePayment.getIsPrint());
			}
			// 缴费单号
			if (feePayment.getCode() != null
					&& !feePayment.getCode().equals("")) {
				hqlConditionExpression2 += " and code like "
						+ Constants.PLACEHOLDER;
				list2.add("%" + feePayment.getCode() + "%");
			}
			// 缴费金额
			if (feemoney != null && !feemoney.equals("")) {
				hqlConditionExpression2 += " and feePayment = "
						+ Constants.PLACEHOLDER;
				list2.add(Double.valueOf(feemoney + ""));
			}
			// 缴费日期起
			if (starttime != null && !starttime.equals("")) {
				hqlConditionExpression2 += " and  createdTime >= "
						+ Constants.PLACEHOLDER;
				list2.add(starttime);
			}
			// 缴费日期止
			if (endtime != null && !endtime.equals("")) {
				hqlConditionExpression2 += " and  createdTime <= "
						+ Constants.PLACEHOLDER;
				list2.add(endtime);
			}
			// 全局批次
			if (globalids != null && !globalids.equals("")) {
				Object[] ids = globalids.split(",");
				hqlConditionExpression2 += " and ( ";
				for (int i = 0; i < ids.length; i++) {
					if (i != 0) {
						hqlConditionExpression2 += " or ";
					}
					hqlConditionExpression2 += " commonBatch = "
							+ Constants.PLACEHOLDER;
					list2.add(ids[i].toString());
				}
				hqlConditionExpression2 += " ) ";
			}
		}

		if (checkStudentInfo(student)) {
			// 学生ID
			String studentIds = findStudentIds(student);
			if (studentIds != null) {
				hqlConditionExpression2 += " and studentId in ("
						+ Constants.PLACEHOLDER + ")";
				list2.add("$" + studentIds);
			}
		}
		if (!hqlConditionExpression2.equals("")) {
			p2.setHqlConditionExpression(hqlConditionExpression2);
			p2.setValues(list2.toArray());
		}
		// Ids集合
		Long[] idsLongs = feePaymentDao.getIDs(p2);
		for (int i = 0; i < idsLongs.length; i++) {
			FeePayment f = feePaymentDao.findById(Integer.parseInt(idsLongs[i]
					.toString()));
			if (f != null) {
				Student d = studentBiz.findStudentById(f.getStudentId());
				if (d != null) {
					f.setStudent(d);
					// 姓名
					f.setStudentName(d.getName());
					Academy academy = academyBiz.findAcademyById(d
							.getAcademyId());

					if (academy != null) {
						f.setSchoolName(academy.getName());
					}

					AcademyEnrollBatch academyenrollbatch = academyenrollbatchBiz
							.findAcademyEnrollBatchById(d
									.getEnrollmentBatchId());
					if (academyenrollbatch != null) {
						f.setAcademyenrollbatchName(academyenrollbatch
								.getEnrollmentName());
					}

					Level level = levelbiz.findLevelById(d.getLevelId());
					if (level != null) {
						f.setLevelName(level.getName());
					}

					Major major = majorbiz.findMajorById(d.getMajorId());
					if (major != null)
						f.setMajorName(major.getName());
				}

				feePayments.add(f);
			}
		}
		return feePayments;
	}

	/*
	 * 缴费单作废(2012-01-16 重构)（更新缴费单状态，以及缴费单明细状态，及其所有关联缴费单的表都还原） 0代表成功 1代表不满足退费情况
	 * 2代表报名费和测试费的缴费单不是开单情况不能作废 3代表退费失败
	 * 
	 * @see
	 * net.cedu.biz.finance.PaymentBiz#updateFeePaymentInvalidStatusRewrite(int)
	 */
	public int updateFeePaymentInvalidStatusRewrite(int fpId) throws Exception {
		int count = 0;
		// 缴费单实体
		FeePayment feePayment = feePaymentDao.findById(fpId);
		if (feePayment != null) {
			// 缴费单明细集合
			List<FeePaymentDetail> feePaymentDetailList = this.feePaymentDetailBiz
					.findFeePaymentDetailListByFeePaymentId(feePayment.getId());

			// 更新其他表 主要是优惠卷、待缴费单、学生账户、学生账户明细
			if (feePaymentDetailList != null && feePaymentDetailList.size() > 0) {
				if (feePayment.getStatus() == Constants.PAYMENT_STATUS_YI_KAI_DAN) {
					for (FeePaymentDetail fd : feePaymentDetailList) {
						// 修改优惠卷
						String yhids = fd.getDiscountPolicyDetailId();
						if (yhids != null && !yhids.equals("") && yhids != ""
								&& yhids.length() > 0) {
							String[] did = yhids.split(",");
							for (int i = 0; i < did.length; i++) {
								DiscountApplication discount = this.discountApplicationBiz
										.findDiscountApplicationById(Integer
												.valueOf(did[i]));
								if (discount != null) {
									discount.setUsageFlag(Constants.POLICY_USING_STATUS_FALSE);
									this.discountApplicationBiz
											.updateDiscountApplication(discount);
								}
							}
						}
						// 报名费和测试费才需要修改待缴费单
						if (fd.getFeeSubjectId() == FeeSubjectEnum.RegistrationFee
								.value()
								|| fd.getFeeSubjectId() == FeeSubjectEnum.TestFee
										.value()) {
							if (fd.getPendingPaymentId() > 0) {
								this.updatePendingFeePaymentById(fd
										.getPendingPaymentId());
							}
						}
						// 修改缴费单明细状态
						fd.setStatus(Constants.PAYMENT_STATUS_ZUO_FEI);
						this.feePaymentDetailBiz.updateFeePaymentDetail(fd);
					}
					// 修改充值账户
					this.updateStudentAccountAmountByFeePaymentId(
							feePayment.getId(), feePayment.getStudentId());
					// 修改缴费单
					feePayment.setStatus(Constants.PAYMENT_STATUS_ZUO_FEI);
					this.feePaymentBiz.updateFeePayment(feePayment);
					// 作废收据
					if (feePayment.getIsPrint() == 1
							&& feePayment.getBarCode() != null
							&& !feePayment.getBarCode().equals("")) {
						this.receiptBiz.updateReceiptByCode(feePayment
								.getBarCode());
					}
				} else if (feePayment.getStatus() > Constants.PAYMENT_STATUS_YI_KAI_DAN) {
					// 排除不能作废的情况
					for (FeePaymentDetail fd : feePaymentDetailList) {
						// 报名费和测试费的缴费单不是开单情况不能作废
						// if(fd.getFeeSubjectId()==FeeSubjectEnum.RegistrationFee.value()
						// ||
						// fd.getFeeSubjectId()==FeeSubjectEnum.TestFee.value())
						// {
						// count=2;//报名费和测试费的缴费单不是开单情况不能作废
						// return count;
						// }
						List<FeePaymentDetail> rfdlist = this.feePaymentBiz
								.findRefundFpdListByFeePaymentDetailId(fd
										.getId());
						if (rfdlist != null && rfdlist.size() > 0) {
							count = 1;// 有退费单的情况不能退费
							return count;
						}
						if (feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_WANG_YIN_ZONG_BU
								|| feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_DI_SAN_FAN_ZHI_FU
								|| feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_POS_ZHI_HUI_ZONG_BU) {
							if (fd.getStatus() != Constants.PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU) {
								count = 1;// 缴费单明细状态不一致不能退费
								return count;
							}
						} else if (feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_XIAN_JIN_HUI_ZONG_BU
								|| feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_XIAN_JIN_HUI_YUAN_XIAO) {
							if (fd.getStatus() != Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN) {
								count = 1;// 缴费单明细状态不一致不能退费
								return count;
							}
						} else if (feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_POS_ZHI_HUI_YUAN_XIAO
								|| feePayment.getFeeWayId() == Constants.PAYMENT_METHOD_WANG_YIN_YUAN_XIAO) {
							if (fd.getStatus() != Constants.PAYMENT_STATUS_YI_QUE_REN_DAI_HUI_KUAN) {
								count = 1;// 缴费单明细状态不一致不能退费
								return count;
							}
						}
					}
					// 确认可以作废时的情况
					for (FeePaymentDetail feedetail : feePaymentDetailList) {
						// 修改优惠卷
						String yhids = feedetail.getDiscountPolicyDetailId();
						if (yhids != null && !yhids.equals("") && yhids != ""
								&& yhids.length() > 0) {
							String[] did = yhids.split(",");
							for (int i = 0; i < did.length; i++) {
								DiscountApplication discount = this.discountApplicationBiz
										.findDiscountApplicationById(Integer
												.valueOf(did[i]));
								if (discount != null) {
									discount.setUsageFlag(Constants.POLICY_USING_STATUS_FALSE);
									this.discountApplicationBiz
											.updateDiscountApplication(discount);
								}
							}
						}

						// 修改缴费单明细状态
						feedetail.setStatus(Constants.PAYMENT_STATUS_ZUO_FEI);
						this.feePaymentDetailBiz
								.updateFeePaymentDetail(feedetail);

						// 修改学生缴学费状态
						if (feedetail.getFeeSubjectId() == FeeSubjectEnum.TuitionFee.value()) {
							Student student = this.studentBiz
									.findStudentById(feedetail.getStudentId());
							if (student!=null && student.getTuitionStatus() > Constants.STU_TUITION_STATUS_WEI_JIAO_XUE_FEI) {
								student.setTuitionStatus(student.getTuitionStatus() - 1);
								this.studentBiz.updateStudentInfo(student);
							}
						}
					}
					// 修改充值账户
					this.updateStudentAccountAmountByFeePaymentId(
							feePayment.getId(), feePayment.getStudentId());
					// 修改缴费单
					feePayment.setStatus(Constants.PAYMENT_STATUS_ZUO_FEI);
					this.feePaymentBiz.updateFeePayment(feePayment);
					// 作废收据
					if (feePayment.getIsPrint() == 1
							&& feePayment.getBarCode() != null
							&& !feePayment.getBarCode().equals("")) {
						this.receiptBiz.updateReceiptByCode(feePayment
								.getBarCode());
					}
				}
			} else {
				count = 3;// 作废失败
			}
		} else {
			count = 3;// 作废失败
		}
		return count;

	}

	/**
	 * 缴费单作废~~~~~修改学生充值账户
	 * 
	 * @param feePaymentId
	 *            缴费单Id
	 * @param studentId
	 *            学生Id
	 * @throws Exception
	 */
	private void updateStudentAccountAmountByFeePaymentId(int feePaymentId,
			int studentId) throws Exception {
		BigDecimal xcmoney = new BigDecimal(0);// （消费-充值）后的金额
		List<StudentAccountAmountManagement> saamlist = this.studentAccountAmountManagementBiz
				.findStudentAccountAmountManagementListByFeePaymentIdTypes(
						feePaymentId, Constants.STATUS_RECHARGE);
		if (saamlist != null && saamlist.size() > 0) {
			for (StudentAccountAmountManagement saam : saamlist) {
				if (saam.getTypes() == Constants.STATUS_RECHARGE) {
					xcmoney = xcmoney.subtract(saam.getAccountMoney());
					saam.setTypes(Constants.STATUS_RECHARGE_REFUND);
				} else if (saam.getTypes() == Constants.STATUS_CONSUMPTION) {
					xcmoney = xcmoney.add(saam.getAccountMoney());
					saam.setTypes(Constants.STATUS_CONSUMPTION_REFUND);
				} else {
					saam.setTypes(Constants.STATUS_RECHARGE_REFUND);
				}
				// 修改学生账户明细
				// saam.setTypes(Constants.STATUS_RECHARGE_CONSUMPTION_REFUND);
				this.studentAccountAmountManagementBiz.updateStudentAccountAmountManagement(saam);
			}
			// 修改学生账户
			StudentAccountManagement sam11 = this.studentAccountManagementBiz.findStudentAccountManagementByStudentId(studentId);
			if (sam11 != null) {
				sam11.setAccountBalance(sam11.getAccountBalance().add(xcmoney));
				this.studentAccountManagementBiz.updateStudentAccountManagementById(sam11);
			}
		}
	}

	/**
	 * 缴费单作废~~~~~修改待缴费单
	 * 
	 * @param pfpId
	 *            待缴费单Id
	 * @throws Exception
	 */
	private void updatePendingFeePaymentById(int pfpId) throws Exception {

		PendingFeePayment pend = this.pendingFeePaymentBiz
				.findPendingFeePaymentById(pfpId);
		if (pend != null && pend.getStatus() > Constants.PENDING_STATUS_UNPAID) {
			pend.setStatus(Constants.PENDING_STATUS_UNPAID);
			pend.setAmountSurplus(pend.getAmountPaid().add(
					pend.getAmountSurplus()));
			pend.setAmountPaid(new BigDecimal(0));
			this.pendingFeePaymentBiz.updatePendingFeePayment(pend);

		}
	}
	
	/*
	 * 预缴费单作废
	 * 
	 * @see net.cedu.biz.finance.PaymentBiz#updateFeePaymentInvalidStatusRewrite(int)
	 */
	public int updateFeePaymentInvalidStatusRewriteYuJiaoFei(int fpId) throws Exception 
	{
		int count=0;
		// 缴费单实体
		FeePayment feePayment = feePaymentDao.findById(fpId);
		if (feePayment != null) 
		{
			if(testStuAccountAmountByFeePaymentId(feePayment.getId(),feePayment.getStudentId()))
			{
				// 修改充值账户
				this.updateStudentAccountAmountByFeePaymentId(
						feePayment.getId(), feePayment.getStudentId());
				// 修改缴费单
				feePayment.setStatus(Constants.PAYMENT_STATUS_ZUO_FEI);
				this.feePaymentBiz.updateFeePayment(feePayment);
				// 作废收据
				if (feePayment.getIsPrint() == 1
						&& feePayment.getBarCode() != null
						&& !feePayment.getBarCode().equals("")) {
					this.receiptBiz.updateReceiptByCode(feePayment
							.getBarCode());
				}
			}
			else
			{
				count=4;
			}
		}
		else
		{
			count=3;
		}
		return count;
	}
	
	/**
	 * 判断预缴费单作废时充值账户是否有足够的钱去作废
	 * @param feePaymentId 缴费单Id
	 * @param studentId 学生Id
	 * @return
	 * @throws Exception
	 */
	private boolean testStuAccountAmountByFeePaymentId(int feePaymentId,
			int studentId) throws Exception {
		boolean isback=true;
		BigDecimal xcmoney = new BigDecimal(0);// （充值）金额
		List<StudentAccountAmountManagement> saamlist = this.studentAccountAmountManagementBiz
				.findStudentAccountAmountManagementListByFeePaymentIdTypes(
						feePaymentId, Constants.STATUS_RECHARGE);
		if (saamlist != null && saamlist.size() > 0) {
			for (StudentAccountAmountManagement saam : saamlist)
			{
				if (saam.getTypes() == Constants.STATUS_RECHARGE)
				{
					xcmoney=studentAccountAmountManagementBiz.findStudentAccountFeesubjectBalanceByStudentIdFeeSubjectId(studentId,saam.getFeeSubject());
					if(xcmoney.subtract(saam.getAccountMoney()).compareTo(new BigDecimal(0))<0)
					{
						isback=false;
						break;
					}
				}
			}
			
		}
		return isback;
	}
	
	/*
	 * 释放收据号
	 * 
	 * @see net.cedu.biz.finance.PaymentBiz#updateFpdBarCode(net.cedu.entity.finance.FeePayment)
	 */
	public boolean updateFpdBarCode(FeePayment feePayment) throws Exception 
	{
		boolean isback=false;
		if (feePayment != null && feePayment.getStatus()==Constants.PAYMENT_STATUS_ZUO_FEI) 
		{		
			isback=this.receiptBiz.updateReceiptStatusByCodeForShiFang(feePayment.getBarCode());
			if(isback)
			{
				feePayment.setBarCode("");
				feePayment.setIsPrint(0);
				isback=this.feePaymentBiz.updateFeePayment(feePayment);		
			}
		}
		return isback;
	}
}
