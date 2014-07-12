package net.cedu.action.admin.privilege.subsystem;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.privilege.SubSystemBiz;
import net.cedu.entity.admin.privilege.SubSystem;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 查询子系统数量_json
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonPageCountSubSystemAction extends BaseAction 
{
	private static final long serialVersionUID = -8620752958733775163L;

	@Autowired
	private SubSystemBiz subSystemBiz;
	
	private SubSystem subSystem=new SubSystem();
	
	// 分页结果
	private PageResult<SubSystem> result = new PageResult<SubSystem>();
	
	@Action(value = "page_count_sub_system", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute() throws Exception 
	{
		result.setRecordCount(subSystemBiz.findCountByConditionForHQL(subSystem));
		return SUCCESS;
	}

	public PageResult<SubSystem> getResult() {
		return result;
	}

	public void setModular(SubSystem subSystem) {
		this.subSystem = subSystem;
	}
}
