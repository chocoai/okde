package net.cedu.action.examination.examroom;





import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.ClassRoomBiz;
import net.cedu.entity.examination.ClassRoom;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;


public class UpdateClassroomAction extends BaseAction 
	 {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5204070342259384638L;
	@Autowired
	private ClassRoomBiz classroombiz;
	private ClassRoom classroom;
	private int id;
	
	@Action(results = {
			@Result(name = "input", location = "update_classroom.jsp"),
			//@Result(name = "success", type = "redirect", location = "index_classroom?tab=2")
			})
	public String execute() throws Exception {

		if (super.isGetRequest()) {
			// 获取该详细数据
			classroom=classroombiz.findClassroomById(id);	
			return INPUT;
		}
		ClassRoom er=new ClassRoom();
		try {
			// 获取该原始详细数据
			er = classroombiz.findClassroomById(id);
			if (classroom != null) {
				er.setCapacity(classroom.getCapacity());
				er.setFeeStandard(classroom.getFeeStandard());
				er.setName(classroom.getName());
				er.setFeeType(classroom.getFeeType());
				er.setPhoto(classroom.getPhoto());
			}
			er.setUpdaterId(super.getUser().getUserId());
			er.setUpdatedTime(new Date());
			// 执行修改
			classroombiz.updateClassroom(er);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return SUCCESS;
	}
	
		
	public ClassRoom getClassroom() {
		return classroom;
	}

	public void setClassroom(ClassRoom classroom) {
		this.classroom = classroom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

}
