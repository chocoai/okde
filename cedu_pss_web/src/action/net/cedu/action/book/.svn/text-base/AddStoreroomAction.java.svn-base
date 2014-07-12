package net.cedu.action.book;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.StoreroomBiz;
import net.cedu.entity.book.Storeroom;
/**
 * 库房位置增加
 * @author XFY
 *
 */

public class AddStoreroomAction extends BaseAction {
	 
	private static final long serialVersionUID = 2987122584831267547L;

	@Autowired
	private StoreroomBiz storeroombiz;
	
	private Storeroom storeroom;
	@Action(value="add_storeroom",results={@Result(name="success",type="redirect",location="index_storeroom"),@Result(name="input",location="../book/error.jsp")})
	public String execute()
	{
		try {
			
			storeroom.setCreatedTime(new Date());
			storeroom.setCreatorId(this.getUser().getUserId());
			Storeroom sr=storeroombiz.findByNameOrCodeStoreroom(storeroom.getCode(), storeroom.getName());
			if(null==sr)
			{
			storeroombiz.addStoreroomBiz(storeroom);
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

	public Storeroom getStoreroom() {
		return storeroom;
	}

	public void setStoreroom(Storeroom storeroom) {
		this.storeroom = storeroom;
	}
	
	
}
