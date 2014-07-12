package net.cedu.biz.examination.impl;
import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.examination.InvigilatorBiz;
import net.cedu.common.Constants;
import net.cedu.dao.examination.InvigilatorDao;
import net.cedu.entity.admin.User;
import net.cedu.entity.examination.Invigilator;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 
 * 监考老师业务层实现类
 * @author panyuheng
 * 
 * */
@Service
public class InvigilatorBizImpl implements InvigilatorBiz {

	@Autowired
	private InvigilatorDao invigilatordao;
	@Autowired
	private UserBiz userbiz;
	
	/*  分页查询
	 * (non-Javadoc)
	 * @see net.cedu.biz.examination.InvigilatorBiz#page(net.cedu.biz.model.page.PageResult)
	 */
	public List<Invigilator> page(PageResult<Invigilator> result)throws Exception{
		List<Invigilator> invigilatorlist =new ArrayList<Invigilator>();

		PageParame pp = new PageParame(result);
		Long[]list=invigilatordao.getIDs(pp);
		
		for(int i=0;i<list.length;i++)
		{
			invigilatorlist.add(invigilatordao.findById(list[i].intValue()));
		}
		return invigilatorlist;
	}

	/*  分页数量
	 * (non-Javadoc)
	 * @see net.cedu.biz.examination.InvigilatorBiz#countInvigilators(net.cedu.biz.model.page.PageResult)
	 */
	public int countInvigilators(PageResult<Invigilator> result) throws Exception{
		
		PageParame pp = new PageParame(result);		

		return invigilatordao.getCounts(pp);
	}

	/*  根据主键ID删
	 * (non-Javadoc)
	 * @see net.cedu.biz.examination.InvigilatorBiz#countInvigilators(net.cedu.biz.model.page.PageResult)
	 */
	public Object deleteInvigilator(int id)throws Exception {
		
		return invigilatordao.deleteById(id);
		
	}
	/*  多条件查询
	 * (non-Javadoc)
	 * @see net.cedu.biz.examination.InvigilatorBiz#countInvigilators(net.cedu.biz.model.page.PageResult)
	 */

	public List<Invigilator> findByConditions(
			Invigilator invigilator, PageResult<Invigilator> pr)
			throws Exception {
		List<Invigilator> invigilators =null;
		PageParame p = new PageParame(pr);
		
		List<Object> paramlist=new ArrayList<Object>();
		String hql="";
		if (invigilator != null) {
			
			if(StringUtils.isNotBlank(invigilator.getName()))
			{
				hql+="and name like "+Constants.PLACEHOLDER;
				paramlist.add("%"+invigilator.getName()+"%");
			}
			if(StringUtils.isNotBlank(invigilator.getWorkUnit()))
			{
				hql+="and workUnit like "+Constants.PLACEHOLDER;
				paramlist.add("%"+invigilator.getWorkUnit()+"%");
			}
			
			if (invigilator.getStatus()!=null && invigilator.getStatus()>-1)// 状态
			{
				hql+="and status = "+Constants.PLACEHOLDER;
				paramlist.add(invigilator.getStatus());
			}
			
			p.setHqlConditionExpression(hql);
			p.setValues(paramlist.toArray());
		}
		Long[] invigilatorIds = invigilatordao.getIDs(p);
		if (invigilatorIds != null && invigilatorIds.length != 0) {
			invigilators = new ArrayList<Invigilator>();
			Invigilator d=null;
			for(Long id : invigilatorIds){
		    	if (null !=(d = invigilatordao.findById(Integer.parseInt(id
						.toString())))) {
		    		if(d.getCreatorId()!=null && d.getCreatorId()>0){
		    		User u=userbiz.findUserById(d.getCreatorId());
		    		if(u!=null){
		    			d.setCreatorname(u.getUserName());
		    		}
		    		}
		    	}
		    	invigilators.add(d);
		    	}
				}

		return invigilators;
	}
	
	public List<Invigilator> findAllInvigilator() throws Exception{
		return invigilatordao.findAll();
	}
	/*  多条件分页数量
	 * (non-Javadoc)
	 * @see net.cedu.biz.examination.InvigilatorBiz#countInvigilators(net.cedu.biz.model.page.PageResult)
	 */	
    public int findInvigilatorPageCount(Invigilator invigilator, PageResult<Invigilator> pr)
		  throws Exception {
    	
		PageParame p = new PageParame(pr);
		List<Object> list=new ArrayList<Object>();
		String hql="";
		//System.out.println(invigilator.getStatus());
			if (invigilator != null) 
			{
				if(StringUtils.isNotBlank(invigilator.getName()))
				{
					hql+="and name like "+Constants.PLACEHOLDER;
					list.add("%"+invigilator.getName()+"%");
				}
				if(StringUtils.isNotBlank(invigilator.getWorkUnit()))
				{
					hql+="and workUnit like "+Constants.PLACEHOLDER;
					list.add("%"+invigilator.getWorkUnit()+"%");
				}
				if (invigilator.getStatus()!=null && invigilator.getStatus()>-1)// 状态
				{
					hql+="and status = "+Constants.PLACEHOLDER;
					list.add(invigilator.getStatus());
				}
				p.setHqlConditionExpression(hql);
				p.setValues(list.toArray());
				
			}
			return invigilatordao.getCounts(p);
    }
    /* 
     * 增加监考教师
	 * (non-Javadoc)
	 * @see net.cedu.biz.examination.InvigilatorBiz#countInvigilators(net.cedu.biz.model.page.PageResult)
	 */
	public Object saveInvgilator(Invigilator invigilator) throws Exception {
		
		return invigilatordao.save(invigilator);
	}
	
	/**
	 * 按名查找用户
	 */
	public Invigilator findByName(String name) throws Exception {
		String hqlcon = "";
		List<Invigilator> list = null;
		List<Object> paramList = new ArrayList<Object>();

		// 名称
		if (StringUtils.isNotBlank(name)) {
			hqlcon += " and name=" + Constants.PLACEHOLDER;
			paramList.add(name);
		}
		if (0 < hqlcon.length())
			list = invigilatordao.getByProperty(hqlcon, paramList);
		if (null != list && 0 < list.size())
			return list.get(0);
		return null;
	}
	
	public boolean createNew(Invigilator invigilator) throws Exception 
	{
		if(null==findByName(invigilator.getName()))
		{
			invigilatordao.save(invigilator);
			return true;
		}
		return false;
	}

	//根据Id查找监考老师
	public Invigilator findByInvigilatorId(int id) throws Exception {
		
		return invigilatordao.findById(id);
	}
	
	//修改监考老师
	public Object updateInvigilate(Invigilator invigilator)
	{
		return invigilatordao.update(invigilator);
	}
	
}
