package net.cedu.dao.academy;

import java.util.List;

import net.cedu.dao.BaseDao;
import net.cedu.entity.academy.AcademyLinkMan;
import net.cedu.model.page.PageParame;


/**
 * 
 * 院校联系人数据接口
 * 
 * @author gaolei
 *
 * 
 * */

public interface AcademyLinkManDao extends BaseDao<AcademyLinkMan> {

		
	/*
	 * 添加院校联系人
	 * 
	 *@author gaolei
	 */
	public boolean addAcademyLinkMan(AcademyLinkMan  academylm);
	
	
	/*
	 * 查询重复院校联系人
	 * 
	 *  @author gaolei
	 */
	public int getCountsByAcademyLinkMan(PageParame p);
	
	/*
	 * 查询院校联系人按院校ID
	 * 
	 *  @author gaolei
	 */
	public List<AcademyLinkMan> findAcademyLinkManByAcademyId(int id);
	
	
	
	
	
	
	
	
	
	
	
}
