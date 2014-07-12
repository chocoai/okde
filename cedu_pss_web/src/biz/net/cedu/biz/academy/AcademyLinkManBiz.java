package net.cedu.biz.academy;

import java.util.List;


import net.cedu.entity.academy.AcademyLinkMan;


/**
 * 
 * 院校联系人
 * 
 * @author gaolei
 *
 * 
 * */

public interface AcademyLinkManBiz {

	/**
	 * 添加院校联系人
	 * @param academylm
	 * @return
	 * @throws Exception
	 */
	public boolean addAcademyLinkMan(AcademyLinkMan  academylm)throws Exception;
	
	

	/**
	 * 删除院校联系人
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteAcademyLinkMan(int id)throws Exception;
	
	
	/**
	 * 修改院校联系人
	 * @param academylm
	 * @return
	 * @throws Exception
	 */
	public boolean updateAcademyLinkMan(AcademyLinkMan academylm)throws Exception;
	
	
	/**
	 * 查询重复院校联系人
	 * @param id
	 * @param name
	 * @param mobile
	 * @return
	 * @throws Exception
	 */
	public int getCountsByAcademyLinkMan(int id,String name,String mobile)throws Exception;
	
	/**
	 * 查询院校联系人按院校ID
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<AcademyLinkMan> findAcademyLinkManByAcademyId(int id)throws Exception;
	
	
	/**
	 * 查询院校联系人按ID
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public AcademyLinkMan findAcademyLinkManId(int id)throws Exception;
	
	
	
	
}
