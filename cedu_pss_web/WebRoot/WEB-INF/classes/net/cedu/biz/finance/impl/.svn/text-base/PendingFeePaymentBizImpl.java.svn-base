package net.cedu.biz.finance.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.enrollment.AcademyMajorBiz;
import net.cedu.biz.enrollment.FeeStandardBiz;
import net.cedu.biz.enrollment.PolicyFeeBiz;
import net.cedu.biz.finance.PendingFeePaymentBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.enums.FeeSubjectEnum;
import net.cedu.dao.finance.PendingFeePaymentDao;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.AcademyLevel;
import net.cedu.entity.enrollment.FeeStandard;
import net.cedu.entity.enrollment.Major;
import net.cedu.entity.finance.PendingFeePayment;
import net.cedu.model.page.PageParame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 待缴费单
 * 
 * @author lixiaojun
 * 
 */
@Service
public class PendingFeePaymentBizImpl implements PendingFeePaymentBiz
{
	
	@Autowired
	private PendingFeePaymentDao pendingFeePaymentDao;//待缴费单数据访问接口
	
	@Autowired
	private AcademyBiz academyBiz;//院校业务接口
	
	@Autowired 
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;//院校招生批次业务接口
	
	@Autowired
	private AcademyLevelBiz academyLevelBiz;//院校层次关系业务接口
	
	@Autowired
	private AcademyMajorBiz academyMajorBiz;//院校专业关系业务接口
	
	@Autowired 
	private FeeSubjectBiz feeSubjectBiz;//费用科目业务接口
	
	@Autowired 
	private PolicyFeeBiz policyFeeBiz;//收费政策标准业务接口
	
	@Autowired 
	private FeeStandardBiz feeStandardBiz;//收费政策标准明细业务接口
	
	@Autowired 
	private StudentBiz studentBiz;//学生业务接口
	
	@Autowired
	private BaseDictBiz BaseDictBiz;//基础表(查询学制)
	
	/*
	 * 添加待缴费单
	 * @see net.cedu.biz.finance.PendingFeePaymentBiz#addPendingFeePayment(net.cedu.entity.finance.PendingFeePayment)
	 */
	public boolean addPendingFeePayment(PendingFeePayment pendingFeePayment) throws Exception 
	{
		if (pendingFeePayment != null)
		{
			Object object = pendingFeePaymentDao.save(pendingFeePayment);
			if (object != null) 
			{
				return true;
			}
		}
		
		return false;
	}
	
	/*
	 * 删除待缴费单(读取配置文件)
	 * @see net.cedu.biz.finance.PendingFeePaymentBiz#deleteConfigPendingFeePaymentById(int)
	 */
	public boolean deleteConfigPendingFeePaymentById(int id) throws Exception 
	{
		if (id != 0) {
			Object object = pendingFeePaymentDao.deleteConfig(id);
			if (object != null) {
				return true;
			}
		}
		
		return false;
	}
	
	/*
	 * 批量删除待缴费单(慎用，真删)
	 * 
	 * @see net.cedu.biz.finance.PendingFeePaymentBiz#deleteBatchPendingFeePayment(java.util.List)
	 */
	public void deleteBatchPendingFeePayment(List<PendingFeePayment> pendingFeePaymentList) throws Exception 
	{
		if (pendingFeePaymentList!=null && pendingFeePaymentList.size()>0)
		{
			for(PendingFeePayment pfp:pendingFeePaymentList)
			{
				if(pfp!=null && pfp.getId()>0)
				{
					pendingFeePaymentDao.deleteById(pfp.getId());
				}
			}
		}
		
	}
	
	/*
	 * 根据学生Id批量删除待缴费单(慎用，真删)
	 * 
	 * @see net.cedu.biz.finance.PendingFeePaymentBiz#deletePendingFeePaymentByStudentId(int)
	 */
	public boolean deletePendingFeePaymentByStudentId(int studentId) throws Exception 
	{
		boolean isback=false;
		if(studentId>0)
		{
			int count=pendingFeePaymentDao.deleteByProperty("and studentId="+Constants.PLACEHOLDER,  new Object[]{studentId});
			//if(count>0)
			//{
				isback=true;
			//}
		}
		return isback;
		
	}
	
	/*
	 * 修改待缴费单
	 * @see net.cedu.biz.finance.PendingFeePaymentBiz#updatePendingFeePayment(net.cedu.entity.finance.PendingFeePayment)
	 */
	public boolean updatePendingFeePayment(PendingFeePayment pendingFeePayment) throws Exception 
	{
		if (pendingFeePayment != null) 
		{
			Object object = pendingFeePaymentDao.update(pendingFeePayment);
			if (object != null) 
			{
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 根据Id查找待缴费单
	 * @see net.cedu.biz.finance.PendingFeePaymentBiz#findPendingFeePaymentById(int)
	 */
	public PendingFeePayment findPendingFeePaymentById(int id) throws Exception 
	{
		return this.pendingFeePaymentDao.findById(id);
	}
	
	/*
	 * 通过学生Id查找待缴费单列表
	 * @see net.cedu.biz.finance.PendingFeePaymentBiz#findPendingFeePaymentListByStudentId(int)
	 */
	public List<PendingFeePayment> findPendingFeePaymentListByStudentId(int studentId) throws Exception 
	{
		String con=" and studentId="+Constants.PLACEHOLDER;
		return this.pendingFeePaymentDao.getByProperty(con, new Object[]{studentId});
	}
	
	/*
	 * 通过学生Id和收费政策明细Id查询待缴费单数量
	 * @see net.cedu.biz.finance.PendingFeePaymentBiz#findPendingFeePaymentListByStudentIdFeeStandardId(int, int)
	 */
	public int findPendingFeePaymentListByStudentIdFeeStandardId(int studentId,int feeStandardId) throws Exception
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List list = new ArrayList();
		hqlparam += " and studentId= " + Constants.PLACEHOLDER;
		list.add(studentId);
		hqlparam += " and feeStandardId= " + Constants.PLACEHOLDER;
		list.add(feeStandardId);
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		return pendingFeePaymentDao.getCounts(p);
	}
	
	/*
	 * 批量添加所有生成的待缴费单(添加)
	 * @see net.cedu.biz.finance.PendingFeePaymentBiz#addBatchPendingFeePayment(java.util.List)
	 */
	public boolean addBatchPendingFeePayment(List<PendingFeePayment> list) throws Exception
	{
		boolean isfail=false;
		for(PendingFeePayment pend:list)
		{
			int count=this.findPendingFeePaymentListByStudentIdFeeStandardId(pend.getStudentId(),pend.getFeeStandardId());
			if(count>0)
			{
				continue;
			}
			else
			{
				isfail=this.addPendingFeePayment(pend);
			}
		}
		return isfail;
	}
	
	/*
	 * 通过院校Id和费用科目Id生成所有待缴费单列表(不添加,为添加方法服务)
	 * @see net.cedu.biz.finance.PendingFeePaymentBiz#findAddPendingFeePaymentByAcademyIdFeeSubjectId(int, int)
	 */
	public List<PendingFeePayment> findAddPendingFeePaymentByAcademyIdFeeSubjectId(int academyId,int feeSubjectId) throws Exception
	{
		List<PendingFeePayment> pendingList=new ArrayList<PendingFeePayment>();
		
		List<Academy> academyList=new ArrayList<Academy>();
		if(academyId==-1)
		{
			academyList=this.academyBiz.findAllAcademyByFlagFalse();
		}
		else if(this.academyBiz.findAcademyById(academyId)!=null)
		{
			academyList.add(this.academyBiz.findAcademyById(academyId));
		}
		if(academyList!=null && academyList.size()>0)
		{
			for(Academy academy:academyList)
			{
				List<AcademyEnrollBatch> batchList=this.academyEnrollBatchBiz.findBatchNotInFinishedByAcademyId(academy.getId());
				if(batchList!=null && batchList.size()>0)
				{
					for(AcademyEnrollBatch batch:batchList)
					{
						List<AcademyLevel> levelList=this.academyLevelBiz.findBatchId(batch.getId());
						if(levelList!=null && levelList.size()>0)
						{
							for(AcademyLevel academyLevel:levelList)
							{
								List<Major> majorList=this.academyMajorBiz.findMajorListByLevelId(academyLevel.getId());
								if(majorList!=null && majorList.size()>0)
								{
									for(Major major:majorList)
									{
										Student student=new Student();
										student.setAcademyId(academy.getId());
										student.setBatchId(batch.getId());
										student.setLevelId(academyLevel.getLevelId());
										student.setMajorId(major.getId());
										student.setStartStatusId(Constants.STU_CALL_STATUS_GENG_JIN_ZHONG);
										List<Student> studentList=this.studentBiz.findStudentsByParam(student);
										if(studentList!=null && studentList.size()>0)
										{
											for(Student stud:studentList)
											{
												List<FeeSubject> feeSubjectList=new ArrayList<FeeSubject>();
												if(feeSubjectId==-1)
												{
													feeSubjectList=this.feeSubjectBiz.findAllFeeSubjectByDeleteFlag();
												}
												else if(this.feeSubjectBiz.findFeeSubjectById(feeSubjectId)!=null)
												{
													feeSubjectList.add(this.feeSubjectBiz.findFeeSubjectById(feeSubjectId));
												}
												if(feeSubjectList!=null && feeSubjectList.size()>0)
												{
													for(FeeSubject feeSubject:feeSubjectList)
													{
														List<FeeStandard> feeStandardList=this.feeStandardBiz.findFeeStandardListsByStudentIdFeeSubjectId(stud.getId(), feeSubject.getId());
														if(feeStandardList!=null && feeStandardList.size()>0)
														{
															for(FeeStandard feeStandard:feeStandardList)
															{
																int count=this.findPendingFeePaymentListByStudentIdFeeStandardId(stud.getId(),feeStandard.getId());
																if(count>0)
																{
																	continue;
																}
																else
																{
																	PendingFeePayment pending=new PendingFeePayment();
																	pending.setAmountPaid(new BigDecimal(0));
																	pending.setAmountSurplus(new BigDecimal(feeStandard.getChargingCeil()));
																	//pending.setCode(buildCodeBiz.getCodes(CodeEnum.PendingFeePaymentTB.getCode(),CodeEnum.PendingFeePaymentPrefix.getCode()))
																	pending.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
																	//pending.setCreatorId(creatorId)
																	pending.setDeleteFlag(Constants.DELETE_FALSE);
																	pending.setFeeStandardId(feeStandard.getId());
																	pending.setStatus(Constants.PENDING_STATUS_UNPAID);
																	pending.setStudentId(stud.getId());
																	pending.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
																	pending.setPolicyFeeDetailId(feeStandard.getPolicyFeeDetail());
																	//pending.setUpdaterId(updaterId)
																	
																	pendingList.add(pending);
																}
															}
														}
														else
														{
															continue;
														}
													}
												}
												else
												{
													continue;
												}
											}
										}
										else
										{
											continue;
										}
									}
								}
								else
								{
									continue;
								}
							}
						}
						else
						{
							continue;
						}
					}
				}
				else
				{
					continue;
				}
			}
		}
		return pendingList;
	}
	
	/*
	 * 通过院校Id和费用科目Id生成所有待缴费单列表(不添加,为添加方法服务)_定时任务用
	 * @see net.cedu.biz.finance.PendingFeePaymentBiz#findAddPendingFeePaymentByAcademyIdFeeSubjectId(int, int)
	 */
	public List<PendingFeePayment> findAddPendingFeePaymentByAcademyIdFeeSubjectIdFortask() throws Exception
	{
		System.out.println(new Date());
		List<PendingFeePayment> pendingList=new ArrayList<PendingFeePayment>();
		
		List<Academy> academyList=new ArrayList<Academy>();
		List<AcademyEnrollBatch> batchLists=new ArrayList<AcademyEnrollBatch>();
		List<AcademyLevel> levelLists=new ArrayList<AcademyLevel>();
		List<Major> majorLists=new ArrayList<Major>();
		List<Student> studentLists=new ArrayList<Student>();
		List<FeeStandard> feeStandardLists=new ArrayList<FeeStandard>();
		
		List<FeeSubject> feeSubjectList=this.feeSubjectBiz.findAllFeeSubjectByDeleteFlag();
		
		//获取院校列表
		academyList=this.academyBiz.findAllAcademyByFlagFalse();
		
		//获取院校<b>招生/学籍</b>批次
		if(academyList!=null && academyList.size()>0)
		{
			for(Academy academy:academyList)
			{
				List<AcademyEnrollBatch> batchList=this.academyEnrollBatchBiz.findBatchNotInFinishedByAcademyId(academy.getId());
				if(batchList!=null && batchList.size()>0)
				{
					for(AcademyEnrollBatch batch:batchList)
					{
						batchLists.add(batch);
					}
				}
				else continue;
			}
		}
		if(batchLists!=null && batchLists.size()>0)
		{
			for(AcademyEnrollBatch batch:batchLists)
			{
				List<AcademyLevel> levelList=this.academyLevelBiz.findBatchId(batch.getId());
				if(levelList!=null && levelList.size()>0)
				{
					for(AcademyLevel level:levelList)
					{
						levelLists.add(level);
					}					
				}
				else continue;
			}
		}
		if(levelLists!=null && levelLists.size()>0)
		{
			for(AcademyLevel academyLevel:levelLists)
			{
				List<Major> majorList=this.academyMajorBiz.findMajorListByLevelId(academyLevel.getId());
				if(majorList!=null && majorList.size()>0)
				{
					for(Major major:majorList)
					{
						majorLists.add(major);
					}						
				}
				else continue;
			}
		}
		if(majorLists!=null && majorLists.size()>0)
		{
			for(Major major:majorLists)
			{
				for(AcademyLevel academyLevel:levelLists)
				{
					for(AcademyEnrollBatch batch:batchLists)
					{
						Student student=new Student();
						student=getStudentForSearch(student,major.getAcademyId(),batch,academyLevel,major);
						List<Student> studentList=this.studentBiz.findStudentsByParam(student);
						if(studentList!=null && studentList.size()>0)
						{
							for(Student stud:studentList)
							{
								studentLists.add(stud);
							}							
						}						
					}
				}
			}
		}
		
		if(studentLists!=null && studentLists.size()>0)
		{
			for(Student stud:studentLists)
			{
				if(feeSubjectList!=null && feeSubjectList.size()>0)
				{
					for(FeeSubject feeSubject:feeSubjectList)
					{
						List<FeeStandard> feeStandardList=this.feeStandardBiz.findFeeStandardListsByStudentIdFeeSubjectId(stud.getId(), feeSubject.getId());
						if(feeStandardList!=null && feeStandardList.size()>0)
						{
							for(FeeStandard feeStandard:feeStandardList)
							{
								feeStandardLists.add(feeStandard);
							}	
							
						}						
					}
				}
				else continue;
			}
		}
		pendingList=getPendingList(feeStandardLists,studentLists);	
		System.out.println(new Date());
		return pendingList;
	}
	
	//获取待缴费单
	private List<PendingFeePayment> getPendingList(List<FeeStandard> feeStandardLists,List<Student> studentLists)throws Exception
	{
		List<PendingFeePayment> pendingList=new ArrayList<PendingFeePayment>();
		if(feeStandardLists!=null && feeStandardLists.size()>0)
		{
			for(FeeStandard feeStandard:feeStandardLists)
			{
				for(Student stud:studentLists)
				{
					int count=this.findPendingFeePaymentListByStudentIdFeeStandardId(stud.getId(),feeStandard.getId());
					if(count>0) continue;
					else
					{
						PendingFeePayment pending=new PendingFeePayment();
						pending=getPendingForSearch(pending,feeStandard,stud);
						pendingList.add(pending);
					}
				}
			}
		}		
		return pendingList;
	}
	
	//初始化学生信息
	private Student getStudentForSearch(Student student,int academyId,AcademyEnrollBatch batch,AcademyLevel academyLevel,Major major)
	{
		student.setAcademyId(academyId);
		student.setBatchId(batch.getId());
		student.setLevelId(academyLevel.getLevelId());
		student.setMajorId(major.getId());
		student.setStartStatusId(Constants.STU_CALL_STATUS_GENG_JIN_ZHONG);
		return student;
	}
	
	private PendingFeePayment getPendingForSearch(PendingFeePayment pending,FeeStandard feeStandard,Student stud)
	{
		pending.setAmountPaid(new BigDecimal(0));
		pending.setAmountSurplus(new BigDecimal(feeStandard.getChargingCeil()));
		//pending.setCode(buildCodeBiz.getCodes(CodeEnum.PendingFeePaymentTB.getCode(),CodeEnum.PendingFeePaymentPrefix.getCode()))
		pending.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
		//pending.setCreatorId(creatorId)
		pending.setDeleteFlag(Constants.DELETE_FALSE);
		pending.setFeeStandardId(feeStandard.getId());
		pending.setStatus(Constants.PENDING_STATUS_UNPAID);
		pending.setStudentId(stud.getId());
		pending.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
		pending.setPolicyFeeDetailId(feeStandard.getPolicyFeeDetail());
		return pending;
	}
	
	/*
	 * 通过学生Id和费用科目Id查找该学生需要缴费的待缴费单列表
	 * @see net.cedu.biz.finance.PendingFeePaymentBiz#findPendingFeePaymentListByStudentIdFeeSubjectId(int, int)
	 */
	public List<PendingFeePayment> findPendingFeePaymentListByStudentIdFeeSubjectId(int studentId,int feeSubjectId) throws Exception
	{
		List<PendingFeePayment> pendinglist=null;
		if(studentId!=0 && feeSubjectId!=0)
		{
			PageParame p = new PageParame();
			String hqlparam = "";
			List list = new ArrayList();		
			//学生Id
			if (studentId!=0) 
			{
				hqlparam += " and studentId= " + Constants.PLACEHOLDER;
				list.add(studentId);
			}		
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
			Long[] ids = pendingFeePaymentDao.getIDs(p);
			if (ids != null && ids.length != 0)
			{
				pendinglist=new ArrayList<PendingFeePayment>();
				for (int i = 0; i < ids.length; i++) 
				{
					PendingFeePayment pending=this.pendingFeePaymentDao.findById(Integer.valueOf(ids[i].toString()));
					if(pending!=null && pending.getStatus()!=Constants.PENDING_STATUS_PAID && pending.getFeeStandardId()!=0 && this.feeStandardBiz.findFeeStandardById(pending.getFeeStandardId())!=null)
					{
						//报名费和测试费分别单独查出来
						if(feeSubjectId!=FeeSubjectEnum.OtherFee.value())
						{
							if(this.policyFeeBiz.findPolicyFeeById(this.feeStandardBiz.findFeeStandardById(pending.getFeeStandardId()).getPolicyFeeDetailId())!=null && this.policyFeeBiz.findPolicyFeeById(this.feeStandardBiz.findFeeStandardById(pending.getFeeStandardId()).getPolicyFeeDetailId()).getFeeSubjectId()==feeSubjectId)
							{
								if(this.feeSubjectBiz.findFeeSubjectById(this.policyFeeBiz.findPolicyFeeById(this.feeStandardBiz.findFeeStandardById(pending.getFeeStandardId()).getPolicyFeeDetailId()).getFeeSubjectId())!=null)
								{
									pending.setFeeSubjectName(this.feeSubjectBiz.findFeeSubjectById(this.policyFeeBiz.findPolicyFeeById(this.feeStandardBiz.findFeeStandardById(pending.getFeeStandardId()).getPolicyFeeDetailId()).getFeeSubjectId()).getName());
									
								}
								if(this.BaseDictBiz.findBaseDictById(this.policyFeeBiz.findPolicyFeeById(this.feeStandardBiz.findFeeStandardById(pending.getFeeStandardId()).getPolicyFeeDetailId()).getModeId())!=null)
								{
									pending.setModeName(this.BaseDictBiz.findBaseDictById(this.policyFeeBiz.findPolicyFeeById(this.feeStandardBiz.findFeeStandardById(pending.getFeeStandardId()).getPolicyFeeDetailId()).getModeId()).getName());
								}
								pendinglist.add(pending);
							}
						}
						else   //非招生阶段要缴的费用一起查出来
						{
							if(this.policyFeeBiz.findPolicyFeeById(this.feeStandardBiz.findFeeStandardById(pending.getFeeStandardId()).getPolicyFeeDetailId())!=null && this.feeSubjectBiz.findFeeSubjectById(this.policyFeeBiz.findPolicyFeeById(this.feeStandardBiz.findFeeStandardById(pending.getFeeStandardId()).getPolicyFeeDetailId()).getFeeSubjectId())!=null && this.feeSubjectBiz.findFeeSubjectById(this.policyFeeBiz.findPolicyFeeById(this.feeStandardBiz.findFeeStandardById(pending.getFeeStandardId()).getPolicyFeeDetailId()).getFeeSubjectId()).getBatchType()==Constants.BATCH_TYPE_NON_ENROLL)
							{
								if(this.feeSubjectBiz.findFeeSubjectById(this.policyFeeBiz.findPolicyFeeById(this.feeStandardBiz.findFeeStandardById(pending.getFeeStandardId()).getPolicyFeeDetailId()).getFeeSubjectId())!=null)
								{
									pending.setFeeSubjectName(this.feeSubjectBiz.findFeeSubjectById(this.policyFeeBiz.findPolicyFeeById(this.feeStandardBiz.findFeeStandardById(pending.getFeeStandardId()).getPolicyFeeDetailId()).getFeeSubjectId()).getName());
									
								}
								if(this.BaseDictBiz.findBaseDictById(this.policyFeeBiz.findPolicyFeeById(this.feeStandardBiz.findFeeStandardById(pending.getFeeStandardId()).getPolicyFeeDetailId()).getModeId())!=null)
								{
									pending.setModeName(this.BaseDictBiz.findBaseDictById(this.policyFeeBiz.findPolicyFeeById(this.feeStandardBiz.findFeeStandardById(pending.getFeeStandardId()).getPolicyFeeDetailId()).getModeId()).getName());
								}
								pendinglist.add(pending);
							}
						}
					}
				}
			}
		}
		return pendinglist;
	}
	
	
	

	
	/*
	 * 通过院校Id和费用科目Id生成所有待缴费单列表(不添加,为添加方法服务)(重构之后的方法)
	 * @see net.cedu.biz.finance.PendingFeePaymentBiz#generatePendingFeePaymentListByAcademyIdFeeSubjectId(int, int)
	 */
	public List<PendingFeePayment> generatePendingFeePaymentListByAcademyIdFeeSubjectId(int academyId,int feeSubjectId) throws Exception
	{
		List<PendingFeePayment> pendingList=new ArrayList<PendingFeePayment>();
		
		List<Academy> academyList=new ArrayList<Academy>();
		if(academyId==-1)
		{
			academyList=this.academyBiz.findAllAcademyByFlagFalse();
		}
		else if(this.academyBiz.findAcademyById(academyId)!=null)
		{
			academyList.add(this.academyBiz.findAcademyById(academyId));
		}
		if(academyList!=null && academyList.size()>0)
		{
			for(Academy academy:academyList)
			{
				Student student=new Student();
				student.setAcademyId(academy.getId());
				student.setStartStatusId(Constants.STU_CALL_STATUS_GENG_JIN_ZHONG);
				student.setEndStatusId(Constants.STU_CALL_STATUS_YI_BI_YI);
				List<Student> studentList=this.studentBiz.findStudentsByParam(student);
				if(studentList!=null && studentList.size()>0)
				{
					for(Student stud:studentList)
					{
						List<FeeSubject> feeSubjectList=new ArrayList<FeeSubject>();
						if(feeSubjectId==-1)
						{
							feeSubjectList=this.feeSubjectBiz.findAllFeeSubjectByDeleteFlag();
						}
						else if(this.feeSubjectBiz.findFeeSubjectById(feeSubjectId)!=null)
						{
							feeSubjectList.add(this.feeSubjectBiz.findFeeSubjectById(feeSubjectId));
						}
						if(feeSubjectList!=null && feeSubjectList.size()>0)
						{
							for(FeeSubject feeSubject:feeSubjectList)
							{
								List<FeeStandard> feeStandardList=this.feeStandardBiz.findFeeStandardListReconstructionByStudentIdFeeSubjectId(stud.getId(), feeSubject.getId());
								if(feeStandardList!=null && feeStandardList.size()>0)
								{
									for(FeeStandard feeStandard:feeStandardList)
									{
										int count=this.findPendingFeePaymentListByStudentIdFeeStandardId(stud.getId(),feeStandard.getId());
										if(count>0)
										{
											continue;
										}
										else
										{
											PendingFeePayment pending=new PendingFeePayment();
											pending.setAmountPaid(new BigDecimal(0));
											pending.setAmountSurplus(new BigDecimal(feeStandard.getChargingFloor()));
											//pending.setCode(buildCodeBiz.getCodes(CodeEnum.PendingFeePaymentTB.getCode(),CodeEnum.PendingFeePaymentPrefix.getCode()))
											pending.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
											//pending.setCreatorId(creatorId)
											pending.setDeleteFlag(Constants.DELETE_FALSE);
											pending.setFeeStandardId(feeStandard.getId());
											pending.setStatus(Constants.PENDING_STATUS_UNPAID);
											pending.setStudentId(stud.getId());
											pending.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
											pending.setPolicyFeeDetailId(feeStandard.getPolicyFeeDetail());
											//pending.setUpdaterId(updaterId)
											//新加字段
											pending.setFeeBatchId(feeStandard.getFeeBatchId());
											pending.setFeeSubjectId(feeSubject.getId());
											pending.setMoneyToPay(new BigDecimal(feeStandard.getChargingFloor()));
											pending.setChargingCeil(new BigDecimal(feeStandard.getChargingCeil()));
											pending.setStartTime(feeStandard.getStartTime());
											pending.setEndTime(feeStandard.getEndTime());
											
											pendingList.add(pending);
										}
									}
								}
								else
								{
									continue;
								}
							}
						}
						else
						{
							continue;
						}
					}
				}
				else
				{
					continue;
				}
			}
		}
		return pendingList;
	}
	
	
	/*
	 * 通过学生Id和费用科目Id查找该学生需要缴费的待缴费单列表(重构之后的方法)
	 * @see net.cedu.biz.finance.PendingFeePaymentBiz#findStudentsPendingFeePaymentListByStudentIdFeeSubjectId(int, int)
	 */
	public List<PendingFeePayment> findStudentsPendingFeePaymentListByStudentIdFeeSubjectId(int studentId,int feeSubjectId) throws Exception
	{
		List<PendingFeePayment> pendinglist=null;
		if(studentId!=0 && feeSubjectId!=0)
		{
			PageParame p = new PageParame();
			String hqlparam = "";
			List list = new ArrayList();		
			//学生Id
			if (studentId!=0) 
			{
				hqlparam += " and studentId= " + Constants.PLACEHOLDER;
				list.add(studentId);
			}		
			//费用科目Id
			if (feeSubjectId!=0) 
			{
				hqlparam += " and feeSubjectId= " + Constants.PLACEHOLDER;
				list.add(feeSubjectId);
			}	
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
			Long[] ids = pendingFeePaymentDao.getIDs(p);
			if (ids != null && ids.length != 0)
			{
				pendinglist=new ArrayList<PendingFeePayment>();
				for (int i = 0; i < ids.length; i++) 
				{
					PendingFeePayment pending=this.pendingFeePaymentDao.findById(Integer.valueOf(ids[i].toString()));
					if(pending!=null && pending.getStatus()!=Constants.PENDING_STATUS_PAID)
					{
						if(this.feeSubjectBiz.findFeeSubjectById(pending.getFeeSubjectId())!=null)
						{
							pending.setFeeSubjectName(this.feeSubjectBiz.findFeeSubjectById(pending.getFeeSubjectId()).getName());
						}
						else
						{
							pending.setFeeSubjectName("");
						}
						pendinglist.add(pending);
					}
				}
			}
		}
		return pendinglist;
	}
	
	/*
	 * 通过学生Id和费用科目Id生成该学生所有待缴费单列表(不添加,为添加方法服务)(重构之后的优化方法)
	 * @see net.cedu.biz.finance.PendingFeePaymentBiz#generatePendingFeePaymentListByStudentIdFeeSubjectId(int, int)
	 */
	public List<PendingFeePayment> generatePendingFeePaymentListByStudentIdFeeSubjectId(int studentId,int feeSubjectId) throws Exception
	{
		List<PendingFeePayment> pendingList=new ArrayList<PendingFeePayment>();
		if(studentId!=0)
		{
			List<FeeSubject> feeSubjectList=new ArrayList<FeeSubject>();
			if(feeSubjectId==-1)
			{
				feeSubjectList=this.feeSubjectBiz.findAllFeeSubjectByDeleteFlag();
			}
			else if(this.feeSubjectBiz.findFeeSubjectById(feeSubjectId)!=null)
			{
				feeSubjectList.add(this.feeSubjectBiz.findFeeSubjectById(feeSubjectId));
			}
			if(feeSubjectList!=null && feeSubjectList.size()>0)
			{
				for(FeeSubject feeSubject:feeSubjectList)
				{
					List<FeeStandard> feeStandardList=this.feeStandardBiz.findFeeStandardListReconstructionByStudentIdFeeSubjectId(studentId, feeSubject.getId());
					if(feeStandardList!=null && feeStandardList.size()>0)
					{
						for(FeeStandard feeStandard:feeStandardList)
						{
							int count=this.findPendingFeePaymentListByStudentIdFeeStandardId(studentId,feeStandard.getId());
							if(count>0)
							{
								continue;
							}
							else
							{
								PendingFeePayment pending=new PendingFeePayment();
								pending.setAmountPaid(new BigDecimal(0));
								pending.setAmountSurplus(new BigDecimal(feeStandard.getChargingFloor()));
								//pending.setCode(buildCodeBiz.getCodes(CodeEnum.PendingFeePaymentTB.getCode(),CodeEnum.PendingFeePaymentPrefix.getCode()))
								pending.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
								//pending.setCreatorId(creatorId)
								pending.setDeleteFlag(Constants.DELETE_FALSE);
								pending.setFeeStandardId(feeStandard.getId());
								pending.setStatus(Constants.PENDING_STATUS_UNPAID);
								pending.setStudentId(studentId);
								pending.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
								pending.setPolicyFeeDetailId(feeStandard.getPolicyFeeDetail());
								//pending.setUpdaterId(updaterId)
								//新加字段
								pending.setFeeBatchId(feeStandard.getFeeBatchId());
								pending.setFeeSubjectId(feeSubject.getId());
								pending.setMoneyToPay(new BigDecimal(feeStandard.getChargingFloor()));
								pending.setChargingCeil(new BigDecimal(feeStandard.getChargingCeil()));
								pending.setStartTime(feeStandard.getStartTime());
								pending.setEndTime(feeStandard.getEndTime());
								
								pendingList.add(pending);
							}
						}
					}
					else
					{
						continue;
					}
				}
			}
		}
		return pendingList;
	}
	
}
