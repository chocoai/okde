package net.cedu.action.book;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.SupplierCategoryBiz;
import net.cedu.entity.book.SupplierCategory;
/**
 * 书商分类修改
 * @author XFY
 *
 */
@ParentPackage("json-default")
public class JsonUpdateSupplierCategoryAction extends BaseAction {
	 
	private static final long serialVersionUID = -377560891612216754L;

	@Autowired
	private SupplierCategoryBiz suppliercategorybiz;
	
	private SupplierCategory suppliercategory;
	
	private boolean results=false;
	
	@Action(value="update_suppliercategory",
			results={@Result(name="success",type="json",params={
				"contentType","text/json",
				"includeproperties","results"
			})})
	public String execute()
	{
		try {
			if(suppliercategory!=null)
			{
				if(suppliercategory.getId()!=0)
				{
					SupplierCategory sc=suppliercategorybiz.findSupplierCategoryById(suppliercategory.getId());
					sc.setUpdaterId(this.getUser().getUserId());
					sc.setUpdatedTime(new Date());
					sc.setCode(suppliercategory.getCode());
					sc.setName(suppliercategory.getName());
					suppliercategorybiz.updateSupplierCategory(sc);
					results=true;
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}




	public SupplierCategory getSuppliercategory() {
		return suppliercategory;
	}




	public void setSuppliercategory(SupplierCategory suppliercategory) {
		this.suppliercategory = suppliercategory;
	}




	public boolean isResults() {
		return results;
	}


	public void setResults(boolean results) {
		this.results = results;
	}
	
	
}
