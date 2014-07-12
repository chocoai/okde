package net.cedu.action.meterial.meterialapplication;

import net.cedu.action.BaseAction;
import net.cedu.biz.meterial.biz.MeterialApplicationBiz;
import net.cedu.entity.meterial.MeterialApplication;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 增加中心申请
 * @author YY
 *
 */
public class AddMeterialApplicationAction extends BaseAction {

 
	private static final long serialVersionUID = -2491143995759019684L;

	@Autowired
	private MeterialApplicationBiz meterialapplicationbiz;

	private MeterialApplication meterialapplication;

	public MeterialApplication getMeterialapplication() {
		return meterialapplication;
	}

	public void setMeterialapplication(MeterialApplication meterialapplication) {
		this.meterialapplication = meterialapplication;
	}

	//增加方法
	@Action(value = "add_meterialapplication", results = { @Result(name = "success", location = "add_meterialapplication.jsp") })
	public String excute() {
		try {
			meterialapplicationbiz.saveMeterialApplication(meterialapplication);
			meterialapplication.setUpdaterId(this.getUser().getUserId());
 
			 
			meterialapplication.setCreatorId(this.getUser().getUserId());
			
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			return INPUT;
		}
	}
	
	
}
