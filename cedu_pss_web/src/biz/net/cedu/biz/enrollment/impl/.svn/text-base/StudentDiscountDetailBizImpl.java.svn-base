package net.cedu.biz.enrollment.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.enrollment.ChannelBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.biz.enrollment.StudentDiscountDetailBiz;
import net.cedu.biz.enrollment.StudentDiscountPolicyBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.il8n.ResourcesTool;
import net.cedu.dao.enrollment.StudentDiscountDetailDao;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.StudentDiscountDetail;
import net.cedu.entity.enrollment.StudentDiscountPolicy;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 学生优惠政策
 * 
 * @author lixaojun
 * 
 */
@Service
public class StudentDiscountDetailBizImpl implements StudentDiscountDetailBiz
{
	
	@Autowired
	private StudentDiscountDetailDao studentDiscountDetailDao;
	
	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;//招生途径业务接口(合作方类型)
	
	@Autowired
	private ChannelBiz channelBiz;//合作方业务接口
	
	@Autowired
	private AcademyBiz academyBiz;//院校业务接口
	
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;//批次业务接口
	
	@Autowired
	private BranchBiz branchBiz;//学习中心业务接口
	
	@Autowired
	private LevelBiz levleBiz;//层次业务接口
	
	@Autowired
	private AcademyLevelBiz academyLevelBiz;//院校层次关系业务接口
	
	@Autowired
	private MajorBiz majorBiz;//专业业务接口
	
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;//费用科目业务接口
	
	@Autowired
	private StudentDiscountPolicyBiz studentDiscountPolicyBiz;//院校收费政策标准业务接口
	private StudentDiscountPolicy studentDiscountPolicy;//院校收费政策标准实体
	
	@Autowired
	private StudentBiz studentBiz;//学生业务接口
	
	/*
	 *  根据Id查找学生优惠政策
	 * 
	 * @see net.cedu.biz.enrollment.StudentDiscountDetailBiz#findStudentDiscountDetailById(int)
	 */
	public StudentDiscountDetail findStudentDiscountDetailById(int id)throws Exception 
	{
		return this.studentDiscountDetailDao.findById(id);
	}
	
	/*
	 * 根据Id查找学生优惠政策详细信息
	 * 
	 * @see net.cedu.biz.enrollment.StudentDiscountDetailBiz#findStudentDiscountDetailDetailsById(int)
	 */
	public StudentDiscountDetail findStudentDiscountDetailDetailsById(int id)throws Exception
	{
		StudentDiscountDetail obj=this.studentDiscountDetailDao.findById(id);
		if(obj!=null)
		{
			if(obj.getAcademyId()!=-1)
			{
				//合作方类别
				if(obj.getChannelType()!=0)
				{
					obj.setChanneltypename(this.enrollmentSourceBiz.findEnrollmentSourceById(obj.getChannelType()).getName());
				}
				//合作方
				if(obj.getChannelId()!=0)
				{
					if(obj.getChannelId()!=-1)
					{
						obj.setChannelname(this.channelBiz.findChannel(obj.getChannelId()).getName());
					}
					else
					{
						obj.setChannelname(ResourcesTool.getText("enrollment","#public_all"));
					}
				}
				//院校
				if(obj.getAcademyId()!=0)
				{
					if(obj.getAcademyId()!=-1)
					{
						obj.setAcademyname(this.academyBiz.findAcademyById(obj.getAcademyId()).getName());
					}
					else
					{
						obj.setAcademyname(ResourcesTool.getText("enrollment","#public_all"));
					}
				}
				//批次
				
				if(obj.getBatchId()!=0)
				{
					if(obj.getBatchId()!=-1)
					{
						obj.setBatchname(this.academyEnrollBatchBiz.findAcademyEnrollBatchById(obj.getBatchId()).getEnrollmentName());
					}
					else
					{
						obj.setBatchname(ResourcesTool.getText("enrollment","#public_all"));
					}
				}						
				
				//层次					
				if(obj.getLevelId()!=0)
				{
					if(obj.getLevelId()!=-1)
					{
						obj.setLevelname(this.levleBiz.findLevelById(obj.getLevelId()).getName());
					}
					else
					{
						obj.setLevelname(ResourcesTool.getText("enrollment","#public_all"));
					}
				}
				
				//专业
				if(obj.getMajorId()!=0)
				{
					if(obj.getMajorId()!=-1)
					{
						obj.setMajorname(this.majorBiz.findMajorById(obj.getMajorId()).getName());
					}
					else
					{
						obj.setMajorname(ResourcesTool.getText("enrollment","#public_all"));
					}
				}
			}
			//学习中心
			if(obj.getBranchId()!=0)
			{
				if(obj.getBranchId()!=-1)
				{
					obj.setBranchname(this.branchBiz.findBranchById(obj.getBranchId()).getName());
				}
				else
				{
					obj.setBranchname(ResourcesTool.getText("enrollment","#public_all"));
				}
			}
				
			//费用科目
			if(obj.getFeeSubjectId()!=0)
			{
				obj.setFeesubjectname(this.feeSubjectBiz.findFeeSubjectById(obj.getFeeSubjectId()).getName());
			}
		}
		return obj;
	}
	
	/*
	 * 添加学生优惠政策
	 * 
	 * @see net.cedu.biz.enrollment.StudentDiscountDetailBiz#addStudentDiscountDetail(net.cedu.entity.enrollment.StudentDiscountDetail)
	 */
	public boolean addStudentDiscountDetail(StudentDiscountDetail studentDiscountDetail) throws Exception 
	{
		if (studentDiscountDetail != null)
		{
			Object object = studentDiscountDetailDao.save(studentDiscountDetail);
			if (object != null) 
			{
				return true;
			}
		}
		
		return false;
	}
	
	/*
	 * 批量添加学生优惠政策
	 * @see net.cedu.biz.enrollment.StudentDiscountDetailBiz#addBatchStudentDiscountDetail(java.util.List)
	 */
	public boolean addBatchStudentDiscountDetail(List<StudentDiscountDetail> studentDiscountDetailList) throws Exception
	{
		boolean isfail=false;
		if(studentDiscountDetailList!=null && studentDiscountDetailList.size()>0)
		{
			for(StudentDiscountDetail sd:studentDiscountDetailList)
			{
				isfail=this.addStudentDiscountDetail(sd);
			}
		}	
		return isfail;
	}
	
	/*
	 * 删除学生优惠政策(读取配置文件)
	 * 
	 * @see net.cedu.biz.enrollment.StudentDiscountDetailBiz#deleteStudentDiscountDetailById(int)
	 */
	public boolean deleteConfigStudentDiscountDetailById(int id) throws Exception 
	{
		if (id != 0) {
			Object object = studentDiscountDetailDao.deleteConfig(id);
			if (object != null) {
				return true;
			}
		}
		
		return false;
	}

//	/*
//	 * 删除学生优惠政策(物理删除)
//	 * 
//	 * @see net.cedu.biz.enrollment.StudentDiscountDetailBiz#deleteStudentDiscountDetailById(int)
//	 */
//	public boolean deleteStudentDiscountDetailById(int id) throws Exception 
//	{
//		if (id != 0) {
//			Object object = studentDiscountDetailDao.deleteById(id);
//			if (object != null) {
//				return true;
//			}
//		}
//		
//		return false;
//	}
//
//	/*
//	 * 删除学生优惠政策(逻辑删除)
//	 * 
//	 * @see net.cedu.biz.enrollment.StudentDiscountDetailBiz#deleteStudentDiscountDetailFlag(int)
//	 */
//	public boolean deleteStudentDiscountDetailFlag(int id) throws Exception 
//	{
//		if (id != 0)
//		{
//			int num = studentDiscountDetailDao.deleteByFlag(id);
//			if (num>0) 
//			{
//				return true;
//			}
//		}	
//		return false;
//	}

	/*
	 * 修改学生优惠政策
	 * 
	 * @see net.cedu.biz.enrollment.StudentDiscountDetailBiz#modifyStudentDiscountDetail(net.cedu.entity.enrollment.StudentDiscountDetail)
	 */
	public boolean modifyStudentDiscountDetail(StudentDiscountDetail studentDiscountDetail) throws Exception 
	{
		if (studentDiscountDetail != null) 
		{
			Object object = studentDiscountDetailDao.update(studentDiscountDetail);
			if (object != null) 
			{
				return true;
			}
		}
		return false;
	}

	/*
	 * 批量修改学生优惠政策
	 * @see net.cedu.biz.enrollment.StudentDiscountDetailBiz#updateBatchStudentDiscountDetail(java.util.List)
	 */
	public boolean updateBatchStudentDiscountDetail(List<StudentDiscountDetail> studentDiscountDetailList) throws Exception
	{
		boolean isfail=false;
		if(studentDiscountDetailList!=null && studentDiscountDetailList.size()>0)
		{
			for(StudentDiscountDetail sd:studentDiscountDetailList)
			{
				isfail=this.modifyStudentDiscountDetail(sd);
			}
		}	
		return isfail;
	}
	
	/*
	 * 获取学生优惠政策数量
	 * 
	 * @see net.cedu.biz.enrollment.StudentDiscountDetailBiz#findStudentDiscountDetailCountByDetails(net.cedu.entity.enrollment.StudentDiscountDetail, net.cedu.model.page.PageResult)
	 */
	public int findStudentDiscountDetailCountByDetails(StudentDiscountDetail studentDiscountDetail,PageResult<StudentDiscountDetail> pr) throws Exception 
	{
		PageParame p = new PageParame();
		String params="";
		String canshu="";
		if(studentDiscountDetail.getChannelType()!=0)
		{
			params=" and channelType="+ Constants.PLACEHOLDER;
			canshu=studentDiscountDetail.getChannelType()+",";
		}
		if(studentDiscountDetail.getChannelId()!=0)
		{
			params+=" and ( channelId="+ Constants.PLACEHOLDER +" or channelId=-1 ) ";
			canshu+=studentDiscountDetail.getChannelId()+",";
		}
		if(studentDiscountDetail.getAcademyId()!=0)
		{
			params+=" and academyId="+ Constants.PLACEHOLDER;
			canshu+=studentDiscountDetail.getAcademyId()+",";
		}
		if(studentDiscountDetail.getBatchId()!=0)
		{
			params+=" and ( batchId="+ Constants.PLACEHOLDER+" or batchId=-1 ) ";
			canshu+=studentDiscountDetail.getBatchId()+",";
		}
		if(studentDiscountDetail.getBranchId()!=0)
		{
			params+=" and ( branchId="+ Constants.PLACEHOLDER+" or branchId=-1 ) ";
			canshu+=studentDiscountDetail.getBranchId()+",";
		}
		if(studentDiscountDetail.getLevelId()!=0)
		{
			params+=" and ( levelId="+ Constants.PLACEHOLDER+" or levelId=-1 ) ";	
			//转换为基础的层次ID
			studentDiscountDetail.setLevelId(this.academyLevelBiz.findById(studentDiscountDetail.getLevelId()).getLevelId());

			canshu+=studentDiscountDetail.getLevelId()+",";
		}
		if(studentDiscountDetail.getMajorId()!=0)
		{
			params+=" and ( majorId="+ Constants.PLACEHOLDER+" or majorId=-1 ) ";
			canshu+=studentDiscountDetail.getMajorId()+",";
		}
		if(studentDiscountDetail.getFeeSubjectId()!=0)
		{
			params+=" and feeSubjectId="+ Constants.PLACEHOLDER;
			canshu+=studentDiscountDetail.getFeeSubjectId()+",";
		}
		if(studentDiscountDetail.getFeeCountId()!=0)
		{
			params+=" and feeCountId="+ Constants.PLACEHOLDER;
			canshu+=studentDiscountDetail.getFeeCountId()+",";
		}
		if(studentDiscountDetail.getAduitStatus()!=3)
		{
			params+=" and aduitStatus="+ Constants.PLACEHOLDER;
			canshu+=studentDiscountDetail.getAduitStatus()+",";
		}
		
		params+=" and deleteFlag="+ Constants.PLACEHOLDER;
		canshu+=Constants.DELETE_FALSE;
		if(!params.equals(""))
		{
			p.setHqlConditionExpression(params);
			p.setValues(canshu.split(","));
		}
		return studentDiscountDetailDao.getCounts(p);
	}

	/*
	 * 获取学生优惠政策列表
	 * 
	 * @see net.cedu.biz.enrollment.StudentDiscountDetailBiz#findStudentDiscountDetailListByDetails(net.cedu.entity.enrollment.StudentDiscountDetail, net.cedu.model.page.PageResult)
	 */
	public List<StudentDiscountDetail> findStudentDiscountDetailListByDetails(StudentDiscountDetail studentDiscountDetail,PageResult<StudentDiscountDetail> pr) throws Exception
	{
		List<StudentDiscountDetail> policys = null;
		// Ids集合
		PageParame p = new PageParame(pr);
		
		String params="";
		String canshu="";
		if(studentDiscountDetail.getChannelType()!=0)
		{
			params=" and channelType="+ Constants.PLACEHOLDER;
			canshu=studentDiscountDetail.getChannelType()+",";
		}
		if(studentDiscountDetail.getChannelId()!=0)
		{
			params+=" and ( channelId="+ Constants.PLACEHOLDER +" or channelId=-1 ) ";
			canshu+=studentDiscountDetail.getChannelId()+",";
		}
		if(studentDiscountDetail.getAcademyId()!=0)
		{
			params+=" and academyId="+ Constants.PLACEHOLDER;
			canshu+=studentDiscountDetail.getAcademyId()+",";
		}
		if(studentDiscountDetail.getBatchId()!=0)
		{
			params+=" and ( batchId="+ Constants.PLACEHOLDER+" or batchId=-1 ) ";
			canshu+=studentDiscountDetail.getBatchId()+",";
		}
		if(studentDiscountDetail.getBranchId()!=0)
		{
			params+=" and ( branchId="+ Constants.PLACEHOLDER+" or branchId=-1 ) ";
			canshu+=studentDiscountDetail.getBranchId()+",";
		}
		if(studentDiscountDetail.getLevelId()!=0)
		{
			params+=" and ( levelId="+ Constants.PLACEHOLDER+" or levelId=-1 ) ";	
			//转换为基础的层次ID
			studentDiscountDetail.setLevelId(this.academyLevelBiz.findById(studentDiscountDetail.getLevelId()).getLevelId());

			canshu+=studentDiscountDetail.getLevelId()+",";
		}
		if(studentDiscountDetail.getMajorId()!=0)
		{
			params+=" and ( majorId="+ Constants.PLACEHOLDER+" or majorId=-1 ) ";
			canshu+=studentDiscountDetail.getMajorId()+",";
		}
		if(studentDiscountDetail.getFeeSubjectId()!=0)
		{
			params+=" and feeSubjectId="+ Constants.PLACEHOLDER;
			canshu+=studentDiscountDetail.getFeeSubjectId()+",";
		}
		if(studentDiscountDetail.getFeeCountId()!=0)
		{
			params+=" and feeCountId="+ Constants.PLACEHOLDER;
			canshu+=studentDiscountDetail.getFeeCountId()+",";
		}
		if(studentDiscountDetail.getAduitStatus()!=3)
		{
			params+=" and aduitStatus="+ Constants.PLACEHOLDER;
			canshu+=studentDiscountDetail.getAduitStatus()+",";
		}
		params+=" and deleteFlag="+ Constants.PLACEHOLDER;
		canshu+=0;
		if(!params.equals(""))
		{
			p.setHqlConditionExpression(params);
			p.setValues(canshu.split(","));
		}
		
		
		Long[] feeIds = studentDiscountDetailDao.getIDs(p);
		if (feeIds != null && feeIds.length != 0) {
			policys = new ArrayList<StudentDiscountDetail>();
			for (int i = 0; i < feeIds.length; i++) {
				// 内存获取
				StudentDiscountDetail policyObj = studentDiscountDetailDao.findById(Integer.valueOf(feeIds[i].toString()));
				if (policyObj != null) {
					StudentDiscountDetail obj = policyObj;
					if(obj.getAcademyId()!=-1)
					{
						//合作方类别
						if(obj.getChannelType()!=0)
						{
							obj.setChanneltypename(this.enrollmentSourceBiz.findEnrollmentSourceById(obj.getChannelType()).getName());
						}
						//合作方
						if(obj.getChannelId()!=0)
						{
							if(obj.getChannelId()!=-1)
							{
								obj.setChannelname(this.channelBiz.findChannel(obj.getChannelId()).getName());
							}
							else
							{
								obj.setChannelname(ResourcesTool.getText("enrollment","#public_all"));
							}
						}
						//院校
						if(obj.getAcademyId()!=0)
						{
							if(obj.getAcademyId()!=-1)
							{
								obj.setAcademyname(this.academyBiz.findAcademyById(obj.getAcademyId()).getName());
							}
							else
							{
								obj.setAcademyname(ResourcesTool.getText("enrollment","#public_all"));
							}
						}
						//批次
						
						if(obj.getBatchId()!=0)
						{
							if(obj.getBatchId()!=-1)
							{
								obj.setBatchname(this.academyEnrollBatchBiz.findAcademyEnrollBatchById(obj.getBatchId()).getEnrollmentName());
							}
							else
							{
								obj.setBatchname(ResourcesTool.getText("enrollment","#public_all"));
							}
						}						
						
						//层次					
						if(obj.getLevelId()!=0)
						{
							if(obj.getLevelId()!=-1)
							{
								obj.setLevelname(this.levleBiz.findLevelById(obj.getLevelId()).getName());
							}
							else
							{
								obj.setLevelname(ResourcesTool.getText("enrollment","#public_all"));
							}
						}
						
						//专业
						if(obj.getMajorId()!=0)
						{
							if(obj.getMajorId()!=-1)
							{
								obj.setMajorname(this.majorBiz.findMajorById(obj.getMajorId()).getName());
							}
							else
							{
								obj.setMajorname(ResourcesTool.getText("enrollment","#public_all"));
							}
						}
					}
					//学习中心
					if(obj.getBranchId()!=0)
					{
						if(obj.getBranchId()!=-1)
						{
							obj.setBranchname(this.branchBiz.findBranchById(obj.getBranchId()).getName());
						}
						else
						{
							obj.setBranchname(ResourcesTool.getText("enrollment","#public_all"));
						}
					}
						
					//费用科目
					if(obj.getFeeSubjectId()!=0)
					{
						obj.setFeesubjectname(this.feeSubjectBiz.findFeeSubjectById(obj.getFeeSubjectId()).getName());
					}
					
					//优惠标准
//					String standardes="";
					StringBuffer standardesSB=new StringBuffer("");
					if(obj.getDiscountPolicyId()!="" && obj.getDiscountPolicyId().length()>0)
					{			
						String[] ids=obj.getDiscountPolicyId().split(",");
						int index=1;
						for(int k=0;k<ids.length;k++)
						{
							studentDiscountPolicy=this.studentDiscountPolicyBiz.findStudentDiscountPolicyById(Integer.valueOf(ids[k]));						
							if(studentDiscountPolicy!=null)
							{
//								standardes+=index+ResourcesTool.getText("enrollment","tagtwo");
								standardesSB.append(index+ResourcesTool.getText("enrollment","tagtwo"));
								//standardes+=ResourcesTool.getText("enrollment","activitydate")+DateUtil.getDateStr(studentDiscountPolicy.getActivityBeginDate(), "yyyy-MM-dd")+ResourcesTool.getText("enrollment","tag")+DateUtil.getDateStr(studentDiscountPolicy.getActivityEndDate(), "yyyy-MM-dd")+"<br/>";
//								standardes+=ResourcesTool.getText("enrollment","usedate")+DateUtil.getDateStr(studentDiscountPolicy.getUseBeginDate(), "yyyy-MM-dd")+ResourcesTool.getText("enrollment","tag")+DateUtil.getDateStr(studentDiscountPolicy.getUseEndDate(), "yyyy-MM-dd")+"<br/>";
								standardesSB.append(ResourcesTool.getText("enrollment","usedate")+DateUtil.getDateStr(studentDiscountPolicy.getUseBeginDate(), "yyyy-MM-dd")+ResourcesTool.getText("enrollment","tag")+DateUtil.getDateStr(studentDiscountPolicy.getUseEndDate(), "yyyy-MM-dd")+"<br/>");
								if(studentDiscountPolicy.getDiscountWayId()==Constants.MONEY_FORM_AMOUNT)
								{
//									standardes+=ResourcesTool.getText("enrollment","money")+studentDiscountPolicy.getMoney()+ResourcesTool.getText("enrollment","moneyunit")+"<br/>";
									standardesSB.append(ResourcesTool.getText("enrollment","money")+studentDiscountPolicy.getMoney()+ResourcesTool.getText("enrollment","moneyunit")+"<br/>");
								}
								else
								{
//									standardes+=ResourcesTool.getText("enrollment","proportion")+studentDiscountPolicy.getMoney()+ResourcesTool.getText("enrollment","percent")+"<br/>";
									standardesSB.append(ResourcesTool.getText("enrollment","proportion")+studentDiscountPolicy.getMoney()+ResourcesTool.getText("enrollment","percent")+"<br/>");
								}
								if(studentDiscountPolicy.getMutable()==Constants.MONEY_FORM_MUTABLE)
								{
//									standardes+=ResourcesTool.getText("enrollment","mutable")+"<br/>";
									standardesSB.append(ResourcesTool.getText("enrollment","mutable")+"<br/>");
								}
								else
								{
									if(studentDiscountPolicy.getDiscountWayId()==Constants.MONEY_FORM_AMOUNT)
									{
//										standardes+=ResourcesTool.getText("enrollment","gradient")+studentDiscountPolicy.getDelta()+ResourcesTool.getText("enrollment","moneyunit")+"<br/>";
										standardesSB.append(ResourcesTool.getText("enrollment","gradient")+studentDiscountPolicy.getDelta()+ResourcesTool.getText("enrollment","moneyunit")+"<br/>");
									}
									else
									{
//										standardes+=ResourcesTool.getText("enrollment","gradient")+studentDiscountPolicy.getDelta()+ResourcesTool.getText("enrollment","percent")+"<br/>";
										standardesSB.append(ResourcesTool.getText("enrollment","gradient")+studentDiscountPolicy.getDelta()+ResourcesTool.getText("enrollment","percent")+"<br/>");
									}
								}
								index++;
							}
						}
					}
					obj.setDiscountstandard(standardesSB.toString());
					
					policys.add(obj);
				}
			}
		}

		return policys;

	}
	
	/*
	 * 按多个条件查询收费政策   
	 * @see net.cedu.biz.enrollment.StudentDiscountDetailBiz#getByProperty(int, int, int, int, int, int, int, int, int)
	 */
	public List<StudentDiscountDetail> getByProperty(int channelType,int channelId,int feeCountId,int academyId,int batchId,int branchId,int levelId,int majorId,int feesubjectId) throws Exception
	{
		String con=" and channelType="+Constants.PLACEHOLDER+" and channelId="+Constants.PLACEHOLDER+" and feeCountId="+Constants.PLACEHOLDER+" and academyId="+Constants.PLACEHOLDER+" and batchId="+Constants.PLACEHOLDER+" and branchId="+Constants.PLACEHOLDER+" and levelId="+Constants.PLACEHOLDER+" and majorId="+Constants.PLACEHOLDER+" and feeSubjectId="+Constants.PLACEHOLDER+" and deleteFlag="+Constants.PLACEHOLDER;
		return this.studentDiscountDetailDao.getByProperty(con, new Object[]{channelType,channelId,feeCountId,academyId,batchId,branchId,levelId,majorId,feesubjectId,Constants.DELETE_FALSE});
	}
	
	/*
	 * 按多个条件查询收费政策   (报名后政策)
	 * 
	 * @see net.cedu.biz.enrollment.StudentDiscountDetailBiz#getByProperty(int, int, int, int, int, int, int, int, int, int, int)
	 */
	public List<StudentDiscountDetail> getByProperty(int studentDataSource,int enrollmentWay,int channelType,int channelId,int feeCountId,int academyId,int batchId,int branchId,int levelId,int majorId,int feesubjectId) throws Exception
	{
		String con=" and studentDataSource="+Constants.PLACEHOLDER+" and enrollmentWay="+Constants.PLACEHOLDER+" and channelType="+Constants.PLACEHOLDER+" and channelId="+Constants.PLACEHOLDER+" and feeCountId="+Constants.PLACEHOLDER+" and academyId="+Constants.PLACEHOLDER+" and batchId="+Constants.PLACEHOLDER+" and branchId="+Constants.PLACEHOLDER+" and levelId="+Constants.PLACEHOLDER+" and majorId="+Constants.PLACEHOLDER+" and feeSubjectId="+Constants.PLACEHOLDER+" and deleteFlag="+Constants.PLACEHOLDER;
		return this.studentDiscountDetailDao.getByProperty(con, new Object[]{studentDataSource,enrollmentWay,channelType,channelId,feeCountId,academyId,batchId,branchId,levelId,majorId,feesubjectId,Constants.DELETE_FALSE});
	}
	
	/*
	 * 按多个条件查询收费政策   *报名前政策)
	 * @see net.cedu.biz.enrollment.StudentDiscountDetailBiz#getBeforeDiscountByProperty(int, int, int, int)
	 */
	public List<StudentDiscountDetail> getBeforeDiscountByProperty(int feeCountId,int academyId,int branchId,int feesubjectId) throws Exception
	{
		String con=" and feeCountId="+Constants.PLACEHOLDER+" and academyId="+Constants.PLACEHOLDER+" and branchId="+Constants.PLACEHOLDER+" and feeSubjectId="+Constants.PLACEHOLDER+" and deleteFlag="+Constants.PLACEHOLDER;
		return this.studentDiscountDetailDao.getByProperty(con, new Object[]{feeCountId,academyId,branchId,feesubjectId,Constants.DELETE_FALSE});
	}
	
	/*
	 * 批量修改院校收费政策
	 * 
	 * @see net.cedu.biz.enrollment.StudentDiscountDetailBiz#updateBatchStudentDiscountDetail(java.lang.String, java.lang.String)
	 */
	public boolean updateBatchStudentDiscountDetail(String discoutDetailIds,String discountPolicyIds) throws Exception
	{
		boolean rs=false;
		if(discoutDetailIds.length()>0)
		{
			String[] ids=discoutDetailIds.split(",");
			StudentDiscountDetail discountDetail=null;
			for(int i=0;i<ids.length;i++)
			{
				discountDetail=new StudentDiscountDetail();
				discountDetail=this.findStudentDiscountDetailById(Integer.valueOf(ids[i]));
				discountDetail.setDiscountPolicyId(discountPolicyIds);
				rs=this.modifyStudentDiscountDetail(discountDetail);
			}
			return rs;
		}
		return false;
	}
	
	/*
	 * 通过学生信息获取该学生优惠政策标准集合
	 * 
	 * @see net.cedu.biz.enrollment.StudentDiscountDetailBiz#findStudentDiscountPolicyListByStudentId(net.cedu.entity.crm.Student)
	 */
	public List<StudentDiscountPolicy> findStudentDiscountPolicyListByStudent(Student student) throws Exception
	{
		List<StudentDiscountPolicy> discountList = null;
		if(student!=null)
		{
			PageParame p = new PageParame();
			String hqlparam = "";
			List list = new ArrayList();
			//招生途径
			if (student.getEnrollmentSource()!=0) {
				hqlparam += " and channelType= " + Constants.PLACEHOLDER;
				list.add(student.getEnrollmentSource());
			}
			//合作方Id
			if (student.getChannelId()!=0) {
				hqlparam += " and ( channelId= " + Constants.PLACEHOLDER+" or channelId=-1 ) ";
				list.add(student.getChannelId());
			}
			//市场途径
			if (student.getEnrollmentWay()!=0) {
				hqlparam += " and ( enrollmentWay= " + Constants.PLACEHOLDER+" or enrollmentWay=-1 )";
				list.add(student.getEnrollmentWay());
			}
			//数据来源
			if (student.getStudentDataSource()!=0) {
				hqlparam += " and ( studentDataSource= " + Constants.PLACEHOLDER+" or studentDataSource=-1 ) ";
				list.add(student.getStudentDataSource());
			}
			//院校Id
			if (student.getAcademyId()!=0) {
				hqlparam += " and academyId= " + Constants.PLACEHOLDER;
				list.add(student.getAcademyId());
			}
			//招生批次Id
			if (student.getEnrollmentBatchId()!=0) {
				hqlparam += " and ( batchId= " + Constants.PLACEHOLDER+" or batchId=-1 ) ";
				list.add(student.getEnrollmentBatchId());
			}
			//学习中心Id
			if (student.getBranchId()!=0) {
				hqlparam += " and ( branchId= " + Constants.PLACEHOLDER+" or branchId=-1 ) ";
				list.add(student.getBranchId());
			}
			//层次Id
			if (student.getLevelId()!=0) {
				hqlparam += " and ( levelId= " + Constants.PLACEHOLDER+" or levelId=-1 ) ";
				list.add(student.getLevelId());
			}			
			//专业Id
			if (student.getMajorId()!=0) {
				hqlparam += " and ( majorId= " + Constants.PLACEHOLDER+" or majorId=-1 ) ";
				list.add(student.getMajorId());
			}
			//审核通过
			hqlparam += " and aduitStatus= " + Constants.PLACEHOLDER;
			list.add(Constants.AUDIT_STATUS_TRUE);
			//已启用
			hqlparam += " and isUsing= " + Constants.PLACEHOLDER;
			list.add(Constants.STATUS_ENABLED);
			
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
			Long[] ids = studentDiscountDetailDao.getIDs(p);
			if (ids != null && ids.length != 0) {
				discountList = new ArrayList<StudentDiscountPolicy>();
				for (int i = 0; i < ids.length; i++) {
					StudentDiscountDetail discountDetail = studentDiscountDetailDao
							.findById(Integer.valueOf(ids[i].toString()));
					if(discountDetail.getDiscountPolicyId()!=null && discountDetail.getDiscountPolicyId().length()>0)
					{
						String[] pids=discountDetail.getDiscountPolicyId().split(",");
						for(int k=0;k<pids.length;k++)
						{
							StudentDiscountPolicy discountPolicy = this.studentDiscountPolicyBiz.findStudentDiscountPolicyById(Integer.valueOf(pids[k]));
							discountList.add(discountPolicy);
						}
					}
				}
			}
		}
		return discountList;
	}
}
