package net.cedu.biz.book.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.book.BookBiz;
import net.cedu.biz.book.PurchaseRequisitionerDetailBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.common.Constants;
import net.cedu.dao.book.PurchaseRequisitionerDetailDao;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.book.Book;
import net.cedu.entity.book.PurchaseRequisitionerDetail;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.Major;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 中心申购单人员明细
 * @author YY
 *
 */
@Service
public class PurchaseRequisitionerDetailBizImpl implements PurchaseRequisitionerDetailBiz {
	
	@Autowired
	private PurchaseRequisitionerDetailDao purchaseRequisitionerDetailDao; //数据层接口
	@Autowired
	private StudentBiz studentBiz; //学生业务层
	@Autowired
	private AcademyBiz academyBiz; //院校业务层
	@Autowired
	private BookBiz bookBiz; //材料业务层
	@Autowired
	private  MajorBiz majorBiz; //专业
	@Autowired
	private LevelBiz LevelBiz; //院校层次
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz; //院校批次
	
	
	/**
	 * 分页方法（数量）
	 */
	public int findPurchaseRequisitionerDetailPageCountByConditions(
			PurchaseRequisitionerDetail purchaseRequisitionerDetail,
			PageResult<PurchaseRequisitionerDetail> pr) throws Exception {
		PageParame p = new PageParame(pr);
		List<Object> paramlist = new ArrayList<Object>();
		String hql = "";
		if (purchaseRequisitionerDetail != null) {
			//是否领用
			if(0<=purchaseRequisitionerDetail.getStatus())
			{
				hql+=" and status=" + Constants.PLACEHOLDER;
				paramlist.add(purchaseRequisitionerDetail.getStatus());
			}
			//批次
			if(0!=purchaseRequisitionerDetail.getBatchId())
			{
				hql+=" and batchId=" + Constants.PLACEHOLDER;
				paramlist.add(purchaseRequisitionerDetail.getBatchId());
			}
			//院校
			if(0!=purchaseRequisitionerDetail.getAcademyId())
			{
				Student stu=studentBiz.findAcademyIdByStudent(purchaseRequisitionerDetail.getAcademyId());
				if(null!=stu)
				{	
					if(0!=purchaseRequisitionerDetail.getStudentId())
					{
						purchaseRequisitionerDetail.setStudentId(stu.getId());
						hql+="and studentId="+Constants.PLACEHOLDER;
						paramlist.add(purchaseRequisitionerDetail.getStudentId());
					}
				}
			}
			//层次
			if(0!=purchaseRequisitionerDetail.getLevelId())
			{
				hql+=" and levelId=" + Constants.PLACEHOLDER;
				paramlist.add(purchaseRequisitionerDetail.getLevelId());
			}
			//专业
			if(0!=purchaseRequisitionerDetail.getMajorId())
			{
				hql+=" and majorId=" + Constants.PLACEHOLDER;
				paramlist.add(purchaseRequisitionerDetail.getMajorId());
			}
			//学生姓名
			if(StringUtils.isNotBlank(purchaseRequisitionerDetail.getStudentname()))
			{
				Long[] ids=studentBiz.findStudentByNames(purchaseRequisitionerDetail.getStudentname());
				 StringBuffer sb = new StringBuffer();
				if(null!=ids && ids.length>0)
				{
					
					 sb.append(",");
					 for(Long id :ids){
						if(sb.equals(",")){
							sb.replace(0, sb.length(), id+"");
						}else{
							sb.append(","+id);
						}
					 }
					 hql+="and studentId in ("+Constants.PLACEHOLDER+")";
					 paramlist.add("$"+sb.toString());	  
				}	
				 
			}
			//教材名称
			if(StringUtils.isNotBlank(purchaseRequisitionerDetail.getBookname()))
			{
				Long [] ids=bookBiz.findBookByLikeNames(purchaseRequisitionerDetail.getBookname());
				StringBuffer sb = new StringBuffer();
				if(null!=ids && ids.length>0)
				{
					 
					 sb.append(",");
					 for(Long id :ids){
						if(sb.equals(",")){
							sb.replace(0, sb.length(), id+"");
						}else{
							sb.append(","+id);
						}
					 }
					
				}	
				 hql+="and bookId in ("+Constants.PLACEHOLDER+")";
				 paramlist.add("$"+sb.toString());	  
			}
			//教材版次
			if(StringUtils.isNotBlank(purchaseRequisitionerDetail.getBookedition()))
			{
				Long [] ids=bookBiz.findBookByLikeeditions(purchaseRequisitionerDetail.getBookedition());
				 StringBuffer sb = new StringBuffer();
				if(null!=ids && ids.length>0)
				{
					
					 sb.append(",");
					 for(Long id :ids){
						if(sb.equals(",")){
							sb.replace(0, sb.length(), id+"");
						}else{
							sb.append(","+id);
						}
					 }
					 
				}	
				hql+="and bookId in ("+Constants.PLACEHOLDER+")";
				 paramlist.add("$"+sb.toString());	  
			}
			p.setHqlConditionExpression(hql);	
			p.setValues(paramlist.toArray());
		}
		int count=purchaseRequisitionerDetailDao.getCounts(p);
		
		return count;
	}
	/**
	 * 分页方法（集合）
	 */
	public List<PurchaseRequisitionerDetail> findPurchaseRequisitionerDetailPageListByConditions(
			PurchaseRequisitionerDetail purchaseRequisitionerDetail,
			PageResult<PurchaseRequisitionerDetail> pr) throws Exception {
		List<PurchaseRequisitionerDetail> books = new ArrayList<PurchaseRequisitionerDetail>();
		PageParame p = new PageParame(pr);
		List<Object> paramlist=new ArrayList<Object>();
		String hql="";
		if (purchaseRequisitionerDetail!= null) 
		{
			//是否领用
			if(0<=purchaseRequisitionerDetail.getStatus())
			{
				hql+=" and status=" + Constants.PLACEHOLDER;
				paramlist.add(purchaseRequisitionerDetail.getStatus());
			}
			//批次
			if(0!=purchaseRequisitionerDetail.getBatchId())
			{
				hql+=" and batchId=" + Constants.PLACEHOLDER;
				paramlist.add(purchaseRequisitionerDetail.getBatchId());
				
			}
			//院校
			if(0!=purchaseRequisitionerDetail.getAcademyId())
			{
				Student stu=studentBiz.findAcademyIdByStudent(purchaseRequisitionerDetail.getAcademyId());
				if(null!=stu)
				{	
					if(0!=purchaseRequisitionerDetail.getStudentId())
					{
						purchaseRequisitionerDetail.setStudentId(stu.getId());
						hql+="and studentId="+Constants.PLACEHOLDER;
						paramlist.add(purchaseRequisitionerDetail.getStudentId());
					}
				}
			}
			//层次
			if(0!=purchaseRequisitionerDetail.getLevelId())
			{
				hql+=" and levelId=" + Constants.PLACEHOLDER;
				paramlist.add(purchaseRequisitionerDetail.getLevelId());
			}
			//专业
			if(0!=purchaseRequisitionerDetail.getMajorId())
			{
				hql+=" and majorId=" + Constants.PLACEHOLDER;
				paramlist.add(purchaseRequisitionerDetail.getMajorId());
			}
			//学生姓名
			if(StringUtils.isNotBlank(purchaseRequisitionerDetail.getStudentname()))
			{
				Long[] ids=studentBiz.findStudentByNames(purchaseRequisitionerDetail.getStudentname());
				 StringBuffer sb = new StringBuffer();
				if(null!=ids && ids.length>0)
				{
					
					 sb.append(",");
					 for(Long id :ids){
						if(sb.equals(",")){
							sb.replace(0, sb.length(), id+"");
						}else{
							sb.append(","+id);
						}
					 }
					 hql+="and studentId in ("+Constants.PLACEHOLDER+")";
					 paramlist.add("$"+sb.toString());	 
				}	
				 
			}
			//教材名称
			if(StringUtils.isNotBlank(purchaseRequisitionerDetail.getBookname()))
			{
				Long [] ids=bookBiz.findBookByLikeNames(purchaseRequisitionerDetail.getBookname());
				StringBuffer sb = new StringBuffer();
				if(null!=ids && ids.length>0)
				{
					 
					 sb.append(",");
					 for(Long id :ids){
						if(sb.equals(",")){
							sb.replace(0, sb.length(), id+"");
						}else{
							sb.append(","+id);
						}
					 }
					
				}	
				 hql+="and bookId in ("+Constants.PLACEHOLDER+")";
				 paramlist.add("$"+sb.toString());	  
			}
			//教材版次
			if(StringUtils.isNotBlank(purchaseRequisitionerDetail.getBookedition()))
			{
				Long [] ids=bookBiz.findBookByLikeeditions(purchaseRequisitionerDetail.getBookedition());
				 StringBuffer sb = new StringBuffer();
				if(null!=ids && ids.length>0)
				{
					
					 sb.append(",");
					 for(Long id :ids){
						if(sb.equals(",")){
							sb.replace(0, sb.length(), id+"");
						}else{
							sb.append(","+id);
						}
					 }
				}	
				 hql+="and bookId in ("+Constants.PLACEHOLDER+")";
				 paramlist.add("$"+sb.toString());	  
			}
			p.setHqlConditionExpression(hql);
			p.setValues(paramlist.toArray());
		}

		Long[] bookIds = purchaseRequisitionerDetailDao.getIDs(p);
		if (bookIds!= null && bookIds.length != 0) {
			for (int i = 0; i < bookIds.length; i++) {
				PurchaseRequisitionerDetail p1 = purchaseRequisitionerDetailDao.findById(Integer.parseInt(bookIds[i]
						.toString()));
					//赋值		 
				if (p1 != null) {
					PurchaseRequisitionerDetail purchase=p1;
					//教材
					Book book=bookBiz.findBookById(purchase.getBookId());
					//专业
					Major baseMajor=majorBiz.findMajorById(purchase.getMajorId());
					//层次
					Level level=LevelBiz.findLevelById(purchase.getLevelId());
					//姓名
					Student student=studentBiz.findStudentById(purchase.getStudentId());
					//批次
					AcademyEnrollBatch enrollBatch=academyEnrollBatchBiz.findAcademyEnrollBatchById(purchase.getBatchId());
					if(null!=baseMajor)
					{
						purchase.setMajorname(baseMajor.getName());
					}
					if(null!=level)
					{
						purchase.setLevelname(level.getName());
					}
					if(null!=enrollBatch)
					{
						purchase.setBatchname(enrollBatch.getEnrollmentName());
					}
					if(null!=book)
					{
						purchase.setBookedition(book.getEdition());
						purchase.setBookname(book.getName());
						purchase.setBookprice(book.getPrice());
						purchase.setAvg(purchase.getBookprice()*purchase.getRequiredAmount());
					}
					if(null!=student)
					{
						purchase.setStudentname(student.getName());
						Academy academy=academyBiz.findAcademyById(student.getAcademyId());
						if(null!=academy)
						{
							purchase.setAcademyname(academy.getName());
						}
					}
					books.add(p1);
				}
			}
		}

		return books;
	}
	/**
	 * 修改方法
	 */
	public PurchaseRequisitionerDetail updateStatusByPurchaseRequisitionerDetail(
			PurchaseRequisitionerDetail purchaseRequisitionerDetail)throws Exception {
		
		return purchaseRequisitionerDetailDao.update(purchaseRequisitionerDetail);
	}
	
	/**
	 * 根据id查询领用单
	 */
	public PurchaseRequisitionerDetail findByid(int id) throws Exception {
		 
		return purchaseRequisitionerDetailDao.findById(id);
	}
	/**
	 * 增加中心预申购人员明细
	 */
	public void addPurchaseRequisitionerDetail(
			PurchaseRequisitionerDetail purchaseRequisitionerDetail) throws Exception {
		 
		  purchaseRequisitionerDetailDao.save(purchaseRequisitionerDetail);
	}
	/**
	 * 修改中心预申购人员明细
	 */
	public void updatePurchaseRequisitionerDetail(
			PurchaseRequisitionerDetail purchaseRequisitionerDetail)
			throws Exception {
		purchaseRequisitionerDetailDao.update(purchaseRequisitionerDetail);
		
	}
	/**
	 * 按照申购单明细编号查询申购单学生明细
	 */
	public List<PurchaseRequisitionerDetail> findStudentByPurchaseRequisitionerDetail(
			int purchaseRequisitionDetailId) throws Exception {
	 
		String sql="";
		List<PurchaseRequisitionerDetail> purchaseList=new ArrayList<PurchaseRequisitionerDetail>();
		List<Object> list=new ArrayList<Object>();
		
		if(purchaseRequisitionDetailId!=0)
		{
			sql+="and purchaseRequisitionId = "+Constants.PLACEHOLDER;
			list.add(purchaseRequisitionDetailId);
		}
		
		purchaseList=purchaseRequisitionerDetailDao.getByProperty(sql, list);
		return  purchaseList;
	}

}
