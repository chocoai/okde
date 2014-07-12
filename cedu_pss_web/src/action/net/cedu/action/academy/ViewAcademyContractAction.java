package net.cedu.action.academy;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.academy.AcademyContractBiz;
import net.cedu.biz.admin.UserBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.academy.AcademyContract;
import net.cedu.entity.academy.AcademyLinkMan;
import net.cedu.entity.admin.User;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * 院校沟通记录
 * 
 * @author gaolei
 * 
 * */
public class ViewAcademyContractAction extends BaseAction {

	@Autowired
	private AcademyBiz academybiz;                       //院校Biz
	private Academy academy;                             //院校实体
	@Autowired
	private UserBiz userbiz;                             //院校Biz
	@Autowired
	private AcademyContractBiz academycontractbiz;       //合同Biz
	private AcademyContract academycontract;             //合同实体
	private User userinfo;                               //沟通人            
	private int id;                                      //获取连接参数ID

	
	
	

	
	
	@Action(results = {@Result(name = "success", location = "view_academy_contract.jsp")
					  
	})
			public String excute() throws Exception
			{
				if(super.isGetRequest())
				{
					//院校信息
					academy=academybiz.findAcademyById(id);
					//获取院校数据
					academycontract=academycontractbiz.findAcademyContractById(id);
					//获取该合同下的签约人
					if(academycontract!=null)
					{
						userinfo=userbiz.findUserById(academycontract.getSibscriberId());
					}
					return SUCCESS;
				}
				return SUCCESS;
			}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public User getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(User userinfo) {
		this.userinfo = userinfo;
	}


	public AcademyContract getAcademycontract() {
		return academycontract;
	}


	public void setAcademycontract(AcademyContract academycontract) {
		this.academycontract = academycontract;
	}


	public Academy getAcademy() {
		return academy;
	}


	public void setAcademy(Academy academy) {
		this.academy = academy;
	}



	
	
}
