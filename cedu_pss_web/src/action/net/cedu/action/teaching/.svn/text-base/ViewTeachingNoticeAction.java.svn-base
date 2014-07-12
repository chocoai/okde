package net.cedu.action.teaching;

import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.teaching.TeachingNoticeBiz;
import net.cedu.entity.teaching.TeachingNotice;

public class ViewTeachingNoticeAction extends BaseAction{
	@Autowired
	private  TeachingNoticeBiz  teachingNoticeBiz;
	TeachingNotice  teachingNotice=new TeachingNotice();
	public String execute()
	{
		try
		{
			teachingNotice=teachingNoticeBiz.findTeachingNoticeById(teachingNotice.getId());
			return SUCCESS;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public TeachingNotice getTeachingNotice() {
		return teachingNotice;
	}
	public void setTeachingNotice(TeachingNotice teachingNotice) {
		this.teachingNotice = teachingNotice;
	}
	
}
