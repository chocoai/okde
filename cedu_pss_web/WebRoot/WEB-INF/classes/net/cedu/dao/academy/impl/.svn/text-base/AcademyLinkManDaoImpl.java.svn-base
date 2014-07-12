package net.cedu.dao.academy.impl;

import java.util.List;


import org.springframework.stereotype.Repository;

import net.cedu.dao.academy.AcademyLinkManDao;
import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.entity.academy.AcademyLinkMan;
import net.cedu.model.page.PageParame;


/**
 * 
 * 院校联系人数据层
 * 
 * @author gaolei
 *
 * 
 * */
@Repository
public class AcademyLinkManDaoImpl extends BaseMDDaoImpl<AcademyLinkMan> implements AcademyLinkManDao {

	
	/*
	 * 添加院校联系人
	 * 
	 *  @author gaolei
	 */
	public boolean addAcademyLinkMan(AcademyLinkMan academylm) {
		
		try {
			super.save(academylm);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	
	/*
	 * 查询重复院校联系人
	 * 
	 *  @author gaolei
	 */
	public int getCountsByAcademyLinkMan(PageParame p) {
	
		return super.getCounts(p);
	}
	
	/*
	 * 查询院校联系人按院校ID
	 * 
	 *  @author gaolei
	 */
	public List<AcademyLinkMan> findAcademyLinkManByAcademyId(int id)
	{
		return super.getByProperty("academyId", id);
	}
	
	
	
	
	
	
	

}
