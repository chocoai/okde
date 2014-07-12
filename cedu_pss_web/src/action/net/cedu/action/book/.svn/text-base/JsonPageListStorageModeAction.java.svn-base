package net.cedu.action.book;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.StorageModeBiz;
import net.cedu.entity.book.StorageMode;
import net.cedu.model.page.PageResult;
/**
 * 查询入库方式列表_分页
 * @author XFY
 *
 */
@ParentPackage("json-default")
public class JsonPageListStorageModeAction extends BaseAction {
 
	private static final long serialVersionUID = 3810750554503623357L;

	@Autowired
	private StorageModeBiz storagemodebiz;
	
	private PageResult<StorageMode > result = new PageResult<StorageMode >();
	
	@Action(value="page_list_storagemode",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute()
	{
		try {
			result.setList(storagemodebiz.findAllStorageMode(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public PageResult<StorageMode> getResult() {
		return result;
	}

	public void setResult(PageResult<StorageMode> result) {
		this.result = result;
	}

	

	
}
