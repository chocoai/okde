package net.cedu.action.basesetting.branchenrollmentway;

import java.util.LinkedList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.common.Constants;
import net.cedu.entity.basesetting.BaseDict;

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
public class JsonTreeBranchEnrollmentWayAction extends BaseAction 
{
	private static final long serialVersionUID = 4384055175835481869L;
	
	@Autowired
	private BaseDictBiz baseDictBiz;
	
	List<BaseDict> trslist=new LinkedList<BaseDict>();
	
	@Action(value = "tree_branch_enrollment_way", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute()
	{
		try
		{		
			trslist=baseDictBiz.findBaseDictsByType(Constants.BASEDICT_STYLE_ENROLLMENTWAY);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public List<BaseDict> getTrslist() {
		return trslist;
	}
}
