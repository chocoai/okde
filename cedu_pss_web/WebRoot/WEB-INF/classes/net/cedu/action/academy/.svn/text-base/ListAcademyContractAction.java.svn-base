package net.cedu.action.academy;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.entity.academy.Academy;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 院校合同
 * 
 * @author gaolei
 * 
 */

public class ListAcademyContractAction extends BaseAction {

	@Autowired
	private AcademyBiz academybiz;                           //院校Biz
	
	private Academy academy;      
	private int id;
	
	@Action(results = { @Result(name = "success", location = "list_academy_contract.jsp") })
	public String excute() throws Exception
	{
		//院校信息
		academy=academybiz.findAcademyById(id);
		return SUCCESS;	
	}

	public Academy getAcademy() {
		return academy;
	}

	public void setAcademy(Academy academy) {
		this.academy = academy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
