package net.cedu.action.examination.examroom;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.ExamRoomBiz;

import net.cedu.entity.examination.ExamRoom;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;



public class UpdateExamroomAction extends BaseAction  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7432403668912380051L;

	@Autowired
	private ExamRoomBiz examroombiz;
	private ExamRoom examroom;
	private int id;

	@Action(results = {
			@Result(name = "input", location = "update_examroom.jsp"),
			@Result(name = "success", type = "redirect", location = "index_exam_room") })
	public String excute() throws Exception {

		if (super.isGetRequest()) {
			// 获取该详细数据
			examroom = examroombiz.findByExamroomId(id);
			return INPUT;
		}
		ExamRoom er=new ExamRoom();
		try {
			// 获取该原始详细数据
			er = examroombiz.findByExamroomId(examroom.getId());
			if (examroom != null) {
				er.setIsLongTerm(examroom.getIsLongTerm());
				er.setName(examroom.getName());
				er.setLinkman(examroom.getLinkman());
				er.setPhone(examroom.getPhone());
				er.setHasInvigilatorSeason(examroom.getHasInvigilatorSeason());
				er.setIsSignable(examroom.getIsSignable());
				er.setLongTermSeason(examroom.getLongTermSeason());
				er.setCode(examroom.getCode());
				er.setSignableSeason(examroom.getSignableSeason());
				er.setHasInvigilator(examroom.getHasInvigilator());
				er.setAddress(examroom.getAddress());
				er.setCode(examroom.getCode());
				er.setEmail(examroom.getEmail());
			   er.setUpdaterId(super.getUser().getUserId());
			  er.setUpdatedTime(new Date());
			id=examroom.getId();
			// 执行修改
			examroombiz.updateExamroom(er);}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return SUCCESS;
	}

	public ExamRoom getExamroom() {
		return examroom;
	}

	public void setExamroom(ExamRoom examroom) {
		this.examroom = examroom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

}
