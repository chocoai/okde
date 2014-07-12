package net.cedu.action.admin.privilege.modular;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.privilege.ModularBiz;
import net.cedu.entity.admin.privilege.Modular;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 根据条件查询学习中心列表_分页
 * @return
 * @throws Exception
 */
@ParentPackage("json-default")
public class JsonPageListModularAction extends BaseAction
{
	private static final long serialVersionUID = -1813626596721638819L;

	@Autowired
	private ModularBiz modularBiz;
	
	private Modular modular=new Modular();
	
	// 分页结果
	private PageResult<Modular> result = new PageResult<Modular>();
	
	@Action(value="page_list_modular", 
			results={
			@Result(name = "success", type = "json", 
					params={
					"contentType",  "text/json"
					} )})
	public String execute()throws Exception
	{
		result.setList(modularBiz.findListByCondition(result, modular));
		return SUCCESS;
	}

	public PageResult<Modular> getResult() {
		return result;
	}

	public void setModular(Modular modular) {
		this.modular = modular;
	}
}
