package net.cedu.action.examination.invigilator;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.InvigilatorCommunicationRecordBiz;
import net.cedu.entity.examination.InvigilatorCommunicationRecord;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
@ParentPackage("json-default")
public class JsonListInvigilatorcommunicationrecordAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8007376279601159889L;
	@Autowired
	private InvigilatorCommunicationRecordBiz  invigilatorrecordbiz;
	private InvigilatorCommunicationRecord communicationrecord=new InvigilatorCommunicationRecord();
	public InvigilatorCommunicationRecord getCommunicationrecord() {
		return communicationrecord;
	}
	public void setCommunicationrecord(
			InvigilatorCommunicationRecord communicationrecord) {
		this.communicationrecord = communicationrecord;
	}
	private int invigilatorId;
	
	public int getInvigilatorId() {
		return invigilatorId;
	}
	public void setInvigilatorId(int invigilatorId) {
		this.invigilatorId = invigilatorId;
	}
	private   InvigilatorCommunicationRecord invigilatorcommunicationrecord=new  InvigilatorCommunicationRecord();
	public InvigilatorCommunicationRecord getInvigilatorcommunicationrecord() {
		return invigilatorcommunicationrecord;
	}
	public void setInvigilatorcommunicationrecord(
			InvigilatorCommunicationRecord invigilatorcommunicationrecord) {
		this.invigilatorcommunicationrecord = invigilatorcommunicationrecord;
	}
	PageResult<InvigilatorCommunicationRecord> result=new PageResult<InvigilatorCommunicationRecord>();
	
	public PageResult<InvigilatorCommunicationRecord> getResult() {
		return result;
	}
	public void setResult(PageResult<InvigilatorCommunicationRecord> result) {
		this.result = result;
	}
	//分页结果
	@Action(value="list_communicationrecord",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute()
	{
		System.out.println(invigilatorId);
		try {
			result.setList(invigilatorrecordbiz.findByConditions(invigilatorId, communicationrecord, result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
		
	}
	
	
	//分页数量
	@Action(value="count_communicationrecord",
			results={
				@Result(type="json", name = "success",params={"contentType","text/json"}
					   )
			})	
	public String count()
	{
		try {
			result.setRecordCount(invigilatorrecordbiz.findInvigilatorCommunicationRecordPageCount(invigilatorId, communicationrecord, result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

}
