package net.cedu.action.meterial.meterialstock;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.meterial.biz.MeterialBiz;
import net.cedu.biz.meterial.biz.MeterialCategoryBiz;
import net.cedu.biz.meterial.biz.MeterialStockBiz;
import net.cedu.biz.meterial.biz.MeterialStoreroomBiz;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.meterial.Meterial;
import net.cedu.entity.meterial.MeterialCategory;
import net.cedu.entity.meterial.MeterialStock;
import net.cedu.entity.meterial.MeterialStoreroom;
import net.cedu.entity.meterial.MeterialTransfer;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

 
/**
 * json级联库房位置 物料分类
 * @author YY
 *
 */
@ParentPackage("json-default")
public class JsonCascadeMeterialAction extends BaseAction      {

	private static final long serialVersionUID = 2896388062903923861L;
 
	private List<MeterialTransfer> list = new ArrayList<MeterialTransfer>(); // 移库集合
	
	@Autowired
	private MeterialCategoryBiz meterialCategoryBiz; // 物料分類业务逻辑
	private List<MeterialCategory> catrgorylist = new ArrayList<MeterialCategory>(); // 物料分类集合
	
	@Autowired
	private MeterialBiz meterialBiz; // 物料业务逻辑
	private List<Meterial> meteriallist = new ArrayList<Meterial>(); // 物料集合
	private int meterialId; //物料分类ID
	
	@Autowired
	private MeterialStoreroomBiz meterialStoreroomBiz; // 物料业务逻辑
	private List<MeterialStoreroom> storerlist = new ArrayList<MeterialStoreroom>(); // 库房集合
	private List<MeterialStoreroom> fromlist = new ArrayList<MeterialStoreroom>(); //移出库房名称集合
	private List<MeterialStoreroom> tolist = new ArrayList<MeterialStoreroom>(); //移入库房名称集合
	private int toId;    //移入库房id
	private int FromId;   //移出库房id
	private String toname; //移出库房位置
	private String fromname; //移入库房位置

	@Autowired
	private MeterialStockBiz meterialStockBiz; //物料库存业务逻辑
	private MeterialStock meterialStock=new MeterialStock();
	@Autowired
	private BranchBiz branchBiz; //学习中心
	
	PageResult<MeterialTransfer> result = new PageResult<MeterialTransfer>(); // 分頁參數

	private MeterialTransfer meterialtransfer = new MeterialTransfer(); // 移库实体


	
	

	 

	// 物料分類AGAX方法
	@Action(value = "find_meterialcatrgory_ajax", results = { @Result(type = "json", name = "success", params = {
			"contentType", "text/json" }) })
	public String all() {
		try {

			catrgorylist = meterialCategoryBiz.findall();
		} catch (Exception e) {

			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	// 物料名称AGAX方法
	@Action(value = "find_meterial_ajax", results = { @Result(type = "json", name = "success", params = {
			"contentType", "text/json" }) })
	public String meterialall() {
		try {

			meteriallist = meterialBiz.findMeterialByCatrgoryid(meterialId);
		} catch (Exception e) {

			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	// 库房AGAX方法
	@Action(value = "find_meterialstoreroom_ajax", results = { @Result(type = "json", name = "success", params = {
			"contentType", "text/json" }) })
	public String storeroom() {
		try {
			List<MeterialStoreroom> lists=meterialStoreroomBiz.finMeterialStoreroomall();
			if(0<lists.size())
			{
				for (MeterialStoreroom Storeroom : lists) {
				 Branch br=branchBiz.findBranchById(Storeroom.getPosition());
					if(null!=br)	
					{
					 Storeroom.setPositionName(br.getName());
						storerlist.add(Storeroom);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}
	
	// 移入库房AGAX方法
	@Action(value = "find_meterialstoreroom_toid_ajax", results = { @Result(type = "json", name = "success", params = {
			"contentType", "text/json" }) })
	public String storeroomtoid() {
		try {
			 
			tolist = meterialStoreroomBiz.findMeterialStoreroomByPosition(toId);
		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}
	
	// 移出库房AGAX方法
	@Action(value = "find_meterialstoreroom_fromid_ajax", results = { @Result(type = "json", name = "success", params = {
			"contentType", "text/json" }) })
	public String storeroomfromid() {
		try {
			 
			fromlist = meterialStoreroomBiz.findMeterialStoreroomByPosition(FromId);
		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}
	
	
	// 根据移出库房id查询库存方法
	@Action(value = "find_stock_qunation", results = { @Result(type = "json", name = "success", params = {
			"contentType", "text/json" }) })
	public String findstockbyquantion() {
		try {
			meterialStock = meterialStockBiz.findMeterialStockByroomId(meterialtransfer.getFromId());
		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

 

	public PageResult<MeterialTransfer> getResult() {
		return result;
	}

	public void setResult(PageResult<MeterialTransfer> result) {
		this.result = result;
	}

	public MeterialTransfer getMeterialtransfer() {
		return meterialtransfer;
	}

	public void setMeterialtransfer(MeterialTransfer meterialtransfer) {
		this.meterialtransfer = meterialtransfer;
	}

	public List<MeterialTransfer> getList() {
		return list;
	}

	public void setList(List<MeterialTransfer> list) {
		this.list = list;
	}

	public int getFromId() {
		return FromId;
	}

	public void setFromId(int fromId) {
		FromId = fromId;
	}

	public List<MeterialCategory> getCatrgorylist() {
		return catrgorylist;
	}

	public void setCatrgorylist(List<MeterialCategory> catrgorylist) {
		this.catrgorylist = catrgorylist;
	}

	public List<Meterial> getMeteriallist() {
		return meteriallist;
	}

	public void setMeteriallist(List<Meterial> meteriallist) {
		this.meteriallist = meteriallist;
	}

	public List<MeterialStoreroom> getStorerlist() {
		return storerlist;
	}

	public void setStorerlist(List<MeterialStoreroom> storerlist) {
		this.storerlist = storerlist;
	}

	public int getMeterialId() {
		return meterialId;
	}

	public void setMeterialId(int meterialId) {
		this.meterialId = meterialId;
	}

	public int getToId() {
		return toId;
	}

	public void setToId(int toId) {
		this.toId = toId;
	}

	public MeterialTransfer getModel() {
		   
		return meterialtransfer;
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

	public List<MeterialStoreroom> getFromlist() {
		return fromlist;
	}

	public void setFromlist(List<MeterialStoreroom> fromlist) {
		this.fromlist = fromlist;
	}

	public List<MeterialStoreroom> getTolist() {
		return tolist;
	}

	public void setTolist(List<MeterialStoreroom> tolist) {
		this.tolist = tolist;
	}

	public MeterialStock getMeterialStock() {
		return meterialStock;
	}

	public void setMeterialStock(MeterialStock meterialStock) {
		this.meterialStock = meterialStock;
	}
	
	
}
