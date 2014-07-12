package net.cedu.biz.finance.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.finance.StudentAcademyRebateBiz;
import net.cedu.common.Constants;
import net.cedu.dao.finance.StudentAcademyRebateDao;
import net.cedu.entity.finance.StudentAcademyRebate;
import net.cedu.model.page.PageParame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 学生绑定院校返款标准关系表
 * 
 * @author lixiaojun
 * 
 */
@Service
public class StudentAcademyRebateBizImpl implements StudentAcademyRebateBiz
{
	
	@Autowired
	private StudentAcademyRebateDao studentAcademyRebateDao;
	
	
	/*
	 * 添加学生绑定院校返款标准关系表
	 * 
	 * @see net.cedu.biz.finance.StudentAcademyRebateBiz#addStudentAcademyRebate(net.cedu.entity.finance.StudentAcademyRebate)
	 */
	public boolean addStudentAcademyRebate(StudentAcademyRebate studentAcademyRebate) throws Exception
	{
		if (studentAcademyRebate != null)
		{
			Object object = studentAcademyRebateDao.save(studentAcademyRebate);
			if (object != null) 
			{
				return true;
			}
		}
		return false;
	}

	/*
	 * 删除学生绑定院校返款标准关系表（物理删除）
	 * 
	 * @see net.cedu.biz.finance.StudentAcademyRebateBiz#deleteStudentAcademyRebateById(int)
	 */
	public boolean deleteStudentAcademyRebateById(int id) throws Exception
	{
		if (id != 0) 
		{
			Object object = studentAcademyRebateDao.deleteConfig(id);
			if (object != null)
			{
				return true;
			}
		}		
		return false;
	}

	/*
	 * 修改学生绑定院校返款标准关系表
	 * 
	 * @see net.cedu.biz.finance.StudentAcademyRebateBiz#updateStudentAcademyRebate(net.cedu.entity.finance.StudentAcademyRebate)
	 */
	public boolean updateStudentAcademyRebate(StudentAcademyRebate studentAcademyRebate) throws Exception
	{
		if (studentAcademyRebate != null) 
		{
			Object object = studentAcademyRebateDao.update(studentAcademyRebate);
			if (object != null) 
			{
				return true;
			}
		}
		return false;
	}

	/*
	 * 根据Id查找学生绑定院校返款标准关系表
	 * 
	 * @see net.cedu.biz.finance.StudentAcademyRebateBiz#findStudentAcademyRebateById(int)
	 */
	public StudentAcademyRebate findStudentAcademyRebateById(int id) throws Exception 
	{
		return this.studentAcademyRebateDao.findById(id);
	}
	
	/*
	 * 根据条件查找学生绑定院校返款标准关系表数量
	 * 
	 * @see net.cedu.biz.finance.StudentAcademyRebateBiz#findStudentAcademyRebateCountBy(net.cedu.entity.finance.StudentAcademyRebate)
	 */
	public int findStudentAcademyRebateCountBy(StudentAcademyRebate studentAcademyRebate) throws Exception
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (studentAcademyRebate!= null)
		{			
			//学生Id
			if(studentAcademyRebate.getStudentId()!=0)
			{
				hqlparam += " and studentId ="+ Constants.PLACEHOLDER;
				list.add(studentAcademyRebate.getStudentId());
			}
			//费用科目Id
			if(studentAcademyRebate.getFeeSubjectId()!=0)
			{
				hqlparam += " and feeSubjectId ="+ Constants.PLACEHOLDER ;
				list.add(studentAcademyRebate.getFeeSubjectId());
			}	
		}
		else
		{
			return 0;
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		return studentAcademyRebateDao.getCounts(p);
	}
	
	/*
	 * 根据条件查找学生绑定院校返款标准关系表实体
	 * 
	 * @see net.cedu.biz.finance.StudentAcademyRebateBiz#findStudentAcademyRebateListBy(net.cedu.entity.finance.StudentAcademyRebate)
	 */
	public StudentAcademyRebate findStudentAcademyRebateListBy(StudentAcademyRebate studentAcademyRebate) throws Exception
	{
		StudentAcademyRebate sar=null;
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (studentAcademyRebate!= null)
		{			
			//学生Id
			if(studentAcademyRebate.getStudentId()!=0)
			{
				hqlparam += " and studentId ="+ Constants.PLACEHOLDER;
				list.add(studentAcademyRebate.getStudentId());
			}
			//费用科目Id
			if(studentAcademyRebate.getFeeSubjectId()!=0)
			{
				hqlparam += " and feeSubjectId ="+ Constants.PLACEHOLDER ;
				list.add(studentAcademyRebate.getFeeSubjectId());
			}	
		}
		else
		{
			return null;
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		Long[] sarIds = studentAcademyRebateDao.getIDs(p);
		if (sarIds != null && sarIds.length != 0) 
		{
			for (int i = 0; i < sarIds.length; i++) 
			{
				// 内存获取
				sar = studentAcademyRebateDao.findById(Integer.valueOf(sarIds[i].toString()));
				if(sar!=null)
				{
					break;
				}
			}
		}
		return sar;
	}

}
