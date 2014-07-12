package net.cedu.action.enrollment;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;

import org.springframework.beans.factory.annotation.Autowired;


public class ListMajorAction extends BaseAction
{
	private static final long serialVersionUID = -327023577172744362L;
	
	@Autowired
	private AcademyBiz academyBiz;
	private String academyName;

	private int id;//院校id

	/**
	 * 专业(院校基础设置)
	 * @return
	 * @throws Exception
	 */
	public String execute()throws Exception{	
		try {
			academyName = academyBiz.findAcademyById(id).getName();
		} catch (Exception e) {
			academyName = "";
		}
		return SUCCESS;
	}

	//-------------------------------------------------get and set methods----------------------------
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAcademyName() {
		return academyName;
	}

	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}
	
}
