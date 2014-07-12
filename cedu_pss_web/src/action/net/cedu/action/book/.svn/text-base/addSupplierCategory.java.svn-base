package net.cedu.action.book;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.SupplierCategoryBiz;
import net.cedu.entity.book.SupplierCategory;

/**
 * 添加教材分类
 * @author XFY
 *
 */
public class addSupplierCategory extends BaseAction {
 
	private static final long serialVersionUID = 2130354788438418746L;

	@Autowired
	private SupplierCategoryBiz suppliercategorybiz;
	
	private SupplierCategory suppliercategory;
	
	private String resultiterate; //结果
	
	@Action(value="add_suppliercategory",results={@Result(type="redirect",location="index_supplier_category"),@Result(name="input",location="/book/add_suppliercategorys")})
	public String execute()
	{
		try {
			suppliercategory.setCreatorId(this.getUser().getUserId());
			suppliercategory.setCreatedTime(new Date());
			suppliercategorybiz.addSupplierCategory(suppliercategory);
		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	
	public SupplierCategory getSuppliercategory() {
		return suppliercategory;
	}


	public void setSuppliercategory(SupplierCategory suppliercategory) {
		this.suppliercategory = suppliercategory;
	}


	public String getResultiterate() {
		return resultiterate;
	}

	public void setResultiterate(String resultiterate) {
		this.resultiterate = resultiterate;
	}
	
	
	
}
