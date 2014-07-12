package net.cedu.action.examination.invigilator;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.examination.InvigilatorBiz;
import net.cedu.common.il8n.ResourcesTool;
import net.cedu.entity.admin.User;
import net.cedu.entity.examination.Invigilator;
/**
 * 监考老师详情
 * 
 *
 */
public class ViewInvigilatorAction extends BaseAction implements ModelDriven<Invigilator>{

	private static final long serialVersionUID = 8073791778496107728L;
	@Autowired
	private InvigilatorBiz invigilatorbiz;
	private Invigilator invigilator;
	private int id;
	@Autowired
	private UserBiz useriz;                     //院校联系人Biz
	private User updateuserinfo;                //最后修改用户
	private User userinfo;                      
	
	public String execute()
	{
		try {
			invigilator=invigilatorbiz.findByInvigilatorId(id);
			updateuserinfo=useriz.findUserById(invigilator.getUpdaterId());
			userinfo=useriz.findUserById(invigilator.getCreatorId());
			if(null==invigilator.getPhoto())
			{
				invigilator.setPhoto(ResourcesTool.getText("admin","photo.default.view"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return SUCCESS;
	}
	public User getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(User userinfo) {
		this.userinfo = userinfo;
	}
	public User getUpdateuserinfo() {
		return updateuserinfo;
	}
	public void setUpdateuserinfo(User updateuserinfo) {
		this.updateuserinfo = updateuserinfo;
	}
	public Invigilator getModel() {
		
		return invigilator;
	}
	
	public Invigilator getInvigilator() {
		return invigilator;
	}


	public void setInvigilator(Invigilator invigilator) {
		this.invigilator = invigilator;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
