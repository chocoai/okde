package net.cedu.action.examination.examroom;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.ClassRoomBiz;
import net.cedu.entity.examination.ClassRoom;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
@ParentPackage("json-default")
public class JsonIndexClassroomAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -419742619035858246L;
	@Autowired
	private ClassRoomBiz classroombiz;
	private ClassRoom classroom=new ClassRoom();
	public ClassRoom getClassroom() {
		return classroom;
	}
	public void setClassroom(ClassRoom classroom) {
		this.classroom = classroom;
	}
	private String name;
	private int capacity;
	private int examroomId;
	public int getExamroomId() {
		return examroomId;
	}
	public void setExamroomId(int examroomId) {
		this.examroomId = examroomId;
	}
	private double fee;
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	private int feetype;
	public int getFeetype() {
		return feetype;
	}
	public void setFeetype(int feetype) {
		this.feetype = feetype;
	}
	@Action(value="add_classroom" ,results={@Result(name="success",type="json",params={"contentType","text/json"})})
	public String exceute(){
		System.out.println(examroomId+"....."+fee);
		if(name!=null){
			classroom.setName(name);
		}
		if(capacity >0){
			classroom.setCapacity(capacity);
		}
		if(fee >-1){
			classroom.setFeeStandard(fee);
		}
	
		classroom.setExamroomId(examroomId);
		classroom.setFeeType(feetype);
		classroom.setCreateTime(new Date());
		classroom.setCreatorId(super.getUser().getUserId());
		try {
			classroombiz.createNew(classroom);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return SUCCESS;
	}
	public ClassRoom getModel(){
		return classroom;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
}
