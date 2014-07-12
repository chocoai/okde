package net.cedu.action.teaching;

import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.teaching.DiplomaBiz;
import net.cedu.entity.teaching.Diploma;

public class ViewDiplomaImgAction extends BaseAction {
	private Diploma diploma=new Diploma();
	private int id;
	private boolean isback=false;
	@Autowired
	private DiplomaBiz diplomaBiz;
	public String execute()
	{
		try
		{
			diploma=diplomaBiz.findDiplomaByStudentId(id);
			return SUCCESS;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return INPUT;
	}
	public Diploma getDiploma() {
		return diploma;
	}
	public void setDiploma(Diploma diploma) {
		this.diploma = diploma;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isIsback() {
		return isback;
	}
	public void setIsback(boolean isback) {
		this.isback = isback;
	}
	
	
}
