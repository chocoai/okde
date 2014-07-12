package net.cedu.biz.finance.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.finance.StudentAccountManagementBiz;
import net.cedu.common.Constants;
import net.cedu.dao.finance.StudentAccountManagementDao;
import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.StudentAccountManagement;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 学生账户管理业务逻辑实现
 * 
 * @author gaole
 *
 */

@Service
public class StudentAccountManagementBizImpl implements StudentAccountManagementBiz {
	
	@Autowired
	private StudentAccountManagementDao studentaccountmanagementdao;   //学生账户接口
	
	@Autowired
	private StudentBiz studentBiz;                                     //学生接口

	/*
	 * 新增学生账户
	 * @see net.cedu.biz.finance.StudentAccountManagementBiz#addStudentAccountManagement(net.cedu.entity.finance.StudentAccountManagement)
	 */
	public boolean addStudentAccountManagement(
			StudentAccountManagement studentaccountmanagement) throws Exception {
		studentaccountmanagementdao.save(studentaccountmanagement);
		return true;
	}

	
	
	/*
	 * 修改学生账户
	 * @see net.cedu.biz.finance.StudentAccountManagementBiz#updateStudentAccountManagementById(net.cedu.entity.finance.StudentAccountManagement)
	 */
	public boolean updateStudentAccountManagementById(
			StudentAccountManagement studentaccountmanagement) throws Exception {
		studentaccountmanagementdao.update(studentaccountmanagement);
		return true;
	}
	
	

	/*
	 * 根据账户Id查询学生账户
	 * @see net.cedu.biz.finance.StudentAccountManagementBiz#findStudentAccountManagementById(int)
	 */
	public StudentAccountManagement findStudentAccountManagementById(int id)
			throws Exception {
		
		return studentaccountmanagementdao.findById(id);
	}

	/*
	 * 查询学生账户(数量)
	 * @see net.cedu.biz.finance.StudentAccountManagementBiz#countStudentAccountManagementByParams(int, java.lang.String, java.lang.String, net.cedu.model.page.PageResult)
	 */
	public int countStudentAccountManagementByParams(String code,
			String studentName, String cardNo,
			PageResult<StudentAccountManagement> pr) throws Exception {
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if(!studentName.trim().equals(""))
		{
			Student student=studentBiz.findStudentsByNameOrCertNo(studentName,"");
			if(student!=null)
			{
				hqlparam+=" and studentId="+ Constants.PLACEHOLDER;
				list.add(student.getId());
			}
			else
			{
				hqlparam+=" and studentId="+ Constants.PLACEHOLDER;
				list.add(0);
			}
		}
		if(!cardNo.trim().equals(""))
		{
			if(studentName.trim().equals(""))
			{
				Student student=studentBiz.findStudentsByNameOrCertNo("",cardNo);
				if(student!=null)
				{
					hqlparam+=" and studentId="+ Constants.PLACEHOLDER;
					list.add(student.getId());
				}
				else
				{
					hqlparam+=" and studentId="+ Constants.PLACEHOLDER;
					list.add(0);
				}
			}
		}
		if (!code.trim().equals("")) {
			hqlparam += " and  code= " + Constants.PLACEHOLDER;
			list.add(code);
		}
		hqlparam+=" and deleteFlag=";
		list.add(Constants.DELETE_FALSE);
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		return studentaccountmanagementdao.getCounts(p);
	}
	
	/*
	 * 查询学生账户(集合)
	 * @see net.cedu.biz.finance.StudentAccountManagementBiz#findStudentAccountManagementByParams(int, java.lang.String, java.lang.String, net.cedu.model.page.PageResult)
	 */
	public List<StudentAccountManagement> findStudentAccountManagementByParams(String code,
			String studentName, String cardNo,
			PageResult<StudentAccountManagement> pr) throws Exception {
		List<StudentAccountManagement> studentaccountmanagementlst=null;
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if(!studentName.trim().equals(""))
		{
			Student student=studentBiz.findStudentsByNameOrCertNo(studentName,"");
			if(student!=null)
			{
				hqlparam+=" and studentId="+ Constants.PLACEHOLDER;
				list.add(student.getId());
			}
			else
			{
				hqlparam+=" and studentId="+ Constants.PLACEHOLDER;
				list.add(0);
			}
		}
		if(!cardNo.trim().equals(""))
		{
			if(studentName.trim().equals(""))
			{
				Student student=studentBiz.findStudentsByNameOrCertNo("",cardNo);
				if(student!=null)
				{
					hqlparam+=" and studentId="+ Constants.PLACEHOLDER;
					list.add(student.getId());
				}
				else
				{
					hqlparam+=" and studentId="+ Constants.PLACEHOLDER;
					list.add(0);
				}
			}
		}
		if (!code.trim().equals("")) {
			hqlparam += " and  code= " + Constants.PLACEHOLDER;
			list.add(code);
		}
		hqlparam+=" and deleteFlag=";
		list.add(Constants.DELETE_FALSE);
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		
		Long[] studentaccountmanagementIds = studentaccountmanagementdao.getIDs(p);
		
		if(studentaccountmanagementIds!=null && studentaccountmanagementIds.length>0)
		{
			studentaccountmanagementlst=new ArrayList<StudentAccountManagement>();
			for(int i=0;i<studentaccountmanagementIds.length;i++)
			{
				StudentAccountManagement sam=this.findStudentAccountManagementById(Integer.valueOf(studentaccountmanagementIds[i].toString()));
				StudentAccountManagement studentaccountmanagement=sam;
				Student student=studentBiz.findStudentById(studentaccountmanagement.getStudentId());
				if(student!=null)
				{
					studentaccountmanagement.setStudentName(student.getName());
				}
				studentaccountmanagementlst.add(studentaccountmanagement);
			}
		}
		
		return studentaccountmanagementlst;
	}

	/*
	 * 删除学生账户
	 * @see net.cedu.biz.finance.StudentAccountManagementBiz#deleteStudentAccountManagementById(int)
	 */
	public boolean deleteStudentAccountManagementById(int id) throws Exception {
		studentaccountmanagementdao.deleteConfig(id);
		return true;
	}

	/*
	 * 批量创建学生账户
	 * @see net.cedu.biz.finance.StudentAccountManagementBiz#addStudentAccounts()
	 */
	public int addStudentAccounts(int userId) throws Exception {
		Long [] studentIds=studentBiz.findStudentIdsByHasAccount();
		StudentAccountManagement sam=null;
		Student student=null;
		int count=0;
		if(studentIds!=null && studentIds.length>0)
		{
			for(int i=0;i<studentIds.length;i++)
			{
				sam=new StudentAccountManagement();
				sam.setCode("SA0000000"+i);
				sam.setStudentId(Integer.valueOf(studentIds[i].toString()));
				sam.setAccountBalance(new BigDecimal(0.00));
				sam.setUsingStatus(Constants.STATUS_ENABLED);
				sam.setCreatorId(userId);
				sam.setCreatedTime(new Date());
				studentaccountmanagementdao.save(sam);
				student=studentBiz.findStudentById(Integer.valueOf(studentIds[i].toString()));
				student.setHasAccount(Constants.IS_CREATE_ACCOUNT_TRUE);
				student.setUpdaterId(userId);
				student.setModifiedTime(new Date());
				studentBiz.updateStudentInfo(student);
				count++;
			}
		}
		return count;
	}

	/*
	 *  查询学生账户按学生Id
	 * @see net.cedu.biz.finance.StudentAccountManagementBiz#findStudentAccountManagementByStudentId(int)
	 */
	public StudentAccountManagement findStudentAccountManagementByStudentId(
			int studentId) throws Exception {
		List<Object> list = new ArrayList<Object>();
		list.add(studentId);
		list.add(Constants.DELETE_FALSE);
		List<StudentAccountManagement> samlst=studentaccountmanagementdao.getByProperty(" and  studentId="+Constants.PLACEHOLDER+" and  deleteFlag="+Constants.PLACEHOLDER, list);
		if(samlst!=null && samlst.size()>0)
		{
			return samlst.get(0);
		}
		return null;
	}



	/*
	 * 查询学生账户按学生Id 如果没有结果则创建新账户
	 * @see net.cedu.biz.finance.StudentAccountManagementBiz#findStudentAccountManagementByStudentIdForFee(int)
	 */
	public StudentAccountManagement updateStudentAccountManagementByStudentIdForFee(
			int studentId,int userId) throws Exception {
		
		List<Object> list = new ArrayList<Object>();
		list.add(studentId);
		list.add(Constants.DELETE_FALSE);
		List<StudentAccountManagement> samlst=studentaccountmanagementdao.getByProperty(" and  studentId="+Constants.PLACEHOLDER+" and  deleteFlag="+Constants.PLACEHOLDER, list);
		if(samlst!=null && samlst.size()>0)
		{
			return samlst.get(0);
		}else
		{
			StudentAccountManagement sam=new StudentAccountManagement();
			sam.setCode("SA0000000"+studentId);
			sam.setStudentId(studentId);
			sam.setAccountBalance(new BigDecimal(0.00));
			sam.setUsingStatus(Constants.STATUS_ENABLED);
			sam.setCreatorId(userId);
			sam.setCreatedTime(new Date());
			studentaccountmanagementdao.save(sam);
			return sam;
		}
		
	}

	
	
	

}
