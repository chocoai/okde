package net.cedu.action.book;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.SettlementWayBiz;
import net.cedu.entity.book.SettlementWay;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 结算方式类条数_分页
 * @author XFY
 *
 */
@ParentPackage("json-default")
public class JsonPageCountSettlementWayAction extends BaseAction {
 
	private static final long serialVersionUID = -8584187435748271353L;

	@Autowired
	private SettlementWayBiz settlementwaybiz;
	
	private PageResult<SettlementWay> result = new PageResult<SettlementWay>();
	/**
	 * 根据条件查询结算方式数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "page_count_settlementway", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute() throws Exception 
	{
		result.setRecordCount(settlementwaybiz.findAllSettlementWayCount(result));
		return SUCCESS;
	}
	public PageResult<SettlementWay> getResult() {
		return result;
	}
	public void setResult(PageResult<SettlementWay> result) {
		this.result = result;
	}
	
}
