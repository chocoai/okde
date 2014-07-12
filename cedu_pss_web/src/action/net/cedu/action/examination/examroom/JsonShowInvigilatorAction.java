package net.cedu.action.examination.examroom;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.InvigilatorBiz;
import net.cedu.entity.enrollment.Major;
import net.cedu.entity.examination.Invigilator;

@ParentPackage("json-default")
public class JsonShowInvigilatorAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6513300854462834541L;
	@Autowired
	private InvigilatorBiz invigilatorbiz;
	private List<Invigilator> invigilatorlist=new ArrayList<Invigilator>();
	
	private boolean lstrltbool=true;
	public boolean isLstrltbool() {
		return lstrltbool;
	}
	public void setLstrltbool(boolean lstrltbool) {
		this.lstrltbool = lstrltbool;
	}
	public List<Invigilator> getInvigilatorlist() {
		return invigilatorlist;
	}
	public void setInvigilatorlist(List<Invigilator> invigilatorlist) {
		this.invigilatorlist = invigilatorlist;
	}
	@SuppressWarnings("unchecked")
	@Action(value="show_invigilator",results={@Result(type="json", name = "success",params={"contentType","text/json"})})
	public String executes()throws Exception
	{
	
		try {
			invigilatorlist = invigilatorbiz.findAllInvigilator();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			lstrltbool=false;
		}
	return "success";
}
	
	}


