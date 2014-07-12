package net.cedu.action.book.cedubookorder;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.CeduBookOrderBiz;
import net.cedu.entity.book.CeduBookOrder;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 总部代书商发货
 * @author YY
 *
 */
@ParentPackage("json-default")
public class JsonListOrCountCeduBookOrderAction extends BaseAction implements ModelDriven<CeduBookOrder>{
 
	private static final long serialVersionUID = -1347404248966602241L;
	@Autowired
	private CeduBookOrderBiz ceduBookOrderBiz; //发货单业务实体
	
	private List<CeduBookOrder> list=new ArrayList<CeduBookOrder>();//发货单集合
	PageResult<CeduBookOrder> result = new PageResult<CeduBookOrder>(); // 分頁參數
	private CeduBookOrder  ceduBookOrder=new CeduBookOrder(); //发货单实体
	 
	/**
	 * 分页集合
	 * @return list;
	 */
	@Action(value = "list_cedubookorder", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String list()
	{	

		try {
			list = ceduBookOrderBiz.findPageListByCeduBookOrder(ceduBookOrder, result);
 
			result.setList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	/**
	 * 分页数量
	 * @return list;
	 */
	@Action(value = "count_cedubookorder", results = { @Result(type = "json", name = "success", params = {
			"contentType", "text/json" }) })
	public String count()
	{
		try{
		result.setRecordCount(ceduBookOrderBiz
				.findPageCountByCeduBookOrder(ceduBookOrder, result));
	} catch (Exception e) {
		e.printStackTrace();
		return INPUT;
	}
	return SUCCESS;	
	}
	

	public CeduBookOrder getModel() {
		 
		return ceduBookOrder;
	}
	public List<CeduBookOrder> getList() {
		return list;
	}
	public void setList(List<CeduBookOrder> list) {
		this.list = list;
	}
	public PageResult<CeduBookOrder> getResult() {
		return result;
	}
	public void setResult(PageResult<CeduBookOrder> result) {
		this.result = result;
	}
	public CeduBookOrder getCeduBookOrder() {
		return ceduBookOrder;
	}
	public void setCeduBookOrder(CeduBookOrder ceduBookOrder) {
		this.ceduBookOrder = ceduBookOrder;
	}
	
	
}
