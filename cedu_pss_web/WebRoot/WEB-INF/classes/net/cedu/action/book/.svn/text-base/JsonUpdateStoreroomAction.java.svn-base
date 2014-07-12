package net.cedu.action.book;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.StoreroomBiz;
import net.cedu.entity.book.Storeroom;
/**
 * 库房位置修改
 * @author XFY
 *
 */
@ParentPackage("json-default")
public class JsonUpdateStoreroomAction extends BaseAction {
 
	private static final long serialVersionUID = -2357882971439815568L;

	@Autowired
	private StoreroomBiz storeroombiz;
	
	private Storeroom storeroom=new Storeroom();
	private boolean results=false;
	
	@Action(value="update_storeroom",results={@Result(
			name="success",type="json",params={
					"contentType","text/json","includeProperties","results"
			})})
	public String execute()
	{
		try {
			if(storeroom!=null)
			{
				if(storeroom.getId()!=0)
				{
				
					Storeroom s=storeroombiz.findStoreroomById(storeroom.getId());
					s.setUpdatedTime(new Date());
					s.setUpdater_id(this.getUser().getUserId());
					s.setCode(storeroom.getCode());
					s.setName(storeroom.getName());
					s.setTypes(storeroom.getTypes());
					s.setPosition(storeroom.getPosition());
					Storeroom sr=storeroombiz.findByNameOrCodeStoreroom(storeroom.getCode(), storeroom.getName());
					if(null==sr)
					{
					storeroombiz.updateStoreroom(s);
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

	public Storeroom getStoreroom() {
		return storeroom;
	}

	public void setStoreroom(Storeroom storeroom) {
		this.storeroom = storeroom;
	}

	public boolean isResults() {
		return results;
	}

	public void setResults(boolean results) {
		this.results = results;
	}
	
	
}
