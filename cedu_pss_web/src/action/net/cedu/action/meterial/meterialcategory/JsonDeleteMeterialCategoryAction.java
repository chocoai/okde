package net.cedu.action.meterial.meterialcategory;

import net.cedu.action.BaseAction;
import net.cedu.biz.meterial.biz.MeterialCategoryBiz;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 删除物料分类
 * @author YY
 *
 */
@ParentPackage("json-default")
public class JsonDeleteMeterialCategoryAction extends BaseAction {
 
	private static final long serialVersionUID = -264830801514536028L;

	@Autowired
	private MeterialCategoryBiz meterialcagegorybiz; //物料分类业务层
	
	private int id;  //物料分类id
	private boolean results; //判断参数
	/**
	 * 删除物料分类AJAX方法
	 */
	@Action(value="delete_meterialcategory",results={@Result(
			name="success",type="json",params={
					"contentType","text/json","includeProperties","results"
			})})
	public String execute()
	{
		try {
			meterialcagegorybiz.deleteMeterialCategoryById(id);
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
