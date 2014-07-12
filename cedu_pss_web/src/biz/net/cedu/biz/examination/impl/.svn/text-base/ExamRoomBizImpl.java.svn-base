package net.cedu.biz.examination.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.examination.ExamRoomBiz;
import net.cedu.common.Constants;
import net.cedu.dao.examination.ExamRoomDao;
import net.cedu.entity.examination.ExamRoom;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamRoomBizImpl implements ExamRoomBiz {

	@Autowired
	private ExamRoomDao examroomdao;
	
	/*
	 * 无条件查询
	 * 
	 * (non-Javadoc)
	 * @see net.cedu.biz.examination.ExamRoomBiz#page(net.cedu.model.page.PageResult)
	 */
	public List<ExamRoom> page(PageResult<ExamRoom> result)throws Exception{
		List<ExamRoom> examroomlist =new ArrayList<ExamRoom>();

		PageParame pp = new PageParame(result);
		Long[]list=examroomdao.getIDs(pp);
		
		for(int i=0;i<list.length;i++)
		{
			examroomlist.add(examroomdao.findById(list[i].intValue()));
		}
		return examroomlist;
	}

	
	/*  多条件查询
	 * (non-Javadoc)
	 * @see net.cedu.biz.examination.InvigilatorBiz#countInvigilators(net.cedu.biz.model.page.PageResult)
	 */

	public List<ExamRoom> findByConditions(
			ExamRoom examroom, PageResult<ExamRoom> pr)
			throws Exception {
		List<ExamRoom> examrooms =new ArrayList<ExamRoom>();
		PageParame p = new PageParame(pr);
		List<Object> paramlist=new ArrayList<Object>();
		String hql="";
		if (examroom!= null) {
			
			if(StringUtils.isNotBlank(examroom.getName()))
			{
				hql+="and name like "+Constants.PLACEHOLDER;
				paramlist.add("%"+examroom.getName()+"%");
			}
			if(StringUtils.isNotBlank(examroom.getLinkman()))
			{
				hql+="and linkman like "+Constants.PLACEHOLDER;
				paramlist.add("%"+examroom.getLinkman()+"%");
			}
			
			if(StringUtils.isNotBlank(examroom.getStatus().toString()))
			{
				hql+="and status like "+Constants.PLACEHOLDER;
				paramlist.add("%"+examroom.getStatus()+"%");
			}
			
			p.setHqlConditionExpression(hql);
			p.setValues(paramlist.toArray());
		}

		
		Long[] examroomIds = examroomdao.getIDs(p);
		if (examroomIds != null && examroomIds.length != 0) {
			for (int i = 0; i < examroomIds.length; i++) {
				ExamRoom u = examroomdao.findById(Integer.parseInt(examroomIds[i].toString()));
				if (u != null) {
					examrooms.add(u);
				}
			}
		}

		return examrooms;
	}
	
	/*  多条件分页数量
	 * (non-Javadoc)
	 * @see net.cedu.biz.examination.InvigilatorBiz#countInvigilators(net.cedu.biz.model.page.PageResult)
	 */	
    public int findExamRoomPageCount(ExamRoom examroom, PageResult<ExamRoom> pr)
		  throws Exception {
    	
    	
		PageParame p = new PageParame(pr);
		List<Object> list=new ArrayList<Object>();
		String hql="";
		
			if (examroom!= null) 
			{
				if(StringUtils.isNotBlank(examroom.getName()))
				{
					hql+="and name like "+Constants.PLACEHOLDER;
					list.add("%"+examroom.getName()+"%");
				}
				if(StringUtils.isNotBlank(examroom.getLinkman()))
				{
					hql+="and linkman like "+Constants.PLACEHOLDER;
					list.add("%"+examroom.getLinkman()+"%");
				}
				if(StringUtils.isNotBlank(examroom.getStatus().toString()))
				{
					hql+="and status like "+Constants.PLACEHOLDER;
					list.add("%"+examroom.getStatus()+"%");
				}
				p.setHqlConditionExpression(hql);
				p.setValues(list.toArray());
				
			}
			return examroomdao.getCounts(p);
    }
    public Object updateExamroom(ExamRoom examroom)throws Exception{
    	return examroomdao.update(examroom);
    }
    
    public ExamRoom findByExamroomId(int id)throws Exception{
    	return examroomdao.findById(id);
    }
	 /**
	  * 
	  * 根据ID删除考场
	  * 
	  * */
	public Object deleteExamRoomById(int id) throws Exception {
	
		return examroomdao.deleteById(id);
	}
	public ExamRoom findByName(String name) throws Exception {
		String hqlcon = "";
		List<ExamRoom> list = null;
		List<Object> paramList = new ArrayList<Object>();
		// 名称
		if (StringUtils.isNotBlank(name)) {
			hqlcon += " and  name=" + Constants.PLACEHOLDER;
			paramList.add(name);
		}
		if (0 < hqlcon.length())
			list = examroomdao.getByProperty(hqlcon, paramList);
		if (null != list && 0 < list.size())
			return list.get(0);
		return null;
	}
	public boolean createNew(ExamRoom examroom) throws Exception 
	{
		if(null==findByName(examroom.getName()))
		{
			examroomdao.save(examroom);
			return true;
		}
		return false;
	}
	public List<ExamRoom> findAllexamroom()throws Exception{
		return examroomdao.findAll();
	}
}
