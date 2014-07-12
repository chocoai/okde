package net.cedu.action.book;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.DeliveredWayBiz;
import net.cedu.entity.book.DeliveredWay;
/**
 * 配送方式修改
 * @author XFY
 *
 */
@ParentPackage("json-default")
public class JsonUpdateDeliveredWayAction extends BaseAction {
 
	private static final long serialVersionUID = -1910708036799662371L;

	@Autowired
	private DeliveredWayBiz deliveredwaybiz;
	
	private DeliveredWay deliveredway;
	private boolean results=false;
	
	
	@Action(value = "update_deliveredway", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","results"
			}) })
	public String execute()
	{
		try {
				if(deliveredway!=null)
				{
					if(deliveredway.getId()!=0)
					{
						DeliveredWay dw=deliveredwaybiz.findDeliveredWayById(deliveredway.getId());
						dw.setUpdatedTime(new Date());
						dw.setUpdater_id(this.getUser().getUserId());
						dw.setCode(deliveredway.getCode());
						dw.setName(deliveredway.getName());
						DeliveredWay way=deliveredwaybiz.findByNameOrCodeDeliveredWay(deliveredway.getCode(), deliveredway.getName());
						if(null==way){
						deliveredwaybiz.updateDeliveredWay(deliveredway);
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



	public DeliveredWay getDeliveredway() {
		return deliveredway;
	}



	public void setDeliveredway(DeliveredWay deliveredway) {
		this.deliveredway = deliveredway;
	}



	public boolean isResults() {
		return results;
	}

	public void setResults(boolean results) {
		this.results = results;
	}
	
	
}
