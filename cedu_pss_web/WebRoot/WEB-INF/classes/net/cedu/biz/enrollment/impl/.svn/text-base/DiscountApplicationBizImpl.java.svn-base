package net.cedu.biz.enrollment.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.DiscountApplicationBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.biz.enrollment.StudentDiscountPolicyBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.il8n.ResourcesTool;
import net.cedu.dao.crm.StudentDao;
import net.cedu.dao.enrollment.DiscountApplicationDao;
import net.cedu.dao.enrollment.StudentDiscountPolicyDao;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.DiscountApplication;
import net.cedu.entity.enrollment.Major;
import net.cedu.entity.enrollment.StudentDiscountPolicy;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 优惠卷(优惠申请)
 * 
 * @author lixiaojun
 * 
 */
@Service
public class DiscountApplicationBizImpl implements DiscountApplicationBiz
{
	
	@Autowired
	private DiscountApplicationDao discountApplicationDao;
	
	@Autowired 
	private StudentDiscountPolicyDao studentDiscountPolicyDao;//优惠标准数据访问接口
	
	@Autowired 
	private StudentDiscountPolicyBiz studentDiscountPolicyBiz;//优惠标准业务层接口
	
	@Autowired 
	private FeeSubjectBiz feeSubjectBiz;//费用科目业务层接口
	
	@Autowired
	private StudentDao studentDao;//学生数据访问成接口
	
	@Autowired
	private StudentBiz studentBiz;//学生业务层接口
	
	@Autowired
	private BranchBiz branchBiz;//学习中心_业务层接口
	
	@Autowired
	private AcademyBiz academyBiz;//院校业务层接口
	
	@Autowired
	private AcademyEnrollBatchBiz academyenrollbatchBiz;//招生批次业务层接口
	
	@Autowired
	private LevelBiz levelBiz;//层次业务层接口
	
	@Autowired
	private MajorBiz majorBiz;//专业业务层接口
	

	/*
	 * 添加优惠卷
	 * 
	 * @see net.cedu.biz.enrollment.DiscountApplicationBiz#addDiscountApplication(net.cedu.entity.enrollment.DiscountApplication)
	 */
	public boolean addDiscountApplication(DiscountApplication discountApplication) throws Exception 
	{
		if(discountApplication!=null)
		{
			Object object = discountApplicationDao.save(discountApplication);
			if (object != null) 
			{
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 批量添加优惠卷
	 * @see net.cedu.biz.enrollment.DiscountApplicationBiz#addBatchDiscountApplication(java.util.List)
	 */
	public boolean addBatchDiscountApplication(List<DiscountApplication> discountlist) throws Exception
	{
		boolean isfail=false;
		for(DiscountApplication dl:discountlist)
		{
			isfail=this.addDiscountApplication(dl);
		}
		return isfail;
	}

	/*
	 * 删除优惠卷(读取配置文件)
	 * 
	 * @see net.cedu.biz.enrollment.DiscountApplicationBiz#deleteConfigDiscountApplicationById(int)
	 */
	public boolean deleteConfigDiscountApplicationById(int id) throws Exception 
	{
		if (id != 0) 
		{
			Object object = discountApplicationDao.deleteConfig(id);
			if (object != null) 
			{
				return true;
			}
		}
		
		return false;
	}
	
	/*
	 * 修改优惠卷
	 * 
	 * @see net.cedu.biz.enrollment.DiscountApplicationBiz#updateDiscountApplication(net.cedu.entity.enrollment.DiscountApplication)
	 */
	public boolean updateDiscountApplication(DiscountApplication discountApplication) throws Exception 
	{
		if (discountApplication != null) 
		{
			Object object = discountApplicationDao.update(discountApplication);
			if (object != null) 
			{
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 根据Id查找优惠卷
	 * 
	 * @see net.cedu.biz.enrollment.DiscountApplicationBiz#findDiscountApplicationById(int)
	 */
	public DiscountApplication findDiscountApplicationById(int id) throws Exception 
	{
		return this.discountApplicationDao.findById(id);
	}
	
	/*
	 * 根据Id查找优惠卷详细
	 * 
	 * @see net.cedu.biz.enrollment.DiscountApplicationBiz#findDiscountApplicationDetailsById(int)
	 */
	public DiscountApplication findDiscountApplicationDetailsById(int id) throws Exception 
	{
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 检测查询学生的条件是否为空
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
//			// 证件号
//			if (student.getCertNo() != null && !"".equals(student.getCertNo())) {
//				hqlConditionExpression += "and certNo like"
//						+ Constants.PLACEHOLDER;
//			}
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
//			// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
//			// 如果起始状态ID>0,结束状态ID=0;则为无穷大
//			// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
//			// 如果都大于0,则取交集
//			if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0) {
//				hqlConditionExpression += " and  status <"
//						+ Constants.PLACEHOLDER;
//			}
//			if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0) {
//				hqlConditionExpression += " and  status >"
//						+ Constants.PLACEHOLDER;
//			}
//			if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0) {
//				hqlConditionExpression += " and  status> "
//						+ Constants.PLACEHOLDER + " and status<"
//						+ Constants.PLACEHOLDER;
//			}
//			if (!hqlConditionExpression.equals("")) {
//
//			}

		}
		if (hqlConditionExpression != null
				&& !hqlConditionExpression.equals("")) {
			return true;
		}
		return false;
	}
	
	/*
	 * 获取优惠卷数量
	 * 
	 * @see net.cedu.biz.enrollment.DiscountApplicationBiz#findDiscountApplicationCountByDetails(net.cedu.entity.crm.Student, net.cedu.entity.enrollment.DiscountApplication, net.cedu.model.page.PageResult)
	 */
	public int findDiscountApplicationCountByDetails(Student student,DiscountApplication discountApplication,PageResult<DiscountApplication> pr) throws Exception
	{
		try {
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
				if (!hqlConditionExpression.equals("")) {
					p.setHqlConditionExpression(hqlConditionExpression);
					p.setValues(list.toArray());
				}
			}
			String studentIds = ",";
			Long[] sidsLongs = studentDao.getIDs(p);
//			if (sidsLongs == null) {
//				return 0;
//			}
//			for (Long id : sidsLongs) {
//				if (studentIds.startsWith(",")) {
//					studentIds = id + "";
//				} else {
//					studentIds += "," + id;
//				}
//			}
			StringBuffer studentIdsSB = new StringBuffer("");
			if (sidsLongs == null) {
				return 0;
			}
			int index = 0;
			for (Long id : sidsLongs) {
				if (index==0) {
					studentIdsSB.append(id + "");
				} else {
					studentIdsSB.append("," + id);
				}
				index++;
			}
			studentIds = studentIdsSB.toString();

			List list2 = null;
			String hqlConditionExpression2 = "";
			PageParame p2 = new PageParame(pr);
			list2 = new ArrayList();

			if (discountApplication != null) {
				//使用状态
				if (discountApplication.getUsageFlag() != Constants.POLICY_USING_STATUS_ALL) 
				{
					hqlConditionExpression2 += " and usageFlag ="
							+ Constants.PLACEHOLDER;
					list2.add(discountApplication.getUsageFlag());
				}
				//审批方
				if(discountApplication.getAuditee()!=-1)
				{
					hqlConditionExpression2 += " and auditee ="
						+ Constants.PLACEHOLDER;
				list2.add(discountApplication.getAuditee());
				}
			}
			if (!studentIds.startsWith(",") && checkStudentInfo(student)) {
				hqlConditionExpression2 += " and studentId in ("
						+ Constants.PLACEHOLDER + ")";
				list2.add("$" + studentIds);
			}

			if (!hqlConditionExpression2.equals("")) {
				p2.setHqlConditionExpression(hqlConditionExpression2);
				p2.setValues(list2.toArray());
			}

			return discountApplicationDao.getCounts(p2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/*
	 * 获取优惠卷列表
	 * 
	 * @see net.cedu.biz.enrollment.DiscountApplicationBiz#findDiscountApplicationListByDetails(net.cedu.entity.crm.Student, net.cedu.entity.enrollment.DiscountApplication, net.cedu.model.page.PageResult)
	 */
	public List<DiscountApplication> findDiscountApplicationListByDetails(Student student,DiscountApplication discountApplication,PageResult<DiscountApplication> pr) throws Exception 
	{
		List<DiscountApplication> disAppList = new ArrayList<DiscountApplication>();
		try {
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
				if (!hqlConditionExpression.equals("")) {
					p.setHqlConditionExpression(hqlConditionExpression);
					p.setValues(list.toArray());
				}
			}
			String studentIds = ",";
			Long[] sidsLongs = studentDao.getIDs(p);
//			if (sidsLongs == null) {
//				return null;
//			}
//			for (Long id : sidsLongs) {
//				if (studentIds.startsWith(",")) {
//					studentIds = id + "";
//				} else {
//					studentIds += "," + id;
//				}
//			}
			StringBuffer studentIdsSB = new StringBuffer("");
			if (sidsLongs == null) {
				return null;
			}
			int index = 0;
			for (Long id : sidsLongs) {
				if (index==0) {
					studentIdsSB.append(id + "");
				} else {
					studentIdsSB.append("," + id);
				}
				index++;
			}
			studentIds = studentIdsSB.toString();

			List list2 = null;
			String hqlConditionExpression2 = "";
			PageParame p2 = new PageParame(pr);
			list2 = new ArrayList();

			if (discountApplication != null) {
				//使用状态
				if (discountApplication.getUsageFlag() != Constants.POLICY_USING_STATUS_ALL) 
				{
					hqlConditionExpression2 += " and usageFlag ="
							+ Constants.PLACEHOLDER;
					list2.add(discountApplication.getUsageFlag());
				}
				//审批方
				if(discountApplication.getAuditee()!=-1)
				{
					hqlConditionExpression2 += " and auditee ="
						+ Constants.PLACEHOLDER;
				list2.add(discountApplication.getAuditee());
				}
				
			}
			if (!studentIds.startsWith(",") && checkStudentInfo(student)) {
				hqlConditionExpression2 += " and studentId in ("
						+ Constants.PLACEHOLDER + ")";
				list2.add("$" + studentIds);
			}

			if (!hqlConditionExpression2.equals("")) {
				p2.setHqlConditionExpression(hqlConditionExpression2);
				p2.setValues(list2.toArray());
			}
			// Ids集合
			Long[] idsLongs = discountApplicationDao.getIDs(p2);
			for (int i = 0; i < idsLongs.length; i++) {
				DiscountApplication discount = discountApplicationDao.findById(Integer
						.parseInt(idsLongs[i].toString()));
				if (discount != null) 
				{
					//学生相关
					Student d = studentBiz.findStudentById(discount.getStudentId());
					if (d != null)
					{
						// 姓名
						discount.setStudentName(d.getName());
						discount.setGender(d.getGender());
						Academy academy = academyBiz.findAcademyById(d
								.getAcademyId());

						if (academy != null) 
						{
							discount.setAcademyName(academy.getName());
						}
						
						//学习中心
						Branch branch=this.branchBiz.findBranchById(d.getBranchId());
						if(branch!=null)
						{
							discount.setBranchName(branch.getName());
						}

						AcademyEnrollBatch academyenrollbatch = academyenrollbatchBiz
								.findAcademyEnrollBatchById(d
										.getEnrollmentBatchId());
						if (academyenrollbatch != null) 
						{
							discount.setBatchName(academyenrollbatch
									.getEnrollmentName());
						}

						Level level = levelBiz.findLevelById(d.getLevelId());
						if (level != null) 
						{
							discount.setLevelName(level.getName());
						}

						Major major = majorBiz.findMajorById(d.getMajorId());
						if (major != null)
						{
							discount.setMajorName(major.getName());
						}
					}
					
					//优惠相关
					StudentDiscountPolicy studentDiscountPolicy=this.studentDiscountPolicyBiz.findStudentDiscountPolicyById(discount.getPolicyStandardId());
					String standardes="";
					if(discount.getPolicyStandardId()!=0 && studentDiscountPolicy!=null)
					{
						//费用科目
						if(this.feeSubjectBiz.findFeeSubjectById(studentDiscountPolicy.getFeeSubjectId())!=null)
						{
							discount.setFeeSubjectName(this.feeSubjectBiz.findFeeSubjectById(studentDiscountPolicy.getFeeSubjectId()).getName());
						}
						
						//优惠标准
						if(studentDiscountPolicy.getDiscountWayId()==Constants.MONEY_FORM_AMOUNT)
						{
							standardes+=ResourcesTool.getText("enrollment","money")+studentDiscountPolicy.getMoney()+ResourcesTool.getText("enrollment","moneyunit")+"<br/>";
						}
						else
						{
							standardes+=ResourcesTool.getText("enrollment","proportion")+studentDiscountPolicy.getMoney()+ResourcesTool.getText("enrollment","percent")+"<br/>";
						}
						if(studentDiscountPolicy.getMutable()==Constants.MONEY_FORM_MUTABLE)
						{
							//standardes+=ResourcesTool.getText("enrollment","mutable")+"<br/>";
						}
						else
						{
							if(studentDiscountPolicy.getDiscountWayId()==Constants.MONEY_FORM_AMOUNT)
							{
								standardes+=ResourcesTool.getText("enrollment","gradient")+studentDiscountPolicy.getDelta()+ResourcesTool.getText("enrollment","moneyunit")+"<br/>";
							}
							else
							{
								standardes+=ResourcesTool.getText("enrollment","gradient")+studentDiscountPolicy.getDelta()+ResourcesTool.getText("enrollment","percent")+"<br/>";
							}
						}
						discount.setDiscountstandard(standardes);
					}
					

					disAppList.add(discount);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return disAppList;
	}
	
	/*
	 * 根据学生Id和优惠标准Id查询优惠卷的数量
	 * 
	 * @see net.cedu.biz.enrollment.DiscountApplicationBiz#findDiscountApplicationByStudentIdAndDiscountPolicyId(int, int)
	 */
	public int findDiscountApplicationcountsByStudentIdAndDiscountPolicyId(int studentId,int discountPolicyId)throws Exception
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List list = new ArrayList();
		hqlparam += " and studentId= " + Constants.PLACEHOLDER;
		list.add(studentId);
		hqlparam += " and policyStandardId= " + Constants.PLACEHOLDER;
		list.add(discountPolicyId);
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		return discountApplicationDao.getCounts(p);
	}
	
	/*
	 * 根据学生Id和使用状态查询所有优惠卷
	 * 
	 * @see net.cedu.biz.enrollment.DiscountApplicationBiz#findDiscountApplicationByStudentId(int, int)
	 */
	public List<DiscountApplication> findDiscountApplicationByStudentId(int studentId,int usageFlag)throws Exception
	{
		List<DiscountApplication> discountApplicationList=null;
		if(studentId!=0)
		{
			PageParame p = new PageParame();
			String hqlparam = "";
			List list = new ArrayList();
			hqlparam += " and studentId= " + Constants.PLACEHOLDER;
			list.add(studentId);
			if(usageFlag!=Constants.POLICY_USING_STATUS_ALL)
			{
				hqlparam += " and usageFlag= " + Constants.PLACEHOLDER;
				list.add(usageFlag);
			}
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
			Long[] ids = discountApplicationDao.getIDs(p);
			if (ids != null && ids.length != 0) 
			{
				discountApplicationList = new ArrayList<DiscountApplication>();
				for (int i = 0; i < ids.length; i++)
				{
					DiscountApplication discountApplication=this.findDiscountApplicationById(Integer.valueOf(ids[i].toString()));
					StudentDiscountPolicy studentDiscountPolicy=this.studentDiscountPolicyBiz.findStudentDiscountPolicyById(discountApplication.getPolicyStandardId());
					String standardes="";
					if(discountApplication.getPolicyStandardId()!=0 && studentDiscountPolicy!=null)
					{
						//费用科目
						if(this.feeSubjectBiz.findFeeSubjectById(studentDiscountPolicy.getFeeSubjectId())!=null)
						{
							discountApplication.setFeeSubjectName(this.feeSubjectBiz.findFeeSubjectById(studentDiscountPolicy.getFeeSubjectId()).getName());
						}
						
						//优惠标准
						if(studentDiscountPolicy.getDiscountWayId()==Constants.MONEY_FORM_AMOUNT)
						{
							standardes+=ResourcesTool.getText("enrollment","money")+studentDiscountPolicy.getMoney()+ResourcesTool.getText("enrollment","moneyunit")+"<br/>";
						}
						else
						{
							standardes+=ResourcesTool.getText("enrollment","proportion")+studentDiscountPolicy.getMoney()+ResourcesTool.getText("enrollment","percent")+"<br/>";
						}
						if(studentDiscountPolicy.getMutable()==Constants.MONEY_FORM_MUTABLE)
						{
							//standardes+=ResourcesTool.getText("enrollment","mutable")+"<br/>";
						}
						else
						{
							if(studentDiscountPolicy.getDiscountWayId()==Constants.MONEY_FORM_AMOUNT)
							{
								standardes+=ResourcesTool.getText("enrollment","gradient")+studentDiscountPolicy.getDelta()+ResourcesTool.getText("enrollment","moneyunit")+"<br/>";
							}
							else
							{
								standardes+=ResourcesTool.getText("enrollment","gradient")+studentDiscountPolicy.getDelta()+ResourcesTool.getText("enrollment","percent")+"<br/>";
							}
						}
					}
					discountApplication.setDiscountstandard(standardes);
					
					discountApplicationList.add(discountApplication);
				}
			}
		}
		
		return discountApplicationList;
	}
	
	
	/*
	 * 根据优惠标准Id查询相关的优惠卷
	 * 
	 * @see net.cedu.biz.enrollment.DiscountApplicationBiz#findDiscountApplicationListByDiscountPolicyId(int)
	 */
	public List<DiscountApplication> findDiscountApplicationListByDiscountPolicyId(int discountPolicyId)throws Exception
	{
		String con=" and policyStandardId="+Constants.PLACEHOLDER;
		return this.discountApplicationDao.getByProperty(con, new Object[]{discountPolicyId});
	}
	
	/*
	 * 根据优惠标准Id和使用状态查询优惠卷的数量
	 * 
	 * @see net.cedu.biz.enrollment.DiscountApplicationBiz#findDaCountForHasUseByDpId(int, int)
	 */
	public int findDaCountForHasUseByDpId(int discountPolicyId,int status)throws Exception
	{
		String con=" and policyStandardId="+Constants.PLACEHOLDER+" and usageFlag="+Constants.PLACEHOLDER;
		return this.discountApplicationDao.findCountByProperty(con, new Object[]{discountPolicyId,status});
	}
	
	/*
	 * 根据学生Id、费用科目Id、缴费次数查询当前时间下该学生能够使用的优惠卷
	 * 
	 * @see net.cedu.biz.enrollment.DiscountApplicationBiz#findDiscountApplicationListByStudentIdFeeSubjectIdFeePaymentId(int, int, int)
	 */
	public List<DiscountApplication> findDiscountApplicationListByStudentIdFeeSubjectIdFeePaymentId(int studentId,int feeSubjectId,int feePaymentId)throws Exception
	{
		List<DiscountApplication> disAppList = new ArrayList<DiscountApplication>();
		try {
			// 查询优惠标准集合ID
			PageParame p = new PageParame();
			List list = null;
			String hqlConditionExpression = "";
			//if (feeSubjectId != 0 && feePaymentId!=0) 缴费重构修改
			if (feeSubjectId != 0) 
			{
				list = new ArrayList();
				
				// 费用科目Id
				if (feeSubjectId != 0) {
					hqlConditionExpression += "and feeSubjectId="
							+ Constants.PLACEHOLDER;
					list.add(feeSubjectId);
				}
				// 缴费次数
				if (feePaymentId != 0) {
					hqlConditionExpression += " and  feePaymentId= "
							+ Constants.PLACEHOLDER;
					list.add(feePaymentId);
				}
				
				if (!hqlConditionExpression.equals("")) {
					p.setHqlConditionExpression(hqlConditionExpression);
					p.setValues(list.toArray());
				}
			}
			String policyIds = ",";
			StringBuffer policyIdsSB = new StringBuffer(",");
			Long[] sidsLongs = studentDiscountPolicyDao.getIDs(p);
			if (sidsLongs == null) {
				return null;
			}
			for (Long id : sidsLongs) {
//				if (policyIds.startsWith(",")) {
//					policyIds = id + "";
//				} else {
//					policyIds += "," + id;
//				}
				if (policyIdsSB.toString().equals(",")){
					policyIdsSB = new StringBuffer(id+"");
				} else {
					policyIdsSB.append(","+id);
				}
			}
			policyIds = policyIdsSB.toString();

			List list2 = null;
			String hqlConditionExpression2 = "";
			PageParame p2 = new PageParame();
			list2 = new ArrayList();
			
			if (studentId !=0) 
			{
				hqlConditionExpression2 += " and studentId= "
						+ Constants.PLACEHOLDER ;
				list2.add(studentId);
			}
			
			if (!policyIds.startsWith(",")) {
				hqlConditionExpression2 += " and policyStandardId in ("
						+ Constants.PLACEHOLDER + ")";
				list2.add("$" + policyIds);
			}
			
			hqlConditionExpression2 += " and startTime<="
				+ Constants.PLACEHOLDER ;
			list2.add(DateUtil.getNowTimestamp("yyyy-MM-dd").toString().substring(0,10));
			
			hqlConditionExpression2 += " and endTime>="
				+ Constants.PLACEHOLDER ;
			list2.add(DateUtil.getNowTimestamp("yyyy-MM-dd").toString().substring(0,10));
			
			hqlConditionExpression2 += " and usageFlag="
				+ Constants.PLACEHOLDER ;
			list2.add(Constants.POLICY_USING_STATUS_FALSE);
			
			if (!hqlConditionExpression2.equals("")) {
				p2.setHqlConditionExpression(hqlConditionExpression2);
				p2.setValues(list2.toArray());
			}
			// Ids集合
			Long[] idsLongs = discountApplicationDao.getIDs(p2);
			if (idsLongs == null) {
				return null;
			}
			for (int i = 0; i < idsLongs.length; i++) {
				DiscountApplication discount = discountApplicationDao.findById(Integer
						.parseInt(idsLongs[i].toString()));
				if (discount != null) 
				{					
					//优惠相关
					StudentDiscountPolicy studentDiscountPolicy=this.studentDiscountPolicyBiz.findStudentDiscountPolicyById(discount.getPolicyStandardId());
					String standardes="";
					if(discount.getPolicyStandardId()!=0 && studentDiscountPolicy!=null)
					{
						//费用科目
						if(this.feeSubjectBiz.findFeeSubjectById(studentDiscountPolicy.getFeeSubjectId())!=null)
						{
							discount.setFeeSubjectName(this.feeSubjectBiz.findFeeSubjectById(studentDiscountPolicy.getFeeSubjectId()).getName());
						}
						
						//优惠标准
						if(studentDiscountPolicy.getDiscountWayId()==Constants.MONEY_FORM_AMOUNT)
						{
							standardes+=ResourcesTool.getText("enrollment","money")+studentDiscountPolicy.getMoney()+ResourcesTool.getText("enrollment","moneyunit")+"<br/>";
						}
						else
						{
							standardes+=ResourcesTool.getText("enrollment","proportion")+studentDiscountPolicy.getMoney()+ResourcesTool.getText("enrollment","percent")+"<br/>";
						}
						if(studentDiscountPolicy.getMutable()==Constants.MONEY_FORM_MUTABLE)
						{
							//standardes+=ResourcesTool.getText("enrollment","mutable")+"<br/>";
						}
						else
						{
							if(studentDiscountPolicy.getDiscountWayId()==Constants.MONEY_FORM_AMOUNT)
							{
								standardes+=ResourcesTool.getText("enrollment","gradient")+studentDiscountPolicy.getDelta()+ResourcesTool.getText("enrollment","moneyunit")+"<br/>";
							}
							else
							{
								standardes+=ResourcesTool.getText("enrollment","gradient")+studentDiscountPolicy.getDelta()+ResourcesTool.getText("enrollment","percent")+"<br/>";
							}
						}
						discount.setDiscountstandard(standardes);
					}
					

					disAppList.add(discount);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return disAppList;
	}
}
