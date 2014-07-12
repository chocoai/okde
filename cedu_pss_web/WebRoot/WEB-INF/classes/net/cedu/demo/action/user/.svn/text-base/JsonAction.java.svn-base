package net.cedu.demo.action.user;

import net.cedu.action.BaseAction;
import net.cedu.demo.biz.DUserBiz;
import net.cedu.demo.entity.DCredit;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * demo
 * @author yangdongdong
 *
 */
@ParentPackage("json-default")
public class JsonAction extends BaseAction {
	@Autowired
	private DUserBiz duserBiz;

	// 分页结果
	private PageResult<DCredit> result = new PageResult<DCredit>();

	private int type;

	@Action(value = "userjsoncount", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String userJsonCount() throws Exception {
		// 查询数量
		result.setRecordCount(duserBiz.findCreditCountByDetails(type, result));
		return SUCCESS;
	}

	@Action(value = "userjsonlist", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String userJsonList() throws Exception {
		// 查询集合
		result.setList(duserBiz.findCreditByDetails(type, result));
		return SUCCESS;
	}

	public PageResult<DCredit> getResult() {
		return result;
	}

	public void setResult(PageResult<DCredit> result) {
		this.result = result;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
