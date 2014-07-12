package net.cedu.biz.enrollment.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.FeeStandardBiz;
import net.cedu.biz.enrollment.PolicyFeeDetailBiz;
import net.cedu.common.Constants;
import net.cedu.dao.enrollment.FeeStandardDao;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.FeeStandard;
import net.cedu.entity.enrollment.PolicyFee;
import net.cedu.common.date.DateUtil;
import net.cedu.common.enums.FeeSubjectEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 收费标准
 * 
 * @author lixaojun
 * 
 */
@Service
public class FeeStandardBizImpl implements FeeStandardBiz
{
	
	@Autowired
	private FeeStandardDao feeStandardDao;//收费标准dao
	
	@Autowired
	private PolicyFeeDetailBiz policyFeeDetailBiz;//收费政策业务接口
	
	@Autowired
	private StudentBiz studenBiz;//学生业务接口
	
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;//费用科目业务接口
	
	
	/**
	 * 根据Id查找收费标准
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public FeeStandard findFeeStandardById(int id)throws Exception
	{
		return this.feeStandardDao.findById(id);
	}
	
	/**
	 * 添加收费标准
	 * @param policyFee
	 * @return
	 * @throws Exception
	 */
	public boolean addFeeStandard(FeeStandard feeStandard) throws Exception
	{
		if (feeStandard != null) 
		{
			Object object = feeStandardDao.save(feeStandard);
			if (object != null)
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 删除收费标准(配置文件)
	 * @param 
	 */
	public boolean deleteConfigFeeStandardById(int id) throws Exception
	{
		if (id != 0) 
		{
			Object object = feeStandardDao.deleteConfig(id);
			if (object != null) 
			{
				return true;
			}
		}
		
		return false;
	}
	
//	/**
//	 * 删除收费标准(物理删除)
//	 * @param 
//	 */
//	public boolean deleteFeeStandardById(int id) throws Exception
//	{
//		if (id != 0) 
//		{
//			Object object = feeStandardDao.deleteConfig(id);
//			if (object != null) 
//			{
//				return true;
//			}
//		}
//		
//		return false;
//	}
//	
//	/**
//	 * 删除收费标准(逻辑删除)
//	 * @param 
//	 */
//	public boolean deleteFeeStandardFlag(int id) throws Exception
//	{
//		if (id != 0) 
//		{
//			int num = feeStandardDao.deleteByFlag(id);
//			if (num>0) 
//			{
//				return true;
//			}
//		}
//		
//		return false;
//	}
	
	/**
	 * 修改收费标准
	 * @param feeBatch
	 */
	public boolean modifyFeeStandard(FeeStandard feeStandard) throws Exception
	{
		if (feeStandard != null) 
		{
			Object object = feeStandardDao.update(feeStandard);
			if (object != null) 
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 根据收费政策标准Id查找收费标准规则
	 * @param id 收费政策明细Id
	 * @return
	 * @throws Exception
	 */
	public List<FeeStandard> findFeeStandardListByFeeId(int id) throws Exception
	{
		String con=" and policyFeeDetailId="+Constants.PLACEHOLDER;
		return this.feeStandardDao.getByProperty(con, new Object[]{id});
	}
	
	/*
	 * 根据学生Id查询报名费收费标准明细
	 * 
	 * @see net.cedu.biz.enrollment.FeeStandardBiz#findRegistrationFeeStandardByStudentId(int)
	 */
	public FeeStandard findRegistrationFeeStandardByStudentId(int studentId) throws Exception
	{
		FeeStandard feeStandard=null;
		Student student=this.studenBiz.findStudentById(studentId);
		if(student!=null)
		{
			List<PolicyFee> plist=this.policyFeeDetailBiz.findPolicyFeeByStudent(student,FeeSubjectEnum.RegistrationFee.value());
			if(plist!=null && plist.size()>0)
			{
				List<FeeStandard> feeStandardlist=this.findFeeStandardListByFeeId(plist.get(0).getId());
				if(feeStandardlist!=null && feeStandardlist.size()>0)
				{
					feeStandard=feeStandardlist.get(0);
					if(DateUtil.compareDate(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss").toString(),feeStandard.getStartTime().toString()) && DateUtil.compareDate(feeStandard.getEndTime().toString(),DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss").toString()))
					{
						if(this.feeSubjectBiz.findFeeSubjectById(plist.get(0).getFeeSubjectId())!=null)
						{
							feeStandard.setFeeSubjectName(this.feeSubjectBiz.findFeeSubjectById(plist.get(0).getFeeSubjectId()).getName());
						}
					}
					else
					{
						return null;
					}
				}
			}
		}		
		return feeStandard;
	}
	
	/*
	 * 根据学生Id查询测试费收费标准明细
	 * 
	 * @see net.cedu.biz.enrollment.FeeStandardBiz#findTestFeeStandardByStudentId(int)
	 */
	public FeeStandard findTestFeeStandardByStudentId(int studentId) throws Exception
	{
		FeeStandard feeStandard=null;
		Student student=this.studenBiz.findStudentById(studentId);
		if(student!=null)
		{
			List<PolicyFee> plist=this.policyFeeDetailBiz.findPolicyFeeByStudent(student,FeeSubjectEnum.TestFee.value());
			if(plist!=null && plist.size()>0)
			{
				List<FeeStandard> feeStandardlist=this.findFeeStandardListByFeeId(plist.get(0).getId());
				if(feeStandardlist!=null && feeStandardlist.size()>0)
				{
					feeStandard=feeStandardlist.get(0);
					if(DateUtil.compareDate(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss").toString(),feeStandard.getStartTime().toString()) && DateUtil.compareDate(feeStandard.getEndTime().toString(),DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss").toString()))
					{
						if(this.feeSubjectBiz.findFeeSubjectById(plist.get(0).getFeeSubjectId())!=null)
						{
							feeStandard.setFeeSubjectName(this.feeSubjectBiz.findFeeSubjectById(plist.get(0).getFeeSubjectId()).getName());
						}
					}
					else
					{
						return null;
					}
				}
			}
		}		
		return feeStandard;
	}
	
	/*
	 * 根据学生Id查询所有非招生阶段收费标准明细(除去测试费和报名费)
	 * 
	 * @see net.cedu.biz.enrollment.FeeStandardBiz#findOtherFeeStandardByStudentId(int)
	 */
	public List<FeeStandard> findOtherFeeStandardByStudentId(int studentId) throws Exception
	{
		List<FeeStandard> feeStandardList=null;
		Student student=this.studenBiz.findStudentById(studentId);
		if(student!=null)
		{
			List<PolicyFee> plist=this.policyFeeDetailBiz.findPolicyFeeByStudent(student,FeeSubjectEnum.OtherFee.value());
			if(plist!=null && plist.size()>0)
			{
				feeStandardList=new ArrayList<FeeStandard>();
				for(PolicyFee policyFee:plist)
				{
					List<FeeStandard> feeStandardlist=this.findFeeStandardListByFeeId(policyFee.getId());
					if(feeStandardlist!=null && feeStandardlist.size()>0)
					{
						for(FeeStandard feeStandard:feeStandardlist)
						{
							if(this.feeSubjectBiz.findFeeSubjectById(policyFee.getFeeSubjectId())!=null && this.feeSubjectBiz.findFeeSubjectById(policyFee.getFeeSubjectId()).getBatchType()==Constants.BATCH_TYPE_NON_ENROLL)
							{
								if(DateUtil.compareDate(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss").toString(),feeStandard.getStartTime().toString()) && DateUtil.compareDate(feeStandard.getEndTime().toString(),DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss").toString()))
								{
									if(this.feeSubjectBiz.findFeeSubjectById(policyFee.getFeeSubjectId())!=null)
									{
										feeStandard.setFeeSubjectName(this.feeSubjectBiz.findFeeSubjectById(plist.get(0).getFeeSubjectId()).getName());
										feeStandardList.add(feeStandard);
									}
								}
							}
						}
					}
				}
				if(feeStandardList!=null && feeStandardList.size()==0)
				{
					return null;
				}
			}
		}
		return feeStandardList;
	}
	
	/*
	 * 根据学生Id和费用科目Id查询所有收费标准明细
	 * @see net.cedu.biz.enrollment.FeeStandardBiz#findFeeStandardListsByStudentIdFeeSubjectId(int, int)
	 */
	public List<FeeStandard> findFeeStandardListsByStudentIdFeeSubjectId(int studentId,int feeSubjectId) throws Exception
	{
		List<FeeStandard> feeStandardList=null;
		Student student=this.studenBiz.findStudentById(studentId);
		if(student!=null)
		{
			//费用科目类型分为:招生阶段和非招生阶段
			//					招生阶段用学生的招生批次去匹配政策的招生批次   
			//					非招生阶段用学生的学籍批次去匹配政策的招生批次
			if(this.feeSubjectBiz.findFeeSubjectById(feeSubjectId)!=null && this.feeSubjectBiz.findFeeSubjectById(feeSubjectId).getBatchType()==Constants.BATCH_TYPE_NON_ENROLL)
			{
				if(student.getBatchId()!=0)//如果学籍批次为0则还是用招生批次去匹配（非招生阶段用学生的学籍批次去匹配政策的招生批次）
				{
					student.setEnrollmentBatchId(student.getBatchId());
				}
			}
			List<PolicyFee> plist=this.policyFeeDetailBiz.findPolicyFeeByStudent(student,feeSubjectId);
			if(plist!=null && plist.size()>0)
			{
				feeStandardList=new ArrayList<FeeStandard>();
				for(PolicyFee policyFee:plist)
				{
					List<FeeStandard> feeStandardlist=this.findFeeStandardListByFeeId(policyFee.getId());
					if(feeStandardlist!=null && feeStandardlist.size()>0)
					{
						for(FeeStandard feeStandard:feeStandardlist)
						{		
							if(DateUtil.compareDate(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss").toString(),feeStandard.getStartTime().toString()))
							{
								if(this.feeSubjectBiz.findFeeSubjectById(policyFee.getFeeSubjectId())!=null)
								{
									feeStandard.setPolicyFeeDetail(policyFee.getPolicyFeeDetail());
									feeStandard.setFeeSubjectName(this.feeSubjectBiz.findFeeSubjectById(plist.get(0).getFeeSubjectId()).getName());
									feeStandardList.add(feeStandard);
								}
							}							
						}
					}
				}
				if(feeStandardList!=null && feeStandardList.size()==0)
				{
					return null;
				}
			}
		}
		return feeStandardList;
	}
	
	/*
	 * 根据学生Id和费用科目Id查询所有收费标准明细（重构之后的方法）Reconstruction
	 * @see net.cedu.biz.enrollment.FeeStandardBiz#findFeeStandardListsByStudentIdFeeSubjectId(int, int)
	 */
	public List<FeeStandard> findFeeStandardListReconstructionByStudentIdFeeSubjectId(int studentId,int feeSubjectId) throws Exception
	{
		List<FeeStandard> feeStandardList=null;
		Student student=this.studenBiz.findStudentById(studentId);
		if(student!=null)
		{
			//费用科目类型分为:招生阶段和非招生阶段
			//					招生阶段用学生的招生批次去匹配政策的招生批次   
			//					非招生阶段用学生的学籍批次去匹配政策的招生批次
			if(this.feeSubjectBiz.findFeeSubjectById(feeSubjectId)!=null && this.feeSubjectBiz.findFeeSubjectById(feeSubjectId).getBatchType()==Constants.BATCH_TYPE_NON_ENROLL)
			{
				if(student.getBatchId()!=0)//如果学籍批次为0则还是用招生批次去匹配（非招生阶段用学生的学籍批次去匹配政策的招生批次）
				{
					student.setEnrollmentBatchId(student.getBatchId());
				}
			} 
			List<PolicyFee> plist=this.policyFeeDetailBiz.findPolicyFeeByStudent(student,feeSubjectId);
			if(plist!=null && plist.size()>0)
			{
				feeStandardList=new ArrayList<FeeStandard>();
				for(PolicyFee policyFee:plist)
				{
					List<FeeStandard> feeStandardlist=this.findFeeStandardListByFeeId(policyFee.getId());
					if(feeStandardlist!=null && feeStandardlist.size()>0)
					{
						for(FeeStandard feeStandard:feeStandardlist)
						{		
							if(this.feeSubjectBiz.findFeeSubjectById(policyFee.getFeeSubjectId())!=null)
							{
								feeStandard.setPolicyFeeDetail(policyFee.getPolicyFeeDetail());
								feeStandard.setFeeSubjectName(this.feeSubjectBiz.findFeeSubjectById(plist.get(0).getFeeSubjectId()).getName());
								feeStandardList.add(feeStandard);
							}						
						}
					}
				}
				if(feeStandardList!=null && feeStandardList.size()==0)
				{
					return null;
				}
			}
		}
		return feeStandardList;
	}
	
}
