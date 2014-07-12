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
import net.cedu.biz.finance.RefundBranchBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.dao.finance.RefundBranchDao;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.Major;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.RefundBranch;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 总部/院校退中心费用
 * 
 * @author xiao
 *
 */
@Service
public class RefundBranchBizImpl implements RefundBranchBiz
{

	@Autowired
	private RefundBranchDao refundBranchDao;//
	@Autowired
	private StudentBiz studentBiz;
	@Autowired
	private FeePaymentBiz feePaymentBiz;
	@Autowired
	private BranchBiz branchBiz;
	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;
	@Autowired
	private LevelBiz levelBiz;
	@Autowired
	private MajorBiz majorBiz;
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;

	/*
	 * 添加总部/院校退中心费用
	 * 
	 * @see net.cedu.biz.finance.RefundBranchBiz#addRefundBranch(net.cedu.entity.finance.RefundBranch)
	 */
	public boolean addRefundBranch(RefundBranch refundBranch) throws Exception 
	{
		if (refundBranch != null)
		{
			Object object = refundBranchDao.save(refundBranch);
			if (object != null) 
			{
				return true;
			}
		}		
		return false;
	}
	
	/*
	 * 删除总部/院校退中心费用
	 * 
	 * @see net.cedu.biz.finance.RefundBranchBiz#deleteRefundBranchById(int)
	 */
	public boolean deleteRefundBranchById(int id) throws Exception
	{
		if (id != 0) {
			Object object = refundBranchDao.deleteConfig(id);
			if (object != null) {
				return true;
			}
		}		
		return false;
	}

	/*
	 * 修改总部/院校退中心费用
	 * 
	 * @see net.cedu.biz.finance.RefundBranchBiz#updateRefundBranch(net.cedu.entity.finance.RefundBranch)
	 */
	public boolean updateRefundBranch(RefundBranch refundBranch)
			throws Exception 
	{
		if (refundBranch != null) 
		{
			Object object = refundBranchDao.update(refundBranch);
			if (object != null) 
			{
				return true;
			}
		}
		return false;
	}
	
	
	/*
	 * 根据Id查找总部/院校退中心费用
	 * 
	 * @see net.cedu.biz.finance.RefundBranchBiz#findRefundBranchById(int)
	 */
	public RefundBranch findRefundBranchById(int id) throws Exception 
	{
		return this.refundBranchDao.findById(id);
	}

	/*
	 * 查询总部/院校退中心费用集合
	 * @see net.cedu.biz.finance.RefundBranchBiz#findRefundBranchPageList(net.cedu.entity.finance.RefundBranch, net.cedu.entity.crm.Student, net.cedu.entity.finance.FeePayment, java.lang.String, java.lang.String, net.cedu.model.page.PageResult)
	 */
	public List<RefundBranch> findRefundBranchPageList(RefundBranch refundBranch,Student student,FeePayment feePayment,String starttime,String endtime,
			PageResult<RefundBranch> pr) throws Exception {
		List<RefundBranch> rblist=null;
		PageParame p = new PageParame(pr);
		List<Object> list = null;
		String hqlConditionExpression = "";
		if(refundBranch!=null)
		{
			list = new ArrayList<Object>();
			// 中心id
			if (refundBranch.getBranchId() != 0)
			{
				hqlConditionExpression += " and branchId = " + Constants.PLACEHOLDER;
				list.add(refundBranch.getBranchId());
			}
			// 应退部门 
			if (refundBranch.getRefundDepId() !=0)
			{
				hqlConditionExpression += " and refundDepId = " + Constants.PLACEHOLDER;
				list.add(refundBranch.getRefundDepId());
			}
			// 类型
			if (refundBranch.getTypes() !=0)
			{
				hqlConditionExpression += " and types = " + Constants.PLACEHOLDER;
				list.add(refundBranch.getTypes());
			}
			//删除标记
			if (refundBranch.getDeleteFlag() !=-1)
			{
				hqlConditionExpression += " and deleteFlag = " + Constants.PLACEHOLDER;
				list.add(refundBranch.getDeleteFlag());
			}
			//状态
			if (refundBranch.getStatus()!=0)
			{
				hqlConditionExpression += " and status = " + Constants.PLACEHOLDER;
				list.add(refundBranch.getStatus());
			}
			//状态范围
			if (refundBranch.getStatuses()!=null && !refundBranch.getStatuses().equals(""))
			{
				hqlConditionExpression += " and status in ( " + Constants.PLACEHOLDER + ")";
				list.add("$"+refundBranch.getStatuses());
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
			Long[] rbIds = refundBranchDao.getIDs(p);
			if (rbIds != null && rbIds.length != 0)
			{
				rblist = new ArrayList<RefundBranch>();
				for (int i = 0; i < rbIds.length; i++)
				{
					RefundBranch rb = refundBranchDao.findById(Integer.parseInt(rbIds[i].toString()));
					if(rb!=null)
					{
						//退费单
						FeePayment fp = feePaymentBiz.findFeePaymentById(rb.getRefundPaymentId());
						rb.setFeePayment(fp==null?new FeePayment():fp);
						//学生
						Student s = studentBiz.findStudentById(rb.getStudentId());
						if(s!=null)
						{
							//学习中心
							Branch branch =this.branchBiz.findBranchById(s.getBranchId());
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
						rb.setStudent(s==null?new Student():s);
						//金额计算
						//退费单明细
						FeePaymentDetail fpd = feePaymentDetailBiz.findById(rb.getRefundPaymentDetailId());
						if(fpd!=null)
						{
							//退费金额
							rb.setRefundAmount(fpd.getAmountPaied());
							//对应的缴费单明细
							FeePaymentDetail fpd2 = feePaymentDetailBiz.findById(fpd.getSupperId());
							if(fpd2!=null)
							{
								//缴费金额
								rb.setAmountPaied(fpd2.getAmountPaied());
								//充值金额
								rb.setRechargeAmount(fpd2.getRechargeAmount());
							}
							//总金额(缴费金额+充值金额+退费金额)
							rb.setTotalAmount(rb.getAmountPaied()+rb.getRechargeAmount()+rb.getRefundAmount());
						}
						rblist.add(rb);
					}
				}
				return rblist;
			}
		}
		return null;
	}

	/*
	 * 查询总部/院校退中心退费数量
	 * @see net.cedu.biz.finance.RefundBranchBiz#findRefundBranchPageCount(net.cedu.entity.finance.RefundBranch, net.cedu.entity.crm.Student, net.cedu.entity.finance.FeePayment, java.lang.String, java.lang.String, net.cedu.model.page.PageResult)
	 */
	public int findRefundBranchPageCount(RefundBranch refundBranch,Student student,FeePayment feePayment,String starttime,String endtime,
			PageResult<RefundBranch> pr) throws Exception {
		List<Object> list = null;
		String hqlConditionExpression = "";
		PageParame p = new PageParame();
		if(refundBranch!=null)
		{
			list = new ArrayList<Object>();
			// 中心id
			if (refundBranch.getBranchId() != 0)
			{
				hqlConditionExpression += " and branchId = " + Constants.PLACEHOLDER;
				list.add(refundBranch.getBranchId());
			}
			// 应退部门 
			if (refundBranch.getRefundDepId() !=0)
			{
				hqlConditionExpression += " and refundDepId = " + Constants.PLACEHOLDER;
				list.add(refundBranch.getRefundDepId());
			}
			// 类型
			if (refundBranch.getTypes() !=0)
			{
				hqlConditionExpression += " and types = " + Constants.PLACEHOLDER;
				list.add(refundBranch.getTypes());
			}
			//删除标记
			if (refundBranch.getDeleteFlag() !=-1)
			{
				hqlConditionExpression += " and deleteFlag = " + Constants.PLACEHOLDER;
				list.add(refundBranch.getDeleteFlag());
			}
			//状态
			if (refundBranch.getStatus()!=0)
			{
				hqlConditionExpression += " and status = " + Constants.PLACEHOLDER;
				list.add(refundBranch.getStatus());
			}
			//状态范围
			if (refundBranch.getStatuses()!=null && !refundBranch.getStatuses().equals(""))
			{
				hqlConditionExpression += " and status in ( " + Constants.PLACEHOLDER + ")";
				list.add("$"+refundBranch.getStatuses());
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
			return this.refundBranchDao.getCounts(p);
		}
		return 0;
	}

	/*
	 * 查询总部/院校退中心退费总和
	 * @see net.cedu.biz.finance.RefundBranchBiz#findPaymentSum(net.cedu.entity.finance.RefundBranch, net.cedu.entity.crm.Student, net.cedu.entity.finance.FeePayment, java.lang.String, java.lang.String, net.cedu.model.page.PageResult)
	 */
	public double findPaymentSum(RefundBranch refundBranch, Student student,
			FeePayment feePayment, String starttime, String endtime) throws Exception {
		PageParame p = new PageParame();
		List<Object> list = null;
		String hqlConditionExpression = "";
		if(refundBranch!=null)
		{
			list = new ArrayList<Object>();
			// 中心id
			if (refundBranch.getBranchId() != 0)
			{
				hqlConditionExpression += " and branchId = " + Constants.PLACEHOLDER;
				list.add(refundBranch.getBranchId());
			}
			// 应退部门 
			if (refundBranch.getRefundDepId() !=0)
			{
				hqlConditionExpression += " and refundDepId = " + Constants.PLACEHOLDER;
				list.add(refundBranch.getRefundDepId());
			}
			// 类型
			if (refundBranch.getTypes() !=0)
			{
				hqlConditionExpression += " and types = " + Constants.PLACEHOLDER;
				list.add(refundBranch.getTypes());
			}
			//删除标记
			if (refundBranch.getDeleteFlag() !=-1)
			{
				hqlConditionExpression += " and deleteFlag = " + Constants.PLACEHOLDER;
				list.add(refundBranch.getDeleteFlag());
			}
			//状态
			if (refundBranch.getStatus()!=0)
			{
				hqlConditionExpression += " and status = " + Constants.PLACEHOLDER;
				list.add(refundBranch.getStatus());
			}
			//状态范围
			if (refundBranch.getStatuses()!=null && !refundBranch.getStatuses().equals(""))
			{
				hqlConditionExpression += " and status in ( " + Constants.PLACEHOLDER + ")";
				list.add("$"+refundBranch.getStatuses());
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
				hqlConditionExpression += " order by createdTime desc ";
				p.setHqlConditionExpression(hqlConditionExpression);
				p.setValues(list.toArray());
			}
			Long[] rbIds = refundBranchDao.getIDs(p);
			double sum = 0;
			if (rbIds != null && rbIds.length != 0)
			{
				for (int i = 0; i < rbIds.length; i++)
				{
					RefundBranch rb = refundBranchDao.findById(Integer.parseInt(rbIds[i].toString()));
					if(rb!=null)
					{
						//退费总额计算
						FeePaymentDetail fpd = feePaymentDetailBiz.findById(rb.getRefundPaymentDetailId());
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

	/*
	 * 批量修改费用状态
	 * @see net.cedu.biz.finance.RefundBranchBiz#updateRefundBranchStatusByIds(java.lang.String, int)
	 */
	public boolean updateRefundBranchStatusByIds(String ids, int status,int userId)
			throws Exception {
		if(ids!=null && !ids.equals(""))
		{
			refundBranchDao.update(" set status = "+Constants.PLACEHOLDER + ",updaterId = "+Constants.PLACEHOLDER +",updatedTime = "+Constants.PLACEHOLDER, ids, new Object[] { status,userId,DateUtil.getNowDate("yyyy-MM-dd HH:mm:ss") });
			return true;
		}
		return false;
	}

	/*
	 * 查询ids总退费费用
	 * @see net.cedu.biz.finance.RefundBranchBiz#findRefundBranchSumPaymentByIds(java.lang.String)
	 */
	public double findRefundBranchSumPaymentByIds(String ids)throws Exception {
		if(ids!=null && !ids.equals(""))
		{
			String[] idsLong = ids.split(",");
			if(idsLong!=null && idsLong.length>0)
			{
				double sum = 0;
				for(String id : idsLong)
				{
					RefundBranch rb = refundBranchDao.findById(Integer.parseInt(id));
					if(rb!=null)
					{
						FeePaymentDetail fpd = feePaymentDetailBiz.findById(rb.getRefundPaymentDetailId());
						if(fpd!=null)
						{
							sum += fpd.getAmountPaied();
						}
					}
				}
				return sum;
			}
		}
		return 0;
	}
	
	/*
	 * 根据条件查询费用表集合
	 * @see net.cedu.biz.finance.RefundBranchBiz#fingRefundBranchByParam(net.cedu.entity.finance.RefundBranch)
	 */
	public List<RefundBranch> findRefundBranchByParam(RefundBranch refundBranch)throws Exception
	{
		List<RefundBranch> rbList = null;
		PageParame p = new PageParame();
		List<Object> list = null;
		String hqlConditionExpression = "";
		if(refundBranch!=null)
		{
			list = new ArrayList<Object>();
			// 中心id
			if (refundBranch.getBranchId() != 0)
			{
				hqlConditionExpression += " and branchId = " + Constants.PLACEHOLDER;
				list.add(refundBranch.getBranchId());
			}
			// 应退部门 
			if (refundBranch.getRefundDepId() !=0)
			{
				hqlConditionExpression += " and refundDepId = " + Constants.PLACEHOLDER;
				list.add(refundBranch.getRefundDepId());
			}
			// 类型
			if (refundBranch.getTypes() !=0)
			{
				hqlConditionExpression += " and types = " + Constants.PLACEHOLDER;
				list.add(refundBranch.getTypes());
			}
			//删除标记
			if (refundBranch.getDeleteFlag() !=-1)
			{
				hqlConditionExpression += " and deleteFlag = " + Constants.PLACEHOLDER;
				list.add(refundBranch.getDeleteFlag());
			}
			//状态
			if (refundBranch.getStatus()!=0)
			{
				hqlConditionExpression += " and status = " + Constants.PLACEHOLDER;
				list.add(refundBranch.getStatus());
			}
			//状态范围
			if (refundBranch.getStatuses()!=null && !refundBranch.getStatuses().equals(""))
			{
				hqlConditionExpression += " and status in ( " + Constants.PLACEHOLDER + ")";
				list.add("$"+refundBranch.getStatuses());
			}
			//退费单id
			if (refundBranch.getRefundPaymentId()!=0)
			{
				hqlConditionExpression += " and refundPaymentId = " + Constants.PLACEHOLDER;
				list.add(refundBranch.getRefundPaymentId());
			}
			if (!hqlConditionExpression.equals("")) {
				hqlConditionExpression += " order by createdTime desc ";
				p.setHqlConditionExpression(hqlConditionExpression);
				p.setValues(list.toArray());
			}
			Long[] rbIds = refundBranchDao.getIDs(p);
			if (rbIds != null && rbIds.length != 0)
			{
				rbList = new ArrayList<RefundBranch>();
				for (int i = 0; i < rbIds.length; i++)
				{
					RefundBranch rb = refundBranchDao.findById(Integer.parseInt(rbIds[i].toString()));
					if(rb!=null)
					{
						rbList.add(rb);
					}
				}
				return rbList;
			}
		}
		return null;
	}
	
	/*
	 * 根据ids查询费用确认集合
	 * @see net.cedu.biz.finance.RefundBranchBiz#fingRefundBranchByIds(java.lang.String)
	 */
	public List<RefundBranch> findRefundBranchByIds(String ids)throws Exception
	{
		List<RefundBranch> rbList = null;
		PageParame p = new PageParame();
		List<Object> list = null;
		String hqlConditionExpression = "";
		if(ids!=null && !ids.equals(""))
		{
			list = new ArrayList<Object>();
			hqlConditionExpression += " and id in ( " + Constants.PLACEHOLDER + " ) ";
			list.add("$"+ids);
		}
		if (!hqlConditionExpression.equals("")) {
			hqlConditionExpression += " order by createdTime desc ";
			p.setHqlConditionExpression(hqlConditionExpression);
			p.setValues(list.toArray());
		}
		Long[] rbIds = refundBranchDao.getIDs(p);
		if (rbIds != null && rbIds.length != 0)
		{
			rbList = new ArrayList<RefundBranch>();
			for (int i = 0; i < rbIds.length; i++)
			{
				RefundBranch rb = refundBranchDao.findById(Integer.parseInt(rbIds[i].toString()));
				if(rb!=null)
				{
					rbList.add(rb);
				}
			}
		}
		return rbList;
	}

}
