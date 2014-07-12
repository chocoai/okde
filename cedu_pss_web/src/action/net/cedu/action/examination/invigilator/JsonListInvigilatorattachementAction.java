package net.cedu.action.examination.invigilator;

import java.io.File;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.InvigilatorAttachmentBiz;
import net.cedu.entity.examination.InvigilatorAttachment;

import net.cedu.model.page.PageResult;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
@ParentPackage("json-default")
public class JsonListInvigilatorattachementAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4046861218270506919L;
	public PageResult<InvigilatorAttachment> getResult() {
		return result;
	}
	public void setResult(PageResult<InvigilatorAttachment> result) {
		this.result = result;
	}
	@Autowired
	private InvigilatorAttachmentBiz invigilatorattachementbiz;
	private InvigilatorAttachment invigilatorattachment=new InvigilatorAttachment();
	PageResult<InvigilatorAttachment> result=new PageResult<InvigilatorAttachment>();
	public InvigilatorAttachment getInvigilatorattachment() {
		return invigilatorattachment;
	}
	public void setInvigilatorattachment(InvigilatorAttachment invigilatorattachment) {
		this.invigilatorattachment = invigilatorattachment;
	}
	private int invigilatorId;
	public int getInvigilatorId() {
		return invigilatorId;
	}
	public void setInvigilatorId(int invigilatorId) {
		this.invigilatorId = invigilatorId;
	}
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	//分页结果
	@Action(value="list_invigilatorattachment",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute()
	{
		System.out.println(invigilatorId);
		try {
			result.setList(invigilatorattachementbiz.findAllInvigilatorAttachment(invigilatorId,result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
		
	}
	
	
	//分页数量
	@Action(value="count_invigilatorattachment",
			results={
				@Result(type="json", name = "success",params={"contentType","text/json"}
					   )
			})	
	public String count()
	{
		try {
			result.setRecordCount(invigilatorattachementbiz.findAllInvigilatorAttachmentCount(invigilatorId,result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	@Action(value = "deleteinvigilatorattachment", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String DeleteAcademyAttachment() throws Exception {
		
		try {
			invigilatorattachment=invigilatorattachementbiz.findInvigilatorAttachmentById(id);
			//执行删除操作
			invigilatorattachementbiz.deleteInvigilatorAttachment(id);
			if(invigilatorattachment.getAttachmentUrl()!=null)
			{
				String delpath= ServletActionContext.getServletContext().getRealPath(invigilatorattachment.getAttachmentUrl());
				deleteFile(delpath);
			}
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}
	private void deleteFile(String filepath)throws Exception 
	{
		File file=new File(filepath);
		file.delete();
	}

}


