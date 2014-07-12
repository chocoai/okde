package net.cedu.biz.academy;

import java.util.List;

import net.cedu.entity.academy.Academy;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

/**
 * 院校管理业务逻辑
 * @author gaolei
 * */

public interface AcademyBiz {

	
	/**
	 * 查询所有院校
	 * @return
	 * @throws Exception
	 */
	public List<Academy> findAllAcademys()throws Exception;
	
	/**
	 * 查询所有院校（逻辑删除）（已启用）
	 * @return
	 * @throws Exception
	 */
	public List<Academy> findAllAcademyByFlagFalse()throws Exception;
	
	
	/**
	 * 查询所有院校（非逻辑删除）（已启用）
	 * @return
	 * @throws Exception
	 */
	public List<Academy> findAllAcademyByFlagTrue()throws Exception;
	
	/**
	 * 院校新增
	 * @param academy
	 * @return
	 * @throws Exception
	 */
	public boolean addAcademy(Academy academy)throws Exception;
	
	/**
	 * 查询院校按主键ID
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Academy findAcademyById(int id)throws Exception;
	
	/**
	 * 查询院校总数量
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int findAllAcademyCount(PageResult<Academy> pr)throws Exception;
	
	/**
	 * 查询院校信息分页
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<Academy> findAllAcademy( PageResult<Academy> pr)throws Exception;
	
	/**
	 * 删除院校
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteAcademy(int id)throws Exception;
	
	/**
	 * 修改院校
	 * @param academy
	 * @return
	 * @throws Exception
	 */
	public boolean updateAcademy(Academy academy)throws Exception;
	
	
	/**
	 * 获取院校IDs
	 * @param pageParame
	 * @return
	 * @throws Exception
	 */
	public Long[] getAcademyIds(PageParame pageParame)throws Exception;
	
	
	
	
	
	/**
	 * 查询院校总数量未审核
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int findAllAcademyCountByAuditStatus(PageResult<Academy> pr)throws Exception;
	
	/**
	 * 查询院校信息分页未审核
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<Academy> findAllAcademyByAuditStatus( PageResult<Academy> pr)throws Exception;
	
	
	/**
	 * 院校重复校验
	 * @param name院校名称
	 * @param telePhone院校联系方式
	 * @return
	 * @throws Exception
	 */
	public int findAcademyByNameAndTelephone(String name,String telePhone)throws Exception;
	
	
	/**
	 * 招生计划院校列表(数量)
	 * @param pr
	 * @return
	 */
	public int countAcademyByStatus( PageResult<Academy> pr);
	
	/**
	 * 招生计划院校列表(数据)
	 * @param pr
	 * @return
	 */
	public List<Academy> findAcademyByStatus( PageResult<Academy> pr);
	
	/**
	 * 通过院校名称查询院校实体
	 * @param academyName
	 * @return
	 */
	public Academy findAcademyByName(String academyName);
	/**
	 * 
	 * @功能：查询院校IDs通过强制收费状态
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-31 上午11:46:56
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @param status  0,不强制   1,强制收费
	 * @return   如  1,2,3,4,5......字符串
	 * @throws Exception
	 */
	public String findAcademyIdsByForceFeePolicyStatus(int status)throws Exception;
	
	/**
	 * @功能：通过条件查询院校集合(删除用户功能的查询方法)
	 *
	 * @作者： 董溟浩
	 * @作成时间：2012-06-11 上午10:03:08
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * @param academy
	 * @param count 输出条数
	 * @return
	 * @throws Exception
	 */
	public List<Academy> findAcademyListByParams(Academy academy,int count) throws Exception;
}
