package net.cedu.action.meterial.meterialapplicationdetail;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.meterial.biz.MeterialApplicationBiz;
import net.cedu.biz.meterial.biz.MeterialApplicationDetailBiz;
import net.cedu.common.Constants;
import net.cedu.entity.meterial.MeterialApplication;
import net.cedu.entity.meterial.MeterialApplicationDetail;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 修改中心申请明细
 * @author YY
 *
 */
public class UpdateMeterialApplicationDetailAction extends BaseAction {
 
	private static final long serialVersionUID = -8858592520057432369L;
	@Autowired
	private MeterialApplicationDetailBiz meterialApplicationDetailBiz; //申请明细业务逻辑层
	@Autowired
	private MeterialApplicationBiz meterialApplicationBiz;//中心申请明务逻辑
	@Autowired
	private UserBiz userBiz;
	private MeterialApplicationDetail meterialapplicationdetail=new MeterialApplicationDetail();//申请明细实体
	private int meterid; //编号
	private List<MeterialApplicationDetail> list = new ArrayList<MeterialApplicationDetail>();	 //中心申请明细集合
	private String quntion; //再次派发数量
	private String applicationname; //申请人
	
	
	/*总部物料派发
	 * (non-Javadoc)
	 * @see net.cedu.action.BaseAction#execute()
	 */
	@Action(value = "update_meterial_application_detail", results = { @Result(name = "input", location = "update_meterial_application_detail.jsp") 	                                                                 ,@Result(name = "success", type = "redirect", location = "/meterial/meterialapplication/index_meterialapplication") })
	public String execute()
	{
		if (isGetRequest()) {
			try {
			//回填信息
			list=meterialApplicationDetailBiz.findById(meterid);
			applicationname=userBiz.findUserById(list.get(0).getCreatorId()).getUserName();
			} catch (Exception e) {
			e.printStackTrace();
		    }
		return INPUT;
		}
		try {
			
			list=meterialApplicationDetailBiz.findById(meterid);
			String[] quan =quntion.split(","); //转换成数组
			for (int i = 0; i <quan.length ; i++) {      //循环实现批量增加
				meterialapplicationdetail=list.get(i);
				int x=Integer.parseInt(quan[i])+list.get(i).getSuppliedCount();
				//判断是否超出申请数量
				if(meterialapplicationdetail.getAppliedCount()>=x)
				{				
				if(meterialapplicationdetail.getAppliedCount()==x)
				{
					MeterialApplication ma= meterialApplicationBiz.findbyId(meterialapplicationdetail.getApplicationId());
					ma.setStatus(Constants.STATUS_HAS_DISTRIBUTED);
					meterialApplicationBiz.updateMeterialApplication(ma);
				}
				meterialapplicationdetail.setSuppliedCount(x);
				meterialApplicationDetailBiz.UpdateMeterialApplicationDetail(meterialapplicationdetail);
				
				}else{	
					return INPUT;
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
			return INPUT;
		}
		
		return SUCCESS;
	}

	public MeterialApplicationDetail getMeterialapplicationdetail() {
		return meterialapplicationdetail;
	}

	public void setMeterialapplicationdetail(
			MeterialApplicationDetail meterialapplicationdetail) {
		this.meterialapplicationdetail = meterialapplicationdetail;
	}

 

	public List<MeterialApplicationDetail> getList() {
		return list;
	}

	public void setList(List<MeterialApplicationDetail> list) {
		this.list = list;
	}

	public String getQuntion() {
		return quntion;
	}

	public void setQuntion(String quntion) {
		this.quntion = quntion;
	}

	public int getMeterid() {
		return meterid;
	}

	public void setMeterid(int meterid) {
		this.meterid = meterid;
	}

	public String getApplicationname() {
		return applicationname;
	}

	public void setApplicationname(String applicationname) {
		this.applicationname = applicationname;
	}
	
}
