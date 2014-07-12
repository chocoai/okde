package net.cedu.action.book;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.StorageModeBiz;
import net.cedu.entity.book.StorageMode;

/**
 * 添加配送方式
 * @author XFY
 *
 */
public class addStorageMode extends BaseAction {
	@Autowired
	private StorageModeBiz storagemodebiz;
	
	private StorageMode storagemode;
	
	private String resultiterate; //结果
	
	@Action(value="add_storagemode",results={@Result(type="redirect",location="index_storage_mode"),@Result(name="input",location="../book/error.jsp")})
	
	public String execute()
	{	
		try {
			storagemode.setCreatorId(this.getUser().getUserId());
			storagemode.setCreatedTime(new Date());
			StorageMode mode=storagemodebiz.findByNameOrCodeStorageMode(storagemode.getCode(), storagemode.getName());
			if(null==mode)
			{
			storagemodebiz.addStorageMode(storagemode);
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

	public StorageMode getStoragemode() {
		return storagemode;
	}

	public void setStoragemode(StorageMode storagemode) {
		this.storagemode = storagemode;
	}

	public String getResultiterate() {
		return resultiterate;
	}

	public void setResultiterate(String resultiterate) {
		this.resultiterate = resultiterate;
	}
		
}
