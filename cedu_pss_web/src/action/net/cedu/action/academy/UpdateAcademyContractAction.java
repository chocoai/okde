package net.cedu.action.academy;



import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyContractBiz;
import net.cedu.entity.academy.AcademyContract;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 修改合同
 * 
 * @author gaolei
 * 
 */

public class UpdateAcademyContractAction extends BaseAction {

	
	
	@Autowired
	private AcademyContractBiz academycontractbiz;   //院校合同Biz
	private AcademyContract academycontract;         //合同实体
	private int id;                                  //院校ID
	private int sibscriberId;                        //签约人ID
	
	
	
	/**
	 * 修改院校合同
	 * @return
	 * @throws Exception
	 */
	@Action(results = { @Result(name = "input", location = "update_academy_contract.jsp"),
						@Result(name = "success",type="redirect", location = "list_academy_contract?id=${id}&tab=3")})
	public String excute() throws Exception
	{
		
		if(super.isGetRequest())
		{
			//获取该合同详细数据
			academycontract= academycontractbiz.findAcademyContractById(id);
			return INPUT;
		}
		try {
			//获取该合同原始详细数据
			AcademyContract ac= academycontractbiz.findAcademyContractById(academycontract.getId());
			id=ac.getAcademyId();
			if(academycontract!=null)
			{
				ac.setSignupDate(academycontract.getSignupDate());
				ac.setBeginDate(academycontract.getBeginDate());
				ac.setEndDate(academycontract.getEndDate());
				ac.setName(academycontract.getName());
				ac.setNote(academycontract.getNote());	
			}
			ac.setUpdaterId(super.getUser().getUserId());
			ac.setUpdatedTime(new Date());
			//执行修改
			academycontractbiz.updateAcademyContract(ac);
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

	
}
