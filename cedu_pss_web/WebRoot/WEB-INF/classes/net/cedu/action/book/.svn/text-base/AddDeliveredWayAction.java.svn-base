package net.cedu.action.book;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.DeliveredWayBiz;
import net.cedu.entity.book.DeliveredWay;
/**
 *配送方式增加
 * @author XFY
 *
 */
public class AddDeliveredWayAction extends BaseAction {
	@Autowired
	private DeliveredWayBiz deliveredwaybiz;
	
	private DeliveredWay deliveredway;
	
	@Action(value="add_deliveredway",results={@Result(type="redirect",location="index_delivered_way"),@Result(name="input",location="../book/error.jsp")})
	public String execute()
	{
		try {
			DeliveredWay way=deliveredwaybiz.findByNameOrCodeDeliveredWay(deliveredway.getCode(), deliveredway.getName());
			if(null==way){
			deliveredwaybiz.addDeliveredWay(deliveredway);
			}else
			{
				return INPUT;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	
	
}
