package net.cedu.biz.academy.impl;

import java.util.List;

import net.cedu.biz.academy.AcademyLinkManBiz;
import net.cedu.common.Constants;
import net.cedu.dao.academy.AcademyLinkManDao;
import net.cedu.entity.academy.AcademyLinkMan;
import net.cedu.model.page.PageParame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * 院校联系人业务逻辑
 * 
 * @author gaolei
 *
 * 
 * */
@Service
public class AcademyLinkManBizImpl implements AcademyLinkManBiz {

	@Autowired
	private AcademyLinkManDao academylinkmandao;  //院校联系人接口
	
	/*
	 * 添加院校联系人
	 * @see net.cedu.biz.academy.AcademyLinkManBiz#addAcademyLinkMan(net.cedu.entity.academy.AcademyLinkMan)
	 */
	public boolean addAcademyLinkMan(AcademyLinkMan academylm) throws Exception{

		return academylinkmandao.addAcademyLinkMan(academylm);
	}
	
	/*
	 * 删除院校联系人
	 * @see net.cedu.biz.academy.AcademyLinkManBiz#deleteAcademyLinkMan(int)
	 */
	public boolean deleteAcademyLinkMan(int id)throws Exception
	{
		
			academylinkmandao.deleteConfig(id);
			return true;
		
		
	}
	
	
	/*
	 * 修改院校联系人
	 * @see net.cedu.biz.academy.AcademyLinkManBiz#updateAcademyLinkMan(net.cedu.entity.academy.AcademyLinkMan)
	 */
	public boolean updateAcademyLinkMan(AcademyLinkMan academylm)throws Exception
	{
		
			academylinkmandao.update(academylm);
			return true;
		
		
	}
	
	
	/*
	 * 查询重复院校联系人
	 * @see net.cedu.biz.academy.AcademyLinkManBiz#getCountsByAcademyLinkMan(int, java.lang.String, java.lang.String)
	 */
	public int getCountsByAcademyLinkMan(int id,String name,String mobile)throws Exception{
		PageParame p=new PageParame();
		if(name!=null && mobile!=null)
		{
			p.setHqlConditionExpression(" and name="+Constants.PLACEHOLDER+"   and mobile="+Constants.PLACEHOLDER);
			p.setValues(new Object[] { name ,mobile});
		}
		return academylinkmandao.getCountsByAcademyLinkMan(p);
	}

	/*
	 * 查询院校联系人按院校ID
	 * @see net.cedu.biz.academy.AcademyLinkManBiz#findAcademyLinkManByAcademyId(int)
	 */
	public List<AcademyLinkMan> findAcademyLinkManByAcademyId(int id)throws Exception
	{
		return academylinkmandao.findAcademyLinkManByAcademyId(id);
	}
	
	/*
	 * 查询院校联系人按ID
	 * @see net.cedu.biz.academy.AcademyLinkManBiz#findAcademyLinkManId(int)
	 */
	public AcademyLinkMan findAcademyLinkManId(int id)throws Exception
	{
		return academylinkmandao.findById(id);
	}
	
	
	
	
}
