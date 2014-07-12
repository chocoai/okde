package net.cedu.biz.basesetting.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.common.Constants;
import net.cedu.common.string.StringUtil;
import net.cedu.dao.basesetting.EnrollmentSourceDao;
import net.cedu.dao.basesetting.FeeSubjectDao;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.FeePayment;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 招生途径
 * @author HXJ
 *
 */
@Service
public class EnrollmentSourceBizImpl implements EnrollmentSourceBiz{

	@Autowired
	private EnrollmentSourceDao enrollmentSourceDao;
	@Autowired
	private FeeSubjectDao feeSubjectDao;
	@Autowired
	private FeePaymentBiz feePaymentBiz;
	@Autowired
	private StudentBiz studentBiz;   
	
	/* 增加招生途径
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.EnrollmentSourceBiz#addEnrollmentSource(net.cedu.entity.basesetting.EnrollmentSource)
	 */
	public boolean addEnrollmentSource(EnrollmentSource enrollmentsource) throws Exception{
		if (0>=this.findTotalByProperty(enrollmentsource)) {
			enrollmentSourceDao.save(enrollmentsource);
			return true;
		}
		return false;
	}
	
	/* 修改招生途径
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.EnrollmentSourceBiz#modifyEnrollmentSource(net.cedu.entity.basesetting.EnrollmentSource)
	 */
	public boolean modifyEnrollmentSource(EnrollmentSource enrollmentsource)throws Exception{
		if (0>=findTotalByProperty(enrollmentsource)) {
			enrollmentSourceDao.update(enrollmentsource);
			return true;
		}
		return false;
	}
	
//	//按主键删除招生途径(物理删除)
//	public EnrollmentSource deleteEnrollmentSourceById(Serializable id){
//		return enrollmentSourceDao.deleteById(id);
//	}
//	
//	//按主键删除招生途径(逻辑删除)
//	public int deleteEnrollmentSourceByFlag(int id){
//		return enrollmentSourceDao.deleteByFlag(id);
//	}
	
	/*
	 * 查询所有招生途径
	 */
	public List<EnrollmentSource> findAllEnrollmentSources() throws Exception{
		return enrollmentSourceDao.findAll();
	}
	
	/* 查询所有招生途径(delete_flag=0)
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.EnrollmentSourceBiz#findAllEnrollmentSourceByDeleteFlag()
	 */
	public List<EnrollmentSource> findAllEnrollmentSourceByDeleteFlag() throws Exception{
		return enrollmentSourceDao.getByProperty("deleteFlag", Constants.DELETE_FALSE);
	}
	
	/* 按主键id查询招生途径
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.EnrollmentSourceBiz#findEnrollmentSourceById(java.io.Serializable)
	 */
	public EnrollmentSource findEnrollmentSourceById(Serializable id){
		return enrollmentSourceDao.findById(id);
	}
	
	/* 按是否合作方返款查询delete_flag=0的列表
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.EnrollmentSourceBiz#findAllEnrollmentSourceByIsNeedRebatesAndFlag(int)
	 */
	public List<EnrollmentSource> findAllEnrollmentSourceByIsNeedRebatesAndFlag(int isneedRebates) throws Exception{

		String sql=" and isneedRebates="+Constants.PLACEHOLDER+" and deleteFlag="+Constants.PLACEHOLDER;
		return enrollmentSourceDao.getByProperty(sql, new Object[]{isneedRebates,Constants.DELETE_FALSE});
	}
	
	/*查询数据库中是否有重复的数据
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.EnrollmentSourceBiz#findTotalByProperty(net.cedu.entity.basesetting.EnrollmentSource)
	 */
	@SuppressWarnings("unchecked")
	private int findTotalByProperty(EnrollmentSource enrollmentSource)throws Exception{
		String sql="";
		List list = new ArrayList();
		
		if(enrollmentSource.getId()>0){
			sql+=" and id <> "+Constants.PLACEHOLDER;
			list.add(enrollmentSource.getId());
		}
		if(StringUtils.isNotBlank(enrollmentSource.getName())){
			sql+=" and name ="+Constants.PLACEHOLDER;
			list.add(enrollmentSource.getName());
		}
		
		return enrollmentSourceDao.findCountByProperty(sql, list);
	}
	
	/*删除(读取配置文件)
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.EnrollmentSourceBiz#deleteConfigEnrollmentSource(int)
	 */
	public EnrollmentSource deleteConfigEnrollmentSource(int id){
		return enrollmentSourceDao.deleteConfig(id);
	}
	
	/* 把一组费用科目的字符串转成int数组
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.EnrollmentSourceBiz#changeStringIdsIntoIntArray()
	 */
	public Integer[] changeStringIdsIntoIntArray(int sourceid) throws Exception{
		EnrollmentSource enrollsource = new EnrollmentSource();
		String ids="";	
		//根据招生途径id 查询其对应的费用返款科目
		enrollsource = this.findEnrollmentSourceById(sourceid);
		if(enrollsource!=null&&(enrollsource.getSourceRebatesFeesubject()!=null&&!("")
				.equals(enrollsource.getSourceRebatesFeesubject())&&enrollsource
				.getSourceRebatesFeesubject().length()>=1)){
			ids = enrollsource.getSourceRebatesFeesubject().substring(1);
			return  StringUtil.strToIntegers("_",ids);
		}
		return null;
	}
	
	/* 根据每个招生途径的合作方返款科目ids查询其对应的返款科目中文名称
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.EnrollmentSourceBiz#findSubjectNamesBySubjectIds()
	 */
	@SuppressWarnings("unchecked")
	public List<EnrollmentSource> findSubjectNamesBySubjectIds(List<EnrollmentSource> enrollsourcelist) throws Exception{
		if(enrollsourcelist!=null&&enrollsourcelist.size()>0){
			String sql=null; //保存sql语句
			List list=null;
			String ids="";//循环保存每个招生途径对应的所有费用科目的id
			List<FeeSubject> subjectlist=null;//费用科目list
			StringBuffer sb= null;//保存处理后的多个费用名称
			for(int i=0,len=enrollsourcelist.size();i<len;i++){
				//判断Action传递过来的enrollsourcelist是否为空，是否有费用返款
				if(enrollsourcelist.get(i).getSourceRebatesFeesubject()!=null&&!("")
						.equals(enrollsourcelist.get(i).getSourceRebatesFeesubject())&&enrollsourcelist.get(i)
						.getSourceRebatesFeesubject().length()>=1){
					//循环保存每个招生途径对应的所有费用科目的id，去掉前后多余的_
					ids = enrollsourcelist.get(i).getSourceRebatesFeesubject()
							.substring(1,(enrollsourcelist.get(i).getSourceRebatesFeesubject().length()-1));
					ids=ids.replaceAll("_", ",");
					if (ids!=null&&!("").equals(ids)) {
						//查询对应id的费用科目集合
						sql="";
						list=new ArrayList();
						sql += " and id in (" + Constants.PLACEHOLDER + ")";
						list.add('$'+ids);
						subjectlist = new ArrayList<FeeSubject>();
						subjectlist = feeSubjectDao.getByProperty(sql, list);
					}
					sb = new StringBuffer();
					if (subjectlist!=null&&subjectlist.size()>0) {
						for (int j = 0, lenth = subjectlist.size(); j < lenth; j++) {
							if(subjectlist.get(j)!=null&&subjectlist.get(j).getName()!=null&&!("").equals(subjectlist.get(j).getName())){
								sb.append(subjectlist.get(j).getName());
								if (j!=subjectlist.size()-1) {
									sb.append(",");
								}
							}
						}
						if(sb.toString()!=null&&!("").equals(sb.toString())){
							//如果理后的多个费用名称不为空 将其赋值给对应的招生途径
							enrollsourcelist.get(i).setSubjectnames(sb.toString());
						}
					}
				}
			}
			
			return enrollsourcelist;
		}
		return null;
	}
	
	
	/*
	 * 学生来源统计
	 * @see net.cedu.biz.basesetting.EnrollmentSourceBiz#findEnrollmentSourceReport(net.cedu.entity.crm.Student)
	 */
	public List<EnrollmentSource> findEnrollmentSourceReport(Student student) throws Exception
	{
		 //获取学生来源信息
		 List<EnrollmentSource> eslst=enrollmentSourceDao.findAllNotDeleted();
		 if(null!=eslst && eslst.size()>0)
		 {
			
			 for(int i=0;i<eslst.size();i++)
			 {
				 student.setEnrollmentSource(eslst.get(i).getId());
				 //保存报名人数
				 student.setStatus(Constants.STU_CALL_STATUS_GENG_JIN_ZHONG);
				 eslst.get(i).setBaomingNum(studentBiz.countStudentByWayAndStatus(student));
				 
				 //保存录取人数
				 student.setStatus(Constants.STU_CALL_STATUS_YI_FU_HE);
				 eslst.get(i).setLuquNum(studentBiz.countStudentByWayAndStatus(student));
				 
				 //保存录取率
				 if( eslst.get(i).getBaomingNum()!=0)
				 {
					
					 eslst.get(i).setLuqulv((eslst.get(i).getLuquNum()/eslst.get(i).getBaomingNum())*100);
				 }else
				 {
					 eslst.get(i).setLuqulv(0);
				 }
				 
				 
				//保存缴费人数
				 student.setStatus(Constants.STU_CALL_STATUS_YI_DAO_MING);
				 eslst.get(i).setJiaofeiNum(studentBiz.countStudentByWayAndStatus(student));
				
				 //保存缴费率
				 if( eslst.get(i).getJiaofeiNum()!=0)
				 {
					
					 eslst.get(i).setJiaofeilv((eslst.get(i).getJiaofeiNum()/eslst.get(i).getLuquNum())*100);
				 }else
				 {
					 eslst.get(i).setJiaofeilv(0);
				 }
				 
				 
				 //保存缴费金额
				 student.setStatus(Constants.STU_CALL_STATUS_YI_DAO_MING);
				 List<Student> stulist=studentBiz.findStudentByWayAndStatus(student);
				 double money=0;
				 if(null!=stulist && stulist.size()>0)
				 {
					 for(int j=0;j<stulist.size();j++)
					 {
						//获取学生缴费单
						List<FeePayment> fplst= feePaymentBiz.findFeePaymentBySId(stulist.get(j).getId(), Constants.PAYMENT_STATUS_YI_KAI_DAN);
						if(null!=fplst && fplst.size()>0)
						{
							for(int k=0;k<fplst.size();k++)
							{
								//保存缴费金额
								money+=fplst.get(k).getAmountPaied();
							}
						
						}
					 }
				 }
				 eslst.get(i).setJiaofeiMoney(money);
			 }
		 }
			return eslst;
	}

	
	/*
	 * 查询所有招生途径(delete_flag=0)去除社招类型
	 * 
	 * @see net.cedu.biz.basesetting.EnrollmentSourceBiz#findEnrollmentSourceByDeleteFlagAndId()
	 */
	public List<EnrollmentSource> findEnrollmentSourceByDeleteFlagAndId() throws Exception
	{
		return enrollmentSourceDao.getByProperty(" and deleteFlag="+Constants.PLACEHOLDER+" and id<>"+Constants.PLACEHOLDER, new Object[]{Constants.DELETE_FALSE,Constants.WEB_STU_SOURCE_DEFAULT});
	}
	
	
	
	
	
	
	
	
}
