package net.cedu.action.teaching;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.teaching.DiplomaBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.teaching.Diploma;

public class TeachingNoticeAction  extends BaseAction{
	
	List<Academy> academyList = new ArrayList<Academy>();
	@Autowired
	private AcademyBiz academyBiz;
	public String execute() throws Exception
	{
		academyList = academyBiz.findAllAcademys();
		return SUCCESS;
	}

	public List<Academy> getAcademyList() {
		return academyList;
	}

	public void setAcademyList(List<Academy> academyList) {
		this.academyList = academyList;
	}
}
