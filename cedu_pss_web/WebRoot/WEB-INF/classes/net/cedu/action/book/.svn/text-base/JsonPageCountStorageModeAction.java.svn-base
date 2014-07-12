package net.cedu.action.book;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.StorageModeBiz;
import net.cedu.entity.book.StorageMode;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 查询入库方式条数_分页
 * @author XFY
 *
 */
@ParentPackage("json-default")
public class JsonPageCountStorageModeAction extends BaseAction {
 
	private static final long serialVersionUID = -2402641005774940169L;

	@Autowired
	private StorageModeBiz storagemodebiz;
	
	private PageResult<StorageMode> result = new PageResult<StorageMode>();
	/**
	 * 根据条件查询教材分类数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "page_count_storagemode", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute() throws Exception 
	{
		result.setRecordCount(storagemodebiz.findAllStorageModeBCount(result));
		return SUCCESS;
	}
	public PageResult<StorageMode> getResult() {
		return result;
	}
	public void setResult(PageResult<StorageMode> result) {
		this.result = result;
	}
	
	
}
