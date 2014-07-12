package net.cedu.action.meterial.meterial;

import net.cedu.action.BaseAction;
import net.cedu.biz.meterial.biz.MeterialBiz;
import net.cedu.entity.meterial.Meterial;


import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 查看物料详细
 * @author YY
 *
 */
public class FindMeterialAction extends BaseAction implements
		ModelDriven<Meterial> {

	private static final long serialVersionUID = -7155231248863535354L;

	@Autowired
	private MeterialBiz meterialbiz; //物料业务层
	
	private Meterial meterial=new Meterial(); //物料实体


	public String execute() {
		try {
			this.meterial = meterialbiz.findById(meterial.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	public Meterial getMeterial() {
		return meterial;
	}

	public void setMeterial(Meterial meterial) {
		this.meterial = meterial;
	}

	public Meterial getModel() {
		return meterial;
	}
 
}
