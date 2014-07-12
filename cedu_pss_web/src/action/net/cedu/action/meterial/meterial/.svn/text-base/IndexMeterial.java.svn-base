package net.cedu.action.meterial.meterial;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.meterial.biz.MeterialCategoryBiz;
import net.cedu.entity.meterial.MeterialCategory;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 显示所有物料
 * @author YY
 *
 */
public class IndexMeterial extends BaseAction {

 
	private static final long serialVersionUID = -1061005318645544465L;
	@Autowired
	private MeterialCategoryBiz meterialCategoryBiz; //物料分类业务层
	private List<MeterialCategory> list=new ArrayList<MeterialCategory>(); //物料分类集合
	

	public String execute()
	{
		try {
			list=meterialCategoryBiz.findall();
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return SUCCESS;
	}


	public List<MeterialCategory> getList() {
		return list;
	}


	public void setList(List<MeterialCategory> list) {
		this.list = list;
	}


	 
	
	
}
