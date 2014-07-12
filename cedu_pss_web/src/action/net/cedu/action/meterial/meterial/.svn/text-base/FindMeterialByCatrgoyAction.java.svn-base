package net.cedu.action.meterial.meterial;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.meterial.biz.impl.MeterialBizImpl;
import net.cedu.entity.meterial.Meterial;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 按类型查看物料
 * @author YY
 *
 */
public class FindMeterialByCatrgoyAction extends BaseAction {
 
	private static final long serialVersionUID = -4942240624550975974L;

	@Autowired
	private MeterialBizImpl meterialbiz; //物料业务层

	private List<Meterial> list = new ArrayList<Meterial>(); //物料集合

	private Meterial meterial=new Meterial();//物料实体

	private int catrgory; //物料分类id


	@Action(value="criteria_meterialbycatrgory",results={@Result(name="success",location="list_meterialbycatrgory.jsp")})
	public String execute()
	{
		try{
		list=meterialbiz.findMeterialByCatrgoryid(catrgory);
		}catch (Exception e) {
			e.getStackTrace();			 
		}
		return SUCCESS;	
	}
	
	public Meterial getMeterial() {
		return meterial;
	}

	public void setMeterial(Meterial meterial) {
		this.meterial = meterial;
	}

	public List<Meterial> getList() {
		return list;
	}

	public void setList(List<Meterial> list) {
		this.list = list;
	}
	
	public int getCatrgory() {
		return catrgory;
	}

	public void setCatrgory(int catrgory) {
		this.catrgory = catrgory;
	}

 
}
