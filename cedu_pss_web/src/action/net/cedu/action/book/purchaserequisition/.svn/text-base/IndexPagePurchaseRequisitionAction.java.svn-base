package net.cedu.action.book.purchaserequisition;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.entity.academy.Academy;

public class IndexPagePurchaseRequisitionAction extends BaseAction {
 
	private static final long serialVersionUID = 3857742217787704173L;
	@Autowired
	private AcademyBiz academyBiz; //院校业务层
	private List<Academy> academies=new ArrayList<Academy>(); //院校集合
	
	
	public String execute() throws Exception {
		//查询所有院校
		academies=academyBiz.findAllAcademys(); 
		return SUCCESS;
	}
	public List<Academy> getAcademies() {
		return academies;
	}
	public void setAcademies(List<Academy> academies) {
		this.academies = academies;
	}
	
	
	
}
