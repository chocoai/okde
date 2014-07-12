package net.cedu.action.examination.examroom;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.biz.examination.ExamRoomBiz;
import net.cedu.common.Constants;
import net.cedu.common.enums.CodeEnum;
import net.cedu.entity.examination.ExamRoom;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 添加考场
 * @author 
 *
 */
public class AddExamRoomAction extends BaseAction { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 3496452536576015018L;
	@Autowired
	private ExamRoomBiz examroombiz;
	private ExamRoom examroom;
  
	private boolean addrlt=true;
	//private boolean results=true;
	@Autowired
	private BuildCodeBiz buildCodeBiz;//编码_业务层接口
	@Action(value="add_exam_room",results={@Result(name="success",type="redirect",location="index_exam_room")})
	public String execute() throws Exception{
		if(isGetRequest()){
			return INPUT;
		}
		try {
			examroom.setCreatedTime(new Date());
			examroom.setCode(buildCodeBiz.getCodes(CodeEnum.ExamRoomTB.getCode(),CodeEnum.ExamRoom.getCode()));
			examroom.setDeleteFlag(Constants.DELETE_FALSE);
			examroom.setRoomCount(0);
			examroom.setUpdatedTime(new Date());
			examroom.setStatus(0);
			examroom.setCreatorId(super.getUser().getUserId());
			examroom.setUpdaterId(super.getUser().getUserId());
			
			addrlt=examroombiz.createNew(examroom);
			if(!addrlt){
				return INPUT;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//results=false;
			return INPUT;
		}
		return SUCCESS;
	}
	public ExamRoom getExamroom() {
		return examroom;
	}
	public void setExamroom(ExamRoom examroom) {
		this.examroom = examroom;
	}

}
