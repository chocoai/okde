package net.cedu.biz.finance.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.InvoiceManagementBiz;
import net.cedu.biz.finance.PostalParcelBiz;
import net.cedu.common.Constants;
import net.cedu.common.string.StringUtil;
import net.cedu.dao.finance.InvoiceManagementDao;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.Major;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.InvoiceManagement;
import net.cedu.entity.finance.PostalParcel;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 发票管理 业务逻辑实现
 * 
 * @author gaole
 * 
 */

@Service
public class InvoiceManagementBizImpl implements InvoiceManagementBiz {

	@Autowired
	private InvoiceManagementDao invoiceManagementDao; // 发票接口
	@Autowired
	private AcademyEnrollBatchBiz academyenrollbatchBiz;// 院校招生批次 Biz
	@Autowired
	private LevelBiz levelbiz; // 层次 业务Biz
	@Autowired
	private MajorBiz majorbiz; // 专业 业务Biz
	@Autowired
	private StudentBiz studentbiz; // 学生 业务Biz
	@Autowired
	private FeePaymentDetailBiz feepaymentdetailbiz; // 缴费单明细 业务Biz
	@Autowired
	private PostalParcelBiz postalparcelbiz; // 缴费单明细 业务Biz
	@Autowired
	private FeePaymentBiz feepaymentbiz; // 缴费单明细 业务Biz
	@Autowired
	private AcademyBiz academyBiz; // 院校 业务Biz
	@Autowired
	private BranchBiz branchBiz; // 学习中心 业务Biz

	/*
	 * 按照ID查发票
	 * 
	 * @see
	 * net.cedu.biz.finance.InvoiceManagementBiz#findInvoiceManagementById(int)
	 */
	public InvoiceManagement findInvoiceManagementById(int id) throws Exception {
		return invoiceManagementDao.findById(id);
	}

	/*
	 * 发票登记
	 * 
	 * @see
	 * net.cedu.biz.finance.InvoiceManagementBiz#addInvoiceManagement(net.cedu
	 * .entity.finance.InvoiceManagement)
	 */
	public boolean addInvoiceManagement(InvoiceManagement invoicemanagement)
			throws Exception {
		invoiceManagementDao.save(invoicemanagement);
		return true;
	}

	/*
	 * 查询发票按学习中心
	 * 
	 * @see
	 * net.cedu.biz.finance.InvoiceManagementBiz#findInvoiceManagementByBranchId
	 * (int)
	 */
	public List<InvoiceManagement> findInvoiceManagementByBranchId(
			int branchId, int isPost, PageResult<InvoiceManagement> pr)
			throws Exception {
		List<InvoiceManagement> invoicemanagements = null;
		// Ids集合
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();

		if (branchId != 0) {
			hqlparam += " and  branchId= " + Constants.PLACEHOLDER;
			list.add(branchId);
		}
		if (isPost != -1) {
			hqlparam += " and  isPost= " + Constants.PLACEHOLDER;
			list.add(isPost);
		}
		hqlparam += " and deleteFlag=";
		list.add(Constants.DELETE_FALSE);
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		Long[] invoiceManagementIds = invoiceManagementDao.getIDs(p);

		if (invoiceManagementIds != null && invoiceManagementIds.length != 0) {
			invoicemanagements = new ArrayList<InvoiceManagement>();
			for (int i = 0; i < invoiceManagementIds.length; i++) {
				InvoiceManagement im = this.findInvoiceManagementById(Integer
						.valueOf(invoiceManagementIds[i].toString()));
				InvoiceManagement invoicemanagement = im;
				Student student = studentbiz.findStudentById(invoicemanagement
						.getStudentId());
				AcademyEnrollBatch academyenrollbatch = new AcademyEnrollBatch();
				Level level = new Level();
				Major major = new Major();
				Academy academy = new Academy();
				Branch branch = new Branch();
				if (student != null) {
					invoicemanagement.setStudentName(student.getName());
					academyenrollbatch = academyenrollbatchBiz
							.findAcademyEnrollBatchById(student
									.getEnrollmentBatchId());
					level = levelbiz.findLevelById(student.getLevelId());
					major = majorbiz.findMajorById(student.getMajorId());
					academy = academyBiz.findAcademyById(student.getAcademyId());
					branch = branchBiz.findBranchById(student.getBranchId());
				}
				if (academyenrollbatch != null) {
					invoicemanagement
							.setAcademyenrollbatchName(academyenrollbatch
									.getEnrollmentName());
				}
				if (level != null) {
					invoicemanagement.setLevelName(level.getName());
				}
				if (major != null) {
					invoicemanagement.setMajorName(major.getName());
				}
				if (academy != null) {
					invoicemanagement.setAcademyName(academy.getName());
				}
				if (branch != null) {
					invoicemanagement.setBranchName(branch.getName());
				}

				Object[] obj = StringUtil.strToObject(invoicemanagement
						.getFeePaymentDetailId());
				double money = 0.00d;
				// 循环缴费单ID
				for (int j = 0; j < obj.length; j++) {
					FeePaymentDetail feepaymentdetail = feepaymentdetailbiz
							.findFeePaymentDetailByFeePaymentId(Integer
									.valueOf(obj[j].toString()));
					money += feepaymentdetail.getAmountPaied();
					// System.out.println(feepaymentdetail.getAmountPaied());
				}
				// 获得总金额
				invoicemanagement.setAmountPaied(money);
				invoicemanagements.add(invoicemanagement);
			}
		}
		return invoicemanagements;
	}

	/*
	 * 查询发票按学习中心(分页数量)
	 * 
	 * @see
	 * net.cedu.biz.finance.InvoiceManagementBiz#countInvoiceManagementByBranchId
	 * (int)
	 */
	public int countInvoiceManagementByBranchId(int branchId, int isPost,
			PageResult<InvoiceManagement> pr) throws Exception {

		// Ids集合
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();

		if (branchId != 0) {
			hqlparam += " and  branchId= " + Constants.PLACEHOLDER;
			list.add(branchId);
		}
		if (isPost != -1) {
			hqlparam += " and  isPost= " + Constants.PLACEHOLDER;
			list.add(isPost);
		}
		hqlparam += " and deleteFlag=";
		list.add(Constants.DELETE_FALSE);

		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		return invoiceManagementDao.getCounts(p);
	}

	/*
	 * 查询邮寄单所有发票
	 * 
	 * @see
	 * net.cedu.biz.finance.InvoiceManagementBiz#findInvoiceManagementByInvoiceIds
	 * (java.lang.String, net.cedu.model.page.PageResult)
	 */
	public List<InvoiceManagement> findInvoiceManagementByInvoiceIds(
			String invoiceIds, PageResult<InvoiceManagement> pr)
			throws Exception {
		List<InvoiceManagement> invoicemanagements = null;
//		PageParame p = new PageParame(pr);
		Object[] ids = StringUtil.strToObject(invoiceIds);

		if (ids != null && ids.length > 0) {
			invoicemanagements = new ArrayList<InvoiceManagement>();
			for (int i = 0; i < ids.length; i++) {
				InvoiceManagement im = this.findInvoiceManagementById(Integer
						.valueOf(ids[i].toString()));
				InvoiceManagement invoicemanagement = im;
				Student student = studentbiz.findStudentById(invoicemanagement
						.getStudentId());
				AcademyEnrollBatch academyenrollbatch = new AcademyEnrollBatch();
				Level level = new Level();
				Major major = new Major();
				if (student != null) {
					invoicemanagement.setStudentName(student.getName());
					academyenrollbatch = academyenrollbatchBiz
							.findAcademyEnrollBatchById(student
									.getEnrollmentBatchId());
					level = levelbiz.findLevelById(student.getLevelId());
					major = majorbiz.findMajorById(student.getMajorId());
				}
				if (academyenrollbatch != null) {
					invoicemanagement
							.setAcademyenrollbatchName(academyenrollbatch
									.getEnrollmentName());
				}
				if (level != null) {
					invoicemanagement.setLevelName(level.getName());
				}
				if (major != null) {
					invoicemanagement.setMajorName(major.getName());
				}
				Object[] obj = StringUtil.strToObject(invoicemanagement
						.getFeePaymentDetailId());
				double money = 0;
				// 循环缴费单ID
				for (int j = 0; j < obj.length; j++) {
					FeePaymentDetail feepaymentdetail = feepaymentdetailbiz
							.findFeePaymentDetailByFeePaymentId(Integer
									.valueOf(obj[j].toString()));
					money += feepaymentdetail.getAmountPaied();
				}
				// 获得总金额
				invoicemanagement.setAmountPaied(money);
				invoicemanagements.add(invoicemanagement);
			}
		}
		return invoicemanagements;
	}

	/*
	 * 修改发票
	 * 
	 * @see
	 * net.cedu.biz.finance.InvoiceManagementBiz#updateInvoiceManagement(net
	 * .cedu.entity.finance.InvoiceManagement)
	 */
	public boolean updateInvoiceManagement(InvoiceManagement invoicemanagement)
			throws Exception {
		invoiceManagementDao.update(invoicemanagement);

		return true;
	}

	/*
	 * 查询发票按学习中心(分页数量)
	 * 
	 * @see
	 * net.cedu.biz.finance.InvoiceManagementBiz#countInvoiceManagementByParams
	 * (java.lang.String, java.lang.String, java.lang.String,
	 * net.cedu.model.page.PageResult)
	 */
	public int countInvoiceManagementByParams(int branchId, String studentIds,
			String postalNo, String invoiceNo, String feePaymentNo, int isSign,
			PageResult<InvoiceManagement> pr) throws Exception {
		PageParame p = new PageParame(pr);

		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		// 发票号
		if (invoiceNo != null && !invoiceNo.equals("")) {
			hqlparam += " and invoiceCode like " + Constants.PLACEHOLDER;
			list.add("%" + invoiceNo + "%");
		}
		//
		if (postalNo != null && !postalNo.equals("")) {
			String ids = ",";
			// 获取邮寄包下的发票Ids
			List<PostalParcel> postalParcelList = postalparcelbiz
					.findPostalParcelsByCode(postalNo);
			if (postalParcelList != null && postalParcelList.size() != 0) {
				for (PostalParcel postalParcel : postalParcelList) {
					if (postalParcel != null) {

						String[] array = postalParcel.getInvoiceIds()
								.split("_");
						for (String string : array) {
							if (string != null) {
								if (ids.equals(",")) {
									ids = string;
								} else {
									ids += "," + string;
								}
							}
						}
					}
				}
			}
			if (ids.equals(",")) {
				ids = "0";
			}
			hqlparam += " and id in(" + Constants.PLACEHOLDER + ")";
			list.add("$" + ids);
		} else {
			// 不存在未签领的发票
			hqlparam += " and id in (" + Constants.PLACEHOLDER + ")";
			list.add("$"
					+ postalparcelbiz
							.findPostalParcelInvoiceIdsByBranchId(branchId));
		}
		//交费单号
		if (feePaymentNo != null && !feePaymentNo.equals("")) {
			hqlparam += " and feePaymentCode like" + Constants.PLACEHOLDER;
			list.add("%" + feePaymentNo + "%");
		}

		//学生IDs
		if (studentIds != null) {
			hqlparam += " and studentId in (" + Constants.PLACEHOLDER + ")";
			list.add("$" + studentIds);
		}
		//学习中心
		hqlparam += " and branchId=" + Constants.PLACEHOLDER;
		list.add(branchId);
		//是否签领
		hqlparam += " and isSign=" + Constants.PLACEHOLDER;
		list.add(isSign);
		//删除标记
		hqlparam += " and deleteFlag=" + Constants.PLACEHOLDER;
		list.add(Constants.DELETE_FALSE);

		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		return invoiceManagementDao.getCounts(p);
	}

	/*
	 * (查询发票按学习中心(分页集合)
	 * 
	 * @see
	 * net.cedu.biz.finance.InvoiceManagementBiz#findInvoiceManagementByParams
	 * (java.lang.String, java.lang.String, java.lang.String,
	 * net.cedu.model.page.PageResult)
	 */
	public List<InvoiceManagement> findInvoiceManagementByParams(int branchId,
			String studentIds, String postalNo, String invoiceNo,
			String feePaymentNo, int isSign, PageResult<InvoiceManagement> pr)
			throws Exception {

		// 按学习中心获取所有的未签领的邮包的发票单号

		List<InvoiceManagement> invoicemanagements = null;
		PageParame p = new PageParame(pr);

		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		// 发票号
		if (invoiceNo != null && !invoiceNo.equals("")) {
			hqlparam += " and invoiceCode like " + Constants.PLACEHOLDER;
			list.add("%" + invoiceNo + "%");
		}
		//邮寄号
		if (postalNo != null && !postalNo.equals("")) {
			String ids = ",";
			// 获取邮寄包下的发票Ids
			List<PostalParcel> postalParcelList = postalparcelbiz
					.findPostalParcelsByCode(postalNo);
			if (postalParcelList != null && postalParcelList.size() != 0) {
				for (PostalParcel postalParcel : postalParcelList) {
					if (postalParcel != null) {

						String[] array = postalParcel.getInvoiceIds()
								.split("_");
						for (String string : array) {
							if (string != null) {
								if (ids.equals(",")) {
									ids = string;
								} else {
									ids += "," + string;
								}
							}
						}
					}
				}
			}
			if (ids.equals(",")) {
				ids = "0";
			}
			hqlparam += " and id in(" + Constants.PLACEHOLDER + ")";
			list.add("$" + ids);
		} else {
			// 不存在已签领的发票
			hqlparam += " and id in (" + Constants.PLACEHOLDER + ")";
			list.add("$"
					+ postalparcelbiz
							.findPostalParcelInvoiceIdsByBranchId(branchId));
		}
		//交费单号
		if (feePaymentNo != null && !feePaymentNo.equals("")) {
			hqlparam += " and feePaymentCode like" + Constants.PLACEHOLDER;
			list.add("%" + feePaymentNo + "%");
		}

		//学生IDs
		if (studentIds != null) {
			hqlparam += " and studentId in (" + Constants.PLACEHOLDER + ")";
			list.add("$" + studentIds);
		}
		//学习中心
		hqlparam += " and branchId=" + Constants.PLACEHOLDER;
		list.add(branchId);
		//是否签领
		hqlparam += " and isSign=" + Constants.PLACEHOLDER;
		list.add(isSign);
		//删除标记
		hqlparam += " and deleteFlag=" + Constants.PLACEHOLDER;
		list.add(Constants.DELETE_FALSE);

		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		Long[] invoiceManagementIds = invoiceManagementDao.getIDs(p);

		if (invoiceManagementIds != null && invoiceManagementIds.length > 0) {
			invoicemanagements = new ArrayList<InvoiceManagement>();
			for (int i = 0; i < invoiceManagementIds.length; i++) {
				InvoiceManagement im = this.findInvoiceManagementById(Integer
						.valueOf(invoiceManagementIds[i].toString()));
				InvoiceManagement invoicemanagement = im;
				Student student = studentbiz.findStudentById(invoicemanagement
						.getStudentId());
				AcademyEnrollBatch academyenrollbatch = new AcademyEnrollBatch();
				Level level = new Level();
				Major major = new Major();
				if (student != null) {
					invoicemanagement.setStudentName(student.getName());
					academyenrollbatch = academyenrollbatchBiz
							.findAcademyEnrollBatchById(student
									.getEnrollmentBatchId());
					level = levelbiz.findLevelById(student.getLevelId());
					major = majorbiz.findMajorById(student.getMajorId());
				}
				if (academyenrollbatch != null) {
					invoicemanagement
							.setAcademyenrollbatchName(academyenrollbatch
									.getEnrollmentName());
				}
				if (level != null) {
					invoicemanagement.setLevelName(level.getName());
				}
				if (major != null) {
					invoicemanagement.setMajorName(major.getName());
				}
				Object[] obj = StringUtil.strToObject(invoicemanagement
						.getFeePaymentDetailId());
				double money = 0;
				// 循环缴费单ID
				for (int j = 0; j < obj.length; j++) {
					FeePaymentDetail feepaymentdetail = feepaymentdetailbiz
							.findFeePaymentDetailByFeePaymentId(Integer
									.valueOf(obj[j].toString()));
					money += feepaymentdetail.getAmountPaied();
				}
				// 获得总金额
				invoicemanagement.setAmountPaied(money);
				invoicemanagements.add(invoicemanagement);
			}
		}
		return invoicemanagements;
	}

	/*
	 * 按照发票号查询
	 * 
	 * @see
	 * net.cedu.biz.finance.InvoiceManagementBiz#findInvoiceManagementByInvoiceCode
	 * (java.lang.String)
	 */
	public InvoiceManagement findInvoiceManagementByInvoiceCode(
			String invoiceCode) throws Exception {
		
		return invoiceManagementDao.getObjByProperty(
				" and invoiceCode=" + Constants.PLACEHOLDER
						+ " and deleteFlag=" + Constants.PLACEHOLDER,
				invoiceCode, Constants.DELETE_FALSE);
	}

	/*
	 * 查询发票按学生Id
	 * 
	 * @see
	 * net.cedu.biz.finance.InvoiceManagementBiz#findInvoiceManagementByStudentId
	 * (int)
	 */
	public List<InvoiceManagement> findInvoiceManagementByStudentId(
			int studentId) throws Exception {

		return invoiceManagementDao.getByProperty(" and studentId="
				+ Constants.PLACEHOLDER, new Object[] { studentId });
	}

}
