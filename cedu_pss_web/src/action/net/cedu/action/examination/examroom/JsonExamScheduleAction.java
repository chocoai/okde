package net.cedu.action.examination.examroom;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.ExamAreaBiz;
import net.cedu.biz.examination.ExamScheduleBiz;
import net.cedu.entity.examination.ExamArea;
import net.cedu.entity.examination.ExamSchedule;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;



@ParentPackage("json-default")
public class JsonExamScheduleAction extends BaseAction
		 {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ExamScheduleBiz examschedulebiz;
	private ExamSchedule examschedule = new ExamSchedule();
	private int examBatchId;
	private int examAreaId;
	private int inspector;
	private Date startTime;
	private Date endTime;
	private double actualCost;
	private int inspectorMark;
	private double planedCost;
	@Autowired
	private ExamAreaBiz examareabiz;
	private ExamArea exameara = new ExamArea();
	private int branchId;
	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	private String code;
	private String name;
	private String address;
	private int id;
	private int bid;
	private double zhi;
	private int grades;
	private int scheduleid;
	public int getScheduleid() {
		return scheduleid;
	}

	public void setScheduleid(int scheduleid) {
		this.scheduleid = scheduleid;
	}

	public int getGrades() {
		return grades;
	}

	public void setGrades(int grades) {
		this.grades = grades;
	}

	public double getZhi() {
		return zhi;
	}

	public void setZhi(double zhi) {
		this.zhi = zhi;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	private boolean results = false;

	public boolean isResults() {
		return results;
	}

	public void setResults(boolean results) {
		this.results = results;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ExamArea getExameara() {
		return exameara;
	}

	public void setExameara(ExamArea exameara) {
		this.exameara = exameara;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public int getInspectorMark() {
		return inspectorMark;
	}

	public void setInspectorMark(int inspectorMark) {
		this.inspectorMark = inspectorMark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ExamSchedule getExamschedule() {
		return examschedule;
	}

	public void setExamschedule(ExamSchedule examschedule) {
		this.examschedule = examschedule;
	}

	public int getExamBatchId() {
		return examBatchId;
	}

	public void setExamBatchId(int examBatchId) {
		this.examBatchId = examBatchId;
	}

	public int getExamAreaId() {
		return examAreaId;
	}

	public void setExamAreaId(int examAreaId) {
		this.examAreaId = examAreaId;
	}

	public int getInspector() {
		return inspector;
	}

	public void setInspector(int inspector) {
		this.inspector = inspector;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public double getPlanedCost() {
		return planedCost;
	}

	public void setPlanedCost(double planedCost) {
		this.planedCost = planedCost;
	}
	public double getActualCost() {
		return actualCost;
	}

	public void setActualCost(double actualCost) {
		this.actualCost = actualCost;
	}
	@Action(value ="add_examschedule", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String AddExamSchedule() throws Exception {

		try {
			System.out.println(startTime+","+endTime);
			if (startTime != null) {
				examschedule.setStartTime(startTime);
			}
			if (endTime != null) {
				examschedule.setEndTime(endTime);
			}
            examschedule.setPlanedCost(planedCost);
			examschedule.setExamBatchId(examBatchId);
			examschedule.setExamAreaId(examAreaId);
			examschedule.setCreatorId(super.getUser().getUserId());
			examschedule.setInspector(inspector);
			examschedule.setCreatedTime(new Date());
			examschedule.setInspectorMark(0);
			examschedule.setDeleteFlag(0);
			
			// 执行添加操作
			examschedulebiz.createNew(examschedule);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Action(value ="add_exameara", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String AddExamEara() throws Exception {

		try {
			if (code != null) {
				exameara.setCode(code);
			}
			if (address != null) {
				exameara.setAddress(address);
			}
			if (name != null) {
				exameara.setName(name);
			}
           exameara.setBranchId(branchId);
			exameara.setCreatedTime(new Date());
			exameara.setCreatorId(super.getUser().getUserId());
			exameara.setUpdaterId(super.getUser().getUserId());
			exameara.setDeleteFlag(0);
			System.out.println(branchId+","+code+","+address);

			// 执行添加操作
			if(exameara!=null){
			examareabiz.createNew(exameara);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Action(value ="delete_examschedule", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String DeleteExamschedule() {
		try {
			
			Object obj = examschedulebiz.deleteExamScheduleById(scheduleid);
			if (null != obj) {
				results = true;
			} else {
				results = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Action(value="update_actualcost",results={@Result(name="success",type="json",params={"contentType","text/json"})})
	public String Updateactualcost(){
		 try {
			 System.out.println(bid+","+zhi);
			examschedule=examschedulebiz.findByExamScheduleId(bid);
			 if(examschedule!=null){
				 examschedule.setActualCost(zhi);
				 examschedulebiz.updateExamSchedule(examschedule);
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
		
	}
	@Action(value="update_inspector_mark",results={@Result(name="success",type="json",params={"contentType","text/json"})})
	public String Updatemark(){
		 try {
			 System.out.println(bid+","+grades);
			examschedule=examschedulebiz.findByExamScheduleId(bid);
			 if(examschedule!=null){
				 examschedule.setInspectorMark(grades);
				 examschedulebiz.updateExamSchedule(examschedule);
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
		
	}

	


}
