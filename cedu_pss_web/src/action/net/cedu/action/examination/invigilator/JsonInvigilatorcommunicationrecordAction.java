package net.cedu.action.examination.invigilator;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.InvigilatorCommunicationRecordBiz;
import net.cedu.entity.examination.InvigilatorCommunicationRecord;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonInvigilatorcommunicationrecordAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4410950583185846887L;
	@Autowired
	private InvigilatorCommunicationRecordBiz invigilatorrecordbiz;
	private String content;
	private int id;
	private int invigilatorId;
	public int getInvigilatorId() {
		return invigilatorId;
	}
	public void setInvigilatorId(int invigilatorId) {
		this.invigilatorId = invigilatorId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	private InvigilatorCommunicationRecord invigilatorcommunicationrecord=new InvigilatorCommunicationRecord();
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Action(value ="add_communicationrecord", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String Add() throws Exception {

        if(content!=null){
        	invigilatorcommunicationrecord.setContent(content);
        }
        invigilatorcommunicationrecord.setInvigilatorId(invigilatorId);
        invigilatorcommunicationrecord.setCreaterId(super.getUser().getUserId());
        invigilatorcommunicationrecord.setCreateTime(new Date());
        invigilatorcommunicationrecord.setDeleteFlag(0);
        if(invigilatorcommunicationrecord!=null){
        	invigilatorrecordbiz.createNew(invigilatorcommunicationrecord);
        }
			// 执行添加操作
		
		return SUCCESS;
	}
	
	@Action(value ="delete_invigilatorcommunicationrecord", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" })})
	public String Deleterecord(){
		try {
			Object obj=invigilatorrecordbiz.deleteInvigilatorCommunicationRecordById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	}
	
	
	
	
	


