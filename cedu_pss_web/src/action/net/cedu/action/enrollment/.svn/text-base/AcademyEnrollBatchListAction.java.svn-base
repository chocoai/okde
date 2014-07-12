package net.cedu.action.enrollment;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;

import org.springframework.beans.factory.annotation.Autowired;
public class AcademyEnrollBatchListAction extends BaseAction{
	
	private static final long serialVersionUID = -8150002874235831519L;

	@Autowired
	private AcademyBiz academyBiz;
	
	private int academyId;
	private String academyName;

	/**
	 * 院校招生(学籍)批次
	 * @return
	 * @throws Exception
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
