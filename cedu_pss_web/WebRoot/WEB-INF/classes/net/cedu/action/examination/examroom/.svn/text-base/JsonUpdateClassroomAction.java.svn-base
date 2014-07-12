package net.cedu.action.examination.examroom;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.ClassRoomBiz;
import net.cedu.entity.examination.ClassRoom;

import com.opensymphony.xwork2.ModelDriven;
@ParentPackage("json-default")
public class JsonUpdateClassroomAction extends BaseAction implements
		ModelDriven<ClassRoom> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5204070342259384638L;
	private ClassRoomBiz classroombiz;
	private ClassRoom classroom=new ClassRoom();
	
	private int id;
	
	@Action(value = "update_classroom", results = { @Result(name = "success", type = "json", params = {
					"contentType", "text/json"}) })
	public String updateClassroom(){
			try {
				classroom=classroombiz.findClassroomById(id);				
				if(classroom!=null){
					classroombiz.updateClassroom(classroom);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
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

	public ClassRoom getModel(){
		
		return classroom;
	}

}
