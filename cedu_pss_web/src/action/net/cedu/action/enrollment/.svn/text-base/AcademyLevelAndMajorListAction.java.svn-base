package net.cedu.action.enrollment;

import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;

public class AcademyLevelAndMajorListAction extends BaseAction{

	private static final long serialVersionUID = 1795971770044687220L;
	
	@Autowired
	private AcademyBiz academyBiz;
	
	private int academyId;
	private String academyName;

	/**
	 * 跳转专业层次加载页面
	 */
	public String execute()throws Exception{	
		try {
			academyName = academyBiz.findAcademyById(academyId).getName();
		} catch (Exception e) {
			academyName = "";
		}
		return SUCCESS;
	}
	//-------------------------------------------------get and set methods----------------------------
	
	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public String getAcademyName() {
		return academyName;
	}

	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}

}
