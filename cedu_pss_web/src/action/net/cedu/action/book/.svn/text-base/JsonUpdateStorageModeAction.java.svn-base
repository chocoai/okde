package net.cedu.action.book;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.StorageModeBiz;
import net.cedu.entity.book.StorageMode;
/**
 * 入库方式修改
 * @author XFY
 *
 */
@ParentPackage("json-default")
public class JsonUpdateStorageModeAction extends BaseAction {
 
	private static final long serialVersionUID = -7258087994389927122L;


	@Autowired
	private StorageModeBiz storagemodebiz;
	
	
	private StorageMode storagemode;
	private boolean results=false;
	
	@Action(value="update_storagemode",results={@Result(
			name="success",type="json",params={
					"contentType","text/json","includeProperties","results"
			})})
	public String execute()
	{
		try {
			if(storagemode!=null)
			{
				if(storagemode.getId()!=0)
				{
					StorageMode sm=storagemodebiz.findStorageModeById(storagemode.getId());
					sm.setUpdatedTime(new Date());
					sm.setUpdater_id(this.getUser().getUserId());
					sm.setCode(storagemode.getCode());
					sm.setName(storagemode.getName());
					StorageMode mode=storagemodebiz.findByNameOrCodeStorageMode(storagemode.getCode(), storagemode.getName());
					if(null==mode)
					{
					storagemodebiz.updateStorageMode(sm);
					results=true;
					}else
					{
						results=false;
					}
					
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public StorageMode getStoragemode() {
		return storagemode;
	}

	public void setStoragemode(StorageMode storagemode) {
		this.storagemode = storagemode;
	}

	public boolean isResults() {
		return results;
	}

	public void setResults(boolean results) {
		this.results = results;
	}
	
	
}
