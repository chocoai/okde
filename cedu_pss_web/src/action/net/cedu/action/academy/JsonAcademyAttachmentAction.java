package net.cedu.action.academy;

import java.io.File;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyAttachmentBiz;
import net.cedu.biz.admin.UserBiz;
import net.cedu.entity.academy.AcademyAttachment;
import net.cedu.model.page.PageResult;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * json院校管理
 * @author gaolei
 *
 */
@ParentPackage("json-default")
public class JsonAcademyAttachmentAction extends BaseAction {

	@Autowired
	private AcademyAttachmentBiz academyattachmentbiz;       //附件Biz
	private AcademyAttachment academyattachment;             //附件实体     
	// 分页结果
	private PageResult<AcademyAttachment> result = new PageResult<AcademyAttachment>();
	private int academyId;                                          //院校ID
	private int id;                                                 //附件ID
	

	/**
	 *  查询附件(数量)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "countacademyattachment", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json"
	}) })
	public String CountAcademyAttachment() throws Exception {
		try {
				// 查询数量
				result.setRecordCount(academyattachmentbiz.findAllAcademyAttachmentCount(academyId,result));
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		return SUCCESS;
	}

	/**
	 *  查询附件(数据)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "listacademyattachment", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"excludeProperties",
			"result.*.pictureUrl,"+
			"result.*.shortName,"+
			"result.*.foundedYear,"+
			"result.*.website,"+
			"result.*.scale,"+
			"result.*.introduction,"+
			"result.*.auditer,"+
			"result.*.auditedDate,"+
			"result.*.account,"+
			"result.*.isForceFeePolicy,"+
			"result.*.deleteFlag,"+
			"result.*.updaterId,"+
			"result.*.updatedTime,"+
			"result.*.user.createdTime,"+
			"result.*.user.creatorId,"+
			"result.*.user.deleteFlag,"+
			"result.*.user.email,"+
			"result.*.user.mobile,"+
			"result.*.user.orgId,"+
			"result.*.user.password,"+
			"result.*.user.photoUrl,"+
			"result.*.user.status,"+
			"result.*.user.telephone,"+
			"result.*.user.type,"+
			"result.*.user.userName,"+
			"result.*.user.updaterId,"+
			"result.*.user.updatedTime"
			
	}) })
	public String AcademyAttachmentList() throws Exception {
		// 查询集合
		result.setList(academyattachmentbiz.findAllAcademyAttachment(academyId, result));
		return SUCCESS;
	}
	
	
	/**
	 *  删除院校附件
	 * @return
	 * @throws Exception
	 */
	@Action(value = "deleteacademyattachment", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String DeleteAcademyAttachment() throws Exception {
		
		try {
			academyattachment=academyattachmentbiz.findAcademyAttachmentById(id);
			//执行删除操作
			academyattachmentbiz.deleteAcademyAttachment(id);
			if(academyattachment.getAttachmentUrl()!=null)
			{
				String delpath= ServletActionContext.getServletContext().getRealPath(academyattachment.getAttachmentUrl());
				deleteFile(delpath);
			}
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 删除文件
	 * @param filepath
	 * @throws Exception
	 */
	private void deleteFile(String filepath)throws Exception 
	{
		File file=new File(filepath);
		file.delete();
	}
	
	

	public AcademyAttachment getAcademyattachment() {
		return academyattachment;
	}

	public void setAcademyattachment(AcademyAttachment academyattachment) {
		this.academyattachment = academyattachment;
	}

	public PageResult<AcademyAttachment> getResult() {
		return result;
	}

	public void setResult(PageResult<AcademyAttachment> result) {
		this.result = result;
	}

	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
