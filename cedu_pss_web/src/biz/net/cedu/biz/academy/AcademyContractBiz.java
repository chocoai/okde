package net.cedu.biz.academy;

import java.util.Date;
import java.util.List;

import net.cedu.entity.academy.AcademyContract;
import net.cedu.model.page.PageResult;

/**
 * 院校合同业务逻辑
 * @author gaolei
 * */

public interface AcademyContractBiz {

	
	/**
	 * 院校合同新增
	 * @param academycontract
	 * @return
	 * @throws Exception
	 */
	public boolean addAcademyContract(AcademyContract academycontract)throws Exception;
	
	
	/**
	 * 查询院校合同总数量
	 * @param academyId
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int findAllAcademyContractCount(int academyId,PageResult<AcademyContract> pr)throws Exception;
	
	
	/**
	 * 查询院校合同信息分页
	 * @param academyId
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<AcademyContract> findAllAcademyContract(int academyId,PageResult<AcademyContract> pr)throws Exception;
	
	
	/**
	 * 删除院校合同
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteAcademyContract(int id)throws Exception;
	
	/**
	 * 修改院校合同
	 * @param academycontract
	 * @return
	 * @throws Exception
	 */
	public boolean updateAcademyContract(AcademyContract academycontract)throws Exception;
	
	/**
	 * 查看院校合同按ID
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public AcademyContract findAcademyContractById(int id)throws Exception;
	
	
	/**
	 * 重复数据校验
	 * @param academyId
	 * @param name
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public AcademyContract findAcademyContractByNameAndDate(int academyId,String name,Date startDate,Date endDate);
	
}
