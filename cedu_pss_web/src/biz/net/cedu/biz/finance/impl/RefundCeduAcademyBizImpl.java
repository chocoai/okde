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
import net.cedu.biz.finance.RefundCeduAcademyBiz;
import net.cedu.common.Constants;
import net.cedu.dao.finance.RefundCeduAcademyDao;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.Major;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.RefundCeduAcademy;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 总部替院校垫付退费
 * 
 * @author xiao
 *
 */
@Service
public class RefundCeduAcademyBizImpl implements RefundCeduAcademyBiz
{

	@Autowired
	private RefundCeduAcademyDao refundCeduAcademyDao;//
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;
	@Autowired
	private FeePaymentBiz feePaymentBiz;
	@Autowired
	private BranchBiz branchBiz;
	@Autowired
	private StudentBiz studentBiz;
	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;
	@Autowired
	private LevelBiz levelBiz;
	@Autowired
	private MajorBiz majorBiz;

	/*
	 * 添加总部替院校垫付退费
	 * 
	 * @see net.cedu.biz.finance.RefundCeduAcademyBiz#addRefundCeduAcademy(net.cedu.entity.finance.RefundCeduAcademy)
	 */
	public boolean addRefundCeduAcademy(RefundCeduAcademy refundCeduAcademy)
			throws Exception 
	{
		if (refundCeduAcademy != null)
		{
			Object object = refundCeduAcademyDao.save(refundCeduAcademy);
			if (object != null) 
			{
				return true;
			}
		}		
		return false;
	}

	/*
	 * 删除总部替院校垫付退费
	 * 
	 * @see net.cedu.biz.finance.RefundCeduAcademyBiz#deleteRefundCeduAcademyById(int)
	 */
	public boolean deleteRefundCeduAcademyById(int id) throws Exception
	{
		if (id != 0)
		{
			Object object = refundCeduAcademyDao.deleteConfig(id);
			if (object != null)
			{
				return true;
			}
		}		
		return false;
	}

	/*
	 * 修改总部替院校垫付退费
	 * 
	 * @see net.cedu.biz.finance.RefundCeduAcademyBiz#updateRefundCeduAcademy(net.cedu.entity.finance.RefundCeduAcademy)
	 */
	public boolean updateRefundCeduAcademy(RefundCeduAcademy refundCeduAcademy)
			throws Exception
	{
		if (refundCeduAcademy != null) 
		{
			Object object = refundCeduAcademyDao.update(refundCeduAcademy);
			if (object != null) 
			{
				return true;
			}
		}
		return false;
	}	
	
	/*
	 * 根据Id查找总部替院校垫付退费
	 * 
	 * @see net.cedu.biz.finance.RefundCeduAcademyBiz#findRefundCeduAcademyById(int)
	 */
	public RefundCeduAcademy findRefundCeduAcademyById(int id) throws Exception 
	{
		return this.refundCeduAcademyDao.findById(id);
	}

	/*
	 * 查询总部替院校垫付退费总和
	 * @see net.cedu.biz.finance.RefundCeduAcademyBiz#findPaymentSum(net.cedu.entity.finance.RefundCeduAcademy, net.cedu.entity.crm.Student, net.cedu.entity.finance.FeePayment, java.lang.String, java.lang.String)
	 */
	public double findPaymentSum(RefundCeduAcademy refundCeduAcademy,
			Student student, FeePayment feePayment, String starttime,
			String endtime) throws Exception {
		PageParame p = new PageParame();
		List<Object> list = null;
		String hqlConditionExpression = "";
		if(refundCeduAcademy!=null)
		{
			list = new ArrayList<Object>();
			// 院校id
			if (refundCeduAcademy.getAcademyId() != 0)
			{
				hqlConditionExpression += " and academyId = " + Constants.PLACEHOLDER;
				list.add(refundCeduAcademy.getAcademyId());
			}
			// 类型
			if (refundCeduAcademy.getTypes() !=0)
			{
				hqlConditionExpression += " and types = " + Constants.PLACEHOLDER;
				list.add(refundCeduAcademy.getTypes());
			}
			//删除标记
			if (refundCeduAcademy.getDeleteFlag() !=-1)
			{
				hqlConditionExpression += " and deleteFlag = " + Constants.PLACEHOLDER;
				list.add(refundCeduAcademy.getDeleteFlag());
			}
			//状态
			if (refundCeduAcademy.getStatus()!=0)
			{
				hqlConditionExpression += " and status = " + Constants.PLACEHOLDER;
				list.add(refundCeduAcademy.getStatus());
			}
			// 学生
			String stuhql = getStudentHql(student);
			if(stuhql!="select id from Student where 1=1 ")
			{
				hqlConditionExpression += " and studentId in ( "+stuhql+" ) ";
			}
			// 退费单
			String refundhql = getFeePaymentHql(feePayment, starttime, endtime);
			if(refundhql!="select id from FeePayment where 1=1 ")
			{
				hqlConditionExpression += " and refundPaymentId in ( "+refundhql+" ) ";
			}
			if (!hqlConditionExpression.equals("")) {
				//为了调用索引查询提高效率
				hqlConditionExpression += " order by createdTime desc";
				p.setHqlConditionExpression(hqlConditionExpression);
				p.setValues(list.toArray());
			}
			Long[] racIds = refundCeduAcademyDao.getIDs(p);
			double sum = 0;
			if (racIds != null && racIds.length != 0)
			{
				for (int i = 0; i < racIds.length; i++)
				{
					RefundCeduAcademy rac = refundCeduAcademyDao.findById(Integer.parseInt(racIds[i].toString()));
					if(rac!=null)
					{
						//退费总额计算
						FeePaymentDetail fpd = feePaymentDetailBiz.findById(rac.getRefundPaymentDetailId());
						if(fpd!=null)
						{
							sum += fpd.getAmountPaied();
						}
					}
				}
			}
			return sum;
		}
		return 0;
	}

	/*
	 * 查询总部替院校垫付退费数量
	 * @see net.cedu.biz.finance.RefundCeduAcademyBiz#findRefundCeduAcademyPageCount(net.cedu.entity.finance.RefundCeduAcademy, net.cedu.entity.crm.Student, net.cedu.entity.finance.FeePayment, java.lang.String, java.lang.String, net.cedu.model.page.PageResult)
	 */
	public int findRefundCeduAcademyPageCount(
			RefundCeduAcademy refundCeduAcademy, Student student,
			FeePayment feePayment, String starttime, String endtime,
			PageResult<RefundCeduAcademy> pr) throws Exception {
		List<Object> list = null;
		String hqlConditionExpression = "";
		PageParame p = new PageParame();
		if(refundCeduAcademy!=null)
		{
			list = new ArrayList<Object>();
			// 院校id
			if (refundCeduAcademy.getAcademyId() != 0)
			{
				hqlConditionExpression += " and academyId = " + Constants.PLACEHOLDER;
				list.add(refundCeduAcademy.getAcademyId());
			}
			// 类型
			if (refundCeduAcademy.getTypes() !=0)
			{
				hqlConditionExpression += " and types = " + Constants.PLACEHOLDER;
				list.add(refundCeduAcademy.getTypes());
			}
			//删除标记
			if (refundCeduAcademy.getDeleteFlag() !=-1)
			{
				hqlConditionExpression += " and deleteFlag = " + Constants.PLACEHOLDER;
				list.add(refundCeduAcademy.getDeleteFlag());
			}
			//状态
			if (refundCeduAcademy.getStatus()!=0)
			{
				hqlConditionExpression += " and status = " + Constants.PLACEHOLDER;
				list.add(refundCeduAcademy.getStatus());
			}
			// 学生
			String stuhql = getStudentHql(student);
			if(stuhql!="select id from Student where 1=1 ")
			{
				hqlConditionExpression += " and studentId in ( "+stuhql+" ) ";
			}
			// 退费单
			String refundhql = getFeePaymentHql(feePayment, starttime, endtime);
			if(refundhql!="select id from FeePayment where 1=1 ")
			{
				hqlConditionExpression += " and refundPaymentId in ( "+refundhql+" ) ";
			}
			if (!hqlConditionExpression.equals("")) {
				p.setHqlConditionExpression(hqlConditionExpression);
				p.setValues(list.toArray());
			}
			return this.refundCeduAcademyDao.getCounts(p);
		}
		return 0;
	}

	/*
	 * 查询总部替院校垫付退费集合
	 * @see net.cedu.biz.finance.RefundCeduAcademyBiz#findRefundCeduAcademyPageList(net.cedu.entity.finance.RefundCeduAcademy, net.cedu.entity.crm.Student, net.cedu.entity.finance.FeePayment, java.lang.String, java.lang.String, net.cedu.model.page.PageResult)
	 */
	public List<RefundCeduAcademy> findRefundCeduAcademyPageList(
			RefundCeduAcademy refundCeduAcademy, Student student,
			FeePayment feePayment, String starttime, String endtime,
			PageResult<RefundCeduAcademy> pr) throws Exception {
		List<RefundCeduAcademy> raclist=null;
		PageParame p = new PageParame(pr);
		List<Object> list = null;
		String hqlConditionExpression = "";
		if(refundCeduAcademy!=null)
		{
			list = new ArrayList<Object>();
			// 院校id
			if (refundCeduAcademy.getAcademyId() != 0)
			{
				hqlConditionExpression += " and academyId = " + Constants.PLACEHOLDER;
				list.add(refundCeduAcademy.getAcademyId());
			}
			// 类型
			if (refundCeduAcademy.getTypes() !=0)
			{
				hqlConditionExpression += " and types = " + Constants.PLACEHOLDER;
				list.add(refundCeduAcademy.getTypes());
			}
			//删除标记
			if (refundCeduAcademy.getDeleteFlag() !=-1)
			{
				hqlConditionExpression += " and deleteFlag = " + Constants.PLACEHOLDER;
				list.add(refundCeduAcademy.getDeleteFlag());
			}
			//状态
			if (refundCeduAcademy.getStatus()!=0)
			{
				hqlConditionExpression += " and status = " + Constants.PLACEHOLDER;
				list.add(refundCeduAcademy.getStatus());
			}
			// 学生
			String stuhql = getStudentHql(student);
			if(stuhql!="select id from Student where 1=1 ")
			{
				hqlConditionExpression += " and studentId in ( "+stuhql+" ) ";
			}
			// 退费单
			String refundhql = getFeePaymentHql(feePayment, starttime, endtime);
			if(refundhql!="select id from FeePayment where 1=1 ")
			{
				hqlConditionExpression += " and refundPaymentId in ( "+refundhql+" ) ";
			}
			if (!hqlConditionExpression.equals("")) {
				p.setHqlConditionExpression(hqlConditionExpression);
				p.setValues(list.toArray());
			}
			Long[] rbIds = refundCeduAcademyDao.getIDs(p);
			if (rbIds != null && rbIds.length != 0)
			{
				raclist = new ArrayList<RefundCeduAcademy>();
				for (int i = 0; i < rbIds.length; i++)
				{
					RefundCeduAcademy rac = refundCeduAcademyDao.findById(Integer.parseInt(rbIds[i].toString()));
					if(rac!=null)
					{
						//退费单
						FeePayment fp = feePaymentBiz.findFeePaymentById(rac.getRefundPaymentId());
						rac.setFeePayment(fp==null?new FeePayment():fp);
						//学生
						Student s = studentBiz.findStudentById(rac.getStudentId());
						if(s!=null)
						{
							//学习中心
							Branch branch=this.branchBiz.findBranchById(s.getBranchId());
							if(branch!=null)
							{
								s.setBranchName(branch.getName());
							}
							//院校
							Academy academy = academyBiz.findAcademyById(s.getAcademyId());
							s.setSchoolName(academy==null?"":academy.getName());
							//批次
							AcademyEnrollBatch academyenrollbatch = academyEnrollBatchBiz.findAcademyEnrollBatchById(s.getEnrollmentBatchId());
							s.setAcademyenrollbatchName(academyenrollbatch==null?"":academyenrollbatch.getEnrollmentName());
							//层次
							Level level = levelBiz.findLevelById(s.getLevelId());
							s.setLevelName(level==null?"":level.getName());
							//专业
							Major major = majorBiz.findMajorById(s.getMajorId());
							s.setMajorName(major==null?"":major.getName());
						}
						rac.setStudent(s==null?new Student():s);
						//金额计算
						//退费单明细
						FeePaymentDetail fpd = feePaymentDetailBiz.findById(rac.getRefundPaymentDetailId());
						if(fpd!=null)
						{
							//退费金额
							rac.setRefundAmount(fpd.getAmountPaied());
							//对应的缴费单明细
							FeePaymentDetail fpd2 = feePaymentDetailBiz.findById(fpd.getSupperId());
							if(fpd2!=null)
							{
								//缴费金额
								rac.setAmountPaied(fpd2.getAmountPaied());
								//充值金额
								rac.setRechargeAmount(fpd2.getRechargeAmount());
							}
							//总金额(缴费金额+充值金额+退费金额)
							rac.setTotalAmount(rac.getAmountPaied()+rac.getRechargeAmount()+rac.getRefundAmount());
						}
						raclist.add(rac);
					}
				}
				return raclist;
			}
		}
		return null;
	}
	
	/*
	 * 获取学生查询条件（in堆栈溢出解决方案）
	 */
	private String getStudentHql(Student student)
	{
		String hql = "select id from Student where 1=1 ";
		if(student!=null)
		{
			if (student.getName() != null && !student.getName().equals("")) {
				hql += " and  name like '%"+student.getName()+"%'";
			}
			if (student.getAcademyId() != 0) {
				hql += " and  academyId= " + student.getAcademyId();
			}
			if (student.getBranchId() != 0) {
				hql += " and  branchId= " + student.getBranchId();
			}
			if (student.getBatchId() != 0) {
				hql += " and  enrollmentBatchId= " + student.getBatchId();
			}
			if (student.getLevelId() != 0) {
				hql += " and  levelId= " + student.getLevelId();
			}
			if (student.getMajorId() != 0) {
				hql += " and  majorId= " + student.getMajorId();
			}
			if (student.getCertNo()!=null && !student.getCertNo().equals("")) {
				hql += " and  certNo = '" + student.getCertNo() + "'";
			}
		}
		return hql;
	}
	
	/*
	 * 获取退费单查询条件（in堆栈溢出解决方案）
	 */
	private String getFeePaymentHql(FeePayment feePayment,String starttime,String endtime)
	{
		String hql = "select id from FeePayment where 1=1 ";
		if(feePayment!=null)
		{
			// 全局批次
			if(feePayment.getCommonBatch()!=0)
			{
				hql += "and commonBatch = " + feePayment.getCommonBatch();
			}
			// 退费单号
			if(feePayment.getCode()!=null && !feePayment.getCode().equals(""))
			{
				hql += " and code = '" + feePayment.getCode() +"'";
			}
			// 退费单状态
			if(feePayment.getStatus()!=0)
			{
				hql += " and  status = " + feePayment.getStatus();
			}
			// 退费日期起
			if (starttime != null && !starttime.equals("")) {
				hql += " and  createdTime >= '" + starttime + "'";
			}
			// 退费日期止
			if (endtime != null && !endtime.equals("")) {
				hql += " and  createdTime <= '" + endtime + "'";
			}
		}
		//退费单
		hql += " and  status < " + Constants.PAYMENT_STATUS_ZUO_FEI;
		return hql;
	}
	
}
