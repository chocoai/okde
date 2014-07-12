package net.cedu.biz.examination.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.examination.ExamAreaBiz;
import net.cedu.common.Constants;
import net.cedu.dao.examination.ExamAreaDao;
import net.cedu.entity.examination.ExamArea;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ExamAreaBizImpl implements ExamAreaBiz{
	@Autowired
	private ExamAreaDao examareadao;
	
	 public boolean createNew(ExamArea examarea) throws Exception {
		 if(null==findByName(examarea.getName()))
			{
				examareadao.save(examarea);
				return true;
			}
			return false;
		}
	 public ExamArea findByExamAreaId(int id)throws Exception{
		 return examareadao.findById(id);
	 }
	 
	 public List<ExamArea> page(PageResult<ExamArea> result)throws Exception{
		 List<ExamArea> examareaist =new ArrayList<ExamArea>();

			PageParame pp = new PageParame(result);
			Long[]list=examareadao.getIDs(pp);
			
			for(int i=0;i<list.length;i++)
			{
				examareaist.add(examareadao.findById(list[i].intValue()));
			}
			return examareaist;
		}

		 
	 
	 public ExamArea findByName(String name) throws Exception {
			String hqlcon = "";
			List<ExamArea> list = null;
			List<Object> paramList = new ArrayList<Object>();
			// 名称
			if (StringUtils.isNotBlank(name)) {
				hqlcon += " and name like " + Constants.PLACEHOLDER;
				paramList.add("%"+name+"%");
			}
			if (0 < hqlcon.length())
				list = examareadao.getByProperty(hqlcon, paramList);
			if (null != list && 0 < list.size())
				return list.get(0);
			return null;
		}

		/**
		 * 多条件查询
		 * 
		 * */
	public List<ExamArea> findByConditions(ExamArea examarea, PageResult<ExamArea> pr)
		throws Exception{
		List<ExamArea> examrooms =new ArrayList<ExamArea>();
		PageParame p = new PageParame(pr);
		
		List<Object> paramlist=new ArrayList<Object>();
		String hql="";
		if (examarea!= null) {
			
			if(StringUtils.isNotBlank(examarea.getName()))
			{
				hql+="and name like "+Constants.PLACEHOLDER;
				paramlist.add("%"+examarea.getName()+"%");
			}
			
			
		
			p.setHqlConditionExpression(hql);
			p.setValues(paramlist.toArray());
		}

		Long[] examroomIds = examareadao.getIDs(p);
		if (examroomIds != null && examroomIds.length != 0) {
			for (int i = 0; i < examroomIds.length; i++) {
				ExamArea u = examareadao.findById(Integer.parseInt(examroomIds[i].toString()));
				if (u != null) {
					examrooms.add(u);
				}
			}
		}

		return examrooms;
	}
	
		
	public Object updateExamArea(ExamArea examarea)throws Exception{
		return examareadao.update(examarea);
		
	}
		/**
		 * 多条件查询结果数量
		 * 
		 * */
	 public int findExamAreaPageCount(ExamArea examarea, PageResult<ExamArea> pr)
		  throws Exception{
		 PageParame p = new PageParame(pr);
			List<Object> list=new ArrayList<Object>();
			String hql="";
			
			if (examarea!= null) 
			{
				if(StringUtils.isNotBlank(examarea.getName()))
				{
					hql+="and name like "+Constants.PLACEHOLDER;
					list.add("%"+examarea.getName()+"%");
				}
			
				p.setHqlConditionExpression(hql);
				p.setValues(list.toArray());
				
			}
			return examareadao.getCounts(p);
 }
		 /**
		  * 根据ID删除考场
		  * */
	public Object deleteExamAreaById(int id)throws Exception{
		
		return examareadao.deleteById(id);
	}
	public List<ExamArea> findAllexamarea()throws Exception{
		return examareadao.findAll();
	}
		 

	
}
