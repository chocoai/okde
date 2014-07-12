package net.cedu.action.meterial.meterialcategory;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.meterial.biz.MeterialStorageBiz;
import net.cedu.entity.meterial.MeterialStorage;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/*
 * 入库方式 查询全部 
 *   YY
 */
public class IndexMeterialstorageAction extends BaseAction {
 
	private static final long serialVersionUID = 4531826765568823255L;

	@Autowired
	private MeterialStorageBiz meterialStoragebiz;  //入库方式业务层
	
	List<MeterialStorage> list= new ArrayList<MeterialStorage>();//入库方式集合

	@Action(value="index_meterialstorage",results={@Result(name="success",location="index_meterialstorage.jsp")})
	public String execute(){
		
		try {
			list=meterialStoragebiz.finmeterialstorageall();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public List<MeterialStorage> getList() {
		return list;
	}

	public void setList(List<MeterialStorage> list) {
		this.list = list;
	}
	
	
}
