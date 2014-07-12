package net.cedu.action.book;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.DeliveredWayBiz;
import net.cedu.entity.book.DeliveredWay;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 查询配送方式条数_分页
 * @author XFY
 *
 */
@ParentPackage("json-default")
public class JsonPageCountDeliveredWayAction extends BaseAction {
 
	private static final long serialVersionUID = 3142149095749291010L;

	@Autowired
	private DeliveredWayBiz deliveredwaybiz;
	
	private PageResult<DeliveredWay> result = new PageResult<DeliveredWay>();
	/**
	 * 根据条件查询教材分类数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "page_count_deliveredway", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
			
			
	public String execute() throws Exception 
	{
		result.setRecordCount(deliveredwaybiz.findAllDeliveredWayCount(result));
		return SUCCESS;
	}
	public PageResult<DeliveredWay> getResult() {
		return result;
	}
	public void setResult(PageResult<DeliveredWay> result) {
		this.result = result;
	}
	
	
	
	
	}

