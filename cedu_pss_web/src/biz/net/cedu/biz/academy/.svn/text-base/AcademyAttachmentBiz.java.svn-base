package net.cedu.biz.academy;

import java.util.List;


import net.cedu.entity.academy.AcademyAttachment;
import net.cedu.model.page.PageResult;

/**
 * 院校附件业务逻辑
 * @author gaolei
 * */

public interface AcademyAttachmentBiz {

	/**
	 * 添加附件
	 * @param academyattachment
	 * @return
	 * @throws Exception
	 */
	public boolean addAcademyAttachment(AcademyAttachment academyattachment)throws Exception;
	
	/**
	 * 查询附件总数量
	 * @param academyId
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int findAllAcademyAttachmentCount(int academyId,PageResult<AcademyAttachment> pr)throws Exception;
	
	/**
	 * 查询附件信息分页
	 * @param academyId
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<AcademyAttachment> findAllAcademyAttachment(int academyId,PageResult<AcademyAttachment> pr)throws Exception;
	
	/**
	 * 删除附件
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteAcademyAttachment(int id)throws Exception;
	

	/**
	 * 修改附件
	 * @param academyattachment
	 * @return
	 * @throws Exception
	 */
	public boolean updateAcademyAttachment(AcademyAttachment academyattachment)throws Exception;
	
	
	/**
	 * 查看附件按ID
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public AcademyAttachment findAcademyAttachmentById(int id)throws Exception;
	
	
	
	
}
