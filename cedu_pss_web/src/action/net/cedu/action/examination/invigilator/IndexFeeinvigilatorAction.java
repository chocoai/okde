package net.cedu.action.examination.invigilator;

import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.InvigilatorBiz;
import net.cedu.entity.examination.Invigilator;

public class IndexFeeinvigilatorAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6329024347628137485L;
	@Autowired
	private InvigilatorBiz invigilatorbiz;
	private Invigilator invigilator;
	public Invigilator getInvigilator() {
		return invigilator;
	}
	public void setInvigilator(Invigilator invigilator) {
		this.invigilator = invigilator;
	}
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String execute(){
		
			try {
				invigilator=invigilatorbiz.findByInvigilatorId(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		
		return SUCCESS;
	}

}
