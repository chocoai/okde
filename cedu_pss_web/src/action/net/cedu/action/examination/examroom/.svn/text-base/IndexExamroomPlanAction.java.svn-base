package net.cedu.action.examination.examroom;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.entity.basesetting.Level;

public class IndexExamroomPlanAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5686238931418542738L;
	@Autowired
	private LevelBiz levelbiz;
	private List<Level> levelist=new ArrayList<Level>();
	public String execute()throws Exception
	{
		if(super.isGetRequest())
		{	
			levelist=levelbiz.findAllLevels();
			return INPUT;
		}
		return SUCCESS;
	}
	public List<Level> getLevelist() {
		return levelist;
	}
	public void setLevelist(List<Level> levelist) {
		this.levelist = levelist;
	}

}
