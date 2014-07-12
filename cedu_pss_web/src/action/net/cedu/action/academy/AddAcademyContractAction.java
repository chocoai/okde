package net.cedu.action.academy;



import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.academy.AcademyContractBiz;
import net.cedu.common.date.DateUtil;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.academy.AcademyContract;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 新增合同
 * 
 * @author gaolei
 * 
 */

public class AddAcademyContractAction extends BaseAction {

	
	
	@Autowired
	private AcademyContractBiz academycontractbiz;   //院校合同Biz
	@Autowired
	private AcademyBiz academybiz;   //院校Biz
	private AcademyContract academycontract;         //合同实体
	private int id;                                  //院校ID
	private int sibscriberId;                        //签约人ID
	private boolean bol;                             
	
	@Action(results = { @Result(name = "input", type="dispatcher", location = "add_academy_contract.jsp"),
						@Result(name = "success",type="redirect", location = "list_academy_contract?id=${id}&tab=3")})
	public String excute() throws Exception
	{
		//执行get请求
		if(super.isGetRequest())
		{
			return INPUT;
		}
		try {
			AcademyContract ac=academycontractbiz.findAcademyContractByNameAndDate(academycontract.getAcademyId(), academycontract.getName(), academycontract.getBeginDate(),academycontract.getEndDate() );
			if(ac!=null)
			{
				bol=true;
				
				return INPUT;
			}else{
			
			id=academycontract.getAcademyId();
			academycontract.setSibscriberId(super.getUser().getUserId());
			academycontract.setCreatorId(super.getUser().getUserId());
			academycontract.setCreatedTime(new Date());
			//执行添加操作
			if(academycontractbiz.addAcademyContract(academycontract))
			{
				 Academy academy=academybiz.findAcademyById(academycontract.getAcademyId());
				 if(academy!=null)
				 {
					 academy.setContractEndtime(DateUtil.getTimestamp(academycontract.getEndDate(),"yyyy-MM-dd"));
					 academy.setUpdaterId(super.getUser().getUserId());
					 academy.setUpdatedTime(new Date());
				 }
				 academybiz.updateAcademy(academy);
			}
			bol=false;
			return SUCCESS;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public AcademyContract getAcademycontract() {
		return academycontract;
	}

	public void setAcademycontract(AcademyContract academycontract) {
		this.academycontract = academycontract;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSibscriberId() {
		return sibscriberId;
	}

	public void setSibscriberId(int sibscriberId) {
		this.sibscriberId = sibscriberId;
	}

	public boolean isBol() {
		return bol;
	}

	public void setBol(boolean bol) {
		this.bol = bol;
	}

	
}
