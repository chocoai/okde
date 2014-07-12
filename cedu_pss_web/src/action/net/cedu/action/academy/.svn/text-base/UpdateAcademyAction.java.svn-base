package net.cedu.action.academy;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.academy.AcademyLinkManBiz;
import net.cedu.common.Constants;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.academy.AcademyLinkMan;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 修改院校
 * 
 * @author gaolei
 * 
 */

public class UpdateAcademyAction extends BaseAction {

	
	@Autowired
	private AcademyBiz academybiz;              //院校Biz
	@Autowired
	private AcademyLinkManBiz academylinkmanbiz;//院校联系人Biz
	private Academy academy=new Academy();      //院校实体
	private AcademyLinkMan academylinkman;      //院校联系人实体
	private String IsForceFeePolicy;            //是否强制执行收费政策
	private int id;                             //院校ID
	private int scale;                          //院校规模
	
	
	
	/**
	 * 修改院校信息
	 * @return
	 * @throws Exception
	 */
	@Action(results = { @Result(name = "input",location = "update_academy.jsp"),
						@Result(name = "view",type="redirect", location = "view_academy?id=${id}")})
	public String excute() throws Exception
	{
		//执行get请求
		if(super.isGetRequest())
		{
			//获取院校原始数据
			academy=academybiz.findAcademyById(id);
			
			return INPUT;
		}
		 Academy upacademy=new Academy();
		
		
		try {
		//获取原始数据
		upacademy=academybiz.findAcademyById(academy.getId());
	
		if(academy.getName()!=null || !academy.getName().equals("") )
		{	
			upacademy.setName(academy.getName());
		}
		if(academy.getCode()!=null || !academy.getCode().equals("") )
		{	
			upacademy.setCode(academy.getCode());
		}
		if(academy.getShortName()!=null || !academy.getShortName().equals("") )
		{
			upacademy.setShortName(academy.getShortName());
		}
		if(academy.getFoundedYear()!=null || !academy.getFoundedYear().equals("") )
		{
			upacademy.setFoundedYear(academy.getFoundedYear());
		}
		if(academy.getTelephone()!=null || !academy.getTelephone().equals("") )
		{
			upacademy.setTelephone(academy.getTelephone());
		}
		if(academy.getAccount()!=null || !academy.getAccount().equals("") )
		{
			upacademy.setAccount(academy.getAccount());
		}
		if(academy.getWebsite()!=null || !academy.getWebsite().equals("") )
		{
			upacademy.setWebsite(academy.getWebsite());
		}
		if(academy.getAddress()!=null || !academy.getAddress().equals("") )
		{
			upacademy.setAddress(academy.getAddress());
		}
		if(academy.getIntroduction()!=null || !academy.getIntroduction().equals("") )
		{
			upacademy.setIntroduction(academy.getIntroduction());
		}
		//项目经理人
		if(academy.getProjectManagerId()>0)
		{
			upacademy.setProjectManagerId(academy.getProjectManagerId());
		}

		//是否强制执行收费政策  1：是  0：否
		if(IsForceFeePolicy!=null){
			upacademy.setIsForceFeePolicy(Constants.ISNEED_REBATES_TRUE);
		}else
		{
			upacademy.setIsForceFeePolicy(Constants.ISNEED_REBATES_FALSE);
		}
		upacademy.setScale(scale);
		upacademy.setUpdaterId(super.getUser().getUserId());
		upacademy.setUpdatedTime(new Date());
		id=academy.getId();
		//执行修改操作
		academybiz.updateAcademy(upacademy);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return "view";
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

	

	public String getIsForceFeePolicy() {
		return IsForceFeePolicy;
	}

	public void setIsForceFeePolicy(String isForceFeePolicy) {
		IsForceFeePolicy = isForceFeePolicy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	


	
}
