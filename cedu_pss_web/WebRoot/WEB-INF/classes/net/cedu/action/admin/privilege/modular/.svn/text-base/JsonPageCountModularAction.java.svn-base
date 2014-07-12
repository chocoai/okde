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
 * 查询模块数量_json
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonPageCountModularAction extends BaseAction 
{
	private static final long serialVersionUID = -8620752958733775163L;

	@Autowired
	private ModularBiz modularBiz;
	
	private Modular modular=new Modular();
	
	// 分页结果
	private PageResult<Modular> result = new PageResult<Modular>();
	
	@Action(value = "page_count_modular", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute() throws Exception 
	{
		result.setRecordCount(modularBiz.findCountByConditionForHQL(modular));
		return SUCCESS;
	}

	public PageResult<Modular> getResult() {
		return result;
	}

	public void setModular(Modular modular) {
		this.modular = modular;
	}
}
