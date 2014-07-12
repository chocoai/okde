package net.cedu.biz.examination;

import java.util.List;


import net.cedu.entity.examination.InvigilatorAttachment;
import net.cedu.model.page.PageResult;

public interface InvigilatorAttachmentBiz {
public boolean addInvigilatorAttachment(InvigilatorAttachment invigilatorattachment)throws Exception;
	
	/**
	 * 查询附件总数量
	 * @param academyId
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int findAllInvigilatorAttachmentCount(int invigilatorId,PageResult<InvigilatorAttachment> pr)throws Exception;
	
	/**
	 * 查询附件信息分页
	 * @param academyId
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<InvigilatorAttachment> findAllInvigilatorAttachment(int invigilatorId,PageResult<InvigilatorAttachment> pr)throws Exception;
	
	/**
	 * 删除附件
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteInvigilatorAttachment(int id)throws Exception;
	

	/**
	 * 修改附件
	 * @param academyattachment
	 * @return
	 * @throws Exception
	 */
	public boolean updateInvigilatorAttachment(InvigilatorAttachment invigilatorattachment)throws Exception;
	
	
	/**
	 * 查看附件按ID
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public InvigilatorAttachment findInvigilatorAttachmentById(int id)throws Exception;
	

}
