package net.cedu.action.basesetting.branchenrollmentway;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.BranchEnrollmentWayBiz;
import net.cedu.entity.basesetting.BranchEnrollmentWay;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 市场途径授权范围
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonUpdateBranchEnrollmentWayAction extends BaseAction implements ModelDriven<BranchEnrollmentWay>
{
	private static final long serialVersionUID = 4384055175835481869L;
	
	@Autowired
	private BranchEnrollmentWayBiz branchEnrollmentWayBiz;
	
	private BranchEnrollmentWay bew=new BranchEnrollmentWay();
	
	private boolean results=false;
	private String ids;
	
	@Action(value = "update_branch_enrollment_way", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute()
	{
		try
		{		
			bew.setEnrollmentWays(ids);
			branchEnrollmentWayBiz.modify(bew);
			results=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public BranchEnrollmentWay getBew() {
		return bew;
	}

	public void setBew(BranchEnrollmentWay bew) {
		this.bew = bew;
	}

	public boolean getResults() {
		return results;
	}

	public BranchEnrollmentWay getModel() {
		return bew;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
