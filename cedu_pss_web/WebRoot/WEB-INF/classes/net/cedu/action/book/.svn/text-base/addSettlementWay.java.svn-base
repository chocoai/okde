package net.cedu.action.book;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookCategoryBiz;
import net.cedu.biz.book.SettlementWayBiz;
import net.cedu.entity.book.BookCategory;
import net.cedu.entity.book.DeliveredWay;
import net.cedu.entity.book.SettlementWay;

/**
 * 添加结算方式
 * @author XFY
 *
 */
public class addSettlementWay extends BaseAction {
	@Autowired
	private SettlementWayBiz settlementwaybiz;
	
	private SettlementWay settlementway;
	
	private String resultiterate; //结果
	
	@Action(value="add_settlementway",results={@Result(type="redirect",location="index_settlement_way"),@Result(name="input",location="../book/error.jsp")})
	
	public String execute()
	{
		
		try {
			settlementway.setCreatorId(this.getUser().getUserId());
			settlementway.setCreatedTime(new Date());
			SettlementWay way=settlementwaybiz.findByNameOrCodeSettlementWay(settlementway.getCode(), settlementway.getName());
			if(null==way){
			settlementwaybiz.addSettlementWay(settlementway);
			}else
			{
				return INPUT;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	

	public SettlementWay getSettlementway() {
		return settlementway;
	}



	public void setSettlementway(SettlementWay settlementway) {
		this.settlementway = settlementway;
	}



	public String getResultiterate() {
		return resultiterate;
	}

	public void setResultiterate(String resultiterate) {
		this.resultiterate = resultiterate;
	}
	
	
	
}
