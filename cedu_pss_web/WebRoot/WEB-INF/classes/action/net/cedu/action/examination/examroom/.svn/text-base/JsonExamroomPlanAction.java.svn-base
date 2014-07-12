package net.cedu.action.examination.examroom;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.ExamPlanBiz;
import net.cedu.biz.examination.ExamroomPlanBiz;
import net.cedu.biz.examination.InvigilatorBiz;
import net.cedu.entity.examination.ExamPlan;
import net.cedu.entity.examination.ExamroomPlan;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;


@ParentPackage("json-default")
public class JsonExamroomPlanAction extends BaseAction 
		 {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8955838754052510418L;
	@Autowired
	private ExamroomPlanBiz examroomplanbiz;
	private ExamroomPlan examroomplan=new ExamroomPlan();
	@Autowired
	private ExamPlanBiz examplanbiz;
	private ExamPlan examplan=new ExamPlan();
	public ExamPlan getExamplan() {
		return examplan;
	}
	public void setExamplan(ExamPlan examplan) {
		this.examplan = examplan;
	}
	private int examroomId;
	private int bid;
	
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public int getExamroomId() {
		return examroomId;
	}
	public void setExamroomId(int examroomId) {
		this.examroomId = examroomId;
	}
	private int classroomId;
	private String invigilatorIds;
	private int studentNo;
	public int getClassroomId() {
		return classroomId;
	}
	public void setClassroomId(int classroomId) {
		this.classroomId = classroomId;
	}
	public String getInvigilatorIds() {
		return invigilatorIds;
	}
	public void setInvigilatorIds(String invigilatorIds) {
		this.invigilatorIds = invigilatorIds;
	}
	public int getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(int studentNo) {
		this.studentNo = studentNo;
	}
	public boolean isIsart() {
		return isart;
	}
	public void setIsart(boolean isart) {
		this.isart = isart;
	}
	private boolean isart=true;
	public ExamroomPlan getExamroomplan() {
		return examroomplan;
	}
	public void setExamroomplan(ExamroomPlan examroomplan) {
		this.examroomplan = examroomplan;
	}
	
	@Action(value="add_examroomplan",results={@Result(name="success",type="redirect",location="kaochang_anpai.jsp?id=${bid}")})
	public String exceute(){
		System.out.println(bid);
		
		System.out.println(studentNo);
		examroomplan.setExamPlanId(bid);
		examroomplan.setStudentNo(studentNo);
		examroomplan.setClassroomId(classroomId);
		examroomplan.setExamRoomId(examroomId);
		examroomplan.setInvigilatorIds(invigilatorIds);
		
		examroomplan.setCreatorId(super.getUser().getUserId());
		examroomplan.setDeleteFlag(0);
		if(!isart){
			return INPUT;
		}
		try {
			isart=examroomplanbiz.createNew(examroomplan);
			examplan=examplanbiz.findByExamPlanId(examroomplan.getExamPlanId());
			examplan.setStatus(1);
			examplan.setInvigilatorIds(examroomplan.getInvigilatorIds());
			examplanbiz.updateExamPlan(examplan);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
		
	}
	

}
