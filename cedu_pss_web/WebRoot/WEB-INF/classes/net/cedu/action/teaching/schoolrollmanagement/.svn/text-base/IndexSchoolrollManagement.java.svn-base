package net.cedu.action.teaching.schoolrollmanagement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.entity.academy.Academy;
/**
 * 学籍管理对应jsp的Action
 * @author YY
 *
 */
public class IndexSchoolrollManagement extends BaseAction {
 
	private static final long serialVersionUID = 4731330568634000764L;
 
	@Autowired
	private AcademyBiz academyBiz;//院校业务层
	List<Academy> academyList = new ArrayList<Academy>(); //院校集合
	
	@Override
	public String execute() throws Exception {
		academyList=academyBiz.findAllAcademys();
		return super.execute();
	}

	public List<Academy> getAcademyList() {
		return academyList;
	}

	public void setAcademyList(List<Academy> academyList) {
		this.academyList = academyList;
	}
	
	
	
}
