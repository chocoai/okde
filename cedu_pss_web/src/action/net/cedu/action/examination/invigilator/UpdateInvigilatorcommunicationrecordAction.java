package net.cedu.action.examination.invigilator;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.InvigilatorCommunicationRecordBiz;
import net.cedu.entity.examination.InvigilatorCommunicationRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateInvigilatorcommunicationrecordAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8385590938878216527L;
	@Autowired
	private InvigilatorCommunicationRecordBiz invigilatorcommunicationrecordbiz;
	private int id;
	private InvigilatorCommunicationRecord invigilatorcommunicationrecord;

	public InvigilatorCommunicationRecord getInvigilatorcommunicationrecord() {
		return invigilatorcommunicationrecord;
	}
	public void setInvigilatorcommunicationrecord(
			InvigilatorCommunicationRecord invigilatorcommunicationrecord) {
		this.invigilatorcommunicationrecord = invigilatorcommunicationrecord;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String exceute(){
		try {
			invigilatorcommunicationrecord=invigilatorcommunicationrecordbiz.findByInvigilatorCommunicationRecordId(id);
			if(invigilatorcommunicationrecord!=null){invigilatorcommunicationrecordbiz.updateInvigilatorcommunicationrecord(invigilatorcommunicationrecord);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	

}
