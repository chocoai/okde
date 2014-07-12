package net.cedu.action.examination.invigilator;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.InvigilatorCommunicationRecordBiz;
import net.cedu.entity.examination.InvigilatorCommunicationRecord;

import org.springframework.beans.factory.annotation.Autowired;

public class ViewInvigilatorcommunicationrecordAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4313433570412633369L;
	@Autowired
	private InvigilatorCommunicationRecordBiz invigilatorcommunicationrecordbiz;
	private int id;
	private InvigilatorCommunicationRecord invigilatorcommunicationrecord=new InvigilatorCommunicationRecord();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public InvigilatorCommunicationRecord getInvigilatorcommunicationrecord() {
		return invigilatorcommunicationrecord;
	}
	public void setInvigilatorcommunicationrecord(
			InvigilatorCommunicationRecord invigilatorcommunicationrecord) {
		this.invigilatorcommunicationrecord = invigilatorcommunicationrecord;
	}
	public String execute(){
		try {
			invigilatorcommunicationrecord=invigilatorcommunicationrecordbiz.findByInvigilatorCommunicationRecordId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
		
	}
	

}
