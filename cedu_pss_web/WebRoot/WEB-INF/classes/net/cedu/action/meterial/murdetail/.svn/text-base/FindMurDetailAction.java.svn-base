package net.cedu.action.meterial.murdetail;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
 
import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.meterial.biz.MeterialUsageRecordBiz;
import net.cedu.biz.meterial.biz.MurDetailBiz;
 
import net.cedu.entity.meterial.MeterialUsageRecord;
import net.cedu.entity.meterial.MurDetail;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 根据id查询领用单明细
 * @author YY
 *
 */
public class FindMurDetailAction extends BaseAction {
 
	private static final long serialVersionUID = -4440593108678230579L;

	@Autowired
	private MurDetailBiz murbiz; //领用单明细业务逻辑层
	@Autowired
	private MeterialUsageRecordBiz meterialUsageRecordBiz;//领用单业务逻辑层
	@Autowired
	private UserBiz userBiz; //用户实体业务逻辑层
	private int meterid; //领用单id

	private double avg; //总价格
	
	private List<MurDetail> list = new ArrayList<MurDetail>(); //领用单明细集合
	
	private String username; //领用人
	private String application; //领用单编号
	

	/*
	 * 根据id查询个人领用单详细 (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Action(value = "find_murdetail", results = { @Result(name = "success", location = "find_murdetail.jsp") })
	public String execute() {
		try {
			list = murbiz.findById(meterid);
			MeterialUsageRecord usageRecord=meterialUsageRecordBiz.findById(meterid);
			avg=usageRecord.getAmount();
			application=usageRecord.getCode();
			username=userBiz.findUserById(usageRecord.getCreatorId()).getUserName();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return SUCCESS;
	}

	public int getMeterid() {
		return meterid;
	}

	public void setMeterid(int meterid) {
		this.meterid = meterid;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public List<MurDetail> getList() {
		return list;
	}

	public void setList(List<MurDetail> list) {
		this.list = list;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

}
