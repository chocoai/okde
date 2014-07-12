package net.cedu.biz.enrollment;

import java.util.List;



import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.EnrollQuota;
import net.cedu.model.page.PageResult;

/**
 * 年度总指标  业务逻辑接口
 * 
 * @author gaolei
 *
 */
public interface EnrollQuotaBiz {
	

	/**
	 * 添加年度总指标
	 * @param enrollquota
	 * @return
	 * @throws Exception
	 */
	public boolean addEnrollQuota(EnrollQuota enrollquota)throws Exception;
	
	
	/**
	 * 修改年度总指标
	 * @param enrollquota
	 * @return
	 * @throws Exception
	 */
	public boolean updateEnrollQuota(EnrollQuota enrollquota)throws Exception;
	
	
	/**
	 * 删除年度总指标
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteEnrollQuota(int id)throws Exception;
	
	

	/**
	 * 查询年度总指标按Id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public EnrollQuota findEnrollQuotaById(int id)throws Exception;
	
	
	/**
	 * 查询所有年度总指标
	 * @return
	 * @throws Exception
	 */
	public List<EnrollQuota> findEnrollQuotaAll() throws Exception;
	
	
	/**
	 * 查询年度总指标(数量)
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int countEnrollQuota(PageResult<EnrollQuota> pr)throws Exception;
	
	
	/**
	 * 查询年度总指标(数据)
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<EnrollQuota> findEnrollQuotalist(PageResult<EnrollQuota> pr)throws Exception;
	
	
	
	/**
	 * 查询年度总指标按年份
	 * @param year
	 * @return
	 * @throws Exception
	 */
	public EnrollQuota findEnrollQuotaByYear(int year)throws Exception;	


	/**
	 * 添加年度总指标(计算)
	 * @param year
	 * @param branch
	 * @param opeaing
	 * @param major
	 * @param target
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int[] addEnrollQuotas(int year,int branch,int opeaing,int major,int target,int userId)throws Exception;	
	
	
	/**
	 * 查询最大年份
	 * @return
	 * @throws Exception
	 */
	public int findEnrollQuotaMaxYear()throws Exception;	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
