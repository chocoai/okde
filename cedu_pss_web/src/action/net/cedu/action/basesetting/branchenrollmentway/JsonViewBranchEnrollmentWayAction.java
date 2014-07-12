package net.cedu.action.basesetting.branchenrollmentway;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.BranchEnrollmentWayBiz;
import net.cedu.entity.basesetting.BranchEnrollmentWay;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 市场途径授权范围
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonViewBranchEnrollmentWayAction extends BaseAction
{
	private static final long serialVersionUID = 4384055175835481869L;
	
	@Autowired
	private BranchEnrollmentWayBiz branchEnrollmentWayBiz;

	private int branchId;
	
	private List<String> ids=new ArrayList<String>();
	
	@Action(value = "view_branch_enrollment_way", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute()
	{
		try
		{		
			BranchEnrollmentWay bew=branchEnrollmentWayBiz.findByBranchId(branchId);
			if(null!=bew)
			{
				ids=initList(bew.getEnrollmentWays(),"s");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	private List<String> initList(String strings,String keys)
	{
		List<String> list=new ArrayList<String>();
		String temp="";
		int index=-1;
		while(strings.length()>1)
		{
			index=strings.indexOf("@");
			if(0<=index&&strings.length()>index+1)
			{
				strings=strings.substring(index+1);
				temp=strings.substring(0,strings.indexOf("@"));
				temp="_"+keys+"_"+temp;
				list.add(temp);
			}
			else
				break;
		}
		return list;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
}
