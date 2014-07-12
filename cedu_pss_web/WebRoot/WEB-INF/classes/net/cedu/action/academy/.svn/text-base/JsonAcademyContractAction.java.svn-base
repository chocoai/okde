package net.cedu.action.academy;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyContractBiz;
import net.cedu.entity.academy.AcademyContract;
import net.cedu.model.page.PageResult;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * json院校合同
 * @author gaolei
 *
 */
@ParentPackage("json-default")
public class JsonAcademyContractAction extends BaseAction {

	@Autowired
	private AcademyContractBiz academycontractbiz;           //院校合同Biz
	// 分页结果
	private PageResult<AcademyContract> result = new PageResult<AcademyContract>();
	private AcademyContract academycontract;                 //合同实体
	private int id;                                          //院校合同ID
	private int academyId;                                   //院校ID
	private int sibscriberId;                                //签约人ID
    
	
	/**
	 * 删除合同
	 * @return
	 * @throws Exception
	 */
	@Action(value = "deleteacademycontract", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String DeleteAcademyContract() throws Exception {
		
		try {
			//执行删除操作
			academycontractbiz.deleteAcademyContract(id);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 查询合同(数量)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "countacademycontract", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"result.*,academyId"
		}) })
	public String AcademyContractCount() throws Exception
	{	
		try {
			//查询数量
			int count=academycontractbiz.findAllAcademyContractCount(academyId,result);
			result.setRecordCount(count);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	/**
	 * 查询合同(数据)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "listacademycontract", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"result.*,academyId"
	}) })
	public String AcademyContractList() throws Exception
	{
		try {	
			
			//获取合同数据集合
			result.setList(academycontractbiz.findAllAcademyContract(academyId,result));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public PageResult<AcademyContract> getResult() {
		return result;
	}

	public void setResult(PageResult<AcademyContract> result) {
		this.result = result;
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

	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public int getSibscriberId() {
		return sibscriberId;
	}

	public void setSibscriberId(int sibscriberId) {
		this.sibscriberId = sibscriberId;
	}
	
	
}
