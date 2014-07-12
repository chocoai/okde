package net.cedu.action.book;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.DeliveredWayBiz;
import net.cedu.entity.book.DeliveredWay;
import net.cedu.model.page.PageResult;
/**
 * 查询配送方式列表_分页
 * @author XFY
 *
 */
@ParentPackage("json-default")
public class JsonPageListDeliveredWayAction extends BaseAction {
 
	private static final long serialVersionUID = -2975952379375684226L;

	@Autowired
	private DeliveredWayBiz deliveredwaybiz;
	
	private PageResult<DeliveredWay> result = new PageResult<DeliveredWay>();
	
	@Action(value="page_list_deliveredway",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute()
	{
		try {
			result.setList(deliveredwaybiz.findAllDeliveredWay(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public PageResult<DeliveredWay> getResult() {
		return result;
	}

	public void setResult(PageResult<DeliveredWay> result) {
		this.result = result;
	}

	

	
}
