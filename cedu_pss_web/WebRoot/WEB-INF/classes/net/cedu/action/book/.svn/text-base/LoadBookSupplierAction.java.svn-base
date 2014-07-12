package net.cedu.action.book;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.SettlementWayBiz;
import net.cedu.biz.book.SupplierCategoryBiz;
import net.cedu.entity.book.SettlementWay;
import net.cedu.entity.book.SupplierCategory;
/**
 * 书商增加加载
 * @author XFY
 *
 */
public class LoadBookSupplierAction extends BaseAction {
 
	private static final long serialVersionUID = 5683170624848746984L;
	@Autowired
	private SupplierCategoryBiz scbiz;
	@Autowired
	private SettlementWayBiz swbiz;
	
	private List<SupplierCategory> sclist=new ArrayList<SupplierCategory>();
	private List<SettlementWay> swlist=new ArrayList<SettlementWay>();
	
	@Action(value="load_booksupplier",results={@Result(location="add_booksuppliers.jsp")})
	public String execute()
	{
		try {
			sclist=scbiz.findAllSupplierCategory();
			swlist=swbiz.findAllSettlementWay();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public List<SupplierCategory> getSclist() {
		return sclist;
	}

	public void setSclist(List<SupplierCategory> sclist) {
		this.sclist = sclist;
	}

	public List<SettlementWay> getSwlist() {
		return swlist;
	}

	public void setSwlist(List<SettlementWay> swlist) {
		this.swlist = swlist;
	}
	
	
}
