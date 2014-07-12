package net.cedu.action.book;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.StorageModeBiz;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 入库方式删除
 * @author XFY
 *
 */
@ParentPackage("json-default")
public class JsonDeleteStorageModeAction extends BaseAction {
 
	private static final long serialVersionUID = -6234372671818301511L;

	@Autowired
	private StorageModeBiz storagemodebiz;
	
	private int id;
	private boolean results=false;
	
	@Action(value="delete_storagemode",results={@Result(
			name="success",type="json",params={
					"contentType","text/json","includeProperties","results"
			})})
	public String execute()
	{
		try {
			storagemodebiz.deleteStorageMode(id);
			results=true;
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isResults() {
		return results;
	}

	public void setResults(boolean results) {
		this.results = results;
	}
	
	
	
}
