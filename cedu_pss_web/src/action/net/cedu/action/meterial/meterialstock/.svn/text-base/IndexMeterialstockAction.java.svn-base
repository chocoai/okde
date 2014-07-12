package net.cedu.action.meterial.meterialstock;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.meterial.biz.MeterialBiz;
import net.cedu.biz.meterial.biz.MeterialStockBiz;
import net.cedu.biz.meterial.biz.MeterialStoreroomBiz;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.meterial.Meterial;
import net.cedu.entity.meterial.MeterialStock;
import net.cedu.entity.meterial.MeterialStoreroom;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 库存
 * @author YY
 *
 */
public class IndexMeterialstockAction extends BaseAction  {
 
	private static final long serialVersionUID = 1501785438491374547L;

	@Autowired
	private MeterialStockBiz meterialStockBiz; //库存业务层
	@Autowired
	private MeterialBiz meterialBiz; //物料逻辑层
	
	@Autowired
	private MeterialStoreroomBiz meterialStoreroomBiz; //库房逻辑层
	@Autowired
	private BranchBiz branchBiz; //中心业务层
	
	
	private List<MeterialStock> list=new ArrayList<MeterialStock>(); //库存集合
	
	private MeterialStock meterialstock=new MeterialStock(); //库存实体
	
 
	
	private double com;
	
	@Action(results={@Result(name="success" ,location="index_meterialstock.jsp")})
	public String execute()
	{
		try {
			list=meterialStockBiz.findMeterialStockByStock(meterialstock);
			for (MeterialStock stock : list) {
				Meterial meterial=meterialBiz.findById(stock.getMeterialId());
				stock.setMeterialname(meterial.getName());
				stock.setMeterialpirce(meterial.getPrice().toString());
				MeterialStoreroom room=meterialStoreroomBiz.findMeterialStoreroomById(stock.getStoreroomId());
				stock.setStoreroomname(room.getName());
				Branch br=branchBiz.findBranchById(room.getPosition());
				stock.setStoreroomweizhi(br.getName());
				list.add(stock);
				com+=stock.getAmount();			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;

		}
		return SUCCESS;
	}


	public List<MeterialStock> getList() {
		return list;
	}


	public void setList(List<MeterialStock> list) {
		this.list = list;
	}


	public MeterialStock getMeterialstock() {
		return meterialstock;
	}


	public void setMeterialstock(MeterialStock meterialstock) {
		this.meterialstock = meterialstock;
	}


	public double getCom() {
		return com;
	}


	public void setCom(double com) {
		this.com = com;
	}

 
	
	
}
