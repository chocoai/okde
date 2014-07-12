package net.cedu.action.book;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookSupplierBiz;
import net.cedu.biz.book.SettlementWayBiz;
import net.cedu.biz.book.SupplierCategoryBiz;
import net.cedu.entity.book.BookSupplier;
import net.cedu.entity.book.SettlementWay;
import net.cedu.entity.book.SupplierCategory;
/**
 * 书商修改加载
 * @author XFY
 *
 */
public class DoUpdateBookSupplierAction extends BaseAction {
 
	private static final long serialVersionUID = -4286223810951109509L;

	@Autowired
	private BookSupplierBiz bsbiz;
	
	@Autowired
	private SupplierCategoryBiz scbiz;
	@Autowired
	private SettlementWayBiz swbiz;
	
	private int id;
	private BookSupplier bs;
	private List<SupplierCategory> sclist=new ArrayList<SupplierCategory>();
	private List<SettlementWay> swlist=new ArrayList<SettlementWay>();
	
	public String execute()
	{
		try {
			bs=bsbiz.findBookSupplierById(id);
			sclist=scbiz.findAllSupplierCategory();
			swlist=swbiz.findAllSettlementWay();
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BookSupplier getBs() {
		return bs;
	}

	public void setBs(BookSupplier bs) {
		this.bs = bs;
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
