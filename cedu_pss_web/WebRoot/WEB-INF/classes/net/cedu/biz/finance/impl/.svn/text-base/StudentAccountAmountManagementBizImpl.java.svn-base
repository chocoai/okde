package net.cedu.biz.finance.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.StudentAccountAmountManagementBiz;
import net.cedu.biz.finance.StudentAccountManagementBiz;
import net.cedu.common.Constants;
import net.cedu.dao.crm.StudentDao;
import net.cedu.dao.finance.FeePaymentDao;
import net.cedu.dao.finance.FeePaymentDetailDao;
import net.cedu.dao.finance.StudentAccountAmountManagementDao;
import net.cedu.dao.finance.StudentAccountManagementDao;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.Major;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.StudentAccountAmountManagement;
import net.cedu.entity.finance.StudentAccountManagement;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *学生账户金额变动管理 业务逻辑实现
 * 
 * @author gaole
 *
 */

@Service
public class StudentAccountAmountManagementBizImpl implements StudentAccountAmountManagementBiz {
	
	@Autowired
	private StudentAccountManagementDao stuamDao;  //学生账户_数据层访问接口
	
	@Autowired
	private StudentAccountAmountManagementDao studentaccountamountmanagementdao;  //学生账户金额变动接口
	
	@Autowired
	private StudentAccountManagementBiz studentaccountmanagementbiz;  //学生账户Biz
	
	@Autowired
	private FeeSubjectBiz fsbiz;  //费用科目Biz
	
	@Autowired
	private StudentDao studentDao;//学生_数据层接口
	
	@Autowired
	private StudentBiz studentBiz; //学生_业务层接口
	@Autowired
	private AcademyBiz academyBiz;// 院校_业务接口
	@Autowired
	private AcademyEnrollBatchBiz academyenrollbatchBiz;// 院校招生批次 _业务接口
	@Autowired
	private LevelBiz levelbiz; // 层次_业务接口
	@Autowired
	private MajorBiz majorbiz; // 专业_业务接口
	@Autowired
	private BranchBiz branchBiz; // 学习中心_业务接口
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;//费用科目业务层接口
	
	@Autowired
	private FeePaymentBiz feePaymentBiz;//缴费单_业务层接口
	
	@Autowired
	private FeePaymentDao feePaymentDao;//缴费单_数据访问层
	
	@Autowired
	private FeePaymentDetailDao feePaymentDetailDao;//缴费单明细_数据访问层

	
	/*
	 * 学生账户充值(消费)
	 * @see net.cedu.biz.finance.StudentAccountAmountManagementBiz#addStudentAccountAmountManagement(net.cedu.entity.finance.StudentAccountAmountManagement)
	 */
	public boolean addStudentAccountAmountManagement(
			StudentAccountAmountManagement saam) throws Exception {
		studentaccountamountmanagementdao.save(saam);
		return true;
	}

	/*
	 * 学生账户充值(消费)
	 * 
	 * @see net.cedu.biz.finance.StudentAccountAmountManagementBiz#addBatchStuAccAmManag(net.cedu.entity.finance.StudentAccountAmountManagement, net.cedu.entity.finance.StudentAccountManagement, net.cedu.entity.finance.FeePayment)
	 */
	public boolean addBatchStuAccAmManag(
			StudentAccountAmountManagement saam,StudentAccountManagement sam,FeePayment feePayment) throws Exception {
		boolean isback=false;
		if(saam!=null && sam!=null && feePayment!=null)
		{
			feePayment.setCommonBatch(this.feePaymentDao.getCommonBatch(feePayment.getStudentId()));
			isback=this.feePaymentBiz.addFeePayment(feePayment);
			saam.setFeePaymentId(feePayment.getId());
			studentaccountamountmanagementdao.save(saam);
			studentaccountmanagementbiz.updateStudentAccountManagementById(sam);
			
		}
		return isback;
	}
	
	/*
	 * 根据账户Id查询学生账户金额变动
	 * @see net.cedu.biz.finance.StudentAccountAmountManagementBiz#findStudentAccountAmountManagementById(int)
	 */
	public StudentAccountAmountManagement findStudentAccountAmountManagementById(
			int id) throws Exception {
		return studentaccountamountmanagementdao.findById(id);
	}

	
	/*
	 * 学生账户金额变动记录(数量)
	 * @see net.cedu.biz.finance.StudentAccountAmountManagementBiz#countStudentAccountAmountManagementByAccountId(int, int, net.cedu.model.page.PageResult)
	 */
	public int countStudentAccountAmountManagementByAccountId(int accountId,
			int types, PageResult<StudentAccountAmountManagement> pr)
			throws Exception {
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if(accountId!=0)
		{
			hqlparam+=" and  accountId="+Constants.PLACEHOLDER;
			list.add(accountId);
		}
		if(types>-1)
		{
			hqlparam+=" and types="+Constants.PLACEHOLDER;
			list.add(types);
		}	
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		return studentaccountamountmanagementdao.getCounts(p);
	}
	/*
	 * 学生账户金额变动记录(集合)
	 * @see net.cedu.biz.finance.StudentAccountAmountManagementBiz#findStudentAccountAmountManagementByAccountId(int, int, net.cedu.model.page.PageResult)
	 */
	public List<StudentAccountAmountManagement> findStudentAccountAmountManagementByAccountId(
			int accountId, int types,
			PageResult<StudentAccountAmountManagement> pr) throws Exception {
		List<StudentAccountAmountManagement> studentaccountamountmanagementlst=null;
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if(accountId!=0)
		{
			hqlparam+=" and  accountId="+Constants.PLACEHOLDER;
			list.add(accountId);
		}
		if(types>Constants.PAYMENT_STATUS_ZUO_FEI)
		{
			hqlparam+=" and types="+Constants.PLACEHOLDER;
			list.add(types);
		}	
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		
		Long[] studentAccountAmountManagementIds=studentaccountamountmanagementdao.getIDs(p);

		if(studentAccountAmountManagementIds!=null && studentAccountAmountManagementIds.length>0)
		{
			studentaccountamountmanagementlst=new ArrayList<StudentAccountAmountManagement>();
			for(int i=0;i<studentAccountAmountManagementIds.length;i++)
			{
				StudentAccountAmountManagement saam=this.findStudentAccountAmountManagementById(Integer.valueOf(studentAccountAmountManagementIds[i].toString()));
				FeeSubject fs= fsbiz.findFeeSubjectById(saam.getFeeSubject());
				if(fs!=null)
				{
					saam.setFeeSubjectName(fs.getName());
				}
				
				studentaccountamountmanagementlst.add(saam);
			}
		}

		return studentaccountamountmanagementlst;
	}

	
	/*
	 * 查询学生单个费用余额（2012.01.09重构   (申请退费中的不扣除)）
	 * @see net.cedu.biz.finance.StudentAccountAmountManagementBiz#findStudentAccountAmountManagementBySidAndFeeSubject(int, int)
	 */
	public BigDecimal findStudentAccountAmountManagementBySidAndFeeSubject(
			int studentId, int feeSubjectId) throws Exception {
		List<StudentAccountAmountManagement> studentaccountamountmanagementlst=null;
		BigDecimal adds=new BigDecimal(0);
		BigDecimal yue=new BigDecimal(0);
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if(studentId!=0)
		{
			int accountId=0;
			StudentAccountManagement sam=studentaccountmanagementbiz.findStudentAccountManagementByStudentId(studentId);
			if(sam!=null)
			{
				accountId=sam.getId();
			}
			hqlparam+=" and  accountId="+Constants.PLACEHOLDER;
			list.add(accountId);
		}
		if(feeSubjectId!=0)
		{
			hqlparam+=" and  feeSubject="+Constants.PLACEHOLDER;
			list.add(feeSubjectId);
		}
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		
		Long[] saamIds=studentaccountamountmanagementdao.getIDs(p);
		
		if(saamIds!=null && saamIds.length>0)
		{
			studentaccountamountmanagementlst=new ArrayList<StudentAccountAmountManagement>();
			
			for(int i=0;i<saamIds.length;i++)
			{
				StudentAccountAmountManagement saam=this.findStudentAccountAmountManagementById(Integer.valueOf(saamIds[i].toString()));
				
				
				if(saam.getTypes()==Constants.STATUS_RECHARGE)
				{
					adds=adds.add(saam.getAccountMoney());
				}
				else if(saam.getTypes()==Constants.STATUS_CONSUMPTION || saam.getTypes()==Constants.STATUS_APPLY_CONSUMPTION_TRUE)
				{
					yue=yue.add(saam.getAccountMoney());
				}
				studentaccountamountmanagementlst.add(saam);
			}
		}
		
		
		
		
		
		return adds.subtract(yue);
	}
	
	/*
	 * 根据缴费单明细查询学生账户明细具体消费、充值记录
	 * @see net.cedu.biz.finance.StudentAccountAmountManagementBiz#findStudentAccountAmountManagementByFeePaymentDetailId(int)
	 */
	public List<StudentAccountAmountManagement> findStudentAccountAmountManagementByFeePaymentDetailId(int feePaymentDetailId) throws Exception
	{
		List<StudentAccountAmountManagement> stulist=null;
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if(feePaymentDetailId!=0)
		{
			hqlparam+=" and  fee_payment_id="+Constants.PLACEHOLDER;
			list.add(feePaymentDetailId);
		}

		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		
		Long[] studentAccountAmountManagementIds=studentaccountamountmanagementdao.getIDs(p);

		if(studentAccountAmountManagementIds!=null && studentAccountAmountManagementIds.length>0)
		{
			stulist=new ArrayList<StudentAccountAmountManagement>();
			for(int i=0;i<studentAccountAmountManagementIds.length;i++)
			{
				StudentAccountAmountManagement saam=this.findStudentAccountAmountManagementById(Integer.valueOf(studentAccountAmountManagementIds[i].toString()));
				
				stulist.add(saam);
			}
		}

		return stulist;
	}

	
	/*
	 * 查询学生单个费用余额（2012.01.09重构   (申请退费中的扣除)）
	 * @see net.cedu.biz.finance.StudentAccountAmountManagementBiz#findStudentAccountFeesubjectBalanceByStudentIdFeeSubjectId(int, int)
	 */
	public BigDecimal findStudentAccountFeesubjectBalanceByStudentIdFeeSubjectId(
			int studentId, int feeSubjectId) throws Exception {
		List<StudentAccountAmountManagement> studentaccountamountmanagementlst=null;
		BigDecimal adds=new BigDecimal(0);
		BigDecimal yue=new BigDecimal(0);
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if(studentId!=0)
		{
			int accountId=0;
			StudentAccountManagement sam=studentaccountmanagementbiz.findStudentAccountManagementByStudentId(studentId);
			if(sam!=null)
			{
				accountId=sam.getId();
			}
			hqlparam+=" and  accountId="+Constants.PLACEHOLDER;
			list.add(accountId);
		}
		if(feeSubjectId!=0)
		{
			hqlparam+=" and  feeSubject="+Constants.PLACEHOLDER;
			list.add(feeSubjectId);
		}
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		
		Long[] saamIds=studentaccountamountmanagementdao.getIDs(p);
		
		if(saamIds!=null && saamIds.length>0)
		{
			studentaccountamountmanagementlst=new ArrayList<StudentAccountAmountManagement>();
			
			for(int i=0;i<saamIds.length;i++)
			{
				StudentAccountAmountManagement saam=this.findStudentAccountAmountManagementById(Integer.valueOf(saamIds[i].toString()));
				
				
				if(saam.getTypes()==Constants.STATUS_RECHARGE)
				{
					adds=adds.add(saam.getAccountMoney());
				}
				else if(saam.getTypes()==Constants.STATUS_CONSUMPTION || saam.getTypes()==Constants.STATUS_APPLY_CONSUMPTION_TRUE || saam.getTypes()==Constants.STATUS_APPLY_CONSUMPTION)
				{
					yue=yue.add(saam.getAccountMoney());
				}
				studentaccountamountmanagementlst.add(saam);
			}
		}
		
		
		
		
		
		return adds.subtract(yue);
	}
	
	/*
	 * 根据缴费单Id和学生账户明细查询学生账户退费情况(退费时用到、不能用于其它地方 2012.01.09)
	 * @see net.cedu.biz.finance.StudentAccountAmountManagementBiz#findStudentAccountAmountManagementListByFeePaymentIdTypes(int,int)
	 */
	public List<StudentAccountAmountManagement> findStudentAccountAmountManagementListByFeePaymentIdTypes(int feePaymentId,int types) throws Exception
	{
		List<StudentAccountAmountManagement> stulist=null;
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if(feePaymentId!=0)
		{
			hqlparam+=" and  feePaymentId="+Constants.PLACEHOLDER;
			list.add(feePaymentId);
		}
		if(types!=-1)
		{
			hqlparam+=" and  types >= "+Constants.PLACEHOLDER;
			list.add(types);
		}
		
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		
		Long[] studentAccountAmountManagementIds=studentaccountamountmanagementdao.getIDs(p);

		if(studentAccountAmountManagementIds!=null && studentAccountAmountManagementIds.length>0)
		{
			stulist=new ArrayList<StudentAccountAmountManagement>();
			for(int i=0;i<studentAccountAmountManagementIds.length;i++)
			{
				StudentAccountAmountManagement saam=this.findStudentAccountAmountManagementById(Integer.valueOf(studentAccountAmountManagementIds[i].toString()));
				//费用科目
				if(saam.getFeeSubject()!=0 && this.fsbiz.findFeeSubjectById(saam.getFeeSubject())!=null)
				{
					saam.setFeeSubjectName(this.fsbiz.findFeeSubjectById(saam.getFeeSubject()).getName());
				}
				else
				{
					saam.setFeeSubjectName("");
				}
				stulist.add(saam);
			}
		}

		return stulist;
	}
	
	/*
	 * 根据缴费单Id学生账户缴费时的充值情况(查看缴费单时用到、不能用于其它地方 2012.01.17)
	 * 
	 * @see net.cedu.biz.finance.StudentAccountAmountManagementBiz#findStudentAccountAmountManagementListByFeePaymentIdForViewFeePayment(int)
	 */
	public List<StudentAccountAmountManagement> findStudentAccountAmountManagementListByFeePaymentIdForViewFeePayment(int feePaymentId) throws Exception
	{
		List<StudentAccountAmountManagement> stulist=null;
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if(feePaymentId!=0)
		{
			hqlparam+=" and  feePaymentId="+Constants.PLACEHOLDER;
			list.add(feePaymentId);
		}
		
		hqlparam+=" and ( types = "+Constants.PLACEHOLDER +" or types="+Constants.PLACEHOLDER +" ) ";
		list.add(Constants.STATUS_RECHARGE);
		list.add(Constants.STATUS_RECHARGE_REFUND);
		
		
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		
		Long[] studentAccountAmountManagementIds=studentaccountamountmanagementdao.getIDs(p);

		if(studentAccountAmountManagementIds!=null && studentAccountAmountManagementIds.length>0)
		{
			stulist=new ArrayList<StudentAccountAmountManagement>();
			for(int i=0;i<studentAccountAmountManagementIds.length;i++)
			{
				StudentAccountAmountManagement saam=this.findStudentAccountAmountManagementById(Integer.valueOf(studentAccountAmountManagementIds[i].toString()));
				//费用科目
				if(saam.getFeeSubject()!=0 && this.fsbiz.findFeeSubjectById(saam.getFeeSubject())!=null)
				{
					saam.setFeeSubjectName(this.fsbiz.findFeeSubjectById(saam.getFeeSubject()).getName());
				}
				else
				{
					saam.setFeeSubjectName("");
				}
				stulist.add(saam);
			}
		}

		return stulist;
	}

	/*
	 * 修改学生账户明细
	 * 
	 * @see net.cedu.biz.finance.StudentAccountAmountManagementBiz#updateStudentAccountAmountManagement(net.cedu.entity.finance.StudentAccountAmountManagement)
	 */
	public boolean updateStudentAccountAmountManagement(StudentAccountAmountManagement saam) throws Exception
	{
		try
		{
			if (saam != null) 
			{
				Object object = this.studentaccountamountmanagementdao.update(saam);
				if (object != null) 
				{
					return true;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	
	/**
	 * 检查学生的查询条件是否为空    为空则不查询学生ID （为空的时候  学生为全部in学生id太多）
	 * @param student
	 * @return
	 */
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
			// 全局批次
			if (student.getGlbtach() != 0)
			{
				hqlConditionExpression += " and  enrollmentBatchId= "
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
			// 基础专业
			if (student.getGlmajor() != 0) {
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
	
	/**
	 * 查询学生ID集合
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	private String findStudentIds(Student student) throws Exception 
	{
		// 查询学生集合ID
		PageParame p = new PageParame();
		List list = null;
		String hqlConditionExpression = "";
		if (student != null)
		{
			list = new ArrayList();
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
			
			// 院校
			if (student.getAcademyId() != 0) {
				hqlConditionExpression += "and academyId="
						+ Constants.PLACEHOLDER;
				list.add(student.getAcademyId());
			}
			// 全局批次
			if (student.getGlbtach() != 0)
			{
				// 全局批次和院校批次可以固定一个招生批次，这样减轻数据库负荷
				if (student.getAcademyId() != 0) 
				{
					AcademyEnrollBatch aeb = academyenrollbatchBiz
							.findNonFinishedAcademyBatchByGlobalBatchIdAndAcademyId(
									student.getGlbtach(),
									student.getAcademyId());
					if (aeb != null) {
						hqlConditionExpression += " and  enrollmentBatchId = "
								+ Constants.PLACEHOLDER;
						list.add(aeb.getId());
					} else {
						return null;
					}
				} else {
					List<AcademyEnrollBatch> aeblst = academyenrollbatchBiz
							.findAcademyEnrollBatchByGId(student.getGlbtach());
					String gbatchIds = "";
					if (null != aeblst && aeblst.size() > 0) {
						for (int i = 0; i < aeblst.size(); i++) {
							gbatchIds += "," + aeblst.get(i).getId();
						}
						hqlConditionExpression += " and  enrollmentBatchId in ( "
								+ Constants.PLACEHOLDER + " ) ";
						list.add("$"
								+ gbatchIds.substring(1, gbatchIds.length()));
					} else {
						return null;
					}
				}
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
			// 基础专业
			if (student.getGlmajor() != 0) {
				List<Major> majorList = this.majorbiz
						.findMajorListByBaseMajorId(student.getGlmajor());
				String glmajors = "";
				if (null != majorList && majorList.size() > 0) {
					for (int i = 0; i < majorList.size(); i++) {
						glmajors += "," + majorList.get(i).getId();
					}
					hqlConditionExpression += " and  majorId in ( "
							+ Constants.PLACEHOLDER + " ) ";
					list.add("$" + glmajors.substring(1, glmajors.length()));
				} else {
					return null;
				}
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
		if (sidsLongs == null || sidsLongs.length == 0) 
		{
			return null;
		}
		for (Long id : sidsLongs)
		{
			if (studentIds.startsWith(","))
			{
				studentIds = id + "";
			} 
			else 
			{
				studentIds += "," + id;
			}
		}
		return studentIds;
	}
	
	/**
	 * 查询学生IDs集合_Hql语句
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	private String findStudentIdsByHql(Student student) throws Exception 
	{
		// 查询学生集合ID——hql
		String stuHql= "select id from Student where 1=1 ";
		if (student != null)
		{
			// 姓名
			if (student.getName() != null && !student.getName().equals("")) 
			{
				stuHql += " and name like '%"+student.getName()+"%' ";
			}
			// 证件号
			if (student.getCertNo() != null && !"".equals(student.getCertNo()))
			{
				stuHql += " and certNo like '%"+student.getCertNo()+"%' ";
			}
			// 院校
			if (student.getAcademyId() != 0)
			{
				stuHql += " and academyId="+student.getAcademyId();					
			}
			// 全局批次
			if (student.getGlbtach() != 0)
			{
				// 全局批次和院校批次可以固定一个招生批次，这样减轻数据库负荷
				if (student.getAcademyId() != 0) 
				{
					AcademyEnrollBatch aeb = academyenrollbatchBiz
							.findNonFinishedAcademyBatchByGlobalBatchIdAndAcademyId(
									student.getGlbtach(),
									student.getAcademyId());
					if (aeb != null) 
					{
						stuHql += " and  enrollmentBatchId = "+ aeb.getId();
						
					} 
					else 
					{
						return null;
					}
				} else {
					List<AcademyEnrollBatch> aeblst = academyenrollbatchBiz
							.findAcademyEnrollBatchByGId(student.getGlbtach());
					String gbatchIds = "";
					if (null != aeblst && aeblst.size() > 0) 
					{
						for (int i = 0; i < aeblst.size(); i++)
						{
							gbatchIds += "," + aeblst.get(i).getId();
						}
						stuHql += " and enrollmentBatchId in ( "+ gbatchIds.substring(1, gbatchIds.length()) + " ) ";
					} 
					else
					{
						return null;
					}
				}
			}
			// 批次
			if (student.getEnrollmentBatchId() != 0)
			{
				stuHql += " and  enrollmentBatchId= "+student.getEnrollmentBatchId();
			}
			
			// 层次
			if (student.getLevelId() != 0) 
			{
				stuHql += " and  levelId= "+student.getLevelId();
			}
			// 专业
			if (student.getMajorId() != 0) 
			{
				stuHql += " and  majorId= "+student.getMajorId();
			}
			// 基础专业
			if (student.getGlmajor() != 0) {
				List<Major> majorList = this.majorbiz
						.findMajorListByBaseMajorId(student.getGlmajor());
				String glmajors = "";
				if (null != majorList && majorList.size() > 0) 
				{
					for (int i = 0; i < majorList.size(); i++) 
					{
						glmajors += "," + majorList.get(i).getId();
					}
					stuHql += " and  majorId in ( "+ glmajors.substring(1, glmajors.length()) + " ) ";
				} 
				else 
				{
					return null;
				}
			}
			// 学习中心
			if (student.getBranchId() != 0) 
			{
				stuHql += " and  branchId= "+student.getBranchId();
			}
			// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
			// 如果起始状态ID>0,结束状态ID=0;则为无穷大
			// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
			// 如果都大于0,则取交集
			if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0)
			{
				stuHql += " and  status <"+student.getEndStatusId();
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0)
			{
				stuHql += " and  status >"+student.getStartStatusId();
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0)
			{
				stuHql += " and  status> "+student.getStartStatusId() + " and status<"+student.getEndStatusId();
			}
			// 复核状态
			if (student.getParamsInt().get("isChannelTypeChecked")!=null && student.getParamsInt().get("isChannelTypeChecked")!=-1)
			{
				stuHql += " and isChannelTypeChecked = "+student.getParamsInt().get("isChannelTypeChecked");
			}
		}
		return stuHql;
	}
	
	/**
	 * 查询学生账户ids集合
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	private String findAccountIds(Student student) throws Exception 
	{
		// 查询学生账户集合ID
		PageParame p = new PageParame();
		List<Object> list = null;
		String hqlConditionExpression = "";
		if (student != null)
		{
			list = new ArrayList<Object>();
			String studentIds=this.findStudentIds(student);
			//学生Ids
			if (studentIds != null && !studentIds.equals("")) 
			{
				hqlConditionExpression += " and studentId in ("
						+ Constants.PLACEHOLDER + ")";
				list.add("$" + studentIds);
			}
			else
			{
				return null;
			}
			
			if (!hqlConditionExpression.equals("")) {
				p.setHqlConditionExpression(hqlConditionExpression);
				p.setValues(list.toArray());
			}
		}
		String accountIds = ",";
		Long[] sidsLongs = this.stuamDao.getIDs(p);
		if (sidsLongs == null || sidsLongs.length == 0) 
		{
			return null;
		}
		for (Long id : sidsLongs)
		{
			if (accountIds.startsWith(","))
			{
				accountIds = id + "";
			} 
			else 
			{
				accountIds += "," + id;
			}
		}
		return accountIds;
	}
	
	/**
	 * 查询学生账户ids集合_HQL语句
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	private String findAccountIdsByHql(Student student) throws Exception 
	{
		// 查询学生账户集合ID
		String samHql= "select id from StudentAccountManagement where 1=1 ";
		if (student != null)
		{
			String stuHqlload=this.findStudentIdsByHql(student);
			if (stuHqlload != null && !stuHqlload.equals("select id from Student where 1=1 ")) 
			{
				samHql += " and studentId in (" + stuHqlload + ")";
			}
			else if(stuHqlload==null)
			{
				return null;
			}
		}
		return samHql;
	}
	
	/*
	 * 根据条件查询学生单纯充值的数量（分页）
	 * 
	 * @see net.cedu.biz.finance.StudentAccountAmountManagementBiz#findStudentSimpleRechargeCountByPage(net.cedu.entity.crm.Student, java.lang.String, java.lang.String)
	 */
	public int findStudentSimpleRechargeCountByPage(Student student,String starttime,String endtime) throws Exception
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (student != null)
		{						
			// 开始时间
			if (starttime != null && !"".equals(starttime)) 
			{
				hqlparam += " and createdTime >="+ Constants.PLACEHOLDER;
				list.add(starttime);
			}
			// 结束时间
			if (endtime != null && !"".equals(endtime)) 
			{
				hqlparam += " and createdTime <="+ Constants.PLACEHOLDER;
				list.add(endtime);
			}
			//单纯充值  feePaymentId=0 && feePaymentDetailId==0
			hqlparam += " and feePaymentId ="+ Constants.PLACEHOLDER;
			list.add(0);
			hqlparam += " and feePaymentDetailId ="+ Constants.PLACEHOLDER;
			list.add(0);
			//充值
			hqlparam += " and types ="+ Constants.PLACEHOLDER;
			list.add(Constants.STATUS_RECHARGE);
			
			//账户ID__Hql
			String samHql=this.findAccountIdsByHql(student);
			if (samHql != null && !samHql.equals("select id from StudentAccountManagement where 1=1 ")) 
			{
				hqlparam += " and accountId in ("
							+ samHql + ")";
			}
			else if(samHql==null)
			{
				return 0;
			}
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}

		return this.studentaccountamountmanagementdao.getCounts(p);
		
	}
	
	/*
	 * 根据条件查询学生单纯充值的集合（分页）
	 * 
	 * @see net.cedu.biz.finance.StudentAccountAmountManagementBiz#findStudentSimpleRechargeListByPage(net.cedu.entity.crm.Student, java.lang.String, java.lang.String, net.cedu.model.page.PageResult)
	 */
	public List<StudentAccountAmountManagement> findStudentSimpleRechargeListByPage(Student student,String starttime,String endtime,PageResult<StudentAccountAmountManagement> pr) throws Exception
	{
		List<StudentAccountAmountManagement> saamlist=null;
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (student != null)
		{						
			// 开始时间
			if (starttime != null && !"".equals(starttime)) 
			{
				hqlparam += " and createdTime >="+ Constants.PLACEHOLDER;
				list.add(starttime);
			}
			// 结束时间
			if (endtime != null && !"".equals(endtime)) 
			{
				hqlparam += " and createdTime <="+ Constants.PLACEHOLDER;
				list.add(endtime);
			}
			//单纯充值  feePaymentId=0 && feePaymentDetailId==0
			hqlparam += " and feePaymentId ="+ Constants.PLACEHOLDER;
			list.add(0);
			hqlparam += " and feePaymentDetailId ="+ Constants.PLACEHOLDER;
			list.add(0);
			//充值
			hqlparam += " and types ="+ Constants.PLACEHOLDER;
			list.add(Constants.STATUS_RECHARGE);
			
			//账户ID__Hql
			String samHql=this.findAccountIdsByHql(student);
			if (samHql != null && !samHql.equals("select id from StudentAccountManagement where 1=1 ")) 
			{
				hqlparam += " and accountId in ("
							+ samHql + ")";
			}
			else if(samHql==null)
			{
				return null;
			}
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		Long[] feeIds = studentaccountamountmanagementdao.getIDs(p);
		if (feeIds != null && feeIds.length != 0) 
		{
			saamlist=new ArrayList<StudentAccountAmountManagement>();
			for (int i = 0; i < feeIds.length; i++) 
			{
				StudentAccountAmountManagement feeObj=studentaccountamountmanagementdao.findById(Integer.valueOf(feeIds[i].toString()));
				if(feeObj!=null && this.stuamDao.findById(feeObj.getAccountId())!=null)
				{
					//学生相关
					Student stu=studentBiz.findStudentById(this.stuamDao.findById(feeObj.getAccountId()).getStudentId());
					if(stu!=null)
					{
						//学生姓名
						feeObj.setStudentName(stu.getName());
						//院校
						Academy academy=academyBiz.findAcademyById(stu.getAcademyId());
						if(stu.getAcademyId()>0 && academy!=null)
						{
							feeObj.setSchoolName(academy.getName());
						}
						//学习中心
						Branch branch=branchBiz.findBranchById(stu.getBranchId());
						if(stu.getBranchId()>0 && branch!=null)
						{
							feeObj.setBranchName(branch.getName());
						}
						//批次
						AcademyEnrollBatch aeb=academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId());
						if(stu.getEnrollmentBatchId()>0 && aeb!=null)
						{
							feeObj.setAcademyenrollbatchName(aeb.getEnrollmentName());
						}
						//层次
						Level level=levelbiz.findLevelById(stu.getLevelId());
						if(stu.getLevelId()>0 && level!=null)
						{
							feeObj.setLevelName(level.getName());
						}
						//专业
						Major major=majorbiz.findMajorById(stu.getMajorId());
						if(stu.getMajorId()>0 && major!=null)
						{
							feeObj.setMajorName(major.getName());
						}
						
					}
					//费用科目
					FeeSubject feeSubject=feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubject());
					if(feeObj.getFeeSubject()>0 && feeSubject!=null)
					{
						feeObj.setFeeSubjectName(feeSubject.getName());
					}
					saamlist.add(feeObj);
				}
			}
		}
		
		return saamlist;
		
	}
	
	/*
	 * 统计学生单纯充值
	 * 
	 * @see net.cedu.biz.finance.StudentAccountAmountManagementBiz#countStudentSimplyAccountMoney(net.cedu.entity.crm.Student, java.lang.String, java.lang.String)
	 */
	public String countStudentSimplyAccountMoney(Student student ,String starttime,String endtime) throws Exception
	{
		return studentaccountamountmanagementdao.countStudentSimplyAccountMoney(student, starttime, endtime);
	}
	
	/*
	 * 根据条件查询学生充值的数量（分页）_缴费单明细查询页面
	 * 
	 * @see net.cedu.biz.finance.StudentAccountAmountManagementBiz#findStudentRechargeCountByPage(net.cedu.entity.crm.Student, java.lang.String, java.lang.String,, java.lang.String)
	 */
	public int findStudentRechargeCountByPage(Student student,String starttime,String endtime,String feeSubjectIds) throws Exception
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (student != null)
		{						
			// 开始时间
			if (starttime != null && !"".equals(starttime)) 
			{
				hqlparam += " and createdTime >="+ Constants.PLACEHOLDER;
				list.add(starttime);
			}
			// 结束时间
			if (endtime != null && !"".equals(endtime)) 
			{
				hqlparam += " and createdTime <="+ Constants.PLACEHOLDER;
				list.add(endtime);
			}
			//费用科目
			if(feeSubjectIds!=null && !feeSubjectIds.equals(""))
			{
				hqlparam += " and feeSubject in ("
					+ Constants.PLACEHOLDER + ")";
				list.add("$" + feeSubjectIds);
			}
			
//			//充值
//			hqlparam += " and types ="+ Constants.PLACEHOLDER;
//			list.add(Constants.STATUS_RECHARGE);
			
			//充值、消费、退费
			hqlparam += " and types in ("+ Constants.PLACEHOLDER+") ";
			list.add("$"+Constants.STATUS_RECHARGE+","+Constants.STATUS_CONSUMPTION+","+Constants.STATUS_APPLY_CONSUMPTION_TRUE);
			
			//账户ID__Hql
			String samHql=this.findAccountIdsByHql(student);
			if (samHql != null && !samHql.equals("select id from StudentAccountManagement where 1=1 ")) 
			{
				hqlparam += " and accountId in ("
							+ samHql + ")";
			}
			else if(samHql==null)
			{
				return 0;
			}
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}

		return this.studentaccountamountmanagementdao.getCounts(p);
		
	}
	
	/*
	 * 根据条件查询学生充值的集合（分页）_缴费单明细查询页面
	 * 
	 * @see net.cedu.biz.finance.StudentAccountAmountManagementBiz#findStudentRechargeListByPage(net.cedu.entity.crm.Student, java.lang.String, java.lang.String, java.lang.String, net.cedu.model.page.PageResult)
	 */
	public List<StudentAccountAmountManagement> findStudentRechargeListByPage(Student student,String starttime,String endtime,String feeSubjectIds,PageResult<StudentAccountAmountManagement> pr) throws Exception
	{
		List<StudentAccountAmountManagement> saamlist=null;
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (student != null)
		{						
			// 开始时间
			if (starttime != null && !"".equals(starttime)) 
			{
				hqlparam += " and createdTime >="+ Constants.PLACEHOLDER;
				list.add(starttime);
			}
			// 结束时间
			if (endtime != null && !"".equals(endtime)) 
			{
				hqlparam += " and createdTime <="+ Constants.PLACEHOLDER;
				list.add(endtime);
			}
			//费用科目
			if(feeSubjectIds!=null && !feeSubjectIds.equals(""))
			{
				hqlparam += " and feeSubject in ("
					+ Constants.PLACEHOLDER + ")";
				list.add("$" + feeSubjectIds);
			}
			
//			//充值
//			hqlparam += " and types ="+ Constants.PLACEHOLDER;
//			list.add(Constants.STATUS_RECHARGE);
			//充值、消费、退费
			hqlparam += " and types in ("+ Constants.PLACEHOLDER+") ";
			list.add("$"+Constants.STATUS_RECHARGE+","+Constants.STATUS_CONSUMPTION+","+Constants.STATUS_APPLY_CONSUMPTION_TRUE);
			
			//账户ID__Hql
			String samHql=this.findAccountIdsByHql(student);
			if (samHql != null && !samHql.equals("select id from StudentAccountManagement where 1=1 ")) 
			{
				hqlparam += " and accountId in ("
							+ samHql + ")";
			}
			else if(samHql==null)
			{
				return null;
			}
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		Long[] feeIds = studentaccountamountmanagementdao.getIDs(p);
		if (feeIds != null && feeIds.length != 0) 
		{
			saamlist=new ArrayList<StudentAccountAmountManagement>();
			for (int i = 0; i < feeIds.length; i++) 
			{
				StudentAccountAmountManagement feeObj=studentaccountamountmanagementdao.findById(Integer.valueOf(feeIds[i].toString()));
				if(feeObj!=null && this.stuamDao.findById(feeObj.getAccountId())!=null)
				{
					FeePayment fp=this.feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId());
					if(fp!=null)
					{
						feeObj.setFeePayment(fp);
					}
					//学生相关
					Student stu=studentBiz.findStudentById(this.stuamDao.findById(feeObj.getAccountId()).getStudentId());
					if(stu!=null)
					{
						feeObj.setStudent(stu);
						//学生姓名
						feeObj.setStudentName(stu.getName());
						//院校
						Academy academy=academyBiz.findAcademyById(stu.getAcademyId());
						if(stu.getAcademyId()>0 && academy!=null)
						{
							feeObj.setSchoolName(academy.getName());
						}
						//学习中心
						Branch branch=branchBiz.findBranchById(stu.getBranchId());
						if(stu.getBranchId()>0 && branch!=null)
						{
							feeObj.setBranchName(branch.getName());
						}
						//批次
						AcademyEnrollBatch aeb=academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId());
						if(stu.getEnrollmentBatchId()>0 && aeb!=null)
						{
							feeObj.setAcademyenrollbatchName(aeb.getEnrollmentName());
						}
						//层次
						Level level=levelbiz.findLevelById(stu.getLevelId());
						if(stu.getLevelId()>0 && level!=null)
						{
							feeObj.setLevelName(level.getName());
						}
						//专业
						Major major=majorbiz.findMajorById(stu.getMajorId());
						if(stu.getMajorId()>0 && major!=null)
						{
							feeObj.setMajorName(major.getName());
						}
						
					}
					//费用科目
					FeeSubject feeSubject=feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubject());
					if(feeObj.getFeeSubject()>0 && feeSubject!=null)
					{
						feeObj.setFeeSubjectName(feeSubject.getName());
					}
					saamlist.add(feeObj);
				}
			}
		}		
		return saamlist;		
	}
	
	/*
	 * 根据条件查询缴费单明细数量（分页）_缴费单明细查询页面
	 * 
	 * @see net.cedu.biz.finance.StudentAccountAmountManagementBiz#findFeePaymentDetailCountByPageForSearch(net.cedu.entity.crm.Student, java.lang.String, java.lang.String, java.lang.String, int)
	 */
	public int findFeePaymentDetailCountByPageForSearch(Student student,String starttime,String endtime,String feeSubjectIds,int status,String ccStartTime,String ccEndTime,int feeWayId) throws Exception
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (student != null)
		{						
			// 开始时间
			if (starttime != null && !"".equals(starttime)) 
			{
				hqlparam += " and createdTime >="+ Constants.PLACEHOLDER;
				list.add(starttime);
			}
			// 结束时间
			if (endtime != null && !"".equals(endtime)) 
			{
				hqlparam += " and createdTime <="+ Constants.PLACEHOLDER;
				list.add(endtime);
			}	
			//总部确认时间起
			if (ccStartTime != null && !"".equals(ccStartTime)) 
			{
				hqlparam += " and ceduConfirmTime >="+ Constants.PLACEHOLDER;
				list.add(ccStartTime);
			}
			//总部确认时间止
			if (ccEndTime != null && !"".equals(ccEndTime)) 
			{
				hqlparam += " and ceduConfirmTime <="+ Constants.PLACEHOLDER;
				list.add(ccEndTime+" 23:59:59");
			}
			//费用科目
			if(feeSubjectIds!=null && !feeSubjectIds.equals(""))
			{
				hqlparam += " and feeSubjectId in ("
					+ Constants.PLACEHOLDER + ")";
				list.add("$" + feeSubjectIds);
			}
			//状态
			if(status!=0)
			{
				hqlparam += " and status ="+ Constants.PLACEHOLDER;
				list.add(status);
			}
			else
			{
				//状态
				hqlparam += " and ( status >="+ Constants.PLACEHOLDER;
				list.add(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);
				hqlparam += " or status <="+ Constants.PLACEHOLDER +" ) ";
				list.add(Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_DAI_QUE_REN);	
			}
			//缴费方式
			if(feeWayId>0)
			{
				hqlparam += " and feePaymentId in ( select id from FeePayment where feeWayId="
					+ feeWayId + ")";
			}
			
			//学生Ids__Hql
			String stuHql=this.findStudentIdsByHql(student);
			if (stuHql != null && !stuHql.equals("select id from Student where 1=1 ")) 
			{
				hqlparam += " and studentId in ("
							+ stuHql + ")";
			}
			else if(stuHql==null)
			{
				return 0;
			}
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		return this.feePaymentDetailDao.getCounts(p);
	}
	
	/*
	 * 根据条件查询缴费单明细集合（分页）_缴费单明细查询页面
	 * 
	 * @see net.cedu.biz.finance.StudentAccountAmountManagementBiz#findFeePaymentDetailListByPageForSearch(net.cedu.entity.crm.Student, java.lang.String, java.lang.String, java.lang.String, int, net.cedu.model.page.PageResult)
	 */
	public List<FeePaymentDetail> findFeePaymentDetailListByPageForSearch(Student student,String starttime,String endtime,String feeSubjectIds,int status,String ccStartTime,String ccEndTime,int feeWayId,PageResult<FeePaymentDetail> pr) throws Exception
	{
		List<FeePaymentDetail> feepdlist=null;
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (student != null)
		{						
			// 开始时间
			if (starttime != null && !"".equals(starttime)) 
			{
				hqlparam += " and createdTime >="+ Constants.PLACEHOLDER;
				list.add(starttime);
			}
			// 结束时间
			if (endtime != null && !"".equals(endtime)) 
			{
				hqlparam += " and createdTime <="+ Constants.PLACEHOLDER;
				list.add(endtime);
			}		
			//总部确认时间起
			if (ccStartTime != null && !"".equals(ccStartTime)) 
			{
				hqlparam += " and ceduConfirmTime >="+ Constants.PLACEHOLDER;
				list.add(ccStartTime);
			}
			//总部确认时间止
			if (ccEndTime != null && !"".equals(ccEndTime)) 
			{
				hqlparam += " and ceduConfirmTime <="+ Constants.PLACEHOLDER;
				list.add(ccEndTime+" 23:59:59");
			}
			//费用科目
			if(feeSubjectIds!=null && !feeSubjectIds.equals(""))
			{
				hqlparam += " and feeSubjectId in ("
					+ Constants.PLACEHOLDER + ")";
				list.add("$" + feeSubjectIds);
			}
			//状态
			if(status!=0)
			{
				hqlparam += " and status ="+ Constants.PLACEHOLDER;
				list.add(status);
			}
			else
			{
				//状态
				hqlparam += " and ( status >="+ Constants.PLACEHOLDER;
				list.add(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);
				hqlparam += " or status <="+ Constants.PLACEHOLDER +" ) ";
				list.add(Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_DAI_QUE_REN);	
			}
			//缴费方式
			if(feeWayId>0)
			{
				hqlparam += " and feePaymentId in ( select id from FeePayment where feeWayId="
					+ feeWayId + ")";
			}
			//学生Ids__Hql
			String stuHql=this.findStudentIdsByHql(student);
			if (stuHql != null && !stuHql.equals("select id from Student where 1=1 ")) 
			{
				hqlparam += " and studentId in ("
							+ stuHql + ")";
			}
			else if(stuHql==null)
			{
				return null;
			}
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		Long[] feeIds = feePaymentDetailDao.getIDs(p);
		if (feeIds != null && feeIds.length != 0) 
		{
			feepdlist=new ArrayList<FeePaymentDetail>();
			for (int i = 0; i < feeIds.length; i++) 
			{
				// 内存获取
				FeePaymentDetail feeObj = feePaymentDetailDao.findById(Integer.valueOf(feeIds[i].toString()));
				if(feeObj!=null)
				{
					//缴费单的编号
					FeePayment feePayment=feePaymentBiz.findFeePaymentById(feeObj.getFeePaymentId());
					if(feeObj.getFeePaymentId()>0 && feePayment!=null)
					{
						feeObj.setPaymentCode(feePayment.getCode());
						feeObj.setFeePayment(feePayment);
						feeObj.setFeeWayId(feePayment.getFeeWayId());//缴费方式
					}
					else
					{
						feeObj.setPaymentCode("");
					}
				
					//学生相关
					Student stu1=studentBiz.findStudentById(feeObj.getStudentId());
					if(feeObj.getStudentId()>0 && stu1!=null)
					{
						feeObj.setStudent(stu1);
						//学生姓名
						feeObj.setStudentName(stu1.getName());
						//院校
						Academy academy=academyBiz.findAcademyById(stu1.getAcademyId());
						if(stu1.getAcademyId()>0 && academy!=null)
						{
							feeObj.setSchoolName(academy.getName());
						}
						//学习中心
						Branch branch=branchBiz.findBranchById(stu1.getBranchId());
						if(stu1.getBranchId()>0 && branch!=null)
						{
							feeObj.setBranchName(branch.getName());
						}
						//批次
						AcademyEnrollBatch aeb=academyenrollbatchBiz.findAcademyEnrollBatchById(stu1.getEnrollmentBatchId());
						if(stu1.getEnrollmentBatchId()>0 && aeb!=null)
						{
							feeObj.setAcademyenrollbatchName(aeb.getEnrollmentName());
						}
						//层次
						Level level=levelbiz.findLevelById(stu1.getLevelId());
						if(stu1.getLevelId()>0 && level!=null)
						{
							feeObj.setLevelName(level.getName());
						}
						//专业
						Major major=majorbiz.findMajorById(stu1.getMajorId());
						if(stu1.getMajorId()>0 && major!=null)
						{
							feeObj.setMajorName(major.getName());
						}
					}
					//费用科目
					FeeSubject feeSubject=feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId());
					if(feeObj.getFeeSubjectId()>0 && feeSubject!=null)
					{
						feeObj.setPaymentSubjectName(feeSubject.getName());
					}
					//缴费金额
					feeObj.setJiaofeiValue((new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount())).subtract(new BigDecimal(feeObj.getRefundAmount()))).doubleValue());
					//多费用科目显示的金额
					feeObj.setMoneyToChannel((new BigDecimal(feeObj.getAmountPaied()).add(new BigDecimal(feeObj.getRechargeAmount()))).doubleValue());
					
					feepdlist.add(feeObj);
				}
			}
		}
		return feepdlist;
	}
	
	/*
	 * 统计学生充值_缴费单明细查询页面
	 * 
	 * @see net.cedu.biz.finance.StudentAccountAmountManagementBiz#countStudentRechargeMoney(net.cedu.entity.crm.Student, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String countStudentRechargeMoney(Student student ,String starttime,String endtime,String feeSubjectIds) throws Exception
	{
		return studentaccountamountmanagementdao.countStudentRechargeMoney(student, starttime, endtime, feeSubjectIds);
	}
	
	/*
	 * 统计学生消费_缴费单明细查询页面
	 * 
	 * @see net.cedu.biz.finance.StudentAccountAmountManagementBiz#countStudentXiaoFeiMoney(net.cedu.entity.crm.Student, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String countStudentXiaoFeiMoney(Student student ,String starttime,String endtime,String feeSubjectIds) throws Exception
	{
		return studentaccountamountmanagementdao.countStudentXiaoFeiMoney(student, starttime, endtime, feeSubjectIds);
	}
	
	/*
	 * 统计学生退费_缴费单明细查询页面
	 * 
	 * @see net.cedu.biz.finance.StudentAccountAmountManagementBiz#countStudentTuiFeiMoney(net.cedu.entity.crm.Student, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String countStudentTuiFeiMoney(Student student ,String starttime,String endtime,String feeSubjectIds) throws Exception
	{
		return studentaccountamountmanagementdao.countStudentTuiFeiMoney(student, starttime, endtime, feeSubjectIds);
	}
	
	/*
	 * 统计缴费单明细金额_缴费单明细查询页面
	 * 
	 * @see net.cedu.biz.finance.StudentAccountAmountManagementBiz#countStudentFeePaymentDetailMoney(net.cedu.entity.crm.Student, java.lang.String, java.lang.String, java.lang.String, int)
	 */
	public String countStudentFeePaymentDetailMoney(Student student ,String starttime,String endtime,String feeSubjectIds,int status,String ccStartTime,String ccEndTime,int feeWayId) throws Exception
	{
		return studentaccountamountmanagementdao.countStudentFeePaymentDetailMoney(student, starttime, endtime, feeSubjectIds,status,ccStartTime,ccEndTime,feeWayId);
	}
	
	/*
	 * 统计缴费单明细使用充值金额_缴费单明细查询页面
	 * 
	 * @see net.cedu.biz.finance.StudentAccountAmountManagementBiz#countStudentFeePaymentDetailShiYongChongZhiMoney(net.cedu.entity.crm.Student, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, java.lang.String, int)
	 */
	public String countStudentFeePaymentDetailShiYongChongZhiMoney(Student student ,String starttime,String endtime,String feeSubjectIds,int status,String ccStartTime,String ccEndTime,int feeWayId) throws Exception
	{
		return studentaccountamountmanagementdao.countStudentFeePaymentDetailShiYongChongZhiMoney(student, starttime, endtime, feeSubjectIds,status,ccStartTime,ccEndTime,feeWayId);
	}
	
	/*
	 * 统计缴费单明细金额_日收款单查询页面
	 * 
	 * @see net.cedu.biz.finance.StudentAccountAmountManagementBiz#countStudentfpdMoneyForCeduConfirmDay(net.cedu.entity.crm.Student, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, java.lang.String)
	 */
	public String countStudentfpdMoneyForCeduConfirmDay(Student student ,String starttime,String endtime,String feeSubjectIds,int status,String ccStartTime,String ccEndTime) throws Exception
	{
		return studentaccountamountmanagementdao.countStudentfpdMoneyForCeduConfirmDay(student, starttime, endtime, feeSubjectIds, status, ccStartTime, ccEndTime);
	}
	
	
}
