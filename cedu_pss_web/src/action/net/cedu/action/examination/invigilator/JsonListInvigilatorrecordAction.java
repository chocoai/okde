package net.cedu.action.examination.invigilator;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.InvigilatorRecordBiz;

import net.cedu.entity.examination.InvigilatorRecord;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonListInvigilatorrecordAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8894437238717456141L;
	@Autowired
	private InvigilatorRecordBiz invigilatorrecordbiz;
	private int invigilatorId;
	private InvigilatorRecord invigilatorrecord=new InvigilatorRecord();
	PageResult<InvigilatorRecord> result=new PageResult<InvigilatorRecord>();
	public PageResult<InvigilatorRecord> getResult() {
		return result;
	}
	public void setResult(PageResult<InvigilatorRecord> result) {
		this.result = result;
	}
	public int getInvigilatorId() {
		return invigilatorId;
	}
	public void setInvigilatorId(int invigilatorId) {
		this.invigilatorId = invigilatorId;
	}
	public InvigilatorRecord getInvigilatorrecord() {
		return invigilatorrecord;
	}
	public void setInvigilatorrecord(InvigilatorRecord invigilatorrecord) {
		this.invigilatorrecord = invigilatorrecord;
	}
	
	
	//分页结果
	@Action(value="list_invigilatorrecord",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute()
	{
		System.out.println(invigilatorId);
		try {
			result.setList(invigilatorrecordbiz.findByConditions(invigilatorId, invigilatorrecord, result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
		
	}
	
	
	//分页数量
	@Action(value="count_invigilatorrecord",
			results={
				@Result(type="json", name = "success",params={"contentType","text/json"}
					   )
			})	
	public String count()
	{
		try {
			result.setRecordCount(invigilatorrecordbiz.findInvigilatorRecordPageCount(invigilatorId, invigilatorrecord, result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	
	

}
