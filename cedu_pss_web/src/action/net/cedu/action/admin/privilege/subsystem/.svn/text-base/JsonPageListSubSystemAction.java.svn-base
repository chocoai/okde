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
 * 根据条件查询子系统_分页
 * @return
 * @throws Exception
 */
@ParentPackage("json-default")
public class JsonPageListSubSystemAction extends BaseAction
{
	private static final long serialVersionUID = -1813626596721638819L;

	@Autowired
	private SubSystemBiz subSystemBiz;
	
	private SubSystem subSystem=new SubSystem();
	
	// 分页结果
	private PageResult<SubSystem> result = new PageResult<SubSystem>();
	
	@Action(value="page_list_sub_system", 
			results={
			@Result(name = "success", type = "json", 
					params={
					"contentType",  "text/json"
					} )})
	public String execute()throws Exception
	{
		result.setList(subSystemBiz.findListByCondition(result, subSystem));
		return SUCCESS;
	}

	public PageResult<SubSystem> getResult() {
		return result;
	}

	public void setSubSystem(SubSystem subSystem) {
		this.subSystem = subSystem;
	}
}
