package net.cedu.action.examination.examroom;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.ExamCommunicationRecordBiz;
import net.cedu.entity.examination.ExamCommunicationRecord;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("json-default")
public class JsonAddExamcommunicationAction extends BaseAction implements
		ModelDriven<ExamCommunicationRecord> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7936637526177014292L;
	@Autowired
	private ExamCommunicationRecordBiz examrecordbiz;
	private ExamCommunicationRecord examcommunicationrecord=new ExamCommunicationRecord();
	
	private String titles;
	private String contents;
	private int nid;
	
	
	
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public String getTitles() {
		return titles;
	}
	public void setTitles(String titles) {
		this.titles = titles;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	@Action(value="add_examcommunication",results={@Result(name="success",type="redirect",location="exam_communication")})
	public String exceute(){
	
		examcommunicationrecord.setScheduleId(nid);
		examcommunicationrecord.setContent(contents);
		examcommunicationrecord.setTitle(titles);
		examcommunicationrecord.setCreateTime(new Date());
		try {
			
			examrecordbiz.createNew(examcommunicationrecord);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
		
	}
	
	public ExamCommunicationRecord getModel(){
		return examcommunicationrecord;
		
	}

}
