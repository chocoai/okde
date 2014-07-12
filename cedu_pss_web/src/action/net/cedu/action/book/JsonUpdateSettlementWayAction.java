package net.cedu.action.book;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.SettlementWayBiz;
import net.cedu.entity.book.SettlementWay;
/**
 * 结算方式修改
 * @author XFY
 *
 */
@ParentPackage("json-default")
public class JsonUpdateSettlementWayAction extends BaseAction {
 
	private static final long serialVersionUID = 5777276072501310919L;

	@Autowired
	private SettlementWayBiz settlementwayBiz;
	
	private SettlementWay settlementway;
	private boolean results=false;
	
	
	@Action(value="update_settlementway",
			results={@Result(name="success",type="json",params={
				"contentType","text/json",
				"includeproperties","results"
			})})
	public String exectue()
	{
		try {
			if(settlementway!=null)
			{
				if(settlementway.getId()!=0)
				{
					SettlementWay sw=settlementwayBiz.findSettlementWayById(settlementway.getId());
					sw.setUpdatedTime(new Date());
					sw.setUpdater_id(this.getUser().getUserId());
					sw.setCode(settlementway.getCode());
					sw.setName(settlementway.getName());
					SettlementWay way=settlementwayBiz.findByNameOrCodeSettlementWay(settlementway.getCode(), settlementway.getName());
					if(null==way){
					settlementwayBiz.updateSettlementWay(settlementway);
					results=true;
					}else
					{
						results=false;
					}
				}
			}
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return SUCCESS;
	}

	

	public SettlementWay getSettlementway() {
		return settlementway;
	}



	public void setSettlementway(SettlementWay settlementway) {
		this.settlementway = settlementway;
	}



	public boolean isResults() {
		return results;
	}

	public void setResults(boolean results) {
		this.results = results;
	}
	
}
