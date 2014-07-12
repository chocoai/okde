/**
 * 文件名：JsonJurisdictionAction.java
 * 包名：net.cedu.action.orgstructure.jurisdiction
 * 工程：cedu_pss_web/教师培训平台
 * 功能： TODO /请自行添加
 *
 * 作者：Administrator    
 * 日期：2011-12-29 下午10:05:23
 *
 * Copyright (c) 2011, 北京弘成教育科技有限公司 All Rights Reserved.
*/
package net.cedu.action.orgstructure.jurisdiction;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.orgstructure.JurisdictionBiz;
import net.cedu.entity.orgstructure.Jurisdiction;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonJurisdictionAction extends BaseAction 
{
	private static final long serialVersionUID = 6341171717364737572L;
	
	@Autowired
	private JurisdictionBiz jurisdictionBiz;
	
	List<String> deplist=new ArrayList<String>();
	
	private int userId;
	private boolean result=false;
	
	@Action(value = "save_jurisdiction", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","result"
			}) })
	public String execute()
	{
		try
		{
			Jurisdiction j=jurisdictionBiz.findById(userId);
			if(null==j)
			{
				j=new Jurisdiction();
				j.setUserId(userId);
				j.setDepartmentIds(getDepCode());
				jurisdictionBiz.addNew(j);
			}
			else
			{
				j.setDepartmentIds(getDepCode());
				jurisdictionBiz.modify(j);
			}
			result=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	private String getDepCode()
	{
//		String depCode="";
		StringBuffer depCodeSB = new StringBuffer("");
//		System.out.println("size:"+deplist.size());
		for(int i=0,k=deplist.size();i<k;i++)
		{
//			System.out.println("@"+deplist.get(i));
			if(0==i){
//				depCode+="@"+deplist.get(i);
				depCodeSB.append("@"+deplist.get(i));
			}
			else{
//				depCode+=deplist.get(i);
				depCodeSB.append(deplist.get(i));
			}
//			depCode+="@";
			depCodeSB.append("@");
		}
		return depCodeSB.toString();
	}

	public void setDeplist(List<String> deplist) {
		this.deplist = deplist;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean getResult() {
		return result;
	}
}
