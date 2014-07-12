package net.cedu.action.academy;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.academy.AcademyLinkManBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.academy.AcademyLinkMan;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 新增院校联系人
 * 
 * @author gaolei
 * 
 */

public class AddAcademyLinkManAction extends BaseAction {

	
	@Autowired
	private AcademyBiz academybiz;              //院校Biz
	@Autowired
	private AcademyLinkManBiz academylinkmanbiz;//院校联系人Biz
	private Academy academy;                    //院校实体
	private AcademyLinkMan academylinkman;      //院校联系人实体
	private String msg;                         //消息提示
	private int id;                             //院校ID
	
	
	@Action(results = { @Result(name = "input",location = "add_academy_link_man.jsp"),
						@Result(name = "addfalse",type="dispatcher",location = "add_academy_link_man.jsp"),
						@Result(name = "view",type="redirect", location = "view_academy?id=${id}")})
	public String excute() throws Exception
	{
		//执行get请求
		if(super.isGetRequest())
		{
			//获取该联系人详细信息
			academy=academybiz.findAcademyById(id);
			return INPUT;
		}
		if(academylinkman!=null)
		{
			if(academylinkman.getName()!=null && academylinkman.getMobile()!=null)
			{
				//查询是否存在相同联系人
				int count=academylinkmanbiz.getCountsByAcademyLinkMan(academylinkman.getAcademyId(),academylinkman.getName(),academylinkman.getMobile());
				if(count==0)
				{
						academylinkman.setCreatorId(super.getUser().getUserId());
						academylinkman.setCreatedTime(new Date());
						//新增院校联系人
						academylinkmanbiz.addAcademyLinkMan(academylinkman);
				}
				else
				{
						msg="alert('联系人已存在')";
						return "addfalse";
				}
			}	
		}
		//参数赋值
		id=academylinkman.getAcademyId();
		return "view";
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Academy getAcademy() {
		return academy;
	}


	public void setAcademy(Academy academy) {
		this.academy = academy;
	}


	public AcademyLinkMan getAcademylinkman() {
		return academylinkman;
	}


	public void setAcademylinkman(AcademyLinkMan academylinkman) {
		this.academylinkman = academylinkman;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	

	
}
