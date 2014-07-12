package net.cedu.biz.academy;

import java.util.Date;
import java.util.List;

import net.cedu.entity.academy.AcademyCommunicationRecord;
import net.cedu.model.page.PageResult;

/**
 * 院校沟通记录业务逻辑
 * @author gaolei
 * */

public interface AcademyCommunicationRecordBiz {

	/**
	 *  新增沟通记录
	 * @param acr
	 * @return
	 * @throws Exception
	 */
	public boolean addAcademy(AcademyCommunicationRecord acr)throws Exception;
	
	/**
	 * 删除沟通记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteAcademy(int id)throws Exception;
	
	/**
	 * 修改沟通记录
	 * @param acr
	 * @return
	 * @throws Exception
	 */
	public boolean updateAcademy(AcademyCommunicationRecord acr)throws Exception;
	
	
	/**
	 * 查询沟通记录按Id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public AcademyCommunicationRecord findAcademyCommunicationRecordById(int id)throws Exception;
	
	
	/**
	 * 查询沟通记录条数 
	 * @param academyId
	 * @param linkmanId
	 * @param subject
	 * @param result
	 * @param time
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int findAcademyCommunicationRecordCount(int academyId,int linkmanId, String subject,String result,String starttime,String endtime, PageResult<AcademyCommunicationRecord> pr)throws Exception;
	
	
	/**
	 * 查询沟通记录（分页） 
	 * @param academyId
	 * @param linkmanId
	 * @param subject
	 * @param result
	 * @param time
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<AcademyCommunicationRecord> findAcademyCommunicationRecordList(int academyId,int linkmanId, String subject,String result,String starttime,String endtime, PageResult<AcademyCommunicationRecord> pr)throws Exception;
	
}
