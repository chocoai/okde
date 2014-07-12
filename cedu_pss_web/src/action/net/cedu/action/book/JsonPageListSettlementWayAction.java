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
 * 查询结算方式列表_分页
 * @author XFY
 *
 */
@ParentPackage("json-default")
public class JsonPageListSettlementWayAction extends BaseAction {
 
	private static final long serialVersionUID = -3026530537804815204L;

	@Autowired
	private SettlementWayBiz settlementwaybiz;
	
	private PageResult<SettlementWay> result = new PageResult<SettlementWay>();
	
	@Action(value="page_list_settlementway",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute()
	{
		try {
			result.setList(settlementwaybiz.findAllSettlementWay(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public PageResult<SettlementWay> getResult() {
		return result;
	}

	public void setResult(PageResult<SettlementWay> result) {
		this.result = result;
	}

	
}
