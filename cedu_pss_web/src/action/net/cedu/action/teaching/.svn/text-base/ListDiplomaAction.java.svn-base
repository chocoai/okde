package net.cedu.action.teaching;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.teaching.DiplomaBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.teaching.Diploma;

import org.springframework.beans.factory.annotation.Autowired;

public class ListDiplomaAction extends BaseAction {
	List<Academy> academyList = new ArrayList<Academy>();
	@Autowired
	private AcademyBiz academyBiz;
	List<Diploma> diplomaList =new ArrayList<Diploma>();
	@Autowired
	private DiplomaBiz  diplomaBiz;
	
	public String execute() throws Exception
	{
		academyList = academyBiz.findAllAcademys();
		diplomaList=diplomaBiz.findAllDiplomas();
		return SUCCESS;
	}

	public List<Academy> getAcademyList() {
		return academyList;
	}

	public void setAcademyList(List<Academy> academyList) {
		this.academyList = academyList;
	}

	public List<Diploma> getDiplomaList() {
		return diplomaList;
	}

	public void setDiplomaList(List<Diploma> diplomaList) {
		this.diplomaList = diplomaList;
	}
	
	
	
}
