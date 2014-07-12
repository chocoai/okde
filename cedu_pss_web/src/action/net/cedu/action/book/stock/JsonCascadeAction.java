package net.cedu.action.book.stock;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.book.BookBiz;
import net.cedu.biz.book.StockBiz;
import net.cedu.biz.book.StoreroomBiz;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.book.Book;
import net.cedu.entity.book.Stock;
import net.cedu.entity.book.StockTransfer;
import net.cedu.entity.book.Storeroom;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonCascadeAction extends BaseAction {

	private static final long serialVersionUID = 5961058526799346888L;

	private List<StockTransfer> list = new ArrayList<StockTransfer>(); // 移库集合

	@Autowired
	private StoreroomBiz StoreroomBiz; // 库房设置业务逻辑
	private List<Storeroom> storerlist = new ArrayList<Storeroom>(); // 库房集合
	private List<Storeroom> fromlist = new ArrayList<Storeroom>(); // 移出库房名称集合
	private List<Storeroom> tolist = new ArrayList<Storeroom>(); // 移入库房名称集合
	@Autowired
	private BookBiz bookBiz;                                  //教材业务逻辑层
	private List<Book> booklist=new ArrayList<Book>();			//教材名称集合
	private int toId; // 移入库房id 
	private int FromId; // 移出库房id
	private String toname; // 移出库房位置
	private String fromname; // 移入库房位置

	@Autowired
	private StockBiz StockBiz; // 库存业务逻辑
	private Stock stock = new Stock();
	@Autowired
	private BranchBiz branchBiz; // 学习中心

	private StockTransfer Transfer=new StockTransfer(); // 移库实体

	// 库房AGAX方法
	@Action(value = "find_storeroom_ajax", results = { @Result(type = "json", name = "success", params = {
			"contentType", "text/json" }) })
	public String storeroom() {
		try {
			storerlist = StoreroomBiz.findStoreroomAll();
			if (0 < storerlist.size()) {
				for (Storeroom storeroom : storerlist) {
					Branch br = branchBiz.findBranchById(storeroom
							.getPosition());
					storeroom.setPositionName(br.getName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	// 移入库房AGAX方法
	@Action(value = "find_storeroom_toid_ajax", results = { @Result(type = "json", name = "success", params = {
			"contentType", "text/json" }) })
	public String storeroomtoid() {
		try {

			tolist = StoreroomBiz.findStoreroomByPosition(toId);
		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	// 移出库房AGAX方法
	@Action(value = "find_storeroom_fromid_ajax", results = { @Result(type = "json", name = "success", params = {
			"contentType", "text/json" }) })
	public String storeroomfromid() {
		try {

			fromlist = StoreroomBiz.findStoreroomByPosition(FromId);
			stock = StockBiz.findStockByroomId(FromId);
		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	// 根据移出库房id查询库存方法
	@Action(value = "find_stock_qunation_ajax", results = { @Result(type = "json", name = "success", params = {
			"contentType", "text/json" }) })
	public String findstockbyquantion() {
		try {
			stock = StockBiz.findStockByroomId(FromId);
		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	//查询所有教材的ajax方法
	@Action(value = "find_bookall_ajax", results = { @Result(type = "json", name = "success", params = {
			"contentType", "text/json" }) })
	public String findStockAll()
	{
		try {
			booklist=bookBiz.findBookAll();
		} catch (Exception e) {
			 
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
		
	}

	public int getFromId() {
		return FromId;
	}

	public void setFromId(int fromId) {
		FromId = fromId;
	}

	public int getToId() {
		return toId;
	}

	public void setToId(int toId) {
		this.toId = toId;
	}

 
	public String getToname() {
		return toname;
	}

	public void setToname(String toname) {
		this.toname = toname;
	}

	public String getFromname() {
		return fromname;
	}

	public void setFromname(String fromname) {
		this.fromname = fromname;
	}

	public List<StockTransfer> getList() {
		return list;
	}

	public void setList(List<StockTransfer> list) {
		this.list = list;
	}

	public List<Storeroom> getStorerlist() {
		return storerlist;
	}

	public void setStorerlist(List<Storeroom> storerlist) {
		this.storerlist = storerlist;
	}

	public List<Storeroom> getFromlist() {
		return fromlist;
	}

	public void setFromlist(List<Storeroom> fromlist) {
		this.fromlist = fromlist;
	}

	public List<Storeroom> getTolist() {
		return tolist;
	}

	public void setTolist(List<Storeroom> tolist) {
		this.tolist = tolist;
	}

	 

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public List<Book> getBooklist() {
		return booklist;
	}

	public void setBooklist(List<Book> booklist) {
		this.booklist = booklist;
	}

	public StockTransfer getTransfer() {
		return Transfer;
	}

	public void setTransfer(StockTransfer transfer) {
		Transfer = transfer;
	}
	
}
